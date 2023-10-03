package p000;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

/* renamed from: di */
/* compiled from: PG */
public final class C0094di implements Serializable {

    /* renamed from: b */
    public static final Pattern f6577b = Pattern.compile("\\s*,\\s*");

    /* renamed from: c */
    public static final Pattern f6578c = Pattern.compile("\\s*~\\s*");

    /* renamed from: d */
    private static final C0086da f6579d = new C0082cx();

    /* renamed from: e */
    private static final C0092dg f6580e = new C0092dg("other", f6579d, (C0089dd) null, (C0089dd) null);

    /* renamed from: f */
    private static final C0094di f6581f;

    /* renamed from: g */
    private static final Pattern f6582g = Pattern.compile("\\s*\\Q\\E@\\s*");

    /* renamed from: h */
    private static final Pattern f6583h = Pattern.compile("\\s*or\\s*");

    /* renamed from: i */
    private static final Pattern f6584i = Pattern.compile("\\s*and\\s*");

    /* renamed from: j */
    private static final Pattern f6585j = Pattern.compile("\\s*;\\s*");
    public static final long serialVersionUID = 1;

    /* renamed from: a */
    public final C0093dh f6586a;

    static {
        C0093dh dhVar = new C0093dh((byte[]) null);
        dhVar.mo4130a(f6580e);
        f6581f = new C0094di(dhVar);
        Pattern.compile("\\s*\\Q..\\E\\s*");
    }

