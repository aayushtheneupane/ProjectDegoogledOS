package p000;

import android.content.Context;
import android.text.TextUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import com.google.android.apps.photosgo.R;
import java.util.ArrayList;
import java.util.List;

/* renamed from: acl */
/* compiled from: PG */
final class acl extends Preference {

    /* renamed from: a */
    private long f183a;

    public acl(Context context, List list, long j) {
        super(context);
        this.f1126y = R.layout.expand_button;
        mo1181c((int) R.drawable.ic_arrow_down_24dp);
        mo1176b((int) R.string.expand_button_title);
        mo1169a(999);
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        CharSequence charSequence = null;
        for (int i = 0; i < size; i++) {
            Preference preference = (Preference) list.get(i);
            CharSequence charSequence2 = preference.f1118q;
            boolean z = preference instanceof PreferenceGroup;
            if (z && !TextUtils.isEmpty(charSequence2)) {
                arrayList.add((PreferenceGroup) preference);
            }
            if (!arrayList.contains(preference.f1089B)) {
                if (!TextUtils.isEmpty(charSequence2)) {
                    if (charSequence != null) {
                        charSequence = this.f1111j.getString(R.string.summary_collapsed_preference_list, new Object[]{charSequence, charSequence2});
                    } else {
                        charSequence = charSequence2;
                    }
                }
            } else if (z) {
                arrayList.add((PreferenceGroup) preference);
            }
        }
        mo1164a(charSequence);
        this.f183a = j + 1000000;
    }

    /* renamed from: e */
    public final long mo170e() {
        return this.f183a;
    }

    /* renamed from: a */
    public final void mo169a(ady ady) {
        super.mo169a(ady);
        ady.f247p = false;
    }
}
