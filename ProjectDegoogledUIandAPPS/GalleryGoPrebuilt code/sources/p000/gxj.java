package p000;

import java.lang.reflect.Field;

/* renamed from: gxj */
/* compiled from: PG */
final /* synthetic */ class gxj implements Runnable {

    /* renamed from: a */
    private final gxn f12248a;

    /* renamed from: b */
    private final Object f12249b;

    public gxj(gxn gxn, Object obj) {
        this.f12248a = gxn;
        this.f12249b = obj;
    }

    public final void run() {
        gxn gxn = this.f12248a;
        Object obj = this.f12249b;
        Field field = gxo.f12261a;
        gxn.f12256a = obj;
    }
}
