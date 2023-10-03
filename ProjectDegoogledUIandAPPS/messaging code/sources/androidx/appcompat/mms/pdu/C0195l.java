package androidx.appcompat.mms.pdu;

import java.util.ArrayList;
import java.util.HashMap;

/* renamed from: androidx.appcompat.mms.pdu.l */
public class C0195l {

    /* renamed from: fm */
    private HashMap f182fm;

    public C0195l() {
        this.f182fm = null;
        this.f182fm = new HashMap();
    }

    /* access modifiers changed from: protected */
    /* renamed from: C */
    public C0188e mo1243C(int i) {
        return (C0188e) this.f182fm.get(Integer.valueOf(i));
    }

    /* access modifiers changed from: protected */
    /* renamed from: D */
    public C0188e[] mo1244D(int i) {
        ArrayList arrayList = (ArrayList) this.f182fm.get(Integer.valueOf(i));
        if (arrayList == null) {
            return null;
        }
        return (C0188e[]) arrayList.toArray(new C0188e[arrayList.size()]);
    }

    /* access modifiers changed from: protected */
    /* renamed from: E */
    public long mo1245E(int i) {
        Long l = (Long) this.f182fm.get(Integer.valueOf(i));
        if (l == null) {
            return -1;
        }
        return l.longValue();
    }

