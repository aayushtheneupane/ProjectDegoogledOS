package com.android.keyguard.clock;

import android.content.Context;
import android.content.res.Resources;
import android.text.Annotation;
import android.text.SpannableString;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.widget.TextView;
import com.android.systemui.C1771R$array;
import com.android.systemui.C1774R$color;
import com.android.systemui.C1782R$plurals;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class TypographicClock extends TextView {
    private int mAccentColor;
    private String mDescFormat;
    private final String[] mHours;
    private final String[] mMinutes;
    private final Resources mResources;
    private final Calendar mTime;
    private TimeZone mTimeZone;

    public boolean hasOverlappingRendering() {
        return false;
    }

    public TypographicClock(Context context) {
        this(context, (AttributeSet) null);
    }

    public TypographicClock(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TypographicClock(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mTime = Calendar.getInstance(TimeZone.getDefault());
        this.mDescFormat = ((SimpleDateFormat) DateFormat.getTimeFormat(context)).toLocalizedPattern();
        this.mResources = context.getResources();
        this.mHours = this.mResources.getStringArray(C1771R$array.type_clock_hours);
        this.mMinutes = this.mResources.getStringArray(C1771R$array.type_clock_minutes);
        this.mAccentColor = this.mResources.getColor(C1774R$color.typeClockAccentColor, (Resources.Theme) null);
    }

    public void onTimeChanged() {
        this.mTime.setTimeInMillis(System.currentTimeMillis());
        setContentDescription(DateFormat.format(this.mDescFormat, this.mTime));
        int i = this.mTime.get(10) % 12;
        int i2 = this.mTime.get(12) % 60;
        SpannedString spannedString = (SpannedString) this.mResources.getQuantityText(C1782R$plurals.type_clock_header, i);
        Annotation[] annotationArr = (Annotation[]) spannedString.getSpans(0, spannedString.length(), Annotation.class);
        SpannableString spannableString = new SpannableString(spannedString);
        for (Annotation annotation : annotationArr) {
            if ("color".equals(annotation.getValue())) {
                spannableString.setSpan(new ForegroundColorSpan(this.mAccentColor), spannableString.getSpanStart(annotation), spannableString.getSpanEnd(annotation), 33);
            }
        }
        setText(TextUtils.expandTemplate(spannableString, new CharSequence[]{this.mHours[i], this.mMinutes[i2]}));
    }

    public void onTimeZoneChanged(TimeZone timeZone) {
        this.mTimeZone = timeZone;
        this.mTime.setTimeZone(timeZone);
    }

    public void setClockColor(int i) {
        this.mAccentColor = i;
        onTimeChanged();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Calendar calendar = this.mTime;
        TimeZone timeZone = this.mTimeZone;
        if (timeZone == null) {
            timeZone = TimeZone.getDefault();
        }
        calendar.setTimeZone(timeZone);
        onTimeChanged();
    }
}
