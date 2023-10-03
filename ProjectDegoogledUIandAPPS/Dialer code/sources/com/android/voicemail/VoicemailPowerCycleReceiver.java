package com.android.voicemail;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.android.tools.p006r8.GeneratedOutlineSupport;

public class VoicemailPowerCycleReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        VoicemailClient voicemailClient = VoicemailComponent.get(context).getVoicemailClient();
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            voicemailClient.onBoot(context);
        } else if ("android.intent.action.ACTION_SHUTDOWN".equals(intent.getAction())) {
            voicemailClient.onShutdown(context);
        } else {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("unexpected action: ");
            outline13.append(intent.getAction());
            throw new AssertionError(outline13.toString());
        }
    }
}
