package p000a.p005b;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/* renamed from: a.b.d */
public final class C0017d implements Collection, Set {
    private static final int[] INT = new int[0];
    private static final Object[] OBJECT = new Object[0];
    private static Object[] sBaseCache;
    private static int sBaseCacheSize;
    private static Object[] sTwiceBaseCache;
    private static int sTwiceBaseCacheSize;
    Object[] mArray = OBJECT;
    private C0026m mCollections;
    private int[] mHashes = INT;
    int mSize = 0;

    /* renamed from: a */
    private static void m21a(int[] iArr, Object[] objArr, int i) {
        if (iArr.length == 8) {
            synchronized (C0017d.class) {
                if (sTwiceBaseCacheSize < 10) {
                    objArr[0] = sTwiceBaseCache;
                    objArr[1] = iArr;
                    for (int i2 = i - 1; i2 >= 2; i2--) {
                        objArr[i2] = null;
                    }
                    sTwiceBaseCache = objArr;
                    sTwiceBaseCacheSize++;
                }
            }
        } else if (iArr.length == 4) {
            synchronized (C0017d.class) {
                if (sBaseCacheSize < 10) {
                    objArr[0] = sBaseCache;
                    objArr[1] = iArr;
                    for (int i3 = i - 1; i3 >= 2; i3--) {
                        objArr[i3] = null;
                    }
                    sBaseCache = objArr;
                    sBaseCacheSize++;
                }
            }
        }
    }

    private int indexOf(Object obj, int i) {
        int i2 = this.mSize;
        if (i2 == 0) {
            return -1;
        }
        int binarySearch = C0018e.binarySearch(this.mHashes, i2, i);
        if (binarySearch < 0 || obj.equals(this.mArray[binarySearch])) {
            return binarySearch;
        }
        int i3 = binarySearch + 1;
        while (i3 < i2 && this.mHashes[i3] == i) {
            if (obj.equals(this.mArray[i3])) {
                return i3;
            }
            i3++;
        }
        int i4 = binarySearch - 1;
        while (i4 >= 0 && this.mHashes[i4] == i) {
            if (obj.equals(this.mArray[i4])) {
                return i4;
            }
            i4--;
        }
        return ~i3;
    }

    private int indexOfNull() {
        int i = this.mSize;
        if (i == 0) {
            return -1;
        }
        int binarySearch = C0018e.binarySearch(this.mHashes, i, 0);
        if (binarySearch < 0 || this.mArray[binarySearch] == null) {
            return binarySearch;
        }
        int i2 = binarySearch + 1;
        while (i2 < i && this.mHashes[i2] == 0) {
            if (this.mArray[i2] == null) {
                return i2;
            }
            i2++;
        }
        int i3 = binarySearch - 1;
        while (i3 >= 0 && this.mHashes[i3] == 0) {
            if (this.mArray[i3] == null) {
                return i3;
            }
            i3--;
        }
        return ~i2;
    }

    /* renamed from: ob */
    private void m22ob(int i) {
        if (i == 8) {
            synchronized (C0017d.class) {
                if (sTwiceBaseCache != null) {
                    Object[] objArr = sTwiceBaseCache;
                    this.mArray = objArr;
                    sTwiceBaseCache = (Object[]) objArr[0];
                    this.mHashes = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    sTwiceBaseCacheSize--;
                    return;
                }
            }
        } else if (i == 4) {
            synchronized (C0017d.class) {
                if (sBaseCache != null) {
                    Object[] objArr2 = sBaseCache;
                    this.mArray = objArr2;
                    sBaseCache = (Object[]) objArr2[0];
                    this.mHashes = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    sBaseCacheSize--;
                    return;
                }
            }
        }
        this.mHashes = new int[i];
        this.mArray = new Object[i];
    }

