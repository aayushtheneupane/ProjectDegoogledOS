package p000;

import android.content.Context;
import androidx.work.ListenableWorker;
import androidx.work.impl.WorkDatabase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* renamed from: ahz */
/* compiled from: PG */
public final class ahz implements ahw {

    /* renamed from: a */
    public final Context f509a;

    /* renamed from: b */
    public final Map f510b = new HashMap();

    /* renamed from: c */
    public final Map f511c = new HashMap();

    /* renamed from: d */
    public final Set f512d;

    /* renamed from: e */
    public final Object f513e;

    /* renamed from: f */
    private final agz f514f;

    /* renamed from: g */
    private final amz f515g;

    /* renamed from: h */
    private final WorkDatabase f516h;

    /* renamed from: i */
    private final List f517i;

    /* renamed from: j */
    private final List f518j;

    static {
        iol.m14236b("Processor");
    }

    public ahz(Context context, agz agz, amz amz, WorkDatabase workDatabase, List list) {
        this.f509a = context;
        this.f514f = agz;
        this.f515g = amz;
        this.f516h = workDatabase;
        this.f517i = list;
        this.f512d = new HashSet();
        this.f518j = new ArrayList();
        this.f513e = new Object();
    }

    /* renamed from: a */
    public final void mo506a(ahw ahw) {
        synchronized (this.f513e) {
            this.f518j.add(ahw);
        }
    }

    /* renamed from: a */
    public static boolean m517a(String str, ait ait) {
        boolean z;
        if (ait != null) {
            ait.f577e = true;
            ait.mo529b();
            ieh ieh = ait.f576d;
            if (ieh != null) {
                z = ieh.isDone();
                ait.f576d.cancel(true);
            } else {
                z = false;
            }
            ListenableWorker listenableWorker = ait.f575c;
            if (listenableWorker != null && !z) {
                listenableWorker.mo1222d();
            } else {
                String.format("WorkSpec %s is already done. Not interrupting.", new Object[]{ait.f574b});
                iol.m14231a();
            }
            iol.m14231a();
            String.format("WorkerWrapper interrupted for %s", new Object[]{str});
            return true;
        }
        iol.m14231a();
        String.format("WorkerWrapper could not be found for %s", new Object[]{str});
        return false;
    }

    /* renamed from: a */
    public final void mo503a(String str, boolean z) {
        synchronized (this.f513e) {
            this.f511c.remove(str);
            iol.m14231a();
            String.format("%s %s executed; reschedule = %s", new Object[]{getClass().getSimpleName(), str, Boolean.valueOf(z)});
            for (ahw a : this.f518j) {
                a.mo503a(str, z);
            }
        }
    }

    /* renamed from: b */
    public final void mo508b(ahw ahw) {
        synchronized (this.f513e) {
            this.f518j.remove(ahw);
        }
    }

    /* renamed from: a */
    public final boolean mo507a(String str, ckx ckx) {
        synchronized (this.f513e) {
            if (!this.f511c.containsKey(str)) {
                ais ais = new ais(this.f509a, this.f514f, this.f515g, this.f516h, str);
                ais.f571f = this.f517i;
                if (ckx != null) {
                    ais.f572g = ckx;
                }
                ait ait = new ait(ais);
                amx amx = ait.f578f;
                amx.mo53a(new ahy(this, str, amx), ((anb) this.f515g).f811c);
                this.f511c.put(str, ait);
                ((anb) this.f515g).f809a.execute(ait);
                iol.m14231a();
                String.format("%s: processing %s", new Object[]{getClass().getSimpleName(), str});
                return true;
            }
            iol.m14231a();
            String.format("Work %s is already enqueued for processing", new Object[]{str});
            return false;
        }
    }
}
