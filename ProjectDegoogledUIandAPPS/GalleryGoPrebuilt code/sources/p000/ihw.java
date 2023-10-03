package p000;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

/* renamed from: ihw */
/* compiled from: PG */
public abstract class ihw implements Iterable, Serializable {

    /* renamed from: a */
    private static final ihp f14202a = (ihe.m13011a() ? new ihv((byte[]) null) : new ihn((byte[]) null));

    /* renamed from: b */
    public static final ihw f14203b = new iht(ijf.f14337b);

    /* renamed from: c */
    public int f14204c = 0;

    /* renamed from: a */
    public abstract byte mo8596a(int i);

    /* renamed from: a */
    public abstract int mo8597a();

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract int mo8606a(int i, int i2, int i3);

    /* renamed from: a */
    public abstract ihw mo8607a(int i, int i2);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract String mo8608a(Charset charset);

    /* renamed from: a */
    public abstract void mo8609a(ihk ihk);

    /* renamed from: a */
    public abstract void mo8610a(OutputStream outputStream);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo8598a(byte[] bArr, int i, int i2, int i3);

    /* renamed from: b */
    public abstract byte mo8599b(int i);

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract int mo8611b(int i, int i2, int i3);

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public abstract int mo8604c();

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public abstract boolean mo8605d();

    /* renamed from: e */
    public abstract boolean mo8612e();

    public abstract boolean equals(Object obj);

    /* renamed from: f */
    public abstract InputStream mo8614f();

    /* renamed from: g */
    public abstract ihz mo8615g();

    /* renamed from: a */
    private static ihw m13161a(Iterator it, int i) {
        if (i <= 0) {
            throw new IllegalArgumentException(String.format("length (%s) must be >= 1", new Object[]{Integer.valueOf(i)}));
        } else if (i == 1) {
            return (ihw) it.next();
        } else {
            int i2 = i >>> 1;
            ihw a = m13161a(it, i2);
            ihw a2 = m13161a(it, i - i2);
            if (Integer.MAX_VALUE - a.mo8597a() >= a2.mo8597a()) {
                return ikx.m13877a(a, a2);
            }
            int a3 = a.mo8597a();
            int a4 = a2.mo8597a();
            StringBuilder sb = new StringBuilder(53);
            sb.append("ByteString would be too long: ");
            sb.append(a3);
            sb.append("+");
            sb.append(a4);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    /* renamed from: b */
    static void m13166b(int i, int i2) {
        if (((i2 - (i + 1)) | i) >= 0) {
            return;
        }
        if (i >= 0) {
            StringBuilder sb = new StringBuilder(40);
            sb.append("Index > length: ");
            sb.append(i);
            sb.append(", ");
            sb.append(i2);
            throw new ArrayIndexOutOfBoundsException(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder(22);
        sb2.append("Index < 0: ");
        sb2.append(i);
        throw new ArrayIndexOutOfBoundsException(sb2.toString());
    }

    /* renamed from: c */
    static int m13167c(int i, int i2, int i3) {
        int i4 = i2 - i;
        if ((i | i2 | i4 | (i3 - i2)) >= 0) {
            return i4;
        }
        if (i < 0) {
            StringBuilder sb = new StringBuilder(32);
            sb.append("Beginning index: ");
            sb.append(i);
            sb.append(" < 0");
            throw new IndexOutOfBoundsException(sb.toString());
        } else if (i2 < i) {
            StringBuilder sb2 = new StringBuilder(66);
            sb2.append("Beginning index larger than ending index: ");
            sb2.append(i);
            sb2.append(", ");
            sb2.append(i2);
            throw new IndexOutOfBoundsException(sb2.toString());
        } else {
            StringBuilder sb3 = new StringBuilder(37);
            sb3.append("End index: ");
            sb3.append(i2);
            sb3.append(" >= ");
            sb3.append(i3);
            throw new IndexOutOfBoundsException(sb3.toString());
        }
    }

    /* renamed from: a */
    public static ihw m13162a(byte[] bArr) {
        return m13163a(bArr, 0, bArr.length);
    }

    /* renamed from: a */
    public static ihw m13163a(byte[] bArr, int i, int i2) {
        m13167c(i, i + i2, bArr.length);
        return new iht(f14202a.mo8595a(bArr, i, i2));
    }

    /* renamed from: a */
    public static ihw m13160a(String str) {
        return new iht(str.getBytes(ijf.f14336a));
    }

    @Deprecated
    /* renamed from: b */
    public final void mo8620b(byte[] bArr, int i, int i2, int i3) {
        m13167c(i, i + i3, mo8597a());
        m13167c(i2, i2 + i3, bArr.length);
        if (i3 > 0) {
            mo8598a(bArr, i, i2, i3);
        }
    }

    public final int hashCode() {
        int i = this.f14204c;
        if (i == 0) {
            int a = mo8597a();
            i = mo8611b(a, 0, a);
            if (i == 0) {
                i = 1;
            }
            this.f14204c = i;
        }
        return i;
    }

    /* renamed from: i */
    public final boolean mo8623i() {
        return mo8597a() == 0;
    }

    /* renamed from: h */
    public ihq iterator() {
        return new ihl(this);
    }

    /* renamed from: c */
    static ihr m13168c(int i) {
        return new ihr(i);
    }

    /* renamed from: l */
    public static ihu m13169l() {
        return new ihu();
    }

    /* renamed from: a */
    public static ihw m13159a(InputStream inputStream) {
        ihw ihw;
        ArrayList arrayList = new ArrayList();
        int i = 256;
        while (true) {
            byte[] bArr = new byte[i];
            int i2 = 0;
            while (i2 < i) {
                int read = inputStream.read(bArr, i2, i - i2);
                if (read == -1) {
                    break;
                }
                i2 += read;
            }
            if (i2 != 0) {
                ihw = m13163a(bArr, 0, i2);
            } else {
                ihw = null;
            }
            if (ihw == null) {
                break;
            }
            arrayList.add(ihw);
            i = Math.min(i + i, 8192);
        }
        int size = arrayList.size();
        return size != 0 ? m13161a(arrayList.iterator(), size) : f14203b;
    }

    /* renamed from: j */
    public final byte[] mo8625j() {
        int a = mo8597a();
        if (a == 0) {
            return ijf.f14337b;
        }
        byte[] bArr = new byte[a];
        mo8598a(bArr, 0, 0, a);
        return bArr;
    }

    public final String toString() {
        Locale locale = Locale.ROOT;
        Object[] objArr = new Object[3];
        objArr[0] = Integer.toHexString(System.identityHashCode(this));
        objArr[1] = Integer.valueOf(mo8597a());
        objArr[2] = mo8597a() > 50 ? String.valueOf(imu.m14136a(mo8607a(0, 47))).concat("...") : imu.m14136a(this);
        return String.format(locale, "<ByteString@%s size=%d contents=\"%s\">", objArr);
    }

    /* renamed from: k */
    public final String mo8626k() {
        return mo8597a() == 0 ? "" : mo8608a(ijf.f14336a);
    }

    /* renamed from: b */
    static ihw m13164b(byte[] bArr) {
        return new iht(bArr);
    }

    /* renamed from: b */
    static ihw m13165b(byte[] bArr, int i, int i2) {
        return new iho(bArr, i, i2);
    }
}
