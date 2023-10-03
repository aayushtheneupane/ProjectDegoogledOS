package com.havoc.config.center.fragments;

import android.content.ContentResolver;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.widget.Toast;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceScreen;
import com.android.internal.util.havoc.Utils;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.gestures.SystemNavigationGestureSettings;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.SecureSettingSwitchPreference;
import com.havoc.support.preferences.SwitchPreference;
import com.havoc.support.preferences.SystemSettingListPreference;
import com.havoc.support.preferences.SystemSettingSwitchPreference;

public class Buttons extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener {
    private PreferenceCategory appSwitchCategory;
    private PreferenceCategory assistCategory;
    private PreferenceCategory backCategory;
    private PreferenceCategory backGestureCategory;
    private PreferenceCategory cameraCategory;
    private int deviceKeys;
    private PreferenceCategory homeCategory;
    private PreferenceCategory hwKeysCategory;
    private ListPreference mAppSwitchDoubleTap;
    private Preference mAppSwitchDoubleTapCustomApp;
    private ListPreference mAppSwitchLongPress;
    private Preference mAppSwitchLongPressCustomApp;
    private ListPreference mAssistDoubleTap;
    private ListPreference mAssistLongPress;
    private ListPreference mBackDoubleTap;
    private Preference mBackDoubleTapCustomApp;
    private ListPreference mBackLongPress;
    private Preference mBackLongPressCustomApp;
    private SystemSettingListPreference mBackSwipeType;
    private Preference mButtonBacklight;
    private ListPreference mCameraDoubleTap;
    private ListPreference mCameraLongPress;
    private Preference mGestureSystemNavigation;
    private Handler mHandler;
    private ListPreference mHomeDoubleTap;
    private Preference mHomeDoubleTapCustomApp;
    private ListPreference mHomeLongPress;
    private Preference mHomeLongPressCustomApp;
    /* access modifiers changed from: private */
    public boolean mIsNavSwitchingMode = false;
    private ListPreference mLeftSwipeActions;
    private Preference mLeftSwipeAppSelection;
    private ListPreference mMenuDoubleTap;
    private ListPreference mMenuLongPress;
    private ListPreference mNavBarLayout;
    private SystemSettingSwitchPreference mNavigationArrowKeys;
    private SwitchPreference mNavigationBar;
    private SystemSettingSwitchPreference mNavigationIMESpace;
    private ListPreference mRightSwipeActions;
    private Preference mRightSwipeAppSelection;
    private SystemSettingSwitchPreference mSwapKeys;
    private SecureSettingSwitchPreference mSwapNavbar;
    private SystemSettingListPreference mTimeout;
    private ListPreference mTorchPowerButton;
    private PreferenceCategory menuCategory;
    private ContentResolver resolver;

    public int getMetricsCategory() {
        return 1999;
    }

