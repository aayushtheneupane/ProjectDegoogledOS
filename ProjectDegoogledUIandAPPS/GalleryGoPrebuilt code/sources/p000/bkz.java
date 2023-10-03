package p000;

import android.app.Activity;
import android.os.Build;
import android.os.SystemClock;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: bkz */
/* compiled from: PG */
public final class bkz {

    /* renamed from: a */
    public final Set f3084a;

    /* renamed from: b */
    public final cwl f3085b;

    /* renamed from: c */
    private final AtomicBoolean f3086c = new AtomicBoolean(false);

    /* renamed from: d */
    private final iel f3087d;

    public bkz(iel iel, Set set, cwl cwl) {
        this.f3084a = set;
        this.f3087d = iel;
        this.f3085b = cwl;
    }

    /* renamed from: a */
    public final void mo2540a(Activity activity) {
        if (!this.f3086c.getAndSet(true)) {
            fmj fmj = fmj.f10033a;
            if (fxk.m9826a() && fmj.f10041g == 0) {
                fmj.f10041g = SystemClock.elapsedRealtime();
                fmj.f10042h.f10032e = true;
                int i = Build.VERSION.SDK_INT;
                if (activity != null) {
                    try {
                        activity.reportFullyDrawn();
                    } catch (RuntimeException e) {
                        flw.m9192a("PrimesStartupMeasure", "Failed to report App usable time.", (Throwable) e, new Object[0]);
                    }
                }
            }
            cwn.m5509a(gte.m10771a((ieh) this.f3087d.mo5936a(hmq.m11749a(bkw.f3081a), 5, TimeUnit.SECONDS), (icf) new bkx(this), (Executor) this.f3087d), "InteractiveCallback: Failed to schedule a job.", new Object[0]);
            cwn.m5509a(ife.m12815a(hmq.m11743a((ice) new bky(this)), 8, TimeUnit.SECONDS, (ScheduledExecutorService) this.f3087d), "InteractiveCallback: Failed to log first app open.", new Object[0]);
        }
    }
}
