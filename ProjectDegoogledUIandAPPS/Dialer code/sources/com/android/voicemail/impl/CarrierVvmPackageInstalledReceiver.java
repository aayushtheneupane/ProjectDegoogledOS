package com.android.voicemail.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import com.android.voicemail.impl.settings.VisualVoicemailSettingsUtil;

public class CarrierVvmPackageInstalledReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("android.intent.extra.PACKAGE_NAME");
        VvmLog.m45i("CarrierVvmPackageInstalledReceiver.onReceive", "package installed: " + stringExtra);
        for (PhoneAccountHandle next : ((TelecomManager) context.getSystemService(TelecomManager.class)).getCallCapablePhoneAccounts()) {
            OmtpVvmCarrierConfigHelper omtpVvmCarrierConfigHelper = new OmtpVvmCarrierConfigHelper(context, next);
            if (omtpVvmCarrierConfigHelper.isValid() && omtpVvmCarrierConfigHelper.isCarrierAppInstalled()) {
                VvmLog.m45i("VvmPackageInstallHandler.handlePackageInstalled", "Carrier VVM package installed, disabling system VVM client");
                VisualVoicemailSettingsUtil.setEnabled(context, next, false);
            }
        }
    }
}
