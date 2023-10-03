package p000;

import android.os.Handler;
import android.os.Message;

/* renamed from: gio */
/* compiled from: PG */
final class gio implements Handler.Callback {

    /* renamed from: a */
    private final /* synthetic */ gir f11434a;

    public gio(gir gir) {
        this.f11434a = gir;
    }

    public final boolean handleMessage(Message message) {
        if (message.what != 0) {
            return false;
        }
        gir gir = this.f11434a;
        giq giq = (giq) message.obj;
        synchronized (gir.f11439a) {
            if (gir.f11441c == giq || gir.f11442d == giq) {
                gir.mo6725a(giq, 2);
            }
        }
        return true;
    }
}
