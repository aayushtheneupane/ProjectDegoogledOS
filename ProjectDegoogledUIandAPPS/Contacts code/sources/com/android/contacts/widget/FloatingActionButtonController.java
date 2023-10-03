package com.android.contacts.widget;

import android.app.Activity;
import android.content.res.Resources;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ImageButton;
import com.android.contacts.R;
import com.android.contacts.util.ViewUtil;
import com.android.phone.common.animation.AnimUtils;

public class FloatingActionButtonController {
    private final int mAnimationDuration;
    private final Interpolator mFabInterpolator;
    private final ImageButton mFloatingActionButton;
    private final View mFloatingActionButtonContainer;
    private final int mFloatingActionButtonMarginRight;
    private final int mFloatingActionButtonWidth;

    public FloatingActionButtonController(Activity activity, View view, ImageButton imageButton) {
        Resources resources = activity.getResources();
        this.mFabInterpolator = AnimationUtils.loadInterpolator(activity, 17563661);
        this.mFloatingActionButtonWidth = resources.getDimensionPixelSize(R.dimen.floating_action_button_width);
        this.mFloatingActionButtonMarginRight = resources.getDimensionPixelOffset(R.dimen.floating_action_button_margin_right);
        this.mAnimationDuration = resources.getInteger(R.integer.floating_action_button_animation_duration);
        this.mFloatingActionButtonContainer = view;
        this.mFloatingActionButton = imageButton;
        ViewUtil.setupFloatingActionButton(this.mFloatingActionButtonContainer, resources);
    }

    public void setVisible(boolean z) {
        this.mFloatingActionButtonContainer.setVisibility(z ? 0 : 8);
    }

    public void scaleIn(int i) {
        setVisible(true);
        AnimUtils.scaleIn(this.mFloatingActionButtonContainer, 186, i);
        AnimUtils.fadeIn(this.mFloatingActionButton, 186, i + 70, (AnimUtils.AnimationCallback) null);
    }

    public void resetIn() {
        this.mFloatingActionButton.setAlpha(1.0f);
        this.mFloatingActionButton.setVisibility(0);
        this.mFloatingActionButtonContainer.setScaleX(1.0f);
        this.mFloatingActionButtonContainer.setScaleY(1.0f);
    }

    public void scaleOut() {
        AnimUtils.scaleOut(this.mFloatingActionButtonContainer, this.mAnimationDuration);
        AnimUtils.fadeOut(this.mFloatingActionButton, 46, (AnimUtils.AnimationCallback) null);
    }
}
