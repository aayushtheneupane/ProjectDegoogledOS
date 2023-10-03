package com.android.messaging.p041ui.conversation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.android.messaging.datamodel.data.C0931n;
import com.android.messaging.util.C1424b;

/* renamed from: com.android.messaging.ui.conversation.A */
class C1129A extends BroadcastReceiver {
    final /* synthetic */ C1146O this$0;

    C1129A(C1146O o) {
        this.this$0 = o;
    }

    public void onReceive(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("conversation_id");
        String stringExtra2 = intent.getStringExtra("conversation_self_id");
        C1424b.m3594t(stringExtra);
        C1424b.m3594t(stringExtra2);
        if (this.this$0.isBound() && TextUtils.equals(((C0931n) this.this$0.mBinding.getData()).mo6458Ue(), stringExtra)) {
            this.this$0.f1828Ha.mo7323v(stringExtra2);
        }
    }
}
