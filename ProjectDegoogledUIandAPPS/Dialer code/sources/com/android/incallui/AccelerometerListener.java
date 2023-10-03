package com.android.incallui;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Message;

public class AccelerometerListener {
    Handler handler = new Handler() {
        public void handleMessage(Message message) {
            String str;
            if (message.what == 1234) {
                synchronized (this) {
                    int unused = AccelerometerListener.this.orientation = AccelerometerListener.this.pendingOrientation;
                    StringBuilder sb = new StringBuilder();
                    sb.append("orientation: ");
                    if (AccelerometerListener.this.orientation == 2) {
                        str = "horizontal";
                    } else {
                        str = AccelerometerListener.this.orientation == 1 ? "vertical" : "unknown";
                    }
                    sb.append(str);
                    sb.toString();
                    if (AccelerometerListener.this.listener != null) {
                        ((ProximitySensor) AccelerometerListener.this.listener).orientationChanged(AccelerometerListener.this.orientation);
                    }
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public OrientationListener listener;
    /* access modifiers changed from: private */
    public int orientation;
    /* access modifiers changed from: private */
    public int pendingOrientation;
    private Sensor sensor;
    SensorEventListener sensorListener = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            AccelerometerListener accelerometerListener = AccelerometerListener.this;
            float[] fArr = sensorEvent.values;
            accelerometerListener.onSensorEvent((double) fArr[0], (double) fArr[1], (double) fArr[2]);
        }
    };
    private SensorManager sensorManager;

    public interface OrientationListener {
    }

    public AccelerometerListener(Context context) {
        this.sensorManager = (SensorManager) context.getSystemService("sensor");
        this.sensor = this.sensorManager.getDefaultSensor(1);
    }

    /* access modifiers changed from: private */
    public void onSensorEvent(double d, double d2, double d3) {
        if (d != 0.0d && d2 != 0.0d && d3 != 0.0d) {
            setOrientation((Math.atan2(Math.hypot(d, d2), d3) * 180.0d) / 3.141592653589793d > 50.0d ? 1 : 2);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002d, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setOrientation(int r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            int r0 = r4.pendingOrientation     // Catch:{ all -> 0x002e }
            if (r0 != r5) goto L_0x0007
            monitor-exit(r4)     // Catch:{ all -> 0x002e }
            return
        L_0x0007:
            android.os.Handler r0 = r4.handler     // Catch:{ all -> 0x002e }
            r1 = 1234(0x4d2, float:1.729E-42)
            r0.removeMessages(r1)     // Catch:{ all -> 0x002e }
            int r0 = r4.orientation     // Catch:{ all -> 0x002e }
            if (r0 == r5) goto L_0x0029
            r4.pendingOrientation = r5     // Catch:{ all -> 0x002e }
            android.os.Handler r0 = r4.handler     // Catch:{ all -> 0x002e }
            android.os.Message r0 = r0.obtainMessage(r1)     // Catch:{ all -> 0x002e }
            r1 = 1
            if (r5 != r1) goto L_0x0020
            r5 = 100
            goto L_0x0022
        L_0x0020:
            r5 = 500(0x1f4, float:7.0E-43)
        L_0x0022:
            android.os.Handler r1 = r4.handler     // Catch:{ all -> 0x002e }
            long r2 = (long) r5     // Catch:{ all -> 0x002e }
            r1.sendMessageDelayed(r0, r2)     // Catch:{ all -> 0x002e }
            goto L_0x002c
        L_0x0029:
            r5 = 0
            r4.pendingOrientation = r5     // Catch:{ all -> 0x002e }
        L_0x002c:
            monitor-exit(r4)     // Catch:{ all -> 0x002e }
            return
        L_0x002e:
            r5 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x002e }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.incallui.AccelerometerListener.setOrientation(int):void");
    }

    public void enable(boolean z) {
        "enable(" + z + ")";
        synchronized (this) {
            if (z) {
                this.orientation = 0;
                this.pendingOrientation = 0;
                this.sensorManager.registerListener(this.sensorListener, this.sensor, 3);
            } else {
                this.sensorManager.unregisterListener(this.sensorListener);
                this.handler.removeMessages(1234);
            }
        }
    }

    public void setListener(OrientationListener orientationListener) {
        this.listener = orientationListener;
    }
}
