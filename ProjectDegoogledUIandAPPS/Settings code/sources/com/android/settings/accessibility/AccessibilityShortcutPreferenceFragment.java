package com.android.settings.accessibility;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.os.UserHandle;
import android.provider.Settings;
import android.view.accessibility.AccessibilityManager;
import android.widget.Switch;
import androidx.preference.Preference;
import com.android.internal.accessibility.AccessibilityShortcutController;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settings.widget.SwitchBar;
import com.android.settingslib.accessibility.AccessibilityUtils;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.SwitchPreference;

public class AccessibilityShortcutPreferenceFragment extends ToggleFeaturePreferenceFragment implements Indexable {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        /* access modifiers changed from: protected */
        public boolean isPageSearchEnabled(Context context) {
            return false;
        }
    };
    private final ContentObserver mContentObserver = new ContentObserver(new Handler()) {
        public void onChange(boolean z) {
            AccessibilityShortcutPreferenceFragment.this.updatePreferences();
        }
    };
    private SwitchPreference mOnLockScreenSwitchPreference;
    private Preference mServicePreference;

    public int getHelpResource() {
        return C1715R.string.help_url_accessibility_shortcut;
    }

    public int getMetricsCategory() {
        return 6;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.accessibility_shortcut_settings;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mServicePreference = findPreference("accessibility_shortcut_service");
        this.mOnLockScreenSwitchPreference = (SwitchPreference) findPreference("accessibility_shortcut_on_lock_screen");
        this.mOnLockScreenSwitchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public final boolean onPreferenceChange(Preference preference, Object obj) {
                return AccessibilityShortcutPreferenceFragment.this.lambda$onCreate$0$AccessibilityShortcutPreferenceFragment(preference, obj);
            }
        });
        this.mFooterPreferenceMixin.createFooterPreference().setTitle((int) C1715R.string.accessibility_shortcut_description);
    }

    public /* synthetic */ boolean lambda$onCreate$0$AccessibilityShortcutPreferenceFragment(Preference preference, Object obj) {
        Settings.Secure.putInt(getContentResolver(), "accessibility_shortcut_on_lock_screen", ((Boolean) obj).booleanValue() ? 1 : 0);
        return true;
    }

    public void onResume() {
        super.onResume();
        updatePreferences();
        getContentResolver().registerContentObserver(Settings.Secure.getUriFor("accessibility_shortcut_dialog_shown"), false, this.mContentObserver);
    }

    public void onPause() {
        getContentResolver().unregisterContentObserver(this.mContentObserver);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onInstallSwitchBarToggleSwitch() {
        super.onInstallSwitchBarToggleSwitch();
        this.mSwitchBar.addOnSwitchChangeListener(new SwitchBar.OnSwitchChangeListener() {
            public final void onSwitchChanged(Switch switchR, boolean z) {
                AccessibilityShortcutPreferenceFragment.this.mo7718x6d38fc90(switchR, z);
            }
        });
    }

    /* renamed from: lambda$onInstallSwitchBarToggleSwitch$1$AccessibilityShortcutPreferenceFragment */
    public /* synthetic */ void mo7718x6d38fc90(Switch switchR, boolean z) {
        Context context = getContext();
        if (!z || shortcutFeatureAvailable(context)) {
            onPreferenceToggled("accessibility_shortcut_enabled", z);
            return;
        }
        Settings.Secure.putInt(getContentResolver(), "accessibility_shortcut_enabled", 1);
        this.mServicePreference.setEnabled(true);
        this.mServicePreference.performClick();
    }

    /* access modifiers changed from: protected */
    public void onPreferenceToggled(String str, boolean z) {
        Settings.Secure.putInt(getContentResolver(), str, z ? 1 : 0);
        updatePreferences();
    }

    /* access modifiers changed from: private */
    public void updatePreferences() {
        ContentResolver contentResolver = getContentResolver();
        Context context = getContext();
        this.mServicePreference.setSummary(getServiceName(context));
        if (!shortcutFeatureAvailable(context)) {
            Settings.Secure.putInt(getContentResolver(), "accessibility_shortcut_enabled", 0);
        }
        boolean z = true;
        this.mSwitchBar.setChecked(Settings.Secure.getInt(contentResolver, "accessibility_shortcut_enabled", 1) == 1);
        if (Settings.Secure.getInt(contentResolver, "accessibility_shortcut_on_lock_screen", Settings.Secure.getInt(contentResolver, "accessibility_shortcut_dialog_shown", 0)) != 1) {
            z = false;
        }
        this.mOnLockScreenSwitchPreference.setChecked(z);
        this.mServicePreference.setEnabled(this.mToggleSwitch.isChecked());
        this.mOnLockScreenSwitchPreference.setEnabled(this.mToggleSwitch.isChecked());
    }

    public static CharSequence getServiceName(Context context) {
        if (!shortcutFeatureAvailable(context)) {
            return context.getString(C1715R.string.accessibility_no_service_selected);
        }
        AccessibilityServiceInfo serviceInfo = getServiceInfo(context);
        if (serviceInfo != null) {
            return serviceInfo.getResolveInfo().loadLabel(context.getPackageManager());
        }
        return ((AccessibilityShortcutController.ToggleableFrameworkFeatureInfo) AccessibilityShortcutController.getFrameworkShortcutFeaturesMap().get(getShortcutComponent(context))).getLabel(context);
    }

    private static AccessibilityServiceInfo getServiceInfo(Context context) {
        return AccessibilityManager.getInstance(context).getInstalledServiceInfoWithComponentName(getShortcutComponent(context));
    }

    private static boolean shortcutFeatureAvailable(Context context) {
        ComponentName shortcutComponent = getShortcutComponent(context);
        if (shortcutComponent == null) {
            return false;
        }
        if (AccessibilityShortcutController.getFrameworkShortcutFeaturesMap().containsKey(shortcutComponent)) {
            return true;
        }
        if (getServiceInfo(context) != null) {
            return true;
        }
        return false;
    }

    private static ComponentName getShortcutComponent(Context context) {
        String shortcutTargetServiceComponentNameString = AccessibilityUtils.getShortcutTargetServiceComponentNameString(context, UserHandle.myUserId());
        if (shortcutTargetServiceComponentNameString == null) {
            return null;
        }
        return ComponentName.unflattenFromString(shortcutTargetServiceComponentNameString);
    }
}
