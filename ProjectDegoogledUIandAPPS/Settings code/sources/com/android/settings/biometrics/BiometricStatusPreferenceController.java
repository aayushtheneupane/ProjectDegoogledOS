package com.android.settings.biometrics;

import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import android.os.UserManager;
import androidx.preference.Preference;
import com.android.internal.widget.LockPatternUtils;
import com.android.settings.Utils;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.overlay.FeatureFactory;

public abstract class BiometricStatusPreferenceController extends BasePreferenceController {
    protected final LockPatternUtils mLockPatternUtils;
    protected final int mProfileChallengeUserId;
    protected final UserManager mUm;
    private final int mUserId = UserHandle.myUserId();

    /* access modifiers changed from: protected */
    public abstract String getEnrollClassName();

    /* access modifiers changed from: protected */
    public abstract String getSettingsClassName();

    /* access modifiers changed from: protected */
    public abstract String getSummaryTextEnrolled();

    /* access modifiers changed from: protected */
    public abstract String getSummaryTextNoneEnrolled();

    /* access modifiers changed from: protected */
    public abstract boolean hasEnrolledBiometrics();

    /* access modifiers changed from: protected */
    public abstract boolean isDeviceSupported();

    /* access modifiers changed from: protected */
    public boolean isUserSupported() {
        return true;
    }

    public BiometricStatusPreferenceController(Context context, String str) {
        super(context, str);
        this.mUm = (UserManager) context.getSystemService("user");
        this.mLockPatternUtils = FeatureFactory.getFactory(context).getSecurityFeatureProvider().getLockPatternUtils(context);
        this.mProfileChallengeUserId = Utils.getManagedProfileId(this.mUm, this.mUserId);
    }

    public int getAvailabilityStatus() {
        if (!isDeviceSupported()) {
            return 3;
        }
        return isUserSupported() ? 0 : 4;
    }

    public void updateState(Preference preference) {
        String str;
        if (isAvailable()) {
            preference.setVisible(true);
            int userId = getUserId();
            if (hasEnrolledBiometrics()) {
                preference.setSummary((CharSequence) getSummaryTextEnrolled());
                str = getSettingsClassName();
            } else {
                preference.setSummary((CharSequence) getSummaryTextNoneEnrolled());
                str = getEnrollClassName();
            }
            preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(userId, str) {
                private final /* synthetic */ int f$0;
                private final /* synthetic */ String f$1;

                {
                    this.f$0 = r1;
                    this.f$1 = r2;
                }

                public final boolean onPreferenceClick(Preference preference) {
                    return BiometricStatusPreferenceController.lambda$updateState$0(this.f$0, this.f$1, preference);
                }
            });
        } else if (preference != null) {
            preference.setVisible(false);
        }
    }

    static /* synthetic */ boolean lambda$updateState$0(int i, String str, Preference preference) {
        Context context = preference.getContext();
        if (Utils.startQuietModeDialogIfNecessary(context, UserManager.get(context), i)) {
            return false;
        }
        Intent intent = new Intent();
        intent.setClassName("com.android.settings", str);
        intent.putExtra("android.intent.extra.USER_ID", i);
        intent.putExtra("from_settings_summary", true);
        context.startActivity(intent);
        return true;
    }

    /* access modifiers changed from: protected */
    public int getUserId() {
        return this.mUserId;
    }
}
