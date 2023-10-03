package com.android.settings.notification;

import android.content.Context;
import android.content.IntentFilter;
import android.os.UserHandle;
import androidx.preference.Preference;
import com.android.settings.accounts.AccountRestrictionHelper;
import com.android.settings.core.SliderPreferenceController;
import com.android.settingslib.RestrictedPreference;

public abstract class AdjustVolumeRestrictedPreferenceController extends SliderPreferenceController {
    private AccountRestrictionHelper mHelper;

    public AdjustVolumeRestrictedPreferenceController(Context context, String str) {
        this(context, new AccountRestrictionHelper(context), str);
    }

    AdjustVolumeRestrictedPreferenceController(Context context, AccountRestrictionHelper accountRestrictionHelper, String str) {
        super(context, str);
        this.mHelper = accountRestrictionHelper;
    }

    public void updateState(Preference preference) {
        if (preference instanceof RestrictedPreference) {
            this.mHelper.enforceRestrictionOnPreference((RestrictedPreference) preference, "no_adjust_volume", UserHandle.myUserId());
        }
    }

    public IntentFilter getIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.media.VOLUME_CHANGED_ACTION");
        intentFilter.addAction("android.media.STREAM_MUTE_CHANGED_ACTION");
        intentFilter.addAction("android.media.MASTER_MUTE_CHANGED_ACTION");
        return intentFilter;
    }
}
