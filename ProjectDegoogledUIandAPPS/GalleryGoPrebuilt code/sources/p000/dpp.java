package p000;

import com.google.android.apps.photosgo.editor.parameters.PipelineParams;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: dpp */
/* compiled from: PG */
final /* synthetic */ class dpp implements Consumer {

    /* renamed from: a */
    private final dpt f6995a;

    public dpp(dpt dpt) {
        this.f6995a = dpt;
    }

    public final void accept(Object obj) {
        dpt dpt = this.f6995a;
        Void voidR = (Void) obj;
        PipelineParams adjustmentsAutoParams = dpt.f7014f.getAdjustmentsAutoParams(new PipelineParams());
        if (adjustmentsAutoParams != null) {
            dpt.f7016h.mo6987a(dpt.f7017i.mo2830a(adjustmentsAutoParams), dpt.f7003E);
        } else {
            dpt.mo4327a(new Exception("Failed to get auto enhance parameters for saved image"));
        }
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
