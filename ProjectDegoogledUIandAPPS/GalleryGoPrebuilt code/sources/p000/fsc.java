package p000;

import android.util.Log;
import android.util.SparseIntArray;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.TimeZone;
import java.util.regex.Pattern;
import p003j$.util.DesugarTimeZone;

/* renamed from: fsc */
/* compiled from: PG */
public final class fsc {

    /* renamed from: A */
    private static final int f10348A = m9493a(0, 277);

    /* renamed from: B */
    private static final int f10349B = m9493a(0, 278);

    /* renamed from: C */
    private static final int f10350C = m9493a(0, 282);

    /* renamed from: D */
    private static final int f10351D = m9493a(0, 283);

    /* renamed from: E */
    private static final int f10352E = m9493a(0, 284);

    /* renamed from: F */
    private static final int f10353F = m9493a(0, 296);

    /* renamed from: G */
    private static final int f10354G = m9493a(0, 301);

    /* renamed from: H */
    private static final int f10355H = m9493a(0, 315);

    /* renamed from: I */
    private static final int f10356I = m9493a(0, 318);

    /* renamed from: J */
    private static final int f10357J = m9493a(0, 319);

    /* renamed from: K */
    private static final int f10358K = m9493a(0, 529);

    /* renamed from: L */
    private static final int f10359L = m9493a(0, 530);

    /* renamed from: M */
    private static final int f10360M = m9493a(0, 531);

    /* renamed from: N */
    private static final int f10361N = m9493a(0, 532);

    /* renamed from: O */
    private static final int f10362O = m9493a(0, -32104);

    /* renamed from: P */
    private static final int f10363P = m9493a(2, -30686);

    /* renamed from: Q */
    private static final int f10364Q = m9493a(2, -30684);

    /* renamed from: R */
    private static final int f10365R = m9493a(2, -30680);

    /* renamed from: S */
    private static final int f10366S = m9493a(2, -28672);

    /* renamed from: T */
    private static final int f10367T = m9493a(2, -28668);

    /* renamed from: U */
    private static final int f10368U = m9493a(2, -28415);

    /* renamed from: V */
    private static final int f10369V = m9493a(2, -28414);

    /* renamed from: W */
    private static final int f10370W = m9493a(2, -28159);

    /* renamed from: X */
    private static final int f10371X = m9493a(2, -28158);

    /* renamed from: Y */
    private static final int f10372Y = m9493a(2, -28157);

    /* renamed from: Z */
    private static final int f10373Z = m9493a(2, -28156);

    /* renamed from: a */
    public static final int f10374a = m9493a(0, 272);

    /* renamed from: aA */
    private static final int f10375aA = m9493a(2, -23806);

    /* renamed from: aB */
    private static final int f10376aB = m9493a(2, -23551);

    /* renamed from: aC */
    private static final int f10377aC = m9493a(2, -23550);

    /* renamed from: aD */
    private static final int f10378aD = m9493a(2, -23549);

    /* renamed from: aE */
    private static final int f10379aE = m9493a(2, -23548);

    /* renamed from: aF */
    private static final int f10380aF = m9493a(2, -23547);

    /* renamed from: aG */
    private static final int f10381aG = m9493a(2, -23546);

    /* renamed from: aH */
    private static final int f10382aH = m9493a(2, -23545);

    /* renamed from: aI */
    private static final int f10383aI = m9493a(2, -23544);

    /* renamed from: aJ */
    private static final int f10384aJ = m9493a(2, -23543);

    /* renamed from: aK */
    private static final int f10385aK = m9493a(2, -23542);

    /* renamed from: aL */
    private static final int f10386aL = m9493a(2, -23541);

    /* renamed from: aM */
    private static final int f10387aM = m9493a(2, -23540);

    /* renamed from: aN */
    private static final int f10388aN = m9493a(2, -23520);

    /* renamed from: aO */
    private static final int f10389aO = m9493a(2, -28654);

    /* renamed from: aP */
    private static final int f10390aP = m9493a(4, 0);

    /* renamed from: aQ */
    private static final int f10391aQ = m9493a(4, 1);

    /* renamed from: aR */
    private static final int f10392aR = m9493a(4, 2);

    /* renamed from: aS */
    private static final int f10393aS = m9493a(4, 3);

    /* renamed from: aT */
    private static final int f10394aT = m9493a(4, 4);

    /* renamed from: aU */
    private static final int f10395aU = m9493a(4, 5);

