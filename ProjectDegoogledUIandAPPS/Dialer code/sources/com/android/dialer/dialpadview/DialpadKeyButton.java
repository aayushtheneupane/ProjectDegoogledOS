package com.android.dialer.dialpadview;

import android.content.Context;
import android.graphics.RectF;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;

public class DialpadKeyButton extends FrameLayout {
    private AccessibilityManager accessibilityManager;
    private RectF hoverBounds = new RectF();
    private CharSequence longHoverContentDesc;
    private OnPressedListener onPressedListener;
    private boolean wasClickable;
    private boolean wasLongClickable;

    public interface OnPressedListener {
        void onPressed(View view, boolean z);
    }

    public DialpadKeyButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        if (this.accessibilityManager.isEnabled() && this.accessibilityManager.isTouchExplorationEnabled()) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 9) {
                this.wasClickable = isClickable();
                this.wasLongClickable = isLongClickable();
                setClickable(false);
                setLongClickable(false);
            } else if (actionMasked == 10) {
                if (this.hoverBounds.contains(motionEvent.getX(), motionEvent.getY()) && !isPressed()) {
                    setPressed(true);
                    sendAccessibilityEvent(1);
                    setPressed(false);
                }
                setClickable(this.wasClickable);
                setLongClickable(this.wasLongClickable);
            }
        }
        return super.onHoverEvent(motionEvent);
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (!TextUtils.isEmpty(this.longHoverContentDesc)) {
            accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(32, this.longHoverContentDesc));
        }
    }

    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.hoverBounds.left = (float) getPaddingLeft();
        this.hoverBounds.right = (float) (i - getPaddingRight());
        this.hoverBounds.top = (float) getPaddingTop();
        this.hoverBounds.bottom = (float) (i2 - getPaddingBottom());
    }

    public boolean performAccessibilityAction(int i, Bundle bundle) {
        if (i != 16) {
            return super.performAccessibilityAction(i, bundle);
        }
        if (!isPressed()) {
            setPressed(true);
            sendAccessibilityEvent(1);
            setPressed(false);
        }
        return true;
    }

    public void setLongHoverContentDescription(CharSequence charSequence) {
        this.longHoverContentDesc = charSequence;
    }

    public void setOnPressedListener(OnPressedListener onPressedListener2) {
        this.onPressedListener = onPressedListener2;
    }

    public void setPressed(boolean z) {
        super.setPressed(z);
        OnPressedListener onPressedListener2 = this.onPressedListener;
        if (onPressedListener2 != null) {
            onPressedListener2.onPressed(this, z);
        }
    }

    public DialpadKeyButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
    }
}
