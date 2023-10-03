package com.google.common.collect;

final class SingletonImmutableBiMap extends ImmutableBiMap {

    /* renamed from: tN */
    transient ImmutableBiMap f2484tN;

    /* renamed from: uN */
    final transient Object f2485uN;

    /* renamed from: vN */
    final transient Object f2486vN;

    SingletonImmutableBiMap(Object obj, Object obj2) {
        C1630W.m4537h(obj, obj2);
        this.f2485uN = obj;
        this.f2486vN = obj2;
    }

    public boolean containsKey(Object obj) {
        return this.f2485uN.equals(obj);
    }

    public boolean containsValue(Object obj) {
        return this.f2486vN.equals(obj);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: fl */
    public ImmutableSet mo8715fl() {
        return ImmutableSet.m4234of(this.f2485uN);
    }

    public Object get(Object obj) {
        if (this.f2485uN.equals(obj)) {
            return this.f2486vN;
        }
        return null;
    }

    public ImmutableBiMap inverse() {
        ImmutableBiMap immutableBiMap = this.f2484tN;
        if (immutableBiMap != null) {
            return immutableBiMap;
        }
        SingletonImmutableBiMap singletonImmutableBiMap = new SingletonImmutableBiMap(this.f2486vN, this.f2485uN, this);
        this.f2484tN = singletonImmutableBiMap;
        return singletonImmutableBiMap;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: ol */
    public ImmutableSet mo8644ol() {
        return ImmutableSet.m4234of(C1633Xa.m4547i(this.f2485uN, this.f2486vN));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: pl */
    public boolean mo8645pl() {
        return false;
    }

    public int size() {
        return 1;
    }

    private SingletonImmutableBiMap(Object obj, Object obj2, ImmutableBiMap immutableBiMap) {
        this.f2485uN = obj;
        this.f2486vN = obj2;
        this.f2484tN = immutableBiMap;
    }
}
