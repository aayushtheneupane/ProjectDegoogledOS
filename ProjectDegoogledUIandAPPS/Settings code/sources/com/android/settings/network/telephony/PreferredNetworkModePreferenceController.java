package com.android.settings.network.telephony;

import android.content.ContentResolver;
import android.content.Context;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.telephony.CarrierConfigManager;
import android.telephony.TelephonyManager;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import com.android.internal.telephony.Phone;
import com.havoc.config.center.C1715R;

public class PreferredNetworkModePreferenceController extends TelephonyBasePreferenceController implements Preference.OnPreferenceChangeListener {
    private CarrierConfigManager mCarrierConfigManager;
    private boolean mIsGlobalCdma;
    private PersistableBundle mPersistableBundle;
    private TelephonyManager mTelephonyManager;

    public PreferredNetworkModePreferenceController(Context context, String str) {
        super(context, str);
        this.mCarrierConfigManager = (CarrierConfigManager) context.getSystemService(CarrierConfigManager.class);
    }

    public int getAvailabilityStatus(int i) {
        boolean z;
        PersistableBundle configForSubId = this.mCarrierConfigManager.getConfigForSubId(i);
        TelephonyManager.from(this.mContext).createForSubscriptionId(i);
        if (i != -1 && configForSubId != null && !configForSubId.getBoolean("hide_carrier_network_settings_bool") && !configForSubId.getBoolean("hide_preferred_network_type_bool") && configForSubId.getBoolean("world_phone_bool")) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return 0;
        }
        return 2;
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        ListPreference listPreference = (ListPreference) preference;
        int preferredNetworkMode = getPreferredNetworkMode();
        listPreference.setValue(Integer.toString(preferredNetworkMode));
        listPreference.setSummary(getPreferredNetworkModeSummaryResId(preferredNetworkMode));
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        int parseInt = Integer.parseInt((String) obj);
        if (!this.mTelephonyManager.setPreferredNetworkType(this.mSubId, parseInt)) {
            return false;
        }
        ContentResolver contentResolver = this.mContext.getContentResolver();
        Settings.Global.putInt(contentResolver, "preferred_network_mode" + this.mSubId, parseInt);
        ((ListPreference) preference).setSummary(getPreferredNetworkModeSummaryResId(parseInt));
        return true;
    }

    public void init(int i) {
        this.mSubId = i;
        PersistableBundle configForSubId = this.mCarrierConfigManager.getConfigForSubId(this.mSubId);
        this.mTelephonyManager = TelephonyManager.from(this.mContext).createForSubscriptionId(this.mSubId);
        boolean z = false;
        if ((this.mTelephonyManager.getLteOnCdmaMode() == 1) && configForSubId.getBoolean("show_cdma_choices_bool")) {
            z = true;
        }
        this.mIsGlobalCdma = z;
    }

    private int getPreferredNetworkMode() {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        return Settings.Global.getInt(contentResolver, "preferred_network_mode" + this.mSubId, Phone.PREFERRED_NT_MODE);
    }

    private int getPreferredNetworkModeSummaryResId(int i) {
        switch (i) {
            case 0:
                return C1715R.string.preferred_network_mode_wcdma_perf_summary;
            case 1:
                return C1715R.string.preferred_network_mode_gsm_only_summary;
            case 2:
                return C1715R.string.preferred_network_mode_wcdma_only_summary;
            case 3:
                return C1715R.string.preferred_network_mode_gsm_wcdma_summary;
            case 4:
                return this.mTelephonyManager.getLteOnCdmaMode() != 1 ? C1715R.string.preferred_network_mode_cdma_evdo_summary : C1715R.string.preferred_network_mode_cdma_summary;
            case 5:
                return C1715R.string.preferred_network_mode_cdma_only_summary;
            case 6:
                return C1715R.string.preferred_network_mode_evdo_only_summary;
            case 7:
                return C1715R.string.preferred_network_mode_cdma_evdo_gsm_wcdma_summary;
            case 8:
                return C1715R.string.preferred_network_mode_lte_cdma_evdo_summary;
            case 9:
                return C1715R.string.preferred_network_mode_lte_gsm_wcdma_summary;
            case 10:
                return (this.mTelephonyManager.getPhoneType() == 2 || this.mIsGlobalCdma || MobileNetworkUtils.isWorldMode(this.mContext, this.mSubId)) ? C1715R.string.preferred_network_mode_global_summary : C1715R.string.preferred_network_mode_lte_summary;
            case 11:
                return C1715R.string.preferred_network_mode_lte_summary;
            case 12:
                return C1715R.string.preferred_network_mode_lte_wcdma_summary;
            case 13:
                return C1715R.string.preferred_network_mode_tdscdma_summary;
            case 14:
                return C1715R.string.preferred_network_mode_tdscdma_wcdma_summary;
            case 15:
                return C1715R.string.preferred_network_mode_lte_tdscdma_summary;
            case 16:
                return C1715R.string.preferred_network_mode_tdscdma_gsm_summary;
            case 17:
                return C1715R.string.preferred_network_mode_lte_tdscdma_gsm_summary;
            case 18:
                return C1715R.string.preferred_network_mode_tdscdma_gsm_wcdma_summary;
            case 19:
                return C1715R.string.preferred_network_mode_lte_tdscdma_wcdma_summary;
            case 20:
                return C1715R.string.preferred_network_mode_lte_tdscdma_gsm_wcdma_summary;
            case 21:
                return C1715R.string.preferred_network_mode_tdscdma_cdma_evdo_gsm_wcdma_summary;
            case 22:
                return C1715R.string.preferred_network_mode_lte_tdscdma_cdma_evdo_gsm_wcdma_summary;
            default:
                return C1715R.string.preferred_network_mode_global_summary;
        }
    }
}
