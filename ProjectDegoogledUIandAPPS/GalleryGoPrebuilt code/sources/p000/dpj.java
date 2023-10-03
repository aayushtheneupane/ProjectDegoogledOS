package p000;

import com.google.android.apps.photosgo.editor.parameters.PipelineParams;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: dpj */
/* compiled from: PG */
final /* synthetic */ class dpj implements Consumer {

    /* renamed from: a */
    private final dpt f6989a;

    public dpj(dpt dpt) {
        this.f6989a = dpt;
    }

    public final void accept(Object obj) {
        dpt dpt = this.f6989a;
        Void voidR = (Void) obj;
        PipelineParams adjustmentsAutoParams = dpt.f7014f.getAdjustmentsAutoParams(new PipelineParams());
        if (adjustmentsAutoParams != null) {
            dpt.f7016h.mo6987a(dpt.f7017i.mo2830a(adjustmentsAutoParams), dpt.f7000B);
            return;
        }
        cwn.m5510a("Failed to get auto enhance parameters", new Object[0]);
        dpt.mo4328b();
        dpt.f7031w.setVisibility(8);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
