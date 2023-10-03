package p000;

import android.content.Context;

/* renamed from: aik */
/* compiled from: PG */
public final class aik extends C0062cf {

    /* renamed from: c */
    private final Context f538c;

    public aik(Context context, int i, int i2) {
        super(i, i2);
        this.f538c = context;
    }

    /* renamed from: a */
    public final void mo519a(C0028az azVar) {
        if (this.f4221b >= 10) {
            azVar.mo1732a("INSERT OR REPLACE INTO `Preference` (`key`, `long_value`) VALUES (@key, @long_value)", new Object[]{"reschedule_needed", 1});
            return;
        }
        this.f538c.getSharedPreferences("androidx.work.util.preferences", 0).edit().putBoolean("reschedule_needed", true).apply();
    }
}
