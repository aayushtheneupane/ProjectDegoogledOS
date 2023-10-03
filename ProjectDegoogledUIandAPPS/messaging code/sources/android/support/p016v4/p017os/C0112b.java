package android.support.p016v4.p017os;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* renamed from: android.support.v4.os.b */
public abstract class C0112b extends Binder implements C0113c {
    public C0112b() {
        attachInterface(this, "android.support.v4.os.IResultReceiver");
    }

    public static C0113c asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("android.support.v4.os.IResultReceiver");
        if (queryLocalInterface == null || !(queryLocalInterface instanceof C0113c)) {
            return new C0111a(iBinder);
        }
        return (C0113c) queryLocalInterface;
    }

    public IBinder asBinder() {
        return this;
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 1) {
            parcel.enforceInterface("android.support.v4.os.IResultReceiver");
            ((C0115e) this).send(parcel.readInt(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
            return true;
        } else if (i != 1598968902) {
            return super.onTransact(i, parcel, parcel2, i2);
        } else {
            parcel2.writeString("android.support.v4.os.IResultReceiver");
            return true;
        }
    }
}
