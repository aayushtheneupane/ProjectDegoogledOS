package p000;

import p003j$.util.Optional;
import p003j$.util.function.Supplier;

/* renamed from: dmh */
/* compiled from: PG */
final /* synthetic */ class dmh implements Supplier {

    /* renamed from: a */
    private final Optional f6837a;

    public dmh(Optional optional) {
        this.f6837a = optional;
    }

    public final Object get() {
        Optional optional = this.f6837a;
        String[] strArr = dnn.f6870a;
        return (Boolean) optional.map(dmw.f6852a).orElse(true);
    }
}
