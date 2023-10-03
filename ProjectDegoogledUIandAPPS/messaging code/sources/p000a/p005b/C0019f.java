package p000a.p005b;

/* renamed from: a.b.f */
public class C0019f implements Cloneable {
    private static final Object DELETED = new Object();

    /* renamed from: Zn */
    private boolean f12Zn = false;
    private long[] mKeys;
    private int mSize;
    private Object[] mValues;

    public C0019f() {
        int M = C0018e.m25M(10);
        this.mKeys = new long[M];
        this.mValues = new Object[M];
    }

    /* renamed from: gc */
    private void m27gc() {
        int i = this.mSize;
        long[] jArr = this.mKeys;
        Object[] objArr = this.mValues;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != DELETED) {
                if (i3 != i2) {
                    jArr[i2] = jArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.f12Zn = false;
        this.mSize = i2;
    }

    public void append(long j, Object obj) {
        int i = this.mSize;
        if (i == 0 || j > this.mKeys[i - 1]) {
            if (this.f12Zn && this.mSize >= this.mKeys.length) {
                m27gc();
            }
            int i2 = this.mSize;
            if (i2 >= this.mKeys.length) {
                int M = C0018e.m25M(i2 + 1);
                long[] jArr = new long[M];
                Object[] objArr = new Object[M];
                long[] jArr2 = this.mKeys;
                System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
                Object[] objArr2 = this.mValues;
                System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
                this.mKeys = jArr;
                this.mValues = objArr;
            }
            this.mKeys[i2] = j;
            this.mValues[i2] = obj;
            this.mSize = i2 + 1;
            return;
        }
        put(j, obj);
    }

    public void clear() {
        int i = this.mSize;
        Object[] objArr = this.mValues;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.mSize = 0;
        this.f12Zn = false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000a, code lost:
        r4 = r2.mValues;
     */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void delete(long r3) {
        /*
            r2 = this;
            long[] r0 = r2.mKeys
            int r1 = r2.mSize
            int r3 = p000a.p005b.C0018e.binarySearch((long[]) r0, (int) r1, (long) r3)
            if (r3 < 0) goto L_0x0017
            java.lang.Object[] r4 = r2.mValues
            r0 = r4[r3]
            java.lang.Object r1 = DELETED
            if (r0 == r1) goto L_0x0017
            r4[r3] = r1
            r3 = 1
            r2.f12Zn = r3
        L_0x0017:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000a.p005b.C0019f.delete(long):void");
    }

    public Object get(long j) {
        return get(j, (Object) null);
    }

    public void put(long j, Object obj) {
        int binarySearch = C0018e.binarySearch(this.mKeys, this.mSize, j);
        if (binarySearch >= 0) {
            this.mValues[binarySearch] = obj;
            return;
        }
        int i = ~binarySearch;
        if (i < this.mSize) {
            Object[] objArr = this.mValues;
            if (objArr[i] == DELETED) {
                this.mKeys[i] = j;
                objArr[i] = obj;
                return;
            }
        }
        if (this.f12Zn && this.mSize >= this.mKeys.length) {
            m27gc();
            i = ~C0018e.binarySearch(this.mKeys, this.mSize, j);
        }
        int i2 = this.mSize;
        if (i2 >= this.mKeys.length) {
            int M = C0018e.m25M(i2 + 1);
            long[] jArr = new long[M];
            Object[] objArr2 = new Object[M];
            long[] jArr2 = this.mKeys;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr3 = this.mValues;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.mKeys = jArr;
            this.mValues = objArr2;
        }
        int i3 = this.mSize;
        if (i3 - i != 0) {
            long[] jArr3 = this.mKeys;
            int i4 = i + 1;
            System.arraycopy(jArr3, i, jArr3, i4, i3 - i);
            Object[] objArr4 = this.mValues;
            System.arraycopy(objArr4, i, objArr4, i4, this.mSize - i);
        }
        this.mKeys[i] = j;
        this.mValues[i] = obj;
        this.mSize++;
    }

    public void removeAt(int i) {
        Object[] objArr = this.mValues;
        Object obj = objArr[i];
        Object obj2 = DELETED;
        if (obj != obj2) {
            objArr[i] = obj2;
            this.f12Zn = true;
        }
    }

    public int size() {
        if (this.f12Zn) {
            m27gc();
        }
        return this.mSize;
    }

    public String toString() {
        if (size() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.mSize * 28);
        sb.append('{');
        for (int i = 0; i < this.mSize; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            if (this.f12Zn) {
                m27gc();
            }
            sb.append(this.mKeys[i]);
            sb.append('=');
            Object valueAt = valueAt(i);
            if (valueAt != this) {
                sb.append(valueAt);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public Object valueAt(int i) {
        if (this.f12Zn) {
            m27gc();
        }
        return this.mValues[i];
    }

    public C0019f clone() {
        try {
            C0019f fVar = (C0019f) super.clone();
            fVar.mKeys = (long[]) this.mKeys.clone();
            fVar.mValues = (Object[]) this.mValues.clone();
            return fVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public Object get(long j, Object obj) {
        int binarySearch = C0018e.binarySearch(this.mKeys, this.mSize, j);
        if (binarySearch >= 0) {
            Object[] objArr = this.mValues;
            if (objArr[binarySearch] != DELETED) {
                return objArr[binarySearch];
            }
        }
        return obj;
    }
}
