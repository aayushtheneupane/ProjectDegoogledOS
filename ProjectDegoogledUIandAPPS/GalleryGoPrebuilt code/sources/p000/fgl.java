package p000;

import android.view.Choreographer;

/* renamed from: fgl */
/* compiled from: PG */
public abstract class fgl {

    /* renamed from: a */
    private Choreographer.FrameCallback f9515a;

    /* renamed from: a */
    public abstract void mo5586a(long j);

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final Choreographer.FrameCallback mo5590a() {
        if (this.f9515a == null) {
            this.f9515a = new fgk(this);
        }
        return this.f9515a;
    }
}
