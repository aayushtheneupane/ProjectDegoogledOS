package com.google.common.collect;

final class EmptyImmutableBiMap extends ImmutableBiMap {
    static final EmptyImmutableBiMap INSTANCE = new EmptyImmutableBiMap();

    private EmptyImmutableBiMap() {
    }

    public Object get(Object obj) {
        return null;
    }

    public ImmutableBiMap inverse() {
        return this;
    }

    public boolean isEmpty() {
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: ol */
    public ImmutableSet mo8644ol() {
        throw new AssertionError("should never be called");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: pl */
    public boolean mo8645pl() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public Object readResolve() {
        return INSTANCE;
    }

    public int size() {
        return 0;
    }

    public ImmutableSet entrySet() {
        return ImmutableSet.m4235ql();
    }

    public ImmutableSet keySet() {
        return ImmutableSet.m4235ql();
    }
}
