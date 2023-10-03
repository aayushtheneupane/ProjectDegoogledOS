package com.android.settings.privacy;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.view.accessibility.AccessibilityManager;
import com.android.settings.core.BasePreferenceController;
import com.havoc.config.center.C1715R;
import java.util.List;

public class AccessibilityUsagePreferenceController extends BasePreferenceController {
    private final List<AccessibilityServiceInfo> mEnabledServiceInfos;

    public AccessibilityUsagePreferenceController(Context context, String str) {
        super(context, str);
        this.mEnabledServiceInfos = ((AccessibilityManager) context.getSystemService(AccessibilityManager.class)).getEnabledAccessibilityServiceList(-1);
    }

    public int getAvailabilityStatus() {
        return this.mEnabledServiceInfos.isEmpty() ? 3 : 0;
    }

    public CharSequence getSummary() {
        return this.mContext.getResources().getQuantityString(C1715R.plurals.accessibility_usage_summary, this.mEnabledServiceInfos.size(), new Object[]{Integer.valueOf(this.mEnabledServiceInfos.size())});
    }
}
