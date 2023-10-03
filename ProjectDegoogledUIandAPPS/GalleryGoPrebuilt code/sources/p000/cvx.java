package p000;

import android.provider.MediaStore;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* renamed from: cvx */
/* compiled from: PG */
public final class cvx implements cuj {

    /* renamed from: a */
    public static /* synthetic */ int f5759a;

    /* renamed from: b */
    private static final ahb f5760b;

    /* renamed from: c */
    private final Executor f5761c;

    /* renamed from: d */
    private final inw f5762d;

    static {
        aha aha = new aha();
        aha.mo458a(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        aha.mo458a(MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        aha.f472c = TimeUnit.MILLISECONDS.toMillis(5);
        f5760b = aha.mo457a();
    }

    public cvx(Executor executor, inw inw) {
        this.f5761c = executor;
        this.f5762d = inw;
    }

    /* renamed from: a */
    public final ieh mo3836a() {
        gsr a = gsv.m10751a(cvv.class);
        a.mo7029a(f5760b);
        a.mo7030a(gsu.m10748a("CONTENT_UPDATE_WORKER"));
        a.f11981b = gst.m10745a(5, TimeUnit.MILLISECONDS);
        return gte.m10770a(((gsq) this.f5762d.mo9034a()).mo7027a(a.mo7028a()), cvw.f5758a, this.f5761c);
    }
}
