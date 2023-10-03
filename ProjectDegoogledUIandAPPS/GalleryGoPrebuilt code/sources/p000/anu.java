package p000;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import p003j$.util.DesugarTimeZone;

/* renamed from: anu */
/* compiled from: PG */
public final class anu implements ane {

    /* renamed from: a */
    public int f1212a = 0;

    /* renamed from: b */
    public int f1213b = 0;

    /* renamed from: c */
    public int f1214c = 0;

    /* renamed from: d */
    public int f1215d = 0;

    /* renamed from: e */
    public int f1216e = 0;

    /* renamed from: f */
    public int f1217f = 0;

    /* renamed from: g */
    public TimeZone f1218g = DesugarTimeZone.getTimeZone("UTC");

    /* renamed from: h */
    public int f1219h;

    public anu() {
    }

    /* renamed from: a */
    public final int mo1246a() {
        return this.f1212a;
    }

    /* renamed from: b */
    public final int mo1248b() {
        return this.f1213b;
    }

    /* renamed from: c */
    public final int mo1250c() {
        return this.f1214c;
    }

    /* renamed from: d */
    public final int mo1251d() {
        return this.f1215d;
    }

    /* renamed from: e */
    public final int mo1252e() {
        return this.f1216e;
    }

    /* renamed from: f */
    public final int mo1253f() {
        return this.f1217f;
    }

    /* renamed from: g */
    public final int mo1254g() {
        return this.f1219h;
    }

    /* renamed from: h */
    public final TimeZone mo1255h() {
        return this.f1218g;
    }

    public anu(Calendar calendar) {
        Date time = calendar.getTime();
        TimeZone timeZone = calendar.getTimeZone();
        GregorianCalendar gregorianCalendar = (GregorianCalendar) Calendar.getInstance(Locale.US);
        gregorianCalendar.setGregorianChange(new Date(Long.MIN_VALUE));
        gregorianCalendar.setTimeZone(timeZone);
        gregorianCalendar.setTime(time);
        this.f1212a = gregorianCalendar.get(1);
        this.f1213b = gregorianCalendar.get(2) + 1;
        this.f1214c = gregorianCalendar.get(5);
        this.f1215d = gregorianCalendar.get(11);
        this.f1216e = gregorianCalendar.get(12);
        this.f1217f = gregorianCalendar.get(13);
        this.f1219h = gregorianCalendar.get(14) * 1000000;
        this.f1218g = gregorianCalendar.getTimeZone();
    }

    public final int compareTo(Object obj) {
        ane ane = (ane) obj;
        long timeInMillis = mo1256i().getTimeInMillis() - ane.mo1256i().getTimeInMillis();
        if (timeInMillis == 0) {
            timeInMillis = (long) (this.f1219h - ane.mo1254g());
        }
        return (int) (timeInMillis % 2);
    }

    /* renamed from: i */
    public final Calendar mo1256i() {
        GregorianCalendar gregorianCalendar = (GregorianCalendar) Calendar.getInstance(Locale.US);
        gregorianCalendar.setGregorianChange(new Date(Long.MIN_VALUE));
        gregorianCalendar.setTimeZone(this.f1218g);
        gregorianCalendar.set(1, this.f1212a);
        gregorianCalendar.set(2, this.f1213b - 1);
        gregorianCalendar.set(5, this.f1214c);
        gregorianCalendar.set(11, this.f1215d);
        gregorianCalendar.set(12, this.f1216e);
        gregorianCalendar.set(13, this.f1217f);
        gregorianCalendar.set(14, this.f1219h / 1000000);
        return gregorianCalendar;
    }

    /* renamed from: b */
    public final void mo1249b(int i) {
        if (i <= 0) {
            this.f1214c = 1;
        } else if (i > 31) {
            this.f1214c = 31;
        } else {
            this.f1214c = i;
        }
    }

    /* renamed from: a */
    public final void mo1247a(int i) {
        if (i <= 0) {
            this.f1213b = 1;
        } else if (i > 12) {
            this.f1213b = 12;
        } else {
            this.f1213b = i;
        }
    }

    public final String toString() {
        return cdu.m4145a((ane) this);
    }
}
