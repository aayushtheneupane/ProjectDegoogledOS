package com.google.common.collect;

public enum BoundType {
    OPEN {
        /* access modifiers changed from: package-private */
        public BoundType flip() {
            return BoundType.CLOSED;
        }
    },
    CLOSED {
        /* access modifiers changed from: package-private */
        public BoundType flip() {
            return BoundType.OPEN;
        }
    };

    /* access modifiers changed from: package-private */
    public abstract BoundType flip();

    static BoundType forBoolean(boolean z) {
        return z ? CLOSED : OPEN;
    }
}
