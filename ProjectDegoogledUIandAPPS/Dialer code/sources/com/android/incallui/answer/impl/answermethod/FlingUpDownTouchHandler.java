package com.android.incallui.answer.impl.answermethod;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import com.android.dialer.R;
import com.android.incallui.answer.impl.classifier.FalsingManager;
import com.android.incallui.answer.impl.utils.FlingAnimationUtils;

@SuppressLint({"ClickableViewAccessibility"})
class FlingUpDownTouchHandler implements View.OnTouchListener {
    private final float acceptThresholdPx;
    private float acceptThresholdY;
    private float currentProgress;
    private final float deadZoneTopPx;
    private final FalsingManager falsingManager;
    private float falsingThresholdPx;
    private FlingAnimationUtils flingAnimationUtils;
    private boolean flingEnabled = true;
    private boolean hintDistanceExceeded;
    private float initialTouchY;
    private final OnProgressChangedListener listener;
    private boolean motionAborted;
    /* access modifiers changed from: private */
    public Animator progressAnimator;
    private final float rejectThresholdPx;
    private float rejectThresholdY;
    private final View target;
    private boolean touchAboveFalsingThreshold;
    private boolean touchEnabled = true;
    private float touchSlop;
    private boolean touchSlopExceeded;
    private boolean tracking;
    private int trackingPointer;
    private VelocityTracker velocityTracker;
    private float zeroY;

    interface OnProgressChangedListener {
        void onMoveFinish(boolean z);

        void onMoveReset(boolean z);

        void onProgressChanged(float f);

        void onTrackingStart();

        void onTrackingStopped();

        boolean shouldUseFalsing(MotionEvent motionEvent);
    }

    private FlingUpDownTouchHandler(View view, OnProgressChangedListener onProgressChangedListener, FalsingManager falsingManager2) {
        this.target = view;
        this.listener = onProgressChangedListener;
        Context context = view.getContext();
        this.touchSlop = (float) ViewConfiguration.get(context).getScaledTouchSlop();
        this.flingAnimationUtils = new FlingAnimationUtils(context, 0.6f);
        this.falsingThresholdPx = 40.0f * context.getResources().getDisplayMetrics().density;
        this.acceptThresholdPx = context.getResources().getDisplayMetrics().density * 150.0f;
        this.rejectThresholdPx = 150.0f * context.getResources().getDisplayMetrics().density;
        this.deadZoneTopPx = Math.max(context.getResources().getDimension(R.dimen.answer_swipe_dead_zone_top), this.acceptThresholdPx);
        this.falsingManager = falsingManager2;
    }

