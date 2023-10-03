package com.android.settings.wfd;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.hardware.display.DisplayManager;
import android.hardware.display.WifiDisplay;
import android.hardware.display.WifiDisplayStatus;
import android.media.MediaRouter;
import android.net.Uri;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.SearchIndexableResource;
import android.provider.Settings;
import android.util.Slog;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import androidx.preference.PreferenceViewHolder;
import com.android.internal.app.MediaRouteDialogPresenter;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.dashboard.SummaryLoader;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.SwitchPreference;
import java.util.ArrayList;
import java.util.List;

public final class WifiDisplaySettings extends SettingsPreferenceFragment implements Indexable {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.wifi_display_settings;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }
    };
    public static final SummaryLoader.SummaryProviderFactory SUMMARY_PROVIDER_FACTORY = $$Lambda$WifiDisplaySettings$FSGRkDMrB620EgLXH7J2ShDkw60.INSTANCE;
    /* access modifiers changed from: private */
    public boolean mAutoGO;
    private PreferenceGroup mCertCategory;
    /* access modifiers changed from: private */
    public DisplayManager mDisplayManager;
    private TextView mEmptyView;
    private final Handler mHandler = new Handler();
    /* access modifiers changed from: private */
    public boolean mListen;
    /* access modifiers changed from: private */
    public int mListenChannel;
    /* access modifiers changed from: private */
    public int mOperatingChannel;
    /* access modifiers changed from: private */
    public int mPendingChanges;
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.hardware.display.action.WIFI_DISPLAY_STATUS_CHANGED")) {
                WifiDisplaySettings.this.scheduleUpdate(4);
            }
        }
    };
    private MediaRouter mRouter;
    private final MediaRouter.Callback mRouterCallback = new MediaRouter.SimpleCallback() {
        public void onRouteAdded(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
            WifiDisplaySettings.this.scheduleUpdate(2);
        }

        public void onRouteChanged(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
            WifiDisplaySettings.this.scheduleUpdate(2);
        }

        public void onRouteRemoved(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
            WifiDisplaySettings.this.scheduleUpdate(2);
        }

        public void onRouteSelected(MediaRouter mediaRouter, int i, MediaRouter.RouteInfo routeInfo) {
            WifiDisplaySettings.this.scheduleUpdate(2);
        }

        public void onRouteUnselected(MediaRouter mediaRouter, int i, MediaRouter.RouteInfo routeInfo) {
            WifiDisplaySettings.this.scheduleUpdate(2);
        }
    };
    private final ContentObserver mSettingsObserver = new ContentObserver(new Handler()) {
        public void onChange(boolean z, Uri uri) {
            WifiDisplaySettings.this.scheduleUpdate(1);
        }
    };
    private boolean mStarted;
    private final Runnable mUpdateRunnable = new Runnable() {
        public void run() {
            int access$1000 = WifiDisplaySettings.this.mPendingChanges;
            int unused = WifiDisplaySettings.this.mPendingChanges = 0;
            WifiDisplaySettings.this.update(access$1000);
        }
    };
    private boolean mWifiDisplayCertificationOn;
    private boolean mWifiDisplayOnSetting;
    private WifiDisplayStatus mWifiDisplayStatus;
    private WifiP2pManager.Channel mWifiP2pChannel;
    private WifiP2pManager mWifiP2pManager;
    /* access modifiers changed from: private */
    public int mWpsConfig = 4;

    public int getHelpResource() {
        return C1715R.string.help_url_remote_display;
    }

    public int getMetricsCategory() {
        return 102;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FragmentActivity activity = getActivity();
        this.mRouter = (MediaRouter) activity.getSystemService("media_router");
        this.mRouter.setRouterGroupId("android.media.mirroring_group");
        this.mDisplayManager = (DisplayManager) activity.getSystemService("display");
        this.mWifiP2pManager = (WifiP2pManager) activity.getSystemService("wifip2p");
        this.mWifiP2pChannel = this.mWifiP2pManager.initialize(activity, Looper.getMainLooper(), (WifiP2pManager.ChannelListener) null);
        addPreferencesFromResource(C1715R.xml.wifi_display_settings);
        setHasOptionsMenu(true);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mEmptyView = (TextView) getView().findViewById(16908292);
        this.mEmptyView.setText(C1715R.string.wifi_display_no_devices_found);
        setEmptyView(this.mEmptyView);
    }

    public void onStart() {
        super.onStart();
        this.mStarted = true;
        FragmentActivity activity = getActivity();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.hardware.display.action.WIFI_DISPLAY_STATUS_CHANGED");
        activity.registerReceiver(this.mReceiver, intentFilter);
        getContentResolver().registerContentObserver(Settings.Global.getUriFor("wifi_display_on"), false, this.mSettingsObserver);
        getContentResolver().registerContentObserver(Settings.Global.getUriFor("wifi_display_certification_on"), false, this.mSettingsObserver);
        getContentResolver().registerContentObserver(Settings.Global.getUriFor("wifi_display_wps_config"), false, this.mSettingsObserver);
        this.mRouter.addCallback(4, this.mRouterCallback, 1);
        update(-1);
    }

    public void onStop() {
        super.onStop();
        this.mStarted = false;
        getActivity().unregisterReceiver(this.mReceiver);
        getContentResolver().unregisterContentObserver(this.mSettingsObserver);
        this.mRouter.removeCallback(this.mRouterCallback);
        unscheduleUpdate();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        WifiDisplayStatus wifiDisplayStatus = this.mWifiDisplayStatus;
        if (!(wifiDisplayStatus == null || wifiDisplayStatus.getFeatureState() == 0)) {
            MenuItem add = menu.add(0, 1, 0, C1715R.string.wifi_display_enable_menu_item);
            add.setCheckable(true);
            add.setChecked(this.mWifiDisplayOnSetting);
        }
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 1) {
            return super.onOptionsItemSelected(menuItem);
        }
        this.mWifiDisplayOnSetting = !menuItem.isChecked();
        menuItem.setChecked(this.mWifiDisplayOnSetting);
        Settings.Global.putInt(getContentResolver(), "wifi_display_on", this.mWifiDisplayOnSetting ? 1 : 0);
        return true;
    }

    public static boolean isAvailable(Context context) {
        return (context.getSystemService("display") == null || !context.getPackageManager().hasSystemFeature("android.hardware.wifi.direct") || context.getSystemService("wifip2p") == null) ? false : true;
    }

    /* access modifiers changed from: private */
    public void scheduleUpdate(int i) {
        if (this.mStarted) {
            if (this.mPendingChanges == 0) {
                this.mHandler.post(this.mUpdateRunnable);
            }
            this.mPendingChanges = i | this.mPendingChanges;
        }
    }

    private void unscheduleUpdate() {
        if (this.mPendingChanges != 0) {
            this.mPendingChanges = 0;
            this.mHandler.removeCallbacks(this.mUpdateRunnable);
        }
    }

    /* access modifiers changed from: private */
    public void update(int i) {
        boolean z;
        if ((i & 1) != 0) {
            this.mWifiDisplayOnSetting = Settings.Global.getInt(getContentResolver(), "wifi_display_on", 0) != 0;
            this.mWifiDisplayCertificationOn = Settings.Global.getInt(getContentResolver(), "wifi_display_certification_on", 0) != 0;
            this.mWpsConfig = Settings.Global.getInt(getContentResolver(), "wifi_display_wps_config", 4);
            z = true;
        } else {
            z = false;
        }
        if ((i & 4) != 0) {
            this.mWifiDisplayStatus = this.mDisplayManager.getWifiDisplayStatus();
            z = true;
        }
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        preferenceScreen.removeAll();
        int routeCount = this.mRouter.getRouteCount();
        for (int i2 = 0; i2 < routeCount; i2++) {
            MediaRouter.RouteInfo routeAt = this.mRouter.getRouteAt(i2);
            if (routeAt.matchesTypes(4)) {
                preferenceScreen.addPreference(createRoutePreference(routeAt));
            }
        }
        WifiDisplayStatus wifiDisplayStatus = this.mWifiDisplayStatus;
        if (wifiDisplayStatus != null && wifiDisplayStatus.getFeatureState() == 3) {
            for (WifiDisplay wifiDisplay : this.mWifiDisplayStatus.getDisplays()) {
                if (!wifiDisplay.isRemembered() && wifiDisplay.isAvailable() && !wifiDisplay.equals(this.mWifiDisplayStatus.getActiveDisplay())) {
                    preferenceScreen.addPreference(new UnpairedWifiDisplayPreference(getPrefContext(), wifiDisplay));
                }
            }
            if (this.mWifiDisplayCertificationOn) {
                buildCertificationMenu(preferenceScreen);
            }
        }
        if (z) {
            getActivity().invalidateOptionsMenu();
        }
    }

    private RoutePreference createRoutePreference(MediaRouter.RouteInfo routeInfo) {
        WifiDisplay findWifiDisplay = findWifiDisplay(routeInfo.getDeviceAddress());
        if (findWifiDisplay != null) {
            return new WifiDisplayRoutePreference(getPrefContext(), routeInfo, findWifiDisplay);
        }
        return new RoutePreference(getPrefContext(), routeInfo);
    }

    private WifiDisplay findWifiDisplay(String str) {
        WifiDisplayStatus wifiDisplayStatus = this.mWifiDisplayStatus;
        if (wifiDisplayStatus == null || str == null) {
            return null;
        }
        for (WifiDisplay wifiDisplay : wifiDisplayStatus.getDisplays()) {
            if (wifiDisplay.getDeviceAddress().equals(str)) {
                return wifiDisplay;
            }
        }
        return null;
    }

    private void buildCertificationMenu(PreferenceScreen preferenceScreen) {
        PreferenceGroup preferenceGroup = this.mCertCategory;
        if (preferenceGroup == null) {
            this.mCertCategory = new PreferenceCategory(getPrefContext());
            this.mCertCategory.setTitle((int) C1715R.string.wifi_display_certification_heading);
            this.mCertCategory.setOrder(1);
        } else {
            preferenceGroup.removeAll();
        }
        preferenceScreen.addPreference(this.mCertCategory);
        if (!this.mWifiDisplayStatus.getSessionInfo().getGroupId().isEmpty()) {
            Preference preference = new Preference(getPrefContext());
            preference.setTitle((int) C1715R.string.wifi_display_session_info);
            preference.setSummary((CharSequence) this.mWifiDisplayStatus.getSessionInfo().toString());
            this.mCertCategory.addPreference(preference);
            if (this.mWifiDisplayStatus.getSessionInfo().getSessionId() != 0) {
                this.mCertCategory.addPreference(new Preference(getPrefContext()) {
                    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
                        super.onBindViewHolder(preferenceViewHolder);
                        Button button = (Button) preferenceViewHolder.findViewById(C1715R.C1718id.left_button);
                        button.setText(C1715R.string.wifi_display_pause);
                        button.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                WifiDisplaySettings.this.mDisplayManager.pauseWifiDisplay();
                            }
                        });
                        Button button2 = (Button) preferenceViewHolder.findViewById(C1715R.C1718id.right_button);
                        button2.setText(C1715R.string.wifi_display_resume);
                        button2.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                WifiDisplaySettings.this.mDisplayManager.resumeWifiDisplay();
                            }
                        });
                    }
                });
                this.mCertCategory.setLayoutResource(C1715R.layout.two_buttons_panel);
            }
        }
        C12522 r11 = new SwitchPreference(getPrefContext()) {
            /* access modifiers changed from: protected */
            public void onClick() {
                WifiDisplaySettings wifiDisplaySettings = WifiDisplaySettings.this;
                boolean unused = wifiDisplaySettings.mListen = !wifiDisplaySettings.mListen;
                WifiDisplaySettings wifiDisplaySettings2 = WifiDisplaySettings.this;
                wifiDisplaySettings2.setListenMode(wifiDisplaySettings2.mListen);
                setChecked(WifiDisplaySettings.this.mListen);
            }
        };
        r11.setTitle((int) C1715R.string.wifi_display_listen_mode);
        r11.setChecked(this.mListen);
        this.mCertCategory.addPreference(r11);
        C12533 r112 = new SwitchPreference(getPrefContext()) {
            /* access modifiers changed from: protected */
            public void onClick() {
                WifiDisplaySettings wifiDisplaySettings = WifiDisplaySettings.this;
                boolean unused = wifiDisplaySettings.mAutoGO = !wifiDisplaySettings.mAutoGO;
                if (WifiDisplaySettings.this.mAutoGO) {
                    WifiDisplaySettings.this.startAutoGO();
                } else {
                    WifiDisplaySettings.this.stopAutoGO();
                }
                setChecked(WifiDisplaySettings.this.mAutoGO);
            }
        };
        r112.setTitle((int) C1715R.string.wifi_display_autonomous_go);
        r112.setChecked(this.mAutoGO);
        this.mCertCategory.addPreference(r112);
        ListPreference listPreference = new ListPreference(getPrefContext());
        listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object obj) {
                int parseInt = Integer.parseInt((String) obj);
                if (parseInt == WifiDisplaySettings.this.mWpsConfig) {
                    return true;
                }
                int unused = WifiDisplaySettings.this.mWpsConfig = parseInt;
                WifiDisplaySettings.this.getActivity().invalidateOptionsMenu();
                Settings.Global.putInt(WifiDisplaySettings.this.getActivity().getContentResolver(), "wifi_display_wps_config", WifiDisplaySettings.this.mWpsConfig);
                return true;
            }
        });
        this.mWpsConfig = Settings.Global.getInt(getActivity().getContentResolver(), "wifi_display_wps_config", 4);
        listPreference.setKey("wps");
        listPreference.setTitle((int) C1715R.string.wifi_display_wps_config);
        listPreference.setEntries((CharSequence[]) new String[]{"Default", "PBC", "KEYPAD", "DISPLAY"});
        listPreference.setEntryValues((CharSequence[]) new String[]{"4", "0", "2", "1"});
        listPreference.setValue("" + this.mWpsConfig);
        listPreference.setSummary("%1$s");
        this.mCertCategory.addPreference(listPreference);
        ListPreference listPreference2 = new ListPreference(getPrefContext());
        listPreference2.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object obj) {
                int parseInt = Integer.parseInt((String) obj);
                if (parseInt == WifiDisplaySettings.this.mListenChannel) {
                    return true;
                }
                int unused = WifiDisplaySettings.this.mListenChannel = parseInt;
                WifiDisplaySettings.this.getActivity().invalidateOptionsMenu();
                WifiDisplaySettings wifiDisplaySettings = WifiDisplaySettings.this;
                wifiDisplaySettings.setWifiP2pChannels(wifiDisplaySettings.mListenChannel, WifiDisplaySettings.this.mOperatingChannel);
                return true;
            }
        });
        listPreference2.setKey("listening_channel");
        listPreference2.setTitle((int) C1715R.string.wifi_display_listen_channel);
        listPreference2.setEntries((CharSequence[]) new String[]{"Auto", "1", "6", "11"});
        listPreference2.setEntryValues((CharSequence[]) new String[]{"0", "1", "6", "11"});
        listPreference2.setValue("" + this.mListenChannel);
        listPreference2.setSummary("%1$s");
        this.mCertCategory.addPreference(listPreference2);
        ListPreference listPreference3 = new ListPreference(getPrefContext());
        listPreference3.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object obj) {
                int parseInt = Integer.parseInt((String) obj);
                if (parseInt == WifiDisplaySettings.this.mOperatingChannel) {
                    return true;
                }
                int unused = WifiDisplaySettings.this.mOperatingChannel = parseInt;
                WifiDisplaySettings.this.getActivity().invalidateOptionsMenu();
                WifiDisplaySettings wifiDisplaySettings = WifiDisplaySettings.this;
                wifiDisplaySettings.setWifiP2pChannels(wifiDisplaySettings.mListenChannel, WifiDisplaySettings.this.mOperatingChannel);
                return true;
            }
        });
        listPreference3.setKey("operating_channel");
        listPreference3.setTitle((int) C1715R.string.wifi_display_operating_channel);
        listPreference3.setEntries((CharSequence[]) new String[]{"Auto", "1", "6", "11", "36"});
        listPreference3.setEntryValues((CharSequence[]) new String[]{"0", "1", "6", "11", "36"});
        listPreference3.setValue("" + this.mOperatingChannel);
        listPreference3.setSummary("%1$s");
        this.mCertCategory.addPreference(listPreference3);
    }

    /* access modifiers changed from: private */
    public void startAutoGO() {
        this.mWifiP2pManager.createGroup(this.mWifiP2pChannel, new WifiP2pManager.ActionListener() {
            public void onSuccess() {
            }

            public void onFailure(int i) {
                Slog.e("WifiDisplaySettings", "Failed to start AutoGO with reason " + i + ".");
            }
        });
    }

    /* access modifiers changed from: private */
    public void stopAutoGO() {
        this.mWifiP2pManager.removeGroup(this.mWifiP2pChannel, new WifiP2pManager.ActionListener() {
            public void onSuccess() {
            }

            public void onFailure(int i) {
                Slog.e("WifiDisplaySettings", "Failed to stop AutoGO with reason " + i + ".");
            }
        });
    }

    /* access modifiers changed from: private */
    public void setListenMode(final boolean z) {
        this.mWifiP2pManager.listen(this.mWifiP2pChannel, z, new WifiP2pManager.ActionListener() {
            public void onSuccess() {
            }

            public void onFailure(int i) {
                StringBuilder sb = new StringBuilder();
                sb.append("Failed to ");
                sb.append(z ? "entered" : "exited");
                sb.append(" listen mode with reason ");
                sb.append(i);
                sb.append(".");
                Slog.e("WifiDisplaySettings", sb.toString());
            }
        });
    }

    /* access modifiers changed from: private */
    public void setWifiP2pChannels(int i, int i2) {
        this.mWifiP2pManager.setWifiP2pChannels(this.mWifiP2pChannel, i, i2, new WifiP2pManager.ActionListener() {
            public void onSuccess() {
            }

            public void onFailure(int i) {
                Slog.e("WifiDisplaySettings", "Failed to set wifi p2p channels with reason " + i + ".");
            }
        });
    }

    /* access modifiers changed from: private */
    public void toggleRoute(MediaRouter.RouteInfo routeInfo) {
        if (routeInfo.isSelected()) {
            MediaRouteDialogPresenter.showDialogFragment(getActivity(), 4, (View.OnClickListener) null);
        } else {
            routeInfo.select();
        }
    }

    /* access modifiers changed from: private */
    public void pairWifiDisplay(WifiDisplay wifiDisplay) {
        if (wifiDisplay.canConnect()) {
            this.mDisplayManager.connectWifiDisplay(wifiDisplay.getDeviceAddress());
        }
    }

    /* access modifiers changed from: private */
    public void showWifiDisplayOptionsDialog(final WifiDisplay wifiDisplay) {
        View inflate = getActivity().getLayoutInflater().inflate(C1715R.layout.wifi_display_options, (ViewGroup) null);
        final EditText editText = (EditText) inflate.findViewById(C1715R.C1718id.name);
        editText.setText(wifiDisplay.getFriendlyDisplayName());
        C124511 r2 = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                String trim = editText.getText().toString().trim();
                if (trim.isEmpty() || trim.equals(wifiDisplay.getDeviceName())) {
                    trim = null;
                }
                WifiDisplaySettings.this.mDisplayManager.renameWifiDisplay(wifiDisplay.getDeviceAddress(), trim);
            }
        };
        C124612 r1 = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                WifiDisplaySettings.this.mDisplayManager.forgetWifiDisplay(wifiDisplay.getDeviceAddress());
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setTitle((int) C1715R.string.wifi_display_options_title);
        builder.setView(inflate);
        builder.setPositiveButton((int) C1715R.string.wifi_display_options_done, (DialogInterface.OnClickListener) r2);
        builder.setNegativeButton((int) C1715R.string.wifi_display_options_forget, (DialogInterface.OnClickListener) r1);
        builder.create().show();
    }

    private class RoutePreference extends Preference implements Preference.OnPreferenceClickListener {
        private final MediaRouter.RouteInfo mRoute;

        public RoutePreference(Context context, MediaRouter.RouteInfo routeInfo) {
            super(context);
            this.mRoute = routeInfo;
            setTitle(routeInfo.getName());
            setSummary(routeInfo.getDescription());
            setEnabled(routeInfo.isEnabled());
            if (routeInfo.isSelected()) {
                setOrder(2);
                if (routeInfo.isConnecting()) {
                    setSummary((int) C1715R.string.wifi_display_status_connecting);
                } else {
                    setSummary((int) C1715R.string.wifi_display_status_connected);
                }
            } else if (isEnabled()) {
                setOrder(3);
            } else {
                setOrder(4);
                if (routeInfo.getStatusCode() == 5) {
                    setSummary((int) C1715R.string.wifi_display_status_in_use);
                } else {
                    setSummary((int) C1715R.string.wifi_display_status_not_available);
                }
            }
            setOnPreferenceClickListener(this);
        }

        public boolean onPreferenceClick(Preference preference) {
            WifiDisplaySettings.this.toggleRoute(this.mRoute);
            return true;
        }
    }

    private class WifiDisplayRoutePreference extends RoutePreference implements View.OnClickListener {
        private final WifiDisplay mDisplay;

        public WifiDisplayRoutePreference(Context context, MediaRouter.RouteInfo routeInfo, WifiDisplay wifiDisplay) {
            super(context, routeInfo);
            this.mDisplay = wifiDisplay;
            setWidgetLayoutResource(C1715R.layout.wifi_display_preference);
        }

        public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
            super.onBindViewHolder(preferenceViewHolder);
            ImageView imageView = (ImageView) preferenceViewHolder.findViewById(C1715R.C1718id.deviceDetails);
            if (imageView != null) {
                imageView.setOnClickListener(this);
                if (!isEnabled()) {
                    TypedValue typedValue = new TypedValue();
                    getContext().getTheme().resolveAttribute(16842803, typedValue, true);
                    imageView.setImageAlpha((int) (typedValue.getFloat() * 255.0f));
                    imageView.setEnabled(true);
                }
            }
        }

        public void onClick(View view) {
            WifiDisplaySettings.this.showWifiDisplayOptionsDialog(this.mDisplay);
        }
    }

    private class UnpairedWifiDisplayPreference extends Preference implements Preference.OnPreferenceClickListener {
        private final WifiDisplay mDisplay;

        public UnpairedWifiDisplayPreference(Context context, WifiDisplay wifiDisplay) {
            super(context);
            this.mDisplay = wifiDisplay;
            setTitle((CharSequence) wifiDisplay.getFriendlyDisplayName());
            setSummary(17041410);
            setEnabled(wifiDisplay.canConnect());
            if (isEnabled()) {
                setOrder(3);
            } else {
                setOrder(4);
                setSummary((int) C1715R.string.wifi_display_status_in_use);
            }
            setOnPreferenceClickListener(this);
        }

        public boolean onPreferenceClick(Preference preference) {
            WifiDisplaySettings.this.pairWifiDisplay(this.mDisplay);
            return true;
        }
    }

    private static class SummaryProvider implements SummaryLoader.SummaryProvider {
        private final Context mContext;
        private final MediaRouter mRouter;
        private final MediaRouter.Callback mRouterCallback = new MediaRouter.SimpleCallback() {
            public void onRouteSelected(MediaRouter mediaRouter, int i, MediaRouter.RouteInfo routeInfo) {
                SummaryProvider.this.updateSummary();
            }

            public void onRouteUnselected(MediaRouter mediaRouter, int i, MediaRouter.RouteInfo routeInfo) {
                SummaryProvider.this.updateSummary();
            }

            public void onRouteAdded(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
                SummaryProvider.this.updateSummary();
            }

            public void onRouteRemoved(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
                SummaryProvider.this.updateSummary();
            }

            public void onRouteChanged(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
                SummaryProvider.this.updateSummary();
            }
        };
        private final SummaryLoader mSummaryLoader;

        public SummaryProvider(Context context, SummaryLoader summaryLoader) {
            this.mContext = context;
            this.mSummaryLoader = summaryLoader;
            this.mRouter = (MediaRouter) context.getSystemService("media_router");
            this.mRouter.setRouterGroupId("android.media.mirroring_group");
        }

        public void setListening(boolean z) {
            if (z) {
                this.mRouter.addCallback(4, this.mRouterCallback);
                updateSummary();
                return;
            }
            this.mRouter.removeCallback(this.mRouterCallback);
        }

        /* access modifiers changed from: private */
        public void updateSummary() {
            String string = this.mContext.getString(C1715R.string.disconnected);
            int routeCount = this.mRouter.getRouteCount();
            int i = 0;
            while (true) {
                if (i >= routeCount) {
                    break;
                }
                MediaRouter.RouteInfo routeAt = this.mRouter.getRouteAt(i);
                if (routeAt.matchesTypes(4) && routeAt.isSelected() && !routeAt.isConnecting()) {
                    string = this.mContext.getString(C1715R.string.wifi_display_status_connected);
                    break;
                }
                i++;
            }
            this.mSummaryLoader.setSummary(this, string);
        }
    }

    static /* synthetic */ SummaryLoader.SummaryProvider lambda$static$0(Activity activity, SummaryLoader summaryLoader) {
        return new SummaryProvider(activity, summaryLoader);
    }
}
