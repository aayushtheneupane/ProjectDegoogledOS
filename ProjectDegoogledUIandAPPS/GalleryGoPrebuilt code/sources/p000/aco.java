package p000;

import android.text.TextUtils;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import com.google.android.apps.photosgo.R;

/* renamed from: aco */
/* compiled from: PG */
public final class aco implements adc {

    /* renamed from: a */
    public static aco f185a;

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ CharSequence mo163a(Preference preference) {
        ListPreference listPreference = (ListPreference) preference;
        if (TextUtils.isEmpty(listPreference.mo1167g())) {
            return listPreference.f1111j.getString(R.string.not_set);
        }
        return listPreference.mo1167g();
    }
}
