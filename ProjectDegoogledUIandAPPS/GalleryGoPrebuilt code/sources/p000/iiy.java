package p000;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* renamed from: iiy */
/* compiled from: PG */
public final class iiy extends ihd implements RandomAccess, ijc, iko {

    /* renamed from: b */
    public static final iiy f14327b;

    /* renamed from: c */
    private int[] f14328c;

    /* renamed from: d */
    private int f14329d;

    static {
        iiy iiy = new iiy(new int[0], 0);
        f14327b = iiy;
        iiy.mo8526b();
    }

    public final int size() {
        return this.f14329d;
    }

    public iiy() {
        this(new int[10], 0);
    }

    private iiy(int[] iArr, int i) {
        this.f14328c = iArr;
        this.f14329d = i;
    }

    public final /* bridge */ /* synthetic */ void add(int i, Object obj) {
        int i2;
        int intValue = ((Integer) obj).intValue();
        mo8527c();
        if (i < 0 || i > (i2 = this.f14329d)) {
            throw new IndexOutOfBoundsException(m13630f(i));
        }
        int[] iArr = this.f14328c;
        if (i2 < iArr.length) {
            System.arraycopy(iArr, i, iArr, i + 1, i2 - i);
        } else {
            int[] iArr2 = new int[(((i2 * 3) / 2) + 1)];
            System.arraycopy(iArr, 0, iArr2, 0, i);
            System.arraycopy(this.f14328c, i, iArr2, i + 1, this.f14329d - i);
            this.f14328c = iArr2;
        }
        this.f14328c[i] = intValue;
        this.f14329d++;
        this.modCount++;
    }

    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        mo8801d(((Integer) obj).intValue());
        return true;
    }

    public final boolean addAll(Collection collection) {
        mo8527c();
        ijf.m13650a((Object) collection);
        if (!(collection instanceof iiy)) {
            return super.addAll(collection);
        }
        iiy iiy = (iiy) collection;
        int i = iiy.f14329d;
        if (i == 0) {
            return false;
        }
        int i2 = this.f14329d;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            int[] iArr = this.f14328c;
            if (i3 > iArr.length) {
                this.f14328c = Arrays.copyOf(iArr, i3);
            }
            System.arraycopy(iiy.f14328c, 0, this.f14328c, this.f14329d, iiy.f14329d);
            this.f14329d = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    /* renamed from: d */
    public final void mo8801d(int i) {
        mo8527c();
        int i2 = this.f14329d;
        int[] iArr = this.f14328c;
        if (i2 == iArr.length) {
            int[] iArr2 = new int[(((i2 * 3) / 2) + 1)];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            this.f14328c = iArr2;
        }
        int[] iArr3 = this.f14328c;
        int i3 = this.f14329d;
        this.f14329d = i3 + 1;
        iArr3[i3] = i;
    }

    /* renamed from: e */
    private final void m13629e(int i) {
        if (i < 0 || i >= this.f14329d) {
            throw new IndexOutOfBoundsException(m13630f(i));
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof iiy)) {
            return super.equals(obj);
        }
        iiy iiy = (iiy) obj;
        if (this.f14329d != iiy.f14329d) {
            return false;
        }
        int[] iArr = iiy.f14328c;
        for (int i = 0; i < this.f14329d; i++) {
            if (this.f14328c[i] != iArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final /* bridge */ /* synthetic */ Object get(int i) {
        return Integer.valueOf(mo8800c(i));
    }

    /* renamed from: c */
    public final int mo8800c(int i) {
        m13629e(i);
        return this.f14328c[i];
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.f14329d; i2++) {
            i = (i * 31) + this.f14328c[i2];
        }
        return i;
    }

    /* renamed from: f */
    private final String m13630f(int i) {
        int i2 = this.f14329d;
        StringBuilder sb = new StringBuilder(35);
        sb.append("Index:");
        sb.append(i);
        sb.append(", Size:");
        sb.append(i2);
        return sb.toString();
    }

    /* renamed from: b */
    public final ijc mo8585a(int i) {
        if (i >= this.f14329d) {
            return new iiy(Arrays.copyOf(this.f14328c, i), this.f14329d);
        }
        throw new IllegalArgumentException();
    }

    public final /* bridge */ /* synthetic */ Object remove(int i) {
        mo8527c();
        m13629e(i);
        int[] iArr = this.f14328c;
        int i2 = iArr[i];
        int i3 = this.f14329d;
        if (i < i3 - 1) {
            System.arraycopy(iArr, i + 1, iArr, i, (i3 - i) - 1);
        }
        this.f14329d--;
        this.modCount++;
        return Integer.valueOf(i2);
    }

    public final boolean remove(Object obj) {
        mo8527c();
        for (int i = 0; i < this.f14329d; i++) {
            if (obj.equals(Integer.valueOf(this.f14328c[i]))) {
                int[] iArr = this.f14328c;
                System.arraycopy(iArr, i + 1, iArr, i, (this.f14329d - i) - 1);
                this.f14329d--;
                this.modCount++;
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        mo8527c();
        if (i2 >= i) {
            int[] iArr = this.f14328c;
            System.arraycopy(iArr, i2, iArr, i, this.f14329d - i2);
            this.f14329d -= i2 - i;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final /* bridge */ /* synthetic */ Object set(int i, Object obj) {
        int intValue = ((Integer) obj).intValue();
        mo8527c();
        m13629e(i);
        int[] iArr = this.f14328c;
        int i2 = iArr[i];
        iArr[i] = intValue;
        return Integer.valueOf(i2);
    }
}
