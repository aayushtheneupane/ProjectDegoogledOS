package com.android.settings.notification;

import android.app.Activity;
import android.app.AutomaticZenRule;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.service.notification.ZenModeConfig;
import android.util.Slog;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.widget.EntityHeaderController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.widget.LayoutPreference;
import com.havoc.config.center.C1715R;

public class ZenAutomaticRuleHeaderPreferenceController extends AbstractZenModePreferenceController implements PreferenceControllerMixin {
    private final String KEY = "pref_app_header";
    private EntityHeaderController mController;
    private final PreferenceFragmentCompat mFragment;
    private String mId;
    private AutomaticZenRule mRule;

    public String getPreferenceKey() {
        return "pref_app_header";
    }

    public ZenAutomaticRuleHeaderPreferenceController(Context context, PreferenceFragmentCompat preferenceFragmentCompat, Lifecycle lifecycle) {
        super(context, "pref_app_header", lifecycle);
        this.mFragment = preferenceFragmentCompat;
    }

    public boolean isAvailable() {
        return this.mRule != null;
    }

    public void updateState(Preference preference) {
        PreferenceFragmentCompat preferenceFragmentCompat;
        if (this.mRule != null && (preferenceFragmentCompat = this.mFragment) != null) {
            LayoutPreference layoutPreference = (LayoutPreference) preference;
            if (this.mController == null) {
                this.mController = EntityHeaderController.newInstance(preferenceFragmentCompat.getActivity(), this.mFragment, layoutPreference.findViewById(C1715R.C1718id.entity_header));
            }
            this.mController.setIcon(getIcon()).setLabel((CharSequence) this.mRule.getName()).setPackageName(this.mRule.getOwner().getPackageName()).setUid(this.mContext.getUserId()).setHasAppInfoLink(false).setButtonActions(2, 0).done((Activity) this.mFragment.getActivity(), this.mContext).findViewById(C1715R.C1718id.entity_header).setVisibility(0);
        }
    }

    private Drawable getIcon() {
        try {
            PackageManager packageManager = this.mContext.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(this.mRule.getOwner().getPackageName(), 0);
            if (applicationInfo.isSystemApp()) {
                if (ZenModeConfig.isValidScheduleConditionId(this.mRule.getConditionId())) {
                    return this.mContext.getDrawable(C1715R.C1717drawable.ic_timelapse);
                }
                if (ZenModeConfig.isValidEventConditionId(this.mRule.getConditionId())) {
                    return this.mContext.getDrawable(C1715R.C1717drawable.ic_event);
                }
            }
            return applicationInfo.loadIcon(packageManager);
        } catch (PackageManager.NameNotFoundException unused) {
            Slog.w("PrefControllerMixin", "Unable to load icon - PackageManager.NameNotFoundException");
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void onResume(AutomaticZenRule automaticZenRule, String str) {
        this.mRule = automaticZenRule;
        this.mId = str;
    }
}
