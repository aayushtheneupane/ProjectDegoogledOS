package p000;

import android.os.Handler;
import android.os.Message;

/* renamed from: bbz */
/* compiled from: PG */
final class bbz implements Handler.Callback {

    /* renamed from: a */
    private final /* synthetic */ bca f2021a;

    public bbz(bca bca) {
        this.f2021a = bca;
    }

    public final boolean handleMessage(Message message) {
        if (message.what == 1) {
            this.f2021a.mo1802a((bbx) message.obj);
            return true;
        } else if (message.what != 2) {
            return false;
        } else {
            this.f2021a.f2027c.mo1440a((ber) (bbx) message.obj);
            return false;
        }
    }
}