    /* renamed from: aV */
    private static final int f10396aV = m9493a(4, 6);

    /* renamed from: aW */
    private static final int f10397aW = m9493a(4, 7);

    /* renamed from: aX */
    private static final int f10398aX = m9493a(4, 8);

    /* renamed from: aY */
    private static final int f10399aY = m9493a(4, 9);

    /* renamed from: aZ */
    private static final int f10400aZ = m9493a(4, 10);

    /* renamed from: aa */
    private static final int f10401aa = m9493a(2, -28155);

    /* renamed from: ab */
    private static final int f10402ab = m9493a(2, -28154);

    /* renamed from: ac */
    private static final int f10403ac = m9493a(2, -28153);

    /* renamed from: ad */
    private static final int f10404ad = m9493a(2, -28152);

    /* renamed from: ae */
    private static final int f10405ae = m9493a(2, -28151);

    /* renamed from: af */
    private static final int f10406af = m9493a(2, -28140);

    /* renamed from: ag */
    private static final int f10407ag = m9493a(2, -28036);

    /* renamed from: ah */
    private static final int f10408ah = m9493a(2, -28026);

    /* renamed from: ai */
    private static final int f10409ai = m9493a(2, -28016);

    /* renamed from: aj */
    private static final int f10410aj = m9493a(2, -28015);

    /* renamed from: ak */
    private static final int f10411ak = m9493a(2, -28014);

    /* renamed from: al */
    private static final int f10412al = m9493a(2, -24576);

    /* renamed from: am */
    private static final int f10413am = m9493a(2, -24575);

    /* renamed from: an */
    private static final int f10414an = m9493a(2, -24574);

    /* renamed from: ao */
    private static final int f10415ao = m9493a(2, -24573);

    /* renamed from: ap */
    private static final int f10416ap = m9493a(2, -24572);

    /* renamed from: aq */
    private static final int f10417aq = m9493a(2, -24053);

    /* renamed from: ar */
    private static final int f10418ar = m9493a(2, -24052);

    /* renamed from: as */
    private static final int f10419as = m9493a(2, -24050);

    /* renamed from: at */
    private static final int f10420at = m9493a(2, -24049);

    /* renamed from: au */
    private static final int f10421au = m9493a(2, -24048);

    /* renamed from: av */
    private static final int f10422av = m9493a(2, -24044);

    /* renamed from: aw */
    private static final int f10423aw = m9493a(2, -24043);

    /* renamed from: ax */
    private static final int f10424ax = m9493a(2, -24041);

    /* renamed from: ay */
    private static final int f10425ay = m9493a(2, -23808);

    /* renamed from: az */
    private static final int f10426az = m9493a(2, -23807);

    /* renamed from: b */
    public static final int f10427b = m9493a(0, 273);

    /* renamed from: ba */
    private static final int f10428ba = m9493a(4, 11);

    /* renamed from: bb */
    private static final int f10429bb = m9493a(4, 12);

    /* renamed from: bc */
    private static final int f10430bc = m9493a(4, 13);

    /* renamed from: bd */
    private static final int f10431bd = m9493a(4, 14);

    /* renamed from: be */
    private static final int f10432be = m9493a(4, 15);

    /* renamed from: bf */
    private static final int f10433bf = m9493a(4, 16);

    /* renamed from: bg */
    private static final int f10434bg = m9493a(4, 17);

    /* renamed from: bh */
    private static final int f10435bh = m9493a(4, 18);

    /* renamed from: bi */
    private static final int f10436bi = m9493a(4, 19);

    /* renamed from: bj */
    private static final int f10437bj = m9493a(4, 20);

    /* renamed from: bk */
    private static final int f10438bk = m9493a(4, 23);

    /* renamed from: bl */
    private static final int f10439bl = m9493a(4, 24);

    /* renamed from: bm */
    private static final int f10440bm = m9493a(4, 25);

    /* renamed from: bn */
    private static final int f10441bn = m9493a(4, 26);

    /* renamed from: bo */
    private static final int f10442bo = m9493a(4, 27);

    /* renamed from: bp */
    private static final int f10443bp = m9493a(4, 28);

    /* renamed from: bq */
    private static final int f10444bq = m9493a(4, 29);

    /* renamed from: br */
    private static final int f10445br = m9493a(4, 30);

    /* renamed from: bs */
    private static final int f10446bs = m9493a(3, 1);

