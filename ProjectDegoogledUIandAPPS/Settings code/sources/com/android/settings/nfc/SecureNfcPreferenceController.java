package com.android.settings.nfc;

import android.content.Context;
import android.nfc.NfcAdapter;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.TogglePreferenceController;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnPause;
import com.android.settingslib.core.lifecycle.events.OnResume;
import com.havoc.support.preferences.SwitchPreference;

public class SecureNfcPreferenceController extends TogglePreferenceController implements LifecycleObserver, OnResume, OnPause {
    private final NfcAdapter mNfcAdapter;
    private SecureNfcEnabler mSecureNfcEnabler;

    public boolean hasAsyncUpdate() {
        return true;
    }

    public boolean isSliceable() {
        return true;
    }

    public SecureNfcPreferenceController(Context context, String str) {
        super(context, str);
        this.mNfcAdapter = NfcAdapter.getDefaultAdapter(context);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        if (!isAvailable()) {
            this.mSecureNfcEnabler = null;
            return;
        }
        this.mSecureNfcEnabler = new SecureNfcEnabler(this.mContext, (SwitchPreference) preferenceScreen.findPreference(getPreferenceKey()));
    }

    public boolean isChecked() {
        return this.mNfcAdapter.isSecureNfcEnabled();
    }

    public boolean setChecked(boolean z) {
        return this.mNfcAdapter.enableSecureNfc(z);
    }

    public int getAvailabilityStatus() {
        NfcAdapter nfcAdapter = this.mNfcAdapter;
        if (nfcAdapter != null && nfcAdapter.isSecureNfcSupported()) {
            return 0;
        }
        return 3;
    }

    public void onResume() {
        SecureNfcEnabler secureNfcEnabler = this.mSecureNfcEnabler;
        if (secureNfcEnabler != null) {
            secureNfcEnabler.resume();
        }
    }

    public void onPause() {
        SecureNfcEnabler secureNfcEnabler = this.mSecureNfcEnabler;
        if (secureNfcEnabler != null) {
            secureNfcEnabler.pause();
        }
    }
}
