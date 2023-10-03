package p000;

import android.content.BroadcastReceiver;

/* renamed from: hfi */
/* compiled from: PG */
final /* synthetic */ class hfi implements Runnable {

    /* renamed from: a */
    private final BroadcastReceiver.PendingResult f12652a;

    public hfi(BroadcastReceiver.PendingResult pendingResult) {
        this.f12652a = pendingResult;
    }

    public final void run() {
        this.f12652a.finish();
    }
}
