package com.havoc.config.center.fragments;

import android.content.ContentResolver;
import android.os.Bundle;
import android.provider.Settings;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.SettingsPreferenceFragment;
import com.havoc.config.center.C1715R;
import com.havoc.config.center.preferences.Utils;
import com.havoc.support.preferences.SwitchPreference;

public class PowerMenu extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener {
    private SwitchPreference mPowerMenuAdvancedReboot;
    private SwitchPreference mPowerMenuLSTorch;
    private SwitchPreference mPowerMenuLockscreen;
    private SwitchPreference mPowerMenuReboot;
    private SwitchPreference mPowerMenuScreenrecord;
    private SwitchPreference mPowerMenuScreenshot;
    private SwitchPreference mPowermenuTorch;

    public int getMetricsCategory() {
        return 1999;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(C1715R.xml.power_menu);
        ContentResolver contentResolver = getActivity().getContentResolver();
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        this.mPowermenuTorch = (SwitchPreference) findPreference("powermenu_torch");
        this.mPowermenuTorch.setOnPreferenceChangeListener(this);
        boolean z = false;
        if (!Utils.deviceSupportsFlashLight(getActivity())) {
            preferenceScreen.removePreference(this.mPowermenuTorch);
            preferenceScreen.removePreference(this.mPowerMenuLSTorch);
        } else {
            this.mPowermenuTorch.setChecked(Settings.System.getInt(contentResolver, "powermenu_torch", 0) == 1);
        }
        this.mPowerMenuLockscreen = (SwitchPreference) findPreference("powermenu_lockscreen");
        this.mPowerMenuLockscreen.setChecked(Settings.System.getInt(getContentResolver(), "powermenu_lockscreen", 1) == 1);
        this.mPowerMenuLockscreen.setOnPreferenceChangeListener(this);
        this.mPowerMenuReboot = (SwitchPreference) findPreference("powermenu_ls_reboot");
        this.mPowerMenuReboot.setChecked(Settings.System.getInt(getContentResolver(), "powermenu_ls_reboot", 1) == 1);
        this.mPowerMenuReboot.setOnPreferenceChangeListener(this);
        this.mPowerMenuAdvancedReboot = (SwitchPreference) findPreference("powermenu_ls_advanced_reboot");
        this.mPowerMenuAdvancedReboot.setChecked(Settings.System.getInt(getContentResolver(), "powermenu_ls_advanced_reboot", 0) == 1);
        this.mPowerMenuAdvancedReboot.setOnPreferenceChangeListener(this);
        this.mPowerMenuScreenshot = (SwitchPreference) findPreference("powermenu_ls_screenshot");
        this.mPowerMenuScreenshot.setChecked(Settings.System.getInt(getContentResolver(), "powermenu_ls_screenshot", 0) == 1);
        this.mPowerMenuScreenshot.setOnPreferenceChangeListener(this);
        this.mPowerMenuScreenrecord = (SwitchPreference) findPreference("powermenu_ls_screenrecord");
        this.mPowerMenuScreenrecord.setChecked(Settings.System.getInt(getContentResolver(), "powermenu_ls_screenrecord", 0) == 1);
        this.mPowerMenuScreenrecord.setOnPreferenceChangeListener(this);
        this.mPowerMenuLSTorch = (SwitchPreference) findPreference("powermenu_ls_torch");
        SwitchPreference switchPreference = this.mPowerMenuLSTorch;
        if (Settings.System.getInt(getContentResolver(), "powermenu_ls_torch", 0) == 1) {
            z = true;
        }
        switchPreference.setChecked(z);
        this.mPowerMenuLSTorch.setOnPreferenceChangeListener(this);
        updateLockscreen();
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference == this.mPowermenuTorch) {
            Settings.System.putInt(getActivity().getContentResolver(), "powermenu_torch", ((Boolean) obj).booleanValue() ? 1 : 0);
            return true;
        } else if (preference == this.mPowerMenuLockscreen) {
            Settings.System.putInt(getActivity().getContentResolver(), "powermenu_lockscreen", ((Boolean) obj).booleanValue() ? 1 : 0);
            updateLockscreen();
            return true;
        } else if (preference == this.mPowerMenuReboot) {
            Settings.System.putInt(getActivity().getContentResolver(), "powermenu_ls_reboot", ((Boolean) obj).booleanValue() ? 1 : 0);
            return true;
        } else if (preference == this.mPowerMenuAdvancedReboot) {
            Settings.System.putInt(getActivity().getContentResolver(), "powermenu_ls_advanced_reboot", ((Boolean) obj).booleanValue() ? 1 : 0);
            return true;
        } else if (preference == this.mPowerMenuScreenshot) {
            Settings.System.putInt(getActivity().getContentResolver(), "powermenu_ls_screenshot", ((Boolean) obj).booleanValue() ? 1 : 0);
            return true;
        } else if (preference == this.mPowerMenuScreenrecord) {
            Settings.System.putInt(getActivity().getContentResolver(), "powermenu_ls_screenrecord", ((Boolean) obj).booleanValue() ? 1 : 0);
            return true;
        } else if (preference != this.mPowerMenuLSTorch) {
            return false;
        } else {
            Settings.System.putInt(getActivity().getContentResolver(), "powermenu_ls_torch", ((Boolean) obj).booleanValue() ? 1 : 0);
            return true;
        }
    }

    private void updateLockscreen() {
        if (Settings.System.getInt(getActivity().getContentResolver(), "powermenu_lockscreen", 1) == 1) {
            this.mPowerMenuReboot.setEnabled(true);
            this.mPowerMenuAdvancedReboot.setEnabled(true);
            this.mPowerMenuScreenshot.setEnabled(true);
            this.mPowerMenuScreenrecord.setEnabled(true);
            this.mPowerMenuLSTorch.setEnabled(true);
            return;
        }
        this.mPowerMenuReboot.setEnabled(false);
        this.mPowerMenuAdvancedReboot.setEnabled(false);
        this.mPowerMenuScreenshot.setEnabled(false);
        this.mPowerMenuScreenrecord.setEnabled(false);
        this.mPowerMenuLSTorch.setEnabled(false);
    }
}
