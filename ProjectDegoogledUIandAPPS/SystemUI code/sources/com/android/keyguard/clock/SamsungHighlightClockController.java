package com.android.keyguard.clock;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.text.Html;
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

public class SamsungHighlightClockController implements ClockPlugin {
    private int mAccentColor;
    private TextClock mClock;
    private final SysuiColorExtractor mColorExtractor;
    private final LayoutInflater mLayoutInflater;
    private final ViewPreviewer mRenderer = new ViewPreviewer();
    private final Resources mResources;
    private ClockLayout mView;

    public View getBigClockView() {
        return null;
    }

    public String getName() {
        return "samsung_highlight";
    }

    public void onTimeTick() {
    }

    public void onTimeZoneChanged(TimeZone timeZone) {
    }

    public void setStyle(Paint.Style style) {
    }

    public boolean shouldShowStatusArea() {
        return true;
    }

    public SamsungHighlightClockController(Resources resources, LayoutInflater layoutInflater, SysuiColorExtractor sysuiColorExtractor) {
        this.mResources = resources;
        this.mLayoutInflater = layoutInflater;
        this.mColorExtractor = sysuiColorExtractor;
    }

    private void createViews() {
        this.mView = (ClockLayout) this.mLayoutInflater.inflate(C1779R$layout.digital_clock_custom, (ViewGroup) null);
        this.mClock = (TextClock) this.mView.findViewById(C1777R$id.clock);
        ColorExtractor.GradientColors colors = this.mColorExtractor.getColors(2);
        setColorPalette(colors.supportsDarkText(), colors.getColorPalette());
        this.mClock.setLineSpacing(0.0f, 0.8f);
        TextClock textClock = this.mClock;
        textClock.setFormat12Hour(Html.fromHtml("hh<br><font color=" + this.mAccentColor + ">mm</font>"));
        TextClock textClock2 = this.mClock;
        textClock2.setFormat24Hour(Html.fromHtml("kk<br><font color=" + this.mAccentColor + ">mm</font>"));
        onTimeTick();
    }

    public void onDestroyView() {
        this.mView = null;
        this.mClock = null;
    }

    public String getTitle() {
        return this.mResources.getString(C1784R$string.clock_title_samsung_accent);
    }

    public Bitmap getThumbnail() {
        return BitmapFactory.decodeResource(this.mResources, C1776R$drawable.samsung_thumbnail);
    }

    public Bitmap getPreview(int i, int i2) {
        View inflate = this.mLayoutInflater.inflate(C1779R$layout.default_clock_preview, (ViewGroup) null);
        TextClock textClock = (TextClock) inflate.findViewById(C1777R$id.time);
        textClock.setTextColor(-1);
        ((TextClock) inflate.findViewById(C1777R$id.date)).setTextColor(-1);
        ColorExtractor.GradientColors colors = this.mColorExtractor.getColors(2);
        setColorPalette(colors.supportsDarkText(), colors.getColorPalette());
        textClock.setLineSpacing(0.0f, 0.8f);
        textClock.setFormat12Hour(Html.fromHtml("hh<br><font color=" + this.mAccentColor + ">mm</font>"));
        textClock.setFormat24Hour(Html.fromHtml("kk<br><font color=" + this.mAccentColor + ">mm</font>"));
        onTimeTick();
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
