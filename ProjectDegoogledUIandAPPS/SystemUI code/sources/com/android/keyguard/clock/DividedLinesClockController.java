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
import com.android.systemui.colorextraction.SysuiColorExtractor;
import com.android.systemui.plugins.ClockPlugin;
import com.android.systemui.statusbar.phone.KeyguardClockPositionAlgorithm;
import java.util.TimeZone;

public class DividedLinesClockController implements ClockPlugin {
    private View mBottomLine;
    private TextClock mClock;
    private final SysuiColorExtractor mColorExtractor;
    private final LayoutInflater mLayoutInflater;
    private final ViewPreviewer mRenderer = new ViewPreviewer();
    private final Resources mResources;
    private View mTopLine;
    private ClockLayout mView;

    public View getBigClockView() {
        return null;
    }

    public String getName() {
        return "dividedlines";
    }

    public String getTitle() {
        return "Divided Lines";
    }

    public void onTimeTick() {
    }

    public void onTimeZoneChanged(TimeZone timeZone) {
    }

    public void setColorPalette(boolean z, int[] iArr) {
    }

    public void setStyle(Paint.Style style) {
    }

    public boolean shouldShowStatusArea() {
        return false;
    }

    public DividedLinesClockController(Resources resources, LayoutInflater layoutInflater, SysuiColorExtractor sysuiColorExtractor) {
        this.mResources = resources;
        this.mLayoutInflater = layoutInflater;
        this.mColorExtractor = sysuiColorExtractor;
    }

    private void createViews() {
        this.mView = (ClockLayout) this.mLayoutInflater.inflate(C1779R$layout.divided_lines_clock, (ViewGroup) null);
        this.mClock = (TextClock) this.mView.findViewById(C1777R$id.clock);
        onTimeTick();
    }

    public void onDestroyView() {
        this.mView = null;
        this.mClock = null;
        this.mTopLine = null;
        this.mBottomLine = null;
    }

    public Bitmap getThumbnail() {
        return BitmapFactory.decodeResource(this.mResources, C1776R$drawable.divided_lines_thumbnail);
    }

    public Bitmap getPreview(int i, int i2) {
        View inflate = this.mLayoutInflater.inflate(C1779R$layout.divided_lines_preview, (ViewGroup) null);
        View findViewById = inflate.findViewById(C1777R$id.topLine);
        View findViewById2 = inflate.findViewById(C1777R$id.bottomLine);
        ((TextClock) inflate.findViewById(C1777R$id.clock)).setTextColor(-1);
        ((TextClock) inflate.findViewById(C1777R$id.date)).setTextColor(-1);
        findViewById.setBackgroundColor(-1);
        findViewById2.setBackgroundColor(-1);
        ColorExtractor.GradientColors colors = this.mColorExtractor.getColors(2);
        setColorPalette(colors.supportsDarkText(), colors.getColorPalette());
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

    public void setDarkAmount(float f) {
        this.mView.setDarkAmount(f);
    }
}
