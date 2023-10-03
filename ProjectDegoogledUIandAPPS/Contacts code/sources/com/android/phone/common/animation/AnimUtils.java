package com.android.phone.common.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;
import com.android.contacts.ContactPhotoManager;
import com.android.phone.common.compat.PathInterpolatorCompat;

public class AnimUtils {
    public static final Interpolator EASE_IN = PathInterpolatorCompat.create(ContactPhotoManager.OFFSET_DEFAULT, ContactPhotoManager.OFFSET_DEFAULT, 0.2f, 1.0f);
    public static final Interpolator EASE_OUT = PathInterpolatorCompat.create(0.4f, ContactPhotoManager.OFFSET_DEFAULT, 1.0f, 1.0f);
    public static final Interpolator EASE_OUT_EASE_IN = PathInterpolatorCompat.create(0.4f, ContactPhotoManager.OFFSET_DEFAULT, 0.2f, 1.0f);

    public static class AnimationCallback {
        public void onAnimationCancel() {
            throw null;
        }

        public void onAnimationEnd() {
            throw null;
        }
    }

    public static void fadeOut(final View view, int i, final AnimationCallback animationCallback) {
        view.setAlpha(1.0f);
        ViewPropertyAnimator animate = view.animate();
        animate.cancel();
        animate.alpha(ContactPhotoManager.OFFSET_DEFAULT).withLayer().setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                view.setVisibility(8);
                AnimationCallback animationCallback = animationCallback;
                if (animationCallback != null) {
                    animationCallback.onAnimationEnd();
                    throw null;
                }
            }

            public void onAnimationCancel(Animator animator) {
                view.setVisibility(8);
                view.setAlpha(ContactPhotoManager.OFFSET_DEFAULT);
                AnimationCallback animationCallback = animationCallback;
                if (animationCallback != null) {
                    animationCallback.onAnimationCancel();
                    throw null;
                }
            }
        });
        if (i != -1) {
            animate.setDuration((long) i);
        }
        animate.start();
    }

    public static void fadeIn(final View view, int i, int i2, final AnimationCallback animationCallback) {
        view.setAlpha(ContactPhotoManager.OFFSET_DEFAULT);
        ViewPropertyAnimator animate = view.animate();
        animate.cancel();
        animate.setStartDelay((long) i2);
        animate.alpha(1.0f).withLayer().setListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                view.setVisibility(0);
            }

            public void onAnimationCancel(Animator animator) {
                view.setAlpha(1.0f);
                AnimationCallback animationCallback = animationCallback;
                if (animationCallback != null) {
                    animationCallback.onAnimationCancel();
                    throw null;
                }
            }

            public void onAnimationEnd(Animator animator) {
                AnimationCallback animationCallback = animationCallback;
                if (animationCallback != null) {
                    animationCallback.onAnimationEnd();
                    throw null;
                }
            }
        });
        if (i != -1) {
            animate.setDuration((long) i);
        }
        animate.start();
    }

    public static void scaleIn(final View view, int i, int i2) {
        scaleInternal(view, 0, 1, i, i2, new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                view.setVisibility(0);
            }

            public void onAnimationCancel(Animator animator) {
                view.setScaleX(1.0f);
                view.setScaleY(1.0f);
            }
        }, EASE_IN);
    }

    public static void scaleOut(final View view, int i) {
        scaleInternal(view, 1, 0, i, 0, new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                view.setVisibility(8);
            }

            public void onAnimationCancel(Animator animator) {
                view.setVisibility(8);
                view.setScaleX(ContactPhotoManager.OFFSET_DEFAULT);
                view.setScaleY(ContactPhotoManager.OFFSET_DEFAULT);
            }
        }, EASE_OUT);
    }

    private static void scaleInternal(View view, int i, int i2, int i3, int i4, AnimatorListenerAdapter animatorListenerAdapter, Interpolator interpolator) {
        float f = (float) i;
        view.setScaleX(f);
        view.setScaleY(f);
        ViewPropertyAnimator animate = view.animate();
        animate.cancel();
        float f2 = (float) i2;
        animate.setInterpolator(interpolator).scaleX(f2).scaleY(f2).setListener(animatorListenerAdapter).withLayer();
        if (i3 != -1) {
            animate.setDuration((long) i3);
        }
        animate.setStartDelay((long) i4);
        animate.start();
    }
}