    public void onCreate(Bundle bundle) {
        PreferenceScreen preferenceScreen;
        PreferenceScreen preferenceScreen2;
        boolean z;
        PreferenceCategory preferenceCategory;
        PreferenceCategory preferenceCategory2;
        PreferenceCategory preferenceCategory3;
        super.onCreate(bundle);
        addPreferencesFromResource(C1715R.xml.config_center_buttons);
        this.resolver = getActivity().getContentResolver();
        PreferenceScreen preferenceScreen3 = getPreferenceScreen();
        this.mTorchPowerButton = (ListPreference) findPreference("torch_power_button_gesture");
        this.mTorchPowerButton.setValue(Integer.toString(Settings.Secure.getInt(this.resolver, "torch_power_button_gesture", 0)));
        ListPreference listPreference = this.mTorchPowerButton;
        listPreference.setSummary(listPreference.getEntry());
        this.mTorchPowerButton.setOnPreferenceChangeListener(this);
        this.mNavBarLayout = (ListPreference) findPreference("nav_bar_layout");
        this.mNavBarLayout.setOnPreferenceChangeListener(this);
        String string = Settings.Secure.getString(this.resolver, "sysui_nav_bar");
        if (string != null) {
            this.mNavBarLayout.setValue(string);
        } else {
            this.mNavBarLayout.setValueIndex(0);
        }
        this.mSwapNavbar = (SecureSettingSwitchPreference) findPreference("sysui_nav_bar_inverse");
        boolean z2 = getResources().getBoolean(17891387);
        this.deviceKeys = getResources().getInteger(17694793);
        int integer = getResources().getInteger(17694839);
        int integer2 = getResources().getInteger(17694807);
        int integer3 = getResources().getInteger(17694842);
        int integer4 = getResources().getInteger(17694810);
        int integer5 = getResources().getInteger(17694836);
        int integer6 = getResources().getInteger(17694805);
        int integer7 = getResources().getInteger(17694843);
        int integer8 = getResources().getInteger(17694811);
        int integer9 = getResources().getInteger(17694840);
        int integer10 = getResources().getInteger(17694808);
        int integer11 = getResources().getInteger(17694837);
        int integer12 = getResources().getInteger(17694806);
        boolean z3 = z2;
        int i = this.deviceKeys & 1;
        boolean z4 = (this.deviceKeys & 2) != 0;
        boolean z5 = (this.deviceKeys & 4) != 0;
        boolean z6 = (this.deviceKeys & 8) != 0;
        boolean z7 = (this.deviceKeys & 16) != 0;
        boolean z8 = (this.deviceKeys & 32) != 0;
        this.homeCategory = (PreferenceCategory) findPreference("home_key");
        this.backCategory = (PreferenceCategory) findPreference("back_key");
        this.menuCategory = (PreferenceCategory) findPreference("menu_key");
        this.assistCategory = (PreferenceCategory) findPreference("assist_key");
        this.appSwitchCategory = (PreferenceCategory) findPreference("app_switch_key");
        this.cameraCategory = (PreferenceCategory) findPreference("camera_key");
        this.backGestureCategory = (PreferenceCategory) findPreference("back_gesture");
        this.hwKeysCategory = (PreferenceCategory) findPreference("hw_keys");
        this.mGestureSystemNavigation = findPreference("gesture_system_navigation");
        if (Utils.isThemeEnabled("com.android.internal.systemui.navbar.gestural") || Utils.isThemeEnabled("com.android.internal.systemui.navbar.gestural_wide_back") || Utils.isThemeEnabled("com.android.internal.systemui.navbar.gestural_extra_wide_back") || Utils.isThemeEnabled("com.android.internal.systemui.navbar.gestural_narrow_back")) {
            preferenceScreen = preferenceScreen3;
            this.mNavBarLayout.setVisible(false);
            this.mSwapNavbar.setVisible(false);
        } else {
            preferenceScreen = preferenceScreen3;
            this.mNavBarLayout.setVisible(true);
            this.mSwapNavbar.setVisible(true);
        }
        this.mBackLongPressCustomApp = findPreference("back_key_long_press_custom_app");
        this.mBackDoubleTapCustomApp = findPreference("back_key_double_tap_custom_app");
        this.mHomeLongPressCustomApp = findPreference("home_key_long_press_custom_app");
        this.mHomeDoubleTapCustomApp = findPreference("home_key_double_tap_custom_app");
        this.mAppSwitchLongPressCustomApp = findPreference("app_switch_key_long_press_custom_app");
        this.mAppSwitchDoubleTapCustomApp = findPreference("app_switch_key_double_tap_custom_app");
        this.mNavigationArrowKeys = (SystemSettingSwitchPreference) findPreference("navigation_bar_menu_arrow_keys");
        this.mNavigationIMESpace = (SystemSettingSwitchPreference) findPreference("navigation_bar_ime_space");
        this.mNavigationIMESpace.setOnPreferenceChangeListener(this);
        this.mNavigationBar = (SwitchPreference) findPreference("force_show_navbar");
        this.mNavigationBar.setChecked(isNavbarVisible());
        this.mNavigationBar.setOnPreferenceChangeListener(this);
        this.mBackLongPress = (ListPreference) findPreference("back_key_long_press");
        boolean z9 = z5;
        int intForUser = Settings.System.getIntForUser(getContentResolver(), "key_back_long_press_action", integer, -2);
        this.mBackLongPress.setValue(String.valueOf(intForUser));
        ListPreference listPreference2 = this.mBackLongPress;
        listPreference2.setSummary(listPreference2.getEntry());
        this.mBackLongPress.setOnPreferenceChangeListener(this);
        this.mBackDoubleTap = (ListPreference) findPreference("back_key_double_tap");
        int intForUser2 = Settings.System.getIntForUser(getContentResolver(), "key_back_double_tap_action", integer2, -2);
        this.mBackDoubleTap.setValue(String.valueOf(intForUser2));
        ListPreference listPreference3 = this.mBackDoubleTap;
        listPreference3.setSummary(listPreference3.getEntry());
        this.mBackDoubleTap.setOnPreferenceChangeListener(this);
        this.mHomeLongPress = (ListPreference) findPreference("home_key_long_press");
        int intForUser3 = Settings.System.getIntForUser(getContentResolver(), "key_home_long_press_action", integer3, -2);
        this.mHomeLongPress.setValue(String.valueOf(intForUser3));
        ListPreference listPreference4 = this.mHomeLongPress;
        listPreference4.setSummary(listPreference4.getEntry());
        this.mHomeLongPress.setOnPreferenceChangeListener(this);
        this.mHomeDoubleTap = (ListPreference) findPreference("home_key_double_tap");
        int intForUser4 = Settings.System.getIntForUser(getContentResolver(), "key_home_double_tap_action", integer4, -2);
        this.mHomeDoubleTap.setValue(String.valueOf(intForUser4));
        ListPreference listPreference5 = this.mHomeDoubleTap;
        listPreference5.setSummary(listPreference5.getEntry());
        this.mHomeDoubleTap.setOnPreferenceChangeListener(this);
        this.mAppSwitchLongPress = (ListPreference) findPreference("app_switch_key_long_press");
        int intForUser5 = Settings.System.getIntForUser(getContentResolver(), "key_app_switch_long_press_action", integer5, -2);
        this.mAppSwitchLongPress.setValue(String.valueOf(intForUser5));
        ListPreference listPreference6 = this.mAppSwitchLongPress;
        listPreference6.setSummary(listPreference6.getEntry());
        this.mAppSwitchLongPress.setOnPreferenceChangeListener(this);
        this.mAppSwitchDoubleTap = (ListPreference) findPreference("app_switch_key_double_tap");
        int intForUser6 = Settings.System.getIntForUser(getContentResolver(), "key_app_switch_double_tap_action", integer6, -2);
        this.mAppSwitchDoubleTap.setValue(String.valueOf(intForUser6));
        ListPreference listPreference7 = this.mAppSwitchDoubleTap;
        listPreference7.setSummary(listPreference7.getEntry());
        this.mAppSwitchDoubleTap.setOnPreferenceChangeListener(this);
        this.mMenuLongPress = (ListPreference) findPreference("menu_key_long_press");
        this.mMenuLongPress.setValue(String.valueOf(Settings.System.getIntForUser(getContentResolver(), "key_menu_long_press_action", integer7, -2)));
        ListPreference listPreference8 = this.mMenuLongPress;
        listPreference8.setSummary(listPreference8.getEntry());
        this.mMenuLongPress.setOnPreferenceChangeListener(this);
        this.mMenuDoubleTap = (ListPreference) findPreference("menu_key_double_tap");
        this.mMenuDoubleTap.setValue(String.valueOf(Settings.System.getIntForUser(getContentResolver(), "key_menu_double_tap_action", integer8, -2)));
        ListPreference listPreference9 = this.mMenuDoubleTap;
        listPreference9.setSummary(listPreference9.getEntry());
        this.mMenuDoubleTap.setOnPreferenceChangeListener(this);
        this.mCameraLongPress = (ListPreference) findPreference("camera_key_long_press");
        this.mCameraLongPress.setValue(String.valueOf(Settings.System.getIntForUser(getContentResolver(), "key_camera_long_press_action", integer9, -2)));
        ListPreference listPreference10 = this.mCameraLongPress;
        listPreference10.setSummary(listPreference10.getEntry());
        this.mCameraLongPress.setOnPreferenceChangeListener(this);
        this.mCameraDoubleTap = (ListPreference) findPreference("camera_key_double_tap");
        this.mCameraDoubleTap.setValue(String.valueOf(Settings.System.getIntForUser(getContentResolver(), "key_camera_double_tap_action", integer10, -2)));
        ListPreference listPreference11 = this.mCameraDoubleTap;
        listPreference11.setSummary(listPreference11.getEntry());
        this.mCameraDoubleTap.setOnPreferenceChangeListener(this);
        this.mAssistLongPress = (ListPreference) findPreference("assist_key_long_press");
        this.mAssistLongPress.setValue(String.valueOf(Settings.System.getIntForUser(getContentResolver(), "key_assist_long_press_action", integer11, -2)));
        ListPreference listPreference12 = this.mAssistLongPress;
        listPreference12.setSummary(listPreference12.getEntry());
        this.mAssistLongPress.setOnPreferenceChangeListener(this);
        this.mAssistDoubleTap = (ListPreference) findPreference("assist_key_double_tap");
        this.mAssistDoubleTap.setValue(String.valueOf(Settings.System.getIntForUser(getContentResolver(), "key_assist_double_tap_action", integer12, -2)));
        ListPreference listPreference13 = this.mAssistDoubleTap;
        listPreference13.setSummary(listPreference13.getEntry());
        this.mAssistDoubleTap.setOnPreferenceChangeListener(this);
        int intForUser7 = Settings.System.getIntForUser(this.resolver, "left_long_back_swipe_action", 0, -2);
        this.mLeftSwipeActions = (ListPreference) findPreference("left_swipe_actions");
        this.mLeftSwipeActions.setValue(Integer.toString(intForUser7));
        ListPreference listPreference14 = this.mLeftSwipeActions;
        listPreference14.setSummary(listPreference14.getEntry());
        this.mLeftSwipeActions.setOnPreferenceChangeListener(this);
        int intForUser8 = Settings.System.getIntForUser(this.resolver, "right_long_back_swipe_action", 0, -2);
        this.mRightSwipeActions = (ListPreference) findPreference("right_swipe_actions");
        this.mRightSwipeActions.setValue(Integer.toString(intForUser8));
        ListPreference listPreference15 = this.mRightSwipeActions;
        listPreference15.setSummary(listPreference15.getEntry());
        this.mRightSwipeActions.setOnPreferenceChangeListener(this);
        this.mLeftSwipeAppSelection = findPreference("left_swipe_app_action");
        this.mLeftSwipeAppSelection.setEnabled(Settings.System.getIntForUser(this.resolver, "left_long_back_swipe_action", 0, -2) == 5);
        this.mRightSwipeAppSelection = findPreference("right_swipe_app_action");
        this.mRightSwipeAppSelection.setEnabled(Settings.System.getIntForUser(this.resolver, "right_long_back_swipe_action", 0, -2) == 5);
        this.mTimeout = (SystemSettingListPreference) findPreference("long_back_swipe_timeout");
        this.mBackSwipeType = (SystemSettingListPreference) findPreference("back_swipe_type");
        int intForUser9 = Settings.System.getIntForUser(this.resolver, "back_swipe_type", 0, -2);
        this.mBackSwipeType.setValue(String.valueOf(intForUser9));
        SystemSettingListPreference systemSettingListPreference = this.mBackSwipeType;
        systemSettingListPreference.setSummary(systemSettingListPreference.getEntry());
        this.mBackSwipeType.setOnPreferenceChangeListener(this);
        this.mTimeout.setEnabled(intForUser9 == 0);
        this.mButtonBacklight = findPreference("button_backlight");
        this.mSwapKeys = (SystemSettingSwitchPreference) findPreference("swap_navigation_keys");
        if (z9 || (preferenceCategory3 = this.menuCategory) == null) {
            preferenceScreen2 = preferenceScreen;
        } else {
            preferenceScreen2 = preferenceScreen;
            preferenceScreen2.removePreference(preferenceCategory3);
        }
        if (!z6 && (preferenceCategory2 = this.assistCategory) != null) {
            preferenceScreen2.removePreference(preferenceCategory2);
        }
        if (!z8 && (preferenceCategory = this.cameraCategory) != null) {
            preferenceScreen2.removePreference(preferenceCategory);
        }
        if (this.deviceKeys == 0) {
            preferenceScreen2.removePreference(this.hwKeysCategory);
            preferenceScreen2.removePreference(this.menuCategory);
            preferenceScreen2.removePreference(this.assistCategory);
            preferenceScreen2.removePreference(this.cameraCategory);
        }
        if (!(z4 && (z9 || z7))) {
            z = false;
            this.mSwapKeys.setVisible(false);
        } else {
            z = false;
        }
        if (!z3) {
            this.mButtonBacklight.setVisible(z);
        }
        this.mHandler = new Handler();
        updateHwKeys();
        navbarCheck();
        customAppCheck();
        this.mBackLongPressCustomApp.setVisible(this.mBackLongPress.getEntryValues()[intForUser].equals("16"));
        this.mBackDoubleTapCustomApp.setVisible(this.mBackDoubleTap.getEntryValues()[intForUser2].equals("16"));
        this.mHomeLongPressCustomApp.setVisible(this.mHomeLongPress.getEntryValues()[intForUser3].equals("16"));
        this.mHomeDoubleTapCustomApp.setVisible(this.mHomeDoubleTap.getEntryValues()[intForUser4].equals("16"));
        this.mAppSwitchLongPressCustomApp.setVisible(this.mAppSwitchLongPress.getEntryValues()[intForUser5].equals("16"));
        this.mAppSwitchDoubleTapCustomApp.setVisible(this.mAppSwitchDoubleTap.getEntryValues()[intForUser6].equals("16"));
        this.mLeftSwipeAppSelection.setVisible(this.mLeftSwipeActions.getEntryValues()[intForUser7].equals("5"));
        this.mLeftSwipeAppSelection.setVisible(this.mRightSwipeActions.getEntryValues()[intForUser8].equals("5"));
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        this.resolver = getActivity().getContentResolver();
        boolean z = false;
        boolean z2 = Settings.Secure.getInt(this.resolver, "camera_double_tap_power_gesture_disabled", 1) == 0;
        if (preference == this.mTorchPowerButton) {
            String str = (String) obj;
            int intValue = Integer.valueOf(str).intValue();
            int findIndexOfValue = this.mTorchPowerButton.findIndexOfValue(str);
            ListPreference listPreference = this.mTorchPowerButton;
            listPreference.setSummary(listPreference.getEntries()[findIndexOfValue]);
            Settings.Secure.putInt(this.resolver, "torch_power_button_gesture", intValue);
            if (intValue == 1 && z2) {
                Settings.Secure.putInt(this.resolver, "camera_double_tap_power_gesture_disabled", 1);
                Toast.makeText(getActivity(), C1715R.string.torch_power_button_gesture_dt_toast, 0).show();
            }
            return true;
        } else if (preference == this.mNavBarLayout) {
            Settings.Secure.putString(this.resolver, "sysui_nav_bar", (String) obj);
            return true;
        } else if (preference == this.mNavigationIMESpace) {
            navbarCheck();
            SystemNavigationGestureSettings.updateNavigationBarOverlays(getActivity());
            return true;
        } else if (preference == this.mNavigationBar) {
            boolean booleanValue = ((Boolean) obj).booleanValue();
            if (this.mIsNavSwitchingMode) {
                return false;
            }
            this.mIsNavSwitchingMode = true;
            Settings.System.putInt(getActivity().getContentResolver(), "navigation_bar_show_new", booleanValue ? 1 : 0);
            navbarCheck();
            updateHwKeys();
            this.mHandler.postDelayed(new Runnable() {
                public void run() {
                    boolean unused = Buttons.this.mIsNavSwitchingMode = false;
                }
            }, 1500);
            return true;
        } else if (preference == this.mBackLongPress) {
            String str2 = (String) obj;
            Settings.System.putIntForUser(getActivity().getContentResolver(), "key_back_long_press_action", Integer.parseInt(str2), -2);
            int findIndexOfValue2 = this.mBackLongPress.findIndexOfValue(str2);
            ListPreference listPreference2 = this.mBackLongPress;
            listPreference2.setSummary(listPreference2.getEntries()[findIndexOfValue2]);
            customAppCheck();
            this.mBackLongPressCustomApp.setVisible(this.mBackLongPress.getEntryValues()[findIndexOfValue2].equals("16"));
            return true;
        } else if (preference == this.mBackDoubleTap) {
            String str3 = (String) obj;
            Settings.System.putIntForUser(getActivity().getContentResolver(), "key_back_double_tap_action", Integer.parseInt(str3), -2);
            int findIndexOfValue3 = this.mBackDoubleTap.findIndexOfValue(str3);
            ListPreference listPreference3 = this.mBackDoubleTap;
            listPreference3.setSummary(listPreference3.getEntries()[findIndexOfValue3]);
            this.mBackDoubleTapCustomApp.setVisible(this.mBackDoubleTap.getEntryValues()[findIndexOfValue3].equals("16"));
            return true;
        } else if (preference == this.mHomeLongPress) {
            String str4 = (String) obj;
            Settings.System.putIntForUser(getActivity().getContentResolver(), "key_home_long_press_action", Integer.parseInt(str4), -2);
            int findIndexOfValue4 = this.mHomeLongPress.findIndexOfValue(str4);
            ListPreference listPreference4 = this.mHomeLongPress;
            listPreference4.setSummary(listPreference4.getEntries()[findIndexOfValue4]);
            this.mHomeLongPressCustomApp.setVisible(this.mHomeLongPress.getEntryValues()[findIndexOfValue4].equals("16"));
            return true;
        } else if (preference == this.mHomeDoubleTap) {
            String str5 = (String) obj;
            Settings.System.putIntForUser(getActivity().getContentResolver(), "key_home_double_tap_action", Integer.parseInt(str5), -2);
            int findIndexOfValue5 = this.mHomeDoubleTap.findIndexOfValue(str5);
            ListPreference listPreference5 = this.mHomeDoubleTap;
            listPreference5.setSummary(listPreference5.getEntries()[findIndexOfValue5]);
            this.mHomeDoubleTapCustomApp.setVisible(this.mHomeDoubleTap.getEntryValues()[findIndexOfValue5].equals("16"));
            return true;
        } else if (preference == this.mAppSwitchLongPress) {
            String str6 = (String) obj;
            Settings.System.putIntForUser(getActivity().getContentResolver(), "key_app_switch_long_press_action", Integer.parseInt(str6), -2);
            int findIndexOfValue6 = this.mAppSwitchLongPress.findIndexOfValue(str6);
            ListPreference listPreference6 = this.mAppSwitchLongPress;
            listPreference6.setSummary(listPreference6.getEntries()[findIndexOfValue6]);
            this.mAppSwitchLongPressCustomApp.setVisible(this.mAppSwitchLongPress.getEntryValues()[findIndexOfValue6].equals("16"));
            return true;
        } else if (preference == this.mAppSwitchDoubleTap) {
            String str7 = (String) obj;
            Settings.System.putIntForUser(getActivity().getContentResolver(), "key_app_switch_double_tap_action", Integer.parseInt(str7), -2);
            int findIndexOfValue7 = this.mAppSwitchDoubleTap.findIndexOfValue(str7);
            ListPreference listPreference7 = this.mAppSwitchDoubleTap;
            listPreference7.setSummary(listPreference7.getEntries()[findIndexOfValue7]);
            this.mAppSwitchDoubleTapCustomApp.setVisible(this.mAppSwitchDoubleTap.getEntryValues()[findIndexOfValue7].equals("16"));
            return true;
        } else if (preference == this.mMenuLongPress) {
            String str8 = (String) obj;
            Settings.System.putIntForUser(getActivity().getContentResolver(), "key_menu_long_press_action", Integer.parseInt(str8), -2);
            int findIndexOfValue8 = this.mMenuLongPress.findIndexOfValue(str8);
            ListPreference listPreference8 = this.mMenuLongPress;
            listPreference8.setSummary(listPreference8.getEntries()[findIndexOfValue8]);
            return true;
        } else if (preference == this.mMenuDoubleTap) {
            String str9 = (String) obj;
            Settings.System.putIntForUser(getActivity().getContentResolver(), "key_menu_double_tap_action", Integer.parseInt(str9), -2);
            int findIndexOfValue9 = this.mMenuDoubleTap.findIndexOfValue(str9);
            ListPreference listPreference9 = this.mMenuDoubleTap;
            listPreference9.setSummary(listPreference9.getEntries()[findIndexOfValue9]);
            return true;
        } else if (preference == this.mCameraLongPress) {
            String str10 = (String) obj;
            Settings.System.putIntForUser(getActivity().getContentResolver(), "key_camera_long_press_action", Integer.parseInt(str10), -2);
            int findIndexOfValue10 = this.mCameraLongPress.findIndexOfValue(str10);
            ListPreference listPreference10 = this.mCameraLongPress;
            listPreference10.setSummary(listPreference10.getEntries()[findIndexOfValue10]);
            return true;
        } else if (preference == this.mCameraDoubleTap) {
            String str11 = (String) obj;
            Settings.System.putIntForUser(getActivity().getContentResolver(), "key_camera_double_tap_action", Integer.parseInt(str11), -2);
            int findIndexOfValue11 = this.mCameraDoubleTap.findIndexOfValue(str11);
            ListPreference listPreference11 = this.mCameraDoubleTap;
            listPreference11.setSummary(listPreference11.getEntries()[findIndexOfValue11]);
            return true;
        } else if (preference == this.mAssistLongPress) {
            String str12 = (String) obj;
            Settings.System.putIntForUser(getActivity().getContentResolver(), "key_assist_long_press_action", Integer.parseInt(str12), -2);
            int findIndexOfValue12 = this.mAssistLongPress.findIndexOfValue(str12);
            ListPreference listPreference12 = this.mAssistLongPress;
            listPreference12.setSummary(listPreference12.getEntries()[findIndexOfValue12]);
            return true;
        } else if (preference == this.mAssistDoubleTap) {
            String str13 = (String) obj;
            Settings.System.putIntForUser(getActivity().getContentResolver(), "key_assist_double_tap_action", Integer.parseInt(str13), -2);
            int findIndexOfValue13 = this.mAssistDoubleTap.findIndexOfValue(str13);
            ListPreference listPreference13 = this.mAssistDoubleTap;
            listPreference13.setSummary(listPreference13.getEntries()[findIndexOfValue13]);
            return true;
        } else if (preference == this.mLeftSwipeActions) {
            String str14 = (String) obj;
            int intValue2 = Integer.valueOf(str14).intValue();
            Settings.System.putIntForUser(getContentResolver(), "left_long_back_swipe_action", intValue2, -2);
            int findIndexOfValue14 = this.mLeftSwipeActions.findIndexOfValue(str14);
            ListPreference listPreference14 = this.mLeftSwipeActions;
            listPreference14.setSummary(listPreference14.getEntries()[findIndexOfValue14]);
            Preference preference2 = this.mLeftSwipeAppSelection;
            if (intValue2 == 5) {
                z = true;
            }
            preference2.setEnabled(z);
            actionPreferenceReload();
            customAppCheck();
            return true;
        } else if (preference == this.mRightSwipeActions) {
            String str15 = (String) obj;
            int intValue3 = Integer.valueOf(str15).intValue();
            Settings.System.putIntForUser(getContentResolver(), "right_long_back_swipe_action", intValue3, -2);
            int findIndexOfValue15 = this.mRightSwipeActions.findIndexOfValue(str15);
            ListPreference listPreference15 = this.mRightSwipeActions;
            listPreference15.setSummary(listPreference15.getEntries()[findIndexOfValue15]);
            Preference preference3 = this.mRightSwipeAppSelection;
            if (intValue3 == 5) {
                z = true;
            }
            preference3.setEnabled(z);
            actionPreferenceReload();
            customAppCheck();
            return true;
        } else if (preference != this.mBackSwipeType) {
            return false;
        } else {
            String str16 = (String) obj;
            int parseInt = Integer.parseInt(str16);
            int findIndexOfValue16 = this.mBackSwipeType.findIndexOfValue(str16);
            Settings.System.putInt(getActivity().getContentResolver(), "back_swipe_type", parseInt);
            SystemSettingListPreference systemSettingListPreference = this.mBackSwipeType;
            systemSettingListPreference.setSummary(systemSettingListPreference.getEntries()[findIndexOfValue16]);
            SystemSettingListPreference systemSettingListPreference2 = this.mTimeout;
            if (parseInt == 0) {
                z = true;
            }
            systemSettingListPreference2.setEnabled(z);
            return true;
        }
    }

