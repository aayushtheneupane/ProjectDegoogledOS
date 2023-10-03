package p000a.p005b;

/* renamed from: a.b.o */
public class C0028o implements Cloneable {
    private static final Object DELETED = new Object();

    /* renamed from: Zn */
    private boolean f13Zn;
    private int[] mKeys;
    private int mSize;
    private Object[] mValues;

    public C0028o() {
        this(10);
    }

    /* renamed from: gc */
    private void m30gc() {
        int i = this.mSize;
        int[] iArr = this.mKeys;
        Object[] objArr = this.mValues;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != DELETED) {
                if (i3 != i2) {
                    iArr[i2] = iArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.f13Zn = false;
        this.mSize = i2;
    }

    public void append(int i, Object obj) {
        int i2 = this.mSize;
        if (i2 == 0 || i > this.mKeys[i2 - 1]) {
            if (this.f13Zn && this.mSize >= this.mKeys.length) {
                m30gc();
            }
            int i3 = this.mSize;
            if (i3 >= this.mKeys.length) {
                int L = C0018e.m24L(i3 + 1);
                int[] iArr = new int[L];
                Object[] objArr = new Object[L];
                int[] iArr2 = this.mKeys;
                System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
                Object[] objArr2 = this.mValues;
                System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
                this.mKeys = iArr;
                this.mValues = objArr;
            }
            this.mKeys[i3] = i;
            this.mValues[i3] = obj;
            this.mSize = i3 + 1;
            return;
        }
        put(i, obj);
    }

    public void clear() {
        int i = this.mSize;
        Object[] objArr = this.mValues;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.mSize = 0;
        this.f13Zn = false;
    }

    public Object get(int i) {
        return get(i, (Object) null);
    }

    public int indexOfKey(int i) {
        if (this.f13Zn) {
            m30gc();
        }
        return C0018e.binarySearch(this.mKeys, this.mSize, i);
    }

    public int keyAt(int i) {
        if (this.f13Zn) {
            m30gc();
        }
        return this.mKeys[i];
    }

    public void put(int i, Object obj) {
        int binarySearch = C0018e.binarySearch(this.mKeys, this.mSize, i);
        if (binarySearch >= 0) {
            this.mValues[binarySearch] = obj;
            return;
        }
        int i2 = ~binarySearch;
        if (i2 < this.mSize) {
            Object[] objArr = this.mValues;
            if (objArr[i2] == DELETED) {
                this.mKeys[i2] = i;
                objArr[i2] = obj;
                return;
            }
        }
        if (this.f13Zn && this.mSize >= this.mKeys.length) {
            m30gc();
            i2 = ~C0018e.binarySearch(this.mKeys, this.mSize, i);
        }
        int i3 = this.mSize;
        if (i3 >= this.mKeys.length) {
            int L = C0018e.m24L(i3 + 1);
            int[] iArr = new int[L];
            Object[] objArr2 = new Object[L];
            int[] iArr2 = this.mKeys;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr3 = this.mValues;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.mKeys = iArr;
            this.mValues = objArr2;
        }
        int i4 = this.mSize;
        if (i4 - i2 != 0) {
            int[] iArr3 = this.mKeys;
            int i5 = i2 + 1;
            System.arraycopy(iArr3, i2, iArr3, i5, i4 - i2);
            Object[] objArr4 = this.mValues;
            System.arraycopy(objArr4, i2, objArr4, i5, this.mSize - i2);
        }
        this.mKeys[i2] = i;
        this.mValues[i2] = obj;
        this.mSize++;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000a, code lost:
        r0 = r3.mValues;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void remove(int r4) {
        /*
            r3 = this;
            int[] r0 = r3.mKeys
            int r1 = r3.mSize
            int r4 = p000a.p005b.C0018e.binarySearch((int[]) r0, (int) r1, (int) r4)
            if (r4 < 0) goto L_0x0017
            java.lang.Object[] r0 = r3.mValues
            r1 = r0[r4]
            java.lang.Object r2 = DELETED
            if (r1 == r2) goto L_0x0017
            r0[r4] = r2
            r4 = 1
            r3.f13Zn = r4
        L_0x0017:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000a.p005b.C0028o.remove(int):void");
    }

    public int size() {
        if (this.f13Zn) {
            m30gc();
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
            sb.append(keyAt(i));
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
        if (this.f13Zn) {
            m30gc();
        }
        return this.mValues[i];
    }

    public C0028o(int i) {
        this.f13Zn = false;
        if (i == 0) {
            this.mKeys = C0018e.f9Wn;
            this.mValues = C0018e.f11Yn;
            return;
        }
        int L = C0018e.m24L(i);
        this.mKeys = new int[L];
        this.mValues = new Object[L];
    }

    public C0028o clone() {
        try {
            C0028o oVar = (C0028o) super.clone();
            oVar.mKeys = (int[]) this.mKeys.clone();
            oVar.mValues = (Object[]) this.mValues.clone();
            return oVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public Object get(int i, Object obj) {
        int binarySearch = C0018e.binarySearch(this.mKeys, this.mSize, i);
        if (binarySearch >= 0) {
            Object[] objArr = this.mValues;
            if (objArr[binarySearch] != DELETED) {
                return objArr[binarySearch];
            }
        }
        return obj;
    }
}
