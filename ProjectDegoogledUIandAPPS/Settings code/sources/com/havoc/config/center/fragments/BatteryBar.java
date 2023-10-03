package com.havoc.config.center.fragments;

import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import com.android.internal.util.havoc.Utils;
import com.android.settings.SettingsPreferenceFragment;
import com.havoc.config.center.C1715R;
import com.havoc.support.colorpicker.ColorPickerPreference;
import com.havoc.support.preferences.SystemSettingListPreference;
import com.havoc.support.preferences.SystemSettingSeekBarPreference;
import com.havoc.support.preferences.SystemSettingSwitchPreference;

public class BatteryBar extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener, CompoundButton.OnCheckedChangeListener {
    private SystemSettingSwitchPreference mAnimate;
    private SystemSettingSwitchPreference mCharging;
    private ColorPickerPreference mChargingColor;
    private ColorPickerPreference mChargingColorDark;
    private PreferenceCategory mDark;
    private PreferenceCategory mLight;
    private ListPreference mLocation;
    private SystemSettingListPreference mStyle;
    private View mSwitchBar;
    private TextView mTextView;
    private SystemSettingSeekBarPreference mThickness;

    public int getMetricsCategory() {
        return 1999;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(C1715R.xml.battery_bar);
        this.mThickness = (SystemSettingSeekBarPreference) findPreference("statusbar_battery_bar_thickness");
        this.mStyle = (SystemSettingListPreference) findPreference("statusbar_battery_bar_style");
        this.mAnimate = (SystemSettingSwitchPreference) findPreference("statusbar_battery_bar_animate");
        this.mCharging = (SystemSettingSwitchPreference) findPreference("statusbar_battery_bar_enable_charging_color");
        this.mChargingColorDark = (ColorPickerPreference) findPreference("statusbar_battery_bar_charging_dark_color");
        this.mLight = (PreferenceCategory) findPreference("light_statusbar");
        this.mDark = (PreferenceCategory) findPreference("dark_statusbar");
        boolean z = Utils.isThemeEnabled("com.android.internal.systemui.navbar.threebutton") || Utils.isThemeEnabled("com.android.internal.systemui.navbar.twobutton");
        this.mLocation = (ListPreference) findPreference("statusbar_battery_bar_location");
        int i = Settings.System.getInt(getContentResolver(), "statusbar_battery_bar_location", 0);
        CharSequence[] charSequenceArr = {getResources().getString(C1715R.string.battery_bar_location_statusbar_top), getResources().getString(C1715R.string.battery_bar_location_statusbar_bottom), getResources().getString(C1715R.string.battery_bar_location_navbar_top), getResources().getString(C1715R.string.battery_bar_location_navbar_bottom)};
        CharSequence[] charSequenceArr2 = {getResources().getString(C1715R.string.battery_bar_location_statusbar_top), getResources().getString(C1715R.string.battery_bar_location_statusbar_bottom), getResources().getString(C1715R.string.battery_bar_location_navbar_bottom)};
        CharSequence[] charSequenceArr3 = {"1", "2", "3", "4"};
        CharSequence[] charSequenceArr4 = {"1", "2", "4"};
        ListPreference listPreference = this.mLocation;
        if (!z) {
            charSequenceArr = charSequenceArr2;
        }
        listPreference.setEntries(charSequenceArr);
        ListPreference listPreference2 = this.mLocation;
        if (!z) {
            charSequenceArr3 = charSequenceArr4;
        }
        listPreference2.setEntryValues(charSequenceArr3);
        this.mLocation.setValue(String.valueOf(i));
        ListPreference listPreference3 = this.mLocation;
        listPreference3.setSummary(listPreference3.getEntry());
        this.mLocation.setOnPreferenceChangeListener(this);
        this.mChargingColor = (ColorPickerPreference) findPreference("statusbar_battery_bar_charging_color");
        int i2 = Settings.System.getInt(getContentResolver(), "statusbar_battery_bar_charging_color", -256);
        this.mChargingColor.setNewPreviewColor(i2);
        String format = String.format("#%08x", new Object[]{Integer.valueOf(i2 & -256)});
        if (format.equals("#ffffff00")) {
            this.mChargingColor.setSummary((int) C1715R.string.default_string);
        } else {
            this.mChargingColor.setSummary((CharSequence) format);
        }
        this.mChargingColor.setOnPreferenceChangeListener(this);
        this.mChargingColorDark = (ColorPickerPreference) findPreference("statusbar_battery_bar_charging_dark_color");
        int i3 = Settings.System.getInt(getContentResolver(), "statusbar_battery_bar_charging_dark_color", -15906911);
        this.mChargingColorDark.setNewPreviewColor(i3);
        String format2 = String.format("#%08x", new Object[]{Integer.valueOf(i3 & -15906911)});
        if (format2.equals("#ff0d47a1")) {
            this.mChargingColorDark.setSummary((int) C1715R.string.default_string);
        } else {
            this.mChargingColorDark.setSummary((CharSequence) format2);
        }
        this.mChargingColorDark.setOnPreferenceChangeListener(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = LayoutInflater.from(getContext()).inflate(C1715R.layout.master_setting_switch, viewGroup, false);
        ((ViewGroup) inflate).addView(super.onCreateView(layoutInflater, viewGroup, bundle));
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        boolean z = false;
        if (Settings.System.getInt(getContentResolver(), "statusbar_battery_bar", 0) == 1) {
            z = true;
        }
        this.mTextView = (TextView) view.findViewById(C1715R.C1718id.switch_text);
        this.mTextView.setText(getString(z ? C1715R.string.switch_on_text : C1715R.string.switch_off_text));
        this.mSwitchBar = view.findViewById(C1715R.C1718id.switch_bar);
        Switch switchR = (Switch) this.mSwitchBar.findViewById(16908352);
        switchR.setChecked(z);
        switchR.setOnCheckedChangeListener(this);
        this.mSwitchBar.setActivated(z);
        this.mSwitchBar.setOnClickListener(new View.OnClickListener(switchR) {
            private final /* synthetic */ Switch f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                BatteryBar.this.lambda$onViewCreated$0$BatteryBar(this.f$1, view);
            }
        });
        this.mLocation.setEnabled(z);
        this.mThickness.setEnabled(z);
        this.mStyle.setEnabled(z);
        this.mAnimate.setEnabled(z);
        this.mCharging.setEnabled(z);
        this.mLight.setEnabled(z);
        this.mDark.setEnabled(z);
    }

