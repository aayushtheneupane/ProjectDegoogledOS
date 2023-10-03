package com.android.dialer.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;

public class AnimUtils {
    public static final Interpolator EASE_IN = new PathInterpolator(0.0f, 0.0f, 0.2f, 1.0f);
    public static final Interpolator EASE_OUT = new PathInterpolator(0.4f, 0.0f, 1.0f, 1.0f);
    public static final Interpolator EASE_OUT_EASE_IN = new PathInterpolator(0.4f, 0.0f, 0.2f, 1.0f);

    public static class AnimationCallback {
        public void onAnimationCancel() {
        }

        public void onAnimationEnd() {
            throw null;
        }
    }

    public static void crossFadeViews(View view, View view2, int i) {
        fadeIn(view, i);
        fadeOut(view2, i, (AnimationCallback) null);
    }

    public static void fadeIn(final View view, int i) {
        view.setAlpha(0.0f);
        ViewPropertyAnimator animate = view.animate();
        animate.cancel();
        animate.setStartDelay((long) 0);
        animate.alpha(1.0f).withLayer().setListener(new AnimatorListenerAdapter((AnimationCallback) null) {
            public void onAnimationCancel(Animator animator) {
                view.setAlpha(1.0f);
                AnimationCallback animationCallback = null;
                if (animationCallback != null) {
                    animationCallback.onAnimationCancel();
                }
            }

            public void onAnimationEnd(Animator animator) {
                AnimationCallback animationCallback = null;
                if (animationCallback != null) {
                    animationCallback.onAnimationEnd();
                }
            }

            public void onAnimationStart(Animator animator) {
                view.setVisibility(0);
            }
        });
        if (i != -1) {
            animate.setDuration((long) i);
        }
        animate.start();
    }

    public static void fadeOut(final View view, int i, final AnimationCallback animationCallback) {
        view.setAlpha(1.0f);
        ViewPropertyAnimator animate = view.animate();
        animate.cancel();
        animate.alpha(0.0f).withLayer().setListener(new AnimatorListenerAdapter() {
            public void onAnimationCancel(Animator animator) {
                view.setVisibility(8);
                view.setAlpha(0.0f);
                AnimationCallback animationCallback = animationCallback;
                if (animationCallback != null) {
                    animationCallback.onAnimationCancel();
                }
            }

            public void onAnimationEnd(Animator animator) {
                view.setVisibility(8);
                AnimationCallback animationCallback = animationCallback;
                if (animationCallback != null) {
                    animationCallback.onAnimationEnd();
                }
            }
        });
        if (i != -1) {
            animate.setDuration((long) i);
        }
        animate.start();
    }
}
