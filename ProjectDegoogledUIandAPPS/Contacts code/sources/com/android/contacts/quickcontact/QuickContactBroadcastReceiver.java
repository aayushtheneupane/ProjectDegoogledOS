package com.android.contacts.quickcontact;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.android.contacts.util.ImplicitIntentsUtil;

public class QuickContactBroadcastReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Uri data = intent.getData();
        Intent intent2 = new Intent("android.provider.action.QUICK_CONTACT");
        intent2.setSourceBounds(intent.getSourceBounds());
        intent2.addFlags(268468224);
        intent2.setData(data);
        ImplicitIntentsUtil.startActivityInApp(context, intent2);
    }
}
