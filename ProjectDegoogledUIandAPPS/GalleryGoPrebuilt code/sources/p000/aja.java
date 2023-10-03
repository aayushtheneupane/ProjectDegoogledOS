package p000;

import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* renamed from: aja */
/* compiled from: PG */
public final class aja implements ajk, ahw, aml {

    /* renamed from: a */
    public final Context f610a;

    /* renamed from: b */
    public final int f611b;

    /* renamed from: c */
    public final String f612c;

    /* renamed from: d */
    public final ajf f613d;

    /* renamed from: e */
    public final ajl f614e;

    /* renamed from: f */
    public PowerManager.WakeLock f615f;

    /* renamed from: g */
    public boolean f616g = false;

    /* renamed from: h */
    private final Object f617h = new Object();

    /* renamed from: i */
    private int f618i = 0;

    static {
        iol.m14236b("DelayMetCommandHandler");
    }

    public aja(Context context, int i, String str, ajf ajf) {
        this.f610a = context;
        this.f611b = i;
        this.f613d = ajf;
        this.f612c = str;
        this.f614e = new ajl(this.f610a, ajf.f626c, this);
    }

    /* renamed from: b */
    private final void m584b() {
        synchronized (this.f617h) {
            this.f614e.mo551a();
            this.f613d.f627d.mo648a(this.f612c);
            PowerManager.WakeLock wakeLock = this.f615f;
            if (wakeLock != null && wakeLock.isHeld()) {
                iol.m14231a();
                String.format("Releasing wakelock %s for WorkSpec %s", new Object[]{this.f615f, this.f612c});
                this.f615f.release();
            }
        }
    }

    /* renamed from: a */
    public final void mo531a(List list) {
        if (list.contains(this.f612c)) {
            synchronized (this.f617h) {
                if (this.f618i == 0) {
                    this.f618i = 1;
                    iol.m14231a();
                    String.format("onAllConstraintsMet for %s", new Object[]{this.f612c});
                    if (this.f613d.f628e.mo507a(this.f612c, (ckx) null)) {
                        amn amn = this.f613d.f627d;
                        String str = this.f612c;
                        synchronized (amn.f777d) {
                            iol.m14231a();
                            String.format("Starting timer for %s", new Object[]{str});
                            amn.mo648a(str);
                            amm amm = new amm(amn, str);
                            amn.f775b.put(str, amm);
                            amn.f776c.put(str, this);
                            amn.f774a.schedule(amm, 600000, TimeUnit.MILLISECONDS);
                        }
                    } else {
                        m584b();
                    }
                } else {
                    iol.m14231a();
                    String.format("Already started work for %s", new Object[]{this.f612c});
                }
            }
        }
    }

    /* renamed from: b */
    public final void mo532b(List list) {
        mo536a();
    }

    /* renamed from: a */
    public final void mo503a(String str, boolean z) {
        iol.m14231a();
        String.format("onExecuted %s, %s", new Object[]{str, Boolean.valueOf(z)});
        m584b();
        if (z) {
            Intent a = aiw.m576a(this.f610a, this.f612c);
            ajf ajf = this.f613d;
            ajf.mo544a(new ajc(ajf, a, this.f611b));
        }
        if (this.f616g) {
            Intent a2 = aiw.m575a(this.f610a);
            ajf ajf2 = this.f613d;
            ajf2.mo544a(new ajc(ajf2, a2, this.f611b));
        }
    }

    /* renamed from: a */
    public final void mo537a(String str) {
        iol.m14231a();
        String.format("Exceeded time limits on execution for %s", new Object[]{str});
        mo536a();
    }

    /* renamed from: a */
    public final void mo536a() {
        boolean z;
        synchronized (this.f617h) {
            if (this.f618i < 2) {
                this.f618i = 2;
                iol.m14231a();
                String.format("Stopping work for WorkSpec %s", new Object[]{this.f612c});
                Intent c = aiw.m579c(this.f610a, this.f612c);
                ajf ajf = this.f613d;
                ajf.mo544a(new ajc(ajf, c, this.f611b));
                ahz ahz = this.f613d.f628e;
                String str = this.f612c;
                synchronized (ahz.f513e) {
                    if (ahz.f511c.containsKey(str) || ahz.f510b.containsKey(str)) {
                        z = true;
                    } else {
                        z = false;
                    }
                }
                if (z) {
                    iol.m14231a();
                    String.format("WorkSpec %s needs to be rescheduled", new Object[]{this.f612c});
                    Intent a = aiw.m576a(this.f610a, this.f612c);
                    ajf ajf2 = this.f613d;
                    ajf2.mo544a(new ajc(ajf2, a, this.f611b));
                } else {
                    iol.m14231a();
                    String.format("Processor does not have WorkSpec %s. No need to reschedule ", new Object[]{this.f612c});
                }
            } else {
                iol.m14231a();
                String.format("Already stopped work for %s", new Object[]{this.f612c});
            }
        }
    }
}
