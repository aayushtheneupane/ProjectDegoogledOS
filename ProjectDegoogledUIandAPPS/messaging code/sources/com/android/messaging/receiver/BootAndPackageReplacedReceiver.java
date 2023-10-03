package com.android.messaging.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.android.messaging.BugleApplication;
import com.android.messaging.C0967f;
import com.android.messaging.datamodel.action.UpdateMessageNotificationAction;
import com.android.messaging.util.C1430e;
import p026b.p027a.p030b.p031a.C0632a;

public class BootAndPackageReplacedReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction()) || "android.intent.action.MY_PACKAGE_REPLACED".equals(intent.getAction())) {
            C0967f.get().mo6645Hd().putLong("latest_notification_message_timestamp", Long.MIN_VALUE);
            UpdateMessageNotificationAction.m1460Le();
            BugleApplication.m1225d(context);
            return;
        }
        StringBuilder Pa = C0632a.m1011Pa("BootAndPackageReplacedReceiver got unexpected action: ");
        Pa.append(intent.getAction());
        C1430e.m3625i("MessagingApp", Pa.toString());
    }
}
