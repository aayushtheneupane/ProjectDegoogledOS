package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: cni */
/* compiled from: PG */
final /* synthetic */ class cni implements Consumer {

    /* renamed from: a */
    private final C0147fh f4728a;

    public cni(C0147fh fhVar) {
        this.f4728a = fhVar;
    }

    public final void accept(Object obj) {
        ihg.m13041a((hoi) obj, this.f4728a);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
