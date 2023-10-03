package p000;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.p002v7.widget.RecyclerView;
import java.util.Map;

/* renamed from: bdx */
/* compiled from: PG */
public class bdx implements Cloneable {

    /* renamed from: a */
    private int f2107a;

    /* renamed from: b */
    private boolean f2108b;

    /* renamed from: c */
    public float f2109c;

    /* renamed from: d */
    public atc f2110d;

    /* renamed from: e */
    public apb f2111e;

    /* renamed from: f */
    public Drawable f2112f;

    /* renamed from: g */
    public boolean f2113g;

    /* renamed from: h */
    public int f2114h;

    /* renamed from: i */
    public int f2115i;

    /* renamed from: j */
    public aqu f2116j;

    /* renamed from: k */
    public boolean f2117k;

    /* renamed from: l */
    public boolean f2118l;

    /* renamed from: m */
    public aqz f2119m;

    /* renamed from: n */
    public Map f2120n;

    /* renamed from: o */
    public Class f2121o;

    /* renamed from: p */
    public boolean f2122p;

    /* renamed from: q */
    public boolean f2123q;

    /* renamed from: r */
    public boolean f2124r;

    /* renamed from: s */
    private boolean f2125s;

    public bdx() {
        this.f2109c = 1.0f;
        this.f2110d = atc.f1599c;
        this.f2111e = apb.NORMAL;
        this.f2113g = true;
        this.f2114h = -1;
        this.f2115i = -1;
        this.f2116j = bez.f2193b;
        this.f2118l = true;
        this.f2119m = new aqz();
        this.f2120n = new bfe();
        this.f2121o = Object.class;
        this.f2123q = true;
    }

    /* renamed from: b */
    private static boolean m2224b(int i, int i2) {
        return (i & i2) != 0;
    }

    /* renamed from: b */
    public bdx mo1426b(bdx bdx) {
        if (this.f2125s) {
            return clone().mo1426b(bdx);
        }
        if (m2224b(bdx.f2107a, 2)) {
            this.f2109c = bdx.f2109c;
        }
        if (m2224b(bdx.f2107a, 1048576)) {
            this.f2124r = bdx.f2124r;
        }
        if (m2224b(bdx.f2107a, 4)) {
            this.f2110d = bdx.f2110d;
        }
        if (m2224b(bdx.f2107a, 8)) {
            this.f2111e = bdx.f2111e;
        }
        if (m2224b(bdx.f2107a, 16)) {
            this.f2107a &= -33;
        }
        if (m2224b(bdx.f2107a, 32)) {
            this.f2107a &= -17;
        }
        if (m2224b(bdx.f2107a, 64)) {
            this.f2112f = bdx.f2112f;
            this.f2107a &= -129;
        }
        if (m2224b(bdx.f2107a, 128)) {
            this.f2112f = null;
            this.f2107a &= -65;
        }
        if (m2224b(bdx.f2107a, 256)) {
            this.f2113g = bdx.f2113g;
        }
        if (m2224b(bdx.f2107a, 512)) {
            this.f2115i = bdx.f2115i;
            this.f2114h = bdx.f2114h;
        }
        if (m2224b(bdx.f2107a, 1024)) {
            this.f2116j = bdx.f2116j;
        }
        if (m2224b(bdx.f2107a, 4096)) {
            this.f2121o = bdx.f2121o;
        }
        if (m2224b(bdx.f2107a, 8192)) {
            this.f2107a &= -16385;
        }
        if (m2224b(bdx.f2107a, 16384)) {
            this.f2107a &= -8193;
        }
        if (m2224b(bdx.f2107a, 65536)) {
            this.f2118l = bdx.f2118l;
        }
        if (m2224b(bdx.f2107a, 131072)) {
            this.f2117k = bdx.f2117k;
        }
        if (m2224b(bdx.f2107a, 2048)) {
            this.f2120n.putAll(bdx.f2120n);
            this.f2123q = bdx.f2123q;
        }
        if (m2224b(bdx.f2107a, 524288)) {
            this.f2122p = bdx.f2122p;
        }
        if (!this.f2118l) {
            this.f2120n.clear();
            int i = this.f2107a;
            this.f2117k = false;
            this.f2107a = i & -133121;
            this.f2123q = true;
        }
        this.f2107a |= bdx.f2107a;
        this.f2119m.mo1504a(bdx.f2119m);
        return mo1413a();
    }

    /* renamed from: k */
    public bdx mo1872k() {
        if (this.f2108b && !this.f2125s) {
            throw new IllegalStateException("You cannot auto lock an already locked options object, try clone() first");
        }
        this.f2125s = true;
        return mo1871j();
    }

    /* renamed from: f */
    public bdx mo1866f() {
        return m2222b(azz.f1919c, (ard) new azn());
    }

    /* renamed from: i */
    public bdx mo1870i() {
        return m2219a(azz.f1918b, (ard) new azo(), true);
    }

