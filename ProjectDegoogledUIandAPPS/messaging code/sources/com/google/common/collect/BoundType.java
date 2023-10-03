package com.google.common.collect;

public enum BoundType {
    OPEN {
    },
    CLOSED {
    };

    /* renamed from: pa */
    static BoundType m4074pa(boolean z) {
        return z ? CLOSED : OPEN;
    }
}