    public static FlingUpDownTouchHandler attach(View view, OnProgressChangedListener onProgressChangedListener, FalsingManager falsingManager2) {
        FlingUpDownTouchHandler flingUpDownTouchHandler = new FlingUpDownTouchHandler(view, onProgressChangedListener, falsingManager2);
        view.setOnTouchListener(flingUpDownTouchHandler);
        return flingUpDownTouchHandler;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00b9, code lost:
        if (r5 < 0) goto L_0x00cb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00c9, code lost:
        if (r1 > 0.0f) goto L_0x00cb;
     */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00e6  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00ee  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0102  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void endMotionEvent(android.view.MotionEvent r16, float r17, boolean r18) {
        /*
            r15 = this;
            r0 = r15
            r1 = r17
            r2 = -1
            r0.trackingPointer = r2
            boolean r3 = r0.tracking
            r4 = 0
            r5 = 3
            r6 = 0
            if (r3 == 0) goto L_0x0011
            boolean r3 = r0.touchSlopExceeded
            if (r3 != 0) goto L_0x0037
        L_0x0011:
            float r3 = r0.initialTouchY
            float r3 = r1 - r3
            float r3 = java.lang.Math.abs(r3)
            float r7 = r0.touchSlop
            int r3 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r3 > 0) goto L_0x0037
            int r3 = r16.getActionMasked()
            if (r3 == r5) goto L_0x0037
            if (r18 == 0) goto L_0x0028
            goto L_0x0037
        L_0x0028:
            r0.tracking = r4
            com.android.incallui.answer.impl.answermethod.FlingUpDownTouchHandler$OnProgressChangedListener r1 = r0.listener
            r1.onTrackingStopped()
            r15.setCurrentProgress(r6)
            r15.onMoveEnded()
            goto L_0x011b
        L_0x0037:
            android.view.VelocityTracker r3 = r0.velocityTracker
            if (r3 == 0) goto L_0x005e
            r7 = 1000(0x3e8, float:1.401E-42)
            r3.computeCurrentVelocity(r7)
            android.view.VelocityTracker r3 = r0.velocityTracker
            float r3 = r3.getYVelocity()
            android.view.VelocityTracker r7 = r0.velocityTracker
            float r7 = r7.getXVelocity()
            double r7 = (double) r7
            android.view.VelocityTracker r9 = r0.velocityTracker
            float r9 = r9.getYVelocity()
            double r9 = (double) r9
            double r7 = java.lang.Math.hypot(r7, r9)
            float r7 = (float) r7
            float r7 = java.lang.Math.copySign(r7, r3)
            goto L_0x0060
        L_0x005e:
            r3 = r6
            r7 = r3
        L_0x0060:
            boolean r8 = r0.touchAboveFalsingThreshold
            r9 = 1
            r14 = r8 ^ 1
            if (r14 != 0) goto L_0x0076
            boolean r8 = r0.touchSlopExceeded
            if (r8 == 0) goto L_0x0076
            if (r18 != 0) goto L_0x0076
            int r8 = r16.getActionMasked()
            if (r8 != r5) goto L_0x0074
            goto L_0x0076
        L_0x0074:
            r5 = r4
            goto L_0x0077
        L_0x0076:
            r5 = r9
        L_0x0077:
            if (r5 == 0) goto L_0x007a
            goto L_0x00cd
        L_0x007a:
            float r1 = r15.pointerYToProgress(r1)
            com.android.incallui.answer.impl.utils.FlingAnimationUtils r5 = r0.flingAnimationUtils
            float r5 = r5.getMinVelocityPxPerSecond()
            int r8 = (r7 > r6 ? 1 : (r7 == r6 ? 0 : -1))
            if (r8 <= 0) goto L_0x008b
            r8 = 1073741824(0x40000000, float:2.0)
            float r5 = r5 * r8
        L_0x008b:
            boolean r8 = r0.flingEnabled
            if (r8 == 0) goto L_0x00bc
            float r8 = java.lang.Math.abs(r7)
            int r5 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1))
            if (r5 >= 0) goto L_0x0098
            goto L_0x00bc
        L_0x0098:
            int r5 = (r7 > r6 ? 1 : (r7 == r6 ? 0 : -1))
            if (r5 >= 0) goto L_0x009e
            r7 = r9
            goto L_0x009f
        L_0x009e:
            r7 = r4
        L_0x009f:
            int r8 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r8 <= 0) goto L_0x00a5
            r8 = r9
            goto L_0x00a6
        L_0x00a5:
            r8 = r4
        L_0x00a6:
            if (r7 != r8) goto L_0x00aa
            r7 = r9
            goto L_0x00ab
        L_0x00aa:
            r7 = r4
        L_0x00ab:
            if (r7 != 0) goto L_0x00b9
            float r1 = java.lang.Math.abs(r1)
            r7 = 1036831949(0x3dcccccd, float:0.1)
            int r1 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r1 < 0) goto L_0x00b9
            goto L_0x00cd
        L_0x00b9:
            if (r5 >= 0) goto L_0x00ce
            goto L_0x00cb
        L_0x00bc:
            float r5 = java.lang.Math.abs(r1)
            r7 = 1061997773(0x3f4ccccd, float:0.8)
            int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r5 <= 0) goto L_0x00cd
            int r1 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r1 <= 0) goto L_0x00ce
        L_0x00cb:
            r2 = r9
            goto L_0x00ce
        L_0x00cd:
            r2 = r4
        L_0x00ce:
            float r11 = (float) r2
            r1 = 2
            float[] r1 = new float[r1]
            float r5 = r0.currentProgress
            r1[r4] = r5
            r1[r9] = r11
            android.animation.ValueAnimator r1 = android.animation.ValueAnimator.ofFloat(r1)
            com.android.incallui.answer.impl.answermethod.FlingUpDownTouchHandler$2 r5 = new com.android.incallui.answer.impl.answermethod.FlingUpDownTouchHandler$2
            r5.<init>()
            r1.addUpdateListener(r5)
            if (r2 != 0) goto L_0x00ee
            com.android.incallui.answer.impl.utils.FlingAnimationUtils r5 = r0.flingAnimationUtils
            float r7 = r0.currentProgress
            r5.apply(r1, r7, r11, r3)
            goto L_0x00f9
        L_0x00ee:
            com.android.incallui.answer.impl.utils.FlingAnimationUtils r8 = r0.flingAnimationUtils
            float r10 = r0.currentProgress
            r13 = 1065353216(0x3f800000, float:1.0)
            r9 = r1
            r12 = r3
            r8.applyDismissing(r9, r10, r11, r12, r13)
        L_0x00f9:
            if (r2 != 0) goto L_0x00fe
            if (r14 == 0) goto L_0x00fe
            r3 = r6
        L_0x00fe:
            int r2 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r2 != 0) goto L_0x0107
            r2 = 350(0x15e, double:1.73E-321)
            r1.setDuration(r2)
        L_0x0107:
            com.android.incallui.answer.impl.answermethod.FlingUpDownTouchHandler$1 r2 = new com.android.incallui.answer.impl.answermethod.FlingUpDownTouchHandler$1
            r2.<init>()
            r1.addListener(r2)
            r0.progressAnimator = r1
            r1.start()
            r0.tracking = r4
            com.android.incallui.answer.impl.answermethod.FlingUpDownTouchHandler$OnProgressChangedListener r1 = r0.listener
            r1.onTrackingStopped()
        L_0x011b:
            android.view.VelocityTracker r1 = r0.velocityTracker
            if (r1 == 0) goto L_0x0125
            r1.recycle()
            r1 = 0
            r0.velocityTracker = r1
        L_0x0125:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.incallui.answer.impl.answermethod.FlingUpDownTouchHandler.endMotionEvent(android.view.MotionEvent, float, boolean):void");
    }

    /* access modifiers changed from: private */
    public void onMoveEnded() {
        float f = this.currentProgress;
        boolean z = true;
        if (f == 0.0f) {
            this.listener.onMoveReset(!this.hintDistanceExceeded);
            return;
        }
        OnProgressChangedListener onProgressChangedListener = this.listener;
        if (f <= 0.0f) {
            z = false;
        }
        onProgressChangedListener.onMoveFinish(z);
    }

    private float pointerYToProgress(float f) {
        int i = 1;
        boolean z = f > this.zeroY;
        float f2 = z ? this.rejectThresholdY : this.acceptThresholdY;
        float f3 = this.zeroY;
        float f4 = (f - f3) / (f2 - f3);
        if (z) {
            i = -1;
        }
        return Math.max(-1.0f, Math.min(f4 * ((float) i), 1.0f));
    }

    /* access modifiers changed from: private */
    public void setCurrentProgress(float f) {
        if (Math.abs(f) > 0.1f) {
            this.hintDistanceExceeded = true;
        }
        this.currentProgress = f;
        this.listener.onProgressChanged(f);
    }

    private void startMotion(float f, boolean z, float f2) {
        this.initialTouchY = f;
        this.hintDistanceExceeded = false;
        if (((double) f2) <= 0.25d) {
            this.acceptThresholdY = Math.max(0.0f, this.initialTouchY - this.acceptThresholdPx);
            this.rejectThresholdY = Math.min((float) this.target.getHeight(), this.initialTouchY + this.rejectThresholdPx);
            this.zeroY = this.initialTouchY;
        }
        if (z) {
            this.touchSlopExceeded = true;
            this.tracking = true;
            this.listener.onTrackingStart();
            setCurrentProgress(f2);
        }
    }

    public void detach() {
        Animator animator = this.progressAnimator;
        if (animator != null) {
            animator.cancel();
        }
        this.touchEnabled = false;
    }

    public boolean isTracking() {
        return this.tracking;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        int pointerId;
        FalsingManager falsingManager2 = this.falsingManager;
        if (falsingManager2 != null) {
            falsingManager2.onTouchEvent(motionEvent);
        }
        boolean z = false;
        if (!this.touchEnabled) {
            return false;
        }
        if (this.motionAborted && motionEvent.getActionMasked() != 0) {
            return false;
        }
        int findPointerIndex = motionEvent.findPointerIndex(this.trackingPointer);
        if (findPointerIndex < 0) {
            this.trackingPointer = motionEvent.getPointerId(0);
            findPointerIndex = 0;
        }
        float y = motionEvent.getY(findPointerIndex);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    float f = y - this.initialTouchY;
                    if (Math.abs(f) > this.touchSlop) {
                        this.touchSlopExceeded = true;
                    }
                    if (Math.abs(f) >= this.falsingThresholdPx) {
                        this.touchAboveFalsingThreshold = true;
                    }
                    setCurrentProgress(pointerYToProgress(y));
                    VelocityTracker velocityTracker2 = this.velocityTracker;
                    if (velocityTracker2 != null) {
                        velocityTracker2.addMovement(motionEvent);
                    }
                } else if (actionMasked != 3) {
                    if (actionMasked == 5) {
                        this.motionAborted = true;
                        endMotionEvent(motionEvent, y, true);
                        return false;
                    } else if (actionMasked == 6 && this.trackingPointer == (pointerId = motionEvent.getPointerId(motionEvent.getActionIndex()))) {
                        if (motionEvent.getPointerId(0) == pointerId) {
                            z = true;
                        }
                        float y2 = motionEvent.getY(z ? 1 : 0);
                        this.trackingPointer = motionEvent.getPointerId(z);
                        startMotion(y2, true, this.currentProgress);
                    }
                }
            }
            VelocityTracker velocityTracker3 = this.velocityTracker;
            if (velocityTracker3 != null) {
                velocityTracker3.addMovement(motionEvent);
            }
            endMotionEvent(motionEvent, y, false);
        } else if (y < this.deadZoneTopPx) {
            return false;
        } else {
            this.motionAborted = false;
            startMotion(y, false, this.currentProgress);
            this.touchAboveFalsingThreshold = false;
            this.listener.shouldUseFalsing(motionEvent);
            VelocityTracker velocityTracker4 = this.velocityTracker;
            if (velocityTracker4 == null) {
                if (velocityTracker4 != null) {
                    velocityTracker4.recycle();
                }
                this.velocityTracker = VelocityTracker.obtain();
            }
            VelocityTracker velocityTracker5 = this.velocityTracker;
            if (velocityTracker5 != null) {
                velocityTracker5.addMovement(motionEvent);
            }
            Animator animator = this.progressAnimator;
            if (animator != null) {
                animator.cancel();
            }
            if (this.progressAnimator != null) {
                z = true;
            }
            this.touchSlopExceeded = z;
            this.tracking = true;
            this.listener.onTrackingStart();
        }
        return true;
    }

    public void setFlingEnabled(boolean z) {
        this.flingEnabled = z;
    }

    public void setTouchEnabled(boolean z) {
        this.touchEnabled = z;
    }
}
