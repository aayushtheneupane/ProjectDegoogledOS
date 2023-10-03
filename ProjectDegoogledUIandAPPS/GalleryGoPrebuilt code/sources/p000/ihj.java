package p000;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* renamed from: ihj */
/* compiled from: PG */
final class ihj extends ihd implements RandomAccess, ije, iko {

    /* renamed from: b */
    private boolean[] f14187b;

    /* renamed from: c */
    private int f14188c;

    static {
        new ihj(new boolean[0], 0).mo8526b();
    }

    public final int size() {
        return this.f14188c;
    }

    public ihj() {
        this(new boolean[10], 0);
    }

    private ihj(boolean[] zArr, int i) {
        this.f14187b = zArr;
        this.f14188c = i;
    }

    public final /* bridge */ /* synthetic */ void add(int i, Object obj) {
        int i2;
        boolean booleanValue = ((Boolean) obj).booleanValue();
        mo8527c();
        if (i < 0 || i > (i2 = this.f14188c)) {
            throw new IndexOutOfBoundsException(m13124c(i));
        }
        boolean[] zArr = this.f14187b;
        if (i2 < zArr.length) {
            System.arraycopy(zArr, i, zArr, i + 1, i2 - i);
        } else {
            boolean[] zArr2 = new boolean[(((i2 * 3) / 2) + 1)];
            System.arraycopy(zArr, 0, zArr2, 0, i);
            System.arraycopy(this.f14187b, i, zArr2, i + 1, this.f14188c - i);
            this.f14187b = zArr2;
        }
        this.f14187b[i] = booleanValue;
        this.f14188c++;
        this.modCount++;
    }

    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        mo8586a(((Boolean) obj).booleanValue());
        return true;
    }

    public final boolean addAll(Collection collection) {
        mo8527c();
        ijf.m13650a((Object) collection);
        if (!(collection instanceof ihj)) {
            return super.addAll(collection);
        }
        ihj ihj = (ihj) collection;
        int i = ihj.f14188c;
        if (i == 0) {
            return false;
        }
        int i2 = this.f14188c;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            boolean[] zArr = this.f14187b;
            if (i3 > zArr.length) {
                this.f14187b = Arrays.copyOf(zArr, i3);
            }
            System.arraycopy(ihj.f14187b, 0, this.f14187b, this.f14188c, ihj.f14188c);
            this.f14188c = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    /* renamed from: a */
    public final void mo8586a(boolean z) {
        mo8527c();
        int i = this.f14188c;
        boolean[] zArr = this.f14187b;
        if (i == zArr.length) {
            boolean[] zArr2 = new boolean[(((i * 3) / 2) + 1)];
            System.arraycopy(zArr, 0, zArr2, 0, i);
            this.f14187b = zArr2;
        }
        boolean[] zArr3 = this.f14187b;
        int i2 = this.f14188c;
        this.f14188c = i2 + 1;
        zArr3[i2] = z;
    }

    /* renamed from: b */
    private final void m13123b(int i) {
        if (i < 0 || i >= this.f14188c) {
            throw new IndexOutOfBoundsException(m13124c(i));
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ihj)) {
            return super.equals(obj);
        }
        ihj ihj = (ihj) obj;
        if (this.f14188c != ihj.f14188c) {
            return false;
        }
        boolean[] zArr = ihj.f14187b;
        for (int i = 0; i < this.f14188c; i++) {
            if (this.f14187b[i] != zArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final /* bridge */ /* synthetic */ Object get(int i) {
        m13123b(i);
        return Boolean.valueOf(this.f14187b[i]);
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.f14188c; i2++) {
            i = (i * 31) + ijf.m13646a(this.f14187b[i2]);
        }
        return i;
    }

    /* renamed from: c */
    private final String m13124c(int i) {
        int i2 = this.f14188c;
        StringBuilder sb = new StringBuilder(35);
        sb.append("Index:");
        sb.append(i);
        sb.append(", Size:");
        sb.append(i2);
        return sb.toString();
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ ije mo8585a(int i) {
        if (i >= this.f14188c) {
            return new ihj(Arrays.copyOf(this.f14187b, i), this.f14188c);
        }
        throw new IllegalArgumentException();
    }

    public final /* bridge */ /* synthetic */ Object remove(int i) {
        mo8527c();
        m13123b(i);
        boolean[] zArr = this.f14187b;
        boolean z = zArr[i];
        int i2 = this.f14188c;
        if (i < i2 - 1) {
            System.arraycopy(zArr, i + 1, zArr, i, (i2 - i) - 1);
        }
        this.f14188c--;
        this.modCount++;
        return Boolean.valueOf(z);
    }

    public final boolean remove(Object obj) {
        mo8527c();
        for (int i = 0; i < this.f14188c; i++) {
            if (obj.equals(Boolean.valueOf(this.f14187b[i]))) {
                boolean[] zArr = this.f14187b;
                System.arraycopy(zArr, i + 1, zArr, i, (this.f14188c - i) - 1);
                this.f14188c--;
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
            boolean[] zArr = this.f14187b;
            System.arraycopy(zArr, i2, zArr, i, this.f14188c - i2);
            this.f14188c -= i2 - i;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final /* bridge */ /* synthetic */ Object set(int i, Object obj) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        mo8527c();
        m13123b(i);
        boolean[] zArr = this.f14187b;
        boolean z = zArr[i];
        zArr[i] = booleanValue;
        return Boolean.valueOf(z);
    }
}
