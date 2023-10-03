package com.android.settings.privacy;

import android.content.Context;
import android.text.TextUtils;
import androidx.preference.Preference;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.enterprise.EnterprisePrivacyFeatureProvider;
import com.android.settings.overlay.FeatureFactory;

public class WorkPolicyInfoPreferenceController extends BasePreferenceController {
    private final EnterprisePrivacyFeatureProvider mEnterpriseProvider;

    public WorkPolicyInfoPreferenceController(Context context, String str) {
        super(context, str);
        this.mEnterpriseProvider = FeatureFactory.getFactory(context).getEnterprisePrivacyFeatureProvider(context);
    }

    public int getAvailabilityStatus() {
        return this.mEnterpriseProvider.hasWorkPolicyInfo() ? 1 : 3;
    }

    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!TextUtils.equals(getPreferenceKey(), preference.getKey())) {
            return false;
        }
        this.mEnterpriseProvider.showWorkPolicyInfo();
        return true;
    }
}
