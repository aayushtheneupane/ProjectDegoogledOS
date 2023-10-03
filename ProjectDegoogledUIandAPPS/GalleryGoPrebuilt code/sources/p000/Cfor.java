package p000;

import java.util.AbstractList;
import java.util.Comparator;
import p003j$.util.Collection;
import p003j$.util.Collection$$CC;
import p003j$.util.List;
import p003j$.util.List$$CC;
import p003j$.util.Spliterator;
import p003j$.util.function.Predicate;
import p003j$.util.function.UnaryOperator;
import p003j$.util.stream.Stream;

/* renamed from: for  reason: invalid class name */
/* compiled from: PG */
final class Cfor extends AbstractList implements List, Collection {

    /* renamed from: a */
    public static final Cfor f10163a = new Cfor();

    public final boolean add(Object obj) {
        return true;
    }

    public final Object get(int i) {
        return null;
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

    public final int size() {
        return 0;
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
}
