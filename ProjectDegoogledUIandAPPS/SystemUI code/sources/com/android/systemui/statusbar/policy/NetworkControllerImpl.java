package com.android.systemui.statusbar.policy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.telephony.CarrierConfigManager;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.telephony.UiccAccessRule;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import com.android.internal.annotations.GuardedBy;
import com.android.internal.annotations.VisibleForTesting;
import com.android.settingslib.net.DataUsageController;
import com.android.systemui.C1773R$bool;
import com.android.systemui.C1784R$string;
import com.android.systemui.DemoMode;
import com.android.systemui.Dumpable;
import com.android.systemui.settings.CurrentUserTracker;
import com.android.systemui.statusbar.phone.StatusBar;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.MobileSignalController;
import com.android.systemui.statusbar.policy.NetworkController;
import com.android.systemui.statusbar.policy.NetworkControllerImpl;
import com.android.systemui.statusbar.policy.WifiSignalController;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class NetworkControllerImpl extends BroadcastReceiver implements NetworkController, DemoMode, DataUsageController.NetworkNameProvider, Dumpable {
    static final boolean CHATTY = Log.isLoggable("NetworkControllerChat", 3);
    static final boolean DEBUG = Log.isLoggable("NetworkController", 3);
    private final AccessPointControllerImpl mAccessPoints;
    /* access modifiers changed from: private */
    public int mActiveMobileDataSubscription;
    private boolean mAirplaneMode;
    /* access modifiers changed from: private */
    public final CallbackHandler mCallbackHandler;
    /* access modifiers changed from: private */
    public Config mConfig;
    private ConfigurationController.ConfigurationListener mConfigurationListener;
    private final BitSet mConnectedTransports;
    private final ConnectivityManager mConnectivityManager;
    /* access modifiers changed from: private */
    public final Context mContext;
    private List<SubscriptionInfo> mCurrentSubscriptions;
    private int mCurrentUserId;
    private final DataSaverController mDataSaverController;
    private final DataUsageController mDataUsageController;
    private MobileSignalController mDefaultSignalController;
    private boolean mDemoInetCondition;
    private boolean mDemoMode;
    private WifiSignalController.WifiState mDemoWifiState;
    private int mEmergencySource;
    @VisibleForTesting
    final EthernetSignalController mEthernetSignalController;
    private final boolean mHasMobileDataFeature;
    private boolean mHasNoSubs;
    private boolean mInetCondition;
    private boolean mIsEmergency;
    @VisibleForTesting
    ServiceState mLastServiceState;
    @VisibleForTesting
    boolean mListening;
    private Locale mLocale;
    private final Object mLock;
    @VisibleForTesting
    final SparseArray<MobileSignalController> mMobileSignalControllers;
    private final TelephonyManager mPhone;
    private PhoneStateListener mPhoneStateListener;
    /* access modifiers changed from: private */
    public final Handler mReceiverHandler;
    private final Runnable mRegisterListeners;
    private boolean mSimDetected;
    private final SubscriptionDefaults mSubDefaults;
    private SubscriptionManager.OnSubscriptionsChangedListener mSubscriptionListener;
    private final SubscriptionManager mSubscriptionManager;
    private boolean mUserSetup;
    private final CurrentUserTracker mUserTracker;
    private final BitSet mValidatedTransports;
    /* access modifiers changed from: private */
    public final WifiManager mWifiManager;
    @VisibleForTesting
    final WifiSignalController mWifiSignalController;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public NetworkControllerImpl(Context context, Looper looper, DeviceProvisionedController deviceProvisionedController) {
        this(context, (ConnectivityManager) context.getSystemService("connectivity"), (TelephonyManager) context.getSystemService("phone"), (WifiManager) context.getSystemService("wifi"), SubscriptionManager.from(context), Config.readConfig(context), looper, new CallbackHandler(), new AccessPointControllerImpl(context), new DataUsageController(context), new SubscriptionDefaults(), deviceProvisionedController);
        this.mReceiverHandler.post(this.mRegisterListeners);
    }

    @VisibleForTesting
    NetworkControllerImpl(Context context, ConnectivityManager connectivityManager, TelephonyManager telephonyManager, WifiManager wifiManager, SubscriptionManager subscriptionManager, Config config, Looper looper, CallbackHandler callbackHandler, AccessPointControllerImpl accessPointControllerImpl, DataUsageController dataUsageController, SubscriptionDefaults subscriptionDefaults, DeviceProvisionedController deviceProvisionedController) {
        Looper looper2 = looper;
        final DeviceProvisionedController deviceProvisionedController2 = deviceProvisionedController;
        this.mLock = new Object();
        this.mActiveMobileDataSubscription = -1;
        this.mMobileSignalControllers = new SparseArray<>();
        this.mConnectedTransports = new BitSet();
        this.mValidatedTransports = new BitSet();
        this.mAirplaneMode = false;
        this.mLocale = null;
        this.mCurrentSubscriptions = new ArrayList();
        this.mConfigurationListener = new ConfigurationController.ConfigurationListener() {
            public void onConfigChanged(Configuration configuration) {
                NetworkControllerImpl networkControllerImpl = NetworkControllerImpl.this;
                Config unused = networkControllerImpl.mConfig = Config.readConfig(networkControllerImpl.mContext);
                NetworkControllerImpl.this.mReceiverHandler.post(new Runnable() {
                    public final void run() {
                        NetworkControllerImpl.C15461.this.lambda$onConfigChanged$0$NetworkControllerImpl$1();
                    }
                });
            }

            public /* synthetic */ void lambda$onConfigChanged$0$NetworkControllerImpl$1() {
                NetworkControllerImpl.this.handleConfigurationChanged();
            }
        };
        this.mRegisterListeners = new Runnable() {
            public void run() {
                NetworkControllerImpl.this.registerListeners();
            }
        };
        this.mContext = context;
        this.mConfig = config;
        this.mReceiverHandler = new Handler(looper);
        this.mCallbackHandler = callbackHandler;
        this.mDataSaverController = new DataSaverControllerImpl(context);
        this.mSubscriptionManager = subscriptionManager;
        this.mSubDefaults = subscriptionDefaults;
        this.mConnectivityManager = connectivityManager;
        this.mHasMobileDataFeature = this.mConnectivityManager.isNetworkSupported(0);
        this.mPhone = telephonyManager;
        this.mWifiManager = wifiManager;
        this.mLocale = this.mContext.getResources().getConfiguration().locale;
        this.mAccessPoints = accessPointControllerImpl;
        this.mDataUsageController = dataUsageController;
        this.mDataUsageController.setNetworkController(this);
        this.mDataUsageController.setCallback(new DataUsageController.Callback() {
            public void onMobileDataEnabled(boolean z) {
                NetworkControllerImpl.this.mCallbackHandler.setMobileDataEnabled(z);
                NetworkControllerImpl.this.notifyControllersMobileDataChanged();
            }
        });
        this.mWifiSignalController = new WifiSignalController(this.mContext, this.mHasMobileDataFeature, this.mCallbackHandler, this, this.mWifiManager);
        this.mEthernetSignalController = new EthernetSignalController(this.mContext, this.mCallbackHandler, this);
        updateAirplaneMode(true);
        this.mUserTracker = new CurrentUserTracker(this.mContext) {
            public void onUserSwitched(int i) {
                NetworkControllerImpl.this.onUserSwitched(i);
            }
        };
        this.mUserTracker.startTracking();
        deviceProvisionedController2.addCallback(new DeviceProvisionedController.DeviceProvisionedListener() {
            public void onUserSetupChanged() {
                NetworkControllerImpl networkControllerImpl = NetworkControllerImpl.this;
                DeviceProvisionedController deviceProvisionedController = deviceProvisionedController2;
                networkControllerImpl.setUserSetupComplete(deviceProvisionedController.isUserSetup(deviceProvisionedController.getCurrentUser()));
            }
        });
        this.mConnectivityManager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback() {
            private Network mLastNetwork;
            private NetworkCapabilities mLastNetworkCapabilities;

            public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                NetworkCapabilities networkCapabilities2 = this.mLastNetworkCapabilities;
                boolean z = networkCapabilities2 != null && networkCapabilities2.hasCapability(16);
                boolean hasCapability = networkCapabilities.hasCapability(16);
                if (!network.equals(this.mLastNetwork) || !networkCapabilities.equalsTransportTypes(this.mLastNetworkCapabilities) || hasCapability != z) {
                    this.mLastNetwork = network;
                    this.mLastNetworkCapabilities = networkCapabilities;
                    NetworkControllerImpl.this.updateConnectivity();
                }
            }
        }, this.mReceiverHandler);
        this.mPhoneStateListener = new PhoneStateListener(looper) {
            public void onActiveDataSubscriptionIdChanged(int i) {
                int unused = NetworkControllerImpl.this.mActiveMobileDataSubscription = i;
                NetworkControllerImpl.this.doUpdateMobileControllers();
            }
        };
    }

    public DataSaverController getDataSaverController() {
        return this.mDataSaverController;
    }

    /* access modifiers changed from: private */
    public void registerListeners() {
        for (int i = 0; i < this.mMobileSignalControllers.size(); i++) {
            this.mMobileSignalControllers.valueAt(i).registerListener();
        }
        if (this.mSubscriptionListener == null) {
            this.mSubscriptionListener = new SubListener();
        }
        this.mSubscriptionManager.addOnSubscriptionsChangedListener(this.mSubscriptionListener);
        this.mPhone.listen(this.mPhoneStateListener, 4194304);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.RSSI_CHANGED");
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        intentFilter.addAction("android.intent.action.SIM_STATE_CHANGED");
        intentFilter.addAction("android.intent.action.ACTION_DEFAULT_DATA_SUBSCRIPTION_CHANGED");
        intentFilter.addAction("android.intent.action.ACTION_DEFAULT_VOICE_SUBSCRIPTION_CHANGED");
        intentFilter.addAction("android.intent.action.SERVICE_STATE");
        intentFilter.addAction("android.provider.Telephony.SPN_STRINGS_UPDATED");
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.net.conn.INET_CONDITION_ACTION");
        intentFilter.addAction("android.intent.action.AIRPLANE_MODE");
        intentFilter.addAction("android.telephony.action.CARRIER_CONFIG_CHANGED");
        this.mContext.registerReceiver(this, intentFilter, (String) null, this.mReceiverHandler);
        this.mListening = true;
        updateMobileControllers();
    }

    private void unregisterListeners() {
        this.mListening = false;
        for (int i = 0; i < this.mMobileSignalControllers.size(); i++) {
            this.mMobileSignalControllers.valueAt(i).unregisterListener();
        }
        this.mSubscriptionManager.removeOnSubscriptionsChangedListener(this.mSubscriptionListener);
        this.mContext.unregisterReceiver(this);
    }

    public NetworkController.AccessPointController getAccessPointController() {
        return this.mAccessPoints;
    }

    public DataUsageController getMobileDataController() {
        return this.mDataUsageController;
    }

    public boolean hasMobileDataFeature() {
        return this.mHasMobileDataFeature;
    }

    public boolean hasVoiceCallingFeature() {
        return this.mPhone.getPhoneType() != 0;
    }

    private MobileSignalController getDataController() {
        int activeDataSubId = this.mSubDefaults.getActiveDataSubId();
        if (!SubscriptionManager.isValidSubscriptionId(activeDataSubId)) {
            if (DEBUG) {
                Log.e("NetworkController", "No data sim selected");
            }
            return this.mDefaultSignalController;
        } else if (this.mMobileSignalControllers.indexOfKey(activeDataSubId) >= 0) {
            return this.mMobileSignalControllers.get(activeDataSubId);
        } else {
            if (DEBUG) {
                Log.e("NetworkController", "Cannot find controller for data sub: " + activeDataSubId);
            }
            return this.mDefaultSignalController;
        }
    }

    public String getMobileDataNetworkName() {
        MobileSignalController dataController = getDataController();
        return dataController != null ? ((MobileSignalController.MobileState) dataController.getState()).networkNameData : "";
    }

    public int getNumberSubscriptions() {
        return this.mMobileSignalControllers.size();
    }

    /* access modifiers changed from: package-private */
    public boolean isDataControllerDisabled() {
        MobileSignalController dataController = getDataController();
        if (dataController == null) {
            return false;
        }
        return dataController.isDataDisabled();
    }

    /* access modifiers changed from: private */
    public void notifyControllersMobileDataChanged() {
        for (int i = 0; i < this.mMobileSignalControllers.size(); i++) {
            this.mMobileSignalControllers.valueAt(i).onMobileDataChanged();
        }
    }

    public boolean isEmergencyOnly() {
        if (this.mMobileSignalControllers.size() == 0) {
            this.mEmergencySource = 0;
            ServiceState serviceState = this.mLastServiceState;
            if (serviceState == null || !serviceState.isEmergencyOnly()) {
                return false;
            }
            return true;
        }
        int defaultVoiceSubId = this.mSubDefaults.getDefaultVoiceSubId();
        if (!SubscriptionManager.isValidSubscriptionId(defaultVoiceSubId)) {
            for (int i = 0; i < this.mMobileSignalControllers.size(); i++) {
                MobileSignalController valueAt = this.mMobileSignalControllers.valueAt(i);
                if (!((MobileSignalController.MobileState) valueAt.getState()).isEmergency) {
                    this.mEmergencySource = valueAt.mSubscriptionInfo.getSubscriptionId() + 100;
                    if (DEBUG) {
                        Log.d("NetworkController", "Found emergency " + valueAt.mTag);
                    }
                    return false;
                }
            }
        }
        if (this.mMobileSignalControllers.indexOfKey(defaultVoiceSubId) >= 0) {
            this.mEmergencySource = defaultVoiceSubId + 200;
            if (DEBUG) {
                Log.d("NetworkController", "Getting emergency from " + defaultVoiceSubId);
            }
            return ((MobileSignalController.MobileState) this.mMobileSignalControllers.get(defaultVoiceSubId).getState()).isEmergency;
        } else if (this.mMobileSignalControllers.size() == 1) {
            this.mEmergencySource = this.mMobileSignalControllers.keyAt(0) + 400;
            if (DEBUG) {
                Log.d("NetworkController", "Getting assumed emergency from " + this.mMobileSignalControllers.keyAt(0));
            }
            return ((MobileSignalController.MobileState) this.mMobileSignalControllers.valueAt(0).getState()).isEmergency;
        } else {
            if (DEBUG) {
                Log.e("NetworkController", "Cannot find controller for voice sub: " + defaultVoiceSubId);
            }
            this.mEmergencySource = defaultVoiceSubId + StatusBar.FADE_KEYGUARD_DURATION;
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public void recalculateEmergency() {
        this.mIsEmergency = isEmergencyOnly();
        this.mCallbackHandler.setEmergencyCallsOnly(this.mIsEmergency);
    }

    public void addCallback(NetworkController.SignalCallback signalCallback) {
        signalCallback.setSubs(this.mCurrentSubscriptions);
        signalCallback.setIsAirplaneMode(new NetworkController.IconState(this.mAirplaneMode, TelephonyIcons.FLIGHT_MODE_ICON, C1784R$string.accessibility_airplane_mode, this.mContext));
        signalCallback.setNoSims(this.mHasNoSubs, this.mSimDetected);
        this.mWifiSignalController.notifyListeners(signalCallback);
        this.mEthernetSignalController.notifyListeners(signalCallback);
        for (int i = 0; i < this.mMobileSignalControllers.size(); i++) {
            this.mMobileSignalControllers.valueAt(i).notifyListeners(signalCallback);
        }
        this.mCallbackHandler.setListening(signalCallback, true);
    }

    public void removeCallback(NetworkController.SignalCallback signalCallback) {
        this.mCallbackHandler.setListening(signalCallback, false);
    }

    public void setWifiEnabled(final boolean z) {
        new AsyncTask<Void, Void, Void>() {
            /* access modifiers changed from: protected */
            public Void doInBackground(Void... voidArr) {
                NetworkControllerImpl.this.mWifiManager.setWifiEnabled(z);
                return null;
            }
        }.execute(new Void[0]);
    }

    /* access modifiers changed from: private */
    public void onUserSwitched(int i) {
        this.mCurrentUserId = i;
        this.mAccessPoints.onUserSwitched(i);
        updateConnectivity();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onReceive(android.content.Context r4, android.content.Intent r5) {
        /*
            r3 = this;
            boolean r4 = CHATTY
            if (r4 == 0) goto L_0x001a
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r0 = "onReceive: intent="
            r4.append(r0)
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            java.lang.String r0 = "NetworkController"
            android.util.Log.d(r0, r4)
        L_0x001a:
            java.lang.String r4 = r5.getAction()
            int r0 = r4.hashCode()
            r1 = -1
            r2 = 0
            switch(r0) {
                case -2104353374: goto L_0x006e;
                case -1465084191: goto L_0x0064;
                case -1172645946: goto L_0x005a;
                case -1138588223: goto L_0x0050;
                case -1076576821: goto L_0x0046;
                case -229777127: goto L_0x003c;
                case -25388475: goto L_0x0032;
                case 623179603: goto L_0x0028;
                default: goto L_0x0027;
            }
        L_0x0027:
            goto L_0x0078
        L_0x0028:
            java.lang.String r0 = "android.net.conn.INET_CONDITION_ACTION"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0078
            r4 = 1
            goto L_0x0079
        L_0x0032:
            java.lang.String r0 = "android.intent.action.ACTION_DEFAULT_DATA_SUBSCRIPTION_CHANGED"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0078
            r4 = 4
            goto L_0x0079
        L_0x003c:
            java.lang.String r0 = "android.intent.action.SIM_STATE_CHANGED"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0078
            r4 = 5
            goto L_0x0079
        L_0x0046:
            java.lang.String r0 = "android.intent.action.AIRPLANE_MODE"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0078
            r4 = 2
            goto L_0x0079
        L_0x0050:
            java.lang.String r0 = "android.telephony.action.CARRIER_CONFIG_CHANGED"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0078
            r4 = 7
            goto L_0x0079
        L_0x005a:
            java.lang.String r0 = "android.net.conn.CONNECTIVITY_CHANGE"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0078
            r4 = r2
            goto L_0x0079
        L_0x0064:
            java.lang.String r0 = "android.intent.action.ACTION_DEFAULT_VOICE_SUBSCRIPTION_CHANGED"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0078
            r4 = 3
            goto L_0x0079
        L_0x006e:
            java.lang.String r0 = "android.intent.action.SERVICE_STATE"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0078
            r4 = 6
            goto L_0x0079
        L_0x0078:
            r4 = r1
        L_0x0079:
            switch(r4) {
                case 0: goto L_0x0107;
                case 1: goto L_0x0107;
                case 2: goto L_0x0100;
                case 3: goto L_0x00fc;
                case 4: goto L_0x00d3;
                case 5: goto L_0x00c6;
                case 6: goto L_0x00b0;
                case 7: goto L_0x009d;
                default: goto L_0x007c;
            }
        L_0x007c:
            java.lang.String r4 = "subscription"
            int r4 = r5.getIntExtra(r4, r1)
            boolean r0 = android.telephony.SubscriptionManager.isValidSubscriptionId(r4)
            if (r0 == 0) goto L_0x010f
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController> r0 = r3.mMobileSignalControllers
            int r0 = r0.indexOfKey(r4)
            if (r0 < 0) goto L_0x010b
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController> r3 = r3.mMobileSignalControllers
            java.lang.Object r3 = r3.get(r4)
            com.android.systemui.statusbar.policy.MobileSignalController r3 = (com.android.systemui.statusbar.policy.MobileSignalController) r3
            r3.handleBroadcast(r5)
            goto L_0x0114
        L_0x009d:
            android.content.Context r4 = r3.mContext
            com.android.systemui.statusbar.policy.NetworkControllerImpl$Config r4 = com.android.systemui.statusbar.policy.NetworkControllerImpl.Config.readConfig(r4)
            r3.mConfig = r4
            android.os.Handler r4 = r3.mReceiverHandler
            com.android.systemui.statusbar.policy.-$$Lambda$ybM43k5QVX_SxWbQACu1XwL3Knk r5 = new com.android.systemui.statusbar.policy.-$$Lambda$ybM43k5QVX_SxWbQACu1XwL3Knk
            r5.<init>()
            r4.post(r5)
            goto L_0x0114
        L_0x00b0:
            android.os.Bundle r4 = r5.getExtras()
            android.telephony.ServiceState r4 = android.telephony.ServiceState.newFromBundle(r4)
            r3.mLastServiceState = r4
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController> r4 = r3.mMobileSignalControllers
            int r4 = r4.size()
            if (r4 != 0) goto L_0x0114
            r3.recalculateEmergency()
            goto L_0x0114
        L_0x00c6:
            java.lang.String r4 = "rebroadcastOnUnlock"
            boolean r4 = r5.getBooleanExtra(r4, r2)
            if (r4 == 0) goto L_0x00cf
            goto L_0x0114
        L_0x00cf:
            r3.updateMobileControllers()
            goto L_0x0114
        L_0x00d3:
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController> r4 = r3.mMobileSignalControllers
            int r4 = r4.size()
            if (r2 >= r4) goto L_0x00e9
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController> r4 = r3.mMobileSignalControllers
            java.lang.Object r4 = r4.valueAt(r2)
            com.android.systemui.statusbar.policy.MobileSignalController r4 = (com.android.systemui.statusbar.policy.MobileSignalController) r4
            r4.handleBroadcast(r5)
            int r2 = r2 + 1
            goto L_0x00d3
        L_0x00e9:
            android.content.Context r4 = r3.mContext
            com.android.systemui.statusbar.policy.NetworkControllerImpl$Config r4 = com.android.systemui.statusbar.policy.NetworkControllerImpl.Config.readConfig(r4)
            r3.mConfig = r4
            android.os.Handler r4 = r3.mReceiverHandler
            com.android.systemui.statusbar.policy.-$$Lambda$ybM43k5QVX_SxWbQACu1XwL3Knk r5 = new com.android.systemui.statusbar.policy.-$$Lambda$ybM43k5QVX_SxWbQACu1XwL3Knk
            r5.<init>()
            r4.post(r5)
            goto L_0x0114
        L_0x00fc:
            r3.recalculateEmergency()
            goto L_0x0114
        L_0x0100:
            r3.refreshLocale()
            r3.updateAirplaneMode(r2)
            goto L_0x0114
        L_0x0107:
            r3.updateConnectivity()
            goto L_0x0114
        L_0x010b:
            r3.updateMobileControllers()
            goto L_0x0114
        L_0x010f:
            com.android.systemui.statusbar.policy.WifiSignalController r3 = r3.mWifiSignalController
            r3.handleBroadcast(r5)
        L_0x0114:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.policy.NetworkControllerImpl.onReceive(android.content.Context, android.content.Intent):void");
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void handleConfigurationChanged() {
        updateMobileControllers();
        for (int i = 0; i < this.mMobileSignalControllers.size(); i++) {
            this.mMobileSignalControllers.valueAt(i).setConfiguration(this.mConfig);
        }
        refreshLocale();
    }

    /* access modifiers changed from: private */
    public void updateMobileControllers() {
        if (this.mListening) {
            doUpdateMobileControllers();
        }
    }

    private void filterMobileSubscriptionInSameGroup(List<SubscriptionInfo> list) {
        if (list.size() == 2) {
            SubscriptionInfo subscriptionInfo = list.get(0);
            SubscriptionInfo subscriptionInfo2 = list.get(1);
            if (subscriptionInfo.getGroupUuid() != null && subscriptionInfo.getGroupUuid().equals(subscriptionInfo2.getGroupUuid())) {
                if (!subscriptionInfo.isOpportunistic() && !subscriptionInfo2.isOpportunistic()) {
                    return;
                }
                if (CarrierConfigManager.getDefaultConfig().getBoolean("always_show_primary_signal_bar_in_opportunistic_network_boolean")) {
                    if (!subscriptionInfo.isOpportunistic()) {
                        subscriptionInfo = subscriptionInfo2;
                    }
                    list.remove(subscriptionInfo);
                    return;
                }
                if (subscriptionInfo.getSubscriptionId() == this.mActiveMobileDataSubscription) {
                    subscriptionInfo = subscriptionInfo2;
                }
                list.remove(subscriptionInfo);
            }
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void doUpdateMobileControllers() {
        List activeSubscriptionInfoList = this.mSubscriptionManager.getActiveSubscriptionInfoList(false);
        if (activeSubscriptionInfoList == null) {
            activeSubscriptionInfoList = Collections.emptyList();
        }
        filterMobileSubscriptionInSameGroup(activeSubscriptionInfoList);
        if (hasCorrectMobileControllers(activeSubscriptionInfoList)) {
            updateNoSims();
            return;
        }
        synchronized (this.mLock) {
            setCurrentSubscriptionsLocked(activeSubscriptionInfoList);
        }
        updateNoSims();
        recalculateEmergency();
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public void updateNoSims() {
        boolean z = this.mHasMobileDataFeature && this.mMobileSignalControllers.size() == 0;
        boolean hasAnySim = hasAnySim();
        if (z != this.mHasNoSubs || hasAnySim != this.mSimDetected) {
            this.mHasNoSubs = z;
            this.mSimDetected = hasAnySim;
            this.mCallbackHandler.setNoSims(this.mHasNoSubs, this.mSimDetected);
        }
    }

    private boolean hasAnySim() {
        int simCount = this.mPhone.getSimCount();
        for (int i = 0; i < simCount; i++) {
            int simState = this.mPhone.getSimState(i);
            if (simState != 1 && simState != 0) {
                return true;
            }
        }
        return false;
    }

    @GuardedBy({"mLock"})
    @VisibleForTesting
    public void setCurrentSubscriptionsLocked(List<SubscriptionInfo> list) {
        int i;
        List<SubscriptionInfo> list2 = list;
        Collections.sort(list2, new Comparator<SubscriptionInfo>() {
            public int compare(SubscriptionInfo subscriptionInfo, SubscriptionInfo subscriptionInfo2) {
                int i;
                int i2;
                if (subscriptionInfo.getSimSlotIndex() == subscriptionInfo2.getSimSlotIndex()) {
                    i2 = subscriptionInfo.getSubscriptionId();
                    i = subscriptionInfo2.getSubscriptionId();
                } else {
                    i2 = subscriptionInfo.getSimSlotIndex();
                    i = subscriptionInfo2.getSimSlotIndex();
                }
                return i2 - i;
            }
        });
        this.mCurrentSubscriptions = list2;
        SparseArray sparseArray = new SparseArray();
        for (int i2 = 0; i2 < this.mMobileSignalControllers.size(); i2++) {
            sparseArray.put(this.mMobileSignalControllers.keyAt(i2), this.mMobileSignalControllers.valueAt(i2));
        }
        this.mMobileSignalControllers.clear();
        int size = list.size();
        int i3 = 0;
        while (i3 < size) {
            int subscriptionId = list2.get(i3).getSubscriptionId();
            if (sparseArray.indexOfKey(subscriptionId) >= 0) {
                this.mMobileSignalControllers.put(subscriptionId, (MobileSignalController) sparseArray.get(subscriptionId));
                sparseArray.remove(subscriptionId);
                i = size;
            } else {
                SubscriptionDefaults subscriptionDefaults = this.mSubDefaults;
                MobileSignalController mobileSignalController = r0;
                SubscriptionDefaults subscriptionDefaults2 = subscriptionDefaults;
                i = size;
                MobileSignalController mobileSignalController2 = new MobileSignalController(this.mContext, this.mConfig, this.mHasMobileDataFeature, this.mPhone.createForSubscriptionId(subscriptionId), this.mCallbackHandler, this, list2.get(i3), subscriptionDefaults2, this.mReceiverHandler.getLooper());
                mobileSignalController.setUserSetupComplete(this.mUserSetup);
                this.mMobileSignalControllers.put(subscriptionId, mobileSignalController);
                if (list2.get(i3).getSimSlotIndex() == 0) {
                    this.mDefaultSignalController = mobileSignalController;
                }
                if (this.mListening) {
                    mobileSignalController.registerListener();
                }
            }
            i3++;
            size = i;
        }
        if (this.mListening) {
            for (int i4 = 0; i4 < sparseArray.size(); i4++) {
                int keyAt = sparseArray.keyAt(i4);
                if (sparseArray.get(keyAt) == this.mDefaultSignalController) {
                    this.mDefaultSignalController = null;
                }
                ((MobileSignalController) sparseArray.get(keyAt)).unregisterListener();
            }
        }
        this.mCallbackHandler.setSubs(list2);
        notifyAllListeners();
        pushConnectivityToSignals();
        updateAirplaneMode(true);
    }

    /* access modifiers changed from: private */
    public void setUserSetupComplete(boolean z) {
        this.mReceiverHandler.post(new Runnable(z) {
            private final /* synthetic */ boolean f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                NetworkControllerImpl.this.lambda$setUserSetupComplete$0$NetworkControllerImpl(this.f$1);
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: handleSetUserSetupComplete */
    public void lambda$setUserSetupComplete$0$NetworkControllerImpl(boolean z) {
        this.mUserSetup = z;
        for (int i = 0; i < this.mMobileSignalControllers.size(); i++) {
            this.mMobileSignalControllers.valueAt(i).setUserSetupComplete(this.mUserSetup);
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public boolean hasCorrectMobileControllers(List<SubscriptionInfo> list) {
        if (list.size() != this.mMobileSignalControllers.size()) {
            return false;
        }
        for (SubscriptionInfo subscriptionId : list) {
            if (this.mMobileSignalControllers.indexOfKey(subscriptionId.getSubscriptionId()) < 0) {
                return false;
            }
        }
        return true;
    }

    private void updateAirplaneMode(boolean z) {
        boolean z2 = true;
        if (Settings.Global.getInt(this.mContext.getContentResolver(), "airplane_mode_on", 0) != 1) {
            z2 = false;
        }
        if (z2 != this.mAirplaneMode || z) {
            this.mAirplaneMode = z2;
            for (int i = 0; i < this.mMobileSignalControllers.size(); i++) {
                this.mMobileSignalControllers.valueAt(i).setAirplaneMode(this.mAirplaneMode);
            }
            notifyListeners();
        }
    }

    private void refreshLocale() {
        Locale locale = this.mContext.getResources().getConfiguration().locale;
        if (!locale.equals(this.mLocale)) {
            this.mLocale = locale;
            this.mWifiSignalController.refreshLocale();
            notifyAllListeners();
        }
    }

    private void notifyAllListeners() {
        notifyListeners();
        for (int i = 0; i < this.mMobileSignalControllers.size(); i++) {
            this.mMobileSignalControllers.valueAt(i).notifyListeners();
        }
        this.mWifiSignalController.notifyListeners();
        this.mEthernetSignalController.notifyListeners();
    }

    private void notifyListeners() {
        this.mCallbackHandler.setIsAirplaneMode(new NetworkController.IconState(this.mAirplaneMode, TelephonyIcons.FLIGHT_MODE_ICON, C1784R$string.accessibility_airplane_mode, this.mContext));
        this.mCallbackHandler.setNoSims(this.mHasNoSubs, this.mSimDetected);
    }

    /* access modifiers changed from: private */
    public void updateConnectivity() {
        this.mConnectedTransports.clear();
        this.mValidatedTransports.clear();
        for (NetworkCapabilities networkCapabilities : this.mConnectivityManager.getDefaultNetworkCapabilitiesForUser(this.mCurrentUserId)) {
            for (int i : networkCapabilities.getTransportTypes()) {
                this.mConnectedTransports.set(i);
                if (networkCapabilities.hasCapability(16)) {
                    this.mValidatedTransports.set(i);
                }
            }
        }
        if (CHATTY) {
            Log.d("NetworkController", "updateConnectivity: mConnectedTransports=" + this.mConnectedTransports);
            Log.d("NetworkController", "updateConnectivity: mValidatedTransports=" + this.mValidatedTransports);
        }
        this.mInetCondition = !this.mValidatedTransports.isEmpty();
        pushConnectivityToSignals();
    }

    private void pushConnectivityToSignals() {
        for (int i = 0; i < this.mMobileSignalControllers.size(); i++) {
            this.mMobileSignalControllers.valueAt(i).updateConnectivity(this.mConnectedTransports, this.mValidatedTransports);
        }
        this.mWifiSignalController.updateConnectivity(this.mConnectedTransports, this.mValidatedTransports);
        this.mEthernetSignalController.updateConnectivity(this.mConnectedTransports, this.mValidatedTransports);
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.println("NetworkController state:");
        printWriter.println("  - telephony ------");
        printWriter.print("  hasVoiceCallingFeature()=");
        printWriter.println(hasVoiceCallingFeature());
        printWriter.println("  - connectivity ------");
        printWriter.print("  mConnectedTransports=");
        printWriter.println(this.mConnectedTransports);
        printWriter.print("  mValidatedTransports=");
        printWriter.println(this.mValidatedTransports);
        printWriter.print("  mInetCondition=");
        printWriter.println(this.mInetCondition);
        printWriter.print("  mAirplaneMode=");
        printWriter.println(this.mAirplaneMode);
        printWriter.print("  mLocale=");
        printWriter.println(this.mLocale);
        printWriter.print("  mLastServiceState=");
        printWriter.println(this.mLastServiceState);
        printWriter.print("  mIsEmergency=");
        printWriter.println(this.mIsEmergency);
        printWriter.print("  mEmergencySource=");
        printWriter.println(emergencyToString(this.mEmergencySource));
        printWriter.println("  - config ------");
        printWriter.print("  patternOfCarrierSpecificDataIcon=");
        printWriter.println(this.mConfig.patternOfCarrierSpecificDataIcon);
        printWriter.print("  nr5GIconMap=");
        printWriter.println(this.mConfig.nr5GIconMap.toString());
        printWriter.print("  nrIconDisplayGracePeriodMs=");
        printWriter.println(this.mConfig.nrIconDisplayGracePeriodMs);
        for (int i = 0; i < this.mMobileSignalControllers.size(); i++) {
            this.mMobileSignalControllers.valueAt(i).dump(printWriter);
        }
        this.mWifiSignalController.dump(printWriter);
        this.mEthernetSignalController.dump(printWriter);
        this.mAccessPoints.dump(printWriter);
    }

    private static final String emergencyToString(int i) {
        if (i > 300) {
            return "ASSUMED_VOICE_CONTROLLER(" + (i - 200) + ")";
        } else if (i > 300) {
            return "NO_SUB(" + (i - StatusBar.FADE_KEYGUARD_DURATION) + ")";
        } else if (i > 200) {
            return "VOICE_CONTROLLER(" + (i - 200) + ")";
        } else if (i <= 100) {
            return i == 0 ? "NO_CONTROLLERS" : "UNKNOWN_SOURCE";
        } else {
            return "FIRST_CONTROLLER(" + (i - 100) + ")";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:209:0x03f2  */
    /* JADX WARNING: Removed duplicated region for block: B:215:0x0406  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x015d  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0175  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void dispatchDemoCommand(java.lang.String r18, android.os.Bundle r19) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            boolean r3 = r0.mDemoMode
            java.lang.String r4 = "NetworkController"
            r5 = 1
            if (r3 != 0) goto L_0x0039
            java.lang.String r3 = "enter"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0039
            boolean r1 = DEBUG
            if (r1 == 0) goto L_0x001e
            java.lang.String r1 = "Entering demo mode"
            android.util.Log.d(r4, r1)
        L_0x001e:
            r17.unregisterListeners()
            r0.mDemoMode = r5
            boolean r1 = r0.mInetCondition
            r0.mDemoInetCondition = r1
            com.android.systemui.statusbar.policy.WifiSignalController r1 = r0.mWifiSignalController
            com.android.systemui.statusbar.policy.SignalController$State r1 = r1.getState()
            com.android.systemui.statusbar.policy.WifiSignalController$WifiState r1 = (com.android.systemui.statusbar.policy.WifiSignalController.WifiState) r1
            r0.mDemoWifiState = r1
            com.android.systemui.statusbar.policy.WifiSignalController$WifiState r0 = r0.mDemoWifiState
            java.lang.String r1 = "DemoMode"
            r0.ssid = r1
            goto L_0x043f
        L_0x0039:
            boolean r3 = r0.mDemoMode
            r6 = 0
            if (r3 == 0) goto L_0x007b
            java.lang.String r3 = "exit"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x007b
            boolean r1 = DEBUG
            if (r1 == 0) goto L_0x004f
            java.lang.String r1 = "Exiting demo mode"
            android.util.Log.d(r4, r1)
        L_0x004f:
            r0.mDemoMode = r6
            r17.updateMobileControllers()
        L_0x0054:
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController> r1 = r0.mMobileSignalControllers
            int r1 = r1.size()
            if (r6 >= r1) goto L_0x006a
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController> r1 = r0.mMobileSignalControllers
            java.lang.Object r1 = r1.valueAt(r6)
            com.android.systemui.statusbar.policy.MobileSignalController r1 = (com.android.systemui.statusbar.policy.MobileSignalController) r1
            r1.resetLastState()
            int r6 = r6 + 1
            goto L_0x0054
        L_0x006a:
            com.android.systemui.statusbar.policy.WifiSignalController r1 = r0.mWifiSignalController
            r1.resetLastState()
            android.os.Handler r1 = r0.mReceiverHandler
            java.lang.Runnable r2 = r0.mRegisterListeners
            r1.post(r2)
            r17.notifyAllListeners()
            goto L_0x043f
        L_0x007b:
            boolean r3 = r0.mDemoMode
            if (r3 == 0) goto L_0x043f
            java.lang.String r3 = "network"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x043f
            java.lang.String r1 = "airplane"
            java.lang.String r1 = r2.getString(r1)
            java.lang.String r3 = "show"
            if (r1 == 0) goto L_0x00a5
            boolean r1 = r1.equals(r3)
            com.android.systemui.statusbar.policy.CallbackHandler r4 = r0.mCallbackHandler
            com.android.systemui.statusbar.policy.NetworkController$IconState r7 = new com.android.systemui.statusbar.policy.NetworkController$IconState
            int r8 = com.android.systemui.statusbar.policy.TelephonyIcons.FLIGHT_MODE_ICON
            int r9 = com.android.systemui.C1784R$string.accessibility_airplane_mode
            android.content.Context r10 = r0.mContext
            r7.<init>(r1, r8, r9, r10)
            r4.setIsAirplaneMode(r7)
        L_0x00a5:
            java.lang.String r1 = "fully"
            java.lang.String r1 = r2.getString(r1)
            if (r1 == 0) goto L_0x00e8
            boolean r1 = java.lang.Boolean.parseBoolean(r1)
            r0.mDemoInetCondition = r1
            java.util.BitSet r1 = new java.util.BitSet
            r1.<init>()
            boolean r4 = r0.mDemoInetCondition
            if (r4 == 0) goto L_0x00c3
            com.android.systemui.statusbar.policy.WifiSignalController r4 = r0.mWifiSignalController
            int r4 = r4.mTransportType
            r1.set(r4)
        L_0x00c3:
            com.android.systemui.statusbar.policy.WifiSignalController r4 = r0.mWifiSignalController
            r4.updateConnectivity(r1, r1)
            r4 = r6
        L_0x00c9:
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController> r7 = r0.mMobileSignalControllers
            int r7 = r7.size()
            if (r4 >= r7) goto L_0x00e8
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController> r7 = r0.mMobileSignalControllers
            java.lang.Object r7 = r7.valueAt(r4)
            com.android.systemui.statusbar.policy.MobileSignalController r7 = (com.android.systemui.statusbar.policy.MobileSignalController) r7
            boolean r8 = r0.mDemoInetCondition
            if (r8 == 0) goto L_0x00e2
            int r8 = r7.mTransportType
            r1.set(r8)
        L_0x00e2:
            r7.updateConnectivity(r1, r1)
            int r4 = r4 + 1
            goto L_0x00c9
        L_0x00e8:
            java.lang.String r1 = "wifi"
            java.lang.String r1 = r2.getString(r1)
            java.lang.String r4 = "out"
            java.lang.String r7 = "in"
            r8 = 100357129(0x5fb5409, float:2.3634796E-35)
            r9 = 110414(0x1af4e, float:1.54723E-40)
            r10 = 3365(0xd25, float:4.715E-42)
            java.lang.String r11 = "null"
            java.lang.String r12 = "activity"
            java.lang.String r13 = "level"
            if (r1 == 0) goto L_0x0197
            boolean r1 = r1.equals(r3)
            java.lang.String r14 = r2.getString(r13)
            if (r14 == 0) goto L_0x012f
            com.android.systemui.statusbar.policy.WifiSignalController$WifiState r6 = r0.mDemoWifiState
            boolean r16 = r14.equals(r11)
            if (r16 == 0) goto L_0x0116
            r14 = -1
            goto L_0x0122
        L_0x0116:
            int r14 = java.lang.Integer.parseInt(r14)
            int r16 = com.android.systemui.statusbar.policy.WifiIcons.WIFI_LEVEL_COUNT
            int r15 = r16 + -1
            int r14 = java.lang.Math.min(r14, r15)
        L_0x0122:
            r6.level = r14
            com.android.systemui.statusbar.policy.WifiSignalController$WifiState r6 = r0.mDemoWifiState
            int r14 = r6.level
            if (r14 < 0) goto L_0x012c
            r14 = r5
            goto L_0x012d
        L_0x012c:
            r14 = 0
        L_0x012d:
            r6.connected = r14
        L_0x012f:
            java.lang.String r6 = r2.getString(r12)
            if (r6 == 0) goto L_0x017c
            int r14 = r6.hashCode()
            if (r14 == r10) goto L_0x0152
            if (r14 == r9) goto L_0x014a
            if (r14 == r8) goto L_0x0140
            goto L_0x015a
        L_0x0140:
            java.lang.String r14 = "inout"
            boolean r6 = r6.equals(r14)
            if (r6 == 0) goto L_0x015a
            r6 = 0
            goto L_0x015b
        L_0x014a:
            boolean r6 = r6.equals(r4)
            if (r6 == 0) goto L_0x015a
            r6 = 2
            goto L_0x015b
        L_0x0152:
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x015a
            r6 = r5
            goto L_0x015b
        L_0x015a:
            r6 = -1
        L_0x015b:
            if (r6 == 0) goto L_0x0175
            if (r6 == r5) goto L_0x016f
            r14 = 2
            if (r6 == r14) goto L_0x0169
            com.android.systemui.statusbar.policy.WifiSignalController r6 = r0.mWifiSignalController
            r15 = 0
            r6.setActivity(r15)
            goto L_0x0182
        L_0x0169:
            com.android.systemui.statusbar.policy.WifiSignalController r6 = r0.mWifiSignalController
            r6.setActivity(r14)
            goto L_0x0182
        L_0x016f:
            com.android.systemui.statusbar.policy.WifiSignalController r6 = r0.mWifiSignalController
            r6.setActivity(r5)
            goto L_0x0182
        L_0x0175:
            com.android.systemui.statusbar.policy.WifiSignalController r6 = r0.mWifiSignalController
            r14 = 3
            r6.setActivity(r14)
            goto L_0x0182
        L_0x017c:
            com.android.systemui.statusbar.policy.WifiSignalController r6 = r0.mWifiSignalController
            r14 = 0
            r6.setActivity(r14)
        L_0x0182:
            java.lang.String r6 = "ssid"
            java.lang.String r6 = r2.getString(r6)
            if (r6 == 0) goto L_0x018e
            com.android.systemui.statusbar.policy.WifiSignalController$WifiState r14 = r0.mDemoWifiState
            r14.ssid = r6
        L_0x018e:
            com.android.systemui.statusbar.policy.WifiSignalController$WifiState r6 = r0.mDemoWifiState
            r6.enabled = r1
            com.android.systemui.statusbar.policy.WifiSignalController r1 = r0.mWifiSignalController
            r1.notifyListeners()
        L_0x0197:
            java.lang.String r1 = "sims"
            java.lang.String r1 = r2.getString(r1)
            r6 = 8
            if (r1 == 0) goto L_0x01f2
            int r1 = java.lang.Integer.parseInt(r1)
            int r1 = android.util.MathUtils.constrain(r1, r5, r6)
            java.util.ArrayList r14 = new java.util.ArrayList
            r14.<init>()
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController> r15 = r0.mMobileSignalControllers
            int r15 = r15.size()
            if (r1 == r15) goto L_0x01f2
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController> r15 = r0.mMobileSignalControllers
            r15.clear()
            android.telephony.SubscriptionManager r15 = r0.mSubscriptionManager
            int r15 = r15.getActiveSubscriptionInfoCountMax()
            r8 = r15
        L_0x01c2:
            int r9 = r15 + r1
            if (r8 >= r9) goto L_0x01d0
            android.telephony.SubscriptionInfo r9 = r0.addSignalController(r8, r8)
            r14.add(r9)
            int r8 = r8 + 1
            goto L_0x01c2
        L_0x01d0:
            com.android.systemui.statusbar.policy.CallbackHandler r1 = r0.mCallbackHandler
            r1.setSubs(r14)
            r1 = 0
        L_0x01d6:
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController> r8 = r0.mMobileSignalControllers
            int r8 = r8.size()
            if (r1 >= r8) goto L_0x01f2
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController> r8 = r0.mMobileSignalControllers
            int r8 = r8.keyAt(r1)
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController> r9 = r0.mMobileSignalControllers
            java.lang.Object r8 = r9.get(r8)
            com.android.systemui.statusbar.policy.MobileSignalController r8 = (com.android.systemui.statusbar.policy.MobileSignalController) r8
            r8.notifyListeners()
            int r1 = r1 + 1
            goto L_0x01d6
        L_0x01f2:
            java.lang.String r1 = "nosim"
            java.lang.String r1 = r2.getString(r1)
            if (r1 == 0) goto L_0x0209
            boolean r1 = r1.equals(r3)
            r0.mHasNoSubs = r1
            com.android.systemui.statusbar.policy.CallbackHandler r1 = r0.mCallbackHandler
            boolean r8 = r0.mHasNoSubs
            boolean r9 = r0.mSimDetected
            r1.setNoSims(r8, r9)
        L_0x0209:
            java.lang.String r1 = "mobile"
            java.lang.String r1 = r2.getString(r1)
            if (r1 == 0) goto L_0x041c
            boolean r1 = r1.equals(r3)
            java.lang.String r8 = "datatype"
            java.lang.String r8 = r2.getString(r8)
            java.lang.String r9 = "slot"
            java.lang.String r9 = r2.getString(r9)
            boolean r14 = android.text.TextUtils.isEmpty(r9)
            if (r14 == 0) goto L_0x0229
            r9 = 0
            goto L_0x022d
        L_0x0229:
            int r9 = java.lang.Integer.parseInt(r9)
        L_0x022d:
            r14 = 0
            int r6 = android.util.MathUtils.constrain(r9, r14, r6)
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
        L_0x0237:
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController> r14 = r0.mMobileSignalControllers
            int r14 = r14.size()
            if (r14 > r6) goto L_0x024d
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController> r14 = r0.mMobileSignalControllers
            int r14 = r14.size()
            android.telephony.SubscriptionInfo r14 = r0.addSignalController(r14, r14)
            r9.add(r14)
            goto L_0x0237
        L_0x024d:
            boolean r14 = r9.isEmpty()
            if (r14 != 0) goto L_0x0258
            com.android.systemui.statusbar.policy.CallbackHandler r14 = r0.mCallbackHandler
            r14.setSubs(r9)
        L_0x0258:
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController> r9 = r0.mMobileSignalControllers
            java.lang.Object r6 = r9.valueAt(r6)
            com.android.systemui.statusbar.policy.MobileSignalController r6 = (com.android.systemui.statusbar.policy.MobileSignalController) r6
            com.android.systemui.statusbar.policy.SignalController$State r9 = r6.getState()
            com.android.systemui.statusbar.policy.MobileSignalController$MobileState r9 = (com.android.systemui.statusbar.policy.MobileSignalController.MobileState) r9
            if (r8 == 0) goto L_0x026a
            r14 = r5
            goto L_0x026b
        L_0x026a:
            r14 = 0
        L_0x026b:
            r9.dataSim = r14
            com.android.systemui.statusbar.policy.SignalController$State r9 = r6.getState()
            com.android.systemui.statusbar.policy.MobileSignalController$MobileState r9 = (com.android.systemui.statusbar.policy.MobileSignalController.MobileState) r9
            if (r8 == 0) goto L_0x0277
            r14 = r5
            goto L_0x0278
        L_0x0277:
            r14 = 0
        L_0x0278:
            r9.isDefault = r14
            com.android.systemui.statusbar.policy.SignalController$State r9 = r6.getState()
            com.android.systemui.statusbar.policy.MobileSignalController$MobileState r9 = (com.android.systemui.statusbar.policy.MobileSignalController.MobileState) r9
            if (r8 == 0) goto L_0x0284
            r14 = r5
            goto L_0x0285
        L_0x0284:
            r14 = 0
        L_0x0285:
            r9.dataConnected = r14
            if (r8 == 0) goto L_0x033e
            com.android.systemui.statusbar.policy.SignalController$State r9 = r6.getState()
            com.android.systemui.statusbar.policy.MobileSignalController$MobileState r9 = (com.android.systemui.statusbar.policy.MobileSignalController.MobileState) r9
            java.lang.String r14 = "1x"
            boolean r14 = r8.equals(r14)
            if (r14 == 0) goto L_0x029b
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r8 = com.android.systemui.statusbar.policy.TelephonyIcons.ONE_X
            goto L_0x033c
        L_0x029b:
            java.lang.String r14 = "3g"
            boolean r14 = r8.equals(r14)
            if (r14 == 0) goto L_0x02a7
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r8 = com.android.systemui.statusbar.policy.TelephonyIcons.THREE_G
            goto L_0x033c
        L_0x02a7:
            java.lang.String r14 = "4g"
            boolean r14 = r8.equals(r14)
            if (r14 == 0) goto L_0x02b3
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r8 = com.android.systemui.statusbar.policy.TelephonyIcons.FOUR_G
            goto L_0x033c
        L_0x02b3:
            java.lang.String r14 = "4g+"
            boolean r14 = r8.equals(r14)
            if (r14 == 0) goto L_0x02bf
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r8 = com.android.systemui.statusbar.policy.TelephonyIcons.FOUR_G_PLUS
            goto L_0x033c
        L_0x02bf:
            java.lang.String r14 = "5g"
            boolean r14 = r8.equals(r14)
            if (r14 == 0) goto L_0x02cb
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r8 = com.android.systemui.statusbar.policy.TelephonyIcons.NR_5G
            goto L_0x033c
        L_0x02cb:
            java.lang.String r14 = "5ge"
            boolean r14 = r8.equals(r14)
            if (r14 == 0) goto L_0x02d7
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r8 = com.android.systemui.statusbar.policy.TelephonyIcons.LTE_CA_5G_E
            goto L_0x033c
        L_0x02d7:
            java.lang.String r14 = "5g+"
            boolean r14 = r8.equals(r14)
            if (r14 == 0) goto L_0x02e2
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r8 = com.android.systemui.statusbar.policy.TelephonyIcons.NR_5G_PLUS
            goto L_0x033c
        L_0x02e2:
            java.lang.String r14 = "e"
            boolean r14 = r8.equals(r14)
            if (r14 == 0) goto L_0x02ed
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r8 = com.android.systemui.statusbar.policy.TelephonyIcons.f72E
            goto L_0x033c
        L_0x02ed:
            java.lang.String r14 = "g"
            boolean r14 = r8.equals(r14)
            if (r14 == 0) goto L_0x02f8
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r8 = com.android.systemui.statusbar.policy.TelephonyIcons.f73G
            goto L_0x033c
        L_0x02f8:
            java.lang.String r14 = "h"
            boolean r14 = r8.equals(r14)
            if (r14 == 0) goto L_0x0303
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r8 = com.android.systemui.statusbar.policy.TelephonyIcons.f74H
            goto L_0x033c
        L_0x0303:
            java.lang.String r14 = "h+"
            boolean r14 = r8.equals(r14)
            if (r14 == 0) goto L_0x030e
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r8 = com.android.systemui.statusbar.policy.TelephonyIcons.H_PLUS
            goto L_0x033c
        L_0x030e:
            java.lang.String r14 = "lte"
            boolean r14 = r8.equals(r14)
            if (r14 == 0) goto L_0x0319
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r8 = com.android.systemui.statusbar.policy.TelephonyIcons.LTE
            goto L_0x033c
        L_0x0319:
            java.lang.String r14 = "lte+"
            boolean r14 = r8.equals(r14)
            if (r14 == 0) goto L_0x0324
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r8 = com.android.systemui.statusbar.policy.TelephonyIcons.LTE_PLUS
            goto L_0x033c
        L_0x0324:
            java.lang.String r14 = "dis"
            boolean r14 = r8.equals(r14)
            if (r14 == 0) goto L_0x032f
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r8 = com.android.systemui.statusbar.policy.TelephonyIcons.DATA_DISABLED
            goto L_0x033c
        L_0x032f:
            java.lang.String r14 = "not"
            boolean r8 = r8.equals(r14)
            if (r8 == 0) goto L_0x033a
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r8 = com.android.systemui.statusbar.policy.TelephonyIcons.NOT_DEFAULT_DATA
            goto L_0x033c
        L_0x033a:
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r8 = com.android.systemui.statusbar.policy.TelephonyIcons.UNKNOWN
        L_0x033c:
            r9.iconGroup = r8
        L_0x033e:
            java.lang.String r8 = "roam"
            boolean r8 = r2.containsKey(r8)
            if (r8 == 0) goto L_0x0358
            com.android.systemui.statusbar.policy.SignalController$State r8 = r6.getState()
            com.android.systemui.statusbar.policy.MobileSignalController$MobileState r8 = (com.android.systemui.statusbar.policy.MobileSignalController.MobileState) r8
            java.lang.String r9 = "roam"
            java.lang.String r9 = r2.getString(r9)
            boolean r9 = r3.equals(r9)
            r8.roaming = r9
        L_0x0358:
            java.lang.String r8 = r2.getString(r13)
            if (r8 == 0) goto L_0x038c
            com.android.systemui.statusbar.policy.SignalController$State r9 = r6.getState()
            com.android.systemui.statusbar.policy.MobileSignalController$MobileState r9 = (com.android.systemui.statusbar.policy.MobileSignalController.MobileState) r9
            boolean r11 = r8.equals(r11)
            if (r11 == 0) goto L_0x036c
            r14 = -1
            goto L_0x0375
        L_0x036c:
            int r8 = java.lang.Integer.parseInt(r8)
            r11 = 5
            int r14 = java.lang.Math.min(r8, r11)
        L_0x0375:
            r9.level = r14
            com.android.systemui.statusbar.policy.SignalController$State r8 = r6.getState()
            com.android.systemui.statusbar.policy.MobileSignalController$MobileState r8 = (com.android.systemui.statusbar.policy.MobileSignalController.MobileState) r8
            com.android.systemui.statusbar.policy.SignalController$State r9 = r6.getState()
            com.android.systemui.statusbar.policy.MobileSignalController$MobileState r9 = (com.android.systemui.statusbar.policy.MobileSignalController.MobileState) r9
            int r9 = r9.level
            if (r9 < 0) goto L_0x0389
            r9 = r5
            goto L_0x038a
        L_0x0389:
            r9 = 0
        L_0x038a:
            r8.connected = r9
        L_0x038c:
            java.lang.String r8 = "inflate"
            boolean r8 = r2.containsKey(r8)
            if (r8 == 0) goto L_0x03b6
            r8 = 0
        L_0x0395:
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController> r9 = r0.mMobileSignalControllers
            int r9 = r9.size()
            if (r8 >= r9) goto L_0x03b6
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController> r9 = r0.mMobileSignalControllers
            java.lang.Object r9 = r9.valueAt(r8)
            com.android.systemui.statusbar.policy.MobileSignalController r9 = (com.android.systemui.statusbar.policy.MobileSignalController) r9
            java.lang.String r11 = "inflate"
            java.lang.String r11 = r2.getString(r11)
            java.lang.String r13 = "true"
            boolean r11 = r13.equals(r11)
            r9.mInflateSignalStrengths = r11
            int r8 = r8 + 1
            goto L_0x0395
        L_0x03b6:
            java.lang.String r8 = r2.getString(r12)
            if (r8 == 0) goto L_0x040c
            com.android.systemui.statusbar.policy.SignalController$State r9 = r6.getState()
            com.android.systemui.statusbar.policy.MobileSignalController$MobileState r9 = (com.android.systemui.statusbar.policy.MobileSignalController.MobileState) r9
            r9.dataConnected = r5
            int r9 = r8.hashCode()
            if (r9 == r10) goto L_0x03e7
            r10 = 110414(0x1af4e, float:1.54723E-40)
            if (r9 == r10) goto L_0x03df
            r7 = 100357129(0x5fb5409, float:2.3634796E-35)
            if (r9 == r7) goto L_0x03d5
            goto L_0x03ef
        L_0x03d5:
            java.lang.String r4 = "inout"
            boolean r4 = r8.equals(r4)
            if (r4 == 0) goto L_0x03ef
            r4 = 0
            goto L_0x03f0
        L_0x03df:
            boolean r4 = r8.equals(r4)
            if (r4 == 0) goto L_0x03ef
            r4 = 2
            goto L_0x03f0
        L_0x03e7:
            boolean r4 = r8.equals(r7)
            if (r4 == 0) goto L_0x03ef
            r4 = r5
            goto L_0x03f0
        L_0x03ef:
            r4 = -1
        L_0x03f0:
            if (r4 == 0) goto L_0x0406
            if (r4 == r5) goto L_0x0401
            r7 = 2
            if (r4 == r7) goto L_0x03fc
            r15 = 0
            r6.setActivity(r15)
            goto L_0x0410
        L_0x03fc:
            r15 = 0
            r6.setActivity(r7)
            goto L_0x0410
        L_0x0401:
            r15 = 0
            r6.setActivity(r5)
            goto L_0x0410
        L_0x0406:
            r15 = 0
            r4 = 3
            r6.setActivity(r4)
            goto L_0x0410
        L_0x040c:
            r15 = 0
            r6.setActivity(r15)
        L_0x0410:
            com.android.systemui.statusbar.policy.SignalController$State r4 = r6.getState()
            com.android.systemui.statusbar.policy.MobileSignalController$MobileState r4 = (com.android.systemui.statusbar.policy.MobileSignalController.MobileState) r4
            r4.enabled = r1
            r6.notifyListeners()
            goto L_0x041d
        L_0x041c:
            r15 = 0
        L_0x041d:
            java.lang.String r1 = "carriernetworkchange"
            java.lang.String r1 = r2.getString(r1)
            if (r1 == 0) goto L_0x043f
            boolean r1 = r1.equals(r3)
        L_0x0429:
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController> r2 = r0.mMobileSignalControllers
            int r2 = r2.size()
            if (r15 >= r2) goto L_0x043f
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController> r2 = r0.mMobileSignalControllers
            java.lang.Object r2 = r2.valueAt(r15)
            com.android.systemui.statusbar.policy.MobileSignalController r2 = (com.android.systemui.statusbar.policy.MobileSignalController) r2
            r2.setCarrierNetworkChangeMode(r1)
            int r15 = r15 + 1
            goto L_0x0429
        L_0x043f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.policy.NetworkControllerImpl.dispatchDemoCommand(java.lang.String, android.os.Bundle):void");
    }

    private SubscriptionInfo addSignalController(int i, int i2) {
        SubscriptionInfo subscriptionInfo = new SubscriptionInfo(i, "", i2, "", "", 0, 0, "", 0, (Bitmap) null, (String) null, (String) null, "", false, (UiccAccessRule[]) null, (String) null);
        MobileSignalController mobileSignalController = new MobileSignalController(this.mContext, this.mConfig, this.mHasMobileDataFeature, this.mPhone.createForSubscriptionId(subscriptionInfo.getSubscriptionId()), this.mCallbackHandler, this, subscriptionInfo, this.mSubDefaults, this.mReceiverHandler.getLooper());
        this.mMobileSignalControllers.put(i, mobileSignalController);
        ((MobileSignalController.MobileState) mobileSignalController.getState()).userSetup = true;
        return subscriptionInfo;
    }

    public boolean hasEmergencyCryptKeeperText() {
        return EncryptionHelper.IS_DATA_ENCRYPTED;
    }

    public boolean isRadioOn() {
        return !this.mAirplaneMode;
    }

    private class SubListener extends SubscriptionManager.OnSubscriptionsChangedListener {
        private SubListener() {
        }

        public void onSubscriptionsChanged() {
            NetworkControllerImpl.this.updateMobileControllers();
        }
    }

    public static class SubscriptionDefaults {
        public int getDefaultVoiceSubId() {
            return SubscriptionManager.getDefaultVoiceSubscriptionId();
        }

        public int getDefaultDataSubId() {
            return SubscriptionManager.getDefaultDataSubscriptionId();
        }

        public int getActiveDataSubId() {
            return SubscriptionManager.getActiveDataSubscriptionId();
        }
    }

    @VisibleForTesting
    static class Config {
        private static final Map<String, Integer> NR_STATUS_STRING_TO_INDEX = new HashMap(5);
        boolean alwaysShowCdmaRssi = false;
        boolean alwaysShowDataRatIcon = false;
        boolean hideLtePlus = false;
        boolean hspaDataDistinguishable;
        boolean inflateSignalStrengths = false;
        Map<Integer, MobileSignalController.MobileIconGroup> nr5GIconMap = new HashMap();
        public long nrIconDisplayGracePeriodMs;
        public String patternOfCarrierSpecificDataIcon = "";
        boolean show4gFor3g = false;
        boolean show4gForLte = false;
        boolean showAtLeast3G = false;

        Config() {
        }

        static {
            NR_STATUS_STRING_TO_INDEX.put("connected_mmwave", 1);
            NR_STATUS_STRING_TO_INDEX.put("connected", 2);
            NR_STATUS_STRING_TO_INDEX.put("not_restricted_rrc_idle", 3);
            NR_STATUS_STRING_TO_INDEX.put("not_restricted_rrc_con", 4);
            NR_STATUS_STRING_TO_INDEX.put("restricted", 5);
        }

        static Config readConfig(Context context) {
            Config config = new Config();
            Resources resources = context.getResources();
            config.showAtLeast3G = resources.getBoolean(C1773R$bool.config_showMin3G);
            config.alwaysShowCdmaRssi = resources.getBoolean(17891360);
            config.hspaDataDistinguishable = resources.getBoolean(C1773R$bool.config_hspa_data_distinguishable);
            config.inflateSignalStrengths = resources.getBoolean(17891492);
            SubscriptionManager.from(context);
            PersistableBundle configForSubId = ((CarrierConfigManager) context.getSystemService("carrier_config")).getConfigForSubId(SubscriptionManager.getDefaultDataSubscriptionId());
            if (configForSubId != null) {
                config.alwaysShowDataRatIcon = configForSubId.getBoolean("always_show_data_rat_icon_bool");
                config.show4gForLte = configForSubId.getBoolean("show_4g_for_lte_data_icon_bool");
                config.show4gFor3g = configForSubId.getBoolean("show_4g_for_3g_data_icon_bool");
                config.hideLtePlus = configForSubId.getBoolean("hide_lte_plus_data_icon_bool");
                config.patternOfCarrierSpecificDataIcon = configForSubId.getString("show_carrier_data_icon_pattern_string");
                String string = configForSubId.getString("5g_icon_configuration_string");
                if (!TextUtils.isEmpty(string)) {
                    for (String add5GIconMapping : string.trim().split(",")) {
                        add5GIconMapping(add5GIconMapping, config);
                    }
                }
                setDisplayGraceTime(configForSubId.getInt("5g_icon_display_grace_period_sec_int"), config);
            }
            return config;
        }

        @VisibleForTesting
        static void add5GIconMapping(String str, Config config) {
            String[] split = str.trim().toLowerCase().split(":");
            if (split.length == 2) {
                String str2 = split[0];
                String str3 = split[1];
                if (!str3.equals("none") && NR_STATUS_STRING_TO_INDEX.containsKey(str2) && TelephonyIcons.ICON_NAME_TO_ICON.containsKey(str3)) {
                    config.nr5GIconMap.put(NR_STATUS_STRING_TO_INDEX.get(str2), TelephonyIcons.ICON_NAME_TO_ICON.get(str3));
                }
            } else if (NetworkControllerImpl.DEBUG) {
                Log.e("NetworkController", "Invalid 5G icon configuration, config = " + str);
            }
        }

        @VisibleForTesting
        static void setDisplayGraceTime(int i, Config config) {
            config.nrIconDisplayGracePeriodMs = ((long) i) * 1000;
        }
    }
}
