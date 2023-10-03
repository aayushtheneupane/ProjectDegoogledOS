package com.android.incallui.answer.impl;

import android.content.Context;
import android.content.res.Configuration;
import android.support.p002v7.appcompat.R$style;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import com.android.incallui.answer.impl.affordance.SwipeButtonHelper;
import com.android.incallui.answer.impl.affordance.SwipeButtonView;

public class AffordanceHolderLayout extends FrameLayout {
    /* access modifiers changed from: private */
    public SwipeButtonHelper.Callback affordanceCallback;
    private SwipeButtonHelper affordanceHelper;

    public AffordanceHolderLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public void animateHideLeftRightIcon() {
        this.affordanceHelper.animateHideLeftRightIcon();
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.affordanceHelper.onConfigurationChanged();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (R$style.isTouchExplorationEnabled(getContext())) {
            return false;
        }
        if (this.affordanceHelper.onTouchEvent(motionEvent) || super.onInterceptTouchEvent(motionEvent)) {
            return true;
        }
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.affordanceHelper.onTouchEvent(motionEvent) || super.onTouchEvent(motionEvent);
    }

    public void reset(boolean z) {
        this.affordanceHelper.reset(z);
    }

    public void setAffordanceCallback(SwipeButtonHelper.Callback callback) {
        this.affordanceCallback = callback;
        this.affordanceHelper.init();
    }

    public void startHintAnimation(boolean z, Runnable runnable) {
        this.affordanceHelper.startHintAnimation(z, runnable);
    }

    public AffordanceHolderLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.affordanceHelper = new SwipeButtonHelper(new SwipeButtonHelper.Callback() {
            public float getAffordanceFalsingFactor() {
                if (AffordanceHolderLayout.this.affordanceCallback != null) {
                    return AffordanceHolderLayout.this.affordanceCallback.getAffordanceFalsingFactor();
                }
                return 1.0f;
            }

            public SwipeButtonView getLeftIcon() {
                if (AffordanceHolderLayout.this.affordanceCallback != null) {
                    return AffordanceHolderLayout.this.affordanceCallback.getLeftIcon();
                }
                return null;
            }

            public View getLeftPreview() {
                if (AffordanceHolderLayout.this.affordanceCallback != null) {
                    return AffordanceHolderLayout.this.affordanceCallback.getLeftPreview();
                }
                return null;
            }

            public float getMaxTranslationDistance() {
                if (AffordanceHolderLayout.this.affordanceCallback != null) {
                    return AffordanceHolderLayout.this.affordanceCallback.getMaxTranslationDistance();
                }
                return 0.0f;
            }

            public SwipeButtonView getRightIcon() {
                if (AffordanceHolderLayout.this.affordanceCallback != null) {
                    return AffordanceHolderLayout.this.affordanceCallback.getRightIcon();
                }
                return null;
            }

            public View getRightPreview() {
                if (AffordanceHolderLayout.this.affordanceCallback == null) {
                    return null;
                }
                AffordanceHolderLayout.this.affordanceCallback.getRightPreview();
                return null;
            }

            public void onAnimationToSideEnded(boolean z) {
                if (AffordanceHolderLayout.this.affordanceCallback != null) {
                    AffordanceHolderLayout.this.affordanceCallback.onAnimationToSideEnded(z);
                }
            }

            public void onAnimationToSideStarted(boolean z, float f, float f2) {
                if (AffordanceHolderLayout.this.affordanceCallback != null) {
                    AffordanceHolderLayout.this.affordanceCallback.onAnimationToSideStarted(z, f, f2);
                }
            }

            public void onIconClicked(boolean z) {
                if (AffordanceHolderLayout.this.affordanceCallback != null) {
                    AffordanceHolderLayout.this.affordanceCallback.onIconClicked(z);
                }
            }

            public void onSwipingAborted() {
                if (AffordanceHolderLayout.this.affordanceCallback != null) {
                    AffordanceHolderLayout.this.affordanceCallback.onSwipingAborted();
                }
            }

            public void onSwipingStarted(boolean z) {
                if (AffordanceHolderLayout.this.affordanceCallback != null) {
                    AffordanceHolderLayout.this.affordanceCallback.onSwipingStarted(z);
                }
            }
        }, context);
    }

    public AffordanceHolderLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.affordanceHelper = new SwipeButtonHelper(new SwipeButtonHelper.Callback() {
            public float getAffordanceFalsingFactor() {
                if (AffordanceHolderLayout.this.affordanceCallback != null) {
                    return AffordanceHolderLayout.this.affordanceCallback.getAffordanceFalsingFactor();
                }
                return 1.0f;
            }

            public SwipeButtonView getLeftIcon() {
                if (AffordanceHolderLayout.this.affordanceCallback != null) {
                    return AffordanceHolderLayout.this.affordanceCallback.getLeftIcon();
                }
                return null;
            }

            public View getLeftPreview() {
                if (AffordanceHolderLayout.this.affordanceCallback != null) {
                    return AffordanceHolderLayout.this.affordanceCallback.getLeftPreview();
                }
                return null;
            }

            public float getMaxTranslationDistance() {
                if (AffordanceHolderLayout.this.affordanceCallback != null) {
                    return AffordanceHolderLayout.this.affordanceCallback.getMaxTranslationDistance();
                }
                return 0.0f;
            }

            public SwipeButtonView getRightIcon() {
                if (AffordanceHolderLayout.this.affordanceCallback != null) {
                    return AffordanceHolderLayout.this.affordanceCallback.getRightIcon();
                }
                return null;
            }

            public View getRightPreview() {
                if (AffordanceHolderLayout.this.affordanceCallback == null) {
                    return null;
                }
                AffordanceHolderLayout.this.affordanceCallback.getRightPreview();
                return null;
            }

            public void onAnimationToSideEnded(boolean z) {
                if (AffordanceHolderLayout.this.affordanceCallback != null) {
                    AffordanceHolderLayout.this.affordanceCallback.onAnimationToSideEnded(z);
                }
            }

            public void onAnimationToSideStarted(boolean z, float f, float f2) {
                if (AffordanceHolderLayout.this.affordanceCallback != null) {
                    AffordanceHolderLayout.this.affordanceCallback.onAnimationToSideStarted(z, f, f2);
                }
            }

            public void onIconClicked(boolean z) {
                if (AffordanceHolderLayout.this.affordanceCallback != null) {
                    AffordanceHolderLayout.this.affordanceCallback.onIconClicked(z);
                }
            }

            public void onSwipingAborted() {
                if (AffordanceHolderLayout.this.affordanceCallback != null) {
                    AffordanceHolderLayout.this.affordanceCallback.onSwipingAborted();
                }
            }

            public void onSwipingStarted(boolean z) {
                if (AffordanceHolderLayout.this.affordanceCallback != null) {
                    AffordanceHolderLayout.this.affordanceCallback.onSwipingStarted(z);
                }
            }
        }, context);
    }
}
