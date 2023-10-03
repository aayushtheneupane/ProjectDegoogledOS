package p000;

import android.animation.TimeInterpolator;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: bvq */
/* compiled from: PG */
final /* synthetic */ class bvq implements Consumer {

    /* renamed from: a */
    public static final Consumer f3682a = new bvq();

    private bvq() {
    }

    public final void accept(Object obj) {
        TimeInterpolator timeInterpolator = bvv.f3687a;
        cwn.m5511a((Throwable) obj, "EditorFragment: Unable to compute editing data.", new Object[0]);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
