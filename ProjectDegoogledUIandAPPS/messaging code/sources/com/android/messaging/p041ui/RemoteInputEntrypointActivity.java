package com.android.messaging.p041ui;

import android.content.Intent;
import android.os.Bundle;
import com.android.messaging.datamodel.NoConfirmationSmsSendService;
import com.android.messaging.util.C1430e;

/* renamed from: com.android.messaging.ui.RemoteInputEntrypointActivity */
public class RemoteInputEntrypointActivity extends BaseBugleActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null) {
            C1430e.m3630w("MessagingApp", "No intent attached");
            setResult(0);
            finish();
            return;
        }
        String action = intent.getAction();
        if ("android.intent.action.SENDTO".equals(action)) {
            Intent intent2 = new Intent(this, NoConfirmationSmsSendService.class);
            intent2.setAction("android.intent.action.RESPOND_VIA_MESSAGE");
            intent2.putExtras(intent);
            intent2.setClipData(intent.getClipData());
            startService(intent2);
            setResult(-1);
        } else {
            C1430e.m3630w("MessagingApp", "Unrecognized intent action: " + action);
            setResult(0);
        }
        finish();
    }
}
