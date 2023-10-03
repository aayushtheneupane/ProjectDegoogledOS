package com.android.settings.privacy;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.permission.PermissionControllerManager;
import android.permission.RuntimePermissionUsageInfo;
import android.util.Log;
import android.view.View;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
import com.android.settingslib.Utils;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnCreate;
import com.android.settingslib.core.lifecycle.events.OnSaveInstanceState;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.widget.BarChartInfo;
import com.android.settingslib.widget.BarChartPreference;
import com.android.settingslib.widget.BarViewInfo;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PermissionBarChartPreferenceController extends BasePreferenceController implements PermissionControllerManager.OnPermissionUsageResultCallback, LifecycleObserver, OnCreate, OnStart, OnSaveInstanceState {
    private static final String KEY_PERMISSION_USAGE = "usage_infos";
    private static final String TAG = "BarChartPreferenceCtl";
    private BarChartPreference mBarChartPreference;
    List<RuntimePermissionUsageInfo> mOldUsageInfos = new ArrayList();
    private PackageManager mPackageManager;
    private PrivacyDashboardFragment mParent;

    public int getAvailabilityStatus() {
        return 1;
    }

    public PermissionBarChartPreferenceController(Context context, String str) {
        super(context, str);
        this.mPackageManager = context.getPackageManager();
    }

    public void setFragment(PrivacyDashboardFragment privacyDashboardFragment) {
        this.mParent = privacyDashboardFragment;
    }

    public void onCreate(Bundle bundle) {
        if (bundle != null) {
            this.mOldUsageInfos = bundle.getParcelableArrayList(KEY_PERMISSION_USAGE);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putParcelableList(KEY_PERMISSION_USAGE, this.mOldUsageInfos);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mBarChartPreference = (BarChartPreference) preferenceScreen.findPreference(getPreferenceKey());
        BarChartInfo.Builder builder = new BarChartInfo.Builder();
        builder.setTitle(C1715R.string.permission_bar_chart_title);
        builder.setDetails(C1715R.string.permission_bar_chart_details);
        builder.setEmptyText(C1715R.string.permission_bar_chart_empty_text);
        builder.setDetailsOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                PermissionBarChartPreferenceController.this.mo11541x27e37c09(view);
            }
        });
        this.mBarChartPreference.initializeBarChart(builder.build());
        if (!this.mOldUsageInfos.isEmpty()) {
            this.mBarChartPreference.setBarViewInfos(createBarViews(this.mOldUsageInfos));
        }
    }

    /* renamed from: lambda$displayPreference$0$PermissionBarChartPreferenceController */
    public /* synthetic */ void mo11541x27e37c09(View view) {
        Intent intent = new Intent("android.intent.action.REVIEW_PERMISSION_USAGE");
        intent.putExtra("android.intent.extra.DURATION_MILLIS", TimeUnit.DAYS.toMillis(1));
        this.mContext.startActivity(intent);
    }

    public void onStart() {
        if (isAvailable()) {
            this.mBarChartPreference.updateLoadingState(this.mOldUsageInfos.isEmpty());
            this.mParent.setLoadingEnabled(true);
            retrievePermissionUsageData();
        }
    }

    public void onPermissionUsageResult(List<RuntimePermissionUsageInfo> list) {
        list.sort(C1152xd263d1d1.INSTANCE);
        if (!areSamePermissionGroups(list)) {
            this.mBarChartPreference.setBarViewInfos(createBarViews(list));
            this.mOldUsageInfos = list;
        }
        this.mBarChartPreference.updateLoadingState(false);
        this.mParent.setLoadingEnabled(false);
    }

    static /* synthetic */ int lambda$onPermissionUsageResult$1(RuntimePermissionUsageInfo runtimePermissionUsageInfo, RuntimePermissionUsageInfo runtimePermissionUsageInfo2) {
        int appAccessCount = runtimePermissionUsageInfo2.getAppAccessCount() - runtimePermissionUsageInfo.getAppAccessCount();
        if (appAccessCount != 0) {
            return appAccessCount;
        }
        String name = runtimePermissionUsageInfo.getName();
        String name2 = runtimePermissionUsageInfo2.getName();
        if (name.equals("android.permission-group.LOCATION")) {
            return -1;
        }
        if (name2.equals("android.permission-group.LOCATION")) {
            return 1;
        }
        if (name.equals("android.permission-group.MICROPHONE")) {
            return -1;
        }
        if (name2.equals("android.permission-group.MICROPHONE")) {
            return 1;
        }
        if (name.equals("android.permission-group.CAMERA")) {
            return -1;
        }
        if (name2.equals("android.permission-group.CAMERA")) {
            return 1;
        }
        return runtimePermissionUsageInfo.getName().compareTo(runtimePermissionUsageInfo2.getName());
    }

    private void retrievePermissionUsageData() {
        ((PermissionControllerManager) this.mContext.getSystemService(PermissionControllerManager.class)).getPermissionUsages(false, (long) ((int) TimeUnit.DAYS.toMillis(1)), this.mContext.getMainExecutor(), this);
    }

    private BarViewInfo[] createBarViews(List<RuntimePermissionUsageInfo> list) {
        if (list.isEmpty()) {
            return null;
        }
        BarViewInfo[] barViewInfoArr = new BarViewInfo[Math.min(4, list.size())];
        for (int i = 0; i < barViewInfoArr.length; i++) {
            RuntimePermissionUsageInfo runtimePermissionUsageInfo = list.get(i);
            int appAccessCount = runtimePermissionUsageInfo.getAppAccessCount();
            CharSequence permissionGroupLabel = getPermissionGroupLabel(runtimePermissionUsageInfo.getName());
            barViewInfoArr[i] = new BarViewInfo(getPermissionGroupIcon(runtimePermissionUsageInfo.getName()), appAccessCount, permissionGroupLabel, this.mContext.getResources().getQuantityString(C1715R.plurals.permission_bar_chart_label, appAccessCount, new Object[]{Integer.valueOf(appAccessCount)}), permissionGroupLabel);
            barViewInfoArr[i].setClickListener(new View.OnClickListener(runtimePermissionUsageInfo) {
                private final /* synthetic */ RuntimePermissionUsageInfo f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    PermissionBarChartPreferenceController.this.lambda$createBarViews$2$PermissionBarChartPreferenceController(this.f$1, view);
                }
            });
        }
        return barViewInfoArr;
    }

    public /* synthetic */ void lambda$createBarViews$2$PermissionBarChartPreferenceController(RuntimePermissionUsageInfo runtimePermissionUsageInfo, View view) {
        Intent intent = new Intent("android.intent.action.REVIEW_PERMISSION_USAGE");
        intent.putExtra("android.intent.extra.PERMISSION_GROUP_NAME", runtimePermissionUsageInfo.getName());
        intent.putExtra("android.intent.extra.DURATION_MILLIS", TimeUnit.DAYS.toMillis(1));
        this.mContext.startActivity(intent);
    }

    private Drawable getPermissionGroupIcon(String str) {
        Drawable drawable = null;
        try {
            drawable = this.mPackageManager.getPermissionGroupInfo(str, 0).loadIcon(this.mPackageManager);
            drawable.setTintList(Utils.getColorAttr(this.mContext, 16842808));
            return drawable;
        } catch (PackageManager.NameNotFoundException e) {
            Log.w(TAG, "Cannot find group icon for " + str, e);
            return drawable;
        }
    }

    private CharSequence getPermissionGroupLabel(String str) {
        try {
            return this.mPackageManager.getPermissionGroupInfo(str, 0).loadLabel(this.mPackageManager);
        } catch (PackageManager.NameNotFoundException e) {
            Log.w(TAG, "Cannot find group label for " + str, e);
            return null;
        }
    }

    private boolean areSamePermissionGroups(List<RuntimePermissionUsageInfo> list) {
        if (list.size() != this.mOldUsageInfos.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            RuntimePermissionUsageInfo runtimePermissionUsageInfo = list.get(i);
            RuntimePermissionUsageInfo runtimePermissionUsageInfo2 = this.mOldUsageInfos.get(i);
            if (!runtimePermissionUsageInfo.getName().equals(runtimePermissionUsageInfo2.getName()) || runtimePermissionUsageInfo.getAppAccessCount() != runtimePermissionUsageInfo2.getAppAccessCount()) {
                return false;
            }
        }
        return true;
    }
}
