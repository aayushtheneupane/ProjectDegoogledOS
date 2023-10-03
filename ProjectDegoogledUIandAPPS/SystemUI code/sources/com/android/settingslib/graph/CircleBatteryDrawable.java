package com.android.settingslib.graph;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
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

/* compiled from: CircleBatteryDrawable.kt */
public class CircleBatteryDrawable extends Drawable {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private int BATTERY_STYLE_CIRCLE = 1;
    private int BATTERY_STYLE_DOTTED_CIRCLE = 2;
    private int batteryLevel = -1;
    private final Paint batteryPaint;
    private final RectF boltFrame = new RectF();
    private final Paint boltPaint;
    private final Path boltPath = new Path();
    private final float[] boltPoints;
    private int chargeColor;
    private boolean charging;
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
    private int meterStyle = this.BATTERY_STYLE_CIRCLE;
    private final Rect padding = new Rect();
    private final DashPathEffect pathEffect = new DashPathEffect(new float[]{3.0f, 2.0f}, 0.0f);
    private final Paint plusPaint;
    private boolean powerSaveEnabled;
    private final Paint powerSavePaint;
    private boolean showPercent;
    private final Paint textPaint;
    private final String warningString;
    private final Paint warningTextPaint;
    private int width;

    public int getOpacity() {
        return 0;
    }

    public void setAlpha(int i) {
    }

    public CircleBatteryDrawable(Context context2, int i) {
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
        this.textPaint = new Paint(1);
        this.textPaint.setTypeface(Typeface.create("sans-serif-condensed", 1));
        this.textPaint.setTextAlign(Paint.Align.CENTER);
        this.warningTextPaint = new Paint(1);
        this.warningTextPaint.setTypeface(Typeface.create("sans-serif", 1));
        this.warningTextPaint.setTextAlign(Paint.Align.CENTER);
        int[] iArr = this.colors;
        if (iArr.length > 1) {
            this.warningTextPaint.setColor(iArr[1]);
        }
        this.chargeColor = Utils.getColorStateListDefaultColor(this.context, R$color.meter_consumed_color);
        this.boltPaint = new Paint(1);
        this.boltPaint.setColor(Utils.getColorStateListDefaultColor(this.context, R$color.batterymeter_bolt_color));
        Companion companion = Companion;
        Intrinsics.checkExpressionValueIsNotNull(resources, "res");
        this.boltPoints = companion.loadPoints(resources, R$array.batterymeter_bolt_points);
        this.plusPaint = new Paint(1);
        this.plusPaint.setColor(Utils.getColorStateListDefaultColor(this.context, R$color.batterymeter_plus_color));
        this.powerSavePaint = new Paint(1);
        this.powerSavePaint.setColor(this.plusPaint.getColor());
        this.powerSavePaint.setStyle(Paint.Style.STROKE);
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
        this.charging = z;
        postInvalidate();
    }

    public final void setPowerSaveEnabled(boolean z) {
        this.powerSaveEnabled = z;
        postInvalidate();
    }

    public final void setShowPercent(boolean z) {
        this.showPercent = z;
        postInvalidate();
    }

    public final void setMeterStyle(int i) {
        this.meterStyle = i;
        postInvalidate();
    }

    private final void postInvalidate() {
        unscheduleSelf(new CircleBatteryDrawable$postInvalidate$1(this));
        scheduleSelf(new CircleBatteryDrawable$postInvalidate$2(this), 0);
    }

    public void setBounds(int i, int i2, int i3, int i4) {
        super.setBounds(i, i2, i3, i4);
        updateSize();
    }

