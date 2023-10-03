package com.google.common.collect;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* renamed from: com.google.common.collect.I */
public abstract class C1570I extends C1576J implements Map {
    protected C1570I() {
    }

    public void clear() {
        mo8674ml().clear();
    }

    public boolean containsKey(Object obj) {
        return mo8674ml().containsKey(obj);
    }

    public boolean containsValue(Object obj) {
        return mo8674ml().containsValue(obj);
    }

    public Set entrySet() {
        return mo8674ml().entrySet();
    }

    public boolean equals(Object obj) {
        return obj == this || mo8674ml().equals(obj);
    }

    public Object get(Object obj) {
        return mo8674ml().get(obj);
    }

    public int hashCode() {
        return mo8674ml().hashCode();
    }

    public boolean isEmpty() {
        return mo8674ml().isEmpty();
    }

    public Set keySet() {
        return mo8674ml().keySet();
    }

    /* access modifiers changed from: protected */
    /* renamed from: ml */
    public abstract Map mo8674ml();

    /* access modifiers changed from: protected */
    /* renamed from: nl */
    public String mo8689nl() {
        return C1633Xa.m4544d(this);
    }

    public Object put(Object obj, Object obj2) {
        return mo8674ml().put(obj, obj2);
    }

    public void putAll(Map map) {
        mo8674ml().putAll(map);
    }

    public Object remove(Object obj) {
        return mo8674ml().remove(obj);
    }

    public int size() {
        return mo8674ml().size();
    }

    public Collection values() {
        return mo8674ml().values();
    }
}
