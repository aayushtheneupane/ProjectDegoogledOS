package com.android.contacts.interactions;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.android.contacts.util.ContactsNotificationChannelsUtil;

public class OnBootOrUpgradeReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("android.intent.action.BOOT_COMPLETED".equals(action) || "android.intent.action.MY_PACKAGE_REPLACED".equals(action)) {
            ContactsNotificationChannelsUtil.createDefaultChannel(context);
        }
    }
}
