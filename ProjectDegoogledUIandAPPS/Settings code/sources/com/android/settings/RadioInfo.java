package com.android.settings;

import android.app.Activity;
import android.app.QueuedWork;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.net.TrafficStats;
import android.net.Uri;
import android.os.AsyncResult;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.os.SystemProperties;
import android.provider.Settings;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellLocation;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.CellSignalStrengthLte;
import android.telephony.PhoneStateListener;
import android.telephony.PhysicalChannelConfig;
import android.telephony.PreciseCallState;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.android.ims.ImsConfig;
import com.android.ims.ImsException;
import com.android.ims.ImsManager;
import com.android.internal.telephony.Phone;
import com.android.internal.telephony.PhoneFactory;
import com.havoc.config.center.C1715R;
import java.util.List;

public class RadioInfo extends Activity {
    private static final String[] PREFERRED_NETWORK_LABELS = {"GSM/WCDMA preferred", "GSM only", "WCDMA only", "GSM/WCDMA auto (PRL)", "CDMA/EvDo auto (PRL)", "CDMA only", "EvDo only", "CDMA/EvDo/GSM/WCDMA (PRL)", "CDMA + LTE/EvDo (PRL)", "GSM/WCDMA/LTE (PRL)", "LTE/CDMA/EvDo/GSM/WCDMA (PRL)", "LTE only", "LTE/WCDMA", "TDSCDMA only", "TDSCDMA/WCDMA", "LTE/TDSCDMA", "TDSCDMA/GSM", "LTE/TDSCDMA/GSM", "TDSCDMA/GSM/WCDMA", "LTE/TDSCDMA/WCDMA", "LTE/TDSCDMA/GSM/WCDMA", "TDSCDMA/CDMA/EvDo/GSM/WCDMA ", "LTE/TDSCDMA/CDMA/EvDo/GSM/WCDMA", "NR only", "NR/LTE", "NR/LTE/CDMA/EvDo", "NR/LTE/GSM/WCDMA", "NR/LTE/CDMA/EvDo/GSM/WCDMA", "NR/LTE/WCDMA", "NR/LTE/TDSCDMA", "NR/LTE/TDSCDMA/GSM", "NR/LTE/TDSCDMA/WCDMA", "NR/LTE/TDSCDMA/GSM/WCDMA", "NR/LTE/TDSCDMA/CDMA/EvDo/GSM/WCDMA", "Unknown"};
    private static final String[] PREFERRED_NETWORK_LABELS_MAX_LTE = {"GSM/WCDMA preferred", "GSM only", "WCDMA only", "GSM/WCDMA auto (PRL)", "CDMA/EvDo auto (PRL)", "CDMA only", "EvDo only", "CDMA/EvDo/GSM/WCDMA (PRL)", "CDMA + LTE/EvDo (PRL)", "GSM/WCDMA/LTE (PRL)", "LTE/CDMA/EvDo/GSM/WCDMA (PRL)", "LTE only", "LTE/WCDMA", "TDSCDMA only", "TDSCDMA/WCDMA", "LTE/TDSCDMA", "TDSCDMA/GSM", "LTE/TDSCDMA/GSM", "TDSCDMA/GSM/WCDMA", "LTE/TDSCDMA/WCDMA", "LTE/TDSCDMA/GSM/WCDMA", "TDSCDMA/CDMA/EvDo/GSM/WCDMA ", "LTE/TDSCDMA/CDMA/EvDo/GSM/WCDMA", "Unknown"};
    private static final String[] mCellInfoRefreshRateLabels = {"Disabled", "Immediate", "Min 5s", "Min 10s", "Min 60s"};
    /* access modifiers changed from: private */
    public static final int[] mCellInfoRefreshRates = {Integer.MAX_VALUE, 0, 5000, 10000, 60000};
    /* access modifiers changed from: private */
    public static String[] mPhoneIndexLabels;
    private TextView callState;
    private Button carrierProvisioningButton;
    /* access modifiers changed from: private */
    public Switch cbrsDataSwitch;
    private Spinner cellInfoRefreshRateSpinner;
    private TextView dBm;
    private TextView dataNetwork;
    private TextView dnsCheckState;
    private Button dnsCheckToggleButton;
    /* access modifiers changed from: private */
    public Switch dsdsSwitch;
    private Switch eabProvisionedSwitch;
    private TextView gprsState;
    private TextView gsmState;
    private Switch imsVolteProvisionedSwitch;
    private Switch imsVtProvisionedSwitch;
    private Switch imsWfcProvisionedSwitch;
    View.OnClickListener mCarrierProvisioningButtonHandler = new View.OnClickListener() {
        public void onClick(View view) {
            Intent intent = new Intent("com.android.settings.CARRIER_PROVISIONING");
            intent.setComponent(ComponentName.unflattenFromString("com.android.omadm.service/.DMIntentReceiver"));
            RadioInfo.this.sendBroadcast(intent);
        }
    };
    CompoundButton.OnCheckedChangeListener mCbrsDataSwitchChangeListener = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            RadioInfo.this.updateCbrsDataState(z);
        }
    };
    private TextView mCellInfo;
    AdapterView.OnItemSelectedListener mCellInfoRefreshRateHandler = new AdapterView.OnItemSelectedListener() {
        public void onNothingSelected(AdapterView adapterView) {
        }

        public void onItemSelected(AdapterView adapterView, View view, int i, long j) {
            int unused = RadioInfo.this.mCellInfoRefreshRateIndex = i;
            RadioInfo.this.mTelephonyManager.setCellInfoListRate(RadioInfo.mCellInfoRefreshRates[i]);
            RadioInfo.this.updateAllCellInfo();
        }
    };
    /* access modifiers changed from: private */
    public int mCellInfoRefreshRateIndex;
    /* access modifiers changed from: private */
    public List<CellInfo> mCellInfoResult = null;
    /* access modifiers changed from: private */
    public CellLocation mCellLocationResult = null;
    private TextView mCfi;
    /* access modifiers changed from: private */
    public boolean mCfiValue = false;
    private ConnectivityManager mConnectivityManager;
    private TextView mDds;
    private final NetworkRequest mDefaultNetworkRequest = new NetworkRequest.Builder().addTransportType(0).addCapability(12).build();
    private TextView mDeviceId;
    View.OnClickListener mDnsCheckButtonHandler = new View.OnClickListener() {
        public void onClick(View view) {
            RadioInfo.this.mPhone.disableDnsCheck(!RadioInfo.this.mPhone.isDnsCheckDisabled());
            RadioInfo.this.updateDnsCheckState();
        }
    };
    private TextView mDownlinkKbps;
    CompoundButton.OnCheckedChangeListener mEabCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            RadioInfo.this.setEabProvisionedState(z);
        }
    };
    private MenuItem.OnMenuItemClickListener mGetImsStatus = new MenuItem.OnMenuItemClickListener() {
        public boolean onMenuItemClick(MenuItem menuItem) {
            String str;
            boolean isImsRegistered = RadioInfo.this.mPhone.isImsRegistered();
            boolean isVolteEnabled = RadioInfo.this.mPhone.isVolteEnabled();
            boolean isWifiCallingEnabled = RadioInfo.this.mPhone.isWifiCallingEnabled();
            boolean isVideoEnabled = RadioInfo.this.mPhone.isVideoEnabled();
            boolean isUtEnabled = RadioInfo.this.mPhone.isUtEnabled();
            if (isImsRegistered) {
                str = RadioInfo.this.getString(C1715R.string.radio_info_ims_reg_status_registered);
            } else {
                str = RadioInfo.this.getString(C1715R.string.radio_info_ims_reg_status_not_registered);
            }
            String string = RadioInfo.this.getString(C1715R.string.radio_info_ims_feature_status_available);
            String string2 = RadioInfo.this.getString(C1715R.string.radio_info_ims_feature_status_unavailable);
            RadioInfo radioInfo = RadioInfo.this;
            Object[] objArr = new Object[5];
            objArr[0] = str;
            objArr[1] = isVolteEnabled ? string : string2;
            objArr[2] = isWifiCallingEnabled ? string : string2;
            objArr[3] = isVideoEnabled ? string : string2;
            if (!isUtEnabled) {
                string = string2;
            }
            objArr[4] = string;
            String string3 = radioInfo.getString(C1715R.string.radio_info_ims_reg_status, objArr);
            AlertDialog.Builder builder = new AlertDialog.Builder(RadioInfo.this);
            builder.setMessage((CharSequence) string3);
            builder.setTitle((CharSequence) RadioInfo.this.getString(C1715R.string.radio_info_ims_reg_status_title));
            builder.create().show();
            return true;
        }
    };
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            Object obj;
            int i = message.what;
            if (i == 1000) {
                AsyncResult asyncResult = (AsyncResult) message.obj;
                if (asyncResult.exception != null || (obj = asyncResult.result) == null) {
                    RadioInfo radioInfo = RadioInfo.this;
                    radioInfo.updatePreferredNetworkType(radioInfo.mPreferredNetworkLabels.length - 1);
                    return;
                }
                RadioInfo.this.updatePreferredNetworkType(((int[]) obj)[0]);
            } else if (i != 1001) {
                if (i == 1005) {
                    AsyncResult asyncResult2 = (AsyncResult) message.obj;
                    if (asyncResult2.exception != null) {
                        RadioInfo.this.smsc.setText("refresh error");
                    } else {
                        RadioInfo.this.smsc.setText((String) asyncResult2.result);
                    }
                } else if (i != 1006) {
                    super.handleMessage(message);
                } else {
                    RadioInfo.this.updateSmscButton.setEnabled(true);
                    if (((AsyncResult) message.obj).exception != null) {
                        RadioInfo.this.smsc.setText("update error");
                    }
                }
            } else if (((AsyncResult) message.obj).exception != null) {
                RadioInfo.log("Set preferred network type failed.");
            }
        }
    };
    /* access modifiers changed from: private */
    public TextView mHttpClientTest;
    /* access modifiers changed from: private */
    public String mHttpClientTestResult;
    /* access modifiers changed from: private */
    public ImsManager mImsManager = null;
    CompoundButton.OnCheckedChangeListener mImsVolteCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            RadioInfo.this.setImsVolteProvisionedState(z);
        }
    };
    CompoundButton.OnCheckedChangeListener mImsVtCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            RadioInfo.this.setImsVtProvisionedState(z);
        }
    };
    CompoundButton.OnCheckedChangeListener mImsWfcCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            RadioInfo.this.setImsWfcProvisionedState(z);
        }
    };
    private TextView mLocation;
    private TextView mMwi;
    /* access modifiers changed from: private */
    public boolean mMwiValue = false;
    private final ConnectivityManager.NetworkCallback mNetworkCallback = new ConnectivityManager.NetworkCallback() {
        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            RadioInfo.this.updateBandwidths(networkCapabilities.getLinkDownstreamBandwidthKbps(), networkCapabilities.getLinkUpstreamBandwidthKbps());
        }
    };
    View.OnClickListener mOemInfoButtonHandler = new View.OnClickListener() {
        public void onClick(View view) {
            try {
                RadioInfo.this.startActivity(new Intent("com.android.settings.OEM_RADIO_INFO"));
            } catch (ActivityNotFoundException e) {
                RadioInfo.log("OEM-specific Info/Settings Activity Not Found : " + e);
            }
        }
    };
    DialogInterface.OnClickListener mOnDsdsDialogConfirmedListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialogInterface, int i) {
            if (i == -1) {
                RadioInfo.this.dsdsSwitch.toggle();
                RadioInfo.this.performDsdsSwitch();
            }
        }
    };
    /* access modifiers changed from: private */
    public Phone mPhone = null;
    private PhoneStateListener mPhoneStateListener = new RadioInfoPhoneStateListener();
    private TextView mPhyChanConfig;
    View.OnClickListener mPingButtonHandler = new View.OnClickListener() {
        public void onClick(View view) {
            RadioInfo.this.updatePingState();
        }
    };
    /* access modifiers changed from: private */
    public String mPingHostnameResultV4;
    /* access modifiers changed from: private */
    public String mPingHostnameResultV6;
    /* access modifiers changed from: private */
    public TextView mPingHostnameV4;
    /* access modifiers changed from: private */
    public TextView mPingHostnameV6;
    AdapterView.OnItemSelectedListener mPreferredNetworkHandler = new AdapterView.OnItemSelectedListener() {
        public void onNothingSelected(AdapterView adapterView) {
        }

        public void onItemSelected(AdapterView adapterView, View view, int i, long j) {
            if (RadioInfo.this.mPreferredNetworkTypeResult != i && i >= 0 && i <= RadioInfo.this.mPreferredNetworkLabels.length - 2) {
                int unused = RadioInfo.this.mPreferredNetworkTypeResult = i;
                int subId = RadioInfo.this.mPhone.getSubId();
                if (SubscriptionManager.isUsableSubIdValue(subId)) {
                    ContentResolver contentResolver = RadioInfo.this.mPhone.getContext().getContentResolver();
                    Settings.Global.putInt(contentResolver, "preferred_network_mode" + subId, RadioInfo.this.mPreferredNetworkTypeResult);
                }
                RadioInfo.log("Calling setPreferredNetworkType(" + RadioInfo.this.mPreferredNetworkTypeResult + ")");
                RadioInfo.this.mPhone.setPreferredNetworkType(RadioInfo.this.mPreferredNetworkTypeResult, RadioInfo.this.mHandler.obtainMessage(1001));
            }
        }
    };
    /* access modifiers changed from: private */
    public String[] mPreferredNetworkLabels;
    /* access modifiers changed from: private */
    public int mPreferredNetworkTypeResult;
    CompoundButton.OnCheckedChangeListener mRadioPowerOnChangeListener = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            StringBuilder sb = new StringBuilder();
            sb.append("toggle radio power: currently ");
            sb.append(RadioInfo.this.isRadioOn() ? "on" : "off");
            RadioInfo.log(sb.toString());
            RadioInfo.this.mPhone.setRadioPower(z);
        }
    };
    View.OnClickListener mRefreshSmscButtonHandler = new View.OnClickListener() {
        public void onClick(View view) {
            RadioInfo.this.refreshSmsc();
        }
    };
    private MenuItem.OnMenuItemClickListener mSelectBandCallback = new MenuItem.OnMenuItemClickListener() {
        public boolean onMenuItemClick(MenuItem menuItem) {
            Intent intent = new Intent();
            intent.setClass(RadioInfo.this, BandMode.class);
            RadioInfo.this.startActivity(intent);
            return true;
        }
    };
    private Spinner mSelectPhoneIndex;
    AdapterView.OnItemSelectedListener mSelectPhoneIndexHandler = new AdapterView.OnItemSelectedListener() {
        public void onNothingSelected(AdapterView adapterView) {
        }

        public void onItemSelected(AdapterView adapterView, View view, int i, long j) {
            Phone[] phones;
            int[] subId;
            if (i >= 0 && i <= RadioInfo.mPhoneIndexLabels.length - 1 && (phones = PhoneFactory.getPhones()) != null && phones.length > i && (subId = SubscriptionManager.getSubId(i)) != null && subId.length >= 1) {
                int unused = RadioInfo.this.mSelectedPhoneIndex = i;
                RadioInfo.this.updatePhoneIndex(i, subId[0]);
            }
        }
    };
    /* access modifiers changed from: private */
    public int mSelectedPhoneIndex;
    private TextView mSubscriberId;
    private TextView mSubscriptionId;
    /* access modifiers changed from: private */
    public TelephonyManager mTelephonyManager;
    private MenuItem.OnMenuItemClickListener mToggleData = new MenuItem.OnMenuItemClickListener() {
        public boolean onMenuItemClick(MenuItem menuItem) {
            int dataState = RadioInfo.this.mTelephonyManager.getDataState();
            if (dataState == 0) {
                RadioInfo.this.mTelephonyManager.setDataEnabled(true);
            } else if (dataState == 2) {
                RadioInfo.this.mTelephonyManager.setDataEnabled(false);
            }
            return true;
        }
    };
    View.OnClickListener mTriggerCarrierProvisioningButtonHandler = new View.OnClickListener() {
        public void onClick(View view) {
            Intent intent = new Intent("com.android.settings.TRIGGER_CARRIER_PROVISIONING");
            intent.setComponent(ComponentName.unflattenFromString("com.android.omadm.service/.DMIntentReceiver"));
            RadioInfo.this.sendBroadcast(intent);
        }
    };
    View.OnClickListener mUpdateSmscButtonHandler = new View.OnClickListener() {
        public void onClick(View view) {
            RadioInfo.this.updateSmscButton.setEnabled(false);
            RadioInfo.this.mPhone.setSmscAddress(RadioInfo.this.smsc.getText().toString(), RadioInfo.this.mHandler.obtainMessage(1006));
        }
    };
    private TextView mUplinkKbps;
    private MenuItem.OnMenuItemClickListener mViewADNCallback = new MenuItem.OnMenuItemClickListener() {
        public boolean onMenuItemClick(MenuItem menuItem) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setClassName("com.android.phone", "com.android.phone.SimContacts");
            RadioInfo.this.startActivity(intent);
            return true;
        }
    };
    private MenuItem.OnMenuItemClickListener mViewFDNCallback = new MenuItem.OnMenuItemClickListener() {
        public boolean onMenuItemClick(MenuItem menuItem) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setClassName("com.android.phone", "com.android.phone.settings.fdn.FdnList");
            RadioInfo.this.startActivity(intent);
            return true;
        }
    };
    private MenuItem.OnMenuItemClickListener mViewSDNCallback = new MenuItem.OnMenuItemClickListener() {
        public boolean onMenuItemClick(MenuItem menuItem) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("content://icc/sdn"));
            intent.setClassName("com.android.phone", "com.android.phone.ADNList");
            RadioInfo.this.startActivity(intent);
            return true;
        }
    };
    private TextView number;
    private Button oemInfoButton;
    private TextView operatorName;
    private Button pingTestButton;
    private Spinner preferredNetworkType;
    private Switch radioPowerOnSwitch;
    private TextView received;
    private Button refreshSmscButton;
    private TextView roamingState;
    private TextView sent;
    /* access modifiers changed from: private */
    public EditText smsc;
    private Button triggercarrierProvisioningButton;
    /* access modifiers changed from: private */
    public Button updateSmscButton;
    private TextView voiceNetwork;

    /* access modifiers changed from: private */
    public static void log(String str) {
        Log.d("RadioInfo", str);
    }

    private class RadioInfoPhoneStateListener extends PhoneStateListener {
        private RadioInfoPhoneStateListener() {
        }

        public void onDataConnectionStateChanged(int i) {
            RadioInfo.this.updateDataState();
            RadioInfo.this.updateNetworkType();
        }

        public void onDataActivity(int i) {
            RadioInfo.this.updateDataStats2();
        }

        public void onCallStateChanged(int i, String str) {
            RadioInfo.this.updateNetworkType();
            RadioInfo.this.updatePhoneState(i);
        }

        public void onPreciseCallStateChanged(PreciseCallState preciseCallState) {
            RadioInfo.this.updateNetworkType();
        }

        public void onCellLocationChanged(CellLocation cellLocation) {
            RadioInfo.this.updateLocation(cellLocation);
        }

        public void onMessageWaitingIndicatorChanged(boolean z) {
            boolean unused = RadioInfo.this.mMwiValue = z;
            RadioInfo.this.updateMessageWaiting();
        }

        public void onCallForwardingIndicatorChanged(boolean z) {
            boolean unused = RadioInfo.this.mCfiValue = z;
            RadioInfo.this.updateCallRedirect();
        }

        public void onCellInfoChanged(List<CellInfo> list) {
            RadioInfo.log("onCellInfoChanged: arrayCi=" + list);
            List unused = RadioInfo.this.mCellInfoResult = list;
            RadioInfo radioInfo = RadioInfo.this;
            radioInfo.updateCellInfo(radioInfo.mCellInfoResult);
        }

        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            RadioInfo.log("onSignalStrengthChanged: SignalStrength=" + signalStrength);
            RadioInfo.this.updateSignalStrength(signalStrength);
        }

        public void onServiceStateChanged(ServiceState serviceState) {
            RadioInfo.log("onServiceStateChanged: ServiceState=" + serviceState);
            RadioInfo.this.updateServiceState(serviceState);
            RadioInfo.this.updateRadioPowerState();
            RadioInfo.this.updateNetworkType();
            RadioInfo.this.updateImsProvisionedState();
        }

        public void onPhysicalChannelConfigurationChanged(List<PhysicalChannelConfig> list) {
            RadioInfo.this.updatePhysicalChannelConfiguration(list);
        }
    }

    /* access modifiers changed from: private */
    public void updatePhysicalChannelConfiguration(List<PhysicalChannelConfig> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (list != null) {
            String str = "";
            for (PhysicalChannelConfig append : list) {
                sb.append(str);
                sb.append(append);
                str = ",";
            }
        }
        sb.append("}");
        this.mPhyChanConfig.setText(sb.toString());
    }

    /* access modifiers changed from: private */
    public void updatePreferredNetworkType(int i) {
        if (i >= this.mPreferredNetworkLabels.length || i < 0) {
            log("EVENT_QUERY_PREFERRED_TYPE_DONE: unknown type=" + i);
            i = this.mPreferredNetworkLabels.length - 1;
        }
        this.mPreferredNetworkTypeResult = i;
        this.preferredNetworkType.setSelection(this.mPreferredNetworkTypeResult, true);
    }

    /* access modifiers changed from: private */
    public void updatePhoneIndex(int i, int i2) {
        unregisterPhoneStateListener();
        this.mTelephonyManager.setCellInfoListRate(Integer.MAX_VALUE);
        this.mTelephonyManager = this.mTelephonyManager.createForSubscriptionId(i2);
        this.mImsManager = ImsManager.getInstance(getApplicationContext(), i);
        this.mPhone = PhoneFactory.getPhone(i);
        updateAllFields();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!Process.myUserHandle().isSystem()) {
            Log.e("RadioInfo", "Not run from system user, don't do anything.");
            finish();
            return;
        }
        setContentView(C1715R.layout.radio_info);
        log("Started onCreate");
        this.mTelephonyManager = (TelephonyManager) getSystemService("phone");
        this.mConnectivityManager = (ConnectivityManager) getSystemService("connectivity");
        this.mPhone = PhoneFactory.getDefaultPhone();
        this.mImsManager = ImsManager.getInstance(getApplicationContext(), SubscriptionManager.getDefaultVoicePhoneId());
        mPhoneIndexLabels = getPhoneIndexLabels(this.mTelephonyManager);
        this.mDeviceId = (TextView) findViewById(C1715R.C1718id.imei);
        this.number = (TextView) findViewById(C1715R.C1718id.number);
        this.mSubscriptionId = (TextView) findViewById(C1715R.C1718id.subid);
        this.mDds = (TextView) findViewById(C1715R.C1718id.dds);
        this.mSubscriberId = (TextView) findViewById(C1715R.C1718id.imsi);
        this.callState = (TextView) findViewById(C1715R.C1718id.call);
        this.operatorName = (TextView) findViewById(C1715R.C1718id.operator);
        this.roamingState = (TextView) findViewById(C1715R.C1718id.roaming);
        this.gsmState = (TextView) findViewById(C1715R.C1718id.gsm);
        this.gprsState = (TextView) findViewById(C1715R.C1718id.gprs);
        this.voiceNetwork = (TextView) findViewById(C1715R.C1718id.voice_network);
        this.dataNetwork = (TextView) findViewById(C1715R.C1718id.data_network);
        this.dBm = (TextView) findViewById(C1715R.C1718id.dbm);
        this.mMwi = (TextView) findViewById(C1715R.C1718id.mwi);
        this.mCfi = (TextView) findViewById(C1715R.C1718id.cfi);
        this.mLocation = (TextView) findViewById(C1715R.C1718id.location);
        this.mCellInfo = (TextView) findViewById(C1715R.C1718id.cellinfo);
        this.mCellInfo.setTypeface(Typeface.MONOSPACE);
        this.sent = (TextView) findViewById(C1715R.C1718id.sent);
        this.received = (TextView) findViewById(C1715R.C1718id.received);
        this.smsc = (EditText) findViewById(C1715R.C1718id.smsc);
        this.dnsCheckState = (TextView) findViewById(C1715R.C1718id.dnsCheckState);
        this.mPingHostnameV4 = (TextView) findViewById(C1715R.C1718id.pingHostnameV4);
        this.mPingHostnameV6 = (TextView) findViewById(C1715R.C1718id.pingHostnameV6);
        this.mHttpClientTest = (TextView) findViewById(C1715R.C1718id.httpClientTest);
        this.mPhyChanConfig = (TextView) findViewById(C1715R.C1718id.phy_chan_config);
        this.mPreferredNetworkLabels = (this.mTelephonyManager.getSupportedRadioAccessFamily() & 524288) > 0 ? PREFERRED_NETWORK_LABELS : PREFERRED_NETWORK_LABELS_MAX_LTE;
        this.preferredNetworkType = (Spinner) findViewById(C1715R.C1718id.preferredNetworkType);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, 17367048, this.mPreferredNetworkLabels);
        arrayAdapter.setDropDownViewResource(17367049);
        this.preferredNetworkType.setAdapter(arrayAdapter);
        this.mSelectPhoneIndex = (Spinner) findViewById(C1715R.C1718id.phoneIndex);
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this, 17367048, mPhoneIndexLabels);
        arrayAdapter2.setDropDownViewResource(17367049);
        this.mSelectPhoneIndex.setAdapter(arrayAdapter2);
        this.cellInfoRefreshRateSpinner = (Spinner) findViewById(C1715R.C1718id.cell_info_rate_select);
        ArrayAdapter arrayAdapter3 = new ArrayAdapter(this, 17367048, mCellInfoRefreshRateLabels);
        arrayAdapter3.setDropDownViewResource(17367049);
        this.cellInfoRefreshRateSpinner.setAdapter(arrayAdapter3);
        this.imsVolteProvisionedSwitch = (Switch) findViewById(C1715R.C1718id.volte_provisioned_switch);
        this.imsVtProvisionedSwitch = (Switch) findViewById(C1715R.C1718id.vt_provisioned_switch);
        this.imsWfcProvisionedSwitch = (Switch) findViewById(C1715R.C1718id.wfc_provisioned_switch);
        this.eabProvisionedSwitch = (Switch) findViewById(C1715R.C1718id.eab_provisioned_switch);
        if (!ImsManager.isImsSupportedOnDevice(this.mPhone.getContext())) {
            this.imsVolteProvisionedSwitch.setVisibility(8);
            this.imsVtProvisionedSwitch.setVisibility(8);
            this.imsWfcProvisionedSwitch.setVisibility(8);
            this.eabProvisionedSwitch.setVisibility(8);
        }
        this.cbrsDataSwitch = (Switch) findViewById(C1715R.C1718id.cbrs_data_switch);
        this.cbrsDataSwitch.setVisibility(isCbrsSupported() ? 0 : 8);
        this.dsdsSwitch = (Switch) findViewById(C1715R.C1718id.dsds_switch);
        if (!isDsdsSupported() || dsdsModeOnly()) {
            this.dsdsSwitch.setVisibility(8);
        } else {
            this.dsdsSwitch.setVisibility(0);
            this.dsdsSwitch.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    RadioInfo.this.lambda$onCreate$0$RadioInfo(view);
                }
            });
            this.dsdsSwitch.setChecked(isDsdsEnabled());
        }
        this.radioPowerOnSwitch = (Switch) findViewById(C1715R.C1718id.radio_power);
        this.mDownlinkKbps = (TextView) findViewById(C1715R.C1718id.dl_kbps);
        this.mUplinkKbps = (TextView) findViewById(C1715R.C1718id.ul_kbps);
        updateBandwidths(0, 0);
        this.pingTestButton = (Button) findViewById(C1715R.C1718id.ping_test);
        this.pingTestButton.setOnClickListener(this.mPingButtonHandler);
        this.updateSmscButton = (Button) findViewById(C1715R.C1718id.update_smsc);
        this.updateSmscButton.setOnClickListener(this.mUpdateSmscButtonHandler);
        this.refreshSmscButton = (Button) findViewById(C1715R.C1718id.refresh_smsc);
        this.refreshSmscButton.setOnClickListener(this.mRefreshSmscButtonHandler);
        this.dnsCheckToggleButton = (Button) findViewById(C1715R.C1718id.dns_check_toggle);
        this.dnsCheckToggleButton.setOnClickListener(this.mDnsCheckButtonHandler);
        this.carrierProvisioningButton = (Button) findViewById(C1715R.C1718id.carrier_provisioning);
        this.carrierProvisioningButton.setOnClickListener(this.mCarrierProvisioningButtonHandler);
        this.triggercarrierProvisioningButton = (Button) findViewById(C1715R.C1718id.trigger_carrier_provisioning);
        this.triggercarrierProvisioningButton.setOnClickListener(this.mTriggerCarrierProvisioningButtonHandler);
        this.oemInfoButton = (Button) findViewById(C1715R.C1718id.oem_info);
        this.oemInfoButton.setOnClickListener(this.mOemInfoButtonHandler);
        if (getPackageManager().queryIntentActivities(new Intent("com.android.settings.OEM_RADIO_INFO"), 0).size() == 0) {
            this.oemInfoButton.setEnabled(false);
        }
        this.mCellInfoRefreshRateIndex = 0;
        this.mPreferredNetworkTypeResult = this.mPreferredNetworkLabels.length - 1;
        this.mSelectedPhoneIndex = 0;
        this.mPhone.getPreferredNetworkType(this.mHandler.obtainMessage(1000));
        restoreFromBundle(bundle);
    }

    public /* synthetic */ void lambda$onCreate$0$RadioInfo(View view) {
        if (this.mTelephonyManager.doesSwitchMultiSimConfigTriggerReboot()) {
            this.dsdsSwitch.toggle();
            showDsdsChangeDialog();
            return;
        }
        performDsdsSwitch();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        log("Started onResume");
        updateAllFields();
    }

    private void updateAllFields() {
        updateMessageWaiting();
        updateCallRedirect();
        updateDataState();
        updateDataStats2();
        updateRadioPowerState();
        updateImsProvisionedState();
        updateProperties();
        updateDnsCheckState();
        updateNetworkType();
        updateLocation(this.mCellLocationResult);
        updateCellInfo(this.mCellInfoResult);
        updateSubscriptionIds();
        this.mPingHostnameV4.setText(this.mPingHostnameResultV4);
        this.mPingHostnameV6.setText(this.mPingHostnameResultV6);
        this.mHttpClientTest.setText(this.mHttpClientTestResult);
        this.cellInfoRefreshRateSpinner.setOnItemSelectedListener(this.mCellInfoRefreshRateHandler);
        this.cellInfoRefreshRateSpinner.setSelection(this.mCellInfoRefreshRateIndex);
        this.preferredNetworkType.setSelection(this.mPreferredNetworkTypeResult, true);
        this.preferredNetworkType.setOnItemSelectedListener(this.mPreferredNetworkHandler);
        this.mSelectPhoneIndex.setSelection(this.mSelectedPhoneIndex, true);
        this.mSelectPhoneIndex.setOnItemSelectedListener(this.mSelectPhoneIndexHandler);
        this.radioPowerOnSwitch.setOnCheckedChangeListener(this.mRadioPowerOnChangeListener);
        this.imsVolteProvisionedSwitch.setOnCheckedChangeListener(this.mImsVolteCheckedChangeListener);
        this.imsVtProvisionedSwitch.setOnCheckedChangeListener(this.mImsVtCheckedChangeListener);
        this.imsWfcProvisionedSwitch.setOnCheckedChangeListener(this.mImsWfcCheckedChangeListener);
        this.eabProvisionedSwitch.setOnCheckedChangeListener(this.mEabCheckedChangeListener);
        if (isCbrsSupported()) {
            this.cbrsDataSwitch.setChecked(getCbrsDataState());
            this.cbrsDataSwitch.setOnCheckedChangeListener(this.mCbrsDataSwitchChangeListener);
        }
        unregisterPhoneStateListener();
        registerPhoneStateListener();
        this.mConnectivityManager.registerNetworkCallback(this.mDefaultNetworkRequest, this.mNetworkCallback, this.mHandler);
        this.smsc.clearFocus();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        log("onPause: unregister phone & data intents");
        this.mTelephonyManager.listen(this.mPhoneStateListener, 0);
        this.mTelephonyManager.setCellInfoListRate(Integer.MAX_VALUE);
        this.mConnectivityManager.unregisterNetworkCallback(this.mNetworkCallback);
    }

    private void restoreFromBundle(Bundle bundle) {
        if (bundle != null) {
            this.mPingHostnameResultV4 = bundle.getString("mPingHostnameResultV4", "");
            this.mPingHostnameResultV6 = bundle.getString("mPingHostnameResultV6", "");
            this.mHttpClientTestResult = bundle.getString("mHttpClientTestResult", "");
            this.mPingHostnameV4.setText(this.mPingHostnameResultV4);
            this.mPingHostnameV6.setText(this.mPingHostnameResultV6);
            this.mHttpClientTest.setText(this.mHttpClientTestResult);
            this.mPreferredNetworkTypeResult = bundle.getInt("mPreferredNetworkTypeResult", this.mPreferredNetworkLabels.length - 1);
            this.mSelectedPhoneIndex = bundle.getInt("mSelectedPhoneIndex", 0);
            this.mCellInfoRefreshRateIndex = bundle.getInt("mCellInfoRefreshRateIndex", 0);
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putString("mPingHostnameResultV4", this.mPingHostnameResultV4);
        bundle.putString("mPingHostnameResultV6", this.mPingHostnameResultV6);
        bundle.putString("mHttpClientTestResult", this.mHttpClientTestResult);
        bundle.putInt("mPreferredNetworkTypeResult", this.mPreferredNetworkTypeResult);
        bundle.putInt("mSelectedPhoneIndex", this.mSelectedPhoneIndex);
        bundle.putInt("mCellInfoRefreshRateIndex", this.mCellInfoRefreshRateIndex);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, C1715R.string.radio_info_band_mode_label).setOnMenuItemClickListener(this.mSelectBandCallback).setAlphabeticShortcut('b');
        menu.add(1, 1, 0, C1715R.string.radioInfo_menu_viewADN).setOnMenuItemClickListener(this.mViewADNCallback);
        menu.add(1, 2, 0, C1715R.string.radioInfo_menu_viewFDN).setOnMenuItemClickListener(this.mViewFDNCallback);
        menu.add(1, 3, 0, C1715R.string.radioInfo_menu_viewSDN).setOnMenuItemClickListener(this.mViewSDNCallback);
        if (ImsManager.isImsSupportedOnDevice(this.mPhone.getContext())) {
            menu.add(1, 4, 0, C1715R.string.radioInfo_menu_getIMS).setOnMenuItemClickListener(this.mGetImsStatus);
        }
        menu.add(1, 5, 0, C1715R.string.radio_info_data_connection_disable).setOnMenuItemClickListener(this.mToggleData);
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean z;
        MenuItem findItem = menu.findItem(5);
        int dataState = this.mTelephonyManager.getDataState();
        if (dataState == 0) {
            findItem.setTitle(C1715R.string.radio_info_data_connection_enable);
        } else if (dataState == 2 || dataState == 3) {
            findItem.setTitle(C1715R.string.radio_info_data_connection_disable);
        } else {
            z = false;
            findItem.setVisible(z);
            return true;
        }
        z = true;
        findItem.setVisible(z);
        return true;
    }

    private static String[] getPhoneIndexLabels(TelephonyManager telephonyManager) {
        int phoneCount = telephonyManager.getPhoneCount();
        String[] strArr = new String[phoneCount];
        for (int i = 0; i < phoneCount; i++) {
            strArr[i] = "Phone " + i;
        }
        return strArr;
    }

    private void unregisterPhoneStateListener() {
        this.mTelephonyManager.listen(this.mPhoneStateListener, 0);
        this.operatorName.setText("");
        this.gprsState.setText("");
        this.dataNetwork.setText("");
        this.voiceNetwork.setText("");
        this.sent.setText("");
        this.received.setText("");
        this.callState.setText("");
        this.mLocation.setText("");
        this.mMwiValue = false;
        this.mMwi.setText("");
        this.mCfiValue = false;
        this.mCfi.setText("");
        this.mCellInfo.setText("");
        this.dBm.setText("");
        this.gsmState.setText("");
        this.roamingState.setText("");
        this.mPhyChanConfig.setText("");
    }

    private void registerPhoneStateListener() {
        this.mPhoneStateListener = new RadioInfoPhoneStateListener();
        this.mTelephonyManager.listen(this.mPhoneStateListener, 1050109);
    }

    /* access modifiers changed from: private */
    public void updateDnsCheckState() {
        this.dnsCheckState.setText(this.mPhone.isDnsCheckDisabled() ? "0.0.0.0 allowed" : "0.0.0.0 not allowed");
    }

    /* access modifiers changed from: private */
    public void updateBandwidths(int i, int i2) {
        if (i < 0 || i == Integer.MAX_VALUE) {
            i = -1;
        }
        if (i2 < 0 || i2 == Integer.MAX_VALUE) {
            i2 = -1;
        }
        this.mDownlinkKbps.setText(String.format("%-5d", new Object[]{Integer.valueOf(i)}));
        this.mUplinkKbps.setText(String.format("%-5d", new Object[]{Integer.valueOf(i2)}));
    }

    /* access modifiers changed from: private */
    public final void updateSignalStrength(SignalStrength signalStrength) {
        Resources resources = getResources();
        int dbm = signalStrength.getDbm();
        int asuLevel = signalStrength.getAsuLevel();
        if (-1 == asuLevel) {
            asuLevel = 0;
        }
        TextView textView = this.dBm;
        textView.setText(String.valueOf(dbm) + " " + resources.getString(C1715R.string.radioInfo_display_dbm) + "   " + String.valueOf(asuLevel) + " " + resources.getString(C1715R.string.radioInfo_display_asu));
    }

    /* access modifiers changed from: private */
    public final void updateLocation(CellLocation cellLocation) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        Resources resources = getResources();
        String str6 = "unknown";
        if (cellLocation instanceof GsmCellLocation) {
            GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
            int lac = gsmCellLocation.getLac();
            int cid = gsmCellLocation.getCid();
            TextView textView = this.mLocation;
            StringBuilder sb = new StringBuilder();
            sb.append(resources.getString(C1715R.string.radioInfo_lac));
            sb.append(" = ");
            if (lac == -1) {
                str5 = str6;
            } else {
                str5 = Integer.toHexString(lac);
            }
            sb.append(str5);
            sb.append("   ");
            sb.append(resources.getString(C1715R.string.radioInfo_cid));
            sb.append(" = ");
            if (cid != -1) {
                str6 = Integer.toHexString(cid);
            }
            sb.append(str6);
            textView.setText(sb.toString());
        } else if (cellLocation instanceof CdmaCellLocation) {
            CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
            int baseStationId = cdmaCellLocation.getBaseStationId();
            int systemId = cdmaCellLocation.getSystemId();
            int networkId = cdmaCellLocation.getNetworkId();
            int baseStationLatitude = cdmaCellLocation.getBaseStationLatitude();
            int baseStationLongitude = cdmaCellLocation.getBaseStationLongitude();
            TextView textView2 = this.mLocation;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("BID = ");
            if (baseStationId == -1) {
                str = str6;
            } else {
                str = Integer.toHexString(baseStationId);
            }
            sb2.append(str);
            sb2.append("   SID = ");
            if (systemId == -1) {
                str2 = str6;
            } else {
                str2 = Integer.toHexString(systemId);
            }
            sb2.append(str2);
            sb2.append("   NID = ");
            if (networkId == -1) {
                str3 = str6;
            } else {
                str3 = Integer.toHexString(networkId);
            }
            sb2.append(str3);
            sb2.append("\nLAT = ");
            if (baseStationLatitude == -1) {
                str4 = str6;
            } else {
                str4 = Integer.toHexString(baseStationLatitude);
            }
            sb2.append(str4);
            sb2.append("   LONG = ");
            if (baseStationLongitude != -1) {
                str6 = Integer.toHexString(baseStationLongitude);
            }
            sb2.append(str6);
            textView2.setText(sb2.toString());
        } else {
            this.mLocation.setText(str6);
        }
    }

    private final String getCellInfoDisplayString(int i) {
        return i != Integer.MAX_VALUE ? Integer.toString(i) : "";
    }

    private final String getConnectionStatusString(CellInfo cellInfo) {
        String str = "";
        String str2 = cellInfo.isRegistered() ? "R" : str;
        int cellConnectionStatus = cellInfo.getCellConnectionStatus();
        String str3 = cellConnectionStatus != 0 ? cellConnectionStatus != 1 ? cellConnectionStatus != 2 ? str : "S" : "P" : "N";
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            str = "+";
        }
        return str2 + str + str3;
    }

    private final String buildCdmaInfoString(CellInfoCdma cellInfoCdma) {
        CellIdentityCdma cellIdentity = cellInfoCdma.getCellIdentity();
        CellSignalStrengthCdma cellSignalStrength = cellInfoCdma.getCellSignalStrength();
        return String.format("%-3.3s %-5.5s %-5.5s %-5.5s %-6.6s %-6.6s %-6.6s %-6.6s %-5.5s", new Object[]{getConnectionStatusString(cellInfoCdma), getCellInfoDisplayString(cellIdentity.getSystemId()), getCellInfoDisplayString(cellIdentity.getNetworkId()), getCellInfoDisplayString(cellIdentity.getBasestationId()), getCellInfoDisplayString(cellSignalStrength.getCdmaDbm()), getCellInfoDisplayString(cellSignalStrength.getCdmaEcio()), getCellInfoDisplayString(cellSignalStrength.getEvdoDbm()), getCellInfoDisplayString(cellSignalStrength.getEvdoEcio()), getCellInfoDisplayString(cellSignalStrength.getEvdoSnr())});
    }

    private final String buildGsmInfoString(CellInfoGsm cellInfoGsm) {
        CellIdentityGsm cellIdentity = cellInfoGsm.getCellIdentity();
        return String.format("%-3.3s %-3.3s %-3.3s %-5.5s %-5.5s %-6.6s %-4.4s %-4.4s\n", new Object[]{getConnectionStatusString(cellInfoGsm), getCellInfoDisplayString(cellIdentity.getMcc()), getCellInfoDisplayString(cellIdentity.getMnc()), getCellInfoDisplayString(cellIdentity.getLac()), getCellInfoDisplayString(cellIdentity.getCid()), getCellInfoDisplayString(cellIdentity.getArfcn()), getCellInfoDisplayString(cellIdentity.getBsic()), getCellInfoDisplayString(cellInfoGsm.getCellSignalStrength().getDbm())});
    }

    private final String buildLteInfoString(CellInfoLte cellInfoLte) {
        CellIdentityLte cellIdentity = cellInfoLte.getCellIdentity();
        CellSignalStrengthLte cellSignalStrength = cellInfoLte.getCellSignalStrength();
        return String.format("%-3.3s %-3.3s %-3.3s %-5.5s %-5.5s %-3.3s %-6.6s %-2.2s %-4.4s %-4.4s %-2.2s\n", new Object[]{getConnectionStatusString(cellInfoLte), getCellInfoDisplayString(cellIdentity.getMcc()), getCellInfoDisplayString(cellIdentity.getMnc()), getCellInfoDisplayString(cellIdentity.getTac()), getCellInfoDisplayString(cellIdentity.getCi()), getCellInfoDisplayString(cellIdentity.getPci()), getCellInfoDisplayString(cellIdentity.getEarfcn()), getCellInfoDisplayString(cellIdentity.getBandwidth()), getCellInfoDisplayString(cellSignalStrength.getDbm()), getCellInfoDisplayString(cellSignalStrength.getRsrq()), getCellInfoDisplayString(cellSignalStrength.getTimingAdvance())});
    }

    private final String buildWcdmaInfoString(CellInfoWcdma cellInfoWcdma) {
        CellIdentityWcdma cellIdentity = cellInfoWcdma.getCellIdentity();
        return String.format("%-3.3s %-3.3s %-3.3s %-5.5s %-5.5s %-6.6s %-3.3s %-4.4s\n", new Object[]{getConnectionStatusString(cellInfoWcdma), getCellInfoDisplayString(cellIdentity.getMcc()), getCellInfoDisplayString(cellIdentity.getMnc()), getCellInfoDisplayString(cellIdentity.getLac()), getCellInfoDisplayString(cellIdentity.getCid()), getCellInfoDisplayString(cellIdentity.getUarfcn()), getCellInfoDisplayString(cellIdentity.getPsc()), getCellInfoDisplayString(cellInfoWcdma.getCellSignalStrength().getDbm())});
    }

    private final String buildCellInfoString(List<CellInfo> list) {
        String str = new String();
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();
        StringBuilder sb4 = new StringBuilder();
        if (list != null) {
            for (CellInfo next : list) {
                if (next instanceof CellInfoLte) {
                    sb3.append(buildLteInfoString((CellInfoLte) next));
                } else if (next instanceof CellInfoWcdma) {
                    sb4.append(buildWcdmaInfoString((CellInfoWcdma) next));
                } else if (next instanceof CellInfoGsm) {
                    sb2.append(buildGsmInfoString((CellInfoGsm) next));
                } else if (next instanceof CellInfoCdma) {
                    sb.append(buildCdmaInfoString((CellInfoCdma) next));
                }
            }
            if (sb3.length() != 0) {
                str = (str + String.format("LTE\n%-3.3s %-3.3s %-3.3s %-5.5s %-5.5s %-3.3s %-6.6s %-2.2s %-4.4s %-4.4s %-2.2s\n", new Object[]{"SRV", "MCC", "MNC", "TAC", "CID", "PCI", "EARFCN", "BW", "RSRP", "RSRQ", "TA"})) + sb3.toString();
            }
            if (sb4.length() != 0) {
                str = (str + String.format("WCDMA\n%-3.3s %-3.3s %-3.3s %-5.5s %-5.5s %-6.6s %-3.3s %-4.4s\n", new Object[]{"SRV", "MCC", "MNC", "LAC", "CID", "UARFCN", "PSC", "RSCP"})) + sb4.toString();
            }
            if (sb2.length() != 0) {
                str = (str + String.format("GSM\n%-3.3s %-3.3s %-3.3s %-5.5s %-5.5s %-6.6s %-4.4s %-4.4s\n", new Object[]{"SRV", "MCC", "MNC", "LAC", "CID", "ARFCN", "BSIC", "RSSI"})) + sb2.toString();
            }
            if (sb.length() != 0) {
                str = (str + String.format("CDMA/EVDO\n%-3.3s %-5.5s %-5.5s %-5.5s %-6.6s %-6.6s %-6.6s %-6.6s %-5.5s\n", new Object[]{"SRV", "SID", "NID", "BSID", "C-RSSI", "C-ECIO", "E-RSSI", "E-ECIO", "E-SNR"})) + sb.toString();
            }
        } else {
            str = "unknown";
        }
        return str.toString();
    }

    /* access modifiers changed from: private */
    public final void updateCellInfo(List<CellInfo> list) {
        this.mCellInfo.setText(buildCellInfoString(list));
    }

    private final void updateSubscriptionIds() {
        this.mSubscriptionId.setText(Integer.toString(this.mPhone.getSubId()));
        this.mDds.setText(Integer.toString(SubscriptionManager.getDefaultDataSubscriptionId()));
    }

    /* access modifiers changed from: private */
    public final void updateMessageWaiting() {
        this.mMwi.setText(String.valueOf(this.mMwiValue));
    }

    /* access modifiers changed from: private */
    public final void updateCallRedirect() {
        this.mCfi.setText(String.valueOf(this.mCfiValue));
    }

    /* access modifiers changed from: private */
    public final void updateServiceState(ServiceState serviceState) {
        int state = serviceState.getState();
        Resources resources = getResources();
        String string = resources.getString(C1715R.string.radioInfo_unknown);
        if (state == 0) {
            string = resources.getString(C1715R.string.radioInfo_service_in);
        } else if (state == 1 || state == 2) {
            string = resources.getString(C1715R.string.radioInfo_service_emergency);
        } else if (state == 3) {
            string = resources.getString(C1715R.string.radioInfo_service_off);
        }
        this.gsmState.setText(string);
        if (serviceState.getRoaming()) {
            this.roamingState.setText(C1715R.string.radioInfo_roaming_in);
        } else {
            this.roamingState.setText(C1715R.string.radioInfo_roaming_not);
        }
        this.operatorName.setText(serviceState.getOperatorAlphaLong());
    }

    /* access modifiers changed from: private */
    public final void updatePhoneState(int i) {
        Resources resources = getResources();
        String string = resources.getString(C1715R.string.radioInfo_unknown);
        if (i == 0) {
            string = resources.getString(C1715R.string.radioInfo_phone_idle);
        } else if (i == 1) {
            string = resources.getString(C1715R.string.radioInfo_phone_ringing);
        } else if (i == 2) {
            string = resources.getString(C1715R.string.radioInfo_phone_offhook);
        }
        this.callState.setText(string);
    }

    /* access modifiers changed from: private */
    public final void updateDataState() {
        int dataState = this.mTelephonyManager.getDataState();
        Resources resources = getResources();
        String string = resources.getString(C1715R.string.radioInfo_unknown);
        if (dataState == 0) {
            string = resources.getString(C1715R.string.radioInfo_data_disconnected);
        } else if (dataState == 1) {
            string = resources.getString(C1715R.string.radioInfo_data_connecting);
        } else if (dataState == 2) {
            string = resources.getString(C1715R.string.radioInfo_data_connected);
        } else if (dataState == 3) {
            string = resources.getString(C1715R.string.radioInfo_data_suspended);
        }
        this.gprsState.setText(string);
    }

    /* access modifiers changed from: private */
    public final void updateNetworkType() {
        Phone phone = this.mPhone;
        if (phone != null) {
            phone.getServiceState();
            this.dataNetwork.setText(ServiceState.rilRadioTechnologyToString(this.mPhone.getServiceState().getRilDataRadioTechnology()));
            this.voiceNetwork.setText(ServiceState.rilRadioTechnologyToString(this.mPhone.getServiceState().getRilVoiceRadioTechnology()));
        }
    }

    private final void updateProperties() {
        Resources resources = getResources();
        String deviceId = this.mPhone.getDeviceId();
        if (deviceId == null) {
            deviceId = resources.getString(C1715R.string.radioInfo_unknown);
        }
        this.mDeviceId.setText(deviceId);
        String subscriberId = this.mPhone.getSubscriberId();
        if (subscriberId == null) {
            subscriberId = resources.getString(C1715R.string.radioInfo_unknown);
        }
        this.mSubscriberId.setText(subscriberId);
        String line1Number = this.mPhone.getLine1Number();
        if (line1Number == null) {
            line1Number = resources.getString(C1715R.string.radioInfo_unknown);
        }
        this.number.setText(line1Number);
    }

    /* access modifiers changed from: private */
    public final void updateDataStats2() {
        Resources resources = getResources();
        long mobileTxPackets = TrafficStats.getMobileTxPackets();
        long mobileRxPackets = TrafficStats.getMobileRxPackets();
        long mobileTxBytes = TrafficStats.getMobileTxBytes();
        long mobileRxBytes = TrafficStats.getMobileRxBytes();
        String string = resources.getString(C1715R.string.radioInfo_display_packets);
        String string2 = resources.getString(C1715R.string.radioInfo_display_bytes);
        TextView textView = this.sent;
        textView.setText(mobileTxPackets + " " + string + ", " + mobileTxBytes + " " + string2);
        TextView textView2 = this.received;
        textView2.setText(mobileRxPackets + " " + string + ", " + mobileRxBytes + " " + string2);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't wrap try/catch for region: R(2:6|7) */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r7.mPingHostnameResultV6 = "Fail: IOException";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
        r7.mPingHostnameResultV4 = "Fail: IOException";
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x004e */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x002a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void pingHostname() {
        /*
            r7 = this;
            java.lang.String r0 = "Pass"
            java.lang.String r1 = "Fail(%d)"
            java.lang.String r2 = "Fail: IOException"
            r3 = 0
            r4 = 1
            java.lang.Runtime r5 = java.lang.Runtime.getRuntime()     // Catch:{ IOException -> 0x002a }
            java.lang.String r6 = "ping -c 1 www.google.com"
            java.lang.Process r5 = r5.exec(r6)     // Catch:{ IOException -> 0x002a }
            int r5 = r5.waitFor()     // Catch:{ IOException -> 0x002a }
            if (r5 != 0) goto L_0x001b
            r7.mPingHostnameResultV4 = r0     // Catch:{ IOException -> 0x002a }
            goto L_0x002c
        L_0x001b:
            java.lang.Object[] r6 = new java.lang.Object[r4]     // Catch:{ IOException -> 0x002a }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ IOException -> 0x002a }
            r6[r3] = r5     // Catch:{ IOException -> 0x002a }
            java.lang.String r5 = java.lang.String.format(r1, r6)     // Catch:{ IOException -> 0x002a }
            r7.mPingHostnameResultV4 = r5     // Catch:{ IOException -> 0x002a }
            goto L_0x002c
        L_0x002a:
            r7.mPingHostnameResultV4 = r2     // Catch:{ InterruptedException -> 0x0051 }
        L_0x002c:
            java.lang.Runtime r5 = java.lang.Runtime.getRuntime()     // Catch:{ IOException -> 0x004e }
            java.lang.String r6 = "ping6 -c 1 www.google.com"
            java.lang.Process r5 = r5.exec(r6)     // Catch:{ IOException -> 0x004e }
            int r5 = r5.waitFor()     // Catch:{ IOException -> 0x004e }
            if (r5 != 0) goto L_0x003f
            r7.mPingHostnameResultV6 = r0     // Catch:{ IOException -> 0x004e }
            goto L_0x0057
        L_0x003f:
            java.lang.Object[] r0 = new java.lang.Object[r4]     // Catch:{ IOException -> 0x004e }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r5)     // Catch:{ IOException -> 0x004e }
            r0[r3] = r4     // Catch:{ IOException -> 0x004e }
            java.lang.String r0 = java.lang.String.format(r1, r0)     // Catch:{ IOException -> 0x004e }
            r7.mPingHostnameResultV6 = r0     // Catch:{ IOException -> 0x004e }
            goto L_0x0057
        L_0x004e:
            r7.mPingHostnameResultV6 = r2     // Catch:{ InterruptedException -> 0x0051 }
            goto L_0x0057
        L_0x0051:
            java.lang.String r0 = "Fail: InterruptedException"
            r7.mPingHostnameResultV6 = r0
            r7.mPingHostnameResultV4 = r0
        L_0x0057:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.RadioInfo.pingHostname():void");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void httpClientTest() {
        /*
            r3 = this;
            r0 = 0
            java.net.URL r1 = new java.net.URL     // Catch:{ IOException -> 0x003f }
            java.lang.String r2 = "https://www.google.com"
            r1.<init>(r2)     // Catch:{ IOException -> 0x003f }
            java.net.URLConnection r1 = r1.openConnection()     // Catch:{ IOException -> 0x003f }
            java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch:{ IOException -> 0x003f }
            int r0 = r1.getResponseCode()     // Catch:{ IOException -> 0x003a, all -> 0x0038 }
            r2 = 200(0xc8, float:2.8E-43)
            if (r0 != r2) goto L_0x001b
            java.lang.String r0 = "Pass"
            r3.mHttpClientTestResult = r0     // Catch:{ IOException -> 0x003a, all -> 0x0038 }
            goto L_0x0032
        L_0x001b:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x003a, all -> 0x0038 }
            r0.<init>()     // Catch:{ IOException -> 0x003a, all -> 0x0038 }
            java.lang.String r2 = "Fail: Code: "
            r0.append(r2)     // Catch:{ IOException -> 0x003a, all -> 0x0038 }
            java.lang.String r2 = r1.getResponseMessage()     // Catch:{ IOException -> 0x003a, all -> 0x0038 }
            r0.append(r2)     // Catch:{ IOException -> 0x003a, all -> 0x0038 }
            java.lang.String r0 = r0.toString()     // Catch:{ IOException -> 0x003a, all -> 0x0038 }
            r3.mHttpClientTestResult = r0     // Catch:{ IOException -> 0x003a, all -> 0x0038 }
        L_0x0032:
            if (r1 == 0) goto L_0x0048
            r1.disconnect()
            goto L_0x0048
        L_0x0038:
            r3 = move-exception
            goto L_0x0049
        L_0x003a:
            r0 = r1
            goto L_0x003f
        L_0x003c:
            r3 = move-exception
            r1 = r0
            goto L_0x0049
        L_0x003f:
            java.lang.String r1 = "Fail: IOException"
            r3.mHttpClientTestResult = r1     // Catch:{ all -> 0x003c }
            if (r0 == 0) goto L_0x0048
            r0.disconnect()
        L_0x0048:
            return
        L_0x0049:
            if (r1 == 0) goto L_0x004e
            r1.disconnect()
        L_0x004e:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.RadioInfo.httpClientTest():void");
    }

    /* access modifiers changed from: private */
    public void refreshSmsc() {
        this.mPhone.getSmscAddress(this.mHandler.obtainMessage(1005));
    }

    /* access modifiers changed from: private */
    public final void updateAllCellInfo() {
        this.mCellInfo.setText("");
        this.mLocation.setText("");
        final C04333 r0 = new Runnable() {
            public void run() {
                RadioInfo radioInfo = RadioInfo.this;
                radioInfo.updateLocation(radioInfo.mCellLocationResult);
                RadioInfo radioInfo2 = RadioInfo.this;
                radioInfo2.updateCellInfo(radioInfo2.mCellInfoResult);
            }
        };
        new Thread() {
            public void run() {
                RadioInfo radioInfo = RadioInfo.this;
                List unused = radioInfo.mCellInfoResult = radioInfo.mTelephonyManager.getAllCellInfo();
                RadioInfo radioInfo2 = RadioInfo.this;
                CellLocation unused2 = radioInfo2.mCellLocationResult = radioInfo2.mTelephonyManager.getCellLocation();
                RadioInfo.this.mHandler.post(r0);
            }
        }.start();
    }

    /* access modifiers changed from: private */
    public final void updatePingState() {
        this.mPingHostnameResultV4 = getResources().getString(C1715R.string.radioInfo_unknown);
        this.mPingHostnameResultV6 = getResources().getString(C1715R.string.radioInfo_unknown);
        this.mHttpClientTestResult = getResources().getString(C1715R.string.radioInfo_unknown);
        this.mPingHostnameV4.setText(this.mPingHostnameResultV4);
        this.mPingHostnameV6.setText(this.mPingHostnameResultV6);
        this.mHttpClientTest.setText(this.mHttpClientTestResult);
        final C04385 r0 = new Runnable() {
            public void run() {
                RadioInfo.this.mPingHostnameV4.setText(RadioInfo.this.mPingHostnameResultV4);
                RadioInfo.this.mPingHostnameV6.setText(RadioInfo.this.mPingHostnameResultV6);
                RadioInfo.this.mHttpClientTest.setText(RadioInfo.this.mHttpClientTestResult);
            }
        };
        new Thread() {
            public void run() {
                RadioInfo.this.pingHostname();
                RadioInfo.this.mHandler.post(r0);
            }
        }.start();
        new Thread() {
            public void run() {
                RadioInfo.this.httpClientTest();
                RadioInfo.this.mHandler.post(r0);
            }
        }.start();
    }

    /* access modifiers changed from: private */
    public boolean isRadioOn() {
        return this.mPhone.getServiceState().getState() != 3;
    }

    /* access modifiers changed from: private */
    public void updateRadioPowerState() {
        this.radioPowerOnSwitch.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
        this.radioPowerOnSwitch.setChecked(isRadioOn());
        this.radioPowerOnSwitch.setOnCheckedChangeListener(this.mRadioPowerOnChangeListener);
    }

    /* access modifiers changed from: package-private */
    public void setImsVolteProvisionedState(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("setImsVolteProvisioned state: ");
        sb.append(z ? "on" : "off");
        Log.d("RadioInfo", sb.toString());
        setImsConfigProvisionedState(10, z);
    }

    /* access modifiers changed from: package-private */
    public void setImsVtProvisionedState(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("setImsVtProvisioned() state: ");
        sb.append(z ? "on" : "off");
        Log.d("RadioInfo", sb.toString());
        setImsConfigProvisionedState(11, z);
    }

    /* access modifiers changed from: package-private */
    public void setImsWfcProvisionedState(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("setImsWfcProvisioned() state: ");
        sb.append(z ? "on" : "off");
        Log.d("RadioInfo", sb.toString());
        setImsConfigProvisionedState(28, z);
    }

    /* access modifiers changed from: package-private */
    public void setEabProvisionedState(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("setEabProvisioned() state: ");
        sb.append(z ? "on" : "off");
        Log.d("RadioInfo", sb.toString());
        setImsConfigProvisionedState(25, z);
    }

    /* access modifiers changed from: package-private */
    public void setImsConfigProvisionedState(final int i, final boolean z) {
        if (this.mPhone != null && this.mImsManager != null) {
            QueuedWork.queue(new Runnable() {
                public void run() {
                    try {
                        RadioInfo.this.mImsManager.getConfigInterface().setProvisionedValue(i, z ? 1 : 0);
                    } catch (ImsException e) {
                        Log.e("RadioInfo", "setImsConfigProvisioned() exception:", e);
                    }
                }
            }, false);
        }
    }

    private boolean isImsVolteProvisioned() {
        Phone phone = this.mPhone;
        if (phone == null || this.mImsManager == null || !ImsManager.isVolteEnabledByPlatform(phone.getContext()) || !ImsManager.isVolteProvisionedOnDevice(this.mPhone.getContext())) {
            return false;
        }
        return true;
    }

    private boolean isImsVtProvisioned() {
        Phone phone = this.mPhone;
        if (phone == null || this.mImsManager == null || !ImsManager.isVtEnabledByPlatform(phone.getContext()) || !ImsManager.isVtProvisionedOnDevice(this.mPhone.getContext())) {
            return false;
        }
        return true;
    }

    private boolean isImsWfcProvisioned() {
        Phone phone = this.mPhone;
        if (phone == null || this.mImsManager == null || !ImsManager.isWfcEnabledByPlatform(phone.getContext()) || !ImsManager.isWfcProvisionedOnDevice(this.mPhone.getContext())) {
            return false;
        }
        return true;
    }

    private boolean isEabProvisioned() {
        return isFeatureProvisioned(25, false);
    }

    private boolean isFeatureProvisioned(int i, boolean z) {
        ImsManager imsManager = this.mImsManager;
        if (imsManager != null) {
            try {
                ImsConfig configInterface = imsManager.getConfigInterface();
                if (configInterface != null) {
                    z = true;
                    if (configInterface.getProvisionedValue(i) != 1) {
                        z = false;
                    }
                }
            } catch (ImsException e) {
                Log.e("RadioInfo", "isFeatureProvisioned() exception:", e);
            }
        }
        log("isFeatureProvisioned() featureId=" + i + " provisioned=" + z);
        return z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:1:0x0002, code lost:
        r1 = (android.telephony.CarrierConfigManager) r1.getSystemService("carrier_config");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean isEabEnabledByPlatform(android.content.Context r1) {
        /*
            if (r1 == 0) goto L_0x001a
            java.lang.String r0 = "carrier_config"
            java.lang.Object r1 = r1.getSystemService(r0)
            android.telephony.CarrierConfigManager r1 = (android.telephony.CarrierConfigManager) r1
            if (r1 == 0) goto L_0x001a
            android.os.PersistableBundle r1 = r1.getConfig()
            java.lang.String r0 = "use_rcs_presence_bool"
            boolean r1 = r1.getBoolean(r0)
            if (r1 == 0) goto L_0x001a
            r1 = 1
            return r1
        L_0x001a:
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.RadioInfo.isEabEnabledByPlatform(android.content.Context):boolean");
    }

    /* access modifiers changed from: private */
    public void updateImsProvisionedState() {
        if (ImsManager.isImsSupportedOnDevice(this.mPhone.getContext())) {
            log("updateImsProvisionedState isImsVolteProvisioned()=" + isImsVolteProvisioned());
            this.imsVolteProvisionedSwitch.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
            this.imsVolteProvisionedSwitch.setChecked(isImsVolteProvisioned());
            this.imsVolteProvisionedSwitch.setOnCheckedChangeListener(this.mImsVolteCheckedChangeListener);
            boolean z = true;
            this.imsVolteProvisionedSwitch.setEnabled(!Build.IS_USER && ImsManager.isVolteEnabledByPlatform(this.mPhone.getContext()));
            this.imsVtProvisionedSwitch.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
            this.imsVtProvisionedSwitch.setChecked(isImsVtProvisioned());
            this.imsVtProvisionedSwitch.setOnCheckedChangeListener(this.mImsVtCheckedChangeListener);
            this.imsVtProvisionedSwitch.setEnabled(!Build.IS_USER && ImsManager.isVtEnabledByPlatform(this.mPhone.getContext()));
            this.imsWfcProvisionedSwitch.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
            this.imsWfcProvisionedSwitch.setChecked(isImsWfcProvisioned());
            this.imsWfcProvisionedSwitch.setOnCheckedChangeListener(this.mImsWfcCheckedChangeListener);
            this.imsWfcProvisionedSwitch.setEnabled(!Build.IS_USER && ImsManager.isWfcEnabledByPlatform(this.mPhone.getContext()));
            this.eabProvisionedSwitch.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
            this.eabProvisionedSwitch.setChecked(isEabProvisioned());
            this.eabProvisionedSwitch.setOnCheckedChangeListener(this.mEabCheckedChangeListener);
            Switch switchR = this.eabProvisionedSwitch;
            if (Build.IS_USER || !isEabEnabledByPlatform(this.mPhone.getContext())) {
                z = false;
            }
            switchR.setEnabled(z);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isCbrsSupported() {
        return getResources().getBoolean(17891395);
    }

    /* access modifiers changed from: package-private */
    public void updateCbrsDataState(final boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("setCbrsDataSwitchState() state:");
        sb.append(z ? "on" : "off");
        Log.d("RadioInfo", sb.toString());
        if (this.mTelephonyManager != null) {
            QueuedWork.queue(new Runnable() {
                public void run() {
                    RadioInfo.this.mTelephonyManager.setOpportunisticNetworkState(z);
                    RadioInfo.this.cbrsDataSwitch.setChecked(RadioInfo.this.getCbrsDataState());
                }
            }, false);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean getCbrsDataState() {
        TelephonyManager telephonyManager = this.mTelephonyManager;
        boolean isOpportunisticNetworkEnabled = telephonyManager != null ? telephonyManager.isOpportunisticNetworkEnabled() : false;
        StringBuilder sb = new StringBuilder();
        sb.append("getCbrsDataState() state:");
        sb.append(isOpportunisticNetworkEnabled ? "on" : "off");
        Log.d("RadioInfo", sb.toString());
        return isOpportunisticNetworkEnabled;
    }

    private void showDsdsChangeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle((int) C1715R.string.dsds_dialog_title);
        builder.setMessage((int) C1715R.string.dsds_dialog_message);
        builder.setPositiveButton((int) C1715R.string.dsds_dialog_confirm, this.mOnDsdsDialogConfirmedListener);
        builder.setNegativeButton((int) C1715R.string.dsds_dialog_cancel, this.mOnDsdsDialogConfirmedListener);
        builder.create().show();
    }

    private static boolean isDsdsSupported() {
        return TelephonyManager.getDefault().isMultiSimSupported() == 0;
    }

    private static boolean isDsdsEnabled() {
        return TelephonyManager.getDefault().getPhoneCount() > 1;
    }

    /* access modifiers changed from: private */
    public void performDsdsSwitch() {
        this.mTelephonyManager.switchMultiSimConfig(this.dsdsSwitch.isChecked() ? 2 : 1);
    }

    private boolean dsdsModeOnly() {
        String str = SystemProperties.get("ro.boot.hardware.dsds");
        return !TextUtils.isEmpty(str) && Integer.parseInt(str) == 1;
    }
}
