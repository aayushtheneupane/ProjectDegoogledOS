package com.android.settings.development.featureflags;

import android.content.Context;
import android.util.FeatureFlagUtils;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import java.util.Map;
import java.util.function.Consumer;

public class FeatureFlagsPreferenceController extends BasePreferenceController implements LifecycleObserver, OnStart {
    private PreferenceGroup mGroup;

    public int getAvailabilityStatus() {
        return 0;
    }

    public FeatureFlagsPreferenceController(Context context, String str) {
        super(context, str);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mGroup = (PreferenceGroup) preferenceScreen.findPreference(getPreferenceKey());
    }

    public void onStart() {
        Map allFeatureFlags;
        if (this.mGroup != null && (allFeatureFlags = FeatureFlagUtils.getAllFeatureFlags()) != null) {
            this.mGroup.removeAll();
            allFeatureFlags.keySet().stream().sorted().forEach(new Consumer(this.mGroup.getContext()) {
                private final /* synthetic */ Context f$1;

                {
                    this.f$1 = r2;
                }

                public final void accept(Object obj) {
                    FeatureFlagsPreferenceController.this.lambda$onStart$0$FeatureFlagsPreferenceController(this.f$1, (String) obj);
                }
            });
        }
    }

    public /* synthetic */ void lambda$onStart$0$FeatureFlagsPreferenceController(Context context, String str) {
        this.mGroup.addPreference(new FeatureFlagPreference(context, str));
    }
}
