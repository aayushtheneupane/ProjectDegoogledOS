package p000;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* renamed from: iig */
/* compiled from: PG */
final class iig extends ihd implements RandomAccess, ije, iko {

    /* renamed from: b */
    private double[] f14239b;

    /* renamed from: c */
    private int f14240c;

    static {
        new iig(new double[0], 0).mo8526b();
    }

    public final int size() {
        return this.f14240c;
    }

    public iig() {
        this(new double[10], 0);
    }

    private iig(double[] dArr, int i) {
        this.f14239b = dArr;
        this.f14240c = i;
    }

    public final /* bridge */ /* synthetic */ void add(int i, Object obj) {
        int i2;
        double doubleValue = ((Double) obj).doubleValue();
        mo8527c();
        if (i < 0 || i > (i2 = this.f14240c)) {
            throw new IndexOutOfBoundsException(m13495c(i));
        }
        double[] dArr = this.f14239b;
        if (i2 < dArr.length) {
            System.arraycopy(dArr, i, dArr, i + 1, i2 - i);
        } else {
            double[] dArr2 = new double[(((i2 * 3) / 2) + 1)];
            System.arraycopy(dArr, 0, dArr2, 0, i);
            System.arraycopy(this.f14239b, i, dArr2, i + 1, this.f14240c - i);
            this.f14239b = dArr2;
        }
        this.f14239b[i] = doubleValue;
        this.f14240c++;
        this.modCount++;
    }

    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        mo8706a(((Double) obj).doubleValue());
        return true;
    }

    public final boolean addAll(Collection collection) {
        mo8527c();
        ijf.m13650a((Object) collection);
        if (!(collection instanceof iig)) {
            return super.addAll(collection);
        }
        iig iig = (iig) collection;
        int i = iig.f14240c;
        if (i == 0) {
            return false;
        }
        int i2 = this.f14240c;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            double[] dArr = this.f14239b;
            if (i3 > dArr.length) {
                this.f14239b = Arrays.copyOf(dArr, i3);
            }
            System.arraycopy(iig.f14239b, 0, this.f14239b, this.f14240c, iig.f14240c);
            this.f14240c = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    /* renamed from: a */
    public final void mo8706a(double d) {
        mo8527c();
        int i = this.f14240c;
        double[] dArr = this.f14239b;
        if (i == dArr.length) {
            double[] dArr2 = new double[(((i * 3) / 2) + 1)];
            System.arraycopy(dArr, 0, dArr2, 0, i);
            this.f14239b = dArr2;
        }
        double[] dArr3 = this.f14239b;
        int i2 = this.f14240c;
        this.f14240c = i2 + 1;
        dArr3[i2] = d;
    }

    /* renamed from: b */
    private final void m13494b(int i) {
        if (i < 0 || i >= this.f14240c) {
            throw new IndexOutOfBoundsException(m13495c(i));
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof iig)) {
            return super.equals(obj);
        }
        iig iig = (iig) obj;
        if (this.f14240c != iig.f14240c) {
            return false;
        }
        double[] dArr = iig.f14239b;
        for (int i = 0; i < this.f14240c; i++) {
            if (Double.doubleToLongBits(this.f14239b[i]) != Double.doubleToLongBits(dArr[i])) {
                return false;
            }
        }
        return true;
    }

    public final /* bridge */ /* synthetic */ Object get(int i) {
        m13494b(i);
        return Double.valueOf(this.f14239b[i]);
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.f14240c; i2++) {
            i = (i * 31) + ijf.m13645a(Double.doubleToLongBits(this.f14239b[i2]));
        }
        return i;
    }

    /* renamed from: c */
    private final String m13495c(int i) {
        int i2 = this.f14240c;
        StringBuilder sb = new StringBuilder(35);
        sb.append("Index:");
        sb.append(i);
        sb.append(", Size:");
        sb.append(i2);
        return sb.toString();
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ ije mo8585a(int i) {
        if (i >= this.f14240c) {
            return new iig(Arrays.copyOf(this.f14239b, i), this.f14240c);
        }
        throw new IllegalArgumentException();
    }

    public final /* bridge */ /* synthetic */ Object remove(int i) {
        mo8527c();
        m13494b(i);
        double[] dArr = this.f14239b;
        double d = dArr[i];
        int i2 = this.f14240c;
        if (i < i2 - 1) {
            System.arraycopy(dArr, i + 1, dArr, i, (i2 - i) - 1);
        }
        this.f14240c--;
        this.modCount++;
        return Double.valueOf(d);
    }

    public final boolean remove(Object obj) {
        mo8527c();
        for (int i = 0; i < this.f14240c; i++) {
            if (obj.equals(Double.valueOf(this.f14239b[i]))) {
                double[] dArr = this.f14239b;
                System.arraycopy(dArr, i + 1, dArr, i, (this.f14240c - i) - 1);
                this.f14240c--;
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
            double[] dArr = this.f14239b;
            System.arraycopy(dArr, i2, dArr, i, this.f14240c - i2);
            this.f14240c -= i2 - i;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final /* bridge */ /* synthetic */ Object set(int i, Object obj) {
        double doubleValue = ((Double) obj).doubleValue();
        mo8527c();
        m13494b(i);
        double[] dArr = this.f14239b;
        double d = dArr[i];
        dArr[i] = doubleValue;
        return Double.valueOf(d);
    }
}
