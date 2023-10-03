package p000;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import java.lang.ref.WeakReference;

/* renamed from: of */
/* compiled from: PG */
final class C0390of extends Handler {

    /* renamed from: a */
    private final WeakReference f15373a;

    public C0390of(DialogInterface dialogInterface) {
        this.f15373a = new WeakReference(dialogInterface);
    }

    public final void handleMessage(Message message) {
        int i = message.what;
        if (i == -3 || i == -2 || i == -1) {
            ((DialogInterface.OnClickListener) message.obj).onClick((DialogInterface) this.f15373a.get(), message.what);
        } else if (i == 1) {
            ((DialogInterface) message.obj).dismiss();
        }
    }
}
