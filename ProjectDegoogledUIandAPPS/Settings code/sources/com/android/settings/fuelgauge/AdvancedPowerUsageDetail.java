package com.android.settings.fuelgauge;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.BatteryStats;
import android.os.Bundle;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import com.android.internal.os.BatterySipper;
import com.android.internal.os.BatteryStatsHelper;
import com.android.internal.util.ArrayUtils;
import com.android.settings.SettingsActivity;
import com.android.settings.applications.appinfo.AppButtonsPreferenceController;
import com.android.settings.applications.appinfo.ButtonActionDialogFragment;
import com.android.settings.core.InstrumentedPreferenceFragment;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.fuelgauge.batterytip.BatteryTipPreferenceController;
import com.android.settings.fuelgauge.batterytip.tips.BatteryTip;
import com.android.settings.widget.EntityHeaderController;
import com.android.settingslib.Utils;
import com.android.settingslib.applications.AppUtils;
import com.android.settingslib.applications.ApplicationsState;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.utils.StringUtil;
import com.android.settingslib.widget.LayoutPreference;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class AdvancedPowerUsageDetail extends DashboardFragment implements ButtonActionDialogFragment.AppButtonsDialogListener, BatteryTipPreferenceController.BatteryTipListener {
    private AppButtonsPreferenceController mAppButtonsPreferenceController;
    ApplicationsState.AppEntry mAppEntry;
    private BackgroundActivityPreferenceController mBackgroundActivityPreferenceController;
    Preference mBackgroundPreference;
    BatteryUtils mBatteryUtils;
    Preference mForegroundPreference;
    LayoutPreference mHeaderPreference;
    private String mPackageName;
    ApplicationsState mState;

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "AdvancedPowerDetail";
    }

    public int getMetricsCategory() {
        return 53;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.power_usage_detail;
    }

    static void startBatteryDetailPage(Activity activity, BatteryUtils batteryUtils, InstrumentedPreferenceFragment instrumentedPreferenceFragment, BatteryStatsHelper batteryStatsHelper, int i, BatteryEntry batteryEntry, String str) {
        long j;
        batteryStatsHelper.getStats();
        Bundle bundle = new Bundle();
        BatterySipper batterySipper = batteryEntry.sipper;
        BatteryStats.Uid uid = batterySipper.uidObj;
        boolean z = batterySipper.drainType == BatterySipper.DrainType.APP;
        if (z) {
            j = batteryUtils.getProcessTimeMs(1, uid, i);
        } else {
            j = batterySipper.usageTimeMs;
        }
        long processTimeMs = z ? batteryUtils.getProcessTimeMs(2, uid, i) : 0;
        if (ArrayUtils.isEmpty(batterySipper.mPackages)) {
            bundle.putString("extra_label", batteryEntry.getLabel());
            bundle.putInt("extra_icon_id", batteryEntry.iconId);
            bundle.putString("extra_package_name", (String) null);
        } else {
            String str2 = batteryEntry.defaultPackageName;
            if (str2 == null) {
                str2 = batterySipper.mPackages[0];
            }
            bundle.putString("extra_package_name", str2);
        }
        bundle.putInt("extra_uid", batterySipper.getUid());
        bundle.putLong("extra_background_time", processTimeMs);
        bundle.putLong("extra_foreground_time", j);
        bundle.putString("extra_power_usage_percent", str);
        bundle.putInt("extra_power_usage_amount", (int) batterySipper.totalPowerMah);
        new SubSettingLauncher(activity).setDestination(AdvancedPowerUsageDetail.class.getName()).setTitleRes(C1715R.string.battery_details_title).setArguments(bundle).setSourceMetricsCategory(instrumentedPreferenceFragment.getMetricsCategory()).setUserHandle(new UserHandle(getUserIdToLaunchAdvancePowerUsageDetail(batterySipper))).launch();
    }

    private static int getUserIdToLaunchAdvancePowerUsageDetail(BatterySipper batterySipper) {
        if (batterySipper.drainType == BatterySipper.DrainType.USER) {
            return ActivityManager.getCurrentUser();
        }
        return UserHandle.getUserId(batterySipper.getUid());
    }

    public static void startBatteryDetailPage(Activity activity, InstrumentedPreferenceFragment instrumentedPreferenceFragment, BatteryStatsHelper batteryStatsHelper, int i, BatteryEntry batteryEntry, String str) {
        startBatteryDetailPage(activity, BatteryUtils.getInstance(activity), instrumentedPreferenceFragment, batteryStatsHelper, i, batteryEntry, str);
    }

    public static void startBatteryDetailPage(Activity activity, InstrumentedPreferenceFragment instrumentedPreferenceFragment, String str) {
        Bundle bundle = new Bundle(3);
        PackageManager packageManager = activity.getPackageManager();
        bundle.putString("extra_package_name", str);
        bundle.putString("extra_power_usage_percent", Utils.formatPercentage(0));
        try {
            bundle.putInt("extra_uid", packageManager.getPackageUid(str, 0));
        } catch (PackageManager.NameNotFoundException e) {
            Log.w("AdvancedPowerDetail", "Cannot find package: " + str, e);
        }
        new SubSettingLauncher(activity).setDestination(AdvancedPowerUsageDetail.class.getName()).setTitleRes(C1715R.string.battery_details_title).setArguments(bundle).setSourceMetricsCategory(instrumentedPreferenceFragment.getMetricsCategory()).launch();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mState = ApplicationsState.getInstance(getActivity().getApplication());
        this.mBatteryUtils = BatteryUtils.getInstance(getContext());
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mPackageName = getArguments().getString("extra_package_name");
        this.mForegroundPreference = findPreference("app_usage_foreground");
        this.mBackgroundPreference = findPreference("app_usage_background");
        this.mHeaderPreference = (LayoutPreference) findPreference("header_view");
        String str = this.mPackageName;
        if (str != null) {
            this.mAppEntry = this.mState.getEntry(str, UserHandle.myUserId());
        }
    }

    public void onResume() {
        super.onResume();
        initHeader();
        initPreference();
    }

    /* access modifiers changed from: package-private */
    public void initHeader() {
        View findViewById = this.mHeaderPreference.findViewById(C1715R.C1718id.entity_header);
        FragmentActivity activity = getActivity();
        Bundle arguments = getArguments();
        EntityHeaderController buttonActions = EntityHeaderController.newInstance(activity, this, findViewById).setRecyclerView(getListView(), getSettingsLifecycle()).setButtonActions(0, 0);
        ApplicationsState.AppEntry appEntry = this.mAppEntry;
        if (appEntry == null) {
            buttonActions.setLabel((CharSequence) arguments.getString("extra_label"));
            if (arguments.getInt("extra_icon_id", 0) == 0) {
                buttonActions.setIcon(activity.getPackageManager().getDefaultActivityIcon());
            } else {
                buttonActions.setIcon(activity.getDrawable(arguments.getInt("extra_icon_id")));
            }
        } else {
            this.mState.ensureIcon(appEntry);
            buttonActions.setLabel(this.mAppEntry);
            buttonActions.setIcon(this.mAppEntry);
            AppUtils.isInstant(this.mAppEntry.info);
            buttonActions.setIsInstantApp(AppUtils.isInstant(this.mAppEntry.info));
        }
        buttonActions.done((Activity) activity, true);
    }

    /* access modifiers changed from: package-private */
    public void initPreference() {
        Bundle arguments = getArguments();
        Context context = getContext();
        long j = arguments.getLong("extra_foreground_time");
        long j2 = arguments.getLong("extra_background_time");
        this.mForegroundPreference.setSummary(TextUtils.expandTemplate(getText(C1715R.string.battery_used_for), new CharSequence[]{StringUtil.formatElapsedTime(context, (double) j, false)}));
        this.mBackgroundPreference.setSummary(TextUtils.expandTemplate(getText(C1715R.string.battery_active_for), new CharSequence[]{StringUtil.formatElapsedTime(context, (double) j2, false)}));
    }

    /* access modifiers changed from: protected */
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        ArrayList arrayList = new ArrayList();
        Bundle arguments = getArguments();
        int i = arguments.getInt("extra_uid", 0);
        String string = arguments.getString("extra_package_name");
        this.mBackgroundActivityPreferenceController = new BackgroundActivityPreferenceController(context, this, i, string);
        arrayList.add(this.mBackgroundActivityPreferenceController);
        arrayList.add(new BatteryOptimizationPreferenceController((SettingsActivity) getActivity(), this, string));
        this.mAppButtonsPreferenceController = new AppButtonsPreferenceController((SettingsActivity) getActivity(), this, getSettingsLifecycle(), string, this.mState, 0, 1);
        arrayList.add(this.mAppButtonsPreferenceController);
        return arrayList;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        AppButtonsPreferenceController appButtonsPreferenceController = this.mAppButtonsPreferenceController;
        if (appButtonsPreferenceController != null) {
            appButtonsPreferenceController.handleActivityResult(i, i2, intent);
        }
    }

    public void handleDialogClick(int i) {
        AppButtonsPreferenceController appButtonsPreferenceController = this.mAppButtonsPreferenceController;
        if (appButtonsPreferenceController != null) {
            appButtonsPreferenceController.handleDialogClick(i);
        }
    }

    public void onBatteryTipHandled(BatteryTip batteryTip) {
        BackgroundActivityPreferenceController backgroundActivityPreferenceController = this.mBackgroundActivityPreferenceController;
        backgroundActivityPreferenceController.updateSummary(findPreference(backgroundActivityPreferenceController.getPreferenceKey()));
    }
}
