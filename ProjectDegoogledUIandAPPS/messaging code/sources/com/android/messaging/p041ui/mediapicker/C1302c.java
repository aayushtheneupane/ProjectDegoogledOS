package com.android.messaging.p041ui.mediapicker;

import android.view.MotionEvent;
import android.view.View;

/* renamed from: com.android.messaging.ui.mediapicker.c */
class C1302c implements View.OnTouchListener {
    final /* synthetic */ AudioRecordView this$0;

    C1302c(AudioRecordView audioRecordView) {
        this.this$0 = audioRecordView;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() != 0) {
            return false;
        }
        this.this$0.onRecordButtonTouchDown();
        return false;
    }
}
