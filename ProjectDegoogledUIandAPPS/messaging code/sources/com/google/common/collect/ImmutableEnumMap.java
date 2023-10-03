package com.google.common.collect;

import com.google.common.base.C1508E;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;

final class ImmutableEnumMap extends ImmutableMap {
    /* access modifiers changed from: private */
    public final transient EnumMap delegate;

    class EnumSerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        final EnumMap delegate;

        EnumSerializedForm(EnumMap enumMap) {
            this.delegate = enumMap;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return new ImmutableEnumMap(this.delegate, (C15721) null);
        }
    }

    private ImmutableEnumMap(EnumMap enumMap) {
        this.delegate = enumMap;
        C1508E.checkArgument(!enumMap.isEmpty());
    }

    public boolean containsKey(Object obj) {
        return this.delegate.containsKey(obj);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: fl */
    public ImmutableSet mo8715fl() {
        return new ImmutableSet() {
            public boolean contains(Object obj) {
                return ImmutableEnumMap.this.delegate.containsKey(obj);
            }

            /* access modifiers changed from: package-private */
            /* renamed from: pl */
            public boolean mo8636pl() {
                return true;
            }

            public int size() {
                return ImmutableEnumMap.this.size();
            }

            public C1692rb iterator() {
                return C1652ea.m4582f(ImmutableEnumMap.this.delegate.keySet().iterator());
            }
        };
    }

    public Object get(Object obj) {
        return this.delegate.get(obj);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: ol */
    public ImmutableSet mo8644ol() {
        return new ImmutableMapEntrySet() {
            /* access modifiers changed from: package-private */
            public ImmutableMap map() {
                return ImmutableEnumMap.this;
            }

            public C1692rb iterator() {
                return new C1604O(this);
            }
        };
    }

    /* access modifiers changed from: package-private */
    /* renamed from: pl */
    public boolean mo8645pl() {
        return false;
    }

    public int size() {
        return this.delegate.size();
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new EnumSerializedForm(this.delegate);
    }

    /* renamed from: a */
    static ImmutableMap m4195a(EnumMap enumMap) {
        int size = enumMap.size();
        if (size == 0) {
            return ImmutableBiMap.m4190ql();
        }
        if (size != 1) {
            return new ImmutableEnumMap(enumMap);
        }
        Map.Entry entry = (Map.Entry) C1652ea.m4576b(enumMap.entrySet().iterator());
        return ImmutableBiMap.m4189g(entry.getKey(), entry.getValue());
    }

    /* synthetic */ ImmutableEnumMap(EnumMap enumMap, C15721 r2) {
        this.delegate = enumMap;
        C1508E.checkArgument(!enumMap.isEmpty());
    }
}
