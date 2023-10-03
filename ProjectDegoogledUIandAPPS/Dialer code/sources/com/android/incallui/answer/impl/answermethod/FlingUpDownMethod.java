package com.android.incallui.answer.impl.answermethod;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Trace;
import android.support.p000v4.graphics.ColorUtils;
import android.support.p000v4.view.animation.FastOutLinearInInterpolator;
import android.support.p000v4.view.animation.FastOutSlowInInterpolator;
import android.support.p000v4.view.animation.LinearOutSlowInInterpolator;
import android.support.p000v4.view.animation.PathInterpolatorCompat;
import android.support.p002v7.appcompat.R$style;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.common.LogUtil;
import com.android.dialer.searchfragment.list.NewSearchFragment;
import com.android.dialer.util.CallUtil;
import com.android.incallui.answer.impl.AnswerFragment;
import com.android.incallui.answer.impl.answermethod.FlingUpDownTouchHandler;
import com.android.incallui.answer.impl.classifier.FalsingManager;
import com.android.incallui.answer.impl.hint.AnswerHint;
import com.android.incallui.answer.impl.hint.AnswerHintFactory;
import com.android.incallui.answer.impl.hint.PawImageLoaderImpl;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SuppressLint({"ClickableViewAccessibility"})
public class FlingUpDownMethod extends AnswerMethod implements FlingUpDownTouchHandler.OnProgressChangedListener {
    /* access modifiers changed from: private */
    public int afterSettleAnimationState = 0;
    /* access modifiers changed from: private */
    public int animationState = 0;
    /* access modifiers changed from: private */
    public AnswerHint answerHint;
    private Drawable contactPhoto;
    private ImageView contactPuckBackground;
    /* access modifiers changed from: private */
    public View contactPuckContainer;
    private ImageView contactPuckIcon;
    private FalsingManager falsingManager;
    /* access modifiers changed from: private */
    public View incomingDisconnectText;
    private boolean incomingWillDisconnect;
    /* access modifiers changed from: private */
    public Animator lockBounceAnim;
    private AnimatorSet lockEntryAnim;
    private AnimatorSet lockHintAnim;
    private AnimatorSet lockSettleAnim;
    /* access modifiers changed from: private */
    public Animator rejectHintHide;
    /* access modifiers changed from: private */
    public View spaceHolder;
    private float swipeProgress;
    private TextView swipeToAnswerText;
    private TextView swipeToRejectText;
    private FlingUpDownTouchHandler touchHandler;
    private Animator vibrationAnimator;

    @Retention(RetentionPolicy.SOURCE)
    @interface AnimationState {
    }

    private static class VibrateInterpolator implements Interpolator {
        private final float ampMax;
        private Interpolator sliderInterpolator = new FastOutSlowInInterpolator();

        VibrateInterpolator(Context context) {
            this.ampMax = 1.0f * context.getResources().getDisplayMetrics().density;
        }

        public float getInterpolation(float f) {
            float f2 = f * 1833.0f;
            float f3 = 1.0f;
            if (f2 > 583.0f && f2 < 750.0f) {
                f3 = this.sliderInterpolator.getInterpolation((f2 - 583.0f) / 167.0f);
            } else if (f2 < 750.0f || f2 > 1583.0f) {
                f3 = (f2 <= 1583.0f || f2 >= 1833.0f) ? 0.0f : 1.0f - this.sliderInterpolator.getInterpolation((f2 - 1583.0f) / 250.0f);
            }
            return (float) (Math.sin((double) (f2 * f3 * 80.0f)) * ((double) (this.ampMax * f3)));
        }
    }

    private void addVibrationAnimator(AnimatorSet animatorSet) {
        Animator animator = this.vibrationAnimator;
        if (animator != null) {
            animator.end();
        }
        this.vibrationAnimator = ObjectAnimator.ofFloat(this.contactPuckContainer, View.TRANSLATION_X, new float[]{0.0f, 1.0f});
        this.vibrationAnimator.setDuration(1833);
        this.vibrationAnimator.setInterpolator(new VibrateInterpolator(getContext()));
        animatorSet.play(this.vibrationAnimator).after(0);
    }