    /* renamed from: c */
    public bdx clone() {
        try {
            bdx bdx = (bdx) super.clone();
            aqz aqz = new aqz();
            bdx.f2119m = aqz;
            aqz.mo1504a(this.f2119m);
            bfe bfe = new bfe();
            bdx.f2120n = bfe;
            bfe.putAll(this.f2120n);
            bdx.f2108b = false;
            bdx.f2125s = false;
            return bdx;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    public bdx mo1859a(Class cls) {
        if (this.f2125s) {
            return clone().mo1859a(cls);
        }
        this.f2121o = (Class) cns.m4632a((Object) cls);
        this.f2107a |= 4096;
        return mo1413a();
    }

    /* renamed from: d */
    public bdx mo1863d() {
        return mo1855a(bac.f1933b, (Object) false);
    }

    /* renamed from: a */
    public bdx mo1857a(atc atc) {
        if (this.f2125s) {
            return clone().mo1857a(atc);
        }
        this.f2110d = (atc) cns.m4632a((Object) atc);
        this.f2107a |= 4;
        return mo1413a();
    }

    /* renamed from: a */
    public bdx mo1858a(azz azz) {
        return mo1855a(azz.f1922f, (Object) (azz) cns.m4632a((Object) azz));
    }

    public final boolean equals(Object obj) {
        if (obj instanceof bdx) {
            bdx bdx = (bdx) obj;
            if (Float.compare(bdx.f2109c, this.f2109c) != 0 || !bfp.m2432a((Object) null, (Object) null) || !bfp.m2432a((Object) this.f2112f, (Object) bdx.f2112f) || !bfp.m2432a((Object) null, (Object) null) || this.f2113g != bdx.f2113g || this.f2114h != bdx.f2114h || this.f2115i != bdx.f2115i || this.f2117k != bdx.f2117k || this.f2118l != bdx.f2118l || this.f2122p != bdx.f2122p || !this.f2110d.equals(bdx.f2110d) || this.f2111e != bdx.f2111e || !this.f2119m.equals(bdx.f2119m) || !this.f2120n.equals(bdx.f2120n) || !this.f2121o.equals(bdx.f2121o) || !bfp.m2432a((Object) this.f2116j, (Object) bdx.f2116j) || !bfp.m2432a((Object) null, (Object) null)) {
                return false;
            }
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public bdx mo1853a(aqj aqj) {
        cns.m4632a((Object) aqj);
        return mo1855a(bac.f1932a, (Object) aqj).mo1855a(bcc.f2042a, (Object) aqj);
    }

    /* renamed from: m */
    public bdx mo1874m() {
        return mo1855a(bbf.f1988a, (Object) 0L);
    }

    public final int hashCode() {
        int a = bfp.m2427a((Object) null, bfp.m2434b(0, bfp.m2427a((Object) this.f2112f, bfp.m2434b(0, bfp.m2427a((Object) null, bfp.m2434b(0, bfp.m2424a(this.f2109c)))))));
        boolean z = this.f2113g;
        int i = this.f2114h;
        int i2 = this.f2115i;
        boolean z2 = this.f2117k;
        boolean z3 = this.f2118l;
        boolean z4 = this.f2122p;
        return bfp.m2427a((Object) null, bfp.m2427a((Object) this.f2116j, bfp.m2427a((Object) this.f2121o, bfp.m2427a((Object) this.f2120n, bfp.m2427a((Object) this.f2119m, bfp.m2427a((Object) this.f2111e, bfp.m2427a((Object) this.f2110d, bfp.m2434b(z4 ? 1 : 0, bfp.m2434b(0, bfp.m2434b(z3 ? 1 : 0, bfp.m2434b(z2 ? 1 : 0, bfp.m2434b(i2, bfp.m2434b(i, bfp.m2434b(z ? 1 : 0, a))))))))))))));
    }

    /* renamed from: a */
    public final boolean mo1862a(int i) {
        return m2224b(this.f2107a, i);
    }

    /* renamed from: l */
    public final boolean mo1873l() {
        return bfp.m2431a(this.f2115i, this.f2114h);
    }

    /* renamed from: j */
    public bdx mo1871j() {
        this.f2108b = true;
        return this;
    }

    /* renamed from: a */
    public bdx mo1860a(boolean z) {
        if (this.f2125s) {
            return clone().mo1860a(z);
        }
        this.f2122p = z;
        this.f2107a |= 524288;
        return mo1413a();
    }

    /* renamed from: e */
    public bdx mo1864e() {
        return m2218a(azz.f1919c, (ard) new azn());
    }

    /* renamed from: h */
    public bdx mo1868h() {
        return m2225c(azz.f1918b, new azo());
    }

    /* renamed from: g */
    public bdx mo1867g() {
        return m2225c(azz.f1917a, new bah());
    }

    /* renamed from: c */
    private final bdx m2225c(azz azz, ard ard) {
        return m2219a(azz, ard, false);
    }

    /* renamed from: a */
    private final bdx m2218a(azz azz, ard ard) {
        if (this.f2125s) {
            return clone().m2218a(azz, ard);
        }
        mo1858a(azz);
        return m2217a(ard, false);
    }

    /* renamed from: n */
    public bdx mo1875n() {
        return mo1850a((int) RecyclerView.UNDEFINED_DURATION, (int) RecyclerView.UNDEFINED_DURATION);
    }

    /* renamed from: a */
    public bdx mo1850a(int i, int i2) {
        if (this.f2125s) {
            return clone().mo1850a(i, i2);
        }
        this.f2115i = i;
        this.f2114h = i2;
        this.f2107a |= 512;
        return mo1413a();
    }

    /* renamed from: a */
    public bdx mo1851a(Drawable drawable) {
        if (this.f2125s) {
            return clone().mo1851a(drawable);
        }
        this.f2112f = drawable;
        this.f2107a = (this.f2107a | 64) & -129;
        return mo1413a();
    }

    /* renamed from: a */
    public bdx mo1852a(apb apb) {
        if (this.f2125s) {
            return clone().mo1852a(apb);
        }
        this.f2111e = (apb) cns.m4632a((Object) apb);
        this.f2107a |= 8;
        return mo1413a();
    }

    /* renamed from: a */
    private final bdx m2219a(azz azz, ard ard, boolean z) {
        bdx bdx;
        if (z) {
            bdx = m2222b(azz, ard);
        } else {
            bdx = m2218a(azz, ard);
        }
        bdx.f2123q = true;
        return bdx;
    }

    /* renamed from: a */
    private final bdx mo1413a() {
        if (!this.f2108b) {
            return this;
        }
        throw new IllegalStateException("You cannot modify locked T, consider clone()");
    }

    /* renamed from: a */
    public bdx mo1855a(aqy aqy, Object obj) {
        if (this.f2125s) {
            return clone().mo1855a(aqy, obj);
        }
        cns.m4632a((Object) aqy);
        cns.m4632a(obj);
        this.f2119m.mo1503a(aqy, obj);
        return mo1413a();
    }

    /* renamed from: a */
    public bdx mo1854a(aqu aqu) {
        if (this.f2125s) {
            return clone().mo1854a(aqu);
        }
        this.f2116j = (aqu) cns.m4632a((Object) aqu);
        this.f2107a |= 1024;
        return mo1413a();
    }

    /* renamed from: o */
    public bdx mo1876o() {
        if (this.f2125s) {
            return clone().mo1876o();
        }
        this.f2113g = false;
        this.f2107a |= 256;
        return mo1413a();
    }

    /* renamed from: a */
    public bdx mo1856a(ard ard) {
        return m2217a(ard, true);
    }

    /* renamed from: a */
    private final bdx m2217a(ard ard, boolean z) {
        if (this.f2125s) {
            return clone().m2217a(ard, z);
        }
        baf baf = new baf(ard, z);
        m2220a(Bitmap.class, ard, z);
        m2220a(Drawable.class, (ard) baf, z);
        m2220a(BitmapDrawable.class, (ard) baf, z);
        m2220a(bbt.class, (ard) new bbw(ard), z);
        return mo1413a();
    }

    /* renamed from: b */
    private final bdx m2222b(azz azz, ard ard) {
        if (this.f2125s) {
            return clone().m2222b(azz, ard);
        }
        mo1858a(azz);
        return mo1856a(ard);
    }

    /* renamed from: a */
    private final bdx m2220a(Class cls, ard ard, boolean z) {
        if (this.f2125s) {
            return clone().m2220a(cls, ard, z);
        }
        cns.m4632a((Object) cls);
        cns.m4632a((Object) ard);
        this.f2120n.put(cls, ard);
        int i = this.f2107a;
        this.f2118l = true;
        int i2 = i | 67584;
        this.f2107a = i2;
        this.f2123q = false;
        if (z) {
            this.f2107a = i2 | 131072;
            this.f2117k = true;
        }
        return mo1413a();
    }

    /* renamed from: a */
    public bdx mo1861a(ard... ardArr) {
        return m2217a((ard) new aqv(ardArr), true);
    }

    /* renamed from: p */
    public bdx mo1877p() {
        if (this.f2125s) {
            return clone().mo1877p();
        }
        this.f2124r = true;
        this.f2107a |= 1048576;
        return mo1413a();
    }

    public bdx(byte[] bArr) {
        this();
    }

    /* renamed from: b */
    public static bdx m2223b(Class cls) {
        return new bdx((byte[]) null).mo1859a(cls);
    }

    /* renamed from: b */
    public static bdx m2221b(atc atc) {
        return new bdx((byte[]) null).mo1857a(atc);
    }
}
