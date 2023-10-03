package com.android.settings.language;

import android.content.Context;
import android.view.inputmethod.InputMethodSystemProperty;
import com.android.settings.core.WorkProfilePreferenceController;

public final class UserDictionaryForWorkPreferenceController extends WorkProfilePreferenceController {
    /* access modifiers changed from: protected */
    public int getSourceMetricsCategory() {
        return 750;
    }

    public UserDictionaryForWorkPreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        if (!InputMethodSystemProperty.PER_PROFILE_IME_ENABLED) {
            return 3;
        }
        return super.getAvailabilityStatus();
    }
}
