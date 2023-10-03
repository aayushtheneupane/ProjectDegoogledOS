package com.android.settings.nfc;

import android.content.Context;
import android.os.UserHandle;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.RestrictedPreference;
import com.havoc.config.center.C1715R;

public class AndroidBeamEnabler extends BaseNfcEnabler {
    private final boolean mBeamDisallowedBySystem;
    private final RestrictedPreference mPreference;

    public AndroidBeamEnabler(Context context, RestrictedPreference restrictedPreference) {
        super(context);
        this.mPreference = restrictedPreference;
        this.mBeamDisallowedBySystem = RestrictedLockUtilsInternal.hasBaseUserRestriction(context, "no_outgoing_beam", UserHandle.myUserId());
        if (!isNfcAvailable()) {
            this.mPreference.setEnabled(false);
        } else if (this.mBeamDisallowedBySystem) {
            this.mPreference.setEnabled(false);
        }
    }

    /* access modifiers changed from: protected */
    public void handleNfcStateChanged(int i) {
        if (i == 1) {
            this.mPreference.setEnabled(false);
            this.mPreference.setSummary((int) C1715R.string.nfc_disabled_summary);
        } else if (i == 2) {
            this.mPreference.setEnabled(false);
        } else if (i == 3) {
            if (this.mBeamDisallowedBySystem) {
                this.mPreference.setDisabledByAdmin((RestrictedLockUtils.EnforcedAdmin) null);
                this.mPreference.setEnabled(false);
            } else {
                this.mPreference.checkRestrictionAndSetDisabled("no_outgoing_beam");
            }
            if (!this.mNfcAdapter.isNdefPushEnabled() || !this.mPreference.isEnabled()) {
                this.mPreference.setSummary((int) C1715R.string.android_beam_off_summary);
            } else {
                this.mPreference.setSummary((int) C1715R.string.android_beam_on_summary);
            }
        } else if (i == 4) {
            this.mPreference.setEnabled(false);
        }
    }
}
