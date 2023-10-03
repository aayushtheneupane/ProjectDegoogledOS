package com.android.incallui.answer.impl.answermethod;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.p002v7.appcompat.R$style;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.incallui.answer.impl.AnswerFragment;
import com.android.incallui.answer.impl.answermethod.FlingUpDownTouchHandler;
import com.android.incallui.answer.impl.classifier.FalsingManager;
import com.android.tools.p006r8.GeneratedOutlineSupport;

public class TwoButtonMethod extends AnswerMethod implements View.OnClickListener, ValueAnimator.AnimatorUpdateListener {
    private View answerButton;
    private View answerLabel;
    private boolean buttonClicked;
    private View declineButton;
    private View declineLabel;
    private CharSequence hintText;
    private TextView hintTextView;
    private boolean incomingWillDisconnect;
    private FlingUpDownTouchHandler touchHandler;

    /* access modifiers changed from: private */
    public void answerCall() {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ofFloat.addUpdateListener(this);
        ofFloat.addListener(new AnimatorListenerAdapter() {
            private boolean canceled;

            public void onAnimationCancel(Animator animator) {
                this.canceled = true;
            }

            public void onAnimationEnd(Animator animator) {
                if (!this.canceled) {
                    ((AnswerFragment) TwoButtonMethod.this.getParent()).answerFromMethod();
                }
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofFloat).with(createViewHideAnimation());
        animatorSet.start();
    }

    private Animator createViewHideAnimation() {
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this.answerButton, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat(View.SCALE_X, new float[]{0.0f}), PropertyValuesHolder.ofFloat(View.SCALE_Y, new float[]{0.0f})});
        ObjectAnimator ofPropertyValuesHolder2 = ObjectAnimator.ofPropertyValuesHolder(this.declineButton, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat(View.SCALE_X, new float[]{0.0f}), PropertyValuesHolder.ofFloat(View.SCALE_Y, new float[]{0.0f})});
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.answerLabel, View.ALPHA, new float[]{0.0f});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.declineLabel, View.ALPHA, new float[]{0.0f});
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.hintTextView, View.ALPHA, new float[]{0.0f});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofPropertyValuesHolder).with(ofPropertyValuesHolder2).with(ofFloat).with(ofFloat2).with(ofFloat3);
        return animatorSet;
    }

    /* access modifiers changed from: private */
    public void rejectCall() {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, -1.0f});
        ofFloat.addUpdateListener(this);
        ofFloat.addListener(new AnimatorListenerAdapter() {
            private boolean canceled;

            public void onAnimationCancel(Animator animator) {
                this.canceled = true;
            }

            public void onAnimationEnd(Animator animator) {
                if (!this.canceled) {
                    ((AnswerFragment) TwoButtonMethod.this.getParent()).rejectFromMethod();
                }
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofFloat).with(createViewHideAnimation());
        animatorSet.start();
    }

    private void updateHintText() {
        TextView textView = this.hintTextView;
        if (textView != null) {
            textView.setVisibility(getActivity().isInMultiWindowMode() ? 8 : 0);
            if (!TextUtils.isEmpty(this.hintText) && !this.buttonClicked) {
                this.hintTextView.setText(this.hintText);
                this.hintTextView.animate().alpha(1.0f).start();
            } else if (!this.incomingWillDisconnect || this.buttonClicked) {
                this.hintTextView.animate().alpha(0.0f).start();
            } else {
                this.hintTextView.setText(R.string.call_incoming_will_disconnect);
                this.hintTextView.animate().alpha(1.0f).start();
            }
        }
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        ((AnswerFragment) getParent()).onAnswerProgressUpdate(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }

    public void onClick(View view) {
        if (view == this.answerButton) {
            answerCall();
        } else if (view == this.declineButton) {
            rejectCall();
        } else {
            throw new AssertionError(GeneratedOutlineSupport.outline6("Unknown click from view: ", view));
        }
        this.buttonClicked = true;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.incomingWillDisconnect = bundle.getBoolean("incomingWillDisconnect");
            this.hintText = bundle.getCharSequence("hintText");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.two_button_method, viewGroup, false);
        this.hintTextView = (TextView) inflate.findViewById(R.id.two_button_hint_text);
        updateHintText();
        this.answerButton = inflate.findViewById(R.id.two_button_answer_button);
        this.answerLabel = inflate.findViewById(R.id.two_button_answer_label);
        this.declineButton = inflate.findViewById(R.id.two_button_decline_button);
        this.declineLabel = inflate.findViewById(R.id.two_button_decline_label);
        boolean z = getResources().getBoolean(R.bool.two_button_show_button_labels);
        int i = 8;
        this.answerLabel.setVisibility(z ? 0 : 8);
        View view = this.declineLabel;
        if (z) {
            i = 0;
        }
        view.setVisibility(i);
        this.answerButton.setOnClickListener(this);
        this.declineButton.setOnClickListener(this);
        if (R$style.isTouchExplorationEnabled(getContext())) {
            this.touchHandler = FlingUpDownTouchHandler.attach(inflate, new FlingUpDownTouchHandler.OnProgressChangedListener() {
                public void onMoveFinish(boolean z) {
                    if (z) {
                        TwoButtonMethod.this.answerCall();
                    } else {
                        TwoButtonMethod.this.rejectCall();
                    }
                }

                public void onMoveReset(boolean z) {
                }

                public void onProgressChanged(float f) {
                }

                public void onTrackingStart() {
                }

                public void onTrackingStopped() {
                }

                public boolean shouldUseFalsing(MotionEvent motionEvent) {
                    return false;
                }
            }, (FalsingManager) null);
            this.touchHandler.setFlingEnabled(false);
        }
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

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("incomingWillDisconnect", this.incomingWillDisconnect);
        bundle.putCharSequence("hintText", this.hintText);
    }

    public void setHintText(CharSequence charSequence) {
        this.hintText = charSequence;
        updateHintText();
    }

    public void setShowIncomingWillDisconnect(boolean z) {
        this.incomingWillDisconnect = z;
        updateHintText();
    }
}
