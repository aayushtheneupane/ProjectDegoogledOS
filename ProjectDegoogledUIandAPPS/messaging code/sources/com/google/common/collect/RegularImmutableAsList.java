package com.google.common.collect;

class RegularImmutableAsList extends ImmutableAsList {
    private final ImmutableCollection delegate;
    private final ImmutableList delegateList;

    RegularImmutableAsList(ImmutableCollection immutableCollection, ImmutableList immutableList) {
        this.delegate = immutableCollection;
        this.delegateList = immutableList;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Ql */
    public ImmutableCollection mo8695Ql() {
        return this.delegate;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public int mo8652d(Object[] objArr, int i) {
        return this.delegateList.mo8652d(objArr, i);
    }

    public Object get(int i) {
        return this.delegateList.get(i);
    }

    public C1695sb listIterator(int i) {
        return this.delegateList.listIterator(i);
    }

    RegularImmutableAsList(ImmutableCollection immutableCollection, Object[] objArr) {
        this(immutableCollection, ImmutableList.m4201e(objArr, objArr.length));
    }
}
