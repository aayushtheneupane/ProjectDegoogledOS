package com.android.settings.applications;

import android.app.Activity;
import android.os.Bundle;
import android.util.IconDrawableFactory;
import android.util.Log;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.android.settings.widget.EntityHeaderController;
import com.android.settingslib.applications.AppUtils;

public abstract class AppInfoWithHeader extends AppInfoBase {
    private boolean mCreated;

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (this.mCreated) {
            Log.w("AppInfoWithHeader", "onActivityCreated: ignoring duplicate call");
            return;
        }
        this.mCreated = true;
        if (this.mPackageInfo != null) {
            FragmentActivity activity = getActivity();
            getPreferenceScreen().addPreference(EntityHeaderController.newInstance(activity, this, (View) null).setRecyclerView(getListView(), getSettingsLifecycle()).setIcon(IconDrawableFactory.newInstance(getContext()).getBadgedIcon(this.mPackageInfo.applicationInfo)).setLabel(this.mPackageInfo.applicationInfo.loadLabel(this.mPm)).setSummary(this.mPackageInfo).setIsInstantApp(AppUtils.isInstant(this.mPackageInfo.applicationInfo)).setPackageName(this.mPackageName).setUid(this.mPackageInfo.applicationInfo.uid).setHasAppInfoLink(true).setButtonActions(0, 0).done((Activity) activity, getPrefContext()));
        }
    }
}
