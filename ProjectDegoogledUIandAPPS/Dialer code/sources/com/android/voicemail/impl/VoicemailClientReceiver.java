package com.android.voicemail.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telecom.PhoneAccountHandle;
import com.android.dialer.common.LogUtil;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.android.voicemail.VoicemailComponent;
import com.android.voicemail.impl.sync.UploadTask;
import com.android.voicemail.impl.sync.VvmAccountManager;

public class VoicemailClientReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (!VoicemailComponent.get(context).getVoicemailClient().isVoicemailModuleEnabled()) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("module disabled, ignoring ");
            outline13.append(intent.getAction());
            LogUtil.m9i("VoicemailClientReceiver.onReceive", outline13.toString(), new Object[0]);
            return;
        }
        String action = intent.getAction();
        char c = 65535;
        if (action.hashCode() == -780782977 && action.equals("com.android.voicemail.VoicemailClient.ACTION_UPLOAD")) {
            c = 0;
        }
        if (c == 0) {
            LogUtil.m9i("VoicemailClientReceiver.onReceive", "ACTION_UPLOAD received", new Object[0]);
            for (PhoneAccountHandle start : VvmAccountManager.getActiveAccounts(context)) {
                UploadTask.start(context, start);
            }
            return;
        }
        StringBuilder outline132 = GeneratedOutlineSupport.outline13("Unexpected action ");
        outline132.append(intent.getAction());
        throw new AssertionError(outline132.toString());
    }
}
