package org.apache.james.mime4j.dom.datetime;

import java.util.Date;

public class DateTime {
    private final Date date;
    private final int day;
    private final int hour;
    private final int minute;
    private final int month;
    private final int second;
    private final int timeZone;
    private final int year;

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0012, code lost:
        if (r9 != 3) goto L_0x0020;
     */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0043  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DateTime(java.lang.String r9, int r10, int r11, int r12, int r13, int r14, int r15) {
        /*
            r8 = this;
            r8.<init>()
            int r0 = java.lang.Integer.parseInt(r9)
            int r9 = r9.length()
            r1 = 1
            if (r9 == r1) goto L_0x0015
            r1 = 2
            if (r9 == r1) goto L_0x0015
            r1 = 3
            if (r9 == r1) goto L_0x001e
            goto L_0x0020
        L_0x0015:
            if (r0 < 0) goto L_0x001e
            r9 = 50
            if (r0 >= r9) goto L_0x001e
            int r0 = r0 + 2000
            goto L_0x0020
        L_0x001e:
            int r0 = r0 + 1900
        L_0x0020:
            r8.year = r0
            int r2 = r8.year
            java.util.GregorianCalendar r9 = new java.util.GregorianCalendar
            java.lang.String r0 = "GMT+0"
            java.util.TimeZone r0 = java.util.TimeZone.getTimeZone(r0)
            r9.<init>(r0)
            int r3 = r10 + -1
            r1 = r9
            r4 = r11
            r5 = r12
            r6 = r13
            r7 = r14
            r1.set(r2, r3, r4, r5, r6, r7)
            r0 = 14
            r1 = 0
            r9.set(r0, r1)
            r0 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r15 == r0) goto L_0x0051
            int r0 = r15 / 100
            int r0 = r0 * 60
            int r1 = r15 % 100
            int r1 = r1 + r0
            r0 = 12
            int r1 = r1 * -1
            r9.add(r0, r1)
        L_0x0051:
            java.util.Date r9 = r9.getTime()
            r8.date = r9
            r8.month = r10
            r8.day = r11
            r8.hour = r12
            r8.minute = r13
            r8.second = r14
            r8.timeZone = r15
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.james.mime4j.dom.datetime.DateTime.<init>(java.lang.String, int, int, int, int, int, int):void");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DateTime.class != obj.getClass()) {
            return false;
        }
        DateTime dateTime = (DateTime) obj;
        Date date2 = this.date;
        if (date2 == null) {
            if (dateTime.date != null) {
                return false;
            }
        } else if (!date2.equals(dateTime.date)) {
            return false;
        }
        return this.day == dateTime.day && this.hour == dateTime.hour && this.minute == dateTime.minute && this.month == dateTime.month && this.second == dateTime.second && this.timeZone == dateTime.timeZone && this.year == dateTime.year;
    }

    public Date getDate() {
        return this.date;
    }

    public int hashCode() {
        Date date2 = this.date;
        return (((((((((((((((date2 == null ? 0 : date2.hashCode()) + 31) * 31) + this.day) * 31) + this.hour) * 31) + this.minute) * 31) + this.month) * 31) + this.second) * 31) + this.timeZone) * 31) + this.year;
    }

    public String toString() {
        return this.year + " " + this.month + " " + this.day + "; " + this.hour + " " + this.minute + " " + this.second + " " + this.timeZone;
    }
}
