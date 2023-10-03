package com.android.settings.location;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnPause;
import com.android.settingslib.widget.FooterPreference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class LocationFooterPreferenceController extends LocationBasePreferenceController implements LifecycleObserver, OnPause {
    private static final Intent INJECT_INTENT = new Intent("com.android.settings.location.DISPLAYED_FOOTER");
    private final Context mContext;
    private Collection<ComponentName> mFooterInjectors = new ArrayList();
    private final PackageManager mPackageManager = this.mContext.getPackageManager();

    public String getPreferenceKey() {
        return "location_footer";
    }

    public void onLocationModeChanged(int i, boolean z) {
    }

    public LocationFooterPreferenceController(Context context, Lifecycle lifecycle) {
        super(context, lifecycle);
        this.mContext = context;
        if (lifecycle != null) {
            lifecycle.addObserver(this);
        }
    }

    public void updateState(Preference preference) {
        PreferenceCategory preferenceCategory = (PreferenceCategory) preference;
        preferenceCategory.removeAll();
        this.mFooterInjectors.clear();
        for (FooterData next : getFooterData()) {
            FooterPreference footerPreference = new FooterPreference(preference.getContext());
            try {
                footerPreference.setTitle((CharSequence) this.mPackageManager.getResourcesForApplication(next.applicationInfo).getString(next.footerStringRes));
                preferenceCategory.addPreference(footerPreference);
                sendBroadcastFooterDisplayed(next.componentName);
                this.mFooterInjectors.add(next.componentName);
            } catch (PackageManager.NameNotFoundException unused) {
                Log.w("LocationFooter", "Resources not found for application " + next.applicationInfo.packageName);
            }
        }
    }

    public boolean isAvailable() {
        return !getFooterData().isEmpty();
    }

    public void onPause() {
        for (ComponentName component : this.mFooterInjectors) {
            Intent intent = new Intent("com.android.settings.location.REMOVED_FOOTER");
            intent.setComponent(component);
            this.mContext.sendBroadcast(intent);
        }
    }

    /* access modifiers changed from: package-private */
    public void sendBroadcastFooterDisplayed(ComponentName componentName) {
        Intent intent = new Intent("com.android.settings.location.DISPLAYED_FOOTER");
        intent.setComponent(componentName);
        this.mContext.sendBroadcast(intent);
    }

    private Collection<FooterData> getFooterData() {
        List<ResolveInfo> queryBroadcastReceivers = this.mPackageManager.queryBroadcastReceivers(INJECT_INTENT, 128);
        if (queryBroadcastReceivers == null) {
            Log.e("LocationFooter", "Unable to resolve intent " + INJECT_INTENT);
            return Collections.emptyList();
        }
        if (Log.isLoggable("LocationFooter", 3)) {
            Log.d("LocationFooter", "Found broadcast receivers: " + queryBroadcastReceivers);
        }
        ArrayList arrayList = new ArrayList(queryBroadcastReceivers.size());
        for (ResolveInfo next : queryBroadcastReceivers) {
            ActivityInfo activityInfo = next.activityInfo;
            ApplicationInfo applicationInfo = activityInfo.applicationInfo;
            if ((applicationInfo.flags & 1) == 0) {
                Log.w("LocationFooter", "Ignoring attempt to inject footer from app not in system image: " + next);
            } else if (activityInfo.metaData != null) {
                int i = activityInfo.metaData.getInt("com.android.settings.location.FOOTER_STRING");
                if (i == 0) {
                    Log.w("LocationFooter", "No mapping of integer exists for com.android.settings.location.FOOTER_STRING");
                } else {
                    arrayList.add(new FooterData(i, applicationInfo, new ComponentName(activityInfo.packageName, activityInfo.name)));
                }
            } else if (Log.isLoggable("LocationFooter", 3)) {
                Log.d("LocationFooter", "No METADATA in broadcast receiver " + activityInfo.name);
            }
        }
        return arrayList;
    }

    private static class FooterData {
        final ApplicationInfo applicationInfo;
        final ComponentName componentName;
        final int footerStringRes;

        FooterData(int i, ApplicationInfo applicationInfo2, ComponentName componentName2) {
            this.footerStringRes = i;
            this.applicationInfo = applicationInfo2;
            this.componentName = componentName2;
        }
    }
}
