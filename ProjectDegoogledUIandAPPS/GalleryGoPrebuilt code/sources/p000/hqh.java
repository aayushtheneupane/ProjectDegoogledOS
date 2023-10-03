package p000;

import java.util.Iterator;

/* renamed from: hqh */
/* compiled from: PG */
final class hqh implements Iterable {

    /* renamed from: a */
    private final /* synthetic */ CharSequence f13255a;

    /* renamed from: b */
    private final /* synthetic */ hqj f13256b;

    public hqh(hqj hqj, CharSequence charSequence) {
        this.f13256b = hqj;
        this.f13255a = charSequence;
    }

    public final Iterator iterator() {
        return this.f13256b.mo7680b(this.f13255a);
    }

    public final String toString() {
        hpv a = hpv.m11887a(", ");
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        StringBuilder a2 = a.mo7670a(sb, iterator());
        a2.append(']');
        return a2.toString();
    }
}
