package com.android.settings.connecteddevice.usb;

import android.content.Context;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceScreen;
import com.android.settings.Utils;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.SwitchPreference;

public class UsbDetailsPowerRoleController extends UsbDetailsController implements Preference.OnPreferenceClickListener {
    private final Runnable mFailureCallback = new Runnable() {
        public final void run() {
            UsbDetailsPowerRoleController.this.lambda$new$0$UsbDetailsPowerRoleController();
        }
    };
    private int mNextPowerRole = 0;
    private PreferenceCategory mPreferenceCategory;
    private SwitchPreference mSwitchPreference;

    public String getPreferenceKey() {
        return "usb_details_power_role";
    }

    public /* synthetic */ void lambda$new$0$UsbDetailsPowerRoleController() {
        if (this.mNextPowerRole != 0) {
            this.mSwitchPreference.setSummary((int) C1715R.string.usb_switching_failed);
            this.mNextPowerRole = 0;
        }
    }

    public UsbDetailsPowerRoleController(Context context, UsbDetailsFragment usbDetailsFragment, UsbBackend usbBackend) {
        super(context, usbDetailsFragment, usbBackend);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreferenceCategory = (PreferenceCategory) preferenceScreen.findPreference(getPreferenceKey());
        this.mSwitchPreference = new SwitchPreference(this.mPreferenceCategory.getContext());
        this.mSwitchPreference.setTitle((int) C1715R.string.usb_use_power_only);
        this.mSwitchPreference.setOnPreferenceClickListener(this);
        this.mPreferenceCategory.addPreference(this.mSwitchPreference);
    }

    /* access modifiers changed from: protected */
    public void refresh(boolean z, long j, int i, int i2) {
        if (z && !this.mUsbBackend.areAllRolesSupported()) {
            this.mFragment.getPreferenceScreen().removePreference(this.mPreferenceCategory);
        } else if (z && this.mUsbBackend.areAllRolesSupported()) {
            this.mFragment.getPreferenceScreen().addPreference(this.mPreferenceCategory);
        }
        if (i == 1) {
            this.mSwitchPreference.setChecked(true);
            this.mPreferenceCategory.setEnabled(true);
        } else if (i == 2) {
            this.mSwitchPreference.setChecked(false);
            this.mPreferenceCategory.setEnabled(true);
        } else if (!z || i == 0) {
            this.mPreferenceCategory.setEnabled(false);
            if (this.mNextPowerRole == 0) {
                this.mSwitchPreference.setSummary((CharSequence) "");
            }
        }
        int i3 = this.mNextPowerRole;
        if (i3 != 0 && i != 0) {
            if (i3 == i) {
                this.mSwitchPreference.setSummary((CharSequence) "");
            } else {
                this.mSwitchPreference.setSummary((int) C1715R.string.usb_switching_failed);
            }
            this.mNextPowerRole = 0;
            this.mHandler.removeCallbacks(this.mFailureCallback);
        }
    }

    public boolean onPreferenceClick(Preference preference) {
        int i = this.mSwitchPreference.isChecked() ? 1 : 2;
        if (this.mUsbBackend.getPowerRole() != i && this.mNextPowerRole == 0 && !Utils.isMonkeyRunning()) {
            this.mUsbBackend.setPowerRole(i);
            this.mNextPowerRole = i;
            this.mSwitchPreference.setSummary((int) C1715R.string.usb_switching);
            this.mHandler.postDelayed(this.mFailureCallback, this.mUsbBackend.areAllRolesSupported() ? 3000 : 15000);
        }
        SwitchPreference switchPreference = this.mSwitchPreference;
        switchPreference.setChecked(!switchPreference.isChecked());
        return true;
    }

    public boolean isAvailable() {
        return !Utils.isMonkeyRunning() && !this.mUsbBackend.isSinglePowerRoleSupported();
    }
}
