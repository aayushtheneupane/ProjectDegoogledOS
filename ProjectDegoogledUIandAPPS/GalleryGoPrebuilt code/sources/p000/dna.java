package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: dna */
/* compiled from: PG */
final /* synthetic */ class dna implements Consumer {

    /* renamed from: a */
    private final dnn f6856a;

    public dna(dnn dnn) {
        this.f6856a = dnn;
    }

    public final void accept(Object obj) {
        this.f6856a.mo4277e((cxi) obj);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
