package com.google.common.collect;

import android.support.p002v7.appcompat.R$style;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableCollection;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.UnaryOperator;

public abstract class ImmutableList<E> extends ImmutableCollection<E> implements List<E>, RandomAccess {

    public static final class Builder<E> extends ImmutableCollection.ArrayBasedBuilder<E> {
        public Builder() {
            super(4);
        }

        public ImmutableCollection.Builder add(Object obj) {
            super.add(obj);
            return this;
        }

        public Builder<E> addAll(Iterator<? extends E> it) {
            while (it.hasNext()) {
                add(it.next());
            }
            return this;
        }

        public ImmutableList<E> build() {
            return ImmutableList.asImmutableList(this.contents, this.size);
        }

        /* renamed from: add  reason: collision with other method in class */
        public Builder<E> m129add(E e) {
            super.add(e);
            return this;
        }

        public Builder<E> addAll(Iterable<? extends E> iterable) {
            super.addAll(iterable);
            return this;
        }
    }

    private static class ReverseImmutableList<E> extends ImmutableList<E> {
        private final transient ImmutableList<E> forwardList;

        ReverseImmutableList(ImmutableList<E> immutableList) {
            this.forwardList = immutableList;
        }

        private int reverseIndex(int i) {
            return (size() - 1) - i;
        }

        public boolean contains(Object obj) {
            return this.forwardList.contains(obj);
        }

        public E get(int i) {
            MoreObjects.checkElementIndex(i, size());
            return this.forwardList.get((size() - 1) - i);
        }

        public int indexOf(Object obj) {
            int lastIndexOf = this.forwardList.lastIndexOf(obj);
            if (lastIndexOf >= 0) {
                return reverseIndex(lastIndexOf);
            }
            return -1;
        }

        /* access modifiers changed from: package-private */
        public boolean isPartialView() {
            return this.forwardList.isPartialView();
        }

        public Iterator iterator() {
            return listIterator();
        }

        public int lastIndexOf(Object obj) {
            int indexOf = this.forwardList.indexOf(obj);
            if (indexOf >= 0) {
                return reverseIndex(indexOf);
            }
            return -1;
        }

        public ListIterator listIterator() {
            return listIterator(0);
        }

        public ImmutableList<E> reverse() {
            return this.forwardList;
        }

        public int size() {
            return this.forwardList.size();
        }

        public ListIterator listIterator(int i) {
            return new AbstractIndexedListIterator<E>(size(), i) {
                /* access modifiers changed from: protected */
                public E get(int i) {
                    return ImmutableList.this.get(i);
                }
            };
        }

        public ImmutableList<E> subList(int i, int i2) {
            MoreObjects.checkPositionIndexes(i, i2, size());
            return this.forwardList.subList(size() - i2, size() - i).reverse();
        }
    }

    static class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        final Object[] elements;

