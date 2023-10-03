package com.android.settings.network.telephony;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.Looper;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.telephony.CarrierConfigManager;
import android.telephony.RadioAccessFamily;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.internal.telephony.Phone;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;

public class EnabledNetworkModePreferenceController extends TelephonyBasePreferenceController implements Preference.OnPreferenceChangeListener, LifecycleObserver {
    private static final String LOG_TAG = "EnabledNetworkMode";
    private CarrierConfigManager mCarrierConfigManager;
    boolean mDisplay5gList = false;
    private boolean mIsGlobalCdma;
    /* access modifiers changed from: private */
    public Preference mPreference;
    private ContentObserver mPreferredNetworkModeObserver;
    boolean mShow4GForLTE;
    private TelephonyManager mTelephonyManager;

    public EnabledNetworkModePreferenceController(Context context, String str) {
        super(context, str);
        this.mCarrierConfigManager = (CarrierConfigManager) context.getSystemService(CarrierConfigManager.class);
        this.mPreferredNetworkModeObserver = new ContentObserver(new Handler(Looper.getMainLooper())) {
            public void onChange(boolean z) {
                if (EnabledNetworkModePreferenceController.this.mPreference != null) {
                    EnabledNetworkModePreferenceController enabledNetworkModePreferenceController = EnabledNetworkModePreferenceController.this;
                    enabledNetworkModePreferenceController.updateState(enabledNetworkModePreferenceController.mPreference);
                }
            }
        };
    }

