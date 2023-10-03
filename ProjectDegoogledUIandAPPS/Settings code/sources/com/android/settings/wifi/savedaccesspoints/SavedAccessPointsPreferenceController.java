package com.android.settings.wifi.savedaccesspoints;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.wifi.WifiManager;
import android.util.Log;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.utils.PreferenceGroupChildrenCache;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.utils.ThreadUtils;
import com.android.settingslib.wifi.AccessPoint;
import com.android.settingslib.wifi.AccessPointPreference;
import com.android.settingslib.wifi.WifiSavedConfigUtils;
import com.havoc.config.center.C1715R;
import java.util.Collections;
import java.util.List;

public class SavedAccessPointsPreferenceController extends BasePreferenceController implements LifecycleObserver, OnStart, Preference.OnPreferenceClickListener, WifiManager.ActionListener {
    private static final String TAG = "SavedAPPrefCtrl";
    private final PreferenceGroupChildrenCache mChildrenCache = new PreferenceGroupChildrenCache();
    private SavedAccessPointsWifiSettings mHost;
    private PreferenceGroup mPreferenceGroup;
    private final AccessPointPreference.UserBadgeCache mUserBadgeCache;
    private final WifiManager mWifiManager;

    public int getAvailabilityStatus() {
        return 0;
    }

    public SavedAccessPointsPreferenceController(Context context, String str) {
        super(context, str);
        this.mUserBadgeCache = new AccessPointPreference.UserBadgeCache(context.getPackageManager());
        this.mWifiManager = (WifiManager) context.getSystemService(WifiManager.class);
    }

    public SavedAccessPointsPreferenceController setHost(SavedAccessPointsWifiSettings savedAccessPointsWifiSettings) {
        this.mHost = savedAccessPointsWifiSettings;
        return this;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreferenceGroup = (PreferenceGroup) preferenceScreen.findPreference(getPreferenceKey());
    }

    public void onStart() {
        mo12753x4468e3bd();
    }

    public void postRefreshSavedAccessPoints() {
        ThreadUtils.postOnMainThread(new Runnable() {
            public final void run() {
                SavedAccessPointsPreferenceController.this.mo12753x4468e3bd();
            }
        });
    }

    public boolean onPreferenceClick(Preference preference) {
        SavedAccessPointsWifiSettings savedAccessPointsWifiSettings = this.mHost;
        if (savedAccessPointsWifiSettings == null) {
            return false;
        }
        savedAccessPointsWifiSettings.showWifiPage((AccessPointPreference) preference);
        return false;
    }

    public void onSuccess() {
        postRefreshSavedAccessPoints();
    }

    public void onFailure(int i) {
        postRefreshSavedAccessPoints();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: refreshSavedAccessPoints */
    public void mo12753x4468e3bd() {
        PreferenceGroup preferenceGroup = this.mPreferenceGroup;
        if (preferenceGroup == null) {
            Log.w(TAG, "PreferenceGroup is null, skipping.");
            return;
        }
        Context context = preferenceGroup.getContext();
        List<AccessPoint> allConfigs = WifiSavedConfigUtils.getAllConfigs(this.mContext, this.mWifiManager);
        Collections.sort(allConfigs, SavedNetworkComparator.INSTANCE);
        this.mChildrenCache.cacheRemoveAllPrefs(this.mPreferenceGroup);
        int size = allConfigs.size();
        for (int i = 0; i < size; i++) {
            AccessPoint accessPoint = allConfigs.get(i);
            SavedAccessPointsWifiSettings savedAccessPointsWifiSettings = this.mHost;
            if (savedAccessPointsWifiSettings == null || !savedAccessPointsWifiSettings.isSubscriptionsFeatureEnabled() || !accessPoint.isPasspointConfig()) {
                String key = accessPoint.getKey();
                AccessPointPreference accessPointPreference = (AccessPointPreference) this.mChildrenCache.getCachedPreference(key);
                if (accessPointPreference == null) {
                    accessPointPreference = new AccessPointPreference(accessPoint, context, this.mUserBadgeCache, true);
                    accessPointPreference.setKey(key);
                    accessPointPreference.setIcon((Drawable) null);
                    accessPointPreference.setOnPreferenceClickListener(this);
                    this.mPreferenceGroup.addPreference(accessPointPreference);
                }
                accessPointPreference.setOrder(i);
            }
        }
        this.mChildrenCache.removeCachedPrefs(this.mPreferenceGroup);
        if (this.mPreferenceGroup.getPreferenceCount() < 1) {
            Log.w(TAG, "Saved networks activity loaded, but there are no saved networks!");
            this.mPreferenceGroup.setVisible(false);
        } else {
            this.mPreferenceGroup.setVisible(true);
        }
        SavedAccessPointsWifiSettings savedAccessPointsWifiSettings2 = this.mHost;
        if (savedAccessPointsWifiSettings2 != null && !savedAccessPointsWifiSettings2.isSubscriptionsFeatureEnabled()) {
            this.mPreferenceGroup.setVisible(true);
            this.mPreferenceGroup.setTitle((CharSequence) null);
            this.mPreferenceGroup.setLayoutResource(C1715R.layout.preference_category_no_label);
        }
    }
}
