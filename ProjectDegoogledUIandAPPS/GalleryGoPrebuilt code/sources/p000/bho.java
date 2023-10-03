package p000;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: bho */
/* compiled from: PG */
public final class bho extends bil implements bhq {
    public bho(IBinder iBinder) {
        super(iBinder, "com.firebase.jobdispatcher.IRemoteJobService");
    }

    /* renamed from: a */
    public final void mo2043a(Bundle bundle, bhn bhn) {
        Parcel a = mo2085a();
        bin.m2619a(a, (Parcelable) bundle);
        bin.m2618a(a, (IInterface) bhn);
        mo2089c(1, a);
    }

    /* renamed from: a */
    public final void mo2044a(Bundle bundle, boolean z) {
        Parcel a = mo2085a();
        bin.m2619a(a, (Parcelable) bundle);
        bin.m2620a(a, z);
        mo2089c(2, a);
    }
}
