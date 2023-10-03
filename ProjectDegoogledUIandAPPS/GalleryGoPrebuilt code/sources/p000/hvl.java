package p000;

import java.util.Set;

/* renamed from: hvl */
/* compiled from: PG */
public final class hvl extends hvn {

    /* renamed from: a */
    public final /* synthetic */ Set f13480a;

    /* renamed from: b */
    public final /* synthetic */ Set f13481b;

    public hvl(Set set, Set set2) {
        this.f13480a = set;
        this.f13481b = set2;
    }

    public final boolean contains(Object obj) {
        return this.f13480a.contains(obj) && !this.f13481b.contains(obj);
    }

    public final boolean isEmpty() {
        return this.f13481b.containsAll(this.f13480a);
    }

    /* renamed from: a */
    public final hvr iterator() {
        return new hvk(this);
    }

    public final int size() {
        int i = 0;
        for (Object contains : this.f13480a) {
            if (!this.f13481b.contains(contains)) {
                i++;
            }
        }
        return i;
    }
}
