package com.android.systemui.util;

import android.content.Context;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1784R$string;
import com.android.systemui.util.ProximitySensor;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ProximitySensor {
    private List<ProximitySensorListener> mListeners = new ArrayList();
    private boolean mNear;
    private final Sensor mSensor;
    private SensorEventListener mSensorEventListener = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        public synchronized void onSensorChanged(SensorEvent sensorEvent) {
            ProximitySensor.this.onSensorEvent(sensorEvent);
        }
    };
    private final AsyncSensorManager mSensorManager;
    private String mTag = null;
    private final float mThreshold;
    private final boolean mUsingBrightnessSensor;

    public interface ProximitySensorListener {
        void onProximitySensorEvent(ProximityEvent proximityEvent);
    }

    private void logDebug(String str) {
    }

    public ProximitySensor(Context context, AsyncSensorManager asyncSensorManager) {
        this.mSensorManager = asyncSensorManager;
        Sensor findCustomProxSensor = findCustomProxSensor(context, asyncSensorManager);
        if (findCustomProxSensor == null) {
            this.mUsingBrightnessSensor = false;
            findCustomProxSensor = asyncSensorManager.getDefaultSensor(8);
        } else {
            this.mUsingBrightnessSensor = true;
        }
        this.mSensor = findCustomProxSensor;
        Sensor sensor = this.mSensor;
        if (sensor == null) {
            this.mThreshold = 0.0f;
        } else if (this.mUsingBrightnessSensor) {
            this.mThreshold = getBrightnessSensorThreshold(context.getResources());
        } else {
            this.mThreshold = sensor.getMaximumRange();
        }
    }

    public void setTag(String str) {
        this.mTag = str;
    }

    @Deprecated
    public static Sensor findCustomProxSensor(Context context, SensorManager sensorManager) {
        String string = context.getString(C1784R$string.proximity_sensor_type);
        if (string.isEmpty()) {
            return null;
        }
        for (Sensor next : sensorManager.getSensorList(-1)) {
            if (string.equals(next.getStringType())) {
                return next;
            }
        }
        return null;
    }

    @Deprecated
    public static float getBrightnessSensorThreshold(Resources resources) {
        return resources.getFloat(C1775R$dimen.proximity_sensor_threshold);
    }

    public boolean getSensorAvailable() {
        return this.mSensor != null;
    }

    public boolean register(ProximitySensorListener proximitySensorListener) {
        if (!getSensorAvailable()) {
            return false;
        }
        logDebug("using brightness sensor? " + this.mUsingBrightnessSensor);
        this.mListeners.add(proximitySensorListener);
        if (this.mListeners.size() == 1) {
            logDebug("registering sensor listener");
            this.mSensorManager.registerListener(this.mSensorEventListener, this.mSensor, 1);
        }
        return true;
    }

    public void unregister(ProximitySensorListener proximitySensorListener) {
        this.mListeners.remove(proximitySensorListener);
        if (this.mListeners.size() == 0) {
            logDebug("unregistering sensor listener");
            this.mSensorManager.unregisterListener(this.mSensorEventListener);
        }
    }

    /* access modifiers changed from: private */
    public void onSensorEvent(SensorEvent sensorEvent) {
        boolean z = true;
        if (this.mUsingBrightnessSensor) {
            if (sensorEvent.values[0] > this.mThreshold) {
                z = false;
            }
            this.mNear = z;
        } else {
            if (sensorEvent.values[0] >= this.mThreshold) {
                z = false;
            }
            this.mNear = z;
        }
        this.mListeners.forEach(new Consumer(sensorEvent) {
            private final /* synthetic */ SensorEvent f$1;

            {
                this.f$1 = r2;
            }

            public final void accept(Object obj) {
                ProximitySensor.this.lambda$onSensorEvent$0$ProximitySensor(this.f$1, (ProximitySensor.ProximitySensorListener) obj);
            }
        });
    }

    public /* synthetic */ void lambda$onSensorEvent$0$ProximitySensor(SensorEvent sensorEvent, ProximitySensorListener proximitySensorListener) {
        proximitySensorListener.onProximitySensorEvent(new ProximityEvent(this.mNear, sensorEvent.timestamp));
    }

    public static class ProximityEvent {
        private final boolean mNear;
        private final long mTimestampNs;

        public ProximityEvent(boolean z, long j) {
            this.mNear = z;
            this.mTimestampNs = j;
        }

        public boolean getNear() {
            return this.mNear;
        }

        public long getTimestampNs() {
            return this.mTimestampNs;
        }
    }
}
