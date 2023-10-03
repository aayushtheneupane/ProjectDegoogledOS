package p000;

import android.support.p002v7.widget.RecyclerView;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;

/* renamed from: ikh */
/* compiled from: PG */
final class ikh implements iky {

    /* renamed from: a */
    private static final int[] f14375a = new int[0];

    /* renamed from: b */
    private static final Unsafe f14376b = ilv.m14030a();

    /* renamed from: c */
    private final int[] f14377c;

    /* renamed from: d */
    private final Object[] f14378d;

    /* renamed from: e */
    private final int f14379e;

    /* renamed from: f */
    private final int f14380f;

    /* renamed from: g */
    private final ikf f14381g;

    /* renamed from: h */
    private final boolean f14382h;

    /* renamed from: i */
    private final boolean f14383i;

    /* renamed from: j */
    private final boolean f14384j;

    /* renamed from: k */
    private final int[] f14385k;

    /* renamed from: l */
    private final int f14386l;

    /* renamed from: m */
    private final int f14387m;

    /* renamed from: n */
    private final ikk f14388n;

    /* renamed from: o */
    private final ijr f14389o;

    /* renamed from: p */
    private final ijz f14390p;

    /* renamed from: q */
    private final imu f14391q;

    /* renamed from: g */
    private static int m13781g(int i) {
        return (i >>> 20) & 255;
    }

    /* renamed from: h */
    private static boolean m13784h(int i) {
        return (i & 536870912) != 0;
    }

    /* renamed from: i */
    private static long m13785i(int i) {
        return (long) (i & 1048575);
    }

    private ikh(int[] iArr, Object[] objArr, int i, int i2, ikf ikf, boolean z, int[] iArr2, int i3, int i4, ikk ikk, ijr ijr, imu imu, imi imi, ijz ijz, byte[] bArr, byte[] bArr2) {
        ikf ikf2 = ikf;
        this.f14377c = iArr;
        this.f14378d = objArr;
        this.f14379e = i;
        this.f14380f = i2;
        this.f14383i = ikf2 instanceof iix;
        this.f14384j = z;
        boolean z2 = false;
        if (imi != null && (ikf2 instanceof iiu)) {
            z2 = true;
        }
        this.f14382h = z2;
        this.f14385k = iArr2;
        this.f14386l = i3;
        this.f14387m = i4;
        this.f14388n = ikk;
        this.f14389o = ijr;
        this.f14391q = imu;
        this.f14381g = ikf2;
        this.f14390p = ijz;
    }

    /* renamed from: c */
    private final boolean m13772c(Object obj, Object obj2, int i) {
        return m13757a(obj, i) == m13757a(obj2, i);
    }

