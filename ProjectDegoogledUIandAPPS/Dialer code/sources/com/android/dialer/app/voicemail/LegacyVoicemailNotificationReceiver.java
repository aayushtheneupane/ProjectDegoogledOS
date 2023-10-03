package com.android.dialer.app.voicemail;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.design.R$dimen;
import android.telecom.PhoneAccountHandle;
import com.android.dialer.app.calllog.CallLogAsyncTaskUtil;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.PerAccountSharedPreferences;
import com.android.dialer.compat.telephony.TelephonyManagerCompat;
import com.android.dialer.storage.StorageComponent;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.android.voicemail.VoicemailComponent;

@TargetApi(26)
public class LegacyVoicemailNotificationReceiver extends BroadcastReceiver {
    static final String LEGACY_VOICEMAIL_DISMISSED = "legacy_voicemail_dismissed";

    static PerAccountSharedPreferences getSharedPreferences(Context context, PhoneAccountHandle phoneAccountHandle) {
        return new PerAccountSharedPreferences(phoneAccountHandle, StorageComponent.get(context).unencryptedSharedPrefs());
    }

    public static void setDismissed(Context context, PhoneAccountHandle phoneAccountHandle, boolean z) {
        PerAccountSharedPreferences.Editor edit = getSharedPreferences(context, phoneAccountHandle).edit();
        edit.putBoolean(LEGACY_VOICEMAIL_DISMISSED, z);
        edit.apply();
    }

    public void onReceive(Context context, Intent intent) {
        if ("android.telephony.action.SHOW_VOICEMAIL_NOTIFICATION".equals(intent.getAction()) || "com.android.voicemail.VoicemailClient.ACTION_SHOW_LEGACY_VOICEMAIL".equals(intent.getAction())) {
            LogUtil.m9i("LegacyVoicemailNotificationReceiver.onReceive", "received legacy voicemail notification", new Object[0]);
            int i = Build.VERSION.SDK_INT;
            PhoneAccountHandle phoneAccountHandle = (PhoneAccountHandle) intent.getParcelableExtra("android.telephony.extra.PHONE_ACCOUNT_HANDLE");
            Assert.isNotNull(phoneAccountHandle);
            PhoneAccountHandle phoneAccountHandle2 = phoneAccountHandle;
            int intExtra = intent.getIntExtra("android.telephony.extra.NOTIFICATION_COUNT", -1);
            boolean booleanExtra = intent.getBooleanExtra(TelephonyManagerCompat.EXTRA_IS_REFRESH, false);
            LogUtil.m9i("LegacyVoicemailNotificationReceiver.onReceive", GeneratedOutlineSupport.outline10("isRefresh: ", booleanExtra), new Object[0]);
            PerAccountSharedPreferences sharedPreferences = getSharedPreferences(context, phoneAccountHandle2);
            if (!booleanExtra) {
                setDismissed(context, phoneAccountHandle2, false);
            } else if (sharedPreferences.getBoolean(LEGACY_VOICEMAIL_DISMISSED, false)) {
                LogUtil.m9i("LegacyVoicemailNotificationReceiver.onReceive", "notification dismissed, ignoring refresh", new Object[0]);
                return;
            }
            int i2 = intExtra == -1 ? 1 : intExtra;
            if (i2 == 0) {
                LogUtil.m9i("LegacyVoicemailNotificationReceiver.onReceive", "clearing notification", new Object[0]);
                CallLogAsyncTaskUtil.cancelNotification(context, phoneAccountHandle2);
            } else if (intent.getBooleanExtra("is_legacy_mode", false) || !R$dimen.isUserUnlocked(context) || !VoicemailComponent.get(context).getVoicemailClient().isActivated(context, phoneAccountHandle2)) {
                LogUtil.m9i("LegacyVoicemailNotificationReceiver.onReceive", "sending notification", new Object[0]);
                CallLogAsyncTaskUtil.showNotification(context, phoneAccountHandle2, i2, intent.getStringExtra("android.telephony.extra.VOICEMAIL_NUMBER"), (PendingIntent) intent.getParcelableExtra("android.telephony.extra.CALL_VOICEMAIL_INTENT"), (PendingIntent) intent.getParcelableExtra("android.telephony.extra.LAUNCH_VOICEMAIL_SETTINGS_INTENT"), booleanExtra);
            } else {
                LogUtil.m9i("LegacyVoicemailNotificationReceiver.onReceive", "visual voicemail is activated, ignoring notification", new Object[0]);
            }
        }
    }
}
