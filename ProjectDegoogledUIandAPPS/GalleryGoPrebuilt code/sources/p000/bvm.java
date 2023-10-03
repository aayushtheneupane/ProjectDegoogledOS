package p000;

import android.animation.TimeInterpolator;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: bvm */
/* compiled from: PG */
final /* synthetic */ class bvm implements Consumer {

    /* renamed from: a */
    public static final Consumer f3678a = new bvm();

    private bvm() {
    }

    public final void accept(Object obj) {
        TimeInterpolator timeInterpolator = bvv.f3687a;
        cwn.m5511a((Throwable) obj, "EditorFragment: Unable to initialize thumbnail processor.", new Object[0]);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
