package com.android.messaging.datamodel;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.android.messaging.datamodel.action.InsertNewMessageAction;
import com.android.messaging.datamodel.action.UpdateMessageNotificationAction;
import com.android.messaging.datamodel.data.MessageData;
import com.android.messaging.p041ui.conversationlist.ConversationListActivity;
import com.android.messaging.sms.C1029y;
import com.android.messaging.util.C1430e;

public class NoConfirmationSmsSendService extends IntentService {
    public NoConfirmationSmsSendService() {
        super(NoConfirmationSmsSendService.class.getName());
        setIntentRedelivery(true);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000c, code lost:
        r1 = (r1 = androidx.core.app.RemoteInput.getResultsFromIntent(r1)).getCharSequence(r2);
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String m1280b(android.content.Intent r1, java.lang.String r2) {
        /*
            r0 = this;
            java.lang.String r0 = r1.getStringExtra(r2)
            if (r0 != 0) goto L_0x0016
            android.os.Bundle r1 = androidx.core.app.RemoteInput.getResultsFromIntent(r1)
            if (r1 == 0) goto L_0x0016
            java.lang.CharSequence r1 = r1.getCharSequence(r2)
            if (r1 == 0) goto L_0x0016
            java.lang.String r0 = r1.toString()
        L_0x0016:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.NoConfirmationSmsSendService.m1280b(android.content.Intent, java.lang.String):java.lang.String");
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        MessageData messageData;
        if (Log.isLoggable("MessagingApp", 2)) {
            C1430e.m3628v("MessagingApp", "NoConfirmationSmsSendService onHandleIntent");
        }
        String action = intent.getAction();
        if ("android.intent.action.RESPOND_VIA_MESSAGE".equals(action)) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                String stringExtra = intent.getStringExtra("conversation_id");
                String stringExtra2 = intent.getStringExtra("self_id");
                boolean booleanExtra = intent.getBooleanExtra("requires_mms", false);
                String b = m1280b(intent, "android.intent.extra.TEXT");
                String b2 = m1280b(intent, "android.intent.extra.SUBJECT");
                int i = extras.getInt("subscription", -1);
                Uri data = intent.getData();
                String n = data != null ? C1029y.m2448n(data) : null;
                if (!TextUtils.isEmpty(n) || !TextUtils.isEmpty(stringExtra)) {
                    if (extras.getBoolean("showUI", false)) {
                        startActivity(new Intent(this, ConversationListActivity.class));
                    } else if (!TextUtils.isEmpty(b)) {
                        if (TextUtils.isEmpty(stringExtra)) {
                            InsertNewMessageAction.m1377a(i, n, b, b2);
                        } else {
                            if (booleanExtra) {
                                if (Log.isLoggable("MessagingApp", 2)) {
                                    C1430e.m3628v("MessagingApp", "Auto-sending MMS message in conversation: " + stringExtra);
                                }
                                messageData = MessageData.m1753a(stringExtra, stringExtra2, b, b2);
                            } else {
                                if (Log.isLoggable("MessagingApp", 2)) {
                                    C1430e.m3628v("MessagingApp", "Auto-sending SMS message in conversation: " + stringExtra);
                                }
                                messageData = MessageData.m1757b(stringExtra, stringExtra2, b);
                            }
                            InsertNewMessageAction.m1379c(messageData);
                        }
                        UpdateMessageNotificationAction.m1460Le();
                    } else if (Log.isLoggable("MessagingApp", 2)) {
                        C1430e.m3628v("MessagingApp", "Message cannot be empty");
                    }
                } else if (Log.isLoggable("MessagingApp", 2)) {
                    C1430e.m3628v("MessagingApp", "Both conversationId and recipient(s) cannot be empty");
                }
            } else if (Log.isLoggable("MessagingApp", 2)) {
                C1430e.m3628v("MessagingApp", "Called to send SMS but no extras");
            }
        } else if (Log.isLoggable("MessagingApp", 2)) {
            C1430e.m3628v("MessagingApp", "NoConfirmationSmsSendService onHandleIntent wrong action: " + action);
        }
    }
}
