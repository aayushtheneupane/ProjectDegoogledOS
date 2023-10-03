package com.android.incallui.answerproximitysensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.android.dialer.common.LogUtil;
import com.android.incallui.answerproximitysensor.AnswerProximityWakeLock;
import com.android.tools.p006r8.GeneratedOutlineSupport;

public class PseudoProximityWakeLock implements AnswerProximityWakeLock, SensorEventListener {
    private final Context context;
    private boolean isHeld;
    private AnswerProximityWakeLock.ScreenOnListener listener;
    private final Sensor proximitySensor;
    private final PseudoScreenState pseudoScreenState;

    public PseudoProximityWakeLock(Context context2, PseudoScreenState pseudoScreenState2) {
        this.context = context2;
        this.pseudoScreenState = pseudoScreenState2;
        pseudoScreenState2.setOn(true);
        this.proximitySensor = ((SensorManager) context2.getSystemService(SensorManager.class)).getDefaultSensor(8);
    }

    public void acquire() {
        this.isHeld = true;
        ((SensorManager) this.context.getSystemService(SensorManager.class)).registerListener(this, this.proximitySensor, 3);
    }

    public boolean isHeld() {
        return this.isHeld;
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        AnswerProximityWakeLock.ScreenOnListener screenOnListener;
        boolean z = sensorEvent.values[0] < sensorEvent.sensor.getMaximumRange();
        LogUtil.m9i("AnswerProximitySensor.PseudoProximityWakeLock.onSensorChanged", GeneratedOutlineSupport.outline10("near: ", z), new Object[0]);
        this.pseudoScreenState.setOn(!z);
        if (!z && (screenOnListener = this.listener) != null) {
            ((AnswerProximitySensor) screenOnListener).onScreenOn();
        }
    }

    public void release() {
        this.isHeld = false;
        ((SensorManager) this.context.getSystemService(SensorManager.class)).unregisterListener(this);
        this.pseudoScreenState.setOn(true);
    }

    public void setScreenOnListener(AnswerProximityWakeLock.ScreenOnListener screenOnListener) {
        this.listener = screenOnListener;
    }
}
