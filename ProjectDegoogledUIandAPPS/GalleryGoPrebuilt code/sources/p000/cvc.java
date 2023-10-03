package p000;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* renamed from: cvc */
/* compiled from: PG */
public final class cvc implements cuj {

    /* renamed from: a */
    public static /* synthetic */ int f5713a;

    /* renamed from: b */
    private static final ahb f5714b;

    /* renamed from: c */
    private final Executor f5715c;

    /* renamed from: d */
    private final inw f5716d;

    static {
        aha aha = new aha();
        aha.f471b = true;
        aha.f470a = true;
        f5714b = aha.mo457a();
    }

    public cvc(Executor executor, inw inw) {
        this.f5715c = executor;
        this.f5716d = inw;
    }

    /* renamed from: a */
    public final ieh mo3836a() {
        gsr a = gsv.m10751a(cva.class);
        a.mo7029a(f5714b);
        a.mo7030a(gsu.m10748a("PLUGGED_IN_IDLE_WORKER"));
        a.f11983d = hpy.m11893b(new gsn(gst.m10745a(30, TimeUnit.MINUTES), hpy.m11893b(gst.m10745a(20, TimeUnit.MINUTES))));
        return gte.m10770a(((gsq) this.f5716d.mo9034a()).mo7027a(a.mo7028a()), cvb.f5712a, this.f5715c);
    }
}
