package p000;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.SortedSet;
import p003j$.util.Set$$CC;
import p003j$.util.Spliterator;

/* renamed from: hto */
/* compiled from: PG */
public abstract class hto extends hsf implements Set, p003j$.util.Set {

    /* renamed from: a */
    private transient hso f13387a;

    /* renamed from: a */
    public static boolean m12127a(int i, int i2) {
        return i < (i2 >> 1) + (i2 >> 2);
    }

    /* renamed from: a */
    public abstract hvr iterator();

    /* renamed from: f */
    public boolean mo7964f() {
        return false;
    }

    public final Spliterator spliterator() {
        return Set$$CC.spliterator$$dflt$$(this);
    }

    /* renamed from: g */
    public hso mo7884g() {
        hso hso = this.f13387a;
        if (hso != null) {
            return hso;
        }
        hso i = mo7999i();
        this.f13387a = i;
        return i;
    }

    /* renamed from: j */
    public static htm m12130j() {
        return new htm();
    }

    /* renamed from: c */
    public static htm m12129c(int i) {
        ife.m12839a(i, "expectedSize");
        return new htm(i);
    }

    /* renamed from: b */
    static int m12128b(int i) {
        boolean z;
        double d;
        int max = Math.max(i, 2);
        if (max < 751619276) {
            int highestOneBit = Integer.highestOneBit(max - 1);
            do {
                highestOneBit += highestOneBit;
                d = (double) highestOneBit;
                Double.isNaN(d);
            } while (d * 0.7d < ((double) max));
            return highestOneBit;
        }
        if (max < 1073741824) {
            z = true;
        } else {
            z = false;
        }
        ife.m12845a(z, (Object) "collection too large");
        return 1073741824;
    }

    /* renamed from: a */
    public static hto m12119a(int i, Object... objArr) {
        if (i == 0) {
            return hvf.f13465a;
        }
        if (i == 1) {
            return m12120a(objArr[0]);
        }
        int b = m12128b(i);
        Object[] objArr2 = new Object[b];
        int i2 = b - 1;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < i; i5++) {
            Object a = ife.m12826a(objArr[i5], i5);
            int hashCode = a.hashCode();
            int d = ife.m12892d(hashCode);
            while (true) {
                int i6 = d & i2;
                Object obj = objArr2[i6];
                if (obj != null) {
                    if (obj.equals(a)) {
                        break;
                    }
                    d++;
                } else {
                    objArr[i4] = a;
                    objArr2[i6] = a;
                    i3 += hashCode;
                    i4++;
                    break;
                }
            }
        }
        Arrays.fill(objArr, i4, i, (Object) null);
        if (i4 == 1) {
            return new hvo(objArr[0], i3);
        }
        if (m12128b(i4) < b / 2) {
            return m12119a(i4, objArr);
        }
        if (m12127a(i4, objArr.length)) {
            objArr = Arrays.copyOf(objArr, i4);
        }
        return new hvf(objArr, i3, objArr2, i2, i4);
    }

    /* renamed from: a */
    public static hto m12125a(Collection collection) {
        if ((collection instanceof hto) && !(collection instanceof SortedSet)) {
            hto hto = (hto) collection;
            if (!hto.mo7885h()) {
                return hto;
            }
        }
        Object[] array = collection.toArray();
        return m12119a(array.length, array);
    }

    /* renamed from: a */
    public static hto m12126a(Object[] objArr) {
        int length = objArr.length;
        if (length == 0) {
            return hvf.f13465a;
        }
        if (length != 1) {
            return m12119a(length, (Object[]) objArr.clone());
        }
        return m12120a(objArr[0]);
    }

    /* renamed from: i */
    public hso mo7999i() {
        return hso.m12044b(toArray());
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof hto) && mo7964f() && ((hto) obj).mo7964f() && hashCode() != obj.hashCode()) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            try {
                if (size() != set.size() || !containsAll(set)) {
                    return false;
                }
                return true;
            } catch (ClassCastException | NullPointerException e) {
            }
        }
        return false;
    }

    public int hashCode() {
        return ife.m12809a((Set) this);
    }

    /* renamed from: a */
    public static hto m12120a(Object obj) {
        return new hvo(obj);
    }

    /* renamed from: a */
    public static hto m12121a(Object obj, Object obj2) {
        return m12119a(2, obj, obj2);
    }

    /* renamed from: a */
    public static hto m12122a(Object obj, Object obj2, Object obj3) {
        return m12119a(3, obj, obj2, obj3);
    }

    /* renamed from: a */
    public static hto m12123a(Object obj, Object obj2, Object obj3, Object obj4) {
        return m12119a(4, obj, obj2, obj3, obj4);
    }

    @SafeVarargs
    /* renamed from: a */
    public static hto m12124a(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object... objArr) {
        ife.m12845a(true, (Object) "the total number of elements must fit in an int");
        int length = objArr.length;
        int i = length + 6;
        Object[] objArr2 = new Object[i];
        objArr2[0] = obj;
        objArr2[1] = obj2;
        objArr2[2] = obj3;
        objArr2[3] = obj4;
        objArr2[4] = obj5;
        objArr2[5] = obj6;
        System.arraycopy(objArr, 0, objArr2, 6, length);
        return m12119a(i, objArr2);
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new htn(toArray());
    }
}
