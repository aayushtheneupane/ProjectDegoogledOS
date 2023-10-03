package p000;

import android.content.Context;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/* renamed from: ehd */
/* compiled from: PG */
public final class ehd {

    /* renamed from: a */
    public final Calendar f8271a;

    /* renamed from: b */
    public final Context f8272b;

    /* renamed from: c */
    public final Locale f8273c;

    /* renamed from: d */
    public String f8274d;

    /* renamed from: e */
    public String f8275e;

    /* renamed from: f */
    public DateFormat f8276f;

    /* renamed from: g */
    public DateFormat f8277g;

    /* renamed from: h */
    public DateFormat f8278h;

    /* renamed from: i */
    public DateFormat f8279i;

    /* renamed from: j */
    public DateFormat f8280j;

    public ehd(Context context, Calendar calendar, cwg cwg) {
        this.f8271a = calendar;
        this.f8272b = context;
        this.f8273c = cwg.mo3859a();
    }

    /* renamed from: a */
    public static DateFormat m7468a(ehc ehc, Locale locale) {
        String bestDateTimePattern = android.text.format.DateFormat.getBestDateTimePattern(locale, ehc.f8270h);
        if (locale.getLanguage().equals("ru") && bestDateTimePattern.indexOf("EEEE") == 0) {
            bestDateTimePattern = bestDateTimePattern.replace("EEEE", "cccc");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(bestDateTimePattern, locale);
        simpleDateFormat.setTimeZone(ehe.f8281a);
        return simpleDateFormat;
    }
}
