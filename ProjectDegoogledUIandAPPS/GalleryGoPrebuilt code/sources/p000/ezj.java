package p000;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

@Deprecated
/* renamed from: ezj */
/* compiled from: PG */
public final class ezj implements eyg {
    ezj() {
    }

    /* renamed from: a */
    public final int mo5389a(Context context) {
        int i = ekh.f8469a;
        try {
            return context.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
            return 0;
        }
    }

    /* renamed from: a */
    public final void mo5390a(int i, Context context) {
        int i2 = ekh.f8469a;
        ejw ejw = ejw.f8454a;
        if (ekh.m7673b(context, i) || (i == 9 && ekh.m7670a(context, "com.android.vending"))) {
            ejw.mo4914a(context);
        } else {
            ejw.mo4915a(context, i);
        }
    }

    public ezj(byte[] bArr) {
    }
}
