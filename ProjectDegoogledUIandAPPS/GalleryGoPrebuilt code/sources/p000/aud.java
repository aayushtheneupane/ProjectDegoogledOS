package p000;

import android.os.Handler;
import android.os.Message;

/* renamed from: aud */
/* compiled from: PG */
final class aud implements Handler.Callback {
    public final boolean handleMessage(Message message) {
        if (message.what != 1) {
            return false;
        }
        ((aua) message.obj).mo1607d();
        return true;
    }
}
