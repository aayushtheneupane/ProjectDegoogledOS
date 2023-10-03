package p000;

import androidx.work.impl.WorkDatabase;

/* renamed from: amh */
/* compiled from: PG */
public final class amh implements Runnable {

    /* renamed from: a */
    private final aip f765a;

    /* renamed from: b */
    private final String f766b;

    /* renamed from: c */
    private final boolean f767c;

    static {
        iol.m14236b("StopWorkRunnable");
    }

    public amh(aip aip, String str, boolean z) {
        this.f765a = aip;
        this.f766b = str;
        this.f767c = z;
    }

    public final void run() {
        boolean z;
        WorkDatabase workDatabase = this.f765a.f554c;
        alh j = workDatabase.mo1226j();
        workDatabase.mo2845e();
        try {
            if (j.mo617f(this.f766b) == 2) {
                j.mo603a(1, this.f766b);
            }
            if (!this.f767c) {
                ahz ahz = this.f765a.f557f;
                String str = this.f766b;
                synchronized (ahz.f513e) {
                    iol.m14231a();
                    String.format("Processor stopping background work %s", new Object[]{str});
                    z = ahz.m517a(str, (ait) ahz.f511c.remove(str));
                }
            } else {
                ahz ahz2 = this.f765a.f557f;
                String str2 = this.f766b;
                synchronized (ahz2.f513e) {
                    iol.m14231a();
                    String.format("Processor stopping foreground work %s", new Object[]{str2});
                    z = ahz.m517a(str2, (ait) ahz2.f510b.remove(str2));
                }
            }
            iol.m14231a();
            String.format("StopWorkRunnable for %s; Processor.stopWork = %s", new Object[]{this.f766b, Boolean.valueOf(z)});
            workDatabase.mo2847g();
            workDatabase.mo2846f();
        } catch (Throwable th) {
            workDatabase.mo2846f();
            throw th;
        }
    }
}