    /* access modifiers changed from: protected */
    /* renamed from: F */
    public int mo1246F(int i) {
        Integer num = (Integer) this.f182fm.get(Integer.valueOf(i));
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    /* access modifiers changed from: protected */
    /* renamed from: G */
    public byte[] mo1247G(int i) {
        return (byte[]) this.f182fm.get(Integer.valueOf(i));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo1249a(C0188e eVar, int i) {
        if (eVar == null) {
            throw new NullPointerException();
        } else if (i == 129 || i == 130 || i == 151) {
            ArrayList arrayList = (ArrayList) this.f182fm.get(Integer.valueOf(i));
            if (arrayList == null) {
                arrayList = new ArrayList();
            }
            arrayList.add(eVar);
            this.f182fm.put(Integer.valueOf(i), arrayList);
        } else {
            throw new RuntimeException("Invalid header field!");
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo1251b(byte[] bArr, int i) {
        if (bArr != null) {
            if (!(i == 131 || i == 132 || i == 138 || i == 139 || i == 152 || i == 158 || i == 189 || i == 190)) {
                switch (i) {
                    case 183:
                    case 184:
                    case 185:
                        break;
                    default:
                        throw new RuntimeException("Invalid header field!");
                }
            }
            this.f182fm.put(Integer.valueOf(i), bArr);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0109, code lost:
        if (r9 < 192) goto L_0x0132;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x010b, code lost:
        if (r9 > 255) goto L_0x0132;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x011a, code lost:
        if (r9 <= 255) goto L_0x0132;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0121, code lost:
        if (r9 < 192) goto L_0x0132;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x0123, code lost:
        if (r9 > 255) goto L_0x0132;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0079, code lost:
        if (r9 <= 255) goto L_0x0132;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0081, code lost:
        if (r9 < 192) goto L_0x0132;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0083, code lost:
        if (r9 > 255) goto L_0x0132;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0102, code lost:
        if (r9 <= 255) goto L_0x0132;
     */
    /* renamed from: e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo1252e(int r9, int r10) {
        /*
            r8 = this;
            r0 = 134(0x86, float:1.88E-43)
            r1 = 129(0x81, float:1.81E-43)
            r2 = 192(0xc0, float:2.69E-43)
            r3 = 224(0xe0, float:3.14E-43)
            java.lang.String r4 = "Invalid Octet value!"
            r5 = 128(0x80, float:1.794E-43)
            if (r10 == r0) goto L_0x0126
            r0 = 153(0x99, float:2.14E-43)
            r6 = 255(0xff, float:3.57E-43)
            if (r10 == r0) goto L_0x010e
            r0 = 165(0xa5, float:2.31E-43)
            if (r10 == r0) goto L_0x00f6
            r0 = 167(0xa7, float:2.34E-43)
            if (r10 == r0) goto L_0x0126
            r0 = 169(0xa9, float:2.37E-43)
            if (r10 == r0) goto L_0x0126
            r0 = 171(0xab, float:2.4E-43)
            if (r10 == r0) goto L_0x0126
            r0 = 177(0xb1, float:2.48E-43)
            if (r10 == r0) goto L_0x0126
            r0 = 180(0xb4, float:2.52E-43)
            if (r10 == r0) goto L_0x00ed
            r0 = 191(0xbf, float:2.68E-43)
            if (r10 == r0) goto L_0x00e2
            r0 = 140(0x8c, float:1.96E-43)
            if (r10 == r0) goto L_0x00d5
            r0 = 141(0x8d, float:1.98E-43)
            if (r10 == r0) goto L_0x00c9
            r0 = 148(0x94, float:2.07E-43)
            if (r10 == r0) goto L_0x0126
            r0 = 149(0x95, float:2.09E-43)
            r7 = 135(0x87, float:1.89E-43)
            if (r10 == r0) goto L_0x00bd
            r0 = 155(0x9b, float:2.17E-43)
            if (r10 == r0) goto L_0x00b1
            r0 = 156(0x9c, float:2.19E-43)
            if (r10 == r0) goto L_0x00a3
            r0 = 162(0xa2, float:2.27E-43)
            if (r10 == r0) goto L_0x0126
            r0 = 163(0xa3, float:2.28E-43)
            if (r10 == r0) goto L_0x0095
            switch(r10) {
                case 143: goto L_0x0087;
                case 144: goto L_0x0126;
                case 145: goto L_0x0126;
                case 146: goto L_0x006c;
                default: goto L_0x0055;
            }
        L_0x0055:
            switch(r10) {
                case 186: goto L_0x0060;
                case 187: goto L_0x0126;
                case 188: goto L_0x0126;
                default: goto L_0x0058;
            }
        L_0x0058:
            java.lang.RuntimeException r8 = new java.lang.RuntimeException
            java.lang.String r9 = "Invalid header field!"
            r8.<init>(r9)
            throw r8
        L_0x0060:
            if (r9 < r5) goto L_0x0066
            if (r9 > r7) goto L_0x0066
            goto L_0x0131
        L_0x0066:
            androidx.appcompat.mms.pdu.InvalidHeaderValueException r8 = new androidx.appcompat.mms.pdu.InvalidHeaderValueException
            r8.<init>(r4)
            throw r8
        L_0x006c:
            r0 = 196(0xc4, float:2.75E-43)
            if (r9 <= r0) goto L_0x0075
            if (r9 >= r3) goto L_0x0075
        L_0x0072:
            r3 = r2
            goto L_0x0132
        L_0x0075:
            r0 = 235(0xeb, float:3.3E-43)
            if (r9 <= r0) goto L_0x007b
            if (r9 <= r6) goto L_0x0132
        L_0x007b:
            if (r9 < r5) goto L_0x0132
            r0 = 136(0x88, float:1.9E-43)
            if (r9 <= r0) goto L_0x0083
            if (r9 < r2) goto L_0x0132
        L_0x0083:
            if (r9 <= r6) goto L_0x0131
            goto L_0x0132
        L_0x0087:
            if (r9 < r5) goto L_0x008f
            r0 = 130(0x82, float:1.82E-43)
            if (r9 > r0) goto L_0x008f
            goto L_0x0131
        L_0x008f:
            androidx.appcompat.mms.pdu.InvalidHeaderValueException r8 = new androidx.appcompat.mms.pdu.InvalidHeaderValueException
            r8.<init>(r4)
            throw r8
        L_0x0095:
            if (r9 < r5) goto L_0x009d
            r0 = 132(0x84, float:1.85E-43)
            if (r9 > r0) goto L_0x009d
            goto L_0x0131
        L_0x009d:
            androidx.appcompat.mms.pdu.InvalidHeaderValueException r8 = new androidx.appcompat.mms.pdu.InvalidHeaderValueException
            r8.<init>(r4)
            throw r8
        L_0x00a3:
            if (r9 < r5) goto L_0x00ab
            r0 = 131(0x83, float:1.84E-43)
            if (r9 > r0) goto L_0x00ab
            goto L_0x0131
        L_0x00ab:
            androidx.appcompat.mms.pdu.InvalidHeaderValueException r8 = new androidx.appcompat.mms.pdu.InvalidHeaderValueException
            r8.<init>(r4)
            throw r8
        L_0x00b1:
            if (r5 == r9) goto L_0x0131
            if (r1 != r9) goto L_0x00b7
            goto L_0x0131
        L_0x00b7:
            androidx.appcompat.mms.pdu.InvalidHeaderValueException r8 = new androidx.appcompat.mms.pdu.InvalidHeaderValueException
            r8.<init>(r4)
            throw r8
        L_0x00bd:
            if (r9 < r5) goto L_0x00c3
            if (r9 > r7) goto L_0x00c3
            goto L_0x0131
        L_0x00c3:
            androidx.appcompat.mms.pdu.InvalidHeaderValueException r8 = new androidx.appcompat.mms.pdu.InvalidHeaderValueException
            r8.<init>(r4)
            throw r8
        L_0x00c9:
            r0 = 16
            if (r9 < r0) goto L_0x00d1
            r0 = 19
            if (r9 <= r0) goto L_0x0131
        L_0x00d1:
            r9 = 18
            goto L_0x0131
        L_0x00d5:
            if (r9 < r5) goto L_0x00dc
            r0 = 151(0x97, float:2.12E-43)
            if (r9 > r0) goto L_0x00dc
            goto L_0x0131
        L_0x00dc:
            androidx.appcompat.mms.pdu.InvalidHeaderValueException r8 = new androidx.appcompat.mms.pdu.InvalidHeaderValueException
            r8.<init>(r4)
            throw r8
        L_0x00e2:
            if (r5 == r9) goto L_0x0131
            if (r1 != r9) goto L_0x00e7
            goto L_0x0131
        L_0x00e7:
            androidx.appcompat.mms.pdu.InvalidHeaderValueException r8 = new androidx.appcompat.mms.pdu.InvalidHeaderValueException
            r8.<init>(r4)
            throw r8
        L_0x00ed:
            if (r5 != r9) goto L_0x00f0
            goto L_0x0131
        L_0x00f0:
            androidx.appcompat.mms.pdu.InvalidHeaderValueException r8 = new androidx.appcompat.mms.pdu.InvalidHeaderValueException
            r8.<init>(r4)
            throw r8
        L_0x00f6:
            r0 = 193(0xc1, float:2.7E-43)
            if (r9 <= r0) goto L_0x00fe
            if (r9 >= r3) goto L_0x00fe
            goto L_0x0072
        L_0x00fe:
            r0 = 228(0xe4, float:3.2E-43)
            if (r9 <= r0) goto L_0x0105
            if (r9 > r6) goto L_0x0105
            goto L_0x0132
        L_0x0105:
            if (r9 < r5) goto L_0x0132
            if (r9 <= r5) goto L_0x010b
            if (r9 < r2) goto L_0x0132
        L_0x010b:
            if (r9 <= r6) goto L_0x0131
            goto L_0x0132
        L_0x010e:
            r0 = 194(0xc2, float:2.72E-43)
            if (r9 <= r0) goto L_0x0116
            if (r9 >= r3) goto L_0x0116
            goto L_0x0072
        L_0x0116:
            r0 = 227(0xe3, float:3.18E-43)
            if (r9 <= r0) goto L_0x011d
            if (r9 > r6) goto L_0x011d
            goto L_0x0132
        L_0x011d:
            if (r9 < r5) goto L_0x0132
            if (r9 <= r5) goto L_0x0123
            if (r9 < r2) goto L_0x0132
        L_0x0123:
            if (r9 <= r6) goto L_0x0131
            goto L_0x0132
        L_0x0126:
            if (r5 == r9) goto L_0x0131
            if (r1 != r9) goto L_0x012b
            goto L_0x0131
        L_0x012b:
            androidx.appcompat.mms.pdu.InvalidHeaderValueException r8 = new androidx.appcompat.mms.pdu.InvalidHeaderValueException
            r8.<init>(r4)
            throw r8
        L_0x0131:
            r3 = r9
        L_0x0132:
            java.util.HashMap r8 = r8.f182fm
            java.lang.Integer r9 = java.lang.Integer.valueOf(r10)
            java.lang.Integer r10 = java.lang.Integer.valueOf(r3)
            r8.put(r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.mms.pdu.C0195l.mo1252e(int, int):void");
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo1250b(C0188e eVar, int i) {
        if (eVar == null) {
            throw new NullPointerException();
        } else if (i == 137 || i == 147 || i == 150 || i == 154 || i == 160 || i == 164 || i == 166 || i == 181 || i == 182) {
            this.f182fm.put(Integer.valueOf(i), eVar);
        } else {
            throw new RuntimeException("Invalid header field!");
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo1248a(long j, int i) {
        if (i == 133 || i == 142 || i == 157 || i == 159 || i == 161 || i == 173 || i == 175 || i == 179 || i == 135 || i == 136) {
            this.f182fm.put(Integer.valueOf(i), Long.valueOf(j));
            return;
        }
        throw new RuntimeException("Invalid header field!");
    }
}
