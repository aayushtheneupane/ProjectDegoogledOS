package androidx.core.app;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Build;

public final class AppOpsManagerCompat {
    public static final int MODE_ALLOWED = 0;
    public static final int MODE_DEFAULT = 3;
    public static final int MODE_ERRORED = 2;
    public static final int MODE_IGNORED = 1;

    private AppOpsManagerCompat() {
    }

    public static int noteOp(Context context, String str, int i, String str2) {
        int i2 = Build.VERSION.SDK_INT;
        return ((AppOpsManager) context.getSystemService("appops")).noteOp(str, i, str2);
    }

    public static int noteOpNoThrow(Context context, String str, int i, String str2) {
        int i2 = Build.VERSION.SDK_INT;
        return ((AppOpsManager) context.getSystemService("appops")).noteOpNoThrow(str, i, str2);
    }

    public static int noteProxyOp(Context context, String str, String str2) {
        int i = Build.VERSION.SDK_INT;
        return ((AppOpsManager) context.getSystemService(AppOpsManager.class)).noteProxyOp(str, str2);
    }

    public static int noteProxyOpNoThrow(Context context, String str, String str2) {
        int i = Build.VERSION.SDK_INT;
        return ((AppOpsManager) context.getSystemService(AppOpsManager.class)).noteProxyOpNoThrow(str, str2);
    }

    public static String permissionToOp(String str) {
        int i = Build.VERSION.SDK_INT;
        return AppOpsManager.permissionToOp(str);
    }
}
