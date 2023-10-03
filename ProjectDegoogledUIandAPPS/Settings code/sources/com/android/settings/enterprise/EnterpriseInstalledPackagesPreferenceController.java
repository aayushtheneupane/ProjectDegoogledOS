package com.android.settings.enterprise;

import android.content.Context;
import androidx.preference.Preference;
import com.android.settings.applications.ApplicationFeatureProvider;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.overlay.FeatureFactory;
import com.android.settingslib.core.AbstractPreferenceController;
import com.havoc.config.center.C1715R;

public class EnterpriseInstalledPackagesPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin {
    private final boolean mAsync;
    private final ApplicationFeatureProvider mFeatureProvider;

    public String getPreferenceKey() {
        return "number_enterprise_installed_packages";
    }

    public EnterpriseInstalledPackagesPreferenceController(Context context, boolean z) {
        super(context);
        this.mFeatureProvider = FeatureFactory.getFactory(context).getApplicationFeatureProvider(context);
        this.mAsync = z;
    }

    public void updateState(Preference preference) {
        this.mFeatureProvider.calculateNumberOfPolicyInstalledApps(true, new ApplicationFeatureProvider.NumberOfAppsCallback(preference) {
            private final /* synthetic */ Preference f$1;

            {
                this.f$1 = r2;
            }

            public final void onNumberOfAppsResult(int i) {
                EnterpriseInstalledPackagesPreferenceController.this.mo9753x52f59810(this.f$1, i);
            }
        });
    }

    /* renamed from: lambda$updateState$0$EnterpriseInstalledPackagesPreferenceController */
    public /* synthetic */ void mo9753x52f59810(Preference preference, int i) {
        boolean z = false;
        if (i != 0) {
            preference.setSummary((CharSequence) this.mContext.getResources().getQuantityString(C1715R.plurals.enterprise_privacy_number_packages_lower_bound, i, new Object[]{Integer.valueOf(i)}));
            z = true;
        }
        preference.setVisible(z);
    }

    public boolean isAvailable() {
        if (this.mAsync) {
            return true;
        }
        Boolean[] boolArr = {null};
        this.mFeatureProvider.calculateNumberOfPolicyInstalledApps(false, new ApplicationFeatureProvider.NumberOfAppsCallback(boolArr) {
            private final /* synthetic */ Boolean[] f$0;

            {
                this.f$0 = r1;
            }

            public final void onNumberOfAppsResult(int i) {
                EnterpriseInstalledPackagesPreferenceController.lambda$isAvailable$1(this.f$0, i);
            }
        });
        return boolArr[0].booleanValue();
    }

    static /* synthetic */ void lambda$isAvailable$1(Boolean[] boolArr, int i) {
        boolArr[0] = Boolean.valueOf(i > 0);
    }
}
