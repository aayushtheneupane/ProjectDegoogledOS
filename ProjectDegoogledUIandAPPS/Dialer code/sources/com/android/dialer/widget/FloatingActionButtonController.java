package com.android.dialer.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.support.design.widget.FloatingActionButton;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import com.android.dialer.R;
import com.android.tools.p006r8.GeneratedOutlineSupport;

public class FloatingActionButtonController {
    private final int animationDuration;
    private final FloatingActionButton fab;
    private int fabIconId = -1;
    private final Interpolator fabInterpolator;
    private final int floatingActionButtonMarginRight;
    private final int floatingActionButtonWidth;
    private int screenWidth;

    public FloatingActionButtonController(Activity activity, FloatingActionButton floatingActionButton) {
        Resources resources = activity.getResources();
        this.fabInterpolator = AnimationUtils.loadInterpolator(activity, 17563661);
        this.floatingActionButtonWidth = resources.getDimensionPixelSize(R.dimen.floating_action_button_width);
        this.floatingActionButtonMarginRight = resources.getDimensionPixelOffset(R.dimen.floating_action_button_margin_right);
        this.animationDuration = resources.getInteger(R.integer.floating_action_button_animation_duration);
        this.fab = floatingActionButton;
    }

    private int getTranslationXForAlignment(int i) {
        int i2;
        boolean z = false;
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            i2 = this.screenWidth / 4;
        } else if (i == 2) {
            i2 = ((this.screenWidth / 2) - (this.floatingActionButtonWidth / 2)) - this.floatingActionButtonMarginRight;
        } else {
            throw new IllegalStateException(GeneratedOutlineSupport.outline5("Invalid alignment value: ", i));
        }
        if (this.fab.getLayoutDirection() == 1) {
            z = true;
        }
        return z ? i2 * -1 : i2;
    }

    public void align(int i, boolean z) {
        if (this.screenWidth != 0) {
            int translationXForAlignment = getTranslationXForAlignment(i);
            if (!z || !this.fab.isShown()) {
                this.fab.setTranslationX((float) (translationXForAlignment + 0));
                this.fab.setTranslationY((float) 0);
                return;
            }
            this.fab.animate().translationX((float) (translationXForAlignment + 0)).translationY((float) 0).setInterpolator(this.fabInterpolator).setDuration((long) this.animationDuration).start();
        }
    }

    public void changeIcon(Context context, int i, String str) {
        if (this.fabIconId != i) {
            this.fab.setImageResource(i);
            this.fab.setImageTintList(ColorStateList.valueOf(context.getResources().getColor(17170443)));
            this.fabIconId = i;
        }
        if (!this.fab.getContentDescription().equals(str)) {
            this.fab.setContentDescription(str);
        }
    }

    public boolean isVisible() {
        return this.fab.isShown();
    }

    public void onPageScrolled(float f) {
        this.fab.setTranslationX(f * ((float) getTranslationXForAlignment(2)));
    }

    public void scaleIn() {
        this.fab.show();
    }

    public void scaleOut() {
        this.fab.hide();
    }

    public void setScreenWidth(int i) {
        this.screenWidth = i;
    }

    public void setVisible(boolean z) {
        if (z) {
            this.fab.show();
        } else {
            this.fab.hide();
        }
    }

    public void scaleOut(FloatingActionButton.OnVisibilityChangedListener onVisibilityChangedListener) {
        this.fab.hide(onVisibilityChangedListener);
    }
}
