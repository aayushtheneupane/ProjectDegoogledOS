package p000;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.p002v7.widget.RecyclerView;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/* renamed from: apj */
/* compiled from: PG */
public class apj extends bdx implements Cloneable {

    /* renamed from: A */
    private boolean f1340A;

    /* renamed from: a */
    public apj f1341a;

    /* renamed from: b */
    public apj f1342b;

    /* renamed from: s */
    private final apn f1343s;

    /* renamed from: t */
    private final Class f1344t;

    /* renamed from: u */
    private final apa f1345u;

    /* renamed from: v */
    private apo f1346v;

    /* renamed from: w */
    private Object f1347w;

    /* renamed from: x */
    private List f1348x;

    /* renamed from: y */
    private boolean f1349y = true;

    /* renamed from: z */
    private boolean f1350z;

    static {
        new bdx((byte[]) null).mo1857a(atc.f1598b).mo1852a(apb.LOW).mo1876o();
    }

    protected apj(aow aow, apn apn, Class cls) {
        this.f1343s = apn;
        this.f1344t = cls;
        apa apa = apn.f1355a.f1290c;
        apo apo = (apo) apa.f1318e.get(cls);
        if (apo == null) {
            for (Map.Entry entry : apa.f1318e.entrySet()) {
                if (((Class) entry.getKey()).isAssignableFrom(cls)) {
                    apo = (apo) entry.getValue();
                }
            }
        }
        this.f1346v = apo == null ? apa.f1314a : apo;
        this.f1345u = aow.f1290c;
        for (bee b : apn.f1358d) {
            mo1424b(b);
        }
        mo1426b(apn.mo1447g());
    }

    /* renamed from: b */
    public apj mo1424b(bee bee) {
        if (bee != null) {
            if (this.f1348x == null) {
                this.f1348x = new ArrayList();
            }
            this.f1348x.add(bee);
        }
        return this;
    }

    /* renamed from: a */
    public apj mo1426b(bdx bdx) {
        cns.m4632a((Object) bdx);
        return (apj) super.mo1426b(bdx);
    }

