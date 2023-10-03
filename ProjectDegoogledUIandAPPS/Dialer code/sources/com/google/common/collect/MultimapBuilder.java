package com.google.common.collect;

import com.google.common.base.Supplier;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class MultimapBuilder<K0, V0> {

    private static final class LinkedHashSetSupplier<V> implements Supplier<Set<V>>, Serializable {
        private final int expectedValuesPerKey;

        LinkedHashSetSupplier(int i) {
            Collections2.checkNonnegative(i, "expectedValuesPerKey");
            this.expectedValuesPerKey = i;
        }

        public Set<V> get() {
            return new LinkedHashSet(Collections2.capacity(this.expectedValuesPerKey));
        }
    }

    public static abstract class MultimapBuilderWithKeys<K0> {
        MultimapBuilderWithKeys() {
        }

        public SetMultimapBuilder<K0, Object> linkedHashSetValues() {
            Collections2.checkNonnegative(2, "expectedValuesPerKey");
            return new SetMultimapBuilder<K0, Object>(2) {
                public <K extends K0, V> SetMultimap<K, V> build() {
                    return new Multimaps$CustomSetMultimap(new HashMap(Collections2.capacity(8)), new LinkedHashSetSupplier(2));
                }
            };
        }
    }

    public static abstract class SetMultimapBuilder<K0, V0> extends MultimapBuilder<K0, V0> {
        SetMultimapBuilder() {
            super((C08801) null);
        }

        public abstract <K extends K0, V extends V0> SetMultimap<K, V> build();
    }

    /* synthetic */ MultimapBuilder(C08801 r1) {
    }

    public static MultimapBuilderWithKeys<Object> hashKeys() {
        Collections2.checkNonnegative(8, "expectedKeys");
        return new MultimapBuilderWithKeys<Object>(8) {
        };
    }
}
