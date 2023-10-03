package p000;

import java.util.ConcurrentModificationException;
import java.util.Map;

/* renamed from: lf */
/* compiled from: PG */
public class C0309lf {

    /* renamed from: c */
    private static Object[] f15188c;

    /* renamed from: d */
    private static int f15189d;

    /* renamed from: e */
    private static Object[] f15190e;

    /* renamed from: f */
    private static int f15191f;

    /* renamed from: a */
    public Object[] f15192a;

    /* renamed from: b */
    public int f15193b;

    /* renamed from: g */
    private int[] f15194g;

    public C0309lf() {
        this.f15194g = C0294kr.f15150a;
        this.f15192a = C0294kr.f15151b;
        this.f15193b = 0;
    }

    public final boolean isEmpty() {
        return this.f15193b <= 0;
    }

    public final int size() {
        return this.f15193b;
    }

    public C0309lf(int i) {
        if (i == 0) {
            this.f15194g = C0294kr.f15150a;
            this.f15192a = C0294kr.f15151b;
        } else {
            m14585e(i);
        }
        this.f15193b = 0;
    }

    public C0309lf(C0309lf lfVar) {
        this();
        if (lfVar != null) {
            mo1933a(lfVar);
        }
    }

    /* renamed from: e */
    private final void m14585e(int i) {
        Class<C0309lf> cls = C0309lf.class;
        if (i == 8) {
            synchronized (cls) {
                if (f15190e != null) {
                    Object[] objArr = f15190e;
                    this.f15192a = objArr;
                    f15190e = (Object[]) objArr[0];
                    this.f15194g = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    f15191f--;
                    return;
                }
            }
        } else if (i == 4) {
            synchronized (cls) {
                if (f15188c != null) {
                    Object[] objArr2 = f15188c;
                    this.f15192a = objArr2;
                    f15188c = (Object[]) objArr2[0];
                    this.f15194g = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    f15189d--;
                    return;
                }
            }
        }
        this.f15194g = new int[i];
        this.f15192a = new Object[(i + i)];
    }

    /* renamed from: a */
    private static int m14583a(int[] iArr, int i, int i2) {
        try {
            return C0294kr.m14537a(iArr, i, i2);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ConcurrentModificationException();
        }
    }

