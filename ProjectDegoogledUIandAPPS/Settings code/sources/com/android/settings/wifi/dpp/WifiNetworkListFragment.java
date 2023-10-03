package com.android.settings.wifi.dpp;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.wifi.AddNetworkFragment;
import com.android.settingslib.wifi.AccessPoint;
import com.android.settingslib.wifi.AccessPointPreference;
import com.android.settingslib.wifi.WifiSavedConfigUtils;
import com.android.settingslib.wifi.WifiTracker;
import com.android.settingslib.wifi.WifiTrackerFactory;
import com.havoc.config.center.C1715R;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class WifiNetworkListFragment extends SettingsPreferenceFragment implements WifiTracker.WifiListener, AccessPoint.AccessPointListener {
    private PreferenceCategory mAccessPointsPreferenceCategory;
    private Preference mAddPreference;
    private Preference mFakeNetworkPreference;
    private boolean mIsTest;
    OnChooseNetworkListener mOnChooseNetworkListener;
    private WifiManager.ActionListener mSaveListener;
    private AccessPointPreference.UserBadgeCache mUserBadgeCache;
    private WifiManager mWifiManager;
    private WifiTracker mWifiTracker;

    public interface OnChooseNetworkListener {
        void onChooseNetwork(WifiNetworkConfig wifiNetworkConfig);
    }

    private String nullToEmpty(String str) {
        return str == null ? "" : str;
    }

    public int getMetricsCategory() {
        return 1595;
    }

    public void onConnectedChanged() {
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnChooseNetworkListener) {
            this.mOnChooseNetworkListener = (OnChooseNetworkListener) context;
            return;
        }
        throw new IllegalArgumentException("Invalid context type");
    }

    public void onDetach() {
        this.mOnChooseNetworkListener = null;
        super.onDetach();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mWifiTracker = WifiTrackerFactory.create(getActivity(), this, getSettingsLifecycle(), true, true);
        this.mWifiManager = this.mWifiTracker.getManager();
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mIsTest = arguments.getBoolean("test", false);
        }
        this.mSaveListener = new WifiManager.ActionListener() {
            public void onSuccess() {
            }

            public void onFailure(int i) {
                FragmentActivity activity = WifiNetworkListFragment.this.getActivity();
                if (activity != null) {
                    Toast.makeText(activity, C1715R.string.wifi_failed_save_message, 0).show();
                }
            }
        };
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1) {
            if (i2 == -1) {
                handleAddNetworkSubmitEvent(intent);
            }
            this.mWifiTracker.resumeScanning();
        }
    }

    public void onCreatePreferences(Bundle bundle, String str) {
        addPreferencesFromResource(C1715R.xml.wifi_dpp_network_list);
        this.mAccessPointsPreferenceCategory = (PreferenceCategory) findPreference("access_points");
        this.mFakeNetworkPreference = new Preference(getPrefContext());
        this.mFakeNetworkPreference.setIcon((int) C1715R.C1717drawable.ic_wifi_signal_0);
        this.mFakeNetworkPreference.setKey("fake_key");
        this.mFakeNetworkPreference.setTitle((CharSequence) "fake network");
        this.mAddPreference = new Preference(getPrefContext());
        this.mAddPreference.setIcon((int) C1715R.C1717drawable.ic_add_24dp);
        this.mAddPreference.setTitle((int) C1715R.string.wifi_add_network);
        this.mUserBadgeCache = new AccessPointPreference.UserBadgeCache(getPackageManager());
    }

    public void onWifiStateChanged(int i) {
        int wifiState = this.mWifiManager.getWifiState();
        if (wifiState == 0 || wifiState == 2) {
            removeAccessPointPreferences();
        } else if (wifiState == 3) {
            updateAccessPointPreferences();
        }
    }

    public void onAccessPointsChanged() {
        updateAccessPointPreferences();
    }

    public void onAccessPointChanged(AccessPoint accessPoint) {
        Log.d("WifiNetworkListFragment", "onAccessPointChanged (singular) callback initiated");
        View view = getView();
        if (view != null) {
            view.post(new Runnable() {
                public final void run() {
                    WifiNetworkListFragment.lambda$onAccessPointChanged$0(AccessPoint.this);
                }
            });
        }
    }

    static /* synthetic */ void lambda$onAccessPointChanged$0(AccessPoint accessPoint) {
        Object tag = accessPoint.getTag();
        if (tag != null) {
            ((AccessPointPreference) tag).refresh();
        }
    }

    public void onLevelChanged(AccessPoint accessPoint) {
        ((AccessPointPreference) accessPoint.getTag()).onLevelChanged();
    }

    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference instanceof AccessPointPreference) {
            AccessPoint accessPoint = ((AccessPointPreference) preference).getAccessPoint();
            if (accessPoint == null) {
                return false;
            }
            WifiConfiguration config = accessPoint.getConfig();
            if (config != null) {
                WifiNetworkConfig validConfigOrNull = WifiNetworkConfig.getValidConfigOrNull(accessPoint.getSecurityString(true), config.getPrintableSsid(), config.preSharedKey, config.hiddenSSID, config.networkId, false);
                OnChooseNetworkListener onChooseNetworkListener = this.mOnChooseNetworkListener;
                if (onChooseNetworkListener != null) {
                    onChooseNetworkListener.onChooseNetwork(validConfigOrNull);
                }
            } else {
                throw new IllegalArgumentException("Invalid access point");
            }
        } else if (preference == this.mAddPreference) {
            launchAddNetworkFragment();
        } else if (preference != this.mFakeNetworkPreference) {
            return super.onPreferenceTreeClick(preference);
        } else {
            OnChooseNetworkListener onChooseNetworkListener2 = this.mOnChooseNetworkListener;
            if (onChooseNetworkListener2 != null) {
                onChooseNetworkListener2.onChooseNetwork(new WifiNetworkConfig("WPA", "fake network", "password", true, -1, false));
            }
        }
        return true;
    }

    private void handleAddNetworkSubmitEvent(Intent intent) {
        WifiConfiguration wifiConfiguration = (WifiConfiguration) intent.getParcelableExtra("wifi_config_key");
        if (wifiConfiguration != null) {
            this.mWifiManager.save(wifiConfiguration, this.mSaveListener);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: isValidForDppConfiguration */
    public boolean lambda$updateAccessPointPreferences$1$WifiNetworkListFragment(AccessPoint accessPoint) {
        int security = accessPoint.getSecurity();
        return security == 2 || security == 5;
    }

    private void launchAddNetworkFragment() {
        new SubSettingLauncher(getContext()).setTitleRes(C1715R.string.wifi_add_network).setDestination(AddNetworkFragment.class.getName()).setSourceMetricsCategory(getMetricsCategory()).setResultListener(this, 1).launch();
    }

    private void removeAccessPointPreferences() {
        this.mAccessPointsPreferenceCategory.removeAll();
        this.mAccessPointsPreferenceCategory.setVisible(false);
    }

    private void updateAccessPointPreferences() {
        if (this.mWifiManager.isWifiEnabled()) {
            int i = 0;
            this.mAccessPointsPreferenceCategory.removeAll();
            for (AccessPoint accessPoint : (List) WifiSavedConfigUtils.getAllConfigs(getContext(), this.mWifiManager).stream().filter(new Predicate() {
                public final boolean test(Object obj) {
                    return WifiNetworkListFragment.this.lambda$updateAccessPointPreferences$1$WifiNetworkListFragment((AccessPoint) obj);
                }
            }).map(new Function() {
                public final Object apply(Object obj) {
                    return WifiNetworkListFragment.this.lambda$updateAccessPointPreferences$2$WifiNetworkListFragment((AccessPoint) obj);
                }
            }).sorted(new Comparator() {
                public final int compare(Object obj, Object obj2) {
                    return WifiNetworkListFragment.this.lambda$updateAccessPointPreferences$3$WifiNetworkListFragment((AccessPoint) obj, (AccessPoint) obj2);
                }
            }).collect(Collectors.toList())) {
                AccessPointPreference createAccessPointPreference = createAccessPointPreference(accessPoint);
                createAccessPointPreference.setOrder(i);
                createAccessPointPreference.setEnabled(accessPoint.isReachable());
                accessPoint.setListener(this);
                createAccessPointPreference.refresh();
                this.mAccessPointsPreferenceCategory.addPreference(createAccessPointPreference);
                i++;
            }
            this.mAddPreference.setOrder(i);
            this.mAccessPointsPreferenceCategory.addPreference(this.mAddPreference);
            if (this.mIsTest) {
                this.mAccessPointsPreferenceCategory.addPreference(this.mFakeNetworkPreference);
            }
        }
    }

    public /* synthetic */ int lambda$updateAccessPointPreferences$3$WifiNetworkListFragment(AccessPoint accessPoint, AccessPoint accessPoint2) {
        if (accessPoint.isReachable() && !accessPoint2.isReachable()) {
            return -1;
        }
        if (accessPoint.isReachable() || !accessPoint2.isReachable()) {
            return nullToEmpty(accessPoint.getTitle()).compareToIgnoreCase(nullToEmpty(accessPoint2.getTitle()));
        }
        return 1;
    }

    /* access modifiers changed from: private */
    /* renamed from: getScannedAccessPointIfAvailable */
    public AccessPoint lambda$updateAccessPointPreferences$2$WifiNetworkListFragment(AccessPoint accessPoint) {
        List<AccessPoint> accessPoints = this.mWifiTracker.getAccessPoints();
        WifiConfiguration config = accessPoint.getConfig();
        for (AccessPoint next : accessPoints) {
            if (next.matches(config)) {
                return next;
            }
        }
        return accessPoint;
    }

    private AccessPointPreference createAccessPointPreference(AccessPoint accessPoint) {
        return new AccessPointPreference(accessPoint, getPrefContext(), this.mUserBadgeCache, C1715R.C1717drawable.ic_wifi_signal_0, false);
    }
}
