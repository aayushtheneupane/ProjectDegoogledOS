package com.android.dialer.about;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.text.TextUtils;
import com.android.dialer.R;

public class AboutPhoneFragment extends PreferenceFragment {
    public Context getContext() {
        return getActivity();
    }

    public void onCreate(Bundle bundle) {
        String str;
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.about_phone_fragment);
        findPreference(getString(R.string.open_source_licenses_key)).setIntent(new Intent(getActivity().getApplicationContext(), LicenseMenuActivity.class));
        Preference findPreference = findPreference(getResources().getString(R.string.build_version_key));
        Activity activity = getActivity();
        try {
            str = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            str = "";
        }
        if (!TextUtils.isEmpty(str)) {
            findPreference.setSummary(str);
        }
    }
}
