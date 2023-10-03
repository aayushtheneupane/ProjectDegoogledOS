package com.android.settings.datausage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.AttributeSet;
import android.view.View;
import androidx.preference.PreferenceViewHolder;
import com.android.settings.applications.appinfo.AppInfoDashboardFragment;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.datausage.AppStateDataUsageBridge;
import com.android.settings.datausage.DataSaverBackend;
import com.android.settings.widget.AppSwitchPreference;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.RestrictedPreferenceHelper;
import com.android.settingslib.applications.ApplicationsState;
import com.havoc.config.center.C1715R;

public class UnrestrictedDataAccessPreference extends AppSwitchPreference implements DataSaverBackend.Listener {
    /* access modifiers changed from: private */
    public final ApplicationsState mApplicationsState;
    private final DataSaverBackend mDataSaverBackend;
    private final AppStateDataUsageBridge.DataUsageState mDataUsageState;
    /* access modifiers changed from: private */
    public final ApplicationsState.AppEntry mEntry;
    private final RestrictedPreferenceHelper mHelper;
    private final DashboardFragment mParentFragment;

    public void onDataSaverChanged(boolean z) {
    }

    public UnrestrictedDataAccessPreference(Context context, ApplicationsState.AppEntry appEntry, ApplicationsState applicationsState, DataSaverBackend dataSaverBackend, DashboardFragment dashboardFragment) {
        super(context);
        setWidgetLayoutResource(C1715R.layout.restricted_switch_widget);
        this.mHelper = new RestrictedPreferenceHelper(context, this, (AttributeSet) null);
        this.mEntry = appEntry;
        ApplicationsState.AppEntry appEntry2 = this.mEntry;
        this.mDataUsageState = (AppStateDataUsageBridge.DataUsageState) appEntry2.extraInfo;
        appEntry2.ensureLabel(context);
        this.mApplicationsState = applicationsState;
        this.mDataSaverBackend = dataSaverBackend;
        this.mParentFragment = dashboardFragment;
        setDisabledByAdmin(RestrictedLockUtilsInternal.checkIfMeteredDataRestricted(context, appEntry.info.packageName, UserHandle.getUserId(appEntry.info.uid)));
        updateState();
        setKey(generateKey(this.mEntry));
        Drawable drawable = this.mEntry.icon;
        if (drawable != null) {
            setIcon(drawable);
        }
    }

    static String generateKey(ApplicationsState.AppEntry appEntry) {
        return appEntry.info.packageName + "|" + appEntry.info.uid;
    }

    public void onAttached() {
        super.onAttached();
        this.mDataSaverBackend.addListener(this);
    }

    public void onDetached() {
        this.mDataSaverBackend.remListener(this);
        super.onDetached();
    }

    /* access modifiers changed from: protected */
    public void onClick() {
        if (this.mDataUsageState.isDataSaverBlacklisted) {
            AppInfoDashboardFragment.startAppInfoFragment(AppDataUsage.class, C1715R.string.data_usage_app_summary_title, (Bundle) null, this.mParentFragment, this.mEntry);
        } else {
            super.onClick();
        }
    }

    public void performClick() {
        if (!this.mHelper.performClick()) {
            super.performClick();
        }
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        if (this.mEntry.icon == null) {
            preferenceViewHolder.itemView.post(new Runnable() {
                public void run() {
                    UnrestrictedDataAccessPreference.this.mApplicationsState.ensureIcon(UnrestrictedDataAccessPreference.this.mEntry);
                    UnrestrictedDataAccessPreference unrestrictedDataAccessPreference = UnrestrictedDataAccessPreference.this;
                    unrestrictedDataAccessPreference.setIcon(unrestrictedDataAccessPreference.mEntry.icon);
                }
            });
        }
        boolean isDisabledByAdmin = isDisabledByAdmin();
        View findViewById = preferenceViewHolder.findViewById(16908312);
        if (isDisabledByAdmin) {
            findViewById.setVisibility(0);
        } else {
            AppStateDataUsageBridge.DataUsageState dataUsageState = this.mDataUsageState;
            findViewById.setVisibility((dataUsageState == null || !dataUsageState.isDataSaverBlacklisted) ? 0 : 4);
        }
        super.onBindViewHolder(preferenceViewHolder);
        this.mHelper.onBindViewHolder(preferenceViewHolder);
        int i = 8;
        preferenceViewHolder.findViewById(C1715R.C1718id.restricted_icon).setVisibility(isDisabledByAdmin ? 0 : 8);
        View findViewById2 = preferenceViewHolder.findViewById(16908352);
        if (!isDisabledByAdmin) {
            i = 0;
        }
        findViewById2.setVisibility(i);
    }

    public void onWhitelistStatusChanged(int i, boolean z) {
        AppStateDataUsageBridge.DataUsageState dataUsageState = this.mDataUsageState;
        if (dataUsageState != null && this.mEntry.info.uid == i) {
            dataUsageState.isDataSaverWhitelisted = z;
            updateState();
        }
    }

    public void onBlacklistStatusChanged(int i, boolean z) {
        AppStateDataUsageBridge.DataUsageState dataUsageState = this.mDataUsageState;
        if (dataUsageState != null && this.mEntry.info.uid == i) {
            dataUsageState.isDataSaverBlacklisted = z;
            updateState();
        }
    }

    public AppStateDataUsageBridge.DataUsageState getDataUsageState() {
        return this.mDataUsageState;
    }

    public ApplicationsState.AppEntry getEntry() {
        return this.mEntry;
    }

    public boolean isDisabledByAdmin() {
        return this.mHelper.isDisabledByAdmin();
    }

    public void setDisabledByAdmin(RestrictedLockUtils.EnforcedAdmin enforcedAdmin) {
        this.mHelper.setDisabledByAdmin(enforcedAdmin);
    }

    public void updateState() {
        setTitle((CharSequence) this.mEntry.label);
        AppStateDataUsageBridge.DataUsageState dataUsageState = this.mDataUsageState;
        if (dataUsageState != null) {
            setChecked(dataUsageState.isDataSaverWhitelisted);
            if (isDisabledByAdmin()) {
                setSummary((int) C1715R.string.disabled_by_admin);
            } else if (this.mDataUsageState.isDataSaverBlacklisted) {
                setSummary((int) C1715R.string.restrict_background_blacklisted);
            } else {
                setSummary((CharSequence) "");
            }
        }
        notifyChanged();
    }
}
