package com.android.systemui.classifier.brightline;

import android.provider.DeviceConfig;
import android.view.MotionEvent;
import com.android.systemui.util.ProximitySensor;

class ProximityClassifier extends FalsingClassifier {
    private final DistanceClassifier mDistanceClassifier;
    private long mGestureStartTimeNs;
    private boolean mNear;
    private long mNearDurationNs;
    private final float mPercentCoveredThreshold = DeviceConfig.getFloat("systemui", "brightline_falsing_proximity_percent_covered_threshold", 0.1f);
    private float mPercentNear;
    private long mPrevNearTimeNs;

    ProximityClassifier(DistanceClassifier distanceClassifier, FalsingDataProvider falsingDataProvider) {
        super(falsingDataProvider);
        this.mDistanceClassifier = distanceClassifier;
    }

    /* access modifiers changed from: package-private */
    public void onSessionStarted() {
        this.mPrevNearTimeNs = 0;
        this.mPercentNear = 0.0f;
    }

    /* access modifiers changed from: package-private */
    public void onSessionEnded() {
        this.mPrevNearTimeNs = 0;
        this.mPercentNear = 0.0f;
    }

    public void onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.mGestureStartTimeNs = motionEvent.getEventTimeNano();
            if (this.mPrevNearTimeNs > 0) {
                this.mPrevNearTimeNs = motionEvent.getEventTimeNano();
            }
            "Gesture start time: " + this.mGestureStartTimeNs;
            this.mNearDurationNs = 0;
        }
        if (actionMasked == 1 || actionMasked == 3) {
            update(this.mNear, motionEvent.getEventTimeNano());
            long eventTimeNano = motionEvent.getEventTimeNano() - this.mGestureStartTimeNs;
            "Gesture duration, Proximity duration: " + eventTimeNano + ", " + this.mNearDurationNs;
            if (eventTimeNano == 0) {
                this.mPercentNear = this.mNear ? 1.0f : 0.0f;
            } else {
                this.mPercentNear = ((float) this.mNearDurationNs) / ((float) eventTimeNano);
            }
        }
    }

    public void onProximityEvent(ProximitySensor.ProximityEvent proximityEvent) {
        boolean near = proximityEvent.getNear();
        long timestampNs = proximityEvent.getTimestampNs();
        "Sensor is: " + near + " at time " + timestampNs;
        update(near, timestampNs);
    }

    public boolean isFalseTouch() {
        if (getInteractionType() == 0) {
            return false;
        }
        FalsingClassifier.logInfo("Percent of gesture in proximity: " + this.mPercentNear);
        if (this.mPercentNear > this.mPercentCoveredThreshold) {
            return !this.mDistanceClassifier.isLongSwipe();
        }
        return false;
    }

    private void update(boolean z, long j) {
        long j2 = this.mPrevNearTimeNs;
        if (j2 != 0 && j > j2 && this.mNear) {
            this.mNearDurationNs += j - j2;
            "Updating duration: " + this.mNearDurationNs;
        }
        if (z) {
            "Set prevNearTimeNs: " + j;
            this.mPrevNearTimeNs = j;
        }
        this.mNear = z;
    }
}
