package com.android.systemui.classifier.brightline;

import android.provider.DeviceConfig;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import java.util.List;

class DistanceClassifier extends FalsingClassifier {
    private DistanceVectors mCachedDistance;
    private boolean mDistanceDirty;
    /* access modifiers changed from: private */
    public final float mHorizontalFlingThresholdPx;
    /* access modifiers changed from: private */
    public final float mHorizontalSwipeThresholdPx;
    /* access modifiers changed from: private */
    public final float mVelocityToDistanceMultiplier = DeviceConfig.getFloat("systemui", "brightline_falsing_distance_velcoity_to_distance", 80.0f);
    /* access modifiers changed from: private */
    public final float mVerticalFlingThresholdPx;
    /* access modifiers changed from: private */
    public final float mVerticalSwipeThresholdPx;

    DistanceClassifier(FalsingDataProvider falsingDataProvider) {
        super(falsingDataProvider);
        float f = DeviceConfig.getFloat("systemui", "brightline_falsing_distance_horizontal_fling_threshold_in", 1.0f);
        float f2 = DeviceConfig.getFloat("systemui", "brightline_falsing_distance_vertical_fling_threshold_in", 1.0f);
        float f3 = DeviceConfig.getFloat("systemui", "brightline_falsing_distance_horizontal_swipe_threshold_in", 3.0f);
        float f4 = DeviceConfig.getFloat("systemui", "brightline_falsing_distance_horizontal_swipe_threshold_in", 3.0f);
        float f5 = DeviceConfig.getFloat("systemui", "brightline_falsing_distance_screen_fraction_max_distance", 0.8f);
        this.mHorizontalFlingThresholdPx = Math.min(((float) getWidthPixels()) * f5, f * getXdpi());
        this.mVerticalFlingThresholdPx = Math.min(((float) getHeightPixels()) * f5, f2 * getYdpi());
        this.mHorizontalSwipeThresholdPx = Math.min(((float) getWidthPixels()) * f5, f3 * getXdpi());
        this.mVerticalSwipeThresholdPx = Math.min(((float) getHeightPixels()) * f5, f4 * getYdpi());
        this.mDistanceDirty = true;
    }

    private DistanceVectors getDistances() {
        if (this.mDistanceDirty) {
            this.mCachedDistance = calculateDistances();
            this.mDistanceDirty = false;
        }
        return this.mCachedDistance;
    }

    private DistanceVectors calculateDistances() {
        VelocityTracker obtain = VelocityTracker.obtain();
        List<MotionEvent> recentMotionEvents = getRecentMotionEvents();
        if (recentMotionEvents.size() < 3) {
            "Only " + recentMotionEvents.size() + " motion events recorded.";
            return new DistanceVectors(0.0f, 0.0f, 0.0f, 0.0f);
        }
        for (MotionEvent addMovement : recentMotionEvents) {
            obtain.addMovement(addMovement);
        }
        obtain.computeCurrentVelocity(1);
        float xVelocity = obtain.getXVelocity();
        float yVelocity = obtain.getYVelocity();
        obtain.recycle();
        float x = getLastMotionEvent().getX() - getFirstMotionEvent().getX();
        float y = getLastMotionEvent().getY() - getFirstMotionEvent().getY();
        FalsingClassifier.logInfo("dX: " + x + " dY: " + y + " xV: " + xVelocity + " yV: " + yVelocity);
        return new DistanceVectors(x, y, xVelocity, yVelocity);
    }

    public void onTouchEvent(MotionEvent motionEvent) {
        this.mDistanceDirty = true;
    }

    public boolean isFalseTouch() {
        return !getDistances().getPassedFlingThreshold();
    }

    /* access modifiers changed from: package-private */
    public boolean isLongSwipe() {
        boolean passedDistanceThreshold = getDistances().getPassedDistanceThreshold();
        "Is longSwipe? " + passedDistanceThreshold;
        return passedDistanceThreshold;
    }

    private class DistanceVectors {
        final float mDx;
        final float mDy;
        private final float mVx;
        private final float mVy;

        DistanceVectors(float f, float f2, float f3, float f4) {
            this.mDx = f;
            this.mDy = f2;
            this.mVx = f3;
            this.mVy = f4;
        }

        /* access modifiers changed from: package-private */
        public boolean getPassedDistanceThreshold() {
            if (DistanceClassifier.this.isHorizontal()) {
                "Horizontal swipe distance: " + Math.abs(this.mDx);
                "Threshold: " + DistanceClassifier.this.mHorizontalSwipeThresholdPx;
                if (Math.abs(this.mDx) >= DistanceClassifier.this.mHorizontalSwipeThresholdPx) {
                    return true;
                }
                return false;
            }
            "Vertical swipe distance: " + Math.abs(this.mDy);
            "Threshold: " + DistanceClassifier.this.mVerticalSwipeThresholdPx;
            if (Math.abs(this.mDy) >= DistanceClassifier.this.mVerticalSwipeThresholdPx) {
                return true;
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        public boolean getPassedFlingThreshold() {
            float access$200 = this.mDx + (this.mVx * DistanceClassifier.this.mVelocityToDistanceMultiplier);
            float access$2002 = this.mDy + (this.mVy * DistanceClassifier.this.mVelocityToDistanceMultiplier);
            if (DistanceClassifier.this.isHorizontal()) {
                "Horizontal swipe and fling distance: " + this.mDx + ", " + (this.mVx * DistanceClassifier.this.mVelocityToDistanceMultiplier);
                "Threshold: " + DistanceClassifier.this.mHorizontalFlingThresholdPx;
                if (Math.abs(access$200) >= DistanceClassifier.this.mHorizontalFlingThresholdPx) {
                    return true;
                }
                return false;
            }
            "Vertical swipe and fling distance: " + this.mDy + ", " + (this.mVy * DistanceClassifier.this.mVelocityToDistanceMultiplier);
            "Threshold: " + DistanceClassifier.this.mVerticalFlingThresholdPx;
            if (Math.abs(access$2002) >= DistanceClassifier.this.mVerticalFlingThresholdPx) {
                return true;
            }
            return false;
        }
    }
}
