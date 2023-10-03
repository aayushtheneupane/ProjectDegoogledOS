package com.android.settings.accessibility;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.provider.Settings;
import android.view.accessibility.AccessibilityManager;
import com.android.settings.core.TogglePreferenceController;
import com.android.settingslib.accessibility.AccessibilityUtils;

public class AccessibilitySlicePreferenceController extends TogglePreferenceController {
    private final int OFF = 0;

    /* renamed from: ON */
    private final int f19ON = 1;
    private final ComponentName mComponentName = ComponentName.unflattenFromString(getPreferenceKey());

    public boolean isSliceable() {
        return true;
    }

    public AccessibilitySlicePreferenceController(Context context, String str) {
        super(context, str);
        if (this.mComponentName == null) {
            throw new IllegalArgumentException("Illegal Component Name from: " + str);
        }
    }

    public CharSequence getSummary() {
        AccessibilityServiceInfo accessibilityServiceInfo = getAccessibilityServiceInfo();
        if (accessibilityServiceInfo == null) {
            return "";
        }
        return AccessibilitySettings.getServiceSummary(this.mContext, accessibilityServiceInfo, isChecked());
    }

    public boolean isChecked() {
        boolean z = true;
        if (Settings.Secure.getInt(this.mContext.getContentResolver(), "accessibility_enabled", 0) != 1) {
            z = false;
        }
        if (!z) {
            return false;
        }
        return AccessibilityUtils.getEnabledServicesFromSettings(this.mContext).contains(this.mComponentName);
    }

    public boolean setChecked(boolean z) {
        if (getAccessibilityServiceInfo() == null) {
            return false;
        }
        AccessibilityUtils.setAccessibilityServiceState(this.mContext, this.mComponentName, z);
        if (z == isChecked()) {
            return true;
        }
        return false;
    }

    public int getAvailabilityStatus() {
        return getAccessibilityServiceInfo() == null ? 3 : 0;
    }

    private AccessibilityServiceInfo getAccessibilityServiceInfo() {
        for (AccessibilityServiceInfo next : ((AccessibilityManager) this.mContext.getSystemService(AccessibilityManager.class)).getInstalledAccessibilityServiceList()) {
            if (this.mComponentName.equals(next.getComponentName())) {
                return next;
            }
        }
        return null;
    }
}
