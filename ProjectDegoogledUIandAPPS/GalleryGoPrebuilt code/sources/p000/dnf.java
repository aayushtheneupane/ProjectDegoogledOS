package p000;

import android.os.Bundle;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: dnf */
/* compiled from: PG */
final /* synthetic */ class dnf implements Consumer {

    /* renamed from: a */
    private final dnn f6861a;

    /* renamed from: b */
    private final Bundle f6862b;

    public dnf(dnn dnn, Bundle bundle) {
        this.f6861a = dnn;
        this.f6862b = bundle;
    }

    public final void accept(Object obj) {
        dnn dnn = this.f6861a;
        Bundle bundle = this.f6862b;
        imi.m14107a(bundle, "oneup_media_key", (ikf) (cxi) obj);
        bundle.putBundle("oneup_media_state_key", (Bundle) dnn.f6878H.f6934a.mo4634a().map(dnx.f6925a).orElse((Object) null));
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
