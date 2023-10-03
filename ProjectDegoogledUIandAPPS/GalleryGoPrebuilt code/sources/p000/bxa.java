package p000;

import com.google.android.apps.photosgo.editor.PresetThumbnail;
import com.google.android.apps.photosgo.editor.nativerenderer.NativeRenderer;
import com.google.android.apps.photosgo.editor.parameters.PipelineParams;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.Callable;

/* renamed from: bxa */
/* compiled from: PG */
final /* synthetic */ class bxa implements Callable {

    /* renamed from: a */
    private final bxb f3809a;

    /* renamed from: b */
    private final PipelineParams f3810b;

    public bxa(bxb bxb, PipelineParams pipelineParams) {
        this.f3809a = bxb;
        this.f3810b = pipelineParams;
    }

    public final Object call() {
        bxb bxb = this.f3809a;
        PipelineParams pipelineParams = this.f3810b;
        ArrayList arrayList = new ArrayList();
        for (car car : car.values()) {
            if (!car.COLOR_POP.equals(car)) {
                cam.m3952a(pipelineParams, (Set) cam.f3968d);
                cas.f3991a.mo2915a(pipelineParams, car);
                cas.f3992b.mo2915a(pipelineParams, caq.m3962b());
                NativeRenderer nativeRenderer = (NativeRenderer) bxb.f3811a;
                PresetThumbnail presetThumbnail = (PresetThumbnail) ife.m12898e((Object) nativeRenderer.getPresetThumbnail(pipelineParams, nativeRenderer.f4835a));
                cbc e = cbd.m3991e();
                e.mo2983a(presetThumbnail.f4832a);
                e.mo2984a(presetThumbnail.f4833b);
                e.mo2985a(false);
                arrayList.add(e.mo2982a());
            }
        }
        return arrayList;
    }
}
