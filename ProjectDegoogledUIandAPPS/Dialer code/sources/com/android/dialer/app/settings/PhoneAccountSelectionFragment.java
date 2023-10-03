package com.android.dialer.app.settings;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import java.util.List;

public class PhoneAccountSelectionFragment extends PreferenceFragment {
    /* access modifiers changed from: private */
    public Bundle arguments;
    /* access modifiers changed from: private */
    public String phoneAccountHandleKey;
    /* access modifiers changed from: private */
    public String targetFragment;
    /* access modifiers changed from: private */
    public int titleRes;

    final class AccountPreference extends Preference {
        private final PhoneAccountHandle phoneAccountHandle;

        public AccountPreference(Context context, PhoneAccountHandle phoneAccountHandle2, PhoneAccount phoneAccount) {
            super(context);
            this.phoneAccountHandle = phoneAccountHandle2;
            setTitle(phoneAccount.getLabel());
            setSummary(phoneAccount.getShortDescription());
            Icon icon = phoneAccount.getIcon();
            if (icon != null) {
                setIcon(icon.loadDrawable(context));
            }
        }

        /* access modifiers changed from: package-private */
        public void click() {
            onClick();
        }

        /* access modifiers changed from: protected */
        public void onClick() {
            super.onClick();
            PhoneAccountSelectionFragment.this.arguments.putParcelable(PhoneAccountSelectionFragment.this.phoneAccountHandleKey, this.phoneAccountHandle);
            ((PreferenceActivity) PhoneAccountSelectionFragment.this.getActivity()).startWithFragment(PhoneAccountSelectionFragment.this.targetFragment, PhoneAccountSelectionFragment.this.arguments, (Fragment) null, 0, PhoneAccountSelectionFragment.this.titleRes, 0);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.targetFragment = getArguments().getString("target_fragment");
        this.arguments = new Bundle();
        this.arguments.putAll(getArguments().getBundle("arguments"));
        this.phoneAccountHandleKey = getArguments().getString("phone_account_handle_key");
        this.titleRes = getArguments().getInt("target_title_res", 0);
    }

    public void onResume() {
        super.onResume();
        setPreferenceScreen(getPreferenceManager().createPreferenceScreen(getContext()));
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        TelecomManager telecomManager = (TelecomManager) getContext().getSystemService(TelecomManager.class);
        List<PhoneAccountHandle> callCapablePhoneAccounts = telecomManager.getCallCapablePhoneAccounts();
        Activity activity = getActivity();
        for (PhoneAccountHandle next : callCapablePhoneAccounts) {
            PhoneAccount phoneAccount = telecomManager.getPhoneAccount(next);
            if (phoneAccount != null) {
                if ((phoneAccount.getCapabilities() & 4) != 0) {
                    preferenceScreen.addPreference(new AccountPreference(activity, next, phoneAccount));
                }
            }
        }
    }
}
