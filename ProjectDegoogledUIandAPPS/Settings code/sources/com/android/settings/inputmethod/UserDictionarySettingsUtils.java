package com.android.settings.inputmethod;

import android.content.Context;
import android.text.TextUtils;
import com.android.settings.Utils;
import com.havoc.config.center.C1715R;

public class UserDictionarySettingsUtils {
    public static String getLocaleDisplayName(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return context.getResources().getString(C1715R.string.user_dict_settings_all_languages);
        }
        return Utils.createLocaleFromString(str).getDisplayName(context.getResources().getConfiguration().locale);
    }
}
