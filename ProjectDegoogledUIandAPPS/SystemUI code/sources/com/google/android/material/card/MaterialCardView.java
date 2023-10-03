package com.google.android.material.card;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Checkable;
import android.widget.FrameLayout;
import androidx.cardview.widget.CardView;
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.internal.ThemeEnforcement;

public class MaterialCardView extends CardView implements Checkable {
    private static final int[] CHECKABLE_STATE_SET = {16842911};
    private static final int[] CHECKED_STATE_SET = {16842912};
    private static final int DEF_STYLE_RES = R$style.Widget_MaterialComponents_CardView;
    private static final int[] DRAGGED_STATE_SET = {R$attr.state_dragged};
    private final MaterialCardViewHelper cardViewHelper;
    private boolean checked;
    private final FrameLayout contentLayout;
    private boolean dragged;
    private final boolean isParentCardViewDoneInitializing;
    private OnCheckedChangeListener onCheckedChangeListener;

    public interface OnCheckedChangeListener {
        void onCheckedChanged(MaterialCardView materialCardView, boolean z);
    }

    public MaterialCardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.materialCardViewStyle);
    }

    public MaterialCardView(Context context, AttributeSet attributeSet, int i) {
        super(ThemeEnforcement.createThemedContext(context, attributeSet, i, DEF_STYLE_RES), attributeSet, i);
        this.checked = false;
        this.dragged = false;
        this.isParentCardViewDoneInitializing = true;
        Context context2 = getContext();
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context2, attributeSet, R$styleable.MaterialCardView, i, DEF_STYLE_RES, new int[0]);
        this.contentLayout = new FrameLayout(context2);
        super.addView(this.contentLayout, -1, new FrameLayout.LayoutParams(-1, -1));
        this.cardViewHelper = new MaterialCardViewHelper(this, attributeSet, i, DEF_STYLE_RES);
        this.cardViewHelper.setCardBackgroundColor(super.getCardBackgroundColor());
        this.cardViewHelper.setUserContentPadding(super.getContentPaddingLeft(), super.getContentPaddingTop(), super.getContentPaddingRight(), super.getContentPaddingBottom());
        super.setContentPadding(0, 0, 0, 0);
        this.cardViewHelper.loadFromAttributes(obtainStyledAttributes);
        updateContentLayout();
        obtainStyledAttributes.recycle();
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(MaterialCardView.class.getName());
        accessibilityNodeInfo.setCheckable(isCheckable());
        accessibilityNodeInfo.setLongClickable(isCheckable());
        accessibilityNodeInfo.setClickable(isClickable());
    }

    private void updateContentLayout() {
        if (Build.VERSION.SDK_INT >= 21) {
            this.cardViewHelper.createOutlineProvider(this.contentLayout);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.cardViewHelper.onMeasure(getMeasuredWidth(), getMeasuredHeight());
    }

    /* access modifiers changed from: package-private */
    public float getCardViewRadius() {
        return super.getRadius();
    }

    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        super.setLayoutParams(layoutParams);
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.contentLayout.getLayoutParams();
        if (layoutParams instanceof FrameLayout.LayoutParams) {
            layoutParams2.gravity = ((FrameLayout.LayoutParams) layoutParams).gravity;
            this.contentLayout.requestLayout();
        }
    }

    public void setClickable(boolean z) {
        super.setClickable(z);
        this.cardViewHelper.updateClickable();
    }

    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        this.contentLayout.addView(view, i, layoutParams);
    }

    public void removeAllViews() {
        this.contentLayout.removeAllViews();
    }

    public void removeView(View view) {
        this.contentLayout.removeView(view);
    }

    public void removeViewInLayout(View view) {
        this.contentLayout.removeViewInLayout(view);
    }

    public void removeViewsInLayout(int i, int i2) {
        this.contentLayout.removeViewsInLayout(i, i2);
    }

    public void removeViewAt(int i) {
        this.contentLayout.removeViewAt(i);
    }

    public void removeViews(int i, int i2) {
        this.contentLayout.removeViews(i, i2);
    }

    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    public void setBackgroundDrawable(Drawable drawable) {
        if (this.isParentCardViewDoneInitializing) {
            if (!this.cardViewHelper.isBackgroundOverwritten()) {
                Log.i("MaterialCardView", "Setting a custom background is not supported.");
                this.cardViewHelper.setBackgroundOverwritten(true);
            }
            super.setBackgroundDrawable(drawable);
        }
    }

    /* access modifiers changed from: package-private */
    public void setBackgroundInternal(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    /* access modifiers changed from: package-private */
    public void setContentLayoutPadding(int i, int i2, int i3, int i4) {
        this.contentLayout.setPadding(i, i2, i3, i4);
    }

    public boolean isChecked() {
        return this.checked;
    }

    public void setChecked(boolean z) {
        if (this.checked != z) {
            toggle();
        }
    }

    public boolean isDragged() {
        return this.dragged;
    }

    public boolean isCheckable() {
        MaterialCardViewHelper materialCardViewHelper = this.cardViewHelper;
        return materialCardViewHelper != null && materialCardViewHelper.isCheckable();
    }

    public void toggle() {
        if (isCheckable() && isEnabled()) {
            this.checked = !this.checked;
            refreshDrawableState();
            if (Build.VERSION.SDK_INT > 26) {
                this.cardViewHelper.forceRippleRedraw();
            }
            OnCheckedChangeListener onCheckedChangeListener2 = this.onCheckedChangeListener;
            if (onCheckedChangeListener2 != null) {
                onCheckedChangeListener2.onCheckedChanged(this, this.checked);
            }
        }
    }

    /* access modifiers changed from: protected */
    public int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 3);
        if (isCheckable()) {
            FrameLayout.mergeDrawableStates(onCreateDrawableState, CHECKABLE_STATE_SET);
        }
        if (isChecked()) {
            FrameLayout.mergeDrawableStates(onCreateDrawableState, CHECKED_STATE_SET);
        }
        if (isDragged()) {
            FrameLayout.mergeDrawableStates(onCreateDrawableState, DRAGGED_STATE_SET);
        }
        return onCreateDrawableState;
    }
}
