package p000;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

/* renamed from: amn */
/* compiled from: PG */
public final class amn {

    /* renamed from: a */
    public final ScheduledExecutorService f774a = Executors.newSingleThreadScheduledExecutor(this.f778e);

    /* renamed from: b */
    public final Map f775b = new HashMap();

    /* renamed from: c */
    public final Map f776c = new HashMap();

    /* renamed from: d */
    public final Object f777d = new Object();

    /* renamed from: e */
    private final ThreadFactory f778e = new amk();

    static {
        iol.m14236b("WorkTimer");
    }

    /* renamed from: a */
    public final void mo648a(String str) {
        synchronized (this.f777d) {
            if (((amm) this.f775b.remove(str)) != null) {
                iol.m14231a();
                String.format("Stopping timer for %s", new Object[]{str});
                this.f776c.remove(str);
            }
        }
    }
}
