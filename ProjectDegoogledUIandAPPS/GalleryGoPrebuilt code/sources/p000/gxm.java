package p000;

import java.lang.reflect.Field;

/* renamed from: gxm */
/* compiled from: PG */
final /* synthetic */ class gxm implements Runnable {

    /* renamed from: a */
    private final Object f12254a;

    /* renamed from: b */
    private final Object f12255b;

    public gxm(Object obj, Object obj2) {
        this.f12254a = obj;
        this.f12255b = obj2;
    }

    public final void run() {
        Object obj = this.f12254a;
        Object obj2 = this.f12255b;
        Field field = gxo.f12261a;
        try {
            if (gxo.f12263c != null) {
                gxo.f12263c.invoke(obj, new Object[]{obj2, false, "TikTok recreation"});
                return;
            }
            gxo.f12264d.invoke(obj, new Object[]{obj2, false});
        } catch (RuntimeException e) {
            th = e;
            if (th.getClass() == RuntimeException.class && th.getMessage() != null && th.getMessage().startsWith("Unable to stop")) {
                throw th;
            }
            ifn.m12932a(th);
        } catch (Throwable th) {
            th = th;
            ifn.m12932a(th);
        }
    }
}
