package android.support.design.transformation;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.design.animation.AnimationUtils;
import android.support.design.animation.MotionSpec;
import android.support.design.animation.MotionTiming;
import android.support.design.animation.Positioning;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public abstract class FabTransformationBehavior extends ExpandableTransformationBehavior {
    private final int[] tmpArray = new int[2];
    private final Rect tmpRect = new Rect();
    private final RectF tmpRectF1 = new RectF();
    private final RectF tmpRectF2 = new RectF();

    protected static class FabTransformationSpec {
        public Positioning positioning;
        public MotionSpec timings;

        protected FabTransformationSpec() {
        }
    }

    public FabTransformationBehavior() {
    }

    private float calculateTranslationX(View view, View view2, Positioning positioning) {
        float f;
        float f2;
        float f3;
        RectF rectF = this.tmpRectF1;
        RectF rectF2 = this.tmpRectF2;
        calculateWindowBounds(view, rectF);
        calculateWindowBounds(view2, rectF2);
        int i = positioning.gravity & 7;
        if (i == 1) {
            f3 = rectF2.centerX();
            f2 = rectF.centerX();
        } else if (i == 3) {
            f3 = rectF2.left;
            f2 = rectF.left;
        } else if (i != 5) {
            f = 0.0f;
            return f + positioning.xAdjustment;
        } else {
            f3 = rectF2.right;
            f2 = rectF.right;
        }
        f = f3 - f2;
        return f + positioning.xAdjustment;
    }

    private float calculateTranslationY(View view, View view2, Positioning positioning) {
        float f;
        float f2;
        float f3;
        RectF rectF = this.tmpRectF1;
        RectF rectF2 = this.tmpRectF2;
        calculateWindowBounds(view, rectF);
        calculateWindowBounds(view2, rectF2);
        int i = positioning.gravity & 112;
        if (i == 16) {
            f3 = rectF2.centerY();
            f2 = rectF.centerY();
        } else if (i == 48) {
            f3 = rectF2.top;
            f2 = rectF.top;
        } else if (i != 80) {
            f = 0.0f;
            return f + positioning.yAdjustment;
        } else {
            f3 = rectF2.bottom;
            f2 = rectF.bottom;
        }
        f = f3 - f2;
        return f + positioning.yAdjustment;
    }

    private float calculateValueOfAnimationAtEndOfExpansion(FabTransformationSpec fabTransformationSpec, MotionTiming motionTiming, float f, float f2) {
        long delay = motionTiming.getDelay();
        long duration = motionTiming.getDuration();
        MotionTiming timing = fabTransformationSpec.timings.getTiming("expansion");
        return AnimationUtils.lerp(f, f2, motionTiming.getInterpolator().getInterpolation(((float) (((timing.getDuration() + timing.getDelay()) + 17) - delay)) / ((float) duration)));
    }

    private void calculateWindowBounds(View view, RectF rectF) {
        rectF.set(0.0f, 0.0f, (float) view.getWidth(), (float) view.getHeight());
        int[] iArr = this.tmpArray;
        view.getLocationInWindow(iArr);
        rectF.offsetTo((float) iArr[0], (float) iArr[1]);
        rectF.offset((float) ((int) (-view.getTranslationX())), (float) ((int) (-view.getTranslationY())));
    }

    private ViewGroup toViewGroupOrNull(View view) {
        if (view instanceof ViewGroup) {
            return (ViewGroup) view;
        }
        return null;
    }

    public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, View view, View view2) {
        if (view.getVisibility() == 8) {
            throw new IllegalStateException("This behavior cannot be attached to a GONE view. Set the view to INVISIBLE instead.");
        } else if (!(view2 instanceof FloatingActionButton)) {
            return false;
        } else {
            int expandedComponentIdHint = ((FloatingActionButton) view2).getExpandedComponentIdHint();
            if (expandedComponentIdHint == 0 || expandedComponentIdHint == view.getId()) {
                return true;
            }
            return false;
        }
    }

    public void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams layoutParams) {
        if (layoutParams.dodgeInsetEdges == 0) {
            layoutParams.dodgeInsetEdges = 80;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x0373 A[LOOP:0: B:105:0x0371->B:106:0x0373, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0179  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x017d  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x02a5  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x02a8  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x0312  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0319  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0321  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x0323  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.animation.AnimatorSet onCreateExpandedStateChangeAnimation(android.view.View r23, android.view.View r24, boolean r25, boolean r26) {
        /*
            r22 = this;
            r0 = r22
            r1 = r23
            r2 = r24
            r3 = r25
            android.content.Context r4 = r24.getContext()
            android.support.design.transformation.FabTransformationBehavior$FabTransformationSpec r4 = r0.onCreateMotionSpec(r4, r3)
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            int r7 = android.os.Build.VERSION.SDK_INT
            float r7 = android.support.p000v4.view.ViewCompat.getElevation(r24)
            float r8 = android.support.p000v4.view.ViewCompat.getElevation(r23)
            float r7 = r7 - r8
            r8 = 0
            r9 = 1
            r10 = 0
            if (r3 == 0) goto L_0x003b
            if (r26 != 0) goto L_0x0030
            float r7 = -r7
            r2.setTranslationZ(r7)
        L_0x0030:
            android.util.Property r7 = android.view.View.TRANSLATION_Z
            float[] r11 = new float[r9]
            r11[r10] = r8
            android.animation.ObjectAnimator r7 = android.animation.ObjectAnimator.ofFloat(r2, r7, r11)
            goto L_0x0046
        L_0x003b:
            android.util.Property r11 = android.view.View.TRANSLATION_Z
            float[] r12 = new float[r9]
            float r7 = -r7
            r12[r10] = r7
            android.animation.ObjectAnimator r7 = android.animation.ObjectAnimator.ofFloat(r2, r11, r12)
        L_0x0046:
            android.support.design.animation.MotionSpec r11 = r4.timings
            java.lang.String r12 = "elevation"
            android.support.design.animation.MotionTiming r11 = r11.getTiming(r12)
            r11.apply(r7)
            r5.add(r7)
            android.graphics.RectF r7 = r0.tmpRectF1
            android.support.design.animation.Positioning r11 = r4.positioning
            float r11 = r0.calculateTranslationX(r1, r2, r11)
            android.support.design.animation.Positioning r12 = r4.positioning
            float r12 = r0.calculateTranslationY(r1, r2, r12)
            int r13 = (r11 > r8 ? 1 : (r11 == r8 ? 0 : -1))
            if (r13 == 0) goto L_0x0097
            int r13 = (r12 > r8 ? 1 : (r12 == r8 ? 0 : -1))
            if (r13 != 0) goto L_0x006b
            goto L_0x0097
        L_0x006b:
            if (r3 == 0) goto L_0x0071
            int r14 = (r12 > r8 ? 1 : (r12 == r8 ? 0 : -1))
            if (r14 < 0) goto L_0x0075
        L_0x0071:
            if (r3 != 0) goto L_0x0086
            if (r13 <= 0) goto L_0x0086
        L_0x0075:
            android.support.design.animation.MotionSpec r13 = r4.timings
            java.lang.String r14 = "translationXCurveUpwards"
            android.support.design.animation.MotionTiming r13 = r13.getTiming(r14)
            android.support.design.animation.MotionSpec r14 = r4.timings
            java.lang.String r15 = "translationYCurveUpwards"
            android.support.design.animation.MotionTiming r14 = r14.getTiming(r15)
            goto L_0x00a7
        L_0x0086:
            android.support.design.animation.MotionSpec r13 = r4.timings
            java.lang.String r14 = "translationXCurveDownwards"
            android.support.design.animation.MotionTiming r13 = r13.getTiming(r14)
            android.support.design.animation.MotionSpec r14 = r4.timings
            java.lang.String r15 = "translationYCurveDownwards"
            android.support.design.animation.MotionTiming r14 = r14.getTiming(r15)
            goto L_0x00a7
        L_0x0097:
            android.support.design.animation.MotionSpec r13 = r4.timings
            java.lang.String r14 = "translationXLinear"
            android.support.design.animation.MotionTiming r13 = r13.getTiming(r14)
            android.support.design.animation.MotionSpec r14 = r4.timings
            java.lang.String r15 = "translationYLinear"
            android.support.design.animation.MotionTiming r14 = r14.getTiming(r15)
        L_0x00a7:
            if (r3 == 0) goto L_0x00f1
            if (r26 != 0) goto L_0x00b3
            float r15 = -r11
            r2.setTranslationX(r15)
            float r15 = -r12
            r2.setTranslationY(r15)
        L_0x00b3:
            android.util.Property r15 = android.view.View.TRANSLATION_X
            r16 = r6
            float[] r6 = new float[r9]
            r6[r10] = r8
            android.animation.ObjectAnimator r6 = android.animation.ObjectAnimator.ofFloat(r2, r15, r6)
            android.util.Property r15 = android.view.View.TRANSLATION_Y
            r17 = r6
            float[] r6 = new float[r9]
            r6[r10] = r8
            android.animation.ObjectAnimator r6 = android.animation.ObjectAnimator.ofFloat(r2, r15, r6)
            float r11 = -r11
            float r12 = -r12
            float r11 = r0.calculateValueOfAnimationAtEndOfExpansion(r4, r13, r11, r8)
            float r12 = r0.calculateValueOfAnimationAtEndOfExpansion(r4, r14, r12, r8)
            android.graphics.Rect r15 = r0.tmpRect
            r2.getWindowVisibleDisplayFrame(r15)
            android.graphics.RectF r8 = r0.tmpRectF1
            r8.set(r15)
            android.graphics.RectF r15 = r0.tmpRectF2
            r0.calculateWindowBounds(r2, r15)
            r15.offset(r11, r12)
            r15.intersect(r8)
            r7.set(r15)
            r8 = r6
            r6 = r17
            goto L_0x0109
        L_0x00f1:
            r16 = r6
            android.util.Property r6 = android.view.View.TRANSLATION_X
            float[] r8 = new float[r9]
            float r11 = -r11
            r8[r10] = r11
            android.animation.ObjectAnimator r6 = android.animation.ObjectAnimator.ofFloat(r2, r6, r8)
            android.util.Property r8 = android.view.View.TRANSLATION_Y
            float[] r11 = new float[r9]
            float r12 = -r12
            r11[r10] = r12
            android.animation.ObjectAnimator r8 = android.animation.ObjectAnimator.ofFloat(r2, r8, r11)
        L_0x0109:
            r13.apply(r6)
            r14.apply(r8)
            r5.add(r6)
            r5.add(r8)
            float r6 = r7.width()
            float r7 = r7.height()
            boolean r8 = r2 instanceof android.support.design.circularreveal.CircularRevealWidget
            if (r8 == 0) goto L_0x0175
            boolean r11 = r1 instanceof android.widget.ImageView
            if (r11 != 0) goto L_0x0126
            goto L_0x0175
        L_0x0126:
            r11 = r2
            android.support.design.circularreveal.CircularRevealWidget r11 = (android.support.design.circularreveal.CircularRevealWidget) r11
            r12 = r1
            android.widget.ImageView r12 = (android.widget.ImageView) r12
            android.graphics.drawable.Drawable r12 = r12.getDrawable()
            if (r12 != 0) goto L_0x0133
            goto L_0x0175
        L_0x0133:
            r12.mutate()
            r13 = 255(0xff, float:3.57E-43)
            if (r3 == 0) goto L_0x014a
            if (r26 != 0) goto L_0x013f
            r12.setAlpha(r13)
        L_0x013f:
            android.util.Property<android.graphics.drawable.Drawable, java.lang.Integer> r13 = android.support.design.animation.DrawableAlphaProperty.DRAWABLE_ALPHA_COMPAT
            int[] r14 = new int[r9]
            r14[r10] = r10
            android.animation.ObjectAnimator r13 = android.animation.ObjectAnimator.ofInt(r12, r13, r14)
            goto L_0x0154
        L_0x014a:
            android.util.Property<android.graphics.drawable.Drawable, java.lang.Integer> r14 = android.support.design.animation.DrawableAlphaProperty.DRAWABLE_ALPHA_COMPAT
            int[] r15 = new int[r9]
            r15[r10] = r13
            android.animation.ObjectAnimator r13 = android.animation.ObjectAnimator.ofInt(r12, r14, r15)
        L_0x0154:
            android.support.design.transformation.FabTransformationBehavior$2 r14 = new android.support.design.transformation.FabTransformationBehavior$2
            r14.<init>(r0, r2)
            r13.addUpdateListener(r14)
            android.support.design.animation.MotionSpec r14 = r4.timings
            java.lang.String r15 = "iconFade"
            android.support.design.animation.MotionTiming r14 = r14.getTiming(r15)
            r14.apply(r13)
            r5.add(r13)
            android.support.design.transformation.FabTransformationBehavior$3 r13 = new android.support.design.transformation.FabTransformationBehavior$3
            r13.<init>(r0, r11, r12)
            r11 = r16
            r11.add(r13)
            goto L_0x0177
        L_0x0175:
            r11 = r16
        L_0x0177:
            if (r8 != 0) goto L_0x017d
            r1 = r4
            r0 = r5
            goto L_0x02a3
        L_0x017d:
            r12 = r2
            android.support.design.circularreveal.CircularRevealWidget r12 = (android.support.design.circularreveal.CircularRevealWidget) r12
            android.support.design.animation.Positioning r13 = r4.positioning
            android.graphics.RectF r14 = r0.tmpRectF1
            android.graphics.RectF r15 = r0.tmpRectF2
            r0.calculateWindowBounds(r1, r14)
            r0.calculateWindowBounds(r2, r15)
            float r13 = r0.calculateTranslationX(r1, r2, r13)
            float r13 = -r13
            r10 = 0
            r15.offset(r13, r10)
            float r13 = r14.centerX()
            float r14 = r15.left
            float r13 = r13 - r14
            android.support.design.animation.Positioning r14 = r4.positioning
            android.graphics.RectF r15 = r0.tmpRectF1
            android.graphics.RectF r9 = r0.tmpRectF2
            r0.calculateWindowBounds(r1, r15)
            r0.calculateWindowBounds(r2, r9)
            float r14 = r0.calculateTranslationY(r1, r2, r14)
            float r14 = -r14
            r9.offset(r10, r14)
            float r10 = r15.centerY()
            float r9 = r9.top
            float r10 = r10 - r9
            r9 = r1
            android.support.design.widget.FloatingActionButton r9 = (android.support.design.widget.FloatingActionButton) r9
            android.graphics.Rect r14 = r0.tmpRect
            r9.getContentRect(r14)
            android.graphics.Rect r9 = r0.tmpRect
            int r9 = r9.width()
            float r9 = (float) r9
            r14 = 1073741824(0x40000000, float:2.0)
            float r9 = r9 / r14
            android.support.design.animation.MotionSpec r14 = r4.timings
            java.lang.String r15 = "expansion"
            android.support.design.animation.MotionTiming r14 = r14.getTiming(r15)
            r15 = r4
            r18 = r5
            if (r3 == 0) goto L_0x0246
            if (r26 != 0) goto L_0x01e0
            android.support.design.circularreveal.CircularRevealWidget$RevealInfo r4 = new android.support.design.circularreveal.CircularRevealWidget$RevealInfo
            r4.<init>(r13, r10, r9)
            r12.setRevealInfo(r4)
        L_0x01e0:
            if (r26 == 0) goto L_0x01e8
            android.support.design.circularreveal.CircularRevealWidget$RevealInfo r4 = r12.getRevealInfo()
            float r9 = r4.radius
        L_0x01e8:
            r4 = 0
            float r5 = android.support.design.widget.MathUtils.dist(r13, r10, r4, r4)
            float r21 = android.support.design.widget.MathUtils.dist(r13, r10, r6, r4)
            float r6 = android.support.design.widget.MathUtils.dist(r13, r10, r6, r7)
            float r7 = android.support.design.widget.MathUtils.dist(r13, r10, r4, r7)
            int r4 = (r5 > r21 ? 1 : (r5 == r21 ? 0 : -1))
            if (r4 <= 0) goto L_0x0206
            int r4 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r4 <= 0) goto L_0x0206
            int r4 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r4 <= 0) goto L_0x0206
            goto L_0x0218
        L_0x0206:
            int r4 = (r21 > r6 ? 1 : (r21 == r6 ? 0 : -1))
            if (r4 <= 0) goto L_0x0211
            int r4 = (r21 > r7 ? 1 : (r21 == r7 ? 0 : -1))
            if (r4 <= 0) goto L_0x0211
            r5 = r21
            goto L_0x0218
        L_0x0211:
            int r4 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r4 <= 0) goto L_0x0217
            r5 = r6
            goto L_0x0218
        L_0x0217:
            r5 = r7
        L_0x0218:
            android.animation.Animator r4 = android.support.design.circularreveal.CircularRevealCompat.createCircularReveal(r12, r13, r10, r5)
            android.support.design.transformation.FabTransformationBehavior$4 r5 = new android.support.design.transformation.FabTransformationBehavior$4
            r5.<init>(r0, r12)
            r4.addListener(r5)
            long r5 = r14.getDelay()
            int r7 = (int) r13
            int r10 = (int) r10
            int r13 = android.os.Build.VERSION.SDK_INT
            r0 = 0
            int r13 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r13 <= 0) goto L_0x0242
            android.animation.Animator r7 = android.view.ViewAnimationUtils.createCircularReveal(r2, r7, r10, r9, r9)
            r7.setStartDelay(r0)
            r7.setDuration(r5)
            r0 = r18
            r0.add(r7)
            goto L_0x0244
        L_0x0242:
            r0 = r18
        L_0x0244:
            r1 = r15
            goto L_0x0296
        L_0x0246:
            r0 = r18
            android.support.design.circularreveal.CircularRevealWidget$RevealInfo r1 = r12.getRevealInfo()
            float r1 = r1.radius
            android.animation.Animator r4 = android.support.design.circularreveal.CircularRevealCompat.createCircularReveal(r12, r13, r10, r9)
            long r5 = r14.getDelay()
            int r7 = (int) r13
            int r10 = (int) r10
            int r13 = android.os.Build.VERSION.SDK_INT
            r18 = r4
            r3 = 0
            int r13 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r13 <= 0) goto L_0x026f
            android.animation.Animator r1 = android.view.ViewAnimationUtils.createCircularReveal(r2, r7, r10, r1, r1)
            r1.setStartDelay(r3)
            r1.setDuration(r5)
            r0.add(r1)
        L_0x026f:
            long r3 = r14.getDelay()
            long r5 = r14.getDuration()
            r1 = r15
            android.support.design.animation.MotionSpec r13 = r1.timings
            long r19 = r13.getTotalDuration()
            int r13 = android.os.Build.VERSION.SDK_INT
            long r3 = r3 + r5
            int r5 = (r3 > r19 ? 1 : (r3 == r19 ? 0 : -1))
            if (r5 >= 0) goto L_0x0294
            android.animation.Animator r5 = android.view.ViewAnimationUtils.createCircularReveal(r2, r7, r10, r9, r9)
            r5.setStartDelay(r3)
            long r3 = r19 - r3
            r5.setDuration(r3)
            r0.add(r5)
        L_0x0294:
            r4 = r18
        L_0x0296:
            r14.apply(r4)
            r0.add(r4)
            android.animation.Animator$AnimatorListener r3 = android.support.design.circularreveal.CircularRevealCompat.createCircularRevealListener(r12)
            r11.add(r3)
        L_0x02a3:
            if (r8 != 0) goto L_0x02a8
            r5 = r25
            goto L_0x02fa
        L_0x02a8:
            r3 = r2
            android.support.design.circularreveal.CircularRevealWidget r3 = (android.support.design.circularreveal.CircularRevealWidget) r3
            android.content.res.ColorStateList r4 = android.support.p000v4.view.ViewCompat.getBackgroundTintList(r23)
            if (r4 == 0) goto L_0x02be
            int[] r5 = r23.getDrawableState()
            int r6 = r4.getDefaultColor()
            int r10 = r4.getColorForState(r5, r6)
            goto L_0x02bf
        L_0x02be:
            r10 = 0
        L_0x02bf:
            r4 = 16777215(0xffffff, float:2.3509886E-38)
            r4 = r4 & r10
            r5 = r25
            if (r5 == 0) goto L_0x02d9
            if (r26 != 0) goto L_0x02cc
            r3.setCircularRevealScrimColor(r10)
        L_0x02cc:
            android.util.Property<android.support.design.circularreveal.CircularRevealWidget, java.lang.Integer> r6 = android.support.design.circularreveal.CircularRevealWidget.CircularRevealScrimColorProperty.CIRCULAR_REVEAL_SCRIM_COLOR
            r7 = 1
            int[] r9 = new int[r7]
            r12 = 0
            r9[r12] = r4
            android.animation.ObjectAnimator r3 = android.animation.ObjectAnimator.ofInt(r3, r6, r9)
            goto L_0x02e5
        L_0x02d9:
            r7 = 1
            r12 = 0
            android.util.Property<android.support.design.circularreveal.CircularRevealWidget, java.lang.Integer> r4 = android.support.design.circularreveal.CircularRevealWidget.CircularRevealScrimColorProperty.CIRCULAR_REVEAL_SCRIM_COLOR
            int[] r6 = new int[r7]
            r6[r12] = r10
            android.animation.ObjectAnimator r3 = android.animation.ObjectAnimator.ofInt(r3, r4, r6)
        L_0x02e5:
            android.support.design.animation.ArgbEvaluatorCompat r4 = android.support.design.animation.ArgbEvaluatorCompat.getInstance()
            r3.setEvaluator(r4)
            android.support.design.animation.MotionSpec r4 = r1.timings
            java.lang.String r6 = "color"
            android.support.design.animation.MotionTiming r4 = r4.getTiming(r6)
            r4.apply(r3)
            r0.add(r3)
        L_0x02fa:
            boolean r3 = r2 instanceof android.view.ViewGroup
            if (r3 != 0) goto L_0x0302
        L_0x02fe:
            r12 = 0
            r4 = r22
            goto L_0x035b
        L_0x0302:
            if (r8 == 0) goto L_0x0309
            int r3 = android.support.design.circularreveal.CircularRevealHelper.STRATEGY
            if (r3 != 0) goto L_0x0309
            goto L_0x02fe
        L_0x0309:
            r3 = 2131296693(0x7f0901b5, float:1.821131E38)
            android.view.View r3 = r2.findViewById(r3)
            if (r3 == 0) goto L_0x0319
            r4 = r22
            android.view.ViewGroup r3 = r4.toViewGroupOrNull(r3)
            goto L_0x031f
        L_0x0319:
            r4 = r22
            android.view.ViewGroup r3 = r4.toViewGroupOrNull(r2)
        L_0x031f:
            if (r3 != 0) goto L_0x0323
            r12 = 0
            goto L_0x035b
        L_0x0323:
            if (r5 == 0) goto L_0x0340
            if (r26 != 0) goto L_0x0331
            android.util.Property<android.view.ViewGroup, java.lang.Float> r6 = android.support.design.animation.ChildrenAlphaProperty.CHILDREN_ALPHA
            r7 = 0
            java.lang.Float r7 = java.lang.Float.valueOf(r7)
            r6.set(r3, r7)
        L_0x0331:
            android.util.Property<android.view.ViewGroup, java.lang.Float> r6 = android.support.design.animation.ChildrenAlphaProperty.CHILDREN_ALPHA
            r7 = 1
            float[] r7 = new float[r7]
            r8 = 1065353216(0x3f800000, float:1.0)
            r12 = 0
            r7[r12] = r8
            android.animation.ObjectAnimator r3 = android.animation.ObjectAnimator.ofFloat(r3, r6, r7)
            goto L_0x034d
        L_0x0340:
            r7 = 1
            r12 = 0
            android.util.Property<android.view.ViewGroup, java.lang.Float> r6 = android.support.design.animation.ChildrenAlphaProperty.CHILDREN_ALPHA
            float[] r7 = new float[r7]
            r8 = 0
            r7[r12] = r8
            android.animation.ObjectAnimator r3 = android.animation.ObjectAnimator.ofFloat(r3, r6, r7)
        L_0x034d:
            android.support.design.animation.MotionSpec r1 = r1.timings
            java.lang.String r6 = "contentFade"
            android.support.design.animation.MotionTiming r1 = r1.getTiming(r6)
            r1.apply(r3)
            r0.add(r3)
        L_0x035b:
            android.animation.AnimatorSet r1 = new android.animation.AnimatorSet
            r1.<init>()
            android.support.design.R$dimen.playTogether(r1, r0)
            android.support.design.transformation.FabTransformationBehavior$1 r0 = new android.support.design.transformation.FabTransformationBehavior$1
            r3 = r23
            r0.<init>(r4, r5, r2, r3)
            r1.addListener(r0)
            int r0 = r11.size()
        L_0x0371:
            if (r12 >= r0) goto L_0x037f
            java.lang.Object r2 = r11.get(r12)
            android.animation.Animator$AnimatorListener r2 = (android.animation.Animator.AnimatorListener) r2
            r1.addListener(r2)
            int r12 = r12 + 1
            goto L_0x0371
        L_0x037f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.transformation.FabTransformationBehavior.onCreateExpandedStateChangeAnimation(android.view.View, android.view.View, boolean, boolean):android.animation.AnimatorSet");
    }

    /* access modifiers changed from: protected */
    public abstract FabTransformationSpec onCreateMotionSpec(Context context, boolean z);

    public FabTransformationBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