    private final void updateSize() {
        Resources resources = this.context.getResources();
        this.height = (getBounds().bottom - this.padding.bottom) - (getBounds().top + this.padding.top);
        this.width = (getBounds().right - this.padding.right) - (getBounds().left + this.padding.left);
        this.warningTextPaint.setTextSize(((float) this.height) * 0.75f);
        this.intrinsicHeight = resources.getDimensionPixelSize(R$dimen.battery_height);
        this.intrinsicWidth = resources.getDimensionPixelSize(R$dimen.battery_height);
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

    public final void setColors(int i, int i2, int i3) {
        if (!this.dualTone) {
            i = i3;
        }
        this.iconTint = i;
        this.framePaint.setColor(i2);
        this.boltPaint.setColor(i);
        this.chargeColor = i;
        invalidateSelf();
    }

    public void draw(Canvas canvas) {
        int i;
        String str;
        float[] fArr;
        Intrinsics.checkParameterIsNotNull(canvas, "c");
        if (this.batteryLevel != -1) {
            float min = (float) Math.min(this.width, this.height);
            float f = min / 6.5f;
            this.framePaint.setStrokeWidth(f);
            this.framePaint.setStyle(Paint.Style.STROKE);
            this.batteryPaint.setStrokeWidth(f);
            this.batteryPaint.setStyle(Paint.Style.STROKE);
            if (this.meterStyle == this.BATTERY_STYLE_DOTTED_CIRCLE) {
                this.batteryPaint.setPathEffect(this.pathEffect);
                this.powerSavePaint.setPathEffect(this.pathEffect);
            } else {
                this.batteryPaint.setPathEffect((PathEffect) null);
                this.powerSavePaint.setPathEffect((PathEffect) null);
            }
            this.powerSavePaint.setStrokeWidth(f);
            RectF rectF = this.frame;
            float f2 = f / 2.0f;
            int i2 = this.padding.left;
            float f3 = min - f2;
            rectF.set(((float) i2) + f2, f2, ((float) i2) + f3, f3);
            this.batteryPaint.setColor(batteryColorForLevel(this.batteryLevel));
            if (this.charging) {
                this.boltPaint.setColor(this.chargeColor);
                RectF rectF2 = this.frame;
                float width2 = rectF2.left + (rectF2.width() / 3.0f);
                RectF rectF3 = this.frame;
                float height2 = rectF3.top + (rectF3.height() / 3.4f);
                RectF rectF4 = this.frame;
                float width3 = rectF4.right - (rectF4.width() / 4.0f);
                RectF rectF5 = this.frame;
                float height3 = rectF5.bottom - (rectF5.height() / 5.6f);
                RectF rectF6 = this.boltFrame;
                if (!(rectF6.left == width2 && rectF6.top == height2 && rectF6.right == width3 && rectF6.bottom == height3)) {
                    this.boltFrame.set(width2, height2, width3, height3);
                    this.boltPath.reset();
                    Path path = this.boltPath;
                    RectF rectF7 = this.boltFrame;
                    RectF rectF8 = this.boltFrame;
                    path.moveTo(rectF7.left + (this.boltPoints[0] * rectF7.width()), rectF8.top + (this.boltPoints[1] * rectF8.height()));
                    int i3 = 2;
                    while (true) {
                        fArr = this.boltPoints;
                        if (i3 >= fArr.length) {
                            break;
                        }
                        Path path2 = this.boltPath;
                        RectF rectF9 = this.boltFrame;
                        float width4 = rectF9.left + (fArr[i3] * rectF9.width());
                        RectF rectF10 = this.boltFrame;
                        path2.lineTo(width4, rectF10.top + (this.boltPoints[i3 + 1] * rectF10.height()));
                        i3 += 2;
                    }
                    Path path3 = this.boltPath;
                    RectF rectF11 = this.boltFrame;
                    float width5 = rectF11.left + (fArr[0] * rectF11.width());
                    RectF rectF12 = this.boltFrame;
                    path3.lineTo(width5, rectF12.top + (this.boltPoints[1] * rectF12.height()));
                }
                canvas.drawPath(this.boltPath, this.boltPaint);
            }
            canvas.drawArc(this.frame, 270.0f, 360.0f, false, this.framePaint);
            int i4 = this.batteryLevel;
            if (i4 > 0) {
                if (this.charging || !this.powerSaveEnabled) {
                    canvas.drawArc(this.frame, 270.0f, ((float) this.batteryLevel) * 3.6f, false, this.batteryPaint);
                } else {
                    float f4 = ((float) i4) * 3.6f;
                    canvas.drawArc(this.frame, 270.0f, f4, false, this.powerSavePaint);
                }
            }
            if (!this.charging && (i = this.batteryLevel) != 100 && this.showPercent) {
                this.textPaint.setColor(getColorForLevel(i));
                this.textPaint.setTextSize(((float) this.height) * 0.52f);
                float f5 = -this.textPaint.getFontMetrics().ascent;
                if (this.batteryLevel > getCriticalLevel()) {
                    str = String.valueOf(this.batteryLevel);
                } else {
                    str = this.warningString;
                }
                canvas.drawText(str, ((float) this.width) * 0.5f, (((float) this.height) + f5) * 0.47f, this.textPaint);
            }
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.framePaint.setColorFilter(colorFilter);
        this.batteryPaint.setColorFilter(colorFilter);
        this.warningTextPaint.setColorFilter(colorFilter);
        this.boltPaint.setColorFilter(colorFilter);
        this.plusPaint.setColorFilter(colorFilter);
    }

    /* compiled from: CircleBatteryDrawable.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* access modifiers changed from: private */
        public final float[] loadPoints(Resources resources, int i) {
            int[] intArray = resources.getIntArray(i);
            int i2 = 0;
            int i3 = 0;
            for (int i4 = 0; i4 < intArray.length; i4 += 2) {
                i2 = Math.max(i2, intArray[i4]);
                i3 = Math.max(i3, intArray[i4 + 1]);
            }
            float[] fArr = new float[intArray.length];
            for (int i5 = 0; i5 < intArray.length; i5 += 2) {
                fArr[i5] = ((float) intArray[i5]) / ((float) i2);
                int i6 = i5 + 1;
                fArr[i6] = ((float) intArray[i6]) / ((float) i3);
            }
            return fArr;
        }
    }
}
