package com.google.common.collect;

public enum SortedLists$KeyAbsentBehavior {
    NEXT_LOWER {
        /* access modifiers changed from: package-private */
        /* renamed from: eb */
        public int mo8991eb(int i) {
            return i - 1;
        }
    },
    NEXT_HIGHER {
        /* renamed from: eb */
        public int mo8991eb(int i) {
            return i;
        }
    },
    INVERTED_INSERTION_INDEX {
        /* renamed from: eb */
        public int mo8991eb(int i) {
            return ~i;
        }
    };

    /* access modifiers changed from: package-private */
    /* renamed from: eb */
    public abstract int mo8991eb(int i);
}
