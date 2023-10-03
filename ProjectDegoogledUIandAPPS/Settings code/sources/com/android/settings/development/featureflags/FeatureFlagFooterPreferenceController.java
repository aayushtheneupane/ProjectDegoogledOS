package com.android.settings.development.featureflags;

import android.content.Context;
import com.android.settings.core.BasePreferenceController;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.widget.FooterPreferenceMixinCompat;
import com.havoc.config.center.C1715R;

public class FeatureFlagFooterPreferenceController extends BasePreferenceController implements LifecycleObserver, OnStart {
    private FooterPreferenceMixinCompat mFooterMixin;

    public int getAvailabilityStatus() {
        return 0;
    }

    public FeatureFlagFooterPreferenceController(Context context) {
        super(context, "feature_flag_footer_pref");
    }

    public void setFooterMixin(FooterPreferenceMixinCompat footerPreferenceMixinCompat) {
        this.mFooterMixin = footerPreferenceMixinCompat;
    }

    public void onStart() {
        this.mFooterMixin.createFooterPreference().setTitle((int) C1715R.string.experimental_category_title);
    }
}
