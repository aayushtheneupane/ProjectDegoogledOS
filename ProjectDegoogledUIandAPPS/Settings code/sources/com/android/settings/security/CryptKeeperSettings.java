package com.android.settings.security;

import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.UserHandle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import com.android.settings.CryptKeeperConfirm;
import com.android.settings.SettingsActivity;
import com.android.settings.core.InstrumentedPreferenceFragment;
import com.android.settings.password.ChooseLockSettingsHelper;
import com.havoc.config.center.C1715R;

public class CryptKeeperSettings extends InstrumentedPreferenceFragment {
    /* access modifiers changed from: private */
    public View mBatteryWarning;
    private View mContentView;
    /* access modifiers changed from: private */
    public Button mInitiateButton;
    private View.OnClickListener mInitiateListener = new View.OnClickListener() {
        public void onClick(View view) {
            if (!CryptKeeperSettings.this.runKeyguardConfirmation(55)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CryptKeeperSettings.this.getActivity());
                builder.setTitle((int) C1715R.string.crypt_keeper_dialog_need_password_title);
                builder.setMessage((int) C1715R.string.crypt_keeper_dialog_need_password_message);
                builder.setPositiveButton(17039370, (DialogInterface.OnClickListener) null);
                builder.create().show();
            }
        }
    };
    private IntentFilter mIntentFilter;
    private BroadcastReceiver mIntentReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.BATTERY_CHANGED")) {
                int i = 0;
                int intExtra = intent.getIntExtra("level", 0);
                int intExtra2 = intent.getIntExtra("plugged", 0);
                int intExtra3 = intent.getIntExtra("invalid_charger", 0);
                boolean z = true;
                boolean z2 = intExtra >= 80;
                boolean z3 = (intExtra2 & 7) != 0 && intExtra3 == 0;
                Button access$000 = CryptKeeperSettings.this.mInitiateButton;
                if (!z2 || !z3) {
                    z = false;
                }
                access$000.setEnabled(z);
                CryptKeeperSettings.this.mPowerWarning.setVisibility(z3 ? 8 : 0);
                View access$200 = CryptKeeperSettings.this.mBatteryWarning;
                if (z2) {
                    i = 8;
                }
                access$200.setVisibility(i);
            }
        }
    };
    /* access modifiers changed from: private */
    public View mPowerWarning;

    public int getMetricsCategory() {
        return 32;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mContentView = layoutInflater.inflate(C1715R.layout.crypt_keeper_settings, (ViewGroup) null);
        this.mIntentFilter = new IntentFilter();
        this.mIntentFilter.addAction("android.intent.action.BATTERY_CHANGED");
        this.mInitiateButton = (Button) this.mContentView.findViewById(C1715R.C1718id.initiate_encrypt);
        this.mInitiateButton.setOnClickListener(this.mInitiateListener);
        this.mInitiateButton.setEnabled(false);
        this.mPowerWarning = this.mContentView.findViewById(C1715R.C1718id.warning_unplugged);
        this.mBatteryWarning = this.mContentView.findViewById(C1715R.C1718id.warning_low_charge);
        return this.mContentView;
    }

    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(this.mIntentReceiver, this.mIntentFilter);
    }

    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(this.mIntentReceiver);
    }

    public void onActivityCreated(Bundle bundle) {
        DevicePolicyManager devicePolicyManager;
        super.onActivityCreated(bundle);
        FragmentActivity activity = getActivity();
        if (!(!"android.app.action.START_ENCRYPTION".equals(activity.getIntent().getAction()) || (devicePolicyManager = (DevicePolicyManager) activity.getSystemService("device_policy")) == null || devicePolicyManager.getStorageEncryptionStatus() == 1)) {
            activity.finish();
        }
        activity.setTitle(C1715R.string.crypt_keeper_encrypt_title);
    }

    /* access modifiers changed from: private */
    public boolean runKeyguardConfirmation(int i) {
        Resources resources = getActivity().getResources();
        ChooseLockSettingsHelper chooseLockSettingsHelper = new ChooseLockSettingsHelper(getActivity(), this);
        if (chooseLockSettingsHelper.utils().getKeyguardStoredPasswordQuality(UserHandle.myUserId()) != 0) {
            return chooseLockSettingsHelper.launchConfirmationActivity(i, resources.getText(C1715R.string.crypt_keeper_encrypt_title), true);
        }
        showFinalConfirmation(1, "".getBytes());
        return true;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 55 && i2 == -1 && intent != null) {
            int intExtra = intent.getIntExtra("type", -1);
            byte[] byteArrayExtra = intent.getByteArrayExtra("password");
            if (byteArrayExtra != null && byteArrayExtra.length != 0) {
                showFinalConfirmation(intExtra, byteArrayExtra);
            }
        }
    }

    private void showFinalConfirmation(int i, byte[] bArr) {
        Preference preference = new Preference(getPreferenceManager().getContext());
        preference.setFragment(CryptKeeperConfirm.class.getName());
        preference.setTitle((int) C1715R.string.crypt_keeper_confirm_title);
        addEncryptionInfoToPreference(preference, i, bArr);
        ((SettingsActivity) getActivity()).onPreferenceStartFragment((PreferenceFragmentCompat) null, preference);
    }

    private void addEncryptionInfoToPreference(Preference preference, int i, byte[] bArr) {
        if (((DevicePolicyManager) getActivity().getSystemService("device_policy")).getDoNotAskCredentialsOnBoot()) {
            preference.getExtras().putInt("type", 1);
            preference.getExtras().putByteArray("password", "".getBytes());
            return;
        }
        preference.getExtras().putInt("type", i);
        preference.getExtras().putByteArray("password", bArr);
    }
}
