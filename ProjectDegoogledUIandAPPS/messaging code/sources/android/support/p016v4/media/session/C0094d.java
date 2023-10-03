package android.support.p016v4.media.session;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* renamed from: android.support.v4.media.session.d */
public abstract class C0094d extends Binder implements C0095e {
    public static C0095e asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("android.support.v4.media.session.IMediaSession");
        if (queryLocalInterface == null || !(queryLocalInterface instanceof C0095e)) {
            return new C0093c(iBinder);
        }
        return (C0095e) queryLocalInterface;
    }
}
