package p000;

import android.os.Parcel;
import android.os.Parcelable;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/* renamed from: geu */
/* compiled from: PG */
final class geu implements Comparable, Parcelable {
    public static final Parcelable.Creator CREATOR = new get();

    /* renamed from: a */
    public final Calendar f11129a;

    /* renamed from: b */
    public final String f11130b;

    /* renamed from: c */
    public final int f11131c;

    /* renamed from: d */
    public final int f11132d = this.f11129a.get(1);

    /* renamed from: e */
    public final int f11133e = this.f11129a.getMaximum(7);

    /* renamed from: f */
    public final int f11134f = this.f11129a.getActualMaximum(5);

    public final int describeContents() {
        return 0;
    }

    public geu(Calendar calendar) {
        calendar.set(5, 1);
        Calendar a = ggf.m10252a(calendar);
        this.f11129a = a;
        this.f11131c = a.get(2);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM, yyyy", Locale.getDefault());
        simpleDateFormat.setTimeZone(ggf.m10257c());
        this.f11130b = simpleDateFormat.format(this.f11129a.getTime());
        this.f11129a.getTimeInMillis();
    }

    /* renamed from: a */
    public final int compareTo(geu geu) {
        return this.f11129a.compareTo(geu.f11129a);
    }

    /* renamed from: a */
    static geu m10177a(int i, int i2) {
        Calendar e = ggf.m10260e();
        e.set(1, i);
        e.set(2, i2);
        return new geu(e);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final int mo6523a() {
        int firstDayOfWeek = this.f11129a.get(7) - this.f11129a.getFirstDayOfWeek();
        return firstDayOfWeek < 0 ? firstDayOfWeek + this.f11133e : firstDayOfWeek;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof geu) {
            geu geu = (geu) obj;
            return this.f11131c == geu.f11131c && this.f11132d == geu.f11132d;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final long mo6525a(int i) {
        Calendar a = ggf.m10252a(this.f11129a);
        a.set(5, i);
        return a.getTimeInMillis();
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f11131c), Integer.valueOf(this.f11132d)});
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final geu mo6527b(int i) {
        Calendar a = ggf.m10252a(this.f11129a);
        a.add(2, i);
        return new geu(a);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final int mo6526b(geu geu) {
        if (this.f11129a instanceof GregorianCalendar) {
            return ((geu.f11132d - this.f11132d) * 12) + (geu.f11131c - this.f11131c);
        }
        throw new IllegalArgumentException("Only Gregorian calendars are supported.");
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f11132d);
        parcel.writeInt(this.f11131c);
    }
}
