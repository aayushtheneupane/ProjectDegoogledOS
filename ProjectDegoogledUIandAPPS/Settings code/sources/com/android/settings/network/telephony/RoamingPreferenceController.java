package com.android.settings.network.telephony;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.telephony.CarrierConfigManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import androidx.fragment.app.FragmentManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settingslib.RestrictedSwitchPreference;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;

public class RoamingPreferenceController extends TelephonyTogglePreferenceController implements LifecycleObserver, OnStart, OnStop {
    private static final String DIALOG_TAG = "MobileDataDialog";
    private CarrierConfigManager mCarrierConfigManager;
    private DataContentObserver mDataContentObserver = new DataContentObserver(new Handler(Looper.getMainLooper()));
    FragmentManager mFragmentManager;
    boolean mNeedDialog;
    /* access modifiers changed from: private */
    public RestrictedSwitchPreference mSwitchPreference;
    private TelephonyManager mTelephonyManager;

    public int getAvailabilityStatus(int i) {
        return i != -1 ? 0 : 1;
    }

    public RoamingPreferenceController(Context context, String str) {
        super(context, str);
        this.mCarrierConfigManager = (CarrierConfigManager) context.getSystemService(CarrierConfigManager.class);
    }

    public void onStart() {
        this.mDataContentObserver.register(this.mContext, this.mSubId);
    }

    public void onStop() {
        this.mDataContentObserver.unRegister(this.mContext);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mSwitchPreference = (RestrictedSwitchPreference) preferenceScreen.findPreference(getPreferenceKey());
    }

    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!TextUtils.equals(preference.getKey(), getPreferenceKey())) {
            return false;
        }
        if (!this.mNeedDialog) {
            return true;
        }
        showDialog();
        return true;
    }

    public boolean setChecked(boolean z) {
        this.mNeedDialog = isDialogNeeded();
        if (this.mNeedDialog) {
            return false;
        }
        this.mTelephonyManager.setDataRoamingEnabled(z);
        return true;
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        RestrictedSwitchPreference restrictedSwitchPreference = (RestrictedSwitchPreference) preference;
        if (!restrictedSwitchPreference.isDisabledByAdmin()) {
            restrictedSwitchPreference.setEnabled(this.mSubId != -1);
            restrictedSwitchPreference.setChecked(isChecked());
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isDialogNeeded() {
        boolean isDataRoamingEnabled = this.mTelephonyManager.isDataRoamingEnabled();
        PersistableBundle configForSubId = this.mCarrierConfigManager.getConfigForSubId(this.mSubId);
        if (!isDataRoamingEnabled) {
            return configForSubId == null || !configForSubId.getBoolean("disable_charge_indication_bool");
        }
        return false;
    }

    public boolean isChecked() {
        return this.mTelephonyManager.isDataRoamingEnabled();
    }

    public void init(FragmentManager fragmentManager, int i) {
        this.mFragmentManager = fragmentManager;
        this.mSubId = i;
        this.mTelephonyManager = TelephonyManager.from(this.mContext).createForSubscriptionId(this.mSubId);
    }

    private void showDialog() {
        RoamingDialogFragment.newInstance(this.mSubId).show(this.mFragmentManager, DIALOG_TAG);
    }

    public class DataContentObserver extends ContentObserver {
        public DataContentObserver(Handler handler) {
            super(handler);
        }

        public void onChange(boolean z) {
            super.onChange(z);
            RoamingPreferenceController roamingPreferenceController = RoamingPreferenceController.this;
            roamingPreferenceController.updateState(roamingPreferenceController.mSwitchPreference);
        }

        public void register(Context context, int i) {
            Uri uriFor = Settings.Global.getUriFor("data_roaming");
            if (TelephonyManager.getDefault().getSimCount() != 1) {
                uriFor = Settings.Global.getUriFor("data_roaming" + i);
            }
            context.getContentResolver().registerContentObserver(uriFor, false, this);
        }

        public void unRegister(Context context) {
            context.getContentResolver().unregisterContentObserver(this);
        }
    }
}
