package com.android.settings.accessibility;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import androidx.preference.Preference;
import androidx.recyclerview.widget.RecyclerView;
import com.android.settings.SettingsPreferenceFragment;
import com.google.android.setupdesign.GlifPreferenceLayout;
import com.havoc.config.center.C1715R;

public class AccessibilitySettingsForSetupWizard extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener {
    private Preference mDisplayMagnificationPreference;
    private Preference mScreenReaderPreference;
    private Preference mSelectToSpeakPreference;

    public int getMetricsCategory() {
        return 367;
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        return false;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        GlifPreferenceLayout glifPreferenceLayout = (GlifPreferenceLayout) view;
        glifPreferenceLayout.setDividerInsets(Integer.MAX_VALUE, 0);
        glifPreferenceLayout.setHeaderText((int) C1715R.string.vision_settings_title);
    }

    public RecyclerView onCreateRecyclerView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return ((GlifPreferenceLayout) viewGroup).onCreateRecyclerView(layoutInflater, viewGroup, bundle);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(C1715R.xml.accessibility_settings_for_setup_wizard);
        this.mDisplayMagnificationPreference = findPreference("screen_magnification_preference");
        this.mScreenReaderPreference = findPreference("screen_reader_preference");
        this.mSelectToSpeakPreference = findPreference("select_to_speak_preference");
    }

    public void onResume() {
        super.onResume();
        updateAccessibilityServicePreference(this.mScreenReaderPreference, findService("com.google.android.marvin.talkback", "com.google.android.marvin.talkback.TalkBackService"));
        updateAccessibilityServicePreference(this.mSelectToSpeakPreference, findService("com.google.android.marvin.talkback", "com.google.android.accessibility.selecttospeak.SelectToSpeakService"));
        configureMagnificationPreferenceIfNeeded(this.mDisplayMagnificationPreference);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setHasOptionsMenu(false);
    }

    public boolean onPreferenceTreeClick(Preference preference) {
        Preference preference2 = this.mDisplayMagnificationPreference;
        if (preference2 == preference) {
            preference2.getExtras().putBoolean("from_suw", true);
        }
        return super.onPreferenceTreeClick(preference);
    }

    private AccessibilityServiceInfo findService(String str, String str2) {
        for (AccessibilityServiceInfo next : ((AccessibilityManager) getActivity().getSystemService(AccessibilityManager.class)).getInstalledAccessibilityServiceList()) {
            ServiceInfo serviceInfo = next.getResolveInfo().serviceInfo;
            if (str.equals(serviceInfo.packageName) && str2.equals(serviceInfo.name)) {
                return next;
            }
        }
        return null;
    }

    private void updateAccessibilityServicePreference(Preference preference, AccessibilityServiceInfo accessibilityServiceInfo) {
        if (accessibilityServiceInfo == null) {
            getPreferenceScreen().removePreference(preference);
            return;
        }
        ServiceInfo serviceInfo = accessibilityServiceInfo.getResolveInfo().serviceInfo;
        String charSequence = accessibilityServiceInfo.getResolveInfo().loadLabel(getPackageManager()).toString();
        preference.setTitle((CharSequence) charSequence);
        ComponentName componentName = new ComponentName(serviceInfo.packageName, serviceInfo.name);
        preference.setKey(componentName.flattenToString());
        Bundle extras = preference.getExtras();
        extras.putParcelable("component_name", componentName);
        extras.putString("preference_key", preference.getKey());
        extras.putString("title", charSequence);
        String loadDescription = accessibilityServiceInfo.loadDescription(getPackageManager());
        if (TextUtils.isEmpty(loadDescription)) {
            loadDescription = getString(C1715R.string.accessibility_service_default_description);
        }
        extras.putString("summary", loadDescription);
    }

    private static void configureMagnificationPreferenceIfNeeded(Preference preference) {
        Context context = preference.getContext();
        if (!MagnificationPreferenceFragment.isApplicable(context.getResources())) {
            preference.setFragment(ToggleScreenMagnificationPreferenceFragmentForSetupWizard.class.getName());
            MagnificationGesturesPreferenceController.populateMagnificationGesturesPreferenceExtras(preference.getExtras(), context);
        }
    }
}
