package p000;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: bhl */
/* compiled from: PG */
public final class bhl extends bil implements bhn {
    public bhl(IBinder iBinder) {
        super(iBinder, "com.firebase.jobdispatcher.IJobCallback");
    }

    /* renamed from: a */
    public final void mo2041a(Bundle bundle, int i) {
        Parcel a = mo2085a();
        bin.m2619a(a, (Parcelable) bundle);
        a.writeInt(i);
        mo2089c(1, a);
    }
}