        SerializedForm(Object[] objArr) {
            this.elements = objArr;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return ImmutableList.copyOf((E[]) this.elements);
        }
    }

    class SubList extends ImmutableList<E> {
        final transient int length;
        final transient int offset;

        SubList(int i, int i2) {
            this.offset = i;
            this.length = i2;
        }

        public E get(int i) {
            MoreObjects.checkElementIndex(i, this.length);
            return ImmutableList.this.get(i + this.offset);
        }

        /* access modifiers changed from: package-private */
        public boolean isPartialView() {
            return true;
        }

        public Iterator iterator() {
            return listIterator();
        }

        public ListIterator listIterator() {
            return listIterator(0);
        }

        public int size() {
            return this.length;
        }

        public ListIterator listIterator(int i) {
            return new AbstractIndexedListIterator<E>(size(), i) {
                /* access modifiers changed from: protected */
                public E get(int i) {
                    return ImmutableList.this.get(i);
                }
            };
        }

        public ImmutableList<E> subList(int i, int i2) {
            MoreObjects.checkPositionIndexes(i, i2, this.length);
            ImmutableList immutableList = ImmutableList.this;
            int i3 = this.offset;
            return immutableList.subList(i + i3, i2 + i3);
        }
    }

    ImmutableList() {
    }

    static <E> ImmutableList<E> asImmutableList(Object[] objArr) {
        return asImmutableList(objArr, objArr.length);
    }

    public static <E> Builder<E> builder() {
        return new Builder<>();
    }

    private static <E> ImmutableList<E> construct(Object... objArr) {
        Collections2.checkElementsNotNull(objArr);
        return asImmutableList(objArr, objArr.length);
    }

    public static <E> ImmutableList<E> copyOf(Iterable<? extends E> iterable) {
        if (iterable == null) {
            throw new NullPointerException();
        } else if (iterable instanceof Collection) {
            return copyOf((Collection) iterable);
        } else {
            Iterator<? extends E> it = iterable.iterator();
            if (!it.hasNext()) {
                return RegularImmutableList.EMPTY;
            }
            Object next = it.next();
            if (!it.hasNext()) {
                return new SingletonImmutableList(next);
            }
            Builder builder = new Builder();
            builder.add((Object) next);
            builder.addAll(it);
            return builder.build();
        }
    }

    /* renamed from: of */
    public static <E> ImmutableList<E> m74of() {
        return RegularImmutableList.EMPTY;
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Use SerializedForm");
    }

    @Deprecated
    public final void add(int i, E e) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean addAll(int i, Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    public final ImmutableList<E> asList() {
        return this;
    }

    public boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    /* access modifiers changed from: package-private */
    public int copyIntoArray(Object[] objArr, int i) {
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
            int size = size();
            if (size == list.size()) {
                if (list instanceof RandomAccess) {
                    int i = 0;
                    while (i < size) {
                        if (R$style.equal(get(i), list.get(i))) {
                            i++;
                        }
                    }
                    return true;
                }
                Iterator it = list.iterator();
                for (Object equal : this) {
                    if (it.hasNext()) {
                        if (!R$style.equal(equal, it.next())) {
                        }
                    }
                }
                return !it.hasNext();
            }
        }
        return false;
    }

    public void forEach(Consumer<? super E> consumer) {
        if (consumer != null) {
            int size = size();
            for (int i = 0; i < size; i++) {
                consumer.accept(get(i));
            }
            return;
        }
        throw new NullPointerException();
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
        int size = size();
        for (int i = 0; i < size; i++) {
            if (obj.equals(get(i))) {
                return i;
            }
        }
        return -1;
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

    @Deprecated
    public final E remove(int i) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void replaceAll(UnaryOperator<E> unaryOperator) {
        throw new UnsupportedOperationException();
    }

    public ImmutableList<E> reverse() {
        return size() <= 1 ? this : new ReverseImmutableList(this);
    }

    @Deprecated
    public final E set(int i, E e) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void sort(Comparator<? super E> comparator) {
        throw new UnsupportedOperationException();
    }

    public Spliterator<E> spliterator() {
        return Collections2.indexed(size(), 1296, new IntFunction() {
            public final Object apply(int i) {
                return ImmutableList.this.get(i);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public ImmutableList<E> subListUnchecked(int i, int i2) {
        return new SubList(i, i2 - i);
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new SerializedForm(toArray());
    }

    static <E> ImmutableList<E> asImmutableList(Object[] objArr, int i) {
        if (i == 0) {
            return RegularImmutableList.EMPTY;
        }
        if (i == 1) {
            return new SingletonImmutableList(objArr[0]);
        }
        if (i < objArr.length) {
            objArr = Arrays.copyOf(objArr, i);
        }
        return new RegularImmutableList(objArr);
    }

    /* renamed from: of */
    public static <E> ImmutableList<E> m75of(E e) {
        return new SingletonImmutableList(e);
    }

    public UnmodifiableIterator<E> iterator() {
        return listIterator();
    }

    public ImmutableList<E> subList(int i, int i2) {
        MoreObjects.checkPositionIndexes(i, i2, size());
        int i3 = i2 - i;
        if (i3 == size()) {
            return this;
        }
        if (i3 == 0) {
            return RegularImmutableList.EMPTY;
        }
        if (i3 == 1) {
            return new SingletonImmutableList(get(i));
        }
        return subListUnchecked(i, i2);
    }

    /* renamed from: of */
    public static <E> ImmutableList<E> m76of(E e, E e2) {
        return construct(e, e2);
    }

    public UnmodifiableListIterator<E> listIterator() {
        return listIterator(0);
    }

    /* renamed from: of */
    public static <E> ImmutableList<E> m77of(E e, E e2, E e3) {
        return construct(e, e2, e3);
    }

    public UnmodifiableListIterator<E> listIterator(int i) {
        return new AbstractIndexedListIterator<E>(size(), i) {
            /* access modifiers changed from: protected */
            public E get(int i) {
                return ImmutableList.this.get(i);
            }
        };
    }

    /* renamed from: of */
    public static <E> ImmutableList<E> m78of(E e, E e2, E e3, E e4, E e5) {
        return construct(e, e2, e3, e4, e5);
    }

    /* renamed from: of */
    public static <E> ImmutableList<E> m79of(E e, E e2, E e3, E e4, E e5, E e6) {
        return construct(e, e2, e3, e4, e5, e6);
    }

    /* renamed from: of */
    public static <E> ImmutableList<E> m80of(E e, E e2, E e3, E e4, E e5, E e6, E e7) {
        return construct(e, e2, e3, e4, e5, e6, e7);
    }

    /* renamed from: of */
    public static <E> ImmutableList<E> m81of(E e, E e2, E e3, E e4, E e5, E e6, E e7, E e8) {
        return construct(e, e2, e3, e4, e5, e6, e7, e8);
    }

    public static <E> ImmutableList<E> copyOf(Collection<? extends E> collection) {
        if (!(collection instanceof ImmutableCollection)) {
            return construct(collection.toArray());
        }
        ImmutableList<E> asList = ((ImmutableCollection) collection).asList();
        return asList.isPartialView() ? asImmutableList(asList.toArray()) : asList;
    }

    public static <E> ImmutableList<E> copyOf(E[] eArr) {
        int length = eArr.length;
        if (length == 0) {
            return RegularImmutableList.EMPTY;
        }
        if (length != 1) {
            return construct((Object[]) eArr.clone());
        }
        return new SingletonImmutableList(eArr[0]);
    }
}
