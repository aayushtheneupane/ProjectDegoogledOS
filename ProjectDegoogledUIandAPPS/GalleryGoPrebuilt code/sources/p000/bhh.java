package p000;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* renamed from: bhh */
/* compiled from: PG */
public final class bhh implements bht {

    /* renamed from: a */
    private final IBinder f2375a;

    public bhh(IBinder iBinder) {
        this.f2375a = iBinder;
    }

    /* renamed from: a */
    public final void mo2039a(int i) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.gcm.INetworkTaskCallback");
            obtain.writeInt(i);
            this.f2375a.transact(2, obtain, obtain2, 0);
            obtain2.readException();
            obtain.recycle();
            obtain2.recycle();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (Throwable th) {
            obtain.recycle();
            obtain2.recycle();
            throw th;
        }
    }
}