    public void onResume() {
        super.onResume();
        navbarCheck();
        customAppCheck();
        updateHwKeys();
        actionPreferenceReload();
    }

    public void onPause() {
        super.onPause();
        navbarCheck();
        customAppCheck();
        updateHwKeys();
        actionPreferenceReload();
    }

    private void customAppCheck() {
        this.mBackLongPressCustomApp.setSummary((CharSequence) Settings.System.getStringForUser(getActivity().getContentResolver(), "key_back_long_press_custom_app_fr_name", -2));
        this.mBackDoubleTapCustomApp.setSummary((CharSequence) Settings.System.getStringForUser(getActivity().getContentResolver(), "key_back_double_tap_custom_app_fr_name", -2));
        this.mHomeLongPressCustomApp.setSummary((CharSequence) Settings.System.getStringForUser(getActivity().getContentResolver(), "key_home_long_press_custom_app_fr_name", -2));
        this.mHomeDoubleTapCustomApp.setSummary((CharSequence) Settings.System.getStringForUser(getActivity().getContentResolver(), "key_home_double_tap_custom_app_fr_name", -2));
        this.mAppSwitchLongPressCustomApp.setSummary((CharSequence) Settings.System.getStringForUser(getActivity().getContentResolver(), "key_app_switch_long_press_custom_app_fr_name", -2));
        this.mAppSwitchDoubleTapCustomApp.setSummary((CharSequence) Settings.System.getStringForUser(getActivity().getContentResolver(), "key_app_switch_double_tap_custom_app_fr_name", -2));
        this.mLeftSwipeAppSelection.setSummary((CharSequence) Settings.System.getStringForUser(getActivity().getContentResolver(), "left_long_back_swipe_app_fr_action", -2));
        this.mRightSwipeAppSelection.setSummary((CharSequence) Settings.System.getStringForUser(getActivity().getContentResolver(), "right_long_back_swipe_app_fr_action", -2));
    }

