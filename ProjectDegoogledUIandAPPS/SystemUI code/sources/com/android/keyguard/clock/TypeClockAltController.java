package com.android.keyguard.clock;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.util.MathUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.internal.colorextraction.ColorExtractor;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1779R$layout;
import com.android.systemui.C1784R$string;
import com.android.systemui.colorextraction.SysuiColorExtractor;
import com.android.systemui.plugins.ClockPlugin;
import java.util.TimeZone;

public class TypeClockAltController implements ClockPlugin {
    private final int mBurnInOffsetY;
    private final SysuiColorExtractor mColorExtractor;
    private float mDarkAmount;
    private CrossFadeDarkController mDarkController;
    private final int mKeyguardLockHeight;
    private final int mKeyguardLockPadding;
    private final LayoutInflater mLayoutInflater;
    private TypographicClock mLockClock;
    private final ViewPreviewer mRenderer = new ViewPreviewer();
    private final Resources mResources;
    private final int mStatusBarHeight;
    private TypographicClock mTypeClock;
    private View mView;

    public String getName() {
        return "type_alt";
    }

    public void setStyle(Paint.Style style) {
    }

    public boolean shouldShowStatusArea() {
        return true;
    }

    TypeClockAltController(Resources resources, LayoutInflater layoutInflater, SysuiColorExtractor sysuiColorExtractor) {
        this.mResources = resources;
        this.mLayoutInflater = layoutInflater;
        this.mColorExtractor = sysuiColorExtractor;
        this.mStatusBarHeight = resources.getDimensionPixelSize(C1775R$dimen.status_bar_height);
        this.mKeyguardLockPadding = resources.getDimensionPixelSize(C1775R$dimen.keyguard_lock_padding);
        this.mKeyguardLockHeight = resources.getDimensionPixelSize(C1775R$dimen.keyguard_lock_height);
        this.mBurnInOffsetY = resources.getDimensionPixelSize(C1775R$dimen.burn_in_prevention_offset_y);
    }

    private void createViews() {
        this.mView = this.mLayoutInflater.inflate(C1779R$layout.type_clock_alt, (ViewGroup) null);
        this.mTypeClock = (TypographicClock) this.mView.findViewById(C1777R$id.type_clock);
        this.mLockClock = (TypographicClock) this.mLayoutInflater.inflate(C1779R$layout.typographic_clock_alt, (ViewGroup) null);
        this.mLockClock.setVisibility(8);
        this.mDarkController = new CrossFadeDarkController(this.mView, this.mLockClock);
    }

    public void onDestroyView() {
        this.mView = null;
        this.mTypeClock = null;
        this.mLockClock = null;
        this.mDarkController = null;
    }

    public String getTitle() {
        return this.mResources.getString(C1784R$string.clock_title_type_alt);
    }

    public Bitmap getThumbnail() {
        return BitmapFactory.decodeResource(this.mResources, C1776R$drawable.type_thumbnail);
    }

    public Bitmap getPreview(int i, int i2) {
        View bigClockView = getBigClockView();
        setDarkAmount(1.0f);
        setTextColor(-1);
        ColorExtractor.GradientColors colors = this.mColorExtractor.getColors(2);
        setColorPalette(colors.supportsDarkText(), colors.getColorPalette());
        onTimeTick();
        return this.mRenderer.createPreview(bigClockView, i, i2);
    }

    public View getView() {
        if (this.mLockClock == null) {
            createViews();
        }
        return this.mLockClock;
    }

    public View getBigClockView() {
        if (this.mView == null) {
            createViews();
        }
        return this.mView;
    }

    public int getPreferredY(int i) {
        return (int) MathUtils.lerp((float) (this.mStatusBarHeight + this.mKeyguardLockHeight + (this.mKeyguardLockPadding * 2) + (this.mTypeClock.getHeight() / 2)), (float) (this.mStatusBarHeight + this.mKeyguardLockHeight + (this.mKeyguardLockPadding * 2) + this.mBurnInOffsetY + this.mTypeClock.getHeight() + (this.mTypeClock.getHeight() / 2)), this.mDarkAmount);
    }

    public void setTextColor(int i) {
        this.mTypeClock.setTextColor(i);
        this.mLockClock.setTextColor(i);
    }

    public void setColorPalette(boolean z, int[] iArr) {
        if (iArr != null && iArr.length != 0) {
            int i = iArr[Math.max(0, iArr.length - 5)];
            this.mTypeClock.setClockColor(i);
            this.mLockClock.setClockColor(i);
        }
    }

    public void onTimeTick() {
        this.mTypeClock.onTimeChanged();
        this.mLockClock.onTimeChanged();
    }

    public void setDarkAmount(float f) {
        this.mDarkAmount = f;
        CrossFadeDarkController crossFadeDarkController = this.mDarkController;
        if (crossFadeDarkController != null) {
            crossFadeDarkController.setDarkAmount(f);
        }
    }

    public void onTimeZoneChanged(TimeZone timeZone) {
        this.mTypeClock.onTimeZoneChanged(timeZone);
        this.mLockClock.onTimeZoneChanged(timeZone);
    }
}
