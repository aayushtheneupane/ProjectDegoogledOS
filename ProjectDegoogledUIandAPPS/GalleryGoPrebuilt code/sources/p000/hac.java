package p000;

import android.app.Activity;
import android.os.MessageQueue;

/* renamed from: hac */
/* compiled from: PG */
final /* synthetic */ class hac implements MessageQueue.IdleHandler {

    /* renamed from: a */
    private final ham f12399a;

    public hac(ham ham) {
        this.f12399a = ham;
    }

    public final boolean queueIdle() {
        ham ham = this.f12399a;
        if (ham.f12417e || !ham.f12414b.isEmpty()) {
            return false;
        }
        hlj a = hnb.m11765a("Recreating all activities");
        try {
            if (ham.mo7250a()) {
                ham.f12417e = true;
                fxk.m9824a(hmq.m11748a((Runnable) new had(ham)));
                for (Activity a2 : ham.f12413a) {
                    gxo.m11006a(a2);
                }
                fxk.m9824a((Runnable) new hae(ham));
            }
            if (a == null) {
                return false;
            }
            a.close();
            return false;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }
}
