package p000;

import java.util.Arrays;
import java.util.RandomAccess;

/* renamed from: ikq */
/* compiled from: PG */
public final class ikq extends ihd implements RandomAccess {

    /* renamed from: b */
    public static final ikq f14400b;

    /* renamed from: c */
    private Object[] f14401c;

    /* renamed from: d */
    private int f14402d;

    static {
        ikq ikq = new ikq(new Object[0], 0);
        f14400b = ikq;
        ikq.mo8526b();
    }

    public final int size() {
        return this.f14402d;
    }

    public ikq() {
        this(new Object[10], 0);
    }

    private ikq(Object[] objArr, int i) {
        this.f14401c = objArr;
        this.f14402d = i;
    }

    public final void add(int i, Object obj) {
        int i2;
        mo8527c();
        if (i < 0 || i > (i2 = this.f14402d)) {
            throw new IndexOutOfBoundsException(m13819c(i));
        }
        Object[] objArr = this.f14401c;
        if (i2 < objArr.length) {
            System.arraycopy(objArr, i, objArr, i + 1, i2 - i);
        } else {
            Object[] objArr2 = new Object[(((i2 * 3) / 2) + 1)];
            System.arraycopy(objArr, 0, objArr2, 0, i);
            System.arraycopy(this.f14401c, i, objArr2, i + 1, this.f14402d - i);
            this.f14401c = objArr2;
        }
        this.f14401c[i] = obj;
        this.f14402d++;
        this.modCount++;
    }

    public final boolean add(Object obj) {
        mo8527c();
        int i = this.f14402d;
        Object[] objArr = this.f14401c;
        if (i == objArr.length) {
            this.f14401c = Arrays.copyOf(objArr, ((i * 3) / 2) + 1);
        }
        Object[] objArr2 = this.f14401c;
        int i2 = this.f14402d;
        this.f14402d = i2 + 1;
        objArr2[i2] = obj;
        this.modCount++;
        return true;
    }

    /* renamed from: b */
    private final void m13818b(int i) {
        if (i < 0 || i >= this.f14402d) {
            throw new IndexOutOfBoundsException(m13819c(i));
        }
    }

    public final Object get(int i) {
        m13818b(i);
        return this.f14401c[i];
    }

    /* renamed from: c */
    private final String m13819c(int i) {
        int i2 = this.f14402d;
        StringBuilder sb = new StringBuilder(35);
        sb.append("Index:");
        sb.append(i);
        sb.append(", Size:");
        sb.append(i2);
        return sb.toString();
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ ije mo8585a(int i) {
        if (i >= this.f14402d) {
            return new ikq(Arrays.copyOf(this.f14401c, i), this.f14402d);
        }
        throw new IllegalArgumentException();
    }

    public final Object remove(int i) {
        mo8527c();
        m13818b(i);
        Object[] objArr = this.f14401c;
        Object obj = objArr[i];
        int i2 = this.f14402d;
        if (i < i2 - 1) {
            System.arraycopy(objArr, i + 1, objArr, i, (i2 - i) - 1);
        }
        this.f14402d--;
        this.modCount++;
        return obj;
    }

    public final Object set(int i, Object obj) {
        mo8527c();
        m13818b(i);
        Object[] objArr = this.f14401c;
        Object obj2 = objArr[i];
        objArr[i] = obj;
        this.modCount++;
        return obj2;
    }
}
