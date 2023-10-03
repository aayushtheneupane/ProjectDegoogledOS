package p000;

import android.view.View;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: cbh */
/* compiled from: PG */
final /* synthetic */ class cbh implements Consumer {

    /* renamed from: a */
    private final View f4009a;

    public cbh(View view) {
        this.f4009a = view;
    }

    public final void accept(Object obj) {
        ihg.m13039a((hoi) (cba) obj, this.f4009a);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
