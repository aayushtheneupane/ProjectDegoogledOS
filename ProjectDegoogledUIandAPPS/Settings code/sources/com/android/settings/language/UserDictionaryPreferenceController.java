package com.android.settings.language;

import android.content.Context;
import android.os.Bundle;
import androidx.preference.Preference;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.inputmethod.UserDictionaryList;
import com.android.settings.inputmethod.UserDictionaryListPreferenceController;
import com.android.settings.inputmethod.UserDictionarySettings;
import java.util.TreeSet;

public class UserDictionaryPreferenceController extends BasePreferenceController {
    public int getAvailabilityStatus() {
        return 1;
    }

    public UserDictionaryPreferenceController(Context context, String str) {
        super(context, str);
    }

    public void updateState(Preference preference) {
        Class cls;
        if (isAvailable() && preference != null) {
            TreeSet<String> dictionaryLocales = getDictionaryLocales();
            Bundle extras = preference.getExtras();
            if (dictionaryLocales.size() <= 1) {
                if (!dictionaryLocales.isEmpty()) {
                    extras.putString("locale", dictionaryLocales.first());
                }
                cls = UserDictionarySettings.class;
            } else {
                cls = UserDictionaryList.class;
            }
            preference.setFragment(cls.getCanonicalName());
        }
    }

    /* access modifiers changed from: protected */
    public TreeSet<String> getDictionaryLocales() {
        return UserDictionaryListPreferenceController.getUserDictionaryLocalesSet(this.mContext);
    }
}
