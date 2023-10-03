package p000;

import android.view.Choreographer;

/* renamed from: fgm */
/* compiled from: PG */
final class fgm extends fgn {

    /* renamed from: a */
    private final Choreographer f9516a = Choreographer.getInstance();

    /* renamed from: a */
    public final void mo5591a(fgl fgl) {
        this.f9516a.postFrameCallback(fgl.mo5590a());
    }

    /* renamed from: b */
    public final void mo5592b(fgl fgl) {
        this.f9516a.removeFrameCallback(fgl.mo5590a());
    }
}
