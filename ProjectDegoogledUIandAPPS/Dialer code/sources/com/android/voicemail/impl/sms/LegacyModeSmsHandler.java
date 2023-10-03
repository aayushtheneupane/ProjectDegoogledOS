package com.android.voicemail.impl.sms;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telecom.PhoneAccountHandle;
import android.telephony.TelephonyManager;
import android.telephony.VisualVoicemailSms;
import com.android.dialer.callintent.CallInitiationType$Type;
import com.android.dialer.callintent.CallIntentBuilder;
import com.android.dialer.precall.PreCall;
import com.android.voicemail.impl.VvmLog;

@TargetApi(26)
public class LegacyModeSmsHandler {
    public static void handle(Context context, VisualVoicemailSms visualVoicemailSms) {
        VvmLog.m45i("LegacyModeSmsHandler", "processing VVM SMS on legacy mode");
        String prefix = visualVoicemailSms.getPrefix();
        Bundle fields = visualVoicemailSms.getFields();
        PhoneAccountHandle phoneAccountHandle = visualVoicemailSms.getPhoneAccountHandle();
        int i = 0;
        if (prefix.equals("SYNC")) {
            SyncMessage syncMessage = new SyncMessage(fields);
            VvmLog.m45i("LegacyModeSmsHandler", "Received SYNC sms for " + phoneAccountHandle + " with event " + syncMessage.getSyncTriggerEvent());
            String syncTriggerEvent = syncMessage.getSyncTriggerEvent();
            char c = 65535;
            int hashCode = syncTriggerEvent.hashCode();
            if (hashCode != 2495) {
                if (hashCode == 76128 && syncTriggerEvent.equals("MBU")) {
                    c = 1;
                }
            } else if (syncTriggerEvent.equals("NM")) {
                c = 0;
            }
            if (c == 0 || c == 1) {
                sendLegacyVoicemailNotification(context, phoneAccountHandle, syncMessage.getNewMessageCount());
            }
        } else if ("MBOXUPDATE".equals(prefix)) {
            VvmLog.m46w("LegacyModeSmsHandler", "receiving alternative VVM SMS on non-activated account");
            try {
                i = Integer.parseInt(visualVoicemailSms.getFields().getString("m"));
            } catch (NumberFormatException unused) {
                VvmLog.m43e("LegacyModeSmsHandler", "missing message count");
            }
            sendLegacyVoicemailNotification(context, phoneAccountHandle, i);
        }
    }

    private static void sendLegacyVoicemailNotification(Context context, PhoneAccountHandle phoneAccountHandle, int i) {
        String str;
        PendingIntent pendingIntent;
        VvmLog.m45i("LegacyModeSmsHandler", "sending voicemail notification");
        Intent intent = new Intent("com.android.voicemail.VoicemailClient.ACTION_SHOW_LEGACY_VOICEMAIL");
        intent.setPackage(context.getPackageName());
        intent.putExtra("is_legacy_mode", true);
        intent.putExtra("android.telephony.extra.PHONE_ACCOUNT_HANDLE", phoneAccountHandle);
        intent.putExtra("android.telephony.extra.NOTIFICATION_COUNT", i);
        TelephonyManager createForPhoneAccountHandle = ((TelephonyManager) context.getSystemService(TelephonyManager.class)).createForPhoneAccountHandle(phoneAccountHandle);
        PendingIntent pendingIntent2 = null;
        if (createForPhoneAccountHandle == null) {
            str = null;
        } else {
            str = createForPhoneAccountHandle.getVoiceMailNumber();
        }
        if (str != null) {
            pendingIntent = PendingIntent.getActivity(context, 1, PreCall.getIntent(context, CallIntentBuilder.forVoicemail(phoneAccountHandle, CallInitiationType$Type.LEGACY_VOICEMAIL_NOTIFICATION)), 134217728);
        } else {
            Intent intent2 = new Intent("android.telephony.action.CONFIGURE_VOICEMAIL");
            intent2.putExtra("android.telephony.extra.HIDE_PUBLIC_SETTINGS", true);
            intent2.putExtra("android.telephony.extra.PHONE_ACCOUNT_HANDLE", phoneAccountHandle);
            pendingIntent2 = PendingIntent.getActivity(context, 2, intent2, 134217728);
            pendingIntent = null;
        }
        intent.putExtra("android.telephony.extra.VOICEMAIL_NUMBER", str);
        intent.putExtra("android.telephony.extra.CALL_VOICEMAIL_INTENT", pendingIntent);
        intent.putExtra("android.telephony.extra.LAUNCH_VOICEMAIL_SETTINGS_INTENT", pendingIntent2);
        context.sendBroadcast(intent);
    }
}
