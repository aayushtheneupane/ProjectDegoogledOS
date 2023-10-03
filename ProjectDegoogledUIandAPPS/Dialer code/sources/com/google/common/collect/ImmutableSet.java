package com.google.common.collect;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;

public abstract class ImmutableSet<E> extends ImmutableCollection<E> implements Set<E> {
    private transient ImmutableList<E> asList;

    public static class Builder<E> extends ImmutableCollection.ArrayBasedBuilder<E> {
        public Builder() {
            super(4);
        }

        public ImmutableSet<E> build() {
            ImmutableSet<E> access$000 = ImmutableSet.construct(this.size, this.contents);
            this.size = access$000.size();
            return access$000;
        }

        public Builder<E> add(E e) {
            super.add(e);
            return this;
        }

        public Builder<E> add(E... eArr) {
            super.add(eArr);
            return this;
        }
    }

    static abstract class Indexed<E> extends ImmutableSet<E> {
        Indexed() {
        }

        /* access modifiers changed from: package-private */
        public ImmutableList<E> createAsList() {
            return new ImmutableAsList<E>() {
                public E get(int i) {
                    return Indexed.this.get(i);
                }

                /* access modifiers changed from: package-private */
                public Indexed<E> delegateCollection() {
                    return Indexed.this;
                }
            };
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

        /* access modifiers changed from: package-private */
        public abstract E get(int i);

        public Spliterator<E> spliterator() {
            return Collections2.indexed(size(), 1297, new IntFunction() {
                public final Object apply(int i) {
                    return ImmutableSet.Indexed.this.get(i);
                }
            });
        }

        public UnmodifiableIterator<E> iterator() {
            return asList().iterator();
        }
    }

    private static class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        final Object[] elements;

        SerializedForm(Object[] objArr) {
            this.elements = objArr;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return ImmutableSet.copyOf((E[]) this.elements);
        }
    }

    ImmutableSet() {
    }

    public static <E> Builder<E> builder() {
        return new Builder<>();
    }

    static int chooseTableSize(int i) {
        boolean z = true;
        if (i < 751619276) {
            int highestOneBit = Integer.highestOneBit(i - 1) << 1;
            while (((double) highestOneBit) * 0.7d < ((double) i)) {
                highestOneBit <<= 1;
            }
            return highestOneBit;
        }
        if (i >= 1073741824) {
            z = false;
        }
        MoreObjects.checkArgument(z, "collection too large");
        return 1073741824;
    }

    /* access modifiers changed from: private */
    public static <E> ImmutableSet<E> construct(int i, Object... objArr) {
        if (i == 0) {
            return RegularImmutableSet.EMPTY;
        }
        if (i == 1) {
            return new SingletonImmutableSet(objArr[0]);
        }
        int chooseTableSize = chooseTableSize(i);
        Object[] objArr2 = new Object[chooseTableSize];
        int i2 = chooseTableSize - 1;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < i; i5++) {
            Object obj = objArr[i5];
            Collections2.checkElementNotNull(obj, i5);
            int hashCode = obj.hashCode();
            int smear = Collections2.smear(hashCode);
            while (true) {
                int i6 = smear & i2;
                Object obj2 = objArr2[i6];
                if (obj2 == null) {
                    objArr[i3] = obj;
                    objArr2[i6] = obj;
                    i4 += hashCode;
                    i3++;
                    break;
                } else if (obj2.equals(obj)) {
                    break;
                } else {
                    smear++;
                }
            }
        }
        Arrays.fill(objArr, i3, i, (Object) null);
        if (i3 == 1) {
            return new SingletonImmutableSet(objArr[0], i4);
        }
        if (chooseTableSize != chooseTableSize(i3)) {
            return construct(i3, objArr);
        }
        if (i3 < objArr.length) {
            objArr = Arrays.copyOf(objArr, i3);
        }
        return new RegularImmutableSet(objArr, i4, objArr2, i2);
    }

    public static <E> ImmutableSet<E> copyOf(Collection<? extends E> collection) {
        if ((collection instanceof ImmutableSet) && !(collection instanceof SortedSet)) {
            ImmutableSet<E> immutableSet = (ImmutableSet) collection;
            if (!immutableSet.isPartialView()) {
                return immutableSet;
            }
        } else if (collection instanceof EnumSet) {
            return ImmutableEnumSet.asImmutable(EnumSet.copyOf((EnumSet) collection));
        }
        Object[] array = collection.toArray();
        return construct(array.length, array);
    }

    /* renamed from: of */
    public static <E> ImmutableSet<E> m86of() {
        return RegularImmutableSet.EMPTY;
    }

    public ImmutableList<E> asList() {
        ImmutableList<E> immutableList = this.asList;
        if (immutableList != null) {
            return immutableList;
        }
        ImmutableList<E> createAsList = createAsList();
        this.asList = createAsList;
        return createAsList;
    }

    /* access modifiers changed from: package-private */
    public ImmutableList<E> createAsList() {
        return new RegularImmutableAsList(this, toArray());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ImmutableSet) || !isHashCodeFast() || !((ImmutableSet) obj).isHashCodeFast() || hashCode() == obj.hashCode()) {
            return Collections2.equalsImpl(this, obj);
        }
        return false;
    }

    public int hashCode() {
        return Collections2.hashCodeImpl(this);
    }

    /* access modifiers changed from: package-private */
    public boolean isHashCodeFast() {
        return false;
    }

    public /* bridge */ /* synthetic */ Iterator iterator() {
        return iterator();
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new SerializedForm(toArray());
    }

    /* renamed from: of */
    public static <E> ImmutableSet<E> m87of(E e) {
        return new SingletonImmutableSet(e);
    }

    /* renamed from: of */
    public static <E> ImmutableSet<E> m88of(E e, E e2, E e3) {
        return construct(3, e, e2, e3);
    }

    /* renamed from: of */
    public static <E> ImmutableSet<E> m89of(E e, E e2, E e3, E e4, E e5) {
        return construct(5, e, e2, e3, e4, e5);
    }

    public static <E> ImmutableSet<E> copyOf(E[] eArr) {
        int length = eArr.length;
        if (length == 0) {
            return RegularImmutableSet.EMPTY;
        }
        if (length != 1) {
            return construct(eArr.length, (Object[]) eArr.clone());
        }
        return new SingletonImmutableSet(eArr[0]);
    }
}
