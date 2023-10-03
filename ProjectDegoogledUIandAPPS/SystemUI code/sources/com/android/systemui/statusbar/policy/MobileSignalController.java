package com.android.systemui.statusbar.policy;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.telephony.ims.ImsMmTelManager;
import android.telephony.ims.ImsReasonInfo;
import android.telephony.ims.feature.MmTelFeature;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import com.android.ims.ImsException;
import com.android.ims.ImsManager;
import com.android.internal.annotations.VisibleForTesting;
import com.android.settingslib.Utils;
import com.android.settingslib.graph.SignalDrawable;
import com.android.settingslib.net.SignalStrengthUtil;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1784R$string;
import com.android.systemui.Dependency;
import com.android.systemui.statusbar.policy.NetworkControllerImpl;
import com.android.systemui.statusbar.policy.SignalController;
import com.android.systemui.tuner.TunerService;
import java.io.PrintWriter;
import java.util.BitSet;
import java.util.Objects;
import java.util.regex.Pattern;

public class MobileSignalController extends SignalController<MobileState, MobileIconGroup> implements TunerService.Tunable {
    /* access modifiers changed from: private */
    public int mCallState = 0;
    private ImsMmTelManager.CapabilityCallback mCapabilityCallback = new ImsMmTelManager.CapabilityCallback() {
        public void onCapabilitiesStatusChanged(MmTelFeature.MmTelCapabilities mmTelCapabilities) {
            ((MobileState) MobileSignalController.this.mCurrentState).voiceCapable = mmTelCapabilities.isCapable(1);
            ((MobileState) MobileSignalController.this.mCurrentState).videoCapable = mmTelCapabilities.isCapable(2);
            String str = MobileSignalController.this.mTag;
            Log.d(str, "onCapabilitiesStatusChanged isVoiceCapable=" + ((MobileState) MobileSignalController.this.mCurrentState).voiceCapable + " isVideoCapable=" + ((MobileState) MobileSignalController.this.mCurrentState).videoCapable);
            MobileSignalController.this.notifyListenersIfNecessary();
        }
    };
    private NetworkControllerImpl.Config mConfig;
    private boolean mDataDisabledIcon;
    /* access modifiers changed from: private */
    public int mDataNetType = 0;
    /* access modifiers changed from: private */
    public int mDataState = 0;
    private MobileIconGroup mDefaultIcons;
    private final NetworkControllerImpl.SubscriptionDefaults mDefaults;
    private final Handler mDisplayGraceHandler;
    /* access modifiers changed from: private */
    public ImsManager mImsManager;
    private ImsManager.Connector mImsManagerConnector;
    private final ImsMmTelManager.RegistrationCallback mImsRegistrationCallback = new ImsMmTelManager.RegistrationCallback() {
        public void onRegistered(int i) {
            String str = MobileSignalController.this.mTag;
            Log.d(str, "onRegistered imsTransportType=" + i);
            MobileSignalController mobileSignalController = MobileSignalController.this;
            ((MobileState) mobileSignalController.mCurrentState).imsRegistered = true;
            mobileSignalController.notifyListenersIfNecessary();
        }

        public void onRegistering(int i) {
            String str = MobileSignalController.this.mTag;
            Log.d(str, "onRegistering imsTransportType=" + i);
            MobileSignalController mobileSignalController = MobileSignalController.this;
            ((MobileState) mobileSignalController.mCurrentState).imsRegistered = false;
            mobileSignalController.notifyListenersIfNecessary();
        }

        public void onUnregistered(ImsReasonInfo imsReasonInfo) {
            String str = MobileSignalController.this.mTag;
            Log.d(str, "onDeregistered imsReasonInfo=" + imsReasonInfo);
            MobileSignalController mobileSignalController = MobileSignalController.this;
            ((MobileState) mobileSignalController.mCurrentState).imsRegistered = false;
            mobileSignalController.notifyListenersIfNecessary();
        }
    };
    @VisibleForTesting
    boolean mInflateSignalStrengths = false;
    @VisibleForTesting
    boolean mIsShowingIconGracefully = false;
    private final String mNetworkNameDefault;
    private final String mNetworkNameSeparator;
    final SparseArray<MobileIconGroup> mNetworkToIconLookup = new SparseArray<>();
    private final ContentObserver mObserver;
    private final TelephonyManager mPhone;
    @VisibleForTesting
    final PhoneStateListener mPhoneStateListener;
    private boolean mRoamingIconAllowed;
    /* access modifiers changed from: private */
    public ServiceState mServiceState;
    private boolean mShow4gForLte;
    /* access modifiers changed from: private */
    public SignalStrength mSignalStrength;
    final SubscriptionInfo mSubscriptionInfo;
    private boolean mVolteIcon = true;
    private final BroadcastReceiver mVolteSwitchObserver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String str = MobileSignalController.this.mTag;
            Log.d(str, "action=" + intent.getAction());
            MobileSignalController.this.notifyListeners();
        }
    };
    private boolean mVowifiIcon = true;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MobileSignalController(Context context, NetworkControllerImpl.Config config, boolean z, TelephonyManager telephonyManager, CallbackHandler callbackHandler, NetworkControllerImpl networkControllerImpl, SubscriptionInfo subscriptionInfo, NetworkControllerImpl.SubscriptionDefaults subscriptionDefaults, Looper looper) {
        super("MobileSignalController(" + subscriptionInfo.getSubscriptionId() + ")", context, 0, callbackHandler, networkControllerImpl);
        String str;
        boolean z2 = z;
        Looper looper2 = looper;
        this.mConfig = config;
        this.mPhone = telephonyManager;
        this.mDefaults = subscriptionDefaults;
        this.mSubscriptionInfo = subscriptionInfo;
        this.mPhoneStateListener = new MobilePhoneStateListener(looper2);
        this.mNetworkNameSeparator = getStringIfExists(C1784R$string.status_bar_network_name_separator).toString();
        this.mNetworkNameDefault = getStringIfExists(17040310).toString();
        ((TunerService) Dependency.get(TunerService.class)).addTunable(this, "volte");
        ((TunerService) Dependency.get(TunerService.class)).addTunable(this, "vowifi");
        mapIconSets();
        if (subscriptionInfo.getCarrierName() != null) {
            str = subscriptionInfo.getCarrierName().toString();
        } else {
            str = this.mNetworkNameDefault;
        }
        T t = this.mLastState;
        T t2 = this.mCurrentState;
        ((MobileState) t2).networkName = str;
        ((MobileState) t).networkName = str;
        ((MobileState) t2).networkNameData = str;
        ((MobileState) t).networkNameData = str;
        ((MobileState) t2).enabled = z2;
        ((MobileState) t).enabled = z2;
        MobileIconGroup mobileIconGroup = this.mDefaultIcons;
        ((MobileState) t2).iconGroup = mobileIconGroup;
        ((MobileState) t).iconGroup = mobileIconGroup;
        updateDataSim();
        this.mImsManagerConnector = new ImsManager.Connector(this.mContext, this.mSubscriptionInfo.getSimSlotIndex(), new ImsManager.Connector.Listener() {
            public void connectionReady(ImsManager imsManager) throws ImsException {
                Log.d(MobileSignalController.this.mTag, "ImsManager: connection ready.");
                ImsManager unused = MobileSignalController.this.mImsManager = imsManager;
                MobileSignalController.this.setListeners();
            }

            public void connectionUnavailable() {
                Log.d(MobileSignalController.this.mTag, "ImsManager: connection unavailable.");
                MobileSignalController.this.removeListeners();
            }
        });
        this.mObserver = new ContentObserver(new Handler(looper2)) {
            public void onChange(boolean z) {
                MobileSignalController.this.updateTelephony();
            }
        };
        this.mDisplayGraceHandler = new Handler(looper2) {
            public void handleMessage(Message message) {
                if (message.what == 1) {
                    MobileSignalController mobileSignalController = MobileSignalController.this;
                    mobileSignalController.mIsShowingIconGracefully = false;
                    mobileSignalController.updateTelephony();
                }
            }
        };
        new SettingsObserver(new Handler()).observe();
    }

    class SettingsObserver extends ContentObserver {
        SettingsObserver(Handler handler) {
            super(handler);
        }

        /* access modifiers changed from: package-private */
        public void observe() {
            ContentResolver contentResolver = MobileSignalController.this.mContext.getContentResolver();
            contentResolver.registerContentObserver(Settings.System.getUriFor("show_fourg_icon"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("data_disabled_icon"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("roaming_indicator_icon"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("use_old_mobiletype"), false, this, -1);
            MobileSignalController.this.updateSettings();
        }

        public void onChange(boolean z) {
            MobileSignalController.this.updateSettings();
        }
    }

    /* access modifiers changed from: private */
    public void updateSettings() {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        boolean z = false;
        this.mShow4gForLte = Settings.System.getIntForUser(contentResolver, "show_fourg_icon", 0, -2) == 1;
        this.mDataDisabledIcon = Settings.System.getIntForUser(contentResolver, "data_disabled_icon", 1, -2) == 1;
        if (Settings.System.getIntForUser(contentResolver, "roaming_indicator_icon", 1, -2) == 1) {
            z = true;
        }
        this.mRoamingIconAllowed = z;
        mapIconSets();
        updateTelephony();
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0027  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x002a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onTuningChanged(java.lang.String r4, java.lang.String r5) {
        /*
            r3 = this;
            int r0 = r4.hashCode()
            r1 = -810567346(0xffffffffcfafb94e, float:-5.8963139E9)
            r2 = 1
            if (r0 == r1) goto L_0x001a
            r1 = 112389764(0x6b2ee84, float:6.7306625E-35)
            if (r0 == r1) goto L_0x0010
            goto L_0x0024
        L_0x0010:
            java.lang.String r0 = "volte"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0024
            r4 = 0
            goto L_0x0025
        L_0x001a:
            java.lang.String r0 = "vowifi"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0024
            r4 = r2
            goto L_0x0025
        L_0x0024:
            r4 = -1
        L_0x0025:
            if (r4 == 0) goto L_0x002a
            if (r4 == r2) goto L_0x0033
            goto L_0x003c
        L_0x002a:
            boolean r4 = com.android.systemui.tuner.TunerService.parseIntegerSwitch(r5, r2)
            r3.mVolteIcon = r4
            r3.notifyListenersIfNecessary()
        L_0x0033:
            boolean r4 = com.android.systemui.tuner.TunerService.parseIntegerSwitch(r5, r2)
            r3.mVowifiIcon = r4
            r3.notifyListenersIfNecessary()
        L_0x003c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.policy.MobileSignalController.onTuningChanged(java.lang.String, java.lang.String):void");
    }

    public void setConfiguration(NetworkControllerImpl.Config config) {
        this.mConfig = config;
        updateInflateSignalStrength();
        mapIconSets();
        updateTelephony();
    }

    public void setAirplaneMode(boolean z) {
        ((MobileState) this.mCurrentState).airplaneMode = z;
        notifyListenersIfNecessary();
    }

    public void setUserSetupComplete(boolean z) {
        ((MobileState) this.mCurrentState).userSetup = z;
        notifyListenersIfNecessary();
    }

    public void updateConnectivity(BitSet bitSet, BitSet bitSet2) {
        boolean z = bitSet2.get(this.mTransportType);
        ((MobileState) this.mCurrentState).isDefault = bitSet.get(this.mTransportType);
        T t = this.mCurrentState;
        ((MobileState) t).inetCondition = (z || !((MobileState) t).isDefault) ? 1 : 0;
        notifyListenersIfNecessary();
    }

    public void setCarrierNetworkChangeMode(boolean z) {
        ((MobileState) this.mCurrentState).carrierNetworkChangeMode = z;
        updateTelephony();
    }

    public void registerListener() {
        this.mPhone.listen(this.mPhoneStateListener, 4260321);
        this.mContext.getContentResolver().registerContentObserver(Settings.Global.getUriFor("mobile_data"), true, this.mObserver);
        ContentResolver contentResolver = this.mContext.getContentResolver();
        contentResolver.registerContentObserver(Settings.Global.getUriFor("mobile_data" + this.mSubscriptionInfo.getSubscriptionId()), true, this.mObserver);
        this.mContext.registerReceiver(this.mVolteSwitchObserver, new IntentFilter("org.codeaurora.intent.action.ACTION_ENHANCE_4G_SWITCH"));
        this.mImsManagerConnector.connect();
    }

    public void unregisterListener() {
        this.mPhone.listen(this.mPhoneStateListener, 0);
        this.mContext.getContentResolver().unregisterContentObserver(this.mObserver);
        this.mContext.unregisterReceiver(this.mVolteSwitchObserver);
        this.mImsManagerConnector.disconnect();
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x00bc  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x00d9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void mapIconSets() {
        /*
            r6 = this;
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup> r0 = r6.mNetworkToIconLookup
            r0.clear()
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup> r0 = r6.mNetworkToIconLookup
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r1 = com.android.systemui.statusbar.policy.TelephonyIcons.THREE_G
            r2 = 5
            r0.put(r2, r1)
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup> r0 = r6.mNetworkToIconLookup
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r1 = com.android.systemui.statusbar.policy.TelephonyIcons.THREE_G
            r2 = 6
            r0.put(r2, r1)
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup> r0 = r6.mNetworkToIconLookup
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r1 = com.android.systemui.statusbar.policy.TelephonyIcons.THREE_G
            r2 = 12
            r0.put(r2, r1)
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup> r0 = r6.mNetworkToIconLookup
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r1 = com.android.systemui.statusbar.policy.TelephonyIcons.THREE_G
            r2 = 14
            r0.put(r2, r1)
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup> r0 = r6.mNetworkToIconLookup
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r1 = com.android.systemui.statusbar.policy.TelephonyIcons.THREE_G
            r2 = 3
            r0.put(r2, r1)
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup> r0 = r6.mNetworkToIconLookup
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r1 = com.android.systemui.statusbar.policy.TelephonyIcons.THREE_G
            r2 = 17
            r0.put(r2, r1)
            com.android.systemui.statusbar.policy.NetworkControllerImpl$Config r0 = r6.mConfig
            boolean r0 = r0.showAtLeast3G
            r1 = 7
            r2 = 4
            r3 = 2
            r4 = 0
            if (r0 != 0) goto L_0x0063
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup> r0 = r6.mNetworkToIconLookup
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r5 = com.android.systemui.statusbar.policy.TelephonyIcons.UNKNOWN
            r0.put(r4, r5)
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup> r0 = r6.mNetworkToIconLookup
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r4 = com.android.systemui.statusbar.policy.TelephonyIcons.f72E
            r0.put(r3, r4)
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup> r0 = r6.mNetworkToIconLookup
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r3 = com.android.systemui.statusbar.policy.TelephonyIcons.ONE_X
            r0.put(r2, r3)
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup> r0 = r6.mNetworkToIconLookup
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r2 = com.android.systemui.statusbar.policy.TelephonyIcons.ONE_X
            r0.put(r1, r2)
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r0 = com.android.systemui.statusbar.policy.TelephonyIcons.f73G
            r6.mDefaultIcons = r0
            goto L_0x0083
        L_0x0063:
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup> r0 = r6.mNetworkToIconLookup
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r5 = com.android.systemui.statusbar.policy.TelephonyIcons.THREE_G
            r0.put(r4, r5)
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup> r0 = r6.mNetworkToIconLookup
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r4 = com.android.systemui.statusbar.policy.TelephonyIcons.THREE_G
            r0.put(r3, r4)
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup> r0 = r6.mNetworkToIconLookup
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r3 = com.android.systemui.statusbar.policy.TelephonyIcons.THREE_G
            r0.put(r2, r3)
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup> r0 = r6.mNetworkToIconLookup
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r2 = com.android.systemui.statusbar.policy.TelephonyIcons.THREE_G
            r0.put(r1, r2)
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r0 = com.android.systemui.statusbar.policy.TelephonyIcons.THREE_G
            r6.mDefaultIcons = r0
        L_0x0083:
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r0 = com.android.systemui.statusbar.policy.TelephonyIcons.THREE_G
            com.android.systemui.statusbar.policy.NetworkControllerImpl$Config r1 = r6.mConfig
            boolean r2 = r1.show4gFor3g
            if (r2 == 0) goto L_0x008e
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r0 = com.android.systemui.statusbar.policy.TelephonyIcons.FOUR_G
            goto L_0x0097
        L_0x008e:
            boolean r1 = r1.hspaDataDistinguishable
            if (r1 == 0) goto L_0x0097
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r0 = com.android.systemui.statusbar.policy.TelephonyIcons.f74H
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r1 = com.android.systemui.statusbar.policy.TelephonyIcons.H_PLUS
            goto L_0x0098
        L_0x0097:
            r1 = r0
        L_0x0098:
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup> r2 = r6.mNetworkToIconLookup
            r3 = 8
            r2.put(r3, r0)
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup> r2 = r6.mNetworkToIconLookup
            r3 = 9
            r2.put(r3, r0)
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup> r2 = r6.mNetworkToIconLookup
            r3 = 10
            r2.put(r3, r0)
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup> r0 = r6.mNetworkToIconLookup
            r2 = 15
            r0.put(r2, r1)
            boolean r0 = r6.mShow4gForLte
            r1 = 13
            r2 = 19
            if (r0 == 0) goto L_0x00d9
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup> r0 = r6.mNetworkToIconLookup
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r3 = com.android.systemui.statusbar.policy.TelephonyIcons.FOUR_G
            r0.put(r1, r3)
            com.android.systemui.statusbar.policy.NetworkControllerImpl$Config r0 = r6.mConfig
            boolean r0 = r0.hideLtePlus
            if (r0 == 0) goto L_0x00d1
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup> r0 = r6.mNetworkToIconLookup
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r1 = com.android.systemui.statusbar.policy.TelephonyIcons.FOUR_G
            r0.put(r2, r1)
            goto L_0x00f5
        L_0x00d1:
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup> r0 = r6.mNetworkToIconLookup
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r1 = com.android.systemui.statusbar.policy.TelephonyIcons.FOUR_G_PLUS
            r0.put(r2, r1)
            goto L_0x00f5
        L_0x00d9:
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup> r0 = r6.mNetworkToIconLookup
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r3 = com.android.systemui.statusbar.policy.TelephonyIcons.LTE
            r0.put(r1, r3)
            com.android.systemui.statusbar.policy.NetworkControllerImpl$Config r0 = r6.mConfig
            boolean r0 = r0.hideLtePlus
            if (r0 == 0) goto L_0x00ee
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup> r0 = r6.mNetworkToIconLookup
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r1 = com.android.systemui.statusbar.policy.TelephonyIcons.LTE
            r0.put(r2, r1)
            goto L_0x00f5
        L_0x00ee:
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup> r0 = r6.mNetworkToIconLookup
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r1 = com.android.systemui.statusbar.policy.TelephonyIcons.LTE_PLUS
            r0.put(r2, r1)
        L_0x00f5:
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup> r0 = r6.mNetworkToIconLookup
            r1 = 21
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r2 = com.android.systemui.statusbar.policy.TelephonyIcons.LTE_CA_5G_E
            r0.put(r1, r2)
            android.util.SparseArray<com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup> r6 = r6.mNetworkToIconLookup
            r0 = 18
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r1 = com.android.systemui.statusbar.policy.TelephonyIcons.WFC
            r6.put(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.policy.MobileSignalController.mapIconSets():void");
    }

    private void updateInflateSignalStrength() {
        this.mInflateSignalStrengths = SignalStrengthUtil.shouldInflateSignalStrength(this.mContext, this.mSubscriptionInfo.getSubscriptionId());
    }

    private int getNumLevels() {
        return this.mInflateSignalStrengths ? 6 : 5;
    }

    public int getCurrentIconId() {
        T t = this.mCurrentState;
        if (((MobileState) t).iconGroup == TelephonyIcons.CARRIER_NETWORK_CHANGE) {
            return SignalDrawable.getCarrierChangeState(getNumLevels());
        }
        boolean z = false;
        if (((MobileState) t).connected) {
            int i = ((MobileState) t).level;
            if (this.mInflateSignalStrengths) {
                i++;
            }
            T t2 = this.mCurrentState;
            boolean z2 = ((MobileState) t2).userSetup && (((MobileState) t2).iconGroup == TelephonyIcons.DATA_DISABLED || (((MobileState) t2).iconGroup == TelephonyIcons.NOT_DEFAULT_DATA && ((MobileState) t2).defaultDataOff));
            boolean z3 = ((MobileState) this.mCurrentState).inetCondition == 0;
            if (z2 || z3) {
                z = true;
            }
            return SignalDrawable.getState(i, getNumLevels(), z);
        } else if (((MobileState) t).enabled) {
            return SignalDrawable.getEmptyState(getNumLevels());
        } else {
            return 0;
        }
    }

    public int getQsCurrentIconId() {
        return getCurrentIconId();
    }

    private int getVolteResId() {
        if (this.mVowifiIcon && isVowifiAvailable()) {
            return C1776R$drawable.ic_vowifi;
        }
        if (!this.mVolteIcon || !isVolteAvailable()) {
            return 0;
        }
        return C1776R$drawable.ic_volte;
    }

    /* access modifiers changed from: private */
    public void setListeners() {
        ImsManager imsManager = this.mImsManager;
        if (imsManager == null) {
            Log.e(this.mTag, "setListeners mImsManager is null");
            return;
        }
        try {
            imsManager.addCapabilitiesCallback(this.mCapabilityCallback);
            this.mImsManager.addRegistrationCallback(this.mImsRegistrationCallback);
            String str = this.mTag;
            Log.d(str, "addCapabilitiesCallback " + this.mCapabilityCallback + " into " + this.mImsManager);
            String str2 = this.mTag;
            Log.d(str2, "addRegistrationCallback " + this.mImsRegistrationCallback + " into " + this.mImsManager);
        } catch (ImsException unused) {
            Log.d(this.mTag, "unable to addCapabilitiesCallback callback.");
        }
        queryImsState();
    }

    private void queryImsState() {
        TelephonyManager createForSubscriptionId = this.mPhone.createForSubscriptionId(this.mSubscriptionInfo.getSubscriptionId());
        ((MobileState) this.mCurrentState).voiceCapable = createForSubscriptionId.isVolteAvailable();
        ((MobileState) this.mCurrentState).videoCapable = createForSubscriptionId.isVideoTelephonyAvailable();
        ((MobileState) this.mCurrentState).imsRegistered = this.mPhone.isImsRegistered(this.mSubscriptionInfo.getSubscriptionId());
        if (SignalController.DEBUG) {
            String str = this.mTag;
            Log.d(str, "queryImsState tm=" + createForSubscriptionId + " phone=" + this.mPhone + " voiceCapable=" + ((MobileState) this.mCurrentState).voiceCapable + " videoCapable=" + ((MobileState) this.mCurrentState).videoCapable + " imsResitered=" + ((MobileState) this.mCurrentState).imsRegistered);
        }
        notifyListenersIfNecessary();
    }

    /* access modifiers changed from: private */
    public void removeListeners() {
        ImsManager imsManager = this.mImsManager;
        if (imsManager == null) {
            Log.e(this.mTag, "removeListeners mImsManager is null");
            return;
        }
        try {
            imsManager.removeCapabilitiesCallback(this.mCapabilityCallback);
            this.mImsManager.removeRegistrationListener(this.mImsRegistrationCallback);
            String str = this.mTag;
            Log.d(str, "removeCapabilitiesCallback " + this.mCapabilityCallback + " from " + this.mImsManager);
            String str2 = this.mTag;
            Log.d(str2, "removeRegistrationCallback " + this.mImsRegistrationCallback + " from " + this.mImsManager);
        } catch (ImsException unused) {
            Log.d(this.mTag, "unable to remove callback.");
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v4, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v2, resolved type: com.android.systemui.statusbar.policy.NetworkController$IconState} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v7, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v8, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v9, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r10v12, types: [com.android.systemui.statusbar.policy.NetworkController$IconState] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void notifyListeners(com.android.systemui.statusbar.policy.NetworkController.SignalCallback r19) {
        /*
            r18 = this;
            r0 = r18
            com.android.systemui.statusbar.policy.SignalController$IconGroup r1 = r18.getIcons()
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r1 = (com.android.systemui.statusbar.policy.MobileSignalController.MobileIconGroup) r1
            int r2 = r18.getContentDescription()
            java.lang.CharSequence r2 = r0.getStringIfExists(r2)
            java.lang.String r2 = r2.toString()
            int r3 = r1.mDataContentDescription
            java.lang.CharSequence r13 = r0.getStringIfExists(r3)
            java.lang.String r3 = r13.toString()
            r4 = 0
            android.text.Spanned r3 = android.text.Html.fromHtml(r3, r4)
            java.lang.String r3 = r3.toString()
            T r5 = r0.mCurrentState
            com.android.systemui.statusbar.policy.MobileSignalController$MobileState r5 = (com.android.systemui.statusbar.policy.MobileSignalController.MobileState) r5
            int r5 = r5.inetCondition
            if (r5 != 0) goto L_0x0037
            android.content.Context r3 = r0.mContext
            int r5 = com.android.systemui.C1784R$string.data_connection_no_internet
            java.lang.String r3 = r3.getString(r5)
        L_0x0037:
            r12 = r3
            T r3 = r0.mCurrentState
            r5 = r3
            com.android.systemui.statusbar.policy.MobileSignalController$MobileState r5 = (com.android.systemui.statusbar.policy.MobileSignalController.MobileState) r5
            com.android.systemui.statusbar.policy.SignalController$IconGroup r5 = r5.iconGroup
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r6 = com.android.systemui.statusbar.policy.TelephonyIcons.DATA_DISABLED
            r7 = 1
            if (r5 == r6) goto L_0x004c
            com.android.systemui.statusbar.policy.MobileSignalController$MobileState r3 = (com.android.systemui.statusbar.policy.MobileSignalController.MobileState) r3
            com.android.systemui.statusbar.policy.SignalController$IconGroup r3 = r3.iconGroup
            com.android.systemui.statusbar.policy.MobileSignalController$MobileIconGroup r5 = com.android.systemui.statusbar.policy.TelephonyIcons.NOT_DEFAULT_DATA
            if (r3 != r5) goto L_0x0056
        L_0x004c:
            T r3 = r0.mCurrentState
            com.android.systemui.statusbar.policy.MobileSignalController$MobileState r3 = (com.android.systemui.statusbar.policy.MobileSignalController.MobileState) r3
            boolean r3 = r3.userSetup
            if (r3 == 0) goto L_0x0056
            r3 = r7
            goto L_0x0057
        L_0x0056:
            r3 = r4
        L_0x0057:
            T r5 = r0.mCurrentState
            com.android.systemui.statusbar.policy.MobileSignalController$MobileState r5 = (com.android.systemui.statusbar.policy.MobileSignalController.MobileState) r5
            boolean r5 = r5.dataConnected
            if (r5 != 0) goto L_0x0064
            if (r3 == 0) goto L_0x0062
            goto L_0x0064
        L_0x0062:
            r5 = r4
            goto L_0x0065
        L_0x0064:
            r5 = r7
        L_0x0065:
            com.android.systemui.statusbar.policy.NetworkController$IconState r6 = new com.android.systemui.statusbar.policy.NetworkController$IconState
            T r8 = r0.mCurrentState
            r9 = r8
            com.android.systemui.statusbar.policy.MobileSignalController$MobileState r9 = (com.android.systemui.statusbar.policy.MobileSignalController.MobileState) r9
            boolean r9 = r9.enabled
            if (r9 == 0) goto L_0x0078
            com.android.systemui.statusbar.policy.MobileSignalController$MobileState r8 = (com.android.systemui.statusbar.policy.MobileSignalController.MobileState) r8
            boolean r8 = r8.airplaneMode
            if (r8 != 0) goto L_0x0078
            r8 = r7
            goto L_0x0079
        L_0x0078:
            r8 = r4
        L_0x0079:
            int r9 = r18.getCurrentIconId()
            r6.<init>(r8, r9, r2)
            T r8 = r0.mCurrentState
            com.android.systemui.statusbar.policy.MobileSignalController$MobileState r8 = (com.android.systemui.statusbar.policy.MobileSignalController.MobileState) r8
            boolean r8 = r8.dataSim
            r9 = 0
            if (r8 == 0) goto L_0x00c2
            if (r5 != 0) goto L_0x0094
            com.android.systemui.statusbar.policy.NetworkControllerImpl$Config r8 = r0.mConfig
            boolean r8 = r8.alwaysShowDataRatIcon
            if (r8 == 0) goto L_0x0092
            goto L_0x0094
        L_0x0092:
            r8 = r4
            goto L_0x0096
        L_0x0094:
            int r8 = r1.mQsDataType
        L_0x0096:
            com.android.systemui.statusbar.policy.NetworkController$IconState r10 = new com.android.systemui.statusbar.policy.NetworkController$IconState
            T r11 = r0.mCurrentState
            r14 = r11
            com.android.systemui.statusbar.policy.MobileSignalController$MobileState r14 = (com.android.systemui.statusbar.policy.MobileSignalController.MobileState) r14
            boolean r14 = r14.enabled
            if (r14 == 0) goto L_0x00a9
            com.android.systemui.statusbar.policy.MobileSignalController$MobileState r11 = (com.android.systemui.statusbar.policy.MobileSignalController.MobileState) r11
            boolean r11 = r11.isEmergency
            if (r11 != 0) goto L_0x00a9
            r11 = r7
            goto L_0x00aa
        L_0x00a9:
            r11 = r4
        L_0x00aa:
            int r14 = r18.getQsCurrentIconId()
            r10.<init>(r11, r14, r2)
            T r2 = r0.mCurrentState
            r11 = r2
            com.android.systemui.statusbar.policy.MobileSignalController$MobileState r11 = (com.android.systemui.statusbar.policy.MobileSignalController.MobileState) r11
            boolean r11 = r11.isEmergency
            if (r11 == 0) goto L_0x00bb
            goto L_0x00bf
        L_0x00bb:
            com.android.systemui.statusbar.policy.MobileSignalController$MobileState r2 = (com.android.systemui.statusbar.policy.MobileSignalController.MobileState) r2
            java.lang.String r9 = r2.networkName
        L_0x00bf:
            r14 = r9
            r9 = r10
            goto L_0x00c4
        L_0x00c2:
            r8 = r4
            r14 = r9
        L_0x00c4:
            T r2 = r0.mCurrentState
            r10 = r2
            com.android.systemui.statusbar.policy.MobileSignalController$MobileState r10 = (com.android.systemui.statusbar.policy.MobileSignalController.MobileState) r10
            boolean r10 = r10.dataConnected
            if (r10 == 0) goto L_0x00dc
            r10 = r2
            com.android.systemui.statusbar.policy.MobileSignalController$MobileState r10 = (com.android.systemui.statusbar.policy.MobileSignalController.MobileState) r10
            boolean r10 = r10.carrierNetworkChangeMode
            if (r10 != 0) goto L_0x00dc
            com.android.systemui.statusbar.policy.MobileSignalController$MobileState r2 = (com.android.systemui.statusbar.policy.MobileSignalController.MobileState) r2
            boolean r2 = r2.activityIn
            if (r2 == 0) goto L_0x00dc
            r2 = r7
            goto L_0x00dd
        L_0x00dc:
            r2 = r4
        L_0x00dd:
            T r10 = r0.mCurrentState
            r11 = r10
            com.android.systemui.statusbar.policy.MobileSignalController$MobileState r11 = (com.android.systemui.statusbar.policy.MobileSignalController.MobileState) r11
            boolean r11 = r11.dataConnected
            if (r11 == 0) goto L_0x00f5
            r11 = r10
            com.android.systemui.statusbar.policy.MobileSignalController$MobileState r11 = (com.android.systemui.statusbar.policy.MobileSignalController.MobileState) r11
            boolean r11 = r11.carrierNetworkChangeMode
            if (r11 != 0) goto L_0x00f5
            com.android.systemui.statusbar.policy.MobileSignalController$MobileState r10 = (com.android.systemui.statusbar.policy.MobileSignalController.MobileState) r10
            boolean r10 = r10.activityOut
            if (r10 == 0) goto L_0x00f5
            r10 = r7
            goto L_0x00f6
        L_0x00f5:
            r10 = r4
        L_0x00f6:
            T r11 = r0.mCurrentState
            com.android.systemui.statusbar.policy.MobileSignalController$MobileState r11 = (com.android.systemui.statusbar.policy.MobileSignalController.MobileState) r11
            boolean r11 = r11.isDefault
            if (r11 != 0) goto L_0x0102
            if (r3 == 0) goto L_0x0101
            goto L_0x0102
        L_0x0101:
            r7 = r4
        L_0x0102:
            r3 = r5 & r7
            if (r3 != 0) goto L_0x010f
            com.android.systemui.statusbar.policy.NetworkControllerImpl$Config r3 = r0.mConfig
            boolean r3 = r3.alwaysShowDataRatIcon
            if (r3 == 0) goto L_0x010d
            goto L_0x010f
        L_0x010d:
            r7 = r4
            goto L_0x0112
        L_0x010f:
            int r3 = r1.mDataType
            r7 = r3
        L_0x0112:
            int r11 = r18.getVolteResId()
            boolean r15 = r1.mIsWide
            android.telephony.SubscriptionInfo r1 = r0.mSubscriptionInfo
            int r16 = r1.getSubscriptionId()
            T r0 = r0.mCurrentState
            com.android.systemui.statusbar.policy.MobileSignalController$MobileState r0 = (com.android.systemui.statusbar.policy.MobileSignalController.MobileState) r0
            boolean r0 = r0.roaming
            r4 = r19
            r5 = r6
            r6 = r9
            r9 = r2
            r17 = r0
            r4.setMobileDataIndicators(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.policy.MobileSignalController.notifyListeners(com.android.systemui.statusbar.policy.NetworkController$SignalCallback):void");
    }

    /* access modifiers changed from: protected */
    public MobileState cleanState() {
        return new MobileState();
    }

    private boolean isCdma() {
        SignalStrength signalStrength = this.mSignalStrength;
        return signalStrength != null && !signalStrength.isGsm();
    }

    public boolean isEmergencyOnly() {
        ServiceState serviceState = this.mServiceState;
        return serviceState != null && serviceState.isEmergencyOnly();
    }

    private boolean isRoaming() {
        ServiceState serviceState;
        if (isCarrierNetworkChangeActive()) {
            return false;
        }
        if (!isCdma() || (serviceState = this.mServiceState) == null) {
            ServiceState serviceState2 = this.mServiceState;
            if (serviceState2 == null || !serviceState2.getRoaming()) {
                return false;
            }
            return true;
        }
        int cdmaEriIconMode = serviceState.getCdmaEriIconMode();
        if (this.mServiceState.getCdmaEriIconIndex() == 1) {
            return false;
        }
        if (cdmaEriIconMode == 0 || cdmaEriIconMode == 1) {
            return true;
        }
        return false;
    }

    private boolean isCarrierNetworkChangeActive() {
        return ((MobileState) this.mCurrentState).carrierNetworkChangeMode;
    }

    public void handleBroadcast(Intent intent) {
        String action = intent.getAction();
        if (action.equals("android.provider.Telephony.SPN_STRINGS_UPDATED")) {
            updateNetworkName(intent.getBooleanExtra("showSpn", false), intent.getStringExtra("spn"), intent.getStringExtra("spnData"), intent.getBooleanExtra("showPlmn", false), intent.getStringExtra("plmn"));
            notifyListenersIfNecessary();
        } else if (action.equals("android.intent.action.ACTION_DEFAULT_DATA_SUBSCRIPTION_CHANGED")) {
            updateDataSim();
            notifyListenersIfNecessary();
        }
    }

    /* access modifiers changed from: private */
    public void updateDataSim() {
        int activeDataSubId = this.mDefaults.getActiveDataSubId();
        boolean z = true;
        if (SubscriptionManager.isValidSubscriptionId(activeDataSubId)) {
            MobileState mobileState = (MobileState) this.mCurrentState;
            if (activeDataSubId != this.mSubscriptionInfo.getSubscriptionId()) {
                z = false;
            }
            mobileState.dataSim = z;
            return;
        }
        ((MobileState) this.mCurrentState).dataSim = true;
    }

    /* access modifiers changed from: private */
    public boolean isCarrierSpecificDataIcon() {
        String str = this.mConfig.patternOfCarrierSpecificDataIcon;
        if (!(str == null || str.length() == 0)) {
            Pattern compile = Pattern.compile(this.mConfig.patternOfCarrierSpecificDataIcon);
            for (String str2 : new String[]{this.mServiceState.getOperatorAlphaLongRaw(), this.mServiceState.getOperatorAlphaShortRaw()}) {
                if (!TextUtils.isEmpty(str2) && compile.matcher(str2).find()) {
                    return true;
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void updateNetworkName(boolean z, String str, String str2, boolean z2, String str3) {
        if (SignalController.CHATTY) {
            Log.d("CarrierLabel", "updateNetworkName showSpn=" + z + " spn=" + str + " dataSpn=" + str2 + " showPlmn=" + z2 + " plmn=" + str3);
        }
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        if (z2 && str3 != null) {
            sb.append(str3);
            sb2.append(str3);
        }
        if (z && str != null) {
            if (sb.length() != 0) {
                sb.append(this.mNetworkNameSeparator);
            }
            sb.append(str);
        }
        if (sb.length() != 0) {
            ((MobileState) this.mCurrentState).networkName = sb.toString();
        } else {
            ((MobileState) this.mCurrentState).networkName = this.mNetworkNameDefault;
        }
        if (z && str2 != null) {
            if (sb2.length() != 0) {
                sb2.append(this.mNetworkNameSeparator);
            }
            sb2.append(str2);
        }
        if (sb2.length() != 0) {
            ((MobileState) this.mCurrentState).networkNameData = sb2.toString();
            return;
        }
        ((MobileState) this.mCurrentState).networkNameData = this.mNetworkNameDefault;
    }

    /* access modifiers changed from: private */
    public final void updateTelephony() {
        ServiceState serviceState;
        ServiceState serviceState2;
        if (SignalController.DEBUG) {
            Log.d(this.mTag, "updateTelephonySignalStrength: hasService=" + Utils.isInService(this.mServiceState) + " ss=" + this.mSignalStrength);
        }
        checkDefaultData();
        boolean z = true;
        ((MobileState) this.mCurrentState).connected = Utils.isInService(this.mServiceState) && this.mSignalStrength != null;
        if (((MobileState) this.mCurrentState).connected) {
            if (this.mSignalStrength.isGsm() || !this.mConfig.alwaysShowCdmaRssi) {
                ((MobileState) this.mCurrentState).level = this.mSignalStrength.getLevel();
            } else {
                ((MobileState) this.mCurrentState).level = this.mSignalStrength.getCdmaLevel();
            }
        }
        MobileIconGroup nr5GIconGroup = getNr5GIconGroup();
        if (this.mConfig.nrIconDisplayGracePeriodMs > 0) {
            nr5GIconGroup = adjustNr5GIconGroupByDisplayGraceTime(nr5GIconGroup);
        }
        if (nr5GIconGroup != null) {
            ((MobileState) this.mCurrentState).iconGroup = nr5GIconGroup;
        } else if (this.mNetworkToIconLookup.indexOfKey(this.mDataNetType) >= 0) {
            ((MobileState) this.mCurrentState).iconGroup = this.mNetworkToIconLookup.get(this.mDataNetType);
        } else {
            ((MobileState) this.mCurrentState).iconGroup = this.mDefaultIcons;
        }
        T t = this.mCurrentState;
        ((MobileState) t).dataConnected = ((MobileState) t).connected && this.mDataState == 2;
        MobileState mobileState = (MobileState) this.mCurrentState;
        if (!isRoaming() || !this.mRoamingIconAllowed) {
            z = false;
        }
        mobileState.roaming = z;
        if (isCarrierNetworkChangeActive()) {
            ((MobileState) this.mCurrentState).iconGroup = TelephonyIcons.CARRIER_NETWORK_CHANGE;
        } else if (isDataDisabled() && this.mDataDisabledIcon) {
            if (this.mSubscriptionInfo.getSubscriptionId() != this.mDefaults.getDefaultDataSubId()) {
                ((MobileState) this.mCurrentState).iconGroup = TelephonyIcons.NOT_DEFAULT_DATA;
            } else {
                ((MobileState) this.mCurrentState).iconGroup = TelephonyIcons.DATA_DISABLED;
            }
        }
        boolean isEmergencyOnly = isEmergencyOnly();
        T t2 = this.mCurrentState;
        if (isEmergencyOnly != ((MobileState) t2).isEmergency) {
            ((MobileState) t2).isEmergency = isEmergencyOnly();
            this.mNetworkController.recalculateEmergency();
        }
        if (((MobileState) this.mCurrentState).networkName.equals(this.mNetworkNameDefault) && (serviceState2 = this.mServiceState) != null && !TextUtils.isEmpty(serviceState2.getOperatorAlphaShort())) {
            ((MobileState) this.mCurrentState).networkName = this.mServiceState.getOperatorAlphaShort();
        }
        if (((MobileState) this.mCurrentState).networkNameData.equals(this.mNetworkNameDefault) && (serviceState = this.mServiceState) != null && ((MobileState) this.mCurrentState).dataSim && !TextUtils.isEmpty(serviceState.getDataOperatorAlphaShort())) {
            ((MobileState) this.mCurrentState).networkNameData = this.mServiceState.getDataOperatorAlphaShort();
        }
        notifyListenersIfNecessary();
    }

    private void checkDefaultData() {
        T t = this.mCurrentState;
        if (((MobileState) t).iconGroup != TelephonyIcons.NOT_DEFAULT_DATA) {
            ((MobileState) t).defaultDataOff = false;
            return;
        }
        ((MobileState) t).defaultDataOff = this.mNetworkController.isDataControllerDisabled();
    }

    /* access modifiers changed from: package-private */
    public void onMobileDataChanged() {
        checkDefaultData();
        notifyListeners();
    }

    private MobileIconGroup getNr5GIconGroup() {
        ServiceState serviceState = this.mServiceState;
        if (serviceState == null) {
            return null;
        }
        int nrState = serviceState.getNrState();
        if (nrState == 3) {
            if (this.mServiceState.getNrFrequencyRange() == 4 && this.mConfig.nr5GIconMap.containsKey(1)) {
                return this.mConfig.nr5GIconMap.get(1);
            }
            if (this.mConfig.nr5GIconMap.containsKey(2)) {
                return this.mConfig.nr5GIconMap.get(2);
            }
        } else if (nrState == 2) {
            if (((MobileState) this.mCurrentState).activityDormant) {
                if (this.mConfig.nr5GIconMap.containsKey(3)) {
                    return this.mConfig.nr5GIconMap.get(3);
                }
            } else if (this.mConfig.nr5GIconMap.containsKey(4)) {
                return this.mConfig.nr5GIconMap.get(4);
            }
        } else if (nrState == 1 && this.mConfig.nr5GIconMap.containsKey(5)) {
            return this.mConfig.nr5GIconMap.get(5);
        }
        return null;
    }

    private MobileIconGroup adjustNr5GIconGroupByDisplayGraceTime(MobileIconGroup mobileIconGroup) {
        if (this.mIsShowingIconGracefully && mobileIconGroup == null) {
            return (MobileIconGroup) ((MobileState) this.mCurrentState).iconGroup;
        }
        if (!this.mIsShowingIconGracefully && mobileIconGroup != null && ((MobileState) this.mLastState).iconGroup != mobileIconGroup) {
            Handler handler = this.mDisplayGraceHandler;
            handler.sendMessageDelayed(handler.obtainMessage(1), this.mConfig.nrIconDisplayGracePeriodMs);
            this.mIsShowingIconGracefully = true;
            return mobileIconGroup;
        } else if (((MobileState) this.mCurrentState).connected && this.mDataState != 0 && mobileIconGroup != null) {
            return mobileIconGroup;
        } else {
            this.mDisplayGraceHandler.removeMessages(1);
            this.mIsShowingIconGracefully = false;
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isDataDisabled() {
        return !this.mPhone.isDataCapable();
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void setActivity(int i) {
        boolean z = false;
        ((MobileState) this.mCurrentState).activityIn = i == 3 || i == 1;
        ((MobileState) this.mCurrentState).activityOut = i == 3 || i == 2;
        MobileState mobileState = (MobileState) this.mCurrentState;
        if (i == 4) {
            z = true;
        }
        mobileState.activityDormant = z;
        notifyListenersIfNecessary();
    }

    private boolean isVolteAvailable() {
        ImsManager imsManager = this.mImsManager;
        if (imsManager != null && imsManager.isEnhanced4gLteModeSettingEnabledByUser()) {
            T t = this.mCurrentState;
            if ((((MobileState) t).voiceCapable || ((MobileState) t).videoCapable) && ((MobileState) this.mCurrentState).imsRegistered) {
                return true;
            }
        }
        return false;
    }

    private boolean isVowifiAvailable() {
        T t = this.mCurrentState;
        return ((MobileState) t).voiceCapable && ((MobileState) t).imsRegistered && this.mServiceState.getDataNetworkType() == 18;
    }

    public void dump(PrintWriter printWriter) {
        super.dump(printWriter);
        printWriter.println("  mSubscription=" + this.mSubscriptionInfo + ",");
        printWriter.println("  mServiceState=" + this.mServiceState + ",");
        printWriter.println("  mSignalStrength=" + this.mSignalStrength + ",");
        printWriter.println("  mDataState=" + this.mDataState + ",");
        printWriter.println("  mDataNetType=" + this.mDataNetType + ",");
        printWriter.println("  mInflateSignalStrengths=" + this.mInflateSignalStrengths + ",");
        printWriter.println("  isDataDisabled=" + isDataDisabled() + ",");
        printWriter.println("  mIsShowingIconGracefully=" + this.mIsShowingIconGracefully + ",");
    }

    class MobilePhoneStateListener extends PhoneStateListener {
        public MobilePhoneStateListener(Looper looper) {
            super(looper);
        }

        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            String str;
            if (SignalController.DEBUG) {
                String str2 = MobileSignalController.this.mTag;
                StringBuilder sb = new StringBuilder();
                sb.append("onSignalStrengthsChanged signalStrength=");
                sb.append(signalStrength);
                if (signalStrength == null) {
                    str = "";
                } else {
                    str = " level=" + signalStrength.getLevel();
                }
                sb.append(str);
                Log.d(str2, sb.toString());
            }
            SignalStrength unused = MobileSignalController.this.mSignalStrength = signalStrength;
            MobileSignalController.this.updateTelephony();
        }

        public void onServiceStateChanged(ServiceState serviceState) {
            if (SignalController.DEBUG) {
                String str = MobileSignalController.this.mTag;
                Log.d(str, "onServiceStateChanged voiceState=" + serviceState.getVoiceRegState() + " dataState=" + serviceState.getDataRegState());
            }
            ServiceState unused = MobileSignalController.this.mServiceState = serviceState;
            if (MobileSignalController.this.mServiceState != null) {
                updateDataNetType(MobileSignalController.this.mServiceState.getDataNetworkType());
            }
            MobileSignalController.this.updateTelephony();
        }

        public void onDataConnectionStateChanged(int i, int i2) {
            if (SignalController.DEBUG) {
                String str = MobileSignalController.this.mTag;
                Log.d(str, "onDataConnectionStateChanged: state=" + i + " type=" + i2);
            }
            int unused = MobileSignalController.this.mDataState = i;
            updateDataNetType(i2);
            MobileSignalController.this.updateTelephony();
        }

        private void updateDataNetType(int i) {
            int unused = MobileSignalController.this.mDataNetType = i;
            if (MobileSignalController.this.mDataNetType != 13) {
                return;
            }
            if (MobileSignalController.this.isCarrierSpecificDataIcon()) {
                int unused2 = MobileSignalController.this.mDataNetType = 21;
            } else if (MobileSignalController.this.mServiceState != null && MobileSignalController.this.mServiceState.isUsingCarrierAggregation()) {
                int unused3 = MobileSignalController.this.mDataNetType = 19;
            }
        }

        public void onDataActivity(int i) {
            if (SignalController.DEBUG) {
                String str = MobileSignalController.this.mTag;
                Log.d(str, "onDataActivity: direction=" + i);
            }
            MobileSignalController.this.setActivity(i);
        }

        public void onCarrierNetworkChange(boolean z) {
            if (SignalController.DEBUG) {
                String str = MobileSignalController.this.mTag;
                Log.d(str, "onCarrierNetworkChange: active=" + z);
            }
            MobileSignalController mobileSignalController = MobileSignalController.this;
            ((MobileState) mobileSignalController.mCurrentState).carrierNetworkChangeMode = z;
            mobileSignalController.updateTelephony();
        }

        public void onActiveDataSubscriptionIdChanged(int i) {
            if (SignalController.DEBUG) {
                String str = MobileSignalController.this.mTag;
                Log.d(str, "onActiveDataSubscriptionIdChanged: subId=" + i);
            }
            MobileSignalController.this.updateDataSim();
            MobileSignalController.this.updateTelephony();
        }

        public void onCallStateChanged(int i, String str) {
            if (SignalController.DEBUG) {
                String str2 = MobileSignalController.this.mTag;
                Log.d(str2, "onCallStateChanged: state=" + i);
            }
            int unused = MobileSignalController.this.mCallState = i;
            MobileSignalController.this.updateTelephony();
        }
    }

    static class MobileIconGroup extends SignalController.IconGroup {
        final int mDataContentDescription;
        final int mDataType;
        final boolean mIsWide;
        final int mQsDataType;

        public MobileIconGroup(String str, int[][] iArr, int[][] iArr2, int[] iArr3, int i, int i2, int i3, int i4, int i5, int i6, int i7, boolean z) {
            super(str, iArr, iArr2, iArr3, i, i2, i3, i4, i5);
            this.mDataContentDescription = i6;
            this.mDataType = i7;
            this.mIsWide = z;
            this.mQsDataType = i7;
        }
    }

    static class MobileState extends SignalController.State {
        boolean airplaneMode;
        boolean carrierNetworkChangeMode;
        boolean dataConnected;
        boolean dataSim;
        boolean defaultDataOff;
        boolean imsRegistered;
        boolean isDefault;
        boolean isEmergency;
        String networkName;
        String networkNameData;
        boolean roaming;
        boolean userSetup;
        boolean videoCapable;
        boolean voiceCapable;

        MobileState() {
        }

        public void copyFrom(SignalController.State state) {
            super.copyFrom(state);
            MobileState mobileState = (MobileState) state;
            this.dataSim = mobileState.dataSim;
            this.networkName = mobileState.networkName;
            this.networkNameData = mobileState.networkNameData;
            this.dataConnected = mobileState.dataConnected;
            this.isDefault = mobileState.isDefault;
            this.isEmergency = mobileState.isEmergency;
            this.airplaneMode = mobileState.airplaneMode;
            this.carrierNetworkChangeMode = mobileState.carrierNetworkChangeMode;
            this.userSetup = mobileState.userSetup;
            this.roaming = mobileState.roaming;
            this.defaultDataOff = mobileState.defaultDataOff;
            this.imsRegistered = mobileState.imsRegistered;
            this.voiceCapable = mobileState.voiceCapable;
            this.videoCapable = mobileState.videoCapable;
        }

        /* access modifiers changed from: protected */
        public void toString(StringBuilder sb) {
            super.toString(sb);
            sb.append(',');
            sb.append("dataSim=");
            sb.append(this.dataSim);
            sb.append(',');
            sb.append("networkName=");
            sb.append(this.networkName);
            sb.append(',');
            sb.append("networkNameData=");
            sb.append(this.networkNameData);
            sb.append(',');
            sb.append("dataConnected=");
            sb.append(this.dataConnected);
            sb.append(',');
            sb.append("roaming=");
            sb.append(this.roaming);
            sb.append(',');
            sb.append("isDefault=");
            sb.append(this.isDefault);
            sb.append(',');
            sb.append("isEmergency=");
            sb.append(this.isEmergency);
            sb.append(',');
            sb.append("airplaneMode=");
            sb.append(this.airplaneMode);
            sb.append(',');
            sb.append("carrierNetworkChangeMode=");
            sb.append(this.carrierNetworkChangeMode);
            sb.append(',');
            sb.append("userSetup=");
            sb.append(this.userSetup);
            sb.append(',');
            sb.append("defaultDataOff=");
            sb.append(this.defaultDataOff);
            sb.append(',');
            sb.append("imsRegistered=");
            sb.append(this.imsRegistered);
            sb.append(',');
            sb.append("voiceCapable=");
            sb.append(this.voiceCapable);
            sb.append(',');
            sb.append("videoCapable=");
            sb.append(this.videoCapable);
        }

        public boolean equals(Object obj) {
            if (super.equals(obj)) {
                MobileState mobileState = (MobileState) obj;
                return Objects.equals(mobileState.networkName, this.networkName) && Objects.equals(mobileState.networkNameData, this.networkNameData) && mobileState.dataSim == this.dataSim && mobileState.dataConnected == this.dataConnected && mobileState.isEmergency == this.isEmergency && mobileState.airplaneMode == this.airplaneMode && mobileState.carrierNetworkChangeMode == this.carrierNetworkChangeMode && mobileState.userSetup == this.userSetup && mobileState.isDefault == this.isDefault && mobileState.roaming == this.roaming && mobileState.defaultDataOff == this.defaultDataOff && mobileState.imsRegistered == this.imsRegistered && mobileState.voiceCapable == this.voiceCapable && mobileState.videoCapable == this.videoCapable;
            }
        }
    }
}
