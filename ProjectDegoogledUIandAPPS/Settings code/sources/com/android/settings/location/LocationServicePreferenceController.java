package com.android.settings.location;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.UserHandle;
import android.util.Log;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceScreen;
import com.android.settings.Utils;
import com.android.settings.widget.RestrictedAppPreference;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnPause;
import com.android.settingslib.core.lifecycle.events.OnResume;
import java.util.List;
import java.util.Map;

public class LocationServicePreferenceController extends LocationBasePreferenceController implements LifecycleObserver, OnResume, OnPause {
    static final IntentFilter INTENT_FILTER_INJECTED_SETTING_CHANGED = new IntentFilter("android.location.InjectedSettingChanged");
    private PreferenceCategory mCategoryLocationServices;
    private PreferenceCategory mCategoryLocationServicesManaged;
    private final LocationSettings mFragment;
    BroadcastReceiver mInjectedSettingsReceiver;
    /* access modifiers changed from: private */
    public final AppSettingsInjector mInjector;

    public String getPreferenceKey() {
        return "location_services";
    }

    public LocationServicePreferenceController(Context context, LocationSettings locationSettings, Lifecycle lifecycle) {
        this(context, locationSettings, lifecycle, new AppSettingsInjector(context));
    }

    LocationServicePreferenceController(Context context, LocationSettings locationSettings, Lifecycle lifecycle, AppSettingsInjector appSettingsInjector) {
        super(context, lifecycle);
        this.mFragment = locationSettings;
        this.mInjector = appSettingsInjector;
        if (lifecycle != null) {
            lifecycle.addObserver(this);
        }
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mCategoryLocationServices = (PreferenceCategory) preferenceScreen.findPreference("location_services");
        this.mCategoryLocationServicesManaged = (PreferenceCategory) preferenceScreen.findPreference("location_services_managed_profile");
    }

    public void updateState(Preference preference) {
        this.mCategoryLocationServices.removeAll();
        this.mCategoryLocationServicesManaged.removeAll();
        boolean z = false;
        boolean z2 = false;
        for (Map.Entry next : getLocationServices().entrySet()) {
            for (Preference preference2 : (List) next.getValue()) {
                if (preference2 instanceof RestrictedAppPreference) {
                    ((RestrictedAppPreference) preference2).checkRestrictionAndSetDisabled();
                }
            }
            if (((Integer) next.getKey()).intValue() == UserHandle.myUserId()) {
                LocationSettings.addPreferencesSorted((List) next.getValue(), this.mCategoryLocationServices);
                z = true;
            } else {
                LocationSettings.addPreferencesSorted((List) next.getValue(), this.mCategoryLocationServicesManaged);
                z2 = true;
            }
        }
        this.mCategoryLocationServices.setVisible(z);
        this.mCategoryLocationServicesManaged.setVisible(z2);
    }

    public void onLocationModeChanged(int i, boolean z) {
        this.mInjector.reloadStatusMessages();
    }

    public void onResume() {
        if (this.mInjectedSettingsReceiver == null) {
            this.mInjectedSettingsReceiver = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (Log.isLoggable("LocationServicePrefCtrl", 3)) {
                        Log.d("LocationServicePrefCtrl", "Received settings change intent: " + intent);
                    }
                    LocationServicePreferenceController.this.mInjector.reloadStatusMessages();
                }
            };
        }
        this.mContext.registerReceiver(this.mInjectedSettingsReceiver, INTENT_FILTER_INJECTED_SETTING_CHANGED);
    }

    public void onPause() {
        this.mContext.unregisterReceiver(this.mInjectedSettingsReceiver);
    }

    private Map<Integer, List<Preference>> getLocationServices() {
        int managedProfileId = Utils.getManagedProfileId(this.mUserManager, UserHandle.myUserId());
        return this.mInjector.getInjectedSettings(this.mFragment.getPreferenceManager().getContext(), (managedProfileId == -10000 || this.mLocationEnabler.getShareLocationEnforcedAdmin(managedProfileId) == null) ? -2 : UserHandle.myUserId());
    }
}
