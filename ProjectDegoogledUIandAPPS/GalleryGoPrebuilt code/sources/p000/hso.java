package p000;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
import p003j$.util.List$$CC;
import p003j$.util.Spliterator;
import p003j$.util.function.UnaryOperator;

/* renamed from: hso */
/* compiled from: PG */
public abstract class hso extends hsf implements List, RandomAccess, p003j$.util.List {

    /* renamed from: a */
    private static final hvs f13352a = new hsk(huw.f13440a, 0);

    /* renamed from: f */
    public static hso m12047f() {
        return huw.f13440a;
    }

    /* renamed from: g */
    public final hso mo7884g() {
        return this;
    }

    public final void replaceAll(UnaryOperator unaryOperator) {
        List$$CC.replaceAll$$dflt$$(this, unaryOperator);
    }

    public final void sort(Comparator comparator) {
        List$$CC.sort$$dflt$$(this, comparator);
    }

    public final Spliterator spliterator() {
        return List$$CC.spliterator$$dflt$$(this);
    }

    @Deprecated
    public final void add(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean addAll(int i, Collection collection) {
        throw new UnsupportedOperationException();
    }

    /* renamed from: b */
    static hso m12044b(Object[] objArr) {
        return m12045b(objArr, objArr.length);
    }

    /* renamed from: b */
    static hso m12045b(Object[] objArr, int i) {
        return i != 0 ? new huw(objArr, i) : huw.f13440a;
    }

    /* renamed from: j */
    public static hsj m12048j() {
        return new hsj();
    }

    /* renamed from: c */
    private static hso m12046c(Object... objArr) {
        return m12044b(ife.m12859a(objArr));
    }

    public boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    /* renamed from: a */
    public int mo7875a(Object[] objArr, int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            objArr[i + i2] = get(i2);
        }
        return i + size;
    }

    /* renamed from: a */
    public static hso m12032a(Iterable iterable) {
        ife.m12898e((Object) iterable);
        if (iterable instanceof Collection) {
            return m12041a((Collection) iterable);
        }
        Iterator it = iterable.iterator();
        if (!it.hasNext()) {
            return huw.f13440a;
        }
        Object next = it.next();
        if (!it.hasNext()) {
            return m12033a(next);
        }
        hsj hsj = new hsj();
        hsj.mo7908c(next);
        hsj.mo7906a(it);
        return hsj.mo7905a();
    }

    /* renamed from: a */
    public static hso m12041a(Collection collection) {
        if (!(collection instanceof hsf)) {
            return m12046c(collection.toArray());
        }
        hso g = ((hsf) collection).mo7884g();
        return g.mo7885h() ? m12044b(g.toArray()) : g;
    }

    /* renamed from: a */
    public static hso m12042a(Object[] objArr) {
        return objArr.length != 0 ? m12046c((Object[]) objArr.clone()) : huw.f13440a;
    }

    public final boolean equals(Object obj) {
        return ife.m12856a((List) this, obj);
    }

    public final int hashCode() {
        int size = size();
        int i = 1;
        for (int i2 = 0; i2 < size; i2++) {
            i = (i * 31) + get(i2).hashCode();
        }
        return i;
    }

    public int indexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            if (obj.equals(get(i))) {
                return i;
            }
        }
        return -1;
    }

    /* renamed from: a */
    public final hvr mo7876a() {
        return listIterator();
    }

    public int lastIndexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        for (int size = size() - 1; size >= 0; size--) {
            if (obj.equals(get(size))) {
                return size;
            }
        }
        return -1;
    }

    /* renamed from: i */
    public final hvs listIterator() {
        return listIterator(0);
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public final hvs listIterator(int i) {
        ife.m12888c(i, size());
        return !isEmpty() ? new hsk(this, i) : f13352a;
    }

    /* renamed from: a */
    public static hso m12033a(Object obj) {
        return m12046c(obj);
    }

    /* renamed from: a */
    public static hso m12034a(Object obj, Object obj2) {
        return m12046c(obj, obj2);
    }

    /* renamed from: a */
    public static hso m12035a(Object obj, Object obj2, Object obj3) {
        return m12046c(obj, obj2, obj3);
    }

    /* renamed from: a */
    public static hso m12036a(Object obj, Object obj2, Object obj3, Object obj4) {
        return m12046c(obj, obj2, obj3, obj4);
    }

    /* renamed from: a */
    public static hso m12037a(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        return m12046c(obj, obj2, obj3, obj4, obj5);
    }

    /* renamed from: a */
    public static hso m12038a(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        return m12046c(obj, obj2, obj3, obj4, obj5, obj6);
    }

    /* renamed from: a */
    public static hso m12039a(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        return m12046c(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }

    /* renamed from: a */
    public static hso m12040a(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        return m12046c(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Use SerializedForm");
    }

    @Deprecated
    public final Object remove(int i) {
        throw new UnsupportedOperationException();
    }

    /* renamed from: e */
    public hso mo7910e() {
        return size() > 1 ? new hsl(this) : this;
    }

    @Deprecated
    public final Object set(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    /* renamed from: a */
    public hso subList(int i, int i2) {
        ife.m12874b(i, i2, size());
        int i3 = i2 - i;
        if (i3 != size()) {
            return i3 != 0 ? new hsn(this, i, i3) : huw.f13440a;
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new hsm(toArray());
    }
}
