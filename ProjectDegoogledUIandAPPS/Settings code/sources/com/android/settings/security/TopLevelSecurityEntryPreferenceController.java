package com.android.settings.security;

import android.content.Context;
import android.hardware.face.FaceManager;
import android.hardware.fingerprint.FingerprintManager;
import com.android.settings.Utils;
import com.android.settings.core.BasePreferenceController;
import com.havoc.config.center.C1715R;

public class TopLevelSecurityEntryPreferenceController extends BasePreferenceController {
    public int getAvailabilityStatus() {
        return 1;
    }

    public TopLevelSecurityEntryPreferenceController(Context context, String str) {
        super(context, str);
    }

    public CharSequence getSummary() {
        FingerprintManager fingerprintManagerOrNull = Utils.getFingerprintManagerOrNull(this.mContext);
        FaceManager faceManagerOrNull = Utils.getFaceManagerOrNull(this.mContext);
        if (faceManagerOrNull != null && faceManagerOrNull.isHardwareDetected()) {
            return this.mContext.getText(C1715R.string.security_dashboard_summary_face);
        }
        if (fingerprintManagerOrNull == null || !fingerprintManagerOrNull.isHardwareDetected()) {
            return this.mContext.getText(C1715R.string.security_dashboard_summary_no_fingerprint);
        }
        return this.mContext.getText(C1715R.string.security_dashboard_summary);
    }
}
