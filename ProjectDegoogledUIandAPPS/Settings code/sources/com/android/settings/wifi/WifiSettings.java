package com.android.settings.wifi;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.NetworkTemplate;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.provider.Settings;
import android.util.FeatureFlagUtils;
import android.util.Log;
import android.util.Pair;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import com.android.settings.LinkifyUtils;
import com.android.settings.RestrictedSettingsFragment;
import com.android.settings.SettingsActivity;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.dashboard.SummaryLoader;
import com.android.settings.datausage.DataUsagePreference;
import com.android.settings.datausage.DataUsageUtils;
import com.android.settings.datausage.TemplatePreference;
import com.android.settings.location.ScanningSettings;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settings.search.SearchIndexableRaw;
import com.android.settings.widget.SummaryUpdater;
import com.android.settings.widget.SwitchBarController;
import com.android.settings.wifi.ConnectedAccessPointPreference;
import com.android.settings.wifi.WifiDialog;
import com.android.settings.wifi.details.WifiNetworkDetailsFragment;
import com.android.settings.wifi.dpp.WifiDppUtils;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.wifi.AccessPoint;
import com.android.settingslib.wifi.AccessPointPreference;
import com.android.settingslib.wifi.WifiSavedConfigUtils;
import com.android.settingslib.wifi.WifiTracker;
import com.android.settingslib.wifi.WifiTrackerFactory;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class WifiSettings extends RestrictedSettingsFragment implements Indexable, WifiTracker.WifiListener, AccessPoint.AccessPointListener, WifiDialog.WifiDialogListener, DialogInterface.OnDismissListener {
    static final int ADD_NETWORK_REQUEST = 2;
    static final int MENU_ID_FORGET = 8;
    static final String PREF_KEY_DATA_USAGE = "wifi_data_usage";
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableRaw> getRawDataToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            Resources resources = context.getResources();
            if (resources.getBoolean(C1715R.bool.config_show_wifi_settings)) {
                SearchIndexableRaw searchIndexableRaw = new SearchIndexableRaw(context);
                searchIndexableRaw.title = resources.getString(C1715R.string.wifi_settings);
                searchIndexableRaw.screenTitle = resources.getString(C1715R.string.wifi_settings);
                searchIndexableRaw.keywords = resources.getString(C1715R.string.keywords_wifi);
                searchIndexableRaw.key = "main_toggle_wifi";
                arrayList.add(searchIndexableRaw);
            }
            return arrayList;
        }
    };
    public static final SummaryLoader.SummaryProviderFactory SUMMARY_PROVIDER_FACTORY = new SummaryLoader.SummaryProviderFactory() {
        public SummaryLoader.SummaryProvider createSummaryProvider(Activity activity, SummaryLoader summaryLoader) {
            return new SummaryProvider(activity, summaryLoader);
        }
    };
    private Bundle mAccessPointSavedState;
    private PreferenceCategory mAccessPointsPreferenceCategory;
    AddWifiNetworkPreference mAddWifiNetworkPreference;
    private CaptivePortalNetworkCallback mCaptivePortalNetworkCallback;
    private boolean mClickedConnect;
    Preference mConfigureWifiSettingsPreference;
    private WifiManager.ActionListener mConnectListener;
    private PreferenceCategory mConnectedAccessPointPreferenceCategory;
    private ConnectivityManager mConnectivityManager;
    DataUsagePreference mDataUsagePreference;
    private WifiDialog mDialog;
    private int mDialogMode;
    private AccessPoint mDlgAccessPoint;
    private boolean mEnableNextOnConnection;
    private WifiManager.ActionListener mForgetListener;
    private final Runnable mHideProgressBarRunnable = new Runnable() {
        public final void run() {
            WifiSettings.this.lambda$new$1$WifiSettings();
        }
    };
    private boolean mIsRestricted;
    private String mOpenSsid;
    private View mProgressHeader;
    private WifiManager.ActionListener mSaveListener;
    Preference mSavedNetworksPreference;
    private AccessPoint mSelectedAccessPoint;
    private LinkablePreference mStatusMessagePreference;
    private final Runnable mUpdateAccessPointsRunnable = new Runnable() {
        public final void run() {
            WifiSettings.this.lambda$new$0$WifiSettings();
        }
    };
    private AccessPointPreference.UserBadgeCache mUserBadgeCache;
    private WifiEnabler mWifiEnabler;
    protected WifiManager mWifiManager;
    WifiTracker mWifiTracker;

    public int getDialogMetricsCategory(int i) {
        return i != 1 ? 0 : 603;
    }

    public int getHelpResource() {
        return C1715R.string.help_url_wifi;
    }

    public int getMetricsCategory() {
        return 103;
    }

    private static boolean isVerboseLoggingEnabled() {
        return WifiTracker.sVerboseLogging || Log.isLoggable("WifiSettings", 2);
    }

    public /* synthetic */ void lambda$new$1$WifiSettings() {
        setProgressBarVisible(false);
    }

    public WifiSettings() {
        super("no_config_wifi");
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            this.mProgressHeader = setPinnedHeaderView((int) C1715R.layout.progress_header).findViewById(C1715R.C1718id.progress_bar_animation);
            setProgressBarVisible(false);
        }
        ((SettingsActivity) activity).getSwitchBar().setSwitchBarText(C1715R.string.wifi_settings_master_switch_title, C1715R.string.wifi_settings_master_switch_title);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setAnimationAllowed(false);
        addPreferences();
        this.mIsRestricted = isUiRestricted();
    }

    private void addPreferences() {
        addPreferencesFromResource(C1715R.xml.wifi_settings);
        this.mConnectedAccessPointPreferenceCategory = (PreferenceCategory) findPreference("connected_access_point");
        this.mAccessPointsPreferenceCategory = (PreferenceCategory) findPreference("access_points");
        this.mConfigureWifiSettingsPreference = findPreference("configure_settings");
        this.mSavedNetworksPreference = findPreference("saved_networks");
        this.mAddWifiNetworkPreference = new AddWifiNetworkPreference(getPrefContext());
        this.mStatusMessagePreference = (LinkablePreference) findPreference("wifi_status_message");
        this.mUserBadgeCache = new AccessPointPreference.UserBadgeCache(getPackageManager());
        this.mDataUsagePreference = (DataUsagePreference) findPreference(PREF_KEY_DATA_USAGE);
        this.mDataUsagePreference.setVisible(DataUsageUtils.hasWifiRadio(getContext()));
        this.mDataUsagePreference.setTemplate(NetworkTemplate.buildTemplateWifiWildcard(), 0, (TemplatePreference.NetworkServices) null);
    }

    public void onActivityCreated(Bundle bundle) {
        ConnectivityManager connectivityManager;
        super.onActivityCreated(bundle);
        this.mWifiTracker = WifiTrackerFactory.create(getActivity(), this, getSettingsLifecycle(), true, true);
        this.mWifiManager = this.mWifiTracker.getManager();
        if (getActivity() != null) {
            this.mConnectivityManager = (ConnectivityManager) getActivity().getSystemService(ConnectivityManager.class);
        }
        this.mConnectListener = new WifiConnectListener(getActivity());
        this.mSaveListener = new WifiManager.ActionListener() {
            public void onSuccess() {
            }

            public void onFailure(int i) {
                FragmentActivity activity = WifiSettings.this.getActivity();
                if (activity != null) {
                    Toast.makeText(activity, C1715R.string.wifi_failed_save_message, 0).show();
                }
            }
        };
        this.mForgetListener = new WifiManager.ActionListener() {
            public void onSuccess() {
            }

            public void onFailure(int i) {
                FragmentActivity activity = WifiSettings.this.getActivity();
                if (activity != null) {
                    Toast.makeText(activity, C1715R.string.wifi_failed_forget_message, 0).show();
                }
            }
        };
        if (bundle != null) {
            this.mDialogMode = bundle.getInt("dialog_mode");
            if (bundle.containsKey("wifi_ap_state")) {
                this.mAccessPointSavedState = bundle.getBundle("wifi_ap_state");
            }
        }
        Intent intent = getActivity().getIntent();
        this.mEnableNextOnConnection = intent.getBooleanExtra("wifi_enable_next_on_connect", false);
        if (this.mEnableNextOnConnection && hasNextButton() && (connectivityManager = (ConnectivityManager) getActivity().getSystemService("connectivity")) != null) {
            changeNextButtonState(connectivityManager.getNetworkInfo(1).isConnected());
        }
        registerForContextMenu(getListView());
        setHasOptionsMenu(true);
        if (intent.hasExtra("wifi_start_connect_ssid")) {
            this.mOpenSsid = intent.getStringExtra("wifi_start_connect_ssid");
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        WifiEnabler wifiEnabler = this.mWifiEnabler;
        if (wifiEnabler != null) {
            wifiEnabler.teardownSwitchController();
        }
    }

    public void onStart() {
        super.onStart();
        this.mWifiEnabler = createWifiEnabler();
        if (this.mIsRestricted) {
            restrictUi();
        } else {
            onWifiStateChanged(this.mWifiManager.getWifiState());
        }
    }

    private void restrictUi() {
        if (!isUiRestrictedByOnlyAdmin()) {
            getEmptyTextView().setText(C1715R.string.wifi_empty_list_user_restricted);
        }
        getPreferenceScreen().removeAll();
    }

    private WifiEnabler createWifiEnabler() {
        SettingsActivity settingsActivity = (SettingsActivity) getActivity();
        return new WifiEnabler(settingsActivity, new SwitchBarController(settingsActivity.getSwitchBar()), this.mMetricsFeatureProvider);
    }

    public void onResume() {
        FragmentActivity activity = getActivity();
        super.onResume();
        boolean z = this.mIsRestricted;
        this.mIsRestricted = isUiRestricted();
        if (!z && this.mIsRestricted) {
            restrictUi();
        }
        WifiEnabler wifiEnabler = this.mWifiEnabler;
        if (wifiEnabler != null) {
            wifiEnabler.resume(activity);
        }
    }

    public void onPause() {
        super.onPause();
        WifiEnabler wifiEnabler = this.mWifiEnabler;
        if (wifiEnabler != null) {
            wifiEnabler.pause();
        }
    }

    public void onStop() {
        getView().removeCallbacks(this.mUpdateAccessPointsRunnable);
        getView().removeCallbacks(this.mHideProgressBarRunnable);
        unregisterCaptivePortalNetworkCallback();
        super.onStop();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 2) {
            handleAddNetworkRequest(i2, intent);
        } else if (i != 0) {
            boolean z = this.mIsRestricted;
            this.mIsRestricted = isUiRestricted();
            if (z && !this.mIsRestricted && getPreferenceScreen().getPreferenceCount() == 0) {
                addPreferences();
            }
        } else if (i2 == -1) {
            WifiDialog wifiDialog = this.mDialog;
            if (wifiDialog != null) {
                wifiDialog.dismiss();
            }
            this.mWifiTracker.resumeScanning();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.mDialog != null) {
            bundle.putInt("dialog_mode", this.mDialogMode);
            if (this.mDlgAccessPoint != null) {
                this.mAccessPointSavedState = new Bundle();
                this.mDlgAccessPoint.saveWifiState(this.mAccessPointSavedState);
                bundle.putBundle("wifi_ap_state", this.mAccessPointSavedState);
            }
        }
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        Preference preference = (Preference) view.getTag();
        if (preference instanceof LongPressAccessPointPreference) {
            this.mSelectedAccessPoint = ((LongPressAccessPointPreference) preference).getAccessPoint();
            contextMenu.setHeaderTitle(this.mSelectedAccessPoint.getTitle());
            if (this.mSelectedAccessPoint.isConnectable()) {
                contextMenu.add(0, 7, 0, C1715R.string.wifi_connect);
            }
            if (!WifiUtils.isNetworkLockedDown(getActivity(), this.mSelectedAccessPoint.getConfig())) {
                if (this.mSelectedAccessPoint.isSaved() || this.mSelectedAccessPoint.isEphemeral()) {
                    contextMenu.add(0, 8, 0, this.mSelectedAccessPoint.isEphemeral() ? C1715R.string.wifi_disconnect_button_text : C1715R.string.forget);
                }
                if (this.mSelectedAccessPoint.isSaved() && !this.mSelectedAccessPoint.isActive()) {
                    contextMenu.add(0, 9, 0, C1715R.string.wifi_modify);
                }
            }
        }
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        if (this.mSelectedAccessPoint == null) {
            return super.onContextItemSelected(menuItem);
        }
        int itemId = menuItem.getItemId();
        if (itemId == 7) {
            boolean isSaved = this.mSelectedAccessPoint.isSaved();
            if (isSaved) {
                connect(this.mSelectedAccessPoint.getConfig(), isSaved);
            } else if (this.mSelectedAccessPoint.getSecurity() == 0 || this.mSelectedAccessPoint.getSecurity() == 4) {
                this.mSelectedAccessPoint.generateOpenNetworkConfig();
                connect(this.mSelectedAccessPoint.getConfig(), isSaved);
            } else {
                showDialog(this.mSelectedAccessPoint, 1);
            }
            return true;
        } else if (itemId == 8) {
            forget();
            return true;
        } else if (itemId != 9) {
            return super.onContextItemSelected(menuItem);
        } else {
            showDialog(this.mSelectedAccessPoint, 2);
            return true;
        }
    }

    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference.getFragment() != null) {
            preference.setOnPreferenceClickListener((Preference.OnPreferenceClickListener) null);
            return super.onPreferenceTreeClick(preference);
        }
        if (preference instanceof LongPressAccessPointPreference) {
            this.mSelectedAccessPoint = ((LongPressAccessPointPreference) preference).getAccessPoint();
            AccessPoint accessPoint = this.mSelectedAccessPoint;
            if (accessPoint == null) {
                return false;
            }
            if (accessPoint.isActive()) {
                return super.onPreferenceTreeClick(preference);
            }
            int connectingType = WifiUtils.getConnectingType(this.mSelectedAccessPoint);
            if (connectingType == 1) {
                this.mSelectedAccessPoint.generateOpenNetworkConfig();
                connect(this.mSelectedAccessPoint.getConfig(), this.mSelectedAccessPoint.isSaved());
            } else if (connectingType == 2) {
                connect(this.mSelectedAccessPoint.getConfig(), true);
            } else if (connectingType != 3) {
                showDialog(this.mSelectedAccessPoint, 1);
            } else {
                this.mSelectedAccessPoint.startOsuProvisioning(this.mConnectListener);
                this.mClickedConnect = true;
            }
        } else if (preference != this.mAddWifiNetworkPreference) {
            return super.onPreferenceTreeClick(preference);
        } else {
            onAddNetworkPressed();
        }
        return true;
    }

    private void showDialog(AccessPoint accessPoint, int i) {
        if (accessPoint != null) {
            if (WifiUtils.isNetworkLockedDown(getActivity(), accessPoint.getConfig()) && accessPoint.isActive()) {
                RestrictedLockUtils.sendShowAdminSupportDetailsIntent(getActivity(), RestrictedLockUtilsInternal.getDeviceOwner(getActivity()));
                return;
            }
        }
        if (this.mDialog != null) {
            removeDialog(1);
            this.mDialog = null;
        }
        this.mDlgAccessPoint = accessPoint;
        this.mDialogMode = i;
        showDialog(1);
    }

    public Dialog onCreateDialog(int i) {
        if (i != 1) {
            return super.onCreateDialog(i);
        }
        if (this.mDlgAccessPoint == null && this.mAccessPointSavedState != null) {
            this.mDlgAccessPoint = new AccessPoint((Context) getActivity(), this.mAccessPointSavedState);
            this.mAccessPointSavedState = null;
        }
        this.mDialog = WifiDialog.createModal(getActivity(), this, this.mDlgAccessPoint, this.mDialogMode);
        this.mSelectedAccessPoint = this.mDlgAccessPoint;
        return this.mDialog;
    }

    public void onDialogShowing() {
        super.onDialogShowing();
        setOnDismissListener(this);
    }

    public void onDismiss(DialogInterface dialogInterface) {
        this.mDialog = null;
    }

    public void onAccessPointsChanged() {
        Log.d("WifiSettings", "onAccessPointsChanged (WifiTracker) callback initiated");
        updateAccessPointsDelayed();
    }

    private void updateAccessPointsDelayed() {
        if (getActivity() != null && !this.mIsRestricted && this.mWifiManager.isWifiEnabled()) {
            View view = getView();
            Handler handler = view.getHandler();
            if (handler == null || !handler.hasCallbacks(this.mUpdateAccessPointsRunnable)) {
                setProgressBarVisible(true);
                view.postDelayed(this.mUpdateAccessPointsRunnable, 300);
            }
        }
    }

    public void onWifiStateChanged(int i) {
        if (!this.mIsRestricted) {
            int wifiState = this.mWifiManager.getWifiState();
            if (wifiState == 0) {
                removeConnectedAccessPointPreference();
                removeAccessPointPreference();
                addMessagePreference(C1715R.string.wifi_stopping);
            } else if (wifiState == 1) {
                setOffMessage();
                setAdditionalSettingsSummaries();
                setProgressBarVisible(false);
            } else if (wifiState == 2) {
                removeConnectedAccessPointPreference();
                removeAccessPointPreference();
                addMessagePreference(C1715R.string.wifi_starting);
                setProgressBarVisible(true);
            } else if (wifiState == 3) {
                lambda$new$0$WifiSettings();
            }
        }
    }

    public void onConnectedChanged() {
        changeNextButtonState(this.mWifiTracker.isConnected());
    }

    private static boolean isDisabledByWrongPassword(AccessPoint accessPoint) {
        WifiConfiguration.NetworkSelectionStatus networkSelectionStatus;
        WifiConfiguration config = accessPoint.getConfig();
        if (config != null && (networkSelectionStatus = config.getNetworkSelectionStatus()) != null && !networkSelectionStatus.isNetworkEnabled() && 13 == networkSelectionStatus.getNetworkSelectionDisableReason()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    /* renamed from: updateAccessPointPreferences */
    public void lambda$new$0$WifiSettings() {
        if (this.mWifiManager.isWifiEnabled()) {
            List<AccessPoint> accessPoints = this.mWifiTracker.getAccessPoints();
            if (isVerboseLoggingEnabled()) {
                Log.i("WifiSettings", "updateAccessPoints called for: " + accessPoints);
            }
            this.mStatusMessagePreference.setVisible(false);
            this.mConnectedAccessPointPreferenceCategory.setVisible(true);
            this.mAccessPointsPreferenceCategory.setVisible(true);
            cacheRemoveAllPrefs(this.mAccessPointsPreferenceCategory);
            int configureConnectedAccessPointPreferenceCategory = configureConnectedAccessPointPreferenceCategory(accessPoints);
            int size = accessPoints.size();
            boolean z = false;
            while (configureConnectedAccessPointPreferenceCategory < size) {
                AccessPoint accessPoint = accessPoints.get(configureConnectedAccessPointPreferenceCategory);
                if (accessPoint.isReachable()) {
                    String key = accessPoint.getKey();
                    LongPressAccessPointPreference longPressAccessPointPreference = (LongPressAccessPointPreference) getCachedPreference(key);
                    if (longPressAccessPointPreference != null) {
                        longPressAccessPointPreference.setOrder(configureConnectedAccessPointPreferenceCategory);
                    } else {
                        LongPressAccessPointPreference createLongPressAccessPointPreference = createLongPressAccessPointPreference(accessPoint);
                        createLongPressAccessPointPreference.setKey(key);
                        createLongPressAccessPointPreference.setOrder(configureConnectedAccessPointPreferenceCategory);
                        String str = this.mOpenSsid;
                        if (!(str == null || !str.equals(accessPoint.getSsidStr()) || accessPoint.getSecurity() == 0 || accessPoint.getSecurity() == 4 || (accessPoint.isSaved() && !isDisabledByWrongPassword(accessPoint)))) {
                            onPreferenceTreeClick(createLongPressAccessPointPreference);
                            this.mOpenSsid = null;
                        }
                        this.mAccessPointsPreferenceCategory.addPreference(createLongPressAccessPointPreference);
                        accessPoint.setListener(this);
                        createLongPressAccessPointPreference.refresh();
                    }
                    z = true;
                }
                configureConnectedAccessPointPreferenceCategory++;
            }
            removeCachedPrefs(this.mAccessPointsPreferenceCategory);
            this.mAddWifiNetworkPreference.setOrder(configureConnectedAccessPointPreferenceCategory);
            this.mAccessPointsPreferenceCategory.addPreference(this.mAddWifiNetworkPreference);
            setAdditionalSettingsSummaries();
            if (!z) {
                setProgressBarVisible(true);
                Preference preference = new Preference(getPrefContext());
                preference.setSelectable(false);
                preference.setSummary((int) C1715R.string.wifi_empty_list_wifi_on);
                preference.setOrder(configureConnectedAccessPointPreferenceCategory);
                preference.setKey("wifi_empty_list");
                this.mAccessPointsPreferenceCategory.addPreference(preference);
                return;
            }
            getView().postDelayed(this.mHideProgressBarRunnable, 1700);
        }
    }

    private LongPressAccessPointPreference createLongPressAccessPointPreference(AccessPoint accessPoint) {
        return new LongPressAccessPointPreference(accessPoint, getPrefContext(), this.mUserBadgeCache, false, C1715R.C1717drawable.ic_wifi_signal_0, this);
    }

    /* access modifiers changed from: package-private */
    public ConnectedAccessPointPreference createConnectedAccessPointPreference(AccessPoint accessPoint, Context context) {
        return new ConnectedAccessPointPreference(accessPoint, context, this.mUserBadgeCache, C1715R.C1717drawable.ic_wifi_signal_0, false, this);
    }

    private boolean configureConnectedAccessPointPreferenceCategory(List<AccessPoint> list) {
        if (list.size() == 0) {
            removeConnectedAccessPointPreference();
            return false;
        }
        AccessPoint accessPoint = list.get(0);
        if (!accessPoint.isActive()) {
            removeConnectedAccessPointPreference();
            return false;
        } else if (this.mConnectedAccessPointPreferenceCategory.getPreferenceCount() == 0) {
            addConnectedAccessPointPreference(accessPoint);
            return true;
        } else {
            ConnectedAccessPointPreference connectedAccessPointPreference = (ConnectedAccessPointPreference) this.mConnectedAccessPointPreferenceCategory.getPreference(0);
            if (connectedAccessPointPreference.getAccessPoint() != accessPoint) {
                removeConnectedAccessPointPreference();
                addConnectedAccessPointPreference(accessPoint);
                return true;
            }
            connectedAccessPointPreference.refresh();
            registerCaptivePortalNetworkCallback(getCurrentWifiNetwork(), connectedAccessPointPreference);
            return true;
        }
    }

    private void addConnectedAccessPointPreference(AccessPoint accessPoint) {
        ConnectedAccessPointPreference createConnectedAccessPointPreference = createConnectedAccessPointPreference(accessPoint, getPrefContext());
        registerCaptivePortalNetworkCallback(getCurrentWifiNetwork(), createConnectedAccessPointPreference);
        createConnectedAccessPointPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(createConnectedAccessPointPreference) {
            private final /* synthetic */ ConnectedAccessPointPreference f$1;

            {
                this.f$1 = r2;
            }

            public final boolean onPreferenceClick(Preference preference) {
                return WifiSettings.this.lambda$addConnectedAccessPointPreference$2$WifiSettings(this.f$1, preference);
            }
        });
        createConnectedAccessPointPreference.setOnGearClickListener(new ConnectedAccessPointPreference.OnGearClickListener(createConnectedAccessPointPreference) {
            private final /* synthetic */ ConnectedAccessPointPreference f$1;

            {
                this.f$1 = r2;
            }

            public final void onGearClick(ConnectedAccessPointPreference connectedAccessPointPreference) {
                WifiSettings.this.lambda$addConnectedAccessPointPreference$3$WifiSettings(this.f$1, connectedAccessPointPreference);
            }
        });
        createConnectedAccessPointPreference.refresh();
        this.mConnectedAccessPointPreferenceCategory.addPreference(createConnectedAccessPointPreference);
        this.mConnectedAccessPointPreferenceCategory.setVisible(true);
        if (this.mClickedConnect) {
            this.mClickedConnect = false;
            scrollToPreference(this.mConnectedAccessPointPreferenceCategory);
        }
    }

    public /* synthetic */ boolean lambda$addConnectedAccessPointPreference$2$WifiSettings(ConnectedAccessPointPreference connectedAccessPointPreference, Preference preference) {
        connectedAccessPointPreference.getAccessPoint().saveWifiState(connectedAccessPointPreference.getExtras());
        CaptivePortalNetworkCallback captivePortalNetworkCallback = this.mCaptivePortalNetworkCallback;
        if (captivePortalNetworkCallback == null || !captivePortalNetworkCallback.isCaptivePortal()) {
            launchNetworkDetailsFragment(connectedAccessPointPreference);
            return true;
        }
        this.mConnectivityManager.startCaptivePortalApp(this.mCaptivePortalNetworkCallback.getNetwork());
        return true;
    }

    public /* synthetic */ void lambda$addConnectedAccessPointPreference$3$WifiSettings(ConnectedAccessPointPreference connectedAccessPointPreference, ConnectedAccessPointPreference connectedAccessPointPreference2) {
        connectedAccessPointPreference.getAccessPoint().saveWifiState(connectedAccessPointPreference.getExtras());
        launchNetworkDetailsFragment(connectedAccessPointPreference);
    }

    private void registerCaptivePortalNetworkCallback(Network network, ConnectedAccessPointPreference connectedAccessPointPreference) {
        if (network == null || connectedAccessPointPreference == null) {
            Log.w("WifiSettings", "Network or Preference were null when registering callback.");
            return;
        }
        CaptivePortalNetworkCallback captivePortalNetworkCallback = this.mCaptivePortalNetworkCallback;
        if (captivePortalNetworkCallback == null || !captivePortalNetworkCallback.isSameNetworkAndPreference(network, connectedAccessPointPreference)) {
            unregisterCaptivePortalNetworkCallback();
            this.mCaptivePortalNetworkCallback = new CaptivePortalNetworkCallback(network, connectedAccessPointPreference);
            this.mConnectivityManager.registerNetworkCallback(new NetworkRequest.Builder().clearCapabilities().addTransportType(1).build(), this.mCaptivePortalNetworkCallback, new Handler(Looper.getMainLooper()));
        }
    }

    private void unregisterCaptivePortalNetworkCallback() {
        CaptivePortalNetworkCallback captivePortalNetworkCallback = this.mCaptivePortalNetworkCallback;
        if (captivePortalNetworkCallback != null) {
            try {
                this.mConnectivityManager.unregisterNetworkCallback(captivePortalNetworkCallback);
            } catch (RuntimeException e) {
                Log.e("WifiSettings", "Unregistering CaptivePortalNetworkCallback failed.", e);
            }
            this.mCaptivePortalNetworkCallback = null;
        }
    }

    private void launchAddNetworkFragment() {
        new SubSettingLauncher(getContext()).setTitleRes(C1715R.string.wifi_add_network).setDestination(AddNetworkFragment.class.getName()).setSourceMetricsCategory(getMetricsCategory()).setResultListener(this, 2).launch();
    }

    private void launchNetworkDetailsFragment(ConnectedAccessPointPreference connectedAccessPointPreference) {
        CharSequence charSequence;
        AccessPoint accessPoint = connectedAccessPointPreference.getAccessPoint();
        Context context = getContext();
        if (FeatureFlagUtils.isEnabled(context, "settings_wifi_details_datausage_header")) {
            charSequence = accessPoint.getTitle();
        } else {
            charSequence = context.getText(C1715R.string.pref_title_network_details);
        }
        new SubSettingLauncher(getContext()).setTitleText(charSequence).setDestination(WifiNetworkDetailsFragment.class.getName()).setArguments(connectedAccessPointPreference.getExtras()).setSourceMetricsCategory(getMetricsCategory()).launch();
    }

    private Network getCurrentWifiNetwork() {
        WifiManager wifiManager = this.mWifiManager;
        if (wifiManager != null) {
            return wifiManager.getCurrentNetwork();
        }
        return null;
    }

    private void removeConnectedAccessPointPreference() {
        this.mConnectedAccessPointPreferenceCategory.removeAll();
        this.mConnectedAccessPointPreferenceCategory.setVisible(false);
        unregisterCaptivePortalNetworkCallback();
    }

    private void removeAccessPointPreference() {
        this.mAccessPointsPreferenceCategory.removeAll();
        this.mAccessPointsPreferenceCategory.setVisible(false);
    }

    /* access modifiers changed from: package-private */
    public void setAdditionalSettingsSummaries() {
        this.mConfigureWifiSettingsPreference.setSummary((CharSequence) getString(isWifiWakeupEnabled() ? C1715R.string.wifi_configure_settings_preference_summary_wakeup_on : C1715R.string.wifi_configure_settings_preference_summary_wakeup_off));
        List<AccessPoint> allConfigs = WifiSavedConfigUtils.getAllConfigs(getContext(), this.mWifiManager);
        boolean z = false;
        int size = allConfigs != null ? allConfigs.size() : 0;
        Preference preference = this.mSavedNetworksPreference;
        if (size > 0) {
            z = true;
        }
        preference.setVisible(z);
        if (size > 0) {
            this.mSavedNetworksPreference.setSummary((CharSequence) getSavedNetworkSettingsSummaryText(allConfigs, size));
        }
    }

    private String getSavedNetworkSettingsSummaryText(List<AccessPoint> list, int i) {
        int i2 = 0;
        for (AccessPoint next : list) {
            if (next.isPasspointConfig() || next.isPasspoint()) {
                i2++;
            }
        }
        int i3 = i - i2;
        if (i == i3) {
            return getResources().getQuantityString(C1715R.plurals.wifi_saved_access_points_summary, i3, new Object[]{Integer.valueOf(i3)});
        } else if (i == i2) {
            return getResources().getQuantityString(C1715R.plurals.wifi_saved_passpoint_access_points_summary, i2, new Object[]{Integer.valueOf(i2)});
        } else {
            return getResources().getQuantityString(C1715R.plurals.wifi_saved_all_access_points_summary, i, new Object[]{Integer.valueOf(i)});
        }
    }

    private boolean isWifiWakeupEnabled() {
        Context context = getContext();
        PowerManager powerManager = (PowerManager) context.getSystemService(PowerManager.class);
        ContentResolver contentResolver = context.getContentResolver();
        if (Settings.Global.getInt(contentResolver, "wifi_wakeup_enabled", 0) == 1 && Settings.Global.getInt(contentResolver, "wifi_scan_always_enabled", 0) == 1 && Settings.Global.getInt(contentResolver, "airplane_mode_on", 0) == 0 && !powerManager.isPowerSaveMode()) {
            return true;
        }
        return false;
    }

    private void setOffMessage() {
        CharSequence charSequence;
        CharSequence text = getText(C1715R.string.wifi_empty_list_wifi_off);
        boolean z = false;
        if (Settings.Global.getInt(getActivity().getContentResolver(), "wifi_scan_always_enabled", 0) == 1) {
            z = true;
        }
        if (z) {
            charSequence = getText(C1715R.string.wifi_scan_notify_text);
        } else {
            charSequence = getText(C1715R.string.wifi_scan_notify_text_scanning_off);
        }
        this.mStatusMessagePreference.setText(text, charSequence, new LinkifyUtils.OnClickListener() {
            public final void onClick() {
                WifiSettings.this.lambda$setOffMessage$4$WifiSettings();
            }
        });
        removeConnectedAccessPointPreference();
        removeAccessPointPreference();
        this.mStatusMessagePreference.setVisible(true);
    }

    public /* synthetic */ void lambda$setOffMessage$4$WifiSettings() {
        new SubSettingLauncher(getContext()).setDestination(ScanningSettings.class.getName()).setTitleRes(C1715R.string.location_scanning_screen_title).setSourceMetricsCategory(getMetricsCategory()).launch();
    }

    private void addMessagePreference(int i) {
        this.mStatusMessagePreference.setTitle(i);
        this.mStatusMessagePreference.setVisible(true);
    }

    /* access modifiers changed from: protected */
    public void setProgressBarVisible(boolean z) {
        View view = this.mProgressHeader;
        if (view != null) {
            view.setVisibility(z ? 0 : 8);
        }
    }

    private void changeNextButtonState(boolean z) {
        if (this.mEnableNextOnConnection && hasNextButton()) {
            getNextButton().setEnabled(z);
        }
    }

    public void onForget(WifiDialog wifiDialog) {
        forget();
    }

    public void onSubmit(WifiDialog wifiDialog) {
        WifiDialog wifiDialog2 = this.mDialog;
        if (wifiDialog2 != null) {
            submit(wifiDialog2.getController());
        }
    }

    public void onScan(WifiDialog wifiDialog, String str) {
        startActivityForResult(WifiDppUtils.getEnrolleeQrCodeScannerIntent(str), 0);
    }

    /* access modifiers changed from: package-private */
    public void submit(WifiConfigController wifiConfigController) {
        WifiConfiguration config = wifiConfigController.getConfig();
        if (config == null) {
            AccessPoint accessPoint = this.mSelectedAccessPoint;
            if (accessPoint != null && accessPoint.isSaved()) {
                connect(this.mSelectedAccessPoint.getConfig(), true);
            }
        } else if (wifiConfigController.getMode() == 2) {
            this.mWifiManager.save(config, this.mSaveListener);
        } else {
            this.mWifiManager.save(config, this.mSaveListener);
            if (this.mSelectedAccessPoint != null) {
                connect(config, false);
            }
        }
        this.mWifiTracker.resumeScanning();
    }

    /* access modifiers changed from: package-private */
    public void forget() {
        this.mMetricsFeatureProvider.action((Context) getActivity(), 137, (Pair<Integer, Object>[]) new Pair[0]);
        if (!this.mSelectedAccessPoint.isSaved()) {
            if (this.mSelectedAccessPoint.getNetworkInfo() == null || this.mSelectedAccessPoint.getNetworkInfo().getState() == NetworkInfo.State.DISCONNECTED) {
                Log.e("WifiSettings", "Failed to forget invalid network " + this.mSelectedAccessPoint.getConfig());
                return;
            }
            this.mWifiManager.disableEphemeralNetwork(AccessPoint.convertToQuotedString(this.mSelectedAccessPoint.getSsidStr()));
        } else if (this.mSelectedAccessPoint.getConfig().isPasspoint()) {
            try {
                this.mWifiManager.removePasspointConfiguration(this.mSelectedAccessPoint.getConfig().FQDN);
            } catch (IllegalArgumentException e) {
                Log.e("WifiSettings", "Failed to remove Passpoint configuration with error: " + e);
                return;
            }
        } else {
            this.mWifiManager.forget(this.mSelectedAccessPoint.getConfig().networkId, this.mForgetListener);
        }
        this.mWifiTracker.resumeScanning();
        changeNextButtonState(false);
    }

    /* access modifiers changed from: protected */
    public void connect(WifiConfiguration wifiConfiguration, boolean z) {
        this.mMetricsFeatureProvider.action(getContext(), 135, z);
        this.mWifiManager.connect(wifiConfiguration, this.mConnectListener);
        this.mClickedConnect = true;
    }

    /* access modifiers changed from: package-private */
    public void handleAddNetworkRequest(int i, Intent intent) {
        if (i == -1) {
            handleAddNetworkSubmitEvent(intent);
        }
        this.mWifiTracker.resumeScanning();
    }

    private void handleAddNetworkSubmitEvent(Intent intent) {
        WifiConfiguration wifiConfiguration = (WifiConfiguration) intent.getParcelableExtra("wifi_config_key");
        if (wifiConfiguration != null) {
            this.mWifiManager.save(wifiConfiguration, this.mSaveListener);
        }
    }

    private void onAddNetworkPressed() {
        this.mSelectedAccessPoint = null;
        launchAddNetworkFragment();
    }

    public void onAccessPointChanged(final AccessPoint accessPoint) {
        Log.d("WifiSettings", "onAccessPointChanged (singular) callback initiated");
        View view = getView();
        if (view != null) {
            view.post(new Runnable() {
                public void run() {
                    Object tag = accessPoint.getTag();
                    if (tag != null) {
                        ((AccessPointPreference) tag).refresh();
                    }
                }
            });
        }
    }

    public void onLevelChanged(AccessPoint accessPoint) {
        ((AccessPointPreference) accessPoint.getTag()).onLevelChanged();
    }

    private static class SummaryProvider implements SummaryLoader.SummaryProvider, SummaryUpdater.OnSummaryChangeListener {
        private final Context mContext;
        WifiSummaryUpdater mSummaryHelper = new WifiSummaryUpdater(this.mContext, this);
        private final SummaryLoader mSummaryLoader;

        public SummaryProvider(Context context, SummaryLoader summaryLoader) {
            this.mContext = context;
            this.mSummaryLoader = summaryLoader;
        }

        public void setListening(boolean z) {
            this.mSummaryHelper.register(z);
        }

        public void onSummaryChanged(String str) {
            this.mSummaryLoader.setSummary(this, str);
        }
    }
}
