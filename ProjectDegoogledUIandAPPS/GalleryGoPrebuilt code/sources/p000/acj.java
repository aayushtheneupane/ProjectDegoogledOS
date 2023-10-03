package p000;

import android.text.TextUtils;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import com.google.android.apps.photosgo.R;

/* renamed from: acj */
/* compiled from: PG */
public final class acj implements adc {

    /* renamed from: a */
    public static acj f180a;

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ CharSequence mo163a(Preference preference) {
        EditTextPreference editTextPreference = (EditTextPreference) preference;
        return TextUtils.isEmpty(editTextPreference.f1079g) ? editTextPreference.f1111j.getString(R.string.not_set) : editTextPreference.f1079g;
    }
}
