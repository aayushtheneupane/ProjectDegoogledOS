package android.support.p016v4.media.session;

import android.os.IBinder;
import android.os.Parcel;

/* renamed from: android.support.v4.media.session.c */
class C0093c implements C0095e {
    private IBinder mRemote;

    C0093c(IBinder iBinder) {
        this.mRemote = iBinder;
    }

    /* renamed from: a */
    public void mo550a(C0092b bVar) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            obtain.writeStrongBinder(bVar != null ? bVar.asBinder() : null);
            this.mRemote.transact(3, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public IBinder asBinder() {
        return this.mRemote;
    }
}
