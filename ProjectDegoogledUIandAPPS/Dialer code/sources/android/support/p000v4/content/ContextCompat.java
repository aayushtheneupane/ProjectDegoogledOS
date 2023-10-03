package android.support.p000v4.content;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import java.io.File;

/* renamed from: android.support.v4.content.ContextCompat */
public class ContextCompat {
    private static final Object sLock = new Object();

    public static int checkSelfPermission(Context context, String str) {
        if (str != null) {
            return context.checkPermission(str, Process.myPid(), Process.myUid());
        }
        throw new IllegalArgumentException("permission is null");
    }

    public static Context createDeviceProtectedStorageContext(Context context) {
        int i = Build.VERSION.SDK_INT;
        return context.createDeviceProtectedStorageContext();
    }

    public static int getColor(Context context, int i) {
        int i2 = Build.VERSION.SDK_INT;
        return context.getColor(i);
    }

    public static Drawable getDrawable(Context context, int i) {
        int i2 = Build.VERSION.SDK_INT;
        return context.getDrawable(i);
    }

    public static File[] getExternalCacheDirs(Context context) {
        int i = Build.VERSION.SDK_INT;
        return context.getExternalCacheDirs();
    }

    public static File[] getExternalFilesDirs(Context context, String str) {
        int i = Build.VERSION.SDK_INT;
        return context.getExternalFilesDirs(str);
    }

    public static boolean startActivities(Context context, Intent[] intentArr, Bundle bundle) {
        int i = Build.VERSION.SDK_INT;
        context.startActivities(intentArr, bundle);
        return true;
    }

    public static void startActivity(Context context, Intent intent, Bundle bundle) {
        int i = Build.VERSION.SDK_INT;
        context.startActivity(intent, bundle);
    }
}
