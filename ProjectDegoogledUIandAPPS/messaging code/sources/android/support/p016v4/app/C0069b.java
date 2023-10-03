package android.support.p016v4.app;

import android.app.Notification;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* renamed from: android.support.v4.app.b */
public abstract class C0069b extends Binder implements C0070c {
    private static final String DESCRIPTOR = "android.support.v4.app.INotificationSideChannel";
    static final int TRANSACTION_cancel = 2;
    static final int TRANSACTION_cancelAll = 3;
    static final int TRANSACTION_notify = 1;

    public C0069b() {
        attachInterface(this, DESCRIPTOR);
    }

    public static C0070c asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
        if (queryLocalInterface == null || !(queryLocalInterface instanceof C0070c)) {
            return new C0068a(iBinder);
        }
        return (C0070c) queryLocalInterface;
    }

    public IBinder asBinder() {
        return this;
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 1) {
            parcel.enforceInterface(DESCRIPTOR);
            notify(parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readInt() != 0 ? (Notification) Notification.CREATOR.createFromParcel(parcel) : null);
            return true;
        } else if (i == 2) {
            parcel.enforceInterface(DESCRIPTOR);
            cancel(parcel.readString(), parcel.readInt(), parcel.readString());
            return true;
        } else if (i == 3) {
            parcel.enforceInterface(DESCRIPTOR);
            cancelAll(parcel.readString());
            return true;
        } else if (i != 1598968902) {
            return super.onTransact(i, parcel, parcel2, i2);
        } else {
            parcel2.writeString(DESCRIPTOR);
            return true;
        }
    }
}
