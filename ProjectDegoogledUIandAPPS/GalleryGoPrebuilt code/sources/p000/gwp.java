package p000;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* renamed from: gwp */
/* compiled from: PG */
public final class gwp {

    /* renamed from: a */
    private final iel f12204a;

    public gwp(iel iel) {
        this.f12204a = iel;
    }

    /* renamed from: a */
    public final Executor mo7148a(gws gws) {
        return ((gwh) gws).f12189c == 0 ? idh.f13918a : this.f12204a;
    }

    /* renamed from: a */
    public static final void m10964a(StringBuilder sb, Throwable th) {
        sb.append(th.getClass().getName());
        sb.append("\n");
        for (StackTraceElement append : th.getStackTrace()) {
            sb.append("\tat ");
            sb.append(append);
            sb.append("\n");
        }
    }

    /* renamed from: a */
    public final ieh mo7147a(gws gws, String str, ieh ieh) {
        ieh a = ibd.m12603a(ieh, Throwable.class, (hpr) new gwo(gws), mo7148a(gws));
        long j = ((gwh) gws).f12189c;
        if (j > 0) {
            a = ibd.m12603a(ife.m12818a(a, j, TimeUnit.MILLISECONDS, (ScheduledExecutorService) this.f12204a), TimeoutException.class, gwn.f12202a, (Executor) this.f12204a);
        } else if (j == 0 && !a.isDone()) {
            a.cancel(true);
            a = ife.m12820a((Object) gwq.f12205a);
        }
        return ibv.m12657a(a, (hpr) new gwm(str, gws), mo7148a(gws));
    }
}
