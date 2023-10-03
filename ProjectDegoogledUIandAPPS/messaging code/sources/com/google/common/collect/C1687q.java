package com.google.common.collect;

import android.support.p016v4.media.session.C0107q;
import java.util.Map;

/* renamed from: com.google.common.collect.q */
abstract class C1687q implements Map.Entry {
    C1687q() {
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (!C0107q.m131b(getKey(), entry.getKey()) || !C0107q.m131b(getValue(), entry.getValue())) {
            return false;
        }
        return true;
    }

    public abstract Object getKey();

    public abstract Object getValue();

    public int hashCode() {
        int i;
        Object key = getKey();
        Object value = getValue();
        int i2 = 0;
        if (key == null) {
            i = 0;
        } else {
            i = key.hashCode();
        }
        if (value != null) {
            i2 = value.hashCode();
        }
        return i ^ i2;
    }

    public String toString() {
        return getKey() + "=" + getValue();
    }
}
