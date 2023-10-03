package p000;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* renamed from: iio */
/* compiled from: PG */
final class iio extends ihd implements RandomAccess, ije, iko {

    /* renamed from: b */
    private float[] f14312b;

    /* renamed from: c */
    private int f14313c;

    static {
        new iio(new float[0], 0).mo8526b();
    }

    public final int size() {
        return this.f14313c;
    }

    public iio() {
        this(new float[10], 0);
    }

    private iio(float[] fArr, int i) {
        this.f14312b = fArr;
        this.f14313c = i;
    }

    public final /* bridge */ /* synthetic */ void add(int i, Object obj) {
        int i2;
        float floatValue = ((Float) obj).floatValue();
        mo8527c();
        if (i < 0 || i > (i2 = this.f14313c)) {
            throw new IndexOutOfBoundsException(m13528c(i));
        }
        float[] fArr = this.f14312b;
        if (i2 < fArr.length) {
            System.arraycopy(fArr, i, fArr, i + 1, i2 - i);
        } else {
            float[] fArr2 = new float[(((i2 * 3) / 2) + 1)];
            System.arraycopy(fArr, 0, fArr2, 0, i);
            System.arraycopy(this.f14312b, i, fArr2, i + 1, this.f14313c - i);
            this.f14312b = fArr2;
        }
        this.f14312b[i] = floatValue;
        this.f14313c++;
        this.modCount++;
    }

    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        mo8736a(((Float) obj).floatValue());
        return true;
    }

    public final boolean addAll(Collection collection) {
        mo8527c();
        ijf.m13650a((Object) collection);
        if (!(collection instanceof iio)) {
            return super.addAll(collection);
        }
        iio iio = (iio) collection;
        int i = iio.f14313c;
        if (i == 0) {
            return false;
        }
        int i2 = this.f14313c;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            float[] fArr = this.f14312b;
            if (i3 > fArr.length) {
                this.f14312b = Arrays.copyOf(fArr, i3);
            }
            System.arraycopy(iio.f14312b, 0, this.f14312b, this.f14313c, iio.f14313c);
            this.f14313c = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    /* renamed from: a */
    public final void mo8736a(float f) {
        mo8527c();
        int i = this.f14313c;
        float[] fArr = this.f14312b;
        if (i == fArr.length) {
            float[] fArr2 = new float[(((i * 3) / 2) + 1)];
            System.arraycopy(fArr, 0, fArr2, 0, i);
            this.f14312b = fArr2;
        }
        float[] fArr3 = this.f14312b;
        int i2 = this.f14313c;
        this.f14313c = i2 + 1;
        fArr3[i2] = f;
    }

    /* renamed from: b */
    private final void m13527b(int i) {
        if (i < 0 || i >= this.f14313c) {
            throw new IndexOutOfBoundsException(m13528c(i));
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof iio)) {
            return super.equals(obj);
        }
        iio iio = (iio) obj;
        if (this.f14313c != iio.f14313c) {
            return false;
        }
        float[] fArr = iio.f14312b;
        for (int i = 0; i < this.f14313c; i++) {
            if (Float.floatToIntBits(this.f14312b[i]) != Float.floatToIntBits(fArr[i])) {
                return false;
            }
        }
        return true;
    }

    public final /* bridge */ /* synthetic */ Object get(int i) {
        m13527b(i);
        return Float.valueOf(this.f14312b[i]);
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.f14313c; i2++) {
            i = (i * 31) + Float.floatToIntBits(this.f14312b[i2]);
        }
        return i;
    }

    /* renamed from: c */
    private final String m13528c(int i) {
        int i2 = this.f14313c;
        StringBuilder sb = new StringBuilder(35);
        sb.append("Index:");
        sb.append(i);
        sb.append(", Size:");
        sb.append(i2);
        return sb.toString();
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ ije mo8585a(int i) {
        if (i >= this.f14313c) {
            return new iio(Arrays.copyOf(this.f14312b, i), this.f14313c);
        }
        throw new IllegalArgumentException();
    }

    public final /* bridge */ /* synthetic */ Object remove(int i) {
        mo8527c();
        m13527b(i);
        float[] fArr = this.f14312b;
        float f = fArr[i];
        int i2 = this.f14313c;
        if (i < i2 - 1) {
            System.arraycopy(fArr, i + 1, fArr, i, (i2 - i) - 1);
        }
        this.f14313c--;
        this.modCount++;
        return Float.valueOf(f);
    }

    public final boolean remove(Object obj) {
        mo8527c();
        for (int i = 0; i < this.f14313c; i++) {
            if (obj.equals(Float.valueOf(this.f14312b[i]))) {
                float[] fArr = this.f14312b;
                System.arraycopy(fArr, i + 1, fArr, i, (this.f14313c - i) - 1);
                this.f14313c--;
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
            float[] fArr = this.f14312b;
            System.arraycopy(fArr, i2, fArr, i, this.f14313c - i2);
            this.f14313c -= i2 - i;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final /* bridge */ /* synthetic */ Object set(int i, Object obj) {
        float floatValue = ((Float) obj).floatValue();
        mo8527c();
        m13527b(i);
        float[] fArr = this.f14312b;
        float f = fArr[i];
        fArr[i] = floatValue;
        return Float.valueOf(f);
    }
}
