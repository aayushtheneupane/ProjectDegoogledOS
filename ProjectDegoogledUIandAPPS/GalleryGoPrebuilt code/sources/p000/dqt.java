package p000;

import android.view.View;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: dqt */
/* compiled from: PG */
final /* synthetic */ class dqt implements Consumer {

    /* renamed from: a */
    private final Integer f7129a;

    public dqt(Integer num) {
        this.f7129a = num;
    }

    public final void accept(Object obj) {
        ((View) obj).setSystemUiVisibility(this.f7129a.intValue());
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
