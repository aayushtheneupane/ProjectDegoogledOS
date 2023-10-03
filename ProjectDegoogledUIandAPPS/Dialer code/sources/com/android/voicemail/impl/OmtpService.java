package com.android.voicemail.impl;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserManager;
import android.preference.PreferenceManager;
import android.telecom.PhoneAccountHandle;
import android.telephony.TelephonyManager;
import android.telephony.VisualVoicemailService;
import android.telephony.VisualVoicemailSms;
import android.telephony.VisualVoicemailSmsFilterSettings;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.voicemail.VoicemailComponent;
import com.android.voicemail.impl.settings.VisualVoicemailSettingsUtil;
import com.android.voicemail.impl.sms.LegacyModeSmsHandler;
import com.android.voicemail.impl.sync.VvmAccountManager;

@TargetApi(26)
public class OmtpService extends VisualVoicemailService {
    private void disableFilter(PhoneAccountHandle phoneAccountHandle) {
        TelephonyManager createForPhoneAccountHandle = ((TelephonyManager) getSystemService(TelephonyManager.class)).createForPhoneAccountHandle(phoneAccountHandle);
        if (createForPhoneAccountHandle != null) {
            VvmLog.m45i("VvmOmtpService", "disabling SMS filter");
            createForPhoneAccountHandle.setVisualVoicemailSmsFilterSettings((VisualVoicemailSmsFilterSettings) null);
        }
    }

    private boolean isModuleEnabled() {
        return VoicemailComponent.get(this).getVoicemailClient().isVoicemailModuleEnabled();
    }

    private boolean isServiceEnabled(PhoneAccountHandle phoneAccountHandle) {
        OmtpVvmCarrierConfigHelper omtpVvmCarrierConfigHelper = new OmtpVvmCarrierConfigHelper(this, phoneAccountHandle);
        if (!omtpVvmCarrierConfigHelper.isValid()) {
            VvmLog.m45i("VvmOmtpService", "VVM not supported on " + phoneAccountHandle);
            return false;
        } else if (VisualVoicemailSettingsUtil.isEnabled(this, phoneAccountHandle) || omtpVvmCarrierConfigHelper.isLegacyModeEnabled()) {
            return true;
        } else {
            VvmLog.m45i("VvmOmtpService", "VVM is disabled");
            return false;
        }
    }

    private static boolean isUserUnlocked(Context context) {
        return ((UserManager) context.getSystemService(UserManager.class)).isUserUnlocked();
    }

    static void onBoot(Context context) {
        VvmLog.m45i("VvmOmtpService", "onBoot");
        Assert.isTrue(isUserUnlocked(context));
        Assert.isMainThread();
        setShuttingDown(context, false);
    }

    static void onShutdown(Context context) {
        VvmLog.m45i("VvmOmtpService", "onShutdown");
        Assert.isTrue(isUserUnlocked(context));
        Assert.isMainThread();
        setShuttingDown(context, true);
    }

    private static void setShuttingDown(Context context, boolean z) {
        PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()).edit().putBoolean("com.android.voicemail.impl.is_shutting_down", z).apply();
    }

    public void onCellServiceConnected(VisualVoicemailService.VisualVoicemailTask visualVoicemailTask, PhoneAccountHandle phoneAccountHandle) {
        VvmLog.m45i("VvmOmtpService", "onCellServiceConnected");
        if (!isModuleEnabled()) {
            VvmLog.m43e("VvmOmtpService", "onCellServiceConnected received when module is disabled");
            visualVoicemailTask.finish();
        } else if (!isUserUnlocked(this)) {
            VvmLog.m45i("VvmOmtpService", "onCellServiceConnected: user locked");
            visualVoicemailTask.finish();
        } else if (!isServiceEnabled(phoneAccountHandle)) {
            disableFilter(phoneAccountHandle);
            visualVoicemailTask.finish();
        } else {
            ((LoggingBindingsStub) Logger.get(this)).logImpression(DialerImpression$Type.VVM_UNBUNDLED_EVENT_RECEIVED);
            ActivationTask.start(this, phoneAccountHandle, (Bundle) null);
            visualVoicemailTask.finish();
        }
    }

    public void onSimRemoved(VisualVoicemailService.VisualVoicemailTask visualVoicemailTask, PhoneAccountHandle phoneAccountHandle) {
        VvmLog.m45i("VvmOmtpService", "onSimRemoved");
        if (!isModuleEnabled()) {
            VvmLog.m43e("VvmOmtpService", "onSimRemoved called when module is disabled");
            visualVoicemailTask.finish();
        } else if (!isUserUnlocked(this)) {
            VvmLog.m45i("VvmOmtpService", "onSimRemoved: user locked");
            visualVoicemailTask.finish();
        } else if (PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean("com.android.voicemail.impl.is_shutting_down", false)) {
            VvmLog.m45i("VvmOmtpService", "onSimRemoved: system shutting down, ignoring");
            visualVoicemailTask.finish();
        } else {
            ((LoggingBindingsStub) Logger.get(this)).logImpression(DialerImpression$Type.VVM_UNBUNDLED_EVENT_RECEIVED);
            VvmAccountManager.removeAccount(this, phoneAccountHandle);
            visualVoicemailTask.finish();
        }
    }

    public void onSmsReceived(VisualVoicemailService.VisualVoicemailTask visualVoicemailTask, VisualVoicemailSms visualVoicemailSms) {
        VvmLog.m45i("VvmOmtpService", "onSmsReceived");
        if (!isModuleEnabled()) {
            VvmLog.m43e("VvmOmtpService", "onSmsReceived received when module is disabled");
            visualVoicemailTask.finish();
        } else if (!isUserUnlocked(this)) {
            LegacyModeSmsHandler.handle(this, visualVoicemailSms);
        } else if (!isServiceEnabled(visualVoicemailSms.getPhoneAccountHandle())) {
            VvmLog.m43e("VvmOmtpService", "onSmsReceived received when service is disabled");
            disableFilter(visualVoicemailSms.getPhoneAccountHandle());
            visualVoicemailTask.finish();
        } else {
            ((LoggingBindingsStub) Logger.get(this)).logImpression(DialerImpression$Type.VVM_UNBUNDLED_EVENT_RECEIVED);
            Intent intent = new Intent("com.android.vociemailomtp.sms.sms_received");
            intent.setPackage(getPackageName());
            intent.putExtra("extra_voicemail_sms", visualVoicemailSms);
            sendBroadcast(intent);
            visualVoicemailTask.finish();
        }
    }

    public void onStopped(VisualVoicemailService.VisualVoicemailTask visualVoicemailTask) {
        VvmLog.m45i("VvmOmtpService", "onStopped");
        if (!isModuleEnabled()) {
            VvmLog.m43e("VvmOmtpService", "onStopped called when module is disabled");
            visualVoicemailTask.finish();
        } else if (!isUserUnlocked(this)) {
            VvmLog.m45i("VvmOmtpService", "onStopped: user locked");
            visualVoicemailTask.finish();
        } else {
            ((LoggingBindingsStub) Logger.get(this)).logImpression(DialerImpression$Type.VVM_UNBUNDLED_EVENT_RECEIVED);
        }
    }
}
