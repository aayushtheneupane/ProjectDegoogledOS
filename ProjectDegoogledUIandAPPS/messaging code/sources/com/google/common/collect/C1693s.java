package com.google.common.collect;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.Map;

/* renamed from: com.google.common.collect.s */
class C1693s extends AbstractCollection {
    final /* synthetic */ C1698u this$0;

    private C1693s(C1698u uVar) {
        this.this$0 = uVar;
    }

    public void clear() {
        this.this$0.clear();
    }

    public boolean contains(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return this.this$0.mo8798a(entry.getKey(), entry.getValue());
    }

    public Iterator iterator() {
        return this.this$0.mo8800gl();
    }

    public boolean remove(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return this.this$0.remove(entry.getKey(), entry.getValue());
    }

    public int size() {
        return this.this$0.size();
    }
}
