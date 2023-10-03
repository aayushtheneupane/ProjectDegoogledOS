package com.android.contacts.datepicker;

import android.animation.LayoutTransition;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import com.android.contacts.R;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;

public class DatePicker extends FrameLayout {
    public static int NO_YEAR;
    private static final TwoDigitFormatter sTwoDigitFormatter = new TwoDigitFormatter();
    /* access modifiers changed from: private */
    public int mDay;
    private final NumberPicker mDayPicker;
    /* access modifiers changed from: private */
    public boolean mHasYear;
    /* access modifiers changed from: private */
    public int mMonth;
    private final NumberPicker mMonthPicker;
    private OnDateChangedListener mOnDateChangedListener;
    private final LinearLayout mPickerContainer;
    /* access modifiers changed from: private */
    public int mYear;
    private boolean mYearOptional;
    private final NumberPicker mYearPicker;
    private final CheckBox mYearToggle;

    public interface OnDateChangedListener {
        void onDateChanged(DatePicker datePicker, int i, int i2, int i3);
    }

    public DatePicker(Context context) {
        this(context, (AttributeSet) null);
    }

    public DatePicker(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DatePicker(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.date_picker, this, true);
        this.mPickerContainer = (LinearLayout) findViewById(R.id.parent);
        this.mDayPicker = (NumberPicker) findViewById(R.id.day);
        this.mDayPicker.setFormatter(sTwoDigitFormatter);
        this.mDayPicker.setOnLongPressUpdateInterval(100);
        this.mDayPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            public void onValueChange(NumberPicker numberPicker, int i, int i2) {
                int unused = DatePicker.this.mDay = i2;
                DatePicker.this.notifyDateChanged();
            }
        });
        this.mMonthPicker = (NumberPicker) findViewById(R.id.month);
        this.mMonthPicker.setFormatter(sTwoDigitFormatter);
        String[] shortMonths = new DateFormatSymbols().getShortMonths();
        if (shortMonths[0].startsWith("1")) {
            int i2 = 0;
            while (i2 < shortMonths.length) {
                int i3 = i2 + 1;
                shortMonths[i2] = String.valueOf(i3);
                i2 = i3;
            }
            this.mMonthPicker.setMinValue(1);
            this.mMonthPicker.setMaxValue(12);
        } else {
            this.mMonthPicker.setMinValue(1);
            this.mMonthPicker.setMaxValue(12);
            this.mMonthPicker.setDisplayedValues(shortMonths);
        }
        this.mMonthPicker.setOnLongPressUpdateInterval(200);
        this.mMonthPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            public void onValueChange(NumberPicker numberPicker, int i, int i2) {
                int unused = DatePicker.this.mMonth = i2 - 1;
                DatePicker.this.adjustMaxDay();
                DatePicker.this.notifyDateChanged();
                DatePicker.this.updateDaySpinner();
            }
        });
        this.mYearPicker = (NumberPicker) findViewById(R.id.year);
        this.mYearPicker.setOnLongPressUpdateInterval(100);
        this.mYearPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            public void onValueChange(NumberPicker numberPicker, int i, int i2) {
                int unused = DatePicker.this.mYear = i2;
                DatePicker.this.adjustMaxDay();
                DatePicker.this.notifyDateChanged();
                DatePicker.this.updateDaySpinner();
            }
        });
        this.mYearPicker.setMinValue(1900);
        this.mYearPicker.setMaxValue(2100);
        this.mYearToggle = (CheckBox) findViewById(R.id.yearToggle);
        this.mYearToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                boolean unused = DatePicker.this.mHasYear = z;
                DatePicker.this.adjustMaxDay();
                DatePicker.this.notifyDateChanged();
                DatePicker.this.updateSpinners();
            }
        });
        Calendar instance = Calendar.getInstance();
        init(instance.get(1), instance.get(2), instance.get(5), (OnDateChangedListener) null);
        reorderPickers();
        this.mPickerContainer.setLayoutTransition(new LayoutTransition());
        if (!isEnabled()) {
            setEnabled(false);
        }
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.mDayPicker.setEnabled(z);
        this.mMonthPicker.setEnabled(z);
        this.mYearPicker.setEnabled(z);
    }

    private void reorderPickers() {
        char[] dateFormatOrder = ICU.getDateFormatOrder(DateFormat.getBestDateTimePattern(Locale.getDefault(), this.mHasYear ? "yyyyMMMdd" : "MMMdd"));
        this.mPickerContainer.removeAllViews();
        for (char c : dateFormatOrder) {
            if (c == 'd') {
                this.mPickerContainer.addView(this.mDayPicker);
            } else if (c == 'M') {
                this.mPickerContainer.addView(this.mMonthPicker);
            } else {
                this.mPickerContainer.addView(this.mYearPicker);
            }
        }
    }

    private int getCurrentYear() {
        return Calendar.getInstance().get(1);
    }

    private static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        private final int mDay;
        private final boolean mHasYear;
        private final int mMonth;
        private final int mYear;
        private final boolean mYearOptional;

        private SavedState(Parcelable parcelable, int i, int i2, int i3, boolean z, boolean z2) {
            super(parcelable);
            this.mYear = i;
            this.mMonth = i2;
            this.mDay = i3;
            this.mHasYear = z;
            this.mYearOptional = z2;
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.mYear = parcel.readInt();
            this.mMonth = parcel.readInt();
            this.mDay = parcel.readInt();
            boolean z = true;
            this.mHasYear = parcel.readInt() != 0;
            this.mYearOptional = parcel.readInt() == 0 ? false : z;
        }

        public int getYear() {
            return this.mYear;
        }

        public int getMonth() {
            return this.mMonth;
        }

        public int getDay() {
            return this.mDay;
        }

        public boolean hasYear() {
            return this.mHasYear;
        }

        public boolean isYearOptional() {
            return this.mYearOptional;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.mYear);
            parcel.writeInt(this.mMonth);
            parcel.writeInt(this.mDay);
            parcel.writeInt(this.mHasYear ? 1 : 0);
            parcel.writeInt(this.mYearOptional ? 1 : 0);
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        return new SavedState(super.onSaveInstanceState(), this.mYear, this.mMonth, this.mDay, this.mHasYear, this.mYearOptional);
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.mYear = savedState.getYear();
        this.mMonth = savedState.getMonth();
        this.mDay = savedState.getDay();
        this.mHasYear = savedState.hasYear();
        this.mYearOptional = savedState.isYearOptional();
        updateSpinners();
    }

    public void init(int i, int i2, int i3, OnDateChangedListener onDateChangedListener) {
        init(i, i2, i3, false, onDateChangedListener);
    }

    public void init(int i, int i2, int i3, boolean z, OnDateChangedListener onDateChangedListener) {
        this.mYear = (!z || i != NO_YEAR) ? i : getCurrentYear();
        this.mMonth = i2;
        this.mDay = i3;
        this.mYearOptional = z;
        boolean z2 = true;
        if (z && i == NO_YEAR) {
            z2 = false;
        }
        this.mHasYear = z2;
        this.mOnDateChangedListener = onDateChangedListener;
        updateSpinners();
    }

    /* access modifiers changed from: private */
    public void updateSpinners() {
        updateDaySpinner();
        this.mYearToggle.setChecked(this.mHasYear);
        int i = 0;
        this.mYearToggle.setVisibility(this.mYearOptional ? 0 : 8);
        this.mYearPicker.setValue(this.mYear);
        NumberPicker numberPicker = this.mYearPicker;
        if (!this.mHasYear) {
            i = 8;
        }
        numberPicker.setVisibility(i);
        this.mMonthPicker.setValue(this.mMonth + 1);
    }

    /* access modifiers changed from: private */
    public void updateDaySpinner() {
        Calendar instance = Calendar.getInstance();
        instance.set(this.mHasYear ? this.mYear : 2000, this.mMonth, 1);
        int actualMaximum = instance.getActualMaximum(5);
        this.mDayPicker.setMinValue(1);
        this.mDayPicker.setMaxValue(actualMaximum);
        this.mDayPicker.setValue(this.mDay);
    }

    public int getYear() {
        return (!this.mYearOptional || this.mHasYear) ? this.mYear : NO_YEAR;
    }

    public boolean isYearOptional() {
        return this.mYearOptional;
    }

    public int getMonth() {
        return this.mMonth;
    }

    public int getDayOfMonth() {
        return this.mDay;
    }

    /* access modifiers changed from: private */
    public void adjustMaxDay() {
        Calendar instance = Calendar.getInstance();
        instance.set(1, this.mHasYear ? this.mYear : 2000);
        instance.set(2, this.mMonth);
        int actualMaximum = instance.getActualMaximum(5);
        if (this.mDay > actualMaximum) {
            this.mDay = actualMaximum;
        }
    }

    /* access modifiers changed from: private */
    public void notifyDateChanged() {
        if (this.mOnDateChangedListener != null) {
            this.mOnDateChangedListener.onDateChanged(this, (!this.mYearOptional || this.mHasYear) ? this.mYear : NO_YEAR, this.mMonth, this.mDay);
        }
    }
}
