package p000;

import android.os.Bundle;
import java.util.List;

/* renamed from: bhr */
/* compiled from: PG */
public final class bhr implements bhx {

    /* renamed from: a */
    public String f2382a;

    /* renamed from: b */
    public Bundle f2383b;

    /* renamed from: c */
    public String f2384c;

    /* renamed from: d */
    public int f2385d = 1;

    /* renamed from: e */
    public int[] f2386e;

    /* renamed from: f */
    public bih f2387f = bih.f2448a;

    /* renamed from: g */
    public boolean f2388g = false;

    /* renamed from: h */
    public boolean f2389h = false;

    /* renamed from: i */
    public cns f2390i = bii.f2452a;

    /* renamed from: j */
    private final bik f2391j;

    public bhr(bik bik) {
        this.f2391j = bik;
    }

    /* renamed from: b */
    public final String mo2047b() {
        return this.f2382a;
    }

    /* renamed from: c */
    public final String mo2048c() {
        return this.f2384c;
    }

    /* renamed from: d */
    public final int mo2049d() {
        return this.f2385d;
    }

    /* renamed from: e */
    public final boolean mo2050e() {
        return this.f2389h;
    }

    /* renamed from: g */
    public final Bundle mo2052g() {
        return this.f2383b;
    }

    /* renamed from: h */
    public final bih mo2053h() {
        return this.f2387f;
    }

    /* renamed from: i */
    public final boolean mo2054i() {
        return this.f2388g;
    }

    /* renamed from: k */
    public final cns mo2056k() {
        return this.f2390i;
    }

    public bhr(bik bik, bhx bhx) {
        this.f2391j = bik;
        bhw bhw = (bhw) bhx;
        this.f2384c = bhw.f2412a;
        this.f2382a = bhw.f2413b;
        this.f2390i = bhw.f2419h;
        this.f2389h = bhw.f2414c;
        this.f2385d = bhw.f2415d;
        this.f2386e = bhw.f2416e;
        this.f2383b = bhw.f2417f;
        this.f2387f = bhw.f2418g;
    }

    /* renamed from: a */
    public final void mo2046a(int i) {
        int length;
        int[] iArr = this.f2386e;
        int i2 = 1;
        if (iArr != null) {
            i2 = 1 + iArr.length;
        }
        int[] iArr2 = new int[i2];
        if (!(iArr == null || (length = iArr.length) == 0)) {
            System.arraycopy(iArr, 0, iArr2, 0, length);
        }
        iArr2[i2 - 1] = i;
        this.f2386e = iArr2;
    }

    /* renamed from: a */
    public final bhs mo2045a() {
        List a = this.f2391j.mo2027a(this);
        if (a == null) {
            return new bhs(this);
        }
        throw new bij("JobParameters is invalid", a);
    }

    /* renamed from: f */
    public final int[] mo2051f() {
        int[] iArr = this.f2386e;
        return iArr == null ? new int[0] : iArr;
    }

    /* renamed from: j */
    public final void mo2055j() {
        this.f2388g = true;
    }
}
