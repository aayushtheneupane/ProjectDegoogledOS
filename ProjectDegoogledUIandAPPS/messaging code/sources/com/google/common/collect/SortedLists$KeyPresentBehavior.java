package com.google.common.collect;

import java.util.Comparator;
import java.util.List;

public enum SortedLists$KeyPresentBehavior {
    ANY_PRESENT {
        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public int mo8992a(Comparator comparator, Object obj, List list, int i) {
            return i;
        }
    },
    LAST_PRESENT {
        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public int mo8992a(Comparator comparator, Object obj, List list, int i) {
            int size = list.size() - 1;
            while (i < size) {
                int i2 = ((i + size) + 1) >>> 1;
                if (comparator.compare(list.get(i2), obj) > 0) {
                    size = i2 - 1;
                } else {
                    i = i2;
                }
            }
            return i;
        }
    },
    FIRST_PRESENT {
        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public int mo8992a(Comparator comparator, Object obj, List list, int i) {
            int i2 = 0;
            while (i2 < i) {
                int i3 = (i2 + i) >>> 1;
                if (comparator.compare(list.get(i3), obj) < 0) {
                    i2 = i3 + 1;
                } else {
                    i = i3;
                }
            }
            return i2;
        }
    },
    FIRST_AFTER {
        /* renamed from: a */
        public int mo8992a(Comparator comparator, Object obj, List list, int i) {
            return SortedLists$KeyPresentBehavior.LAST_PRESENT.mo8992a(comparator, obj, list, i) + 1;
        }
    },
    LAST_BEFORE {
        /* renamed from: a */
        public int mo8992a(Comparator comparator, Object obj, List list, int i) {
            return SortedLists$KeyPresentBehavior.FIRST_PRESENT.mo8992a(comparator, obj, list, i) - 1;
        }
    };

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public abstract int mo8992a(Comparator comparator, Object obj, List list, int i);
}
