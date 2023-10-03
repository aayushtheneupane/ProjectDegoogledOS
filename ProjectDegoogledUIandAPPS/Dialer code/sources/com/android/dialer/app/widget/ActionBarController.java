package com.android.dialer.app.widget;

import android.animation.ValueAnimator;
import android.os.Bundle;
import com.android.dialer.animation.AnimUtils;
import com.android.tools.p006r8.GeneratedOutlineSupport;

public class ActionBarController {
    private ActivityUi activityUi;
    private ValueAnimator animator;
    private final AnimUtils.AnimationCallback fadeOutCallback = new AnimUtils.AnimationCallback() {
        public void onAnimationCancel() {
            ActionBarController.this.slideActionBar(true, false);
        }

        public void onAnimationEnd() {
            ActionBarController.this.slideActionBar(true, false);
        }
    };
    private boolean isActionBarSlidUp;
    private SearchEditTextLayout searchBox;

    public interface ActivityUi {
        int getActionBarHeight();

        boolean hasSearchQuery();

        boolean isInSearchUi();

        void setActionBarHideOffset(int i);
    }

    public ActionBarController(ActivityUi activityUi2, SearchEditTextLayout searchEditTextLayout) {
        this.activityUi = activityUi2;
        this.searchBox = searchEditTextLayout;
    }

    public /* synthetic */ void lambda$slideActionBar$0$ActionBarController(ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        this.activityUi.setActionBarHideOffset((int) (((float) this.activityUi.getActionBarHeight()) * floatValue));
    }

    public void onDialpadDown() {
        Object[] objArr = {Boolean.valueOf(this.activityUi.isInSearchUi()), Boolean.valueOf(this.activityUi.hasSearchQuery()), Boolean.valueOf(this.searchBox.isFadedOut()), Boolean.valueOf(this.searchBox.isExpanded())};
        if (this.activityUi.isInSearchUi()) {
            if (this.searchBox.isFadedOut()) {
                this.searchBox.setVisible(true);
            }
            if (!this.searchBox.isExpanded()) {
                this.searchBox.expand(false, false);
            }
            slideActionBar(false, true);
        }
    }

    public void onDialpadUp() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("isInSearchUi ");
        outline13.append(this.activityUi.isInSearchUi());
        outline13.toString();
        if (this.activityUi.isInSearchUi()) {
            slideActionBar(true, true);
        } else {
            this.searchBox.fadeOut(this.fadeOutCallback);
        }
    }

    public void onSearchBoxTapped() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("isInSearchUi ");
        outline13.append(this.activityUi.isInSearchUi());
        outline13.toString();
        if (!this.activityUi.isInSearchUi()) {
            this.searchBox.expand(true, true);
        }
    }

    public void onSearchUiExited() {
        Object[] objArr = {Boolean.valueOf(this.searchBox.isExpanded()), Boolean.valueOf(this.searchBox.isFadedOut())};
        if (this.searchBox.isExpanded()) {
            this.searchBox.collapse(true);
        }
        if (this.searchBox.isFadedOut()) {
            this.searchBox.fadeIn();
        }
        slideActionBar(false, false);
    }

    public void restoreActionBarOffset() {
        slideActionBar(this.isActionBarSlidUp, false);
    }

    public void restoreInstanceState(Bundle bundle) {
        this.isActionBarSlidUp = bundle.getBoolean("key_actionbar_is_slid_up");
        if (bundle.getBoolean("key_actionbar_is_faded_out")) {
            if (!this.searchBox.isFadedOut()) {
                this.searchBox.setVisible(false);
            }
        } else if (this.searchBox.isFadedOut()) {
            this.searchBox.setVisible(true);
        }
        if (bundle.getBoolean("key_actionbar_is_expanded")) {
            if (!this.searchBox.isExpanded()) {
                this.searchBox.expand(false, false);
            }
        } else if (this.searchBox.isExpanded()) {
            this.searchBox.collapse(false);
        }
    }

    public void saveInstanceState(Bundle bundle) {
        bundle.putBoolean("key_actionbar_is_slid_up", this.isActionBarSlidUp);
        bundle.putBoolean("key_actionbar_is_faded_out", this.searchBox.isFadedOut());
        bundle.putBoolean("key_actionbar_is_expanded", this.searchBox.isExpanded());
    }

    public void slideActionBar(boolean z, boolean z2) {
        int i = 0;
        Object[] objArr = {Boolean.valueOf(z), Boolean.valueOf(z2)};
        ValueAnimator valueAnimator = this.animator;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.animator.cancel();
            this.animator.removeAllUpdateListeners();
        }
        if (z2) {
            float[] fArr = new float[2];
            if (z) {
                // fill-array-data instruction
                fArr[0] = 0;
                fArr[1] = 1065353216;
            } else {
                // fill-array-data instruction
                fArr[0] = 1065353216;
                fArr[1] = 0;
            }
            this.animator = ValueAnimator.ofFloat(fArr);
            this.animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    ActionBarController.this.lambda$slideActionBar$0$ActionBarController(valueAnimator);
                }
            });
            this.animator.start();
        } else {
            if (z) {
                i = this.activityUi.getActionBarHeight();
            }
            this.activityUi.setActionBarHideOffset(i);
        }
        this.isActionBarSlidUp = z;
    }
}
