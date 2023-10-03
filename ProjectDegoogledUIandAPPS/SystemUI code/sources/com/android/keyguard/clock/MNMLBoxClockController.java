package com.android.keyguard.clock;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.internal.colorextraction.ColorExtractor;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1779R$layout;
import com.android.systemui.C1784R$string;
import com.android.systemui.colorextraction.SysuiColorExtractor;
import com.android.systemui.plugins.ClockPlugin;
import com.android.systemui.statusbar.phone.KeyguardClockPositionAlgorithm;
import java.util.Calendar;
import java.util.TimeZone;

public class MNMLBoxClockController implements ClockPlugin {
    private TextView mClock;
    private final SysuiColorExtractor mColorExtractor;
    private TextView mDate;
    private TextView mDateDay;
    private final LayoutInflater mLayoutInflater;
    private final ViewPreviewer mRenderer = new ViewPreviewer();
    private final Resources mResources;
    private final Calendar mTime = Calendar.getInstance(TimeZone.getDefault());
    private ClockLayout mView;

    public View getBigClockView() {
        return null;
    }

    public String getName() {
        return "mnml_box";
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

    public MNMLBoxClockController(Resources resources, LayoutInflater layoutInflater, SysuiColorExtractor sysuiColorExtractor) {
        this.mResources = resources;
        this.mLayoutInflater = layoutInflater;
        this.mColorExtractor = sysuiColorExtractor;
    }

    private void createViews() {
        this.mView = (ClockLayout) this.mLayoutInflater.inflate(C1779R$layout.digital_mnml_box, (ViewGroup) null);
        this.mClock = (TextView) this.mView.findViewById(C1777R$id.clock);
        this.mDate = (TextView) this.mView.findViewById(C1777R$id.bigDate);
        this.mDateDay = (TextView) this.mView.findViewById(C1777R$id.bigDateDay);
    }

    public void onDestroyView() {
        this.mView = null;
        this.mClock = null;
        this.mDate = null;
        this.mDateDay = null;
    }

    public String getTitle() {
        return this.mResources.getString(C1784R$string.clock_title_mnml_box);
    }

    public Bitmap getThumbnail() {
        return BitmapFactory.decodeResource(this.mResources, C1776R$drawable.mnmlbox_thumbnail);
    }

    public Bitmap getPreview(int i, int i2) {
        View inflate = this.mLayoutInflater.inflate(C1779R$layout.digital_mnml_box_preview, (ViewGroup) null);
        ((TextView) inflate.findViewById(C1777R$id.clock)).setTextColor(-1);
        ((TextView) inflate.findViewById(C1777R$id.bigDate)).setTextColor(-1);
        ((TextView) inflate.findViewById(C1777R$id.bigDateDay)).setTextColor(-1);
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
        this.mDate.setTextColor(i);
        this.mDateDay.setTextColor(i);
    }

    public void setDarkAmount(float f) {
        this.mView.setDarkAmount(f);
    }
}
