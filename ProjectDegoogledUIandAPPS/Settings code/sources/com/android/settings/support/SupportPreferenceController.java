package com.android.settings.support;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import androidx.preference.Preference;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.overlay.SupportFeatureProvider;

public class SupportPreferenceController extends BasePreferenceController {
    private Activity mActivity;
    private final SupportFeatureProvider mSupportFeatureProvider;

    public SupportPreferenceController(Context context, String str) {
        super(context, str);
        this.mSupportFeatureProvider = FeatureFactory.getFactory(context).getSupportFeatureProvider(context);
    }

    public void setActivity(Activity activity) {
        this.mActivity = activity;
    }

    public int getAvailabilityStatus() {
        return this.mSupportFeatureProvider == null ? 3 : 0;
    }

    public boolean handlePreferenceTreeClick(Preference preference) {
        if (preference == null || this.mActivity == null || !TextUtils.equals(preference.getKey(), getPreferenceKey())) {
            return false;
        }
        this.mSupportFeatureProvider.startSupport(this.mActivity);
        return true;
    }
}
