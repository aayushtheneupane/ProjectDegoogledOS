package p000;

import java.util.ArrayList;

/* renamed from: dl */
/* compiled from: PG */
public final class C0097dl implements Cloneable {

    /* renamed from: f */
    public static final int[] f6756f = {1, 2, 3, 4, 5, 6};

    /* renamed from: h */
    private static final int f6757h;

    /* renamed from: a */
    public String f6758a;

    /* renamed from: b */
    public ArrayList f6759b = new ArrayList();

    /* renamed from: c */
    public ArrayList f6760c;

    /* renamed from: d */
    public boolean f6761d;

    /* renamed from: e */
    public final int f6762e = f6757h;

    /* renamed from: g */
    private boolean f6763g;

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002e  */
    static {
        /*
            java.lang.String r0 = "android.icumessageformat.text.MessagePattern.ApostropheMode"
            java.lang.String r1 = "DOUBLE_OPTIONAL"
            java.lang.String r0 = p000.C0069cm.m4533a(r0, r1)
            int r2 = r0.hashCode()
            r3 = -413919155(0xffffffffe754184d, float:-1.00159E24)
            r4 = 1
            if (r2 == r3) goto L_0x0020
            r3 = -99796978(0xfffffffffa0d380e, float:-1.833127E35)
            if (r2 == r3) goto L_0x0018
        L_0x0017:
            goto L_0x002b
        L_0x0018:
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0017
            r0 = 0
            goto L_0x002c
        L_0x0020:
            java.lang.String r1 = "DOUBLE_REQUIRED"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0017
            r0 = 1
            goto L_0x002c
        L_0x002b:
            r0 = -1
        L_0x002c:
            if (r0 == 0) goto L_0x0038
            if (r0 != r4) goto L_0x0032
            r4 = 2
            goto L_0x003a
        L_0x0032:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>()
            throw r0
        L_0x0038:
        L_0x003a:
            f6757h = r4
            r0 = 6
            int[] r0 = new int[r0]
            r0 = {1, 2, 3, 4, 5, 6} // fill-array
            f6756f = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0097dl.<clinit>():void");
    }

    public final String toString() {
        return this.f6758a;
    }

    public C0097dl() {
    }

    public C0097dl(String str) {
        mo4194a(str);
    }

    /* renamed from: a */
    private final void m6277a(double d, int i, int i2) {
        int i3;
        ArrayList arrayList = this.f6760c;
        if (arrayList == null) {
            this.f6760c = new ArrayList();
            i3 = 0;
        } else {
            i3 = arrayList.size();
            if (i3 > 32767) {
                throw new IndexOutOfBoundsException("Too many numeric values");
            }
        }
        this.f6760c.add(Double.valueOf(d));
        m6281b(14, i, i2, i3);
    }

    /* renamed from: a */
    private final void m6278a(int i, int i2, int i3, int i4, int i5) {
        ((C0096dk) this.f6759b.get(i)).f6700d = this.f6759b.size();
        m6281b(i2, i3, i4, i5);
    }

    /* renamed from: b */
    private final void m6281b(int i, int i2, int i3, int i4) {
        this.f6759b.add(new C0096dk(i, i2, i3, i4));
    }

    public final Object clone() {
        return m6280b();
    }

    /* renamed from: b */
    private final C0097dl m6280b() {
        try {
            C0097dl dlVar = (C0097dl) super.clone();
            dlVar.f6759b = (ArrayList) this.f6759b.clone();
            ArrayList arrayList = this.f6760c;
            if (arrayList != null) {
                dlVar.f6760c = (ArrayList) arrayList.clone();
            }
            dlVar.f6763g = false;
            return dlVar;
        } catch (CloneNotSupportedException e) {
            throw new C0098dm(e);
        }
    }

    /* renamed from: a */
    public final int mo4191a() {
        return this.f6759b.size();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001b, code lost:
        r2 = r4.f6758a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r4 == r5) goto L_0x003d
            r1 = 0
            if (r5 == 0) goto L_0x003b
            java.lang.Class r2 = r4.getClass()
            java.lang.Class r3 = r5.getClass()
            if (r2 == r3) goto L_0x0011
            goto L_0x003b
        L_0x0011:
            dl r5 = (p000.C0097dl) r5
            int r2 = r4.f6762e
            int r3 = r5.f6762e
            if (r2 == 0) goto L_0x0039
            if (r2 != r3) goto L_0x0038
            java.lang.String r2 = r4.f6758a
            if (r2 == 0) goto L_0x0028
            java.lang.String r3 = r5.f6758a
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0038
            goto L_0x002d
        L_0x0028:
            java.lang.String r2 = r5.f6758a
            if (r2 == 0) goto L_0x002d
        L_0x002c:
            goto L_0x0038
        L_0x002d:
            java.util.ArrayList r2 = r4.f6759b
            java.util.ArrayList r5 = r5.f6759b
            boolean r5 = r2.equals(r5)
            if (r5 == 0) goto L_0x002c
            return r0
        L_0x0038:
            return r1
        L_0x0039:
            r5 = 0
            throw r5
        L_0x003b:
            return r1
        L_0x003d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0097dl.equals(java.lang.Object):boolean");
    }

    /* renamed from: b */
    public final int mo4197b(int i) {
        int i2 = ((C0096dk) this.f6759b.get(i)).f6700d;
        return i2 < i ? i : i2;
    }

    /* renamed from: b */
    public final double mo4196b(C0096dk dkVar) {
        int i = dkVar.f6701e;
        if (i == 13) {
            return (double) dkVar.f6699c;
        }
        if (i == 14) {
            return ((Double) this.f6760c.get(dkVar.f6699c)).doubleValue();
        }
        return -1.23456789E8d;
    }

    /* renamed from: a */
    public final C0096dk mo4192a(int i) {
        return (C0096dk) this.f6759b.get(i);
    }

    /* renamed from: c */
    public final int mo4198c(int i) {
        return ((C0096dk) this.f6759b.get(i)).f6701e;
    }

    /* renamed from: a */
    public final String mo4193a(C0096dk dkVar) {
        int i = dkVar.f6697a;
        return this.f6758a.substring(i, dkVar.f6698b + i);
    }

    public final int hashCode() {
        int i = this.f6762e;
        if (i != 0) {
            int i2 = i * 37;
            String str = this.f6758a;
            return ((i2 + (str != null ? str.hashCode() : 0)) * 37) + this.f6759b.hashCode();
        }
        throw null;
    }

    /* renamed from: h */
    private final boolean m6287h(int i) {
        return i > 0 || ((C0096dk) this.f6759b.get(0)).f6701e == 1;
    }

