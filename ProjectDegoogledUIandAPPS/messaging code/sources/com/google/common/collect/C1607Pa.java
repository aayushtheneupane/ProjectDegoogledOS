package com.google.common.collect;

import java.util.Map;

/* renamed from: com.google.common.collect.Pa */
final class C1607Pa extends C1687q {
    final Object key;
    final /* synthetic */ MapMakerInternalMap this$0;
    Object value;

    C1607Pa(MapMakerInternalMap mapMakerInternalMap, Object obj, Object obj2) {
        this.this$0 = mapMakerInternalMap;
        this.key = obj;
        this.value = obj2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (!this.key.equals(entry.getKey()) || !this.value.equals(entry.getValue())) {
            return false;
        }
        return true;
    }

    public Object getKey() {
        return this.key;
    }

    public Object getValue() {
        return this.value;
    }

    public int hashCode() {
        return this.value.hashCode() ^ this.key.hashCode();
    }

    public Object setValue(Object obj) {
        Object put = this.this$0.put(this.key, obj);
        this.value = obj;
        return put;
    }
}
