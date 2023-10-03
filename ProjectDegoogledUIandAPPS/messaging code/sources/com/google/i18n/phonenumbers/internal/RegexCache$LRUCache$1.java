package com.google.i18n.phonenumbers.internal;

import java.util.LinkedHashMap;
import java.util.Map;

class RegexCache$LRUCache$1 extends LinkedHashMap {
    final /* synthetic */ C1736b this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RegexCache$LRUCache$1(C1736b bVar, int i, float f, boolean z) {
        super(i, f, z);
        this.this$0 = bVar;
    }

    /* access modifiers changed from: protected */
    public boolean removeEldestEntry(Map.Entry entry) {
        return size() > this.this$0.size;
    }
}
