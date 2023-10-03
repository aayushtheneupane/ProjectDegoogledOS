package p000;

import java.util.Set;

/* renamed from: hvj */
/* compiled from: PG */
public final class hvj extends hvn {

    /* renamed from: a */
    public final /* synthetic */ Set f13476a;

    /* renamed from: b */
    public final /* synthetic */ Set f13477b;

    public hvj(Set set, Set set2) {
        this.f13476a = set;
        this.f13477b = set2;
    }

    public final boolean contains(Object obj) {
        return this.f13476a.contains(obj) || this.f13477b.contains(obj);
    }

    public final boolean isEmpty() {
        return this.f13476a.isEmpty() && this.f13477b.isEmpty();
    }

    /* renamed from: a */
    public final hvr iterator() {
        return new hvi(this);
    }

    public final int size() {
        int size = this.f13476a.size();
        hvr a = ((hvo) this.f13477b).iterator();
        while (a.hasNext()) {
            if (!this.f13476a.contains(a.next())) {
                size++;
            }
        }
        return size;
    }
}
