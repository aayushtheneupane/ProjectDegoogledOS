package p000;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.icu.text.DateFormat;
import android.icu.util.TimeZone;
import android.util.TypedValue;
import android.view.View;
import com.google.android.apps.photosgo.R;
import java.util.Calendar;
import java.util.Locale;
import p003j$.util.DesugarTimeZone;

/* renamed from: ggf */
/* compiled from: PG */
public class ggf {
    private ggf() {
    }

    /* renamed from: a */
    public static /* synthetic */ String m10251a(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "null" : "TYPE_NOT_SET" : "INTENT" : "MEDIA";
    }

    /* renamed from: b */
    public static int m10254b(int i) {
        if (i == 0) {
            return 3;
        }
        int i2 = 1;
        if (i != 1) {
            i2 = 2;
            if (i != 2) {
                return 0;
            }
        }
        return i2;
    }

    /* renamed from: c */
    public static /* synthetic */ String m10256c(int i) {
        return i != 1 ? i != 2 ? "null" : "SECONDARY" : "PRIMARY";
    }

    /* renamed from: d */
    public static /* synthetic */ int m10258d(int i) {
        if (i != 0) {
            return i;
        }
        throw null;
    }

    /* renamed from: a */
    public boolean mo377a(int[] iArr) {
        return false;
    }

    /* renamed from: b */
    public boolean mo378b() {
        return false;
    }

    public /* synthetic */ ggf(byte[] bArr) {
    }

    /* renamed from: b */
    public static int m10255b(Context context, int i) {
        TypedValue a = m10250a(context, i);
        if (a == null) {
            return 0;
        }
        return a.data;
    }

    /* renamed from: a */
    public static int m10245a(Context context, String str) {
        return m10244a(context, (int) R.attr.colorSurface, str);
    }

    /* renamed from: a */
    public static int m10243a(int i, int i2, float f) {
        return C0238ip.m14265a(C0238ip.m14267b(i2, Math.round(((float) Color.alpha(i2)) * f)), i);
    }

    /* renamed from: a */
    public static long m10247a(long j) {
        Calendar e = m10260e();
        e.setTimeInMillis(j);
        return m10252a(e).getTimeInMillis();
    }

    /* renamed from: a */
    public static DateFormat m10249a(String str, Locale locale) {
        DateFormat instanceForSkeleton = DateFormat.getInstanceForSkeleton(str, locale);
        instanceForSkeleton.setTimeZone(TimeZone.getTimeZone("UTC"));
        return instanceForSkeleton;
    }

    /* renamed from: e */
    public static Calendar m10260e() {
        Calendar instance = Calendar.getInstance(m10257c());
        instance.clear();
        return instance;
    }

    /* renamed from: a */
    public static Calendar m10252a(Calendar calendar) {
        Calendar e = m10260e();
        e.set(calendar.get(1), calendar.get(2), calendar.get(5));
        return e;
    }

    /* renamed from: c */
    public static java.util.TimeZone m10257c() {
        return DesugarTimeZone.getTimeZone("UTC");
    }

    /* renamed from: d */
    public static Calendar m10259d() {
        return m10252a(Calendar.getInstance());
    }

    /* renamed from: a */
    public static PorterDuff.Mode m10248a(int i, PorterDuff.Mode mode) {
        if (i == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i) {
            case 14:
                return PorterDuff.Mode.MULTIPLY;
            case 15:
                return PorterDuff.Mode.SCREEN;
            case 16:
                return PorterDuff.Mode.ADD;
            default:
                return mode;
        }
    }

    /* renamed from: a */
    public static TypedValue m10250a(Context context, int i) {
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(i, typedValue, true)) {
            return null;
        }
        return typedValue;
    }

    /* renamed from: a */
    public static boolean m10253a(Context context, int i, boolean z) {
        TypedValue a = m10250a(context, i);
        if (a == null || a.type != 18) {
            return z;
        }
        return a.data != 0;
    }

    /* renamed from: a */
    public static int m10244a(Context context, int i, String str) {
        TypedValue a = m10250a(context, i);
        if (a != null) {
            return a.data;
        }
        throw new IllegalArgumentException(String.format("%1$s requires a value for the %2$s attribute to be set in your app theme. You can either set the attribute in your theme or update your theme to inherit from Theme.MaterialComponents (or a descendant).", new Object[]{str, context.getResources().getResourceName(i)}));
    }

    /* renamed from: a */
    public static int m10246a(View view, int i) {
        return m10244a(view.getContext(), i, view.getClass().getCanonicalName());
    }
}