    public /* synthetic */ void lambda$onViewCreated$0$BatteryBar(Switch switchR, View view) {
        switchR.setChecked(!switchR.isChecked());
        this.mSwitchBar.setActivated(switchR.isChecked());
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        Settings.System.putInt(getContentResolver(), "statusbar_battery_bar", z ? 1 : 0);
        this.mTextView.setText(getString(z ? C1715R.string.switch_on_text : C1715R.string.switch_off_text));
        this.mSwitchBar.setActivated(z);
        this.mLocation.setEnabled(z);
        this.mThickness.setEnabled(z);
        this.mStyle.setEnabled(z);
        this.mAnimate.setEnabled(z);
        this.mCharging.setEnabled(z);
        this.mLight.setEnabled(z);
        this.mDark.setEnabled(z);
    }

    public void onResume() {
        super.onResume();
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference == this.mLocation) {
            String str = (String) obj;
            int intValue = Integer.valueOf(str).intValue();
            int findIndexOfValue = this.mLocation.findIndexOfValue(str);
            Settings.System.putInt(getActivity().getContentResolver(), "statusbar_battery_bar_location", intValue);
            ListPreference listPreference = this.mLocation;
            listPreference.setSummary(listPreference.getEntries()[findIndexOfValue]);
            return true;
        } else if (preference == this.mChargingColor) {
            String convertToARGB = ColorPickerPreference.convertToARGB(Integer.valueOf(String.valueOf(obj)).intValue());
            if (convertToARGB.equals("#ffffff00")) {
                preference.setSummary((int) C1715R.string.default_string);
            } else {
                preference.setSummary((CharSequence) convertToARGB);
            }
            Settings.System.putInt(getContentResolver(), "statusbar_battery_bar_charging_color", ColorPickerPreference.convertToColorInt(convertToARGB));
            return true;
        } else if (preference != this.mChargingColorDark) {
            return false;
        } else {
            String convertToARGB2 = ColorPickerPreference.convertToARGB(Integer.valueOf(String.valueOf(obj)).intValue());
            if (convertToARGB2.equals("#ff0d47a1")) {
                preference.setSummary((int) C1715R.string.default_string);
            } else {
                preference.setSummary((CharSequence) convertToARGB2);
            }
            Settings.System.putInt(getContentResolver(), "statusbar_battery_bar_charging_dark_color", ColorPickerPreference.convertToColorInt(convertToARGB2));
            return true;
        }
    }
}
