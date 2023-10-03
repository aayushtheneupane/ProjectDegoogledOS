package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: dml */
/* compiled from: PG */
final /* synthetic */ class dml implements Consumer {

    /* renamed from: a */
    private final dnn f6841a;

    public dml(dnn dnn) {
        this.f6841a = dnn;
    }

    public final void accept(Object obj) {
        this.f6841a.mo4276d((cxi) obj);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
