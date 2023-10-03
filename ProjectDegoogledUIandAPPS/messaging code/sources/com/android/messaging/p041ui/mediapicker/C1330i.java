package com.android.messaging.p041ui.mediapicker;

import android.view.MotionEvent;
import android.view.View;

/* renamed from: com.android.messaging.ui.mediapicker.i */
class C1330i implements View.OnTouchListener {
    final /* synthetic */ C1352t this$0;

    C1330i(C1352t tVar) {
        this.this$0 = tVar;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if ((motionEvent.getActionMasked() & 1) == 1) {
            this.this$0.f2164hI.setPreviewSize(view.getWidth(), view.getHeight());
            this.this$0.f2164hI.mo7790C(view.getLeft() + ((int) motionEvent.getX()), view.getTop() + ((int) motionEvent.getY()));
        }
        return true;
    }
}
