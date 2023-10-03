package p000;

import android.content.Context;
import android.text.format.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/* renamed from: ctk */
/* compiled from: PG */
public final class ctk {

    /* renamed from: a */
    public final Context f5629a;

    /* renamed from: b */
    public final Locale f5630b;

    /* renamed from: c */
    public final SimpleDateFormat f5631c = new SimpleDateFormat(DateFormat.getBestDateTimePattern(this.f5630b, ehc.MONTH_DAY_YEAR_FORMAT.f8270h), this.f5630b);

    /* renamed from: d */
    public final SimpleDateFormat f5632d = new SimpleDateFormat(DateFormat.getBestDateTimePattern(this.f5630b, ehc.DAY_TIME_FORMAT.f8270h), this.f5630b);

    public ctk(Context context, cwg cwg) {
        this.f5629a = context;
        this.f5630b = cwg.mo3859a();
    }
}
