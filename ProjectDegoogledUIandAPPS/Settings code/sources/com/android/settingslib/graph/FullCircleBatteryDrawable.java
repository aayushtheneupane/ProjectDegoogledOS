package com.android.settingslib.graph;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import com.android.settingslib.R$array;
import com.android.settingslib.R$color;
import com.android.settingslib.R$dimen;
import com.android.settingslib.R$string;
import com.android.settingslib.Utils;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FullCircleBatteryDrawable.kt */
public class FullCircleBatteryDrawable extends Drawable {
    /* access modifiers changed from: private */
    public int batteryAlpha;
    private int batteryLevel = -1;
    private final Paint batteryPaint;
    private int chargeColor;
    private boolean charging;
    /* access modifiers changed from: private */
    public ValueAnimator chargingAnimator;
    private final int[] colors;
    private final Context context;
    private int criticalLevel = this.context.getResources().getInteger(17694766);
    private boolean dualTone;
    private final RectF frame = new RectF();
    private final Paint framePaint;
    private int height;
    private int iconTint = -1;
    private int intrinsicHeight;
    private int intrinsicWidth;
    private final Rect padding = new Rect();
    private boolean powerSaveEnabled;
    private final Paint powerSavePaint;
    private boolean showPercent;
    private final Paint textPaint;
    private final String warningString;
    private int width;

    public int getOpacity() {
        return 0;
    }

    public void setAlpha(int i) {
    }

