package p000;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

/* renamed from: bhk */
/* compiled from: PG */
final class bhk implements bht {

    /* renamed from: a */
    private final Messenger f2378a;

    /* renamed from: b */
    private final String f2379b;

    public bhk(Messenger messenger, String str) {
        this.f2378a = messenger;
        this.f2379b = str;
    }

    /* renamed from: a */
    public final void mo2039a(int i) {
        try {
            Messenger messenger = this.f2378a;
            Message obtain = Message.obtain();
            obtain.what = 3;
            obtain.arg1 = i;
            Bundle bundle = new Bundle();
            bundle.putString("tag", this.f2379b);
            obtain.setData(bundle);
            messenger.send(obtain);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
