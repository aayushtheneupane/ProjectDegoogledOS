package p000;

import android.text.TextUtils;
import androidx.preference.Preference;

/* renamed from: adq */
/* compiled from: PG */
final class adq {

    /* renamed from: a */
    public final int f226a;

    /* renamed from: b */
    public final int f227b;

    /* renamed from: c */
    private final String f228c;

    public adq(Preference preference) {
        this.f228c = preference.getClass().getName();
        this.f226a = preference.f1126y;
        this.f227b = preference.f1127z;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof adq) {
            adq adq = (adq) obj;
            if (this.f226a == adq.f226a && this.f227b == adq.f227b && TextUtils.equals(this.f228c, adq.f228c)) {
                return true;
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        return ((((this.f226a + 527) * 31) + this.f227b) * 31) + this.f228c.hashCode();
    }
}
