package com.android.settings.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.icu.text.DecimalFormatSymbols;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.util.AttributeSet;
import android.view.View;
import com.android.settings.R$styleable;
import com.android.settingslib.Utils;
import com.havoc.config.center.C1715R;

public class DonutView extends View {
    private Paint mBackgroundCircle;
    private TextPaint mBigNumberPaint;
    private Paint mFilledArc;
    private String mFullString;
    private int mMeterBackgroundColor;
    private int mMeterConsumedColor;
    private double mPercent;
    private String mPercentString;
    private boolean mShowPercentString = true;
    private float mStrokeWidth;
    private TextPaint mTextPaint;

    public DonutView(Context context) {
        super(context);
    }

    public DonutView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        boolean z;
        this.mMeterBackgroundColor = context.getColor(C1715R.C1716color.meter_background_color);
        this.mMeterConsumedColor = Utils.getColorStateListDefaultColor(this.mContext, C1715R.C1716color.meter_consumed_color);
        Resources resources = context.getResources();
        this.mStrokeWidth = resources.getDimension(C1715R.dimen.storage_donut_thickness);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.DonutView);
            this.mMeterBackgroundColor = obtainStyledAttributes.getColor(1, this.mMeterBackgroundColor);
            this.mMeterConsumedColor = obtainStyledAttributes.getColor(2, this.mMeterConsumedColor);
            z = obtainStyledAttributes.getBoolean(0, true);
            this.mShowPercentString = obtainStyledAttributes.getBoolean(3, true);
            this.mStrokeWidth = (float) obtainStyledAttributes.getDimensionPixelSize(4, (int) this.mStrokeWidth);
            obtainStyledAttributes.recycle();
        } else {
            z = true;
        }
        this.mBackgroundCircle = new Paint();
        this.mBackgroundCircle.setAntiAlias(true);
        this.mBackgroundCircle.setStrokeCap(Paint.Cap.BUTT);
        this.mBackgroundCircle.setStyle(Paint.Style.STROKE);
        this.mBackgroundCircle.setStrokeWidth(this.mStrokeWidth);
        this.mBackgroundCircle.setColor(this.mMeterBackgroundColor);
        this.mFilledArc = new Paint();
        this.mFilledArc.setAntiAlias(true);
        this.mFilledArc.setStrokeCap(Paint.Cap.BUTT);
        this.mFilledArc.setStyle(Paint.Style.STROKE);
        this.mFilledArc.setStrokeWidth(this.mStrokeWidth);
        this.mFilledArc.setColor(this.mMeterConsumedColor);
        if (z) {
            PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(Utils.getColorAttrDefaultColor(context, 16843829), PorterDuff.Mode.SRC_IN);
            this.mBackgroundCircle.setColorFilter(porterDuffColorFilter);
            this.mFilledArc.setColorFilter(porterDuffColorFilter);
        }
        int i = TextUtils.getLayoutDirectionFromLocale(resources.getConfiguration().locale) == 0 ? 0 : 1;
        this.mTextPaint = new TextPaint();
        this.mTextPaint.setColor(Utils.getColorAccentDefaultColor(getContext()));
        this.mTextPaint.setAntiAlias(true);
        this.mTextPaint.setTextSize(resources.getDimension(C1715R.dimen.storage_donut_view_label_text_size));
        this.mTextPaint.setTextAlign(Paint.Align.CENTER);
        this.mTextPaint.setBidiFlags(i);
        this.mBigNumberPaint = new TextPaint();
        this.mBigNumberPaint.setColor(Utils.getColorAccentDefaultColor(getContext()));
        this.mBigNumberPaint.setAntiAlias(true);
        this.mBigNumberPaint.setTextSize(resources.getDimension(C1715R.dimen.storage_donut_view_percent_text_size));
        this.mBigNumberPaint.setTypeface(Typeface.create(context.getString(17039781), 0));
        this.mBigNumberPaint.setBidiFlags(i);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawDonut(canvas);
        if (this.mShowPercentString) {
            drawInnerText(canvas);
        }
    }

    private void drawDonut(Canvas canvas) {
        float f = this.mStrokeWidth;
        Canvas canvas2 = canvas;
        canvas2.drawArc(f + 0.0f, f + 0.0f, ((float) getWidth()) - this.mStrokeWidth, ((float) getHeight()) - this.mStrokeWidth, -90.0f, 360.0f, false, this.mBackgroundCircle);
        float f2 = this.mStrokeWidth;
        canvas2.drawArc(f2 + 0.0f, f2 + 0.0f, ((float) getWidth()) - this.mStrokeWidth, ((float) getHeight()) - this.mStrokeWidth, -90.0f, ((float) this.mPercent) * 360.0f, false, this.mFilledArc);
    }

    private void drawInnerText(Canvas canvas) {
        Canvas canvas2 = canvas;
        float textHeight = getTextHeight(this.mTextPaint) + getTextHeight(this.mBigNumberPaint);
        float height = ((float) (getHeight() / 2)) + (textHeight / 2.0f);
        String percentString = new DecimalFormatSymbols().getPercentString();
        canvas.save();
        StaticLayout staticLayout = new StaticLayout(getPercentageStringSpannable(getResources(), this.mPercentString, percentString), this.mBigNumberPaint, getWidth(), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        canvas2.translate(0.0f, (((float) getHeight()) - textHeight) / 2.0f);
        staticLayout.draw(canvas2);
        canvas.restore();
        canvas2.drawText(this.mFullString, (float) (getWidth() / 2), height - this.mTextPaint.descent(), this.mTextPaint);
    }

    public void setPercentage(double d) {
        this.mPercent = d;
        this.mPercentString = Utils.formatPercentage(this.mPercent);
        this.mFullString = getContext().getString(C1715R.string.storage_percent_full);
        if (this.mFullString.length() > 10) {
            this.mTextPaint.setTextSize(getContext().getResources().getDimension(C1715R.dimen.storage_donut_view_shrunken_label_text_size));
        }
        setContentDescription(getContext().getString(C1715R.string.join_two_unrelated_items, new Object[]{this.mPercentString, this.mFullString}));
        invalidate();
    }

    public int getMeterBackgroundColor() {
        return this.mMeterBackgroundColor;
    }

    public void setMeterBackgroundColor(int i) {
        this.mMeterBackgroundColor = i;
        this.mBackgroundCircle.setColor(i);
        invalidate();
    }

    public int getMeterConsumedColor() {
        return this.mMeterConsumedColor;
    }

    public void setMeterConsumedColor(int i) {
        this.mMeterConsumedColor = i;
        this.mFilledArc.setColor(i);
        invalidate();
    }

    static Spannable getPercentageStringSpannable(Resources resources, String str, String str2) {
        float dimension = resources.getDimension(C1715R.dimen.storage_donut_view_percent_sign_size) / resources.getDimension(C1715R.dimen.storage_donut_view_percent_text_size);
        SpannableString spannableString = new SpannableString(str);
        int indexOf = str.indexOf(str2);
        int length = str2.length() + indexOf;
        if (indexOf < 0) {
            indexOf = 0;
            length = str.length();
        }
        spannableString.setSpan(new RelativeSizeSpan(dimension), indexOf, length, 34);
        return spannableString;
    }

    private float getTextHeight(TextPaint textPaint) {
        return textPaint.descent() - textPaint.ascent();
    }
}
