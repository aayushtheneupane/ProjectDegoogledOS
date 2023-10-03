package com.android.settings.applications;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.IntentFilterVerificationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.ArraySet;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.DropDownPreference;
import androidx.preference.Preference;
import com.android.settings.Utils;
import com.havoc.config.center.C1715R;
import java.util.List;

public class AppLaunchSettings extends AppInfoWithHeader implements View.OnClickListener, Preference.OnPreferenceChangeListener {
    private static final Intent sBrowserIntent = new Intent().setAction("android.intent.action.VIEW").addCategory("android.intent.category.BROWSABLE").setData(Uri.parse("http:"));
    private AppDomainsPreference mAppDomainUrls;
    private DropDownPreference mAppLinkState;
    private ClearDefaultsPreference mClearDefaultsPreference;
    private boolean mHasDomainUrls;
    private boolean mIsBrowser;
    private PackageManager mPm;

    private int linkStateToIndex(int i) {
        if (i != 2) {
            return i != 3 ? 1 : 2;
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public AlertDialog createDialog(int i, int i2) {
        return null;
    }

    public int getMetricsCategory() {
        return 17;
    }

    public void onClick(View view) {
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        return true;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(C1715R.xml.installed_app_launch_settings);
        this.mAppDomainUrls = (AppDomainsPreference) findPreference("app_launch_supported_domain_urls");
        this.mClearDefaultsPreference = (ClearDefaultsPreference) findPreference("app_launch_clear_defaults");
        this.mAppLinkState = (DropDownPreference) findPreference("app_link_state");
        this.mPm = getActivity().getPackageManager();
        this.mIsBrowser = isBrowserApp(this.mPackageName);
        this.mHasDomainUrls = (this.mAppEntry.info.privateFlags & 16) != 0;
        if (!this.mIsBrowser) {
            CharSequence[] entries = getEntries(this.mPackageName, this.mPm.getIntentFilterVerifications(this.mPackageName), this.mPm.getAllIntentFilters(this.mPackageName));
            this.mAppDomainUrls.setTitles(entries);
            this.mAppDomainUrls.setValues(new int[entries.length]);
        }
        buildStateDropDown();
    }

    private boolean isBrowserApp(String str) {
        sBrowserIntent.setPackage(str);
        List queryIntentActivitiesAsUser = this.mPm.queryIntentActivitiesAsUser(sBrowserIntent, 131072, UserHandle.myUserId());
        int size = queryIntentActivitiesAsUser.size();
        for (int i = 0; i < size; i++) {
            ResolveInfo resolveInfo = (ResolveInfo) queryIntentActivitiesAsUser.get(i);
            if (resolveInfo.activityInfo != null && resolveInfo.handleAllWebDataURI) {
                return true;
            }
        }
        return false;
    }

    private void buildStateDropDown() {
        if (this.mIsBrowser) {
            this.mAppLinkState.setShouldDisableView(true);
            this.mAppLinkState.setEnabled(false);
            this.mAppDomainUrls.setShouldDisableView(true);
            this.mAppDomainUrls.setEnabled(false);
            return;
        }
        this.mAppLinkState.setEntries(new CharSequence[]{getString(C1715R.string.app_link_open_always), getString(C1715R.string.app_link_open_ask), getString(C1715R.string.app_link_open_never)});
        this.mAppLinkState.setEntryValues(new CharSequence[]{Integer.toString(2), Integer.toString(4), Integer.toString(3)});
        this.mAppLinkState.setEnabled(this.mHasDomainUrls);
        if (this.mHasDomainUrls) {
            this.mAppLinkState.setValueIndex(linkStateToIndex(this.mPm.getIntentVerificationStatusAsUser(this.mPackageName, UserHandle.myUserId())));
            this.mAppLinkState.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                public boolean onPreferenceChange(Preference preference, Object obj) {
                    return AppLaunchSettings.this.updateAppLinkState(Integer.parseInt((String) obj));
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public boolean updateAppLinkState(int i) {
        boolean z = false;
        if (this.mIsBrowser) {
            return false;
        }
        int myUserId = UserHandle.myUserId();
        if (this.mPm.getIntentVerificationStatusAsUser(this.mPackageName, myUserId) == i) {
            return false;
        }
        boolean updateIntentVerificationStatusAsUser = this.mPm.updateIntentVerificationStatusAsUser(this.mPackageName, i, myUserId);
        if (updateIntentVerificationStatusAsUser) {
            if (i == this.mPm.getIntentVerificationStatusAsUser(this.mPackageName, myUserId)) {
                z = true;
            }
            return z;
        }
        Log.e("AppLaunchSettings", "Couldn't update intent verification status!");
        return updateIntentVerificationStatusAsUser;
    }

    private CharSequence[] getEntries(String str, List<IntentFilterVerificationInfo> list, List<IntentFilter> list2) {
        ArraySet<String> handledDomains = Utils.getHandledDomains(this.mPm, str);
        return (CharSequence[]) handledDomains.toArray(new CharSequence[handledDomains.size()]);
    }

    /* access modifiers changed from: protected */
    public boolean refreshUi() {
        this.mClearDefaultsPreference.setPackageName(this.mPackageName);
        this.mClearDefaultsPreference.setAppEntry(this.mAppEntry);
        return true;
    }
}
