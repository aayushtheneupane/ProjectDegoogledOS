package android.support.design.animation;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

public class MotionTiming {
    private long delay = 0;
    private long duration = 300;
    private TimeInterpolator interpolator = null;
    private int repeatCount = 0;
    private int repeatMode = 1;

    public MotionTiming(long j, long j2) {
        this.delay = j;
        this.duration = j2;
    }

    static MotionTiming createFromAnimator(ValueAnimator valueAnimator) {
        long startDelay = valueAnimator.getStartDelay();
        long duration2 = valueAnimator.getDuration();
        TimeInterpolator interpolator2 = valueAnimator.getInterpolator();
        if ((interpolator2 instanceof AccelerateDecelerateInterpolator) || interpolator2 == null) {
            interpolator2 = AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR;
        } else if (interpolator2 instanceof AccelerateInterpolator) {
            interpolator2 = AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR;
        } else if (interpolator2 instanceof DecelerateInterpolator) {
            interpolator2 = AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR;
        }
        MotionTiming motionTiming = new MotionTiming(startDelay, duration2, interpolator2);
        motionTiming.repeatCount = valueAnimator.getRepeatCount();
        motionTiming.repeatMode = valueAnimator.getRepeatMode();
        return motionTiming;
    }

    public void apply(Animator animator) {
        animator.setStartDelay(this.delay);
        animator.setDuration(this.duration);
        animator.setInterpolator(getInterpolator());
        if (animator instanceof ValueAnimator) {
            ValueAnimator valueAnimator = (ValueAnimator) animator;
            valueAnimator.setRepeatCount(this.repeatCount);
            valueAnimator.setRepeatMode(this.repeatMode);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || MotionTiming.class != obj.getClass()) {
            return false;
        }
        MotionTiming motionTiming = (MotionTiming) obj;
        if (this.delay == motionTiming.delay && this.duration == motionTiming.duration && this.repeatCount == motionTiming.repeatCount && this.repeatMode == motionTiming.repeatMode) {
            return getInterpolator().getClass().equals(motionTiming.getInterpolator().getClass());
        }
        return false;
    }

    public long getDelay() {
        return this.delay;
    }

    public long getDuration() {
        return this.duration;
    }

    public TimeInterpolator getInterpolator() {
        TimeInterpolator timeInterpolator = this.interpolator;
        return timeInterpolator != null ? timeInterpolator : AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR;
    }

    public int hashCode() {
        long j = this.delay;
        long j2 = this.duration;
        return ((((getInterpolator().getClass().hashCode() + (((((int) (j ^ (j >>> 32))) * 31) + ((int) ((j2 >>> 32) ^ j2))) * 31)) * 31) + this.repeatCount) * 31) + this.repeatMode;
    }

    public String toString() {
        return 10 + MotionTiming.class.getName() + '{' + Integer.toHexString(System.identityHashCode(this)) + " delay: " + this.delay + " duration: " + this.duration + " interpolator: " + getInterpolator().getClass() + " repeatCount: " + this.repeatCount + " repeatMode: " + this.repeatMode + "}\n";
    }

    public MotionTiming(long j, long j2, TimeInterpolator timeInterpolator) {
        this.delay = j;
        this.duration = j2;
        this.interpolator = timeInterpolator;
    }
}
