package com.android.settings.applications.appinfo;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import androidx.preference.PreferenceScreen;
import com.android.settings.applications.appinfo.AppInfoDashboardFragment;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.widget.EntityHeaderController;
import com.android.settingslib.applications.AppUtils;
import com.android.settingslib.applications.ApplicationsState;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.widget.LayoutPreference;
import com.havoc.config.center.C1715R;

public class AppHeaderViewPreferenceController extends BasePreferenceController implements AppInfoDashboardFragment.Callback, LifecycleObserver, OnStart {
    private static final String KEY_HEADER = "header_view";
    private EntityHeaderController mEntityHeaderController;
    private LayoutPreference mHeader;
    private final Lifecycle mLifecycle;
    private final String mPackageName;
    private final AppInfoDashboardFragment mParent;

    public int getAvailabilityStatus() {
        return 0;
    }

    public AppHeaderViewPreferenceController(Context context, AppInfoDashboardFragment appInfoDashboardFragment, String str, Lifecycle lifecycle) {
        super(context, KEY_HEADER);
        this.mParent = appInfoDashboardFragment;
        this.mPackageName = str;
        this.mLifecycle = lifecycle;
        Lifecycle lifecycle2 = this.mLifecycle;
        if (lifecycle2 != null) {
            lifecycle2.addObserver(this);
        }
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mHeader = (LayoutPreference) preferenceScreen.findPreference(KEY_HEADER);
        this.mEntityHeaderController = EntityHeaderController.newInstance(this.mParent.getActivity(), this.mParent, this.mHeader.findViewById(C1715R.C1718id.entity_header)).setPackageName(this.mPackageName).setButtonActions(0, 0).bindHeaderButtons();
    }

    public void onStart() {
        this.mEntityHeaderController.setRecyclerView(this.mParent.getListView(), this.mLifecycle).styleActionBar(this.mParent.getActivity());
    }

    public void refreshUi() {
        setAppLabelAndIcon(this.mParent.getPackageInfo(), this.mParent.getAppEntry());
    }

    private void setAppLabelAndIcon(PackageInfo packageInfo, ApplicationsState.AppEntry appEntry) {
        this.mEntityHeaderController.setLabel(appEntry).setIcon(appEntry).setIsInstantApp(AppUtils.isInstant(packageInfo.applicationInfo)).done((Activity) this.mParent.getActivity(), false);
    }
}
