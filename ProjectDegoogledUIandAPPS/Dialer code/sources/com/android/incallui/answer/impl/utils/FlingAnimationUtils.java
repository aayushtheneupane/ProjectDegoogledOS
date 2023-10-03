package com.android.incallui.answer.impl.utils;

import android.animation.Animator;
import android.content.Context;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;

public class FlingAnimationUtils {
    private AnimatorProperties animatorProperties = new AnimatorProperties((C06981) null);
    private float highVelocityPxPerSecond;
    private Interpolator linearOutSlowIn;
    private float maxLengthSeconds;
    private float minVelocityPxPerSecond;

    private static class AnimatorProperties {
        long duration;
        Interpolator interpolator;

        /* synthetic */ AnimatorProperties(C06981 r1) {
        }
    }

    private static final class InterpolatorInterpolator implements Interpolator {
        private Interpolator crossfader;
        private Interpolator interpolator1;
        private Interpolator interpolator2;

        InterpolatorInterpolator(Interpolator interpolator, Interpolator interpolator3, Interpolator interpolator4) {
            this.interpolator1 = interpolator;
            this.interpolator2 = interpolator3;
            this.crossfader = interpolator4;
        }

        public float getInterpolation(float f) {
            float interpolation = this.crossfader.getInterpolation(f);
            float interpolation2 = this.interpolator1.getInterpolation(f);
            return (this.interpolator2.getInterpolation(f) * interpolation) + (interpolation2 * (1.0f - interpolation));
        }
    }

    private static final class VelocityInterpolator implements Interpolator {
        private float diff;
        private float durationSeconds;
        private float velocity;

        /* synthetic */ VelocityInterpolator(float f, float f2, float f3, C06981 r4) {
            this.durationSeconds = f;
            this.velocity = f2;
            this.diff = f3;
        }

        public float getInterpolation(float f) {
            return ((f * this.durationSeconds) * this.velocity) / this.diff;
        }
    }

    public FlingAnimationUtils(Context context, float f) {
        this.maxLengthSeconds = f;
        this.linearOutSlowIn = new PathInterpolator(0.0f, 0.0f, 0.35f, 1.0f);
        this.minVelocityPxPerSecond = context.getResources().getDisplayMetrics().density * 250.0f;
        this.highVelocityPxPerSecond = context.getResources().getDisplayMetrics().density * 3000.0f;
    }

    public void apply(Animator animator, float f, float f2, float f3) {
        float f4 = f2 - f;
        float sqrt = (float) (Math.sqrt((double) (Math.abs(f4) / Math.abs(f4))) * ((double) this.maxLengthSeconds));
        float abs = Math.abs(f4);
        float abs2 = Math.abs(f3);
        float f5 = (2.857143f * abs) / abs2;
        if (f5 <= sqrt) {
            this.animatorProperties.interpolator = this.linearOutSlowIn;
            sqrt = f5;
        } else if (abs2 >= this.minVelocityPxPerSecond) {
            VelocityInterpolator velocityInterpolator = new VelocityInterpolator(sqrt, abs2, abs, (C06981) null);
            AnimatorProperties animatorProperties2 = this.animatorProperties;
            Interpolator interpolator = this.linearOutSlowIn;
            animatorProperties2.interpolator = new InterpolatorInterpolator(velocityInterpolator, interpolator, interpolator);
        } else {
            this.animatorProperties.interpolator = Interpolators.FAST_OUT_SLOW_IN;
        }
        AnimatorProperties animatorProperties3 = this.animatorProperties;
        animatorProperties3.duration = (long) (sqrt * 1000.0f);
        animator.setDuration(animatorProperties3.duration);
        animator.setInterpolator(animatorProperties3.interpolator);
    }

    public void applyDismissing(Animator animator, float f, float f2, float f3, float f4) {
        float f5 = f2 - f;
        float pow = (float) (Math.pow((double) (Math.abs(f5) / f4), 0.5d) * ((double) this.maxLengthSeconds));
        float abs = Math.abs(f5);
        float abs2 = Math.abs(f3);
        float f6 = this.minVelocityPxPerSecond;
        float max = Math.max(0.0f, Math.min(1.0f, (abs2 - f6) / (this.highVelocityPxPerSecond - f6)));
        float f7 = (max * 0.5f) + ((1.0f - max) * 0.4f);
        PathInterpolator pathInterpolator = new PathInterpolator(0.0f, 0.0f, 0.5f, f7);
        float f8 = ((f7 / 0.5f) * abs) / abs2;
        if (f8 <= pow) {
            this.animatorProperties.interpolator = pathInterpolator;
            pow = f8;
        } else if (abs2 >= this.minVelocityPxPerSecond) {
            this.animatorProperties.interpolator = new InterpolatorInterpolator(new VelocityInterpolator(pow, abs2, abs, (C06981) null), pathInterpolator, this.linearOutSlowIn);
        } else {
            this.animatorProperties.interpolator = Interpolators.FAST_OUT_LINEAR_IN;
        }
        AnimatorProperties animatorProperties2 = this.animatorProperties;
        animatorProperties2.duration = (long) (pow * 1000.0f);
        animator.setDuration(animatorProperties2.duration);
        animator.setInterpolator(animatorProperties2.interpolator);
    }

    public float getMinVelocityPxPerSecond() {
        return this.minVelocityPxPerSecond;
    }
}
