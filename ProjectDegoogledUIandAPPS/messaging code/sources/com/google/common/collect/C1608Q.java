package com.google.common.collect;

/* renamed from: com.google.common.collect.Q */
public final class C1608Q extends C1582M {
    public C1608Q() {
        super(4);
    }

    public C1602N add(Object obj) {
        super.add(obj);
        return this;
    }

    public ImmutableList build() {
        return ImmutableList.m4201e(this.contents, this.size);
    }

    /* renamed from: add  reason: collision with other method in class */
    public C1608Q m4706add(Object obj) {
        super.add(obj);
        return this;
    }
}
