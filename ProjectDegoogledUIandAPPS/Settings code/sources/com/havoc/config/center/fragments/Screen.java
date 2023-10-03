package com.havoc.config.center.fragments;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.Settings;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import com.android.internal.util.havoc.Utils;
import com.android.settings.SettingsPreferenceFragment;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.CustomSeekBarPreference;
import com.havoc.support.preferences.SecureSettingSwitchPreference;

public class Screen extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener {
    private CustomSeekBarPreference mContentPadding;
    private CustomSeekBarPreference mCornerRadius;
    private SecureSettingSwitchPreference mRoundedFwvals;

    public int getMetricsCategory() {
        return 1999;
    }

    public void onCreate(Bundle bundle) {
        Resources resources;
        super.onCreate(bundle);
        addPreferencesFromResource(C1715R.xml.config_center_screen);
        getActivity().getContentResolver();
        Context context = getContext();
        float f = Resources.getSystem().getDisplayMetrics().density;
        try {
            resources = context.getPackageManager().getResourcesForApplication("com.android.systemui");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            resources = null;
        }
        PreferenceCategory preferenceCategory = (PreferenceCategory) getPreferenceScreen().findPreference("cutout_category");
        if (!Utils.hasNotch(getContext())) {
            getPreferenceScreen().removePreference(preferenceCategory);
        }
        this.mCornerRadius = (CustomSeekBarPreference) findPreference("sysui_rounded_size");
        this.mCornerRadius.setValue(Settings.Secure.getIntForUser(context.getContentResolver(), "sysui_rounded_size", (int) (((float) ((int) context.getResources().getDimension(17105408))) / f), -2));
        this.mCornerRadius.setOnPreferenceChangeListener(this);
        this.mContentPadding = (CustomSeekBarPreference) findPreference("sysui_rounded_content_padding");
        this.mContentPadding.setValue(Settings.Secure.getIntForUser(context.getContentResolver(), "sysui_rounded_content_padding", (int) (resources.getDimension(resources.getIdentifier("com.android.systemui:dimen/rounded_corner_content_padding", (String) null, (String) null)) / f), -2));
        this.mContentPadding.setOnPreferenceChangeListener(this);
        this.mRoundedFwvals = (SecureSettingSwitchPreference) findPreference("sysui_rounded_fwvals");
        this.mRoundedFwvals.setOnPreferenceChangeListener(this);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference == this.mCornerRadius) {
            Settings.Secure.putIntForUser(getContext().getContentResolver(), "sysui_rounded_size", ((Integer) obj).intValue(), -2);
            return true;
        } else if (preference == this.mContentPadding) {
            Settings.Secure.putIntForUser(getContext().getContentResolver(), "sysui_rounded_content_padding", ((Integer) obj).intValue(), -2);
            return true;
        } else if (preference != this.mRoundedFwvals) {
            return false;
        } else {
            restoreCorners();
            return true;
        }
    }

    private void restoreCorners() {
        Resources resources;
        float f = Resources.getSystem().getDisplayMetrics().density;
        Context context = getContext();
        try {
            resources = context.getPackageManager().getResourcesForApplication("com.android.systemui");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            resources = null;
        }
        int identifier = resources.getIdentifier("com.android.systemui:dimen/rounded_corner_content_padding", (String) null, (String) null);
        this.mCornerRadius.setValue((int) (((float) ((int) context.getResources().getDimension(17105408))) / f));
        this.mContentPadding.setValue((int) (resources.getDimension(identifier) / f));
    }
}
