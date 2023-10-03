package com.android.settings.accessibility;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityManager;
import androidx.preference.Preference;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.havoc.config.center.C1715R;
import java.util.Arrays;
import java.util.List;

public final class MagnificationPreferenceFragment extends DashboardFragment {
    static final int OFF = 0;

    /* renamed from: ON */
    static final int f21ON = 1;
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.accessibility_magnification_settings;
            return Arrays.asList(new SearchIndexableResource[]{searchIndexableResource});
        }

        /* access modifiers changed from: protected */
        public boolean isPageSearchEnabled(Context context) {
            return MagnificationPreferenceFragment.isApplicable(context.getResources());
        }
    };
    private boolean mLaunchedFromSuw = false;

    public int getHelpResource() {
        return C1715R.string.help_url_magnification;
    }

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "MagnificationPreferenceFragment";
    }

    public int getMetricsCategory() {
        return 922;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.accessibility_magnification_settings;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey("from_suw")) {
            this.mLaunchedFromSuw = arguments.getBoolean("from_suw");
        }
        ((MagnificationGesturesPreferenceController) use(MagnificationGesturesPreferenceController.class)).setIsFromSUW(this.mLaunchedFromSuw);
        ((MagnificationNavbarPreferenceController) use(MagnificationNavbarPreferenceController.class)).setIsFromSUW(this.mLaunchedFromSuw);
    }

    public boolean onPreferenceTreeClick(Preference preference) {
        if (this.mLaunchedFromSuw) {
            preference.setFragment(ToggleScreenMagnificationPreferenceFragmentForSetupWizard.class.getName());
            Bundle extras = preference.getExtras();
            extras.putInt("help_uri_resource", 0);
            extras.putBoolean("need_search_icon_in_action_bar", false);
        }
        return super.onPreferenceTreeClick(preference);
    }

    static CharSequence getConfigurationWarningStringForSecureSettingsKey(String str, Context context) {
        if (!"accessibility_display_magnification_navbar_enabled".equals(str) || Settings.Secure.getInt(context.getContentResolver(), "accessibility_display_magnification_navbar_enabled", 0) == 0) {
            return null;
        }
        AccessibilityManager accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
        String string = Settings.Secure.getString(context.getContentResolver(), "accessibility_button_target_component");
        if (!TextUtils.isEmpty(string) && !"com.android.server.accessibility.MagnificationController".equals(string)) {
            ComponentName unflattenFromString = ComponentName.unflattenFromString(string);
            List<AccessibilityServiceInfo> enabledAccessibilityServiceList = accessibilityManager.getEnabledAccessibilityServiceList(-1);
            int size = enabledAccessibilityServiceList.size();
            for (int i = 0; i < size; i++) {
                AccessibilityServiceInfo accessibilityServiceInfo = enabledAccessibilityServiceList.get(i);
                if (accessibilityServiceInfo.getComponentName().equals(unflattenFromString)) {
                    return context.getString(isGestureNavigateEnabled(context) ? C1715R.string.accessibility_screen_magnification_gesture_navigation_warning : C1715R.string.accessibility_screen_magnification_navbar_configuration_warning, new Object[]{accessibilityServiceInfo.getResolveInfo().loadLabel(context.getPackageManager())});
                }
            }
        }
        return null;
    }

    static boolean isChecked(ContentResolver contentResolver, String str) {
        return Settings.Secure.getInt(contentResolver, str, 0) == 1;
    }

    static boolean setChecked(ContentResolver contentResolver, String str, boolean z) {
        return Settings.Secure.putInt(contentResolver, str, z ? 1 : 0);
    }

    static boolean isApplicable(Resources resources) {
        return resources.getBoolean(17891544);
    }

    private static boolean isGestureNavigateEnabled(Context context) {
        return context.getResources().getInteger(17694869) == 2;
    }
}
