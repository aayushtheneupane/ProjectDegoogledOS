package p000;

import p003j$.util.function.BiConsumer;

/* renamed from: bpo */
/* compiled from: PG */
public class bpo implements gry {

    /* renamed from: a */
    private final BiConsumer f3317a;

    /* renamed from: b */
    private final BiConsumer f3318b;

    public bpo(BiConsumer biConsumer, BiConsumer biConsumer2) {
        this.f3317a = biConsumer;
        this.f3318b = biConsumer2;
    }

    /* renamed from: a */
    public final void mo2649a(Object obj) {
    }

    /* renamed from: a */
    public final void mo2651a(Object obj, Throwable th) {
        this.f3318b.accept(obj, th);
    }

    /* renamed from: a */
    public final void mo2650a(Object obj, Object obj2) {
        this.f3317a.accept(obj, obj2);
    }
}
