package p000;

import p003j$.util.Optional;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: bnh */
/* compiled from: PG */
final /* synthetic */ class bnh implements Consumer {

    /* renamed from: a */
    private final bnj f3194a;

    /* renamed from: b */
    private final Optional f3195b;

    public bnh(bnj bnj, Optional optional) {
        this.f3194a = bnj;
        this.f3195b = optional;
    }

    public final void accept(Object obj) {
        this.f3194a.put((cxd) this.f3195b.get(), ife.m12872b((Object[]) new String[]{(String) obj}));
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
