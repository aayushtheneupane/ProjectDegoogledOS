package p000;

import java.util.Map;

/* renamed from: ika */
/* compiled from: PG */
final class ika implements ijz {
    /* renamed from: b */
    public final Map mo8855b(Object obj) {
        return (ijy) obj;
    }

    /* renamed from: e */
    public final ijw mo8858e(Object obj) {
        return ((ijx) obj).f14367a;
    }

    /* renamed from: a */
    public final Map mo8854a(Object obj) {
        return (ijy) obj;
    }

    /* renamed from: a */
    public final int mo8851a(int i, Object obj, Object obj2) {
        ijy ijy = (ijy) obj;
        ijx ijx = (ijx) obj2;
        int i2 = 0;
        if (!ijy.isEmpty()) {
            for (Map.Entry entry : ijy.entrySet()) {
                i2 += iie.m13420f(i) + iie.m13430j(ijx.m13699a(ijx.f14367a, entry.getKey(), entry.getValue()));
            }
        }
        return i2;
    }

    /* renamed from: c */
    public final boolean mo8856c(Object obj) {
        return !((ijy) obj).f14369a;
    }

    /* renamed from: a */
    public final Object mo8853a(Object obj, Object obj2) {
        ijy ijy = (ijy) obj;
        ijy ijy2 = (ijy) obj2;
        if (!ijy2.isEmpty()) {
            if (!ijy.f14369a) {
                ijy = ijy.mo8831a();
            }
            ijy.mo8833c();
            if (!ijy2.isEmpty()) {
                ijy.putAll(ijy2);
            }
        }
        return ijy;
    }

    /* renamed from: a */
    public final Object mo8852a() {
        return ijy.f14368b.mo8831a();
    }

    /* renamed from: d */
    public final Object mo8857d(Object obj) {
        ((ijy) obj).mo8832b();
        return obj;
    }
}
