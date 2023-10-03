package com.android.voicemail;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.android.dialer.common.LogUtil;

public class VoicemailSecretCodeReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if ("886266344".equals(intent.getData().getHost())) {
            LogUtil.m9i("VoicemailSecretCodeReceiver.onReceive", "secret code received", new Object[0]);
            VoicemailComponent.get(context).getVoicemailClient().showConfigUi(context);
        }
    }
}
