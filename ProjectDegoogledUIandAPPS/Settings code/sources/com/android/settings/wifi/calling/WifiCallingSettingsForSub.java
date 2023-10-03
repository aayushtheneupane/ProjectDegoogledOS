package com.android.settings.wifi.calling;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.telephony.CarrierConfigManager;
import android.telephony.PhoneStateListener;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.telephony.ims.ProvisioningManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.ims.ImsException;
import com.android.ims.ImsManager;
import com.android.internal.annotations.VisibleForTesting;
import com.android.settings.SettingsActivity;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.Utils;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.widget.SwitchBar;
import com.android.settings.wifi.tether.TetherService;
import com.havoc.config.center.C1715R;

public class WifiCallingSettingsForSub extends SettingsPreferenceFragment implements SwitchBar.OnSwitchChangeListener, Preference.OnPreferenceChangeListener {
    @VisibleForTesting
    static final int REQUEST_CHECK_WFC_DISCLAIMER = 2;
    @VisibleForTesting
    static final int REQUEST_CHECK_WFC_EMERGENCY_ADDRESS = 1;
    private ListWithEntrySummaryPreference mButtonWfcMode;
    private ListWithEntrySummaryPreference mButtonWfcRoamingMode;
    private boolean mEditableWfcMode = true;
    private boolean mEditableWfcRoamingMode = true;
    private TextView mEmptyView;
    /* access modifiers changed from: private */
    public ImsManager mImsManager;
    private IntentFilter mIntentFilter;
    private BroadcastReceiver mIntentReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.android.ims.REGISTRATION_ERROR")) {
                setResultCode(0);
                WifiCallingSettingsForSub.this.showAlert(intent);
            }
        }
    };
    private final PhoneStateListener mPhoneStateListener = new PhoneStateListener() {
        public void onCallStateChanged(int i, String str) {
            boolean z;
            boolean z2;
            PersistableBundle configForSubId;
            SettingsActivity settingsActivity = (SettingsActivity) WifiCallingSettingsForSub.this.getActivity();
            boolean isNonTtyOrTtyOnVolteEnabled = WifiCallingSettingsForSub.this.mImsManager.isNonTtyOrTtyOnVolteEnabled();
            boolean z3 = true;
            boolean z4 = WifiCallingSettingsForSub.this.mSwitchBar.isChecked() && isNonTtyOrTtyOnVolteEnabled;
            WifiCallingSettingsForSub.this.mSwitchBar.setEnabled(i == 0 && isNonTtyOrTtyOnVolteEnabled);
            CarrierConfigManager carrierConfigManager = (CarrierConfigManager) settingsActivity.getSystemService("carrier_config");
            if (carrierConfigManager == null || (configForSubId = carrierConfigManager.getConfigForSubId(WifiCallingSettingsForSub.this.mSubId)) == null) {
                z2 = true;
                z = false;
            } else {
                z2 = configForSubId.getBoolean("editable_wfc_mode_bool");
                z = configForSubId.getBoolean("editable_wfc_roaming_mode_bool");
            }
            Preference findPreference = WifiCallingSettingsForSub.this.getPreferenceScreen().findPreference("wifi_calling_mode");
            if (findPreference != null) {
                findPreference.setEnabled(z4 && z2 && i == 0);
            }
            Preference findPreference2 = WifiCallingSettingsForSub.this.getPreferenceScreen().findPreference("wifi_calling_roaming_mode");
            if (findPreference2 != null) {
                if (!z4 || !z || i != 0) {
                    z3 = false;
                }
                findPreference2.setEnabled(z3);
            }
        }
    };
    private final ProvisioningManager.Callback mProvisioningCallback = new ProvisioningManager.Callback() {
        public void onProvisioningIntChanged(int i, int i2) {
            if (i == 28 || i == 10) {
                WifiCallingSettingsForSub.this.updateBody();
            }
        }
    };
    /* access modifiers changed from: private */
    public int mSubId = -1;
    private Switch mSwitch;
    /* access modifiers changed from: private */
    public SwitchBar mSwitchBar;
    private TelephonyManager mTelephonyManager;
    private Preference mUpdateAddress;
    private final Preference.OnPreferenceClickListener mUpdateAddressListener = new Preference.OnPreferenceClickListener() {
        public final boolean onPreferenceClick(Preference preference) {
            return WifiCallingSettingsForSub.this.lambda$new$0$WifiCallingSettingsForSub(preference);
        }
    };
    private boolean mUseWfcHomeModeForRoaming = false;
    private boolean mValidListener = false;

    public int getHelpResource() {
        return 0;
    }

    public int getMetricsCategory() {
        return 1230;
    }

    public /* synthetic */ boolean lambda$new$0$WifiCallingSettingsForSub(Preference preference) {
        Intent carrierActivityIntent = getCarrierActivityIntent();
        if (carrierActivityIntent != null) {
            carrierActivityIntent.putExtra("EXTRA_LAUNCH_CARRIER_APP", 1);
            startActivity(carrierActivityIntent);
        }
        return true;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mEmptyView = (TextView) getView().findViewById(16908292);
        setEmptyView(this.mEmptyView);
        Resources resourcesForSubId = getResourcesForSubId();
        this.mEmptyView.setText(resourcesForSubId.getString(C1715R.string.wifi_calling_off_explanation, new Object[]{resourcesForSubId.getString(C1715R.string.wifi_calling_off_explanation_2)}));
        this.mSwitchBar = (SwitchBar) getView().findViewById(C1715R.C1718id.switch_bar);
        this.mSwitchBar.show();
        this.mSwitch = this.mSwitchBar.getSwitch();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mSwitchBar.hide();
    }

    /* access modifiers changed from: private */
    public void showAlert(Intent intent) {
        FragmentActivity activity = getActivity();
        CharSequence charSequenceExtra = intent.getCharSequenceExtra("alertTitle");
        CharSequence charSequenceExtra2 = intent.getCharSequenceExtra("alertMessage");
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(charSequenceExtra2);
        builder.setTitle(charSequenceExtra);
        builder.setIcon(17301543);
        builder.setPositiveButton(17039370, (DialogInterface.OnClickListener) null);
        builder.create().show();
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public ImsManager getImsManager() {
        return ImsManager.getInstance(getActivity(), SubscriptionManager.getPhoneId(this.mSubId));
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(C1715R.xml.wifi_calling_settings);
        if (getArguments() != null && getArguments().containsKey(TetherService.EXTRA_SUBID)) {
            this.mSubId = getArguments().getInt(TetherService.EXTRA_SUBID);
        } else if (bundle != null) {
            this.mSubId = bundle.getInt(TetherService.EXTRA_SUBID, -1);
        }
        this.mImsManager = getImsManager();
        this.mTelephonyManager = ((TelephonyManager) getSystemService("phone")).createForSubscriptionId(this.mSubId);
        this.mButtonWfcMode = (ListWithEntrySummaryPreference) findPreference("wifi_calling_mode");
        this.mButtonWfcMode.setOnPreferenceChangeListener(this);
        this.mButtonWfcRoamingMode = (ListWithEntrySummaryPreference) findPreference("wifi_calling_roaming_mode");
        this.mButtonWfcRoamingMode.setOnPreferenceChangeListener(this);
        this.mUpdateAddress = findPreference("emergency_address_key");
        this.mUpdateAddress.setOnPreferenceClickListener(this.mUpdateAddressListener);
        this.mIntentFilter = new IntentFilter();
        this.mIntentFilter.addAction("com.android.ims.REGISTRATION_ERROR");
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putInt(TetherService.EXTRA_SUBID, this.mSubId);
        super.onSaveInstanceState(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C1715R.layout.wifi_calling_settings_preferences, viewGroup, false);
        ViewGroup viewGroup2 = (ViewGroup) inflate.findViewById(C1715R.C1718id.prefs_container);
        Utils.prepareCustomPreferencesList(viewGroup, inflate, viewGroup2, false);
        viewGroup2.addView(super.onCreateView(layoutInflater, viewGroup2, bundle));
        return inflate;
    }

    /* access modifiers changed from: private */
    public void updateBody() {
        boolean z;
        PersistableBundle configForSubId;
        if (!this.mImsManager.isWfcProvisionedOnDevice()) {
            finish();
            return;
        }
        CarrierConfigManager carrierConfigManager = (CarrierConfigManager) getSystemService("carrier_config");
        if (carrierConfigManager == null || (configForSubId = carrierConfigManager.getConfigForSubId(this.mSubId)) == null) {
            z = true;
        } else {
            this.mEditableWfcMode = configForSubId.getBoolean("editable_wfc_mode_bool");
            this.mEditableWfcRoamingMode = configForSubId.getBoolean("editable_wfc_roaming_mode_bool");
            this.mUseWfcHomeModeForRoaming = configForSubId.getBoolean("use_wfc_home_network_mode_in_roaming_network_bool", false);
            z = configForSubId.getBoolean("carrier_wfc_supports_wifi_only_bool", true);
        }
        if (!z) {
            this.mButtonWfcMode.setEntries((int) C1715R.array.wifi_calling_mode_choices_without_wifi_only);
            this.mButtonWfcMode.setEntryValues((int) C1715R.array.wifi_calling_mode_values_without_wifi_only);
            this.mButtonWfcMode.setEntrySummaries(C1715R.array.wifi_calling_mode_summaries_without_wifi_only);
            this.mButtonWfcRoamingMode.setEntries((int) C1715R.array.wifi_calling_mode_choices_v2_without_wifi_only);
            this.mButtonWfcRoamingMode.setEntryValues((int) C1715R.array.wifi_calling_mode_values_without_wifi_only);
            this.mButtonWfcRoamingMode.setEntrySummaries(C1715R.array.wifi_calling_mode_summaries_without_wifi_only);
        }
        boolean z2 = this.mImsManager.isWfcEnabledByUser() && this.mImsManager.isNonTtyOrTtyOnVolteEnabled();
        this.mSwitch.setChecked(z2);
        int wfcMode = this.mImsManager.getWfcMode(false);
        int wfcMode2 = this.mImsManager.getWfcMode(true);
        this.mButtonWfcMode.setValue(Integer.toString(wfcMode));
        this.mButtonWfcRoamingMode.setValue(Integer.toString(wfcMode2));
        updateButtonWfcMode(z2, wfcMode, wfcMode2);
    }

    public void onResume() {
        super.onResume();
        FragmentActivity activity = getActivity();
        updateBody();
        if (this.mImsManager.isWfcEnabledByPlatform()) {
            this.mTelephonyManager.listen(this.mPhoneStateListener, 32);
            this.mSwitchBar.addOnSwitchChangeListener(this);
            this.mValidListener = true;
        }
        activity.registerReceiver(this.mIntentReceiver, this.mIntentFilter);
        Intent intent = getActivity().getIntent();
        if (intent.getBooleanExtra("alertShow", false)) {
            showAlert(intent);
        }
        try {
            this.mImsManager.getConfigInterface().addConfigCallback(this.mProvisioningCallback);
        } catch (ImsException unused) {
            Log.w("WifiCallingForSub", "onResume: Unable to register callback for provisioning changes.");
        }
    }

    public void onPause() {
        super.onPause();
        FragmentActivity activity = getActivity();
        if (this.mValidListener) {
            this.mValidListener = false;
            ((TelephonyManager) getSystemService("phone")).listen(this.mPhoneStateListener, 0);
            this.mSwitchBar.removeOnSwitchChangeListener(this);
        }
        activity.unregisterReceiver(this.mIntentReceiver);
        try {
            this.mImsManager.getConfigInterface().removeConfigCallback(this.mProvisioningCallback.getBinder());
        } catch (ImsException unused) {
            Log.w("WifiCallingForSub", "onPause: Unable to remove callback for provisioning changes");
        }
    }

    public void onSwitchChanged(Switch switchR, boolean z) {
        Log.d("WifiCallingForSub", "onSwitchChanged(" + z + ")");
        if (!z) {
            updateWfcMode(false);
            return;
        }
        FragmentActivity activity = getActivity();
        Bundle bundle = new Bundle();
        bundle.putInt("EXTRA_SUB_ID", this.mSubId);
        new SubSettingLauncher(activity).setDestination(WifiCallingDisclaimerFragment.class.getName()).setArguments(bundle).setTitleRes(C1715R.string.wifi_calling_settings_title).setSourceMetricsCategory(getMetricsCategory()).setResultListener(this, 2).launch();
    }

    private Intent getCarrierActivityIntent() {
        PersistableBundle configForSubId;
        ComponentName unflattenFromString;
        CarrierConfigManager carrierConfigManager = (CarrierConfigManager) getActivity().getSystemService(CarrierConfigManager.class);
        if (carrierConfigManager == null || (configForSubId = carrierConfigManager.getConfigForSubId(this.mSubId)) == null) {
            return null;
        }
        String string = configForSubId.getString("wfc_emergency_address_carrier_app_string");
        if (TextUtils.isEmpty(string) || (unflattenFromString = ComponentName.unflattenFromString(string)) == null) {
            return null;
        }
        Intent intent = new Intent();
        intent.setComponent(unflattenFromString);
        intent.putExtra("android.telephony.extra.SUBSCRIPTION_INDEX", this.mSubId);
        return intent;
    }

    private void updateWfcMode(boolean z) {
        Log.i("WifiCallingForSub", "updateWfcMode(" + z + ")");
        this.mImsManager.setWfcSetting(z);
        int wfcMode = this.mImsManager.getWfcMode(false);
        updateButtonWfcMode(z, wfcMode, this.mImsManager.getWfcMode(true));
        if (z) {
            this.mMetricsFeatureProvider.action((Context) getActivity(), getMetricsCategory(), wfcMode);
        } else {
            this.mMetricsFeatureProvider.action((Context) getActivity(), getMetricsCategory(), -1);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        getActivity();
        Log.d("WifiCallingForSub", "WFC activity request = " + i + " result = " + i2);
        if (i != 1) {
            if (i != 2) {
                Log.e("WifiCallingForSub", "Unexpected request: " + i);
            } else if (i2 == -1) {
                Intent carrierActivityIntent = getCarrierActivityIntent();
                if (carrierActivityIntent != null) {
                    carrierActivityIntent.putExtra("EXTRA_LAUNCH_CARRIER_APP", 0);
                    startActivityForResult(carrierActivityIntent, 1);
                    return;
                }
                updateWfcMode(true);
            }
        } else if (i2 == -1) {
            updateWfcMode(true);
        }
    }

    private void updateButtonWfcMode(boolean z, int i, int i2) {
        this.mButtonWfcMode.setSummary(getWfcModeSummary(i));
        boolean z2 = true;
        this.mButtonWfcMode.setEnabled(z && this.mEditableWfcMode);
        this.mButtonWfcRoamingMode.setEnabled(z && this.mEditableWfcRoamingMode);
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        if (getCarrierActivityIntent() == null) {
            z2 = false;
        }
        if (z) {
            if (this.mEditableWfcMode) {
                preferenceScreen.addPreference(this.mButtonWfcMode);
            } else {
                preferenceScreen.removePreference(this.mButtonWfcMode);
            }
            if (!this.mEditableWfcRoamingMode || this.mUseWfcHomeModeForRoaming) {
                preferenceScreen.removePreference(this.mButtonWfcRoamingMode);
            } else {
                preferenceScreen.addPreference(this.mButtonWfcRoamingMode);
            }
            if (z2) {
                preferenceScreen.addPreference(this.mUpdateAddress);
            } else {
                preferenceScreen.removePreference(this.mUpdateAddress);
            }
        } else {
            preferenceScreen.removePreference(this.mButtonWfcMode);
            preferenceScreen.removePreference(this.mButtonWfcRoamingMode);
            preferenceScreen.removePreference(this.mUpdateAddress);
        }
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference == this.mButtonWfcMode) {
            Log.d("WifiCallingForSub", "onPreferenceChange mButtonWfcMode " + obj);
            String str = (String) obj;
            this.mButtonWfcMode.setValue(str);
            int intValue = Integer.valueOf(str).intValue();
            if (intValue != this.mImsManager.getWfcMode(false)) {
                this.mImsManager.setWfcMode(intValue, false);
                this.mButtonWfcMode.setSummary(getWfcModeSummary(intValue));
                this.mMetricsFeatureProvider.action((Context) getActivity(), getMetricsCategory(), intValue);
                if (this.mUseWfcHomeModeForRoaming) {
                    this.mImsManager.setWfcMode(intValue, true);
                }
            }
        } else {
            ListWithEntrySummaryPreference listWithEntrySummaryPreference = this.mButtonWfcRoamingMode;
            if (preference == listWithEntrySummaryPreference) {
                String str2 = (String) obj;
                listWithEntrySummaryPreference.setValue(str2);
                int intValue2 = Integer.valueOf(str2).intValue();
                if (intValue2 != this.mImsManager.getWfcMode(true)) {
                    this.mImsManager.setWfcMode(intValue2, true);
                    this.mMetricsFeatureProvider.action((Context) getActivity(), getMetricsCategory(), intValue2);
                }
            }
        }
        return true;
    }

    private int getWfcModeSummary(int i) {
        if (this.mImsManager.isWfcEnabledByUser()) {
            if (i == 0) {
                return 17041329;
            }
            if (i == 1) {
                return 17041327;
            }
            if (i == 2) {
                return 17041330;
            }
            Log.e("WifiCallingForSub", "Unexpected WFC mode value: " + i);
        }
        return 17041368;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public Resources getResourcesForSubId() {
        return SubscriptionManager.getResourcesForSubId(getContext(), this.mSubId, false);
    }
}
