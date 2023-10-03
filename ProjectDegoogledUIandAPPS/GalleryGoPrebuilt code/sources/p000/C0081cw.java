package p000;

import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.FieldPosition;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* renamed from: cw */
/* compiled from: PG */
public final class C0081cw extends Format {

    /* renamed from: h */
    private static final String[] f5765h = {"number", "date", "time", "spellout", "ordinal", "duration"};

    /* renamed from: i */
    private static final String[] f5766i = {"", "currency", "percent", "integer"};

    /* renamed from: j */
    private static final String[] f5767j = {"", "short", "medium", "long", "full"};

    /* renamed from: k */
    private static final Locale f5768k = new Locale("");
    public static final long serialVersionUID = 7136212545847378652L;

    /* renamed from: a */
    private transient Locale f5769a;

    /* renamed from: b */
    private transient C0097dl f5770b;

    /* renamed from: c */
    private transient Map f5771c;

    /* renamed from: d */
    private transient DateFormat f5772d;

    /* renamed from: e */
    private transient NumberFormat f5773e;

    /* renamed from: f */
    private transient C0080cv f5774f;

    /* renamed from: g */
    private transient C0080cv f5775g;

    private C0081cw(String str, Locale locale) {
        Object obj;
        this.f5769a = locale;
        try {
            C0097dl dlVar = this.f5770b;
            if (dlVar == null) {
                this.f5770b = new C0097dl(str);
            } else {
                dlVar.mo4194a(str);
            }
            Map map = this.f5771c;
            if (map != null) {
                map.clear();
            }
            int a = this.f5770b.mo4191a() - 2;
            int i = 1;
            while (i < a) {
                C0096dk a2 = this.f5770b.mo4192a(i);
                if (a2.f6701e == 6) {
                    if (a2.mo4170b() == 2) {
                        int i2 = i + 2;
                        C0097dl dlVar2 = this.f5770b;
                        int i3 = i2 + 1;
                        String a3 = dlVar2.mo4193a(dlVar2.mo4192a(i2));
                        String str2 = "";
                        C0096dk a4 = this.f5770b.mo4192a(i3);
                        if (a4.f6701e == 11) {
                            str2 = this.f5770b.mo4193a(a4);
                            i3++;
                        }
                        int a5 = m5487a(a3, f5765h);
                        if (a5 == 0) {
                            int a6 = m5487a(str2, f5766i);
                            obj = a6 != 0 ? a6 != 1 ? a6 != 2 ? a6 != 3 ? new DecimalFormat(str2, new DecimalFormatSymbols(this.f5769a)) : NumberFormat.getIntegerInstance(this.f5769a) : NumberFormat.getPercentInstance(this.f5769a) : NumberFormat.getCurrencyInstance(this.f5769a) : NumberFormat.getInstance(this.f5769a);
                        } else if (a5 == 1) {
                            int a7 = m5487a(str2, f5767j);
                            obj = a7 != 0 ? a7 != 1 ? a7 != 2 ? a7 != 3 ? a7 != 4 ? new SimpleDateFormat(str2, this.f5769a) : DateFormat.getDateInstance(0, this.f5769a) : DateFormat.getDateInstance(1, this.f5769a) : DateFormat.getDateInstance(2, this.f5769a) : DateFormat.getDateInstance(3, this.f5769a) : DateFormat.getDateInstance(2, this.f5769a);
                        } else if (a5 == 2) {
                            int a8 = m5487a(str2, f5767j);
                            obj = a8 != 0 ? a8 != 1 ? a8 != 2 ? a8 != 3 ? a8 != 4 ? new SimpleDateFormat(str2, this.f5769a) : DateFormat.getTimeInstance(0, this.f5769a) : DateFormat.getTimeInstance(1, this.f5769a) : DateFormat.getTimeInstance(2, this.f5769a) : DateFormat.getTimeInstance(3, this.f5769a) : DateFormat.getTimeInstance(2, this.f5769a);
                        } else {
                            StringBuilder sb = new StringBuilder(String.valueOf(a3).length() + 22);
                            sb.append("Unknown format type \"");
                            sb.append(a3);
                            sb.append("\"");
                            throw new IllegalArgumentException(sb.toString());
                        }
                        if (this.f5771c == null) {
                            this.f5771c = new HashMap();
                        }
                        this.f5771c.put(Integer.valueOf(i), obj);
                        i = i3;
                    } else {
                        continue;
                    }
                }
                i++;
            }
        } catch (RuntimeException e) {
            C0097dl dlVar3 = this.f5770b;
            if (dlVar3 != null) {
                dlVar3.f6758a = null;
                dlVar3.f6761d = false;
                dlVar3.f6759b.clear();
                ArrayList arrayList = dlVar3.f6760c;
                if (arrayList != null) {
                    arrayList.clear();
                }
            }
            Map map2 = this.f5771c;
            if (map2 != null) {
                map2.clear();
            }
            throw e;
        }
    }

