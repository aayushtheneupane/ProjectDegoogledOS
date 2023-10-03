package p000;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* renamed from: hjc */
/* compiled from: PG */
public final /* synthetic */ class hjc implements glh {

    /* renamed from: a */
    private final hiq f12846a;

    public hjc(hiq hiq) {
        this.f12846a = hiq;
    }

    /* renamed from: a */
    public final ieh mo6814a() {
        hiq hiq = this.f12846a;
        ife.m12876b(true, (Object) "onAccountsChanged called without an AccountManager bound");
        ieh b = hiq.mo7475b(hiq.mo7477c());
        hiz hiz = hiq.f12813e;
        ieh a = hiz.f12841c.mo5933a(hmq.m11749a((Callable) new hit(hiz)));
        ieh a2 = ife.m12884c(b, a).mo8442a(hmq.m11743a((ice) new hio(hiq, b, a)), (Executor) hiq.f12810b);
        hiq.f12819k.set(a2);
        ieh a3 = ife.m12818a(a2, 10, TimeUnit.SECONDS, (ScheduledExecutorService) hiq.f12811c);
        iei a4 = iei.m12760a(hmq.m11748a((Runnable) new hip(a3)));
        a3.mo53a(a4, idh.f13918a);
        return a4;
    }
}
