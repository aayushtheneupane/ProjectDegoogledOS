package p000;

import com.google.android.apps.photosgo.editor.nativerenderer.NativeRenderer;
import com.google.android.apps.photosgo.editor.parameters.PipelineParams;
import java.util.concurrent.Callable;

/* renamed from: bwd */
/* compiled from: PG */
final /* synthetic */ class bwd implements Callable {

    /* renamed from: a */
    private final bwe f3758a;

    /* renamed from: b */
    private final PipelineParams f3759b;

    public bwd(bwe bwe, PipelineParams pipelineParams) {
        this.f3758a = bwe;
        this.f3759b = pipelineParams;
    }

    public final Object call() {
        bwe bwe = this.f3758a;
        PipelineParams pipelineParams = this.f3759b;
        NativeRenderer nativeRenderer = (NativeRenderer) bwe.f3761b;
        return nativeRenderer.computeResultImage(pipelineParams, true, nativeRenderer.f4835a);
    }
}