    /* renamed from: g */
    private final boolean m6286g(int i) {
        int i2 = i + 1;
        char charAt = this.f6758a.charAt(i);
        if (charAt != 's' && charAt != 'S') {
            return false;
        }
        int i3 = i2 + 1;
        char charAt2 = this.f6758a.charAt(i2);
        if (charAt2 != 'e' && charAt2 != 'E') {
            return false;
        }
        int i4 = i3 + 1;
        char charAt3 = this.f6758a.charAt(i3);
        if (charAt3 != 'l' && charAt3 != 'L') {
            return false;
        }
        int i5 = i4 + 1;
        char charAt4 = this.f6758a.charAt(i4);
        if (charAt4 != 'e' && charAt4 != 'E') {
            return false;
        }
        int i6 = i5 + 1;
        char charAt5 = this.f6758a.charAt(i5);
        if (charAt5 != 'c' && charAt5 != 'C') {
            return false;
        }
        char charAt6 = this.f6758a.charAt(i6);
        return charAt6 == 't' || charAt6 == 'T';
    }

    /* renamed from: a */
    public final void mo4194a(String str) {
        this.f6758a = str;
        this.f6761d = false;
        this.f6759b.clear();
        ArrayList arrayList = this.f6760c;
        if (arrayList != null) {
            arrayList.clear();
        }
        m6275a(0, 0, 0, 1);
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x009a  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void m6279a(int r7, int r8, boolean r9) {
        /*
            r6 = this;
            int r0 = r7 + 1
            java.lang.String r1 = r6.f6758a
            char r1 = r1.charAt(r7)
            r2 = 0
            r3 = 45
            if (r1 == r3) goto L_0x0023
            r3 = 43
            if (r1 == r3) goto L_0x0013
            r3 = 0
            goto L_0x0032
        L_0x0013:
            if (r0 == r8) goto L_0x0080
            int r1 = r0 + 1
            java.lang.String r3 = r6.f6758a
            char r0 = r3.charAt(r0)
            r3 = 0
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x0032
        L_0x0023:
            if (r0 == r8) goto L_0x0080
            int r1 = r0 + 1
            java.lang.String r3 = r6.f6758a
            char r0 = r3.charAt(r0)
            r3 = 1
            r5 = r1
            r1 = r0
            r0 = r5
        L_0x0032:
            r4 = 8734(0x221e, float:1.2239E-41)
            if (r1 == r4) goto L_0x006e
        L_0x0036:
            r9 = 48
            if (r1 >= r9) goto L_0x003b
            goto L_0x005f
        L_0x003b:
            r9 = 57
            if (r1 > r9) goto L_0x005f
            int r2 = r2 * 10
            int r1 = r1 + -48
            int r2 = r2 + r1
            int r9 = r3 + 32767
            if (r2 > r9) goto L_0x005f
            if (r0 == r8) goto L_0x0055
            int r9 = r0 + 1
            java.lang.String r1 = r6.f6758a
            char r1 = r1.charAt(r0)
            r0 = r9
            goto L_0x0036
        L_0x0055:
            int r8 = r8 - r7
            if (r3 == 0) goto L_0x0059
            int r2 = -r2
        L_0x0059:
            r9 = 13
            r6.m6281b(r9, r7, r8, r2)
            return
        L_0x005f:
            java.lang.String r9 = r6.f6758a
            java.lang.String r9 = r9.substring(r7, r8)
            double r0 = java.lang.Double.parseDouble(r9)
            int r8 = r8 - r7
            r6.m6277a((double) r0, (int) r7, (int) r8)
            return
        L_0x006e:
            if (r9 != 0) goto L_0x0071
            goto L_0x0080
        L_0x0071:
            if (r0 != r8) goto L_0x0080
            if (r3 != 0) goto L_0x0078
            r0 = 9218868437227405312(0x7ff0000000000000, double:Infinity)
            goto L_0x007b
        L_0x0078:
            r0 = -4503599627370496(0xfff0000000000000, double:-Infinity)
        L_0x007b:
            int r8 = r8 - r7
            r6.m6277a((double) r0, (int) r7, (int) r8)
            return
        L_0x0080:
            java.lang.NumberFormatException r9 = new java.lang.NumberFormatException
            java.lang.String r0 = r6.f6758a
            java.lang.String r7 = r0.substring(r7, r8)
            java.lang.String r7 = java.lang.String.valueOf(r7)
            java.lang.String r8 = "Bad syntax for numeric value: "
            int r0 = r7.length()
            if (r0 != 0) goto L_0x009a
            java.lang.String r7 = new java.lang.String
            r7.<init>(r8)
            goto L_0x009e
        L_0x009a:
            java.lang.String r7 = r8.concat(r7)
        L_0x009e:
            r9.<init>(r7)
            goto L_0x00a3
        L_0x00a2:
            throw r9
        L_0x00a3:
            goto L_0x00a2
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0097dl.m6279a(int, int, boolean):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:368:0x06dd, code lost:
        throw new java.lang.IllegalArgumentException(r5.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0140, code lost:
        if (r5 == false) goto L_0x0147;
     */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x01f3  */
    /* JADX WARNING: Removed duplicated region for block: B:247:0x043f  */
    /* JADX WARNING: Removed duplicated region for block: B:250:0x0449  */
    /* JADX WARNING: Removed duplicated region for block: B:438:0x08ee  */
    /* JADX WARNING: Removed duplicated region for block: B:516:0x01d5 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:529:0x0140 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0120  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x014e  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x017d  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int m6275a(int r19, int r20, int r21, int r22) {
        /*
            r18 = this;
            r6 = r18
            r0 = r19
            r1 = r20
            r7 = r21
            r8 = r22
            r2 = 32767(0x7fff, float:4.5916E-41)
            if (r7 > r2) goto L_0x0994
            java.util.ArrayList r2 = r6.f6759b
            int r9 = r2.size()
            r10 = 1
            r6.m6281b(r10, r0, r1, r7)
            int r0 = r0 + r1
            r11 = r0
        L_0x001a:
            java.lang.String r0 = r6.f6758a
            int r0 = r0.length()
            java.lang.String r1 = "Unmatched '{' braces in message "
            r12 = 3
            r2 = 0
            if (r11 >= r0) goto L_0x0952
            int r13 = r11 + 1
            java.lang.String r0 = r6.f6758a
            char r0 = r0.charAt(r11)
            r3 = 35
            r4 = 123(0x7b, float:1.72E-43)
            r5 = 4
            r11 = 125(0x7d, float:1.75E-43)
            r14 = 39
            r15 = 2
            if (r0 != r14) goto L_0x00af
            java.lang.String r0 = r6.f6758a
            int r0 = r0.length()
            if (r13 == r0) goto L_0x00a8
            java.lang.String r0 = r6.f6758a
            char r0 = r0.charAt(r13)
            if (r0 != r14) goto L_0x0050
            int r11 = r13 + 1
            r6.m6281b(r12, r13, r10, r2)
            goto L_0x001a
        L_0x0050:
            int r1 = r6.f6762e
            if (r1 != r15) goto L_0x0055
            goto L_0x0070
        L_0x0055:
            if (r0 == r4) goto L_0x0070
            if (r0 == r11) goto L_0x0070
            if (r8 != r12) goto L_0x005f
            r1 = 124(0x7c, float:1.74E-43)
            if (r0 == r1) goto L_0x0070
        L_0x005f:
            boolean r1 = p000.cun.m5451d(r22)
            if (r1 != 0) goto L_0x0066
            goto L_0x0069
        L_0x0066:
            if (r0 != r3) goto L_0x0069
            goto L_0x0070
        L_0x0069:
            r6.m6281b(r5, r13, r2, r14)
            goto L_0x094e
        L_0x0070:
            int r0 = r13 + -1
            r6.m6281b(r12, r0, r10, r2)
        L_0x0075:
            java.lang.String r0 = r6.f6758a
            int r13 = r13 + r10
            int r0 = r0.indexOf(r14, r13)
            if (r0 < 0) goto L_0x009d
            int r13 = r0 + 1
            java.lang.String r1 = r6.f6758a
            int r1 = r1.length()
            if (r13 >= r1) goto L_0x0095
            java.lang.String r1 = r6.f6758a
            char r1 = r1.charAt(r13)
            if (r1 != r14) goto L_0x0095
            r6.m6281b(r12, r13, r10, r2)
            goto L_0x0075
        L_0x0095:
            r6.m6281b(r12, r0, r10, r2)
            r11 = r13
            goto L_0x001a
        L_0x009d:
            java.lang.String r0 = r6.f6758a
            int r11 = r0.length()
            r6.m6281b(r5, r11, r2, r14)
            goto L_0x001a
        L_0x00a8:
            r6.m6281b(r5, r13, r2, r14)
            goto L_0x094e
        L_0x00af:
            boolean r16 = p000.cun.m5451d(r22)
            if (r16 == 0) goto L_0x00c0
            if (r0 != r3) goto L_0x00c0
            r0 = 5
            int r1 = r13 + -1
            r6.m6281b(r0, r1, r10, r2)
            goto L_0x094e
        L_0x00c0:
            if (r0 != r4) goto L_0x0923
            int r13 = r13 + -1
            java.util.ArrayList r0 = r6.f6759b
            int r0 = r0.size()
            r5 = 6
            r6.m6281b(r5, r13, r10, r2)
            int r13 = r13 + r10
            int r13 = r6.m6283d(r13)
            java.lang.String r3 = r6.f6758a
            int r3 = r3.length()
            if (r13 != r3) goto L_0x00f9
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r2 = r18.m6282c()
            java.lang.String r2 = java.lang.String.valueOf(r2)
            int r3 = r2.length()
            if (r3 != 0) goto L_0x00f1
            java.lang.String r2 = new java.lang.String
            r2.<init>(r1)
            goto L_0x00f5
        L_0x00f1:
            java.lang.String r2 = r1.concat(r2)
        L_0x00f5:
            r0.<init>(r2)
            throw r0
        L_0x00f9:
            int r3 = r6.m6284e(r13)
            java.lang.String r12 = r6.f6758a
            r4 = -1
            if (r13 >= r3) goto L_0x0146
            int r14 = r13 + 1
            char r15 = r12.charAt(r13)
            r5 = 48
            if (r15 == r5) goto L_0x011a
            r5 = 49
            if (r15 >= r5) goto L_0x0112
        L_0x0110:
            r15 = -1
            goto L_0x0147
        L_0x0112:
            r5 = 57
            if (r15 > r5) goto L_0x0110
            int r15 = r15 + -48
            r5 = 0
            goto L_0x011e
        L_0x011a:
            if (r14 == r3) goto L_0x0143
            r5 = 1
            r15 = 0
        L_0x011e:
            if (r14 >= r3) goto L_0x0140
            int r17 = r14 + 1
            char r14 = r12.charAt(r14)
            r11 = 48
            if (r14 < r11) goto L_0x0110
            r11 = 57
            if (r14 > r11) goto L_0x0110
            r11 = 214748364(0xccccccc, float:3.1554434E-31)
            if (r15 >= r11) goto L_0x0134
            goto L_0x0135
        L_0x0134:
            r5 = 1
        L_0x0135:
            int r15 = r15 * 10
            int r14 = r14 + -48
            int r15 = r15 + r14
            r14 = r17
            r11 = 125(0x7d, float:1.75E-43)
            goto L_0x011e
        L_0x0140:
            if (r5 != 0) goto L_0x0146
            goto L_0x0147
        L_0x0143:
            r15 = 0
            goto L_0x0147
        L_0x0146:
            r15 = -2
        L_0x0147:
            java.lang.String r5 = "Bad argument syntax: "
            r11 = 65535(0xffff, float:9.1834E-41)
            if (r15 < 0) goto L_0x017d
            int r12 = r3 - r13
            if (r12 <= r11) goto L_0x0153
            goto L_0x015d
        L_0x0153:
            r14 = 32767(0x7fff, float:4.5916E-41)
            if (r15 > r14) goto L_0x015d
            r14 = 8
            r6.m6281b(r14, r13, r12, r15)
            goto L_0x01c9
        L_0x015d:
            java.lang.IndexOutOfBoundsException r0 = new java.lang.IndexOutOfBoundsException
            java.lang.String r1 = r6.m6288i(r13)
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r2 = "Argument number too large: "
            int r3 = r1.length()
            if (r3 != 0) goto L_0x0175
            java.lang.String r1 = new java.lang.String
            r1.<init>(r2)
            goto L_0x0179
        L_0x0175:
            java.lang.String r1 = r2.concat(r1)
        L_0x0179:
            r0.<init>(r1)
            throw r0
        L_0x017d:
            if (r15 == r4) goto L_0x019d
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = r6.m6288i(r13)
            java.lang.String r1 = java.lang.String.valueOf(r1)
            int r2 = r1.length()
            if (r2 != 0) goto L_0x0195
            java.lang.String r1 = new java.lang.String
            r1.<init>(r5)
            goto L_0x0199
        L_0x0195:
            java.lang.String r1 = r5.concat(r1)
        L_0x0199:
            r0.<init>(r1)
            throw r0
        L_0x019d:
            int r12 = r3 - r13
            if (r12 <= r11) goto L_0x01c1
            java.lang.IndexOutOfBoundsException r0 = new java.lang.IndexOutOfBoundsException
            java.lang.String r1 = r6.m6288i(r13)
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r2 = "Argument name too long: "
            int r3 = r1.length()
            if (r3 != 0) goto L_0x01b9
            java.lang.String r1 = new java.lang.String
            r1.<init>(r2)
            goto L_0x01bd
        L_0x01b9:
            java.lang.String r1 = r2.concat(r1)
        L_0x01bd:
            r0.<init>(r1)
            throw r0
        L_0x01c1:
            r6.f6761d = r10
            r14 = 9
            r6.m6281b(r14, r13, r12, r2)
        L_0x01c9:
            int r3 = r6.m6283d(r3)
            java.lang.String r12 = r6.f6758a
            int r12 = r12.length()
            if (r3 != r12) goto L_0x01f3
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r2 = r18.m6282c()
            java.lang.String r2 = java.lang.String.valueOf(r2)
            int r3 = r2.length()
            if (r3 != 0) goto L_0x01eb
            java.lang.String r2 = new java.lang.String
            r2.<init>(r1)
            goto L_0x01ef
        L_0x01eb:
            java.lang.String r2 = r1.concat(r2)
        L_0x01ef:
            r0.<init>(r2)
            throw r0
        L_0x01f3:
            java.lang.String r12 = r6.f6758a
            char r12 = r12.charAt(r3)
            r14 = 125(0x7d, float:1.75E-43)
            if (r12 != r14) goto L_0x0201
            r12 = r3
            r5 = 1
            goto L_0x0912
        L_0x0201:
            r14 = 44
            if (r12 == r14) goto L_0x0223
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = r6.m6288i(r13)
            java.lang.String r1 = java.lang.String.valueOf(r1)
            int r2 = r1.length()
            if (r2 != 0) goto L_0x021b
            java.lang.String r1 = new java.lang.String
            r1.<init>(r5)
            goto L_0x021f
        L_0x021b:
            java.lang.String r1 = r5.concat(r1)
        L_0x021f:
            r0.<init>(r1)
            throw r0
        L_0x0223:
            int r3 = r3 + 1
            int r3 = r6.m6283d(r3)
            r12 = r3
        L_0x022a:
            java.lang.String r14 = r6.f6758a
            int r14 = r14.length()
            r15 = 97
            r4 = 65
            if (r12 < r14) goto L_0x0237
        L_0x0236:
            goto L_0x024f
        L_0x0237:
            java.lang.String r14 = r6.f6758a
            char r14 = r14.charAt(r12)
            if (r14 < r15) goto L_0x0244
            r10 = 122(0x7a, float:1.71E-43)
            if (r14 > r10) goto L_0x0244
            goto L_0x024a
        L_0x0244:
            if (r14 < r4) goto L_0x0236
            r10 = 90
            if (r14 > r10) goto L_0x024f
        L_0x024a:
            int r12 = r12 + 1
            r4 = -1
            r10 = 1
            goto L_0x022a
        L_0x024f:
            int r10 = r12 - r3
            int r12 = r6.m6283d(r12)
            java.lang.String r14 = r6.f6758a
            int r14 = r14.length()
            if (r12 != r14) goto L_0x027b
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r2 = r18.m6282c()
            java.lang.String r2 = java.lang.String.valueOf(r2)
            int r3 = r2.length()
            if (r3 != 0) goto L_0x0273
            java.lang.String r2 = new java.lang.String
            r2.<init>(r1)
            goto L_0x0277
        L_0x0273:
            java.lang.String r2 = r1.concat(r2)
        L_0x0277:
            r0.<init>(r2)
            throw r0
        L_0x027b:
            if (r10 != 0) goto L_0x027e
            goto L_0x028d
        L_0x027e:
            java.lang.String r14 = r6.f6758a
            char r14 = r14.charAt(r12)
            r2 = 44
            if (r14 == r2) goto L_0x02ab
            r2 = 125(0x7d, float:1.75E-43)
            if (r14 != r2) goto L_0x028d
            goto L_0x02ab
        L_0x028d:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = r6.m6288i(r13)
            java.lang.String r1 = java.lang.String.valueOf(r1)
            int r2 = r1.length()
            if (r2 != 0) goto L_0x02a3
            java.lang.String r1 = new java.lang.String
            r1.<init>(r5)
            goto L_0x02a7
        L_0x02a3:
            java.lang.String r1 = r5.concat(r1)
        L_0x02a7:
            r0.<init>(r1)
            throw r0
        L_0x02ab:
            if (r10 <= r11) goto L_0x02cd
            java.lang.IndexOutOfBoundsException r0 = new java.lang.IndexOutOfBoundsException
            java.lang.String r1 = r6.m6288i(r13)
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r2 = "Argument type name too long: "
            int r3 = r1.length()
            if (r3 != 0) goto L_0x02c5
            java.lang.String r1 = new java.lang.String
            r1.<init>(r2)
            goto L_0x02c9
        L_0x02c5:
            java.lang.String r1 = r2.concat(r1)
        L_0x02c9:
            r0.<init>(r1)
            throw r0
        L_0x02cd:
            r5 = 108(0x6c, float:1.51E-43)
            r11 = 6
            if (r10 != r11) goto L_0x03a6
            int r11 = r3 + 1
            java.lang.String r4 = r6.f6758a
            char r4 = r4.charAt(r3)
            r15 = 99
            if (r4 != r15) goto L_0x02df
            goto L_0x02e3
        L_0x02df:
            r15 = 67
            if (r4 != r15) goto L_0x033a
        L_0x02e3:
            int r4 = r11 + 1
            java.lang.String r15 = r6.f6758a
            char r15 = r15.charAt(r11)
            r2 = 104(0x68, float:1.46E-43)
            if (r15 != r2) goto L_0x02f0
            goto L_0x02f4
        L_0x02f0:
            r2 = 72
            if (r15 != r2) goto L_0x033a
        L_0x02f4:
            int r2 = r4 + 1
            java.lang.String r15 = r6.f6758a
            char r4 = r15.charAt(r4)
            r15 = 111(0x6f, float:1.56E-43)
            if (r4 != r15) goto L_0x0301
            goto L_0x0305
        L_0x0301:
            r15 = 79
            if (r4 != r15) goto L_0x033a
        L_0x0305:
            int r4 = r2 + 1
            java.lang.String r15 = r6.f6758a
            char r2 = r15.charAt(r2)
            r15 = 105(0x69, float:1.47E-43)
            if (r2 != r15) goto L_0x0312
            goto L_0x0316
        L_0x0312:
            r15 = 73
            if (r2 != r15) goto L_0x033a
        L_0x0316:
            int r2 = r4 + 1
            java.lang.String r15 = r6.f6758a
            char r4 = r15.charAt(r4)
            r15 = 99
            if (r4 != r15) goto L_0x0323
            goto L_0x0327
        L_0x0323:
            r15 = 67
            if (r4 != r15) goto L_0x033a
        L_0x0327:
            java.lang.String r4 = r6.f6758a
            char r2 = r4.charAt(r2)
            r4 = 101(0x65, float:1.42E-43)
            if (r2 == r4) goto L_0x0337
            r4 = 69
            if (r2 == r4) goto L_0x0336
            goto L_0x033a
        L_0x0336:
        L_0x0337:
            r5 = 3
            goto L_0x042f
        L_0x033a:
            java.lang.String r2 = r6.f6758a
            char r2 = r2.charAt(r3)
            r4 = 112(0x70, float:1.57E-43)
            if (r2 != r4) goto L_0x0345
            goto L_0x0349
        L_0x0345:
            r4 = 80
            if (r2 != r4) goto L_0x039c
        L_0x0349:
            int r2 = r11 + 1
            java.lang.String r4 = r6.f6758a
            char r4 = r4.charAt(r11)
            if (r4 != r5) goto L_0x0354
            goto L_0x0358
        L_0x0354:
            r11 = 76
            if (r4 != r11) goto L_0x039c
        L_0x0358:
            int r4 = r2 + 1
            java.lang.String r11 = r6.f6758a
            char r2 = r11.charAt(r2)
            r11 = 117(0x75, float:1.64E-43)
            if (r2 != r11) goto L_0x0365
            goto L_0x0369
        L_0x0365:
            r11 = 85
            if (r2 != r11) goto L_0x039c
        L_0x0369:
            int r2 = r4 + 1
            java.lang.String r11 = r6.f6758a
            char r4 = r11.charAt(r4)
            r11 = 114(0x72, float:1.6E-43)
            if (r4 != r11) goto L_0x0376
            goto L_0x037a
        L_0x0376:
            r11 = 82
            if (r4 != r11) goto L_0x039c
        L_0x037a:
            int r4 = r2 + 1
            java.lang.String r11 = r6.f6758a
            char r2 = r11.charAt(r2)
            r11 = 97
            if (r2 != r11) goto L_0x0387
            goto L_0x038c
        L_0x0387:
            r11 = 65
            if (r2 != r11) goto L_0x039c
        L_0x038c:
            java.lang.String r2 = r6.f6758a
            char r2 = r2.charAt(r4)
            if (r2 != r5) goto L_0x0397
        L_0x0394:
            r5 = 4
            goto L_0x042f
        L_0x0397:
            r4 = 76
            if (r2 != r4) goto L_0x039c
            goto L_0x0394
        L_0x039c:
            boolean r2 = r6.m6286g(r3)
            if (r2 != 0) goto L_0x03a3
            goto L_0x03aa
        L_0x03a3:
            r5 = 5
            goto L_0x042f
        L_0x03a6:
            r2 = 13
            if (r10 == r2) goto L_0x03ad
        L_0x03aa:
            r5 = 2
            goto L_0x042f
        L_0x03ad:
            boolean r2 = r6.m6286g(r3)
            if (r2 == 0) goto L_0x03aa
            int r2 = r3 + 6
            int r4 = r2 + 1
            java.lang.String r11 = r6.f6758a
            char r2 = r11.charAt(r2)
            r11 = 111(0x6f, float:1.56E-43)
            if (r2 != r11) goto L_0x03c2
            goto L_0x03c7
        L_0x03c2:
            r11 = 79
            if (r2 == r11) goto L_0x03c7
        L_0x03c6:
            goto L_0x03aa
        L_0x03c7:
            int r2 = r4 + 1
            java.lang.String r11 = r6.f6758a
            char r4 = r11.charAt(r4)
            r11 = 114(0x72, float:1.6E-43)
            if (r4 != r11) goto L_0x03d4
            goto L_0x03d9
        L_0x03d4:
            r11 = 82
            if (r4 == r11) goto L_0x03d9
            goto L_0x03c6
        L_0x03d9:
            int r4 = r2 + 1
            java.lang.String r11 = r6.f6758a
            char r2 = r11.charAt(r2)
            r11 = 100
            if (r2 != r11) goto L_0x03e6
            goto L_0x03eb
        L_0x03e6:
            r11 = 68
            if (r2 == r11) goto L_0x03eb
            goto L_0x03c6
        L_0x03eb:
            int r2 = r4 + 1
            java.lang.String r11 = r6.f6758a
            char r4 = r11.charAt(r4)
            r11 = 105(0x69, float:1.47E-43)
            if (r4 != r11) goto L_0x03f8
            goto L_0x03fd
        L_0x03f8:
            r11 = 73
            if (r4 == r11) goto L_0x03fd
            goto L_0x03c6
        L_0x03fd:
            int r4 = r2 + 1
            java.lang.String r11 = r6.f6758a
            char r2 = r11.charAt(r2)
            r11 = 110(0x6e, float:1.54E-43)
            if (r2 != r11) goto L_0x040a
            goto L_0x040f
        L_0x040a:
            r11 = 78
            if (r2 == r11) goto L_0x040f
            goto L_0x03c6
        L_0x040f:
            int r2 = r4 + 1
            java.lang.String r11 = r6.f6758a
            char r4 = r11.charAt(r4)
            r11 = 97
            if (r4 != r11) goto L_0x041c
            goto L_0x0422
        L_0x041c:
            r11 = 65
            if (r4 == r11) goto L_0x0422
            goto L_0x03c6
        L_0x0422:
            java.lang.String r4 = r6.f6758a
            char r2 = r4.charAt(r2)
            if (r2 == r5) goto L_0x042e
            r4 = 76
            if (r2 != r4) goto L_0x03aa
        L_0x042e:
            r5 = 6
        L_0x042f:
            java.util.ArrayList r2 = r6.f6759b
            java.lang.Object r2 = r2.get(r0)
            dk r2 = (p000.C0096dk) r2
            int r4 = r5 + -1
            short r4 = (short) r4
            r2.f6699c = r4
            r2 = 2
            if (r5 != r2) goto L_0x0445
            r2 = 10
            r4 = 0
            r6.m6281b(r2, r3, r10, r4)
        L_0x0445:
            r2 = 125(0x7d, float:1.75E-43)
            if (r14 == r2) goto L_0x08ee
            int r12 = r12 + 1
            r2 = 2
            if (r5 != r2) goto L_0x04f5
            r3 = r12
            r2 = 0
        L_0x0450:
            java.lang.String r4 = r6.f6758a
            int r4 = r4.length()
            if (r3 >= r4) goto L_0x04d7
            int r4 = r3 + 1
            java.lang.String r10 = r6.f6758a
            char r3 = r10.charAt(r3)
            r10 = 39
            if (r3 != r10) goto L_0x048f
            java.lang.String r3 = r6.f6758a
            int r3 = r3.indexOf(r10, r4)
            if (r3 >= 0) goto L_0x048c
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = r6.m6288i(r12)
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r2 = "Quoted literal argument style text reaches to the end of the message: "
            int r3 = r1.length()
            if (r3 != 0) goto L_0x0484
            java.lang.String r1 = new java.lang.String
            r1.<init>(r2)
            goto L_0x0488
        L_0x0484:
            java.lang.String r1 = r2.concat(r1)
        L_0x0488:
            r0.<init>(r1)
            throw r0
        L_0x048c:
            int r3 = r3 + 1
            goto L_0x04d4
        L_0x048f:
            r10 = 123(0x7b, float:1.72E-43)
            if (r3 != r10) goto L_0x0497
            int r2 = r2 + 1
        L_0x0495:
            r3 = r4
            goto L_0x04d4
        L_0x0497:
            r10 = 125(0x7d, float:1.75E-43)
            if (r3 == r10) goto L_0x049c
            goto L_0x0495
        L_0x049c:
            if (r2 > 0) goto L_0x04d0
            int r3 = r4 + -1
            int r1 = r3 - r12
            r2 = 65535(0xffff, float:9.1834E-41)
            if (r1 <= r2) goto L_0x04c7
            java.lang.IndexOutOfBoundsException r0 = new java.lang.IndexOutOfBoundsException
            java.lang.String r1 = r6.m6288i(r12)
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r2 = "Argument style text too long: "
            int r3 = r1.length()
            if (r3 != 0) goto L_0x04bf
            java.lang.String r1 = new java.lang.String
            r1.<init>(r2)
            goto L_0x04c3
        L_0x04bf:
            java.lang.String r1 = r2.concat(r1)
        L_0x04c3:
            r0.<init>(r1)
            throw r0
        L_0x04c7:
            r2 = 11
            r4 = 0
            r6.m6281b(r2, r12, r1, r4)
            r12 = r3
            goto L_0x0912
        L_0x04d0:
            int r2 = r2 + -1
            r3 = r4
        L_0x04d4:
            goto L_0x0450
        L_0x04d7:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r2 = r18.m6282c()
            java.lang.String r2 = java.lang.String.valueOf(r2)
            int r3 = r2.length()
            if (r3 != 0) goto L_0x04ed
            java.lang.String r2 = new java.lang.String
            r2.<init>(r1)
            goto L_0x04f1
        L_0x04ed:
            java.lang.String r2 = r1.concat(r2)
        L_0x04f1:
            r0.<init>(r2)
            throw r0
        L_0x04f5:
            r1 = 3
            if (r5 == r1) goto L_0x07a2
            r3 = r12
            r1 = 0
            r2 = 1
        L_0x04fb:
            int r3 = r6.m6283d(r3)
            java.lang.String r4 = r6.f6758a
            int r4 = r4.length()
            if (r3 == r4) goto L_0x0509
            r10 = 0
            goto L_0x050b
        L_0x0509:
            r10 = 1
        L_0x050b:
            java.lang.String r11 = " pattern syntax: "
            java.lang.String r13 = "Bad "
            if (r3 == r4) goto L_0x071a
            java.lang.String r4 = r6.f6758a
            char r4 = r4.charAt(r3)
            r14 = 125(0x7d, float:1.75E-43)
            if (r4 == r14) goto L_0x071a
            boolean r4 = p000.cun.m5451d(r5)
            if (r4 == 0) goto L_0x05a2
            java.lang.String r4 = r6.f6758a
            char r4 = r4.charAt(r3)
            r10 = 61
            if (r4 != r10) goto L_0x05a2
            int r2 = r3 + 1
            int r4 = r6.m6285f(r2)
            int r10 = r4 - r3
            r14 = 1
            if (r10 == r14) goto L_0x0566
            r11 = 65535(0xffff, float:9.1834E-41)
            if (r10 <= r11) goto L_0x055b
            java.lang.IndexOutOfBoundsException r0 = new java.lang.IndexOutOfBoundsException
            java.lang.String r1 = r6.m6288i(r3)
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r2 = "Argument selector too long: "
            int r3 = r1.length()
            if (r3 != 0) goto L_0x0553
            java.lang.String r1 = new java.lang.String
            r1.<init>(r2)
            goto L_0x0557
        L_0x0553:
            java.lang.String r1 = r2.concat(r1)
        L_0x0557:
            r0.<init>(r1)
            throw r0
        L_0x055b:
            r11 = 12
            r13 = 0
            r6.m6281b(r11, r3, r10, r13)
            r6.m6279a((int) r2, (int) r4, (boolean) r13)
            goto L_0x067d
        L_0x0566:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = p000.cun.m5450c(r5)
            java.util.Locale r2 = java.util.Locale.ENGLISH
            java.lang.String r1 = r1.toLowerCase(r2)
            java.lang.String r2 = r6.m6288i(r12)
            java.lang.String r3 = java.lang.String.valueOf(r1)
            int r3 = r3.length()
            java.lang.String r4 = java.lang.String.valueOf(r2)
            int r4 = r4.length()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            int r3 = r3 + 21
            int r3 = r3 + r4
            r5.<init>(r3)
            r5.append(r13)
            r5.append(r1)
            r5.append(r11)
            r5.append(r2)
            java.lang.String r1 = r5.toString()
            r0.<init>(r1)
            throw r0
        L_0x05a2:
            int r4 = r6.m6284e(r3)
            int r10 = r4 - r3
            if (r10 == 0) goto L_0x06de
            boolean r11 = p000.cun.m5451d(r5)
            if (r11 == 0) goto L_0x0645
            r11 = 6
            if (r10 != r11) goto L_0x0645
            java.lang.String r13 = r6.f6758a
            int r13 = r13.length()
            if (r4 >= r13) goto L_0x0645
            java.lang.String r13 = r6.f6758a
            r14 = 7
            java.lang.String r15 = "offset:"
            r11 = 0
            boolean r13 = r13.regionMatches(r3, r15, r11, r14)
            if (r13 == 0) goto L_0x0645
            if (r2 != 0) goto L_0x05e9
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = r6.m6288i(r12)
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r2 = "Plural argument 'offset:' (if present) must precede key-message pairs: "
            int r3 = r1.length()
            if (r3 != 0) goto L_0x05e1
            java.lang.String r1 = new java.lang.String
            r1.<init>(r2)
            goto L_0x05e5
        L_0x05e1:
            java.lang.String r1 = r2.concat(r1)
        L_0x05e5:
            r0.<init>(r1)
            throw r0
        L_0x05e9:
            int r4 = r4 + 1
            int r2 = r6.m6283d(r4)
            int r3 = r6.m6285f(r2)
            if (r3 != r2) goto L_0x0615
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = r6.m6288i(r12)
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r2 = "Missing value for plural 'offset:' "
            int r3 = r1.length()
            if (r3 != 0) goto L_0x060d
            java.lang.String r1 = new java.lang.String
            r1.<init>(r2)
            goto L_0x0611
        L_0x060d:
            java.lang.String r1 = r2.concat(r1)
        L_0x0611:
            r0.<init>(r1)
            throw r0
        L_0x0615:
            int r4 = r3 - r2
            r10 = 65535(0xffff, float:9.1834E-41)
            if (r4 <= r10) goto L_0x063c
            java.lang.IndexOutOfBoundsException r0 = new java.lang.IndexOutOfBoundsException
            java.lang.String r1 = r6.m6288i(r2)
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r2 = "Plural offset value too long: "
            int r3 = r1.length()
            if (r3 != 0) goto L_0x0634
            java.lang.String r1 = new java.lang.String
            r1.<init>(r2)
            goto L_0x0638
        L_0x0634:
            java.lang.String r1 = r2.concat(r1)
        L_0x0638:
            r0.<init>(r1)
            throw r0
        L_0x063c:
            r4 = 0
            r6.m6279a((int) r2, (int) r3, (boolean) r4)
            r2 = 0
            goto L_0x04fb
        L_0x0645:
            r2 = 65535(0xffff, float:9.1834E-41)
            if (r10 <= r2) goto L_0x066a
            java.lang.IndexOutOfBoundsException r0 = new java.lang.IndexOutOfBoundsException
            java.lang.String r1 = r6.m6288i(r3)
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r2 = "Argument selector too long: "
            int r3 = r1.length()
            if (r3 != 0) goto L_0x0662
            java.lang.String r1 = new java.lang.String
            r1.<init>(r2)
            goto L_0x0666
        L_0x0662:
            java.lang.String r1 = r2.concat(r1)
        L_0x0666:
            r0.<init>(r1)
            throw r0
        L_0x066a:
            r2 = 12
            r11 = 0
            r6.m6281b(r2, r3, r10, r11)
            java.lang.String r2 = r6.f6758a
            java.lang.String r13 = "other"
            boolean r2 = r2.regionMatches(r3, r13, r11, r10)
            if (r2 == 0) goto L_0x067c
            r1 = 1
            goto L_0x067d
        L_0x067c:
        L_0x067d:
            int r2 = r6.m6283d(r4)
            java.lang.String r4 = r6.f6758a
            int r4 = r4.length()
            if (r2 == r4) goto L_0x069e
            java.lang.String r4 = r6.f6758a
            char r4 = r4.charAt(r2)
            r10 = 123(0x7b, float:1.72E-43)
            if (r4 != r10) goto L_0x069e
            int r3 = r7 + 1
            r4 = 1
            int r3 = r6.m6275a(r2, r4, r3, r5)
            r2 = 0
            goto L_0x04fb
        L_0x069e:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = p000.cun.m5450c(r5)
            java.util.Locale r2 = java.util.Locale.ENGLISH
            java.lang.String r1 = r1.toLowerCase(r2)
            java.lang.String r2 = r6.m6288i(r3)
            java.lang.String r3 = java.lang.String.valueOf(r1)
            int r3 = r3.length()
            java.lang.String r4 = java.lang.String.valueOf(r2)
            int r4 = r4.length()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            int r3 = r3 + 37
            int r3 = r3 + r4
            r5.<init>(r3)
            java.lang.String r3 = "No message fragment after "
            r5.append(r3)
            r5.append(r1)
            java.lang.String r1 = " selector: "
            r5.append(r1)
            r5.append(r2)
            java.lang.String r1 = r5.toString()
            r0.<init>(r1)
            throw r0
        L_0x06de:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = p000.cun.m5450c(r5)
            java.util.Locale r2 = java.util.Locale.ENGLISH
            java.lang.String r1 = r1.toLowerCase(r2)
            java.lang.String r2 = r6.m6288i(r12)
            java.lang.String r3 = java.lang.String.valueOf(r1)
            int r3 = r3.length()
            java.lang.String r4 = java.lang.String.valueOf(r2)
            int r4 = r4.length()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            int r3 = r3 + 21
            int r3 = r3 + r4
            r5.<init>(r3)
            r5.append(r13)
            r5.append(r1)
            r5.append(r11)
            r5.append(r2)
            java.lang.String r1 = r5.toString()
            r0.<init>(r1)
            throw r0
        L_0x071a:
            boolean r2 = r6.m6287h(r7)
            if (r10 == r2) goto L_0x0766
            if (r1 == 0) goto L_0x0725
            r12 = r3
            goto L_0x0912
        L_0x0725:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = p000.cun.m5450c(r5)
            java.util.Locale r2 = java.util.Locale.ENGLISH
            java.lang.String r1 = r1.toLowerCase(r2)
            java.lang.String r2 = r18.m6282c()
            java.lang.String r3 = java.lang.String.valueOf(r1)
            int r3 = r3.length()
            java.lang.String r4 = java.lang.String.valueOf(r2)
            int r4 = r4.length()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r7 = 39
            int r3 = r3 + r7
            int r3 = r3 + r4
            r5.<init>(r3)
            java.lang.String r3 = "Missing 'other' keyword in "
            r5.append(r3)
            r5.append(r1)
            java.lang.String r1 = " pattern in "
            r5.append(r1)
            r5.append(r2)
            java.lang.String r1 = r5.toString()
            r0.<init>(r1)
            throw r0
        L_0x0766:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = p000.cun.m5450c(r5)
            java.util.Locale r2 = java.util.Locale.ENGLISH
            java.lang.String r1 = r1.toLowerCase(r2)
            java.lang.String r2 = r6.m6288i(r12)
            java.lang.String r3 = java.lang.String.valueOf(r1)
            int r3 = r3.length()
            java.lang.String r4 = java.lang.String.valueOf(r2)
            int r4 = r4.length()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            int r3 = r3 + 21
            int r3 = r3 + r4
            r5.<init>(r3)
            r5.append(r13)
            r5.append(r1)
            r5.append(r11)
            r5.append(r2)
            java.lang.String r1 = r5.toString()
            r0.<init>(r1)
            throw r0
        L_0x07a2:
            int r1 = r6.m6283d(r12)
            java.lang.String r2 = r6.f6758a
            int r2 = r2.length()
            if (r1 != r2) goto L_0x07b0
        L_0x07ae:
            goto L_0x08ce
        L_0x07b0:
            java.lang.String r2 = r6.f6758a
            char r2 = r2.charAt(r1)
            r3 = 125(0x7d, float:1.75E-43)
            if (r2 == r3) goto L_0x07ae
        L_0x07ba:
            int r2 = r6.m6285f(r1)
            int r3 = r2 - r1
            java.lang.String r4 = "Bad choice pattern syntax: "
            if (r3 != 0) goto L_0x07e2
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = r6.m6288i(r12)
            java.lang.String r1 = java.lang.String.valueOf(r1)
            int r2 = r1.length()
            if (r2 != 0) goto L_0x07da
            java.lang.String r1 = new java.lang.String
            r1.<init>(r4)
            goto L_0x07de
        L_0x07da:
            java.lang.String r1 = r4.concat(r1)
        L_0x07de:
            r0.<init>(r1)
            throw r0
        L_0x07e2:
            r10 = 65535(0xffff, float:9.1834E-41)
            if (r3 <= r10) goto L_0x0807
            java.lang.IndexOutOfBoundsException r0 = new java.lang.IndexOutOfBoundsException
            java.lang.String r1 = r6.m6288i(r1)
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r2 = "Choice number too long: "
            int r3 = r1.length()
            if (r3 != 0) goto L_0x07ff
            java.lang.String r1 = new java.lang.String
            r1.<init>(r2)
            goto L_0x0803
        L_0x07ff:
            java.lang.String r1 = r2.concat(r1)
        L_0x0803:
            r0.<init>(r1)
            throw r0
        L_0x0807:
            r3 = 1
            r6.m6279a((int) r1, (int) r2, (boolean) r3)
            int r1 = r6.m6283d(r2)
            java.lang.String r2 = r6.f6758a
            int r2 = r2.length()
            if (r1 != r2) goto L_0x0836
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = r6.m6288i(r12)
            java.lang.String r1 = java.lang.String.valueOf(r1)
            int r2 = r1.length()
            if (r2 != 0) goto L_0x082e
            java.lang.String r1 = new java.lang.String
            r1.<init>(r4)
            goto L_0x0832
        L_0x082e:
            java.lang.String r1 = r4.concat(r1)
        L_0x0832:
            r0.<init>(r1)
            throw r0
        L_0x0836:
            java.lang.String r2 = r6.f6758a
            char r2 = r2.charAt(r1)
            r3 = 35
            if (r2 == r3) goto L_0x087c
            r11 = 60
            if (r2 == r11) goto L_0x0879
            r11 = 8804(0x2264, float:1.2337E-41)
            if (r2 != r11) goto L_0x084b
            r11 = 65
            goto L_0x087e
        L_0x084b:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = r6.m6288i(r12)
            java.lang.String r3 = java.lang.String.valueOf(r1)
            int r3 = r3.length()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r11 = 65
            int r3 = r3 + r11
            r4.<init>(r3)
            java.lang.String r3 = "Expected choice separator (#<≤) instead of '"
            r4.append(r3)
            r4.append(r2)
            java.lang.String r2 = "' in choice pattern "
            r4.append(r2)
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            r0.<init>(r1)
            throw r0
        L_0x0879:
            r11 = 65
            goto L_0x087e
        L_0x087c:
            r11 = 65
        L_0x087e:
            r2 = 12
            r13 = 0
            r14 = 1
            r6.m6281b(r2, r1, r14, r13)
            int r1 = r1 + 1
            int r2 = r7 + 1
            r14 = 3
            int r1 = r6.m6275a(r1, r13, r2, r14)
            java.lang.String r2 = r6.f6758a
            int r2 = r2.length()
            if (r1 == r2) goto L_0x08cc
            java.lang.String r2 = r6.f6758a
            char r2 = r2.charAt(r1)
            r13 = 125(0x7d, float:1.75E-43)
            if (r2 == r13) goto L_0x08a8
            int r1 = r1 + 1
            int r1 = r6.m6283d(r1)
            goto L_0x07ba
        L_0x08a8:
            boolean r2 = r6.m6287h(r7)
            if (r2 != 0) goto L_0x08cc
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = r6.m6288i(r12)
            java.lang.String r1 = java.lang.String.valueOf(r1)
            int r2 = r1.length()
            if (r2 != 0) goto L_0x08c4
            java.lang.String r1 = new java.lang.String
            r1.<init>(r4)
            goto L_0x08c8
        L_0x08c4:
            java.lang.String r1 = r4.concat(r1)
        L_0x08c8:
            r0.<init>(r1)
            throw r0
        L_0x08cc:
            r12 = r1
            goto L_0x0912
        L_0x08ce:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = r18.m6282c()
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r2 = "Missing choice argument pattern in "
            int r3 = r1.length()
            if (r3 != 0) goto L_0x08e6
            java.lang.String r1 = new java.lang.String
            r1.<init>(r2)
            goto L_0x08ea
        L_0x08e6:
            java.lang.String r1 = r2.concat(r1)
        L_0x08ea:
            r0.<init>(r1)
            throw r0
        L_0x08ee:
            r1 = 2
            if (r5 == r1) goto L_0x0912
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = r6.m6288i(r13)
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r2 = "No style field for complex argument: "
            int r3 = r1.length()
            if (r3 != 0) goto L_0x090a
            java.lang.String r1 = new java.lang.String
            r1.<init>(r2)
            goto L_0x090e
        L_0x090a:
            java.lang.String r1 = r2.concat(r1)
        L_0x090e:
            r0.<init>(r1)
            throw r0
        L_0x0912:
            r2 = 7
            r4 = 1
            r1 = -1
            int r5 = r5 + r1
            r1 = r0
            r0 = r18
            r3 = r12
            r0.m6278a(r1, r2, r3, r4, r5)
            r0 = 1
            int r11 = r12 + 1
            r10 = 1
            goto L_0x001a
        L_0x0923:
            if (r7 <= 0) goto L_0x092c
            r1 = 125(0x7d, float:1.75E-43)
            if (r0 == r1) goto L_0x092a
            goto L_0x092c
        L_0x092a:
            r1 = 3
            goto L_0x0933
        L_0x092c:
            r1 = 3
            if (r8 != r1) goto L_0x094d
            r2 = 124(0x7c, float:1.74E-43)
            if (r0 != r2) goto L_0x094d
        L_0x0933:
            if (r8 == r1) goto L_0x0937
        L_0x0935:
            r4 = 1
            goto L_0x093c
        L_0x0937:
            r1 = 125(0x7d, float:1.75E-43)
            if (r0 != r1) goto L_0x0935
            r4 = 0
        L_0x093c:
            int r10 = r13 + -1
            r2 = 2
            r0 = r18
            r1 = r9
            r3 = r10
            r5 = r21
            r0.m6278a(r1, r2, r3, r4, r5)
            r0 = 3
            if (r8 != r0) goto L_0x094c
            return r10
        L_0x094c:
            return r13
        L_0x094d:
        L_0x094e:
            r11 = r13
            r10 = 1
            goto L_0x001a
        L_0x0952:
            if (r7 > 0) goto L_0x0955
            goto L_0x096a
        L_0x0955:
            r0 = 1
            if (r7 == r0) goto L_0x0959
            goto L_0x0976
        L_0x0959:
            r0 = 3
            if (r8 != r0) goto L_0x0976
            java.util.ArrayList r0 = r6.f6759b
            r2 = 0
            java.lang.Object r0 = r0.get(r2)
            dk r0 = (p000.C0096dk) r0
            int r0 = r0.f6701e
            r2 = 1
            if (r0 == r2) goto L_0x0976
        L_0x096a:
            r2 = 2
            r4 = 0
            r0 = r18
            r1 = r9
            r3 = r11
            r5 = r21
            r0.m6278a(r1, r2, r3, r4, r5)
            return r11
        L_0x0976:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r2 = r18.m6282c()
            java.lang.String r2 = java.lang.String.valueOf(r2)
            int r3 = r2.length()
            if (r3 != 0) goto L_0x098c
            java.lang.String r2 = new java.lang.String
            r2.<init>(r1)
            goto L_0x0990
        L_0x098c:
            java.lang.String r2 = r1.concat(r2)
        L_0x0990:
            r0.<init>(r2)
            throw r0
        L_0x0994:
            java.lang.IndexOutOfBoundsException r0 = new java.lang.IndexOutOfBoundsException
            r0.<init>()
            goto L_0x099b
        L_0x099a:
            throw r0
        L_0x099b:
            goto L_0x099a
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0097dl.m6275a(int, int, int, int):int");
    }

    /* renamed from: a */
    public final boolean mo4195a(C0096dk dkVar, String str) {
        return this.f6758a.regionMatches(dkVar.f6697a, str, 0, dkVar.f6698b);
    }

    /* renamed from: c */
    private final String m6282c() {
        return m6276a(this.f6758a, 0);
    }

    /* renamed from: i */
    private final String m6288i(int i) {
        return m6276a(this.f6758a, i);
    }

    /* renamed from: a */
    private static String m6276a(String str, int i) {
        StringBuilder sb = new StringBuilder(44);
        if (i == 0) {
            sb.append("\"");
        } else {
            sb.append("[at pattern index ");
            sb.append(i);
            sb.append("] \"");
        }
        if (str.length() - i > 24) {
            int i2 = i + 20;
            int i3 = i2 - 1;
            if (Character.isHighSurrogate(str.charAt(i3))) {
                i2 = i3;
            }
            sb.append(str, i, i2);
            sb.append(" ...");
        } else {
            if (i != 0) {
                str = str.substring(i);
            }
            sb.append(str);
        }
        sb.append("\"");
        return sb.toString();
    }

    /* renamed from: f */
    private final int m6285f(int i) {
        while (i < this.f6758a.length() && (((r0 = this.f6758a.charAt(i)) >= '0' || "+-.".indexOf(r0) >= 0) && (r0 <= '9' || r0 == 'e' || r0 == 'E' || r0 == 8734))) {
            i++;
        }
        return i;
    }

    /* renamed from: e */
    private final int m6284e(int i) {
        return C0074cp.m5206b(this.f6758a, i);
    }

    /* renamed from: d */
    private final int m6283d(int i) {
        return C0074cp.m5203a(this.f6758a, i);
    }
}
