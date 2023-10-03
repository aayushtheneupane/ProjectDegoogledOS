package com.google.i18n.phonenumbers.internal;

import java.util.LinkedHashMap;

/* renamed from: com.google.i18n.phonenumbers.internal.b */
class C1736b {
    private LinkedHashMap map;
    /* access modifiers changed from: private */
    public int size;

    public C1736b(int i) {
        this.size = i;
        this.map = new RegexCache$LRUCache$1(this, ((i * 4) / 3) + 1, 0.75f, true);
    }

    public synchronized Object get(Object obj) {
        return this.map.get(obj);
    }

    public synchronized void put(Object obj, Object obj2) {
        this.map.put(obj, obj2);
    }
}
