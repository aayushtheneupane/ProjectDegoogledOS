package p000;

import java.util.AbstractList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.RandomAccess;
import p003j$.util.Collection;
import p003j$.util.Collection$$CC;
import p003j$.util.List;
import p003j$.util.List$$CC;
import p003j$.util.Spliterator;
import p003j$.util.function.Predicate;
import p003j$.util.function.UnaryOperator;
import p003j$.util.stream.Stream;

/* renamed from: ilp */
/* compiled from: PG */
public final class ilp extends AbstractList implements RandomAccess, List, Collection, ijo {

    /* renamed from: a */
    public final ijo f14460a;

    public ilp(ijo ijo) {
        this.f14460a = ijo;
    }

    /* renamed from: e */
    public final ijo mo8821e() {
        return this;
    }

    public final Stream parallelStream() {
        return Collection$$CC.parallelStream$$dflt$$(this);
    }

    public final boolean removeIf(Predicate predicate) {
        return Collection$$CC.removeIf$$dflt$$(this, predicate);
    }

    public final void replaceAll(UnaryOperator unaryOperator) {
        List$$CC.replaceAll$$dflt$$(this, unaryOperator);
    }

    public final void sort(Comparator comparator) {
        List$$CC.sort$$dflt$$(this, comparator);
    }

    public final Spliterator spliterator() {
        return List$$CC.spliterator$$dflt$$(this);
    }

    public final Stream stream() {
        return Collection$$CC.stream$$dflt$$(this);
    }

    /* renamed from: a */
    public final void mo8817a(ihw ihw) {
        throw new UnsupportedOperationException();
    }

    public final /* bridge */ /* synthetic */ Object get(int i) {
        return ((ijn) this.f14460a).get(i);
    }

    /* renamed from: c */
    public final Object mo8819c(int i) {
        return this.f14460a.mo8819c(i);
    }

    /* renamed from: d */
    public final java.util.List mo8820d() {
        return this.f14460a.mo8820d();
    }

    public final Iterator iterator() {
        return new ilo(this);
    }

    public final ListIterator listIterator(int i) {
        return new iln(this, i);
    }

    public final int size() {
        return this.f14460a.size();
    }
}
