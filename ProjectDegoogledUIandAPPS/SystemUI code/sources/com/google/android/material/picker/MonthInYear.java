package com.google.android.material.picker;

import java.util.Arrays;
import java.util.Calendar;

public class MonthInYear {
    public final int daysInMonth = this.monthInYear.getActualMaximum(5);
    public final int daysInWeek = this.monthInYear.getMaximum(7);
    public final int month;
    private final Calendar monthInYear;
    public final int year;

    private MonthInYear(Calendar calendar) {
        this.monthInYear = calendar;
        this.monthInYear.set(5, 1);
        this.month = calendar.get(2);
        this.year = calendar.get(1);
    }

    public static MonthInYear create(int i, int i2) {
        Calendar instance = Calendar.getInstance();
        instance.set(1, i);
        instance.set(2, i2);
        return new MonthInYear(instance);
    }

    public int daysFromStartOfWeekToFirstOfMonth() {
        int firstDayOfWeek = this.monthInYear.get(7) - this.monthInYear.getFirstDayOfWeek();
        return firstDayOfWeek < 0 ? firstDayOfWeek + this.daysInWeek : firstDayOfWeek;
    }

    public Calendar getDay(int i) {
        Calendar calendar = (Calendar) this.monthInYear.clone();
        calendar.set(5, i);
        return calendar;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MonthInYear)) {
            return false;
        }
        MonthInYear monthInYear2 = (MonthInYear) obj;
        if (this.month == monthInYear2.month && this.year == monthInYear2.year) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.month), Integer.valueOf(this.year)});
    }
}
