package p000;

import android.graphics.Bitmap;

/* renamed from: bbv */
/* compiled from: PG */
public final class bbv extends bbk implements atv {
    public bbv(bbt bbt) {
        super(bbt);
    }

    /* renamed from: a */
    public final Class mo1604a() {
        return bbt.class;
    }

    /* renamed from: c */
    public final int mo1606c() {
        bca bca = ((bbt) this.f1995a).f2006a.f2005a;
        aqd aqd = (aqd) bca.f2025a;
        return aqd.f1431b.limit() + aqd.f1433d.length + (aqd.f1434e.length << 2) + bca.f2034j;
    }

    /* renamed from: e */
    public final void mo1621e() {
        ((bbt) this.f1995a).mo1783a().prepareToDraw();
    }

    /* renamed from: d */
    public final void mo1607d() {
        aui aui;
        ((bbt) this.f1995a).stop();
        bbt bbt = (bbt) this.f1995a;
        bbt.f2007b = true;
        bca bca = bbt.f2006a.f2005a;
        bca.f2026b.clear();
        bca.mo1805d();
        bca.mo1803b();
        bbx bbx = bca.f2029e;
        if (bbx != null) {
            bca.f2027c.mo1440a((ber) bbx);
            bca.f2029e = null;
        }
        bbx bbx2 = bca.f2031g;
        if (bbx2 != null) {
            bca.f2027c.mo1440a((ber) bbx2);
            bca.f2031g = null;
        }
        bbx bbx3 = bca.f2033i;
        if (bbx3 != null) {
            bca.f2027c.mo1440a((ber) bbx3);
            bca.f2033i = null;
        }
        aqd aqd = (aqd) bca.f2025a;
        aqd.f1436g = null;
        byte[] bArr = aqd.f1433d;
        if (bArr != null) {
            aqd.f1430a.mo1474a(bArr);
        }
        int[] iArr = aqd.f1434e;
        if (!(iArr == null || (aui = ((bbr) aqd.f1430a).f2004b) == null)) {
            aui.mo1638a((Object) iArr);
        }
        Bitmap bitmap = aqd.f1437h;
        if (bitmap != null) {
            aqd.f1430a.mo1473a(bitmap);
        }
        aqd.f1437h = null;
        aqd.f1431b = null;
        aqd.f1438i = null;
        byte[] bArr2 = aqd.f1432c;
        if (bArr2 != null) {
            aqd.f1430a.mo1474a(bArr2);
        }
        bca.f2030f = true;
    }
}
