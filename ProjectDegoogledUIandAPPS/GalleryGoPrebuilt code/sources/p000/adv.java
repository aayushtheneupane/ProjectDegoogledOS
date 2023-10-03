package p000;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;

/* renamed from: adv */
/* compiled from: PG */
public final class adv {

    /* renamed from: a */
    public final Context f235a;

    /* renamed from: b */
    public PreferenceScreen f236b;

    /* renamed from: c */
    public adu f237c;

    /* renamed from: d */
    public ads f238d;

    /* renamed from: e */
    public adt f239e;

    /* renamed from: f */
    private long f240f = 0;

    /* renamed from: g */
    private SharedPreferences f241g;

    /* renamed from: h */
    private final String f242h;

    public adv(Context context) {
        this.f235a = context;
        this.f242h = context.getPackageName() + "_preferences";
        this.f241g = null;
    }

    /* renamed from: a */
    public final PreferenceScreen mo229a(Context context) {
        PreferenceScreen preferenceScreen = new PreferenceScreen(context, (AttributeSet) null);
        preferenceScreen.mo1171a(this);
        return preferenceScreen;
    }

    /* renamed from: a */
    public final Preference mo228a(CharSequence charSequence) {
        PreferenceScreen preferenceScreen = this.f236b;
        if (preferenceScreen != null) {
            return preferenceScreen.mo1201c(charSequence);
        }
        return null;
    }

    /* renamed from: c */
    public final SharedPreferences.Editor mo231c() {
        return mo230b().edit();
    }

    /* renamed from: a */
    public final long mo227a() {
        long j;
        synchronized (this) {
            j = this.f240f;
            this.f240f = 1 + j;
        }
        return j;
    }

    /* renamed from: b */
    public final SharedPreferences mo230b() {
        if (this.f241g == null) {
            this.f241g = this.f235a.getSharedPreferences(this.f242h, 0);
        }
        return this.f241g;
    }
}
