package p000;

import java.util.Arrays;
import java.util.Locale;

/* renamed from: hxi */
/* compiled from: PG */
public final class hxi extends hyr implements hyn {

    /* renamed from: a */
    public static final Locale f13577a = Locale.ROOT;

    /* renamed from: b */
    public final Object[] f13578b;

    /* renamed from: c */
    public final StringBuilder f13579c = new StringBuilder();

    /* renamed from: d */
    public int f13580d = 0;

    private hxi(hxk hxk, Object[] objArr) {
        super(hxk);
        this.f13578b = (Object[]) ife.m12827a((Object) objArr, "log arguments");
    }

    /* renamed from: a */
    private static void m12397a(StringBuilder sb, hxd hxd) {
        hwx hwx = new hwx("[CONTEXT ", " ]", sb);
        hxj hxj = null;
        for (int i = 0; i < hxd.mo8191a(); i++) {
            hwn a = hxd.mo8193a(i);
            if (!a.equals(hwa.f13496a)) {
                if (a.equals(hwa.f13501f)) {
                    hxj = (hxj) hwa.f13501f.mo8230a(hxd.mo8194b(i));
                } else {
                    hwx.mo8239a(a.f13524a, hxd.mo8194b(i));
                }
            }
        }
        if (hxj != null) {
            hxj.mo8254a(hwx);
        }
        hwx.mo8238a();
    }

    /* renamed from: a */
    private static void m12395a(StringBuilder sb, long j, boolean z) {
        String str;
        if (j == 0) {
            sb.append("0");
            return;
        }
        if (!z) {
            str = "0123456789abcdef";
        } else {
            str = "0123456789ABCDEF";
        }
        for (int numberOfLeadingZeros = (63 - Long.numberOfLeadingZeros(j)) & -4; numberOfLeadingZeros >= 0; numberOfLeadingZeros -= 4) {
            sb.append(str.charAt((int) ((j >>> numberOfLeadingZeros) & 15)));
        }
    }

    /* renamed from: a */
    public static void m12398a(StringBuilder sb, Object obj, String str) {
        sb.append("[INVALID: format=");
        sb.append(str);
        sb.append(", type=");
        sb.append(obj.getClass().getCanonicalName());
        sb.append(", value=");
        sb.append(m12392a(obj));
        sb.append("]");
    }

    /* renamed from: a */
    public static void m12394a(hwz hwz, hxh hxh, int i) {
        String str;
        hxd l = hwz.mo8217l();
        Throwable th = (Throwable) l.mo8195b(hwa.f13496a);
        boolean z = l.mo8191a() == 0 || (l.mo8191a() == 1 && th != null);
        if (hwz.mo8213h() != null) {
            hxi hxi = new hxi(hwz.mo8213h(), hwz.mo8214i());
            hxi.mo8273a().mo8275a(hxi);
            int i2 = hxi.f13660e;
            if (((i2 + 1) & i2) != 0 || (hxi.f13661f > 31 && i2 != -1)) {
                throw new hyt(String.format("unreferenced arguments [first missing index=%d]", new Object[]{Integer.valueOf(Integer.numberOfTrailingZeros(i2 ^ -1))}));
            }
            hxi.mo8273a().mo8276a(hxi.f13579c, hxi.mo8274b(), hxi.f13580d, hxi.mo8274b().length());
            StringBuilder sb = hxi.f13579c;
            if (hwz.mo8214i().length > hxi.f13661f + 1) {
                sb.append(" [ERROR: UNUSED LOG ARGUMENTS]");
            }
            if (i == 2) {
                m12396a(sb, hwz.mo8212g());
            }
            if (!z) {
                m12397a(sb, l);
            }
            str = sb.toString();
        } else {
            String a = m12392a(hwz.mo8215j());
            if (i == 1 && z) {
                str = a;
            } else {
                StringBuilder sb2 = new StringBuilder(a);
                if (i == 2) {
                    m12396a(sb2, hwz.mo8212g());
                }
                if (!z) {
                    m12397a(sb2, hwz.mo8217l());
                }
                str = sb2.toString();
            }
        }
        hxh.mo8252a(hwz.mo8209d(), str, th);
    }

