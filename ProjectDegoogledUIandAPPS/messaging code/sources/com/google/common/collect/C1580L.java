package com.google.common.collect;

/* renamed from: com.google.common.collect.L */
public final class C1580L extends C1614S {
    public ImmutableMap build() {
        int i = this.size;
        if (i == 0) {
            return ImmutableBiMap.m4190ql();
        }
        if (i != 1) {
            return new RegularImmutableBiMap(i, this.entries);
        }
        return ImmutableBiMap.m4189g(this.entries[0].getKey(), this.entries[0].getValue());
    }

    public C1614S put(Object obj, Object obj2) {
        super.put(obj, obj2);
        return this;
    }
}
