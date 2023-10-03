package p000;

import android.os.Build;

/* renamed from: ahb */
/* compiled from: PG */
public final class ahb {

    /* renamed from: a */
    public static final ahb f474a = new aha().mo457a();

    /* renamed from: b */
    public boolean f475b;

    /* renamed from: c */
    public boolean f476c;

    /* renamed from: d */
    public boolean f477d;

    /* renamed from: e */
    public boolean f478e;

    /* renamed from: f */
    public long f479f = -1;

    /* renamed from: g */
    public long f480g = -1;

    /* renamed from: h */
    public ahd f481h = new ahd();

    /* renamed from: i */
    public int f482i = 1;

    public ahb() {
    }

    public ahb(aha aha) {
        this.f475b = aha.f470a;
        int i = Build.VERSION.SDK_INT;
        this.f476c = false;
        this.f482i = 1;
        this.f477d = aha.f471b;
        this.f478e = false;
        int i2 = Build.VERSION.SDK_INT;
        this.f481h = aha.f473d;
        this.f479f = aha.f472c;
        this.f480g = -1;
    }

    public ahb(ahb ahb) {
        this.f475b = ahb.f475b;
        this.f476c = ahb.f476c;
        this.f482i = ahb.f482i;
        this.f477d = ahb.f477d;
        this.f478e = ahb.f478e;
        this.f481h = ahb.f481h;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ahb ahb = (ahb) obj;
        if (this.f475b == ahb.f475b && this.f476c == ahb.f476c && this.f477d == ahb.f477d && this.f478e == ahb.f478e && this.f479f == ahb.f479f && this.f480g == ahb.f480g && this.f482i == ahb.f482i) {
            return this.f481h.equals(ahb.f481h);
        }
        return false;
    }

    /* renamed from: a */
    public final boolean mo459a() {
        return this.f481h.mo464a() > 0;
    }

    public final int hashCode() {
        int i = this.f482i;
        if (i != 0) {
            boolean z = this.f475b;
            boolean z2 = this.f476c;
            boolean z3 = this.f477d;
            boolean z4 = this.f478e;
            long j = this.f479f;
            long j2 = this.f480g;
            return (((((((((((((i * 31) + (z ? 1 : 0)) * 31) + (z2 ? 1 : 0)) * 31) + (z3 ? 1 : 0)) * 31) + (z4 ? 1 : 0)) * 31) + ((int) ((j >>> 32) ^ j))) * 31) + ((int) ((j2 >>> 32) ^ j2))) * 31) + this.f481h.hashCode();
        }
        throw null;
    }
}
