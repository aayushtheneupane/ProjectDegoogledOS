package com.android.messaging.p041ui.appsettings;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import androidx.core.view.PointerIconCompat;
import com.android.messaging.R;

/* renamed from: com.android.messaging.ui.appsettings.i */
class C1093i extends Handler {
    final /* synthetic */ C1094j this$0;

    /* synthetic */ C1093i(C1094j jVar, C1089e eVar) {
        this.this$0 = jVar;
    }

    public void handleMessage(Message message) {
        if (message.what == 2) {
            this.this$0.m2717wm();
            this.this$0.getPreferenceScreen().setEnabled(true);
            boolean unused = C1094j.f1736pa = false;
            Activity activity = this.this$0.getActivity();
            activity.dismissDialog(PointerIconCompat.TYPE_CONTEXT_MENU);
            Toast.makeText(activity, this.this$0.getResources().getString(R.string.restore_default_apn_completed), 1).show();
        }
    }
}
