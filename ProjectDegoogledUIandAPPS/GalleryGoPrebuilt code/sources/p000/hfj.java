package p000;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* renamed from: hfj */
/* compiled from: PG */
final /* synthetic */ class hfj implements Runnable {

    /* renamed from: a */
    private final ieh f12653a;

    public hfj(ieh ieh) {
        this.f12653a = ieh;
    }

    public final void run() {
        try {
            ife.m12871b((Future) this.f12653a);
        } catch (ExecutionException e) {
            if (e.getCause() instanceof gmc) {
                ((hvv) ((hvv) ((hvv) hfk.f12654a.mo8178a()).mo8202a((Throwable) e)).mo8201a("com/google/apps/tiktok/receiver/IntentFilterAcledReceiver", "lambda$crashOnException$3", 207, "IntentFilterAcledReceiver.java")).mo8203a("Got unexpected accountId. Was the account removed?");
            } else {
                hmw.m11760b(e.getCause());
            }
        } catch (Throwable th) {
            hmw.m11760b(th);
        }
    }
}
