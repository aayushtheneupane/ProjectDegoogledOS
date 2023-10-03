package p000;

import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: dlq */
/* compiled from: PG */
final /* synthetic */ class dlq implements Consumer {

    /* renamed from: a */
    public static final Consumer f6795a = new dlq();

    private dlq() {
    }

    public final void accept(Object obj) {
        ((dlr) obj).mo4226c();
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
