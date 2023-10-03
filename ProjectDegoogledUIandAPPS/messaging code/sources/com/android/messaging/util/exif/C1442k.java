package com.android.messaging.util.exif;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.util.exif.k */
public class C1442k {
    private static Charset US_ASCII = Charset.forName("US-ASCII");

    /* renamed from: aM */
    private static final int[] f2296aM = new int[11];

    /* renamed from: WL */
    private final short f2297WL;

    /* renamed from: XL */
    private final short f2298XL;

    /* renamed from: YL */
    private boolean f2299YL;

    /* renamed from: ZL */
    private int f2300ZL;

    /* renamed from: _L */
    private int f2301_L;
    private int mOffset;
    private Object mValue = null;

    static {
        int[] iArr = f2296aM;
        iArr[1] = 1;
        iArr[2] = 1;
        iArr[3] = 2;
        iArr[4] = 4;
        iArr[5] = 8;
        iArr[7] = 1;
        iArr[9] = 4;
        iArr[10] = 8;
        new SimpleDateFormat("yyyy:MM:dd kk:mm:ss");
    }

    C1442k(short s, short s2, int i, int i2, boolean z) {
        this.f2297WL = s;
        this.f2298XL = s2;
        this.f2300ZL = i;
        this.f2299YL = z;
        this.f2301_L = i2;
    }

    /* renamed from: Mb */
    private boolean m3691Mb(int i) {
        return this.f2299YL && this.f2300ZL != i;
    }

    /* renamed from: Za */
    public static boolean m3692Za(int i) {
        return i == 0 || i == 1 || i == 2 || i == 3 || i == 4;
    }

    /* renamed from: b */
    public static boolean m3693b(short s) {
        return s == 1 || s == 2 || s == 3 || s == 4 || s == 5 || s == 7 || s == 9 || s == 10;
    }

    /* renamed from: f */
    private static String m3694f(short s) {
        switch (s) {
            case 1:
                return "UNSIGNED_BYTE";
            case 2:
                return "ASCII";
            case 3:
                return "UNSIGNED_SHORT";
            case 4:
                return "UNSIGNED_LONG";
            case 5:
                return "UNSIGNED_RATIONAL";
            case 7:
                return "UNDEFINED";
            case 9:
                return "LONG";
            case 10:
                return "RATIONAL";
            default:
                return "";
        }
    }

