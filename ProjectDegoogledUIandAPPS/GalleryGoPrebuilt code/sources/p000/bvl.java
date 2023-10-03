package p000;

import com.google.android.apps.photosgo.editor.parameters.PipelineParams;
import java.util.concurrent.Callable;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: bvl */
/* compiled from: PG */
final /* synthetic */ class bvl implements Consumer {

    /* renamed from: a */
    private final bvv f3677a;

    public bvl(bvv bvv) {
        this.f3677a = bvv;
    }

    public final void accept(Object obj) {
        bvv bvv = this.f3677a;
        Void voidR = (Void) obj;
        grx grx = bvv.f3736r;
        bxb bxb = bvv.f3735q;
        hlj a = hnb.m11765a("generate preset thumbnails");
        try {
            PipelineParams pipelineParams = new PipelineParams();
            cam.m3950a(((byc) bxb.f3812b).f3888c, pipelineParams);
            grw b = grw.m10687b(a.mo7548a(bxb.f3813c.mo5933a(hmq.m11749a((Callable) new bxa(bxb, pipelineParams)))));
            if (a != null) {
                a.close();
            }
            grx.mo6987a(b, bvv.f3709V);
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