    private C0094di(C0093dh dhVar) {
        this.f6586a = dhVar;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        List list = dhVar.f6530b;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            linkedHashSet.add(((C0092dg) list.get(i)).f6484a);
        }
        Collections.unmodifiableSet(linkedHashSet);
    }

    /* renamed from: a */
    public static void m6138a(StringBuilder sb, double d, double d2, boolean z) {
        if (z) {
            sb.append(",");
        }
        if (d != d2) {
            String a = m6135a(d);
            String a2 = m6135a(d2);
            StringBuilder sb2 = new StringBuilder(String.valueOf(a).length() + 2 + String.valueOf(a2).length());
            sb2.append(a);
            sb2.append("..");
            sb2.append(a2);
            sb.append(sb2.toString());
            return;
        }
        sb.append(m6135a(d));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r2 = (p000.C0094di) r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r2) {
        /*
            r1 = this;
            boolean r0 = r2 instanceof p000.C0094di
            if (r0 == 0) goto L_0x0018
            di r2 = (p000.C0094di) r2
            if (r2 == 0) goto L_0x0018
            java.lang.String r0 = r1.toString()
            java.lang.String r2 = r2.toString()
            boolean r2 = r0.equals(r2)
            if (r2 == 0) goto L_0x0018
            r2 = 1
            return r2
        L_0x0018:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0094di.equals(java.lang.Object):boolean");
    }

    /* renamed from: a */
    public static C0094di m6134a(Locale locale, int i) {
        Map map;
        C0095dj djVar = C0095dj.f6643c;
        djVar.mo4159a();
        if (i != 1) {
            map = djVar.f6646b;
        } else {
            map = djVar.f6645a;
        }
        String str = (String) map.get(locale.getLanguage());
        if (str == null || str.trim().length() == 0) {
            return f6581f;
        }
        C0094di a = djVar.mo4158a(str);
        if (a == null) {
            return f6581f;
        }
        return a;
    }

    /* renamed from: a */
    private static String m6135a(double d) {
        long j = (long) d;
        return d == ((double) j) ? String.valueOf(j) : String.valueOf(d);
    }

    @Deprecated
    public final int hashCode() {
        return this.f6586a.hashCode();
    }

    /* renamed from: a */
    private static String m6136a(String[] strArr, int i, String str) {
        if (i < strArr.length) {
            return strArr[i];
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 26);
        sb.append("missing token at end of '");
        sb.append(str);
        sb.append("'");
        throw new ParseException(sb.toString(), -1);
    }

    /* renamed from: a */
    public static C0094di m6133a(String str) {
        String trim = str.trim();
        if (trim.length() == 0) {
            return f6581f;
        }
        C0092dg dgVar = null;
        C0093dh dhVar = new C0093dh((byte[]) null);
        if (trim.endsWith(";")) {
            trim = trim.substring(0, trim.length() - 1);
        }
        String[] split = f6585j.split(trim);
        for (String trim2 : split) {
            C0092dg b = m6139b(trim2.trim());
            boolean z = true;
            if (b.f6486c == null && b.f6487d == null) {
                z = false;
            }
            dhVar.f6529a |= z;
            dhVar.mo4130a(b);
        }
        Iterator it = dhVar.f6530b.iterator();
        while (it.hasNext()) {
            C0092dg dgVar2 = (C0092dg) it.next();
            if ("other".equals(dgVar2.f6484a)) {
                it.remove();
                dgVar = dgVar2;
            }
        }
        if (dgVar == null) {
            dgVar = m6139b("other:");
        }
        dhVar.f6530b.add(dgVar);
        return new C0094di(dhVar);
    }

    /* JADX WARNING: Removed duplicated region for block: B:125:0x0204 A[SYNTHETIC, Splitter:B:125:0x0204] */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x0208  */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x020c  */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x0211  */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x0214  */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x0219  */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x021e  */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x0223  */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x022a  */
    /* JADX WARNING: Removed duplicated region for block: B:175:0x02e9  */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x02ec  */
    /* JADX WARNING: Removed duplicated region for block: B:182:0x031f  */
    /* JADX WARNING: Removed duplicated region for block: B:201:0x038a  */
    /* JADX WARNING: Removed duplicated region for block: B:204:0x0398  */
    /* JADX WARNING: Removed duplicated region for block: B:237:0x046e  */
    /* JADX WARNING: Removed duplicated region for block: B:239:0x0483  */
    /* JADX WARNING: Removed duplicated region for block: B:240:0x048c  */
    /* JADX WARNING: Removed duplicated region for block: B:257:0x04f5  */
    /* JADX WARNING: Removed duplicated region for block: B:272:0x0449 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00e6  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00ea  */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static p000.C0092dg m6139b(java.lang.String r40) {
        /*
            int r0 = r40.length()
            if (r0 == 0) goto L_0x0524
            java.util.Locale r0 = java.util.Locale.ENGLISH
            r1 = r40
            java.lang.String r0 = r1.toLowerCase(r0)
            r1 = 58
            int r1 = r0.indexOf(r1)
            r2 = -1
            r3 = 0
            if (r1 == r2) goto L_0x04fd
            java.lang.String r4 = r0.substring(r3, r1)
            java.lang.String r4 = r4.trim()
            r5 = 0
        L_0x0022:
            int r6 = r4.length()
            if (r5 >= r6) goto L_0x005d
            char r6 = r4.charAt(r5)
            r7 = 97
            if (r6 < r7) goto L_0x0037
            r7 = 122(0x7a, float:1.71E-43)
            if (r6 > r7) goto L_0x0037
            int r5 = r5 + 1
            goto L_0x0022
        L_0x0037:
            java.text.ParseException r0 = new java.text.ParseException
            java.lang.String r1 = java.lang.String.valueOf(r4)
            int r1 = r1.length()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            int r1 = r1 + 22
            r2.<init>(r1)
            java.lang.String r1 = "keyword '"
            r2.append(r1)
            r2.append(r4)
            java.lang.String r1 = " is not valid"
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            r0.<init>(r1, r3)
            throw r0
        L_0x005d:
            r5 = 1
            int r1 = r1 + r5
            java.lang.String r0 = r0.substring(r1)
            java.lang.String r0 = r0.trim()
            java.util.regex.Pattern r1 = f6582g
            java.lang.String[] r1 = r1.split(r0)
            int r6 = r1.length
            r7 = 3
            r9 = 2
            if (r6 == r5) goto L_0x00d4
            if (r6 == r9) goto L_0x00c5
            if (r6 == r7) goto L_0x0092
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r2 = "Too many samples in "
            int r3 = r0.length()
            if (r3 != 0) goto L_0x008a
            java.lang.String r0 = new java.lang.String
            r0.<init>(r2)
            goto L_0x008e
        L_0x008a:
            java.lang.String r0 = r2.concat(r0)
        L_0x008e:
            r1.<init>(r0)
            throw r1
        L_0x0092:
            r6 = r1[r5]
            dd r6 = p000.C0089dd.m5938a(r6)
            r10 = r1[r9]
            dd r10 = p000.C0089dd.m5938a(r10)
            int r11 = r6.f6317a
            if (r11 == r5) goto L_0x00a4
            goto L_0x00a9
        L_0x00a4:
            int r11 = r10.f6317a
            if (r11 != r9) goto L_0x00a9
            goto L_0x00d6
        L_0x00a9:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r2 = "Must have @integer then @decimal in "
            int r3 = r0.length()
            if (r3 != 0) goto L_0x00bd
            java.lang.String r0 = new java.lang.String
            r0.<init>(r2)
            goto L_0x00c1
        L_0x00bd:
            java.lang.String r0 = r2.concat(r0)
        L_0x00c1:
            r1.<init>(r0)
            throw r1
        L_0x00c5:
            r0 = r1[r5]
            dd r6 = p000.C0089dd.m5938a(r0)
            int r0 = r6.f6317a
            if (r0 != r9) goto L_0x00d3
            r10 = r6
            r6 = 0
            goto L_0x00d6
        L_0x00d3:
            goto L_0x00d5
        L_0x00d4:
            r6 = 0
        L_0x00d5:
            r10 = 0
        L_0x00d6:
            java.lang.String r0 = "other"
            boolean r0 = r4.equals(r0)
            r11 = r1[r3]
            int r11 = r11.length()
            if (r11 == 0) goto L_0x00e6
            r11 = 0
            goto L_0x00e8
        L_0x00e6:
            r11 = 1
        L_0x00e8:
            if (r0 != r11) goto L_0x04f5
            if (r0 != 0) goto L_0x04e1
            r0 = r1[r3]
            java.util.regex.Pattern r1 = f6583h
            java.lang.String[] r0 = r1.split(r0)
            r1 = 0
            r11 = 0
        L_0x00f6:
            int r12 = r0.length
            if (r1 >= r12) goto L_0x04d9
            java.util.regex.Pattern r12 = f6584i
            r13 = r0[r1]
            java.lang.String[] r12 = r12.split(r13)
            r13 = 0
            r14 = 0
        L_0x0103:
            int r15 = r12.length
            if (r13 >= r15) goto L_0x04ae
            da r15 = f6579d
            r16 = r12[r13]
            java.lang.String r2 = r16.trim()
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            r7 = 0
            r9 = -1
        L_0x0115:
            int r5 = r2.length()
            if (r7 >= r5) goto L_0x017b
            char r5 = r2.charAt(r7)
            r3 = 32
            if (r5 <= r3) goto L_0x0124
            goto L_0x0138
        L_0x0124:
            if (r5 == r3) goto L_0x016b
            r3 = 9
            if (r5 == r3) goto L_0x016b
            r3 = 10
            if (r5 == r3) goto L_0x016b
            r3 = 12
            if (r5 == r3) goto L_0x016b
            r3 = 13
            if (r5 != r3) goto L_0x0138
            goto L_0x016b
        L_0x0138:
            r3 = 61
            if (r5 > r3) goto L_0x0167
            r3 = 33
            if (r5 < r3) goto L_0x0167
            if (r5 == r3) goto L_0x0152
            r3 = 37
            if (r5 == r3) goto L_0x0152
            r3 = 44
            if (r5 == r3) goto L_0x0152
            r3 = 46
            if (r5 == r3) goto L_0x0152
            r3 = 61
            if (r5 != r3) goto L_0x0167
        L_0x0152:
            if (r9 >= 0) goto L_0x0155
            goto L_0x015c
        L_0x0155:
            java.lang.String r3 = r2.substring(r9, r7)
            r8.add(r3)
        L_0x015c:
            int r3 = r7 + 1
            java.lang.String r3 = r2.substring(r7, r3)
            r8.add(r3)
            r9 = -1
            goto L_0x0176
        L_0x0167:
            if (r9 >= 0) goto L_0x0176
            r9 = r7
            goto L_0x0176
        L_0x016b:
            if (r9 < 0) goto L_0x0176
            java.lang.String r3 = r2.substring(r9, r7)
            r8.add(r3)
            r9 = -1
        L_0x0176:
            int r7 = r7 + 1
            r3 = 0
            r5 = 1
            goto L_0x0115
        L_0x017b:
            if (r9 < 0) goto L_0x0184
            java.lang.String r3 = r2.substring(r9)
            r8.add(r3)
        L_0x0184:
            int r3 = r8.size()
            java.lang.String[] r3 = new java.lang.String[r3]
            java.lang.Object[] r3 = r8.toArray(r3)
            java.lang.String[] r3 = (java.lang.String[]) r3
            r5 = 0
            r7 = r3[r5]
            int r5 = r7.hashCode()
            r8 = 102(0x66, float:1.43E-43)
            r9 = 5
            r18 = 6
            r19 = 4
            if (r5 == r8) goto L_0x01f6
            r8 = 110(0x6e, float:1.54E-43)
            if (r5 == r8) goto L_0x01ec
            r8 = 116(0x74, float:1.63E-43)
            if (r5 == r8) goto L_0x01e2
            r8 = 105(0x69, float:1.47E-43)
            if (r5 == r8) goto L_0x01d8
            r8 = 106(0x6a, float:1.49E-43)
            if (r5 == r8) goto L_0x01ce
            r8 = 118(0x76, float:1.65E-43)
            if (r5 == r8) goto L_0x01c4
            r8 = 119(0x77, float:1.67E-43)
            if (r5 == r8) goto L_0x01b9
        L_0x01b8:
            goto L_0x0200
        L_0x01b9:
            java.lang.String r5 = "w"
            boolean r5 = r7.equals(r5)
            if (r5 == 0) goto L_0x01b8
            r5 = 5
            goto L_0x0201
        L_0x01c4:
            java.lang.String r5 = "v"
            boolean r5 = r7.equals(r5)
            if (r5 == 0) goto L_0x01b8
            r5 = 4
            goto L_0x0201
        L_0x01ce:
            java.lang.String r5 = "j"
            boolean r5 = r7.equals(r5)
            if (r5 == 0) goto L_0x01b8
            r5 = 6
            goto L_0x0201
        L_0x01d8:
            java.lang.String r5 = "i"
            boolean r5 = r7.equals(r5)
            if (r5 == 0) goto L_0x01b8
            r5 = 1
            goto L_0x0201
        L_0x01e2:
            java.lang.String r5 = "t"
            boolean r5 = r7.equals(r5)
            if (r5 == 0) goto L_0x01b8
            r5 = 3
            goto L_0x0201
        L_0x01ec:
            java.lang.String r5 = "n"
            boolean r5 = r7.equals(r5)
            if (r5 == 0) goto L_0x01b8
            r5 = 0
            goto L_0x0201
        L_0x01f6:
            java.lang.String r5 = "f"
            boolean r5 = r7.equals(r5)
            if (r5 == 0) goto L_0x01b8
            r5 = 2
            goto L_0x0201
        L_0x0200:
            r5 = -1
        L_0x0201:
            switch(r5) {
                case 0: goto L_0x0223;
                case 1: goto L_0x021e;
                case 2: goto L_0x0219;
                case 3: goto L_0x0214;
                case 4: goto L_0x0211;
                case 5: goto L_0x020c;
                case 6: goto L_0x0208;
                default: goto L_0x0204;
            }
        L_0x0204:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ Exception -> 0x04a8 }
            goto L_0x04a4
        L_0x0208:
            r5 = 7
            r23 = 7
            goto L_0x0226
        L_0x020c:
            r23 = 6
            goto L_0x0226
        L_0x0211:
            r23 = 5
            goto L_0x0226
        L_0x0214:
            r23 = 4
            goto L_0x0226
        L_0x0219:
            r23 = 3
            goto L_0x0226
        L_0x021e:
            r23 = 2
            goto L_0x0226
        L_0x0223:
            r23 = 1
        L_0x0226:
            int r5 = r3.length
            r8 = 1
            if (r5 <= r8) goto L_0x046e
            r5 = r3[r8]
            java.lang.String r7 = "mod"
            boolean r7 = r7.equals(r5)
            if (r7 != 0) goto L_0x0242
            java.lang.String r7 = "%"
            boolean r7 = r7.equals(r5)
            if (r7 == 0) goto L_0x023d
            goto L_0x0242
        L_0x023d:
            r7 = 2
            r9 = 3
            r15 = 0
            goto L_0x0251
        L_0x0242:
            r5 = 2
            r7 = r3[r5]
            int r5 = java.lang.Integer.parseInt(r7)
            r9 = 3
            java.lang.String r7 = m6136a(r3, r9, r2)
            r15 = r5
            r5 = r7
            r7 = 4
        L_0x0251:
            java.lang.String r8 = "not"
            boolean r17 = r8.equals(r5)
            java.lang.String r9 = "="
            if (r17 == 0) goto L_0x0275
            int r5 = r7 + 1
            java.lang.String r7 = m6136a(r3, r7, r2)
            boolean r17 = r9.equals(r7)
            if (r17 != 0) goto L_0x0270
            r17 = r0
            r0 = 0
            r37 = r7
            r7 = r5
            r5 = r37
            goto L_0x0295
        L_0x0270:
            java.text.ParseException r0 = m6137a((java.lang.String) r7, (java.lang.String) r2)
            throw r0
        L_0x0275:
            r17 = r0
            java.lang.String r0 = "!"
            boolean r0 = r0.equals(r5)
            if (r0 == 0) goto L_0x0294
            int r0 = r7 + 1
            java.lang.String r5 = m6136a(r3, r7, r2)
            boolean r7 = r9.equals(r5)
            if (r7 == 0) goto L_0x028f
            r7 = r0
            r0 = 0
            goto L_0x0295
        L_0x028f:
            java.text.ParseException r0 = m6137a((java.lang.String) r5, (java.lang.String) r2)
            throw r0
        L_0x0294:
            r0 = 1
        L_0x0295:
            r19 = r12
            java.lang.String r12 = "is"
            boolean r20 = r12.equals(r5)
            if (r20 == 0) goto L_0x02a2
            r30 = r4
            goto L_0x02cb
        L_0x02a2:
            r30 = r4
            java.lang.String r4 = "in"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x02cb
            boolean r4 = r9.equals(r5)
            if (r4 != 0) goto L_0x02cb
            java.lang.String r4 = "within"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x02c6
            int r4 = r7 + 1
            java.lang.String r5 = m6136a(r3, r7, r2)
            r7 = 0
            r24 = 0
            goto L_0x02e2
        L_0x02c6:
            java.text.ParseException r0 = m6137a((java.lang.String) r5, (java.lang.String) r2)
            throw r0
        L_0x02cb:
            boolean r4 = r12.equals(r5)
            if (r4 != 0) goto L_0x02d2
            goto L_0x02d4
        L_0x02d2:
            if (r0 == 0) goto L_0x0469
        L_0x02d4:
            int r5 = r7 + 1
            java.lang.String r7 = m6136a(r3, r7, r2)
            r24 = 1
            r37 = r7
            r7 = r4
            r4 = r5
            r5 = r37
        L_0x02e2:
            boolean r8 = r8.equals(r5)
            if (r8 != 0) goto L_0x02ec
            r22 = r0
            goto L_0x0300
        L_0x02ec:
            if (r7 == 0) goto L_0x02ef
            goto L_0x02f1
        L_0x02ef:
            if (r0 == 0) goto L_0x0464
        L_0x02f1:
            r0 = r0 ^ 1
            int r5 = r4 + 1
            java.lang.String r4 = m6136a(r3, r4, r2)
            r22 = r0
            r37 = r5
            r5 = r4
            r4 = r37
        L_0x0300:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r8 = 4890909195324358656(0x43e0000000000000, double:9.223372036854776E18)
            r20 = -4332462841530417152(0xc3e0000000000000, double:-9.223372036854776E18)
            r12 = r10
            r31 = r11
            r10 = r20
        L_0x030e:
            r33 = r12
            r32 = r13
            long r12 = java.lang.Long.parseLong(r5)
            r20 = r5
            int r5 = r3.length
            r34 = r6
            java.lang.String r6 = ","
            if (r4 >= r5) goto L_0x038a
            r35 = r1
            int r1 = r4 + 1
            java.lang.String r4 = m6136a(r3, r4, r2)
            r36 = r14
            java.lang.String r14 = "."
            boolean r20 = r4.equals(r14)
            if (r20 == 0) goto L_0x0374
            int r4 = r1 + 1
            java.lang.String r1 = m6136a(r3, r1, r2)
            boolean r14 = r1.equals(r14)
            if (r14 == 0) goto L_0x036f
            int r1 = r4 + 1
            java.lang.String r4 = m6136a(r3, r4, r2)
            long r20 = java.lang.Long.parseLong(r4)
            if (r1 >= r5) goto L_0x0362
            int r4 = r1 + 1
            java.lang.String r1 = m6136a(r3, r1, r2)
            boolean r14 = r1.equals(r6)
            if (r14 == 0) goto L_0x035d
            r14 = r7
            r37 = r20
            r20 = r6
            r6 = r37
            goto L_0x0394
        L_0x035d:
            java.text.ParseException r0 = m6137a((java.lang.String) r1, (java.lang.String) r2)
            throw r0
        L_0x0362:
            r14 = r7
            r37 = r4
            r4 = r1
            r1 = r37
            r38 = r20
            r20 = r6
            r6 = r38
            goto L_0x0394
        L_0x036f:
            java.text.ParseException r0 = m6137a((java.lang.String) r1, (java.lang.String) r2)
            throw r0
        L_0x0374:
            boolean r14 = r4.equals(r6)
            if (r14 == 0) goto L_0x0385
            r20 = r6
            r14 = r7
            r6 = r12
            r37 = r4
            r4 = r1
            r1 = r37
            goto L_0x0394
        L_0x0385:
            java.text.ParseException r0 = m6137a((java.lang.String) r4, (java.lang.String) r2)
            throw r0
        L_0x038a:
            r35 = r1
            r36 = r14
            r14 = r7
            r1 = r20
            r20 = r6
            r6 = r12
        L_0x0394:
            int r21 = (r12 > r6 ? 1 : (r12 == r6 ? 0 : -1))
            if (r21 > 0) goto L_0x0449
            if (r15 == 0) goto L_0x03bf
            r21 = r3
            r25 = r4
            long r3 = (long) r15
            int r26 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r26 >= 0) goto L_0x03a4
            goto L_0x03c3
        L_0x03a4:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r1 = 36
            r0.<init>(r1)
            r0.append(r6)
            java.lang.String r1 = ">mod="
            r0.append(r1)
            r0.append(r15)
            java.lang.String r0 = r0.toString()
            java.text.ParseException r0 = m6137a((java.lang.String) r0, (java.lang.String) r2)
            throw r0
        L_0x03bf:
            r21 = r3
            r25 = r4
        L_0x03c3:
            java.lang.Long r3 = java.lang.Long.valueOf(r12)
            r0.add(r3)
            java.lang.Long r3 = java.lang.Long.valueOf(r6)
            r0.add(r3)
            double r3 = (double) r12
            double r8 = java.lang.Math.min(r8, r3)
            double r3 = (double) r6
            double r10 = java.lang.Math.max(r10, r3)
            r4 = r25
            if (r4 >= r5) goto L_0x03f6
            int r1 = r4 + 1
            r3 = r21
            java.lang.String r5 = m6136a(r3, r4, r2)
            r4 = r1
            r7 = r14
            r13 = r32
            r12 = r33
            r6 = r34
            r1 = r35
            r14 = r36
            goto L_0x030e
        L_0x03f6:
            r3 = r20
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x0444
            int r1 = r0.size()
            r3 = 2
            if (r1 == r3) goto L_0x0421
            int r1 = r0.size()
            long[] r4 = new long[r1]
            r5 = 0
        L_0x040d:
            if (r5 >= r1) goto L_0x041e
            java.lang.Object r6 = r0.get(r5)
            java.lang.Long r6 = (java.lang.Long) r6
            long r6 = r6.longValue()
            r4[r5] = r6
            int r5 = r5 + 1
            goto L_0x040d
        L_0x041e:
            r29 = r4
            goto L_0x0424
        L_0x0421:
            r29 = 0
        L_0x0424:
            int r0 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r0 != 0) goto L_0x0429
            goto L_0x0435
        L_0x0429:
            if (r14 == 0) goto L_0x0435
            if (r22 == 0) goto L_0x042e
            goto L_0x0435
        L_0x042e:
            java.lang.String r0 = "is not <range>"
            java.text.ParseException r0 = m6137a((java.lang.String) r0, (java.lang.String) r2)
            throw r0
        L_0x0435:
            df r0 = new df
            r20 = r0
            r21 = r15
            r25 = r8
            r27 = r10
            r20.<init>(r21, r22, r23, r24, r25, r27, r29)
            r15 = r0
            goto L_0x0481
        L_0x0444:
            java.text.ParseException r0 = m6137a((java.lang.String) r1, (java.lang.String) r2)
            throw r0
        L_0x0449:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r1 = 41
            r0.<init>(r1)
            r0.append(r12)
            java.lang.String r1 = "~"
            r0.append(r1)
            r0.append(r6)
            java.lang.String r0 = r0.toString()
            java.text.ParseException r0 = m6137a((java.lang.String) r0, (java.lang.String) r2)
            throw r0
        L_0x0464:
            java.text.ParseException r0 = m6137a((java.lang.String) r5, (java.lang.String) r2)
            throw r0
        L_0x0469:
            java.text.ParseException r0 = m6137a((java.lang.String) r5, (java.lang.String) r2)
            throw r0
        L_0x046e:
            r17 = r0
            r35 = r1
            r30 = r4
            r34 = r6
            r33 = r10
            r31 = r11
            r19 = r12
            r32 = r13
            r36 = r14
            r3 = 2
        L_0x0481:
            if (r36 == 0) goto L_0x048c
            cy r0 = new cy
            r8 = r36
            r0.<init>(r8, r15)
            r14 = r0
            goto L_0x048d
        L_0x048c:
            r14 = r15
        L_0x048d:
            int r13 = r32 + 1
            r0 = r17
            r12 = r19
            r4 = r30
            r11 = r31
            r10 = r33
            r6 = r34
            r1 = r35
            r2 = -1
            r3 = 0
            r5 = 1
            r7 = 3
            r9 = 2
            goto L_0x0103
        L_0x04a4:
            r0.<init>()     // Catch:{ Exception -> 0x04a8 }
            throw r0     // Catch:{ Exception -> 0x04a8 }
        L_0x04a8:
            r0 = move-exception
            java.text.ParseException r0 = m6137a((java.lang.String) r7, (java.lang.String) r2)
            throw r0
        L_0x04ae:
            r17 = r0
            r35 = r1
            r30 = r4
            r34 = r6
            r33 = r10
            r31 = r11
            r8 = r14
            r3 = 2
            if (r31 == 0) goto L_0x04c7
            de r0 = new de
            r1 = r31
            r0.<init>(r1, r8)
            r11 = r0
            goto L_0x04c8
        L_0x04c7:
            r11 = r8
        L_0x04c8:
            int r1 = r35 + 1
            r0 = r17
            r4 = r30
            r10 = r33
            r6 = r34
            r2 = -1
            r3 = 0
            r5 = 1
            r7 = 3
            r9 = 2
            goto L_0x00f6
        L_0x04d9:
            r30 = r4
            r34 = r6
            r33 = r10
            r1 = r11
            goto L_0x04e9
        L_0x04e1:
            r30 = r4
            r34 = r6
            r33 = r10
            da r11 = f6579d
        L_0x04e9:
            dg r0 = new dg
            r1 = r30
            r8 = r33
            r6 = r34
            r0.<init>(r1, r11, r6, r8)
            return r0
        L_0x04f5:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "The keyword 'other' must have no constraints, just samples."
            r0.<init>(r1)
            throw r0
        L_0x04fd:
            java.text.ParseException r1 = new java.text.ParseException
            java.lang.String r2 = java.lang.String.valueOf(r0)
            int r2 = r2.length()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            int r2 = r2 + 34
            r3.<init>(r2)
            java.lang.String r2 = "missing ':' in rule description '"
            r3.append(r2)
            r3.append(r0)
            java.lang.String r0 = "'"
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r2 = 0
            r1.<init>(r0, r2)
            throw r1
        L_0x0524:
            dg r0 = f6580e
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0094di.m6139b(java.lang.String):dg");
    }

    public final String toString() {
        return this.f6586a.toString();
    }

    /* renamed from: a */
    private static ParseException m6137a(String str, String str2) {
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 25 + String.valueOf(str2).length());
        sb.append("unexpected token '");
        sb.append(str);
        sb.append("' in '");
        sb.append(str2);
        sb.append("'");
        return new ParseException(sb.toString(), -1);
    }
}
