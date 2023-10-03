package com.google.common.collect;

import com.google.common.base.Equivalence;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.MapMakerInternalMap;
import java.util.concurrent.ConcurrentMap;

public final class Interners {
    private Interners() {
    }

    public static <E> Interner<E> newStrongInterner() {
        final ConcurrentMap makeMap = new MapMaker().makeMap();
        return new Interner<E>() {
            public E intern(E e) {
                ConcurrentMap concurrentMap = makeMap;
                Preconditions.checkNotNull(e);
                E putIfAbsent = concurrentMap.putIfAbsent(e, e);
                return putIfAbsent == null ? e : putIfAbsent;
            }
        };
    }

    public static <E> Interner<E> newWeakInterner() {
        return new WeakInterner();
    }

    private static class WeakInterner<E> implements Interner<E> {
        private final MapMakerInternalMap<E, Dummy> map;

        private enum Dummy {
            VALUE
        }

        private WeakInterner() {
            this.map = new MapMaker().weakKeys().keyEquivalence((Equivalence) Equivalence.equals()).makeCustomMap();
        }

        public E intern(E e) {
            E key;
            do {
                MapMakerInternalMap.ReferenceEntry<E, Dummy> entry = this.map.getEntry(e);
                if (entry != null && (key = entry.getKey()) != null) {
                    return key;
                }
            } while (this.map.putIfAbsent(e, Dummy.VALUE) != null);
            return e;
        }
    }

    public static <E> Function<E, E> asFunction(Interner<E> interner) {
        Preconditions.checkNotNull(interner);
        return new InternerFunction(interner);
    }

    private static class InternerFunction<E> implements Function<E, E> {
        private final Interner<E> interner;

        public InternerFunction(Interner<E> interner2) {
            this.interner = interner2;
        }

        public E apply(E e) {
            return this.interner.intern(e);
        }

        public int hashCode() {
            return this.interner.hashCode();
        }

        public boolean equals(Object obj) {
            if (obj instanceof InternerFunction) {
                return this.interner.equals(((InternerFunction) obj).interner);
            }
            return false;
        }
    }
}
