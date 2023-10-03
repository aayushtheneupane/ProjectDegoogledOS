package p000;

import android.content.Context;
import android.content.ServiceConnection;
import android.os.RemoteException;
import p003j$.util.Optional;

/* renamed from: dzj */
/* compiled from: PG */
final /* synthetic */ class dzj implements Runnable {

    /* renamed from: a */
    private final dzk f7713a;

    /* renamed from: b */
    private final fqw f7714b;

    public dzj(dzk dzk, fqw fqw) {
        this.f7713a = dzk;
        this.f7714b = fqw;
    }

    public final void run() {
        ServiceConnection serviceConnection;
        dzk dzk = this.f7713a;
        fqw fqw = this.f7714b;
        Context context = dzk.f7717c;
        gpg gpg = dzk.f7716b;
        if (fqw == null) {
            gpg.mo6902a((Throwable) new IllegalStateException("Photos service was null"));
        } else {
            try {
                if (fqw.mo6039b() == null) {
                    gpg.mo6901a((Object) Optional.empty());
                } else {
                    gpg.mo6901a((Object) dzl.m6969a(context));
                }
            } catch (RemoteException e) {
                gpg.mo6902a((Throwable) e);
            }
        }
        if (!(fqw == null || (serviceConnection = dzk.f7715a) == null)) {
            dzk.f7717c.unbindService(serviceConnection);
        }
        dzk.f7715a = null;
    }
}
