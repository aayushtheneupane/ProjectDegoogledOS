package com.android.settings.security.screenlock;

import android.content.Context;
import android.os.UserHandle;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.internal.widget.LockPatternUtils;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.notification.LockScreenNotificationPreferenceController;
import com.android.settings.overlay.FeatureFactory;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnResume;

public class LockScreenPreferenceController extends BasePreferenceController implements LifecycleObserver, OnResume {
    private static final int MY_USER_ID = UserHandle.myUserId();
    private final LockPatternUtils mLockPatternUtils;
    private Preference mPreference;

    public LockScreenPreferenceController(Context context, String str) {
        super(context, str);
        this.mLockPatternUtils = FeatureFactory.getFactory(context).getSecurityFeatureProvider().getLockPatternUtils(context);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = preferenceScreen.findPreference(getPreferenceKey());
    }

    public int getAvailabilityStatus() {
        if (!this.mLockPatternUtils.isSecure(MY_USER_ID)) {
            if (this.mLockPatternUtils.isLockScreenDisabled(MY_USER_ID)) {
                return 4;
            }
            return 1;
        } else if (this.mLockPatternUtils.getKeyguardStoredPasswordQuality(MY_USER_ID) == 0) {
            return 4;
        } else {
            return 1;
        }
    }

    public void updateState(Preference preference) {
        preference.setSummary(LockScreenNotificationPreferenceController.getSummaryResource(this.mContext));
    }

    public void onResume() {
        this.mPreference.setVisible(isAvailable());
    }
}
