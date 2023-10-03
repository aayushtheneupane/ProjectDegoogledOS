package android.support.p016v4.p017os;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

@SuppressLint({"BanParcelableUsage"})
/* renamed from: android.support.v4.os.ResultReceiver */
public class ResultReceiver implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0114d();
    final Handler mHandler = null;
    final boolean mLocal = false;
    C0113c mReceiver;

    ResultReceiver(Parcel parcel) {
        this.mReceiver = C0112b.asInterface(parcel.readStrongBinder());
    }

    public int describeContents() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public void onReceiveResult(int i, Bundle bundle) {
    }

    public void send(int i, Bundle bundle) {
        if (this.mLocal) {
            Handler handler = this.mHandler;
            if (handler != null) {
                handler.post(new C0116f(this, i, bundle));
            } else {
                onReceiveResult(i, bundle);
            }
        } else {
            C0113c cVar = this.mReceiver;
            if (cVar != null) {
                try {
                    cVar.send(i, bundle);
                } catch (RemoteException unused) {
                }
            }
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        synchronized (this) {
            if (this.mReceiver == null) {
                this.mReceiver = new C0115e(this);
            }
            parcel.writeStrongBinder(this.mReceiver.asBinder());
        }
    }
}
