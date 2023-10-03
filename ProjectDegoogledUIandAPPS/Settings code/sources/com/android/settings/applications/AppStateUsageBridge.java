package com.android.settings.applications;

import android.content.Context;
import com.android.settings.applications.AppStateAppOpsBridge;
import com.android.settings.applications.AppStateBaseBridge;
import com.android.settingslib.applications.ApplicationsState;

public class AppStateUsageBridge extends AppStateAppOpsBridge {
    public static final ApplicationsState.AppFilter FILTER_APP_USAGE = new ApplicationsState.AppFilter() {
        public void init() {
        }

        public boolean filterApp(ApplicationsState.AppEntry appEntry) {
            return appEntry.extraInfo != null;
        }
    };
    private static final String[] PM_PERMISSION = {"android.permission.PACKAGE_USAGE_STATS"};

    public AppStateUsageBridge(Context context, ApplicationsState applicationsState, AppStateBaseBridge.Callback callback) {
        super(context, applicationsState, callback, 43, PM_PERMISSION);
    }

    /* access modifiers changed from: protected */
    public void updateExtraInfo(ApplicationsState.AppEntry appEntry, String str, int i) {
        appEntry.extraInfo = getUsageInfo(str, i);
    }

    public UsageState getUsageInfo(String str, int i) {
        return new UsageState(super.getPermissionInfo(str, i));
    }

    public static class UsageState extends AppStateAppOpsBridge.PermissionState {
        public UsageState(AppStateAppOpsBridge.PermissionState permissionState) {
            super(permissionState.packageName, permissionState.userHandle);
            this.packageInfo = permissionState.packageInfo;
            this.appOpMode = permissionState.appOpMode;
            this.permissionDeclared = permissionState.permissionDeclared;
            this.staticPermissionGranted = permissionState.staticPermissionGranted;
        }
    }
}
