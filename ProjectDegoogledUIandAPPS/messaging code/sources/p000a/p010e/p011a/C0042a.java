package p000a.p010e.p011a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* renamed from: a.e.a.a */
class C0042a extends Handler {
    final /* synthetic */ C0045d this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C0042a(C0045d dVar, Looper looper) {
        super(looper);
        this.this$0 = dVar;
    }

    public void handleMessage(Message message) {
        if (message.what != 1) {
            super.handleMessage(message);
        } else {
            this.this$0.mo258Lc();
        }
    }
}