    /* renamed from: a */
    private final bea m1370a(Object obj, ber ber, bee bee, bec bec, apo apo, apb apb, int i, int i2, bdx bdx, Executor executor) {
        bdy bdy;
        bdy bdy2;
        beh beh;
        int i3;
        int i4;
        apb apb2;
        int i5;
        int i6;
        apb apb3;
        Object obj2 = obj;
        bdx bdx2 = bdx;
        if (this.f1342b != null) {
            bdy2 = new bdy(obj2, bec);
            bdy = bdy2;
        } else {
            bdy = null;
            bdy2 = bec;
        }
        apj apj = this.f1341a;
        if (apj == null) {
            beh = m1369a(obj, ber, bee, bdx, bdy2, apo, apb, i, i2, executor);
        } else if (!this.f1340A) {
            apo apo2 = !apj.f1349y ? apj.f1346v : apo;
            if (super.mo1862a(8)) {
                apb2 = this.f1341a.f2111e;
            } else {
                int i7 = C0017api.f1339b[apb.ordinal()];
                if (i7 == 1) {
                    apb3 = apb.NORMAL;
                } else if (i7 == 2) {
                    apb3 = apb.HIGH;
                } else if (i7 == 3 || i7 == 4) {
                    apb3 = apb.IMMEDIATE;
                } else {
                    String valueOf = String.valueOf(this.f2111e);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 18);
                    sb.append("unknown priority: ");
                    sb.append(valueOf);
                    throw new IllegalArgumentException(sb.toString());
                }
                apb2 = apb3;
            }
            apj apj2 = this.f1341a;
            int i8 = apj2.f2115i;
            int i9 = apj2.f2114h;
            if (!bfp.m2431a(i, i2) || this.f1341a.mo1873l()) {
                i5 = i9;
                i6 = i8;
            } else {
                i6 = bdx2.f2115i;
                i5 = bdx2.f2114h;
            }
            beh beh2 = new beh(obj2, bdy2);
            Object obj3 = obj;
            ber ber2 = ber;
            bee bee2 = bee;
            beh beh3 = beh2;
            bea a = m1369a(obj3, ber2, bee2, bdx, (bec) beh2, apo, apb, i, i2, executor);
            this.f1340A = true;
            apj apj3 = this.f1341a;
            bea a2 = apj3.m1370a(obj3, ber2, bee2, (bec) beh3, apo2, apb2, i6, i5, (bdx) apj3, executor);
            this.f1340A = false;
            beh beh4 = beh3;
            beh4.f2168a = a;
            beh4.f2169b = a2;
            beh = beh4;
        } else {
            throw new IllegalStateException("You cannot use a request as both the main request and a thumbnail, consider using clone() on the request(s) passed to thumbnail()");
        }
        if (bdy == null) {
            return beh;
        }
        apj apj4 = this.f1342b;
        int i10 = apj4.f2115i;
        int i11 = apj4.f2114h;
        if (!bfp.m2431a(i, i2) || this.f1342b.mo1873l()) {
            i3 = i11;
            i4 = i10;
        } else {
            i4 = bdx2.f2115i;
            i3 = bdx2.f2114h;
        }
        apj apj5 = this.f1342b;
        bea a3 = apj5.m1370a(obj, ber, bee, (bec) bdy, apj5.f1346v, apj5.f2111e, i4, i3, (bdx) apj5, executor);
        bdy.f2126a = beh;
        bdy.f2127b = a3;
        return bdy;
    }

    /* renamed from: a */
    public apj clone() {
        apj apj = (apj) super.clone();
        apj.f1346v = apj.f1346v.clone();
        return apj;
    }

    /* renamed from: a */
    public apj mo1416a(apj apj) {
        this.f1342b = apj;
        return this;
    }

    /* renamed from: a */
    public final void mo1422a(ImageView imageView) {
        bdx bdx;
        ber ber;
        bfp.m2430a();
        cns.m4632a((Object) imageView);
        if (!super.mo1862a(2048) && this.f2118l && imageView.getScaleType() != null) {
            switch (C0017api.f1338a[imageView.getScaleType().ordinal()]) {
                case 1:
                    bdx = clone().mo1864e();
                    break;
                case RecyclerView.SCROLL_STATE_SETTLING:
                    bdx = clone().mo1868h();
                    break;
                case 3:
                case 4:
                case 5:
                    bdx = clone().mo1867g();
                    break;
                case 6:
                    bdx = clone().mo1868h();
                    break;
            }
        }
        bdx = this;
        Class cls = this.f1344t;
        if (Bitmap.class.equals(cls)) {
            ber = new bej(imageView);
        } else if (Drawable.class.isAssignableFrom(cls)) {
            ber = new beo(imageView);
        } else {
            String valueOf = String.valueOf(cls);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 64);
            sb.append("Unhandled class: ");
            sb.append(valueOf);
            sb.append(", try .as*(Class).transcode(ResourceTranscoder)");
            throw new IllegalArgumentException(sb.toString());
        }
        beu beu = (beu) m1371a(ber, (bee) null, bdx, bfj.f2208a);
    }

    /* renamed from: a */
    public final ber mo1421a(ber ber) {
        return m1371a(ber, (bee) null, this, bfj.f2208a);
    }

    /* renamed from: a */
    private final ber m1371a(ber ber, bee bee, bdx bdx, Executor executor) {
        ber ber2 = ber;
        bdx bdx2 = bdx;
        cns.m4632a((Object) ber);
        if (this.f1350z) {
            bea a = m1370a(new Object(), ber, bee, (bec) null, this.f1346v, bdx2.f2111e, bdx2.f2115i, bdx2.f2114h, bdx, executor);
            bea ae = ber.mo1896ae();
            if (!a.mo1879a(ae) || (!bdx2.f2113g && ae.mo1887e())) {
                this.f1343s.mo1440a(ber);
                ber.mo1894a(a);
                this.f1343s.mo1441a(ber, a);
                return ber2;
            }
            if (!((bea) cns.m4632a((Object) ae)).mo1884d()) {
                ae.mo1878a();
            }
            return ber2;
        }
        throw new IllegalArgumentException("You must call #load() before calling #into()");
    }

    /* renamed from: a */
    public apj mo1418a(bee bee) {
        this.f1348x = null;
        return mo1424b(bee);
    }

    /* renamed from: a */
    public apj mo1414a(Bitmap bitmap) {
        return mo1425b((Object) bitmap).mo1426b(bdx.m2221b(atc.f1597a));
    }

    /* renamed from: a */
    public apj mo1415a(Uri uri) {
        return mo1425b((Object) uri);
    }

    /* renamed from: a */
    public apj mo1419a(Object obj) {
        return mo1425b(obj);
    }

    /* renamed from: a */
    public apj mo1420a(String str) {
        return mo1425b((Object) str);
    }

    /* renamed from: b */
    public final apj mo1425b(Object obj) {
        this.f1347w = obj;
        this.f1350z = true;
        return this;
    }

    /* renamed from: a */
    private final bea m1369a(Object obj, ber ber, bee bee, bdx bdx, bec bec, apo apo, apb apb, int i, int i2, Executor executor) {
        apa apa = this.f1345u;
        return new beg(apa, obj, this.f1347w, this.f1344t, bdx, i, i2, apb, ber, bee, this.f1348x, bec, apa.f1319f, executor);
    }

    /* renamed from: b */
    public final bdz mo1427b() {
        bed bed = new bed();
        return (bdz) m1371a(bed, bed, this, bfj.f2209b);
    }

    /* renamed from: b */
    public apj mo1423b(apj apj) {
        this.f1341a = apj;
        return this;
    }
}
