package com.android.dialer.app.calllog;

import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.app.AppCompatActivity;
import com.android.dialer.util.PermissionsUtil;

public class CallLogNotificationsActivity extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (PermissionsUtil.hasPermission(this, "android.permission.READ_CALL_LOG")) {
            String action = intent.getAction();
            char c = 65535;
            if (action.hashCode() == -788737331 && action.equals("com.android.dialer.calllog.SEND_SMS_FROM_MISSED_CALL_NOTIFICATION")) {
                c = 0;
            }
            if (c != 0) {
                "could not handle: " + intent;
            } else {
                MissedCallNotifier.getInstance(this).sendSmsFromMissedCall(intent.getStringExtra("MISSED_CALL_NUMBER"), intent.getData());
            }
            finish();
        }
    }
}
