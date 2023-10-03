package com.android.incallui.answer.impl.hint;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.p000v4.view.animation.FastOutSlowInInterpolator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.storage.StorageComponent;

public class DotAnswerHint implements AnswerHint {
    private AnimatorSet answerGestureHintAnim;
    /* access modifiers changed from: private */
    public View answerHintContainer;
    /* access modifiers changed from: private */
    public View answerHintLarge;
    /* access modifiers changed from: private */
    public View answerHintMid;
    /* access modifiers changed from: private */
    public View answerHintSmall;
    private final Context context;
    private View puck;
    private final long puckUpDelayMillis;
    private final long puckUpDurationMillis;

    public DotAnswerHint(Context context2, long j, long j2) {
        this.context = context2;
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

    private Animator createFadeInScaleAndAlpha(View view, int i, int i2, float f) {
        Animator createUniformScaleAnimator = createUniformScaleAnimator(view, this.context.getResources().getDimension(i), this.context.getResources().getDimension(i), this.context.getResources().getDimension(i2), 200, 380, new LinearInterpolator());
        Animator createAlphaAnimator = createAlphaAnimator(view, 0.0f, f, 50, 340, new LinearInterpolator());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(createUniformScaleAnimator).with(createAlphaAnimator);
        return animatorSet;
    }

    private Animator createFadeOutScaleAndAlpha(View view, int i, int i2, long j, float f) {
        int i3 = i;
        int i4 = i2;
        Animator createUniformScaleAnimator = createUniformScaleAnimator(view, this.context.getResources().getDimension(i), this.context.getResources().getDimension(i2), this.context.getResources().getDimension(i), 100, j, new LinearInterpolator());
        Animator createAlphaAnimator = createAlphaAnimator(view, f, 0.0f, 170, 130, new LinearInterpolator());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(createUniformScaleAnimator).with(createAlphaAnimator);
        return animatorSet;
    }

    private static Animator createUniformScaleAnimator(View view, float f, float f2, float f3, long j, long j2, Interpolator interpolator) {
        float f4 = f2 / f;
        float f5 = f3 / f;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.SCALE_X, new float[]{f4, f5});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[]{f4, f5});
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
        SharedPreferences unencryptedSharedPrefs = StorageComponent.get(this.context).unencryptedSharedPrefs();
        unencryptedSharedPrefs.edit().putInt("answer_hint_answered_count", unencryptedSharedPrefs.getInt("answer_hint_answered_count", 0) + 1).apply();
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
            animatorSet.play(createFadeInScaleAndAlpha(this.answerHintSmall, R.dimen.hint_small_begin_size, R.dimen.hint_small_end_size, 0.8f)).with(createFadeInScaleAndAlpha(this.answerHintMid, R.dimen.hint_mid_begin_size, R.dimen.hint_mid_end_size, 0.5f)).with(createFadeInScaleAndAlpha(this.answerHintLarge, R.dimen.hint_large_begin_size, R.dimen.hint_large_end_size, 0.2f));
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.answerHintContainer, View.TRANSLATION_Y, new float[]{((float) iArr[1]) - getDimension(R.dimen.hint_offset)});
            ofFloat.setInterpolator(new FastOutSlowInInterpolator());
            ofFloat.setDuration(500);
            AnimatorSet animatorSet2 = new AnimatorSet();
            animatorSet2.play(createFadeOutScaleAndAlpha(this.answerHintSmall, R.dimen.hint_small_begin_size, R.dimen.hint_small_end_size, 90, 0.8f)).with(createFadeOutScaleAndAlpha(this.answerHintMid, R.dimen.hint_mid_begin_size, R.dimen.hint_mid_end_size, 70, 0.5f)).with(createFadeOutScaleAndAlpha(this.answerHintLarge, R.dimen.hint_large_begin_size, R.dimen.hint_large_end_size, 10, 0.2f));
            this.answerGestureHintAnim.play(animatorSet).after(this.puckUpDelayMillis);
            this.answerGestureHintAnim.play(ofFloat).after(animatorSet);
            this.answerGestureHintAnim.play(animatorSet2).after((this.puckUpDelayMillis + this.puckUpDurationMillis) - 130);
            animatorSet.addListener(new AnimatorListenerAdapter() {
                public void onAnimationStart(Animator animator) {
                    super.onAnimationStart(animator);
                    DotAnswerHint.this.answerHintSmall.setAlpha(0.0f);
                    DotAnswerHint.this.answerHintSmall.setScaleX(1.0f);
                    DotAnswerHint.this.answerHintSmall.setScaleY(1.0f);
                    DotAnswerHint.this.answerHintMid.setAlpha(0.0f);
                    DotAnswerHint.this.answerHintMid.setScaleX(1.0f);
                    DotAnswerHint.this.answerHintMid.setScaleY(1.0f);
                    DotAnswerHint.this.answerHintLarge.setAlpha(0.0f);
                    DotAnswerHint.this.answerHintLarge.setScaleX(1.0f);
                    DotAnswerHint.this.answerHintLarge.setScaleY(1.0f);
                    DotAnswerHint.this.answerHintContainer.setY(((float) iArr[1]) + DotAnswerHint.this.context.getResources().getDimension(R.dimen.hint_initial_offset));
                    DotAnswerHint.this.answerHintContainer.setVisibility(0);
                }
            });
        }
        this.answerGestureHintAnim.start();
    }

    public void onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, View view, TextView textView) {
        this.puck = view;
        View inflate = layoutInflater.inflate(R.layout.dot_hint, viewGroup, true);
        this.answerHintContainer = inflate.findViewById(R.id.answer_hint_container);
        this.answerHintSmall = inflate.findViewById(R.id.answer_hint_small);
        this.answerHintMid = inflate.findViewById(R.id.answer_hint_mid);
        this.answerHintLarge = inflate.findViewById(R.id.answer_hint_large);
        textView.setTextSize(0, this.context.getResources().getDimension(R.dimen.hint_text_size));
    }
}
