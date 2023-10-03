package com.android.voicemail.impl.sync;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import com.android.voicemail.VoicemailComponent;
import com.android.voicemail.impl.ActivationTask;
import com.android.voicemail.impl.VvmLog;
import com.android.voicemail.impl.settings.VisualVoicemailSettingsUtil;

public class OmtpVvmSyncReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (VoicemailComponent.get(context).getVoicemailClient().isVoicemailModuleEnabled() && "android.provider.action.SYNC_VOICEMAIL".equals(intent.getAction())) {
            for (PhoneAccountHandle next : ((TelecomManager) context.getSystemService(TelecomManager.class)).getCallCapablePhoneAccounts()) {
                if (VisualVoicemailSettingsUtil.isEnabled(context, next)) {
                    if (!VvmAccountManager.isAccountActivated(context, next)) {
                        VvmLog.m45i("OmtpVvmSyncReceiver", "Unactivated account " + next + " found, activating");
                        ActivationTask.start(context, next, (Bundle) null);
                    } else {
                        SyncTask.start(context, next);
                    }
                }
            }
        }
    }
}
