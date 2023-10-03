package com.android.settings.accessibility;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.android.internal.accessibility.AccessibilityShortcutController;
import com.android.settings.core.instrumentation.InstrumentedDialogFragment;
import com.android.settings.widget.RadioButtonPickerFragment;
import com.android.settings.widget.RadioButtonPreference;
import com.android.settingslib.accessibility.AccessibilityUtils;
import com.android.settingslib.widget.CandidateInfo;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShortcutServicePickerFragment extends RadioButtonPickerFragment {
    public int getMetricsCategory() {
        return 6;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.accessibility_shortcut_service_settings;
    }

    /* access modifiers changed from: protected */
    public List<? extends CandidateInfo> getCandidates() {
        int i;
        List<AccessibilityServiceInfo> installedAccessibilityServiceList = ((AccessibilityManager) getContext().getSystemService(AccessibilityManager.class)).getInstalledAccessibilityServiceList();
        int size = installedAccessibilityServiceList.size();
        ArrayList arrayList = new ArrayList(size);
        Map frameworkShortcutFeaturesMap = AccessibilityShortcutController.getFrameworkShortcutFeaturesMap();
        for (ComponentName componentName : frameworkShortcutFeaturesMap.keySet()) {
            if (componentName.equals(AccessibilityShortcutController.COLOR_INVERSION_COMPONENT_NAME)) {
                i = C1715R.C1717drawable.ic_color_inversion;
            } else {
                i = componentName.equals(AccessibilityShortcutController.DALTONIZER_COMPONENT_NAME) ? C1715R.C1717drawable.ic_daltonizer : C1715R.C1717drawable.empty_icon;
            }
            arrayList.add(new FrameworkCandidateInfo((AccessibilityShortcutController.ToggleableFrameworkFeatureInfo) frameworkShortcutFeaturesMap.get(componentName), i, componentName.flattenToString()));
        }
        for (int i2 = 0; i2 < size; i2++) {
            arrayList.add(new ServiceCandidateInfo(installedAccessibilityServiceList.get(i2)));
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public String getDefaultKey() {
        ComponentName unflattenFromString;
        String shortcutTargetServiceComponentNameString = AccessibilityUtils.getShortcutTargetServiceComponentNameString(getContext(), UserHandle.myUserId());
        if (shortcutTargetServiceComponentNameString == null || (unflattenFromString = ComponentName.unflattenFromString(shortcutTargetServiceComponentNameString)) == null) {
            return null;
        }
        return unflattenFromString.flattenToString();
    }

    /* access modifiers changed from: protected */
    public boolean setDefaultKey(String str) {
        Settings.Secure.putString(getContext().getContentResolver(), "accessibility_shortcut_target_service", str);
        return true;
    }

    public void onRadioButtonClicked(RadioButtonPreference radioButtonPreference) {
        String key = radioButtonPreference.getKey();
        if (TextUtils.isEmpty(key)) {
            super.onRadioButtonClicked(radioButtonPreference);
            return;
        }
        if (AccessibilityShortcutController.getFrameworkShortcutFeaturesMap().containsKey(ComponentName.unflattenFromString(key))) {
            onRadioButtonConfirmed(key);
            return;
        }
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ConfirmationDialogFragment.newInstance(this, key).show(activity.getSupportFragmentManager(), "ConfirmationDialogFragment");
        }
    }

    /* access modifiers changed from: private */
    public void onServiceConfirmed(String str) {
        onRadioButtonConfirmed(str);
    }

    public static class ConfirmationDialogFragment extends InstrumentedDialogFragment implements View.OnClickListener {
        private IBinder mToken;

        public int getMetricsCategory() {
            return 6;
        }

        public static ConfirmationDialogFragment newInstance(ShortcutServicePickerFragment shortcutServicePickerFragment, String str) {
            ConfirmationDialogFragment confirmationDialogFragment = new ConfirmationDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString("extra_key", str);
            confirmationDialogFragment.setArguments(bundle);
            confirmationDialogFragment.setTargetFragment(shortcutServicePickerFragment, 0);
            confirmationDialogFragment.mToken = new Binder();
            return confirmationDialogFragment;
        }

        public Dialog onCreateDialog(Bundle bundle) {
            return AccessibilityServiceWarning.createCapabilitiesDialog(getActivity(), ((AccessibilityManager) getActivity().getSystemService(AccessibilityManager.class)).getInstalledServiceInfoWithComponentName(ComponentName.unflattenFromString(getArguments().getString("extra_key"))), this);
        }

        public void onClick(View view) {
            Fragment targetFragment = getTargetFragment();
            if (view.getId() == C1715R.C1718id.permission_enable_allow_button && (targetFragment instanceof ShortcutServicePickerFragment)) {
                ((ShortcutServicePickerFragment) targetFragment).onServiceConfirmed(getArguments().getString("extra_key"));
            }
            dismiss();
        }
    }

    private class FrameworkCandidateInfo extends CandidateInfo {
        final int mIconResId;
        final String mKey;
        final AccessibilityShortcutController.ToggleableFrameworkFeatureInfo mToggleableFrameworkFeatureInfo;

        public FrameworkCandidateInfo(AccessibilityShortcutController.ToggleableFrameworkFeatureInfo toggleableFrameworkFeatureInfo, int i, String str) {
            super(true);
            this.mToggleableFrameworkFeatureInfo = toggleableFrameworkFeatureInfo;
            this.mIconResId = i;
            this.mKey = str;
        }

        public CharSequence loadLabel() {
            return this.mToggleableFrameworkFeatureInfo.getLabel(ShortcutServicePickerFragment.this.getContext());
        }

        public Drawable loadIcon() {
            return ShortcutServicePickerFragment.this.getContext().getDrawable(this.mIconResId);
        }

        public String getKey() {
            return this.mKey;
        }
    }

    private class ServiceCandidateInfo extends CandidateInfo {
        final AccessibilityServiceInfo mServiceInfo;

        public ServiceCandidateInfo(AccessibilityServiceInfo accessibilityServiceInfo) {
            super(true);
            this.mServiceInfo = accessibilityServiceInfo;
        }

        public CharSequence loadLabel() {
            PackageManager packageManager = ShortcutServicePickerFragment.this.getContext().getPackageManager();
            CharSequence loadLabel = this.mServiceInfo.getResolveInfo().serviceInfo.loadLabel(packageManager);
            if (loadLabel != null) {
                return loadLabel;
            }
            ComponentName componentName = this.mServiceInfo.getComponentName();
            if (componentName != null) {
                try {
                    return packageManager.getApplicationInfoAsUser(componentName.getPackageName(), 0, UserHandle.myUserId()).loadLabel(packageManager);
                } catch (PackageManager.NameNotFoundException unused) {
                }
            }
            return null;
        }

        public Drawable loadIcon() {
            ResolveInfo resolveInfo = this.mServiceInfo.getResolveInfo();
            if (resolveInfo.getIconResource() == 0) {
                return ShortcutServicePickerFragment.this.getContext().getDrawable(C1715R.C1717drawable.ic_accessibility_generic);
            }
            return resolveInfo.loadIcon(ShortcutServicePickerFragment.this.getContext().getPackageManager());
        }

        public String getKey() {
            return this.mServiceInfo.getComponentName().flattenToString();
        }
    }
}