    /* access modifiers changed from: private */
    public Animator createBreatheAnimation() {
        AnimatorSet animatorSet = new AnimatorSet();
        float dpToPx = R$style.dpToPx(getContext(), 42.0f);
        float f = -dpToPx;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.swipeToAnswerText, View.TRANSLATION_Y, new float[]{0.0f, f});
        ofFloat.setInterpolator(new FastOutSlowInInterpolator());
        ofFloat.setDuration(1333);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.swipeToAnswerText, View.TRANSLATION_Y, new float[]{f, 0.0f});
        ofFloat2.setInterpolator(new FastOutSlowInInterpolator());
        ofFloat2.setDuration(1333);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.swipeToRejectText, View.ALPHA, new float[]{1.0f});
        ofFloat3.setInterpolator(new LinearOutSlowInInterpolator());
        ofFloat3.setDuration(667);
        ofFloat3.setStartDelay(333);
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.swipeToRejectText, View.TRANSLATION_Y, new float[]{R$style.dpToPx(getContext(), -8.0f), 0.0f});
        ofFloat4.setInterpolator(new FastOutSlowInInterpolator());
        ofFloat4.setDuration(1333);
        ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(this.swipeToRejectText, View.ALPHA, new float[]{0.0f});
        ofFloat5.setInterpolator(new FastOutLinearInInterpolator());
        ofFloat5.setDuration(667);
        Interpolator create = PathInterpolatorCompat.create(0.4f, 0.0f, 0.0f, 1.0f);
        float dpToPx2 = R$style.dpToPx(getContext(), 42.0f);
        ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(this.contactPuckContainer, View.TRANSLATION_Y, new float[]{-dpToPx2});
        ofFloat6.setInterpolator(create);
        ofFloat6.setDuration(1500);
        ObjectAnimator objectAnimator = ofFloat3;
        Animator createUniformScaleAnimators = createUniformScaleAnimators(this.contactPuckBackground, 1.0f, 1.0625f, 1333, create);
        ObjectAnimator ofFloat7 = ObjectAnimator.ofFloat(this.contactPuckContainer, View.TRANSLATION_Y, new float[]{0.0f});
        ofFloat7.setInterpolator(new FastOutSlowInInterpolator());
        ofFloat7.setDuration(1333);
        Animator createUniformScaleAnimators2 = createUniformScaleAnimators(this.contactPuckBackground, 1.0625f, 1.0f, 1333, new FastOutSlowInInterpolator());
        animatorSet.play(ofFloat).with(ofFloat5).with(ofFloat6).with(createUniformScaleAnimators).after(167);
        animatorSet.play(ofFloat7).with(ofFloat2).with(createUniformScaleAnimators2).with(objectAnimator).with(ofFloat4).after(ofFloat6);
        addVibrationAnimator(animatorSet);
        return animatorSet;
    }

    private ObjectAnimator createFadeAnimation(View view, float f, long j) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{f});
        ofFloat.setDuration(j);
        return ofFloat;
    }

    private Animator createUniformScaleAnimators(View view, float f, float f2, long j, Interpolator interpolator) {
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat(View.SCALE_X, new float[]{f, f2}), PropertyValuesHolder.ofFloat(View.SCALE_Y, new float[]{f, f2})});
        ofPropertyValuesHolder.setDuration(j);
        ofPropertyValuesHolder.setInterpolator(interpolator);
        return ofPropertyValuesHolder;
    }

    private void endAnimation() {
        LogUtil.m9i("FlingUpDownMethod.endAnimation", "End animations.", new Object[0]);
        AnimatorSet animatorSet = this.lockSettleAnim;
        if (animatorSet != null) {
            animatorSet.cancel();
            this.lockSettleAnim = null;
        }
        Animator animator = this.lockBounceAnim;
        if (animator != null) {
            animator.cancel();
            this.lockBounceAnim = null;
        }
        AnimatorSet animatorSet2 = this.lockEntryAnim;
        if (animatorSet2 != null) {
            animatorSet2.cancel();
            this.lockEntryAnim = null;
        }
        AnimatorSet animatorSet3 = this.lockHintAnim;
        if (animatorSet3 != null) {
            animatorSet3.cancel();
            this.lockHintAnim = null;
        }
        Animator animator2 = this.rejectHintHide;
        if (animator2 != null) {
            animator2.cancel();
            this.rejectHintHide = null;
        }
        Animator animator3 = this.vibrationAnimator;
        if (animator3 != null) {
            animator3.end();
            this.vibrationAnimator = null;
        }
        this.answerHint.onBounceEnd();
    }

    private static void fadeToward(View view, float f) {
        view.setAlpha(R$style.lerp(view.getAlpha(), f, 0.5f));
    }

    private static void moveTowardY(View view, float f) {
        view.setTranslationY(R$style.lerp(view.getTranslationY(), f, 0.5f));
    }

    /* access modifiers changed from: private */
    public void performAccept() {
        LogUtil.m9i("FlingUpDownMethod.performAccept", (String) null, new Object[0]);
        this.swipeToAnswerText.setVisibility(8);
        this.contactPuckContainer.setVisibility(8);
        setAnimationState(6);
        ((AnswerFragment) getParent()).answerFromMethod();
    }

    /* access modifiers changed from: private */
    public void performReject() {
        LogUtil.m9i("FlingUpDownMethod.performReject", (String) null, new Object[0]);
        this.swipeToAnswerText.setVisibility(8);
        this.contactPuckContainer.setVisibility(8);
        setAnimationState(6);
        ((AnswerFragment) getParent()).rejectFromMethod();
    }

    private void resetTouchState() {
        if (getContext() != null) {
            float f = 1.0f;
            this.contactPuckContainer.animate().scaleX(1.0f);
            this.contactPuckContainer.animate().scaleY(1.0f);
            this.contactPuckBackground.animate().scaleX(1.0f);
            this.contactPuckBackground.animate().scaleY(1.0f);
            this.contactPuckBackground.setBackgroundTintList((ColorStateList) null);
            this.contactPuckBackground.setColorFilter((ColorFilter) null);
            this.contactPuckIcon.setImageTintList(ColorStateList.valueOf(getContext().getColor(R.color.incoming_answer_icon)));
            this.contactPuckIcon.animate().rotation(0.0f);
            ((AnswerFragment) getParent()).resetAnswerProgress();
            this.contactPuckBackground.setActivated(this.touchHandler.isTracking());
            this.swipeToAnswerText.animate().alpha(1.0f);
            this.contactPuckContainer.animate().alpha(1.0f);
            this.contactPuckBackground.animate().alpha(1.0f);
            ViewPropertyAnimator animate = this.contactPuckIcon.animate();
            if (shouldShowPhotoInPuck()) {
                f = 0.0f;
            }
            animate.alpha(f);
        }
    }

    private static void rotateToward(View view, float f) {
        view.setRotation(R$style.lerp(view.getRotation(), f, 0.5f));
    }

    private boolean shouldShowPhotoInPuck() {
        return (((AnswerFragment) getParent()).isVideoCall() || ((AnswerFragment) getParent()).isVideoUpgradeRequest()) && this.contactPhoto != null;
    }

    private void startSwipeToAnswerEntryAnimation() {
        LogUtil.m9i("FlingUpDownMethod.startSwipeToAnswerEntryAnimation", "Swipe entry animation.", new Object[0]);
        endAnimation();
        this.lockEntryAnim = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.swipeToAnswerText, View.TRANSLATION_Y, new float[]{R$style.dpToPx(getContext(), 192.0f), R$style.dpToPx(getContext(), -20.0f)});
        ofFloat.setDuration(1333);
        ofFloat.setInterpolator(new LinearOutSlowInInterpolator());
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.swipeToAnswerText, View.TRANSLATION_Y, new float[]{R$style.dpToPx(getContext(), -20.0f), 0.0f});
        ofFloat2.setDuration(1333);
        ofFloat.setInterpolator(new FastOutSlowInInterpolator());
        this.swipeToRejectText.setAlpha(0.0f);
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this.swipeToRejectText, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat(View.ALPHA, new float[]{1.0f}), PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, new float[]{R$style.dpToPx(getContext(), -8.0f), 0.0f})});
        ofPropertyValuesHolder.setInterpolator(new FastOutLinearInInterpolator());
        ofPropertyValuesHolder.setDuration(667);
        ofPropertyValuesHolder.setStartDelay(333);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.contactPuckContainer, View.TRANSLATION_Y, new float[]{R$style.dpToPx(getContext(), 400.0f), R$style.dpToPx(getContext(), -12.0f)});
        ofFloat3.setDuration(1500);
        ofFloat3.setInterpolator(PathInterpolatorCompat.create(0.0f, 0.0f, 0.0f, 1.0f));
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.contactPuckContainer, View.TRANSLATION_Y, new float[]{R$style.dpToPx(getContext(), -12.0f), 0.0f});
        ofFloat4.setDuration(1333);
        ofFloat4.setInterpolator(new FastOutSlowInInterpolator());
        Animator createUniformScaleAnimators = createUniformScaleAnimators(this.contactPuckBackground, 0.33f, 1.1f, 1333, PathInterpolatorCompat.create(0.4f, 0.0f, 0.0f, 1.0f));
        Animator createUniformScaleAnimators2 = createUniformScaleAnimators(this.contactPuckBackground, 1.1f, 1.0f, 1333, new FastOutSlowInInterpolator());
        this.lockEntryAnim.play(ofFloat).with(createUniformScaleAnimators).with(ofFloat3);
        this.lockEntryAnim.play(ofFloat2).with(ofFloat4).with(createUniformScaleAnimators2).after(ofFloat3);
        this.lockEntryAnim.play(ofPropertyValuesHolder).after(ofFloat3);
        addVibrationAnimator(this.lockEntryAnim);
        this.lockEntryAnim.addListener(new AnimatorListenerAdapter() {
            public boolean canceled;

            public void onAnimationCancel(Animator animator) {
                super.onAnimationCancel(animator);
                this.canceled = true;
            }

            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                if (!this.canceled) {
                    FlingUpDownMethod.this.onEntryAnimationDone();
                }
            }
        });
        this.lockEntryAnim.start();
    }

    private void updateContactPuck() {
        if (this.contactPuckIcon != null) {
            if (((AnswerFragment) getParent()).isVideoCall() || ((AnswerFragment) getParent()).isVideoUpgradeRequest()) {
                this.contactPuckIcon.setImageResource(R.drawable.quantum_ic_videocam_vd_white_24);
            } else if (((AnswerFragment) getParent()).getArguments().getBoolean("is_rtt_call")) {
                this.contactPuckIcon.setImageResource(R.drawable.quantum_ic_rtt_vd_theme_24);
            } else {
                this.contactPuckIcon.setImageResource(R.drawable.quantum_ic_call_white_24);
            }
            int dimensionPixelSize = this.contactPuckBackground.getResources().getDimensionPixelSize(shouldShowPhotoInPuck() ? R.dimen.answer_contact_puck_size_photo : R.dimen.answer_contact_puck_size_no_photo);
            this.contactPuckBackground.setImageDrawable(shouldShowPhotoInPuck() ? R$style.getRoundedDrawable(this.contactPuckBackground.getContext(), this.contactPhoto, dimensionPixelSize, dimensionPixelSize) : null);
            ViewGroup.LayoutParams layoutParams = this.contactPuckBackground.getLayoutParams();
            layoutParams.height = dimensionPixelSize;
            layoutParams.width = dimensionPixelSize;
            this.contactPuckBackground.setLayoutParams(layoutParams);
            this.contactPuckIcon.setAlpha(shouldShowPhotoInPuck() ? 0.0f : 1.0f);
        }
    }

    /* access modifiers changed from: package-private */
    public int getAnimationState() {
        return this.animationState;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.falsingManager = new FalsingManager(getContext());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Trace.beginSection("FlingUpDownMethod.onCreateView");
        int i = 0;
        View inflate = layoutInflater.inflate(R.layout.swipe_up_down_method, viewGroup, false);
        this.contactPuckContainer = inflate.findViewById(R.id.incoming_call_puck_container);
        this.contactPuckBackground = (ImageView) inflate.findViewById(R.id.incoming_call_puck_bg);
        this.contactPuckIcon = (ImageView) inflate.findViewById(R.id.incoming_call_puck_icon);
        this.swipeToAnswerText = (TextView) inflate.findViewById(R.id.incoming_swipe_to_answer_text);
        this.swipeToRejectText = (TextView) inflate.findViewById(R.id.incoming_swipe_to_reject_text);
        this.incomingDisconnectText = inflate.findViewById(R.id.incoming_will_disconnect_text);
        this.incomingDisconnectText.setVisibility(this.incomingWillDisconnect ? 0 : 8);
        this.incomingDisconnectText.setAlpha(this.incomingWillDisconnect ? 1.0f : 0.0f);
        this.spaceHolder = inflate.findViewById(R.id.incoming_bouncer_space_holder);
        View view = this.spaceHolder;
        if (this.incomingWillDisconnect) {
            i = 8;
        }
        view.setVisibility(i);
        inflate.findViewById(R.id.incoming_swipe_to_answer_container).setAccessibilityDelegate(new View.AccessibilityDelegate() {
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.accessibility_action_answer, FlingUpDownMethod.this.getString(R.string.call_incoming_answer)));
                accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.accessibility_action_decline, FlingUpDownMethod.this.getString(R.string.call_incoming_decline)));
            }

            public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
                if (i == R.id.accessibility_action_answer) {
                    FlingUpDownMethod.this.performAccept();
                    return true;
                } else if (i != R.id.accessibility_action_decline) {
                    return super.performAccessibilityAction(view, i, bundle);
                } else {
                    FlingUpDownMethod.this.performReject();
                    return true;
                }
            }
        });
        this.swipeProgress = 0.0f;
        updateContactPuck();
        this.touchHandler = FlingUpDownTouchHandler.attach(inflate, this, this.falsingManager);
        this.answerHint = new AnswerHintFactory(new PawImageLoaderImpl()).create(getContext(), 1500, 167);
        this.answerHint.onCreateView(layoutInflater, (ViewGroup) inflate.findViewById(R.id.hint_container), this.contactPuckContainer, this.swipeToAnswerText);
        Trace.endSection();
        return inflate;
    }

    public void onDestroyView() {
        super.onDestroyView();
        FlingUpDownTouchHandler flingUpDownTouchHandler = this.touchHandler;
        if (flingUpDownTouchHandler != null) {
            flingUpDownTouchHandler.detach();
            this.touchHandler = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void onEntryAnimationDone() {
        LogUtil.m9i("FlingUpDownMethod.onEntryAnimationDone", "Swipe entry anim ends.", new Object[0]);
        if (this.animationState == 1) {
            setAnimationState(2);
        }
    }

    /* access modifiers changed from: package-private */
    public void onHintAnimationDone(boolean z) {
        if (!z && this.animationState == 5) {
            setAnimationState(2);
        }
        this.rejectHintHide = null;
    }

    public void onMoveFinish(boolean z) {
        this.touchHandler.setTouchEnabled(false);
        this.answerHint.onAnswered();
        if (z) {
            performAccept();
        } else {
            performReject();
        }
    }

    public void onMoveReset(boolean z) {
        if (z) {
            setAnimationState(5);
        } else {
            setAnimationState(2);
        }
        resetTouchState();
        ((AnswerFragment) getParent()).resetAnswerProgress();
    }

    public void onProgressChanged(float f) {
        this.swipeProgress = f;
        if (this.animationState == 3 && getContext() != null && isVisible()) {
            Trace.beginSection("FlingUpDownMethod.updateSwipeTextAndPuckForTouch");
            float clamp = R$style.clamp(this.swipeProgress, -1.0f, 1.0f);
            float abs = Math.abs(clamp);
            boolean z = clamp >= 0.0f;
            this.swipeToAnswerText.animate().cancel();
            this.contactPuckIcon.animate().cancel();
            float max = Math.max(0.0f, 1.0f - (Math.abs(clamp) * 9.0f));
            fadeToward(this.swipeToAnswerText, max);
            TextView textView = this.swipeToRejectText;
            fadeToward(textView, Math.min(max, textView.getAlpha()));
            View view = this.incomingDisconnectText;
            if (!this.incomingWillDisconnect) {
                max = 0.0f;
            }
            fadeToward(view, max);
            TextView textView2 = this.swipeToAnswerText;
            textView2.setTranslationX(R$style.lerp(textView2.getTranslationX(), 0.0f, 0.5f));
            moveTowardY(this.swipeToAnswerText, 0.0f);
            int alphaComponent = ColorUtils.setAlphaComponent(getContext().getColor(z ? R.color.call_accept_background : R.color.call_hangup_background), (int) (abs * 255.0f));
            this.contactPuckBackground.setBackgroundTintList(ColorStateList.valueOf(alphaComponent));
            this.contactPuckBackground.setBackgroundTintMode(PorterDuff.Mode.SRC_ATOP);
            this.contactPuckBackground.setColorFilter(alphaComponent);
            if (z || ((AnswerFragment) getParent()).isVideoCall() || ((AnswerFragment) getParent()).isVideoUpgradeRequest()) {
                rotateToward(this.contactPuckIcon, 0.0f);
            } else {
                rotateToward(this.contactPuckIcon, 135.0f * abs);
            }
            if (shouldShowPhotoInPuck()) {
                fadeToward(this.contactPuckIcon, abs);
            }
            this.contactPuckIcon.setImageTintList(ColorStateList.valueOf(ColorUtils.setAlphaComponent(this.contactPuckIcon.getContext().getColor(R.color.incoming_answer_icon), (int) ((1.0f - Math.min(1.0f, abs * 4.0f)) * 255.0f))));
            if (z) {
                moveTowardY(this.contactPuckContainer, R$style.dpToPx(getContext(), 150.0f) * (-clamp));
            } else {
                moveTowardY(this.contactPuckContainer, R$style.dpToPx(getContext(), 24.0f) * (-clamp));
            }
            ((AnswerFragment) getParent()).onAnswerProgressUpdate(clamp);
            Trace.endSection();
        }
    }

    /* access modifiers changed from: package-private */
    public void onSettleAnimationDone() {
        int i = this.afterSettleAnimationState;
        if (i != 0) {
            this.afterSettleAnimationState = 0;
            this.lockSettleAnim = null;
            setAnimationState(i);
        }
    }

    public void onStart() {
        Trace.beginSection("FlingUpDownMethod.onStart");
        super.onStart();
        this.falsingManager.onScreenOn();
        if (getView() != null) {
            int i = this.animationState;
            if (i == 3 || i == 5) {
                this.swipeProgress = 0.0f;
                updateContactPuck();
                onMoveReset(false);
            } else if (i == 1) {
                startSwipeToAnswerEntryAnimation();
            }
        }
        Trace.endSection();
    }

    public void onStop() {
        Trace.beginSection("FlingUpDownMethod.onStop");
        endAnimation();
        this.falsingManager.onScreenOff();
        if (getActivity().isFinishing()) {
            setAnimationState(6);
        }
        super.onStop();
        Trace.endSection();
    }

    public void onTrackingStart() {
        setAnimationState(3);
    }

    public void onTrackingStopped() {
    }

    public void onViewCreated(View view, Bundle bundle) {
        setAnimationState(1);
    }

    /* access modifiers changed from: package-private */
    public void setAnimationState(int i) {
        int i2;
        int i3 = i;
        if (i3 != 5 && this.animationState == i3) {
            return;
        }
        if (this.animationState == 6) {
            LogUtil.m8e("FlingUpDownMethod.setAnimationState", GeneratedOutlineSupport.outline5("Animation loop has completed. Cannot switch to new state: ", i3), new Object[0]);
            return;
        }
        if ((i3 == 5 || i3 == 2) && this.animationState == 3) {
            this.afterSettleAnimationState = i3;
            i3 = 4;
        }
        LogUtil.m9i("FlingUpDownMethod.setAnimationState", GeneratedOutlineSupport.outline5("animation state: ", i3), new Object[0]);
        this.animationState = i3;
        if (getView() == null) {
            return;
        }
        if (!isAdded() || (i2 = this.animationState) != i3) {
            endAnimation();
            return;
        }
        switch (i2) {
            case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE:
                startSwipeToAnswerEntryAnimation();
                return;
            case 2:
                LogUtil.m9i("FlingUpDownMethod.startSwipeToAnswerBounceAnimation", "Swipe bounce animation.", new Object[0]);
                endAnimation();
                if (CallUtil.areAnimationsDisabled(getContext())) {
                    this.swipeToAnswerText.setTranslationY(0.0f);
                    this.contactPuckContainer.setTranslationY(0.0f);
                    this.contactPuckBackground.setScaleY(1.0f);
                    this.contactPuckBackground.setScaleX(1.0f);
                    this.swipeToRejectText.setAlpha(1.0f);
                    this.swipeToRejectText.setTranslationY(0.0f);
                    return;
                }
                this.lockBounceAnim = createBreatheAnimation();
                this.answerHint.onBounceStart();
                this.lockBounceAnim.addListener(new AnimatorListenerAdapter() {
                    boolean firstPass = true;

                    public void onAnimationEnd(Animator animator) {
                        super.onAnimationEnd(animator);
                        if (FlingUpDownMethod.this.getContext() != null && FlingUpDownMethod.this.lockBounceAnim != null && FlingUpDownMethod.this.animationState == 2) {
                            if (this.firstPass) {
                                FlingUpDownMethod flingUpDownMethod = FlingUpDownMethod.this;
                                Animator unused = flingUpDownMethod.lockBounceAnim = flingUpDownMethod.createBreatheAnimation();
                                FlingUpDownMethod.this.lockBounceAnim.addListener(this);
                            }
                            this.firstPass = false;
                            FlingUpDownMethod.this.answerHint.onBounceStart();
                            FlingUpDownMethod.this.lockBounceAnim.start();
                        }
                    }
                });
                this.lockBounceAnim.start();
                return;
            case 3:
                LogUtil.m9i("FlingUpDownMethod.startSwipeToAnswerSwipeAnimation", "Start swipe animation.", new Object[0]);
                resetTouchState();
                endAnimation();
                return;
            case 4:
                endAnimation();
                ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this.contactPuckBackground, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat(View.SCALE_X, new float[]{1.0f}), PropertyValuesHolder.ofFloat(View.SCALE_Y, new float[]{1.0f})});
                ofPropertyValuesHolder.setDuration(100);
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.contactPuckIcon, View.ROTATION, new float[]{0.0f});
                ofFloat.setDuration(100);
                ObjectAnimator createFadeAnimation = createFadeAnimation(this.swipeToAnswerText, 1.0f, 100);
                ObjectAnimator createFadeAnimation2 = createFadeAnimation(this.contactPuckContainer, 1.0f, 100);
                ObjectAnimator createFadeAnimation3 = createFadeAnimation(this.contactPuckBackground, 1.0f, 100);
                ObjectAnimator createFadeAnimation4 = createFadeAnimation(this.contactPuckIcon, shouldShowPhotoInPuck() ? 0.0f : 1.0f, 100);
                ObjectAnimator ofPropertyValuesHolder2 = ObjectAnimator.ofPropertyValuesHolder(this.contactPuckContainer, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat(View.TRANSLATION_X, new float[]{0.0f}), PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, new float[]{0.0f})});
                ofPropertyValuesHolder2.setDuration(100);
                this.lockSettleAnim = new AnimatorSet();
                this.lockSettleAnim.play(ofPropertyValuesHolder).with(ofFloat).with(createFadeAnimation).with(createFadeAnimation2).with(createFadeAnimation3).with(createFadeAnimation4).with(ofPropertyValuesHolder2);
                this.lockSettleAnim.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationCancel(Animator animator) {
                        int unused = FlingUpDownMethod.this.afterSettleAnimationState = 0;
                    }

                    public void onAnimationEnd(Animator animator) {
                        FlingUpDownMethod.this.onSettleAnimationDone();
                    }
                });
                this.lockSettleAnim.start();
                return;
            case 5:
                Animator animator = this.rejectHintHide;
                if (animator != null) {
                    animator.cancel();
                }
                endAnimation();
                resetTouchState();
                if (CallUtil.areAnimationsDisabled(getContext())) {
                    onHintAnimationDone(false);
                    return;
                }
                this.lockHintAnim = new AnimatorSet();
                float dpToPx = R$style.dpToPx(getContext(), 60.0f);
                float dpToPx2 = R$style.dpToPx(getContext(), 8.0f);
                int integer = getContext().getResources().getInteger(17694720);
                int integer2 = getContext().getResources().getInteger(17694721);
                ObjectAnimator ofPropertyValuesHolder3 = ObjectAnimator.ofPropertyValuesHolder(this.contactPuckContainer, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat(View.SCALE_Y, new float[]{0.95f}), PropertyValuesHolder.ofFloat(View.SCALE_X, new float[]{1.05f})});
                ofPropertyValuesHolder3.setRepeatCount(1);
                ofPropertyValuesHolder3.setRepeatMode(2);
                long j = (long) (integer / 2);
                ofPropertyValuesHolder3.setDuration(j);
                ofPropertyValuesHolder3.setInterpolator(new DecelerateInterpolator());
                ofPropertyValuesHolder3.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animator) {
                        super.onAnimationEnd(animator);
                        FlingUpDownMethod.this.contactPuckContainer.setPivotY((float) (FlingUpDownMethod.this.contactPuckContainer.getHeight() / 2));
                    }

                    public void onAnimationStart(Animator animator) {
                        super.onAnimationStart(animator);
                        FlingUpDownMethod.this.contactPuckContainer.setPivotY((float) FlingUpDownMethod.this.contactPuckContainer.getHeight());
                    }
                });
                ObjectAnimator ofPropertyValuesHolder4 = ObjectAnimator.ofPropertyValuesHolder(this.contactPuckContainer, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, new float[]{0.0f}), PropertyValuesHolder.ofFloat(View.TRANSLATION_X, new float[]{0.0f})});
                ofPropertyValuesHolder4.setDuration(j);
                ofPropertyValuesHolder3.setInterpolator(new DecelerateInterpolator());
                ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.swipeToAnswerText, View.TRANSLATION_Y, new float[]{-((((float) this.contactPuckBackground.getHeight()) * 0.14999998f) + dpToPx)});
                ofFloat2.setInterpolator(new LinearOutSlowInInterpolator());
                long j2 = (long) integer;
                ofFloat2.setDuration(j2);
                ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.contactPuckContainer, View.TRANSLATION_Y, new float[]{-dpToPx});
                ofFloat3.setInterpolator(new LinearOutSlowInInterpolator());
                ofFloat3.setDuration(j2);
                long j3 = j2;
                ObjectAnimator objectAnimator = ofFloat3;
                Animator createUniformScaleAnimators = createUniformScaleAnimators(this.contactPuckBackground, 1.0f, 1.15f, j2, new LinearOutSlowInInterpolator());
                ObjectAnimator ofPropertyValuesHolder5 = ObjectAnimator.ofPropertyValuesHolder(this.swipeToRejectText, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat(View.ALPHA, new float[]{1.0f}), PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, new float[]{0.0f})});
                long j4 = j3;
                ofPropertyValuesHolder5.setDuration(j4);
                ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.swipeToRejectText, View.TRANSLATION_Y, new float[]{dpToPx2});
                ofFloat4.setInterpolator(new LinearOutSlowInInterpolator());
                ofFloat4.setDuration(j4);
                ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(this.swipeToAnswerText, View.TRANSLATION_Y, new float[]{0.0f});
                ofFloat5.setInterpolator(new LinearOutSlowInInterpolator());
                long j5 = (long) integer2;
                ofFloat5.setDuration(j5);
                ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(this.contactPuckContainer, View.TRANSLATION_Y, new float[]{0.0f});
                ofFloat6.setInterpolator(new BounceInterpolator());
                ofFloat6.setDuration(j5);
                long j6 = j5;
                Animator createUniformScaleAnimators2 = createUniformScaleAnimators(this.contactPuckBackground, 1.15f, 1.0f, j3, new LinearOutSlowInInterpolator());
                ObjectAnimator ofFloat7 = ObjectAnimator.ofFloat(this.swipeToRejectText, View.TRANSLATION_Y, new float[]{0.0f});
                ofFloat7.setInterpolator(new LinearOutSlowInInterpolator());
                ofFloat7.setDuration(j6);
                ObjectAnimator objectAnimator2 = objectAnimator;
                this.lockHintAnim.play(ofPropertyValuesHolder3).with(ofPropertyValuesHolder4).before(objectAnimator2);
                this.lockHintAnim.play(ofFloat2).with(objectAnimator2).with(createUniformScaleAnimators).with(ofFloat4).with(ofPropertyValuesHolder5);
                this.lockHintAnim.play(ofFloat5).with(ofFloat6).with(createUniformScaleAnimators2).with(ofFloat7).after(objectAnimator2);
                this.lockHintAnim.start();
                this.rejectHintHide = ObjectAnimator.ofFloat(this.swipeToRejectText, View.ALPHA, new float[]{0.0f});
                this.rejectHintHide.setStartDelay(2000);
                this.rejectHintHide.addListener(new AnimatorListenerAdapter() {
                    private boolean canceled;

                    public void onAnimationCancel(Animator animator) {
                        super.onAnimationCancel(animator);
                        this.canceled = true;
                        Animator unused = FlingUpDownMethod.this.rejectHintHide = null;
                    }

                    public void onAnimationEnd(Animator animator) {
                        super.onAnimationEnd(animator);
                        FlingUpDownMethod.this.onHintAnimationDone(this.canceled);
                    }
                });
                this.rejectHintHide.start();
                return;
            case 6:
                LogUtil.m9i("FlingUpDownMethod.clearSwipeToAnswerUi", "Clear swipe animation.", new Object[0]);
                endAnimation();
                this.swipeToAnswerText.setVisibility(8);
                this.contactPuckContainer.setVisibility(8);
                return;
            default:
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("Unexpected animation state: ");
                outline13.append(this.animationState);
                LogUtil.m8e("FlingUpDownMethod.updateAnimationState", outline13.toString(), new Object[0]);
                return;
        }
    }

    public void setContactPhoto(Drawable drawable) {
        this.contactPhoto = drawable;
        updateContactPuck();
    }

    public void setHintText(CharSequence charSequence) {
        if (charSequence == null) {
            this.swipeToAnswerText.setText(R.string.call_incoming_swipe_to_answer);
        } else {
            this.swipeToAnswerText.setText(charSequence);
        }
        this.swipeToRejectText.setText(R.string.call_incoming_swipe_to_reject);
    }

    public void setShowIncomingWillDisconnect(boolean z) {
        this.incomingWillDisconnect = z;
        View view = this.incomingDisconnectText;
        if (view == null) {
            return;
        }
        if (z) {
            view.setVisibility(0);
            this.spaceHolder.setVisibility(8);
            this.incomingDisconnectText.animate().alpha(1.0f);
            return;
        }
        view.animate().alpha(0.0f).setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                FlingUpDownMethod.this.incomingDisconnectText.setVisibility(8);
                FlingUpDownMethod.this.spaceHolder.setVisibility(0);
            }
        });
    }

    public boolean shouldUseFalsing(MotionEvent motionEvent) {
        View view = this.contactPuckContainer;
        if (view == null) {
            return false;
        }
        float x = view.getX() + ((float) (this.contactPuckContainer.getWidth() / 2));
        float y = this.contactPuckContainer.getY() + ((float) (this.contactPuckContainer.getHeight() / 2));
        double height = (double) (this.contactPuckContainer.getHeight() / 2);
        if (Math.pow((double) (motionEvent.getY() - y), 2.0d) + Math.pow((double) (motionEvent.getX() - x), 2.0d) >= Math.pow(height, 2.0d)) {
            return true;
        }
        return false;
    }
}
