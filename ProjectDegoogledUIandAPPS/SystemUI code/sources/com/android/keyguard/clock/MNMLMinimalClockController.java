package com.android.keyguard.clock;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
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
import com.android.systemui.statusbar.phone.KeyguardClockPositionAlgorithm;
import java.util.TimeZone;

public class MNMLMinimalClockController implements ClockPlugin {
    private int mAccentColor;
    private TextClock mClock;
    private final SysuiColorExtractor mColorExtractor;
    private TextClock mDate;
    private final LayoutInflater mLayoutInflater;
    private final ViewPreviewer mRenderer = new ViewPreviewer();
    private final Resources mResources;
    private ClockLayout mView;

    public View getBigClockView() {
        return null;
    }

    public String getName() {
        return "mnml_mnml";
    }

    public void onTimeTick() {
    }

    public void onTimeZoneChanged(TimeZone timeZone) {
    }

    public void setStyle(Paint.Style style) {
    }

    public boolean shouldShowStatusArea() {
        return false;
    }

    public MNMLMinimalClockController(Resources resources, LayoutInflater layoutInflater, SysuiColorExtractor sysuiColorExtractor) {
        this.mResources = resources;
        this.mLayoutInflater = layoutInflater;
        this.mColorExtractor = sysuiColorExtractor;
    }

    private void createViews() {
        this.mView = (ClockLayout) this.mLayoutInflater.inflate(C1779R$layout.digital_mnml_minimal, (ViewGroup) null);
        this.mClock = (TextClock) this.mView.findViewById(C1777R$id.clock);
        this.mDate = (TextClock) this.mView.findViewById(C1777R$id.date);
        ColorExtractor.GradientColors colors = this.mColorExtractor.getColors(2);
        setColorPalette(colors.supportsDarkText(), colors.getColorPalette());
        GradientDrawable gradientDrawable = (GradientDrawable) this.mDate.getBackground();
        gradientDrawable.setColor(this.mAccentColor);
        gradientDrawable.setStroke(0, 0);
    }

    public void onDestroyView() {
        this.mView = null;
        this.mClock = null;
        this.mDate = null;
    }

    public String getTitle() {
        return this.mResources.getString(C1784R$string.clock_title_mnml_minimal);
    }

    public Bitmap getThumbnail() {
        return BitmapFactory.decodeResource(this.mResources, C1776R$drawable.mnmlminimal_thumbnail);
    }

    public Bitmap getPreview(int i, int i2) {
        View inflate = this.mLayoutInflater.inflate(C1779R$layout.digital_mnml_minimal_preview, (ViewGroup) null);
        TextClock textClock = (TextClock) inflate.findViewById(C1777R$id.date);
        ((TextClock) inflate.findViewById(C1777R$id.clock)).setTextColor(-1);
        textClock.setTextColor(-16777216);
        ColorExtractor.GradientColors colors = this.mColorExtractor.getColors(2);
        setColorPalette(colors.supportsDarkText(), colors.getColorPalette());
        GradientDrawable gradientDrawable = (GradientDrawable) textClock.getBackground();
        gradientDrawable.setColor(this.mAccentColor);
        gradientDrawable.setStroke(0, 0);
        return this.mRenderer.createPreview(inflate, i, i2);
    }

    public View getView() {
        if (this.mView == null) {
            createViews();
        }
        return this.mView;
    }

    public int getPreferredY(int i) {
        return KeyguardClockPositionAlgorithm.CLOCK_USE_DEFAULT_Y;
    }

    public void setTextColor(int i) {
        this.mClock.setTextColor(i);
    }

    public void setColorPalette(boolean z, int[] iArr) {
        if (iArr != null && iArr.length != 0) {
            this.mAccentColor = iArr[Math.max(0, iArr.length - 5)];
        }
    }

    public void setDarkAmount(float f) {
        this.mView.setDarkAmount(f);
    }
}
