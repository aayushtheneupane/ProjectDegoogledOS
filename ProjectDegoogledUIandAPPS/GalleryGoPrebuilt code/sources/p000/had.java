package p000;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/* renamed from: had */
/* compiled from: PG */
final /* synthetic */ class had implements Runnable {

    /* renamed from: a */
    private final ham f12400a;

    public had(ham ham) {
        this.f12400a = ham;
    }

    public final void run() {
        for (gyc gyc : this.f12400a.f12415c.values()) {
            if (gyc.mo7208a()) {
                fxk.m9830b();
                for (gzn gzn : ((hsu) gyc.f12291b).values()) {
                    if (gzn.mo7224a()) {
                        try {
                            ((gyu) ife.m12871b((Future) gzn.f12357c.mo6948a())).mo7194b();
                            gzn.mo7223a(ibv.m12657a(gzn.f12357c.mo6948a(), gzh.f12349a, (Executor) idh.f13918a));
                        } catch (ExecutionException e) {
                            throw new RuntimeException(e.getCause());
                        }
                    }
                }
            }
        }
    }
}