    /* renamed from: a */
    private static final int m13746a(byte[] bArr, int i, int i2, imb imb, Class cls, ihf ihf) {
        boolean z;
        imb imb2 = imb.DOUBLE;
        switch (imb.ordinal()) {
            case 0:
                ihf.f14180c = Double.valueOf(ihg.m13048c(bArr, i));
                return i + 8;
            case 1:
                ihf.f14180c = Float.valueOf(ihg.m13053d(bArr, i));
                return i + 4;
            case RecyclerView.SCROLL_STATE_SETTLING:
            case 3:
                int b = ihg.m13045b(bArr, i, ihf);
                ihf.f14180c = Long.valueOf(ihf.f14179b);
                return b;
            case 4:
            case 12:
            case 13:
                int a = ihg.m13023a(bArr, i, ihf);
                ihf.f14180c = Integer.valueOf(ihf.f14178a);
                return a;
            case 5:
            case 15:
                ihf.f14180c = Long.valueOf(ihg.m13046b(bArr, i));
                return i + 8;
            case 6:
            case 14:
                ihf.f14180c = Integer.valueOf(ihg.m13022a(bArr, i));
                return i + 4;
            case 7:
                int b2 = ihg.m13045b(bArr, i, ihf);
                if (ihf.f14179b != 0) {
                    z = true;
                } else {
                    z = false;
                }
                ihf.f14180c = Boolean.valueOf(z);
                return b2;
            case 8:
                return ihg.m13055d(bArr, i, ihf);
            case 10:
                return ihg.m13021a(ikp.f14397a.mo8875a(cls), bArr, i, i2, ihf);
            case 11:
                return ihg.m13057e(bArr, i, ihf);
            case 16:
                int a2 = ihg.m13023a(bArr, i, ihf);
                ihf.f14180c = Integer.valueOf(ihz.m13264e(ihf.f14178a));
                return a2;
            case 17:
                int b3 = ihg.m13045b(bArr, i, ihf);
                ihf.f14180c = Long.valueOf(ihz.m13260a(ihf.f14179b));
                return b3;
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    /* renamed from: a */
    public final boolean mo8868a(Object obj, Object obj2) {
        boolean z;
        int length = this.f14377c.length;
        for (int i = 0; i < length; i += 3) {
            int e = m13775e(i);
            long i2 = m13785i(e);
            switch (m13781g(e)) {
                case 0:
                    if (m13772c(obj, obj2, i) && Double.doubleToLongBits(ilv.m14047e(obj, i2)) == Double.doubleToLongBits(ilv.m14047e(obj2, i2))) {
                        continue;
                    }
                case 1:
                    if (m13772c(obj, obj2, i) && Float.floatToIntBits(ilv.m14045d(obj, i2)) == Float.floatToIntBits(ilv.m14045d(obj2, i2))) {
                        continue;
                    }
                case RecyclerView.SCROLL_STATE_SETTLING:
                    if (m13772c(obj, obj2, i) && ilv.m14040b(obj, i2) == ilv.m14040b(obj2, i2)) {
                        continue;
                    }
                case 3:
                    if (m13772c(obj, obj2, i) && ilv.m14040b(obj, i2) == ilv.m14040b(obj2, i2)) {
                        continue;
                    }
                case 4:
                    if (m13772c(obj, obj2, i) && ilv.m14027a(obj, i2) == ilv.m14027a(obj2, i2)) {
                        continue;
                    }
                case 5:
                    if (m13772c(obj, obj2, i) && ilv.m14040b(obj, i2) == ilv.m14040b(obj2, i2)) {
                        continue;
                    }
                case 6:
                    if (m13772c(obj, obj2, i) && ilv.m14027a(obj, i2) == ilv.m14027a(obj2, i2)) {
                        continue;
                    }
                case 7:
                    if (m13772c(obj, obj2, i) && ilv.m14044c(obj, i2) == ilv.m14044c(obj2, i2)) {
                        continue;
                    }
                case 8:
                    if (m13772c(obj, obj2, i) && ila.m13918a(ilv.m14048f(obj, i2), ilv.m14048f(obj2, i2))) {
                        continue;
                    }
                case 9:
                    if (m13772c(obj, obj2, i) && ila.m13918a(ilv.m14048f(obj, i2), ilv.m14048f(obj2, i2))) {
                        continue;
                    }
                case 10:
                    if (m13772c(obj, obj2, i) && ila.m13918a(ilv.m14048f(obj, i2), ilv.m14048f(obj2, i2))) {
                        continue;
                    }
                case 11:
                    if (m13772c(obj, obj2, i) && ilv.m14027a(obj, i2) == ilv.m14027a(obj2, i2)) {
                        continue;
                    }
                case 12:
                    if (m13772c(obj, obj2, i) && ilv.m14027a(obj, i2) == ilv.m14027a(obj2, i2)) {
                        continue;
                    }
                case 13:
                    if (m13772c(obj, obj2, i) && ilv.m14027a(obj, i2) == ilv.m14027a(obj2, i2)) {
                        continue;
                    }
                case 14:
                    if (m13772c(obj, obj2, i) && ilv.m14040b(obj, i2) == ilv.m14040b(obj2, i2)) {
                        continue;
                    }
                case 15:
                    if (m13772c(obj, obj2, i) && ilv.m14027a(obj, i2) == ilv.m14027a(obj2, i2)) {
                        continue;
                    }
                case 16:
                    if (m13772c(obj, obj2, i) && ilv.m14040b(obj, i2) == ilv.m14040b(obj2, i2)) {
                        continue;
                    }
                case 17:
                    if (m13772c(obj, obj2, i) && ila.m13918a(ilv.m14048f(obj, i2), ilv.m14048f(obj2, i2))) {
                        continue;
                    }
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    z = ila.m13918a(ilv.m14048f(obj, i2), ilv.m14048f(obj2, i2));
                    break;
                case 50:
                    z = ila.m13918a(ilv.m14048f(obj, i2), ilv.m14048f(obj2, i2));
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                case 60:
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                case 68:
                    long f = (long) (m13778f(i) & 1048575);
                    if (ilv.m14027a(obj, f) == ilv.m14027a(obj2, f) && ila.m13918a(ilv.m14048f(obj, i2), ilv.m14048f(obj2, i2))) {
                        continue;
                    }
            }
            if (!z) {
                return false;
            }
        }
        if (!imu.m14135a(obj).equals(imu.m14135a(obj2))) {
            return false;
        }
        if (this.f14382h) {
            return imi.m14098a(obj).equals(imi.m14098a(obj2));
        }
        return true;
    }

    /* renamed from: a */
    private final Object m13750a(Object obj, int i, Object obj2) {
        int d = m13773d(i);
        Object f = ilv.m14048f(obj, m13785i(m13775e(i)));
        if (f == null) {
            return obj2;
        }
        ijb c = m13770c(i);
        if (c != null) {
            Map a = this.f14390p.mo8854a(f);
            ijw e = this.f14390p.mo8858e(m13763b(i));
            Iterator it = a.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                if (!c.mo2351a(((Integer) entry.getValue()).intValue())) {
                    if (obj2 == null) {
                        obj2 = ilm.m13974a();
                    }
                    ihr c2 = ihw.m13168c(ijx.m13699a(e, entry.getKey(), entry.getValue()));
                    try {
                        ijx.m13701a(c2.f14194a, e, entry.getKey(), entry.getValue());
                        ((ilm) obj2).mo8943a(imd.m14073a(d, 2), (Object) c2.mo8602a());
                        it.remove();
                    } catch (IOException e2) {
                        throw new RuntimeException(e2);
                    }
                }
            }
        }
        return obj2;
    }

    /* renamed from: c */
    private final ijb m13770c(int i) {
        int i2 = i / 3;
        return (ijb) this.f14378d[i2 + i2 + 1];
    }

    /* renamed from: b */
    private final Object m13763b(int i) {
        int i2 = i / 3;
        return this.f14378d[i2 + i2];
    }

    /* renamed from: a */
    private final iky m13749a(int i) {
        int i2 = i / 3;
        int i3 = i2 + i2;
        iky iky = (iky) this.f14378d[i3];
        if (iky != null) {
            return iky;
        }
        iky a = ikp.f14397a.mo8875a((Class) this.f14378d[i3 + 1]);
        this.f14378d[i3] = a;
        return a;
    }

    /* renamed from: g */
    private static ilm m13782g(Object obj) {
        iix iix = (iix) obj;
        ilm ilm = iix.f14326z;
        if (ilm != ilm.f14449a) {
            return ilm;
        }
        ilm a = ilm.m13974a();
        iix.f14326z = a;
        return a;
    }

    /* renamed from: b */
    public final int mo8869b(Object obj) {
        return this.f14384j ? m13779f(obj) : m13776e(obj);
    }

    /* renamed from: e */
    private final int m13776e(Object obj) {
        int i;
        Unsafe unsafe = f14376b;
        int i2 = -1;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < this.f14377c.length; i5 += 3) {
            int e = m13775e(i5);
            int d = m13773d(i5);
            int g = m13781g(e);
            if (g <= 17) {
                int i6 = this.f14377c[i5 + 2];
                int i7 = 1048575 & i6;
                i = 1 << (i6 >>> 20);
                if (i7 != i2) {
                    i4 = unsafe.getInt(obj, (long) i7);
                    i2 = i7;
                }
            } else {
                i = 0;
            }
            long i8 = m13785i(e);
            switch (g) {
                case 0:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += iie.m13432l(d);
                        break;
                    }
                case 1:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += iie.m13435o(d);
                        break;
                    }
                case RecyclerView.SCROLL_STATE_SETTLING:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += iie.m13415d(d, unsafe.getLong(obj, i8));
                        break;
                    }
                case 3:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += iie.m13418e(d, unsafe.getLong(obj, i8));
                        break;
                    }
                case 4:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += iie.m13421f(d, unsafe.getInt(obj, i8));
                        break;
                    }
                case 5:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += iie.m13434n(d);
                        break;
                    }
                case 6:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += iie.m13433m(d);
                        break;
                    }
                case 7:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += iie.m13431k(d);
                        break;
                    }
                case 8:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        Object object = unsafe.getObject(obj, i8);
                        if (!(object instanceof ihw)) {
                            i3 += iie.m13408b(d, (String) object);
                            break;
                        } else {
                            i3 += iie.m13413c(d, (ihw) object);
                            break;
                        }
                    }
                case 9:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += ila.m13906a(d, unsafe.getObject(obj, i8), m13749a(i5));
                        break;
                    }
                case 10:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += iie.m13413c(d, (ihw) unsafe.getObject(obj, i8));
                        break;
                    }
                case 11:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += iie.m13425g(d, unsafe.getInt(obj, i8));
                        break;
                    }
                case 12:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += iie.m13429i(d, unsafe.getInt(obj, i8));
                        break;
                    }
                case 13:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += iie.m13436p(d);
                        break;
                    }
                case 14:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += iie.m13437q(d);
                        break;
                    }
                case 15:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += iie.m13427h(d, unsafe.getInt(obj, i8));
                        break;
                    }
                case 16:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += iie.m13422f(d, unsafe.getLong(obj, i8));
                        break;
                    }
                case 17:
                    if ((i4 & i) == 0) {
                        break;
                    } else {
                        i3 += iie.m13407b(d, (ikf) unsafe.getObject(obj, i8), m13749a(i5));
                        break;
                    }
                case 18:
                    i3 += ila.m13936f(d, (List) unsafe.getObject(obj, i8));
                    break;
                case 19:
                    i3 += ila.m13933e(d, (List) unsafe.getObject(obj, i8));
                    break;
                case 20:
                    i3 += ila.m13942h(d, (List) unsafe.getObject(obj, i8));
                    break;
                case 21:
                    i3 += ila.m13953l(d, (List) unsafe.getObject(obj, i8));
                    break;
                case 22:
                    i3 += ila.m13939g(d, (List) unsafe.getObject(obj, i8));
                    break;
                case 23:
                    i3 += ila.m13936f(d, (List) unsafe.getObject(obj, i8));
                    break;
                case 24:
                    i3 += ila.m13933e(d, (List) unsafe.getObject(obj, i8));
                    break;
                case 25:
                    i3 += ila.m13926c(d, (List) unsafe.getObject(obj, i8));
                    break;
                case 26:
                    i3 += ila.m13907a(d, (List) unsafe.getObject(obj, i8));
                    break;
                case 27:
                    i3 += ila.m13908a(d, (List) unsafe.getObject(obj, i8), m13749a(i5));
                    break;
                case 28:
                    i3 += ila.m13919b(d, (List) unsafe.getObject(obj, i8));
                    break;
                case 29:
                    i3 += ila.m13951k(d, (List) unsafe.getObject(obj, i8));
                    break;
                case 30:
                    i3 += ila.m13930d(d, (List) unsafe.getObject(obj, i8));
                    break;
                case 31:
                    i3 += ila.m13933e(d, (List) unsafe.getObject(obj, i8));
                    break;
                case 32:
                    i3 += ila.m13936f(d, (List) unsafe.getObject(obj, i8));
                    break;
                case 33:
                    i3 += ila.m13945i(d, (List) unsafe.getObject(obj, i8));
                    break;
                case 34:
                    i3 += ila.m13948j(d, (List) unsafe.getObject(obj, i8));
                    break;
                case 35:
                    int i9 = ila.m13946i((List) unsafe.getObject(obj, i8));
                    if (i9 <= 0) {
                        break;
                    } else {
                        i3 += iie.m13420f(d) + iie.m13426h(i9) + i9;
                        break;
                    }
                case 36:
                    int h = ila.m13943h((List) unsafe.getObject(obj, i8));
                    if (h <= 0) {
                        break;
                    } else {
                        i3 += iie.m13420f(d) + iie.m13426h(h) + h;
                        break;
                    }
                case 37:
                    int a = ila.m13909a((List) unsafe.getObject(obj, i8));
                    if (a <= 0) {
                        break;
                    } else {
                        i3 += iie.m13420f(d) + iie.m13426h(a) + a;
                        break;
                    }
                case 38:
                    int b = ila.m13921b((List) unsafe.getObject(obj, i8));
                    if (b <= 0) {
                        break;
                    } else {
                        i3 += iie.m13420f(d) + iie.m13426h(b) + b;
                        break;
                    }
                case 39:
                    int e2 = ila.m13934e((List) unsafe.getObject(obj, i8));
                    if (e2 <= 0) {
                        break;
                    } else {
                        i3 += iie.m13420f(d) + iie.m13426h(e2) + e2;
                        break;
                    }
                case 40:
                    int i10 = ila.m13946i((List) unsafe.getObject(obj, i8));
                    if (i10 <= 0) {
                        break;
                    } else {
                        i3 += iie.m13420f(d) + iie.m13426h(i10) + i10;
                        break;
                    }
                case 41:
                    int h2 = ila.m13943h((List) unsafe.getObject(obj, i8));
                    if (h2 <= 0) {
                        break;
                    } else {
                        i3 += iie.m13420f(d) + iie.m13426h(h2) + h2;
                        break;
                    }
                case 42:
                    int j = ila.m13949j((List) unsafe.getObject(obj, i8));
                    if (j <= 0) {
                        break;
                    } else {
                        i3 += iie.m13420f(d) + iie.m13426h(j) + j;
                        break;
                    }
                case 43:
                    int f = ila.m13937f((List) unsafe.getObject(obj, i8));
                    if (f <= 0) {
                        break;
                    } else {
                        i3 += iie.m13420f(d) + iie.m13426h(f) + f;
                        break;
                    }
                case 44:
                    int d2 = ila.m13931d((List) unsafe.getObject(obj, i8));
                    if (d2 <= 0) {
                        break;
                    } else {
                        i3 += iie.m13420f(d) + iie.m13426h(d2) + d2;
                        break;
                    }
                case 45:
                    int h3 = ila.m13943h((List) unsafe.getObject(obj, i8));
                    if (h3 <= 0) {
                        break;
                    } else {
                        i3 += iie.m13420f(d) + iie.m13426h(h3) + h3;
                        break;
                    }
                case 46:
                    int i11 = ila.m13946i((List) unsafe.getObject(obj, i8));
                    if (i11 <= 0) {
                        break;
                    } else {
                        i3 += iie.m13420f(d) + iie.m13426h(i11) + i11;
                        break;
                    }
                case 47:
                    int g2 = ila.m13940g((List) unsafe.getObject(obj, i8));
                    if (g2 <= 0) {
                        break;
                    } else {
                        i3 += iie.m13420f(d) + iie.m13426h(g2) + g2;
                        break;
                    }
                case 48:
                    int c = ila.m13927c((List) unsafe.getObject(obj, i8));
                    if (c <= 0) {
                        break;
                    } else {
                        i3 += iie.m13420f(d) + iie.m13426h(c) + c;
                        break;
                    }
                case 49:
                    i3 += ila.m13920b(d, (List) unsafe.getObject(obj, i8), m13749a(i5));
                    break;
                case 50:
                    i3 += this.f14390p.mo8851a(d, unsafe.getObject(obj, i8), m13763b(i5));
                    break;
                case 51:
                    if (!m13758a(obj, d, i5)) {
                        break;
                    } else {
                        i3 += iie.m13432l(d);
                        break;
                    }
                case 52:
                    if (!m13758a(obj, d, i5)) {
                        break;
                    } else {
                        i3 += iie.m13435o(d);
                        break;
                    }
                case 53:
                    if (!m13758a(obj, d, i5)) {
                        break;
                    } else {
                        i3 += iie.m13415d(d, m13777e(obj, i8));
                        break;
                    }
                case 54:
                    if (!m13758a(obj, d, i5)) {
                        break;
                    } else {
                        i3 += iie.m13418e(d, m13777e(obj, i8));
                        break;
                    }
                case 55:
                    if (!m13758a(obj, d, i5)) {
                        break;
                    } else {
                        i3 += iie.m13421f(d, m13774d(obj, i8));
                        break;
                    }
                case 56:
                    if (!m13758a(obj, d, i5)) {
                        break;
                    } else {
                        i3 += iie.m13434n(d);
                        break;
                    }
                case 57:
                    if (!m13758a(obj, d, i5)) {
                        break;
                    } else {
                        i3 += iie.m13433m(d);
                        break;
                    }
                case 58:
                    if (!m13758a(obj, d, i5)) {
                        break;
                    } else {
                        i3 += iie.m13431k(d);
                        break;
                    }
                case 59:
                    if (!m13758a(obj, d, i5)) {
                        break;
                    } else {
                        Object object2 = unsafe.getObject(obj, i8);
                        if (!(object2 instanceof ihw)) {
                            i3 += iie.m13408b(d, (String) object2);
                            break;
                        } else {
                            i3 += iie.m13413c(d, (ihw) object2);
                            break;
                        }
                    }
                case 60:
                    if (!m13758a(obj, d, i5)) {
                        break;
                    } else {
                        i3 += ila.m13906a(d, unsafe.getObject(obj, i8), m13749a(i5));
                        break;
                    }
                case 61:
                    if (!m13758a(obj, d, i5)) {
                        break;
                    } else {
                        i3 += iie.m13413c(d, (ihw) unsafe.getObject(obj, i8));
                        break;
                    }
                case 62:
                    if (!m13758a(obj, d, i5)) {
                        break;
                    } else {
                        i3 += iie.m13425g(d, m13774d(obj, i8));
                        break;
                    }
                case 63:
                    if (!m13758a(obj, d, i5)) {
                        break;
                    } else {
                        i3 += iie.m13429i(d, m13774d(obj, i8));
                        break;
                    }
                case 64:
                    if (!m13758a(obj, d, i5)) {
                        break;
                    } else {
                        i3 += iie.m13436p(d);
                        break;
                    }
                case 65:
                    if (!m13758a(obj, d, i5)) {
                        break;
                    } else {
                        i3 += iie.m13437q(d);
                        break;
                    }
                case 66:
                    if (!m13758a(obj, d, i5)) {
                        break;
                    } else {
                        i3 += iie.m13427h(d, m13774d(obj, i8));
                        break;
                    }
                case 67:
                    if (!m13758a(obj, d, i5)) {
                        break;
                    } else {
                        i3 += iie.m13422f(d, m13777e(obj, i8));
                        break;
                    }
                case 68:
                    if (!m13758a(obj, d, i5)) {
                        break;
                    } else {
                        i3 += iie.m13407b(d, (ikf) unsafe.getObject(obj, i8), m13749a(i5));
                        break;
                    }
            }
        }
        int h4 = i3 + m13783h(obj);
        if (!this.f14382h) {
            return h4;
        }
        iim a2 = imi.m14098a(obj);
        int i12 = 0;
        for (int i13 = 0; i13 < a2.f14255a.mo8916a(); i13++) {
            Map.Entry b2 = a2.f14255a.mo8919b(i13);
            i12 += iim.m13515b((iil) b2.getKey(), b2.getValue());
        }
        for (Map.Entry entry : a2.f14255a.mo8918b()) {
            i12 += iim.m13515b((iil) entry.getKey(), entry.getValue());
        }
        return h4 + i12;
    }

    /* renamed from: f */
    private final int m13779f(Object obj) {
        Unsafe unsafe = f14376b;
        int i = 0;
        for (int i2 = 0; i2 < this.f14377c.length; i2 += 3) {
            int e = m13775e(i2);
            int g = m13781g(e);
            int d = m13773d(i2);
            long i3 = m13785i(e);
            if (g >= iin.DOUBLE_LIST_PACKED.f14311c) {
                int i4 = iin.SINT64_LIST_PACKED.f14311c;
            }
            switch (g) {
                case 0:
                    if (!m13757a(obj, i2)) {
                        break;
                    } else {
                        i += iie.m13432l(d);
                        break;
                    }
                case 1:
                    if (!m13757a(obj, i2)) {
                        break;
                    } else {
                        i += iie.m13435o(d);
                        break;
                    }
                case RecyclerView.SCROLL_STATE_SETTLING:
                    if (!m13757a(obj, i2)) {
                        break;
                    } else {
                        i += iie.m13415d(d, ilv.m14040b(obj, i3));
                        break;
                    }
                case 3:
                    if (!m13757a(obj, i2)) {
                        break;
                    } else {
                        i += iie.m13418e(d, ilv.m14040b(obj, i3));
                        break;
                    }
                case 4:
                    if (!m13757a(obj, i2)) {
                        break;
                    } else {
                        i += iie.m13421f(d, ilv.m14027a(obj, i3));
                        break;
                    }
                case 5:
                    if (!m13757a(obj, i2)) {
                        break;
                    } else {
                        i += iie.m13434n(d);
                        break;
                    }
                case 6:
                    if (!m13757a(obj, i2)) {
                        break;
                    } else {
                        i += iie.m13433m(d);
                        break;
                    }
                case 7:
                    if (!m13757a(obj, i2)) {
                        break;
                    } else {
                        i += iie.m13431k(d);
                        break;
                    }
                case 8:
                    if (!m13757a(obj, i2)) {
                        break;
                    } else {
                        Object f = ilv.m14048f(obj, i3);
                        if (!(f instanceof ihw)) {
                            i += iie.m13408b(d, (String) f);
                            break;
                        } else {
                            i += iie.m13413c(d, (ihw) f);
                            break;
                        }
                    }
                case 9:
                    if (!m13757a(obj, i2)) {
                        break;
                    } else {
                        i += ila.m13906a(d, ilv.m14048f(obj, i3), m13749a(i2));
                        break;
                    }
                case 10:
                    if (!m13757a(obj, i2)) {
                        break;
                    } else {
                        i += iie.m13413c(d, (ihw) ilv.m14048f(obj, i3));
                        break;
                    }
                case 11:
                    if (!m13757a(obj, i2)) {
                        break;
                    } else {
                        i += iie.m13425g(d, ilv.m14027a(obj, i3));
                        break;
                    }
                case 12:
                    if (!m13757a(obj, i2)) {
                        break;
                    } else {
                        i += iie.m13429i(d, ilv.m14027a(obj, i3));
                        break;
                    }
                case 13:
                    if (!m13757a(obj, i2)) {
                        break;
                    } else {
                        i += iie.m13436p(d);
                        break;
                    }
                case 14:
                    if (!m13757a(obj, i2)) {
                        break;
                    } else {
                        i += iie.m13437q(d);
                        break;
                    }
                case 15:
                    if (!m13757a(obj, i2)) {
                        break;
                    } else {
                        i += iie.m13427h(d, ilv.m14027a(obj, i3));
                        break;
                    }
                case 16:
                    if (!m13757a(obj, i2)) {
                        break;
                    } else {
                        i += iie.m13422f(d, ilv.m14040b(obj, i3));
                        break;
                    }
                case 17:
                    if (!m13757a(obj, i2)) {
                        break;
                    } else {
                        i += iie.m13407b(d, (ikf) ilv.m14048f(obj, i3), m13749a(i2));
                        break;
                    }
                case 18:
                    i += ila.m13936f(d, m13752a(obj, i3));
                    break;
                case 19:
                    i += ila.m13933e(d, m13752a(obj, i3));
                    break;
                case 20:
                    i += ila.m13942h(d, m13752a(obj, i3));
                    break;
                case 21:
                    i += ila.m13953l(d, m13752a(obj, i3));
                    break;
                case 22:
                    i += ila.m13939g(d, m13752a(obj, i3));
                    break;
                case 23:
                    i += ila.m13936f(d, m13752a(obj, i3));
                    break;
                case 24:
                    i += ila.m13933e(d, m13752a(obj, i3));
                    break;
                case 25:
                    i += ila.m13926c(d, m13752a(obj, i3));
                    break;
                case 26:
                    i += ila.m13907a(d, m13752a(obj, i3));
                    break;
                case 27:
                    i += ila.m13908a(d, m13752a(obj, i3), m13749a(i2));
                    break;
                case 28:
                    i += ila.m13919b(d, m13752a(obj, i3));
                    break;
                case 29:
                    i += ila.m13951k(d, m13752a(obj, i3));
                    break;
                case 30:
                    i += ila.m13930d(d, m13752a(obj, i3));
                    break;
                case 31:
                    i += ila.m13933e(d, m13752a(obj, i3));
                    break;
                case 32:
                    i += ila.m13936f(d, m13752a(obj, i3));
                    break;
                case 33:
                    i += ila.m13945i(d, m13752a(obj, i3));
                    break;
                case 34:
                    i += ila.m13948j(d, m13752a(obj, i3));
                    break;
                case 35:
                    int i5 = ila.m13946i((List) unsafe.getObject(obj, i3));
                    if (i5 <= 0) {
                        break;
                    } else {
                        i += iie.m13420f(d) + iie.m13426h(i5) + i5;
                        break;
                    }
                case 36:
                    int h = ila.m13943h((List) unsafe.getObject(obj, i3));
                    if (h <= 0) {
                        break;
                    } else {
                        i += iie.m13420f(d) + iie.m13426h(h) + h;
                        break;
                    }
                case 37:
                    int a = ila.m13909a((List) unsafe.getObject(obj, i3));
                    if (a <= 0) {
                        break;
                    } else {
                        i += iie.m13420f(d) + iie.m13426h(a) + a;
                        break;
                    }
                case 38:
                    int b = ila.m13921b((List) unsafe.getObject(obj, i3));
                    if (b <= 0) {
                        break;
                    } else {
                        i += iie.m13420f(d) + iie.m13426h(b) + b;
                        break;
                    }
                case 39:
                    int e2 = ila.m13934e((List) unsafe.getObject(obj, i3));
                    if (e2 <= 0) {
                        break;
                    } else {
                        i += iie.m13420f(d) + iie.m13426h(e2) + e2;
                        break;
                    }
                case 40:
                    int i6 = ila.m13946i((List) unsafe.getObject(obj, i3));
                    if (i6 <= 0) {
                        break;
                    } else {
                        i += iie.m13420f(d) + iie.m13426h(i6) + i6;
                        break;
                    }
                case 41:
                    int h2 = ila.m13943h((List) unsafe.getObject(obj, i3));
                    if (h2 <= 0) {
                        break;
                    } else {
                        i += iie.m13420f(d) + iie.m13426h(h2) + h2;
                        break;
                    }
                case 42:
                    int j = ila.m13949j((List) unsafe.getObject(obj, i3));
                    if (j <= 0) {
                        break;
                    } else {
                        i += iie.m13420f(d) + iie.m13426h(j) + j;
                        break;
                    }
                case 43:
                    int f2 = ila.m13937f((List) unsafe.getObject(obj, i3));
                    if (f2 <= 0) {
                        break;
                    } else {
                        i += iie.m13420f(d) + iie.m13426h(f2) + f2;
                        break;
                    }
                case 44:
                    int d2 = ila.m13931d((List) unsafe.getObject(obj, i3));
                    if (d2 <= 0) {
                        break;
                    } else {
                        i += iie.m13420f(d) + iie.m13426h(d2) + d2;
                        break;
                    }
                case 45:
                    int h3 = ila.m13943h((List) unsafe.getObject(obj, i3));
                    if (h3 <= 0) {
                        break;
                    } else {
                        i += iie.m13420f(d) + iie.m13426h(h3) + h3;
                        break;
                    }
                case 46:
                    int i7 = ila.m13946i((List) unsafe.getObject(obj, i3));
                    if (i7 <= 0) {
                        break;
                    } else {
                        i += iie.m13420f(d) + iie.m13426h(i7) + i7;
                        break;
                    }
                case 47:
                    int g2 = ila.m13940g((List) unsafe.getObject(obj, i3));
                    if (g2 <= 0) {
                        break;
                    } else {
                        i += iie.m13420f(d) + iie.m13426h(g2) + g2;
                        break;
                    }
                case 48:
                    int c = ila.m13927c((List) unsafe.getObject(obj, i3));
                    if (c <= 0) {
                        break;
                    } else {
                        i += iie.m13420f(d) + iie.m13426h(c) + c;
                        break;
                    }
                case 49:
                    i += ila.m13920b(d, m13752a(obj, i3), m13749a(i2));
                    break;
                case 50:
                    i += this.f14390p.mo8851a(d, ilv.m14048f(obj, i3), m13763b(i2));
                    break;
                case 51:
                    if (!m13758a(obj, d, i2)) {
                        break;
                    } else {
                        i += iie.m13432l(d);
                        break;
                    }
                case 52:
                    if (!m13758a(obj, d, i2)) {
                        break;
                    } else {
                        i += iie.m13435o(d);
                        break;
                    }
                case 53:
                    if (!m13758a(obj, d, i2)) {
                        break;
                    } else {
                        i += iie.m13415d(d, m13777e(obj, i3));
                        break;
                    }
                case 54:
                    if (!m13758a(obj, d, i2)) {
                        break;
                    } else {
                        i += iie.m13418e(d, m13777e(obj, i3));
                        break;
                    }
                case 55:
                    if (!m13758a(obj, d, i2)) {
                        break;
                    } else {
                        i += iie.m13421f(d, m13774d(obj, i3));
                        break;
                    }
                case 56:
                    if (!m13758a(obj, d, i2)) {
                        break;
                    } else {
                        i += iie.m13434n(d);
                        break;
                    }
                case 57:
                    if (!m13758a(obj, d, i2)) {
                        break;
                    } else {
                        i += iie.m13433m(d);
                        break;
                    }
                case 58:
                    if (!m13758a(obj, d, i2)) {
                        break;
                    } else {
                        i += iie.m13431k(d);
                        break;
                    }
                case 59:
                    if (!m13758a(obj, d, i2)) {
                        break;
                    } else {
                        Object f3 = ilv.m14048f(obj, i3);
                        if (!(f3 instanceof ihw)) {
                            i += iie.m13408b(d, (String) f3);
                            break;
                        } else {
                            i += iie.m13413c(d, (ihw) f3);
                            break;
                        }
                    }
                case 60:
                    if (!m13758a(obj, d, i2)) {
                        break;
                    } else {
                        i += ila.m13906a(d, ilv.m14048f(obj, i3), m13749a(i2));
                        break;
                    }
                case 61:
                    if (!m13758a(obj, d, i2)) {
                        break;
                    } else {
                        i += iie.m13413c(d, (ihw) ilv.m14048f(obj, i3));
                        break;
                    }
                case 62:
                    if (!m13758a(obj, d, i2)) {
                        break;
                    } else {
                        i += iie.m13425g(d, m13774d(obj, i3));
                        break;
                    }
                case 63:
                    if (!m13758a(obj, d, i2)) {
                        break;
                    } else {
                        i += iie.m13429i(d, m13774d(obj, i3));
                        break;
                    }
                case 64:
                    if (!m13758a(obj, d, i2)) {
                        break;
                    } else {
                        i += iie.m13436p(d);
                        break;
                    }
                case 65:
                    if (!m13758a(obj, d, i2)) {
                        break;
                    } else {
                        i += iie.m13437q(d);
                        break;
                    }
                case 66:
                    if (!m13758a(obj, d, i2)) {
                        break;
                    } else {
                        i += iie.m13427h(d, m13774d(obj, i3));
                        break;
                    }
                case 67:
                    if (!m13758a(obj, d, i2)) {
                        break;
                    } else {
                        i += iie.m13422f(d, m13777e(obj, i3));
                        break;
                    }
                case 68:
                    if (!m13758a(obj, d, i2)) {
                        break;
                    } else {
                        i += iie.m13407b(d, (ikf) ilv.m14048f(obj, i3), m13749a(i2));
                        break;
                    }
            }
        }
        return i + m13783h(obj);
    }

    /* renamed from: h */
    private static final int m13783h(Object obj) {
        return imu.m14135a(obj).mo8946c();
    }

    /* renamed from: a */
    public final int mo8862a(Object obj) {
        int length = this.f14377c.length;
        int i = 0;
        for (int i2 = 0; i2 < length; i2 += 3) {
            int e = m13775e(i2);
            int d = m13773d(i2);
            long i3 = m13785i(e);
            int i4 = 37;
            switch (m13781g(e)) {
                case 0:
                    i = (i * 53) + ijf.m13645a(Double.doubleToLongBits(ilv.m14047e(obj, i3)));
                    break;
                case 1:
                    i = (i * 53) + Float.floatToIntBits(ilv.m14045d(obj, i3));
                    break;
                case RecyclerView.SCROLL_STATE_SETTLING:
                    i = (i * 53) + ijf.m13645a(ilv.m14040b(obj, i3));
                    break;
                case 3:
                    i = (i * 53) + ijf.m13645a(ilv.m14040b(obj, i3));
                    break;
                case 4:
                    i = (i * 53) + ilv.m14027a(obj, i3);
                    break;
                case 5:
                    i = (i * 53) + ijf.m13645a(ilv.m14040b(obj, i3));
                    break;
                case 6:
                    i = (i * 53) + ilv.m14027a(obj, i3);
                    break;
                case 7:
                    i = (i * 53) + ijf.m13646a(ilv.m14044c(obj, i3));
                    break;
                case 8:
                    i = (i * 53) + ((String) ilv.m14048f(obj, i3)).hashCode();
                    break;
                case 9:
                    Object f = ilv.m14048f(obj, i3);
                    if (f != null) {
                        i4 = f.hashCode();
                    }
                    i = (i * 53) + i4;
                    break;
                case 10:
                    i = (i * 53) + ilv.m14048f(obj, i3).hashCode();
                    break;
                case 11:
                    i = (i * 53) + ilv.m14027a(obj, i3);
                    break;
                case 12:
                    i = (i * 53) + ilv.m14027a(obj, i3);
                    break;
                case 13:
                    i = (i * 53) + ilv.m14027a(obj, i3);
                    break;
                case 14:
                    i = (i * 53) + ijf.m13645a(ilv.m14040b(obj, i3));
                    break;
                case 15:
                    i = (i * 53) + ilv.m14027a(obj, i3);
                    break;
                case 16:
                    i = (i * 53) + ijf.m13645a(ilv.m14040b(obj, i3));
                    break;
                case 17:
                    Object f2 = ilv.m14048f(obj, i3);
                    if (f2 != null) {
                        i4 = f2.hashCode();
                    }
                    i = (i * 53) + i4;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    i = (i * 53) + ilv.m14048f(obj, i3).hashCode();
                    break;
                case 50:
                    i = (i * 53) + ilv.m14048f(obj, i3).hashCode();
                    break;
                case 51:
                    if (!m13758a(obj, d, i2)) {
                        break;
                    } else {
                        i = (i * 53) + ijf.m13645a(Double.doubleToLongBits(m13761b(obj, i3)));
                        break;
                    }
                case 52:
                    if (!m13758a(obj, d, i2)) {
                        break;
                    } else {
                        i = (i * 53) + Float.floatToIntBits(m13769c(obj, i3));
                        break;
                    }
                case 53:
                    if (!m13758a(obj, d, i2)) {
                        break;
                    } else {
                        i = (i * 53) + ijf.m13645a(m13777e(obj, i3));
                        break;
                    }
                case 54:
                    if (!m13758a(obj, d, i2)) {
                        break;
                    } else {
                        i = (i * 53) + ijf.m13645a(m13777e(obj, i3));
                        break;
                    }
                case 55:
                    if (!m13758a(obj, d, i2)) {
                        break;
                    } else {
                        i = (i * 53) + m13774d(obj, i3);
                        break;
                    }
                case 56:
                    if (!m13758a(obj, d, i2)) {
                        break;
                    } else {
                        i = (i * 53) + ijf.m13645a(m13777e(obj, i3));
                        break;
                    }
                case 57:
                    if (!m13758a(obj, d, i2)) {
                        break;
                    } else {
                        i = (i * 53) + m13774d(obj, i3);
                        break;
                    }
                case 58:
                    if (!m13758a(obj, d, i2)) {
                        break;
                    } else {
                        i = (i * 53) + ijf.m13646a(m13780f(obj, i3));
                        break;
                    }
                case 59:
                    if (!m13758a(obj, d, i2)) {
                        break;
                    } else {
                        i = (i * 53) + ((String) ilv.m14048f(obj, i3)).hashCode();
                        break;
                    }
                case 60:
                    if (!m13758a(obj, d, i2)) {
                        break;
                    } else {
                        i = (i * 53) + ilv.m14048f(obj, i3).hashCode();
                        break;
                    }
                case 61:
                    if (!m13758a(obj, d, i2)) {
                        break;
                    } else {
                        i = (i * 53) + ilv.m14048f(obj, i3).hashCode();
                        break;
                    }
                case 62:
                    if (!m13758a(obj, d, i2)) {
                        break;
                    } else {
                        i = (i * 53) + m13774d(obj, i3);
                        break;
                    }
                case 63:
                    if (!m13758a(obj, d, i2)) {
                        break;
                    } else {
                        i = (i * 53) + m13774d(obj, i3);
                        break;
                    }
                case 64:
                    if (!m13758a(obj, d, i2)) {
                        break;
                    } else {
                        i = (i * 53) + m13774d(obj, i3);
                        break;
                    }
                case 65:
                    if (!m13758a(obj, d, i2)) {
                        break;
                    } else {
                        i = (i * 53) + ijf.m13645a(m13777e(obj, i3));
                        break;
                    }
                case 66:
                    if (!m13758a(obj, d, i2)) {
                        break;
                    } else {
                        i = (i * 53) + m13774d(obj, i3);
                        break;
                    }
                case 67:
                    if (!m13758a(obj, d, i2)) {
                        break;
                    } else {
                        i = (i * 53) + ijf.m13645a(m13777e(obj, i3));
                        break;
                    }
                case 68:
                    if (!m13758a(obj, d, i2)) {
                        break;
                    } else {
                        i = (i * 53) + ilv.m14048f(obj, i3).hashCode();
                        break;
                    }
            }
        }
        int hashCode = (i * 53) + imu.m14135a(obj).hashCode();
        return this.f14382h ? (hashCode * 53) + imi.m14098a(obj).hashCode() : hashCode;
    }

    /* renamed from: a */
    private final boolean m13757a(Object obj, int i) {
        if (!this.f14384j) {
            int f = m13778f(i);
            return (ilv.m14027a(obj, (long) (1048575 & f)) & (1 << (f >>> 20))) != 0;
        }
        int e = m13775e(i);
        long i2 = m13785i(e);
        switch (m13781g(e)) {
            case 0:
                return ilv.m14047e(obj, i2) != 0.0d;
            case 1:
                return ilv.m14045d(obj, i2) != 0.0f;
            case RecyclerView.SCROLL_STATE_SETTLING:
                return ilv.m14040b(obj, i2) != 0;
            case 3:
                return ilv.m14040b(obj, i2) != 0;
            case 4:
                return ilv.m14027a(obj, i2) != 0;
            case 5:
                return ilv.m14040b(obj, i2) != 0;
            case 6:
                return ilv.m14027a(obj, i2) != 0;
            case 7:
                return ilv.m14044c(obj, i2);
            case 8:
                Object f2 = ilv.m14048f(obj, i2);
                if (f2 instanceof String) {
                    return !((String) f2).isEmpty();
                }
                if (f2 instanceof ihw) {
                    return !ihw.f14203b.equals(f2);
                }
                throw new IllegalArgumentException();
            case 9:
                return ilv.m14048f(obj, i2) != null;
            case 10:
                return !ihw.f14203b.equals(ilv.m14048f(obj, i2));
            case 11:
                return ilv.m14027a(obj, i2) != 0;
            case 12:
                return ilv.m14027a(obj, i2) != 0;
            case 13:
                return ilv.m14027a(obj, i2) != 0;
            case 14:
                return ilv.m14040b(obj, i2) != 0;
            case 15:
                return ilv.m14027a(obj, i2) != 0;
            case 16:
                return ilv.m14040b(obj, i2) != 0;
            case 17:
                return ilv.m14048f(obj, i2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    /* renamed from: a */
    private final boolean m13759a(Object obj, int i, int i2, int i3) {
        if (!this.f14384j) {
            return (i2 & i3) != 0;
        }
        return m13757a(obj, i);
    }

    /* renamed from: d */
    public final boolean mo8872d(Object obj) {
        int i;
        int i2 = -1;
        int i3 = 0;
        for (int i4 = 0; i4 < this.f14386l; i4++) {
            int i5 = this.f14385k[i4];
            int d = m13773d(i5);
            int e = m13775e(i5);
            if (!this.f14384j) {
                int i6 = this.f14377c[i5 + 2];
                int i7 = 1048575 & i6;
                i = 1 << (i6 >>> 20);
                if (i7 != i2) {
                    i3 = f14376b.getInt(obj, (long) i7);
                    i2 = i7;
                }
            } else {
                i = 0;
            }
            if ((268435456 & e) != 0 && !m13759a(obj, i5, i3, i)) {
                return false;
            }
            int g = m13781g(e);
            if (g != 9 && g != 17) {
                if (g != 27) {
                    if (g == 60 || g == 68) {
                        if (m13758a(obj, d, i5) && !m13760a(obj, e, m13749a(i5))) {
                            return false;
                        }
                    } else if (g != 49) {
                        if (g != 50) {
                            continue;
                        } else {
                            Map b = this.f14390p.mo8855b(ilv.m14048f(obj, m13785i(e)));
                            if (!b.isEmpty()) {
                                if (this.f14390p.mo8858e(m13763b(i5)).f14365c.f14494i == imc.MESSAGE) {
                                    iky iky = null;
                                    for (Object next : b.values()) {
                                        if (iky == null) {
                                            iky = ikp.f14397a.mo8875a((Class) next.getClass());
                                        }
                                        if (!iky.mo8872d(next)) {
                                            return false;
                                        }
                                    }
                                    continue;
                                } else {
                                    continue;
                                }
                            } else {
                                continue;
                            }
                        }
                    }
                }
                List list = (List) ilv.m14048f(obj, m13785i(e));
                if (!list.isEmpty()) {
                    iky a = m13749a(i5);
                    for (int i8 = 0; i8 < list.size(); i8++) {
                        if (!a.mo8872d(list.get(i8))) {
                            return false;
                        }
                    }
                    continue;
                } else {
                    continue;
                }
            } else if (m13759a(obj, i5, i3, i) && !m13760a(obj, e, m13749a(i5))) {
                return false;
            }
        }
        if (!this.f14382h || imi.m14098a(obj).mo8733e()) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    private static boolean m13760a(Object obj, int i, iky iky) {
        return iky.mo8872d(ilv.m14048f(obj, m13785i(i)));
    }

    /* renamed from: a */
    private final boolean m13758a(Object obj, int i, int i2) {
        return ilv.m14027a(obj, (long) (m13778f(i2) & 1048575)) == i;
    }

    /* renamed from: a */
    private static List m13752a(Object obj, long j) {
        return (List) ilv.m14048f(obj, j);
    }

    /* renamed from: c */
    public final void mo8871c(Object obj) {
        int i;
        int i2 = this.f14386l;
        while (true) {
            i = this.f14387m;
            if (i2 >= i) {
                break;
            }
            long i3 = m13785i(m13775e(this.f14385k[i2]));
            Object f = ilv.m14048f(obj, i3);
            if (f != null) {
                ilv.m14036a(obj, i3, this.f14390p.mo8857d(f));
            }
            i2++;
        }
        int length = this.f14385k.length;
        while (i < length) {
            this.f14389o.mo8826b(obj, (long) this.f14385k[i]);
            i++;
        }
        imu.m14142c(obj);
        if (this.f14382h) {
            imi.m14118c(obj);
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x05a9 A[SYNTHETIC, Splitter:B:125:0x05a9] */
    /* JADX WARNING: Removed duplicated region for block: B:205:0x0709 A[LOOP:5: B:203:0x0705->B:205:0x0709, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:207:0x0717  */
    /* JADX WARNING: Removed duplicated region for block: B:215:0x05b7 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:222:0x0007 A[SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo8865a(java.lang.Object r12, p000.iks r13, p000.iij r14) {
        /*
            r11 = this;
            r0 = 0
            if (r14 == 0) goto L_0x071b
            imu r1 = r11.f14391q
            r2 = r0
            r3 = r2
        L_0x0007:
            int r4 = r13.mo8541a()     // Catch:{ all -> 0x0702 }
            int r5 = r11.m13786j(r4)     // Catch:{ all -> 0x0702 }
            if (r5 < 0) goto L_0x05cf
            int r6 = r11.m13775e((int) r5)     // Catch:{ all -> 0x0702 }
            int r7 = m13781g((int) r6)     // Catch:{ ijg -> 0x05a6 }
            switch(r7) {
                case 0: goto L_0x056d;
                case 1: goto L_0x055d;
                case 2: goto L_0x054d;
                case 3: goto L_0x053d;
                case 4: goto L_0x052d;
                case 5: goto L_0x051d;
                case 6: goto L_0x050d;
                case 7: goto L_0x04fd;
                case 8: goto L_0x04f5;
                case 9: goto L_0x04be;
                case 10: goto L_0x04ae;
                case 11: goto L_0x049e;
                case 12: goto L_0x047b;
                case 13: goto L_0x046b;
                case 14: goto L_0x045b;
                case 15: goto L_0x044b;
                case 16: goto L_0x043b;
                case 17: goto L_0x0404;
                case 18: goto L_0x03f5;
                case 19: goto L_0x03e6;
                case 20: goto L_0x03d7;
                case 21: goto L_0x03c8;
                case 22: goto L_0x03b9;
                case 23: goto L_0x03aa;
                case 24: goto L_0x039b;
                case 25: goto L_0x038c;
                case 26: goto L_0x0368;
                case 27: goto L_0x0355;
                case 28: goto L_0x0346;
                case 29: goto L_0x0337;
                case 30: goto L_0x031f;
                case 31: goto L_0x0310;
                case 32: goto L_0x0301;
                case 33: goto L_0x02f2;
                case 34: goto L_0x02e3;
                case 35: goto L_0x02d4;
                case 36: goto L_0x02c5;
                case 37: goto L_0x02b6;
                case 38: goto L_0x02a7;
                case 39: goto L_0x0298;
                case 40: goto L_0x0289;
                case 41: goto L_0x027a;
                case 42: goto L_0x026b;
                case 43: goto L_0x025c;
                case 44: goto L_0x0244;
                case 45: goto L_0x0235;
                case 46: goto L_0x0226;
                case 47: goto L_0x0217;
                case 48: goto L_0x0208;
                case 49: goto L_0x01f5;
                case 50: goto L_0x01b0;
                case 51: goto L_0x019c;
                case 52: goto L_0x0188;
                case 53: goto L_0x0174;
                case 54: goto L_0x0160;
                case 55: goto L_0x014c;
                case 56: goto L_0x0138;
                case 57: goto L_0x0124;
                case 58: goto L_0x0110;
                case 59: goto L_0x0108;
                case 60: goto L_0x00cf;
                case 61: goto L_0x00bf;
                case 62: goto L_0x00ab;
                case 63: goto L_0x0084;
                case 64: goto L_0x0070;
                case 65: goto L_0x005c;
                case 66: goto L_0x0048;
                case 67: goto L_0x0034;
                case 68: goto L_0x0020;
                default: goto L_0x001c;
            }     // Catch:{ ijg -> 0x05a6 }
        L_0x001c:
            if (r2 == 0) goto L_0x057d
            goto L_0x0583
        L_0x0020:
            long r6 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            iky r8 = r11.m13749a((int) r5)     // Catch:{ ijg -> 0x05a6 }
            java.lang.Object r8 = r13.mo8548b((p000.iky) r8, (p000.iij) r14)     // Catch:{ ijg -> 0x05a6 }
            p000.ilv.m14036a((java.lang.Object) r12, (long) r6, (java.lang.Object) r8)     // Catch:{ ijg -> 0x05a6 }
            r11.m13765b((java.lang.Object) r12, (int) r4, (int) r5)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x0034:
            long r6 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            long r8 = r13.mo8584t()     // Catch:{ ijg -> 0x05a6 }
            java.lang.Long r8 = java.lang.Long.valueOf(r8)     // Catch:{ ijg -> 0x05a6 }
            p000.ilv.m14036a((java.lang.Object) r12, (long) r6, (java.lang.Object) r8)     // Catch:{ ijg -> 0x05a6 }
            r11.m13765b((java.lang.Object) r12, (int) r4, (int) r5)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x0048:
            long r6 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            int r8 = r13.mo8583s()     // Catch:{ ijg -> 0x05a6 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ ijg -> 0x05a6 }
            p000.ilv.m14036a((java.lang.Object) r12, (long) r6, (java.lang.Object) r8)     // Catch:{ ijg -> 0x05a6 }
            r11.m13765b((java.lang.Object) r12, (int) r4, (int) r5)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x005c:
            long r6 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            long r8 = r13.mo8582r()     // Catch:{ ijg -> 0x05a6 }
            java.lang.Long r8 = java.lang.Long.valueOf(r8)     // Catch:{ ijg -> 0x05a6 }
            p000.ilv.m14036a((java.lang.Object) r12, (long) r6, (java.lang.Object) r8)     // Catch:{ ijg -> 0x05a6 }
            r11.m13765b((java.lang.Object) r12, (int) r4, (int) r5)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x0070:
            long r6 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            int r8 = r13.mo8580q()     // Catch:{ ijg -> 0x05a6 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ ijg -> 0x05a6 }
            p000.ilv.m14036a((java.lang.Object) r12, (long) r6, (java.lang.Object) r8)     // Catch:{ ijg -> 0x05a6 }
            r11.m13765b((java.lang.Object) r12, (int) r4, (int) r5)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x0084:
            int r7 = r13.mo8578p()     // Catch:{ ijg -> 0x05a6 }
            ijb r8 = r11.m13770c((int) r5)     // Catch:{ ijg -> 0x05a6 }
            if (r8 == 0) goto L_0x009b
            boolean r8 = r8.mo2351a(r7)     // Catch:{ ijg -> 0x05a6 }
            if (r8 != 0) goto L_0x009b
            java.lang.Object r2 = p000.ila.m13911a((int) r4, (int) r7, (java.lang.Object) r2)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x0007
        L_0x009b:
            long r8 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r7)     // Catch:{ ijg -> 0x05a6 }
            p000.ilv.m14036a((java.lang.Object) r12, (long) r8, (java.lang.Object) r6)     // Catch:{ ijg -> 0x05a6 }
            r11.m13765b((java.lang.Object) r12, (int) r4, (int) r5)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x00ab:
            long r6 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            int r8 = r13.mo8576o()     // Catch:{ ijg -> 0x05a6 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ ijg -> 0x05a6 }
            p000.ilv.m14036a((java.lang.Object) r12, (long) r6, (java.lang.Object) r8)     // Catch:{ ijg -> 0x05a6 }
            r11.m13765b((java.lang.Object) r12, (int) r4, (int) r5)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x00bf:
            long r6 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            ihw r8 = r13.mo8574n()     // Catch:{ ijg -> 0x05a6 }
            p000.ilv.m14036a((java.lang.Object) r12, (long) r6, (java.lang.Object) r8)     // Catch:{ ijg -> 0x05a6 }
            r11.m13765b((java.lang.Object) r12, (int) r4, (int) r5)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x00cf:
            boolean r7 = r11.m13758a((java.lang.Object) r12, (int) r4, (int) r5)     // Catch:{ ijg -> 0x05a6 }
            if (r7 != 0) goto L_0x00e8
            long r6 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            iky r8 = r11.m13749a((int) r5)     // Catch:{ ijg -> 0x05a6 }
            java.lang.Object r8 = r13.mo8542a((p000.iky) r8, (p000.iij) r14)     // Catch:{ ijg -> 0x05a6 }
            p000.ilv.m14036a((java.lang.Object) r12, (long) r6, (java.lang.Object) r8)     // Catch:{ ijg -> 0x05a6 }
            r11.m13764b((java.lang.Object) r12, (int) r5)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x0103
        L_0x00e8:
            long r7 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            java.lang.Object r7 = p000.ilv.m14048f(r12, r7)     // Catch:{ ijg -> 0x05a6 }
            iky r8 = r11.m13749a((int) r5)     // Catch:{ ijg -> 0x05a6 }
            java.lang.Object r8 = r13.mo8542a((p000.iky) r8, (p000.iij) r14)     // Catch:{ ijg -> 0x05a6 }
            long r9 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            java.lang.Object r6 = p000.ijf.m13647a((java.lang.Object) r7, (java.lang.Object) r8)     // Catch:{ ijg -> 0x05a6 }
            p000.ilv.m14036a((java.lang.Object) r12, (long) r9, (java.lang.Object) r6)     // Catch:{ ijg -> 0x05a6 }
        L_0x0103:
            r11.m13765b((java.lang.Object) r12, (int) r4, (int) r5)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x0108:
            r11.m13755a((java.lang.Object) r12, (int) r6, (p000.iks) r13)     // Catch:{ ijg -> 0x05a6 }
            r11.m13765b((java.lang.Object) r12, (int) r4, (int) r5)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x0110:
            long r6 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            boolean r8 = r13.mo8569k()     // Catch:{ ijg -> 0x05a6 }
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r8)     // Catch:{ ijg -> 0x05a6 }
            p000.ilv.m14036a((java.lang.Object) r12, (long) r6, (java.lang.Object) r8)     // Catch:{ ijg -> 0x05a6 }
            r11.m13765b((java.lang.Object) r12, (int) r4, (int) r5)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x0124:
            long r6 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            int r8 = r13.mo8566j()     // Catch:{ ijg -> 0x05a6 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ ijg -> 0x05a6 }
            p000.ilv.m14036a((java.lang.Object) r12, (long) r6, (java.lang.Object) r8)     // Catch:{ ijg -> 0x05a6 }
            r11.m13765b((java.lang.Object) r12, (int) r4, (int) r5)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x0138:
            long r6 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            long r8 = r13.mo8564i()     // Catch:{ ijg -> 0x05a6 }
            java.lang.Long r8 = java.lang.Long.valueOf(r8)     // Catch:{ ijg -> 0x05a6 }
            p000.ilv.m14036a((java.lang.Object) r12, (long) r6, (java.lang.Object) r8)     // Catch:{ ijg -> 0x05a6 }
            r11.m13765b((java.lang.Object) r12, (int) r4, (int) r5)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x014c:
            long r6 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            int r8 = r13.mo8562h()     // Catch:{ ijg -> 0x05a6 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ ijg -> 0x05a6 }
            p000.ilv.m14036a((java.lang.Object) r12, (long) r6, (java.lang.Object) r8)     // Catch:{ ijg -> 0x05a6 }
            r11.m13765b((java.lang.Object) r12, (int) r4, (int) r5)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x0160:
            long r6 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            long r8 = r13.mo8558f()     // Catch:{ ijg -> 0x05a6 }
            java.lang.Long r8 = java.lang.Long.valueOf(r8)     // Catch:{ ijg -> 0x05a6 }
            p000.ilv.m14036a((java.lang.Object) r12, (long) r6, (java.lang.Object) r8)     // Catch:{ ijg -> 0x05a6 }
            r11.m13765b((java.lang.Object) r12, (int) r4, (int) r5)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x0174:
            long r6 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            long r8 = r13.mo8560g()     // Catch:{ ijg -> 0x05a6 }
            java.lang.Long r8 = java.lang.Long.valueOf(r8)     // Catch:{ ijg -> 0x05a6 }
            p000.ilv.m14036a((java.lang.Object) r12, (long) r6, (java.lang.Object) r8)     // Catch:{ ijg -> 0x05a6 }
            r11.m13765b((java.lang.Object) r12, (int) r4, (int) r5)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x0188:
            long r6 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            float r8 = r13.mo8556e()     // Catch:{ ijg -> 0x05a6 }
            java.lang.Float r8 = java.lang.Float.valueOf(r8)     // Catch:{ ijg -> 0x05a6 }
            p000.ilv.m14036a((java.lang.Object) r12, (long) r6, (java.lang.Object) r8)     // Catch:{ ijg -> 0x05a6 }
            r11.m13765b((java.lang.Object) r12, (int) r4, (int) r5)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x019c:
            long r6 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            double r8 = r13.mo8554d()     // Catch:{ ijg -> 0x05a6 }
            java.lang.Double r8 = java.lang.Double.valueOf(r8)     // Catch:{ ijg -> 0x05a6 }
            p000.ilv.m14036a((java.lang.Object) r12, (long) r6, (java.lang.Object) r8)     // Catch:{ ijg -> 0x05a6 }
            r11.m13765b((java.lang.Object) r12, (int) r4, (int) r5)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x01b0:
            java.lang.Object r4 = r11.m13763b((int) r5)     // Catch:{ ijg -> 0x05a6 }
            int r5 = r11.m13775e((int) r5)     // Catch:{ ijg -> 0x05a6 }
            long r5 = m13785i(r5)     // Catch:{ ijg -> 0x05a6 }
            java.lang.Object r7 = p000.ilv.m14048f(r12, r5)     // Catch:{ ijg -> 0x05a6 }
            if (r7 != 0) goto L_0x01cc
            ijz r7 = r11.f14390p     // Catch:{ ijg -> 0x05a6 }
            java.lang.Object r7 = r7.mo8852a()     // Catch:{ ijg -> 0x05a6 }
            p000.ilv.m14036a((java.lang.Object) r12, (long) r5, (java.lang.Object) r7)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x01e4
        L_0x01cc:
            ijz r8 = r11.f14390p     // Catch:{ ijg -> 0x05a6 }
            boolean r8 = r8.mo8856c(r7)     // Catch:{ ijg -> 0x05a6 }
            if (r8 == 0) goto L_0x01e4
            ijz r8 = r11.f14390p     // Catch:{ ijg -> 0x05a6 }
            java.lang.Object r8 = r8.mo8852a()     // Catch:{ ijg -> 0x05a6 }
            ijz r9 = r11.f14390p     // Catch:{ ijg -> 0x05a6 }
            r9.mo8853a(r8, r7)     // Catch:{ ijg -> 0x05a6 }
            p000.ilv.m14036a((java.lang.Object) r12, (long) r5, (java.lang.Object) r8)     // Catch:{ ijg -> 0x05a6 }
            r7 = r8
        L_0x01e4:
            ijz r5 = r11.f14390p     // Catch:{ ijg -> 0x05a6 }
            java.util.Map r5 = r5.mo8854a(r7)     // Catch:{ ijg -> 0x05a6 }
            ijz r6 = r11.f14390p     // Catch:{ ijg -> 0x05a6 }
            ijw r4 = r6.mo8858e(r4)     // Catch:{ ijg -> 0x05a6 }
            r13.mo8546a((java.util.Map) r5, (p000.ijw) r4, (p000.iij) r14)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x01f5:
            long r6 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            iky r4 = r11.m13749a((int) r5)     // Catch:{ ijg -> 0x05a6 }
            ijr r5 = r11.f14389o     // Catch:{ ijg -> 0x05a6 }
            java.util.List r5 = r5.mo8824a(r12, r6)     // Catch:{ ijg -> 0x05a6 }
            r13.mo8551b(r5, r4, r14)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x0208:
            ijr r4 = r11.f14389o     // Catch:{ ijg -> 0x05a6 }
            long r5 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            java.util.List r4 = r4.mo8824a(r12, r5)     // Catch:{ ijg -> 0x05a6 }
            r13.mo8581q(r4)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x0217:
            ijr r4 = r11.f14389o     // Catch:{ ijg -> 0x05a6 }
            long r5 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            java.util.List r4 = r4.mo8824a(r12, r5)     // Catch:{ ijg -> 0x05a6 }
            r13.mo8579p(r4)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x0226:
            ijr r4 = r11.f14389o     // Catch:{ ijg -> 0x05a6 }
            long r5 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            java.util.List r4 = r4.mo8824a(r12, r5)     // Catch:{ ijg -> 0x05a6 }
            r13.mo8577o(r4)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x0235:
            ijr r4 = r11.f14389o     // Catch:{ ijg -> 0x05a6 }
            long r5 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            java.util.List r4 = r4.mo8824a(r12, r5)     // Catch:{ ijg -> 0x05a6 }
            r13.mo8575n(r4)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x0244:
            ijr r7 = r11.f14389o     // Catch:{ ijg -> 0x05a6 }
            long r8 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            java.util.List r6 = r7.mo8824a(r12, r8)     // Catch:{ ijg -> 0x05a6 }
            r13.mo8573m(r6)     // Catch:{ ijg -> 0x05a6 }
            ijb r5 = r11.m13770c((int) r5)     // Catch:{ ijg -> 0x05a6 }
            java.lang.Object r2 = p000.ila.m13912a((int) r4, (java.util.List) r6, (p000.ijb) r5, (java.lang.Object) r2)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x0007
        L_0x025c:
            ijr r4 = r11.f14389o     // Catch:{ ijg -> 0x05a6 }
            long r5 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            java.util.List r4 = r4.mo8824a(r12, r5)     // Catch:{ ijg -> 0x05a6 }
            r13.mo8571l(r4)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x026b:
            ijr r4 = r11.f14389o     // Catch:{ ijg -> 0x05a6 }
            long r5 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            java.util.List r4 = r4.mo8824a(r12, r5)     // Catch:{ ijg -> 0x05a6 }
            r13.mo8563h(r4)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x027a:
            ijr r4 = r11.f14389o     // Catch:{ ijg -> 0x05a6 }
            long r5 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            java.util.List r4 = r4.mo8824a(r12, r5)     // Catch:{ ijg -> 0x05a6 }
            r13.mo8561g(r4)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x0289:
            ijr r4 = r11.f14389o     // Catch:{ ijg -> 0x05a6 }
            long r5 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            java.util.List r4 = r4.mo8824a(r12, r5)     // Catch:{ ijg -> 0x05a6 }
            r13.mo8559f(r4)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x0298:
            ijr r4 = r11.f14389o     // Catch:{ ijg -> 0x05a6 }
            long r5 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            java.util.List r4 = r4.mo8824a(r12, r5)     // Catch:{ ijg -> 0x05a6 }
            r13.mo8557e(r4)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x02a7:
            ijr r4 = r11.f14389o     // Catch:{ ijg -> 0x05a6 }
            long r5 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            java.util.List r4 = r4.mo8824a(r12, r5)     // Catch:{ ijg -> 0x05a6 }
            r13.mo8552c(r4)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x02b6:
            ijr r4 = r11.f14389o     // Catch:{ ijg -> 0x05a6 }
            long r5 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            java.util.List r4 = r4.mo8824a(r12, r5)     // Catch:{ ijg -> 0x05a6 }
            r13.mo8555d(r4)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x02c5:
            ijr r4 = r11.f14389o     // Catch:{ ijg -> 0x05a6 }
            long r5 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            java.util.List r4 = r4.mo8824a(r12, r5)     // Catch:{ ijg -> 0x05a6 }
            r13.mo8550b(r4)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x02d4:
            ijr r4 = r11.f14389o     // Catch:{ ijg -> 0x05a6 }
            long r5 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            java.util.List r4 = r4.mo8824a(r12, r5)     // Catch:{ ijg -> 0x05a6 }
            r13.mo8544a(r4)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x02e3:
            ijr r4 = r11.f14389o     // Catch:{ ijg -> 0x05a6 }
            long r5 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            java.util.List r4 = r4.mo8824a(r12, r5)     // Catch:{ ijg -> 0x05a6 }
            r13.mo8581q(r4)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x02f2:
            ijr r4 = r11.f14389o     // Catch:{ ijg -> 0x05a6 }
            long r5 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            java.util.List r4 = r4.mo8824a(r12, r5)     // Catch:{ ijg -> 0x05a6 }
            r13.mo8579p(r4)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x0301:
            ijr r4 = r11.f14389o     // Catch:{ ijg -> 0x05a6 }
            long r5 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            java.util.List r4 = r4.mo8824a(r12, r5)     // Catch:{ ijg -> 0x05a6 }
            r13.mo8577o(r4)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x0310:
            ijr r4 = r11.f14389o     // Catch:{ ijg -> 0x05a6 }
            long r5 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            java.util.List r4 = r4.mo8824a(r12, r5)     // Catch:{ ijg -> 0x05a6 }
            r13.mo8575n(r4)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x031f:
            ijr r7 = r11.f14389o     // Catch:{ ijg -> 0x05a6 }
            long r8 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            java.util.List r6 = r7.mo8824a(r12, r8)     // Catch:{ ijg -> 0x05a6 }
            r13.mo8573m(r6)     // Catch:{ ijg -> 0x05a6 }
            ijb r5 = r11.m13770c((int) r5)     // Catch:{ ijg -> 0x05a6 }
            java.lang.Object r2 = p000.ila.m13912a((int) r4, (java.util.List) r6, (p000.ijb) r5, (java.lang.Object) r2)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x0007
        L_0x0337:
            ijr r4 = r11.f14389o     // Catch:{ ijg -> 0x05a6 }
            long r5 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            java.util.List r4 = r4.mo8824a(r12, r5)     // Catch:{ ijg -> 0x05a6 }
            r13.mo8571l(r4)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x0346:
            ijr r4 = r11.f14389o     // Catch:{ ijg -> 0x05a6 }
            long r5 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            java.util.List r4 = r4.mo8824a(r12, r5)     // Catch:{ ijg -> 0x05a6 }
            r13.mo8568k(r4)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x0355:
            iky r4 = r11.m13749a((int) r5)     // Catch:{ ijg -> 0x05a6 }
            ijr r5 = r11.f14389o     // Catch:{ ijg -> 0x05a6 }
            long r6 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            java.util.List r5 = r5.mo8824a(r12, r6)     // Catch:{ ijg -> 0x05a6 }
            r13.mo8545a((java.util.List) r5, (p000.iky) r4, (p000.iij) r14)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x0368:
            boolean r4 = m13784h((int) r6)     // Catch:{ ijg -> 0x05a6 }
            if (r4 != 0) goto L_0x037d
            ijr r4 = r11.f14389o     // Catch:{ ijg -> 0x05a6 }
            long r5 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            java.util.List r4 = r4.mo8824a(r12, r5)     // Catch:{ ijg -> 0x05a6 }
            r13.mo8565i(r4)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x037d:
            ijr r4 = r11.f14389o     // Catch:{ ijg -> 0x05a6 }
            long r5 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            java.util.List r4 = r4.mo8824a(r12, r5)     // Catch:{ ijg -> 0x05a6 }
            r13.mo8567j(r4)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x038c:
            ijr r4 = r11.f14389o     // Catch:{ ijg -> 0x05a6 }
            long r5 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            java.util.List r4 = r4.mo8824a(r12, r5)     // Catch:{ ijg -> 0x05a6 }
            r13.mo8563h(r4)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x039b:
            ijr r4 = r11.f14389o     // Catch:{ ijg -> 0x05a6 }
            long r5 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            java.util.List r4 = r4.mo8824a(r12, r5)     // Catch:{ ijg -> 0x05a6 }
            r13.mo8561g(r4)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x03aa:
            ijr r4 = r11.f14389o     // Catch:{ ijg -> 0x05a6 }
            long r5 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            java.util.List r4 = r4.mo8824a(r12, r5)     // Catch:{ ijg -> 0x05a6 }
            r13.mo8559f(r4)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x03b9:
            ijr r4 = r11.f14389o     // Catch:{ ijg -> 0x05a6 }
            long r5 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            java.util.List r4 = r4.mo8824a(r12, r5)     // Catch:{ ijg -> 0x05a6 }
            r13.mo8557e(r4)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x03c8:
            ijr r4 = r11.f14389o     // Catch:{ ijg -> 0x05a6 }
            long r5 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            java.util.List r4 = r4.mo8824a(r12, r5)     // Catch:{ ijg -> 0x05a6 }
            r13.mo8552c(r4)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x03d7:
            ijr r4 = r11.f14389o     // Catch:{ ijg -> 0x05a6 }
            long r5 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            java.util.List r4 = r4.mo8824a(r12, r5)     // Catch:{ ijg -> 0x05a6 }
            r13.mo8555d(r4)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x03e6:
            ijr r4 = r11.f14389o     // Catch:{ ijg -> 0x05a6 }
            long r5 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            java.util.List r4 = r4.mo8824a(r12, r5)     // Catch:{ ijg -> 0x05a6 }
            r13.mo8550b(r4)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x03f5:
            ijr r4 = r11.f14389o     // Catch:{ ijg -> 0x05a6 }
            long r5 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            java.util.List r4 = r4.mo8824a(r12, r5)     // Catch:{ ijg -> 0x05a6 }
            r13.mo8544a(r4)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x0404:
            boolean r4 = r11.m13757a((java.lang.Object) r12, (int) r5)     // Catch:{ ijg -> 0x05a6 }
            if (r4 != 0) goto L_0x041e
            long r6 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            iky r4 = r11.m13749a((int) r5)     // Catch:{ ijg -> 0x05a6 }
            java.lang.Object r4 = r13.mo8548b((p000.iky) r4, (p000.iij) r14)     // Catch:{ ijg -> 0x05a6 }
            p000.ilv.m14036a((java.lang.Object) r12, (long) r6, (java.lang.Object) r4)     // Catch:{ ijg -> 0x05a6 }
            r11.m13764b((java.lang.Object) r12, (int) r5)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x041e:
            long r7 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            java.lang.Object r4 = p000.ilv.m14048f(r12, r7)     // Catch:{ ijg -> 0x05a6 }
            iky r5 = r11.m13749a((int) r5)     // Catch:{ ijg -> 0x05a6 }
            java.lang.Object r5 = r13.mo8548b((p000.iky) r5, (p000.iij) r14)     // Catch:{ ijg -> 0x05a6 }
            long r6 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            java.lang.Object r4 = p000.ijf.m13647a((java.lang.Object) r4, (java.lang.Object) r5)     // Catch:{ ijg -> 0x05a6 }
            p000.ilv.m14036a((java.lang.Object) r12, (long) r6, (java.lang.Object) r4)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x043b:
            long r6 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            long r8 = r13.mo8584t()     // Catch:{ ijg -> 0x05a6 }
            p000.ilv.m14035a((java.lang.Object) r12, (long) r6, (long) r8)     // Catch:{ ijg -> 0x05a6 }
            r11.m13764b((java.lang.Object) r12, (int) r5)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x044b:
            long r6 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            int r4 = r13.mo8583s()     // Catch:{ ijg -> 0x05a6 }
            p000.ilv.m14034a((java.lang.Object) r12, (long) r6, (int) r4)     // Catch:{ ijg -> 0x05a6 }
            r11.m13764b((java.lang.Object) r12, (int) r5)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x045b:
            long r6 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            long r8 = r13.mo8582r()     // Catch:{ ijg -> 0x05a6 }
            p000.ilv.m14035a((java.lang.Object) r12, (long) r6, (long) r8)     // Catch:{ ijg -> 0x05a6 }
            r11.m13764b((java.lang.Object) r12, (int) r5)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x046b:
            long r6 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            int r4 = r13.mo8580q()     // Catch:{ ijg -> 0x05a6 }
            p000.ilv.m14034a((java.lang.Object) r12, (long) r6, (int) r4)     // Catch:{ ijg -> 0x05a6 }
            r11.m13764b((java.lang.Object) r12, (int) r5)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x047b:
            int r7 = r13.mo8578p()     // Catch:{ ijg -> 0x05a6 }
            ijb r8 = r11.m13770c((int) r5)     // Catch:{ ijg -> 0x05a6 }
            if (r8 == 0) goto L_0x0492
            boolean r8 = r8.mo2351a(r7)     // Catch:{ ijg -> 0x05a6 }
            if (r8 != 0) goto L_0x0492
            java.lang.Object r2 = p000.ila.m13911a((int) r4, (int) r7, (java.lang.Object) r2)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x0007
        L_0x0492:
            long r8 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            p000.ilv.m14034a((java.lang.Object) r12, (long) r8, (int) r7)     // Catch:{ ijg -> 0x05a6 }
            r11.m13764b((java.lang.Object) r12, (int) r5)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x049e:
            long r6 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            int r4 = r13.mo8576o()     // Catch:{ ijg -> 0x05a6 }
            p000.ilv.m14034a((java.lang.Object) r12, (long) r6, (int) r4)     // Catch:{ ijg -> 0x05a6 }
            r11.m13764b((java.lang.Object) r12, (int) r5)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x04ae:
            long r6 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            ihw r4 = r13.mo8574n()     // Catch:{ ijg -> 0x05a6 }
            p000.ilv.m14036a((java.lang.Object) r12, (long) r6, (java.lang.Object) r4)     // Catch:{ ijg -> 0x05a6 }
            r11.m13764b((java.lang.Object) r12, (int) r5)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x04be:
            boolean r4 = r11.m13757a((java.lang.Object) r12, (int) r5)     // Catch:{ ijg -> 0x05a6 }
            if (r4 != 0) goto L_0x04d8
            long r6 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            iky r4 = r11.m13749a((int) r5)     // Catch:{ ijg -> 0x05a6 }
            java.lang.Object r4 = r13.mo8542a((p000.iky) r4, (p000.iij) r14)     // Catch:{ ijg -> 0x05a6 }
            p000.ilv.m14036a((java.lang.Object) r12, (long) r6, (java.lang.Object) r4)     // Catch:{ ijg -> 0x05a6 }
            r11.m13764b((java.lang.Object) r12, (int) r5)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x04d8:
            long r7 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            java.lang.Object r4 = p000.ilv.m14048f(r12, r7)     // Catch:{ ijg -> 0x05a6 }
            iky r5 = r11.m13749a((int) r5)     // Catch:{ ijg -> 0x05a6 }
            java.lang.Object r5 = r13.mo8542a((p000.iky) r5, (p000.iij) r14)     // Catch:{ ijg -> 0x05a6 }
            long r6 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            java.lang.Object r4 = p000.ijf.m13647a((java.lang.Object) r4, (java.lang.Object) r5)     // Catch:{ ijg -> 0x05a6 }
            p000.ilv.m14036a((java.lang.Object) r12, (long) r6, (java.lang.Object) r4)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x04f5:
            r11.m13755a((java.lang.Object) r12, (int) r6, (p000.iks) r13)     // Catch:{ ijg -> 0x05a6 }
            r11.m13764b((java.lang.Object) r12, (int) r5)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x04fd:
            long r6 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            boolean r4 = r13.mo8569k()     // Catch:{ ijg -> 0x05a6 }
            p000.ilv.m14037a((java.lang.Object) r12, (long) r6, (boolean) r4)     // Catch:{ ijg -> 0x05a6 }
            r11.m13764b((java.lang.Object) r12, (int) r5)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x050d:
            long r6 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            int r4 = r13.mo8566j()     // Catch:{ ijg -> 0x05a6 }
            p000.ilv.m14034a((java.lang.Object) r12, (long) r6, (int) r4)     // Catch:{ ijg -> 0x05a6 }
            r11.m13764b((java.lang.Object) r12, (int) r5)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x051d:
            long r6 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            long r8 = r13.mo8564i()     // Catch:{ ijg -> 0x05a6 }
            p000.ilv.m14035a((java.lang.Object) r12, (long) r6, (long) r8)     // Catch:{ ijg -> 0x05a6 }
            r11.m13764b((java.lang.Object) r12, (int) r5)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x052d:
            long r6 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            int r4 = r13.mo8562h()     // Catch:{ ijg -> 0x05a6 }
            p000.ilv.m14034a((java.lang.Object) r12, (long) r6, (int) r4)     // Catch:{ ijg -> 0x05a6 }
            r11.m13764b((java.lang.Object) r12, (int) r5)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x053d:
            long r6 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            long r8 = r13.mo8558f()     // Catch:{ ijg -> 0x05a6 }
            p000.ilv.m14035a((java.lang.Object) r12, (long) r6, (long) r8)     // Catch:{ ijg -> 0x05a6 }
            r11.m13764b((java.lang.Object) r12, (int) r5)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x054d:
            long r6 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            long r8 = r13.mo8560g()     // Catch:{ ijg -> 0x05a6 }
            p000.ilv.m14035a((java.lang.Object) r12, (long) r6, (long) r8)     // Catch:{ ijg -> 0x05a6 }
            r11.m13764b((java.lang.Object) r12, (int) r5)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x055d:
            long r6 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            float r4 = r13.mo8556e()     // Catch:{ ijg -> 0x05a6 }
            p000.ilv.m14033a((java.lang.Object) r12, (long) r6, (float) r4)     // Catch:{ ijg -> 0x05a6 }
            r11.m13764b((java.lang.Object) r12, (int) r5)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x056d:
            long r6 = m13785i(r6)     // Catch:{ ijg -> 0x05a6 }
            double r8 = r13.mo8554d()     // Catch:{ ijg -> 0x05a6 }
            p000.ilv.m14032a((java.lang.Object) r12, (long) r6, (double) r8)     // Catch:{ ijg -> 0x05a6 }
            r11.m13764b((java.lang.Object) r12, (int) r5)     // Catch:{ ijg -> 0x05a6 }
            goto L_0x06fc
        L_0x057d:
            ilm r2 = p000.ilm.m13974a()     // Catch:{ ijg -> 0x05a6 }
        L_0x0583:
            boolean r4 = r1.mo9005a((java.lang.Object) r2, (p000.iks) r13)     // Catch:{ ijg -> 0x05a4 }
            if (r4 != 0) goto L_0x0007
            int r13 = r11.f14386l
        L_0x058b:
            int r14 = r11.f14387m
            if (r13 >= r14) goto L_0x059a
            int[] r14 = r11.f14385k
            r14 = r14[r13]
            java.lang.Object r2 = r11.m13750a((java.lang.Object) r12, (int) r14, (java.lang.Object) r2)
            int r13 = r13 + 1
            goto L_0x058b
        L_0x059a:
            if (r2 == 0) goto L_0x061a
        L_0x059c:
            p000.imu.m14139a((java.lang.Object) r12, (p000.ilm) r2)
            return
        L_0x05a0:
            r13 = move-exception
            goto L_0x0703
        L_0x05a4:
            r4 = move-exception
            goto L_0x05a7
        L_0x05a6:
            r4 = move-exception
        L_0x05a7:
            if (r2 != 0) goto L_0x05b0
            java.lang.Object r2 = p000.imu.m14141b(r12)     // Catch:{ all -> 0x05ae }
            goto L_0x05b0
        L_0x05ae:
            r13 = move-exception
            goto L_0x05cc
        L_0x05b0:
            boolean r4 = r1.mo9005a((java.lang.Object) r2, (p000.iks) r13)     // Catch:{ all -> 0x05cb }
            if (r4 != 0) goto L_0x0007
            int r13 = r11.f14386l
        L_0x05b9:
            int r14 = r11.f14387m
            if (r13 >= r14) goto L_0x05c8
            int[] r14 = r11.f14385k
            r14 = r14[r13]
            java.lang.Object r2 = r11.m13750a((java.lang.Object) r12, (int) r14, (java.lang.Object) r2)
            int r13 = r13 + 1
            goto L_0x05b9
        L_0x05c8:
            if (r2 == 0) goto L_0x061a
            goto L_0x059c
        L_0x05cb:
            r13 = move-exception
        L_0x05cc:
            goto L_0x0703
        L_0x05cf:
            r5 = 2147483647(0x7fffffff, float:NaN)
            if (r4 != r5) goto L_0x05e8
            int r13 = r11.f14386l
        L_0x05d6:
            int r14 = r11.f14387m
            if (r13 >= r14) goto L_0x05e5
            int[] r14 = r11.f14385k
            r14 = r14[r13]
            java.lang.Object r2 = r11.m13750a((java.lang.Object) r12, (int) r14, (java.lang.Object) r2)
            int r13 = r13 + 1
            goto L_0x05d6
        L_0x05e5:
            if (r2 == 0) goto L_0x061a
            goto L_0x059c
        L_0x05e8:
            boolean r5 = r11.f14382h     // Catch:{ all -> 0x0702 }
            if (r5 == 0) goto L_0x05f3
            ikf r5 = r11.f14381g     // Catch:{ all -> 0x0702 }
            iih r4 = r14.mo8715a(r5, r4)     // Catch:{ all -> 0x0702 }
            goto L_0x05f5
        L_0x05f3:
            r4 = r0
        L_0x05f5:
            if (r4 != 0) goto L_0x061b
            if (r2 == 0) goto L_0x05fa
            goto L_0x0600
        L_0x05fa:
            java.lang.Object r2 = p000.imu.m14141b(r12)     // Catch:{ all -> 0x0702 }
        L_0x0600:
            boolean r4 = r1.mo9005a((java.lang.Object) r2, (p000.iks) r13)     // Catch:{ all -> 0x05a0 }
            if (r4 != 0) goto L_0x0007
            int r13 = r11.f14386l
        L_0x0608:
            int r14 = r11.f14387m
            if (r13 >= r14) goto L_0x0617
            int[] r14 = r11.f14385k
            r14 = r14[r13]
            java.lang.Object r2 = r11.m13750a((java.lang.Object) r12, (int) r14, (java.lang.Object) r2)
            int r13 = r13 + 1
            goto L_0x0608
        L_0x0617:
            if (r2 == 0) goto L_0x061a
            goto L_0x059c
        L_0x061a:
            return
        L_0x061b:
            if (r3 == 0) goto L_0x061e
            goto L_0x0622
        L_0x061e:
            iim r3 = p000.imi.m14114b((java.lang.Object) r12)     // Catch:{ all -> 0x0702 }
        L_0x0622:
            imb r5 = r4.mo8712b()     // Catch:{ all -> 0x0702 }
            imb r6 = p000.imb.ENUM     // Catch:{ all -> 0x0702 }
            if (r5 == r6) goto L_0x06fe
            imb r5 = r4.mo8712b()     // Catch:{ all -> 0x0702 }
            int r5 = r5.ordinal()     // Catch:{ all -> 0x0702 }
            switch(r5) {
                case 0: goto L_0x06d0;
                case 1: goto L_0x06c7;
                case 2: goto L_0x06be;
                case 3: goto L_0x06b5;
                case 4: goto L_0x06ac;
                case 5: goto L_0x06a3;
                case 6: goto L_0x069a;
                case 7: goto L_0x0691;
                case 8: goto L_0x068c;
                case 9: goto L_0x0681;
                case 10: goto L_0x0676;
                case 11: goto L_0x0671;
                case 12: goto L_0x0667;
                case 13: goto L_0x065f;
                case 14: goto L_0x0655;
                case 15: goto L_0x064b;
                case 16: goto L_0x0641;
                case 17: goto L_0x0637;
                default: goto L_0x0635;
            }     // Catch:{ all -> 0x0702 }
        L_0x0635:
            goto L_0x06d9
        L_0x0637:
            long r5 = r13.mo8584t()     // Catch:{ all -> 0x0702 }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0702 }
            goto L_0x06da
        L_0x0641:
            int r5 = r13.mo8583s()     // Catch:{ all -> 0x0702 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0702 }
            goto L_0x06da
        L_0x064b:
            long r5 = r13.mo8582r()     // Catch:{ all -> 0x0702 }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0702 }
            goto L_0x06da
        L_0x0655:
            int r5 = r13.mo8580q()     // Catch:{ all -> 0x0702 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0702 }
            goto L_0x06da
        L_0x065f:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0702 }
            java.lang.String r14 = "Shouldn't reach here."
            r13.<init>(r14)     // Catch:{ all -> 0x0702 }
            throw r13     // Catch:{ all -> 0x0702 }
        L_0x0667:
            int r5 = r13.mo8576o()     // Catch:{ all -> 0x0702 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0702 }
            goto L_0x06da
        L_0x0671:
            ihw r5 = r13.mo8574n()     // Catch:{ all -> 0x0702 }
            goto L_0x06da
        L_0x0676:
            ikf r5 = r4.f14243c     // Catch:{ all -> 0x0702 }
            java.lang.Class r5 = r5.getClass()     // Catch:{ all -> 0x0702 }
            java.lang.Object r5 = r13.mo8543a((java.lang.Class) r5, (p000.iij) r14)     // Catch:{ all -> 0x0702 }
            goto L_0x06da
        L_0x0681:
            ikf r5 = r4.f14243c     // Catch:{ all -> 0x0702 }
            java.lang.Class r5 = r5.getClass()     // Catch:{ all -> 0x0702 }
            java.lang.Object r5 = r13.mo8549b((java.lang.Class) r5, (p000.iij) r14)     // Catch:{ all -> 0x0702 }
            goto L_0x06da
        L_0x068c:
            java.lang.String r5 = r13.mo8570l()     // Catch:{ all -> 0x0702 }
            goto L_0x06da
        L_0x0691:
            boolean r5 = r13.mo8569k()     // Catch:{ all -> 0x0702 }
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)     // Catch:{ all -> 0x0702 }
            goto L_0x06da
        L_0x069a:
            int r5 = r13.mo8566j()     // Catch:{ all -> 0x0702 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0702 }
            goto L_0x06da
        L_0x06a3:
            long r5 = r13.mo8564i()     // Catch:{ all -> 0x0702 }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0702 }
            goto L_0x06da
        L_0x06ac:
            int r5 = r13.mo8562h()     // Catch:{ all -> 0x0702 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0702 }
            goto L_0x06da
        L_0x06b5:
            long r5 = r13.mo8558f()     // Catch:{ all -> 0x0702 }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0702 }
            goto L_0x06da
        L_0x06be:
            long r5 = r13.mo8560g()     // Catch:{ all -> 0x0702 }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0702 }
            goto L_0x06da
        L_0x06c7:
            float r5 = r13.mo8556e()     // Catch:{ all -> 0x0702 }
            java.lang.Float r5 = java.lang.Float.valueOf(r5)     // Catch:{ all -> 0x0702 }
            goto L_0x06da
        L_0x06d0:
            double r5 = r13.mo8554d()     // Catch:{ all -> 0x0702 }
            java.lang.Double r5 = java.lang.Double.valueOf(r5)     // Catch:{ all -> 0x0702 }
            goto L_0x06da
        L_0x06d9:
            r5 = r0
        L_0x06da:
            imb r6 = r4.mo8712b()     // Catch:{ all -> 0x0702 }
            int r6 = r6.ordinal()     // Catch:{ all -> 0x0702 }
            r7 = 9
            if (r6 == r7) goto L_0x06eb
            r7 = 10
            if (r6 == r7) goto L_0x06eb
            goto L_0x06f7
        L_0x06eb:
            iiw r6 = r4.f14244d     // Catch:{ all -> 0x0702 }
            java.lang.Object r6 = r3.mo8728b((p000.iil) r6)     // Catch:{ all -> 0x0702 }
            if (r6 == 0) goto L_0x06f7
            java.lang.Object r5 = p000.ijf.m13647a((java.lang.Object) r6, (java.lang.Object) r5)     // Catch:{ all -> 0x0702 }
        L_0x06f7:
            iiw r4 = r4.f14244d     // Catch:{ all -> 0x0702 }
            r3.mo8723a(r4, r5)     // Catch:{ all -> 0x0702 }
        L_0x06fc:
            goto L_0x0007
        L_0x06fe:
            r13.mo8562h()     // Catch:{ all -> 0x0702 }
            throw r0     // Catch:{ all -> 0x0702 }
        L_0x0702:
            r13 = move-exception
        L_0x0703:
            int r14 = r11.f14386l
        L_0x0705:
            int r0 = r11.f14387m
            if (r14 >= r0) goto L_0x0714
            int[] r0 = r11.f14385k
            r0 = r0[r14]
            java.lang.Object r2 = r11.m13750a((java.lang.Object) r12, (int) r0, (java.lang.Object) r2)
            int r14 = r14 + 1
            goto L_0x0705
        L_0x0714:
            if (r2 != 0) goto L_0x0717
            goto L_0x071a
        L_0x0717:
            p000.imu.m14139a((java.lang.Object) r12, (p000.ilm) r2)
        L_0x071a:
            throw r13
        L_0x071b:
            goto L_0x071e
        L_0x071d:
            throw r0
        L_0x071e:
            goto L_0x071d
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.ikh.mo8865a(java.lang.Object, iks, iij):void");
    }

    /* renamed from: b */
    public final void mo8870b(Object obj, Object obj2) {
        if (obj2 != null) {
            for (int i = 0; i < this.f14377c.length; i += 3) {
                int e = m13775e(i);
                long i2 = m13785i(e);
                int d = m13773d(i);
                switch (m13781g(e)) {
                    case 0:
                        if (!m13757a(obj2, i)) {
                            break;
                        } else {
                            ilv.m14032a(obj, i2, ilv.m14047e(obj2, i2));
                            m13764b(obj, i);
                            break;
                        }
                    case 1:
                        if (!m13757a(obj2, i)) {
                            break;
                        } else {
                            ilv.m14033a(obj, i2, ilv.m14045d(obj2, i2));
                            m13764b(obj, i);
                            break;
                        }
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        if (!m13757a(obj2, i)) {
                            break;
                        } else {
                            ilv.m14035a(obj, i2, ilv.m14040b(obj2, i2));
                            m13764b(obj, i);
                            break;
                        }
                    case 3:
                        if (!m13757a(obj2, i)) {
                            break;
                        } else {
                            ilv.m14035a(obj, i2, ilv.m14040b(obj2, i2));
                            m13764b(obj, i);
                            break;
                        }
                    case 4:
                        if (!m13757a(obj2, i)) {
                            break;
                        } else {
                            ilv.m14034a(obj, i2, ilv.m14027a(obj2, i2));
                            m13764b(obj, i);
                            break;
                        }
                    case 5:
                        if (!m13757a(obj2, i)) {
                            break;
                        } else {
                            ilv.m14035a(obj, i2, ilv.m14040b(obj2, i2));
                            m13764b(obj, i);
                            break;
                        }
                    case 6:
                        if (!m13757a(obj2, i)) {
                            break;
                        } else {
                            ilv.m14034a(obj, i2, ilv.m14027a(obj2, i2));
                            m13764b(obj, i);
                            break;
                        }
                    case 7:
                        if (!m13757a(obj2, i)) {
                            break;
                        } else {
                            ilv.m14037a(obj, i2, ilv.m14044c(obj2, i2));
                            m13764b(obj, i);
                            break;
                        }
                    case 8:
                        if (!m13757a(obj2, i)) {
                            break;
                        } else {
                            ilv.m14036a(obj, i2, ilv.m14048f(obj2, i2));
                            m13764b(obj, i);
                            break;
                        }
                    case 9:
                        m13756a(obj, obj2, i);
                        break;
                    case 10:
                        if (!m13757a(obj2, i)) {
                            break;
                        } else {
                            ilv.m14036a(obj, i2, ilv.m14048f(obj2, i2));
                            m13764b(obj, i);
                            break;
                        }
                    case 11:
                        if (!m13757a(obj2, i)) {
                            break;
                        } else {
                            ilv.m14034a(obj, i2, ilv.m14027a(obj2, i2));
                            m13764b(obj, i);
                            break;
                        }
                    case 12:
                        if (!m13757a(obj2, i)) {
                            break;
                        } else {
                            ilv.m14034a(obj, i2, ilv.m14027a(obj2, i2));
                            m13764b(obj, i);
                            break;
                        }
                    case 13:
                        if (!m13757a(obj2, i)) {
                            break;
                        } else {
                            ilv.m14034a(obj, i2, ilv.m14027a(obj2, i2));
                            m13764b(obj, i);
                            break;
                        }
                    case 14:
                        if (!m13757a(obj2, i)) {
                            break;
                        } else {
                            ilv.m14035a(obj, i2, ilv.m14040b(obj2, i2));
                            m13764b(obj, i);
                            break;
                        }
                    case 15:
                        if (!m13757a(obj2, i)) {
                            break;
                        } else {
                            ilv.m14034a(obj, i2, ilv.m14027a(obj2, i2));
                            m13764b(obj, i);
                            break;
                        }
                    case 16:
                        if (!m13757a(obj2, i)) {
                            break;
                        } else {
                            ilv.m14035a(obj, i2, ilv.m14040b(obj2, i2));
                            m13764b(obj, i);
                            break;
                        }
                    case 17:
                        m13756a(obj, obj2, i);
                        break;
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                    case 33:
                    case 34:
                    case 35:
                    case 36:
                    case 37:
                    case 38:
                    case 39:
                    case 40:
                    case 41:
                    case 42:
                    case 43:
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                    case 48:
                    case 49:
                        this.f14389o.mo8825a(obj, obj2, i2);
                        break;
                    case 50:
                        ila.m13916a(this.f14390p, obj, obj2, i2);
                        break;
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                    case 58:
                    case 59:
                        if (!m13758a(obj2, d, i)) {
                            break;
                        } else {
                            ilv.m14036a(obj, i2, ilv.m14048f(obj2, i2));
                            m13765b(obj, d, i);
                            break;
                        }
                    case 60:
                        m13767b(obj, obj2, i);
                        break;
                    case 61:
                    case 62:
                    case 63:
                    case 64:
                    case 65:
                    case 66:
                    case 67:
                        if (!m13758a(obj2, d, i)) {
                            break;
                        } else {
                            ilv.m14036a(obj, i2, ilv.m14048f(obj2, i2));
                            m13765b(obj, d, i);
                            break;
                        }
                    case 68:
                        m13767b(obj, obj2, i);
                        break;
                }
            }
            ila.m13929c(obj, obj2);
            if (this.f14382h) {
                ila.m13925b(obj, obj2);
                return;
            }
            return;
        }
        throw null;
    }

    /* renamed from: a */
    public final void mo8867a(Object obj, byte[] bArr, int i, int i2, ihf ihf) {
        if (!this.f14384j) {
            mo8863a(obj, bArr, i, i2, 0, ihf);
        } else {
            m13768b(obj, bArr, i, i2, ihf);
        }
    }

    /* renamed from: a */
    private final void m13756a(Object obj, Object obj2, int i) {
        long i2 = m13785i(m13775e(i));
        if (m13757a(obj2, i)) {
            Object f = ilv.m14048f(obj, i2);
            Object f2 = ilv.m14048f(obj2, i2);
            if (f != null && f2 != null) {
                ilv.m14036a(obj, i2, ijf.m13647a(f, f2));
                m13764b(obj, i);
            } else if (f2 != null) {
                ilv.m14036a(obj, i2, f2);
                m13764b(obj, i);
            }
        }
    }

    /* renamed from: b */
    private final void m13767b(Object obj, Object obj2, int i) {
        int e = m13775e(i);
        int d = m13773d(i);
        long i2 = m13785i(e);
        if (m13758a(obj2, d, i)) {
            Object f = ilv.m14048f(obj, i2);
            Object f2 = ilv.m14048f(obj2, i2);
            if (f != null && f2 != null) {
                ilv.m14036a(obj, i2, ijf.m13647a(f, f2));
                m13765b(obj, d, i);
            } else if (f2 != null) {
                ilv.m14036a(obj, i2, f2);
                m13765b(obj, d, i);
            }
        }
    }

    /* renamed from: a */
    public final Object mo8864a() {
        return this.f14388n.mo8874a(this.f14381g);
    }

    /* renamed from: a */
    static ikh m13747a(ikc ikc, ikk ikk, ijr ijr, imu imu, imi imi, ijz ijz) {
        if (ikc instanceof ikr) {
            return m13748a((ikr) ikc, ikk, ijr, imu, imi, ijz);
        }
        ili ili = (ili) ikc;
        throw null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v1, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v2, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v0, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v1, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v3, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v8, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v10, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v26, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v3, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v13, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v15, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v26, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v27, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v4, resolved type: char} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x02ea  */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x02f2  */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x035a  */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x0365  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static p000.ikh m13748a(p000.ikr r36, p000.ikk r37, p000.ijr r38, p000.imu r39, p000.imi r40, p000.ijz r41) {
        /*
            r0 = r36
            int r1 = r36.mo8861c()
            r2 = 0
            r4 = 2
            if (r1 != r4) goto L_0x000c
            r11 = 1
            goto L_0x000e
        L_0x000c:
            r11 = 0
        L_0x000e:
            java.lang.String r1 = r0.f14404b
            int r4 = r1.length()
            char r5 = r1.charAt(r2)
            r7 = 55296(0xd800, float:7.7486E-41)
            if (r5 < r7) goto L_0x0036
            r5 = r5 & 8191(0x1fff, float:1.1478E-41)
            r8 = 1
            r9 = 13
        L_0x0022:
            int r10 = r8 + 1
            char r8 = r1.charAt(r8)
            if (r8 < r7) goto L_0x0033
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r9
            r5 = r5 | r8
            int r9 = r9 + 13
            r8 = r10
            goto L_0x0022
        L_0x0033:
            int r8 = r8 << r9
            r5 = r5 | r8
            goto L_0x0038
        L_0x0036:
            r10 = 1
        L_0x0038:
            int r8 = r10 + 1
            char r9 = r1.charAt(r10)
            if (r9 < r7) goto L_0x0059
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            r10 = 13
        L_0x0044:
            int r12 = r8 + 1
            char r8 = r1.charAt(r8)
            if (r8 < r7) goto L_0x0055
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r10
            r9 = r9 | r8
            int r10 = r10 + 13
            r8 = r12
            goto L_0x0044
        L_0x0055:
            int r8 = r8 << r10
            r9 = r9 | r8
            r8 = r12
            goto L_0x005a
        L_0x0059:
        L_0x005a:
            if (r9 != 0) goto L_0x0067
            int[] r9 = f14375a
            r13 = r9
            r6 = 0
            r9 = 0
            r10 = 0
            r12 = 0
            r14 = 0
            r15 = 0
            goto L_0x019f
        L_0x0067:
            int r9 = r8 + 1
            char r8 = r1.charAt(r8)
            if (r8 < r7) goto L_0x0089
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            r10 = 13
        L_0x0074:
            int r12 = r9 + 1
            char r9 = r1.charAt(r9)
            if (r9 < r7) goto L_0x0085
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            int r9 = r9 << r10
            r8 = r8 | r9
            int r10 = r10 + 13
            r9 = r12
            goto L_0x0074
        L_0x0085:
            int r9 = r9 << r10
            r8 = r8 | r9
            r9 = r12
            goto L_0x008a
        L_0x0089:
        L_0x008a:
            int r10 = r9 + 1
            char r9 = r1.charAt(r9)
            if (r9 < r7) goto L_0x00ab
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            r12 = 13
        L_0x0096:
            int r13 = r10 + 1
            char r10 = r1.charAt(r10)
            if (r10 < r7) goto L_0x00a7
            r10 = r10 & 8191(0x1fff, float:1.1478E-41)
            int r10 = r10 << r12
            r9 = r9 | r10
            int r12 = r12 + 13
            r10 = r13
            goto L_0x0096
        L_0x00a7:
            int r10 = r10 << r12
            r9 = r9 | r10
            r10 = r13
            goto L_0x00ac
        L_0x00ab:
        L_0x00ac:
            int r12 = r10 + 1
            char r10 = r1.charAt(r10)
            if (r10 < r7) goto L_0x00cd
            r10 = r10 & 8191(0x1fff, float:1.1478E-41)
            r13 = 13
        L_0x00b8:
            int r14 = r12 + 1
            char r12 = r1.charAt(r12)
            if (r12 < r7) goto L_0x00c9
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            int r12 = r12 << r13
            r10 = r10 | r12
            int r13 = r13 + 13
            r12 = r14
            goto L_0x00b8
        L_0x00c9:
            int r12 = r12 << r13
            r10 = r10 | r12
            r12 = r14
            goto L_0x00ce
        L_0x00cd:
        L_0x00ce:
            int r13 = r12 + 1
            char r12 = r1.charAt(r12)
            if (r12 < r7) goto L_0x00ef
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            r14 = 13
        L_0x00da:
            int r15 = r13 + 1
            char r13 = r1.charAt(r13)
            if (r13 < r7) goto L_0x00eb
            r13 = r13 & 8191(0x1fff, float:1.1478E-41)
            int r13 = r13 << r14
            r12 = r12 | r13
            int r14 = r14 + 13
            r13 = r15
            goto L_0x00da
        L_0x00eb:
            int r13 = r13 << r14
            r12 = r12 | r13
            r13 = r15
            goto L_0x00f0
        L_0x00ef:
        L_0x00f0:
            int r14 = r13 + 1
            char r13 = r1.charAt(r13)
            if (r13 < r7) goto L_0x0113
            r13 = r13 & 8191(0x1fff, float:1.1478E-41)
            r15 = 13
        L_0x00fc:
            int r16 = r14 + 1
            char r14 = r1.charAt(r14)
            if (r14 < r7) goto L_0x010e
            r14 = r14 & 8191(0x1fff, float:1.1478E-41)
            int r14 = r14 << r15
            r13 = r13 | r14
            int r15 = r15 + 13
            r14 = r16
            goto L_0x00fc
        L_0x010e:
            int r14 = r14 << r15
            r13 = r13 | r14
            r14 = r16
            goto L_0x0114
        L_0x0113:
        L_0x0114:
            int r15 = r14 + 1
            char r14 = r1.charAt(r14)
            if (r14 < r7) goto L_0x0139
            r14 = r14 & 8191(0x1fff, float:1.1478E-41)
            r16 = 13
        L_0x0120:
            int r17 = r15 + 1
            char r15 = r1.charAt(r15)
            if (r15 < r7) goto L_0x0133
            r15 = r15 & 8191(0x1fff, float:1.1478E-41)
            int r15 = r15 << r16
            r14 = r14 | r15
            int r16 = r16 + 13
            r15 = r17
            goto L_0x0120
        L_0x0133:
            int r15 = r15 << r16
            r14 = r14 | r15
            r15 = r17
            goto L_0x013a
        L_0x0139:
        L_0x013a:
            int r16 = r15 + 1
            char r15 = r1.charAt(r15)
            if (r15 < r7) goto L_0x0161
            r15 = r15 & 8191(0x1fff, float:1.1478E-41)
            r2 = r16
            r16 = 13
        L_0x0148:
            int r18 = r2 + 1
            char r2 = r1.charAt(r2)
            if (r2 < r7) goto L_0x015b
            r2 = r2 & 8191(0x1fff, float:1.1478E-41)
            int r2 = r2 << r16
            r15 = r15 | r2
            int r16 = r16 + 13
            r2 = r18
            goto L_0x0148
        L_0x015b:
            int r2 = r2 << r16
            r15 = r15 | r2
            r2 = r18
            goto L_0x0163
        L_0x0161:
            r2 = r16
        L_0x0163:
            int r16 = r2 + 1
            char r2 = r1.charAt(r2)
            if (r2 < r7) goto L_0x018a
            r2 = r2 & 8191(0x1fff, float:1.1478E-41)
            r6 = r16
            r16 = 13
        L_0x0171:
            int r19 = r6 + 1
            char r6 = r1.charAt(r6)
            if (r6 < r7) goto L_0x0184
            r6 = r6 & 8191(0x1fff, float:1.1478E-41)
            int r6 = r6 << r16
            r2 = r2 | r6
            int r16 = r16 + 13
            r6 = r19
            goto L_0x0171
        L_0x0184:
            int r6 = r6 << r16
            r2 = r2 | r6
            r16 = r19
            goto L_0x018b
        L_0x018a:
        L_0x018b:
            int r6 = r2 + r14
            int r6 = r6 + r15
            int[] r6 = new int[r6]
            int r15 = r8 + r8
            int r15 = r15 + r9
            r9 = r10
            r10 = r14
            r14 = r2
            r2 = r8
            r8 = r16
            r35 = r13
            r13 = r6
            r6 = r35
        L_0x019f:
            sun.misc.Unsafe r3 = f14376b
            java.lang.Object[] r7 = r0.f14405c
            r20 = r8
            ikf r8 = r0.f14403a
            java.lang.Class r8 = r8.getClass()
            r21 = r15
            int r15 = r6 * 3
            int[] r15 = new int[r15]
            int r6 = r6 + r6
            java.lang.Object[] r6 = new java.lang.Object[r6]
            int r22 = r14 + r10
            r24 = r14
            r10 = r20
            r25 = r22
            r20 = 0
            r23 = 0
        L_0x01c0:
            if (r10 >= r4) goto L_0x0444
            int r26 = r10 + 1
            char r10 = r1.charAt(r10)
            r27 = r4
            r4 = 55296(0xd800, float:7.7486E-41)
            if (r10 < r4) goto L_0x01f5
            r10 = r10 & 8191(0x1fff, float:1.1478E-41)
            r4 = r26
            r26 = 13
        L_0x01d5:
            int r28 = r4 + 1
            char r4 = r1.charAt(r4)
            r29 = r14
            r14 = 55296(0xd800, float:7.7486E-41)
            if (r4 < r14) goto L_0x01ef
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            int r4 = r4 << r26
            r10 = r10 | r4
            int r26 = r26 + 13
            r4 = r28
            r14 = r29
            goto L_0x01d5
        L_0x01ef:
            int r4 = r4 << r26
            r10 = r10 | r4
            r4 = r28
            goto L_0x01f9
        L_0x01f5:
            r29 = r14
            r4 = r26
        L_0x01f9:
            int r14 = r4 + 1
            char r4 = r1.charAt(r4)
            r26 = r14
            r14 = 55296(0xd800, float:7.7486E-41)
            if (r4 < r14) goto L_0x022c
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            r14 = r26
            r26 = 13
        L_0x020c:
            int r28 = r14 + 1
            char r14 = r1.charAt(r14)
            r30 = r11
            r11 = 55296(0xd800, float:7.7486E-41)
            if (r14 < r11) goto L_0x0226
            r11 = r14 & 8191(0x1fff, float:1.1478E-41)
            int r11 = r11 << r26
            r4 = r4 | r11
            int r26 = r26 + 13
            r14 = r28
            r11 = r30
            goto L_0x020c
        L_0x0226:
            int r11 = r14 << r26
            r4 = r4 | r11
            r14 = r28
            goto L_0x0230
        L_0x022c:
            r30 = r11
            r14 = r26
        L_0x0230:
            r11 = r4 & 255(0xff, float:3.57E-43)
            r26 = r12
            r12 = r4 & 1024(0x400, float:1.435E-42)
            if (r12 != 0) goto L_0x0239
            goto L_0x0241
        L_0x0239:
            int r12 = r23 + 1
            r13[r23] = r20
            r23 = r12
        L_0x0241:
            r12 = 51
            if (r11 >= r12) goto L_0x0369
            int r12 = r21 + 1
            r21 = r7[r21]
            r28 = r9
            r9 = r21
            java.lang.String r9 = (java.lang.String) r9
            java.lang.reflect.Field r9 = m13751a((java.lang.Class) r8, (java.lang.String) r9)
            r0 = 9
            if (r11 != r0) goto L_0x025b
            r33 = r4
            goto L_0x02d4
        L_0x025b:
            r0 = 17
            if (r11 == r0) goto L_0x02d2
            r0 = 27
            if (r11 != r0) goto L_0x0267
            r33 = r4
            goto L_0x02c1
        L_0x0267:
            r0 = 49
            if (r11 == r0) goto L_0x02bf
            r0 = 12
            if (r11 == r0) goto L_0x02a7
            r0 = 30
            if (r11 == r0) goto L_0x02a7
            r0 = 44
            if (r11 == r0) goto L_0x02a7
            r0 = 50
            if (r11 != r0) goto L_0x02a4
            int r0 = r24 + 1
            r13[r24] = r20
            int r24 = r20 / 3
            int r24 = r24 + r24
            int r31 = r12 + 1
            r12 = r7[r12]
            r6[r24] = r12
            r12 = r4 & 2048(0x800, float:2.87E-42)
            if (r12 == 0) goto L_0x029c
            int r12 = r31 + 1
            int r24 = r24 + 1
            r31 = r7[r31]
            r6[r24] = r31
            r24 = r0
            r33 = r4
            r4 = 1
            goto L_0x02df
        L_0x029c:
            r24 = r0
            r33 = r4
            r12 = r31
        L_0x02a2:
            r4 = 1
            goto L_0x02df
        L_0x02a4:
            r33 = r4
            goto L_0x02a2
        L_0x02a7:
            r0 = r5 & 1
            r33 = r4
            r4 = 1
            if (r0 != r4) goto L_0x02be
            int r0 = r20 / 3
            int r31 = r12 + 1
            int r0 = r0 + r0
            int r0 = r0 + r4
            r4 = r7[r12]
            r6[r0] = r4
            r12 = r31
            r4 = 1
            goto L_0x02df
        L_0x02be:
            goto L_0x02a2
        L_0x02bf:
            r33 = r4
        L_0x02c1:
            int r0 = r20 / 3
            int r4 = r12 + 1
            int r0 = r0 + r0
            r16 = 1
            int r0 = r0 + 1
            r12 = r7[r12]
            r6[r0] = r12
            r12 = r4
            r4 = 1
            goto L_0x02df
        L_0x02d2:
            r33 = r4
        L_0x02d4:
            int r0 = r20 / 3
            int r0 = r0 + r0
            r4 = 1
            int r0 = r0 + r4
            java.lang.Class r16 = r9.getType()
            r6[r0] = r16
        L_0x02df:
            r0 = r5
            long r4 = r3.objectFieldOffset(r9)
            int r5 = (int) r4
            r4 = r0 & 1
            r9 = 1
            if (r4 == r9) goto L_0x02f2
            r34 = r2
            r31 = r12
            r4 = r15
        L_0x02ef:
            r2 = 0
            r9 = 0
            goto L_0x0350
        L_0x02f2:
            r4 = 17
            if (r11 > r4) goto L_0x034a
            int r4 = r14 + 1
            char r9 = r1.charAt(r14)
            r14 = 55296(0xd800, float:7.7486E-41)
            if (r9 < r14) goto L_0x0321
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            r31 = 13
        L_0x0305:
            int r32 = r4 + 1
            char r4 = r1.charAt(r4)
            if (r4 < r14) goto L_0x031b
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            int r4 = r4 << r31
            r9 = r9 | r4
            int r31 = r31 + 13
            r4 = r32
            r14 = 55296(0xd800, float:7.7486E-41)
            goto L_0x0305
        L_0x031b:
            int r4 = r4 << r31
            r9 = r9 | r4
            r14 = r32
            goto L_0x0322
        L_0x0321:
            r14 = r4
        L_0x0322:
            int r4 = r2 + r2
            int r31 = r9 / 32
            int r4 = r4 + r31
            r34 = r2
            r2 = r7[r4]
            r31 = r12
            boolean r12 = r2 instanceof java.lang.reflect.Field
            if (r12 == 0) goto L_0x0335
            java.lang.reflect.Field r2 = (java.lang.reflect.Field) r2
            goto L_0x033f
        L_0x0335:
            java.lang.String r2 = (java.lang.String) r2
            java.lang.reflect.Field r2 = m13751a((java.lang.Class) r8, (java.lang.String) r2)
            r7[r4] = r2
        L_0x033f:
            r12 = r14
            r4 = r15
            long r14 = r3.objectFieldOffset(r2)
            int r2 = (int) r14
            int r9 = r9 % 32
            r14 = r12
            goto L_0x0350
        L_0x034a:
            r34 = r2
            r31 = r12
            r4 = r15
            goto L_0x02ef
        L_0x0350:
            r12 = 18
            if (r11 >= r12) goto L_0x0355
        L_0x0354:
            goto L_0x0365
        L_0x0355:
            r12 = 49
            if (r11 > r12) goto L_0x0354
            int r12 = r25 + 1
            r13[r25] = r5
            r25 = r12
            r21 = r31
            goto L_0x0404
        L_0x0365:
            r21 = r31
            goto L_0x0404
        L_0x0369:
            r34 = r2
            r33 = r4
            r0 = r5
            r28 = r9
            r4 = r15
            int r2 = r14 + 1
            char r5 = r1.charAt(r14)
            r9 = 55296(0xd800, float:7.7486E-41)
            if (r5 >= r9) goto L_0x0380
            r14 = 55296(0xd800, float:7.7486E-41)
            goto L_0x039d
        L_0x0380:
            r5 = r5 & 8191(0x1fff, float:1.1478E-41)
            r9 = 13
        L_0x0385:
            int r12 = r2 + 1
            char r2 = r1.charAt(r2)
            r14 = 55296(0xd800, float:7.7486E-41)
            if (r2 < r14) goto L_0x039a
            r2 = r2 & 8191(0x1fff, float:1.1478E-41)
            int r2 = r2 << r9
            r5 = r5 | r2
            int r9 = r9 + 13
            r2 = r12
            goto L_0x0385
        L_0x039a:
            int r2 = r2 << r9
            r5 = r5 | r2
            r2 = r12
        L_0x039d:
            int r9 = r11 + -51
            r12 = 9
            if (r9 != r12) goto L_0x03a4
            goto L_0x03c1
        L_0x03a4:
            r12 = 17
            if (r9 == r12) goto L_0x03c1
            r12 = 12
            if (r9 != r12) goto L_0x03bf
            r9 = r0 & 1
            r12 = 1
            if (r9 != r12) goto L_0x03bf
            int r9 = r20 / 3
            int r15 = r21 + 1
            int r9 = r9 + r9
            int r9 = r9 + r12
            r12 = r7[r21]
            r6[r9] = r12
            r21 = r15
            r15 = 1
            goto L_0x03ce
        L_0x03bf:
            r15 = 1
            goto L_0x03ce
        L_0x03c1:
            int r9 = r20 / 3
            int r12 = r21 + 1
            int r9 = r9 + r9
            r15 = 1
            int r9 = r9 + r15
            r16 = r7[r21]
            r6[r9] = r16
            r21 = r12
        L_0x03ce:
            int r5 = r5 + r5
            r9 = r7[r5]
            boolean r12 = r9 instanceof java.lang.reflect.Field
            if (r12 == 0) goto L_0x03d8
            java.lang.reflect.Field r9 = (java.lang.reflect.Field) r9
            goto L_0x03e0
        L_0x03d8:
            java.lang.String r9 = (java.lang.String) r9
            java.lang.reflect.Field r9 = m13751a((java.lang.Class) r8, (java.lang.String) r9)
            r7[r5] = r9
        L_0x03e0:
            long r14 = r3.objectFieldOffset(r9)
            int r9 = (int) r14
            int r5 = r5 + 1
            r12 = r7[r5]
            boolean r14 = r12 instanceof java.lang.reflect.Field
            if (r14 == 0) goto L_0x03f0
            java.lang.reflect.Field r12 = (java.lang.reflect.Field) r12
            goto L_0x03fa
        L_0x03f0:
            java.lang.String r12 = (java.lang.String) r12
            java.lang.reflect.Field r12 = m13751a((java.lang.Class) r8, (java.lang.String) r12)
            r7[r5] = r12
        L_0x03fa:
            long r14 = r3.objectFieldOffset(r12)
            int r5 = (int) r14
            r14 = r2
            r2 = r5
            r5 = r9
            r9 = 0
        L_0x0404:
            int r12 = r20 + 1
            r4[r20] = r10
            int r10 = r12 + 1
            r20 = r0
            r15 = r33
            r0 = r15 & 512(0x200, float:7.175E-43)
            if (r0 == 0) goto L_0x0415
            r0 = 536870912(0x20000000, float:1.0842022E-19)
            goto L_0x0416
        L_0x0415:
            r0 = 0
        L_0x0416:
            r15 = r15 & 256(0x100, float:3.59E-43)
            if (r15 == 0) goto L_0x041d
            r15 = 268435456(0x10000000, float:2.5243549E-29)
            goto L_0x041f
        L_0x041d:
            r15 = 0
        L_0x041f:
            r0 = r0 | r15
            int r11 = r11 << 20
            r0 = r0 | r11
            r0 = r0 | r5
            r4[r12] = r0
            int r0 = r10 + 1
            int r5 = r9 << 20
            r2 = r2 | r5
            r4[r10] = r2
            r15 = r4
            r10 = r14
            r5 = r20
            r12 = r26
            r4 = r27
            r9 = r28
            r14 = r29
            r11 = r30
            r2 = r34
            r20 = r0
            r0 = r36
            goto L_0x01c0
        L_0x0444:
            r28 = r9
            r30 = r11
            r26 = r12
            r29 = r14
            r4 = r15
            ikh r0 = new ikh
            r5 = r0
            r1 = r36
            ikf r10 = r1.f14403a
            r20 = 0
            r21 = 0
            r1 = r6
            r6 = r4
            r7 = r1
            r8 = r28
            r9 = r26
            r12 = r13
            r13 = r29
            r14 = r22
            r15 = r37
            r16 = r38
            r17 = r39
            r18 = r40
            r19 = r41
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.ikh.m13748a(ikr, ikk, ijr, imu, imi, ijz):ikh");
    }

    /* renamed from: d */
    private final int m13773d(int i) {
        return this.f14377c[i];
    }

    /* renamed from: f */
    private static boolean m13780f(Object obj, long j) {
        return ((Boolean) ilv.m14048f(obj, j)).booleanValue();
    }

    /* renamed from: b */
    private static double m13761b(Object obj, long j) {
        return ((Double) ilv.m14048f(obj, j)).doubleValue();
    }

    /* renamed from: c */
    private static float m13769c(Object obj, long j) {
        return ((Float) ilv.m14048f(obj, j)).floatValue();
    }

    /* renamed from: d */
    private static int m13774d(Object obj, long j) {
        return ((Integer) ilv.m14048f(obj, j)).intValue();
    }

    /* renamed from: e */
    private static long m13777e(Object obj, long j) {
        return ((Long) ilv.m14048f(obj, j)).longValue();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v11, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v12, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int m13745a(java.lang.Object r8, byte[] r9, int r10, int r11, int r12, long r13, p000.ihf r15) {
        /*
            r7 = this;
            sun.misc.Unsafe r0 = f14376b
            java.lang.Object r12 = r7.m13763b((int) r12)
            java.lang.Object r1 = r0.getObject(r8, r13)
            ijz r2 = r7.f14390p
            boolean r2 = r2.mo8856c(r1)
            if (r2 == 0) goto L_0x0022
            ijz r2 = r7.f14390p
            java.lang.Object r2 = r2.mo8852a()
            ijz r3 = r7.f14390p
            r3.mo8853a(r2, r1)
            r0.putObject(r8, r13, r2)
            r1 = r2
            goto L_0x0023
        L_0x0022:
        L_0x0023:
            ijz r8 = r7.f14390p
            ijw r8 = r8.mo8858e(r12)
            ijz r12 = r7.f14390p
            java.util.Map r12 = r12.mo8854a(r1)
            int r10 = p000.ihg.m13023a((byte[]) r9, (int) r10, (p000.ihf) r15)
            int r13 = r15.f14178a
            if (r13 < 0) goto L_0x0093
            int r14 = r11 - r10
            if (r13 > r14) goto L_0x0093
            int r13 = r13 + r10
            java.lang.Object r14 = r8.f14364b
            java.lang.Object r0 = r8.f14366d
        L_0x0040:
            if (r10 >= r13) goto L_0x0088
            int r1 = r10 + 1
            byte r10 = r9[r10]
            if (r10 >= 0) goto L_0x004f
            int r1 = p000.ihg.m13017a((int) r10, (byte[]) r9, (int) r1, (p000.ihf) r15)
            int r10 = r15.f14178a
            goto L_0x0050
        L_0x004f:
        L_0x0050:
            r2 = r1
            r1 = r10 & 7
            int r3 = r10 >>> 3
            r4 = 1
            if (r3 == r4) goto L_0x0072
            r4 = 2
            if (r3 == r4) goto L_0x005c
            goto L_0x0083
        L_0x005c:
            imb r4 = r8.f14365c
            int r3 = r4.f14495j
            if (r1 != r3) goto L_0x0083
            java.lang.Object r10 = r8.f14366d
            java.lang.Class r5 = r10.getClass()
            r1 = r9
            r3 = r11
            r6 = r15
            int r10 = m13746a((byte[]) r1, (int) r2, (int) r3, (p000.imb) r4, (java.lang.Class) r5, (p000.ihf) r6)
            java.lang.Object r0 = r15.f14180c
            goto L_0x0040
        L_0x0072:
            imb r4 = r8.f14363a
            int r3 = r4.f14495j
            if (r1 != r3) goto L_0x0083
            r5 = 0
            r1 = r9
            r3 = r11
            r6 = r15
            int r10 = m13746a((byte[]) r1, (int) r2, (int) r3, (p000.imb) r4, (java.lang.Class) r5, (p000.ihf) r6)
            java.lang.Object r14 = r15.f14180c
            goto L_0x0040
        L_0x0083:
            int r10 = p000.ihg.m13014a((int) r10, (byte[]) r9, (int) r2, (int) r11, (p000.ihf) r15)
            goto L_0x0040
        L_0x0088:
            if (r10 != r13) goto L_0x008e
            r12.put(r14, r0)
            return r13
        L_0x008e:
            ijh r8 = p000.ijh.m13661h()
            throw r8
        L_0x0093:
            ijh r8 = p000.ijh.m13654a()
            goto L_0x0099
        L_0x0098:
            throw r8
        L_0x0099:
            goto L_0x0098
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.ikh.m13745a(java.lang.Object, byte[], int, int, int, long, ihf):int");
    }

    /* renamed from: a */
    private final int m13743a(Object obj, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, ihf ihf) {
        boolean z;
        Object obj2 = obj;
        byte[] bArr2 = bArr;
        int i9 = i;
        int i10 = i3;
        int i11 = i4;
        int i12 = i5;
        long j2 = j;
        int i13 = i8;
        ihf ihf2 = ihf;
        Unsafe unsafe = f14376b;
        long j3 = (long) (this.f14377c[i13 + 2] & 1048575);
        switch (i7) {
            case 51:
                if (i12 == 1) {
                    unsafe.putObject(obj2, j2, Double.valueOf(ihg.m13048c(bArr, i)));
                    unsafe.putInt(obj2, j3, i11);
                    return i9 + 8;
                }
                break;
            case 52:
                if (i12 == 5) {
                    unsafe.putObject(obj2, j2, Float.valueOf(ihg.m13053d(bArr, i)));
                    unsafe.putInt(obj2, j3, i11);
                    return i9 + 4;
                }
                break;
            case 53:
            case 54:
                if (i12 == 0) {
                    int b = ihg.m13045b(bArr2, i9, ihf2);
                    unsafe.putObject(obj2, j2, Long.valueOf(ihf2.f14179b));
                    unsafe.putInt(obj2, j3, i11);
                    return b;
                }
                break;
            case 55:
            case 62:
                if (i12 == 0) {
                    int a = ihg.m13023a(bArr2, i9, ihf2);
                    unsafe.putObject(obj2, j2, Integer.valueOf(ihf2.f14178a));
                    unsafe.putInt(obj2, j3, i11);
                    return a;
                }
                break;
            case 56:
            case 65:
                if (i12 == 1) {
                    unsafe.putObject(obj2, j2, Long.valueOf(ihg.m13046b(bArr, i)));
                    unsafe.putInt(obj2, j3, i11);
                    return i9 + 8;
                }
                break;
            case 57:
            case 64:
                if (i12 == 5) {
                    unsafe.putObject(obj2, j2, Integer.valueOf(ihg.m13022a(bArr, i)));
                    unsafe.putInt(obj2, j3, i11);
                    return i9 + 4;
                }
                break;
            case 58:
                if (i12 == 0) {
                    int b2 = ihg.m13045b(bArr2, i9, ihf2);
                    if (ihf2.f14179b != 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    unsafe.putObject(obj2, j2, Boolean.valueOf(z));
                    unsafe.putInt(obj2, j3, i11);
                    return b2;
                }
                break;
            case 59:
                if (i12 == 2) {
                    int a2 = ihg.m13023a(bArr2, i9, ihf2);
                    int i14 = ihf2.f14178a;
                    if (i14 == 0) {
                        unsafe.putObject(obj2, j2, "");
                    } else if ((i6 & 536870912) != 0 && !ima.m14069a(bArr2, a2, a2 + i14)) {
                        throw ijh.m13662i();
                    } else {
                        unsafe.putObject(obj2, j2, new String(bArr2, a2, i14, ijf.f14336a));
                        a2 += i14;
                    }
                    unsafe.putInt(obj2, j3, i11);
                    return a2;
                }
                break;
            case 60:
                if (i12 == 2) {
                    int a3 = ihg.m13021a(m13749a(i13), bArr2, i9, i2, ihf2);
                    Object object = unsafe.getInt(obj2, j3) == i11 ? unsafe.getObject(obj2, j2) : null;
                    if (object != null) {
                        unsafe.putObject(obj2, j2, ijf.m13647a(object, ihf2.f14180c));
                    } else {
                        unsafe.putObject(obj2, j2, ihf2.f14180c);
                    }
                    unsafe.putInt(obj2, j3, i11);
                    return a3;
                }
                break;
            case 61:
                if (i12 == 2) {
                    int e = ihg.m13057e(bArr2, i9, ihf2);
                    unsafe.putObject(obj2, j2, ihf2.f14180c);
                    unsafe.putInt(obj2, j3, i11);
                    return e;
                }
                break;
            case 63:
                if (i12 == 0) {
                    int a4 = ihg.m13023a(bArr2, i9, ihf2);
                    int i15 = ihf2.f14178a;
                    ijb c = m13770c(i13);
                    if (c == null || c.mo2351a(i15)) {
                        unsafe.putObject(obj2, j2, Integer.valueOf(i15));
                        unsafe.putInt(obj2, j3, i11);
                    } else {
                        m13782g(obj).mo8943a(i10, (Object) Long.valueOf((long) i15));
                    }
                    return a4;
                }
                break;
            case 66:
                if (i12 == 0) {
                    int a5 = ihg.m13023a(bArr2, i9, ihf2);
                    unsafe.putObject(obj2, j2, Integer.valueOf(ihz.m13264e(ihf2.f14178a)));
                    unsafe.putInt(obj2, j3, i11);
                    return a5;
                }
                break;
            case 67:
                if (i12 == 0) {
                    int b3 = ihg.m13045b(bArr2, i9, ihf2);
                    unsafe.putObject(obj2, j2, Long.valueOf(ihz.m13260a(ihf2.f14179b)));
                    unsafe.putInt(obj2, j3, i11);
                    return b3;
                }
                break;
            case 68:
                if (i12 == 3) {
                    int a6 = ihg.m13020a(m13749a(i13), bArr, i, i2, (i10 & -8) | 4, ihf);
                    Object object2 = unsafe.getInt(obj2, j3) == i11 ? unsafe.getObject(obj2, j2) : null;
                    if (object2 != null) {
                        unsafe.putObject(obj2, j2, ijf.m13647a(object2, ihf2.f14180c));
                    } else {
                        unsafe.putObject(obj2, j2, ihf2.f14180c);
                    }
                    unsafe.putInt(obj2, j3, i11);
                    return a6;
                }
                break;
        }
        return i9;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v10, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v53, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v24, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v21, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v23, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v24, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v29, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v25, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v26, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v35, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v27, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v28, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v9, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v29, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v10, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v42, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v11, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v12, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v13, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v43, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v14, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v15, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v45, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v16, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v17, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v47, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v18, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v19, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v50, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v20, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v21, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v51, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v22, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v23, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v53, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v24, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v25, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v56, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v26, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v27, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v59, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v28, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v29, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v65, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v30, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v31, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v67, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v32, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v69, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v33, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v34, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v72, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v35, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v36, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v38, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v74, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v39, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v40, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v79, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v41, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v20, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v21, resolved type: byte} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x03be, code lost:
        if (r0 == r15) goto L_0x034c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x02bb, code lost:
        r23 = r7;
        r2 = r8;
        r8 = r10;
        r7 = r14;
        r9 = r15;
        r26 = r25;
        r18 = true;
        r25 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x034a, code lost:
        if (r0 == r15) goto L_0x034c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x0359, code lost:
        r15 = r29;
        r14 = r30;
        r12 = r31;
        r13 = r33;
        r11 = r34;
        r9 = r35;
        r6 = r21;
        r3 = r23;
        r7 = r24;
        r1 = r25;
        r2 = r26;
        r10 = r28;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x03a3, code lost:
        if (r0 == r15) goto L_0x034c;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int mo8863a(java.lang.Object r30, byte[] r31, int r32, int r33, int r34, p000.ihf r35) {
        /*
            r29 = this;
            r15 = r29
            r14 = r30
            r12 = r31
            r13 = r33
            r11 = r34
            r9 = r35
            sun.misc.Unsafe r10 = f14376b
            r16 = 0
            r0 = r32
            r1 = 0
            r2 = -1
            r3 = 0
            r6 = 0
            r7 = -1
        L_0x0017:
            r17 = 0
            if (r0 >= r13) goto L_0x0662
            int r1 = r0 + 1
            byte r0 = r12[r0]
            if (r0 >= 0) goto L_0x002a
            int r0 = p000.ihg.m13017a((int) r0, (byte[]) r12, (int) r1, (p000.ihf) r9)
            int r1 = r9.f14178a
            r5 = r0
            r4 = r1
            goto L_0x002e
        L_0x002a:
            r4 = r0
            r5 = r1
        L_0x002e:
            int r1 = r4 >>> 3
            r0 = r4 & 7
            r8 = 3
            if (r1 <= r2) goto L_0x003b
            int r3 = r3 / r8
            int r2 = r15.m13742a((int) r1, (int) r3)
            goto L_0x003f
        L_0x003b:
            int r2 = r15.m13786j(r1)
        L_0x003f:
            r19 = 0
            r3 = -1
            if (r2 == r3) goto L_0x0440
            int[] r3 = r15.f14377c
            int r22 = r2 + 1
            r3 = r3[r22]
            int r8 = m13781g((int) r3)
            r23 = r4
            r24 = r5
            long r4 = m13785i(r3)
            r25 = r1
            r1 = 17
            if (r8 > r1) goto L_0x0306
            int[] r1 = r15.f14377c
            int r26 = r2 + 2
            r1 = r1[r26]
            int r26 = r1 >>> 20
            r22 = 1
            int r26 = r22 << r26
            r27 = 1048575(0xfffff, float:1.469367E-39)
            r1 = r1 & r27
            if (r1 != r7) goto L_0x0070
            goto L_0x0080
        L_0x0070:
            r11 = -1
            if (r7 != r11) goto L_0x0075
            goto L_0x0079
        L_0x0075:
            long r11 = (long) r7
            r10.putInt(r14, r11, r6)
        L_0x0079:
            long r6 = (long) r1
            int r6 = r10.getInt(r14, r6)
            r7 = r1
        L_0x0080:
            r1 = 5
            switch(r8) {
                case 0: goto L_0x0296;
                case 1: goto L_0x0271;
                case 2: goto L_0x0248;
                case 3: goto L_0x0248;
                case 4: goto L_0x0228;
                case 5: goto L_0x0201;
                case 6: goto L_0x01e1;
                case 7: goto L_0x01b8;
                case 8: goto L_0x018d;
                case 9: goto L_0x0156;
                case 10: goto L_0x0135;
                case 11: goto L_0x0228;
                case 12: goto L_0x00f1;
                case 13: goto L_0x01e1;
                case 14: goto L_0x0201;
                case 15: goto L_0x00cd;
                case 16: goto L_0x0095;
                default: goto L_0x0084;
            }
        L_0x0084:
            r12 = r31
            r11 = r23
            r8 = r24
            r24 = r7
            r7 = r2
            r2 = 1
            r1 = 3
            if (r0 == r1) goto L_0x02c9
            goto L_0x02bb
        L_0x0095:
            if (r0 != 0) goto L_0x00c2
            r12 = r31
            r8 = r24
            int r8 = p000.ihg.m13045b(r12, r8, r9)
            long r0 = r9.f14179b
            long r19 = p000.ihz.m13260a((long) r0)
            r0 = r10
            r11 = r25
            r1 = r30
            r24 = r7
            r7 = r2
            r2 = r4
            r11 = r23
            r4 = r19
            r0.putLong(r1, r2, r4)
            r6 = r6 | r26
            r3 = r7
            r0 = r8
            r1 = r11
            r7 = r24
            r2 = r25
            r11 = r34
            goto L_0x0017
        L_0x00c2:
            r12 = r31
            r11 = r23
            r8 = r24
            r24 = r7
            r7 = r2
            goto L_0x0291
        L_0x00cd:
            r12 = r31
            r11 = r23
            r8 = r24
            r24 = r7
            r7 = r2
            if (r0 != 0) goto L_0x0291
            int r0 = p000.ihg.m13023a((byte[]) r12, (int) r8, (p000.ihf) r9)
            int r1 = r9.f14178a
            int r1 = p000.ihz.m13264e(r1)
            r10.putInt(r14, r4, r1)
            r6 = r6 | r26
            r3 = r7
            r1 = r11
            r7 = r24
            r2 = r25
            r11 = r34
            goto L_0x0017
        L_0x00f1:
            r12 = r31
            r11 = r23
            r8 = r24
            r24 = r7
            r7 = r2
            if (r0 != 0) goto L_0x0291
            int r0 = p000.ihg.m13023a((byte[]) r12, (int) r8, (p000.ihf) r9)
            int r1 = r9.f14178a
            ijb r2 = r15.m13770c((int) r7)
            if (r2 == 0) goto L_0x0126
            boolean r2 = r2.mo2351a(r1)
            if (r2 == 0) goto L_0x010f
            goto L_0x0126
        L_0x010f:
            ilm r2 = m13782g((java.lang.Object) r30)
            long r3 = (long) r1
            java.lang.Long r1 = java.lang.Long.valueOf(r3)
            r2.mo8943a((int) r11, (java.lang.Object) r1)
            r3 = r7
            r1 = r11
            r7 = r24
            r2 = r25
            r11 = r34
            goto L_0x0017
        L_0x0126:
            r10.putInt(r14, r4, r1)
            r6 = r6 | r26
            r3 = r7
            r1 = r11
            r7 = r24
            r2 = r25
            r11 = r34
            goto L_0x0017
        L_0x0135:
            r12 = r31
            r11 = r23
            r8 = r24
            r24 = r7
            r7 = r2
            r1 = 2
            if (r0 != r1) goto L_0x0291
            int r0 = p000.ihg.m13057e(r12, r8, r9)
            java.lang.Object r1 = r9.f14180c
            r10.putObject(r14, r4, r1)
            r6 = r6 | r26
            r3 = r7
            r1 = r11
            r7 = r24
            r2 = r25
            r11 = r34
            goto L_0x0017
        L_0x0156:
            r12 = r31
            r11 = r23
            r8 = r24
            r24 = r7
            r7 = r2
            r1 = 2
            if (r0 != r1) goto L_0x0291
            iky r0 = r15.m13749a((int) r7)
            int r0 = p000.ihg.m13021a((p000.iky) r0, (byte[]) r12, (int) r8, (int) r13, (p000.ihf) r9)
            r1 = r6 & r26
            if (r1 != 0) goto L_0x0174
            java.lang.Object r1 = r9.f14180c
            r10.putObject(r14, r4, r1)
            goto L_0x0181
        L_0x0174:
            java.lang.Object r1 = r10.getObject(r14, r4)
            java.lang.Object r2 = r9.f14180c
            java.lang.Object r1 = p000.ijf.m13647a((java.lang.Object) r1, (java.lang.Object) r2)
            r10.putObject(r14, r4, r1)
        L_0x0181:
            r6 = r6 | r26
            r3 = r7
            r1 = r11
            r7 = r24
            r2 = r25
            r11 = r34
            goto L_0x0017
        L_0x018d:
            r12 = r31
            r11 = r23
            r8 = r24
            r24 = r7
            r7 = r2
            r1 = 2
            if (r0 != r1) goto L_0x0291
            r0 = 536870912(0x20000000, float:1.0842022E-19)
            r0 = r0 & r3
            if (r0 == 0) goto L_0x01a3
            int r0 = p000.ihg.m13055d(r12, r8, r9)
            goto L_0x01a7
        L_0x01a3:
            int r0 = p000.ihg.m13051c(r12, r8, r9)
        L_0x01a7:
            java.lang.Object r1 = r9.f14180c
            r10.putObject(r14, r4, r1)
            r6 = r6 | r26
            r3 = r7
            r1 = r11
            r7 = r24
            r2 = r25
            r11 = r34
            goto L_0x0017
        L_0x01b8:
            r12 = r31
            r11 = r23
            r8 = r24
            r24 = r7
            r7 = r2
            if (r0 != 0) goto L_0x0291
            int r0 = p000.ihg.m13045b(r12, r8, r9)
            long r1 = r9.f14179b
            int r3 = (r1 > r19 ? 1 : (r1 == r19 ? 0 : -1))
            if (r3 == 0) goto L_0x01cf
            r8 = 1
            goto L_0x01d1
        L_0x01cf:
            r8 = 0
        L_0x01d1:
            p000.ilv.m14037a((java.lang.Object) r14, (long) r4, (boolean) r8)
            r6 = r6 | r26
            r3 = r7
            r1 = r11
            r7 = r24
            r2 = r25
            r11 = r34
            goto L_0x0017
        L_0x01e1:
            r12 = r31
            r11 = r23
            r8 = r24
            r24 = r7
            r7 = r2
            if (r0 != r1) goto L_0x0291
            int r0 = p000.ihg.m13022a((byte[]) r12, (int) r8)
            r10.putInt(r14, r4, r0)
            int r0 = r8 + 4
            r6 = r6 | r26
            r3 = r7
            r1 = r11
            r7 = r24
            r2 = r25
            r11 = r34
            goto L_0x0017
        L_0x0201:
            r12 = r31
            r11 = r23
            r8 = r24
            r24 = r7
            r7 = r2
            r1 = 1
            if (r0 != r1) goto L_0x0291
            long r19 = p000.ihg.m13046b((byte[]) r12, (int) r8)
            r0 = r10
            r1 = r30
            r2 = r4
            r4 = r19
            r0.putLong(r1, r2, r4)
            int r0 = r8 + 8
            r6 = r6 | r26
            r3 = r7
            r1 = r11
            r7 = r24
            r2 = r25
            r11 = r34
            goto L_0x0017
        L_0x0228:
            r12 = r31
            r11 = r23
            r8 = r24
            r24 = r7
            r7 = r2
            if (r0 != 0) goto L_0x0291
            int r0 = p000.ihg.m13023a((byte[]) r12, (int) r8, (p000.ihf) r9)
            int r1 = r9.f14178a
            r10.putInt(r14, r4, r1)
            r6 = r6 | r26
            r3 = r7
            r1 = r11
            r7 = r24
            r2 = r25
            r11 = r34
            goto L_0x0017
        L_0x0248:
            r12 = r31
            r11 = r23
            r8 = r24
            r24 = r7
            r7 = r2
            if (r0 != 0) goto L_0x0291
            int r8 = p000.ihg.m13045b(r12, r8, r9)
            long r2 = r9.f14179b
            r0 = r10
            r1 = r30
            r19 = r2
            r2 = r4
            r4 = r19
            r0.putLong(r1, r2, r4)
            r6 = r6 | r26
            r3 = r7
            r0 = r8
            r1 = r11
            r7 = r24
            r2 = r25
            r11 = r34
            goto L_0x0017
        L_0x0271:
            r12 = r31
            r11 = r23
            r8 = r24
            r24 = r7
            r7 = r2
            if (r0 != r1) goto L_0x0291
            float r0 = p000.ihg.m13053d((byte[]) r12, (int) r8)
            p000.ilv.m14033a((java.lang.Object) r14, (long) r4, (float) r0)
            int r0 = r8 + 4
            r6 = r6 | r26
            r3 = r7
            r1 = r11
            r7 = r24
            r2 = r25
            r11 = r34
            goto L_0x0017
        L_0x0291:
            r2 = 1
            goto L_0x02bb
        L_0x0296:
            r12 = r31
            r11 = r23
            r8 = r24
            r24 = r7
            r7 = r2
            r2 = 1
            if (r0 != r2) goto L_0x02b7
            double r0 = p000.ihg.m13048c((byte[]) r12, (int) r8)
            p000.ilv.m14032a((java.lang.Object) r14, (long) r4, (double) r0)
            int r0 = r8 + 8
            r6 = r6 | r26
            r3 = r7
            r1 = r11
            r7 = r24
            r2 = r25
            r11 = r34
            goto L_0x0017
        L_0x02b7:
        L_0x02bb:
            r23 = r7
            r2 = r8
            r8 = r10
            r7 = r14
            r9 = r15
            r26 = r25
            r18 = 1
            r25 = r11
            goto L_0x0452
        L_0x02c9:
            iky r0 = r15.m13749a((int) r7)
            int r1 = r25 << 3
            r17 = r1 | 4
            r1 = r31
            r2 = r8
            r3 = r33
            r12 = r4
            r4 = r17
            r5 = r35
            int r0 = p000.ihg.m13020a((p000.iky) r0, (byte[]) r1, (int) r2, (int) r3, (int) r4, (p000.ihf) r5)
            r1 = r6 & r26
            if (r1 != 0) goto L_0x02e9
            java.lang.Object r1 = r9.f14180c
            r10.putObject(r14, r12, r1)
            goto L_0x02f6
        L_0x02e9:
            java.lang.Object r1 = r10.getObject(r14, r12)
            java.lang.Object r2 = r9.f14180c
            java.lang.Object r1 = p000.ijf.m13647a((java.lang.Object) r1, (java.lang.Object) r2)
            r10.putObject(r14, r12, r1)
        L_0x02f6:
            r6 = r6 | r26
            r12 = r31
            r13 = r33
            r3 = r7
            r1 = r11
            r7 = r24
            r2 = r25
            r11 = r34
            goto L_0x0017
        L_0x0306:
            r12 = r4
            r11 = r23
            r5 = r24
            r24 = r7
            r7 = r2
            r2 = 1
            r1 = 27
            if (r8 == r1) goto L_0x03cf
            r1 = 49
            if (r8 > r1) goto L_0x0373
            long r3 = (long) r3
            r1 = r0
            r0 = r29
            r32 = r1
            r1 = r30
            r22 = 1
            r2 = r31
            r26 = r3
            r4 = 10
            r3 = r5
            r4 = r33
            r15 = r5
            r5 = r11
            r21 = r6
            r6 = r25
            r23 = r7
            r7 = r32
            r22 = r8
            r18 = 1
            r8 = r23
            r28 = r10
            r9 = r26
            r26 = r25
            r25 = r11
            r11 = r22
            r14 = r35
            int r0 = r0.m13744a((java.lang.Object) r1, (byte[]) r2, (int) r3, (int) r4, (int) r5, (int) r6, (int) r7, (int) r8, (long) r9, (int) r11, (long) r12, (p000.ihf) r14)
            if (r0 != r15) goto L_0x0359
        L_0x034c:
            r9 = r29
            r7 = r30
            r2 = r0
            r6 = r21
            r8 = r28
            goto L_0x0452
        L_0x0359:
            r15 = r29
            r14 = r30
            r12 = r31
            r13 = r33
            r11 = r34
            r9 = r35
            r6 = r21
            r3 = r23
            r7 = r24
            r1 = r25
            r2 = r26
            r10 = r28
            goto L_0x0017
        L_0x0373:
            r32 = r0
            r15 = r5
            r21 = r6
            r23 = r7
            r22 = r8
            r28 = r10
            r26 = r25
            r18 = 1
            r25 = r11
            r0 = 50
            r9 = r22
            if (r9 == r0) goto L_0x03a6
            r0 = r29
            r1 = r30
            r2 = r31
            r8 = r3
            r3 = r15
            r4 = r33
            r5 = r25
            r6 = r26
            r7 = r32
            r10 = r12
            r12 = r23
            r13 = r35
            int r0 = r0.m13743a((java.lang.Object) r1, (byte[]) r2, (int) r3, (int) r4, (int) r5, (int) r6, (int) r7, (int) r8, (int) r9, (long) r10, (int) r12, (p000.ihf) r13)
            if (r0 != r15) goto L_0x0359
            goto L_0x034c
        L_0x03a6:
            r0 = r32
            r1 = 2
            if (r0 != r1) goto L_0x03c1
            r0 = r29
            r1 = r30
            r2 = r31
            r3 = r15
            r4 = r33
            r5 = r23
            r6 = r12
            r8 = r35
            int r0 = r0.m13745a(r1, r2, r3, r4, r5, r6, r8)
            if (r0 != r15) goto L_0x0359
            goto L_0x034c
        L_0x03c1:
            r9 = r29
            r7 = r30
            r3 = r15
            r10 = r23
            r8 = r28
            goto L_0x043a
        L_0x03cf:
            r15 = r5
            r21 = r6
            r23 = r7
            r28 = r10
            r26 = r25
            r18 = 1
            r25 = r11
            r1 = 2
            if (r0 != r1) goto L_0x042e
            r7 = r30
            r8 = r28
            java.lang.Object r0 = r8.getObject(r7, r12)
            ije r0 = (p000.ije) r0
            boolean r1 = r0.mo8521a()
            if (r1 != 0) goto L_0x0403
            int r1 = r0.size()
            if (r1 == 0) goto L_0x03f8
            int r3 = r1 + r1
            goto L_0x03fb
        L_0x03f8:
            r3 = 10
        L_0x03fb:
            ije r0 = r0.mo8585a(r3)
            r8.putObject(r7, r12, r0)
            goto L_0x0404
        L_0x0403:
        L_0x0404:
            r5 = r0
            r9 = r29
            r3 = r15
            r10 = r23
            iky r0 = r9.m13749a((int) r10)
            r1 = r25
            r2 = r31
            r4 = r33
            r6 = r35
            int r0 = p000.ihg.m13019a(r0, r1, r2, r3, r4, r5, r6)
            r12 = r31
            r13 = r33
            r11 = r34
            r14 = r7
            r15 = r9
            r3 = r10
            r6 = r21
            r7 = r24
            r2 = r26
            r9 = r35
            r10 = r8
            goto L_0x0017
        L_0x042e:
            r9 = r29
            r7 = r30
            r3 = r15
            r10 = r23
            r8 = r28
        L_0x043a:
            r2 = r3
            r23 = r10
            r6 = r21
            goto L_0x0452
        L_0x0440:
            r26 = r1
            r25 = r4
            r3 = r5
            r21 = r6
            r24 = r7
            r8 = r10
            r7 = r14
            r9 = r15
            r18 = 1
            r2 = r3
            r23 = 0
        L_0x0452:
            r10 = r34
            r11 = r25
            if (r11 == r10) goto L_0x0459
            goto L_0x0464
        L_0x0459:
            if (r10 == 0) goto L_0x0464
            r0 = r2
            r2 = r6
            r1 = r11
            r3 = r24
            r6 = r33
            goto L_0x066f
        L_0x0464:
            boolean r0 = r9.f14382h
            if (r0 == 0) goto L_0x0636
            r12 = r35
            iij r0 = r12.f14181d
            iij r1 = p000.iij.m13501a()
            if (r0 == r1) goto L_0x062d
            ikf r0 = r9.f14381g
            iij r1 = r12.f14181d
            r13 = r26
            iih r14 = r1.mo8715a(r0, r13)
            if (r14 != 0) goto L_0x0497
            ilm r4 = m13782g((java.lang.Object) r30)
            r0 = r11
            r1 = r31
            r3 = r33
            r5 = r35
            int r0 = p000.ihg.m13016a((int) r0, (byte[]) r1, (int) r2, (int) r3, (p000.ilm) r4, (p000.ihf) r5)
            r32 = r6
            r25 = r13
            r13 = r31
            r6 = r33
            goto L_0x064f
        L_0x0497:
            r0 = r7
            iiu r0 = (p000.iiu) r0
            r0.mo8785a()
            iim r15 = r0.f14321j
            imb r0 = r14.mo8712b()
            imb r1 = p000.imb.ENUM
            if (r0 == r1) goto L_0x0627
            imb r0 = r14.mo8712b()
            int r0 = r0.ordinal()
            switch(r0) {
                case 0: goto L_0x05ee;
                case 1: goto L_0x05d9;
                case 2: goto L_0x05c4;
                case 3: goto L_0x05c4;
                case 4: goto L_0x05af;
                case 5: goto L_0x059a;
                case 6: goto L_0x0584;
                case 7: goto L_0x0565;
                case 8: goto L_0x0555;
                case 9: goto L_0x052b;
                case 10: goto L_0x050d;
                case 11: goto L_0x04fc;
                case 12: goto L_0x05af;
                case 13: goto L_0x04f4;
                case 14: goto L_0x0584;
                case 15: goto L_0x059a;
                case 16: goto L_0x04d9;
                case 17: goto L_0x04be;
                default: goto L_0x04b2;
            }
        L_0x04b2:
            r32 = r6
            r25 = r13
            r13 = r31
            r6 = r33
            r0 = r17
            goto L_0x0602
        L_0x04be:
            r5 = r31
            int r2 = p000.ihg.m13045b(r5, r2, r12)
            long r0 = r12.f14179b
            long r0 = p000.ihz.m13260a((long) r0)
            java.lang.Long r17 = java.lang.Long.valueOf(r0)
            r32 = r6
            r25 = r13
            r0 = r17
            r6 = r33
            r13 = r5
            goto L_0x0602
        L_0x04d9:
            r5 = r31
            int r2 = p000.ihg.m13023a((byte[]) r5, (int) r2, (p000.ihf) r12)
            int r0 = r12.f14178a
            int r0 = p000.ihz.m13264e(r0)
            java.lang.Integer r17 = java.lang.Integer.valueOf(r0)
            r32 = r6
            r25 = r13
            r0 = r17
            r6 = r33
            r13 = r5
            goto L_0x0602
        L_0x04f4:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Shouldn't reach here."
            r0.<init>(r1)
            throw r0
        L_0x04fc:
            r5 = r31
            int r2 = p000.ihg.m13057e(r5, r2, r12)
            java.lang.Object r0 = r12.f14180c
            r32 = r6
            r25 = r13
            r6 = r33
            r13 = r5
            goto L_0x0602
        L_0x050d:
            r5 = r31
            ikp r0 = p000.ikp.f14397a
            ikf r1 = r14.f14243c
            java.lang.Class r1 = r1.getClass()
            iky r0 = r0.mo8875a((java.lang.Class) r1)
            r4 = r33
            int r2 = p000.ihg.m13021a((p000.iky) r0, (byte[]) r5, (int) r2, (int) r4, (p000.ihf) r12)
            java.lang.Object r0 = r12.f14180c
            r32 = r6
            r25 = r13
            r6 = r4
            r13 = r5
            goto L_0x0602
        L_0x052b:
            r5 = r31
            r4 = r33
            ikp r0 = p000.ikp.f14397a
            ikf r1 = r14.f14243c
            java.lang.Class r1 = r1.getClass()
            iky r0 = r0.mo8875a((java.lang.Class) r1)
            int r1 = r13 << 3
            r17 = r1 | 4
            r1 = r31
            r3 = r33
            r32 = r6
            r6 = r4
            r4 = r17
            r25 = r13
            r13 = r5
            r5 = r35
            int r2 = p000.ihg.m13020a((p000.iky) r0, (byte[]) r1, (int) r2, (int) r3, (int) r4, (p000.ihf) r5)
            java.lang.Object r0 = r12.f14180c
            goto L_0x0602
        L_0x0555:
            r32 = r6
            r25 = r13
            r13 = r31
            r6 = r33
            int r2 = p000.ihg.m13051c(r13, r2, r12)
            java.lang.Object r0 = r12.f14180c
            goto L_0x0602
        L_0x0565:
            r32 = r6
            r25 = r13
            r13 = r31
            r6 = r33
            int r2 = p000.ihg.m13045b(r13, r2, r12)
            long r0 = r12.f14179b
            int r3 = (r0 > r19 ? 1 : (r0 == r19 ? 0 : -1))
            if (r3 == 0) goto L_0x0578
            goto L_0x057b
        L_0x0578:
            r18 = 0
        L_0x057b:
            java.lang.Boolean r17 = java.lang.Boolean.valueOf(r18)
            r0 = r17
            goto L_0x0602
        L_0x0584:
            r32 = r6
            r25 = r13
            r13 = r31
            r6 = r33
            int r0 = p000.ihg.m13022a((byte[]) r13, (int) r2)
            java.lang.Integer r17 = java.lang.Integer.valueOf(r0)
            int r2 = r2 + 4
            r0 = r17
            goto L_0x0602
        L_0x059a:
            r32 = r6
            r25 = r13
            r13 = r31
            r6 = r33
            long r0 = p000.ihg.m13046b((byte[]) r13, (int) r2)
            java.lang.Long r17 = java.lang.Long.valueOf(r0)
            int r2 = r2 + 8
            r0 = r17
            goto L_0x0602
        L_0x05af:
            r32 = r6
            r25 = r13
            r13 = r31
            r6 = r33
            int r2 = p000.ihg.m13023a((byte[]) r13, (int) r2, (p000.ihf) r12)
            int r0 = r12.f14178a
            java.lang.Integer r17 = java.lang.Integer.valueOf(r0)
            r0 = r17
            goto L_0x0602
        L_0x05c4:
            r32 = r6
            r25 = r13
            r13 = r31
            r6 = r33
            int r2 = p000.ihg.m13045b(r13, r2, r12)
            long r0 = r12.f14179b
            java.lang.Long r17 = java.lang.Long.valueOf(r0)
            r0 = r17
            goto L_0x0602
        L_0x05d9:
            r32 = r6
            r25 = r13
            r13 = r31
            r6 = r33
            float r0 = p000.ihg.m13053d((byte[]) r13, (int) r2)
            java.lang.Float r17 = java.lang.Float.valueOf(r0)
            int r2 = r2 + 4
            r0 = r17
            goto L_0x0602
        L_0x05ee:
            r32 = r6
            r25 = r13
            r13 = r31
            r6 = r33
            double r0 = p000.ihg.m13048c((byte[]) r13, (int) r2)
            java.lang.Double r17 = java.lang.Double.valueOf(r0)
            int r2 = r2 + 8
            r0 = r17
        L_0x0602:
            imb r1 = r14.mo8712b()
            int r1 = r1.ordinal()
            r3 = 9
            if (r1 == r3) goto L_0x0613
            r3 = 10
            if (r1 == r3) goto L_0x0613
            goto L_0x0620
        L_0x0613:
            iiw r1 = r14.f14244d
            java.lang.Object r1 = r15.mo8728b((p000.iil) r1)
            if (r1 != 0) goto L_0x061c
            goto L_0x0620
        L_0x061c:
            java.lang.Object r0 = p000.ijf.m13647a((java.lang.Object) r1, (java.lang.Object) r0)
        L_0x0620:
            iiw r1 = r14.f14244d
            r15.mo8723a(r1, r0)
            r0 = r2
            goto L_0x064f
        L_0x0627:
            r13 = r31
            p000.ihg.m13023a((byte[]) r13, (int) r2, (p000.ihf) r12)
            throw r17
        L_0x062d:
            r13 = r31
            r32 = r6
            r25 = r26
            r6 = r33
            goto L_0x0640
        L_0x0636:
            r13 = r31
            r12 = r35
            r32 = r6
            r25 = r26
            r6 = r33
        L_0x0640:
            ilm r4 = m13782g((java.lang.Object) r30)
            r0 = r11
            r1 = r31
            r3 = r33
            r5 = r35
            int r0 = p000.ihg.m13016a((int) r0, (byte[]) r1, (int) r2, (int) r3, (p000.ilm) r4, (p000.ihf) r5)
        L_0x064f:
            r14 = r7
            r15 = r9
            r1 = r11
            r9 = r12
            r12 = r13
            r3 = r23
            r7 = r24
            r2 = r25
            r13 = r6
            r11 = r10
            r6 = r32
            r10 = r8
            goto L_0x0017
        L_0x0662:
            r21 = r6
            r24 = r7
            r8 = r10
            r10 = r11
            r6 = r13
            r7 = r14
            r9 = r15
            r2 = r21
            r3 = r24
        L_0x066f:
            r4 = -1
            if (r3 != r4) goto L_0x0673
            goto L_0x0677
        L_0x0673:
            long r3 = (long) r3
            r8.putInt(r7, r3, r2)
        L_0x0677:
            int r2 = r9.f14386l
            r3 = r17
        L_0x067b:
            int r4 = r9.f14387m
            if (r2 >= r4) goto L_0x068c
            int[] r4 = r9.f14385k
            r4 = r4[r2]
            java.lang.Object r3 = r9.m13750a((java.lang.Object) r7, (int) r4, (java.lang.Object) r3)
            ilm r3 = (p000.ilm) r3
            int r2 = r2 + 1
            goto L_0x067b
        L_0x068c:
            if (r3 == 0) goto L_0x0691
            p000.imu.m14139a((java.lang.Object) r7, (p000.ilm) r3)
        L_0x0691:
            if (r10 == 0) goto L_0x069d
            if (r0 > r6) goto L_0x0698
            if (r1 != r10) goto L_0x0698
            goto L_0x069f
        L_0x0698:
            ijh r0 = p000.ijh.m13661h()
            throw r0
        L_0x069d:
            if (r0 != r6) goto L_0x06a0
        L_0x069f:
            return r0
        L_0x06a0:
            ijh r0 = p000.ijh.m13661h()
            goto L_0x06a6
        L_0x06a5:
            throw r0
        L_0x06a6:
            goto L_0x06a5
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.ikh.mo8863a(java.lang.Object, byte[], int, int, int, ihf):int");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v2, resolved type: byte} */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0054, code lost:
        r10 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0056, code lost:
        r24 = r7;
        r3 = r8;
        r8 = r9;
        r7 = r14;
        r9 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0116, code lost:
        r10 = r4;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void m13768b(java.lang.Object r27, byte[] r28, int r29, int r30, p000.ihf r31) {
        /*
            r26 = this;
            r15 = r26
            r14 = r27
            r12 = r28
            r13 = r30
            r11 = r31
            sun.misc.Unsafe r9 = f14376b
            r10 = -1
            r16 = 0
            r0 = r29
            r1 = -1
            r2 = 0
        L_0x0013:
            if (r0 >= r13) goto L_0x02e8
            int r3 = r0 + 1
            byte r0 = r12[r0]
            if (r0 >= 0) goto L_0x0025
            int r0 = p000.ihg.m13017a((int) r0, (byte[]) r12, (int) r3, (p000.ihf) r11)
            int r3 = r11.f14178a
            r8 = r0
            r17 = r3
            goto L_0x0028
        L_0x0025:
            r17 = r0
            r8 = r3
        L_0x0028:
            int r7 = r17 >>> 3
            r6 = r17 & 7
            if (r7 <= r1) goto L_0x0036
            int r2 = r2 / 3
            int r0 = r15.m13742a((int) r7, (int) r2)
            r4 = r0
            goto L_0x003b
        L_0x0036:
            int r0 = r15.m13786j(r7)
            r4 = r0
        L_0x003b:
            if (r4 == r10) goto L_0x02bf
            int[] r0 = r15.f14377c
            int r1 = r4 + 1
            r5 = r0[r1]
            int r3 = m13781g((int) r5)
            long r1 = m13785i(r5)
            r0 = 17
            r10 = 2
            if (r3 > r0) goto L_0x018a
            r0 = 1
            switch(r3) {
                case 0: goto L_0x0178;
                case 1: goto L_0x0165;
                case 2: goto L_0x014f;
                case 3: goto L_0x014f;
                case 4: goto L_0x013d;
                case 5: goto L_0x0127;
                case 6: goto L_0x0112;
                case 7: goto L_0x00f6;
                case 8: goto L_0x00db;
                case 9: goto L_0x00b6;
                case 10: goto L_0x00a5;
                case 11: goto L_0x013d;
                case 12: goto L_0x0093;
                case 13: goto L_0x0112;
                case 14: goto L_0x0127;
                case 15: goto L_0x007e;
                case 16: goto L_0x0060;
                default: goto L_0x0054;
            }
        L_0x0054:
            r10 = r4
        L_0x0055:
        L_0x0056:
            r24 = r7
            r3 = r8
            r8 = r9
            r7 = r14
            r9 = r15
            r18 = -1
            goto L_0x02bd
        L_0x0060:
            if (r6 != 0) goto L_0x0054
            int r6 = p000.ihg.m13045b(r12, r8, r11)
            r19 = r1
            long r0 = r11.f14179b
            long r21 = p000.ihz.m13260a((long) r0)
            r0 = r9
            r2 = r19
            r1 = r27
            r10 = r4
            r4 = r21
            r0.putLong(r1, r2, r4)
            r0 = r6
            r1 = r7
            r2 = r10
            r10 = -1
            goto L_0x0013
        L_0x007e:
            r2 = r1
            r10 = r4
            if (r6 != 0) goto L_0x0055
            int r0 = p000.ihg.m13023a((byte[]) r12, (int) r8, (p000.ihf) r11)
            int r1 = r11.f14178a
            int r1 = p000.ihz.m13264e(r1)
            r9.putInt(r14, r2, r1)
            r1 = r7
            r2 = r10
            r10 = -1
            goto L_0x0013
        L_0x0093:
            r2 = r1
            r10 = r4
            if (r6 != 0) goto L_0x0055
            int r0 = p000.ihg.m13023a((byte[]) r12, (int) r8, (p000.ihf) r11)
            int r1 = r11.f14178a
            r9.putInt(r14, r2, r1)
            r1 = r7
            r2 = r10
            r10 = -1
            goto L_0x0013
        L_0x00a5:
            r2 = r1
            if (r6 != r10) goto L_0x0116
            int r0 = p000.ihg.m13057e(r12, r8, r11)
            java.lang.Object r1 = r11.f14180c
            r9.putObject(r14, r2, r1)
            r2 = r4
            r1 = r7
            r10 = -1
            goto L_0x0013
        L_0x00b6:
            r2 = r1
            if (r6 != r10) goto L_0x0116
            iky r0 = r15.m13749a((int) r4)
            int r0 = p000.ihg.m13021a((p000.iky) r0, (byte[]) r12, (int) r8, (int) r13, (p000.ihf) r11)
            java.lang.Object r1 = r9.getObject(r14, r2)
            if (r1 != 0) goto L_0x00cd
            java.lang.Object r1 = r11.f14180c
            r9.putObject(r14, r2, r1)
            goto L_0x00d6
        L_0x00cd:
            java.lang.Object r5 = r11.f14180c
            java.lang.Object r1 = p000.ijf.m13647a((java.lang.Object) r1, (java.lang.Object) r5)
            r9.putObject(r14, r2, r1)
        L_0x00d6:
            r2 = r4
            r1 = r7
            r10 = -1
            goto L_0x0013
        L_0x00db:
            r2 = r1
            if (r6 != r10) goto L_0x0116
            r0 = 536870912(0x20000000, float:1.0842022E-19)
            r0 = r0 & r5
            if (r0 == 0) goto L_0x00e8
            int r0 = p000.ihg.m13055d(r12, r8, r11)
            goto L_0x00ec
        L_0x00e8:
            int r0 = p000.ihg.m13051c(r12, r8, r11)
        L_0x00ec:
            java.lang.Object r1 = r11.f14180c
            r9.putObject(r14, r2, r1)
            r2 = r4
            r1 = r7
            r10 = -1
            goto L_0x0013
        L_0x00f6:
            r2 = r1
            if (r6 == 0) goto L_0x00fa
            goto L_0x0116
        L_0x00fa:
            int r1 = p000.ihg.m13045b(r12, r8, r11)
            long r5 = r11.f14179b
            r19 = 0
            int r8 = (r5 > r19 ? 1 : (r5 == r19 ? 0 : -1))
            if (r8 == 0) goto L_0x0107
            goto L_0x0109
        L_0x0107:
            r0 = 0
        L_0x0109:
            p000.ilv.m14037a((java.lang.Object) r14, (long) r2, (boolean) r0)
            r0 = r1
            r2 = r4
            r1 = r7
            r10 = -1
            goto L_0x0013
        L_0x0112:
            r2 = r1
            r0 = 5
            if (r6 == r0) goto L_0x0119
        L_0x0116:
            r10 = r4
            goto L_0x0056
        L_0x0119:
            int r0 = p000.ihg.m13022a((byte[]) r12, (int) r8)
            r9.putInt(r14, r2, r0)
            int r0 = r8 + 4
            r2 = r4
            r1 = r7
            r10 = -1
            goto L_0x0013
        L_0x0127:
            r2 = r1
            if (r6 != r0) goto L_0x0054
            long r5 = p000.ihg.m13046b((byte[]) r12, (int) r8)
            r0 = r9
            r1 = r27
            r10 = r4
            r4 = r5
            r0.putLong(r1, r2, r4)
            int r0 = r8 + 8
            r1 = r7
            r2 = r10
            r10 = -1
            goto L_0x0013
        L_0x013d:
            r2 = r1
            r10 = r4
            if (r6 != 0) goto L_0x0055
            int r0 = p000.ihg.m13023a((byte[]) r12, (int) r8, (p000.ihf) r11)
            int r1 = r11.f14178a
            r9.putInt(r14, r2, r1)
            r1 = r7
            r2 = r10
            r10 = -1
            goto L_0x0013
        L_0x014f:
            r2 = r1
            r10 = r4
            if (r6 != 0) goto L_0x0055
            int r6 = p000.ihg.m13045b(r12, r8, r11)
            long r4 = r11.f14179b
            r0 = r9
            r1 = r27
            r0.putLong(r1, r2, r4)
            r0 = r6
            r1 = r7
            r2 = r10
            r10 = -1
            goto L_0x0013
        L_0x0165:
            r2 = r1
            r10 = r4
            r0 = 5
            if (r6 != r0) goto L_0x0055
            float r0 = p000.ihg.m13053d((byte[]) r12, (int) r8)
            p000.ilv.m14033a((java.lang.Object) r14, (long) r2, (float) r0)
            int r0 = r8 + 4
            r1 = r7
            r2 = r10
            r10 = -1
            goto L_0x0013
        L_0x0178:
            r2 = r1
            r10 = r4
            if (r6 != r0) goto L_0x0055
            double r0 = p000.ihg.m13048c((byte[]) r12, (int) r8)
            p000.ilv.m14032a((java.lang.Object) r14, (long) r2, (double) r0)
            int r0 = r8 + 8
            r1 = r7
            r2 = r10
            r10 = -1
            goto L_0x0013
        L_0x018a:
            r0 = 27
            if (r3 == r0) goto L_0x025b
            r0 = 49
            if (r3 > r0) goto L_0x01df
            r19 = r9
            long r9 = (long) r5
            r0 = r26
            r20 = r1
            r1 = r27
            r2 = r28
            r5 = r3
            r3 = r8
            r29 = r4
            r4 = r30
            r22 = r5
            r5 = r17
            r23 = r6
            r6 = r7
            r24 = r7
            r7 = r23
            r15 = r8
            r8 = r29
            r25 = r19
            r18 = -1
            r11 = r22
            r12 = r20
            r14 = r31
            int r0 = r0.m13744a((java.lang.Object) r1, (byte[]) r2, (int) r3, (int) r4, (int) r5, (int) r6, (int) r7, (int) r8, (long) r9, (int) r11, (long) r12, (p000.ihf) r14)
            if (r0 == r15) goto L_0x01d4
            r15 = r26
            r14 = r27
            r12 = r28
            r2 = r29
            r13 = r30
            r11 = r31
            r1 = r24
            r9 = r25
            r10 = -1
            goto L_0x0013
        L_0x01d4:
            r9 = r26
            r7 = r27
            r10 = r29
            r2 = r0
            r8 = r25
            goto L_0x02c9
        L_0x01df:
            r20 = r1
            r22 = r3
            r29 = r4
            r23 = r6
            r24 = r7
            r15 = r8
            r25 = r9
            r18 = -1
            r0 = 50
            r9 = r22
            if (r9 == r0) goto L_0x0223
            r0 = r26
            r1 = r27
            r2 = r28
            r3 = r15
            r4 = r30
            r8 = r5
            r5 = r17
            r6 = r24
            r7 = r23
            r10 = r20
            r12 = r29
            r13 = r31
            int r0 = r0.m13743a((java.lang.Object) r1, (byte[]) r2, (int) r3, (int) r4, (int) r5, (int) r6, (int) r7, (int) r8, (int) r9, (long) r10, (int) r12, (p000.ihf) r13)
            if (r0 == r15) goto L_0x01d4
            r15 = r26
            r14 = r27
            r12 = r28
            r2 = r29
            r13 = r30
            r11 = r31
            r1 = r24
            r9 = r25
            r10 = -1
            goto L_0x0013
        L_0x0223:
            r0 = r23
            if (r0 != r10) goto L_0x0250
            r0 = r26
            r1 = r27
            r2 = r28
            r3 = r15
            r4 = r30
            r5 = r29
            r6 = r20
            r8 = r31
            int r0 = r0.m13745a(r1, r2, r3, r4, r5, r6, r8)
            if (r0 == r15) goto L_0x01d4
            r15 = r26
            r14 = r27
            r12 = r28
            r2 = r29
            r13 = r30
            r11 = r31
            r1 = r24
            r9 = r25
            r10 = -1
            goto L_0x0013
        L_0x0250:
            r9 = r26
            r7 = r27
            r10 = r29
            r3 = r15
            r8 = r25
            goto L_0x02bd
        L_0x025b:
            r20 = r1
            r29 = r4
            r0 = r6
            r24 = r7
            r15 = r8
            r25 = r9
            r18 = -1
            if (r0 != r10) goto L_0x02b4
            r7 = r27
            r0 = r20
            r8 = r25
            java.lang.Object r2 = r8.getObject(r7, r0)
            ije r2 = (p000.ije) r2
            boolean r3 = r2.mo8521a()
            if (r3 != 0) goto L_0x028e
            int r3 = r2.size()
            if (r3 == 0) goto L_0x0283
            int r3 = r3 + r3
            goto L_0x0286
        L_0x0283:
            r3 = 10
        L_0x0286:
            ije r2 = r2.mo8585a(r3)
            r8.putObject(r7, r0, r2)
            goto L_0x028f
        L_0x028e:
        L_0x028f:
            r5 = r2
            r9 = r26
            r10 = r29
            r3 = r15
            iky r0 = r9.m13749a((int) r10)
            r1 = r17
            r2 = r28
            r4 = r30
            r6 = r31
            int r0 = p000.ihg.m13019a(r0, r1, r2, r3, r4, r5, r6)
            r12 = r28
            r13 = r30
            r11 = r31
            r14 = r7
            r15 = r9
            r2 = r10
            r1 = r24
            r10 = -1
            r9 = r8
            goto L_0x0013
        L_0x02b4:
            r9 = r26
            r7 = r27
            r10 = r29
            r3 = r15
            r8 = r25
        L_0x02bd:
            r2 = r3
            goto L_0x02c9
        L_0x02bf:
            r24 = r7
            r3 = r8
            r8 = r9
            r7 = r14
            r9 = r15
            r18 = -1
            r2 = r3
            r10 = 0
        L_0x02c9:
            ilm r4 = m13782g((java.lang.Object) r27)
            r0 = r17
            r1 = r28
            r3 = r30
            r5 = r31
            int r0 = p000.ihg.m13016a((int) r0, (byte[]) r1, (int) r2, (int) r3, (p000.ilm) r4, (p000.ihf) r5)
            r12 = r28
            r13 = r30
            r11 = r31
            r14 = r7
            r15 = r9
            r2 = r10
            r1 = r24
            r10 = -1
            r9 = r8
            goto L_0x0013
        L_0x02e8:
            r9 = r15
            r1 = r30
            if (r0 != r1) goto L_0x02ee
            return
        L_0x02ee:
            ijh r0 = p000.ijh.m13661h()
            goto L_0x02f4
        L_0x02f3:
            throw r0
        L_0x02f4:
            goto L_0x02f3
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.ikh.m13768b(java.lang.Object, byte[], int, int, ihf):void");
    }

    /* renamed from: a */
    private final int m13744a(Object obj, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, long j2, ihf ihf) {
        boolean z;
        boolean z2;
        int i8;
        int i9;
        int i10;
        Object obj2 = obj;
        byte[] bArr2 = bArr;
        int i11 = i;
        int i12 = i2;
        int i13 = i3;
        int i14 = i5;
        int i15 = i6;
        long j3 = j2;
        ihf ihf2 = ihf;
        ije ije = (ije) f14376b.getObject(obj2, j3);
        if (!ije.mo8521a()) {
            int size = ije.size();
            if (size != 0) {
                i10 = size + size;
            } else {
                i10 = 10;
            }
            ije = ije.mo8585a(i10);
            f14376b.putObject(obj2, j3, ije);
        }
        switch (i7) {
            case 18:
            case 35:
                if (i14 == 2) {
                    iig iig = (iig) ije;
                    int a = ihg.m13023a(bArr2, i11, ihf2);
                    int i16 = ihf2.f14178a + a;
                    while (a < i16) {
                        iig.mo8706a(ihg.m13048c(bArr2, a));
                        a += 8;
                    }
                    if (a == i16) {
                        return a;
                    }
                    throw ijh.m13654a();
                } else if (i14 == 1) {
                    iig iig2 = (iig) ije;
                    iig2.mo8706a(ihg.m13048c(bArr, i));
                    int i17 = i11 + 8;
                    while (i17 < i12) {
                        int a2 = ihg.m13023a(bArr2, i17, ihf2);
                        if (i13 != ihf2.f14178a) {
                            return i17;
                        }
                        iig2.mo8706a(ihg.m13048c(bArr2, a2));
                        i17 = a2 + 8;
                    }
                    return i17;
                }
                break;
            case 19:
            case 36:
                if (i14 == 2) {
                    iio iio = (iio) ije;
                    int a3 = ihg.m13023a(bArr2, i11, ihf2);
                    int i18 = ihf2.f14178a + a3;
                    while (a3 < i18) {
                        iio.mo8736a(ihg.m13053d(bArr2, a3));
                        a3 += 4;
                    }
                    if (a3 == i18) {
                        return a3;
                    }
                    throw ijh.m13654a();
                } else if (i14 == 5) {
                    iio iio2 = (iio) ije;
                    iio2.mo8736a(ihg.m13053d(bArr, i));
                    int i19 = i11 + 4;
                    while (i19 < i12) {
                        int a4 = ihg.m13023a(bArr2, i19, ihf2);
                        if (i13 != ihf2.f14178a) {
                            return i19;
                        }
                        iio2.mo8736a(ihg.m13053d(bArr2, a4));
                        i19 = a4 + 4;
                    }
                    return i19;
                }
                break;
            case 20:
            case 21:
            case 37:
            case 38:
                if (i14 == 2) {
                    ijs ijs = (ijs) ije;
                    int a5 = ihg.m13023a(bArr2, i11, ihf2);
                    int i20 = ihf2.f14178a + a5;
                    while (a5 < i20) {
                        a5 = ihg.m13045b(bArr2, a5, ihf2);
                        ijs.mo8805a(ihf2.f14179b);
                    }
                    if (a5 == i20) {
                        return a5;
                    }
                    throw ijh.m13654a();
                } else if (i14 == 0) {
                    ijs ijs2 = (ijs) ije;
                    int b = ihg.m13045b(bArr2, i11, ihf2);
                    ijs2.mo8805a(ihf2.f14179b);
                    while (b < i12) {
                        int a6 = ihg.m13023a(bArr2, b, ihf2);
                        if (i13 != ihf2.f14178a) {
                            return b;
                        }
                        b = ihg.m13045b(bArr2, a6, ihf2);
                        ijs2.mo8805a(ihf2.f14179b);
                    }
                    return b;
                }
                break;
            case 22:
            case 29:
            case 39:
            case 43:
                if (i14 == 2) {
                    return ihg.m13024a(bArr2, i11, ije, ihf2);
                }
                if (i14 == 0) {
                    return ihg.m13015a(i3, bArr, i, i2, ije, ihf);
                }
                break;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i14 == 2) {
                    ijs ijs3 = (ijs) ije;
                    int a7 = ihg.m13023a(bArr2, i11, ihf2);
                    int i21 = ihf2.f14178a + a7;
                    while (a7 < i21) {
                        ijs3.mo8805a(ihg.m13046b(bArr2, a7));
                        a7 += 8;
                    }
                    if (a7 == i21) {
                        return a7;
                    }
                    throw ijh.m13654a();
                } else if (i14 == 1) {
                    ijs ijs4 = (ijs) ije;
                    ijs4.mo8805a(ihg.m13046b(bArr, i));
                    int i22 = i11 + 8;
                    while (i22 < i12) {
                        int a8 = ihg.m13023a(bArr2, i22, ihf2);
                        if (i13 != ihf2.f14178a) {
                            return i22;
                        }
                        ijs4.mo8805a(ihg.m13046b(bArr2, a8));
                        i22 = a8 + 8;
                    }
                    return i22;
                }
                break;
            case 24:
            case 31:
            case 41:
            case 45:
                if (i14 == 2) {
                    iiy iiy = (iiy) ije;
                    int a9 = ihg.m13023a(bArr2, i11, ihf2);
                    int i23 = ihf2.f14178a + a9;
                    while (a9 < i23) {
                        iiy.mo8801d(ihg.m13022a(bArr2, a9));
                        a9 += 4;
                    }
                    if (a9 == i23) {
                        return a9;
                    }
                    throw ijh.m13654a();
                } else if (i14 == 5) {
                    iiy iiy2 = (iiy) ije;
                    iiy2.mo8801d(ihg.m13022a(bArr, i));
                    int i24 = i11 + 4;
                    while (i24 < i12) {
                        int a10 = ihg.m13023a(bArr2, i24, ihf2);
                        if (i13 != ihf2.f14178a) {
                            return i24;
                        }
                        iiy2.mo8801d(ihg.m13022a(bArr2, a10));
                        i24 = a10 + 4;
                    }
                    return i24;
                }
                break;
            case 25:
            case 42:
                if (i14 == 2) {
                    ihj ihj = (ihj) ije;
                    int a11 = ihg.m13023a(bArr2, i11, ihf2);
                    int i25 = ihf2.f14178a + a11;
                    while (a11 < i25) {
                        a11 = ihg.m13045b(bArr2, a11, ihf2);
                        ihj.mo8586a(ihf2.f14179b != 0);
                    }
                    if (a11 == i25) {
                        return a11;
                    }
                    throw ijh.m13654a();
                } else if (i14 == 0) {
                    ihj ihj2 = (ihj) ije;
                    int b2 = ihg.m13045b(bArr2, i11, ihf2);
                    if (ihf2.f14179b != 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    ihj2.mo8586a(z);
                    while (b2 < i12) {
                        int a12 = ihg.m13023a(bArr2, b2, ihf2);
                        if (i13 != ihf2.f14178a) {
                            return b2;
                        }
                        b2 = ihg.m13045b(bArr2, a12, ihf2);
                        if (ihf2.f14179b != 0) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        ihj2.mo8586a(z2);
                    }
                    return b2;
                }
                break;
            case 26:
                if (i14 == 2) {
                    if ((j & 536870912) != 0) {
                        int a13 = ihg.m13023a(bArr2, i11, ihf2);
                        int i26 = ihf2.f14178a;
                        if (i26 >= 0) {
                            if (i26 == 0) {
                                ije.add("");
                            } else {
                                int i27 = a13 + i26;
                                if (ima.m14069a(bArr2, a13, i27)) {
                                    ije.add(new String(bArr2, a13, i26, ijf.f14336a));
                                    a13 = i27;
                                } else {
                                    throw ijh.m13662i();
                                }
                            }
                            while (i11 < i12) {
                                int a14 = ihg.m13023a(bArr2, i11, ihf2);
                                if (i13 != ihf2.f14178a) {
                                    break;
                                } else {
                                    i11 = ihg.m13023a(bArr2, a14, ihf2);
                                    int i28 = ihf2.f14178a;
                                    if (i28 < 0) {
                                        throw ijh.m13655b();
                                    } else if (i28 == 0) {
                                        ije.add("");
                                    } else {
                                        int i29 = i11 + i28;
                                        if (ima.m14069a(bArr2, i11, i29)) {
                                            ije.add(new String(bArr2, i11, i28, ijf.f14336a));
                                            i11 = i29;
                                        } else {
                                            throw ijh.m13662i();
                                        }
                                    }
                                }
                            }
                            break;
                        } else {
                            throw ijh.m13655b();
                        }
                    } else {
                        int a15 = ihg.m13023a(bArr2, i11, ihf2);
                        int i30 = ihf2.f14178a;
                        if (i30 >= 0) {
                            if (i30 != 0) {
                                ije.add(new String(bArr2, a15, i30, ijf.f14336a));
                                a15 += i30;
                            } else {
                                ije.add("");
                            }
                            while (i11 < i12) {
                                int a16 = ihg.m13023a(bArr2, i11, ihf2);
                                if (i13 != ihf2.f14178a) {
                                    break;
                                } else {
                                    int i31 = ihg.m13023a(bArr2, a16, ihf2);
                                    int i32 = ihf2.f14178a;
                                    if (i32 < 0) {
                                        throw ijh.m13655b();
                                    } else if (i32 != 0) {
                                        ije.add(new String(bArr2, i31, i32, ijf.f14336a));
                                        i31 += i32;
                                    } else {
                                        ije.add("");
                                    }
                                }
                            }
                            break;
                        } else {
                            throw ijh.m13655b();
                        }
                    }
                }
                break;
            case 27:
                if (i14 == 2) {
                    return ihg.m13019a(m13749a(i15), i3, bArr, i, i2, ije, ihf);
                }
                break;
            case 28:
                if (i14 == 2) {
                    int a17 = ihg.m13023a(bArr2, i11, ihf2);
                    int i33 = ihf2.f14178a;
                    if (i33 < 0) {
                        throw ijh.m13655b();
                    } else if (i33 <= bArr2.length - a17) {
                        if (i33 != 0) {
                            ije.add(ihw.m13163a(bArr2, a17, i33));
                            a17 += i33;
                        } else {
                            ije.add(ihw.f14203b);
                        }
                        while (i8 < i12) {
                            int a18 = ihg.m13023a(bArr2, i8, ihf2);
                            if (i13 != ihf2.f14178a) {
                                return i8;
                            }
                            i8 = ihg.m13023a(bArr2, a18, ihf2);
                            int i34 = ihf2.f14178a;
                            if (i34 < 0) {
                                throw ijh.m13655b();
                            } else if (i34 > bArr2.length - i8) {
                                throw ijh.m13654a();
                            } else if (i34 != 0) {
                                ije.add(ihw.m13163a(bArr2, i8, i34));
                                i8 += i34;
                            } else {
                                ije.add(ihw.f14203b);
                            }
                        }
                        return i8;
                    } else {
                        throw ijh.m13654a();
                    }
                }
                break;
            case 30:
            case 44:
                if (i14 == 2) {
                    i9 = ihg.m13024a(bArr2, i11, ije, ihf2);
                } else if (i14 == 0) {
                    i9 = ihg.m13015a(i3, bArr, i, i2, ije, ihf);
                }
                iix iix = (iix) obj2;
                ilm ilm = iix.f14326z;
                if (ilm == ilm.f14449a) {
                    ilm = null;
                }
                ilm ilm2 = (ilm) ila.m13912a(i4, (List) ije, m13770c(i15), (Object) ilm);
                if (ilm2 == null) {
                    return i9;
                }
                iix.f14326z = ilm2;
                return i9;
            case 33:
            case 47:
                if (i14 == 2) {
                    iiy iiy3 = (iiy) ije;
                    int a19 = ihg.m13023a(bArr2, i11, ihf2);
                    int i35 = ihf2.f14178a + a19;
                    while (a19 < i35) {
                        a19 = ihg.m13023a(bArr2, a19, ihf2);
                        iiy3.mo8801d(ihz.m13264e(ihf2.f14178a));
                    }
                    if (a19 == i35) {
                        return a19;
                    }
                    throw ijh.m13654a();
                } else if (i14 == 0) {
                    iiy iiy4 = (iiy) ije;
                    int a20 = ihg.m13023a(bArr2, i11, ihf2);
                    iiy4.mo8801d(ihz.m13264e(ihf2.f14178a));
                    while (a20 < i12) {
                        int a21 = ihg.m13023a(bArr2, a20, ihf2);
                        if (i13 != ihf2.f14178a) {
                            return a20;
                        }
                        a20 = ihg.m13023a(bArr2, a21, ihf2);
                        iiy4.mo8801d(ihz.m13264e(ihf2.f14178a));
                    }
                    return a20;
                }
                break;
            case 34:
            case 48:
                if (i14 == 2) {
                    ijs ijs5 = (ijs) ije;
                    int a22 = ihg.m13023a(bArr2, i11, ihf2);
                    int i36 = ihf2.f14178a + a22;
                    while (a22 < i36) {
                        a22 = ihg.m13045b(bArr2, a22, ihf2);
                        ijs5.mo8805a(ihz.m13260a(ihf2.f14179b));
                    }
                    if (a22 == i36) {
                        return a22;
                    }
                    throw ijh.m13654a();
                } else if (i14 == 0) {
                    ijs ijs6 = (ijs) ije;
                    int b3 = ihg.m13045b(bArr2, i11, ihf2);
                    ijs6.mo8805a(ihz.m13260a(ihf2.f14179b));
                    while (b3 < i12) {
                        int a23 = ihg.m13023a(bArr2, b3, ihf2);
                        if (i13 != ihf2.f14178a) {
                            return b3;
                        }
                        b3 = ihg.m13045b(bArr2, a23, ihf2);
                        ijs6.mo8805a(ihz.m13260a(ihf2.f14179b));
                    }
                    return b3;
                }
                break;
            default:
                if (i14 == 3) {
                    iky a24 = m13749a(i15);
                    int i37 = (i13 & -8) | 4;
                    int a25 = ihg.m13020a(a24, bArr, i, i2, i37, ihf);
                    ije.add(ihf2.f14180c);
                    while (a25 < i12) {
                        int a26 = ihg.m13023a(bArr2, a25, ihf2);
                        if (i13 != ihf2.f14178a) {
                            return a25;
                        }
                        a25 = ihg.m13020a(a24, bArr, a26, i2, i37, ihf);
                        ije.add(ihf2.f14180c);
                    }
                    return a25;
                }
                break;
        }
        return i11;
    }

    /* renamed from: j */
    private final int m13786j(int i) {
        if (i < this.f14379e || i > this.f14380f) {
            return -1;
        }
        return m13762b(i, 0);
    }

    /* renamed from: a */
    private final int m13742a(int i, int i2) {
        if (i < this.f14379e || i > this.f14380f) {
            return -1;
        }
        return m13762b(i, i2);
    }

    /* renamed from: f */
    private final int m13778f(int i) {
        return this.f14377c[i + 2];
    }

    /* renamed from: a */
    private final void m13755a(Object obj, int i, iks iks) {
        if (m13784h(i)) {
            ilv.m14036a(obj, m13785i(i), (Object) iks.mo8572m());
        } else if (!this.f14383i) {
            ilv.m14036a(obj, m13785i(i), (Object) iks.mo8574n());
        } else {
            ilv.m14036a(obj, m13785i(i), (Object) iks.mo8570l());
        }
    }

    /* renamed from: a */
    private static Field m13751a(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException e) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String arrays = Arrays.toString(declaredFields);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 40 + String.valueOf(name).length() + String.valueOf(arrays).length());
            sb.append("Field ");
            sb.append(str);
            sb.append(" for ");
            sb.append(name);
            sb.append(" not found. Known fields are ");
            sb.append(arrays);
            throw new RuntimeException(sb.toString());
        }
    }

    /* renamed from: b */
    private final void m13764b(Object obj, int i) {
        if (!this.f14384j) {
            int f = m13778f(i);
            long j = (long) (1048575 & f);
            ilv.m14034a(obj, j, (1 << (f >>> 20)) | ilv.m14027a(obj, j));
        }
    }

    /* renamed from: b */
    private final void m13765b(Object obj, int i, int i2) {
        ilv.m14034a(obj, (long) (m13778f(i2) & 1048575), i);
    }

    /* renamed from: b */
    private final int m13762b(int i, int i2) {
        int length = (this.f14377c.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int d = m13773d(i4);
            if (i == d) {
                return i4;
            }
            if (i < d) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }

    /* renamed from: e */
    private final int m13775e(int i) {
        return this.f14377c[i + 1];
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: java.util.Map$Entry} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x01eb  */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x01f5  */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x0209  */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x021a  */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x022b  */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x023c  */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x024d  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x025e  */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x026f  */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x0280  */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x0291  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x02a2  */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x02b3  */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x02c4  */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x02d5  */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x02e6  */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x02f7  */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x0307  */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x0317  */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x0327  */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x0337  */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x0347  */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x0357  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x0367  */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x037b  */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x038b  */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x039b  */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x03ab  */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x03bb  */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x03cb  */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x03db  */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x03eb  */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x03fb  */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x040b  */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x041c  */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x0429  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x0436  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x0443  */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x0450  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x045d  */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x046a  */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x0479  */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x048a  */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x0496  */
    /* JADX WARNING: Removed duplicated region for block: B:167:0x04a2  */
    /* JADX WARNING: Removed duplicated region for block: B:170:0x04ae  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x04ba  */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x04c6  */
    /* JADX WARNING: Removed duplicated region for block: B:179:0x04d2  */
    /* JADX WARNING: Removed duplicated region for block: B:182:0x04de  */
    /* JADX WARNING: Removed duplicated region for block: B:185:0x04ea  */
    /* JADX WARNING: Removed duplicated region for block: B:191:0x0500  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00a2  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00db  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00ee  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0101  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0114  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0129  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0140  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0153  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0166  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0179  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x018c  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x019f  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x01b2  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002c  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x01c5  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x01d8  */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void m13766b(java.lang.Object r18, p000.ime r19) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            boolean r3 = r0.f14382h
            if (r3 == 0) goto L_0x0020
            iim r3 = p000.imi.m14098a((java.lang.Object) r18)
            boolean r5 = r3.mo8725a()
            if (r5 != 0) goto L_0x001f
            java.util.Iterator r3 = r3.mo8732d()
            java.lang.Object r5 = r3.next()
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            goto L_0x0022
        L_0x001f:
        L_0x0020:
            r3 = 0
            r5 = 0
        L_0x0022:
            int[] r6 = r0.f14377c
            int r6 = r6.length
            sun.misc.Unsafe r7 = f14376b
            r8 = -1
            r10 = 0
            r11 = 0
        L_0x002a:
            if (r10 >= r6) goto L_0x04fc
            int r12 = r0.m13775e((int) r10)
            int r13 = r0.m13773d((int) r10)
            int r14 = m13781g((int) r12)
            boolean r15 = r0.f14384j
            if (r15 == 0) goto L_0x003e
        L_0x003c:
            r4 = 0
            goto L_0x0065
        L_0x003e:
            r15 = 17
            if (r14 > r15) goto L_0x0062
            int[] r15 = r0.f14377c
            int r16 = r10 + 2
            r15 = r15[r16]
            r16 = 1048575(0xfffff, float:1.469367E-39)
            r9 = r15 & r16
            if (r9 == r8) goto L_0x0058
            r16 = r5
            long r4 = (long) r9
            int r11 = r7.getInt(r1, r4)
            r8 = r9
            goto L_0x005a
        L_0x0058:
            r16 = r5
        L_0x005a:
            int r4 = r15 >>> 20
            r5 = 1
            int r4 = r5 << r4
            r5 = r16
            goto L_0x0065
        L_0x0062:
            r16 = r5
            goto L_0x003c
        L_0x0065:
            if (r5 == 0) goto L_0x007f
            int r9 = p000.imi.m14097a((java.util.Map.Entry) r5)
            if (r9 > r13) goto L_0x007f
            p000.imi.m14111a(r2, r5)
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L_0x007d
            java.lang.Object r5 = r3.next()
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            goto L_0x0065
        L_0x007d:
            r5 = 0
            goto L_0x0065
        L_0x007f:
            r15 = r5
            r9 = r6
            long r5 = m13785i(r12)
            switch(r14) {
                case 0: goto L_0x04ea;
                case 1: goto L_0x04de;
                case 2: goto L_0x04d2;
                case 3: goto L_0x04c6;
                case 4: goto L_0x04ba;
                case 5: goto L_0x04ae;
                case 6: goto L_0x04a2;
                case 7: goto L_0x0496;
                case 8: goto L_0x048a;
                case 9: goto L_0x0479;
                case 10: goto L_0x046a;
                case 11: goto L_0x045d;
                case 12: goto L_0x0450;
                case 13: goto L_0x0443;
                case 14: goto L_0x0436;
                case 15: goto L_0x0429;
                case 16: goto L_0x041c;
                case 17: goto L_0x040b;
                case 18: goto L_0x03fb;
                case 19: goto L_0x03eb;
                case 20: goto L_0x03db;
                case 21: goto L_0x03cb;
                case 22: goto L_0x03bb;
                case 23: goto L_0x03ab;
                case 24: goto L_0x039b;
                case 25: goto L_0x038b;
                case 26: goto L_0x037b;
                case 27: goto L_0x0367;
                case 28: goto L_0x0357;
                case 29: goto L_0x0347;
                case 30: goto L_0x0337;
                case 31: goto L_0x0327;
                case 32: goto L_0x0317;
                case 33: goto L_0x0307;
                case 34: goto L_0x02f7;
                case 35: goto L_0x02e6;
                case 36: goto L_0x02d5;
                case 37: goto L_0x02c4;
                case 38: goto L_0x02b3;
                case 39: goto L_0x02a2;
                case 40: goto L_0x0291;
                case 41: goto L_0x0280;
                case 42: goto L_0x026f;
                case 43: goto L_0x025e;
                case 44: goto L_0x024d;
                case 45: goto L_0x023c;
                case 46: goto L_0x022b;
                case 47: goto L_0x021a;
                case 48: goto L_0x0209;
                case 49: goto L_0x01f5;
                case 50: goto L_0x01eb;
                case 51: goto L_0x01d8;
                case 52: goto L_0x01c5;
                case 53: goto L_0x01b2;
                case 54: goto L_0x019f;
                case 55: goto L_0x018c;
                case 56: goto L_0x0179;
                case 57: goto L_0x0166;
                case 58: goto L_0x0153;
                case 59: goto L_0x0140;
                case 60: goto L_0x0129;
                case 61: goto L_0x0114;
                case 62: goto L_0x0101;
                case 63: goto L_0x00ee;
                case 64: goto L_0x00db;
                case 65: goto L_0x00c8;
                case 66: goto L_0x00b5;
                case 67: goto L_0x00a2;
                case 68: goto L_0x008b;
                default: goto L_0x0088;
            }
        L_0x0088:
            r12 = 0
            goto L_0x04f5
        L_0x008b:
            boolean r4 = r0.m13758a((java.lang.Object) r1, (int) r13, (int) r10)
            if (r4 == 0) goto L_0x009f
            java.lang.Object r4 = r7.getObject(r1, r5)
            iky r5 = r0.m13749a((int) r10)
            r2.mo8698b(r13, r4, r5)
            r12 = 0
            goto L_0x04f5
        L_0x009f:
            r12 = 0
            goto L_0x04f5
        L_0x00a2:
            boolean r4 = r0.m13758a((java.lang.Object) r1, (int) r13, (int) r10)
            if (r4 == 0) goto L_0x00b2
            long r4 = m13777e(r1, r5)
            r2.mo8704e((int) r13, (long) r4)
            r12 = 0
            goto L_0x04f5
        L_0x00b2:
            r12 = 0
            goto L_0x04f5
        L_0x00b5:
            boolean r4 = r0.m13758a((java.lang.Object) r1, (int) r13, (int) r10)
            if (r4 == 0) goto L_0x00c5
            int r4 = m13774d(r1, r5)
            r2.mo8705f(r13, r4)
            r12 = 0
            goto L_0x04f5
        L_0x00c5:
            r12 = 0
            goto L_0x04f5
        L_0x00c8:
            boolean r4 = r0.m13758a((java.lang.Object) r1, (int) r13, (int) r10)
            if (r4 == 0) goto L_0x00d8
            long r4 = m13777e(r1, r5)
            r2.mo8697b((int) r13, (long) r4)
            r12 = 0
            goto L_0x04f5
        L_0x00d8:
            r12 = 0
            goto L_0x04f5
        L_0x00db:
            boolean r4 = r0.m13758a((java.lang.Object) r1, (int) r13, (int) r10)
            if (r4 == 0) goto L_0x00eb
            int r4 = m13774d(r1, r5)
            r2.mo8689a((int) r13, (int) r4)
            r12 = 0
            goto L_0x04f5
        L_0x00eb:
            r12 = 0
            goto L_0x04f5
        L_0x00ee:
            boolean r4 = r0.m13758a((java.lang.Object) r1, (int) r13, (int) r10)
            if (r4 == 0) goto L_0x00fe
            int r4 = m13774d(r1, r5)
            r2.mo8696b((int) r13, (int) r4)
            r12 = 0
            goto L_0x04f5
        L_0x00fe:
            r12 = 0
            goto L_0x04f5
        L_0x0101:
            boolean r4 = r0.m13758a((java.lang.Object) r1, (int) r13, (int) r10)
            if (r4 == 0) goto L_0x0111
            int r4 = m13774d(r1, r5)
            r2.mo8703e((int) r13, (int) r4)
            r12 = 0
            goto L_0x04f5
        L_0x0111:
            r12 = 0
            goto L_0x04f5
        L_0x0114:
            boolean r4 = r0.m13758a((java.lang.Object) r1, (int) r13, (int) r10)
            if (r4 == 0) goto L_0x0126
            java.lang.Object r4 = r7.getObject(r1, r5)
            ihw r4 = (p000.ihw) r4
            r2.mo8691a((int) r13, (p000.ihw) r4)
            r12 = 0
            goto L_0x04f5
        L_0x0126:
            r12 = 0
            goto L_0x04f5
        L_0x0129:
            boolean r4 = r0.m13758a((java.lang.Object) r1, (int) r13, (int) r10)
            if (r4 == 0) goto L_0x013d
            java.lang.Object r4 = r7.getObject(r1, r5)
            iky r5 = r0.m13749a((int) r10)
            r2.mo8693a(r13, r4, r5)
            r12 = 0
            goto L_0x04f5
        L_0x013d:
            r12 = 0
            goto L_0x04f5
        L_0x0140:
            boolean r4 = r0.m13758a((java.lang.Object) r1, (int) r13, (int) r10)
            if (r4 == 0) goto L_0x0150
            java.lang.Object r4 = r7.getObject(r1, r5)
            m13753a((int) r13, (java.lang.Object) r4, (p000.ime) r2)
            r12 = 0
            goto L_0x04f5
        L_0x0150:
            r12 = 0
            goto L_0x04f5
        L_0x0153:
            boolean r4 = r0.m13758a((java.lang.Object) r1, (int) r13, (int) r10)
            if (r4 == 0) goto L_0x0163
            boolean r4 = m13780f(r1, r5)
            r2.mo8695a((int) r13, (boolean) r4)
            r12 = 0
            goto L_0x04f5
        L_0x0163:
            r12 = 0
            goto L_0x04f5
        L_0x0166:
            boolean r4 = r0.m13758a((java.lang.Object) r1, (int) r13, (int) r10)
            if (r4 == 0) goto L_0x0176
            int r4 = m13774d(r1, r5)
            r2.mo8701d((int) r13, (int) r4)
            r12 = 0
            goto L_0x04f5
        L_0x0176:
            r12 = 0
            goto L_0x04f5
        L_0x0179:
            boolean r4 = r0.m13758a((java.lang.Object) r1, (int) r13, (int) r10)
            if (r4 == 0) goto L_0x0189
            long r4 = m13777e(r1, r5)
            r2.mo8702d((int) r13, (long) r4)
            r12 = 0
            goto L_0x04f5
        L_0x0189:
            r12 = 0
            goto L_0x04f5
        L_0x018c:
            boolean r4 = r0.m13758a((java.lang.Object) r1, (int) r13, (int) r10)
            if (r4 == 0) goto L_0x019c
            int r4 = m13774d(r1, r5)
            r2.mo8699c((int) r13, (int) r4)
            r12 = 0
            goto L_0x04f5
        L_0x019c:
            r12 = 0
            goto L_0x04f5
        L_0x019f:
            boolean r4 = r0.m13758a((java.lang.Object) r1, (int) r13, (int) r10)
            if (r4 == 0) goto L_0x01af
            long r4 = m13777e(r1, r5)
            r2.mo8700c((int) r13, (long) r4)
            r12 = 0
            goto L_0x04f5
        L_0x01af:
            r12 = 0
            goto L_0x04f5
        L_0x01b2:
            boolean r4 = r0.m13758a((java.lang.Object) r1, (int) r13, (int) r10)
            if (r4 == 0) goto L_0x01c2
            long r4 = m13777e(r1, r5)
            r2.mo8690a((int) r13, (long) r4)
            r12 = 0
            goto L_0x04f5
        L_0x01c2:
            r12 = 0
            goto L_0x04f5
        L_0x01c5:
            boolean r4 = r0.m13758a((java.lang.Object) r1, (int) r13, (int) r10)
            if (r4 == 0) goto L_0x01d5
            float r4 = m13769c((java.lang.Object) r1, (long) r5)
            r2.mo8688a((int) r13, (float) r4)
            r12 = 0
            goto L_0x04f5
        L_0x01d5:
            r12 = 0
            goto L_0x04f5
        L_0x01d8:
            boolean r4 = r0.m13758a((java.lang.Object) r1, (int) r13, (int) r10)
            if (r4 == 0) goto L_0x01e8
            double r4 = m13761b((java.lang.Object) r1, (long) r5)
            r2.mo8687a((int) r13, (double) r4)
            r12 = 0
            goto L_0x04f5
        L_0x01e8:
            r12 = 0
            goto L_0x04f5
        L_0x01eb:
            java.lang.Object r4 = r7.getObject(r1, r5)
            r0.m13754a((p000.ime) r2, (int) r13, (java.lang.Object) r4, (int) r10)
            r12 = 0
            goto L_0x04f5
        L_0x01f5:
            int r4 = r0.m13773d((int) r10)
            java.lang.Object r5 = r7.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            iky r6 = r0.m13749a((int) r10)
            p000.ila.m13923b((int) r4, (java.util.List) r5, (p000.ime) r2, (p000.iky) r6)
            r12 = 0
            goto L_0x04f5
        L_0x0209:
            int r4 = r0.m13773d((int) r10)
            java.lang.Object r5 = r7.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            r12 = 1
            p000.ila.m13935e(r4, r5, r2, r12)
            r12 = 0
            goto L_0x04f5
        L_0x021a:
            r12 = 1
            int r4 = r0.m13773d((int) r10)
            java.lang.Object r5 = r7.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            p000.ila.m13950j(r4, r5, r2, r12)
            r12 = 0
            goto L_0x04f5
        L_0x022b:
            r12 = 1
            int r4 = r0.m13773d((int) r10)
            java.lang.Object r5 = r7.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            p000.ila.m13941g(r4, r5, r2, r12)
            r12 = 0
            goto L_0x04f5
        L_0x023c:
            r12 = 1
            int r4 = r0.m13773d((int) r10)
            java.lang.Object r5 = r7.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            p000.ila.m13954l(r4, r5, r2, r12)
            r12 = 0
            goto L_0x04f5
        L_0x024d:
            r12 = 1
            int r4 = r0.m13773d((int) r10)
            java.lang.Object r5 = r7.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            p000.ila.m13955m(r4, r5, r2, r12)
            r12 = 0
            goto L_0x04f5
        L_0x025e:
            r12 = 1
            int r4 = r0.m13773d((int) r10)
            java.lang.Object r5 = r7.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            p000.ila.m13947i(r4, r5, r2, r12)
            r12 = 0
            goto L_0x04f5
        L_0x026f:
            r12 = 1
            int r4 = r0.m13773d((int) r10)
            java.lang.Object r5 = r7.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            p000.ila.m13956n(r4, r5, r2, r12)
            r12 = 0
            goto L_0x04f5
        L_0x0280:
            r12 = 1
            int r4 = r0.m13773d((int) r10)
            java.lang.Object r5 = r7.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            p000.ila.m13952k(r4, r5, r2, r12)
            r12 = 0
            goto L_0x04f5
        L_0x0291:
            r12 = 1
            int r4 = r0.m13773d((int) r10)
            java.lang.Object r5 = r7.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            p000.ila.m13938f(r4, r5, r2, r12)
            r12 = 0
            goto L_0x04f5
        L_0x02a2:
            r12 = 1
            int r4 = r0.m13773d((int) r10)
            java.lang.Object r5 = r7.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            p000.ila.m13944h(r4, r5, r2, r12)
            r12 = 0
            goto L_0x04f5
        L_0x02b3:
            r12 = 1
            int r4 = r0.m13773d((int) r10)
            java.lang.Object r5 = r7.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            p000.ila.m13932d(r4, r5, r2, r12)
            r12 = 0
            goto L_0x04f5
        L_0x02c4:
            r12 = 1
            int r4 = r0.m13773d((int) r10)
            java.lang.Object r5 = r7.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            p000.ila.m13928c(r4, r5, r2, r12)
            r12 = 0
            goto L_0x04f5
        L_0x02d5:
            r12 = 1
            int r4 = r0.m13773d((int) r10)
            java.lang.Object r5 = r7.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            p000.ila.m13924b((int) r4, (java.util.List) r5, (p000.ime) r2, (boolean) r12)
            r12 = 0
            goto L_0x04f5
        L_0x02e6:
            r12 = 1
            int r4 = r0.m13773d((int) r10)
            java.lang.Object r5 = r7.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            p000.ila.m13915a((int) r4, (java.util.List) r5, (p000.ime) r2, (boolean) r12)
            r12 = 0
            goto L_0x04f5
        L_0x02f7:
            int r4 = r0.m13773d((int) r10)
            java.lang.Object r5 = r7.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            r12 = 0
            p000.ila.m13935e(r4, r5, r2, r12)
            goto L_0x04f5
        L_0x0307:
            r12 = 0
            int r4 = r0.m13773d((int) r10)
            java.lang.Object r5 = r7.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            p000.ila.m13950j(r4, r5, r2, r12)
            goto L_0x04f5
        L_0x0317:
            r12 = 0
            int r4 = r0.m13773d((int) r10)
            java.lang.Object r5 = r7.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            p000.ila.m13941g(r4, r5, r2, r12)
            goto L_0x04f5
        L_0x0327:
            r12 = 0
            int r4 = r0.m13773d((int) r10)
            java.lang.Object r5 = r7.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            p000.ila.m13954l(r4, r5, r2, r12)
            goto L_0x04f5
        L_0x0337:
            r12 = 0
            int r4 = r0.m13773d((int) r10)
            java.lang.Object r5 = r7.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            p000.ila.m13955m(r4, r5, r2, r12)
            goto L_0x04f5
        L_0x0347:
            r12 = 0
            int r4 = r0.m13773d((int) r10)
            java.lang.Object r5 = r7.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            p000.ila.m13947i(r4, r5, r2, r12)
            goto L_0x04f5
        L_0x0357:
            int r4 = r0.m13773d((int) r10)
            java.lang.Object r5 = r7.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            p000.ila.m13922b((int) r4, (java.util.List) r5, (p000.ime) r2)
            r12 = 0
            goto L_0x04f5
        L_0x0367:
            int r4 = r0.m13773d((int) r10)
            java.lang.Object r5 = r7.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            iky r6 = r0.m13749a((int) r10)
            p000.ila.m13914a((int) r4, (java.util.List) r5, (p000.ime) r2, (p000.iky) r6)
            r12 = 0
            goto L_0x04f5
        L_0x037b:
            int r4 = r0.m13773d((int) r10)
            java.lang.Object r5 = r7.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            p000.ila.m13913a((int) r4, (java.util.List) r5, (p000.ime) r2)
            r12 = 0
            goto L_0x04f5
        L_0x038b:
            int r4 = r0.m13773d((int) r10)
            java.lang.Object r5 = r7.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            r12 = 0
            p000.ila.m13956n(r4, r5, r2, r12)
            goto L_0x04f5
        L_0x039b:
            r12 = 0
            int r4 = r0.m13773d((int) r10)
            java.lang.Object r5 = r7.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            p000.ila.m13952k(r4, r5, r2, r12)
            goto L_0x04f5
        L_0x03ab:
            r12 = 0
            int r4 = r0.m13773d((int) r10)
            java.lang.Object r5 = r7.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            p000.ila.m13938f(r4, r5, r2, r12)
            goto L_0x04f5
        L_0x03bb:
            r12 = 0
            int r4 = r0.m13773d((int) r10)
            java.lang.Object r5 = r7.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            p000.ila.m13944h(r4, r5, r2, r12)
            goto L_0x04f5
        L_0x03cb:
            r12 = 0
            int r4 = r0.m13773d((int) r10)
            java.lang.Object r5 = r7.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            p000.ila.m13932d(r4, r5, r2, r12)
            goto L_0x04f5
        L_0x03db:
            r12 = 0
            int r4 = r0.m13773d((int) r10)
            java.lang.Object r5 = r7.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            p000.ila.m13928c(r4, r5, r2, r12)
            goto L_0x04f5
        L_0x03eb:
            r12 = 0
            int r4 = r0.m13773d((int) r10)
            java.lang.Object r5 = r7.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            p000.ila.m13924b((int) r4, (java.util.List) r5, (p000.ime) r2, (boolean) r12)
            goto L_0x04f5
        L_0x03fb:
            r12 = 0
            int r4 = r0.m13773d((int) r10)
            java.lang.Object r5 = r7.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            p000.ila.m13915a((int) r4, (java.util.List) r5, (p000.ime) r2, (boolean) r12)
            goto L_0x04f5
        L_0x040b:
            r12 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x04f5
            java.lang.Object r4 = r7.getObject(r1, r5)
            iky r5 = r0.m13749a((int) r10)
            r2.mo8698b(r13, r4, r5)
            goto L_0x04f5
        L_0x041c:
            r12 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x04f5
            long r4 = r7.getLong(r1, r5)
            r2.mo8704e((int) r13, (long) r4)
            goto L_0x04f5
        L_0x0429:
            r12 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x04f5
            int r4 = r7.getInt(r1, r5)
            r2.mo8705f(r13, r4)
            goto L_0x04f5
        L_0x0436:
            r12 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x04f5
            long r4 = r7.getLong(r1, r5)
            r2.mo8697b((int) r13, (long) r4)
            goto L_0x04f5
        L_0x0443:
            r12 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x04f5
            int r4 = r7.getInt(r1, r5)
            r2.mo8689a((int) r13, (int) r4)
            goto L_0x04f5
        L_0x0450:
            r12 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x04f5
            int r4 = r7.getInt(r1, r5)
            r2.mo8696b((int) r13, (int) r4)
            goto L_0x04f5
        L_0x045d:
            r12 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x04f5
            int r4 = r7.getInt(r1, r5)
            r2.mo8703e((int) r13, (int) r4)
            goto L_0x04f5
        L_0x046a:
            r12 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x04f5
            java.lang.Object r4 = r7.getObject(r1, r5)
            ihw r4 = (p000.ihw) r4
            r2.mo8691a((int) r13, (p000.ihw) r4)
            goto L_0x04f5
        L_0x0479:
            r12 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x04f5
            java.lang.Object r4 = r7.getObject(r1, r5)
            iky r5 = r0.m13749a((int) r10)
            r2.mo8693a(r13, r4, r5)
            goto L_0x04f5
        L_0x048a:
            r12 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x04f5
            java.lang.Object r4 = r7.getObject(r1, r5)
            m13753a((int) r13, (java.lang.Object) r4, (p000.ime) r2)
            goto L_0x04f5
        L_0x0496:
            r12 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x04f5
            boolean r4 = p000.ilv.m14044c(r1, r5)
            r2.mo8695a((int) r13, (boolean) r4)
            goto L_0x04f5
        L_0x04a2:
            r12 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x04f5
            int r4 = r7.getInt(r1, r5)
            r2.mo8701d((int) r13, (int) r4)
            goto L_0x04f5
        L_0x04ae:
            r12 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x04f5
            long r4 = r7.getLong(r1, r5)
            r2.mo8702d((int) r13, (long) r4)
            goto L_0x04f5
        L_0x04ba:
            r12 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x04f5
            int r4 = r7.getInt(r1, r5)
            r2.mo8699c((int) r13, (int) r4)
            goto L_0x04f5
        L_0x04c6:
            r12 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x04f5
            long r4 = r7.getLong(r1, r5)
            r2.mo8700c((int) r13, (long) r4)
            goto L_0x04f5
        L_0x04d2:
            r12 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x04f5
            long r4 = r7.getLong(r1, r5)
            r2.mo8690a((int) r13, (long) r4)
            goto L_0x04f5
        L_0x04de:
            r12 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x04f5
            float r4 = p000.ilv.m14045d(r1, r5)
            r2.mo8688a((int) r13, (float) r4)
            goto L_0x04f5
        L_0x04ea:
            r12 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x04f5
            double r4 = p000.ilv.m14047e(r1, r5)
            r2.mo8687a((int) r13, (double) r4)
        L_0x04f5:
            int r10 = r10 + 3
            r6 = r9
            r5 = r15
            goto L_0x002a
        L_0x04fc:
            r16 = r5
        L_0x04fe:
            if (r5 == 0) goto L_0x0513
            p000.imi.m14111a(r2, r5)
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0511
            java.lang.Object r4 = r3.next()
            r5 = r4
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            goto L_0x04fe
        L_0x0511:
            r5 = 0
            goto L_0x04fe
        L_0x0513:
            m13771c((java.lang.Object) r18, (p000.ime) r19)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.ikh.m13766b(java.lang.Object, ime):void");
    }

    /* renamed from: a */
    private final void m13754a(ime ime, int i, Object obj, int i2) {
        if (obj != null) {
            ijw e = this.f14390p.mo8858e(m13763b(i2));
            for (Map.Entry entry : this.f14390p.mo8855b(obj).entrySet()) {
                iif iif = (iif) ime;
                iif.f14238a.mo8655a(i, 2);
                iif.f14238a.mo8668b(ijx.m13699a(e, entry.getKey(), entry.getValue()));
                ijx.m13701a(iif.f14238a, e, entry.getKey(), entry.getValue());
            }
        }
    }

    /* renamed from: a */
    private static final void m13753a(int i, Object obj, ime ime) {
        if (obj instanceof String) {
            ime.mo8694a(i, (String) obj);
        } else {
            ime.mo8691a(i, (ihw) obj);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x002b  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0041  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo8866a(java.lang.Object r13, p000.ime r14) {
        /*
            r12 = this;
            boolean r0 = r12.f14384j
            if (r0 == 0) goto L_0x059e
            boolean r0 = r12.f14382h
            r1 = 0
            if (r0 == 0) goto L_0x001f
            iim r0 = p000.imi.m14098a((java.lang.Object) r13)
            boolean r2 = r0.mo8725a()
            if (r2 != 0) goto L_0x001e
            java.util.Iterator r0 = r0.mo8732d()
            java.lang.Object r2 = r0.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            goto L_0x0021
        L_0x001e:
        L_0x001f:
            r0 = r1
            r2 = r0
        L_0x0021:
            int[] r3 = r12.f14377c
            int r3 = r3.length
            r4 = 0
            r5 = 0
        L_0x0026:
            if (r5 < r3) goto L_0x0041
        L_0x0029:
            if (r2 == 0) goto L_0x003d
            p000.imi.m14111a(r14, r2)
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x003b
            java.lang.Object r2 = r0.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            goto L_0x0029
        L_0x003b:
            r2 = r1
            goto L_0x0029
        L_0x003d:
            m13771c((java.lang.Object) r13, (p000.ime) r14)
            return
        L_0x0041:
            int r6 = r12.m13775e((int) r5)
            int r7 = r12.m13773d((int) r5)
        L_0x0049:
            if (r2 == 0) goto L_0x0063
            int r8 = p000.imi.m14097a((java.util.Map.Entry) r2)
            if (r8 > r7) goto L_0x0063
            p000.imi.m14111a(r14, r2)
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0061
            java.lang.Object r2 = r0.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            goto L_0x0049
        L_0x0061:
            r2 = r1
            goto L_0x0049
        L_0x0063:
            int r8 = m13781g((int) r6)
            r9 = 1
            switch(r8) {
                case 0: goto L_0x0589;
                case 1: goto L_0x0577;
                case 2: goto L_0x0565;
                case 3: goto L_0x0553;
                case 4: goto L_0x0541;
                case 5: goto L_0x052f;
                case 6: goto L_0x051d;
                case 7: goto L_0x050a;
                case 8: goto L_0x04f7;
                case 9: goto L_0x04e0;
                case 10: goto L_0x04cb;
                case 11: goto L_0x04b8;
                case 12: goto L_0x04a5;
                case 13: goto L_0x0492;
                case 14: goto L_0x047f;
                case 15: goto L_0x046c;
                case 16: goto L_0x0459;
                case 17: goto L_0x0442;
                case 18: goto L_0x042f;
                case 19: goto L_0x041c;
                case 20: goto L_0x0409;
                case 21: goto L_0x03f6;
                case 22: goto L_0x03e3;
                case 23: goto L_0x03d0;
                case 24: goto L_0x03bd;
                case 25: goto L_0x03aa;
                case 26: goto L_0x0397;
                case 27: goto L_0x0380;
                case 28: goto L_0x036d;
                case 29: goto L_0x035a;
                case 30: goto L_0x0347;
                case 31: goto L_0x0334;
                case 32: goto L_0x0321;
                case 33: goto L_0x030e;
                case 34: goto L_0x02fb;
                case 35: goto L_0x02e8;
                case 36: goto L_0x02d5;
                case 37: goto L_0x02c2;
                case 38: goto L_0x02af;
                case 39: goto L_0x029c;
                case 40: goto L_0x0289;
                case 41: goto L_0x0276;
                case 42: goto L_0x0263;
                case 43: goto L_0x0250;
                case 44: goto L_0x023d;
                case 45: goto L_0x022a;
                case 46: goto L_0x0217;
                case 47: goto L_0x0204;
                case 48: goto L_0x01f1;
                case 49: goto L_0x01da;
                case 50: goto L_0x01cd;
                case 51: goto L_0x01ba;
                case 52: goto L_0x01a7;
                case 53: goto L_0x0194;
                case 54: goto L_0x0181;
                case 55: goto L_0x016e;
                case 56: goto L_0x015b;
                case 57: goto L_0x0148;
                case 58: goto L_0x0135;
                case 59: goto L_0x0122;
                case 60: goto L_0x010b;
                case 61: goto L_0x00f6;
                case 62: goto L_0x00e3;
                case 63: goto L_0x00d0;
                case 64: goto L_0x00bd;
                case 65: goto L_0x00aa;
                case 66: goto L_0x0097;
                case 67: goto L_0x0084;
                case 68: goto L_0x006d;
                default: goto L_0x006b;
            }
        L_0x006b:
            goto L_0x059a
        L_0x006d:
            boolean r8 = r12.m13758a((java.lang.Object) r13, (int) r7, (int) r5)
            if (r8 == 0) goto L_0x059a
            long r8 = m13785i(r6)
            java.lang.Object r6 = p000.ilv.m14048f(r13, r8)
            iky r8 = r12.m13749a((int) r5)
            r14.mo8698b(r7, r6, r8)
            goto L_0x059a
        L_0x0084:
            boolean r8 = r12.m13758a((java.lang.Object) r13, (int) r7, (int) r5)
            if (r8 == 0) goto L_0x059a
            long r8 = m13785i(r6)
            long r8 = m13777e(r13, r8)
            r14.mo8704e((int) r7, (long) r8)
            goto L_0x059a
        L_0x0097:
            boolean r8 = r12.m13758a((java.lang.Object) r13, (int) r7, (int) r5)
            if (r8 == 0) goto L_0x059a
            long r8 = m13785i(r6)
            int r6 = m13774d(r13, r8)
            r14.mo8705f(r7, r6)
            goto L_0x059a
        L_0x00aa:
            boolean r8 = r12.m13758a((java.lang.Object) r13, (int) r7, (int) r5)
            if (r8 == 0) goto L_0x059a
            long r8 = m13785i(r6)
            long r8 = m13777e(r13, r8)
            r14.mo8697b((int) r7, (long) r8)
            goto L_0x059a
        L_0x00bd:
            boolean r8 = r12.m13758a((java.lang.Object) r13, (int) r7, (int) r5)
            if (r8 == 0) goto L_0x059a
            long r8 = m13785i(r6)
            int r6 = m13774d(r13, r8)
            r14.mo8689a((int) r7, (int) r6)
            goto L_0x059a
        L_0x00d0:
            boolean r8 = r12.m13758a((java.lang.Object) r13, (int) r7, (int) r5)
            if (r8 == 0) goto L_0x059a
            long r8 = m13785i(r6)
            int r6 = m13774d(r13, r8)
            r14.mo8696b((int) r7, (int) r6)
            goto L_0x059a
        L_0x00e3:
            boolean r8 = r12.m13758a((java.lang.Object) r13, (int) r7, (int) r5)
            if (r8 == 0) goto L_0x059a
            long r8 = m13785i(r6)
            int r6 = m13774d(r13, r8)
            r14.mo8703e((int) r7, (int) r6)
            goto L_0x059a
        L_0x00f6:
            boolean r8 = r12.m13758a((java.lang.Object) r13, (int) r7, (int) r5)
            if (r8 == 0) goto L_0x059a
            long r8 = m13785i(r6)
            java.lang.Object r6 = p000.ilv.m14048f(r13, r8)
            ihw r6 = (p000.ihw) r6
            r14.mo8691a((int) r7, (p000.ihw) r6)
            goto L_0x059a
        L_0x010b:
            boolean r8 = r12.m13758a((java.lang.Object) r13, (int) r7, (int) r5)
            if (r8 == 0) goto L_0x059a
            long r8 = m13785i(r6)
            java.lang.Object r6 = p000.ilv.m14048f(r13, r8)
            iky r8 = r12.m13749a((int) r5)
            r14.mo8693a(r7, r6, r8)
            goto L_0x059a
        L_0x0122:
            boolean r8 = r12.m13758a((java.lang.Object) r13, (int) r7, (int) r5)
            if (r8 == 0) goto L_0x059a
            long r8 = m13785i(r6)
            java.lang.Object r6 = p000.ilv.m14048f(r13, r8)
            m13753a((int) r7, (java.lang.Object) r6, (p000.ime) r14)
            goto L_0x059a
        L_0x0135:
            boolean r8 = r12.m13758a((java.lang.Object) r13, (int) r7, (int) r5)
            if (r8 == 0) goto L_0x059a
            long r8 = m13785i(r6)
            boolean r6 = m13780f(r13, r8)
            r14.mo8695a((int) r7, (boolean) r6)
            goto L_0x059a
        L_0x0148:
            boolean r8 = r12.m13758a((java.lang.Object) r13, (int) r7, (int) r5)
            if (r8 == 0) goto L_0x059a
            long r8 = m13785i(r6)
            int r6 = m13774d(r13, r8)
            r14.mo8701d((int) r7, (int) r6)
            goto L_0x059a
        L_0x015b:
            boolean r8 = r12.m13758a((java.lang.Object) r13, (int) r7, (int) r5)
            if (r8 == 0) goto L_0x059a
            long r8 = m13785i(r6)
            long r8 = m13777e(r13, r8)
            r14.mo8702d((int) r7, (long) r8)
            goto L_0x059a
        L_0x016e:
            boolean r8 = r12.m13758a((java.lang.Object) r13, (int) r7, (int) r5)
            if (r8 == 0) goto L_0x059a
            long r8 = m13785i(r6)
            int r6 = m13774d(r13, r8)
            r14.mo8699c((int) r7, (int) r6)
            goto L_0x059a
        L_0x0181:
            boolean r8 = r12.m13758a((java.lang.Object) r13, (int) r7, (int) r5)
            if (r8 == 0) goto L_0x059a
            long r8 = m13785i(r6)
            long r8 = m13777e(r13, r8)
            r14.mo8700c((int) r7, (long) r8)
            goto L_0x059a
        L_0x0194:
            boolean r8 = r12.m13758a((java.lang.Object) r13, (int) r7, (int) r5)
            if (r8 == 0) goto L_0x059a
            long r8 = m13785i(r6)
            long r8 = m13777e(r13, r8)
            r14.mo8690a((int) r7, (long) r8)
            goto L_0x059a
        L_0x01a7:
            boolean r8 = r12.m13758a((java.lang.Object) r13, (int) r7, (int) r5)
            if (r8 == 0) goto L_0x059a
            long r8 = m13785i(r6)
            float r6 = m13769c((java.lang.Object) r13, (long) r8)
            r14.mo8688a((int) r7, (float) r6)
            goto L_0x059a
        L_0x01ba:
            boolean r8 = r12.m13758a((java.lang.Object) r13, (int) r7, (int) r5)
            if (r8 == 0) goto L_0x059a
            long r8 = m13785i(r6)
            double r8 = m13761b((java.lang.Object) r13, (long) r8)
            r14.mo8687a((int) r7, (double) r8)
            goto L_0x059a
        L_0x01cd:
            long r8 = m13785i(r6)
            java.lang.Object r6 = p000.ilv.m14048f(r13, r8)
            r12.m13754a((p000.ime) r14, (int) r7, (java.lang.Object) r6, (int) r5)
            goto L_0x059a
        L_0x01da:
            int r7 = r12.m13773d((int) r5)
            long r8 = m13785i(r6)
            java.lang.Object r6 = p000.ilv.m14048f(r13, r8)
            java.util.List r6 = (java.util.List) r6
            iky r8 = r12.m13749a((int) r5)
            p000.ila.m13923b((int) r7, (java.util.List) r6, (p000.ime) r14, (p000.iky) r8)
            goto L_0x059a
        L_0x01f1:
            int r7 = r12.m13773d((int) r5)
            long r10 = m13785i(r6)
            java.lang.Object r6 = p000.ilv.m14048f(r13, r10)
            java.util.List r6 = (java.util.List) r6
            p000.ila.m13935e(r7, r6, r14, r9)
            goto L_0x059a
        L_0x0204:
            int r7 = r12.m13773d((int) r5)
            long r10 = m13785i(r6)
            java.lang.Object r6 = p000.ilv.m14048f(r13, r10)
            java.util.List r6 = (java.util.List) r6
            p000.ila.m13950j(r7, r6, r14, r9)
            goto L_0x059a
        L_0x0217:
            int r7 = r12.m13773d((int) r5)
            long r10 = m13785i(r6)
            java.lang.Object r6 = p000.ilv.m14048f(r13, r10)
            java.util.List r6 = (java.util.List) r6
            p000.ila.m13941g(r7, r6, r14, r9)
            goto L_0x059a
        L_0x022a:
            int r7 = r12.m13773d((int) r5)
            long r10 = m13785i(r6)
            java.lang.Object r6 = p000.ilv.m14048f(r13, r10)
            java.util.List r6 = (java.util.List) r6
            p000.ila.m13954l(r7, r6, r14, r9)
            goto L_0x059a
        L_0x023d:
            int r7 = r12.m13773d((int) r5)
            long r10 = m13785i(r6)
            java.lang.Object r6 = p000.ilv.m14048f(r13, r10)
            java.util.List r6 = (java.util.List) r6
            p000.ila.m13955m(r7, r6, r14, r9)
            goto L_0x059a
        L_0x0250:
            int r7 = r12.m13773d((int) r5)
            long r10 = m13785i(r6)
            java.lang.Object r6 = p000.ilv.m14048f(r13, r10)
            java.util.List r6 = (java.util.List) r6
            p000.ila.m13947i(r7, r6, r14, r9)
            goto L_0x059a
        L_0x0263:
            int r7 = r12.m13773d((int) r5)
            long r10 = m13785i(r6)
            java.lang.Object r6 = p000.ilv.m14048f(r13, r10)
            java.util.List r6 = (java.util.List) r6
            p000.ila.m13956n(r7, r6, r14, r9)
            goto L_0x059a
        L_0x0276:
            int r7 = r12.m13773d((int) r5)
            long r10 = m13785i(r6)
            java.lang.Object r6 = p000.ilv.m14048f(r13, r10)
            java.util.List r6 = (java.util.List) r6
            p000.ila.m13952k(r7, r6, r14, r9)
            goto L_0x059a
        L_0x0289:
            int r7 = r12.m13773d((int) r5)
            long r10 = m13785i(r6)
            java.lang.Object r6 = p000.ilv.m14048f(r13, r10)
            java.util.List r6 = (java.util.List) r6
            p000.ila.m13938f(r7, r6, r14, r9)
            goto L_0x059a
        L_0x029c:
            int r7 = r12.m13773d((int) r5)
            long r10 = m13785i(r6)
            java.lang.Object r6 = p000.ilv.m14048f(r13, r10)
            java.util.List r6 = (java.util.List) r6
            p000.ila.m13944h(r7, r6, r14, r9)
            goto L_0x059a
        L_0x02af:
            int r7 = r12.m13773d((int) r5)
            long r10 = m13785i(r6)
            java.lang.Object r6 = p000.ilv.m14048f(r13, r10)
            java.util.List r6 = (java.util.List) r6
            p000.ila.m13932d(r7, r6, r14, r9)
            goto L_0x059a
        L_0x02c2:
            int r7 = r12.m13773d((int) r5)
            long r10 = m13785i(r6)
            java.lang.Object r6 = p000.ilv.m14048f(r13, r10)
            java.util.List r6 = (java.util.List) r6
            p000.ila.m13928c(r7, r6, r14, r9)
            goto L_0x059a
        L_0x02d5:
            int r7 = r12.m13773d((int) r5)
            long r10 = m13785i(r6)
            java.lang.Object r6 = p000.ilv.m14048f(r13, r10)
            java.util.List r6 = (java.util.List) r6
            p000.ila.m13924b((int) r7, (java.util.List) r6, (p000.ime) r14, (boolean) r9)
            goto L_0x059a
        L_0x02e8:
            int r7 = r12.m13773d((int) r5)
            long r10 = m13785i(r6)
            java.lang.Object r6 = p000.ilv.m14048f(r13, r10)
            java.util.List r6 = (java.util.List) r6
            p000.ila.m13915a((int) r7, (java.util.List) r6, (p000.ime) r14, (boolean) r9)
            goto L_0x059a
        L_0x02fb:
            int r7 = r12.m13773d((int) r5)
            long r8 = m13785i(r6)
            java.lang.Object r6 = p000.ilv.m14048f(r13, r8)
            java.util.List r6 = (java.util.List) r6
            p000.ila.m13935e(r7, r6, r14, r4)
            goto L_0x059a
        L_0x030e:
            int r7 = r12.m13773d((int) r5)
            long r8 = m13785i(r6)
            java.lang.Object r6 = p000.ilv.m14048f(r13, r8)
            java.util.List r6 = (java.util.List) r6
            p000.ila.m13950j(r7, r6, r14, r4)
            goto L_0x059a
        L_0x0321:
            int r7 = r12.m13773d((int) r5)
            long r8 = m13785i(r6)
            java.lang.Object r6 = p000.ilv.m14048f(r13, r8)
            java.util.List r6 = (java.util.List) r6
            p000.ila.m13941g(r7, r6, r14, r4)
            goto L_0x059a
        L_0x0334:
            int r7 = r12.m13773d((int) r5)
            long r8 = m13785i(r6)
            java.lang.Object r6 = p000.ilv.m14048f(r13, r8)
            java.util.List r6 = (java.util.List) r6
            p000.ila.m13954l(r7, r6, r14, r4)
            goto L_0x059a
        L_0x0347:
            int r7 = r12.m13773d((int) r5)
            long r8 = m13785i(r6)
            java.lang.Object r6 = p000.ilv.m14048f(r13, r8)
            java.util.List r6 = (java.util.List) r6
            p000.ila.m13955m(r7, r6, r14, r4)
            goto L_0x059a
        L_0x035a:
            int r7 = r12.m13773d((int) r5)
            long r8 = m13785i(r6)
            java.lang.Object r6 = p000.ilv.m14048f(r13, r8)
            java.util.List r6 = (java.util.List) r6
            p000.ila.m13947i(r7, r6, r14, r4)
            goto L_0x059a
        L_0x036d:
            int r7 = r12.m13773d((int) r5)
            long r8 = m13785i(r6)
            java.lang.Object r6 = p000.ilv.m14048f(r13, r8)
            java.util.List r6 = (java.util.List) r6
            p000.ila.m13922b((int) r7, (java.util.List) r6, (p000.ime) r14)
            goto L_0x059a
        L_0x0380:
            int r7 = r12.m13773d((int) r5)
            long r8 = m13785i(r6)
            java.lang.Object r6 = p000.ilv.m14048f(r13, r8)
            java.util.List r6 = (java.util.List) r6
            iky r8 = r12.m13749a((int) r5)
            p000.ila.m13914a((int) r7, (java.util.List) r6, (p000.ime) r14, (p000.iky) r8)
            goto L_0x059a
        L_0x0397:
            int r7 = r12.m13773d((int) r5)
            long r8 = m13785i(r6)
            java.lang.Object r6 = p000.ilv.m14048f(r13, r8)
            java.util.List r6 = (java.util.List) r6
            p000.ila.m13913a((int) r7, (java.util.List) r6, (p000.ime) r14)
            goto L_0x059a
        L_0x03aa:
            int r7 = r12.m13773d((int) r5)
            long r8 = m13785i(r6)
            java.lang.Object r6 = p000.ilv.m14048f(r13, r8)
            java.util.List r6 = (java.util.List) r6
            p000.ila.m13956n(r7, r6, r14, r4)
            goto L_0x059a
        L_0x03bd:
            int r7 = r12.m13773d((int) r5)
            long r8 = m13785i(r6)
            java.lang.Object r6 = p000.ilv.m14048f(r13, r8)
            java.util.List r6 = (java.util.List) r6
            p000.ila.m13952k(r7, r6, r14, r4)
            goto L_0x059a
        L_0x03d0:
            int r7 = r12.m13773d((int) r5)
            long r8 = m13785i(r6)
            java.lang.Object r6 = p000.ilv.m14048f(r13, r8)
            java.util.List r6 = (java.util.List) r6
            p000.ila.m13938f(r7, r6, r14, r4)
            goto L_0x059a
        L_0x03e3:
            int r7 = r12.m13773d((int) r5)
            long r8 = m13785i(r6)
            java.lang.Object r6 = p000.ilv.m14048f(r13, r8)
            java.util.List r6 = (java.util.List) r6
            p000.ila.m13944h(r7, r6, r14, r4)
            goto L_0x059a
        L_0x03f6:
            int r7 = r12.m13773d((int) r5)
            long r8 = m13785i(r6)
            java.lang.Object r6 = p000.ilv.m14048f(r13, r8)
            java.util.List r6 = (java.util.List) r6
            p000.ila.m13932d(r7, r6, r14, r4)
            goto L_0x059a
        L_0x0409:
            int r7 = r12.m13773d((int) r5)
            long r8 = m13785i(r6)
            java.lang.Object r6 = p000.ilv.m14048f(r13, r8)
            java.util.List r6 = (java.util.List) r6
            p000.ila.m13928c(r7, r6, r14, r4)
            goto L_0x059a
        L_0x041c:
            int r7 = r12.m13773d((int) r5)
            long r8 = m13785i(r6)
            java.lang.Object r6 = p000.ilv.m14048f(r13, r8)
            java.util.List r6 = (java.util.List) r6
            p000.ila.m13924b((int) r7, (java.util.List) r6, (p000.ime) r14, (boolean) r4)
            goto L_0x059a
        L_0x042f:
            int r7 = r12.m13773d((int) r5)
            long r8 = m13785i(r6)
            java.lang.Object r6 = p000.ilv.m14048f(r13, r8)
            java.util.List r6 = (java.util.List) r6
            p000.ila.m13915a((int) r7, (java.util.List) r6, (p000.ime) r14, (boolean) r4)
            goto L_0x059a
        L_0x0442:
            boolean r8 = r12.m13757a((java.lang.Object) r13, (int) r5)
            if (r8 == 0) goto L_0x059a
            long r8 = m13785i(r6)
            java.lang.Object r6 = p000.ilv.m14048f(r13, r8)
            iky r8 = r12.m13749a((int) r5)
            r14.mo8698b(r7, r6, r8)
            goto L_0x059a
        L_0x0459:
            boolean r8 = r12.m13757a((java.lang.Object) r13, (int) r5)
            if (r8 == 0) goto L_0x059a
            long r8 = m13785i(r6)
            long r8 = p000.ilv.m14040b(r13, r8)
            r14.mo8704e((int) r7, (long) r8)
            goto L_0x059a
        L_0x046c:
            boolean r8 = r12.m13757a((java.lang.Object) r13, (int) r5)
            if (r8 == 0) goto L_0x059a
            long r8 = m13785i(r6)
            int r6 = p000.ilv.m14027a((java.lang.Object) r13, (long) r8)
            r14.mo8705f(r7, r6)
            goto L_0x059a
        L_0x047f:
            boolean r8 = r12.m13757a((java.lang.Object) r13, (int) r5)
            if (r8 == 0) goto L_0x059a
            long r8 = m13785i(r6)
            long r8 = p000.ilv.m14040b(r13, r8)
            r14.mo8697b((int) r7, (long) r8)
            goto L_0x059a
        L_0x0492:
            boolean r8 = r12.m13757a((java.lang.Object) r13, (int) r5)
            if (r8 == 0) goto L_0x059a
            long r8 = m13785i(r6)
            int r6 = p000.ilv.m14027a((java.lang.Object) r13, (long) r8)
            r14.mo8689a((int) r7, (int) r6)
            goto L_0x059a
        L_0x04a5:
            boolean r8 = r12.m13757a((java.lang.Object) r13, (int) r5)
            if (r8 == 0) goto L_0x059a
            long r8 = m13785i(r6)
            int r6 = p000.ilv.m14027a((java.lang.Object) r13, (long) r8)
            r14.mo8696b((int) r7, (int) r6)
            goto L_0x059a
        L_0x04b8:
            boolean r8 = r12.m13757a((java.lang.Object) r13, (int) r5)
            if (r8 == 0) goto L_0x059a
            long r8 = m13785i(r6)
            int r6 = p000.ilv.m14027a((java.lang.Object) r13, (long) r8)
            r14.mo8703e((int) r7, (int) r6)
            goto L_0x059a
        L_0x04cb:
            boolean r8 = r12.m13757a((java.lang.Object) r13, (int) r5)
            if (r8 == 0) goto L_0x059a
            long r8 = m13785i(r6)
            java.lang.Object r6 = p000.ilv.m14048f(r13, r8)
            ihw r6 = (p000.ihw) r6
            r14.mo8691a((int) r7, (p000.ihw) r6)
            goto L_0x059a
        L_0x04e0:
            boolean r8 = r12.m13757a((java.lang.Object) r13, (int) r5)
            if (r8 == 0) goto L_0x059a
            long r8 = m13785i(r6)
            java.lang.Object r6 = p000.ilv.m14048f(r13, r8)
            iky r8 = r12.m13749a((int) r5)
            r14.mo8693a(r7, r6, r8)
            goto L_0x059a
        L_0x04f7:
            boolean r8 = r12.m13757a((java.lang.Object) r13, (int) r5)
            if (r8 == 0) goto L_0x059a
            long r8 = m13785i(r6)
            java.lang.Object r6 = p000.ilv.m14048f(r13, r8)
            m13753a((int) r7, (java.lang.Object) r6, (p000.ime) r14)
            goto L_0x059a
        L_0x050a:
            boolean r8 = r12.m13757a((java.lang.Object) r13, (int) r5)
            if (r8 == 0) goto L_0x059a
            long r8 = m13785i(r6)
            boolean r6 = p000.ilv.m14044c(r13, r8)
            r14.mo8695a((int) r7, (boolean) r6)
            goto L_0x059a
        L_0x051d:
            boolean r8 = r12.m13757a((java.lang.Object) r13, (int) r5)
            if (r8 == 0) goto L_0x059a
            long r8 = m13785i(r6)
            int r6 = p000.ilv.m14027a((java.lang.Object) r13, (long) r8)
            r14.mo8701d((int) r7, (int) r6)
            goto L_0x059a
        L_0x052f:
            boolean r8 = r12.m13757a((java.lang.Object) r13, (int) r5)
            if (r8 == 0) goto L_0x059a
            long r8 = m13785i(r6)
            long r8 = p000.ilv.m14040b(r13, r8)
            r14.mo8702d((int) r7, (long) r8)
            goto L_0x059a
        L_0x0541:
            boolean r8 = r12.m13757a((java.lang.Object) r13, (int) r5)
            if (r8 == 0) goto L_0x059a
            long r8 = m13785i(r6)
            int r6 = p000.ilv.m14027a((java.lang.Object) r13, (long) r8)
            r14.mo8699c((int) r7, (int) r6)
            goto L_0x059a
        L_0x0553:
            boolean r8 = r12.m13757a((java.lang.Object) r13, (int) r5)
            if (r8 == 0) goto L_0x059a
            long r8 = m13785i(r6)
            long r8 = p000.ilv.m14040b(r13, r8)
            r14.mo8700c((int) r7, (long) r8)
            goto L_0x059a
        L_0x0565:
            boolean r8 = r12.m13757a((java.lang.Object) r13, (int) r5)
            if (r8 == 0) goto L_0x059a
            long r8 = m13785i(r6)
            long r8 = p000.ilv.m14040b(r13, r8)
            r14.mo8690a((int) r7, (long) r8)
            goto L_0x059a
        L_0x0577:
            boolean r8 = r12.m13757a((java.lang.Object) r13, (int) r5)
            if (r8 == 0) goto L_0x059a
            long r8 = m13785i(r6)
            float r6 = p000.ilv.m14045d(r13, r8)
            r14.mo8688a((int) r7, (float) r6)
            goto L_0x059a
        L_0x0589:
            boolean r8 = r12.m13757a((java.lang.Object) r13, (int) r5)
            if (r8 == 0) goto L_0x059a
            long r8 = m13785i(r6)
            double r8 = p000.ilv.m14047e(r13, r8)
            r14.mo8687a((int) r7, (double) r8)
        L_0x059a:
            int r5 = r5 + 3
            goto L_0x0026
        L_0x059e:
            r12.m13766b((java.lang.Object) r13, (p000.ime) r14)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.ikh.mo8866a(java.lang.Object, ime):void");
    }

    /* renamed from: c */
    private static final void m13771c(Object obj, ime ime) {
        imu.m14135a(obj).mo8944a(ime);
    }
}
