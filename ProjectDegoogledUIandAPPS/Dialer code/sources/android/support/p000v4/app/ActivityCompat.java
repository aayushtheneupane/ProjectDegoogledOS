package android.support.p000v4.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.p000v4.content.ContextCompat;

/* renamed from: android.support.v4.app.ActivityCompat */
public class ActivityCompat extends ContextCompat {

    /* renamed from: android.support.v4.app.ActivityCompat$OnRequestPermissionsResultCallback */
    public interface OnRequestPermissionsResultCallback {
    }

    /* renamed from: android.support.v4.app.ActivityCompat$RequestPermissionsRequestCodeValidator */
    public interface RequestPermissionsRequestCodeValidator {
        void validateRequestPermissionsRequestCode(int i);
    }

    public static void finishAffinity(Activity activity) {
        int i = Build.VERSION.SDK_INT;
        activity.finishAffinity();
    }

    public static void getPermissionCompatDelegate() {
    }

    public static void requestPermissions(Activity activity, String[] strArr, int i) {
        int i2 = Build.VERSION.SDK_INT;
        if (activity instanceof RequestPermissionsRequestCodeValidator) {
            ((RequestPermissionsRequestCodeValidator) activity).validateRequestPermissionsRequestCode(i);
        }
        activity.requestPermissions(strArr, i);
    }

    public static boolean shouldShowRequestPermissionRationale(Activity activity, String str) {
        int i = Build.VERSION.SDK_INT;
        return activity.shouldShowRequestPermissionRationale(str);
    }

    public static void startActivityForResult(Activity activity, Intent intent, int i, Bundle bundle) {
        int i2 = Build.VERSION.SDK_INT;
        activity.startActivityForResult(intent, i, bundle);
    }
}