    public int getAvailabilityStatus(int i) {
        boolean z;
        PersistableBundle configForSubId = this.mCarrierConfigManager.getConfigForSubId(i);
        TelephonyManager.from(this.mContext).createForSubscriptionId(i);
        if (i != -1 && configForSubId != null && !configForSubId.getBoolean("hide_carrier_network_settings_bool") && !configForSubId.getBoolean("hide_preferred_network_type_bool") && !configForSubId.getBoolean("world_phone_bool")) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return 0;
        }
        return 2;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        contentResolver.registerContentObserver(Settings.Global.getUriFor("preferred_network_mode" + this.mSubId), true, this.mPreferredNetworkModeObserver);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        this.mContext.getContentResolver().unregisterContentObserver(this.mPreferredNetworkModeObserver);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = preferenceScreen.findPreference(getPreferenceKey());
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        ListPreference listPreference = (ListPreference) preference;
        int preferredNetworkMode = getPreferredNetworkMode();
        updatePreferenceEntries(listPreference);
        updatePreferenceValueAndSummary(listPreference, preferredNetworkMode);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        int parseInt = Integer.parseInt((String) obj);
        if (!this.mTelephonyManager.setPreferredNetworkType(this.mSubId, parseInt)) {
            return false;
        }
        ContentResolver contentResolver = this.mContext.getContentResolver();
        Settings.Global.putInt(contentResolver, "preferred_network_mode" + this.mSubId, parseInt);
        updatePreferenceValueAndSummary((ListPreference) preference, parseInt);
        return true;
    }

    public void init(Lifecycle lifecycle, int i) {
        this.mSubId = i;
        PersistableBundle configForSubId = this.mCarrierConfigManager.getConfigForSubId(this.mSubId);
        this.mTelephonyManager = TelephonyManager.from(this.mContext).createForSubscriptionId(this.mSubId);
        boolean z = false;
        boolean z2 = true;
        if (!(this.mTelephonyManager.getLteOnCdmaMode() == 1) || !configForSubId.getBoolean("show_cdma_choices_bool")) {
            z2 = false;
        }
        this.mIsGlobalCdma = z2;
        if (configForSubId != null) {
            z = configForSubId.getBoolean("show_4g_for_lte_data_icon_bool");
        }
        this.mShow4GForLTE = z;
        this.mDisplay5gList = checkSupportedRadioBitmask(this.mTelephonyManager.getSupportedRadioAccessFamily(), 524288);
        lifecycle.addObserver(this);
    }

    private int getPreferredNetworkMode() {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        return Settings.Global.getInt(contentResolver, "preferred_network_mode" + this.mSubId, Phone.PREFERRED_NT_MODE);
    }

    private void updatePreferenceEntries(ListPreference listPreference) {
        int phoneType = this.mTelephonyManager.getPhoneType();
        PersistableBundle configForSubId = this.mCarrierConfigManager.getConfigForSubId(this.mSubId);
        boolean z = true;
        if (phoneType == 2) {
            int i = Settings.Global.getInt(this.mContext.getContentResolver(), "lte_service_forced" + this.mSubId, 0);
            if (this.mTelephonyManager.getLteOnCdmaMode() != 1) {
                z = false;
            }
            int i2 = Settings.Global.getInt(this.mContext.getContentResolver(), "preferred_network_mode" + this.mSubId, Phone.PREFERRED_NT_MODE);
            if (z) {
                if (i != 0) {
                    switch (i2) {
                        case 4:
                        case 5:
                        case 6:
                            listPreference.setEntries((int) C1715R.array.enabled_networks_cdma_no_lte_choices);
                            listPreference.setEntryValues((int) C1715R.array.enabled_networks_cdma_no_lte_values);
                            break;
                        case 7:
                        case 8:
                        case 10:
                        case 11:
                            listPreference.setEntries((int) C1715R.array.enabled_networks_cdma_only_lte_choices);
                            listPreference.setEntryValues((int) C1715R.array.enabled_networks_cdma_only_lte_values);
                            break;
                        default:
                            listPreference.setEntries((int) C1715R.array.enabled_networks_cdma_choices);
                            listPreference.setEntryValues((int) C1715R.array.enabled_networks_cdma_values);
                            break;
                    }
                } else {
                    listPreference.setEntries((int) C1715R.array.enabled_networks_cdma_choices);
                    listPreference.setEntryValues((int) C1715R.array.enabled_networks_cdma_values);
                }
            }
        } else if (phoneType == 1) {
            if (MobileNetworkUtils.isTdscdmaSupported(this.mContext, this.mSubId)) {
                listPreference.setEntries((int) C1715R.array.enabled_networks_tdscdma_choices);
                listPreference.setEntryValues((int) C1715R.array.enabled_networks_tdscdma_values);
            } else if (configForSubId != null && !configForSubId.getBoolean("prefer_2g_bool") && !configForSubId.getBoolean("lte_enabled_bool")) {
                listPreference.setEntries((int) C1715R.array.enabled_networks_except_gsm_lte_choices);
                listPreference.setEntryValues((int) C1715R.array.enabled_networks_except_gsm_lte_values);
            } else if (configForSubId != null && !configForSubId.getBoolean("prefer_2g_bool")) {
                listPreference.setEntries(this.mShow4GForLTE ? C1715R.array.enabled_networks_except_gsm_4g_choices : C1715R.array.enabled_networks_except_gsm_choices);
                listPreference.setEntryValues((int) C1715R.array.enabled_networks_except_gsm_values);
            } else if (configForSubId != null && !configForSubId.getBoolean("lte_enabled_bool")) {
                listPreference.setEntries((int) C1715R.array.enabled_networks_except_lte_choices);
                listPreference.setEntryValues((int) C1715R.array.enabled_networks_except_lte_values);
            } else if (this.mIsGlobalCdma) {
                listPreference.setEntries((int) C1715R.array.enabled_networks_cdma_choices);
                listPreference.setEntryValues((int) C1715R.array.enabled_networks_cdma_values);
            } else {
                listPreference.setEntries(this.mShow4GForLTE ? C1715R.array.enabled_networks_4g_choices : C1715R.array.enabled_networks_choices);
                listPreference.setEntryValues((int) C1715R.array.enabled_networks_values);
            }
        }
        if (MobileNetworkUtils.isWorldMode(this.mContext, this.mSubId)) {
            listPreference.setEntries((int) C1715R.array.preferred_network_mode_choices_world_mode);
            listPreference.setEntryValues((int) C1715R.array.preferred_network_mode_values_world_mode);
        }
        if (this.mDisplay5gList) {
            add5gListItem(listPreference);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean checkSupportedRadioBitmask(long j, long j2) {
        Log.d(LOG_TAG, "supportedRadioBitmask: " + j);
        return (j2 & j) > 0;
    }

    /* access modifiers changed from: package-private */
    public void add5gListItem(ListPreference listPreference) {
        CharSequence charSequence;
        CharSequence[] entries = listPreference.getEntries();
        CharSequence[] entryValues = listPreference.getEntryValues();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < entries.length; i++) {
            CharSequence charSequence2 = entries[i];
            CharSequence charSequence3 = entryValues[i];
            String str = "";
            if (this.mContext.getString(C1715R.string.network_lte).equals(charSequence2)) {
                charSequence2 = this.mContext.getString(C1715R.string.network_lte_pure);
                str = this.mContext.getString(C1715R.string.network_5G) + this.mContext.getString(C1715R.string.network_recommended);
                charSequence = transformLteEntryValueTo5gEntryValue(charSequence3);
            } else if (this.mContext.getString(C1715R.string.network_4G).equals(charSequence2)) {
                charSequence2 = this.mContext.getString(C1715R.string.network_4G_pure);
                str = this.mContext.getString(C1715R.string.network_5G) + this.mContext.getString(C1715R.string.network_recommended);
                charSequence = transformLteEntryValueTo5gEntryValue(charSequence3);
            } else {
                if (this.mContext.getString(C1715R.string.network_global).equals(charSequence2)) {
                    charSequence3 = Integer.toString(27);
                }
                charSequence = str;
            }
            if (!TextUtils.isEmpty(str)) {
                arrayList.add(str);
                arrayList2.add(charSequence);
            }
            arrayList.add(charSequence2);
            arrayList2.add(charSequence3);
        }
        listPreference.setEntries((CharSequence[]) arrayList.toArray(new CharSequence[arrayList.size()]));
        listPreference.setEntryValues((CharSequence[]) arrayList2.toArray(new CharSequence[arrayList2.size()]));
    }

    private CharSequence transformLteEntryValueTo5gEntryValue(CharSequence charSequence) {
        return Integer.toString(addNrToNetworkType(Integer.valueOf(charSequence.toString()).intValue()));
    }

    private int addNrToNetworkType(int i) {
        return RadioAccessFamily.getNetworkTypeFromRaf((int) (((long) RadioAccessFamily.getRafFromNetworkType(i)) | 524288));
    }

    private void updatePreferenceValueAndSummary(ListPreference listPreference, int i) {
        listPreference.setValue(Integer.toString(i));
        int i2 = C1715R.string.network_4G;
        int i3 = C1715R.string.network_lte_pure;
        switch (i) {
            case 0:
            case 2:
            case 3:
                if (!this.mIsGlobalCdma) {
                    listPreference.setValue(Integer.toString(0));
                    listPreference.setSummary((int) C1715R.string.network_3G);
                    return;
                }
                listPreference.setValue(Integer.toString(10));
                listPreference.setSummary((int) C1715R.string.network_global);
                return;
            case 1:
                if (!this.mIsGlobalCdma) {
                    listPreference.setValue(Integer.toString(1));
                    listPreference.setSummary((int) C1715R.string.network_2G);
                    return;
                }
                listPreference.setValue(Integer.toString(10));
                listPreference.setSummary((int) C1715R.string.network_global);
                return;
            case 4:
            case 6:
            case 7:
                listPreference.setValue(Integer.toString(4));
                listPreference.setSummary((int) C1715R.string.network_3G);
                return;
            case 5:
                listPreference.setValue(Integer.toString(5));
                listPreference.setSummary((int) C1715R.string.network_1x);
                return;
            case 8:
                if (MobileNetworkUtils.isWorldMode(this.mContext, this.mSubId)) {
                    listPreference.setSummary((int) C1715R.string.preferred_network_mode_lte_cdma_summary);
                    return;
                }
                listPreference.setValue(Integer.toString(8));
                if (!this.mDisplay5gList) {
                    i3 = C1715R.string.network_lte;
                }
                listPreference.setSummary(i3);
                return;
            case 9:
                if (MobileNetworkUtils.isWorldMode(this.mContext, this.mSubId)) {
                    listPreference.setSummary((int) C1715R.string.preferred_network_mode_lte_gsm_umts_summary);
                    return;
                }
                break;
            case 10:
            case 15:
            case 17:
            case 19:
            case 20:
            case 22:
                if (MobileNetworkUtils.isTdscdmaSupported(this.mContext, this.mSubId)) {
                    listPreference.setValue(Integer.toString(22));
                    if (!this.mDisplay5gList) {
                        i3 = C1715R.string.network_lte;
                    }
                    listPreference.setSummary(i3);
                    return;
                }
                listPreference.setValue(Integer.toString(10));
                if (this.mTelephonyManager.getPhoneType() == 2 || this.mIsGlobalCdma || MobileNetworkUtils.isWorldMode(this.mContext, this.mSubId)) {
                    listPreference.setSummary((int) C1715R.string.network_global);
                    return;
                } else if (this.mDisplay5gList) {
                    if (this.mShow4GForLTE) {
                        i3 = C1715R.string.network_4G_pure;
                    }
                    listPreference.setSummary(i3);
                    return;
                } else {
                    if (!this.mShow4GForLTE) {
                        i2 = C1715R.string.network_lte;
                    }
                    listPreference.setSummary(i2);
                    return;
                }
            case 11:
            case 12:
                break;
            case 13:
                listPreference.setValue(Integer.toString(13));
                listPreference.setSummary((int) C1715R.string.network_3G);
                return;
            case 14:
            case 16:
            case 18:
                listPreference.setValue(Integer.toString(18));
                listPreference.setSummary((int) C1715R.string.network_3G);
                return;
            case 21:
                listPreference.setValue(Integer.toString(21));
                listPreference.setSummary((int) C1715R.string.network_3G);
                return;
            case 23:
            case 24:
            case 26:
            case 28:
                listPreference.setValue(Integer.toString(26));
                listPreference.setSummary(this.mContext.getString(C1715R.string.network_5G) + this.mContext.getString(C1715R.string.network_recommended));
                return;
            case 25:
                listPreference.setValue(Integer.toString(25));
                listPreference.setSummary(this.mContext.getString(C1715R.string.network_5G) + this.mContext.getString(C1715R.string.network_recommended));
                return;
            case 27:
                listPreference.setValue(Integer.toString(27));
                if (this.mTelephonyManager.getPhoneType() == 2 || this.mIsGlobalCdma || MobileNetworkUtils.isWorldMode(this.mContext, this.mSubId)) {
                    listPreference.setSummary((int) C1715R.string.network_global);
                    return;
                }
                listPreference.setSummary(this.mContext.getString(C1715R.string.network_5G) + this.mContext.getString(C1715R.string.network_recommended));
                return;
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
                listPreference.setValue(Integer.toString(33));
                listPreference.setSummary(this.mContext.getString(C1715R.string.network_5G) + this.mContext.getString(C1715R.string.network_recommended));
                return;
            default:
                listPreference.setSummary(this.mContext.getString(C1715R.string.mobile_network_mode_error, new Object[]{Integer.valueOf(i)}));
                return;
        }
        if (!this.mIsGlobalCdma) {
            listPreference.setValue(Integer.toString(9));
            if (!this.mShow4GForLTE) {
                i2 = C1715R.string.network_lte;
            }
            listPreference.setSummary(i2);
            return;
        }
        listPreference.setValue(Integer.toString(10));
        listPreference.setSummary((int) C1715R.string.network_global);
    }
}
