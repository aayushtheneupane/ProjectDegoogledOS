package com.android.dialer.callcomposer.camera.exif;

import com.android.dialer.searchfragment.list.NewSearchFragment;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Objects;

public class ExifTag {
    private static final int[] TYPE_TO_SIZE_MAP = new int[11];
    private static final Charset US_ASCII = Charset.forName("US-ASCII");
    private int componentCountActual;
    private final short dataType;
    private boolean hasDefinedDefaultComponentCount;
    private int ifd;
    private int offset;
    private final short tagId;
    private Object value = null;

    static {
        int[] iArr = TYPE_TO_SIZE_MAP;
        iArr[1] = 1;
        iArr[2] = 1;
        iArr[3] = 2;
        iArr[4] = 4;
        iArr[5] = 8;
        iArr[7] = 1;
        iArr[9] = 4;
        iArr[10] = 8;
    }

    ExifTag(short s, short s2, int i, int i2, boolean z) {
        this.tagId = s;
        this.dataType = s2;
        this.componentCountActual = i;
        this.hasDefinedDefaultComponentCount = z;
        this.ifd = i2;
    }

    private boolean checkBadComponentCount(int i) {
        return this.hasDefinedDefaultComponentCount && this.componentCountActual != i;
    }

    private static String convertTypeToString(short s) {
        switch (s) {
            case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE /*1*/:
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

    static boolean isValidIfd(int i) {
        return i == 0 || i == 1 || i == 2 || i == 3 || i == 4;
    }

    static boolean isValidType(short s) {
        return s == 1 || s == 2 || s == 3 || s == 4 || s == 5 || s == 7 || s == 9 || s == 10;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof ExifTag)) {
            return false;
        }
        ExifTag exifTag = (ExifTag) obj;
        if (exifTag.tagId != this.tagId || exifTag.componentCountActual != this.componentCountActual || exifTag.dataType != this.dataType) {
            return false;
        }
        Object obj2 = this.value;
        if (obj2 != null) {
            Object obj3 = exifTag.value;
            if (obj3 == null) {
                return false;
            }
            if (obj2 instanceof long[]) {
                if (!(obj3 instanceof long[])) {
                    return false;
                }
                return Arrays.equals((long[]) obj2, (long[]) obj3);
            } else if (obj2 instanceof Rational[]) {
                if (!(obj3 instanceof Rational[])) {
                    return false;
                }
                return Arrays.equals((Rational[]) obj2, (Rational[]) obj3);
            } else if (!(obj2 instanceof byte[])) {
                return obj2.equals(obj3);
            } else {
                if (!(obj3 instanceof byte[])) {
                    return false;
                }
                return Arrays.equals((byte[]) obj2, (byte[]) obj3);
            }
        } else if (exifTag.value == null) {
            return true;
        } else {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void forceSetComponentCount(int i) {
        this.componentCountActual = i;
    }

    /* access modifiers changed from: package-private */
    public int getComponentCount() {
        return this.componentCountActual;
    }

    /* access modifiers changed from: package-private */
    public int getDataSize() {
        return this.componentCountActual * TYPE_TO_SIZE_MAP[this.dataType];
    }

    /* access modifiers changed from: package-private */
    public short getDataType() {
        return this.dataType;
    }

    /* access modifiers changed from: package-private */
    public int getIfd() {
        return this.ifd;
    }

    /* access modifiers changed from: protected */
    public int getOffset() {
        return this.offset;
    }

    /* access modifiers changed from: package-private */
    public short getTagId() {
        return this.tagId;
    }

    /* access modifiers changed from: package-private */
    public int[] getValueAsInts() {
        Object obj = this.value;
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

    /* access modifiers changed from: package-private */
    public long getValueAt(int i) {
        Object obj = this.value;
        if (obj instanceof long[]) {
            return ((long[]) obj)[i];
        }
        if (obj instanceof byte[]) {
            return (long) ((byte[]) obj)[i];
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Cannot get integer value from ");
        outline13.append(convertTypeToString(this.dataType));
        throw new IllegalArgumentException(outline13.toString());
    }

    /* access modifiers changed from: package-private */
    public boolean hasDefinedCount() {
        return this.hasDefinedDefaultComponentCount;
    }

    /* access modifiers changed from: package-private */
    public boolean hasValue() {
        return this.value != null;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Short.valueOf(this.tagId), Short.valueOf(this.dataType), Boolean.valueOf(this.hasDefinedDefaultComponentCount), Integer.valueOf(this.componentCountActual), Integer.valueOf(this.ifd), this.value, Integer.valueOf(this.offset)});
    }

    /* access modifiers changed from: package-private */
    public void setHasDefinedCount(boolean z) {
        this.hasDefinedDefaultComponentCount = z;
    }

    /* access modifiers changed from: package-private */
    public void setIfd(int i) {
        this.ifd = i;
    }

    /* access modifiers changed from: protected */
    public void setOffset(int i) {
        this.offset = i;
    }

    /* access modifiers changed from: package-private */
    public boolean setValue(int[] iArr) {
        boolean z;
        boolean z2;
        if (checkBadComponentCount(iArr.length)) {
            return false;
        }
        short s = this.dataType;
        if (s != 3 && s != 9 && s != 4) {
            return false;
        }
        if (this.dataType == 3) {
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
        if (this.dataType == 4) {
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
        this.value = jArr;
        this.componentCountActual = iArr.length;
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("tag id: %04X\n", new Object[]{Short.valueOf(this.tagId)}));
        sb.append("ifd id: ");
        sb.append(this.ifd);
        sb.append("\ntype: ");
        sb.append(convertTypeToString(this.dataType));
        sb.append("\ncount: ");
        sb.append(this.componentCountActual);
        sb.append("\noffset: ");
        sb.append(this.offset);
        sb.append("\nvalue: ");
        Object obj = this.value;
        String str = "";
        if (obj != null) {
            if (obj instanceof byte[]) {
                str = this.dataType == 2 ? new String((byte[]) obj, US_ASCII) : Arrays.toString((byte[]) obj);
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
        return GeneratedOutlineSupport.outline12(sb, str, "\n");
    }

    /* access modifiers changed from: package-private */
    public boolean setValue(long[] jArr) {
        boolean z;
        if (checkBadComponentCount(jArr.length) || this.dataType != 4) {
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
        this.value = jArr;
        this.componentCountActual = jArr.length;
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean setValue(String str) {
        short s = this.dataType;
        if (s != 2 && s != 7) {
            return false;
        }
        byte[] bytes = str.getBytes(US_ASCII);
        if (bytes.length > 0) {
            if (!(bytes[bytes.length - 1] == 0 || this.dataType == 7)) {
                bytes = Arrays.copyOf(bytes, bytes.length + 1);
            }
        } else if (this.dataType == 2 && this.componentCountActual == 1) {
            bytes = new byte[]{0};
        }
        int length = bytes.length;
        if (checkBadComponentCount(length)) {
            return false;
        }
        this.componentCountActual = length;
        this.value = bytes;
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean setValue(Rational[] rationalArr) {
        boolean z;
        boolean z2;
        if (checkBadComponentCount(rationalArr.length)) {
            return false;
        }
        short s = this.dataType;
        if (s != 5 && s != 10) {
            return false;
        }
        if (this.dataType == 5) {
            int length = rationalArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    z2 = false;
                    break;
                }
                Rational rational = rationalArr[i];
                if (rational.getNumerator() < 0 || rational.getDenominator() < 0 || rational.getNumerator() > 4294967295L || rational.getDenominator() > 4294967295L) {
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
        if (this.dataType == 10) {
            int length2 = rationalArr.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length2) {
                    z = false;
                    break;
                }
                Rational rational2 = rationalArr[i2];
                if (rational2.getNumerator() < -2147483648L || rational2.getDenominator() < -2147483648L || rational2.getNumerator() > 2147483647L || rational2.getDenominator() > 2147483647L) {
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
        this.value = rationalArr;
        this.componentCountActual = rationalArr.length;
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean setValue(byte[] bArr) {
        int length = bArr.length;
        if (checkBadComponentCount(length)) {
            return false;
        }
        short s = this.dataType;
        if (s != 1 && s != 7) {
            return false;
        }
        this.value = new byte[length];
        System.arraycopy(bArr, 0, this.value, 0, length);
        this.componentCountActual = length;
        return true;
    }
}
