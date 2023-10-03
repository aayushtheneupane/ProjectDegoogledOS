package p000;

import android.graphics.Bitmap;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: bvd */
/* compiled from: PG */
final /* synthetic */ class bvd implements Consumer {

    /* renamed from: a */
    private final bvv f3669a;

    public bvd(bvv bvv) {
        this.f3669a = bvv;
    }

    public final void accept(Object obj) {
        this.f3669a.mo2804a((Bitmap) obj);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
