package com.google.common.collect;

import android.support.p016v4.media.session.C0107q;
import com.google.common.base.C1508E;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public abstract class ImmutableList extends ImmutableCollection implements List, RandomAccess {
    private static final ImmutableList EMPTY = new RegularImmutableList(C1638_a.EMPTY_ARRAY);

    class ReverseImmutableList extends ImmutableList {

        /* renamed from: JP */
        private final transient ImmutableList f2436JP;

        ReverseImmutableList(ImmutableList immutableList) {
            this.f2436JP = immutableList;
        }

        /* renamed from: Nb */
        private int m4208Nb(int i) {
            return (size() - 1) - i;
        }

        public boolean contains(Object obj) {
            return this.f2436JP.contains(obj);
        }

        public Object get(int i) {
            C1508E.m3958J(i, size());
            return this.f2436JP.get((size() - 1) - i);
        }

        public int indexOf(Object obj) {
            int lastIndexOf = this.f2436JP.lastIndexOf(obj);
            if (lastIndexOf >= 0) {
                return m4208Nb(lastIndexOf);
            }
            return -1;
        }

        public int lastIndexOf(Object obj) {
            int indexOf = this.f2436JP.indexOf(obj);
            if (indexOf >= 0) {
                return m4208Nb(indexOf);
            }
            return -1;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: pl */
        public boolean mo8636pl() {
            return this.f2436JP.mo8636pl();
        }

        public ImmutableList reverse() {
            return this.f2436JP;
        }

        public int size() {
            return this.f2436JP.size();
        }

        public ImmutableList subList(int i, int i2) {
            C1508E.m3963c(i, i2, size());
            return this.f2436JP.subList(size() - i2, size() - i).reverse();
        }
    }

    class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        final Object[] elements;

        SerializedForm(Object[] objArr) {
            this.elements = objArr;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return ImmutableList.m4202g(this.elements);
        }
    }

    class SubList extends ImmutableList {
        final transient int length;
        final transient int offset;

        SubList(int i, int i2) {
            this.offset = i;
            this.length = i2;
        }

        public Object get(int i) {
            C1508E.m3958J(i, this.length);
            return ImmutableList.this.get(i + this.offset);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: pl */
        public boolean mo8636pl() {
            return true;
        }

        public int size() {
            return this.length;
        }

        public ImmutableList subList(int i, int i2) {
            C1508E.m3963c(i, i2, this.length);
            ImmutableList immutableList = ImmutableList.this;
            int i3 = this.offset;
            return immutableList.subList(i + i3, i2 + i3);
        }
    }

    ImmutableList() {
    }

    public static C1608Q builder() {
        return new C1608Q();
    }

    /* renamed from: e */
    static ImmutableList m4201e(Object[] objArr, int i) {
        if (i == 0) {
            return EMPTY;
        }
        if (i == 1) {
            return new SingletonImmutableList(objArr[0]);
        }
        if (i < objArr.length) {
            objArr = C1638_a.m4553a(objArr, i);
        }
        return new RegularImmutableList(objArr);
    }

    /* renamed from: g */
    public static ImmutableList m4202g(Object[] objArr) {
        int length = objArr.length;
        if (length == 0) {
            return EMPTY;
        }
        if (length == 1) {
            return new SingletonImmutableList(objArr[0]);
        }
        Object[] objArr2 = (Object[]) objArr.clone();
        C1638_a.m4557f(objArr2);
        return new RegularImmutableList(objArr2);
    }

    /* renamed from: of */
    public static ImmutableList m4203of(Object obj) {
        return new SingletonImmutableList(obj);
    }

    /* renamed from: ql */
    public static ImmutableList m4204ql() {
        return EMPTY;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Use SerializedForm");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: M */
    public ImmutableList mo8720M(int i, int i2) {
        return new SubList(i, i2 - i);
    }

    /* renamed from: Ol */
    public final ImmutableList mo8648Ol() {
        return this;
    }

    @Deprecated
    public final void add(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean addAll(int i, Collection collection) {
        throw new UnsupportedOperationException();
    }

    public boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public int mo8652d(Object[] objArr, int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            objArr[i + i2] = get(i2);
        }
        return i + size;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            if (size() == list.size() && C1652ea.m4575a(iterator(), list.iterator())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int size = size();
        int i = 1;
        for (int i2 = 0; i2 < size; i2++) {
            i = ~(~(get(i2).hashCode() + (i * 31)));
        }
        return i;
    }

    public int indexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        ListIterator listIterator = listIterator();
        while (listIterator.hasNext()) {
            if (C0107q.m131b(obj, listIterator.next())) {
                return listIterator.previousIndex();
            }
        }
        return -1;
    }

    public int lastIndexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        ListIterator listIterator = listIterator(size());
        while (listIterator.hasPrevious()) {
            if (C0107q.m131b(obj, listIterator.previous())) {
                return listIterator.nextIndex();
            }
        }
        return -1;
    }

    @Deprecated
    public final Object remove(int i) {
        throw new UnsupportedOperationException();
    }

    public ImmutableList reverse() {
        return new ReverseImmutableList(this);
    }

    @Deprecated
    public final Object set(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new SerializedForm(toArray());
    }

    public C1692rb iterator() {
        return listIterator();
    }

    public ImmutableList subList(int i, int i2) {
        C1508E.m3963c(i, i2, size());
        int i3 = i2 - i;
        if (i3 == 0) {
            return EMPTY;
        }
        if (i3 != 1) {
            return mo8720M(i, i2);
        }
        return m4203of(get(i));
    }

    public C1695sb listIterator() {
        return listIterator(0);
    }

    public C1695sb listIterator(int i) {
        return new C1606P(this, size(), i);
    }
}
