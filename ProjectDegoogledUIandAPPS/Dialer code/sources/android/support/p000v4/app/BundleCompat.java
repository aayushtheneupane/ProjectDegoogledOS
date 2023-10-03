package android.support.p000v4.app;

import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;

/* renamed from: android.support.v4.app.BundleCompat */
public final class BundleCompat {
    public static IBinder getBinder(Bundle bundle, String str) {
        int i = Build.VERSION.SDK_INT;
        return bundle.getBinder(str);
    }
}