    public void clear() {
        int i = this.f15193b;
        if (i > 0) {
            int[] iArr = this.f15194g;
            Object[] objArr = this.f15192a;
            this.f15194g = C0294kr.f15150a;
            this.f15192a = C0294kr.f15151b;
            this.f15193b = 0;
            m14584a(iArr, objArr, i);
        }
        if (this.f15193b > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public final boolean containsKey(Object obj) {
        return mo9317a(obj) >= 0;
    }

    public final boolean containsValue(Object obj) {
        return mo9319b(obj) >= 0;
    }

    /* renamed from: a */
    public final void mo9318a(int i) {
        int i2 = this.f15193b;
        int[] iArr = this.f15194g;
        if (iArr.length < i) {
            Object[] objArr = this.f15192a;
            m14585e(i);
            if (this.f15193b > 0) {
                System.arraycopy(iArr, 0, this.f15194g, 0, i2);
                System.arraycopy(objArr, 0, this.f15192a, 0, i2 + i2);
            }
            m14584a(iArr, objArr, i2);
        }
        if (this.f15193b != i2) {
            throw new ConcurrentModificationException();
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C0309lf) {
            C0309lf lfVar = (C0309lf) obj;
            if (this.f15193b != lfVar.f15193b) {
                return false;
            }
            int i = 0;
            while (i < this.f15193b) {
                try {
                    Object b = mo9320b(i);
                    Object c = mo9321c(i);
                    Object obj2 = lfVar.get(b);
                    if (c != null) {
                        if (!c.equals(obj2)) {
                            return false;
                        }
                    } else if (obj2 != null || !lfVar.containsKey(b)) {
                        return false;
                    }
                    i++;
                } catch (NullPointerException e) {
                    return false;
                } catch (ClassCastException e2) {
                    return false;
                }
            }
            return true;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (this.f15193b == map.size()) {
                int i2 = 0;
                while (i2 < this.f15193b) {
                    try {
                        Object b2 = mo9320b(i2);
                        Object c2 = mo9321c(i2);
                        Object obj3 = map.get(b2);
                        if (c2 != null) {
                            if (!c2.equals(obj3)) {
                                return false;
                            }
                        } else if (obj3 != null || !map.containsKey(b2)) {
                            return false;
                        }
                        i2++;
                    } catch (NullPointerException e3) {
                    } catch (ClassCastException e4) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    private static void m14584a(int[] iArr, Object[] objArr, int i) {
        Class<C0309lf> cls = C0309lf.class;
        int length = iArr.length;
        if (length == 8) {
            synchronized (cls) {
                if (f15191f < 10) {
                    objArr[0] = f15190e;
                    objArr[1] = iArr;
                    for (int i2 = (i + i) - 1; i2 >= 2; i2--) {
                        objArr[i2] = null;
                    }
                    f15190e = objArr;
                    f15191f++;
                }
            }
        } else if (length == 4) {
            synchronized (cls) {
                if (f15189d < 10) {
                    objArr[0] = f15188c;
                    objArr[1] = iArr;
                    for (int i3 = (i + i) - 1; i3 >= 2; i3--) {
                        objArr[i3] = null;
                    }
                    f15188c = objArr;
                    f15189d++;
                }
            }
        }
    }

    public final Object get(Object obj) {
        return getOrDefault(obj, (Object) null);
    }

    public final Object getOrDefault(Object obj, Object obj2) {
        int a = mo9317a(obj);
        return a >= 0 ? this.f15192a[a + a + 1] : obj2;
    }

    public int hashCode() {
        int[] iArr = this.f15194g;
        Object[] objArr = this.f15192a;
        int i = this.f15193b;
        int i2 = 1;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            Object obj = objArr[i2];
            i4 += (obj != null ? obj.hashCode() : 0) ^ iArr[i3];
            i3++;
            i2 += 2;
        }
        return i4;
    }

    /* renamed from: a */
    private final int m14582a(Object obj, int i) {
        int i2 = this.f15193b;
        if (i2 == 0) {
            return -1;
        }
        int a = m14583a(this.f15194g, i2, i);
        if (a < 0 || obj.equals(this.f15192a[a + a])) {
            return a;
        }
        int i3 = a + 1;
        while (i3 < i2 && this.f15194g[i3] == i) {
            if (obj.equals(this.f15192a[i3 + i3])) {
                return i3;
            }
            i3++;
        }
        int i4 = a - 1;
        while (i4 >= 0 && this.f15194g[i4] == i) {
            if (obj.equals(this.f15192a[i4 + i4])) {
                return i4;
            }
            i4--;
        }
        return i3 ^ -1;
    }

    /* renamed from: a */
    public final int mo9317a(Object obj) {
        return obj != null ? m14582a(obj, obj.hashCode()) : m14581a();
    }

    /* renamed from: a */
    private final int m14581a() {
        int i = this.f15193b;
        if (i == 0) {
            return -1;
        }
        int a = m14583a(this.f15194g, i, 0);
        if (a < 0 || this.f15192a[a + a] == null) {
            return a;
        }
        int i2 = a + 1;
        while (i2 < i && this.f15194g[i2] == 0) {
            if (this.f15192a[i2 + i2] == null) {
                return i2;
            }
            i2++;
        }
        int i3 = a - 1;
        while (i3 >= 0 && this.f15194g[i3] == 0) {
            if (this.f15192a[i3 + i3] == null) {
                return i3;
            }
            i3--;
        }
        return i2 ^ -1;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final int mo9319b(Object obj) {
        int i = this.f15193b;
        int i2 = i + i;
        Object[] objArr = this.f15192a;
        if (obj == null) {
            for (int i3 = 1; i3 < i2; i3 += 2) {
                if (objArr[i3] == null) {
                    return i3 >> 1;
                }
            }
            return -1;
        }
        for (int i4 = 1; i4 < i2; i4 += 2) {
            if (obj.equals(objArr[i4])) {
                return i4 >> 1;
            }
        }
        return -1;
    }

    /* renamed from: b */
    public final Object mo9320b(int i) {
        return this.f15192a[i + i];
    }

    public Object put(Object obj, Object obj2) {
        int i;
        int i2;
        int i3 = this.f15193b;
        if (obj == null) {
            i2 = m14581a();
            i = 0;
        } else {
            int hashCode = obj.hashCode();
            i = hashCode;
            i2 = m14582a(obj, hashCode);
        }
        if (i2 >= 0) {
            int i4 = i2 + i2 + 1;
            Object[] objArr = this.f15192a;
            Object obj3 = objArr[i4];
            objArr[i4] = obj2;
            return obj3;
        }
        int i5 = i2 ^ -1;
        int[] iArr = this.f15194g;
        int length = iArr.length;
        if (i3 >= length) {
            int i6 = 4;
            if (i3 >= 8) {
                i6 = (i3 >> 1) + i3;
            } else if (i3 >= 4) {
                i6 = 8;
            }
            Object[] objArr2 = this.f15192a;
            m14585e(i6);
            if (i3 == this.f15193b) {
                int[] iArr2 = this.f15194g;
                if (iArr2.length > 0) {
                    System.arraycopy(iArr, 0, iArr2, 0, length);
                    System.arraycopy(objArr2, 0, this.f15192a, 0, objArr2.length);
                }
                m14584a(iArr, objArr2, i3);
            } else {
                throw new ConcurrentModificationException();
            }
        }
        if (i5 < i3) {
            int[] iArr3 = this.f15194g;
            int i7 = i5 + 1;
            System.arraycopy(iArr3, i5, iArr3, i7, i3 - i5);
            Object[] objArr3 = this.f15192a;
            int i8 = this.f15193b - i5;
            System.arraycopy(objArr3, i5 + i5, objArr3, i7 + i7, i8 + i8);
        }
        int i9 = this.f15193b;
        if (i3 == i9) {
            int[] iArr4 = this.f15194g;
            if (i5 < iArr4.length) {
                iArr4[i5] = i;
                Object[] objArr4 = this.f15192a;
                int i10 = i5 + i5;
                objArr4[i10] = obj;
                objArr4[i10 + 1] = obj2;
                this.f15193b = i9 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    /* renamed from: a */
    public void mo1933a(C0309lf lfVar) {
        int i = lfVar.f15193b;
        mo9318a(this.f15193b + i);
        if (this.f15193b != 0) {
            for (int i2 = 0; i2 < i; i2++) {
                put(lfVar.mo9320b(i2), lfVar.mo9321c(i2));
            }
        } else if (i > 0) {
            System.arraycopy(lfVar.f15194g, 0, this.f15194g, 0, i);
            System.arraycopy(lfVar.f15192a, 0, this.f15192a, 0, i + i);
            this.f15193b = i;
        }
    }

    public final Object putIfAbsent(Object obj, Object obj2) {
        Object obj3 = get(obj);
        return obj3 == null ? put(obj, obj2) : obj3;
    }

    public final Object remove(Object obj) {
        int a = mo9317a(obj);
        if (a < 0) {
            return null;
        }
        return mo1935d(a);
    }

    public final boolean remove(Object obj, Object obj2) {
        int a = mo9317a(obj);
        if (a < 0) {
            return false;
        }
        Object c = mo9321c(a);
        if (obj2 != c && (obj2 == null || !obj2.equals(c))) {
            return false;
        }
        mo1935d(a);
        return true;
    }

    /* renamed from: d */
    public Object mo1935d(int i) {
        Object[] objArr = this.f15192a;
        int i2 = i + i;
        Object obj = objArr[i2 + 1];
        int i3 = this.f15193b;
        int i4 = 0;
        if (i3 <= 1) {
            m14584a(this.f15194g, objArr, i3);
            this.f15194g = C0294kr.f15150a;
            this.f15192a = C0294kr.f15151b;
        } else {
            int i5 = i3 - 1;
            int[] iArr = this.f15194g;
            int length = iArr.length;
            int i6 = 8;
            if (length <= 8 || i3 >= length / 3) {
                if (i < i5) {
                    int i7 = i + 1;
                    int i8 = i5 - i;
                    System.arraycopy(iArr, i7, iArr, i, i8);
                    Object[] objArr2 = this.f15192a;
                    System.arraycopy(objArr2, i7 + i7, objArr2, i2, i8 + i8);
                }
                Object[] objArr3 = this.f15192a;
                int i9 = i5 + i5;
                objArr3[i9] = null;
                objArr3[i9 + 1] = null;
            } else {
                if (i3 > 8) {
                    i6 = i3 + (i3 >> 1);
                }
                m14585e(i6);
                if (i3 == this.f15193b) {
                    if (i > 0) {
                        System.arraycopy(iArr, 0, this.f15194g, 0, i);
                        System.arraycopy(objArr, 0, this.f15192a, 0, i2);
                    }
                    if (i < i5) {
                        int i10 = i + 1;
                        int i11 = i5 - i;
                        System.arraycopy(iArr, i10, this.f15194g, i, i11);
                        System.arraycopy(objArr, i10 + i10, this.f15192a, i2, i11 + i11);
                    }
                } else {
                    throw new ConcurrentModificationException();
                }
            }
            i4 = i5;
        }
        if (i3 == this.f15193b) {
            this.f15193b = i4;
            return obj;
        }
        throw new ConcurrentModificationException();
    }

    public final Object replace(Object obj, Object obj2) {
        int a = mo9317a(obj);
        if (a < 0) {
            return null;
        }
        return mo1932a(a, obj2);
    }

    public final boolean replace(Object obj, Object obj2, Object obj3) {
        int a = mo9317a(obj);
        if (a < 0) {
            return false;
        }
        Object c = mo9321c(a);
        if (c != obj2 && (obj2 == null || !obj2.equals(c))) {
            return false;
        }
        mo1932a(a, obj3);
        return true;
    }

    /* renamed from: a */
    public Object mo1932a(int i, Object obj) {
        int i2 = i + i + 1;
        Object[] objArr = this.f15192a;
        Object obj2 = objArr[i2];
        objArr[i2] = obj;
        return obj2;
    }

    public final String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f15193b * 28);
        sb.append('{');
        for (int i = 0; i < this.f15193b; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            Object b = mo9320b(i);
            if (b != this) {
                sb.append(b);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            Object c = mo9321c(i);
            if (c == this) {
                sb.append("(this Map)");
            } else {
                sb.append(c);
            }
        }
        sb.append('}');
        return sb.toString();
    }

    /* renamed from: c */
    public final Object mo9321c(int i) {
        return this.f15192a[i + i + 1];
    }
}
