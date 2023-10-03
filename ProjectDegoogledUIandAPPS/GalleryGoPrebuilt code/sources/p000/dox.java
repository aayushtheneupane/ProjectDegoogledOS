package p000;

import android.graphics.Bitmap;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: dox */
/* compiled from: PG */
final /* synthetic */ class dox implements Consumer {

    /* renamed from: a */
    private final dpt f6972a;

    public dox(dpt dpt) {
        this.f6972a = dpt;
    }

    public final void accept(Object obj) {
        dpt dpt = this.f6972a;
        dpt.f7016h.mo6987a(grw.m10691f(dpt.f7018j.mo4041b(cyc.m5648b(dpt.f7010b), dbn.m5855a((Bitmap) obj, dpt.f7020l))), dpt.f7005G);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
