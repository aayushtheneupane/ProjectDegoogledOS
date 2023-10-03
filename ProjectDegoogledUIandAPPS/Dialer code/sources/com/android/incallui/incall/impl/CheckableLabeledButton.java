package com.android.incallui.incall.impl;

import android.animation.AnimatorInflater;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.dialer.R;

public class CheckableLabeledButton extends LinearLayout implements Checkable {
    private static final int[] CHECKED_STATE_SET = {16842912};
    private Drawable background;
    private Drawable backgroundMore;
    private boolean broadcasting;
    private int iconResource = 0;
    private ImageView iconView;
    private boolean isChecked;
    private TextView labelView;
    private OnCheckedChangeListener onCheckedChangeListener;

    public interface OnCheckedChangeListener {
        void onCheckedChanged(CheckableLabeledButton checkableLabeledButton, boolean z);
    }

    public CheckableLabeledButton(Context context) {
        super(context, (AttributeSet) null);
        init(context, (AttributeSet) null);
    }

    private void init(Context context, AttributeSet attributeSet) {
        setOrientation(1);
        setGravity(1);
        this.backgroundMore = getResources().getDrawable(R.drawable.incall_button_background_more, context.getTheme());
        this.background = getResources().getDrawable(R.drawable.incall_button_background, context.getTheme());
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CheckableLabeledButton);
        Drawable drawable = obtainStyledAttributes.getDrawable(1);
        String string = obtainStyledAttributes.getString(2);
        boolean z = obtainStyledAttributes.getBoolean(0, true);
        obtainStyledAttributes.recycle();
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.incall_button_padding);
        setPadding(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.incall_labeled_button_size);
        int dimensionPixelSize2 = (dimensionPixelSize - getResources().getDimensionPixelSize(R.dimen.incall_labeled_button_icon_size)) / 2;
        this.iconView = new ImageView(context, (AttributeSet) null, 16974547);
        LinearLayout.LayoutParams generateDefaultLayoutParams = generateDefaultLayoutParams();
        generateDefaultLayoutParams.width = dimensionPixelSize;
        generateDefaultLayoutParams.height = dimensionPixelSize;
        this.iconView.setLayoutParams(generateDefaultLayoutParams);
        this.iconView.setPadding(dimensionPixelSize2, dimensionPixelSize2, dimensionPixelSize2, dimensionPixelSize2);
        this.iconView.setImageDrawable(drawable);
        this.iconView.setImageTintMode(PorterDuff.Mode.SRC_IN);
        this.iconView.setImageTintList(getResources().getColorStateList(R.color.incall_button_icon, context.getTheme()));
        this.iconView.setBackground(getResources().getDrawable(R.drawable.incall_button_background, context.getTheme()));
        this.iconView.setDuplicateParentStateEnabled(true);
        this.iconView.setElevation(getResources().getDimension(R.dimen.incall_button_elevation));
        this.iconView.setStateListAnimator(AnimatorInflater.loadStateListAnimator(context, R.animator.incall_button_elevation));
        addView(this.iconView);
        this.labelView = new TextView(context);
        LinearLayout.LayoutParams generateDefaultLayoutParams2 = generateDefaultLayoutParams();
        generateDefaultLayoutParams2.width = -2;
        generateDefaultLayoutParams2.height = -2;
        generateDefaultLayoutParams2.topMargin = context.getResources().getDimensionPixelOffset(R.dimen.incall_button_label_margin);
        this.labelView.setLayoutParams(generateDefaultLayoutParams2);
        this.labelView.setTextAppearance(2131886307);
        this.labelView.setText(string);
        this.labelView.setSingleLine();
        this.labelView.setMaxEms(9);
        this.labelView.setEllipsize(TextUtils.TruncateAt.END);
        this.labelView.setGravity(17);
        this.labelView.setDuplicateParentStateEnabled(true);
        addView(this.labelView);
        setFocusable(true);
        setClickable(true);
        setEnabled(z);
        setOutlineProvider((ViewOutlineProvider) null);
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }

    public Drawable getIconDrawable() {
        return this.iconView.getDrawable();
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (isChecked()) {
            LinearLayout.mergeDrawableStates(onCreateDrawableState, CHECKED_STATE_SET);
        }
        return onCreateDrawableState;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        boolean z = savedState.isChecked;
        if (isChecked() != z) {
            this.isChecked = z;
            refreshDrawableState();
        }
        requestLayout();
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        return new SavedState(isChecked(), super.onSaveInstanceState(), (C07141) null);
    }

    public boolean performClick() {
        if (!(this.onCheckedChangeListener != null)) {
            return super.performClick();
        }
        toggle();
        boolean performClick = super.performClick();
        if (!performClick) {
            playSoundEffect(0);
        }
        return performClick;
    }

    public void refreshDrawableState() {
        super.refreshDrawableState();
        float f = 1.0f;
        this.iconView.setAlpha(isEnabled() ? 1.0f : 0.3f);
        TextView textView = this.labelView;
        if (!isEnabled()) {
            f = 0.3f;
        }
        textView.setAlpha(f);
    }

    public void setChecked(boolean z) {
        if (isChecked() != z) {
            this.isChecked = z;
            refreshDrawableState();
        }
    }

    public void setCheckedColor(int i) {
        this.iconView.setImageTintList(new ColorStateList(new int[][]{new int[]{16842912}, new int[0]}, new int[]{i, -1}));
    }

    public void setIconDrawable(int i) {
        if (this.iconResource != i) {
            this.iconView.setImageResource(i);
            this.iconResource = i;
        }
    }

    public void setLabelText(int i) {
        this.labelView.setText(i);
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener2) {
        this.onCheckedChangeListener = onCheckedChangeListener2;
    }

    public void setShouldShowMoreIndicator(boolean z) {
        this.iconView.setBackground(z ? this.backgroundMore : this.background);
    }

    public void toggle() {
        boolean z = !isChecked();
        if (isChecked() != z && !this.broadcasting) {
            this.broadcasting = true;
            OnCheckedChangeListener onCheckedChangeListener2 = this.onCheckedChangeListener;
            if (onCheckedChangeListener2 != null) {
                onCheckedChangeListener2.onCheckedChanged(this, z);
            }
            this.broadcasting = false;
        }
    }

    private static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            public Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public Object[] newArray(int i) {
                return new SavedState[i];
            }
        };
        public final boolean isChecked;

        /* synthetic */ SavedState(boolean z, Parcelable parcelable, C07141 r3) {
            super(parcelable);
            this.isChecked = z;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeByte(this.isChecked ? (byte) 1 : 0);
        }

        protected SavedState(Parcel parcel) {
            super(parcel);
            this.isChecked = parcel.readByte() != 0;
        }
    }

    public void setLabelText(CharSequence charSequence) {
        this.labelView.setText(charSequence);
    }

    public CheckableLabeledButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }
}
