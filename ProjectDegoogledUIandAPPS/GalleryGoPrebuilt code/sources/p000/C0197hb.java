package p000;

import android.graphics.Rect;
import android.transition.Transition;

/* renamed from: hb */
/* compiled from: PG */
final class C0197hb extends Transition.EpicenterCallback {

    /* renamed from: a */
    private final /* synthetic */ Rect f12437a;

    public C0197hb(Rect rect) {
        this.f12437a = rect;
    }

    public final Rect onGetEpicenter(Transition transition) {
        Rect rect = this.f12437a;
        if (rect == null || rect.isEmpty()) {
            return null;
        }
        return this.f12437a;
    }
}
