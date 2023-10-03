package com.android.incallui.answer.impl.classifier;

import android.util.SparseArray;
import android.view.MotionEvent;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

class ClassifierData {
    private SparseArray<Stroke> currentStrokes = new SparseArray<>();
    private final float dpi;
    private ArrayList<Stroke> endingStrokes = new ArrayList<>();

    public ClassifierData(float f, float f2) {
        this.dpi = f;
    }

    /* access modifiers changed from: package-private */
    public void cleanUp(MotionEvent motionEvent) {
        this.endingStrokes.clear();
        int actionMasked = motionEvent.getActionMasked();
        for (int i = 0; i < motionEvent.getPointerCount(); i++) {
            int pointerId = motionEvent.getPointerId(i);
            if (actionMasked == 1 || actionMasked == 3 || (actionMasked == 6 && i == motionEvent.getActionIndex())) {
                this.currentStrokes.remove(pointerId);
            }
        }
    }

    public ArrayList<Stroke> getEndingStrokes() {
        return this.endingStrokes;
    }

    public Stroke getStroke(int i) {
        return this.currentStrokes.get(i);
    }

    public void update(MotionEvent motionEvent) {
        this.endingStrokes.clear();
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.currentStrokes.clear();
        }
        for (int i = 0; i < motionEvent.getPointerCount(); i++) {
            int pointerId = motionEvent.getPointerId(i);
            if (this.currentStrokes.get(pointerId) == null) {
                this.currentStrokes.put(pointerId, new Stroke(TimeUnit.MILLISECONDS.toNanos(motionEvent.getEventTime()), this.dpi));
            }
            this.currentStrokes.get(pointerId).addPoint(motionEvent.getX(i), motionEvent.getY(i), TimeUnit.MILLISECONDS.toNanos(motionEvent.getEventTime()));
            if (actionMasked == 1 || actionMasked == 3 || (actionMasked == 6 && i == motionEvent.getActionIndex())) {
                this.endingStrokes.add(getStroke(pointerId));
            }
        }
    }
}
