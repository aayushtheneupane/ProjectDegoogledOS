package com.android.incallui.answer.impl.classifier;

import android.content.Context;
import android.hardware.SensorEvent;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import com.android.dialer.configprovider.ConfigProviderComponent;
import com.android.dialer.configprovider.SharedPrefConfigProvider;

class HumanInteractionClassifier extends Classifier {
    private final boolean enabled;
    private final GestureClassifier[] gestureClassifiers;
    private final HistoryEvaluator historyEvaluator = new HistoryEvaluator();
    private final StrokeClassifier[] strokeClassifiers;

    HumanInteractionClassifier(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.classifierData = new ClassifierData((displayMetrics.xdpi + displayMetrics.ydpi) / 2.0f, (float) displayMetrics.heightPixels);
        this.enabled = ((SharedPrefConfigProvider) ConfigProviderComponent.get(context).getConfigProvider()).getBoolean("answer_false_touch_detection_enabled", true);
        this.strokeClassifiers = new StrokeClassifier[]{new AnglesClassifier(this.classifierData), new SpeedClassifier(), new DurationCountClassifier(), new EndPointRatioClassifier(this.classifierData), new EndPointLengthClassifier(this.classifierData), new AccelerationClassifier(this.classifierData), new SpeedAnglesClassifier(this.classifierData), new LengthCountClassifier(), new DirectionClassifier()};
        this.gestureClassifiers = new GestureClassifier[]{new PointerCountClassifier(), new ProximityClassifier()};
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        for (StrokeClassifier onSensorChanged : this.strokeClassifiers) {
            onSensorChanged.onSensorChanged(sensorEvent);
        }
        for (GestureClassifier onSensorChanged2 : this.gestureClassifiers) {
            onSensorChanged2.onSensorChanged(sensorEvent);
        }
    }

    public void onTouchEvent(MotionEvent motionEvent) {
        float f;
        this.classifierData.update(motionEvent);
        for (StrokeClassifier onTouchEvent : this.strokeClassifiers) {
            onTouchEvent.onTouchEvent(motionEvent);
        }
        for (GestureClassifier onTouchEvent2 : this.gestureClassifiers) {
            onTouchEvent2.onTouchEvent(motionEvent);
        }
        int size = this.classifierData.getEndingStrokes().size();
        int i = 0;
        while (true) {
            f = 0.0f;
            if (i >= size) {
                break;
            }
            Stroke stroke = this.classifierData.getEndingStrokes().get(i);
            float f2 = 0.0f;
            for (StrokeClassifier falseTouchEvaluation : this.strokeClassifiers) {
                f2 += falseTouchEvaluation.getFalseTouchEvaluation(stroke);
            }
            this.historyEvaluator.addStroke(f2);
            i++;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1 || actionMasked == 3) {
            for (GestureClassifier falseTouchEvaluation2 : this.gestureClassifiers) {
                f += falseTouchEvaluation2.getFalseTouchEvaluation();
            }
            this.historyEvaluator.addGesture(f);
        }
        this.classifierData.cleanUp(motionEvent);
    }
}
