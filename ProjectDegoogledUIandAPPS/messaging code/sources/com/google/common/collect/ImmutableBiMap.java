package com.google.common.collect;

import com.google.common.collect.ImmutableMap;

public abstract class ImmutableBiMap extends ImmutableMap implements C1708z {

    class SerializedForm extends ImmutableMap.SerializedForm {
        private static final long serialVersionUID = 0;

        SerializedForm(ImmutableBiMap immutableBiMap) {
            super(immutableBiMap);
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return mo8745a(new C1580L());
        }
    }

    ImmutableBiMap() {
    }

    /* renamed from: g */
    public static ImmutableBiMap m4189g(Object obj, Object obj2) {
        return new SingletonImmutableBiMap(obj, obj2);
    }

    /* renamed from: ql */
    public static ImmutableBiMap m4190ql() {
        return EmptyImmutableBiMap.INSTANCE;
    }

    public abstract ImmutableBiMap inverse();

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new SerializedForm(this);
    }

    public ImmutableSet values() {
        return inverse().keySet();
    }
}
