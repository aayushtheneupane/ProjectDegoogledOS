package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Paint;
import android.support.p000v4.view.ViewCompat;
import android.view.View;

public class Fade extends Visibility {

    private static class FadeAnimatorListener extends AnimatorListenerAdapter {
        private boolean mLayerTypeChanged = false;
        private final View mView;

        FadeAnimatorListener(View view) {
            this.mView = view;
        }

        public void onAnimationEnd(Animator animator) {
            ViewUtils.setTransitionAlpha(this.mView, 1.0f);
            if (this.mLayerTypeChanged) {
                this.mView.setLayerType(0, (Paint) null);
            }
        }

        public void onAnimationStart(Animator animator) {
            if (ViewCompat.hasOverlappingRendering(this.mView) && this.mView.getLayerType() == 0) {
                this.mLayerTypeChanged = true;
                this.mView.setLayerType(2, (Paint) null);
            }
        }
    }

    public Fade(int i) {
        setMode(i);
    }

    private Animator createAnimation(final View view, float f, float f2) {
        if (f == f2) {
            return null;
        }
        ViewUtils.setTransitionAlpha(view, f);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, ViewUtils.TRANSITION_ALPHA, new float[]{f2});
        ofFloat.addListener(new FadeAnimatorListener(view));
        addListener(new TransitionListenerAdapter(this) {
            public void onTransitionEnd(Transition transition) {
                ViewUtils.setTransitionAlpha(view, 1.0f);
                ViewUtils.clearNonTransitionAlpha(view);
                transition.removeListener(this);
            }
        });
        return ofFloat;
    }

    public void captureStartValues(TransitionValues transitionValues) {
        super.captureStartValues(transitionValues);
        transitionValues.values.put("android:fade:transitionAlpha", Float.valueOf(ViewUtils.getTransitionAlpha(transitionValues.view)));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0003, code lost:
        r4 = (java.lang.Float) r4.values.get("android:fade:transitionAlpha");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.animation.Animator onAppear(android.view.ViewGroup r2, android.view.View r3, android.support.transition.TransitionValues r4, android.support.transition.TransitionValues r5) {
        /*
            r1 = this;
            r2 = 0
            if (r4 == 0) goto L_0x0014
            java.util.Map<java.lang.String, java.lang.Object> r4 = r4.values
            java.lang.String r5 = "android:fade:transitionAlpha"
            java.lang.Object r4 = r4.get(r5)
            java.lang.Float r4 = (java.lang.Float) r4
            if (r4 == 0) goto L_0x0014
            float r4 = r4.floatValue()
            goto L_0x0015
        L_0x0014:
            r4 = r2
        L_0x0015:
            r5 = 1065353216(0x3f800000, float:1.0)
            int r0 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r0 != 0) goto L_0x001c
            goto L_0x001d
        L_0x001c:
            r2 = r4
        L_0x001d:
            android.animation.Animator r1 = r1.createAnimation(r3, r2, r5)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.transition.Fade.onAppear(android.view.ViewGroup, android.view.View, android.support.transition.TransitionValues, android.support.transition.TransitionValues):android.animation.Animator");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0005, code lost:
        r1 = (java.lang.Float) r3.values.get("android:fade:transitionAlpha");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.animation.Animator onDisappear(android.view.ViewGroup r1, android.view.View r2, android.support.transition.TransitionValues r3, android.support.transition.TransitionValues r4) {
        /*
            r0 = this;
            android.support.transition.ViewUtils.saveNonTransitionAlpha(r2)
            if (r3 == 0) goto L_0x0016
            java.util.Map<java.lang.String, java.lang.Object> r1 = r3.values
            java.lang.String r3 = "android:fade:transitionAlpha"
            java.lang.Object r1 = r1.get(r3)
            java.lang.Float r1 = (java.lang.Float) r1
            if (r1 == 0) goto L_0x0016
            float r1 = r1.floatValue()
            goto L_0x0018
        L_0x0016:
            r1 = 1065353216(0x3f800000, float:1.0)
        L_0x0018:
            r3 = 0
            android.animation.Animator r0 = r0.createAnimation(r2, r1, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.transition.Fade.onDisappear(android.view.ViewGroup, android.view.View, android.support.transition.TransitionValues, android.support.transition.TransitionValues):android.animation.Animator");
    }
}
