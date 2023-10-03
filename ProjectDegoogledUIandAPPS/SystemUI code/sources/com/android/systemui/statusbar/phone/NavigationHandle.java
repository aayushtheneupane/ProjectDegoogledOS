package com.android.systemui.statusbar.phone;

import android.animation.ArgbEvaluator;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.View;
import com.android.settingslib.Utils;
import com.android.systemui.C1772R$attr;
import com.android.systemui.C1775R$dimen;

public class NavigationHandle extends View implements ButtonInterface {
    private final int mBaseWidth;
    private final int mBottom;
    private final int mDarkColor;
    private float mDarkIntensity;
    private final int mLightColor;
    private final Paint mPaint;
    private final int mRadius;
    private Resources mRes;
    private ContentResolver mResolver;

    public void abortCurrentGesture() {
    }

    public void setDelayTouchFeedback(boolean z) {
    }

    public void setImageDrawable(Drawable drawable) {
    }

    public void setVertical(boolean z) {
    }

    public NavigationHandle(Context context) {
        this(context, (AttributeSet) null);
    }

    public NavigationHandle(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mDarkIntensity = -1.0f;
        this.mPaint = new Paint();
        this.mRes = context.getResources();
        this.mResolver = context.getContentResolver();
        this.mRadius = this.mRes.getDimensionPixelSize(C1775R$dimen.navigation_handle_radius);
        this.mBottom = this.mRes.getDimensionPixelSize(C1775R$dimen.navigation_handle_bottom);
        this.mBaseWidth = this.mRes.getDimensionPixelSize(C1775R$dimen.navigation_home_handle_width);
        int themeAttr = Utils.getThemeAttr(context, C1772R$attr.darkIconTheme);
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, Utils.getThemeAttr(context, C1772R$attr.lightIconTheme));
        ContextThemeWrapper contextThemeWrapper2 = new ContextThemeWrapper(context, themeAttr);
        this.mLightColor = Utils.getColorAttrDefaultColor(contextThemeWrapper, C1772R$attr.homeHandleColor);
        this.mDarkColor = Utils.getColorAttrDefaultColor(contextThemeWrapper2, C1772R$attr.homeHandleColor);
        this.mPaint.setAntiAlias(true);
        setFocusable(false);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int i = this.mRadius * 2;
        int width = getWidth();
        int i2 = (height - this.mBottom) - i;
        float f = (float) (i2 + i);
        int i3 = this.mRadius;
        canvas.drawRoundRect(0.0f, (float) i2, (float) width, f, (float) i3, (float) i3, this.mPaint);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        setMeasuredDimension(getCustomWidth() + getPaddingLeft() + getPaddingRight(), View.getDefaultSize(getSuggestedMinimumHeight(), i2));
    }

    public void setDarkIntensity(float f) {
        if (this.mDarkIntensity != f) {
            this.mPaint.setColor(((Integer) ArgbEvaluator.getInstance().evaluate(f, Integer.valueOf(this.mLightColor), Integer.valueOf(this.mDarkColor))).intValue());
            this.mDarkIntensity = f;
            invalidate();
        }
    }

    private int getCustomWidth() {
        int i = Settings.System.getInt(this.mResolver, "navigation_handle_width", 1);
        if (i == 1) {
            return this.mBaseWidth;
        }
        if (i == 2) {
            return (int) (((double) this.mBaseWidth) * 1.33d);
        }
        if (i == 3) {
            return this.mBaseWidth * 2;
        }
        return 0;
    }
}
