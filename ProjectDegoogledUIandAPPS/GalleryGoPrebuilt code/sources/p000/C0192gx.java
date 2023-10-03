package p000;

import android.graphics.Rect;
import android.transition.Transition;

/* renamed from: gx */
/* compiled from: PG */
final class C0192gx extends Transition.EpicenterCallback {

    /* renamed from: a */
    private final /* synthetic */ Rect f12233a;

    public C0192gx(Rect rect) {
        this.f12233a = rect;
    }

    public final Rect onGetEpicenter(Transition transition) {
        return this.f12233a;
    }
}
