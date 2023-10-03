package com.android.messaging.p041ui.mediapicker;

import android.content.Context;
import android.view.OrientationEventListener;

/* renamed from: com.android.messaging.ui.mediapicker.r */
class C1348r extends OrientationEventListener {
    final /* synthetic */ C1352t this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C1348r(C1352t tVar, Context context) {
        super(context);
        this.this$0 = tVar;
    }

    public void onOrientationChanged(int i) {
        this.this$0.m3431Go();
    }
}
