package p000;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/* renamed from: guq */
/* compiled from: PG */
final class guq implements gue {

    /* renamed from: a */
    public final Set f12082a;

    /* renamed from: b */
    private final Set f12083b;

    /* renamed from: c */
    private final int f12084c;

    /* renamed from: d */
    private String f12085d;

    public guq(Object... objArr) {
        ife.m12876b(r0 > 0, (Object) "Cannot create a parent key without child keys");
        HashSet hashSet = new HashSet();
        this.f12083b = hashSet;
        Collections.addAll(hashSet, objArr);
        this.f12082a = new HashSet();
        for (guq guq : objArr) {
            if (guq instanceof guq) {
                this.f12082a.addAll(guq.f12082a);
            } else {
                this.f12082a.add(guq);
            }
        }
        this.f12082a.add(this);
        this.f12084c = this.f12082a.hashCode();
    }

    public final int hashCode() {
        return this.f12084c;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof guq) {
            return this.f12083b.equals(((guq) obj).f12083b);
        }
        return false;
    }

    public final String toString() {
        if (this.f12085d == null) {
            TreeSet treeSet = new TreeSet();
            for (Object obj : this.f12083b) {
                treeSet.add(obj.toString());
            }
            this.f12085d = String.format("MergedKey[%s]", new Object[]{hpv.m11887a(", ").mo7669a((Iterable) treeSet)});
        }
        return this.f12085d;
    }
}