    /* renamed from: a */
    private static final int m5487a(String str, String[] strArr) {
        String lowerCase = C0074cp.m5204a(str).toLowerCase(f5768k);
        for (int i = 0; i < strArr.length; i++) {
            if (lowerCase.equals(strArr[i])) {
                return i;
            }
        }
        return -1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:112:0x028b, code lost:
        r7 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x02b8, code lost:
        if (r9 == 2) goto L_0x02ba;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x035b, code lost:
        if (r5.mo4195a(r1, r9) != false) goto L_0x036f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x0413, code lost:
        r2 = r23;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void m5491a(int r30, p000.C0079cu r31, java.lang.Object[] r32, java.util.Map r33, java.lang.Object[] r34, p000.C0076cr r35, java.text.FieldPosition r36) {
        /*
            r29 = this;
            r8 = r29
            r0 = r30
            r9 = r31
            r10 = r32
            r11 = r33
            r12 = r35
            dl r1 = r8.f5770b
            java.lang.String r13 = r1.f6758a
            dk r1 = r1.mo4192a((int) r0)
            int r1 = r1.mo4169a()
            r14 = 1
            int r0 = r0 + r14
            r2 = r1
            r1 = r0
            r0 = r36
        L_0x001e:
            dl r3 = r8.f5770b
            dk r3 = r3.mo4192a((int) r1)
            int r4 = r3.f6701e
            int r5 = r3.f6697a
            java.lang.Appendable r6 = r12.f5449a     // Catch:{ IOException -> 0x0597 }
            r6.append(r13, r2, r5)     // Catch:{ IOException -> 0x0597 }
            int r6 = r12.f5450b     // Catch:{ IOException -> 0x0597 }
            int r5 = r5 - r2
            int r6 = r6 + r5
            r12.f5450b = r6     // Catch:{ IOException -> 0x0597 }
            r2 = 2
            if (r4 == r2) goto L_0x0596
            int r5 = r3.mo4169a()
            r6 = 5
            if (r4 == r6) goto L_0x0569
            r7 = 6
            if (r4 != r7) goto L_0x0565
            dl r4 = r8.f5770b
            int r15 = r4.mo4197b((int) r1)
            int r3 = r3.mo4170b()
            int r1 = r1 + 1
            dl r4 = r8.f5770b
            dk r4 = r4.mo4192a((int) r1)
            dl r5 = r8.f5770b
            java.lang.String r5 = r5.mo4193a((p000.C0096dk) r4)
            r22 = 0
            r23 = 0
            if (r10 == 0) goto L_0x007b
            short r4 = r4.f6699c
            java.util.List r7 = r12.f5451c
            if (r7 == 0) goto L_0x0069
            java.lang.Integer r7 = java.lang.Integer.valueOf(r4)
            goto L_0x006b
        L_0x0069:
            r7 = r22
        L_0x006b:
            if (r4 >= 0) goto L_0x0071
        L_0x006d:
            r4 = r22
            r6 = 1
            goto L_0x007a
        L_0x0071:
            int r6 = r10.length
            if (r4 >= r6) goto L_0x0079
            r4 = r10[r4]
            r6 = 0
            goto L_0x007a
        L_0x0079:
            goto L_0x006d
        L_0x007a:
            goto L_0x00b0
        L_0x007b:
            if (r34 == 0) goto L_0x009b
            r4 = 0
        L_0x007e:
            if (r4 >= r2) goto L_0x0095
            r6 = r34[r4]
            java.lang.String r6 = r6.toString()
            boolean r6 = r5.equals(r6)
            if (r6 == 0) goto L_0x0092
            int r4 = r4 + 1
            r4 = r34[r4]
            r6 = 0
            goto L_0x0098
        L_0x0092:
            int r4 = r4 + 2
            goto L_0x007e
        L_0x0095:
            r4 = r22
            r6 = 1
        L_0x0098:
            r7 = r5
            goto L_0x00b0
        L_0x009b:
            if (r11 == 0) goto L_0x00ac
            boolean r4 = r11.containsKey(r5)
            if (r4 == 0) goto L_0x00ab
            java.lang.Object r4 = r11.get(r5)
            r7 = r5
            r6 = 0
            goto L_0x00b0
        L_0x00ab:
        L_0x00ac:
            r7 = r5
            r4 = r22
            r6 = 1
        L_0x00b0:
            int r1 = r1 + 1
            int r14 = r12.f5450b
            if (r6 == 0) goto L_0x00e5
            java.lang.String r1 = java.lang.String.valueOf(r5)
            int r1 = r1.length()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            int r1 = r1 + r2
            r3.<init>(r1)
            java.lang.String r1 = "{"
            r3.append(r1)
            r3.append(r5)
            java.lang.String r1 = "}"
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r12.mo3771a(r1)
            r19 = r0
            r0 = r7
            r2 = r12
            r16 = r13
            r20 = r14
            r18 = r15
            goto L_0x051e
        L_0x00e5:
            if (r4 == 0) goto L_0x050f
            r24 = 0
            if (r9 == 0) goto L_0x011d
            int r6 = r9.f5654e
            int r2 = r1 + -2
            if (r6 != r2) goto L_0x011d
            double r1 = r9.f5653d
            int r3 = (r1 > r24 ? 1 : (r1 == r24 ? 0 : -1))
            if (r3 != 0) goto L_0x010c
            java.text.Format r1 = r9.f5655f
            java.lang.Number r2 = r9.f5652c
            java.lang.String r3 = r9.f5656g
            r12.mo3773a(r1, r2, r3)
            r19 = r0
            r0 = r7
            r2 = r12
            r16 = r13
            r20 = r14
            r18 = r15
            goto L_0x051e
        L_0x010c:
            java.text.Format r1 = r9.f5655f
            r12.mo3772a(r1, r4)
            r19 = r0
            r0 = r7
            r2 = r12
            r16 = r13
            r20 = r14
            r18 = r15
            goto L_0x051e
        L_0x011d:
            java.util.Map r2 = r8.f5771c
            if (r2 == 0) goto L_0x013f
            int r6 = r1 + -2
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            java.lang.Object r2 = r2.get(r6)
            java.text.Format r2 = (java.text.Format) r2
            if (r2 != 0) goto L_0x0130
            goto L_0x013f
        L_0x0130:
            r12.mo3772a(r2, r4)
            r19 = r0
            r0 = r7
            r2 = r12
            r16 = r13
            r20 = r14
            r18 = r15
            goto L_0x051e
        L_0x013f:
            r6 = 1
            if (r3 == r6) goto L_0x04d7
            java.util.Map r6 = r8.f5771c
            if (r6 == 0) goto L_0x015e
            int r16 = r1 + -2
            java.lang.Integer r2 = java.lang.Integer.valueOf(r16)
            boolean r2 = r6.containsKey(r2)
            if (r2 != 0) goto L_0x0153
            goto L_0x015e
        L_0x0153:
            r19 = r0
            r0 = r7
            r16 = r13
            r20 = r14
            r18 = r15
            goto L_0x04e0
        L_0x015e:
            java.lang.String r2 = "' is not a Number"
            java.lang.String r6 = "'"
            r26 = r7
            r7 = 3
            if (r3 == r7) goto L_0x0448
            boolean r7 = p000.cun.m5451d(r3)
            java.lang.String r10 = "other"
            if (r7 == 0) goto L_0x03d5
            boolean r7 = r4 instanceof java.lang.Number
            if (r7 == 0) goto L_0x03af
            r2 = 4
            if (r3 == r2) goto L_0x0185
            cv r2 = r8.f5775g
            if (r2 != 0) goto L_0x0182
            cv r2 = new cv
            r3 = 2
            r2.<init>(r8, r3)
            r8.f5775g = r2
        L_0x0182:
            cv r2 = r8.f5775g
            goto L_0x0193
        L_0x0185:
            cv r2 = r8.f5774f
            if (r2 != 0) goto L_0x0191
            cv r2 = new cv
            r3 = 1
            r2.<init>(r8, r3)
            r8.f5774f = r2
        L_0x0191:
            cv r2 = r8.f5774f
        L_0x0193:
            java.lang.Number r4 = (java.lang.Number) r4
            dl r3 = r8.f5770b
            java.util.ArrayList r6 = r3.f6759b
            java.lang.Object r6 = r6.get(r1)
            dk r6 = (p000.C0096dk) r6
            int r7 = r6.f6701e
            boolean r7 = p000.C0071co.m4665a((int) r7)
            if (r7 == 0) goto L_0x01ae
            double r6 = r3.mo4196b((p000.C0096dk) r6)
            r20 = r6
            goto L_0x01b1
        L_0x01ae:
            r20 = r24
        L_0x01b1:
            cu r3 = new cu
            r16 = r3
            r17 = r1
            r18 = r5
            r19 = r4
            r16.<init>(r17, r18, r19, r20)
            dl r5 = r8.f5770b
            double r6 = r4.doubleValue()
            int r4 = r5.mo4191a()
            dk r11 = r5.mo4192a((int) r1)
            r16 = r13
            int r13 = r11.f6701e
            boolean r13 = p000.C0071co.m4665a((int) r13)
            if (r13 == 0) goto L_0x01dd
            double r24 = r5.mo4196b((p000.C0096dk) r11)
            int r1 = r1 + 1
            goto L_0x01de
        L_0x01dd:
        L_0x01de:
            r9 = r22
            r11 = 0
            r13 = 0
        L_0x01e2:
            r18 = r15
            int r15 = r1 + 1
            dk r1 = r5.mo4192a((int) r1)
            r19 = r0
            int r0 = r1.f6701e
            r20 = r14
            r14 = 7
            if (r0 == r14) goto L_0x039b
            int r0 = r5.mo4198c(r15)
            boolean r0 = p000.C0071co.m4665a((int) r0)
            if (r0 == 0) goto L_0x0214
            int r0 = r15 + 1
            dk r1 = r5.mo4192a((int) r15)
            double r14 = r5.mo4196b((p000.C0096dk) r1)
            int r1 = (r6 > r14 ? 1 : (r6 == r14 ? 0 : -1))
            if (r1 == 0) goto L_0x0211
            r15 = r0
            r27 = r6
            r12 = 6
            goto L_0x0385
        L_0x0211:
            r2 = r0
            goto L_0x039c
        L_0x0214:
            if (r11 != 0) goto L_0x0379
            boolean r0 = r5.mo4195a((p000.C0096dk) r1, (java.lang.String) r10)
            if (r0 != 0) goto L_0x035e
            if (r9 != 0) goto L_0x034e
            r0 = r15
            double r14 = r6 - r24
            di r9 = r2.f5704b
            if (r9 == 0) goto L_0x0228
            r17 = r0
            goto L_0x0236
        L_0x0228:
            cw r9 = r2.f5703a
            java.util.Locale r9 = r9.f5769a
            r17 = r0
            int r0 = r2.f5705c
            di r0 = p000.C0094di.m6134a((java.util.Locale) r9, (int) r0)
            r2.f5704b = r0
        L_0x0236:
            cw r0 = r2.f5703a
            int r9 = r3.f5650a
            r27 = r6
            dl r6 = r0.f5770b
            int r6 = r6.mo4191a()
            dl r7 = r0.f5770b
            dk r7 = r7.mo4192a((int) r9)
            int r7 = r7.f6701e
            boolean r7 = p000.C0071co.m4665a((int) r7)
            if (r7 != 0) goto L_0x0251
            goto L_0x0254
        L_0x0251:
            int r9 = r9 + 1
        L_0x0254:
            int r7 = r9 + 1
            r21 = r11
            dl r11 = r0.f5770b
            dk r9 = r11.mo4192a((int) r9)
            int r11 = r9.f6701e
            r12 = 7
            if (r11 == r12) goto L_0x028b
            dl r11 = r0.f5770b
            boolean r9 = r11.mo4195a((p000.C0096dk) r9, (java.lang.String) r10)
            if (r9 != 0) goto L_0x028a
            dl r9 = r0.f5770b
            int r9 = r9.mo4198c(r7)
            boolean r9 = p000.C0071co.m4665a((int) r9)
            if (r9 == 0) goto L_0x0279
            int r7 = r7 + 1
        L_0x0279:
            dl r9 = r0.f5770b
            int r7 = r9.mo4197b((int) r7)
            r9 = 1
            int r7 = r7 + r9
            if (r7 < r6) goto L_0x0284
            goto L_0x028b
        L_0x0284:
            r12 = r35
            r9 = r7
            r11 = r21
            goto L_0x0254
        L_0x028a:
            goto L_0x028c
        L_0x028b:
            r7 = 0
        L_0x028c:
            cw r0 = r2.f5703a
            java.lang.String r6 = r3.f5651b
            r9 = 1
            int r7 = r7 + r9
        L_0x0292:
            dl r9 = r0.f5770b
            dk r9 = r9.mo4192a((int) r7)
            int r11 = r9.f6701e
            r12 = 2
            if (r11 == r12) goto L_0x02d4
            r12 = 5
            if (r11 != r12) goto L_0x02a3
            r7 = -1
            r12 = 6
            goto L_0x02d6
        L_0x02a3:
            r12 = 6
            if (r11 == r12) goto L_0x02a7
            goto L_0x02d1
        L_0x02a7:
            int r9 = r9.mo4170b()
            int r11 = r6.length()
            if (r11 != 0) goto L_0x02b2
            goto L_0x02cb
        L_0x02b2:
            r11 = 1
            if (r9 != r11) goto L_0x02b7
            r11 = 2
            goto L_0x02ba
        L_0x02b7:
            r11 = 2
            if (r9 != r11) goto L_0x02cb
        L_0x02ba:
            dl r9 = r0.f5770b
            int r11 = r7 + 1
            dk r9 = r9.mo4192a((int) r11)
            dl r11 = r0.f5770b
            boolean r9 = r11.mo4195a((p000.C0096dk) r9, (java.lang.String) r6)
            if (r9 == 0) goto L_0x02cb
            goto L_0x02d6
        L_0x02cb:
            dl r9 = r0.f5770b
            int r7 = r9.mo4197b((int) r7)
        L_0x02d1:
            r9 = 1
            int r7 = r7 + r9
            goto L_0x0292
        L_0x02d4:
            r12 = 6
            r7 = 0
        L_0x02d6:
            r3.f5654e = r7
            if (r7 <= 0) goto L_0x02ec
            cw r0 = r2.f5703a
            java.util.Map r0 = r0.f5771c
            if (r0 == 0) goto L_0x02ec
            java.lang.Integer r6 = java.lang.Integer.valueOf(r7)
            java.lang.Object r0 = r0.get(r6)
            java.text.Format r0 = (java.text.Format) r0
            r3.f5655f = r0
        L_0x02ec:
            java.text.Format r0 = r3.f5655f
            if (r0 == 0) goto L_0x02f1
            goto L_0x02fc
        L_0x02f1:
            cw r0 = r2.f5703a
            java.text.NumberFormat r0 = r0.m5489a()
            r3.f5655f = r0
            r0 = 1
            r3.f5657h = r0
        L_0x02fc:
            java.text.Format r0 = r3.f5655f
            java.lang.Number r6 = r3.f5652c
            java.lang.String r0 = r0.format(r6)
            r3.f5656g = r0
            di r0 = r2.f5704b
            dh r0 = r0.f6586a
            db r6 = new db
            r6.<init>((double) r14)
            double r14 = r6.f6159a
            boolean r7 = java.lang.Double.isInfinite(r14)
            if (r7 == 0) goto L_0x0318
        L_0x0317:
            goto L_0x033f
        L_0x0318:
            double r14 = r6.f6159a
            boolean r7 = java.lang.Double.isNaN(r14)
            if (r7 != 0) goto L_0x0317
            java.util.List r0 = r0.f6530b
            int r7 = r0.size()
            r9 = 0
        L_0x0327:
            if (r9 >= r7) goto L_0x033a
            java.lang.Object r11 = r0.get(r9)
            dg r11 = (p000.C0092dg) r11
            int r9 = r9 + 1
            da r14 = r11.f6485b
            boolean r14 = r14.mo3936a(r6)
            if (r14 == 0) goto L_0x0327
            goto L_0x033c
        L_0x033a:
            r11 = r22
        L_0x033c:
            java.lang.String r0 = r11.f6484a
            goto L_0x0340
        L_0x033f:
            r0 = r10
        L_0x0340:
            if (r13 == 0) goto L_0x034c
            boolean r6 = r0.equals(r10)
            if (r6 == 0) goto L_0x034c
            r9 = r0
            r21 = 1
            goto L_0x0355
        L_0x034c:
            r9 = r0
            goto L_0x0355
        L_0x034e:
            r27 = r6
            r21 = r11
            r17 = r15
            r12 = 6
        L_0x0355:
            if (r21 != 0) goto L_0x0380
            boolean r0 = r5.mo4195a((p000.C0096dk) r1, (java.lang.String) r9)
            if (r0 == 0) goto L_0x0380
            goto L_0x036f
        L_0x035e:
            r27 = r6
            r21 = r11
            r17 = r15
            r12 = 6
            if (r13 != 0) goto L_0x0380
            if (r9 == 0) goto L_0x0374
            boolean r0 = r9.equals(r10)
            if (r0 == 0) goto L_0x0374
        L_0x036f:
            r13 = r17
            r15 = r13
            r11 = 1
            goto L_0x0385
        L_0x0374:
            r13 = r17
            r15 = r13
            goto L_0x0383
        L_0x0379:
            r27 = r6
            r21 = r11
            r17 = r15
            r12 = 6
        L_0x0380:
            r15 = r17
        L_0x0383:
            r11 = r21
        L_0x0385:
            int r0 = r5.mo4197b((int) r15)
            r1 = 1
            int r0 = r0 + r1
            if (r0 < r4) goto L_0x038e
            goto L_0x039b
        L_0x038e:
            r12 = r35
            r1 = r0
            r15 = r18
            r0 = r19
            r14 = r20
            r6 = r27
            goto L_0x01e2
        L_0x039b:
            r2 = r13
        L_0x039c:
            r1 = r29
            r4 = r32
            r5 = r33
            r6 = r34
            r0 = r26
            r7 = r35
            r1.m5490a(r2, r3, r4, r5, r6, r7)
            r2 = r35
            goto L_0x051e
        L_0x03af:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = java.lang.String.valueOf(r4)
            java.lang.String r3 = java.lang.String.valueOf(r1)
            int r3 = r3.length()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            int r3 = r3 + 18
            r4.<init>(r3)
            r4.append(r6)
            r4.append(r1)
            r4.append(r2)
            java.lang.String r1 = r4.toString()
            r0.<init>(r1)
            throw r0
        L_0x03d5:
            r19 = r0
            r16 = r13
            r20 = r14
            r18 = r15
            r0 = r26
            r2 = 5
            if (r3 != r2) goto L_0x0427
            dl r2 = r8.f5770b
            java.lang.String r3 = r4.toString()
            int r4 = r2.mo4191a()
        L_0x03ec:
            int r5 = r1 + 1
            dk r1 = r2.mo4192a((int) r1)
            int r6 = r1.f6701e
            r7 = 7
            if (r6 == r7) goto L_0x0413
            boolean r6 = r2.mo4195a((p000.C0096dk) r1, (java.lang.String) r3)
            if (r6 == 0) goto L_0x03ff
            r2 = r5
            goto L_0x0415
        L_0x03ff:
            if (r23 == 0) goto L_0x0402
            goto L_0x040b
        L_0x0402:
            boolean r1 = r2.mo4195a((p000.C0096dk) r1, (java.lang.String) r10)
            if (r1 == 0) goto L_0x040b
            r23 = r5
        L_0x040b:
            int r1 = r2.mo4197b((int) r5)
            r5 = 1
            int r1 = r1 + r5
            if (r1 < r4) goto L_0x03ec
        L_0x0413:
            r2 = r23
        L_0x0415:
            r3 = 0
            r1 = r29
            r4 = r32
            r5 = r33
            r6 = r34
            r7 = r35
            r1.m5490a(r2, r3, r4, r5, r6, r7)
            r2 = r35
            goto L_0x051e
        L_0x0427:
            java.lang.String r0 = p000.cun.m5450c(r3)
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            int r2 = r0.length()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            int r2 = r2 + 19
            r3.<init>(r2)
            java.lang.String r2 = "unexpected argType "
            r3.append(r2)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r1.<init>(r0)
            throw r1
        L_0x0448:
            r19 = r0
            r16 = r13
            r20 = r14
            r18 = r15
            r0 = r26
            boolean r3 = r4 instanceof java.lang.Number
            if (r3 == 0) goto L_0x04b1
            java.lang.Number r4 = (java.lang.Number) r4
            double r2 = r4.doubleValue()
            dl r4 = r8.f5770b
            int r5 = r4.mo4191a()
            int r1 = r1 + 2
            r6 = r1
        L_0x0465:
            int r1 = r4.mo4197b((int) r6)
            r7 = 1
            int r1 = r1 + r7
            if (r1 >= r5) goto L_0x049e
            int r7 = r1 + 1
            dk r1 = r4.mo4192a((int) r1)
            int r9 = r1.f6701e
            r10 = 7
            if (r9 == r10) goto L_0x049e
            double r11 = r4.mo4196b((p000.C0096dk) r1)
            int r1 = r7 + 1
            java.util.ArrayList r9 = r4.f6759b
            java.lang.Object r7 = r9.get(r7)
            dk r7 = (p000.C0096dk) r7
            int r7 = r7.f6697a
            java.lang.String r9 = r4.f6758a
            char r7 = r9.charAt(r7)
            r9 = 60
            if (r7 == r9) goto L_0x0497
            int r7 = (r2 > r11 ? 1 : (r2 == r11 ? 0 : -1))
            if (r7 >= 0) goto L_0x049b
            goto L_0x049e
        L_0x0497:
            int r7 = (r2 > r11 ? 1 : (r2 == r11 ? 0 : -1))
            if (r7 <= 0) goto L_0x049e
        L_0x049b:
            r6 = r1
            goto L_0x0465
        L_0x049e:
            r3 = 0
            r1 = r29
            r2 = r6
            r4 = r32
            r5 = r33
            r6 = r34
            r7 = r35
            r1.m5490a(r2, r3, r4, r5, r6, r7)
            r2 = r35
            goto L_0x051e
        L_0x04b1:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = java.lang.String.valueOf(r4)
            java.lang.String r3 = java.lang.String.valueOf(r1)
            int r3 = r3.length()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            int r3 = r3 + 18
            r4.<init>(r3)
            r4.append(r6)
            r4.append(r1)
            r4.append(r2)
            java.lang.String r1 = r4.toString()
            r0.<init>(r1)
            throw r0
        L_0x04d7:
            r19 = r0
            r0 = r7
            r16 = r13
            r20 = r14
            r18 = r15
        L_0x04e0:
            boolean r1 = r4 instanceof java.lang.Number
            if (r1 == 0) goto L_0x04ee
            java.text.NumberFormat r1 = r29.m5489a()
            r2 = r35
            r2.mo3772a(r1, r4)
            goto L_0x051e
        L_0x04ee:
            r2 = r35
            boolean r1 = r4 instanceof java.util.Date
            if (r1 != 0) goto L_0x04fc
            java.lang.String r1 = r4.toString()
            r2.mo3771a(r1)
            goto L_0x051e
        L_0x04fc:
            java.text.DateFormat r1 = r8.f5772d
            if (r1 != 0) goto L_0x0509
            java.util.Locale r1 = r8.f5769a
            r3 = 3
            java.text.DateFormat r1 = java.text.DateFormat.getDateTimeInstance(r3, r3, r1)
            r8.f5772d = r1
        L_0x0509:
            java.text.DateFormat r1 = r8.f5772d
            r2.mo3772a(r1, r4)
            goto L_0x051e
        L_0x050f:
            r19 = r0
            r0 = r7
            r2 = r12
            r16 = r13
            r20 = r14
            r18 = r15
            java.lang.String r1 = "null"
            r2.mo3771a(r1)
        L_0x051e:
            java.util.List r1 = r2.f5451c
            if (r1 == 0) goto L_0x0531
            int r3 = r2.f5450b
            r4 = r20
            if (r4 >= r3) goto L_0x0533
            cs r5 = new cs
            r5.<init>(r0, r4, r3)
            r1.add(r5)
            goto L_0x0533
        L_0x0531:
            r4 = r20
        L_0x0533:
            if (r19 != 0) goto L_0x0538
            r0 = r19
            goto L_0x0554
        L_0x0538:
            ct r0 = p000.C0078ct.f5616a
            java.text.Format$Field r1 = r19.getFieldAttribute()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0552
            r0 = r19
            r0.setBeginIndex(r4)
            int r1 = r2.f5450b
            r0.setEndIndex(r1)
            r0 = r22
            goto L_0x0554
        L_0x0552:
            r0 = r19
        L_0x0554:
            dl r1 = r8.f5770b
            r3 = r18
            dk r1 = r1.mo4192a((int) r3)
            int r1 = r1.mo4169a()
            r5 = r1
            r1 = r3
        L_0x0562:
            r3 = r31
            goto L_0x0588
        L_0x0565:
            r2 = r12
            r16 = r13
            goto L_0x0562
        L_0x0569:
            r2 = r12
            r16 = r13
            r3 = r31
            boolean r4 = r3.f5657h
            if (r4 == 0) goto L_0x057d
            java.text.Format r4 = r3.f5655f
            java.lang.Number r6 = r3.f5652c
            java.lang.String r7 = r3.f5656g
            r2.mo3773a(r4, r6, r7)
            goto L_0x0588
        L_0x057d:
            java.text.NumberFormat r4 = r29.m5489a()
            java.lang.Number r6 = r3.f5652c
            r2.mo3772a(r4, r6)
        L_0x0588:
            r4 = 1
            int r1 = r1 + r4
            r10 = r32
            r11 = r33
            r12 = r2
            r9 = r3
            r2 = r5
            r13 = r16
            r14 = 1
            goto L_0x001e
        L_0x0596:
            return
        L_0x0597:
            r0 = move-exception
            do r1 = new do
            r1.<init>(r0)
            goto L_0x059f
        L_0x059e:
            throw r1
        L_0x059f:
            goto L_0x059e
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0081cw.m5491a(int, cu, java.lang.Object[], java.util.Map, java.lang.Object[], cr, java.text.FieldPosition):void");
    }

    /* renamed from: a */
    private final void m5492a(Object obj, C0076cr crVar, FieldPosition fieldPosition) {
        if (obj != null && !(obj instanceof Map)) {
            m5494a((Object[]) obj, (Map) null, crVar, fieldPosition);
        } else {
            m5494a((Object[]) null, (Map) obj, crVar, fieldPosition);
        }
    }

    public final StringBuffer format(Object obj, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        m5492a(obj, new C0076cr(stringBuffer), fieldPosition);
        return stringBuffer;
    }

    /* renamed from: a */
    private final void m5494a(Object[] objArr, Map map, C0076cr crVar, FieldPosition fieldPosition) {
        if (objArr == null || !this.f5770b.f6761d) {
            m5491a(0, (C0079cu) null, objArr, map, (Object[]) null, crVar, fieldPosition);
            return;
        }
        throw new IllegalArgumentException("This method is not available in MessageFormat objects that use alphanumeric argument names.");
    }

    /* renamed from: a */
    private final void m5490a(int i, C0079cu cuVar, Object[] objArr, Map map, Object[] objArr2, C0076cr crVar) {
        if (this.f5770b.f6762e != 2) {
            m5491a(i, cuVar, objArr, map, objArr2, crVar, (FieldPosition) null);
            return;
        }
        throw new UnsupportedOperationException("JDK apostrophe mode not supported");
    }

    /* renamed from: a */
    public static final String m5488a(Locale locale, String str, Object... objArr) {
        StringBuilder sb = new StringBuilder(str.length());
        new C0081cw(str, locale).m5491a(0, (C0079cu) null, (Object[]) null, (Map) null, objArr, new C0076cr(sb), (FieldPosition) null);
        return sb.toString();
    }

    public final AttributedCharacterIterator formatToCharacterIterator(Object obj) {
        if (obj != null) {
            StringBuilder sb = new StringBuilder();
            C0076cr crVar = new C0076cr(sb);
            crVar.f5451c = new ArrayList();
            m5492a(obj, crVar, (FieldPosition) null);
            AttributedString attributedString = new AttributedString(sb.toString());
            List list = crVar.f5451c;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                C0077cs csVar = (C0077cs) list.get(i);
                attributedString.addAttribute(csVar.f5535a, csVar.f5536b, csVar.f5537c, csVar.f5538d);
            }
            return attributedString.getIterator();
        }
        throw new NullPointerException("formatToCharacterIterator must be passed non-null object");
    }

    /* renamed from: a */
    private final NumberFormat m5489a() {
        if (this.f5773e == null) {
            this.f5773e = NumberFormat.getInstance(this.f5769a);
        }
        return this.f5773e;
    }

    public final int hashCode() {
        return this.f5770b.f6758a.hashCode();
    }

    /* JADX WARNING: Removed duplicated region for block: B:96:0x01fb  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void m5493a(java.lang.String r26, java.text.ParsePosition r27, java.lang.Object[] r28, java.util.Map r29) {
        /*
            r25 = this;
            r0 = r25
            r1 = r26
            r2 = r27
            r3 = r29
            if (r1 == 0) goto L_0x0267
            dl r4 = r0.f5770b
            java.lang.String r5 = r4.f6758a
            r6 = 0
            dk r4 = r4.mo4192a((int) r6)
            int r4 = r4.mo4169a()
            int r7 = r27.getIndex()
            java.text.ParsePosition r8 = new java.text.ParsePosition
            r8.<init>(r6)
            r9 = 1
            r10 = 1
        L_0x0022:
            dl r11 = r0.f5770b
            dk r11 = r11.mo4192a((int) r10)
            int r12 = r11.f6701e
            int r13 = r11.f6697a
            int r13 = r13 - r4
            if (r13 != 0) goto L_0x0030
            goto L_0x003a
        L_0x0030:
            boolean r4 = r5.regionMatches(r4, r1, r7, r13)
            if (r4 != 0) goto L_0x003a
            r2.setErrorIndex(r7)
            return
        L_0x003a:
            int r7 = r7 + r13
            r4 = 2
            if (r12 == r4) goto L_0x0263
            r13 = 3
            if (r12 != r13) goto L_0x0045
            r19 = r5
            goto L_0x0257
        L_0x0045:
            r14 = 4
            if (r12 == r14) goto L_0x0255
            dl r12 = r0.f5770b
            int r12 = r12.mo4197b((int) r10)
            int r11 = r11.mo4170b()
            int r10 = r10 + 1
            dl r14 = r0.f5770b
            dk r14 = r14.mo4192a((int) r10)
            if (r28 == 0) goto L_0x0066
            short r14 = r14.f6699c
            java.lang.Integer r16 = java.lang.Integer.valueOf(r14)
            r15 = r16
            r6 = 0
            goto L_0x007c
        L_0x0066:
            int r6 = r14.f6701e
            r15 = 9
            if (r6 != r15) goto L_0x0073
            dl r6 = r0.f5770b
            java.lang.String r6 = r6.mo4193a((p000.C0096dk) r14)
            goto L_0x0079
        L_0x0073:
            short r6 = r14.f6699c
            java.lang.String r6 = java.lang.Integer.toString(r6)
        L_0x0079:
            r15 = r6
            r14 = 0
        L_0x007c:
            int r10 = r10 + 1
            java.util.Map r13 = r0.f5771c
            if (r13 == 0) goto L_0x00ae
            int r18 = r10 + -2
            java.lang.Integer r4 = java.lang.Integer.valueOf(r18)
            java.lang.Object r4 = r13.get(r4)
            java.text.Format r4 = (java.text.Format) r4
            if (r4 != 0) goto L_0x0091
            goto L_0x00ae
        L_0x0091:
            r8.setIndex(r7)
            java.lang.Object r15 = r4.parseObject(r1, r8)
            int r4 = r8.getIndex()
            if (r4 != r7) goto L_0x00a2
            r2.setErrorIndex(r7)
            return
        L_0x00a2:
            int r4 = r8.getIndex()
            r19 = r5
            r20 = r12
        L_0x00aa:
            r17 = 1
            goto L_0x01f8
        L_0x00ae:
            if (r11 != r9) goto L_0x00b2
            goto L_0x00c8
        L_0x00b2:
            java.util.Map r4 = r0.f5771c
            if (r4 == 0) goto L_0x014f
            int r13 = r10 + -2
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
            boolean r4 = r4.containsKey(r13)
            if (r4 != 0) goto L_0x00c8
            r19 = r5
            r20 = r12
            goto L_0x0153
        L_0x00c8:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            dl r10 = r0.f5770b
            java.lang.String r11 = r10.f6758a
            dk r10 = r10.mo4192a((int) r12)
            int r10 = r10.mo4169a()
            int r13 = r12 + 1
        L_0x00db:
            dl r9 = r0.f5770b
            dk r9 = r9.mo4192a((int) r13)
            r19 = r5
            int r5 = r9.f6701e
            r20 = r12
            int r12 = r9.f6697a
            r4.append(r11, r10, r12)
            r10 = 6
            if (r5 == r10) goto L_0x00ff
            r10 = 2
            if (r5 != r10) goto L_0x00f3
            goto L_0x00ff
        L_0x00f3:
            int r10 = r9.mo4169a()
            int r13 = r13 + 1
            r5 = r19
            r12 = r20
            r9 = 1
            goto L_0x00db
        L_0x00ff:
            java.lang.String r4 = r4.toString()
            int r5 = r4.length()
            if (r5 == 0) goto L_0x010e
            int r4 = r1.indexOf(r4, r7)
            goto L_0x0112
        L_0x010e:
            int r4 = r26.length()
        L_0x0112:
            if (r4 < 0) goto L_0x014b
            java.lang.String r5 = r1.substring(r7, r4)
            java.lang.String r7 = r15.toString()
            java.lang.String r9 = java.lang.String.valueOf(r7)
            int r9 = r9.length()
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r11 = 2
            int r9 = r9 + r11
            r10.<init>(r9)
            java.lang.String r9 = "{"
            r10.append(r9)
            r10.append(r7)
            java.lang.String r7 = "}"
            r10.append(r7)
            java.lang.String r7 = r10.toString()
            boolean r7 = r5.equals(r7)
            if (r7 != 0) goto L_0x0145
            r15 = r5
            goto L_0x00aa
        L_0x0145:
            r15 = 0
            r17 = 0
            goto L_0x01f8
        L_0x014b:
            r2.setErrorIndex(r7)
            return
        L_0x014f:
            r19 = r5
            r20 = r12
        L_0x0153:
            r4 = 3
            if (r11 != r4) goto L_0x0222
            r8.setIndex(r7)
            dl r4 = r0.f5770b
            int r5 = r8.getIndex()
            r11 = 9221120237041090560(0x7ff8000000000000, double:NaN)
            r9 = r5
        L_0x0162:
            int r13 = r4.mo4198c(r10)
            r15 = 7
            if (r13 == r15) goto L_0x01dc
            dk r13 = r4.mo4192a((int) r10)
            double r21 = r4.mo4196b((p000.C0096dk) r13)
            int r10 = r10 + 2
            int r13 = r4.mo4197b((int) r10)
            java.lang.String r15 = r4.f6758a
            dk r17 = r4.mo4192a((int) r10)
            int r17 = r17.mo4169a()
            r23 = r11
            r11 = r17
            r12 = 0
        L_0x0186:
            r17 = 1
            int r10 = r10 + 1
            dk r2 = r4.mo4192a((int) r10)
            if (r10 == r13) goto L_0x0199
            r17 = r4
            int r4 = r2.f6701e
            r0 = 3
            if (r4 != r0) goto L_0x0198
            goto L_0x019c
        L_0x0198:
            goto L_0x01b2
        L_0x0199:
            r17 = r4
            r0 = 3
        L_0x019c:
            int r4 = r2.f6697a
            int r4 = r4 - r11
            if (r4 != 0) goto L_0x01a2
            goto L_0x01aa
        L_0x01a2:
            boolean r11 = r1.regionMatches(r5, r15, r11, r4)
            if (r11 != 0) goto L_0x01aa
            r2 = -1
            goto L_0x01bb
        L_0x01aa:
            int r12 = r12 + r4
            if (r10 == r13) goto L_0x01ba
            int r2 = r2.mo4169a()
            r11 = r2
        L_0x01b2:
            r0 = r25
            r2 = r27
            r4 = r17
            goto L_0x0186
        L_0x01ba:
            r2 = r12
        L_0x01bb:
            if (r2 >= 0) goto L_0x01be
        L_0x01bd:
            goto L_0x01c2
        L_0x01be:
            int r2 = r2 + r5
            if (r2 > r9) goto L_0x01c5
            goto L_0x01bd
        L_0x01c2:
            r11 = r23
            goto L_0x01d2
        L_0x01c5:
            int r4 = r26.length()
            if (r2 != r4) goto L_0x01cf
            r9 = r2
            r11 = r21
            goto L_0x01de
        L_0x01cf:
            r9 = r2
            r11 = r21
        L_0x01d2:
            int r10 = r13 + 1
            r0 = r25
            r2 = r27
            r4 = r17
            goto L_0x0162
        L_0x01dc:
            r23 = r11
        L_0x01de:
            if (r9 == r5) goto L_0x01e4
            r8.setIndex(r9)
            goto L_0x01e7
        L_0x01e4:
            r8.setErrorIndex(r5)
        L_0x01e7:
            int r0 = r8.getIndex()
            if (r0 == r7) goto L_0x021a
            java.lang.Double r15 = java.lang.Double.valueOf(r11)
            int r4 = r8.getIndex()
            r17 = 1
        L_0x01f8:
            if (r17 != 0) goto L_0x01fb
            goto L_0x0205
        L_0x01fb:
            if (r28 == 0) goto L_0x0200
            r28[r14] = r15
            goto L_0x0205
        L_0x0200:
            if (r3 == 0) goto L_0x0205
            r3.put(r6, r15)
        L_0x0205:
            r0 = r25
            dl r2 = r0.f5770b
            r5 = r20
            dk r2 = r2.mo4192a((int) r5)
            int r2 = r2.mo4169a()
            r7 = r4
            r10 = r5
            r4 = r2
            r2 = r27
            goto L_0x025b
        L_0x021a:
            r0 = r25
            r2 = r27
            r2.setErrorIndex(r7)
            return
        L_0x0222:
            boolean r1 = p000.cun.m5451d(r11)
            if (r1 != 0) goto L_0x024d
            r1 = 5
            if (r11 != r1) goto L_0x022c
            goto L_0x024d
        L_0x022c:
            java.lang.String r1 = p000.cun.m5450c(r11)
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            int r3 = r1.length()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            int r3 = r3 + 19
            r4.<init>(r3)
            java.lang.String r3 = "unexpected argType "
            r4.append(r3)
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            r2.<init>(r1)
            throw r2
        L_0x024d:
            java.lang.UnsupportedOperationException r1 = new java.lang.UnsupportedOperationException
            java.lang.String r2 = "Parsing of plural/select/selectordinal argument is not supported."
            r1.<init>(r2)
            throw r1
        L_0x0255:
            r19 = r5
        L_0x0257:
            int r4 = r11.mo4169a()
        L_0x025b:
            r5 = 1
            int r10 = r10 + r5
            r5 = r19
            r6 = 0
            r9 = 1
            goto L_0x0022
        L_0x0263:
            r2.setIndex(r7)
            return
        L_0x0267:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0081cw.m5493a(java.lang.String, java.text.ParsePosition, java.lang.Object[], java.util.Map):void");
    }

    public final Object parseObject(String str, ParsePosition parsePosition) {
        if (!this.f5770b.f6761d) {
            int i = 0;
            short s = -1;
            while (true) {
                if (i != 0) {
                    i = this.f5770b.mo4197b(i);
                }
                while (true) {
                    i++;
                    int c = this.f5770b.mo4198c(i);
                    if (c != 6) {
                        if (c == 2) {
                            i = -1;
                            break;
                        }
                    } else {
                        break;
                    }
                }
                if (i < 0) {
                    break;
                }
                short s2 = this.f5770b.mo4192a(i + 1).f6699c;
                if (s2 > s) {
                    s = s2;
                }
            }
            Object[] objArr = new Object[(s + 1)];
            int index = parsePosition.getIndex();
            m5493a(str, parsePosition, objArr, (Map) null);
            if (parsePosition.getIndex() != index) {
                return objArr;
            }
            return null;
        }
        HashMap hashMap = new HashMap();
        int index2 = parsePosition.getIndex();
        m5493a(str, parsePosition, (Object[]) null, (Map) hashMap);
        if (parsePosition.getIndex() != index2) {
            return hashMap;
        }
        return null;
    }
}
