package p000;

import android.os.Handler;
import android.os.Message;

/* renamed from: ade */
/* compiled from: PG */
final class ade extends Handler {

    /* renamed from: a */
    private final /* synthetic */ adk f207a;

    public ade(adk adk) {
        this.f207a = adk;
    }

    public final void handleMessage(Message message) {
        if (message.what == 1) {
            this.f207a.mo203Q();
        }
    }
}
