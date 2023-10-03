package com.android.settings.fuelgauge;

import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.provider.Settings;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.android.settingslib.Utils;
import com.android.settingslib.graph.CircleBatteryDrawable;
import com.android.settingslib.graph.FullCircleBatteryDrawable;
import com.android.settingslib.graph.ThemedBatteryDrawable;
import com.havoc.config.center.C1715R;

public class BatteryMeterView extends ImageView {
    ColorFilter mAccentColorFilter;
    CircleBatteryDrawable mCircleDrawable;
    ColorFilter mErrorColorFilter;
    ColorFilter mForegroundColorFilter;
    FullCircleBatteryDrawable mFullCircleDrawable;
    private int mIconStyle;
    BatteryMeterDrawable mThemedDrawable;

    public BatteryMeterView(Context context) {
        this(context, (AttributeSet) null, 0);
    }

    public BatteryMeterView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BatteryMeterView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mIconStyle = 0;
        int color = context.getColor(C1715R.C1716color.meter_background_color);
        this.mAccentColorFilter = new PorterDuffColorFilter(Utils.getColorAttrDefaultColor(context, 16843829), PorterDuff.Mode.SRC_IN);
        this.mErrorColorFilter = new PorterDuffColorFilter(context.getColor(C1715R.C1716color.battery_icon_color_error), PorterDuff.Mode.SRC_IN);
        this.mForegroundColorFilter = new PorterDuffColorFilter(Utils.getColorAttrDefaultColor(context, 16842800), PorterDuff.Mode.SRC_IN);
        this.mThemedDrawable = new BatteryMeterDrawable(context, color);
        this.mCircleDrawable = new CircleBatteryDrawable(context, color);
        this.mFullCircleDrawable = new FullCircleBatteryDrawable(context, color);
    }

    public void setDrawableStyle() {
        int i = Settings.System.getInt(this.mContext.getContentResolver(), "status_bar_battery_style", 0);
        if (i == 1 || i == 2) {
            this.mCircleDrawable.setMeterStyle(i);
            this.mCircleDrawable.setColorFilter(this.mAccentColorFilter);
            setImageDrawable(this.mCircleDrawable);
        } else if (i != 3) {
            this.mThemedDrawable.setColorFilter(this.mAccentColorFilter);
            setImageDrawable(this.mThemedDrawable);
        } else {
            this.mFullCircleDrawable.setColorFilter(this.mAccentColorFilter);
            setImageDrawable(this.mFullCircleDrawable);
        }
        if (this.mIconStyle != i) {
            this.mIconStyle = i;
            postInvalidate();
        }
    }

    public void setBatteryLevel(int i) {
        this.mCircleDrawable.setBatteryLevel(i);
        this.mFullCircleDrawable.setBatteryLevel(i);
        this.mThemedDrawable.setBatteryLevel(i);
        updateColorFilter();
    }

    public int getBatteryLevel() {
        return this.mThemedDrawable.getBatteryLevel();
    }

    public void setPowerSave(boolean z) {
        this.mCircleDrawable.setPowerSaveEnabled(z);
        this.mFullCircleDrawable.setPowerSaveEnabled(z);
        this.mThemedDrawable.setPowerSaveEnabled(z);
        updateColorFilter();
    }

    public boolean getPowerSave() {
        return this.mThemedDrawable.getPowerSaveEnabled();
    }

    public void setCharging(boolean z) {
        this.mCircleDrawable.setCharging(z);
        this.mFullCircleDrawable.setCharging(z);
        this.mThemedDrawable.setCharging(z);
        postInvalidate();
    }

    public boolean getCharging() {
        return this.mThemedDrawable.getCharging();
    }

    private int getCriticalLevel() {
        return this.mThemedDrawable.getCriticalLevel();
    }

    private void updateColorFilter() {
        boolean powerSave = getPowerSave();
        int batteryLevel = getBatteryLevel();
        if (powerSave) {
            this.mCircleDrawable.setColorFilter(this.mForegroundColorFilter);
            this.mFullCircleDrawable.setColorFilter(this.mForegroundColorFilter);
            this.mThemedDrawable.setColorFilter(this.mForegroundColorFilter);
        } else if (batteryLevel < getCriticalLevel()) {
            this.mCircleDrawable.setColorFilter(this.mErrorColorFilter);
            this.mFullCircleDrawable.setColorFilter(this.mErrorColorFilter);
            this.mThemedDrawable.setColorFilter(this.mErrorColorFilter);
        } else {
            this.mCircleDrawable.setColorFilter(this.mAccentColorFilter);
            this.mFullCircleDrawable.setColorFilter(this.mAccentColorFilter);
            this.mThemedDrawable.setColorFilter(this.mAccentColorFilter);
        }
    }

    public static class BatteryMeterDrawable extends ThemedBatteryDrawable {
        private final int mIntrinsicHeight;
        private final int mIntrinsicWidth;

        public BatteryMeterDrawable(Context context, int i) {
            super(context, i);
            this.mIntrinsicWidth = context.getResources().getDimensionPixelSize(C1715R.dimen.battery_meter_width);
            this.mIntrinsicHeight = context.getResources().getDimensionPixelSize(C1715R.dimen.battery_meter_height);
        }

        public BatteryMeterDrawable(Context context, int i, int i2, int i3) {
            super(context, i);
            this.mIntrinsicWidth = i2;
            this.mIntrinsicHeight = i3;
        }

        public int getIntrinsicWidth() {
            return this.mIntrinsicWidth;
        }

        public int getIntrinsicHeight() {
            return this.mIntrinsicHeight;
        }
    }
}
