package com.android.settings.accessibility;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import android.view.accessibility.AccessibilityManager;
import androidx.fragment.app.FragmentActivity;
import com.android.internal.annotations.VisibleForTesting;
import com.android.settings.core.InstrumentedFragment;
import com.android.settings.core.SubSettingLauncher;
import com.android.settingslib.accessibility.AccessibilityUtils;
import com.havoc.config.center.C1715R;
import java.util.List;

public class AccessibilityDetailsSettingsFragment extends InstrumentedFragment {
    public int getMetricsCategory() {
        return 1682;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String stringExtra = getActivity().getIntent().getStringExtra("android.intent.extra.COMPONENT_NAME");
        if (stringExtra == null) {
            Log.w("A11yDetailsSettings", "Open accessibility services list due to no component name.");
            openAccessibilitySettingsAndFinish();
            return;
        }
        ComponentName unflattenFromString = ComponentName.unflattenFromString(stringExtra);
        AccessibilityServiceInfo accessibilityServiceInfo = getAccessibilityServiceInfo(unflattenFromString);
        if (accessibilityServiceInfo == null) {
            Log.w("A11yDetailsSettings", "Open accessibility services list due to invalid component name.");
            openAccessibilitySettingsAndFinish();
        } else if (!isServiceAllowed(unflattenFromString.getPackageName())) {
            Log.w("A11yDetailsSettings", "Open accessibility services list due to target accessibility service is prohibited by Device Admin.");
            openAccessibilitySettingsAndFinish();
        } else {
            openAccessibilityDetailsSettingsAndFinish(buildArguments(accessibilityServiceInfo));
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void openAccessibilitySettingsAndFinish() {
        new SubSettingLauncher(getActivity()).setDestination(AccessibilitySettings.class.getName()).setSourceMetricsCategory(getMetricsCategory()).launch();
        finish();
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void openAccessibilityDetailsSettingsAndFinish(Bundle bundle) {
        new SubSettingLauncher(getActivity()).setDestination(ToggleAccessibilityServicePreferenceFragment.class.getName()).setSourceMetricsCategory(getMetricsCategory()).setArguments(bundle).launch();
        finish();
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public boolean isServiceAllowed(String str) {
        List permittedAccessibilityServices = ((DevicePolicyManager) getContext().getSystemService(DevicePolicyManager.class)).getPermittedAccessibilityServices(UserHandle.myUserId());
        return permittedAccessibilityServices == null || permittedAccessibilityServices.contains(str);
    }

    private AccessibilityServiceInfo getAccessibilityServiceInfo(ComponentName componentName) {
        if (componentName == null) {
            return null;
        }
        List<AccessibilityServiceInfo> installedAccessibilityServiceList = AccessibilityManager.getInstance(getActivity()).getInstalledAccessibilityServiceList();
        int size = installedAccessibilityServiceList.size();
        for (int i = 0; i < size; i++) {
            AccessibilityServiceInfo accessibilityServiceInfo = installedAccessibilityServiceList.get(i);
            ResolveInfo resolveInfo = accessibilityServiceInfo.getResolveInfo();
            if (componentName.getPackageName().equals(resolveInfo.serviceInfo.packageName) && componentName.getClassName().equals(resolveInfo.serviceInfo.name)) {
                return accessibilityServiceInfo;
            }
        }
        return null;
    }

    private Bundle buildArguments(AccessibilityServiceInfo accessibilityServiceInfo) {
        ResolveInfo resolveInfo = accessibilityServiceInfo.getResolveInfo();
        String charSequence = resolveInfo.loadLabel(getActivity().getPackageManager()).toString();
        ServiceInfo serviceInfo = resolveInfo.serviceInfo;
        String str = serviceInfo.packageName;
        ComponentName componentName = new ComponentName(str, serviceInfo.name);
        List<AccessibilityServiceInfo> enabledAccessibilityServiceList = AccessibilityManager.getInstance(getActivity()).getEnabledAccessibilityServiceList(-1);
        boolean contains = AccessibilityUtils.getEnabledServicesFromSettings(getActivity()).contains(componentName);
        String loadDescription = accessibilityServiceInfo.loadDescription(getActivity().getPackageManager());
        if (TextUtils.isEmpty(loadDescription)) {
            loadDescription = getString(C1715R.string.accessibility_service_default_description);
        }
        if (contains && AccessibilityUtils.hasServiceCrashed(str, serviceInfo.name, enabledAccessibilityServiceList)) {
            loadDescription = getString(C1715R.string.accessibility_description_state_stopped);
        }
        Bundle bundle = new Bundle();
        bundle.putString("preference_key", componentName.flattenToString());
        bundle.putBoolean("checked", contains);
        bundle.putString("title", charSequence);
        bundle.putParcelable("resolve_info", resolveInfo);
        bundle.putString("summary", loadDescription);
        String settingsActivityName = accessibilityServiceInfo.getSettingsActivityName();
        if (!TextUtils.isEmpty(settingsActivityName)) {
            bundle.putString("settings_title", getString(C1715R.string.accessibility_menu_item_settings));
            bundle.putString("settings_component_name", new ComponentName(str, settingsActivityName).flattenToString());
        }
        bundle.putParcelable("component_name", componentName);
        return bundle;
    }

    private void finish() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
    }
}
