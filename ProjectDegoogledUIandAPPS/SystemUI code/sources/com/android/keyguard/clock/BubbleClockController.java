package com.android.keyguard.clock;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextClock;
import com.android.internal.colorextraction.ColorExtractor;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1779R$layout;
import com.android.systemui.C1784R$string;
import com.android.systemui.colorextraction.SysuiColorExtractor;
import com.android.systemui.plugins.ClockPlugin;
import java.util.TimeZone;

public class BubbleClockController implements ClockPlugin {
    private ImageClock mAnalogClock;
    private final SmallClockPosition mClockPosition;
    private final SysuiColorExtractor mColorExtractor;
    private final LayoutInflater mLayoutInflater;
    private TextClock mLockClock;
    private View mLockClockContainer;
    private final ClockPalette mPalette = new ClockPalette();
    private final ViewPreviewer mRenderer = new ViewPreviewer();
    private final Resources mResources;
    private ClockLayout mView;

    public String getName() {
        return "bubble";
    }

    public void setStyle(Paint.Style style) {
    }

    public boolean shouldShowStatusArea() {
        return true;
    }

    public BubbleClockController(Resources resources, LayoutInflater layoutInflater, SysuiColorExtractor sysuiColorExtractor) {
        this.mResources = resources;
        this.mLayoutInflater = layoutInflater;
        this.mColorExtractor = sysuiColorExtractor;
        this.mClockPosition = new SmallClockPosition(resources);
    }

    private void createViews() {
        this.mView = (ClockLayout) this.mLayoutInflater.inflate(C1779R$layout.bubble_clock, (ViewGroup) null);
        this.mAnalogClock = (ImageClock) this.mView.findViewById(C1777R$id.analog_clock);
        this.mLockClockContainer = this.mLayoutInflater.inflate(C1779R$layout.digital_clock, (ViewGroup) null);
        this.mLockClock = (TextClock) this.mLockClockContainer.findViewById(C1777R$id.lock_screen_clock);
    }

    public void onDestroyView() {
        this.mView = null;
        this.mAnalogClock = null;
        this.mLockClockContainer = null;
        this.mLockClock = null;
    }

    public String getTitle() {
        return this.mResources.getString(C1784R$string.clock_title_bubble);
    }

    public Bitmap getThumbnail() {
        return BitmapFactory.decodeResource(this.mResources, C1776R$drawable.bubble_thumbnail);
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
        if (this.mLockClockContainer == null) {
            createViews();
        }
        return this.mLockClockContainer;
    }

    public View getBigClockView() {
        if (this.mView == null) {
            createViews();
        }
        return this.mView;
    }

    public int getPreferredY(int i) {
        return this.mClockPosition.getPreferredY();
    }

    public void setTextColor(int i) {
        updateColor();
    }

    public void setColorPalette(boolean z, int[] iArr) {
        this.mPalette.setColorPalette(z, iArr);
        updateColor();
    }

    private void updateColor() {
        int primaryColor = this.mPalette.getPrimaryColor();
        int secondaryColor = this.mPalette.getSecondaryColor();
        this.mLockClock.setTextColor(secondaryColor);
        this.mAnalogClock.setClockColors(primaryColor, secondaryColor);
    }

    public void setDarkAmount(float f) {
        this.mPalette.setDarkAmount(f);
        this.mClockPosition.setDarkAmount(f);
        this.mView.setDarkAmount(f);
    }

    public void onTimeTick() {
        this.mAnalogClock.onTimeChanged();
        this.mView.onTimeChanged();
        this.mLockClock.refresh();
    }

    public void onTimeZoneChanged(TimeZone timeZone) {
        this.mAnalogClock.onTimeZoneChanged(timeZone);
    }
}
