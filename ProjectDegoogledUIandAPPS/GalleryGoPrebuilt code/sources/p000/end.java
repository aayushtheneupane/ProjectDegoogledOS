package p000;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.util.concurrent.locks.Lock;

/* renamed from: end */
/* compiled from: PG */
final class end extends eui {

    /* renamed from: a */
    private final /* synthetic */ ene f8627a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public end(ene ene, Looper looper) {
        super(looper);
        this.f8627a = ene;
    }

    public final void handleMessage(Message message) {
        Lock lock;
        int i = message.what;
        if (i == 1) {
            enc enc = (enc) message.obj;
            ene ene = this.f8627a;
            ene.f8628a.lock();
            try {
                if (ene.f8637j != enc.f8626a) {
                    lock = ene.f8628a;
                } else {
                    enc.mo5008a();
                    lock = ene.f8628a;
                }
                lock.unlock();
            } catch (Throwable th) {
                ene.f8628a.unlock();
                throw th;
            }
        } else if (i != 2) {
            int i2 = message.what;
            StringBuilder sb = new StringBuilder(31);
            sb.append("Unknown message id: ");
            sb.append(i2);
            Log.w("GACStateManager", sb.toString());
        } else {
            throw ((RuntimeException) message.obj);
        }
    }
}
