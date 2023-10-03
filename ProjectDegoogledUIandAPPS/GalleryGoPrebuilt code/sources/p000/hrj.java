package p000;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.Set;
import p003j$.util.Collection;
import p003j$.util.Collection$$CC;
import p003j$.util.Spliterator;
import p003j$.util.function.Predicate;
import p003j$.util.stream.Stream;

/* renamed from: hrj */
/* compiled from: PG */
abstract class hrj extends AbstractCollection implements Collection, huo {

    /* renamed from: a */
    private transient Set f13306a;

    /* renamed from: b */
    private transient Set f13307b;

    /* renamed from: a */
    public int mo7770a(Object obj, int i) {
        throw null;
    }

    /* renamed from: a */
    public abstract Iterator mo7771a();

    /* renamed from: b */
    public int mo7772b(Object obj, int i) {
        throw null;
    }

    /* renamed from: b */
    public abstract Iterator mo7773b();

    /* renamed from: c */
    public abstract int mo7774c();

    /* renamed from: c */
    public boolean mo7775c(Object obj, int i) {
        throw null;
    }

    public final Stream parallelStream() {
        return Collection$$CC.parallelStream$$dflt$$(this);
    }

    public final boolean removeIf(Predicate predicate) {
        return Collection$$CC.removeIf$$dflt$$(this, predicate);
    }

    public final Spliterator spliterator() {
        return Collection$$CC.spliterator$$dflt$$(this);
    }

    public final Stream stream() {
        return Collection$$CC.stream$$dflt$$(this);
    }

    public final boolean add(Object obj) {
        mo7770a(obj, 1);
        return true;
    }

    public final boolean addAll(java.util.Collection collection) {
        ife.m12898e((Object) this);
        ife.m12898e((Object) collection);
        if (collection instanceof huo) {
            huo huo = (huo) collection;
            if (huo instanceof hrg) {
                hrg hrg = (hrg) huo;
                if (!hrg.isEmpty()) {
                    ife.m12898e((Object) this);
                    for (int a = hrg.f13302a.mo8100a(); a >= 0; a = hrg.f13302a.mo8101a(a)) {
                        mo7770a(hrg.f13302a.mo8107b(a), hrg.f13302a.mo8109c(a));
                    }
                    return true;
                }
            } else if (!huo.isEmpty()) {
                for (hun hun : huo.mo7796f()) {
                    mo7770a(hun.mo8079a(), hun.mo8080b());
                }
                return true;
            }
        } else if (!collection.isEmpty()) {
            return ife.m12855a((java.util.Collection) this, collection.iterator());
        }
        return false;
    }

    public final boolean contains(Object obj) {
        return mo7769a(obj) > 0;
    }

    /* renamed from: e */
    public final Set mo7794e() {
        Set set = this.f13306a;
        if (set != null) {
            return set;
        }
        huq huq = new huq(this);
        this.f13306a = huq;
        return huq;
    }

    /* renamed from: f */
    public final Set mo7796f() {
        Set set = this.f13307b;
        if (set != null) {
            return set;
        }
        hur hur = new hur(this);
        this.f13307b = hur;
        return hur;
    }

    public final boolean equals(Object obj) {
        return ife.m12853a((huo) this, obj);
    }

    public final int hashCode() {
        return mo7796f().hashCode();
    }

    public final boolean isEmpty() {
        return mo7796f().isEmpty();
    }

    public final boolean remove(Object obj) {
        return mo7772b(obj, 1) > 0;
    }

    public final boolean removeAll(java.util.Collection collection) {
        if (collection instanceof huo) {
            collection = ((huo) collection).mo7794e();
        }
        return mo7794e().removeAll(collection);
    }

    public final boolean retainAll(java.util.Collection collection) {
        ife.m12898e((Object) collection);
        if (collection instanceof huo) {
            collection = ((huo) collection).mo7794e();
        }
        return mo7794e().retainAll(collection);
    }

    public final String toString() {
        return mo7796f().toString();
    }
}
