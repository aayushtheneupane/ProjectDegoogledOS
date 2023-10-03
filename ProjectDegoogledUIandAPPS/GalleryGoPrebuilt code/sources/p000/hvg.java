package p000;

import java.io.Serializable;
import java.util.Iterator;

/* renamed from: hvg */
/* compiled from: PG */
final class hvg extends huv implements Serializable {
    public static final long serialVersionUID = 0;

    /* renamed from: a */
    private final huv f13471a;

    public hvg(huv huv) {
        this.f13471a = (huv) ife.m12898e((Object) huv);
    }

    /* renamed from: a */
    public final huv mo8116a() {
        return this.f13471a;
    }

    public final int compare(Object obj, Object obj2) {
        return this.f13471a.compare(obj2, obj);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof hvg) {
            return this.f13471a.equals(((hvg) obj).f13471a);
        }
        return false;
    }

    public final int hashCode() {
        return -this.f13471a.hashCode();
    }

    /* renamed from: b */
    public final Object mo8120b(Iterable iterable) {
        return this.f13471a.mo8117a(iterable);
    }

    /* renamed from: b */
    public final Object mo8121b(Object obj, Object obj2) {
        return this.f13471a.mo8118a(obj, obj2);
    }

    /* renamed from: b */
    public final Object mo8122b(Iterator it) {
        return this.f13471a.mo8119a(it);
    }

    /* renamed from: a */
    public final Object mo8117a(Iterable iterable) {
        return this.f13471a.mo8120b(iterable);
    }

    /* renamed from: a */
    public final Object mo8118a(Object obj, Object obj2) {
        return this.f13471a.mo8121b(obj, obj2);
    }

    /* renamed from: a */
    public final Object mo8119a(Iterator it) {
        return this.f13471a.mo8122b(it);
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f13471a);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 10);
        sb.append(valueOf);
        sb.append(".reverse()");
        return sb.toString();
    }
}
