package com.android.incallui.answer.impl.hint;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.p000v4.view.animation.FastOutSlowInInterpolator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.common.Assert;

public final class PawAnswerHint implements AnswerHint {
    private AnimatorSet answerGestureHintAnim;
    /* access modifiers changed from: private */
    public View answerHintContainer;
    private final Context context;
    private final Drawable payload;
    /* access modifiers changed from: private */
    public View payloadView;
    private View puck;
    private final long puckUpDelayMillis;
    private final long puckUpDurationMillis;

    public PawAnswerHint(Context context2, Drawable drawable, long j, long j2) {
        Assert.isNotNull(context2);
        this.context = context2;
        Assert.isNotNull(drawable);
        this.payload = drawable;
        this.puckUpDurationMillis = j;
        this.puckUpDelayMillis = j2;
    }

    private static Animator createAlphaAnimator(View view, float f, float f2, long j, long j2, Interpolator interpolator) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{f, f2});
        ofFloat.setDuration(j);
        ofFloat.setInterpolator(interpolator);
        ofFloat.setStartDelay(j2);
        return ofFloat;
    }

    private static Animator createUniformScaleAnimator(View view, float f, float f2, long j, long j2, Interpolator interpolator) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.SCALE_X, new float[]{f, f2});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[]{f, f2});
        ofFloat.setDuration(j);
        ofFloat2.setDuration(j);
        ofFloat.setInterpolator(interpolator);
        ofFloat2.setInterpolator(interpolator);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofFloat).with(ofFloat2).after(j2);
        return animatorSet;
    }

    private float getDimension(int i) {
        return this.context.getResources().getDimension(i);
    }

    public void onAnswered() {
    }

    public void onBounceEnd() {
        AnimatorSet animatorSet = this.answerGestureHintAnim;
        if (animatorSet != null) {
            animatorSet.end();
            this.answerGestureHintAnim = null;
            this.answerHintContainer.setVisibility(8);
        }
    }

    public void onBounceStart() {
        if (this.answerGestureHintAnim == null) {
            this.answerGestureHintAnim = new AnimatorSet();
            final int[] iArr = new int[2];
            this.puck.getLocationInWindow(iArr);
            this.answerHintContainer.setY(((float) iArr[1]) + getDimension(R.dimen.hint_initial_offset));
            AnimatorSet animatorSet = new AnimatorSet();
            View view = this.payloadView;
            Animator createUniformScaleAnimator = createUniformScaleAnimator(view, 2.0f, 1.5f, 200, 380, new LinearInterpolator());
            Animator createAlphaAnimator = createAlphaAnimator(view, 0.0f, 1.0f, 50, 340, new LinearInterpolator());
            AnimatorSet animatorSet2 = new AnimatorSet();
            animatorSet2.play(createUniformScaleAnimator).with(createAlphaAnimator);
            animatorSet.play(animatorSet2);
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.answerHintContainer, View.TRANSLATION_Y, new float[]{((float) iArr[1]) - getDimension(R.dimen.hint_offset)});
            ofFloat.setInterpolator(new FastOutSlowInInterpolator());
            ofFloat.setDuration(500);
            AnimatorSet animatorSet3 = new AnimatorSet();
            View view2 = this.payloadView;
            Animator createUniformScaleAnimator2 = createUniformScaleAnimator(view2, 1.5f, 2.0f, 100, 90, new LinearInterpolator());
            Animator createAlphaAnimator2 = createAlphaAnimator(view2, 1.0f, 0.0f, 170, 130, new LinearInterpolator());
            AnimatorSet animatorSet4 = new AnimatorSet();
            animatorSet4.play(createUniformScaleAnimator2).with(createAlphaAnimator2);
            animatorSet3.play(animatorSet4);
            this.answerGestureHintAnim.play(animatorSet).after(this.puckUpDelayMillis);
            this.answerGestureHintAnim.play(ofFloat).after(animatorSet);
            this.answerGestureHintAnim.play(animatorSet3).after((this.puckUpDelayMillis + this.puckUpDurationMillis) - 130);
            animatorSet.addListener(new AnimatorListenerAdapter() {
                public void onAnimationStart(Animator animator) {
                    super.onAnimationStart(animator);
                    PawAnswerHint.this.payloadView.setAlpha(0.0f);
                    PawAnswerHint.this.payloadView.setScaleX(1.0f);
                    PawAnswerHint.this.payloadView.setScaleY(1.0f);
                    PawAnswerHint.this.answerHintContainer.setY(((float) iArr[1]) + PawAnswerHint.this.context.getResources().getDimension(R.dimen.hint_initial_offset));
                    PawAnswerHint.this.answerHintContainer.setVisibility(0);
                }
            });
        }
        this.answerGestureHintAnim.start();
    }

    public void onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, View view, TextView textView) {
        this.puck = view;
        View inflate = layoutInflater.inflate(R.layout.paw_hint, viewGroup, true);
        this.answerHintContainer = inflate.findViewById(R.id.answer_hint_container);
        this.payloadView = inflate.findViewById(R.id.paw_image);
        textView.setTextSize(0, this.context.getResources().getDimension(R.dimen.hint_text_size));
        ((ImageView) this.payloadView).setImageDrawable(this.payload);
    }
}
