package p000;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;

/* renamed from: dzk */
/* compiled from: PG */
public final class dzk implements ServiceConnection {

    /* renamed from: a */
    public ServiceConnection f7715a;

    /* renamed from: b */
    public final gpg f7716b;

    /* renamed from: c */
    public final Context f7717c;

    /* renamed from: d */
    private final iel f7718d;

    public dzk(iel iel, Context context, gpg gpg) {
        this.f7717c = context;
        this.f7718d = iel;
        this.f7716b = gpg;
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        new Object[1][0] = componentName;
    }

    public final void onNullBinding(ComponentName componentName) {
        this.f7716b.mo6902a((Throwable) new IllegalStateException("Failed to get photos auto-backup state"));
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        fqw fqw;
        if (iBinder != null) {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.libraries.photos.backup.api.IPhotosBackup");
            fqw = queryLocalInterface instanceof fqw ? (fqw) queryLocalInterface : new fqv(iBinder);
        } else {
            fqw = null;
        }
        cwn.m5509a(this.f7718d.mo5931a(hmq.m11748a((Runnable) new dzj(this, fqw))), "PromoDataService[PhotoGrid]: Failed to get autobackup info from Photos.", new Object[0]);
    }
}
