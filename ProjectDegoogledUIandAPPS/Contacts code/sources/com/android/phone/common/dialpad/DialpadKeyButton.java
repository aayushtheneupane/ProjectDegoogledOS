package com.android.phone.common.dialpad;

import android.content.Context;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityManager;
import android.widget.FrameLayout;

public class DialpadKeyButton extends FrameLayout {
    private static final int LONG_HOVER_TIMEOUT = (ViewConfiguration.getLongPressTimeout() * 2);
    private AccessibilityManager mAccessibilityManager;
    private CharSequence mBackupContentDesc;
    private RectF mHoverBounds = new RectF();
    /* access modifiers changed from: private */
    public CharSequence mLongHoverContentDesc;
    private Runnable mLongHoverRunnable;
    private boolean mLongHovered;
    private OnPressedListener mOnPressedListener;
    private boolean mWasClickable;
    private boolean mWasLongClickable;

    public interface OnPressedListener {
        void onPressed(View view, boolean z);
    }

    public DialpadKeyButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initForAccessibility(context);
    }

    public DialpadKeyButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initForAccessibility(context);
    }

    private void initForAccessibility(Context context) {
        this.mAccessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
    }

    public void setLongHoverContentDescription(CharSequence charSequence) {
        this.mLongHoverContentDesc = charSequence;
        if (this.mLongHovered) {
            super.setContentDescription(this.mLongHoverContentDesc);
        }
    }

    public void setContentDescription(CharSequence charSequence) {
        if (this.mLongHovered) {
            this.mBackupContentDesc = charSequence;
        } else {
            super.setContentDescription(charSequence);
        }
    }

    public void setPressed(boolean z) {
        super.setPressed(z);
        OnPressedListener onPressedListener = this.mOnPressedListener;
        if (onPressedListener != null) {
            onPressedListener.onPressed(this, z);
        }
    }

    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.mHoverBounds.left = (float) getPaddingLeft();
        this.mHoverBounds.right = (float) (i - getPaddingRight());
        this.mHoverBounds.top = (float) getPaddingTop();
        this.mHoverBounds.bottom = (float) (i2 - getPaddingBottom());
    }

    public boolean performAccessibilityAction(int i, Bundle bundle) {
        if (i != 16) {
            return super.performAccessibilityAction(i, bundle);
        }
        simulateClickForAccessibility();
        return true;
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        if (this.mAccessibilityManager.isEnabled() && this.mAccessibilityManager.isTouchExplorationEnabled()) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 9) {
                this.mWasClickable = isClickable();
                this.mWasLongClickable = isLongClickable();
                if (this.mWasLongClickable && this.mLongHoverContentDesc != null) {
                    if (this.mLongHoverRunnable == null) {
                        this.mLongHoverRunnable = new Runnable() {
                            public void run() {
                                DialpadKeyButton.this.setLongHovered(true);
                                DialpadKeyButton dialpadKeyButton = DialpadKeyButton.this;
                                dialpadKeyButton.announceForAccessibility(dialpadKeyButton.mLongHoverContentDesc);
                            }
                        };
                    }
                    postDelayed(this.mLongHoverRunnable, (long) LONG_HOVER_TIMEOUT);
                }
                setClickable(false);
                setLongClickable(false);
            } else if (actionMasked == 10) {
                if (this.mHoverBounds.contains(motionEvent.getX(), motionEvent.getY())) {
                    if (this.mLongHovered) {
                        simulateClickForAccessibility();
                        performLongClick();
                    } else {
                        simulateClickForAccessibility();
                    }
                }
                cancelLongHover();
                setClickable(this.mWasClickable);
                setLongClickable(this.mWasLongClickable);
            }
        }
        return super.onHoverEvent(motionEvent);
    }

    private void simulateClickForAccessibility() {
        if (!isPressed()) {
            setPressed(true);
            sendAccessibilityEvent(1);
            setPressed(false);
        }
    }

    /* access modifiers changed from: private */
    public void setLongHovered(boolean z) {
        if (this.mLongHovered != z) {
            this.mLongHovered = z;
            if (z) {
                this.mBackupContentDesc = getContentDescription();
                super.setContentDescription(this.mLongHoverContentDesc);
                return;
            }
            super.setContentDescription(this.mBackupContentDesc);
        }
    }

    private void cancelLongHover() {
        Runnable runnable = this.mLongHoverRunnable;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
        setLongHovered(false);
    }
}
