package com.android.systemui.p006qs.tiles;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.ImageView;
import com.android.internal.util.havoc.Utils;
import com.android.systemui.C1771R$array;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1784R$string;
import com.android.systemui.p006qs.QSHost;
import com.android.systemui.p006qs.tileimpl.QSTileImpl;
import com.android.systemui.plugins.p005qs.QSIconView;
import com.android.systemui.plugins.p005qs.QSTile;

/* renamed from: com.android.systemui.qs.tiles.CompassTile */
public class CompassTile extends QSTileImpl<QSTile.BooleanState> implements SensorEventListener {
    private float[] mAcceleration;
    private Sensor mAccelerationSensor = this.mSensorManager.getDefaultSensor(1);
    private boolean mActive = false;
    private float[] mGeomagnetic;
    private Sensor mGeomagneticFieldSensor = this.mSensorManager.getDefaultSensor(2);
    private ImageView mImage;
    private boolean mListeningSensors;
    private SensorManager mSensorManager = ((SensorManager) this.mContext.getSystemService("sensor"));

    public Intent getLongClickIntent() {
        return null;
    }

    public int getMetricsCategory() {
        return 1999;
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public CompassTile(QSHost qSHost) {
        super(qSHost);
    }

    public QSTile.BooleanState newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        booleanState.handlesLongClick = false;
        return booleanState;
    }

    /* access modifiers changed from: protected */
    public void handleDestroy() {
        super.handleDestroy();
        setListeningSensors(false);
        this.mSensorManager = null;
        this.mImage = null;
    }

    public QSIconView createTileView(Context context) {
        QSIconView createTileView = super.createTileView(context);
        this.mImage = (ImageView) createTileView.findViewById(16908294);
        return createTileView;
    }

    /* access modifiers changed from: protected */
    public void handleClick() {
        this.mActive = !this.mActive;
        refreshState();
        setListeningSensors(this.mActive);
    }

    public void handleLongClick() {
        handleClick();
    }

    private void setListeningSensors(boolean z) {
        if (z != this.mListeningSensors) {
            this.mListeningSensors = z;
            if (this.mListeningSensors) {
                this.mSensorManager.registerListener(this, this.mAccelerationSensor, 1);
                this.mSensorManager.registerListener(this, this.mGeomagneticFieldSensor, 1);
                return;
            }
            this.mSensorManager.unregisterListener(this);
        }
    }

    public CharSequence getTileLabel() {
        return this.mContext.getString(C1784R$string.quick_settings_compass_label);
    }

    /* access modifiers changed from: protected */
    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        Float valueOf = Float.valueOf(obj == null ? 0.0f : ((Float) obj).floatValue());
        booleanState.value = this.mActive;
        booleanState.icon = QSTileImpl.ResourceIcon.get(C1776R$drawable.ic_qs_compass);
        if (booleanState.value) {
            booleanState.state = 2;
            if (obj != null) {
                booleanState.label = formatValueWithCardinalDirection(valueOf.floatValue());
                float floatValue = (360.0f - valueOf.floatValue()) - this.mImage.getRotation();
                if (floatValue > 180.0f) {
                    floatValue -= 360.0f;
                }
                ImageView imageView = this.mImage;
                imageView.setRotation(imageView.getRotation() + (floatValue / 2.0f));
            } else {
                booleanState.label = this.mContext.getString(C1784R$string.quick_settings_compass_init);
                this.mImage.setRotation(0.0f);
            }
            booleanState.contentDescription = this.mContext.getString(C1784R$string.accessibility_quick_settings_compass_on);
            return;
        }
        booleanState.label = this.mContext.getString(C1784R$string.quick_settings_compass_label);
        booleanState.contentDescription = this.mContext.getString(C1784R$string.accessibility_quick_settings_compass_off);
        booleanState.state = 1;
        ImageView imageView2 = this.mImage;
        if (imageView2 != null) {
            imageView2.setRotation(0.0f);
        }
    }

    /* access modifiers changed from: protected */
    public String composeChangeAnnouncement() {
        if (((QSTile.BooleanState) this.mState).value) {
            return this.mContext.getString(C1784R$string.accessibility_quick_settings_compass_on);
        }
        return this.mContext.getString(C1784R$string.accessibility_quick_settings_compass_off);
    }

    public boolean isAvailable() {
        return Utils.deviceHasCompass(this.mContext);
    }

    public void handleSetListening(boolean z) {
        if (!z) {
            setListeningSensors(false);
            this.mActive = false;
        }
    }

    private String formatValueWithCardinalDirection(float f) {
        String[] stringArray = this.mContext.getResources().getStringArray(C1771R$array.cardinal_directions);
        return this.mContext.getString(C1784R$string.quick_settings_compass_value, new Object[]{Float.valueOf(f), stringArray[((int) (Math.floor(((((double) f) - 22.5d) % 360.0d) / 45.0d) + 1.0d)) % 8]});
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        float[] fArr;
        float[] fArr2;
        float[] fArr3;
        if (sensorEvent.sensor.getType() == 1) {
            if (this.mAcceleration == null) {
                this.mAcceleration = (float[]) sensorEvent.values.clone();
            }
            fArr = this.mAcceleration;
        } else {
            if (this.mGeomagnetic == null) {
                this.mGeomagnetic = (float[]) sensorEvent.values.clone();
            }
            fArr = this.mGeomagnetic;
        }
        for (int i = 0; i < 3; i++) {
            fArr[i] = (fArr[i] * 0.97f) + (sensorEvent.values[i] * 0.029999971f);
        }
        if (this.mActive && this.mListeningSensors && (fArr2 = this.mAcceleration) != null && (fArr3 = this.mGeomagnetic) != null) {
            float[] fArr4 = new float[9];
            if (SensorManager.getRotationMatrix(fArr4, new float[9], fArr2, fArr3)) {
                float[] fArr5 = new float[3];
                SensorManager.getOrientation(fArr4, fArr5);
                refreshState(Float.valueOf((Float.valueOf((float) Math.toDegrees((double) fArr5[0])).floatValue() + 360.0f) % 360.0f));
            }
        }
    }
}
