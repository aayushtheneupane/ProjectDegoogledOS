package com.android.settings.applications.specialaccess.zenaccess;

import android.app.ActivityManager;
import android.app.AppGlobals;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.AsyncTask;
import android.os.RemoteException;
import android.util.ArraySet;
import android.util.Log;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.overlay.FeatureFactory;
import java.util.List;
import java.util.Set;

public class ZenAccessController extends BasePreferenceController {
    private static final String TAG = "ZenAccessController";
    private final ActivityManager mActivityManager = ((ActivityManager) this.mContext.getSystemService("activity"));

    public ZenAccessController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        return isSupported(this.mActivityManager) ? 1 : 3;
    }

    public static boolean isSupported(ActivityManager activityManager) {
        return !activityManager.isLowRamDevice();
    }

    public static Set<String> getPackagesRequestingNotificationPolicyAccess() {
        ArraySet arraySet = new ArraySet();
        try {
            List<PackageInfo> list = AppGlobals.getPackageManager().getPackagesHoldingPermissions(new String[]{"android.permission.ACCESS_NOTIFICATION_POLICY"}, 0, ActivityManager.getCurrentUser()).getList();
            if (list != null) {
                for (PackageInfo packageInfo : list) {
                    arraySet.add(packageInfo.packageName);
                }
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Cannot reach packagemanager", e);
        }
        return arraySet;
    }

    public static Set<String> getAutoApprovedPackages(Context context) {
        ArraySet arraySet = new ArraySet();
        arraySet.addAll(((NotificationManager) context.getSystemService(NotificationManager.class)).getEnabledNotificationListenerPackages());
        return arraySet;
    }

    public static boolean hasAccess(Context context, String str) {
        return ((NotificationManager) context.getSystemService(NotificationManager.class)).isNotificationPolicyAccessGrantedForPackage(str);
    }

    public static void setAccess(Context context, String str, boolean z) {
        logSpecialPermissionChange(z, str, context);
        AsyncTask.execute(new Runnable(context, str, z) {
            private final /* synthetic */ Context f$0;
            private final /* synthetic */ String f$1;
            private final /* synthetic */ boolean f$2;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                ((NotificationManager) this.f$0.getSystemService(NotificationManager.class)).setNotificationPolicyAccessGranted(this.f$1, this.f$2);
            }
        });
    }

    public static void deleteRules(Context context, String str) {
        AsyncTask.execute(new Runnable(context, str) {
            private final /* synthetic */ Context f$0;
            private final /* synthetic */ String f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            public final void run() {
                ((NotificationManager) this.f$0.getSystemService(NotificationManager.class)).removeAutomaticZenRules(this.f$1);
            }
        });
    }

    static void logSpecialPermissionChange(boolean z, String str, Context context) {
        FeatureFactory.getFactory(context).getMetricsFeatureProvider().action(context, z ? 768 : 769, str);
    }
}
