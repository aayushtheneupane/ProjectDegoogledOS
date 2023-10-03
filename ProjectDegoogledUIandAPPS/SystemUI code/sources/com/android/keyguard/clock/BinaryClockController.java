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

public class BinaryClockController implements ClockPlugin {
    private ClockLayout mBigClockView;
    private BinaryClock mBinaryClock;
    private final SmallClockPosition mClockPosition;
    private final SysuiColorExtractor mColorExtractor;
    private final LayoutInflater mLayoutInflater;
    private TextClock mLockClock;
    private final ViewPreviewer mRenderer = new ViewPreviewer();
    private final Resources mResources;
    private View mView;

    public String getName() {
        return "binary";
    }

    public void setColorPalette(boolean z, int[] iArr) {
    }

    public void setStyle(Paint.Style style) {
    }

    public boolean shouldShowStatusArea() {
        return true;
    }

    public BinaryClockController(Resources resources, LayoutInflater layoutInflater, SysuiColorExtractor sysuiColorExtractor) {
        this.mResources = resources;
        this.mLayoutInflater = layoutInflater;
        this.mColorExtractor = sysuiColorExtractor;
        this.mClockPosition = new SmallClockPosition(resources);
    }

    private void createViews() {
        this.mBigClockView = (ClockLayout) this.mLayoutInflater.inflate(C1779R$layout.binary_clock, (ViewGroup) null);
        this.mBinaryClock = (BinaryClock) this.mBigClockView.findViewById(C1777R$id.analog_clock);
        this.mView = this.mLayoutInflater.inflate(C1779R$layout.digital_clock, (ViewGroup) null);
        this.mLockClock = (TextClock) this.mView.findViewById(C1777R$id.lock_screen_clock);
    }

    public void onDestroyView() {
        this.mBigClockView = null;
        this.mBinaryClock = null;
        this.mView = null;
        this.mLockClock = null;
    }

    public String getTitle() {
        return this.mResources.getString(C1784R$string.clock_title_binary);
    }

    public Bitmap getThumbnail() {
        return BitmapFactory.decodeResource(this.mResources, C1776R$drawable.binary_thumbnail);
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
        if (this.mView == null) {
            createViews();
        }
        return this.mView;
    }

    public View getBigClockView() {
        if (this.mBigClockView == null) {
            createViews();
        }
        return this.mBigClockView;
    }

    public int getPreferredY(int i) {
        return this.mClockPosition.getPreferredY();
    }

    public void setTextColor(int i) {
        this.mBinaryClock.setTintColor(i);
    }

    public void onTimeTick() {
        this.mBinaryClock.onTimeChanged();
        this.mBigClockView.onTimeChanged();
        this.mLockClock.refresh();
    }

    public void setDarkAmount(float f) {
        this.mClockPosition.setDarkAmount(f);
        this.mBigClockView.setDarkAmount(f);
        this.mBinaryClock.setDark(f == 1.0f);
    }

    public void onTimeZoneChanged(TimeZone timeZone) {
        this.mBinaryClock.onTimeZoneChanged(timeZone);
    }
}
