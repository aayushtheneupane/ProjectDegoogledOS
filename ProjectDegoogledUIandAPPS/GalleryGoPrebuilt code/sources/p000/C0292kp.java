package p000;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Set;
import p003j$.util.Collection$$CC;
import p003j$.util.Set$$CC;
import p003j$.util.Spliterator;
import p003j$.util.function.Predicate;
import p003j$.util.stream.Stream;

/* renamed from: kp */
/* compiled from: PG */
public final class C0292kp implements Collection, Set, p003j$.util.Collection, p003j$.util.Set {

    /* renamed from: c */
    private static final int[] f15138c = new int[0];

    /* renamed from: d */
    private static final Object[] f15139d = new Object[0];

    /* renamed from: e */
    private static Object[] f15140e;

    /* renamed from: f */
    private static int f15141f;

    /* renamed from: g */
    private static Object[] f15142g;

    /* renamed from: h */
    private static int f15143h;

    /* renamed from: i */
    private static final Object f15144i = new Object();

    /* renamed from: j */
    private static final Object f15145j = new Object();

    /* renamed from: a */
    public Object[] f15146a;

    /* renamed from: b */
    public int f15147b;

    /* renamed from: k */
    private int[] f15148k;

    /* renamed from: l */
    private C0304la f15149l;

    public final boolean isEmpty() {
        return this.f15147b <= 0;
    }

    public final Stream parallelStream() {
        return Collection$$CC.parallelStream$$dflt$$(this);
    }

    public final boolean removeIf(Predicate predicate) {
        return Collection$$CC.removeIf$$dflt$$(this, predicate);
    }

    public final int size() {
        return this.f15147b;
    }

    public final Spliterator spliterator() {
        return Set$$CC.spliterator$$dflt$$(this);
    }

    public final Stream stream() {
        return Collection$$CC.stream$$dflt$$(this);
    }

    public C0292kp() {
        this(0);
    }

    public C0292kp(int i) {
        if (i == 0) {
            this.f15148k = f15138c;
            this.f15146a = f15139d;
        } else {
            m14531c(i);
        }
        this.f15147b = 0;
    }

    public C0292kp(Collection collection) {
        this();
        if (collection != null) {
            addAll(collection);
        }
    }

    public final boolean add(Object obj) {
        int i;
        int i2;
        int i3 = this.f15147b;
        if (obj == null) {
            i2 = m14527a();
            i = 0;
        } else {
            int hashCode = obj.hashCode();
            i = hashCode;
            i2 = m14528a(obj, hashCode);
        }
        if (i2 >= 0) {
            return false;
        }
        int i4 = i2 ^ -1;
        int[] iArr = this.f15148k;
        int length = iArr.length;
        if (i3 >= length) {
            int i5 = 4;
            if (i3 >= 8) {
                i5 = (i3 >> 1) + i3;
            } else if (i3 >= 4) {
                i5 = 8;
            }
            Object[] objArr = this.f15146a;
            m14531c(i5);
            if (i3 == this.f15147b) {
                int[] iArr2 = this.f15148k;
                if (iArr2.length > 0) {
                    System.arraycopy(iArr, 0, iArr2, 0, length);
                    System.arraycopy(objArr, 0, this.f15146a, 0, objArr.length);
                }
                m14530a(iArr, objArr, i3);
            } else {
                throw new ConcurrentModificationException();
            }
        }
        if (i4 < i3) {
            int[] iArr3 = this.f15148k;
            int i6 = i4 + 1;
            int i7 = i3 - i4;
            System.arraycopy(iArr3, i4, iArr3, i6, i7);
            Object[] objArr2 = this.f15146a;
            System.arraycopy(objArr2, i4, objArr2, i6, i7);
        }
        int i8 = this.f15147b;
        if (i3 == i8) {
            int[] iArr4 = this.f15148k;
            if (i4 < iArr4.length) {
                iArr4[i4] = i;
                this.f15146a[i4] = obj;
                this.f15147b = i8 + 1;
                return true;
            }
        }
        throw new ConcurrentModificationException();
    }

