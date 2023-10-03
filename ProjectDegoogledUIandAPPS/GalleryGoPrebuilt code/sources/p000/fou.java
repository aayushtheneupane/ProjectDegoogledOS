package p000;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: fou */
/* compiled from: PG */
public final class fou {

    /* renamed from: a */
    private static final AtomicReference f10174a = new AtomicReference((Object) null);

    /* renamed from: a */
    public static void m9332a(fmt fmt) {
        ife.m12898e((Object) fmt);
        if (((fpt) f10174a.getAndSet((Object) null)) != null) {
            throw null;
        }
    }

    /* renamed from: b */
    public static void m9333b(fmt fmt) {
        ife.m12898e((Object) fmt);
        f10174a.set((Object) null);
    }
}
