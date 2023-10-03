package p000;

import android.content.Context;
import android.graphics.Bitmap;
import com.google.android.apps.photosgo.editor.parameters.PipelineParams;
import java.util.concurrent.Callable;

/* renamed from: bwe */
/* compiled from: PG */
public final class bwe {

    /* renamed from: a */
    public final Context f3760a;

    /* renamed from: b */
    public final bxc f3761b;

    /* renamed from: c */
    private final iel f3762c;

    public bwe(Context context, bxc bxc, iel iel) {
        this.f3760a = context;
        this.f3761b = bxc;
        this.f3762c = iel;
    }

    /* renamed from: a */
    public final grw mo2828a() {
        return mo2831a("computeEditingData", new bwc(this));
    }

    /* renamed from: a */
    public final grw mo2830a(PipelineParams pipelineParams) {
        hlj a = hnb.m11765a("computeResultImage");
        try {
            grw a2 = grw.m10686a(a.mo7548a(this.f3762c.mo5933a(hmq.m11749a((Callable) new bwd(this, pipelineParams)))));
            if (a != null) {
                a.close();
            }
            return a2;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: a */
    public final grw mo2831a(String str, Runnable runnable) {
        hlj a = hnb.m11765a(str);
        try {
            grw f = grw.m10691f(a.mo7548a(this.f3762c.mo5931a(hmq.m11748a(runnable))));
            if (a != null) {
                a.close();
            }
            return f;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: a */
    public final grw mo2829a(Bitmap bitmap) {
        return mo2831a("initializeImage", new bwb(this, bitmap));
    }
}
