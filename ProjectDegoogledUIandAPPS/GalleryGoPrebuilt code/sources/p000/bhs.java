package p000;

import android.os.Bundle;

/* renamed from: bhs */
/* compiled from: PG */
public final class bhs implements bhx {

    /* renamed from: a */
    public final String f2392a;

    /* renamed from: b */
    public final String f2393b;

    /* renamed from: c */
    public final bih f2394c;

    /* renamed from: d */
    public final int f2395d;

    /* renamed from: e */
    public final boolean f2396e;

    /* renamed from: f */
    public final int[] f2397f;

    /* renamed from: g */
    public final boolean f2398g;

    /* renamed from: h */
    public final Bundle f2399h;

    /* renamed from: i */
    public final cns f2400i;

    public /* synthetic */ bhs(bhr bhr) {
        Bundle bundle;
        this.f2392a = bhr.f2382a;
        Bundle bundle2 = bhr.f2383b;
        if (bundle2 != null) {
            bundle = new Bundle(bundle2);
        } else {
            bundle = null;
        }
        this.f2399h = bundle;
        this.f2393b = bhr.f2384c;
        this.f2400i = bhr.f2390i;
        this.f2394c = bhr.f2387f;
        this.f2395d = bhr.f2385d;
        this.f2396e = bhr.f2389h;
        int[] iArr = bhr.f2386e;
        this.f2397f = iArr == null ? new int[0] : iArr;
        this.f2398g = bhr.f2388g;
    }

    /* renamed from: b */
    public final String mo2047b() {
        return this.f2392a;
    }

    /* renamed from: c */
    public final String mo2048c() {
        return this.f2393b;
    }

    /* renamed from: d */
    public final int mo2049d() {
        return this.f2395d;
    }

    /* renamed from: e */
    public final boolean mo2050e() {
        return this.f2396e;
    }

    /* renamed from: f */
    public final int[] mo2051f() {
        return this.f2397f;
    }

    /* renamed from: g */
    public final Bundle mo2052g() {
        return this.f2399h;
    }

    /* renamed from: h */
    public final bih mo2053h() {
        return this.f2394c;
    }

    /* renamed from: i */
    public final boolean mo2054i() {
        return this.f2398g;
    }

    /* renamed from: k */
    public final cns mo2056k() {
        return this.f2400i;
    }
}
