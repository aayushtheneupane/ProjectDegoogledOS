package com.android.settings.deviceinfo.simstatus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.os.UserHandle;
import android.telephony.CarrierConfigManager;
import android.telephony.CellBroadcastMessage;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.telephony.euicc.EuiccManager;
import android.text.BidiFormatter;
import android.text.SpannableStringBuilder;
import android.text.TextDirectionHeuristics;
import android.text.TextUtils;
import android.text.style.TtsSpan;
import com.android.settingslib.DeviceInfoUtils;
import com.android.settingslib.Utils;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnPause;
import com.android.settingslib.core.lifecycle.events.OnResume;
import com.havoc.config.center.C1715R;

public class SimStatusDialogController implements LifecycleObserver, OnResume, OnPause {
    static final int CELLULAR_NETWORK_STATE = 2131362132;
    static final int CELL_DATA_NETWORK_TYPE_VALUE_ID = 2131362129;
    static final int CELL_VOICE_NETWORK_TYPE_VALUE_ID = 2131363123;
    static final int EID_INFO_VALUE_ID = 2131362327;
    static final int ICCID_INFO_LABEL_ID = 2131362420;
    static final int ICCID_INFO_VALUE_ID = 2131362421;
    static final int ID_CDMA_SETTINGS = 2131362028;
    static final int ID_GSM_SETTINGS = 2131362389;
    static final int ID_IMEI_SV_VALUE = 2131362438;
    static final int ID_IMEI_VALUE = 2131362439;
    static final int ID_MEID_NUMBER_VALUE = 2131362535;
    static final int ID_MIN_NUMBER_VALUE = 2131362548;
    static final int ID_PRL_VERSION_VALUE = 2131362752;
    static final int IMS_REGISTRATION_STATE_LABEL_ID = 2131362440;
    static final int IMS_REGISTRATION_STATE_VALUE_ID = 2131362441;
    static final int NETWORK_PROVIDER_VALUE_ID = 2131362667;
    static final int OPERATOR_INFO_LABEL_ID = 2131362492;
    static final int OPERATOR_INFO_VALUE_ID = 2131362493;
    static final int PHONE_NUMBER_VALUE_ID = 2131362654;
    static final int ROAMING_INFO_VALUE_ID = 2131362812;
    static final int SERVICE_STATE_VALUE_ID = 2131362870;
    static final int SIGNAL_STRENGTH_LABEL_ID = 2131362894;
    static final int SIGNAL_STRENGTH_VALUE_ID = 2131362895;
    private final BroadcastReceiver mAreaInfoReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            Bundle extras;
            CellBroadcastMessage cellBroadcastMessage;
            if (TextUtils.equals(intent.getAction(), "com.android.cellbroadcastreceiver.CB_AREA_INFO_RECEIVED") && (extras = intent.getExtras()) != null && (cellBroadcastMessage = (CellBroadcastMessage) extras.get("message")) != null && SimStatusDialogController.this.mSubscriptionInfo.getSubscriptionId() == cellBroadcastMessage.getSubId()) {
                SimStatusDialogController.this.mDialog.setText(C1715R.C1718id.latest_area_info_value, cellBroadcastMessage.getMessageBody());
            }
        }
    };
    private final CarrierConfigManager mCarrierConfigManager;
    private final Context mContext;
    /* access modifiers changed from: private */
    public final SimStatusDialogFragment mDialog;
    private final EuiccManager mEuiccManager;
    private final SubscriptionManager.OnSubscriptionsChangedListener mOnSubscriptionsChangedListener = new SubscriptionManager.OnSubscriptionsChangedListener() {
        public void onSubscriptionsChanged() {
            SimStatusDialogController simStatusDialogController = SimStatusDialogController.this;
            SubscriptionInfo unused = simStatusDialogController.mSubscriptionInfo = simStatusDialogController.mSubscriptionManager.getActiveSubscriptionInfo(SimStatusDialogController.this.mSubscriptionInfo.getSubscriptionId());
            SimStatusDialogController.this.updateNetworkProvider();
        }
    };
    private PhoneStateListener mPhoneStateListener;
    private final Resources mRes;
    private boolean mShowLatestAreaInfo;
    private final int mSlotId;
    /* access modifiers changed from: private */
    public SubscriptionInfo mSubscriptionInfo;
    /* access modifiers changed from: private */
    public final SubscriptionManager mSubscriptionManager;
    private final TelephonyManager mTelephonyManager;

    public SimStatusDialogController(SimStatusDialogFragment simStatusDialogFragment, Lifecycle lifecycle, int i) {
        this.mDialog = simStatusDialogFragment;
        this.mSlotId = i;
        this.mContext = simStatusDialogFragment.getContext();
        this.mSubscriptionInfo = getPhoneSubscriptionInfo(i);
        this.mTelephonyManager = (TelephonyManager) this.mContext.getSystemService(TelephonyManager.class);
        this.mCarrierConfigManager = (CarrierConfigManager) this.mContext.getSystemService(CarrierConfigManager.class);
        this.mEuiccManager = (EuiccManager) this.mContext.getSystemService(EuiccManager.class);
        this.mSubscriptionManager = (SubscriptionManager) this.mContext.getSystemService(SubscriptionManager.class);
        this.mRes = this.mContext.getResources();
        if (lifecycle != null) {
            lifecycle.addObserver(this);
        }
    }

    public void initialize() {
        updateEid();
        if (this.mSubscriptionInfo != null) {
            this.mPhoneStateListener = getPhoneStateListener();
            updateNetworkProvider();
            ServiceState currentServiceState = getCurrentServiceState();
            updatePhoneNumber();
            updateLatestAreaInfo();
            updateServiceState(currentServiceState);
            updateSignalStrength(getSignalStrength());
            updateNetworkType();
            updateRoamingStatus(currentServiceState);
            updateIccidNumber();
            updateImsRegistrationState();
            if (this.mTelephonyManager.getPhoneType() == 2) {
                updateDialogForCdmaPhone();
            } else {
                updateDialogForGsmPhone();
            }
        }
    }

    public void onResume() {
        SubscriptionInfo subscriptionInfo = this.mSubscriptionInfo;
        if (subscriptionInfo != null) {
            this.mTelephonyManager.createForSubscriptionId(subscriptionInfo.getSubscriptionId()).listen(this.mPhoneStateListener, 321);
            this.mSubscriptionManager.addOnSubscriptionsChangedListener(this.mOnSubscriptionsChangedListener);
            if (this.mShowLatestAreaInfo) {
                this.mContext.registerReceiver(this.mAreaInfoReceiver, new IntentFilter("com.android.cellbroadcastreceiver.CB_AREA_INFO_RECEIVED"), "android.permission.RECEIVE_EMERGENCY_BROADCAST", (Handler) null);
                Intent intent = new Intent("com.android.cellbroadcastreceiver.GET_LATEST_CB_AREA_INFO");
                intent.setPackage("com.android.cellbroadcastreceiver");
                this.mContext.sendBroadcastAsUser(intent, UserHandle.ALL, "android.permission.RECEIVE_EMERGENCY_BROADCAST");
            }
        }
    }

    public void onPause() {
        if (this.mSubscriptionInfo != null) {
            this.mSubscriptionManager.removeOnSubscriptionsChangedListener(this.mOnSubscriptionsChangedListener);
            this.mTelephonyManager.createForSubscriptionId(this.mSubscriptionInfo.getSubscriptionId()).listen(this.mPhoneStateListener, 0);
            if (this.mShowLatestAreaInfo) {
                this.mContext.unregisterReceiver(this.mAreaInfoReceiver);
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateNetworkProvider() {
        SubscriptionInfo subscriptionInfo = this.mSubscriptionInfo;
        this.mDialog.setText(C1715R.C1718id.operator_name_value, subscriptionInfo != null ? subscriptionInfo.getCarrierName() : null);
    }

    private void updatePhoneNumber() {
        this.mDialog.setText(C1715R.C1718id.number_value, BidiFormatter.getInstance().unicodeWrap(getPhoneNumber(), TextDirectionHeuristics.LTR));
    }

    /* access modifiers changed from: private */
    public void updateDataState(int i) {
        String str;
        if (i == 0) {
            str = this.mRes.getString(C1715R.string.radioInfo_data_disconnected);
        } else if (i == 1) {
            str = this.mRes.getString(C1715R.string.radioInfo_data_connecting);
        } else if (i != 2) {
            str = i != 3 ? this.mRes.getString(C1715R.string.radioInfo_unknown) : this.mRes.getString(C1715R.string.radioInfo_data_suspended);
        } else {
            str = this.mRes.getString(C1715R.string.radioInfo_data_connected);
        }
        this.mDialog.setText(C1715R.C1718id.data_state_value, str);
    }

    private void updateLatestAreaInfo() {
        this.mShowLatestAreaInfo = Resources.getSystem().getBoolean(17891540) && this.mTelephonyManager.getPhoneType() != 2;
        if (!this.mShowLatestAreaInfo) {
            this.mDialog.removeSettingFromScreen(C1715R.C1718id.latest_area_info_label);
            this.mDialog.removeSettingFromScreen(C1715R.C1718id.latest_area_info_value);
        }
    }

    /* access modifiers changed from: private */
    public void updateServiceState(ServiceState serviceState) {
        String str;
        int combinedServiceState = Utils.getCombinedServiceState(serviceState);
        if (!Utils.isInService(serviceState)) {
            resetSignalStrength();
        }
        if (combinedServiceState == 0) {
            str = this.mRes.getString(C1715R.string.radioInfo_service_in);
        } else if (combinedServiceState == 1 || combinedServiceState == 2) {
            str = this.mRes.getString(C1715R.string.radioInfo_service_out);
        } else if (combinedServiceState != 3) {
            str = this.mRes.getString(C1715R.string.radioInfo_unknown);
        } else {
            str = this.mRes.getString(C1715R.string.radioInfo_service_off);
        }
        this.mDialog.setText(C1715R.C1718id.service_state_value, str);
    }

    /* access modifiers changed from: private */
    public void updateSignalStrength(SignalStrength signalStrength) {
        if (signalStrength != null) {
            PersistableBundle configForSubId = this.mCarrierConfigManager.getConfigForSubId(this.mSubscriptionInfo.getSubscriptionId());
            if (!(configForSubId != null ? configForSubId.getBoolean("show_signal_strength_in_sim_status_bool") : true)) {
                this.mDialog.removeSettingFromScreen(C1715R.C1718id.signal_strength_label);
                this.mDialog.removeSettingFromScreen(C1715R.C1718id.signal_strength_value);
                return;
            }
            ServiceState currentServiceState = getCurrentServiceState();
            if (currentServiceState != null && Utils.isInService(currentServiceState)) {
                int dbm = getDbm(signalStrength);
                int asuLevel = getAsuLevel(signalStrength);
                if (dbm == -1) {
                    dbm = 0;
                }
                if (asuLevel == -1) {
                    asuLevel = 0;
                }
                this.mDialog.setText(C1715R.C1718id.signal_strength_value, this.mRes.getString(C1715R.string.sim_signal_strength, new Object[]{Integer.valueOf(dbm), Integer.valueOf(asuLevel)}));
            }
        }
    }

    private void resetSignalStrength() {
        this.mDialog.setText(C1715R.C1718id.signal_strength_value, "0");
    }

    /* access modifiers changed from: private */
    public void updateNetworkType() {
        int subscriptionId = this.mSubscriptionInfo.getSubscriptionId();
        int dataNetworkType = this.mTelephonyManager.getDataNetworkType(subscriptionId);
        int voiceNetworkType = this.mTelephonyManager.getVoiceNetworkType(subscriptionId);
        String str = null;
        String networkTypeName = dataNetworkType != 0 ? TelephonyManager.getNetworkTypeName(dataNetworkType) : null;
        if (voiceNetworkType != 0) {
            str = TelephonyManager.getNetworkTypeName(voiceNetworkType);
        }
        boolean z = false;
        PersistableBundle configForSubId = this.mCarrierConfigManager.getConfigForSubId(subscriptionId);
        if (configForSubId != null) {
            z = configForSubId.getBoolean("show_4g_for_lte_data_icon_bool");
        }
        if (z) {
            if ("LTE".equals(networkTypeName)) {
                networkTypeName = "4G";
            }
            if ("LTE".equals(str)) {
                str = "4G";
            }
        }
        this.mDialog.setText(C1715R.C1718id.voice_network_type_value, str);
        this.mDialog.setText(C1715R.C1718id.data_network_type_value, networkTypeName);
    }

    /* access modifiers changed from: private */
    public void updateRoamingStatus(ServiceState serviceState) {
        if (serviceState.getRoaming()) {
            this.mDialog.setText(C1715R.C1718id.roaming_state_value, this.mRes.getString(C1715R.string.radioInfo_roaming_in));
        } else {
            this.mDialog.setText(C1715R.C1718id.roaming_state_value, this.mRes.getString(C1715R.string.radioInfo_roaming_not));
        }
    }

    private void updateIccidNumber() {
        int subscriptionId = this.mSubscriptionInfo.getSubscriptionId();
        PersistableBundle configForSubId = this.mCarrierConfigManager.getConfigForSubId(subscriptionId);
        if (!(configForSubId != null ? configForSubId.getBoolean("show_iccid_in_sim_status_bool") : false)) {
            this.mDialog.removeSettingFromScreen(C1715R.C1718id.icc_id_label);
            this.mDialog.removeSettingFromScreen(C1715R.C1718id.icc_id_value);
            return;
        }
        this.mDialog.setText(C1715R.C1718id.icc_id_value, getSimSerialNumber(subscriptionId));
    }

    private void updateEid() {
        if (this.mEuiccManager.isEnabled()) {
            this.mDialog.setText(C1715R.C1718id.esim_id_value, this.mEuiccManager.getEid());
        } else {
            this.mDialog.removeSettingFromScreen(C1715R.C1718id.esim_id_value);
        }
    }

    private void updateImsRegistrationState() {
        boolean z;
        int subscriptionId = this.mSubscriptionInfo.getSubscriptionId();
        PersistableBundle configForSubId = this.mCarrierConfigManager.getConfigForSubId(subscriptionId);
        if (configForSubId == null) {
            z = false;
        } else {
            z = configForSubId.getBoolean("show_ims_registration_status_bool");
        }
        if (z) {
            this.mDialog.setText(C1715R.C1718id.ims_reg_state_value, this.mRes.getString(this.mTelephonyManager.isImsRegistered(subscriptionId) ? C1715R.string.ims_reg_status_registered : C1715R.string.ims_reg_status_not_registered));
            return;
        }
        this.mDialog.removeSettingFromScreen(C1715R.C1718id.ims_reg_state_label);
        this.mDialog.removeSettingFromScreen(C1715R.C1718id.ims_reg_state_value);
    }

    private void updateDialogForCdmaPhone() {
        Resources resources = this.mDialog.getContext().getResources();
        this.mDialog.setText(C1715R.C1718id.meid_number_value, getMeid());
        SimStatusDialogFragment simStatusDialogFragment = this.mDialog;
        SubscriptionInfo subscriptionInfo = this.mSubscriptionInfo;
        simStatusDialogFragment.setText(C1715R.C1718id.min_number_value, subscriptionInfo != null ? this.mTelephonyManager.getCdmaMin(subscriptionInfo.getSubscriptionId()) : "");
        if (resources.getBoolean(C1715R.bool.config_msid_enable)) {
            this.mDialog.setText(C1715R.C1718id.min_number_label, resources.getString(C1715R.string.status_msid_number));
        }
        this.mDialog.setText(C1715R.C1718id.prl_version_value, getCdmaPrlVersion());
        if (this.mSubscriptionInfo == null || !isCdmaLteEnabled()) {
            this.mDialog.removeSettingFromScreen(C1715R.C1718id.gsm_settings);
            return;
        }
        this.mDialog.setText(C1715R.C1718id.imei_value, getTextAsDigits(this.mTelephonyManager.getImei(this.mSlotId)));
        this.mDialog.setText(C1715R.C1718id.imei_sv_value, getTextAsDigits(this.mTelephonyManager.getDeviceSoftwareVersion(this.mSlotId)));
    }

    private void updateDialogForGsmPhone() {
        this.mDialog.setText(C1715R.C1718id.imei_value, getTextAsDigits(this.mTelephonyManager.getImei(this.mSlotId)));
        this.mDialog.setText(C1715R.C1718id.imei_sv_value, getTextAsDigits(this.mTelephonyManager.getDeviceSoftwareVersion(this.mSlotId)));
        this.mDialog.removeSettingFromScreen(C1715R.C1718id.cdma_settings);
    }

    private SubscriptionInfo getPhoneSubscriptionInfo(int i) {
        return SubscriptionManager.from(this.mContext).getActiveSubscriptionInfoForSimSlotIndex(i);
    }

    /* access modifiers changed from: package-private */
    public ServiceState getCurrentServiceState() {
        return this.mTelephonyManager.getServiceStateForSubscriber(this.mSubscriptionInfo.getSubscriptionId());
    }

    private int getDbm(SignalStrength signalStrength) {
        return signalStrength.getDbm();
    }

    private int getAsuLevel(SignalStrength signalStrength) {
        return signalStrength.getAsuLevel();
    }

    /* access modifiers changed from: package-private */
    public PhoneStateListener getPhoneStateListener() {
        return new PhoneStateListener() {
            public void onDataConnectionStateChanged(int i) {
                SimStatusDialogController.this.updateDataState(i);
                SimStatusDialogController.this.updateNetworkType();
            }

            public void onSignalStrengthsChanged(SignalStrength signalStrength) {
                SimStatusDialogController.this.updateSignalStrength(signalStrength);
            }

            public void onServiceStateChanged(ServiceState serviceState) {
                SimStatusDialogController.this.updateNetworkProvider();
                SimStatusDialogController.this.updateServiceState(serviceState);
                SimStatusDialogController.this.updateRoamingStatus(serviceState);
            }
        };
    }

    /* access modifiers changed from: package-private */
    public String getPhoneNumber() {
        return DeviceInfoUtils.getFormattedPhoneNumber(this.mContext, this.mSubscriptionInfo);
    }

    /* access modifiers changed from: package-private */
    public SignalStrength getSignalStrength() {
        return this.mTelephonyManager.getSignalStrength();
    }

    /* access modifiers changed from: package-private */
    public String getSimSerialNumber(int i) {
        return this.mTelephonyManager.getSimSerialNumber(i);
    }

    /* access modifiers changed from: package-private */
    public String getCdmaPrlVersion() {
        return this.mTelephonyManager.getCdmaPrlVersion();
    }

    /* access modifiers changed from: package-private */
    public boolean isCdmaLteEnabled() {
        return this.mTelephonyManager.getLteOnCdmaMode(this.mSubscriptionInfo.getSubscriptionId()) == 1;
    }

    /* access modifiers changed from: package-private */
    public String getMeid() {
        return this.mTelephonyManager.getMeid(this.mSlotId);
    }

    private static CharSequence getTextAsDigits(CharSequence charSequence) {
        if (!TextUtils.isDigitsOnly(charSequence)) {
            return charSequence;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(charSequence);
        spannableStringBuilder.setSpan(new TtsSpan.DigitsBuilder(charSequence.toString()).build(), 0, spannableStringBuilder.length(), 33);
        return spannableStringBuilder;
    }
}
