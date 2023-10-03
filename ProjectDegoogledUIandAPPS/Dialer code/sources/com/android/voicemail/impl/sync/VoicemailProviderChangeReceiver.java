package com.android.voicemail.impl.sync;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telecom.PhoneAccountHandle;
import com.android.voicemail.VoicemailComponent;
import com.android.voicemail.impl.settings.VisualVoicemailSettingsUtil;

public class VoicemailProviderChangeReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (VoicemailComponent.get(context).getVoicemailClient().isVoicemailModuleEnabled() && !intent.getBooleanExtra("com.android.voicemail.extra.SELF_CHANGE", false)) {
            for (PhoneAccountHandle next : VvmAccountManager.getActiveAccounts(context)) {
                if (VisualVoicemailSettingsUtil.isEnabled(context, next)) {
                    UploadTask.start(context, next);
                }
            }
        }
    }
}
