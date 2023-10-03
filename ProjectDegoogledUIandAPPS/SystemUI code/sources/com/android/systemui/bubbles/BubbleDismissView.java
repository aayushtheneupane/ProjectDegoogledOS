package com.android.systemui.bubbles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1779R$layout;

public class BubbleDismissView extends FrameLayout {
    private View mDismissCircle = findViewById(C1777R$id.bubble_dismiss_circle);
    private ImageView mDismissIcon = ((ImageView) findViewById(C1777R$id.bubble_dismiss_close_icon));
    private LinearLayout mDismissTarget = ((LinearLayout) findViewById(C1777R$id.bubble_dismiss_icon_container));
    private SpringAnimation mDismissTargetAlphaSpring;
    private SpringAnimation mDismissTargetVerticalSpring;

    public BubbleDismissView(Context context) {
        super(context);
        setVisibility(8);
        LayoutInflater.from(context).inflate(C1779R$layout.bubble_dismiss_target, this, true);
        AccelerateDecelerateInterpolator accelerateDecelerateInterpolator = new AccelerateDecelerateInterpolator();
        this.mDismissIcon.animate().setDuration(150).setInterpolator(accelerateDecelerateInterpolator);
        this.mDismissCircle.animate().setDuration(75).setInterpolator(accelerateDecelerateInterpolator);
        SpringAnimation springAnimation = new SpringAnimation(this.mDismissTarget, DynamicAnimation.ALPHA);
        SpringForce springForce = new SpringForce();
        springForce.setStiffness(200.0f);
        springForce.setDampingRatio(0.75f);
        springAnimation.setSpring(springForce);
        this.mDismissTargetAlphaSpring = springAnimation;
        SpringAnimation springAnimation2 = new SpringAnimation(this.mDismissTarget, DynamicAnimation.TRANSLATION_Y);
        SpringForce springForce2 = new SpringForce();
        springForce2.setStiffness(1500.0f);
        springForce2.setDampingRatio(0.75f);
        springAnimation2.setSpring(springForce2);
        this.mDismissTargetVerticalSpring = springAnimation2;
        this.mDismissTargetAlphaSpring.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
            public final void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z, float f, float f2) {
                BubbleDismissView.this.lambda$new$0$BubbleDismissView(dynamicAnimation, z, f, f2);
            }
        });
    }

    public /* synthetic */ void lambda$new$0$BubbleDismissView(DynamicAnimation dynamicAnimation, boolean z, float f, float f2) {
        if (f < 0.5f) {
            setVisibility(4);
        }
    }

    /* access modifiers changed from: package-private */
    public void springIn() {
        setVisibility(0);
        this.mDismissIcon.animate().setDuration(50).scaleX(1.0f).scaleY(1.0f).alpha(1.0f);
        this.mDismissTarget.setAlpha(0.0f);
        this.mDismissTargetAlphaSpring.animateToFinalPosition(1.0f);
        LinearLayout linearLayout = this.mDismissTarget;
        linearLayout.setTranslationY(((float) linearLayout.getHeight()) / 2.0f);
        this.mDismissTargetVerticalSpring.animateToFinalPosition(0.0f);
        this.mDismissCircle.setAlpha(0.0f);
        this.mDismissCircle.setScaleX(1.2f);
        this.mDismissCircle.setScaleY(1.2f);
        this.mDismissCircle.animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f);
    }

    /* access modifiers changed from: package-private */
    public void springOut() {
        this.mDismissIcon.animate().setDuration(50).scaleX(0.9f).scaleY(0.9f).alpha(0.0f);
        this.mDismissTargetAlphaSpring.animateToFinalPosition(0.0f);
        this.mDismissTargetVerticalSpring.animateToFinalPosition(((float) this.mDismissTarget.getHeight()) / 2.0f);
        this.mDismissCircle.animate().scaleX(0.9f).scaleY(0.9f).alpha(0.0f);
    }

    /* access modifiers changed from: package-private */
    public float getDismissTargetCenterY() {
        return ((float) (getTop() + this.mDismissTarget.getTop())) + (((float) this.mDismissTarget.getHeight()) / 2.0f);
    }

    /* access modifiers changed from: package-private */
    public View getDismissTarget() {
        return this.mDismissTarget;
    }
}