    public boolean add(Object obj) {
        int i;
        int i2;
        if (obj == null) {
            i2 = indexOfNull();
            i = 0;
        } else {
            int hashCode = obj.hashCode();
            i = hashCode;
            i2 = indexOf(obj, hashCode);
        }
        if (i2 >= 0) {
            return false;
        }
        int i3 = ~i2;
        int i4 = this.mSize;
        if (i4 >= this.mHashes.length) {
            int i5 = 4;
            if (i4 >= 8) {
                i5 = (i4 >> 1) + i4;
            } else if (i4 >= 4) {
                i5 = 8;
            }
            int[] iArr = this.mHashes;
            Object[] objArr = this.mArray;
            m22ob(i5);
            int[] iArr2 = this.mHashes;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr, 0, this.mArray, 0, objArr.length);
            }
            m21a(iArr, objArr, this.mSize);
        }
        int i6 = this.mSize;
        if (i3 < i6) {
            int[] iArr3 = this.mHashes;
            int i7 = i3 + 1;
            System.arraycopy(iArr3, i3, iArr3, i7, i6 - i3);
            Object[] objArr2 = this.mArray;
            System.arraycopy(objArr2, i3, objArr2, i7, this.mSize - i3);
        }
        this.mHashes[i3] = i;
        this.mArray[i3] = obj;
        this.mSize++;
        return true;
    }

    public boolean addAll(Collection collection) {
        int size = collection.size() + this.mSize;
        int[] iArr = this.mHashes;
        boolean z = false;
        if (iArr.length < size) {
            Object[] objArr = this.mArray;
            m22ob(size);
            int i = this.mSize;
            if (i > 0) {
                System.arraycopy(iArr, 0, this.mHashes, 0, i);
                System.arraycopy(objArr, 0, this.mArray, 0, this.mSize);
            }
            m21a(iArr, objArr, this.mSize);
        }
        for (Object add : collection) {
            z |= add(add);
        }
        return z;
    }

    public void clear() {
        int i = this.mSize;
        if (i != 0) {
            m21a(this.mHashes, this.mArray, i);
            this.mHashes = INT;
            this.mArray = OBJECT;
            this.mSize = 0;
        }
    }

    public boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    public boolean containsAll(Collection collection) {
        boolean z;
        Iterator it = collection.iterator();
        do {
            z = true;
            if (!it.hasNext()) {
                return true;
            }
            if (indexOf(it.next()) < 0) {
                z = false;
                continue;
            }
        } while (z);
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            if (size() != set.size()) {
                return false;
            }
            int i = 0;
            while (i < this.mSize) {
                try {
                    if (!set.contains(this.mArray[i])) {
                        return false;
                    }
                    i++;
                } catch (ClassCastException | NullPointerException unused) {
                }
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        int[] iArr = this.mHashes;
        int i = this.mSize;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 += iArr[i3];
        }
        return i2;
    }

    public boolean isEmpty() {
        return this.mSize <= 0;
    }

    public Iterator iterator() {
        if (this.mCollections == null) {
            this.mCollections = new C0016c(this);
        }
        C0026m mVar = this.mCollections;
        if (mVar.mKeySet == null) {
            mVar.mKeySet = new C0023j(mVar);
        }
        return mVar.mKeySet.iterator();
    }

    public boolean remove(Object obj) {
        int indexOf = indexOf(obj);
        if (indexOf < 0) {
            return false;
        }
        removeAt(indexOf);
        return true;
    }

    public boolean removeAll(Collection collection) {
        boolean z;
        boolean z2 = false;
        for (Object indexOf : collection) {
            int indexOf2 = indexOf(indexOf);
            if (indexOf2 >= 0) {
                removeAt(indexOf2);
                z = true;
            } else {
                z = false;
            }
            z2 |= z;
        }
        return z2;
    }

    public Object removeAt(int i) {
        Object[] objArr = this.mArray;
        Object obj = objArr[i];
        int i2 = this.mSize;
        if (i2 <= 1) {
            m21a(this.mHashes, objArr, i2);
            this.mHashes = INT;
            this.mArray = OBJECT;
            this.mSize = 0;
        } else {
            int[] iArr = this.mHashes;
            int i3 = 8;
            if (iArr.length <= 8 || i2 >= iArr.length / 3) {
                this.mSize--;
                int i4 = this.mSize;
                if (i < i4) {
                    int[] iArr2 = this.mHashes;
                    int i5 = i + 1;
                    System.arraycopy(iArr2, i5, iArr2, i, i4 - i);
                    Object[] objArr2 = this.mArray;
                    System.arraycopy(objArr2, i5, objArr2, i, this.mSize - i);
                }
                this.mArray[this.mSize] = null;
            } else {
                if (i2 > 8) {
                    i3 = i2 + (i2 >> 1);
                }
                int[] iArr3 = this.mHashes;
                Object[] objArr3 = this.mArray;
                m22ob(i3);
                this.mSize--;
                if (i > 0) {
                    System.arraycopy(iArr3, 0, this.mHashes, 0, i);
                    System.arraycopy(objArr3, 0, this.mArray, 0, i);
                }
                int i6 = this.mSize;
                if (i < i6) {
                    int i7 = i + 1;
                    System.arraycopy(iArr3, i7, this.mHashes, i, i6 - i);
                    System.arraycopy(objArr3, i7, this.mArray, i, this.mSize - i);
                }
            }
        }
        return obj;
    }

    public boolean retainAll(Collection collection) {
        boolean z = false;
        for (int i = this.mSize - 1; i >= 0; i--) {
            if (!collection.contains(this.mArray[i])) {
                removeAt(i);
                z = true;
            }
        }
        return z;
    }

    public int size() {
        return this.mSize;
    }

    public Object[] toArray() {
        int i = this.mSize;
        Object[] objArr = new Object[i];
        System.arraycopy(this.mArray, 0, objArr, 0, i);
        return objArr;
    }

    public String toString() {
        if (this.mSize <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.mSize * 14);
        sb.append('{');
        for (int i = 0; i < this.mSize; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            Object obj = this.mArray[i];
            if (obj != this) {
                sb.append(obj);
            } else {
                sb.append("(this Set)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public Object valueAt(int i) {
        return this.mArray[i];
    }

    public Object[] toArray(Object[] objArr) {
        if (objArr.length < this.mSize) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), this.mSize);
        }
        System.arraycopy(this.mArray, 0, objArr, 0, this.mSize);
        int length = objArr.length;
        int i = this.mSize;
        if (length > i) {
            objArr[i] = null;
        }
        return objArr;
    }

    public int indexOf(Object obj) {
        return obj == null ? indexOfNull() : indexOf(obj, obj.hashCode());
    }
}
