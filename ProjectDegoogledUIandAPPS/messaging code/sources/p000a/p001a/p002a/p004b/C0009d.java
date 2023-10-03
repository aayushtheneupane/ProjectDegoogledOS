package p000a.p001a.p002a.p004b;

import java.util.Map;

/* renamed from: a.a.a.b.d */
class C0009d implements Map.Entry {

    /* renamed from: Qn */
    C0009d f4Qn;
    final Object mKey;
    C0009d mNext;
    final Object mValue;

    C0009d(Object obj, Object obj2) {
        this.mKey = obj;
        this.mValue = obj2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C0009d)) {
            return false;
        }
        C0009d dVar = (C0009d) obj;
        if (!this.mKey.equals(dVar.mKey) || !this.mValue.equals(dVar.mValue)) {
            return false;
        }
        return true;
    }

    public Object getKey() {
        return this.mKey;
    }

    public Object getValue() {
        return this.mValue;
    }

    public int hashCode() {
        return this.mValue.hashCode() ^ this.mKey.hashCode();
    }

    public Object setValue(Object obj) {
        throw new UnsupportedOperationException("An entry modification is not supported");
    }

    public String toString() {
        return this.mKey + "=" + this.mValue;
    }
}
