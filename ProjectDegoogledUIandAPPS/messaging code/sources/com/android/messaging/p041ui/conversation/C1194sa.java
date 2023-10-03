package com.android.messaging.p041ui.conversation;

import android.content.DialogInterface;
import android.text.TextUtils;
import com.android.messaging.R;
import com.android.messaging.util.C1486ya;

/* renamed from: com.android.messaging.ui.conversation.sa */
class C1194sa implements DialogInterface.OnClickListener {
    final /* synthetic */ C1198ua this$0;

    C1194sa(C1198ua uaVar) {
        this.this$0 = uaVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        String obj = this.this$0.f1892H.getText().toString();
        this.this$0.dismiss();
        if (!TextUtils.isEmpty(obj)) {
            C1198ua.m3039a(this.this$0, obj);
            C1486ya.m3847Oa(R.string.toast_after_setting_default_sms_app_for_message_send);
        }
    }
}
