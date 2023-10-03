package com.android.settings.applications.specialaccess;

import android.content.Context;
import android.content.pm.PackageManager;
import android.nfc.NfcAdapter;
import android.os.UserManager;
import com.android.settings.core.BasePreferenceController;

public class DefaultPaymentSettingsPreferenceController extends BasePreferenceController {
    private final NfcAdapter mNfcAdapter = NfcAdapter.getDefaultAdapter(this.mContext);
    private final PackageManager mPackageManager;
    private final UserManager mUserManager;

    public DefaultPaymentSettingsPreferenceController(Context context, String str) {
        super(context, str);
        this.mPackageManager = context.getPackageManager();
        this.mUserManager = (UserManager) context.getSystemService(UserManager.class);
    }

    public int getAvailabilityStatus() {
        if (!this.mPackageManager.hasSystemFeature("android.hardware.nfc") || !this.mPackageManager.hasSystemFeature("android.hardware.nfc.hce")) {
            return 3;
        }
        if (!this.mUserManager.isAdminUser()) {
            return 4;
        }
        NfcAdapter nfcAdapter = this.mNfcAdapter;
        return (nfcAdapter == null || !nfcAdapter.isEnabled()) ? 2 : 0;
    }
}