    private void updateHwKeys() {
        if (isNavbarVisible()) {
            this.hwKeysCategory.setEnabled(false);
        } else {
            this.hwKeysCategory.setEnabled(true);
        }
    }

    private boolean isNavbarVisible() {
        return Settings.System.getInt(getActivity().getContentResolver(), "navigation_bar_show_new", Utils.deviceSupportNavigationBar(getActivity()) ? 1 : 0) == 1;
    }

    private void navbarCheck() {
        this.deviceKeys = getResources().getInteger(17694793);
        if (this.deviceKeys == 0) {
            this.homeCategory.setEnabled(true);
            this.backCategory.setEnabled(true);
            this.menuCategory.setEnabled(true);
            this.assistCategory.setEnabled(true);
            this.appSwitchCategory.setEnabled(true);
            this.cameraCategory.setEnabled(true);
            this.mNavigationArrowKeys.setEnabled(true);
            this.mNavigationIMESpace.setEnabled(true);
            this.mNavBarLayout.setEnabled(true);
            this.mSwapNavbar.setEnabled(true);
        } else if (isNavbarVisible()) {
            this.homeCategory.setEnabled(true);
            this.backCategory.setEnabled(true);
            this.menuCategory.setEnabled(true);
            this.assistCategory.setEnabled(true);
            this.appSwitchCategory.setEnabled(true);
            this.cameraCategory.setEnabled(true);
            this.mNavigationArrowKeys.setEnabled(true);
            this.mNavigationIMESpace.setEnabled(true);
            this.mNavBarLayout.setEnabled(true);
            this.mSwapNavbar.setEnabled(true);
            this.mGestureSystemNavigation.setEnabled(true);
        } else {
            this.homeCategory.setEnabled(true);
            this.backCategory.setEnabled(true);
            this.menuCategory.setEnabled(true);
            this.assistCategory.setEnabled(true);
            this.appSwitchCategory.setEnabled(true);
            this.cameraCategory.setEnabled(true);
            this.mNavigationArrowKeys.setEnabled(false);
            this.mNavigationIMESpace.setEnabled(false);
            this.mNavBarLayout.setEnabled(false);
            this.mSwapNavbar.setEnabled(false);
            this.mGestureSystemNavigation.setEnabled(false);
        }
        if ((Utils.isThemeEnabled("com.android.internal.systemui.navbar.gestural") || Utils.isThemeEnabled("com.android.internal.systemui.navbar.gestural_wide_back") || Utils.isThemeEnabled("com.android.internal.systemui.navbar.gestural_extra_wide_back") || Utils.isThemeEnabled("com.android.internal.systemui.navbar.gestural_narrow_back")) && isNavbarVisible()) {
            this.homeCategory.setVisible(false);
            this.backCategory.setVisible(false);
            this.menuCategory.setVisible(false);
            this.assistCategory.setVisible(false);
            this.appSwitchCategory.setVisible(false);
            this.cameraCategory.setVisible(false);
            this.backGestureCategory.setVisible(true);
        } else {
            this.homeCategory.setVisible(true);
            this.backCategory.setVisible(true);
            this.menuCategory.setVisible(true);
            this.assistCategory.setVisible(true);
            this.appSwitchCategory.setVisible(true);
            this.cameraCategory.setVisible(true);
            this.backGestureCategory.setVisible(false);
        }
        if (Utils.isThemeEnabled("com.android.internal.systemui.navbar.twobutton") && isNavbarVisible()) {
            this.homeCategory.setEnabled(true);
            this.backCategory.setEnabled(true);
            this.menuCategory.setVisible(false);
            this.assistCategory.setVisible(false);
            this.appSwitchCategory.setVisible(false);
            this.cameraCategory.setVisible(false);
            this.backGestureCategory.setVisible(false);
        }
        if (Utils.isThemeEnabled("com.android.internal.systemui.navbar.threebutton")) {
            this.mGestureSystemNavigation.setSummary((CharSequence) getString(C1715R.string.legacy_navigation_title));
            this.backGestureCategory.setVisible(false);
        } else if (Utils.isThemeEnabled("com.android.internal.systemui.navbar.twobutton")) {
            this.mGestureSystemNavigation.setSummary((CharSequence) getString(C1715R.string.swipe_up_to_switch_apps_title));
            this.backGestureCategory.setVisible(false);
        } else if (Utils.isThemeEnabled("com.android.internal.systemui.navbar.gestural") || Utils.isThemeEnabled("com.android.internal.systemui.navbar.gestural_wide_back") || Utils.isThemeEnabled("com.android.internal.systemui.navbar.gestural_extra_wide_back") || Utils.isThemeEnabled("com.android.internal.systemui.navbar.gestural_narrow_back")) {
            this.mGestureSystemNavigation.setSummary((CharSequence) getString(C1715R.string.edge_to_edge_navigation_title));
            this.backGestureCategory.setVisible(true);
        }
        int intForUser = Settings.System.getIntForUser(getContentResolver(), "navigation_handle_width", 1, -2);
        boolean z = Settings.System.getIntForUser(getContentResolver(), "navigation_bar_ime_space", 1, -2) != 0;
        if (intForUser == 0) {
            this.mNavigationIMESpace.setVisible(true);
        } else {
            this.mNavigationIMESpace.setVisible(false);
        }
        if (intForUser != 0 || z) {
            this.mNavigationArrowKeys.setEnabled(true);
        } else {
            this.mNavigationArrowKeys.setEnabled(false);
        }
    }

    private void actionPreferenceReload() {
        int intForUser = Settings.System.getIntForUser(getContentResolver(), "left_long_back_swipe_action", 0, -2);
        int intForUser2 = Settings.System.getIntForUser(getContentResolver(), "right_long_back_swipe_action", 0, -2);
        this.mLeftSwipeActions.setValue(Integer.toString(intForUser));
        ListPreference listPreference = this.mLeftSwipeActions;
        listPreference.setSummary(listPreference.getEntry());
        this.mRightSwipeActions.setValue(Integer.toString(intForUser2));
        ListPreference listPreference2 = this.mRightSwipeActions;
        listPreference2.setSummary(listPreference2.getEntry());
        this.mLeftSwipeAppSelection.setVisible(this.mLeftSwipeActions.getEntryValues()[intForUser].equals("5"));
        this.mRightSwipeAppSelection.setVisible(this.mRightSwipeActions.getEntryValues()[intForUser2].equals("5"));
    }
}
