package p000;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.Arrays;
import p003j$.util.Collection;
import p003j$.util.Collection$$CC;
import p003j$.util.Spliterator;
import p003j$.util.function.Predicate;
import p003j$.util.stream.Stream;

/* renamed from: hsf */
/* compiled from: PG */
public abstract class hsf extends AbstractCollection implements Serializable, Collection {

    /* renamed from: az */
    private static final Object[] f13341az = new Object[0];

    /* renamed from: a */
    public abstract hvr iterator();

    /* renamed from: b */
    public Object[] mo7879b() {
        return null;
    }

    public abstract boolean contains(Object obj);

    /* renamed from: g */
    public hso mo7884g() {
        throw null;
    }

    /* renamed from: h */
    public abstract boolean mo7885h();

    public final Stream parallelStream() {
        return Collection$$CC.parallelStream$$dflt$$(this);
    }

    public final boolean removeIf(Predicate predicate) {
        return Collection$$CC.removeIf$$dflt$$(this, predicate);
    }

    public Spliterator spliterator() {
        return Collection$$CC.spliterator$$dflt$$(this);
    }

    public final Stream stream() {
        return Collection$$CC.stream$$dflt$$(this);
    }

    @Deprecated
    public final boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean addAll(java.util.Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    /* renamed from: a */
    public int mo7875a(Object[] objArr, int i) {
        hvr a = iterator();
        while (a.hasNext()) {
            objArr[i] = a.next();
            i++;
        }
        return i;
    }

    /* renamed from: d */
    public int mo7883d() {
        throw new UnsupportedOperationException();
    }

    /* renamed from: c */
    public int mo7880c() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean removeAll(java.util.Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean retainAll(java.util.Collection collection) {
        throw new UnsupportedOperationException();
    }

    public final Object[] toArray() {
        return toArray(f13341az);
    }

    public final Object[] toArray(Object[] objArr) {
        ife.m12898e((Object) objArr);
        int size = size();
        int length = objArr.length;
        if (length < size) {
            Object[] b = mo7879b();
            if (b != null) {
                return Arrays.copyOfRange(b, mo7880c(), mo7883d(), objArr.getClass());
            }
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), size);
        } else if (length > size) {
            objArr[size] = null;
        }
        mo7875a(objArr, 0);
        return objArr;
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new hsm(toArray());
    }
}
