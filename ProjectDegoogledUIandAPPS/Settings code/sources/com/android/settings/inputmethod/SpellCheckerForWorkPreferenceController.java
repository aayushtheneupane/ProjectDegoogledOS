package com.android.settings.inputmethod;

import android.content.Context;
import android.view.inputmethod.InputMethodSystemProperty;
import com.android.settings.core.WorkProfilePreferenceController;
import com.havoc.config.center.C1715R;

public final class SpellCheckerForWorkPreferenceController extends WorkProfilePreferenceController {
    /* access modifiers changed from: protected */
    public int getSourceMetricsCategory() {
        return 750;
    }

    public SpellCheckerForWorkPreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        if (!this.mContext.getResources().getBoolean(C1715R.bool.config_show_spellcheckers_settings) || !InputMethodSystemProperty.PER_PROFILE_IME_ENABLED) {
            return 3;
        }
        return super.getAvailabilityStatus();
    }
}
