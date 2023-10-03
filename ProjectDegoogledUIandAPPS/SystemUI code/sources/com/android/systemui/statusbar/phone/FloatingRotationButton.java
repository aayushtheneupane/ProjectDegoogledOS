package com.android.systemui.statusbar.phone;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.android.settingslib.Utils;
import com.android.systemui.C1772R$attr;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1779R$layout;
import com.android.systemui.statusbar.policy.KeyButtonDrawable;
import com.android.systemui.statusbar.policy.KeyButtonView;

public class FloatingRotationButton implements RotationButton {
    private boolean mCanShow = true;
    private final Context mContext;
    private final int mDiameter;
    private boolean mIsShowing;
    private KeyButtonDrawable mKeyButtonDrawable;
    private final KeyButtonView mKeyButtonView;
    private final int mMargin;
    private RotationButtonController mRotationButtonController;
    private final WindowManager mWindowManager;

    FloatingRotationButton(Context context) {
        this.mContext = context;
        this.mWindowManager = (WindowManager) this.mContext.getSystemService("window");
        this.mKeyButtonView = (KeyButtonView) LayoutInflater.from(this.mContext).inflate(C1779R$layout.rotate_suggestion, (ViewGroup) null);
        this.mKeyButtonView.setVisibility(0);
        Resources resources = this.mContext.getResources();
        this.mDiameter = resources.getDimensionPixelSize(C1775R$dimen.floating_rotation_button_diameter);
        this.mMargin = Math.max(resources.getDimensionPixelSize(C1775R$dimen.floating_rotation_button_min_margin), resources.getDimensionPixelSize(C1775R$dimen.rounded_corner_content_padding));
    }

    public void setRotationButtonController(RotationButtonController rotationButtonController) {
        this.mRotationButtonController = rotationButtonController;
    }

    public View getCurrentView() {
        return this.mKeyButtonView;
    }

    public boolean show() {
        if (!this.mCanShow || this.mIsShowing) {
            return false;
        }
        this.mIsShowing = true;
        int i = this.mDiameter;
        int i2 = this.mMargin;
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(i, i, i2, i2, 2024, 8, -3);
        layoutParams.privateFlags |= 16;
        layoutParams.setTitle("FloatingRotationButton");
        int rotation = this.mWindowManager.getDefaultDisplay().getRotation();
        if (rotation == 0) {
            layoutParams.gravity = 83;
        } else if (rotation == 1) {
            layoutParams.gravity = 85;
        } else if (rotation == 2) {
            layoutParams.gravity = 53;
        } else if (rotation == 3) {
            layoutParams.gravity = 51;
        }
        updateIcon();
        this.mWindowManager.addView(this.mKeyButtonView, layoutParams);
        KeyButtonDrawable keyButtonDrawable = this.mKeyButtonDrawable;
        if (keyButtonDrawable != null && keyButtonDrawable.canAnimate()) {
            this.mKeyButtonDrawable.resetAnimation();
            this.mKeyButtonDrawable.startAnimation();
        }
        return true;
    }

    public boolean hide() {
        if (!this.mIsShowing) {
            return false;
        }
        this.mWindowManager.removeViewImmediate(this.mKeyButtonView);
        this.mIsShowing = false;
        return true;
    }

    public boolean isVisible() {
        return this.mIsShowing;
    }

    public void updateIcon() {
        if (this.mIsShowing) {
            this.mKeyButtonDrawable = getImageDrawable();
            this.mKeyButtonView.setImageDrawable(this.mKeyButtonDrawable);
            this.mKeyButtonDrawable.setCallback(this.mKeyButtonView);
            KeyButtonDrawable keyButtonDrawable = this.mKeyButtonDrawable;
            if (keyButtonDrawable != null && keyButtonDrawable.canAnimate()) {
                this.mKeyButtonDrawable.resetAnimation();
                this.mKeyButtonDrawable.startAnimation();
            }
        }
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.mKeyButtonView.setOnClickListener(onClickListener);
    }

    public void setOnHoverListener(View.OnHoverListener onHoverListener) {
        this.mKeyButtonView.setOnHoverListener(onHoverListener);
    }

    public KeyButtonDrawable getImageDrawable() {
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(this.mContext.getApplicationContext(), this.mRotationButtonController.getStyleRes());
        int themeAttr = Utils.getThemeAttr(contextThemeWrapper, C1772R$attr.darkIconTheme);
        ContextThemeWrapper contextThemeWrapper2 = new ContextThemeWrapper(contextThemeWrapper, Utils.getThemeAttr(contextThemeWrapper, C1772R$attr.lightIconTheme));
        int colorAttrDefaultColor = Utils.getColorAttrDefaultColor(new ContextThemeWrapper(contextThemeWrapper, themeAttr), C1772R$attr.singleToneColor);
        return KeyButtonDrawable.create(contextThemeWrapper2, Utils.getColorAttrDefaultColor(contextThemeWrapper2, C1772R$attr.singleToneColor), colorAttrDefaultColor, C1776R$drawable.ic_sysbar_rotate_button, false, Color.valueOf((float) Color.red(colorAttrDefaultColor), (float) Color.green(colorAttrDefaultColor), (float) Color.blue(colorAttrDefaultColor), 0.92f));
    }

    public void setDarkIntensity(float f) {
        this.mKeyButtonView.setDarkIntensity(f);
    }

    public void setCanShowRotationButton(boolean z) {
        this.mCanShow = z;
        if (!this.mCanShow) {
            hide();
        }
    }
}