    /* renamed from: Ek */
    public int mo8121Ek() {
        return this.f2301_L;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Fk */
    public byte[] mo8122Fk() {
        return (byte[]) this.mValue;
    }

    /* renamed from: Gk */
    public short mo8123Gk() {
        return this.f2297WL;
    }

    /* renamed from: Hk */
    public int[] mo8124Hk() {
        Object obj = this.mValue;
        int[] iArr = null;
        if (obj == null) {
            return null;
        }
        if (obj instanceof long[]) {
            long[] jArr = (long[]) obj;
            iArr = new int[jArr.length];
            for (int i = 0; i < jArr.length; i++) {
                iArr[i] = (int) jArr[i];
            }
        }
        return iArr;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Ik */
    public boolean mo8125Ik() {
        return this.f2299YL;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Wa */
    public void mo8126Wa(int i) {
        this.f2300ZL = i;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Xa */
    public C1446o mo8127Xa(int i) {
        short s = this.f2298XL;
        if (s == 10 || s == 5) {
            return ((C1446o[]) this.mValue)[i];
        }
        StringBuilder Pa = C0632a.m1011Pa("Cannot get RATIONAL value from ");
        Pa.append(m3694f(this.f2298XL));
        throw new IllegalArgumentException(Pa.toString());
    }

    /* access modifiers changed from: protected */
    /* renamed from: Ya */
    public long mo8128Ya(int i) {
        Object obj = this.mValue;
        if (obj instanceof long[]) {
            return ((long[]) obj)[i];
        }
        if (obj instanceof byte[]) {
            return (long) ((byte[]) obj)[i];
        }
        StringBuilder Pa = C0632a.m1011Pa("Cannot get integer value from ");
        Pa.append(m3694f(this.f2298XL));
        throw new IllegalArgumentException(Pa.toString());
    }

    /* access modifiers changed from: protected */
    /* renamed from: _a */
    public void mo8129_a(int i) {
        this.f2301_L = i;
    }

    /* renamed from: a */
    public boolean mo8130a(long[] jArr) {
        boolean z;
        if (m3691Mb(jArr.length) || this.f2298XL != 4) {
            return false;
        }
        int length = jArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = false;
                break;
            }
            long j = jArr[i];
            if (j < 0 || j > 4294967295L) {
                z = true;
            } else {
                i++;
            }
        }
        z = true;
        if (z) {
            return false;
        }
        this.mValue = jArr;
        this.f2300ZL = jArr.length;
        return true;
    }

    /* renamed from: d */
    public boolean mo8132d(int[] iArr) {
        boolean z;
        boolean z2;
        if (m3691Mb(iArr.length)) {
            return false;
        }
        short s = this.f2298XL;
        if (s != 3 && s != 9 && s != 4) {
            return false;
        }
        if (this.f2298XL == 3) {
            int length = iArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    z2 = false;
                    break;
                }
                int i2 = iArr[i];
                if (i2 > 65535 || i2 < 0) {
                    z2 = true;
                } else {
                    i++;
                }
            }
            if (z2) {
                return false;
            }
        }
        if (this.f2298XL == 4) {
            int length2 = iArr.length;
            int i3 = 0;
            while (true) {
                if (i3 >= length2) {
                    z = false;
                    break;
                } else if (iArr[i3] < 0) {
                    z = true;
                    break;
                } else {
                    i3++;
                }
            }
            if (z) {
                return false;
            }
        }
        long[] jArr = new long[iArr.length];
        for (int i4 = 0; i4 < iArr.length; i4++) {
            jArr[i4] = (long) iArr[i4];
        }
        this.mValue = jArr;
        this.f2300ZL = iArr.length;
        return true;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof C1442k)) {
            return false;
        }
        C1442k kVar = (C1442k) obj;
        if (kVar.f2297WL != this.f2297WL || kVar.f2300ZL != this.f2300ZL || kVar.f2298XL != this.f2298XL) {
            return false;
        }
        Object obj2 = this.mValue;
        if (obj2 != null) {
            Object obj3 = kVar.mValue;
            if (obj3 == null) {
                return false;
            }
            if (obj2 instanceof long[]) {
                if (!(obj3 instanceof long[])) {
                    return false;
                }
                return Arrays.equals((long[]) obj2, (long[]) obj3);
            } else if (obj2 instanceof C1446o[]) {
                if (!(obj3 instanceof C1446o[])) {
                    return false;
                }
                return Arrays.equals((C1446o[]) obj2, (C1446o[]) obj3);
            } else if (!(obj2 instanceof byte[])) {
                return obj2.equals(obj3);
            } else {
                if (!(obj3 instanceof byte[])) {
                    return false;
                }
                return Arrays.equals((byte[]) obj2, (byte[]) obj3);
            }
        } else if (kVar.mValue == null) {
            return true;
        } else {
            return false;
        }
    }

    public int getComponentCount() {
        return this.f2300ZL;
    }

    public int getDataSize() {
        return this.f2300ZL * f2296aM[this.f2298XL];
    }

    public short getDataType() {
        return this.f2298XL;
    }

    /* access modifiers changed from: protected */
    public int getOffset() {
        return this.mOffset;
    }

    public Object getValue() {
        return this.mValue;
    }

    public boolean hasValue() {
        return this.mValue != null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: na */
    public void mo8140na(boolean z) {
        this.f2299YL = z;
    }

    /* access modifiers changed from: protected */
    /* renamed from: q */
    public void mo8141q(byte[] bArr) {
        int length = bArr.length;
        short s = this.f2298XL;
        if (s == 7 || s == 1) {
            Object obj = this.mValue;
            int i = this.f2300ZL;
            if (length <= i) {
                i = length;
            }
            System.arraycopy(obj, 0, bArr, 0, i);
            return;
        }
        StringBuilder Pa = C0632a.m1011Pa("Cannot get BYTE value from ");
        Pa.append(m3694f(this.f2298XL));
        throw new IllegalArgumentException(Pa.toString());
    }

    /* access modifiers changed from: protected */
    public void setOffset(int i) {
        this.mOffset = i;
    }

    public boolean setValue(int i) {
        return mo8132d(new int[]{i});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("tag id: %04X\n", new Object[]{Short.valueOf(this.f2297WL)}));
        sb.append("ifd id: ");
        sb.append(this.f2301_L);
        sb.append("\ntype: ");
        sb.append(m3694f(this.f2298XL));
        sb.append("\ncount: ");
        sb.append(this.f2300ZL);
        sb.append("\noffset: ");
        sb.append(this.mOffset);
        sb.append("\nvalue: ");
        Object obj = this.mValue;
        String str = "";
        if (obj != null) {
            if (obj instanceof byte[]) {
                str = this.f2298XL == 2 ? new String((byte[]) obj, US_ASCII) : Arrays.toString((byte[]) obj);
            } else if (obj instanceof long[]) {
                long[] jArr = (long[]) obj;
                str = jArr.length == 1 ? String.valueOf(jArr[0]) : Arrays.toString(jArr);
            } else if (obj instanceof Object[]) {
                Object[] objArr = (Object[]) obj;
                if (objArr.length == 1) {
                    Object obj2 = objArr[0];
                    if (obj2 != null) {
                        str = obj2.toString();
                    }
                } else {
                    str = Arrays.toString(objArr);
                }
            } else {
                str = obj.toString();
            }
        }
        sb.append(str);
        sb.append("\n");
        return sb.toString();
    }

    public boolean setValue(String str) {
        short s = this.f2298XL;
        if (s != 2 && s != 7) {
            return false;
        }
        byte[] bytes = str.getBytes(US_ASCII);
        if (bytes.length > 0) {
            if (!(bytes[bytes.length - 1] == 0 || this.f2298XL == 7)) {
                bytes = Arrays.copyOf(bytes, bytes.length + 1);
            }
        } else if (this.f2298XL == 2 && this.f2300ZL == 1) {
            bytes = new byte[]{0};
        }
        int length = bytes.length;
        if (m3691Mb(length)) {
            return false;
        }
        this.f2300ZL = length;
        this.mValue = bytes;
        return true;
    }

    /* renamed from: a */
    public boolean mo8131a(C1446o[] oVarArr) {
        boolean z;
        boolean z2;
        if (m3691Mb(oVarArr.length)) {
            return false;
        }
        short s = this.f2298XL;
        if (s != 5 && s != 10) {
            return false;
        }
        if (this.f2298XL == 5) {
            int length = oVarArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    z2 = false;
                    break;
                }
                C1446o oVar = oVarArr[i];
                if (oVar.getNumerator() < 0 || oVar.getDenominator() < 0 || oVar.getNumerator() > 4294967295L || oVar.getDenominator() > 4294967295L) {
                    z2 = true;
                } else {
                    i++;
                }
            }
            z2 = true;
            if (z2) {
                return false;
            }
        }
        if (this.f2298XL == 10) {
            int length2 = oVarArr.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length2) {
                    z = false;
                    break;
                }
                C1446o oVar2 = oVarArr[i2];
                if (oVar2.getNumerator() < -2147483648L || oVar2.getDenominator() < -2147483648L || oVar2.getNumerator() > 2147483647L || oVar2.getDenominator() > 2147483647L) {
                    z = true;
                } else {
                    i2++;
                }
            }
            z = true;
            if (z) {
                return false;
            }
        }
        this.mValue = oVarArr;
        this.f2300ZL = oVarArr.length;
        return true;
    }

    public boolean setValue(byte[] bArr) {
        int length = bArr.length;
        if (m3691Mb(length)) {
            return false;
        }
        short s = this.f2298XL;
        if (s != 1 && s != 7) {
            return false;
        }
        this.mValue = new byte[length];
        System.arraycopy(bArr, 0, this.mValue, 0, length);
        this.f2300ZL = length;
        return true;
    }
}
