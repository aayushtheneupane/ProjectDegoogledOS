package p000;

import java.util.AbstractMap;

/* renamed from: hux */
/* compiled from: PG */
final class hux extends hso {

    /* renamed from: a */
    private final /* synthetic */ huy f13443a;

    public hux(huy huy) {
        this.f13443a = huy;
    }

    /* renamed from: h */
    public final boolean mo7885h() {
        return true;
    }

    public final /* bridge */ /* synthetic */ Object get(int i) {
        ife.m12873b(i, this.f13443a.f13445b);
        Object[] objArr = this.f13443a.f13444a;
        int i2 = i + i;
        return new AbstractMap.SimpleImmutableEntry(objArr[i2], objArr[i2 + 1]);
    }

    public final int size() {
        return this.f13443a.f13445b;
    }
}
