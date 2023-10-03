package com.android.settings.wifi.details;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.net.ConnectivityManager;
import android.net.LinkAddress;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.NetworkUtils;
import android.net.RouteInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.TextUtils;
import android.util.FeatureFlagUtils;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.core.text.BidiFormatter;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.datausage.WifiDataUsageSummaryPreferenceController;
import com.android.settings.development.featureflags.FeatureFlagPersistent;
import com.android.settings.widget.EntityHeaderController;
import com.android.settings.wifi.WifiDialog;
import com.android.settings.wifi.WifiUtils;
import com.android.settings.wifi.dpp.WifiDppUtils;
import com.android.settingslib.Utils;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnPause;
import com.android.settingslib.core.lifecycle.events.OnResume;
import com.android.settingslib.widget.ActionButtonsPreference;
import com.android.settingslib.widget.LayoutPreference;
import com.android.settingslib.wifi.AccessPoint;
import com.android.settingslib.wifi.WifiTracker;
import com.android.settingslib.wifi.WifiTrackerFactory;
import com.havoc.config.center.C1715R;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;
import java.util.Iterator;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class WifiDetailPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin, WifiDialog.WifiDialogListener, LifecycleObserver, OnPause, OnResume {
    private static final boolean DEBUG = Log.isLoggable("WifiDetailsPrefCtrl", 3);
    static final String KEY_BUTTONS_PREF = "buttons";
    static final String KEY_DATA_USAGE_HEADER = "status_header";
    static final String KEY_DNS_PREF = "dns";
    static final String KEY_FREQUENCY_PREF = "frequency";
    static final String KEY_GATEWAY_PREF = "gateway";
    static final String KEY_HEADER = "connection_header";
    static final String KEY_IPV6_ADDRESSES_PREF = "ipv6_addresses";
    static final String KEY_IPV6_CATEGORY = "ipv6_category";
    static final String KEY_IP_ADDRESS_PREF = "ip_address";
    static final String KEY_MAC_ADDRESS_PREF = "mac_address";
    static final String KEY_RX_LINK_SPEED = "rx_link_speed";
    static final String KEY_SECURITY_PREF = "security";
    static final String KEY_SIGNAL_STRENGTH_PREF = "signal_strength";
    static final String KEY_SSID_PREF = "ssid";
    static final String KEY_SUBNET_MASK_PREF = "subnet_mask";
    static final String KEY_TX_LINK_SPEED = "tx_link_speed";
    private static final long TIMEOUT = Duration.ofSeconds(10).toMillis();
    static CountDownTimer mTimer;
    /* access modifiers changed from: private */
    public AccessPoint mAccessPoint;
    private ActionButtonsPreference mButtonsPref;
    private WifiManager.ActionListener mConnectListener;
    private boolean mConnected;
    /* access modifiers changed from: private */
    public int mConnectingState;
    private final ConnectivityManager mConnectivityManager;
    Preference mDataUsageSummaryPref;
    private Preference mDnsPref;
    private EntityHeaderController mEntityHeaderController;
    private final IntentFilter mFilter;
    /* access modifiers changed from: private */
    public final PreferenceFragmentCompat mFragment;
    private Preference mFrequencyPref;
    private Preference mGatewayPref;
    private final Handler mHandler;
    private final IconInjector mIconInjector;
    private Preference mIpAddressPref;
    private Preference mIpv6AddressPref;
    private PreferenceCategory mIpv6Category;
    /* access modifiers changed from: private */
    public boolean mIsEphemeral;
    /* access modifiers changed from: private */
    public boolean mIsOutOfRange;
    private Lifecycle mLifecycle;
    /* access modifiers changed from: private */
    public LinkProperties mLinkProperties;
    private Preference mMacAddressPref;
    private final MetricsFeatureProvider mMetricsFeatureProvider;
    /* access modifiers changed from: private */
    public Network mNetwork;
    private final ConnectivityManager.NetworkCallback mNetworkCallback = new ConnectivityManager.NetworkCallback() {
        public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
            if (network.equals(WifiDetailPreferenceController.this.mNetwork) && !linkProperties.equals(WifiDetailPreferenceController.this.mLinkProperties)) {
                LinkProperties unused = WifiDetailPreferenceController.this.mLinkProperties = linkProperties;
                WifiDetailPreferenceController.this.refreshIpLayerInfo();
            }
        }

        private boolean hasCapabilityChanged(NetworkCapabilities networkCapabilities, int i) {
            if (WifiDetailPreferenceController.this.mNetworkCapabilities != null && WifiDetailPreferenceController.this.mNetworkCapabilities.hasCapability(i) == networkCapabilities.hasCapability(i)) {
                return WifiDetailPreferenceController.DEBUG;
            }
            return true;
        }

        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            if (network.equals(WifiDetailPreferenceController.this.mNetwork) && !networkCapabilities.equals(WifiDetailPreferenceController.this.mNetworkCapabilities)) {
                if (hasCapabilityChanged(networkCapabilities, 16) || hasCapabilityChanged(networkCapabilities, 17) || hasCapabilityChanged(networkCapabilities, 24)) {
                    WifiDetailPreferenceController.this.mAccessPoint.update(WifiDetailPreferenceController.this.mWifiConfig, WifiDetailPreferenceController.this.mWifiInfo, WifiDetailPreferenceController.this.mNetworkInfo);
                    WifiDetailPreferenceController.this.refreshEntityHeader();
                }
                NetworkCapabilities unused = WifiDetailPreferenceController.this.mNetworkCapabilities = networkCapabilities;
                WifiDetailPreferenceController.this.refreshButtons();
                WifiDetailPreferenceController.this.refreshIpLayerInfo();
            }
        }

        public void onLost(Network network) {
            if (WifiDetailPreferenceController.this.mIsEphemeral && network.equals(WifiDetailPreferenceController.this.mNetwork)) {
                WifiDetailPreferenceController.this.exitActivity();
            }
        }
    };
    /* access modifiers changed from: private */
    public NetworkCapabilities mNetworkCapabilities;
    /* access modifiers changed from: private */
    public NetworkInfo mNetworkInfo;
    private final NetworkRequest mNetworkRequest = new NetworkRequest.Builder().clearCapabilities().addTransportType(1).build();
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        /* JADX WARNING: Removed duplicated region for block: B:17:0x003b A[ADDED_TO_REGION] */
        /* JADX WARNING: Removed duplicated region for block: B:19:0x0040  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onReceive(android.content.Context r4, android.content.Intent r5) {
            /*
                r3 = this;
                java.lang.String r4 = r5.getAction()
                int r5 = r4.hashCode()
                r0 = -385684331(0xffffffffe902ec95, float:-9.892349E24)
                r1 = 2
                r2 = 1
                if (r5 == r0) goto L_0x002e
                r0 = -343630553(0xffffffffeb849d27, float:-3.2064068E26)
                if (r5 == r0) goto L_0x0024
                r0 = 1625920338(0x60e99352, float:1.3464709E20)
                if (r5 == r0) goto L_0x001a
                goto L_0x0038
            L_0x001a:
                java.lang.String r5 = "android.net.wifi.CONFIGURED_NETWORKS_CHANGE"
                boolean r4 = r4.equals(r5)
                if (r4 == 0) goto L_0x0038
                r4 = 0
                goto L_0x0039
            L_0x0024:
                java.lang.String r5 = "android.net.wifi.STATE_CHANGE"
                boolean r4 = r4.equals(r5)
                if (r4 == 0) goto L_0x0038
                r4 = r2
                goto L_0x0039
            L_0x002e:
                java.lang.String r5 = "android.net.wifi.RSSI_CHANGED"
                boolean r4 = r4.equals(r5)
                if (r4 == 0) goto L_0x0038
                r4 = r1
                goto L_0x0039
            L_0x0038:
                r4 = -1
            L_0x0039:
                if (r4 == 0) goto L_0x0040
                if (r4 == r2) goto L_0x0043
                if (r4 == r1) goto L_0x0043
                goto L_0x0048
            L_0x0040:
                r3.updateMatchingWifiConfig()
            L_0x0043:
                com.android.settings.wifi.details.WifiDetailPreferenceController r3 = com.android.settings.wifi.details.WifiDetailPreferenceController.this
                r3.refreshPage()
            L_0x0048:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.settings.wifi.details.WifiDetailPreferenceController.C13451.onReceive(android.content.Context, android.content.Intent):void");
        }

        private void updateMatchingWifiConfig() {
            for (WifiConfiguration wifiConfiguration : WifiDetailPreferenceController.this.mWifiManager.getPrivilegedConfiguredNetworks()) {
                if (WifiDetailPreferenceController.this.mAccessPoint.matches(wifiConfiguration)) {
                    WifiConfiguration unused = WifiDetailPreferenceController.this.mWifiConfig = wifiConfiguration;
                    return;
                }
            }
        }
    };
    private int mRssiSignalLevel = -1;
    private Preference mRxLinkSpeedPref;
    private Preference mSecurityPref;
    private String[] mSignalStr;
    private Preference mSignalStrengthPref;
    private Preference mSsidPref;
    private Preference mSubnetPref;
    WifiDataUsageSummaryPreferenceController mSummaryHeaderController;
    private Preference mTxLinkSpeedPref;
    /* access modifiers changed from: private */
    public WifiConfiguration mWifiConfig;
    /* access modifiers changed from: private */
    public WifiInfo mWifiInfo;
    final WifiTracker.WifiListener mWifiListener = new WifiTracker.WifiListener() {
        public void onWifiStateChanged(int i) {
            Log.d("WifiDetailsPrefCtrl", "onWifiStateChanged(" + i + ")");
            if (WifiDetailPreferenceController.this.mConnectingState == 2 && i == 3) {
                WifiDetailPreferenceController.this.updateConnectingState(4);
            } else if (WifiDetailPreferenceController.this.mConnectingState != 1 && i == 1) {
                WifiDetailPreferenceController.this.updateConnectingState(8);
            }
        }

        public void onConnectedChanged() {
            WifiDetailPreferenceController.this.refreshPage();
        }

        public void onAccessPointsChanged() {
            WifiDetailPreferenceController.this.refreshPage();
        }
    };
    /* access modifiers changed from: private */
    public final WifiManager mWifiManager;
    private final WifiTracker mWifiTracker;

    public String getPreferenceKey() {
        return null;
    }

    public boolean isAvailable() {
        return true;
    }

    public static WifiDetailPreferenceController newInstance(AccessPoint accessPoint, ConnectivityManager connectivityManager, Context context, PreferenceFragmentCompat preferenceFragmentCompat, Handler handler, Lifecycle lifecycle, WifiManager wifiManager, MetricsFeatureProvider metricsFeatureProvider) {
        return new WifiDetailPreferenceController(accessPoint, connectivityManager, context, preferenceFragmentCompat, handler, lifecycle, wifiManager, metricsFeatureProvider, new IconInjector(context));
    }

    WifiDetailPreferenceController(AccessPoint accessPoint, ConnectivityManager connectivityManager, Context context, PreferenceFragmentCompat preferenceFragmentCompat, Handler handler, Lifecycle lifecycle, WifiManager wifiManager, MetricsFeatureProvider metricsFeatureProvider, IconInjector iconInjector) {
        super(context);
        this.mAccessPoint = accessPoint;
        this.mConnectivityManager = connectivityManager;
        this.mFragment = preferenceFragmentCompat;
        this.mHandler = handler;
        this.mSignalStr = context.getResources().getStringArray(C1715R.array.wifi_signal);
        this.mWifiConfig = accessPoint.getConfig();
        this.mWifiManager = wifiManager;
        this.mMetricsFeatureProvider = metricsFeatureProvider;
        this.mIconInjector = iconInjector;
        this.mFilter = new IntentFilter();
        this.mFilter.addAction("android.net.wifi.STATE_CHANGE");
        this.mFilter.addAction("android.net.wifi.RSSI_CHANGED");
        this.mFilter.addAction("android.net.wifi.CONFIGURED_NETWORKS_CHANGE");
        this.mLifecycle = lifecycle;
        lifecycle.addObserver(this);
        this.mWifiTracker = WifiTrackerFactory.create(this.mFragment.getActivity(), this.mWifiListener, this.mLifecycle, true, true);
        this.mConnected = this.mAccessPoint.isActive();
        this.mIsEphemeral = this.mAccessPoint.isEphemeral();
        this.mConnectingState = 1;
        this.mConnectListener = new WifiManager.ActionListener() {
            public void onSuccess() {
            }

            public void onFailure(int i) {
                WifiDetailPreferenceController.this.updateConnectingState(6);
            }
        };
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        setupEntityHeader(preferenceScreen);
        this.mButtonsPref = ((ActionButtonsPreference) preferenceScreen.findPreference(KEY_BUTTONS_PREF)).setButton1Text(C1715R.string.forget).setButton1Icon(C1715R.C1717drawable.ic_settings_delete).setButton1OnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                WifiDetailPreferenceController.this.lambda$displayPreference$0$WifiDetailPreferenceController(view);
            }
        }).setButton2Text(C1715R.string.wifi_sign_in_button_text).setButton2Icon(C1715R.C1717drawable.ic_settings_sign_in).setButton2OnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                WifiDetailPreferenceController.this.lambda$displayPreference$1$WifiDetailPreferenceController(view);
            }
        }).setButton3Text(C1715R.string.wifi_connect).setButton3Icon(C1715R.C1717drawable.ic_settings_wireless).setButton3OnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                WifiDetailPreferenceController.this.lambda$displayPreference$2$WifiDetailPreferenceController(view);
            }
        }).setButton3Enabled(true).setButton4Text(C1715R.string.share).setButton4Icon(C1715R.C1717drawable.ic_qrcode_24dp).setButton4OnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                WifiDetailPreferenceController.this.lambda$displayPreference$3$WifiDetailPreferenceController(view);
            }
        });
        this.mSignalStrengthPref = preferenceScreen.findPreference(KEY_SIGNAL_STRENGTH_PREF);
        this.mTxLinkSpeedPref = preferenceScreen.findPreference(KEY_TX_LINK_SPEED);
        this.mRxLinkSpeedPref = preferenceScreen.findPreference(KEY_RX_LINK_SPEED);
        this.mFrequencyPref = preferenceScreen.findPreference(KEY_FREQUENCY_PREF);
        this.mSecurityPref = preferenceScreen.findPreference(KEY_SECURITY_PREF);
        this.mSsidPref = preferenceScreen.findPreference(KEY_SSID_PREF);
        this.mMacAddressPref = preferenceScreen.findPreference(KEY_MAC_ADDRESS_PREF);
        this.mIpAddressPref = preferenceScreen.findPreference(KEY_IP_ADDRESS_PREF);
        this.mGatewayPref = preferenceScreen.findPreference(KEY_GATEWAY_PREF);
        this.mSubnetPref = preferenceScreen.findPreference(KEY_SUBNET_MASK_PREF);
        this.mDnsPref = preferenceScreen.findPreference(KEY_DNS_PREF);
        this.mIpv6Category = (PreferenceCategory) preferenceScreen.findPreference(KEY_IPV6_CATEGORY);
        this.mIpv6AddressPref = preferenceScreen.findPreference(KEY_IPV6_ADDRESSES_PREF);
        this.mSecurityPref.setSummary((CharSequence) this.mAccessPoint.getSecurityString(DEBUG));
    }

    public /* synthetic */ void lambda$displayPreference$0$WifiDetailPreferenceController(View view) {
        forgetNetwork();
    }

    public /* synthetic */ void lambda$displayPreference$1$WifiDetailPreferenceController(View view) {
        signIntoNetwork();
    }

    public /* synthetic */ void lambda$displayPreference$2$WifiDetailPreferenceController(View view) {
        connectNetwork();
    }

    public /* synthetic */ void lambda$displayPreference$3$WifiDetailPreferenceController(View view) {
        shareNetwork();
    }

    private void setupEntityHeader(PreferenceScreen preferenceScreen) {
        LayoutPreference layoutPreference = (LayoutPreference) preferenceScreen.findPreference(KEY_HEADER);
        if (usingDataUsageHeader(this.mContext)) {
            layoutPreference.setVisible(DEBUG);
            this.mDataUsageSummaryPref = preferenceScreen.findPreference(KEY_DATA_USAGE_HEADER);
            this.mDataUsageSummaryPref.setVisible(true);
            this.mSummaryHeaderController = new WifiDataUsageSummaryPreferenceController(this.mFragment.getActivity(), this.mLifecycle, this.mFragment, this.mAccessPoint.getSsid());
            return;
        }
        this.mEntityHeaderController = EntityHeaderController.newInstance(this.mFragment.getActivity(), this.mFragment, layoutPreference.findViewById(C1715R.C1718id.entity_header));
        ((ImageView) layoutPreference.findViewById(C1715R.C1718id.entity_header_icon)).setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        this.mEntityHeaderController.setLabel((CharSequence) this.mAccessPoint.getTitle());
    }

    /* access modifiers changed from: private */
    public void refreshEntityHeader() {
        if (usingDataUsageHeader(this.mContext)) {
            this.mSummaryHeaderController.updateState(this.mDataUsageSummaryPref);
        } else {
            this.mEntityHeaderController.setSummary((CharSequence) this.mAccessPoint.getSettingsSummary(true)).setRecyclerView(this.mFragment.getListView(), this.mLifecycle).done((Activity) this.mFragment.getActivity(), true);
        }
    }

    private void updateNetworkInfo() {
        this.mNetwork = this.mWifiManager.getCurrentNetwork();
        this.mLinkProperties = this.mConnectivityManager.getLinkProperties(this.mNetwork);
        this.mNetworkCapabilities = this.mConnectivityManager.getNetworkCapabilities(this.mNetwork);
    }

    public void onResume() {
        updateNetworkInfo();
        refreshPage();
        this.mContext.registerReceiver(this.mReceiver, this.mFilter);
        this.mConnectivityManager.registerNetworkCallback(this.mNetworkRequest, this.mNetworkCallback, this.mHandler);
    }

    public void onPause() {
        this.mNetwork = null;
        this.mLinkProperties = null;
        this.mNetworkCapabilities = null;
        this.mNetworkInfo = null;
        this.mWifiInfo = null;
        this.mContext.unregisterReceiver(this.mReceiver);
        this.mConnectivityManager.unregisterNetworkCallback(this.mNetworkCallback);
    }

    /* access modifiers changed from: private */
    public void refreshPage() {
        if (updateAccessPoint()) {
            Log.d("WifiDetailsPrefCtrl", "Update UI!");
            refreshEntityHeader();
            refreshButtons();
            refreshRssiViews();
            refreshFrequency();
            refreshTxSpeed();
            refreshRxSpeed();
            refreshIpLayerInfo();
            refreshSsid();
            refreshMacAddress();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean updateAccessPoint() {
        boolean z;
        NetworkInfo networkInfo;
        WifiInfo wifiInfo;
        boolean z2 = this.mIsOutOfRange;
        updateAccessPointFromScannedList();
        boolean isActive = this.mAccessPoint.isActive();
        boolean z3 = DEBUG;
        if (isActive) {
            updateNetworkInfo();
            this.mNetworkInfo = this.mConnectivityManager.getNetworkInfo(this.mNetwork);
            this.mWifiInfo = this.mWifiManager.getConnectionInfo();
            if (this.mNetwork == null || (networkInfo = this.mNetworkInfo) == null || (wifiInfo = this.mWifiInfo) == null) {
                this.mIsOutOfRange = z2;
                return DEBUG;
            }
            z = this.mAccessPoint.update(this.mWifiConfig, wifiInfo, networkInfo) | DEBUG;
        } else {
            z = false;
        }
        boolean z4 = z | (this.mRssiSignalLevel != this.mAccessPoint.getLevel());
        if (z2 != this.mIsOutOfRange) {
            z3 = true;
        }
        boolean z5 = z4 | z3;
        if (this.mConnected == this.mAccessPoint.isActive()) {
            return z5;
        }
        this.mConnected = this.mAccessPoint.isActive();
        updateConnectingState(this.mAccessPoint.isActive() ? 5 : 8);
        return true;
    }

    /* access modifiers changed from: private */
    public void updateAccessPointFromScannedList() {
        this.mIsOutOfRange = true;
        for (AccessPoint next : this.mWifiTracker.getAccessPoints()) {
            if (this.mAccessPoint.matches(next)) {
                this.mAccessPoint = next;
                this.mWifiConfig = next.getConfig();
                this.mIsOutOfRange = true ^ this.mAccessPoint.isReachable();
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    public void exitActivity() {
        if (DEBUG) {
            Log.d("WifiDetailsPrefCtrl", "Exiting the WifiNetworkDetailsPage");
        }
        this.mFragment.getActivity().finish();
    }

    private void refreshRssiViews() {
        int level = this.mAccessPoint.getLevel();
        if (this.mIsOutOfRange) {
            this.mSignalStrengthPref.setVisible(DEBUG);
            this.mRssiSignalLevel = -1;
        } else if (this.mRssiSignalLevel != level) {
            this.mRssiSignalLevel = level;
            Drawable icon = this.mIconInjector.getIcon(this.mRssiSignalLevel);
            EntityHeaderController entityHeaderController = this.mEntityHeaderController;
            if (entityHeaderController != null) {
                entityHeaderController.setIcon(redrawIconForHeader(icon)).done((Activity) this.mFragment.getActivity(), true);
            }
            Drawable mutate = icon.getConstantState().newDrawable().mutate();
            mutate.setTintList(Utils.getColorAttr(this.mContext, 16843817));
            this.mSignalStrengthPref.setIcon(mutate);
            this.mSignalStrengthPref.setSummary((CharSequence) this.mSignalStr[this.mRssiSignalLevel]);
            this.mSignalStrengthPref.setVisible(true);
        }
    }

    private Drawable redrawIconForHeader(Drawable drawable) {
        int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(C1715R.dimen.wifi_detail_page_header_image_size);
        int minimumWidth = drawable.getMinimumWidth();
        int minimumHeight = drawable.getMinimumHeight();
        if ((minimumWidth == dimensionPixelSize && minimumHeight == dimensionPixelSize) || !VectorDrawable.class.isInstance(drawable)) {
            return drawable;
        }
        drawable.setTintList((ColorStateList) null);
        BitmapDrawable bitmapDrawable = new BitmapDrawable((Resources) null, com.android.settings.Utils.createBitmap(drawable, dimensionPixelSize, dimensionPixelSize));
        bitmapDrawable.setTintList(Utils.getColorAttr(this.mContext, 16842806));
        return bitmapDrawable;
    }

    private void refreshFrequency() {
        String str;
        WifiInfo wifiInfo = this.mWifiInfo;
        if (wifiInfo == null) {
            this.mFrequencyPref.setVisible(DEBUG);
            return;
        }
        int frequency = wifiInfo.getFrequency();
        if (frequency >= 2400 && frequency < 2500) {
            str = this.mContext.getResources().getString(C1715R.string.wifi_band_24ghz);
        } else if (frequency < 4900 || frequency >= 5900) {
            Log.e("WifiDetailsPrefCtrl", "Unexpected frequency " + frequency);
            if (this.mConnectingState == 4) {
                this.mFrequencyPref.setVisible(DEBUG);
                return;
            }
            return;
        } else {
            str = this.mContext.getResources().getString(C1715R.string.wifi_band_5ghz);
        }
        this.mFrequencyPref.setSummary((CharSequence) str);
        this.mFrequencyPref.setVisible(true);
    }

    private void refreshTxSpeed() {
        WifiInfo wifiInfo = this.mWifiInfo;
        if (wifiInfo == null) {
            this.mTxLinkSpeedPref.setVisible(DEBUG);
            return;
        }
        this.mTxLinkSpeedPref.setVisible(wifiInfo.getTxLinkSpeedMbps() >= 0);
        this.mTxLinkSpeedPref.setSummary((CharSequence) this.mContext.getString(C1715R.string.tx_link_speed, new Object[]{Integer.valueOf(this.mWifiInfo.getTxLinkSpeedMbps())}));
    }

    private void refreshRxSpeed() {
        WifiInfo wifiInfo = this.mWifiInfo;
        if (wifiInfo == null) {
            this.mRxLinkSpeedPref.setVisible(DEBUG);
            return;
        }
        this.mRxLinkSpeedPref.setVisible(wifiInfo.getRxLinkSpeedMbps() >= 0);
        this.mRxLinkSpeedPref.setSummary((CharSequence) this.mContext.getString(C1715R.string.rx_link_speed, new Object[]{Integer.valueOf(this.mWifiInfo.getRxLinkSpeedMbps())}));
    }

    private void refreshSsid() {
        if (this.mAccessPoint.isPasspoint() || this.mAccessPoint.isOsuProvider()) {
            this.mSsidPref.setVisible(true);
            this.mSsidPref.setSummary((CharSequence) this.mAccessPoint.getSsidStr());
            return;
        }
        this.mSsidPref.setVisible(DEBUG);
    }

    private void refreshMacAddress() {
        String macAddress = getMacAddress();
        if (macAddress == null) {
            this.mMacAddressPref.setVisible(DEBUG);
            return;
        }
        this.mMacAddressPref.setVisible(true);
        if (macAddress.equals("02:00:00:00:00:00")) {
            this.mMacAddressPref.setSummary((int) C1715R.string.device_info_not_available);
        } else {
            this.mMacAddressPref.setSummary((CharSequence) macAddress);
        }
    }

    private String getMacAddress() {
        WifiInfo wifiInfo = this.mWifiInfo;
        if (wifiInfo != null) {
            return wifiInfo.getMacAddress();
        }
        WifiConfiguration wifiConfiguration = this.mWifiConfig;
        if (wifiConfiguration != null && wifiConfiguration.macRandomizationSetting == 1) {
            return wifiConfiguration.getRandomizedMacAddress().toString();
        }
        String[] factoryMacAddresses = this.mWifiManager.getFactoryMacAddresses();
        if (factoryMacAddresses != null && factoryMacAddresses.length > 0) {
            return factoryMacAddresses[0];
        }
        Log.e("WifiDetailsPrefCtrl", "Can't get device MAC address!");
        return null;
    }

    private void updatePreference(Preference preference, String str) {
        if (!TextUtils.isEmpty(str)) {
            preference.setSummary((CharSequence) str);
            preference.setVisible(true);
            return;
        }
        preference.setVisible(DEBUG);
    }

    /* access modifiers changed from: private */
    public void refreshButtons() {
        this.mButtonsPref.setButton1Text(this.mIsEphemeral ? C1715R.string.wifi_disconnect_button_text : C1715R.string.forget);
        boolean canForgetNetwork = canForgetNetwork();
        boolean canSignIntoNetwork = canSignIntoNetwork();
        boolean canConnectNetwork = canConnectNetwork();
        boolean canShareNetwork = canShareNetwork();
        this.mButtonsPref.setButton1Visible(canForgetNetwork);
        this.mButtonsPref.setButton2Visible(canSignIntoNetwork);
        this.mButtonsPref.setButton3Visible(canConnectNetwork);
        this.mButtonsPref.setButton4Visible(canShareNetwork);
        this.mButtonsPref.setVisible((canForgetNetwork || canSignIntoNetwork || canConnectNetwork || canShareNetwork) ? true : DEBUG);
    }

    private boolean canConnectNetwork() {
        return !this.mAccessPoint.isActive();
    }

    /* access modifiers changed from: private */
    public void refreshIpLayerInfo() {
        if (!this.mAccessPoint.isActive() || this.mNetwork == null || this.mLinkProperties == null) {
            this.mIpAddressPref.setVisible(DEBUG);
            this.mSubnetPref.setVisible(DEBUG);
            this.mGatewayPref.setVisible(DEBUG);
            this.mDnsPref.setVisible(DEBUG);
            this.mIpv6Category.setVisible(DEBUG);
            return;
        }
        StringJoiner stringJoiner = new StringJoiner("\n");
        String str = null;
        String str2 = null;
        String str3 = null;
        for (LinkAddress next : this.mLinkProperties.getLinkAddresses()) {
            if (next.getAddress() instanceof Inet4Address) {
                str2 = next.getAddress().getHostAddress();
                str3 = ipv4PrefixLengthToSubnetMask(next.getPrefixLength());
            } else if (next.getAddress() instanceof Inet6Address) {
                stringJoiner.add(next.getAddress().getHostAddress());
            }
        }
        Iterator<RouteInfo> it = this.mLinkProperties.getRoutes().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            RouteInfo next2 = it.next();
            if (next2.isIPv4Default() && next2.hasGateway()) {
                str = next2.getGateway().getHostAddress();
                break;
            }
        }
        updatePreference(this.mIpAddressPref, str2);
        updatePreference(this.mSubnetPref, str3);
        updatePreference(this.mGatewayPref, str);
        updatePreference(this.mDnsPref, (String) this.mLinkProperties.getDnsServers().stream().map($$Lambda$XZAGhHrbkIDyusER4MAM6luKcT0.INSTANCE).collect(Collectors.joining("\n")));
        if (stringJoiner.length() > 0) {
            this.mIpv6AddressPref.setSummary((CharSequence) BidiFormatter.getInstance().unicodeWrap(stringJoiner.toString()));
            this.mIpv6Category.setVisible(true);
            return;
        }
        this.mIpv6Category.setVisible(DEBUG);
    }

    private static String ipv4PrefixLengthToSubnetMask(int i) {
        try {
            return NetworkUtils.getNetworkPart(InetAddress.getByAddress(new byte[]{-1, -1, -1, -1}), i).getHostAddress();
        } catch (UnknownHostException unused) {
            return null;
        }
    }

    private boolean canForgetNetwork() {
        WifiInfo wifiInfo = this.mWifiInfo;
        if ((wifiInfo == null || !wifiInfo.isEphemeral()) && !canModifyNetwork() && !this.mAccessPoint.isPasspoint() && !this.mAccessPoint.isPasspointConfig()) {
            return DEBUG;
        }
        return true;
    }

    public boolean canModifyNetwork() {
        WifiConfiguration wifiConfiguration = this.mWifiConfig;
        if (wifiConfiguration == null || WifiUtils.isNetworkLockedDown(this.mContext, wifiConfiguration)) {
            return DEBUG;
        }
        return true;
    }

    private boolean canSignIntoNetwork() {
        if (!this.mAccessPoint.isActive() || !WifiUtils.canSignIntoNetwork(this.mNetworkCapabilities)) {
            return DEBUG;
        }
        return true;
    }

    private boolean canShareNetwork() {
        if (this.mAccessPoint.getConfig() == null || !WifiDppUtils.isSupportConfiguratorQrCodeGenerator(this.mContext, this.mAccessPoint)) {
            return DEBUG;
        }
        return true;
    }

    private void forgetNetwork() {
        WifiInfo wifiInfo = this.mWifiInfo;
        if (wifiInfo != null && wifiInfo.isEphemeral()) {
            this.mWifiManager.disableEphemeralNetwork(this.mWifiInfo.getSSID());
        } else if (!this.mAccessPoint.isPasspoint() && !this.mAccessPoint.isPasspointConfig()) {
            WifiConfiguration wifiConfiguration = this.mWifiConfig;
            if (wifiConfiguration != null) {
                this.mWifiManager.forget(wifiConfiguration.networkId, (WifiManager.ActionListener) null);
            }
        } else if (FeatureFlagPersistent.isEnabled(this.mContext, "settings_network_and_internet_v2")) {
            showConfirmForgetDialog();
            return;
        } else {
            try {
                this.mWifiManager.removePasspointConfiguration(this.mAccessPoint.getPasspointFqdn());
            } catch (RuntimeException unused) {
                Log.e("WifiDetailsPrefCtrl", "Failed to remove Passpoint configuration for " + this.mAccessPoint.getPasspointFqdn());
            }
        }
        this.mMetricsFeatureProvider.action((Context) this.mFragment.getActivity(), 137, (Pair<Integer, Object>[]) new Pair[0]);
        this.mFragment.getActivity().finish();
    }

    /* access modifiers changed from: protected */
    public void showConfirmForgetDialog() {
        new AlertDialog.Builder(this.mContext).setPositiveButton(C1715R.string.forget, new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                WifiDetailPreferenceController.this.lambda$showConfirmForgetDialog$4$WifiDetailPreferenceController(dialogInterface, i);
            }
        }).setNegativeButton(C1715R.string.cancel, (DialogInterface.OnClickListener) null).setTitle(C1715R.string.wifi_forget_dialog_title).setMessage(C1715R.string.forget_passpoint_dialog_message).create().show();
    }

    public /* synthetic */ void lambda$showConfirmForgetDialog$4$WifiDetailPreferenceController(DialogInterface dialogInterface, int i) {
        try {
            this.mWifiManager.removePasspointConfiguration(this.mAccessPoint.getPasspointFqdn());
        } catch (RuntimeException unused) {
            Log.e("WifiDetailsPrefCtrl", "Failed to remove Passpoint configuration for " + this.mAccessPoint.getPasspointFqdn());
        }
        this.mMetricsFeatureProvider.action((Context) this.mFragment.getActivity(), 137, (Pair<Integer, Object>[]) new Pair[0]);
        this.mFragment.getActivity().finish();
    }

    /* access modifiers changed from: private */
    /* renamed from: launchWifiDppConfiguratorActivity */
    public void lambda$shareNetwork$5$WifiDetailPreferenceController() {
        Intent configuratorQrCodeGeneratorIntentOrNull = WifiDppUtils.getConfiguratorQrCodeGeneratorIntentOrNull(this.mContext, this.mWifiManager, this.mAccessPoint);
        if (configuratorQrCodeGeneratorIntentOrNull == null) {
            Log.e("WifiDetailsPrefCtrl", "Launch Wi-Fi DPP QR code generator with a wrong Wi-Fi network!");
            return;
        }
        this.mMetricsFeatureProvider.action(0, 1710, 1595, (String) null, Integer.MIN_VALUE);
        this.mContext.startActivity(configuratorQrCodeGeneratorIntentOrNull);
    }

    private void shareNetwork() {
        WifiDppUtils.showLockScreen(this.mContext, new Runnable() {
            public final void run() {
                WifiDetailPreferenceController.this.lambda$shareNetwork$5$WifiDetailPreferenceController();
            }
        });
    }

    private void signIntoNetwork() {
        this.mMetricsFeatureProvider.action((Context) this.mFragment.getActivity(), 1008, (Pair<Integer, Object>[]) new Pair[0]);
        this.mConnectivityManager.startCaptivePortalApp(this.mNetwork);
    }

    public void onSubmit(WifiDialog wifiDialog) {
        if (wifiDialog.getController() != null) {
            this.mWifiManager.save(wifiDialog.getController().getConfig(), new WifiManager.ActionListener() {
                public void onSuccess() {
                }

                public void onFailure(int i) {
                    FragmentActivity activity = WifiDetailPreferenceController.this.mFragment.getActivity();
                    if (activity != null) {
                        Toast.makeText(activity, C1715R.string.wifi_failed_save_message, 0).show();
                    }
                }
            });
        }
    }

    static class IconInjector {
        private final Context mContext;

        public IconInjector(Context context) {
            this.mContext = context;
        }

        public Drawable getIcon(int i) {
            return this.mContext.getDrawable(Utils.getWifiIconResource(i)).mutate();
        }
    }

    private boolean usingDataUsageHeader(Context context) {
        return FeatureFlagUtils.isEnabled(context, "settings_wifi_details_datausage_header");
    }

    /* access modifiers changed from: package-private */
    public void connectNetwork() {
        FragmentActivity activity = this.mFragment.getActivity();
        if (this.mWifiConfig == null) {
            Toast.makeText(activity, C1715R.string.wifi_failed_connect_message, 0).show();
            return;
        }
        this.mConnectingState = 1;
        if (this.mWifiManager.isWifiEnabled()) {
            updateConnectingState(4);
        } else {
            updateConnectingState(2);
        }
    }

    /* access modifiers changed from: private */
    public void updateConnectingState(int i) {
        FragmentActivity activity = this.mFragment.getActivity();
        Log.d("WifiDetailsPrefCtrl", "updateConnectingState from " + this.mConnectingState + " to " + i);
        int i2 = this.mConnectingState;
        int i3 = 1;
        if (i2 == 1 || i2 == 2) {
            if (i == 2) {
                Log.d("WifiDetailsPrefCtrl", "Turn on Wi-Fi automatically!");
                updateConnectedButton(2);
                Toast.makeText(activity, C1715R.string.wifi_turned_on_message, 0).show();
                this.mWifiManager.setWifiEnabled(true);
                startTimer();
            } else if (i == 4) {
                Log.d("WifiDetailsPrefCtrl", "connecting...");
                updateConnectedButton(4);
                if (this.mAccessPoint.isPasspoint()) {
                    this.mWifiManager.connect(this.mWifiConfig, this.mConnectListener);
                } else {
                    this.mWifiManager.connect(this.mWifiConfig.networkId, this.mConnectListener);
                }
                startTimer();
            } else if (i == 3) {
                Log.e("WifiDetailsPrefCtrl", "Wi-Fi failed to enable network!");
                stopTimer();
                Toast.makeText(activity, C1715R.string.wifi_failed_connect_message, 0).show();
                updateConnectedButton(3);
                i = 1;
            }
        } else if (i2 == 4) {
            if (i == 5) {
                Log.d("WifiDetailsPrefCtrl", "connected");
                stopTimer();
                updateConnectedButton(5);
                Toast.makeText(activity, this.mContext.getString(C1715R.string.wifi_connected_to_message, new Object[]{this.mAccessPoint.getTitle()}), 0).show();
                refreshPage();
            } else {
                if (i == 7) {
                    Log.d("WifiDetailsPrefCtrl", "AP not in range");
                    stopTimer();
                    Toast.makeText(activity, C1715R.string.wifi_not_in_range_message, 0).show();
                    updateConnectedButton(7);
                } else if (i == 6) {
                    Log.d("WifiDetailsPrefCtrl", "failed");
                    stopTimer();
                    Toast.makeText(activity, C1715R.string.wifi_failed_connect_message, 0).show();
                    updateConnectedButton(6);
                }
                this.mConnectingState = i3;
            }
            i3 = i;
            this.mConnectingState = i3;
        } else if (i2 != 5) {
            Log.e("WifiDetailsPrefCtrl", "Invalid state : " + this.mConnectingState);
            return;
        }
        if (i == 8) {
            Log.d("WifiDetailsPrefCtrl", "disconnected");
            updateConnectedButton(8);
            refreshPage();
            this.mWifiInfo = null;
            this.mConnectingState = i3;
        }
        i3 = i;
        this.mConnectingState = i3;
    }

    private void updateConnectedButton(int i) {
        switch (i) {
            case 2:
            case 4:
                this.mButtonsPref.setButton3Text(C1715R.string.wifi_connecting).setButton3Enabled(DEBUG);
                return;
            case 3:
            case 6:
            case 7:
            case 8:
                this.mButtonsPref.setButton3Text(C1715R.string.wifi_connect).setButton3Icon(C1715R.C1717drawable.ic_settings_wireless).setButton3Enabled(true).setButton3Visible(true);
                return;
            case 5:
                this.mButtonsPref.setButton3Text(C1715R.string.wifi_connect).setButton3Icon(C1715R.C1717drawable.ic_settings_wireless).setButton3Enabled(true).setButton3Visible(DEBUG);
                return;
            default:
                Log.e("WifiDetailsPrefCtrl", "Invalid connect button state : " + i);
                return;
        }
    }

    private void startTimer() {
        if (mTimer != null) {
            stopTimer();
        }
        long j = TIMEOUT;
        mTimer = new CountDownTimer(j, j + 1) {
            public void onTick(long j) {
            }

            public void onFinish() {
                if (WifiDetailPreferenceController.this.mFragment == null || WifiDetailPreferenceController.this.mFragment.getActivity() == null) {
                    Log.d("WifiDetailsPrefCtrl", "Ignore timeout since activity not exist!");
                    return;
                }
                Log.e("WifiDetailsPrefCtrl", "Timeout for state:" + WifiDetailPreferenceController.this.mConnectingState);
                if (WifiDetailPreferenceController.this.mConnectingState == 2) {
                    WifiDetailPreferenceController.this.updateConnectingState(3);
                } else if (WifiDetailPreferenceController.this.mConnectingState == 4) {
                    WifiDetailPreferenceController.this.updateAccessPointFromScannedList();
                    if (WifiDetailPreferenceController.this.mIsOutOfRange) {
                        WifiDetailPreferenceController.this.updateConnectingState(7);
                    } else {
                        WifiDetailPreferenceController.this.updateConnectingState(6);
                    }
                }
            }
        };
        mTimer.start();
    }

    private void stopTimer() {
        CountDownTimer countDownTimer = mTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            mTimer = null;
        }
    }
}