    /* renamed from: bt */
    private static final HashSet f10447bt;

    /* renamed from: bu */
    private static final HashSet f10448bu;

    /* renamed from: bv */
    private static final ByteOrder f10449bv = ByteOrder.BIG_ENDIAN;

    /* renamed from: c */
    public static final int f10450c = m9493a(0, 274);

    /* renamed from: d */
    public static final int f10451d = m9493a(0, 279);

    /* renamed from: e */
    public static final int f10452e = m9493a(0, 305);

    /* renamed from: f */
    public static final int f10453f = m9493a(0, 306);

    /* renamed from: g */
    public static final int f10454g = m9493a(0, -30871);

    /* renamed from: h */
    public static final int f10455h = m9493a(0, -30683);

    /* renamed from: i */
    public static final int f10456i = m9493a(1, 513);

    /* renamed from: j */
    public static final int f10457j = m9493a(1, 514);

    /* renamed from: k */
    public static final int f10458k = m9493a(2, -32102);

    /* renamed from: l */
    public static final int f10459l = m9493a(2, -32099);

    /* renamed from: m */
    public static final int f10460m = m9493a(2, -30681);

    /* renamed from: n */
    public static final int f10461n = m9493a(2, -28669);

    /* renamed from: o */
    public static final int f10462o = m9493a(2, -28150);

    /* renamed from: p */
    public static final int f10463p = m9493a(2, -24571);

    /* renamed from: q */
    public static final int f10464q = m9493a(2, -28656);

    /* renamed from: r */
    public static final int f10465r = m9493a(2, -28655);

    /* renamed from: t */
    private static final int f10466t = m9493a(0, 256);

    /* renamed from: u */
    private static final int f10467u = m9493a(0, 257);

    /* renamed from: v */
    private static final int f10468v = m9493a(0, 258);

    /* renamed from: w */
    private static final int f10469w = m9493a(0, 259);

    /* renamed from: x */
    private static final int f10470x = m9493a(0, 262);

    /* renamed from: y */
    private static final int f10471y = m9493a(0, 270);

    /* renamed from: z */
    private static final int f10472z = m9493a(0, 271);

    /* renamed from: bw */
    private final DateFormat f10473bw = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");

    /* renamed from: bx */
    private final DateFormat f10474bx = new SimpleDateFormat("yyyy:MM:dd");

    /* renamed from: by */
    private SparseIntArray f10475by;

    /* renamed from: s */
    public fsd f10476s = new fsd(f10449bv);

    static {
        HashSet hashSet = new HashSet();
        f10447bt = hashSet;
        hashSet.add(Short.valueOf(m9495a(f10455h)));
        f10447bt.add(Short.valueOf(m9495a(f10454g)));
        f10447bt.add(Short.valueOf(m9495a(f10456i)));
        f10447bt.add(Short.valueOf(m9495a(f10463p)));
        f10447bt.add(Short.valueOf(m9495a(f10427b)));
        HashSet hashSet2 = new HashSet(f10447bt);
        f10448bu = hashSet2;
        hashSet2.add(Short.valueOf(m9495a(-1)));
        f10448bu.add(Short.valueOf(m9495a(f10457j)));
        f10448bu.add(Short.valueOf(m9495a(f10451d)));
        Pattern.compile(".*[1-9].*");
    }

    /* renamed from: a */
    private static int m9493a(int i, short s) {
        return (i << 16) | ((char) s);
    }

    /* renamed from: a */
    public static short m9495a(int i) {
        return (short) i;
    }

    /* renamed from: f */
    private static int m9499f(int i) {
        return i >>> 16;
    }

    /* renamed from: h */
    private static short m9501h(int i) {
        return (short) ((i >> 16) & 255);
    }

    /* renamed from: i */
    private static int m9502i(int i) {
        return (char) i;
    }

    /* renamed from: a */
    public final byte[] mo6108a() {
        return this.f10476s.f10481e;
    }

    public fsc() {
        Calendar.getInstance(DesugarTimeZone.getTimeZone("UTC"));
        this.f10475by = null;
        this.f10474bx.setTimeZone(DesugarTimeZone.getTimeZone("UTC"));
    }

    /* renamed from: a */
    public final void mo6104a(int i, long j, TimeZone timeZone) {
        if (i == f10453f || i == f10367T || i == f10461n) {
            this.f10473bw.setTimeZone(timeZone);
            fsl a = mo6103a(i, (Object) this.f10473bw.format(Long.valueOf(j)));
            if (a != null) {
                mo6105a(a);
            }
        }
    }

