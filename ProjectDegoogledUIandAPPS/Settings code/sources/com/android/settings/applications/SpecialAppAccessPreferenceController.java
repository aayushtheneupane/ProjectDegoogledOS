package com.android.settings.applications;

import android.app.Application;
import android.content.Context;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.applications.AppStateBaseBridge;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.datausage.AppStateDataUsageBridge;
import com.android.settings.datausage.DataSaverBackend;
import com.android.settingslib.applications.ApplicationsState;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnDestroy;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Iterator;

public class SpecialAppAccessPreferenceController extends BasePreferenceController implements AppStateBaseBridge.Callback, ApplicationsState.Callbacks, LifecycleObserver, OnStart, OnStop, OnDestroy {
    private final ApplicationsState mApplicationsState;
    private final DataSaverBackend mDataSaverBackend;
    private final AppStateDataUsageBridge mDataUsageBridge = new AppStateDataUsageBridge(this.mApplicationsState, this, this.mDataSaverBackend);
    private boolean mExtraLoaded;
    private Preference mPreference;
    ApplicationsState.Session mSession;

    public int getAvailabilityStatus() {
        return 1;
    }

    public void onAllSizesComputed() {
    }

    public void onLauncherInfoChanged() {
    }

    public void onLoadEntriesCompleted() {
    }

    public void onPackageIconChanged() {
    }

    public void onPackageListChanged() {
    }

    public void onPackageSizeChanged(String str) {
    }

    public void onRebuildComplete(ArrayList<ApplicationsState.AppEntry> arrayList) {
    }

    public void onRunningStateChanged(boolean z) {
    }

    public SpecialAppAccessPreferenceController(Context context, String str) {
        super(context, str);
        this.mApplicationsState = ApplicationsState.getInstance((Application) context.getApplicationContext());
        this.mDataSaverBackend = new DataSaverBackend(context);
    }

    public void setSession(Lifecycle lifecycle) {
        this.mSession = this.mApplicationsState.newSession(this, lifecycle);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = preferenceScreen.findPreference(getPreferenceKey());
    }

    public void onStart() {
        this.mDataUsageBridge.resume();
    }

    public void onStop() {
        this.mDataUsageBridge.pause();
    }

    public void onDestroy() {
        this.mDataUsageBridge.release();
    }

    public void updateState(Preference preference) {
        updateSummary();
    }

    public void onExtraInfoUpdated() {
        this.mExtraLoaded = true;
        updateSummary();
    }

    private void updateSummary() {
        if (this.mExtraLoaded && this.mPreference != null) {
            Iterator<ApplicationsState.AppEntry> it = this.mSession.getAllApps().iterator();
            int i = 0;
            while (it.hasNext()) {
                ApplicationsState.AppEntry next = it.next();
                if (ApplicationsState.FILTER_DOWNLOADED_AND_LAUNCHER.filterApp(next)) {
                    Object obj = next.extraInfo;
                    if ((obj instanceof AppStateDataUsageBridge.DataUsageState) && ((AppStateDataUsageBridge.DataUsageState) obj).isDataSaverWhitelisted) {
                        i++;
                    }
                }
            }
            this.mPreference.setSummary((CharSequence) this.mContext.getResources().getQuantityString(C1715R.plurals.special_access_summary, i, new Object[]{Integer.valueOf(i)}));
        }
    }
}
