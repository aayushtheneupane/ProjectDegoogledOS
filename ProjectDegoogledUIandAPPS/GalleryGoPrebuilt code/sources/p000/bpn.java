package p000;

import p003j$.util.function.Consumer;

/* renamed from: bpn */
/* compiled from: PG */
public class bpn implements gry {

    /* renamed from: a */
    private final Consumer f3315a;

    /* renamed from: b */
    private final Consumer f3316b;

    public bpn(Consumer consumer, Consumer consumer2) {
        this.f3315a = consumer;
        this.f3316b = consumer2;
    }

    /* renamed from: a */
    public final void mo2649a(Object obj) {
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2651a(Object obj, Throwable th) {
        Void voidR = (Void) obj;
        this.f3316b.accept(th);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2650a(Object obj, Object obj2) {
        Void voidR = (Void) obj;
        this.f3315a.accept(obj2);
    }
}
