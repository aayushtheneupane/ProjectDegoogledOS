package p000;

import android.os.Looper;
import android.os.Message;
import android.util.Log;

/* renamed from: emy */
/* compiled from: PG */
final class emy extends eui {

    /* renamed from: a */
    private final /* synthetic */ ena f8597a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public emy(ena ena, Looper looper) {
        super(looper);
        this.f8597a = ena;
    }

    public final void handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            ena ena = this.f8597a;
            int i2 = ena.f8601t;
            ena.f8602b.lock();
            try {
                if (ena.mo5033f()) {
                    ena.mo5031d();
                }
            } finally {
                ena.f8602b.unlock();
            }
        } else if (i != 2) {
            int i3 = message.what;
            StringBuilder sb = new StringBuilder(31);
            sb.append("Unknown message id: ");
            sb.append(i3);
            Log.w("GoogleApiClientImpl", sb.toString());
        } else {
            ena ena2 = this.f8597a;
            int i4 = ena.f8601t;
            ena2.mo5032e();
        }
    }
}
