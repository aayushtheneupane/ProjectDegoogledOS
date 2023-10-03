package p000;

import java.util.Map;
import p003j$.util.Map;

/* renamed from: ile */
/* compiled from: PG */
final class ile implements Map.Entry, Comparable, Map.Entry {

    /* renamed from: a */
    public final Comparable f14434a;

    /* renamed from: b */
    public Object f14435b;

    /* renamed from: c */
    private final /* synthetic */ ilh f14436c;

    public ile(ilh ilh, Comparable comparable, Object obj) {
        this.f14436c = ilh;
        this.f14434a = comparable;
        this.f14435b = obj;
    }

    public final /* bridge */ /* synthetic */ Object getKey() {
        return this.f14434a;
    }

    public final Object getValue() {
        return this.f14435b;
    }

    public ile(ilh ilh, Map.Entry entry) {
        this(ilh, (Comparable) entry.getKey(), entry.getValue());
    }

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return this.f14434a.compareTo(((ile) obj).f14434a);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            return m13957a(this.f14434a, entry.getKey()) && m13957a(this.f14435b, entry.getValue());
        }
    }

    /* renamed from: a */
    private static final boolean m13957a(Object obj, Object obj2) {
        if (obj != null) {
            return obj.equals(obj2);
        }
        return obj2 == null;
    }

    public final int hashCode() {
        Comparable comparable = this.f14434a;
        int i = 0;
        int hashCode = comparable != null ? comparable.hashCode() : 0;
        Object obj = this.f14435b;
        if (obj != null) {
            i = obj.hashCode();
        }
        return hashCode ^ i;
    }

    public final Object setValue(Object obj) {
        this.f14436c.mo8921c();
        Object obj2 = this.f14435b;
        this.f14435b = obj;
        return obj2;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f14434a);
        String valueOf2 = String.valueOf(this.f14435b);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(valueOf2).length());
        sb.append(valueOf);
        sb.append("=");
        sb.append(valueOf2);
        return sb.toString();
    }
}
