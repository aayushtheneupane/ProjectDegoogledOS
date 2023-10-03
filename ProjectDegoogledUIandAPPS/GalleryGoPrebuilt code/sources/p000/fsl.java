package p000;

import android.support.p002v7.widget.RecyclerView;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;

/* renamed from: fsl */
/* compiled from: PG */
public final class fsl {

    /* renamed from: h */
    private static final Charset f10522h = Charset.forName("US-ASCII");

    /* renamed from: i */
    private static final int[] f10523i;

    /* renamed from: a */
    public final short f10524a;

    /* renamed from: b */
    public final short f10525b;

    /* renamed from: c */
    public boolean f10526c;

    /* renamed from: d */
    public int f10527d;

    /* renamed from: e */
    public int f10528e;

    /* renamed from: f */
    public Object f10529f = null;

    /* renamed from: g */
    public int f10530g;

    static {
        int[] iArr = new int[11];
        f10523i = iArr;
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

    /* renamed from: a */
    public static boolean m9546a(int i) {
        return i == 0 || i == 1 || i == 2 || i == 3 || i == 4;
    }

    /* renamed from: a */
    public static boolean m9547a(short s) {
        return s == 1 || s == 2 || s == 3 || s == 4 || s == 5 || s == 7 || s == 9 || s == 10;
    }

    /* renamed from: b */
    public static String m9548b(short s) {
        switch (s) {
            case 1:
                return "UNSIGNED_BYTE";
            case RecyclerView.SCROLL_STATE_SETTLING:
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

    /* renamed from: d */
    private final boolean m9549d(int i) {
        return this.f10526c && this.f10527d != i;
    }

    /* renamed from: b */
    public final boolean mo6140b() {
        return this.f10529f != null;
    }

    public fsl(short s, short s2, int i, int i2, boolean z) {
        this.f10524a = s;
        this.f10525b = s2;
        this.f10527d = i;
        this.f10526c = z;
        this.f10528e = i2;
    }

    public final boolean equals(Object obj) {
        if (obj != null && (obj instanceof fsl)) {
            fsl fsl = (fsl) obj;
            if (fsl.f10524a == this.f10524a && fsl.f10527d == this.f10527d && fsl.f10525b == this.f10525b) {
                Object obj2 = this.f10529f;
                if (obj2 != null) {
                    Object obj3 = fsl.f10529f;
                    if (obj3 == null) {
                        return false;
                    }
                    if (obj2 instanceof long[]) {
                        if (obj3 instanceof long[]) {
                            return Arrays.equals((long[]) obj2, (long[]) obj3);
                        }
                        return false;
                    } else if (obj2 instanceof fsp[]) {
                        if (obj3 instanceof fsp[]) {
                            return Arrays.equals((fsp[]) obj2, (fsp[]) obj3);
                        }
                        return false;
                    } else if (!(obj2 instanceof byte[])) {
                        return obj2.equals(obj3);
                    } else {
                        if (obj3 instanceof byte[]) {
                            return Arrays.equals((byte[]) obj2, (byte[]) obj3);
                        }
                        return false;
                    }
                } else if (fsl.f10529f == null) {
                    return true;
                }
            }
        }
        return false;
    }

    /* renamed from: a */
    public final long mo6134a() {
        return ((long) this.f10527d) * ((long) f10523i[this.f10525b]);
    }

    /* renamed from: c */
    public final String mo6143c() {
        Object obj = this.f10529f;
        if (obj == null) {
            return null;
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        if (!(obj instanceof byte[])) {
            return null;
        }
        byte[] bArr = (byte[]) obj;
        int length = bArr.length;
        if (length > 0) {
            int i = length - 1;
            if (bArr[i] == 0) {
                return new String(bArr, 0, i, f10522h);
            }
        }
        return new String(bArr, f10522h);
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public final long mo6142c(int i) {
        Object obj = this.f10529f;
        if (obj instanceof long[]) {
            return ((long[]) obj)[i];
        }
        if (obj instanceof byte[]) {
            return (long) ((byte[]) obj)[i];
        }
        String b = m9548b(this.f10525b);
        throw new IllegalArgumentException(b.length() == 0 ? new String("Cannot get integer value from ") : "Cannot get integer value from ".concat(b));
    }

    public final int hashCode() {
        int i = (((((this.f10524a + 527) * 31) + this.f10527d) * 31) + this.f10525b) * 31;
        Object obj = this.f10529f;
        return i + (obj != null ? obj.hashCode() : 0);
    }

    /* renamed from: b */
    public final boolean mo6141b(int i) {
        return mo6137a(new int[]{i});
    }

    /* renamed from: a */
    public final boolean mo6135a(String str) {
        short s = this.f10525b;
        if (s != 2 && s != 7) {
            return false;
        }
        byte[] bytes = str.getBytes(f10522h);
        int length = bytes.length;
        if (length <= 0) {
            if (this.f10525b == 2 && this.f10527d == 1) {
                bytes = new byte[]{0};
            }
        } else if (this.f10525b == 2) {
            int i = length - 1;
            if (bytes[i] != 0) {
                if (this.f10526c && length == this.f10527d) {
                    bytes[i] = 0;
                } else {
                    bytes = Arrays.copyOf(bytes, length + 1);
                }
            }
        }
        int length2 = bytes.length;
        if (m9549d(length2)) {
            return false;
        }
        this.f10527d = length2;
        this.f10529f = bytes;
        return true;
    }

    /* renamed from: a */
    public final boolean mo6136a(byte[] bArr) {
        short s;
        int length = bArr.length;
        if (m9549d(length) || ((s = this.f10525b) != 1 && s != 7)) {
            return false;
        }
        byte[] bArr2 = new byte[length];
        this.f10529f = bArr2;
        System.arraycopy(bArr, 0, bArr2, 0, length);
        this.f10527d = length;
        return true;
    }

    /* renamed from: a */
    public final boolean mo6137a(int[] iArr) {
        int i = 0;
        if (m9549d(r0)) {
            return false;
        }
        short s = this.f10525b;
        if (s == 3) {
            for (int i2 : iArr) {
                if (i2 > 65535 || i2 < 0) {
                    return false;
                }
            }
        } else if (!(s == 9 || s == 4)) {
            return false;
        }
        if (this.f10525b == 4) {
            for (int i3 : iArr) {
                if (i3 < 0) {
                    return false;
                }
            }
        }
        long[] jArr = new long[iArr.length];
        while (true) {
            int length = iArr.length;
            if (i < length) {
                jArr[i] = (long) iArr[i];
                i++;
            } else {
                this.f10529f = jArr;
                this.f10527d = length;
                return true;
            }
        }
    }

    /* renamed from: a */
    public final boolean mo6138a(long[] jArr) {
        if (m9549d(r0) || this.f10525b != 4) {
            return false;
        }
        for (long j : jArr) {
            if (j < 0 || j > 4294967295L) {
                return false;
            }
        }
        this.f10529f = jArr;
        this.f10527d = jArr.length;
        return true;
    }

    /* renamed from: a */
    public final boolean mo6139a(fsp[] fspArr) {
        if (m9549d(r0)) {
            return false;
        }
        short s = this.f10525b;
        if (s == 5) {
            for (fsp fsp : fspArr) {
                if (fsp.f10538a < 0 || fsp.f10539b < 0) {
                    return false;
                }
            }
        } else if (s != 10) {
            return false;
        }
        if (this.f10525b == 10) {
            for (fsp fsp2 : fspArr) {
                if (fsp2.f10538a > 2147483647L || fsp2.f10539b > 2147483647L) {
                    return false;
                }
            }
        }
        this.f10529f = fspArr;
        this.f10527d = fspArr.length;
        return true;
    }

    public final String toString() {
        String format = String.format("tag id: %04X\n", new Object[]{Short.valueOf(this.f10524a)});
        int i = this.f10528e;
        String b = m9548b(this.f10525b);
        int i2 = this.f10527d;
        int i3 = this.f10530g;
        Object obj = this.f10529f;
        String str = "";
        if (obj != null) {
            if (obj instanceof byte[]) {
                str = this.f10525b != 2 ? Arrays.toString((byte[]) obj) : mo6143c();
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
        StringBuilder sb = new StringBuilder(String.valueOf(format).length() + 74 + b.length() + String.valueOf(str).length());
        sb.append(format);
        sb.append("ifd id: ");
        sb.append(i);
        sb.append("\ntype: ");
        sb.append(b);
        sb.append("\ncount: ");
        sb.append(i2);
        sb.append("\noffset: ");
        sb.append(i3);
        sb.append("\nvalue: ");
        sb.append(str);
        sb.append("\n");
        return sb.toString();
    }
}