    public FullCircleBatteryDrawable(Context context2, int i) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        this.context = context2;
        Resources resources = this.context.getResources();
        TypedArray obtainTypedArray = resources.obtainTypedArray(R$array.batterymeter_color_levels);
        TypedArray obtainTypedArray2 = resources.obtainTypedArray(R$array.batterymeter_color_values);
        this.colors = new int[(obtainTypedArray.length() * 2)];
        int length = obtainTypedArray.length();
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 2;
            this.colors[i3] = obtainTypedArray.getInt(i2, 0);
            if (obtainTypedArray2.getType(i2) == 2) {
                this.colors[i3 + 1] = Utils.getColorAttrDefaultColor(this.context, obtainTypedArray2.getThemeAttributeId(i2, 0));
            } else {
                this.colors[i3 + 1] = obtainTypedArray2.getColor(i2, 0);
            }
        }
        obtainTypedArray.recycle();
        obtainTypedArray2.recycle();
        String string = resources.getString(R$string.battery_meter_very_low_overlay_symbol);
        Intrinsics.checkExpressionValueIsNotNull(string, "res.getString(R.string.b…_very_low_overlay_symbol)");
        this.warningString = string;
        this.framePaint = new Paint(1);
        this.framePaint.setColor(i);
        this.framePaint.setDither(true);
        this.batteryPaint = new Paint(1);
        this.batteryPaint.setDither(true);
        this.batteryAlpha = this.batteryPaint.getAlpha();
        this.textPaint = new Paint(1);
        this.textPaint.setTypeface(Typeface.create("sans-serif-condensed", 1));
        this.textPaint.setTextAlign(Paint.Align.CENTER);
        this.textPaint.setStrokeWidth(2.0f);
        this.textPaint.setStyle(Paint.Style.STROKE);
        this.chargeColor = Utils.getColorStateListDefaultColor(this.context, R$color.meter_consumed_color);
        this.powerSavePaint = new Paint(1);
        this.powerSavePaint.setColor(Utils.getColorStateListDefaultColor(this.context, R$color.batterymeter_plus_color));
        this.powerSavePaint.setStyle(Paint.Style.STROKE);
        this.powerSavePaint.setStrokeWidth(3.0f);
        this.intrinsicWidth = resources.getDimensionPixelSize(R$dimen.battery_width);
        this.intrinsicHeight = resources.getDimensionPixelSize(R$dimen.battery_height);
        this.dualTone = resources.getBoolean(17891376);
    }

    public int getIntrinsicHeight() {
        return this.intrinsicHeight;
    }

    public int getIntrinsicWidth() {
        return this.intrinsicWidth;
    }

    public int getCriticalLevel() {
        return this.criticalLevel;
    }

    public final void setCharging(boolean z) {
        boolean z2 = this.charging;
        this.charging = z;
        if (!z) {
            cancelChargingAnimation();
            postInvalidate();
        } else if (!z2) {
            startChargingAnimation(-1);
        }
    }

    public final void setPowerSaveEnabled(boolean z) {
        this.powerSaveEnabled = z;
        postInvalidate();
    }

    private final void startChargingAnimation(int i) {
        int alpha = this.batteryPaint.getAlpha();
        this.chargingAnimator = ValueAnimator.ofInt(new int[]{alpha, 0, alpha});
        ValueAnimator valueAnimator = this.chargingAnimator;
        if (valueAnimator != null) {
            valueAnimator.addUpdateListener(new FullCircleBatteryDrawable$startChargingAnimation$1(this));
        }
        ValueAnimator valueAnimator2 = this.chargingAnimator;
        if (valueAnimator2 != null) {
            valueAnimator2.addListener(new FullCircleBatteryDrawable$startChargingAnimation$2(this, alpha));
        }
        ValueAnimator valueAnimator3 = this.chargingAnimator;
        if (valueAnimator3 != null) {
            valueAnimator3.setRepeatCount(i);
        }
        ValueAnimator valueAnimator4 = this.chargingAnimator;
        if (valueAnimator4 != null) {
            valueAnimator4.setDuration(4000);
        }
        ValueAnimator valueAnimator5 = this.chargingAnimator;
        if (valueAnimator5 != null) {
            valueAnimator5.setStartDelay(500);
        }
        ValueAnimator valueAnimator6 = this.chargingAnimator;
        if (valueAnimator6 != null) {
            valueAnimator6.start();
        }
    }

    private final void cancelChargingAnimation() {
        ValueAnimator valueAnimator = this.chargingAnimator;
        if (valueAnimator != null && valueAnimator != null) {
            valueAnimator.cancel();
        }
    }

    /* access modifiers changed from: private */
    public final void postInvalidate() {
        unscheduleSelf(new FullCircleBatteryDrawable$postInvalidate$1(this));
        scheduleSelf(new FullCircleBatteryDrawable$postInvalidate$2(this), 0);
    }

    public void setBounds(int i, int i2, int i3, int i4) {
        super.setBounds(i, i2, i3, i4);
        updateSize();
    }

    private final void updateSize() {
        Resources resources = this.context.getResources();
        this.height = (getBounds().bottom - this.padding.bottom) - (getBounds().top + this.padding.top);
        this.width = (getBounds().right - this.padding.right) - (getBounds().left + this.padding.left);
        this.intrinsicHeight = resources.getDimensionPixelSize(R$dimen.battery_height);
        this.intrinsicWidth = resources.getDimensionPixelSize(R$dimen.battery_height);
        this.textPaint.setTextSize(((float) this.height) * 0.7f);
    }

    public boolean getPadding(Rect rect) {
        Intrinsics.checkParameterIsNotNull(rect, "padding");
        Rect rect2 = this.padding;
        if (rect2.left == 0 && rect2.top == 0 && rect2.right == 0 && rect2.bottom == 0) {
            return super.getPadding(rect);
        }
        rect.set(this.padding);
        return true;
    }

    public void setBatteryLevel(int i) {
        this.batteryLevel = i;
        this.chargeColor = batteryColorForLevel(this.batteryLevel);
        invalidateSelf();
    }

    private final int getColorForLevel(int i) {
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int[] iArr = this.colors;
            if (i2 >= iArr.length) {
                return i3;
            }
            int i4 = iArr[i2];
            int i5 = iArr[i2 + 1];
            if (i <= i4) {
                return i2 == iArr.length + -2 ? this.iconTint : i5;
            }
            i2 += 2;
            i3 = i5;
        }
    }

    private final int batteryColorForLevel(int i) {
        if (this.charging || this.powerSaveEnabled) {
            return this.chargeColor;
        }
        return getColorForLevel(i);
    }

    public void draw(Canvas canvas) {
        String str;
        Intrinsics.checkParameterIsNotNull(canvas, "c");
        if (this.batteryLevel != -1) {
            float strokeWidth = this.powerSavePaint.getStrokeWidth();
            float min = (float) Math.min(this.width, this.height);
            float f = min / 2.0f;
            float f2 = ((float) this.batteryLevel) / 100.0f;
            if (!this.charging && this.powerSaveEnabled) {
                f -= strokeWidth / 2.0f;
            }
            this.framePaint.setStrokeWidth(0.0f);
            this.framePaint.setStyle(Paint.Style.FILL_AND_STROKE);
            this.batteryPaint.setStrokeWidth(0.0f);
            this.batteryPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            RectF rectF = this.frame;
            float f3 = strokeWidth / 2.0f;
            int i = this.padding.left;
            float f4 = min - f3;
            rectF.set(((float) i) + f3, f3, ((float) i) + f4, f4);
            this.batteryPaint.setColor(batteryColorForLevel(this.batteryLevel));
            if (this.chargingAnimator != null) {
                if (this.batteryLevel == 100) {
                    cancelChargingAnimation();
                } else {
                    this.batteryPaint.setAlpha(this.batteryAlpha);
                }
            }
            if (this.batteryLevel <= getCriticalLevel()) {
                f2 = 0.0f;
            }
            canvas.drawCircle(this.frame.centerX(), this.frame.centerY(), f, this.framePaint);
            canvas.save();
            if (this.batteryLevel != 100 && this.showPercent) {
                float f5 = -this.textPaint.getFontMetrics().ascent;
                if (this.batteryLevel > getCriticalLevel()) {
                    str = String.valueOf(this.batteryLevel);
                } else {
                    str = this.warningString;
                }
                String str2 = str;
                float f6 = (((float) this.height) + f5) * 0.45f;
                this.textPaint.setColor(batteryColorForLevel(this.batteryLevel));
                canvas.drawText(str2, this.frame.centerX(), f6, this.textPaint);
                Path path = new Path();
                this.textPaint.getTextPath(str2, 0, str2.length(), this.frame.centerX(), f6, path);
                canvas.clipOutPath(path);
            }
            if (f2 != 0.0f) {
                canvas.drawCircle(this.frame.centerX(), this.frame.centerY(), f2 * f, this.batteryPaint);
            }
            if (!this.charging && this.powerSaveEnabled) {
                canvas.drawCircle(this.frame.centerX(), this.frame.centerY(), f, this.powerSavePaint);
            }
            canvas.restore();
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.framePaint.setColorFilter(colorFilter);
        this.batteryPaint.setColorFilter(colorFilter);
        this.textPaint.setColorFilter(colorFilter);
    }
}
