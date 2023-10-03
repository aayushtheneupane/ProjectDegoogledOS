package com.android.incallui.answer.impl.classifier;

import android.view.MotionEvent;

class PointerCountClassifier extends GestureClassifier {
    private int count = 0;

    public float getFalseTouchEvaluation() {
        int i = this.count - 1;
        return (float) (i * i);
    }

    public void onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.count = 1;
        }
        if (actionMasked == 5) {
            this.count++;
        }
    }
}
