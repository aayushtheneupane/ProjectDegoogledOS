package p000;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* renamed from: ijs */
/* compiled from: PG */
public final class ijs extends ihd implements RandomAccess, ijd, iko {

    /* renamed from: b */
    public static final ijs f14357b;

    /* renamed from: c */
    private long[] f14358c;

    /* renamed from: d */
    private int f14359d;

    static {
        ijs ijs = new ijs(new long[0], 0);
        f14357b = ijs;
        ijs.mo8526b();
    }

    public final int size() {
        return this.f14359d;
    }

    public ijs() {
        this(new long[10], 0);
    }

    private ijs(long[] jArr, int i) {
        this.f14358c = jArr;
        this.f14359d = i;
    }

    public final /* bridge */ /* synthetic */ void add(int i, Object obj) {
        int i2;
        long longValue = ((Long) obj).longValue();
        mo8527c();
        if (i < 0 || i > (i2 = this.f14359d)) {
            throw new IndexOutOfBoundsException(m13689e(i));
        }
        long[] jArr = this.f14358c;
        if (i2 < jArr.length) {
            System.arraycopy(jArr, i, jArr, i + 1, i2 - i);
        } else {
            long[] jArr2 = new long[(((i2 * 3) / 2) + 1)];
            System.arraycopy(jArr, 0, jArr2, 0, i);
            System.arraycopy(this.f14358c, i, jArr2, i + 1, this.f14359d - i);
            this.f14358c = jArr2;
        }
        this.f14358c[i] = longValue;
        this.f14359d++;
        this.modCount++;
    }

    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        mo8805a(((Long) obj).longValue());
        return true;
    }

    public final boolean addAll(Collection collection) {
        mo8527c();
        ijf.m13650a((Object) collection);
        if (!(collection instanceof ijs)) {
            return super.addAll(collection);
        }
        ijs ijs = (ijs) collection;
        int i = ijs.f14359d;
        if (i == 0) {
            return false;
        }
        int i2 = this.f14359d;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            long[] jArr = this.f14358c;
            if (i3 > jArr.length) {
                this.f14358c = Arrays.copyOf(jArr, i3);
            }
            System.arraycopy(ijs.f14358c, 0, this.f14358c, this.f14359d, ijs.f14359d);
            this.f14359d = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    /* renamed from: a */
    public final void mo8805a(long j) {
        mo8527c();
        int i = this.f14359d;
        long[] jArr = this.f14358c;
        if (i == jArr.length) {
            long[] jArr2 = new long[(((i * 3) / 2) + 1)];
            System.arraycopy(jArr, 0, jArr2, 0, i);
            this.f14358c = jArr2;
        }
        long[] jArr3 = this.f14358c;
        int i2 = this.f14359d;
        this.f14359d = i2 + 1;
        jArr3[i2] = j;
    }

    /* renamed from: d */
    private final void m13688d(int i) {
        if (i < 0 || i >= this.f14359d) {
            throw new IndexOutOfBoundsException(m13689e(i));
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ijs)) {
            return super.equals(obj);
        }
        ijs ijs = (ijs) obj;
        if (this.f14359d != ijs.f14359d) {
            return false;
        }
        long[] jArr = ijs.f14358c;
        for (int i = 0; i < this.f14359d; i++) {
            if (this.f14358c[i] != jArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final /* bridge */ /* synthetic */ Object get(int i) {
        return Long.valueOf(mo8827c(i));
    }

    /* renamed from: c */
    public final long mo8827c(int i) {
        m13688d(i);
        return this.f14358c[i];
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.f14359d; i2++) {
            i = (i * 31) + ijf.m13645a(this.f14358c[i2]);
        }
        return i;
    }

    /* renamed from: e */
    private final String m13689e(int i) {
        int i2 = this.f14359d;
        StringBuilder sb = new StringBuilder(35);
        sb.append("Index:");
        sb.append(i);
        sb.append(", Size:");
        sb.append(i2);
        return sb.toString();
    }

    /* renamed from: b */
    public final ijd mo8585a(int i) {
        if (i >= this.f14359d) {
            return new ijs(Arrays.copyOf(this.f14358c, i), this.f14359d);
        }
        throw new IllegalArgumentException();
    }

    public final /* bridge */ /* synthetic */ Object remove(int i) {
        mo8527c();
        m13688d(i);
        long[] jArr = this.f14358c;
        long j = jArr[i];
        int i2 = this.f14359d;
        if (i < i2 - 1) {
            System.arraycopy(jArr, i + 1, jArr, i, (i2 - i) - 1);
        }
        this.f14359d--;
        this.modCount++;
        return Long.valueOf(j);
    }

    public final boolean remove(Object obj) {
        mo8527c();
        for (int i = 0; i < this.f14359d; i++) {
            if (obj.equals(Long.valueOf(this.f14358c[i]))) {
                long[] jArr = this.f14358c;
                System.arraycopy(jArr, i + 1, jArr, i, (this.f14359d - i) - 1);
                this.f14359d--;
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
            long[] jArr = this.f14358c;
            System.arraycopy(jArr, i2, jArr, i, this.f14359d - i2);
            this.f14359d -= i2 - i;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final /* bridge */ /* synthetic */ Object set(int i, Object obj) {
        long longValue = ((Long) obj).longValue();
        mo8527c();
        m13688d(i);
        long[] jArr = this.f14358c;
        long j = jArr[i];
        jArr[i] = longValue;
        return Long.valueOf(j);
    }
}
