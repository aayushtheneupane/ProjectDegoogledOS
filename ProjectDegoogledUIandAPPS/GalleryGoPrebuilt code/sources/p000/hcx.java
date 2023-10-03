package p000;

import android.util.Log;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* renamed from: hcx */
/* compiled from: PG */
final /* synthetic */ class hcx implements Runnable {

    /* renamed from: a */
    private final ieh f12495a;

    public hcx(ieh ieh) {
        this.f12495a = ieh;
    }

    public final void run() {
        ieh ieh = this.f12495a;
        int i = hde.f12516e;
        if (!ieh.isCancelled()) {
            try {
                ife.m12871b((Future) ieh);
            } catch (ExecutionException e) {
                Log.e("ClientLoggingReceiver", "Serializing log proto failed. This should never happen.", e);
            }
        }
    }
}
