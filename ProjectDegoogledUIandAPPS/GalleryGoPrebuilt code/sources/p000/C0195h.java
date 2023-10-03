package p000;

import java.util.Map;
import p003j$.util.Map;

/* renamed from: h */
/* compiled from: PG */
public final class C0195h implements Map.Entry, Map.Entry {

    /* renamed from: a */
    public final Object f12388a;

    /* renamed from: b */
    public final Object f12389b;

    /* renamed from: c */
    public C0195h f12390c;

    /* renamed from: d */
    public C0195h f12391d;

    public C0195h(Object obj, Object obj2) {
        this.f12388a = obj;
        this.f12389b = obj2;
    }

    public final Object getKey() {
        return this.f12388a;
    }

    public final Object getValue() {
        return this.f12389b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C0195h) {
            C0195h hVar = (C0195h) obj;
            return this.f12388a.equals(hVar.f12388a) && this.f12389b.equals(hVar.f12389b);
        }
    }

    public final int hashCode() {
        return this.f12388a.hashCode() ^ this.f12389b.hashCode();
    }

    public final Object setValue(Object obj) {
        throw new UnsupportedOperationException("An entry modification is not supported");
    }

    public final String toString() {
        return this.f12388a + "=" + this.f12389b;
    }
}
