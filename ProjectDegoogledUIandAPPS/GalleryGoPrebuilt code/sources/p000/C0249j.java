package p000;

import java.util.Iterator;
import p003j$.util.Iterator$$CC;
import p003j$.util.function.Consumer;

/* renamed from: j */
/* compiled from: PG */
abstract class C0249j implements Iterator, p003j$.util.Iterator, C0276k {

    /* renamed from: a */
    private C0195h f15064a;

    /* renamed from: b */
    private C0195h f15065b;

    public C0249j(C0195h hVar, C0195h hVar2) {
        this.f15064a = hVar2;
        this.f15065b = hVar;
    }

    /* renamed from: a */
    public abstract C0195h mo5425a(C0195h hVar);

    /* renamed from: b */
    public abstract C0195h mo5426b(C0195h hVar);

    public final void forEachRemaining(Consumer consumer) {
        Iterator$$CC.forEachRemaining$$dflt$$(this, consumer);
    }

    public final boolean hasNext() {
        return this.f15065b != null;
    }

    public final void remove() {
        Iterator$$CC.remove$$dflt$$(this);
    }

    public final /* bridge */ /* synthetic */ Object next() {
        C0195h hVar = this.f15065b;
        this.f15065b = m14447a();
        return hVar;
    }

    /* renamed from: a */
    private final C0195h m14447a() {
        C0195h hVar = this.f15065b;
        C0195h hVar2 = this.f15064a;
        if (hVar == hVar2 || hVar2 == null) {
            return null;
        }
        return mo5425a(hVar);
    }

    /* renamed from: c */
    public final void mo8313c(C0195h hVar) {
        if (this.f15064a == hVar && hVar == this.f15065b) {
            this.f15065b = null;
            this.f15064a = null;
        }
        C0195h hVar2 = this.f15064a;
        if (hVar2 == hVar) {
            this.f15064a = mo5426b(hVar2);
        }
        if (this.f15065b == hVar) {
            this.f15065b = m14447a();
        }
    }
}
