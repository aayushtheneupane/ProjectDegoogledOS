package p000;

import android.util.Log;

/* renamed from: alg */
/* compiled from: PG */
public final class alg {

    /* renamed from: a */
    public static final String f712a = iol.m14236b("WorkSpec");

    /* renamed from: b */
    public String f713b;

    /* renamed from: c */
    public final String f714c;

    /* renamed from: d */
    public String f715d;

    /* renamed from: e */
    public ahf f716e = ahf.f487a;

    /* renamed from: f */
    public ahf f717f = ahf.f487a;

    /* renamed from: g */
    public long f718g;

    /* renamed from: h */
    public long f719h;

    /* renamed from: i */
    public long f720i;

    /* renamed from: j */
    public ahb f721j = ahb.f474a;

    /* renamed from: k */
    public int f722k;

    /* renamed from: l */
    public long f723l = 30000;

    /* renamed from: m */
    public long f724m;

    /* renamed from: n */
    public long f725n;

    /* renamed from: o */
    public long f726o = -1;

    /* renamed from: p */
    public boolean f727p;

    /* renamed from: q */
    public int f728q = 1;

    /* renamed from: r */
    public int f729r = 1;

    /* renamed from: a */
    public final boolean mo595a() {
        return this.f719h != 0;
    }

    /* renamed from: b */
    public final boolean mo596b() {
        return this.f728q == 1 && this.f722k > 0;
    }

    public alg(alg alg) {
        this.f713b = alg.f713b;
        this.f714c = alg.f714c;
        this.f728q = alg.f728q;
        this.f715d = alg.f715d;
        this.f716e = new ahf(alg.f716e);
        this.f717f = new ahf(alg.f717f);
        this.f718g = alg.f718g;
        this.f719h = alg.f719h;
        this.f720i = alg.f720i;
        this.f721j = new ahb(alg.f721j);
        this.f722k = alg.f722k;
        this.f729r = alg.f729r;
        this.f723l = alg.f723l;
        this.f724m = alg.f724m;
        this.f725n = alg.f725n;
        this.f726o = alg.f726o;
        this.f727p = alg.f727p;
    }

    public alg(String str, String str2) {
        this.f713b = str;
        this.f714c = str2;
    }

    /* renamed from: c */
    public final long mo597c() {
        long j;
        if (mo596b()) {
            if (this.f729r != 2) {
                j = (long) Math.scalb((float) this.f723l, this.f722k - 1);
            } else {
                j = this.f723l * ((long) this.f722k);
            }
            return this.f724m + Math.min(18000000, j);
        }
        long j2 = 0;
        if (mo595a()) {
            long currentTimeMillis = System.currentTimeMillis();
            long j3 = this.f724m;
            long j4 = j3 == 0 ? currentTimeMillis + this.f718g : j3;
            long j5 = this.f720i;
            long j6 = this.f719h;
            if (j5 != j6) {
                if (j3 == 0) {
                    j2 = -j5;
                }
                j4 += j6;
            } else if (j3 != 0) {
                j2 = j6;
            }
            return j4 + j2;
        }
        long j7 = this.f724m;
        if (j7 == 0) {
            j7 = System.currentTimeMillis();
        }
        return j7 + this.f718g;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof alg) {
            alg alg = (alg) obj;
            if (this.f718g == alg.f718g && this.f719h == alg.f719h && this.f720i == alg.f720i && this.f722k == alg.f722k && this.f723l == alg.f723l && this.f724m == alg.f724m && this.f725n == alg.f725n && this.f726o == alg.f726o && this.f727p == alg.f727p && this.f713b.equals(alg.f713b) && this.f728q == alg.f728q && this.f714c.equals(alg.f714c)) {
                String str = this.f715d;
                if (str == null ? alg.f715d == null : str.equals(alg.f715d)) {
                    return this.f716e.equals(alg.f716e) && this.f717f.equals(alg.f717f) && this.f721j.equals(alg.f721j) && this.f729r == alg.f729r;
                }
                return false;
            }
        }
        return false;
    }

    /* renamed from: d */
    public final boolean mo598d() {
        return !ahb.f474a.equals(this.f721j);
    }

    public final int hashCode() {
        int i;
        int hashCode = ((((this.f713b.hashCode() * 31) + gbz.m9997d(this.f728q)) * 31) + this.f714c.hashCode()) * 31;
        String str = this.f715d;
        if (str != null) {
            i = str.hashCode();
        } else {
            i = 0;
        }
        int hashCode2 = this.f716e.hashCode();
        int hashCode3 = this.f717f.hashCode();
        long j = this.f718g;
        long j2 = this.f719h;
        long j3 = this.f720i;
        int hashCode4 = (((((((((((((((hashCode + i) * 31) + hashCode2) * 31) + hashCode3) * 31) + ((int) ((j >>> 32) ^ j))) * 31) + ((int) ((j2 >>> 32) ^ j2))) * 31) + ((int) ((j3 >>> 32) ^ j3))) * 31) + this.f721j.hashCode()) * 31) + this.f722k) * 31;
        int i2 = this.f729r;
        if (i2 != 0) {
            long j4 = this.f723l;
            long j5 = this.f724m;
            long j6 = this.f725n;
            long j7 = this.f726o;
            return ((((((((((hashCode4 + i2) * 31) + ((int) (j4 ^ (j4 >>> 32)))) * 31) + ((int) ((j5 >>> 32) ^ j5))) * 31) + ((int) ((j6 >>> 32) ^ j6))) * 31) + ((int) ((j7 >>> 32) ^ j7))) * 31) + (this.f727p ? 1 : 0);
        }
        throw null;
    }

    /* renamed from: a */
    public final void mo594a(long j, long j2) {
        if (j < 900000) {
            iol.m14231a();
            Log.w(f712a, String.format("Interval duration lesser than minimum allowed value; Changed to %s", new Object[]{900000L}));
            j = 900000;
        }
        if (j2 < 300000) {
            iol.m14231a();
            Log.w(f712a, String.format("Flex duration lesser than minimum allowed value; Changed to %s", new Object[]{300000L}));
            j2 = 300000;
        }
        if (j2 > j) {
            iol.m14231a();
            Log.w(f712a, String.format("Flex duration greater than interval duration; Changed to %s", new Object[]{Long.valueOf(j)}));
            j2 = j;
        }
        this.f719h = j;
        this.f720i = j2;
    }

    public final String toString() {
        return "{WorkSpec: " + this.f713b + "}";
    }
}
