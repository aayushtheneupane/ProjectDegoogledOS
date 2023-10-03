package com.android.messaging.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.android.messaging.datamodel.C0944e;
import com.android.messaging.datamodel.action.MarkAsReadAction;
import com.android.messaging.datamodel.action.MarkAsSeenAction;
import com.android.messaging.util.ConversationIdSet;
import java.util.Iterator;

public class NotificationReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String stringExtra;
        if (intent.getAction().equals("com.android.messaging.reset_notifications")) {
            String stringExtra2 = intent.getStringExtra("conversation_id_set");
            intent.getIntExtra("notifications_update", 3);
            if (stringExtra2 == null) {
                C0944e.m2078Ud();
                return;
            }
            Iterator it = ConversationIdSet.m3538Xa(stringExtra2).iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                new MarkAsSeenAction(str).start();
                C0944e.m2076I(str);
            }
        } else if (intent.getAction().equals("com.android.messaging.mark_as_read") && (stringExtra = intent.getStringExtra("conversation_id_set")) != null) {
            Iterator it2 = ConversationIdSet.m3538Xa(stringExtra).iterator();
            while (it2.hasNext()) {
                MarkAsReadAction.m1383Q((String) it2.next());
            }
        }
    }
}
