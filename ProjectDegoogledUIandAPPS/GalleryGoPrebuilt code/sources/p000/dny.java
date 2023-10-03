package p000;

import android.os.Bundle;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: dny */
/* compiled from: PG */
final /* synthetic */ class dny implements Consumer {

    /* renamed from: a */
    private final Bundle f6926a;

    public dny(Bundle bundle) {
        this.f6926a = bundle;
    }

    public final void accept(Object obj) {
        Bundle bundle = this.f6926a;
        dls dls = (dls) obj;
        if (dls.f6800e.isPresent()) {
            ((dlr) dls.f6800e.get()).mo4222a(bundle);
        } else {
            dls.f6799d = bundle;
        }
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