    /* renamed from: a */
    public final fsl mo6103a(int i, Object obj) {
        boolean z;
        int f = m9499f(i);
        int i2 = mo6114d().get(i);
        if (i2 == 0 || obj == null) {
            return null;
        }
        short h = m9501h(i2);
        int i3 = m9502i(i2);
        boolean z2 = i3 != 0;
        if (!m9497b(i2, f)) {
            return null;
        }
        fsl fsl = new fsl(m9495a(i), h, i3, f, z2);
        if (obj instanceof Short) {
            z = fsl.mo6141b((int) (char) ((Short) obj).shortValue());
        } else if (obj instanceof String) {
            z = fsl.mo6135a((String) obj);
        } else if (obj instanceof int[]) {
            z = fsl.mo6137a((int[]) obj);
        } else if (obj instanceof long[]) {
            z = fsl.mo6138a((long[]) obj);
        } else if (obj instanceof fsp) {
            z = fsl.mo6139a(new fsp[]{(fsp) obj});
        } else if (obj instanceof fsp[]) {
            z = fsl.mo6139a((fsp[]) obj);
        } else if (obj instanceof byte[]) {
            z = fsl.mo6136a((byte[]) obj);
        } else if (obj instanceof Integer) {
            z = fsl.mo6141b(((Integer) obj).intValue());
        } else if (obj instanceof Long) {
            z = fsl.mo6138a(new long[]{((Long) obj).longValue()});
        } else if (obj instanceof Byte) {
            z = fsl.mo6136a(new byte[]{((Byte) obj).byteValue()});
        } else if (obj instanceof Short[]) {
            Short[] shArr = (Short[]) obj;
            int[] iArr = new int[shArr.length];
            for (int i4 = 0; i4 < shArr.length; i4++) {
                Short sh = shArr[i4];
                iArr[i4] = sh != null ? (char) sh.shortValue() : 0;
            }
            z = fsl.mo6137a(iArr);
        } else if (obj instanceof Integer[]) {
            Integer[] numArr = (Integer[]) obj;
            int[] iArr2 = new int[numArr.length];
            for (int i5 = 0; i5 < numArr.length; i5++) {
                Integer num = numArr[i5];
                iArr2[i5] = num != null ? num.intValue() : 0;
            }
            z = fsl.mo6137a(iArr2);
        } else if (obj instanceof Long[]) {
            Long[] lArr = (Long[]) obj;
            long[] jArr = new long[lArr.length];
            for (int i6 = 0; i6 < lArr.length; i6++) {
                Long l = lArr[i6];
                jArr[i6] = l != null ? l.longValue() : 0;
            }
            z = fsl.mo6138a(jArr);
        } else {
            if (obj instanceof Byte[]) {
                Byte[] bArr = (Byte[]) obj;
                byte[] bArr2 = new byte[bArr.length];
                for (int i7 = 0; i7 < bArr.length; i7++) {
                    Byte b = bArr[i7];
                    bArr2[i7] = b != null ? b.byteValue() : 0;
                }
                z = fsl.mo6136a(bArr2);
            }
            return null;
        }
        if (z) {
            return fsl;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public final fsl mo6115e(int i) {
        int i2 = mo6114d().get(i);
        if (i2 == 0) {
            return null;
        }
        short h = m9501h(i2);
        int i3 = m9502i(i2);
        return new fsl(m9495a(i), h, i3, m9499f(i), i3 != 0);
    }

    /* renamed from: a */
    private static double m9492a(fsp[] fspArr, String str) {
        try {
            double a = fspArr[0].mo6158a() + (fspArr[1].mo6158a() / 60.0d) + (fspArr[2].mo6158a() / 3600.0d);
            if (str.equals("S") || str.equals("W")) {
                return -a;
            }
            return a;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException();
        }
    }

    /* renamed from: d */
    public final int mo6113d(int i) {
        if (mo6114d().get(i) == 0) {
            return -1;
        }
        return m9499f(i);
    }

    /* renamed from: a */
    private static int m9494a(int[] iArr) {
        if (iArr.length == 0) {
            return 0;
        }
        int[] iArr2 = fsm.f10531c;
        int i = 0;
        for (int i2 = 0; i2 < 5; i2++) {
            int length = iArr.length;
            int i3 = 0;
            while (true) {
                if (i3 >= length) {
                    break;
                }
                if (iArr2[i2] == iArr[i3]) {
                    i |= 1 << i2;
                    break;
                }
                i3++;
            }
        }
        return i;
    }

    /* renamed from: c */
    public final double[] mo6112c() {
        fsp[] g = m9500g(f10392aR);
        String b = mo6109b(f10391aQ);
        fsp[] g2 = m9500g(f10394aT);
        String b2 = mo6109b(f10393aS);
        if (g == null || g2 == null || b == null || b2 == null || g.length < 3 || g2.length < 3) {
            return null;
        }
        return new double[]{m9492a(g, b), m9492a(g2, b2)};
    }

    /* renamed from: a */
    public final fsl mo6102a(int i, int i2) {
        if (fsl.m9546a(i2)) {
            short a = m9495a(i);
            fsm fsm = this.f10476s.f10477a[i2];
            if (fsm != null) {
                return fsm.mo6147a(a);
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public final SparseIntArray mo6114d() {
        if (this.f10475by == null) {
            this.f10475by = new SparseIntArray();
            int a = m9494a(new int[]{0, 1}) << 24;
            int i = a | 131072;
            this.f10475by.put(f10472z, i);
            int i2 = a | 262145;
            this.f10475by.put(f10466t, i2);
            this.f10475by.put(f10467u, i2);
            this.f10475by.put(f10468v, 196611 | a);
            int i3 = a | 196609;
            this.f10475by.put(f10469w, i3);
            this.f10475by.put(f10470x, i3);
            this.f10475by.put(f10450c, i3);
            this.f10475by.put(f10348A, i3);
            this.f10475by.put(f10352E, i3);
            this.f10475by.put(f10359L, a | 196610);
            this.f10475by.put(f10360M, i3);
            int i4 = a | 327681;
            this.f10475by.put(f10350C, i4);
            this.f10475by.put(f10351D, i4);
            this.f10475by.put(f10353F, i3);
            int i5 = 262144 | a;
            this.f10475by.put(f10427b, i5);
            this.f10475by.put(f10349B, i2);
            this.f10475by.put(f10451d, i5);
            this.f10475by.put(f10354G, 197376 | a);
            this.f10475by.put(f10356I, 327682 | a);
            int i6 = 327686 | a;
            this.f10475by.put(f10357J, i6);
            this.f10475by.put(f10358K, a | 327683);
            this.f10475by.put(f10361N, i6);
            this.f10475by.put(f10453f, a | 131092);
            this.f10475by.put(f10471y, i);
            this.f10475by.put(f10472z, i);
            this.f10475by.put(f10374a, i);
            this.f10475by.put(f10452e, i);
            this.f10475by.put(f10355H, i);
            this.f10475by.put(f10362O, i);
            this.f10475by.put(f10454g, i2);
            this.f10475by.put(f10455h, i2);
            int a2 = (m9494a(new int[]{1}) << 24) | 262145;
            this.f10475by.put(f10456i, a2);
            this.f10475by.put(f10457j, a2);
            int a3 = m9494a(new int[]{2}) << 24;
            int i7 = 458756 | a3;
            this.f10475by.put(f10366S, i7);
            this.f10475by.put(f10412al, i7);
            int i8 = 196609 | a3;
            this.f10475by.put(f10413am, i8);
            this.f10475by.put(f10368U, i7);
            int i9 = a3 | 327681;
            this.f10475by.put(f10369V, i9);
            int i10 = 262145 | a3;
            this.f10475by.put(f10414an, i10);
            this.f10475by.put(f10415ao, i10);
            int i11 = a3 | 458752;
            this.f10475by.put(f10407ag, i11);
            this.f10475by.put(f10408ah, i11);
            this.f10475by.put(f10416ap, a3 | 131085);
            int i12 = a3 | 131092;
            this.f10475by.put(f10461n, i12);
            this.f10475by.put(f10367T, i12);
            int i13 = a3 | 131072;
            this.f10475by.put(f10409ai, i13);
            this.f10475by.put(f10410aj, i13);
            this.f10475by.put(f10411ak, i13);
            this.f10475by.put(f10388aN, 131105 | a3);
            this.f10475by.put(f10458k, i9);
            this.f10475by.put(f10459l, i9);
            this.f10475by.put(f10363P, i8);
            this.f10475by.put(f10364Q, i13);
            int i14 = 196608 | a3;
            this.f10475by.put(f10460m, i14);
            this.f10475by.put(f10365R, i11);
            int i15 = 655361 | a3;
            this.f10475by.put(f10370W, i15);
            this.f10475by.put(f10371X, i9);
            this.f10475by.put(f10372Y, i15);
            this.f10475by.put(f10373Z, i15);
            this.f10475by.put(f10401aa, i9);
            this.f10475by.put(f10402ab, i9);
            this.f10475by.put(f10403ac, i8);
            this.f10475by.put(f10404ad, i8);
            this.f10475by.put(f10405ae, i8);
            this.f10475by.put(f10462o, i9);
            this.f10475by.put(f10406af, i14);
            this.f10475by.put(f10417aq, i9);
            this.f10475by.put(f10418ar, i11);
            this.f10475by.put(f10419as, i9);
            this.f10475by.put(f10420at, i9);
            this.f10475by.put(f10421au, i8);
            this.f10475by.put(f10422av, 196610 | a3);
            this.f10475by.put(f10423aw, i9);
            this.f10475by.put(f10424ax, i8);
            int i16 = 458753 | a3;
            this.f10475by.put(f10425ay, i16);
            this.f10475by.put(f10426az, i16);
            this.f10475by.put(f10375aA, i11);
            this.f10475by.put(f10376aB, i8);
            this.f10475by.put(f10377aC, i8);
            this.f10475by.put(f10378aD, i8);
            this.f10475by.put(f10379aE, i9);
            this.f10475by.put(f10380aF, i8);
            this.f10475by.put(f10381aG, i8);
            this.f10475by.put(f10382aH, i9);
            this.f10475by.put(f10383aI, i8);
            this.f10475by.put(f10384aJ, i8);
            this.f10475by.put(f10385aK, i8);
            this.f10475by.put(f10386aL, i11);
            this.f10475by.put(f10387aM, i8);
            this.f10475by.put(f10463p, i10);
            int i17 = a3 | 131079;
            this.f10475by.put(f10464q, i17);
            this.f10475by.put(f10389aO, i17);
            this.f10475by.put(f10465r, i17);
            int a4 = m9494a(new int[]{4}) << 24;
            this.f10475by.put(f10390aP, 65540 | a4);
            int i18 = 131074 | a4;
            this.f10475by.put(f10391aQ, i18);
            this.f10475by.put(f10393aS, i18);
            int i19 = 655363 | a4;
            this.f10475by.put(f10392aR, i19);
            this.f10475by.put(f10394aT, i19);
            this.f10475by.put(f10395aU, 65537 | a4);
            int i20 = a4 | 327681;
            this.f10475by.put(f10396aV, i20);
            this.f10475by.put(f10397aW, a4 | 327683);
            int i21 = a4 | 131072;
            this.f10475by.put(f10398aX, i21);
            this.f10475by.put(f10399aY, i18);
            this.f10475by.put(f10400aZ, i18);
            this.f10475by.put(f10428ba, i20);
            this.f10475by.put(f10429bb, i18);
            this.f10475by.put(f10430bc, i20);
            this.f10475by.put(f10431bd, i18);
            this.f10475by.put(f10432be, i20);
            this.f10475by.put(f10433bf, i18);
            this.f10475by.put(f10434bg, i20);
            this.f10475by.put(f10435bh, i21);
            this.f10475by.put(f10436bi, i18);
            this.f10475by.put(f10437bj, i20);
            this.f10475by.put(f10438bk, i18);
            this.f10475by.put(f10439bl, i20);
            this.f10475by.put(f10440bm, i18);
            this.f10475by.put(f10441bn, i20);
            int i22 = 458752 | a4;
            this.f10475by.put(f10442bo, i22);
            this.f10475by.put(f10443bp, i22);
            this.f10475by.put(f10444bq, 131083 | a4);
            this.f10475by.put(f10445br, a4 | 196619);
            this.f10475by.put(f10446bs, (m9494a(new int[]{3}) << 24) | 131072);
        }
        return this.f10475by;
    }

    /* renamed from: c */
    public final fsp mo6111c(int i) {
        fsp[] c = m9498c(i, mo6113d(i));
        if (c == null || c.length == 0) {
            return null;
        }
        return new fsp(c[0]);
    }

    /* renamed from: g */
    private final fsp[] m9500g(int i) {
        return m9498c(i, mo6113d(i));
    }

    /* renamed from: c */
    private final fsp[] m9498c(int i, int i2) {
        fsl a = mo6102a(i, i2);
        if (a != null) {
            Object obj = a.f10529f;
            if (obj instanceof fsp[]) {
                return (fsp[]) obj;
            }
        }
        return null;
    }

    /* renamed from: b */
    public final String mo6109b(int i) {
        fsl a = mo6102a(i, mo6113d(i));
        if (a != null) {
            return a.mo6143c();
        }
        return null;
    }

    /* renamed from: b */
    static boolean m9497b(int i, int i2) {
        int i3 = i >>> 24;
        int[] iArr = fsm.f10531c;
        for (int i4 = 0; i4 < iArr.length; i4++) {
            if (i2 == iArr[i4] && ((i3 >> i4) & 1) == 1) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    static boolean m9496a(short s) {
        return f10447bt.contains(Short.valueOf(s));
    }

    /* renamed from: a */
    public final void mo6106a(InputStream inputStream) {
        try {
            fsj fsj = new fsj(inputStream, new fsk(this).f10521a);
            fsd fsd = new fsd(fsj.f10503a.f10346b.order());
            for (int a = fsj.mo6130a(); a != 6; a = fsj.mo6130a()) {
                if (a == 0) {
                    fsd.mo6117a(new fsm(fsj.f10504b));
                } else if (a == 1) {
                    fsl fsl = fsj.f10505c;
                    if (!fsl.mo6140b()) {
                        int i = fsl.f10530g;
                        if (i >= fsj.f10503a.f10345a) {
                            fsj.f10510h.put(Integer.valueOf(i), new fsg(fsl, true));
                        }
                    } else {
                        fsd.mo6122b(fsl.f10528e).mo6148a(fsl);
                    }
                } else if (a != 2) {
                    int i2 = 0;
                    if (a == 3) {
                        fsl fsl2 = fsj.f10508f;
                        if (fsl2 != null) {
                            i2 = (int) fsl2.mo6142c(0);
                        }
                        if (i2 >= 0) {
                            byte[] bArr = new byte[i2];
                            if (i2 == fsj.mo6131a(bArr)) {
                                fsd.f10478b = bArr;
                            } else if (Log.isLoggable("ExifReader", 5)) {
                                Log.w("ExifReader", "Failed to read the compressed thumbnail");
                            }
                        } else if (Log.isLoggable("ExifReader", 6)) {
                            Log.e("ExifReader", "Found negative image size for compressed thumbnail");
                        }
                    } else if (a == 4) {
                        fsl fsl3 = fsj.f10507e;
                        if (fsl3 != null) {
                            i2 = (int) fsl3.mo6142c(0);
                        }
                        byte[] bArr2 = new byte[i2];
                        if (i2 == fsj.mo6131a(bArr2)) {
                            int i3 = fsj.f10506d.f10493a;
                            if (i3 >= fsd.f10479c.size()) {
                                for (int size = fsd.f10479c.size(); size < i3; size++) {
                                    fsd.f10479c.add((Object) null);
                                }
                                fsd.f10479c.add(bArr2);
                            } else {
                                fsd.f10479c.set(i3, bArr2);
                            }
                        } else if (Log.isLoggable("ExifReader", 5)) {
                            Log.w("ExifReader", "Failed to read the strip bytes");
                        }
                    } else if (a == 5) {
                        int i4 = fsj.f10509g;
                        byte[] bArr3 = new byte[i4];
                        fsj.mo6133a(bArr3, i4);
                        fsd.f10481e = bArr3;
                    }
                } else {
                    fsl fsl4 = fsj.f10505c;
                    if (fsl4.f10525b == 7) {
                        fsj.mo6132a(fsl4);
                    }
                    fsd.mo6122b(fsl4.f10528e).mo6148a(fsl4);
                }
            }
            this.f10476s = fsd;
        } catch (fse e) {
            String valueOf = String.valueOf(e);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 22);
            sb.append("Invalid exif format : ");
            sb.append(valueOf);
            throw new IOException(sb.toString());
        }
    }

    /* renamed from: b */
    public final void mo6110b() {
        this.f10476s.f10481e = null;
    }

    /* renamed from: a */
    public final void mo6105a(fsl fsl) {
        this.f10476s.mo6116a(fsl);
    }

    /* renamed from: a */
    public final void mo6107a(byte[] bArr) {
        this.f10476s.f10481e = bArr;
    }
}
