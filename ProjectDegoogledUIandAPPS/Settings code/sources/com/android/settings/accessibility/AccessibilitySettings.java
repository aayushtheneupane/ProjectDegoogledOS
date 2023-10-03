package com.android.settings.accessibility;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.hardware.display.ColorDisplayManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.UserHandle;
import android.os.Vibrator;
import android.provider.DeviceConfig;
import android.provider.SearchIndexableResource;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.view.KeyCharacterMap;
import android.view.accessibility.AccessibilityManager;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import com.android.internal.accessibility.AccessibilityShortcutController;
import com.android.internal.content.PackageMonitor;
import com.android.internal.view.RotationPolicy;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.Utils;
import com.android.settings.display.DarkUIPreferenceController;
import com.android.settings.display.ToggleFontSizePreferenceFragment;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.RestrictedPreference;
import com.android.settingslib.accessibility.AccessibilityUtils;
import com.google.common.primitives.Ints;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.SwitchPreference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AccessibilitySettings extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener {
    private static final String[] CATEGORIES = {"screen_reader_category", "audio_and_captions_category", "display_category", "interaction_control_category", "experimental_category", "user_installed_services_category"};
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.accessibility_settings;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }
    };
    private static final String[] TOGGLE_ANIMATION_TARGETS = {"window_animation_scale", "transition_animation_scale", "animator_duration_scale"};
    private Preference mAccessibilityShortcutPreferenceScreen;
    private Preference mAutoclickPreferenceScreen;
    private Preference mCaptioningPreferenceScreen;
    private final Map<String, PreferenceCategory> mCategoryToPrefCategoryMap = new ArrayMap();
    private SwitchPreference mDarkUIModePreference;
    private DarkUIPreferenceController mDarkUIPreferenceController;
    private Preference mDisplayDaltonizerPreferenceScreen;
    private Preference mDisplayMagnificationPreferenceScreen;
    private DevicePolicyManager mDpm;
    private Preference mFontSizePreferenceScreen;
    /* access modifiers changed from: private */
    public final Handler mHandler = new Handler();
    private Preference mHearingAidPreference;
    private AccessibilityHearingAidPreferenceController mHearingAidPreferenceController;
    private ColorInversionPreferenceController mInversionPreferenceController;
    private Preference mLiveCaptionPreference;
    private LiveCaptionPreferenceController mLiveCaptionPreferenceController;
    private int mLongPressTimeoutDefault;
    private final Map<String, String> mLongPressTimeoutValueToTitleMap = new HashMap();
    private final Map<ComponentName, PreferenceCategory> mPreBundledServiceComponentToCategoryMap = new ArrayMap();
    private Preference mRTTPreference;
    private RTTSettingPreferenceController mRTTSettingPreferenceController;
    private final RotationPolicy.RotationPolicyListener mRotationPolicyListener = new RotationPolicy.RotationPolicyListener() {
        public void onChange() {
            AccessibilitySettings.this.updateLockScreenRotationCheckbox();
        }
    };
    private ListPreference mSelectLongPressTimeoutPreference;
    private final Map<Preference, PreferenceCategory> mServicePreferenceToPreferenceCategoryMap = new ArrayMap();
    private final SettingsContentObserver mSettingsContentObserver;
    private final PackageMonitor mSettingsPackageMonitor = new PackageMonitor() {
        public void onPackageAdded(String str, int i) {
            sendUpdate();
        }

        public void onPackageAppeared(String str, int i) {
            sendUpdate();
        }

        public void onPackageDisappeared(String str, int i) {
            sendUpdate();
        }

        public void onPackageRemoved(String str, int i) {
            sendUpdate();
        }

        private void sendUpdate() {
            AccessibilitySettings.this.mHandler.postDelayed(AccessibilitySettings.this.mUpdateRunnable, 1000);
        }
    };
    private SwitchPreference mToggleDisableAnimationsPreference;
    private SwitchPreference mToggleHighTextContrastPreference;
    private SwitchPreference mToggleInversionPreference;
    private SwitchPreference mToggleLargePointerIconPreference;
    private SwitchPreference mToggleLockScreenRotationPreference;
    private SwitchPreference mToggleMasterMonoPreference;
    private SwitchPreference mTogglePowerButtonEndsCallPreference;
    /* access modifiers changed from: private */
    public final Runnable mUpdateRunnable = new Runnable() {
        public void run() {
            if (AccessibilitySettings.this.getActivity() != null) {
                AccessibilitySettings.this.updateServicePreferences();
            }
        }
    };
    private Preference mVibrationPreferenceScreen;

    public int getHelpResource() {
        return C1715R.string.help_uri_accessibility;
    }

    public int getMetricsCategory() {
        return 2;
    }

    public AccessibilitySettings() {
        Collection<AccessibilityShortcutController.ToggleableFrameworkFeatureInfo> values = AccessibilityShortcutController.getFrameworkShortcutFeaturesMap().values();
        ArrayList arrayList = new ArrayList(values.size());
        for (AccessibilityShortcutController.ToggleableFrameworkFeatureInfo settingKey : values) {
            arrayList.add(settingKey.getSettingKey());
        }
        this.mSettingsContentObserver = new SettingsContentObserver(this.mHandler, arrayList) {
            public void onChange(boolean z, Uri uri) {
                AccessibilitySettings.this.updateAllPreferences();
            }
        };
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(C1715R.xml.accessibility_settings);
        initializeAllPreferences();
        this.mDpm = (DevicePolicyManager) getActivity().getSystemService("device_policy");
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.mHearingAidPreferenceController = new AccessibilityHearingAidPreferenceController(context, "hearing_aid_preference");
        this.mHearingAidPreferenceController.setFragmentManager(getFragmentManager());
        getLifecycle().addObserver(this.mHearingAidPreferenceController);
        this.mLiveCaptionPreferenceController = new LiveCaptionPreferenceController(context, "live_caption");
        this.mRTTSettingPreferenceController = new RTTSettingPreferenceController(context, "rtt_setting");
    }

    public void onResume() {
        super.onResume();
        updateAllPreferences();
        this.mSettingsPackageMonitor.register(getActivity(), getActivity().getMainLooper(), false);
        this.mSettingsContentObserver.register(getContentResolver());
        if (RotationPolicy.isRotationSupported(getActivity())) {
            RotationPolicy.registerRotationPolicyListener(getActivity(), this.mRotationPolicyListener);
        }
    }

    public void onPause() {
        this.mSettingsPackageMonitor.unregister();
        this.mSettingsContentObserver.unregister(getContentResolver());
        if (RotationPolicy.isRotationSupported(getActivity())) {
            RotationPolicy.unregisterRotationPolicyListener(getActivity(), this.mRotationPolicyListener);
        }
        super.onPause();
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (this.mSelectLongPressTimeoutPreference != preference) {
            return false;
        }
        handleLongPressTimeoutPreferenceChange((String) obj);
        return true;
    }

    private void handleLongPressTimeoutPreferenceChange(String str) {
        Settings.Secure.putInt(getContentResolver(), "long_press_timeout", Integer.parseInt(str));
        this.mSelectLongPressTimeoutPreference.setSummary(this.mLongPressTimeoutValueToTitleMap.get(str));
    }

    public boolean onPreferenceTreeClick(Preference preference) {
        if (this.mToggleHighTextContrastPreference == preference) {
            handleToggleTextContrastPreferenceClick();
            return true;
        } else if (this.mTogglePowerButtonEndsCallPreference == preference) {
            handleTogglePowerButtonEndsCallPreferenceClick();
            return true;
        } else if (this.mToggleLockScreenRotationPreference == preference) {
            handleLockScreenRotationPreferenceClick();
            return true;
        } else if (this.mToggleLargePointerIconPreference == preference) {
            handleToggleLargePointerIconPreferenceClick();
            return true;
        } else if (this.mToggleDisableAnimationsPreference == preference) {
            handleToggleDisableAnimations();
            return true;
        } else if (this.mToggleMasterMonoPreference == preference) {
            handleToggleMasterMonoPreferenceClick();
            return true;
        } else if (this.mHearingAidPreferenceController.handlePreferenceTreeClick(preference)) {
            return true;
        } else {
            return super.onPreferenceTreeClick(preference);
        }
    }

    public static CharSequence getServiceSummary(Context context, AccessibilityServiceInfo accessibilityServiceInfo, boolean z) {
        String str;
        if (z) {
            str = context.getString(C1715R.string.accessibility_summary_state_enabled);
        } else {
            str = context.getString(C1715R.string.accessibility_summary_state_disabled);
        }
        CharSequence loadSummary = accessibilityServiceInfo.loadSummary(context.getPackageManager());
        return TextUtils.isEmpty(loadSummary) ? str : context.getString(C1715R.string.preference_summary_default_combination, new Object[]{str, loadSummary});
    }

    static boolean isRampingRingerEnabled(Context context) {
        if (Settings.Global.getInt(context.getContentResolver(), "apply_ramping_ringer", 0) != 1 || !DeviceConfig.getBoolean("telephony", "ramping_ringer_enabled", false)) {
            return false;
        }
        return true;
    }

    private void handleToggleTextContrastPreferenceClick() {
        Settings.Secure.putInt(getContentResolver(), "high_text_contrast_enabled", this.mToggleHighTextContrastPreference.isChecked() ? 1 : 0);
    }

    private void handleTogglePowerButtonEndsCallPreferenceClick() {
        Settings.Secure.putInt(getContentResolver(), "incall_power_button_behavior", this.mTogglePowerButtonEndsCallPreference.isChecked() ? 2 : 1);
    }

    private void handleLockScreenRotationPreferenceClick() {
        RotationPolicy.setRotationLockForAccessibility(getActivity(), !this.mToggleLockScreenRotationPreference.isChecked());
    }

    private void handleToggleLargePointerIconPreferenceClick() {
        Settings.Secure.putInt(getContentResolver(), "accessibility_large_pointer_icon", this.mToggleLargePointerIconPreference.isChecked() ? 1 : 0);
    }

    private void handleToggleDisableAnimations() {
        String str = this.mToggleDisableAnimationsPreference.isChecked() ? "0" : "1";
        for (String putString : TOGGLE_ANIMATION_TARGETS) {
            Settings.Global.putString(getContentResolver(), putString, str);
        }
    }

    private void handleToggleMasterMonoPreferenceClick() {
        Settings.System.putIntForUser(getContentResolver(), "master_mono", this.mToggleMasterMonoPreference.isChecked() ? 1 : 0, -2);
    }

    private void initializeAllPreferences() {
        int i = 0;
        while (true) {
            String[] strArr = CATEGORIES;
            if (i >= strArr.length) {
                break;
            }
            this.mCategoryToPrefCategoryMap.put(CATEGORIES[i], (PreferenceCategory) findPreference(strArr[i]));
            i++;
        }
        this.mToggleHighTextContrastPreference = (SwitchPreference) findPreference("toggle_high_text_contrast_preference");
        this.mToggleInversionPreference = (SwitchPreference) findPreference("toggle_inversion_preference");
        this.mInversionPreferenceController = new ColorInversionPreferenceController(getContext(), "toggle_inversion_preference");
        this.mInversionPreferenceController.displayPreference(getPreferenceScreen());
        this.mTogglePowerButtonEndsCallPreference = (SwitchPreference) findPreference("toggle_power_button_ends_call_preference");
        if (!KeyCharacterMap.deviceHasKey(26) || !Utils.isVoiceCapable(getActivity())) {
            this.mCategoryToPrefCategoryMap.get("interaction_control_category").removePreference(this.mTogglePowerButtonEndsCallPreference);
        }
        this.mToggleLockScreenRotationPreference = (SwitchPreference) findPreference("toggle_lock_screen_rotation_preference");
        if (!RotationPolicy.isRotationSupported(getActivity())) {
            this.mCategoryToPrefCategoryMap.get("interaction_control_category").removePreference(this.mToggleLockScreenRotationPreference);
        }
        this.mToggleLargePointerIconPreference = (SwitchPreference) findPreference("toggle_large_pointer_icon");
        this.mToggleDisableAnimationsPreference = (SwitchPreference) findPreference("toggle_disable_animations");
        this.mToggleMasterMonoPreference = (SwitchPreference) findPreference("toggle_master_mono");
        this.mSelectLongPressTimeoutPreference = (ListPreference) findPreference("select_long_press_timeout_preference");
        this.mSelectLongPressTimeoutPreference.setOnPreferenceChangeListener(this);
        if (this.mLongPressTimeoutValueToTitleMap.size() == 0) {
            String[] stringArray = getResources().getStringArray(C1715R.array.long_press_timeout_selector_values);
            this.mLongPressTimeoutDefault = Integer.parseInt(stringArray[0]);
            String[] stringArray2 = getResources().getStringArray(C1715R.array.long_press_timeout_selector_titles);
            int length = stringArray.length;
            for (int i2 = 0; i2 < length; i2++) {
                this.mLongPressTimeoutValueToTitleMap.put(stringArray[i2], stringArray2[i2]);
            }
        }
        this.mHearingAidPreference = findPreference("hearing_aid_preference");
        this.mHearingAidPreferenceController.displayPreference(getPreferenceScreen());
        this.mRTTPreference = findPreference("rtt_setting");
        this.mRTTSettingPreferenceController.displayPreference(getPreferenceScreen());
        this.mCaptioningPreferenceScreen = findPreference("captioning_preference_screen");
        this.mLiveCaptionPreference = findPreference("live_caption");
        this.mLiveCaptionPreferenceController.displayPreference(getPreferenceScreen());
        this.mDisplayMagnificationPreferenceScreen = findPreference("magnification_preference_screen");
        configureMagnificationPreferenceIfNeeded(this.mDisplayMagnificationPreferenceScreen);
        this.mFontSizePreferenceScreen = findPreference("font_size_preference_screen");
        this.mAutoclickPreferenceScreen = findPreference("autoclick_preference");
        this.mDisplayDaltonizerPreferenceScreen = findPreference("daltonizer_preference");
        this.mAccessibilityShortcutPreferenceScreen = findPreference("accessibility_shortcut_preference");
        this.mVibrationPreferenceScreen = findPreference("vibration_preference_screen");
        this.mDarkUIModePreference = (SwitchPreference) findPreference("dark_ui_mode_accessibility");
        this.mDarkUIPreferenceController = new DarkUIPreferenceController(getContext(), "dark_ui_mode_accessibility");
        this.mDarkUIPreferenceController.setParentFragment(this);
        this.mDarkUIPreferenceController.displayPreference(getPreferenceScreen());
    }

    /* access modifiers changed from: private */
    public void updateAllPreferences() {
        updateSystemPreferences();
        updateServicePreferences();
    }

    /* access modifiers changed from: protected */
    public void updateServicePreferences() {
        int i;
        List<AccessibilityServiceInfo> list;
        Drawable drawable;
        List<AccessibilityServiceInfo> list2;
        boolean z;
        ArrayList arrayList = new ArrayList(this.mServicePreferenceToPreferenceCategoryMap.keySet());
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            Preference preference = (Preference) arrayList.get(i2);
            this.mServicePreferenceToPreferenceCategoryMap.get(preference).removePreference(preference);
        }
        initializePreBundledServicesMapFromArray("screen_reader_category", C1715R.array.config_preinstalled_screen_reader_services);
        initializePreBundledServicesMapFromArray("audio_and_captions_category", C1715R.array.config_preinstalled_audio_and_caption_services);
        String str = "display_category";
        initializePreBundledServicesMapFromArray(str, C1715R.array.config_preinstalled_display_services);
        String str2 = "interaction_control_category";
        initializePreBundledServicesMapFromArray(str2, C1715R.array.config_preinstalled_interaction_control_services);
        AccessibilityManager instance = AccessibilityManager.getInstance(getActivity());
        List<AccessibilityServiceInfo> installedAccessibilityServiceList = instance.getInstalledAccessibilityServiceList();
        List<AccessibilityServiceInfo> enabledAccessibilityServiceList = instance.getEnabledAccessibilityServiceList(-1);
        Set<ComponentName> enabledServicesFromSettings = AccessibilityUtils.getEnabledServicesFromSettings(getActivity());
        List permittedAccessibilityServices = this.mDpm.getPermittedAccessibilityServices(UserHandle.myUserId());
        PreferenceCategory preferenceCategory = this.mCategoryToPrefCategoryMap.get("user_installed_services_category");
        if (findPreference("user_installed_services_category") == null) {
            getPreferenceScreen().addPreference(preferenceCategory);
        }
        int size = installedAccessibilityServiceList.size();
        int i3 = 0;
        while (i3 < size) {
            AccessibilityServiceInfo accessibilityServiceInfo = installedAccessibilityServiceList.get(i3);
            ResolveInfo resolveInfo = accessibilityServiceInfo.getResolveInfo();
            RestrictedPreference restrictedPreference = new RestrictedPreference(preferenceCategory.getContext());
            String charSequence = resolveInfo.loadLabel(getPackageManager()).toString();
            if (resolveInfo.getIconResource() == 0) {
                list = installedAccessibilityServiceList;
                i = size;
                drawable = ContextCompat.getDrawable(getContext(), C1715R.C1717drawable.ic_accessibility_generic);
            } else {
                list = installedAccessibilityServiceList;
                i = size;
                drawable = resolveInfo.loadIcon(getPackageManager());
            }
            ServiceInfo serviceInfo = resolveInfo.serviceInfo;
            PreferenceCategory preferenceCategory2 = preferenceCategory;
            String str3 = serviceInfo.packageName;
            String str4 = str;
            String str5 = str2;
            ComponentName componentName = new ComponentName(str3, serviceInfo.name);
            restrictedPreference.setKey(componentName.flattenToString());
            restrictedPreference.setTitle((CharSequence) charSequence);
            restrictedPreference.setIconSize(1);
            Utils.setSafeIcon(restrictedPreference, drawable);
            boolean contains = enabledServicesFromSettings.contains(componentName);
            String loadDescription = accessibilityServiceInfo.loadDescription(getPackageManager());
            if (TextUtils.isEmpty(loadDescription)) {
                loadDescription = getString(C1715R.string.accessibility_service_default_description);
            }
            if (!contains || !AccessibilityUtils.hasServiceCrashed(str3, serviceInfo.name, enabledAccessibilityServiceList)) {
                restrictedPreference.setSummary(getServiceSummary(getContext(), accessibilityServiceInfo, contains));
            } else {
                restrictedPreference.setSummary((int) C1715R.string.accessibility_summary_state_stopped);
                loadDescription = getString(C1715R.string.accessibility_description_state_stopped);
            }
            if ((permittedAccessibilityServices == null || permittedAccessibilityServices.contains(str3)) || contains) {
                list2 = enabledAccessibilityServiceList;
                z = true;
                restrictedPreference.setEnabled(true);
            } else {
                list2 = enabledAccessibilityServiceList;
                RestrictedLockUtils.EnforcedAdmin checkIfAccessibilityServiceDisallowed = RestrictedLockUtilsInternal.checkIfAccessibilityServiceDisallowed(getActivity(), str3, UserHandle.myUserId());
                if (checkIfAccessibilityServiceDisallowed != null) {
                    restrictedPreference.setDisabledByAdmin(checkIfAccessibilityServiceDisallowed);
                } else {
                    restrictedPreference.setEnabled(false);
                }
                z = true;
            }
            restrictedPreference.setFragment(ToggleAccessibilityServicePreferenceFragment.class.getName());
            restrictedPreference.setPersistent(z);
            Bundle extras = restrictedPreference.getExtras();
            Set<ComponentName> set = enabledServicesFromSettings;
            extras.putString("preference_key", restrictedPreference.getKey());
            extras.putBoolean("checked", contains);
            extras.putString("title", charSequence);
            extras.putParcelable("resolve_info", resolveInfo);
            extras.putString("summary", loadDescription);
            String settingsActivityName = accessibilityServiceInfo.getSettingsActivityName();
            if (!TextUtils.isEmpty(settingsActivityName)) {
                extras.putString("settings_title", getString(C1715R.string.accessibility_menu_item_settings));
                extras.putString("settings_component_name", new ComponentName(str3, settingsActivityName).flattenToString());
            }
            extras.putParcelable("component_name", componentName);
            PreferenceCategory preferenceCategory3 = this.mPreBundledServiceComponentToCategoryMap.containsKey(componentName) ? this.mPreBundledServiceComponentToCategoryMap.get(componentName) : preferenceCategory2;
            restrictedPreference.setOrder(-1);
            preferenceCategory3.addPreference(restrictedPreference);
            this.mServicePreferenceToPreferenceCategoryMap.put(restrictedPreference, preferenceCategory3);
            i3++;
            enabledServicesFromSettings = set;
            installedAccessibilityServiceList = list;
            size = i;
            preferenceCategory = preferenceCategory2;
            str = str4;
            str2 = str5;
            enabledAccessibilityServiceList = list2;
        }
        PreferenceCategory preferenceCategory4 = preferenceCategory;
        updateCategoryOrderFromArray("screen_reader_category", C1715R.array.config_order_screen_reader_services);
        updateCategoryOrderFromArray("audio_and_captions_category", C1715R.array.config_order_audio_and_caption_services);
        updateCategoryOrderFromArray(str2, C1715R.array.config_order_interaction_control_services);
        updateCategoryOrderFromArray(str, C1715R.array.config_order_display_services);
        if (preferenceCategory4.getPreferenceCount() == 0) {
            getPreferenceScreen().removePreference(preferenceCategory4);
        }
    }

    private void initializePreBundledServicesMapFromArray(String str, int i) {
        String[] stringArray = getResources().getStringArray(i);
        PreferenceCategory preferenceCategory = this.mCategoryToPrefCategoryMap.get(str);
        for (String unflattenFromString : stringArray) {
            this.mPreBundledServiceComponentToCategoryMap.put(ComponentName.unflattenFromString(unflattenFromString), preferenceCategory);
        }
    }

    private void updateCategoryOrderFromArray(String str, int i) {
        String[] stringArray = getResources().getStringArray(i);
        PreferenceCategory preferenceCategory = this.mCategoryToPrefCategoryMap.get(str);
        int preferenceCount = preferenceCategory.getPreferenceCount();
        int length = stringArray.length;
        for (int i2 = 0; i2 < preferenceCount; i2++) {
            int i3 = 0;
            while (true) {
                if (i3 >= length) {
                    break;
                } else if (preferenceCategory.getPreference(i2).getKey().equals(stringArray[i3])) {
                    preferenceCategory.getPreference(i2).setOrder(i3);
                    break;
                } else {
                    i3++;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void updateSystemPreferences() {
        boolean z = true;
        if (ColorDisplayManager.isColorTransformAccelerated(getContext())) {
            PreferenceCategory preferenceCategory = this.mCategoryToPrefCategoryMap.get("experimental_category");
            PreferenceCategory preferenceCategory2 = this.mCategoryToPrefCategoryMap.get("display_category");
            preferenceCategory.removePreference(this.mToggleInversionPreference);
            preferenceCategory.removePreference(this.mDisplayDaltonizerPreferenceScreen);
            this.mDisplayDaltonizerPreferenceScreen.setOrder(this.mDisplayMagnificationPreferenceScreen.getOrder() + 1);
            this.mToggleInversionPreference.setOrder(this.mDisplayDaltonizerPreferenceScreen.getOrder() + 1);
            this.mToggleLargePointerIconPreference.setOrder(this.mToggleInversionPreference.getOrder() + 1);
            this.mToggleDisableAnimationsPreference.setOrder(this.mToggleLargePointerIconPreference.getOrder() + 1);
            this.mToggleInversionPreference.setSummary((int) C1715R.string.summary_empty);
            preferenceCategory2.addPreference(this.mToggleInversionPreference);
            preferenceCategory2.addPreference(this.mDisplayDaltonizerPreferenceScreen);
        }
        this.mToggleHighTextContrastPreference.setChecked(Settings.Secure.getInt(getContentResolver(), "high_text_contrast_enabled", 0) == 1);
        this.mInversionPreferenceController.updateState(this.mToggleInversionPreference);
        this.mDarkUIPreferenceController.updateState(this.mDarkUIModePreference);
        if (KeyCharacterMap.deviceHasKey(26) && Utils.isVoiceCapable(getActivity())) {
            this.mTogglePowerButtonEndsCallPreference.setChecked(Settings.Secure.getInt(getContentResolver(), "incall_power_button_behavior", 1) == 2);
        }
        updateLockScreenRotationCheckbox();
        SwitchPreference switchPreference = this.mToggleLargePointerIconPreference;
        if (Settings.Secure.getInt(getContentResolver(), "accessibility_large_pointer_icon", 0) == 0) {
            z = false;
        }
        switchPreference.setChecked(z);
        updateDisableAnimationsToggle();
        updateMasterMono();
        String valueOf = String.valueOf(Settings.Secure.getInt(getContentResolver(), "long_press_timeout", this.mLongPressTimeoutDefault));
        this.mSelectLongPressTimeoutPreference.setValue(valueOf);
        this.mSelectLongPressTimeoutPreference.setSummary(this.mLongPressTimeoutValueToTitleMap.get(valueOf));
        updateVibrationSummary(this.mVibrationPreferenceScreen);
        this.mHearingAidPreferenceController.updateState(this.mHearingAidPreference);
        this.mRTTSettingPreferenceController.updateState(this.mRTTPreference);
        this.mLiveCaptionPreferenceController.updateState(this.mLiveCaptionPreference);
        updateFeatureSummary("accessibility_captioning_enabled", this.mCaptioningPreferenceScreen);
        updateFeatureSummary("accessibility_display_daltonizer_enabled", this.mDisplayDaltonizerPreferenceScreen);
        updateMagnificationSummary(this.mDisplayMagnificationPreferenceScreen);
        updateFontSizeSummary(this.mFontSizePreferenceScreen);
        updateAutoclickSummary(this.mAutoclickPreferenceScreen);
        updateAccessibilityShortcut(this.mAccessibilityShortcutPreferenceScreen);
        updateAccessibilityTimeoutSummary(getContentResolver(), findPreference("accessibility_control_timeout_preference_fragment"));
    }

    /* access modifiers changed from: package-private */
    public void updateAccessibilityTimeoutSummary(ContentResolver contentResolver, Preference preference) {
        String[] stringArray = getResources().getStringArray(C1715R.array.accessibility_timeout_summaries);
        int indexOf = Ints.indexOf(getResources().getIntArray(C1715R.array.accessibility_timeout_selector_values), AccessibilityTimeoutController.getSecureAccessibilityTimeoutValue(contentResolver, "accessibility_interactive_ui_timeout_ms"));
        if (indexOf == -1) {
            indexOf = 0;
        }
        preference.setSummary((CharSequence) stringArray[indexOf]);
    }

    private void updateMagnificationSummary(Preference preference) {
        boolean z = false;
        boolean z2 = Settings.Secure.getInt(getContentResolver(), "accessibility_display_magnification_enabled", 0) == 1;
        if (Settings.Secure.getInt(getContentResolver(), "accessibility_display_magnification_navbar_enabled", 0) == 1) {
            z = true;
        }
        preference.setSummary((z2 || z) ? (z2 || !z) ? (!z2 || z) ? C1715R.string.accessibility_screen_magnification_state_navbar_gesture : C1715R.string.accessibility_screen_magnification_gestures_title : C1715R.string.accessibility_screen_magnification_navbar_title : C1715R.string.accessibility_feature_state_off);
    }

    private void updateFeatureSummary(String str, Preference preference) {
        int i = Settings.Secure.getInt(getContentResolver(), str, 0);
        boolean z = true;
        if (i != 1) {
            z = false;
        }
        preference.setSummary(z ? C1715R.string.accessibility_feature_state_on : C1715R.string.accessibility_feature_state_off);
    }

    private void updateAutoclickSummary(Preference preference) {
        boolean z = false;
        if (Settings.Secure.getInt(getContentResolver(), "accessibility_autoclick_enabled", 0) == 1) {
            z = true;
        }
        if (!z) {
            preference.setSummary((int) C1715R.string.accessibility_feature_state_off);
            return;
        }
        preference.setSummary(ToggleAutoclickPreferenceFragment.getAutoclickPreferenceSummary(getResources(), Settings.Secure.getInt(getContentResolver(), "accessibility_autoclick_delay", 600)));
    }

    private void updateFontSizeSummary(Preference preference) {
        float f = Settings.System.getFloat(getContext().getContentResolver(), "font_scale", 1.0f);
        Resources resources = getContext().getResources();
        preference.setSummary((CharSequence) resources.getStringArray(C1715R.array.entries_font_size_percent)[ToggleFontSizePreferenceFragment.fontSizeValueToIndex(f, resources.getStringArray(C1715R.array.entryvalues_font_size))]);
    }

    /* access modifiers changed from: package-private */
    public void updateVibrationSummary(Preference preference) {
        Context context = getContext();
        Vibrator vibrator = (Vibrator) context.getSystemService(Vibrator.class);
        int i = Settings.System.getInt(context.getContentResolver(), "ring_vibration_intensity", vibrator.getDefaultRingVibrationIntensity());
        if (Settings.System.getInt(context.getContentResolver(), "vibrate_when_ringing", 0) == 0 && !isRampingRingerEnabled(context)) {
            i = 0;
        }
        CharSequence intensityString = VibrationIntensityPreferenceController.getIntensityString(context, i);
        int i2 = Settings.System.getInt(context.getContentResolver(), "notification_vibration_intensity", vibrator.getDefaultNotificationVibrationIntensity());
        CharSequence intensityString2 = VibrationIntensityPreferenceController.getIntensityString(context, i2);
        int i3 = Settings.System.getInt(context.getContentResolver(), "haptic_feedback_intensity", vibrator.getDefaultHapticFeedbackIntensity());
        if (Settings.System.getInt(context.getContentResolver(), "haptic_feedback_enabled", 0) == 0) {
            i3 = 0;
        }
        CharSequence intensityString3 = VibrationIntensityPreferenceController.getIntensityString(context, i3);
        if (this.mVibrationPreferenceScreen == null) {
            this.mVibrationPreferenceScreen = findPreference("vibration_preference_screen");
        }
        if (i == i3 && i == i2) {
            this.mVibrationPreferenceScreen.setSummary(intensityString);
            return;
        }
        this.mVibrationPreferenceScreen.setSummary((CharSequence) getString(C1715R.string.accessibility_vibration_summary, intensityString, intensityString2, intensityString3));
    }

    /* access modifiers changed from: private */
    public void updateLockScreenRotationCheckbox() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            this.mToggleLockScreenRotationPreference.setChecked(!RotationPolicy.isRotationLocked(activity));
        }
    }

    private void updateDisableAnimationsToggle() {
        String[] strArr = TOGGLE_ANIMATION_TARGETS;
        int length = strArr.length;
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = true;
                break;
            }
            if (!TextUtils.equals(Settings.Global.getString(getContentResolver(), strArr[i]), "0")) {
                break;
            }
            i++;
        }
        this.mToggleDisableAnimationsPreference.setChecked(z);
    }

    private void updateMasterMono() {
        boolean z = false;
        if (Settings.System.getIntForUser(getContentResolver(), "master_mono", 0, -2) == 1) {
            z = true;
        }
        this.mToggleMasterMonoPreference.setChecked(z);
    }

    private void updateAccessibilityShortcut(Preference preference) {
        CharSequence charSequence;
        if (AccessibilityManager.getInstance(getActivity()).getInstalledAccessibilityServiceList().isEmpty()) {
            this.mAccessibilityShortcutPreferenceScreen.setSummary((CharSequence) getString(C1715R.string.accessibility_no_services_installed));
            this.mAccessibilityShortcutPreferenceScreen.setEnabled(false);
            return;
        }
        this.mAccessibilityShortcutPreferenceScreen.setEnabled(true);
        if (AccessibilityUtils.isShortcutEnabled(getContext(), UserHandle.myUserId())) {
            charSequence = AccessibilityShortcutPreferenceFragment.getServiceName(getContext());
        } else {
            charSequence = getString(C1715R.string.accessibility_feature_state_off);
        }
        this.mAccessibilityShortcutPreferenceScreen.setSummary(charSequence);
    }

    private static void configureMagnificationPreferenceIfNeeded(Preference preference) {
        Context context = preference.getContext();
        if (!MagnificationPreferenceFragment.isApplicable(context.getResources())) {
            preference.setFragment(ToggleScreenMagnificationPreferenceFragment.class.getName());
            MagnificationGesturesPreferenceController.populateMagnificationGesturesPreferenceExtras(preference.getExtras(), context);
        }
    }
}
