package com.android.messaging.p041ui.mediapicker;

import android.view.View;
import com.android.messaging.util.C1464na;

/* renamed from: com.android.messaging.ui.mediapicker.z */
class C1364z implements View.OnClickListener {
    final /* synthetic */ C1275C this$0;

    C1364z(C1275C c) {
        this.this$0 = c;
    }

    public void onClick(View view) {
        if (!(!C1352t.get().mo7949rj()) || C1464na.m3750Ha("android.permission.RECORD_AUDIO")) {
            this.this$0.m3212oo();
        } else {
            this.this$0.f2118Dj.requestPermissions(new String[]{"android.permission.RECORD_AUDIO"}, 3);
        }
    }
}
