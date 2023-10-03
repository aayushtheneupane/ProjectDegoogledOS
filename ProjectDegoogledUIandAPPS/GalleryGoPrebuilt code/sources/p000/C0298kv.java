package p000;

import java.util.Iterator;
import java.util.NoSuchElementException;
import p003j$.util.Iterator$$CC;
import p003j$.util.function.Consumer;

/* renamed from: kv */
/* compiled from: PG */
final class C0298kv implements Iterator, p003j$.util.Iterator {

    /* renamed from: a */
    private final int f15166a;

    /* renamed from: b */
    private int f15167b;

    /* renamed from: c */
    private int f15168c;

    /* renamed from: d */
    private boolean f15169d = false;

    /* renamed from: e */
    private final /* synthetic */ C0304la f15170e;

    public C0298kv(C0304la laVar, int i) {
        this.f15170e = laVar;
        this.f15166a = i;
        this.f15167b = laVar.mo9184a();
    }

    public final void forEachRemaining(Consumer consumer) {
        Iterator$$CC.forEachRemaining$$dflt$$(this, consumer);
    }

    public final boolean hasNext() {
        return this.f15168c < this.f15167b;
    }

    public final Object next() {
        if (hasNext()) {
            Object a = this.f15170e.mo9186a(this.f15168c, this.f15166a);
            this.f15168c++;
            this.f15169d = true;
            return a;
        }
        throw new NoSuchElementException();
    }

    public final void remove() {
        if (this.f15169d) {
            int i = this.f15168c - 1;
            this.f15168c = i;
            this.f15167b--;
            this.f15169d = false;
            this.f15170e.mo9188a(i);
            return;
        }
        throw new IllegalStateException();
    }
}