    /* renamed from: a */
    private static String m12393a(Object obj, RuntimeException runtimeException) {
        String str;
        try {
            str = runtimeException.toString();
        } catch (RuntimeException e) {
            str = e.getClass().getSimpleName();
        }
        String name = obj.getClass().getName();
        int identityHashCode = System.identityHashCode(obj);
        StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 16 + String.valueOf(str).length());
        sb.append("{");
        sb.append(name);
        sb.append("@");
        sb.append(identityHashCode);
        sb.append(": ");
        sb.append(str);
        sb.append("}");
        return sb.toString();
    }

    /* renamed from: a */
    private static void m12396a(StringBuilder sb, hwg hwg) {
        if (hwg != hwg.f13519a) {
            int length = sb.length();
            sb.insert(0, hwg.mo8219a());
            sb.insert(sb.length() - length, '.');
            sb.insert(sb.length() - length, hwg.mo8220b());
            sb.insert(sb.length() - length, ':');
            sb.insert(sb.length() - length, hwg.mo8221c());
            sb.insert(sb.length() - length, ' ');
        }
    }

    /* renamed from: a */
    public static String m12392a(Object obj) {
        if (obj == null) {
            return "null";
        }
        try {
            if (!obj.getClass().isArray()) {
                return String.valueOf(obj);
            }
            if (obj instanceof int[]) {
                return Arrays.toString((int[]) obj);
            }
            if (obj instanceof long[]) {
                return Arrays.toString((long[]) obj);
            }
            if (obj instanceof byte[]) {
                return Arrays.toString((byte[]) obj);
            }
            if (obj instanceof char[]) {
                return Arrays.toString((char[]) obj);
            }
            if (obj instanceof short[]) {
                return Arrays.toString((short[]) obj);
            }
            if (obj instanceof float[]) {
                return Arrays.toString((float[]) obj);
            }
            if (obj instanceof double[]) {
                return Arrays.toString((double[]) obj);
            }
            return !(obj instanceof boolean[]) ? Arrays.toString((Object[]) obj) : Arrays.toString((boolean[]) obj);
        } catch (RuntimeException e) {
            return m12393a(obj, e);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
        if ((r8 instanceof java.math.BigDecimal) == false) goto L_0x0058;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0035, code lost:
        if ((r8 instanceof java.math.BigInteger) == false) goto L_0x0058;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0056, code lost:
        if (r0 == false) goto L_0x0058;
     */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x009f  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo8253a(java.lang.Object r8, p000.hwu r9, p000.hwv r10) {
        /*
            r7 = this;
            hww r0 = r9.f13548e
            int r0 = r0.ordinal()
            r1 = 4
            r2 = 3
            r3 = 2
            r4 = 1
            if (r0 == 0) goto L_0x0060
            if (r0 == r4) goto L_0x0054
            if (r0 == r3) goto L_0x0038
            if (r0 == r2) goto L_0x0023
            if (r0 != r1) goto L_0x0021
            boolean r0 = r8 instanceof java.lang.Double
            if (r0 != 0) goto L_0x0060
            boolean r0 = r8 instanceof java.lang.Float
            if (r0 != 0) goto L_0x0060
            boolean r0 = r8 instanceof java.math.BigDecimal
            if (r0 == 0) goto L_0x0058
            goto L_0x0060
        L_0x0021:
            r8 = 0
            throw r8
        L_0x0023:
            boolean r0 = r8 instanceof java.lang.Integer
            if (r0 != 0) goto L_0x0060
            boolean r0 = r8 instanceof java.lang.Long
            if (r0 != 0) goto L_0x0060
            boolean r0 = r8 instanceof java.lang.Byte
            if (r0 != 0) goto L_0x0060
            boolean r0 = r8 instanceof java.lang.Short
            if (r0 != 0) goto L_0x0060
            boolean r0 = r8 instanceof java.math.BigInteger
            if (r0 == 0) goto L_0x0058
            goto L_0x0060
        L_0x0038:
            boolean r0 = r8 instanceof java.lang.Character
            if (r0 != 0) goto L_0x0060
            boolean r0 = r8 instanceof java.lang.Integer
            if (r0 != 0) goto L_0x0048
            boolean r0 = r8 instanceof java.lang.Byte
            if (r0 != 0) goto L_0x0048
            boolean r0 = r8 instanceof java.lang.Short
            if (r0 == 0) goto L_0x0058
        L_0x0048:
            r0 = r8
            java.lang.Number r0 = (java.lang.Number) r0
            int r0 = r0.intValue()
            boolean r0 = java.lang.Character.isValidCodePoint(r0)
            goto L_0x0056
        L_0x0054:
            boolean r0 = r8 instanceof java.lang.Boolean
        L_0x0056:
            if (r0 != 0) goto L_0x0060
        L_0x0058:
            java.lang.StringBuilder r10 = r7.f13579c
            java.lang.String r9 = r9.f13550g
            m12398a((java.lang.StringBuilder) r10, (java.lang.Object) r8, (java.lang.String) r9)
            return
        L_0x0060:
            java.lang.StringBuilder r0 = r7.f13579c
            hwu r5 = p000.hwu.STRING
            int r5 = r9.ordinal()
            r6 = 0
            if (r5 == 0) goto L_0x0148
            if (r5 == r4) goto L_0x013e
            if (r5 == r3) goto L_0x0117
            if (r5 == r2) goto L_0x013e
            r1 = 5
            if (r5 == r1) goto L_0x0076
            goto L_0x015a
        L_0x0076:
            boolean r1 = r10.mo8233a()
            if (r1 != 0) goto L_0x0098
            int r1 = r10.f13553b
            r2 = r1 & 128(0x80, float:1.794E-43)
            if (r2 != 0) goto L_0x0085
            hwv r1 = p000.hwv.f13551a
            goto L_0x0099
        L_0x0085:
            r3 = -1
            if (r2 == r1) goto L_0x0089
            goto L_0x0092
        L_0x0089:
            int r1 = r10.f13554c
            if (r1 != r3) goto L_0x0092
            int r1 = r10.f13555d
            if (r1 != r3) goto L_0x0092
            goto L_0x0098
        L_0x0092:
            hwv r1 = new hwv
            r1.<init>(r2, r3, r3)
            goto L_0x0099
        L_0x0098:
            r1 = r10
        L_0x0099:
            boolean r1 = r1.equals(r10)
            if (r1 == 0) goto L_0x015a
            java.lang.Number r8 = (java.lang.Number) r8
            boolean r9 = r10.mo8235b()
            long r1 = r8.longValue()
            boolean r10 = r8 instanceof java.lang.Long
            if (r10 == 0) goto L_0x00b1
            m12395a((java.lang.StringBuilder) r0, (long) r1, (boolean) r9)
            return
        L_0x00b1:
            boolean r10 = r8 instanceof java.lang.Integer
            if (r10 == 0) goto L_0x00bf
            r3 = 4294967295(0xffffffff, double:2.1219957905E-314)
            long r1 = r1 & r3
            m12395a((java.lang.StringBuilder) r0, (long) r1, (boolean) r9)
            return
        L_0x00bf:
            boolean r10 = r8 instanceof java.lang.Byte
            if (r10 == 0) goto L_0x00ca
            r3 = 255(0xff, double:1.26E-321)
            long r1 = r1 & r3
            m12395a((java.lang.StringBuilder) r0, (long) r1, (boolean) r9)
            return
        L_0x00ca:
            boolean r10 = r8 instanceof java.lang.Short
            if (r10 == 0) goto L_0x00d6
            r3 = 65535(0xffff, double:3.23786E-319)
            long r1 = r1 & r3
            m12395a((java.lang.StringBuilder) r0, (long) r1, (boolean) r9)
            return
        L_0x00d6:
            boolean r10 = r8 instanceof java.math.BigInteger
            if (r10 == 0) goto L_0x00ee
            java.math.BigInteger r8 = (java.math.BigInteger) r8
            r10 = 16
            java.lang.String r8 = r8.toString(r10)
            if (r9 == 0) goto L_0x00ea
            java.util.Locale r9 = f13577a
            java.lang.String r8 = r8.toUpperCase(r9)
        L_0x00ea:
            r0.append(r8)
            return
        L_0x00ee:
            java.lang.RuntimeException r9 = new java.lang.RuntimeException
            java.lang.Class r8 = r8.getClass()
            java.lang.String r8 = java.lang.String.valueOf(r8)
            java.lang.String r10 = java.lang.String.valueOf(r8)
            int r10 = r10.length()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            int r10 = r10 + 25
            r0.<init>(r10)
            java.lang.String r10 = "unsupported number type: "
            r0.append(r10)
            r0.append(r8)
            java.lang.String r8 = r0.toString()
            r9.<init>(r8)
            throw r9
        L_0x0117:
            boolean r1 = r10.mo8233a()
            if (r1 == 0) goto L_0x015a
            boolean r9 = r8 instanceof java.lang.Character
            if (r9 == 0) goto L_0x0125
            r0.append(r8)
            return
        L_0x0125:
            java.lang.Number r8 = (java.lang.Number) r8
            int r8 = r8.intValue()
            boolean r9 = java.lang.Character.isBmpCodePoint(r8)
            if (r9 == 0) goto L_0x0136
            char r8 = (char) r8
            r0.append(r8)
            return
        L_0x0136:
            char[] r8 = java.lang.Character.toChars(r8)
            r0.append(r8)
            return
        L_0x013e:
            boolean r1 = r10.mo8233a()
            if (r1 == 0) goto L_0x015a
            r0.append(r8)
            return
        L_0x0148:
            boolean r2 = r8 instanceof java.util.Formattable
            if (r2 != 0) goto L_0x0190
            boolean r1 = r10.mo8233a()
            if (r1 == 0) goto L_0x015a
            java.lang.String r8 = m12392a(r8)
            r0.append(r8)
            return
        L_0x015a:
            java.lang.String r1 = r9.f13550g
            boolean r2 = r10.mo8233a()
            if (r2 != 0) goto L_0x0182
            char r9 = r9.f13547d
            boolean r1 = r10.mo8235b()
            if (r1 != 0) goto L_0x016b
            goto L_0x016f
        L_0x016b:
            r1 = 65503(0xffdf, float:9.1789E-41)
            r9 = r9 & r1
        L_0x016f:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "%"
            r1.<init>(r2)
            java.lang.StringBuilder r10 = r10.mo8232a((java.lang.StringBuilder) r1)
            char r9 = (char) r9
            r10.append(r9)
            java.lang.String r1 = r10.toString()
        L_0x0182:
            java.util.Locale r9 = f13577a
            java.lang.Object[] r10 = new java.lang.Object[r4]
            r10[r6] = r8
            java.lang.String r8 = java.lang.String.format(r9, r1, r10)
            r0.append(r8)
            return
        L_0x0190:
            java.util.Formattable r8 = (java.util.Formattable) r8
            int r9 = r10.f13553b
            r9 = r9 & 162(0xa2, float:2.27E-43)
            if (r9 == 0) goto L_0x01af
            r2 = r9 & 32
            if (r2 == 0) goto L_0x019d
            goto L_0x019e
        L_0x019d:
            r4 = 0
        L_0x019e:
            r2 = r9 & 128(0x80, float:1.794E-43)
            if (r2 == 0) goto L_0x01a3
            goto L_0x01a4
        L_0x01a3:
            r3 = 0
        L_0x01a4:
            r2 = r4 | r3
            r9 = r9 & 2
            if (r9 == 0) goto L_0x01ab
            goto L_0x01ad
        L_0x01ab:
            r1 = 0
        L_0x01ad:
            r9 = r2 | r1
        L_0x01af:
            int r1 = r0.length()
            java.util.Formatter r2 = new java.util.Formatter
            java.util.Locale r3 = f13577a
            r2.<init>(r0, r3)
            int r3 = r10.f13554c     // Catch:{ RuntimeException -> 0x01c2 }
            int r10 = r10.f13555d     // Catch:{ RuntimeException -> 0x01c2 }
            r8.formatTo(r2, r9, r3, r10)     // Catch:{ RuntimeException -> 0x01c2 }
            return
        L_0x01c2:
            r9 = move-exception
            r0.setLength(r1)
            java.lang.Appendable r10 = r2.out()     // Catch:{ IOException -> 0x01d2 }
            java.lang.String r8 = m12393a((java.lang.Object) r8, (java.lang.RuntimeException) r9)     // Catch:{ IOException -> 0x01d2 }
            r10.append(r8)     // Catch:{ IOException -> 0x01d2 }
            return
        L_0x01d2:
            r8 = move-exception
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.hxi.mo8253a(java.lang.Object, hwu, hwv):void");
    }
}
