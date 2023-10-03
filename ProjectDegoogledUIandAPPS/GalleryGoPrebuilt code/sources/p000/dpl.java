package p000;

import android.graphics.Bitmap;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: dpl */
/* compiled from: PG */
final /* synthetic */ class dpl implements Consumer {

    /* renamed from: a */
    private final dpt f6991a;

    public dpl(dpt dpt) {
        this.f6991a = dpt;
    }

    public final void accept(Object obj) {
        dpt dpt = this.f6991a;
        dpt.f7025q.setImageBitmap((Bitmap) obj);
        dpt.mo4328b();
        dpt.f7031w.setVisibility(8);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