    public final boolean addAll(Collection collection) {
        int size = this.f15147b + collection.size();
        int i = this.f15147b;
        int[] iArr = this.f15148k;
        if (iArr.length < size) {
            Object[] objArr = this.f15146a;
            m14531c(size);
            int i2 = this.f15147b;
            if (i2 > 0) {
                System.arraycopy(iArr, 0, this.f15148k, 0, i2);
                System.arraycopy(objArr, 0, this.f15146a, 0, this.f15147b);
            }
            m14530a(iArr, objArr, this.f15147b);
        }
        if (this.f15147b == i) {
            boolean z = false;
            for (Object add : collection) {
                z |= add(add);
            }
            if (z) {
                return true;
            }
            return false;
        }
        throw new ConcurrentModificationException();
    }

    /* renamed from: c */
    private final void m14531c(int i) {
        if (i == 8) {
            synchronized (f15145j) {
                if (f15142g != null) {
                    Object[] objArr = f15142g;
                    try {
                        this.f15146a = objArr;
                        f15142g = (Object[]) objArr[0];
                        int[] iArr = (int[]) objArr[1];
                        this.f15148k = iArr;
                        if (iArr != null) {
                            objArr[1] = null;
                            objArr[0] = null;
                            f15143h--;
                            return;
                        }
                    } catch (ClassCastException e) {
                    }
                    System.out.println("ArraySet Found corrupt ArraySet cache: [0]=" + objArr[0] + " [1]=" + objArr[1]);
                    f15142g = null;
                    f15143h = 0;
                }
            }
        } else if (i == 4) {
            synchronized (f15144i) {
                if (f15140e != null) {
                    Object[] objArr2 = f15140e;
                    try {
                        this.f15146a = objArr2;
                        f15140e = (Object[]) objArr2[0];
                        int[] iArr2 = (int[]) objArr2[1];
                        this.f15148k = iArr2;
                        if (iArr2 != null) {
                            objArr2[1] = null;
                            objArr2[0] = null;
                            f15141f--;
                            return;
                        }
                    } catch (ClassCastException e2) {
                    }
                    System.out.println("ArraySet Found corrupt ArraySet cache: [0]=" + objArr2[0] + " [1]=" + objArr2[1]);
                    f15140e = null;
                    f15141f = 0;
                }
            }
        }
        this.f15148k = new int[i];
        this.f15146a = new Object[i];
    }

    /* renamed from: a */
    private final int m14529a(int[] iArr, int i) {
        try {
            return C0294kr.m14537a(iArr, this.f15147b, i);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ConcurrentModificationException();
        }
    }

    public final void clear() {
        int i = this.f15147b;
        if (i != 0) {
            int[] iArr = this.f15148k;
            Object[] objArr = this.f15146a;
            this.f15148k = f15138c;
            this.f15146a = f15139d;
            this.f15147b = 0;
            m14530a(iArr, objArr, i);
        }
        if (this.f15147b != 0) {
            throw new ConcurrentModificationException();
        }
    }

    public final boolean contains(Object obj) {
        return mo9203a(obj) >= 0;
    }

