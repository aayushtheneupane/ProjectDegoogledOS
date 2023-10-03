package com.android.systemui.statusbar.policy;

import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.app.StatusBarManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.UserHandle;
import android.os.UserManager;
import com.android.settingslib.Utils;
import com.android.systemui.statusbar.policy.LocationController;
import com.android.systemui.statusbar.policy.LocationControllerImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class LocationControllerImpl extends BroadcastReceiver implements LocationController {
    private static final int[] mHighPowerRequestAppOpArray = {42};
    private AppOpsManager mAppOpsManager;
    /* access modifiers changed from: private */
    public boolean mAreActiveLocationRequests;
    private Context mContext;
    private final C1539H mHandler = new C1539H();
    /* access modifiers changed from: private */
    public ArrayList<LocationController.LocationChangeCallback> mSettingsChangeCallbacks = new ArrayList<>();
    private StatusBarManager mStatusBarManager;

    public LocationControllerImpl(Context context, Looper looper) {
        this.mContext = context;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.location.HIGH_POWER_REQUEST_CHANGE");
        intentFilter.addAction("android.location.MODE_CHANGED");
        context.registerReceiverAsUser(this, UserHandle.ALL, intentFilter, (String) null, new Handler(looper));
        this.mAppOpsManager = (AppOpsManager) context.getSystemService("appops");
        this.mStatusBarManager = (StatusBarManager) context.getSystemService("statusbar");
        updateActiveLocationRequests();
    }

    public void addCallback(LocationController.LocationChangeCallback locationChangeCallback) {
        this.mSettingsChangeCallbacks.add(locationChangeCallback);
        this.mHandler.sendEmptyMessage(1);
    }

    public void removeCallback(LocationController.LocationChangeCallback locationChangeCallback) {
        this.mSettingsChangeCallbacks.remove(locationChangeCallback);
    }

    public boolean setLocationEnabled(boolean z) {
        int currentUser = ActivityManager.getCurrentUser();
        if (isUserLocationRestricted(currentUser)) {
            return false;
        }
        Utils.updateLocationEnabled(this.mContext, z, currentUser, 2);
        return true;
    }

    public boolean isLocationEnabled() {
        return ((LocationManager) this.mContext.getSystemService("location")).isLocationEnabledForUser(UserHandle.of(ActivityManager.getCurrentUser()));
    }

    public boolean isLocationActive() {
        return this.mAreActiveLocationRequests;
    }

    private boolean isUserLocationRestricted(int i) {
        return ((UserManager) this.mContext.getSystemService("user")).hasUserRestriction("no_share_location", UserHandle.of(i));
    }

    /* access modifiers changed from: protected */
    public boolean areActiveHighPowerLocationRequests() {
        List packagesForOps = this.mAppOpsManager.getPackagesForOps(mHighPowerRequestAppOpArray);
        if (packagesForOps != null) {
            int size = packagesForOps.size();
            for (int i = 0; i < size; i++) {
                List ops = ((AppOpsManager.PackageOps) packagesForOps.get(i)).getOps();
                if (ops != null) {
                    int size2 = ops.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        AppOpsManager.OpEntry opEntry = (AppOpsManager.OpEntry) ops.get(i2);
                        if (opEntry.getOp() == 42 && opEntry.isRunning()) {
                            return true;
                        }
                    }
                    continue;
                }
            }
        }
        return false;
    }

    private void updateActiveLocationRequests() {
        boolean z = this.mAreActiveLocationRequests;
        this.mAreActiveLocationRequests = areActiveHighPowerLocationRequests();
        if (this.mAreActiveLocationRequests != z) {
            this.mHandler.sendEmptyMessage(2);
        }
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("android.location.HIGH_POWER_REQUEST_CHANGE".equals(action)) {
            updateActiveLocationRequests();
        } else if ("android.location.MODE_CHANGED".equals(action)) {
            this.mHandler.sendEmptyMessage(1);
        }
    }

    /* renamed from: com.android.systemui.statusbar.policy.LocationControllerImpl$H */
    private final class C1539H extends Handler {
        private C1539H() {
        }

        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                locationSettingsChanged();
            } else if (i == 2) {
                locationActiveChanged();
            }
        }

        private void locationActiveChanged() {
            com.android.systemui.util.Utils.safeForeach(LocationControllerImpl.this.mSettingsChangeCallbacks, new Consumer() {
                public final void accept(Object obj) {
                    LocationControllerImpl.C1539H.this.lambda$locationActiveChanged$0$LocationControllerImpl$H((LocationController.LocationChangeCallback) obj);
                }
            });
        }

        public /* synthetic */ void lambda$locationActiveChanged$0$LocationControllerImpl$H(LocationController.LocationChangeCallback locationChangeCallback) {
            locationChangeCallback.onLocationActiveChanged(LocationControllerImpl.this.mAreActiveLocationRequests);
        }

        private void locationSettingsChanged() {
            com.android.systemui.util.Utils.safeForeach(LocationControllerImpl.this.mSettingsChangeCallbacks, new Consumer(LocationControllerImpl.this.isLocationEnabled()) {
                private final /* synthetic */ boolean f$0;

                {
                    this.f$0 = r1;
                }

                public final void accept(Object obj) {
                    ((LocationController.LocationChangeCallback) obj).onLocationSettingsChanged(this.f$0);
                }
            });
        }
    }
}
