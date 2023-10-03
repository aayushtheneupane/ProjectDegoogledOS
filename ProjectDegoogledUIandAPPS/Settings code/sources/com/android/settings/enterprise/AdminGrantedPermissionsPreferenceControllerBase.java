package com.android.settings.enterprise;

import android.content.Context;
import androidx.preference.Preference;
import com.android.settings.applications.ApplicationFeatureProvider;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.overlay.FeatureFactory;
import com.android.settingslib.core.AbstractPreferenceController;
import com.havoc.config.center.C1715R;

public abstract class AdminGrantedPermissionsPreferenceControllerBase extends AbstractPreferenceController implements PreferenceControllerMixin {
    private final boolean mAsync;
    private final ApplicationFeatureProvider mFeatureProvider;
    private boolean mHasApps = false;
    private final String[] mPermissions;

    public AdminGrantedPermissionsPreferenceControllerBase(Context context, boolean z, String[] strArr) {
        super(context);
        this.mPermissions = strArr;
        this.mFeatureProvider = FeatureFactory.getFactory(context).getApplicationFeatureProvider(context);
        this.mAsync = z;
    }

    public void updateState(Preference preference) {
        this.mFeatureProvider.calculateNumberOfAppsWithAdminGrantedPermissions(this.mPermissions, true, new ApplicationFeatureProvider.NumberOfAppsCallback(preference) {
            private final /* synthetic */ Preference f$1;

            {
                this.f$1 = r2;
            }

            public final void onNumberOfAppsResult(int i) {
                AdminGrantedPermissionsPreferenceControllerBase.this.mo9750x936ad1d3(this.f$1, i);
            }
        });
    }

    /* renamed from: lambda$updateState$0$AdminGrantedPermissionsPreferenceControllerBase */
    public /* synthetic */ void mo9750x936ad1d3(Preference preference, int i) {
        if (i == 0) {
            this.mHasApps = false;
        } else {
            preference.setSummary((CharSequence) this.mContext.getResources().getQuantityString(C1715R.plurals.enterprise_privacy_number_packages_lower_bound, i, new Object[]{Integer.valueOf(i)}));
            this.mHasApps = true;
        }
        preference.setVisible(this.mHasApps);
    }

    public boolean isAvailable() {
        if (this.mAsync) {
            return true;
        }
        Boolean[] boolArr = {null};
        this.mFeatureProvider.calculateNumberOfAppsWithAdminGrantedPermissions(this.mPermissions, false, new ApplicationFeatureProvider.NumberOfAppsCallback(boolArr) {
            private final /* synthetic */ Boolean[] f$0;

            {
                this.f$0 = r1;
            }

            public final void onNumberOfAppsResult(int i) {
                AdminGrantedPermissionsPreferenceControllerBase.lambda$isAvailable$1(this.f$0, i);
            }
        });
        this.mHasApps = boolArr[0].booleanValue();
        return this.mHasApps;
    }

    static /* synthetic */ void lambda$isAvailable$1(Boolean[] boolArr, int i) {
        boolArr[0] = Boolean.valueOf(i > 0);
    }

    public boolean handlePreferenceTreeClick(Preference preference) {
        if (getPreferenceKey().equals(preference.getKey()) && this.mHasApps) {
            return super.handlePreferenceTreeClick(preference);
        }
        return false;
    }
}
