package p000;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.List;

/* renamed from: bca */
/* compiled from: PG */
final class bca {

    /* renamed from: a */
    public final apz f2025a;

    /* renamed from: b */
    public final List f2026b = new ArrayList();

    /* renamed from: c */
    public final apn f2027c;

    /* renamed from: d */
    public boolean f2028d;

    /* renamed from: e */
    public bbx f2029e;

    /* renamed from: f */
    public boolean f2030f;

    /* renamed from: g */
    public bbx f2031g;

    /* renamed from: h */
    public Bitmap f2032h;

    /* renamed from: i */
    public bbx f2033i;

    /* renamed from: j */
    public int f2034j;

    /* renamed from: k */
    public int f2035k;

    /* renamed from: l */
    public int f2036l;

    /* renamed from: m */
    private final Handler f2037m;

    /* renamed from: n */
    private final auk f2038n;

    /* renamed from: o */
    private boolean f2039o;

    /* renamed from: p */
    private apj f2040p;

    public bca(aow aow, apz apz, int i, int i2, ard ard, Bitmap bitmap) {
        auk auk = aow.f1289b;
        apn c = aow.m1350c(aow.mo1395a());
        apj a = aow.m1350c(aow.mo1395a()).mo1445e().mo1426b(bdx.m2221b(atc.f1597a).mo1877p().mo1876o().mo1850a(i, i2));
        this.f2027c = c;
        Handler handler = new Handler(Looper.getMainLooper(), new bbz(this));
        this.f2038n = auk;
        this.f2037m = handler;
        this.f2040p = a;
        this.f2025a = apz;
        mo1801a(ard, bitmap);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final int mo1800a() {
        return ((aqd) this.f2025a).f1436g.f1415c;
    }

    /* renamed from: c */
    public final void mo1804c() {
        int i;
        if (this.f2028d && !this.f2039o) {
            bbx bbx = this.f2033i;
            if (bbx == null) {
                this.f2039o = true;
                aqd aqd = (aqd) this.f2025a;
                aqb aqb = aqd.f1436g;
                int i2 = aqb.f1415c;
                int i3 = 0;
                if (i2 > 0 && (i = aqd.f1435f) >= 0) {
                    i3 = i < i2 ? ((aqa) aqb.f1417e.get(i)).f1410i : -1;
                }
                long uptimeMillis = SystemClock.uptimeMillis();
                this.f2025a.mo1476a();
                this.f2031g = new bbx(this.f2037m, ((aqd) this.f2025a).f1435f, uptimeMillis + ((long) i3));
                this.f2040p.mo1426b(new bdx((byte[]) null).mo1854a((aqu) new bfa(Double.valueOf(Math.random())))).mo1419a((Object) this.f2025a).mo1421a((ber) this.f2031g);
                return;
            }
            this.f2033i = null;
            mo1802a(bbx);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo1802a(bbx bbx) {
        this.f2039o = false;
        if (this.f2030f) {
            this.f2037m.obtainMessage(2, bbx).sendToTarget();
        } else if (this.f2028d) {
            if (bbx.f2018b != null) {
                mo1805d();
                bbx bbx2 = this.f2029e;
                this.f2029e = bbx;
                for (int size = this.f2026b.size() - 1; size >= 0; size--) {
                    ((bby) this.f2026b.get(size)).mo1785c();
                }
                if (bbx2 != null) {
                    this.f2037m.obtainMessage(2, bbx2).sendToTarget();
                }
            }
            mo1804c();
        } else {
            this.f2033i = bbx;
        }
    }

    /* renamed from: d */
    public final void mo1805d() {
        Bitmap bitmap = this.f2032h;
        if (bitmap != null) {
            this.f2038n.mo1645a(bitmap);
            this.f2032h = null;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo1801a(ard ard, Bitmap bitmap) {
        ard ard2 = (ard) cns.m4632a((Object) ard);
        this.f2032h = (Bitmap) cns.m4632a((Object) bitmap);
        this.f2040p = this.f2040p.mo1426b(new bdx((byte[]) null).mo1856a(ard));
        this.f2034j = bfp.m2426a(bitmap);
        this.f2035k = bitmap.getWidth();
        this.f2036l = bitmap.getHeight();
    }

    /* renamed from: b */
    public final void mo1803b() {
        this.f2028d = false;
    }
}