    public final boolean containsAll(Collection collection) {
        for (Object contains : collection) {
            if (!contains(contains)) {
                return false;
            }
        }
        return true;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            if (this.f15147b == set.size()) {
                int i = 0;
                while (i < this.f15147b) {
                    try {
                        if (!set.contains(mo9204a(i))) {
                            return false;
                        }
                        i++;
                    } catch (NullPointerException e) {
                    } catch (ClassCastException e2) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    private static void m14530a(int[] iArr, Object[] objArr, int i) {
        int length = iArr.length;
        if (length == 8) {
            synchronized (f15145j) {
                if (f15143h < 10) {
                    objArr[0] = f15142g;
                    objArr[1] = iArr;
                    for (int i2 = i - 1; i2 >= 2; i2--) {
                        objArr[i2] = null;
                    }
                    f15142g = objArr;
                    f15143h++;
                }
            }
        } else if (length == 4) {
            synchronized (f15144i) {
                if (f15141f < 10) {
                    objArr[0] = f15140e;
                    objArr[1] = iArr;
                    for (int i3 = i - 1; i3 >= 2; i3--) {
                        objArr[i3] = null;
                    }
                    f15140e = objArr;
                    f15141f++;
                }
            }
        }
    }

    public final int hashCode() {
        int[] iArr = this.f15148k;
        int i = this.f15147b;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 += iArr[i3];
        }
        return i2;
    }

    /* renamed from: a */
    public final int mo9203a(Object obj) {
        return obj != null ? m14528a(obj, obj.hashCode()) : m14527a();
    }

    /* renamed from: a */
    private final int m14528a(Object obj, int i) {
        int i2 = this.f15147b;
        if (i2 == 0) {
            return -1;
        }
        int a = m14529a(this.f15148k, i);
        if (a < 0 || obj.equals(this.f15146a[a])) {
            return a;
        }
        int i3 = a + 1;
        while (i3 < i2 && this.f15148k[i3] == i) {
            if (obj.equals(this.f15146a[i3])) {
                return i3;
            }
            i3++;
        }
        int i4 = a - 1;
        while (i4 >= 0 && this.f15148k[i4] == i) {
            if (obj.equals(this.f15146a[i4])) {
                return i4;
            }
            i4--;
        }
        return i3 ^ -1;
    }

    /* renamed from: a */
    private final int m14527a() {
        int i = this.f15147b;
        if (i == 0) {
            return -1;
        }
        int a = m14529a(this.f15148k, 0);
        if (a < 0 || this.f15146a[a] == null) {
            return a;
        }
        int i2 = a + 1;
        while (i2 < i && this.f15148k[i2] == 0) {
            if (this.f15146a[i2] == null) {
                return i2;
            }
            i2++;
        }
        int i3 = a - 1;
        while (i3 >= 0 && this.f15148k[i3] == 0) {
            if (this.f15146a[i3] == null) {
                return i3;
            }
            i3--;
        }
        return i2 ^ -1;
    }

    public final Iterator iterator() {
        if (this.f15149l == null) {
            this.f15149l = new C0291ko(this);
        }
        return this.f15149l.mo9313d().iterator();
    }

    public final boolean remove(Object obj) {
        int a = mo9203a(obj);
        if (a < 0) {
            return false;
        }
        mo9207b(a);
        return true;
    }

    public final boolean removeAll(Collection collection) {
        boolean z = false;
        for (Object remove : collection) {
            z |= remove(remove);
        }
        if (z) {
            return true;
        }
        return false;
    }

    /* renamed from: b */
    public final void mo9207b(int i) {
        int i2 = this.f15147b;
        Object[] objArr = this.f15146a;
        if (i2 > 1) {
            int i3 = i2 - 1;
            int[] iArr = this.f15148k;
            int length = iArr.length;
            int i4 = 8;
            if (length <= 8 || i2 >= length / 3) {
                if (i < i3) {
                    int i5 = i + 1;
                    int i6 = i3 - i;
                    System.arraycopy(iArr, i5, iArr, i, i6);
                    Object[] objArr2 = this.f15146a;
                    System.arraycopy(objArr2, i5, objArr2, i, i6);
                }
                this.f15146a[i3] = null;
            } else {
                if (i2 > 8) {
                    i4 = i2 + (i2 >> 1);
                }
                m14531c(i4);
                if (i > 0) {
                    System.arraycopy(iArr, 0, this.f15148k, 0, i);
                    System.arraycopy(objArr, 0, this.f15146a, 0, i);
                }
                if (i < i3) {
                    int i7 = i + 1;
                    int i8 = i3 - i;
                    System.arraycopy(iArr, i7, this.f15148k, i, i8);
                    System.arraycopy(objArr, i7, this.f15146a, i, i8);
                }
            }
            if (i2 == this.f15147b) {
                this.f15147b = i3;
                return;
            }
            throw new ConcurrentModificationException();
        }
        clear();
    }

    public final boolean retainAll(Collection collection) {
        boolean z = false;
        for (int i = this.f15147b - 1; i >= 0; i--) {
            if (!collection.contains(this.f15146a[i])) {
                mo9207b(i);
                z = true;
            }
        }
        return z;
    }

    public final Object[] toArray() {
        int i = this.f15147b;
        Object[] objArr = new Object[i];
        System.arraycopy(this.f15146a, 0, objArr, 0, i);
        return objArr;
    }

    public final Object[] toArray(Object[] objArr) {
        if (objArr.length < this.f15147b) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), this.f15147b);
        }
        System.arraycopy(this.f15146a, 0, objArr, 0, this.f15147b);
        int length = objArr.length;
        int i = this.f15147b;
        if (length > i) {
            objArr[i] = null;
        }
        return objArr;
    }

    public final String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f15147b * 14);
        sb.append('{');
        for (int i = 0; i < this.f15147b; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            Object a = mo9204a(i);
            if (a == this) {
                sb.append("(this Set)");
            } else {
                sb.append(a);
            }
        }
        sb.append('}');
        return sb.toString();
    }

    /* renamed from: a */
    public final Object mo9204a(int i) {
        return this.f15146a[i];
    }
}
