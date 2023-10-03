package com.havoc.config.center.fragments;

import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceScreen;
import com.android.settings.SettingsPreferenceFragment;
import com.havoc.config.center.C1715R;
import com.havoc.support.colorpicker.ColorPickerPreference;
import com.havoc.support.preferences.SystemSettingSwitchPreference;

public class BatteryLightSettings extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener, CompoundButton.OnCheckedChangeListener {
    private SystemSettingSwitchPreference mBatteryLightOnDnd;
    private PreferenceCategory mColorCategory;
    private ColorPickerPreference mFullColor;
    private SystemSettingSwitchPreference mLowBatteryBlinking;
    private ColorPickerPreference mLowColor;
    private ColorPickerPreference mMediumColor;
    private ColorPickerPreference mReallyFullColor;
    private View mSwitchBar;
    private TextView mTextView;

    public int getMetricsCategory() {
        return 1999;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(C1715R.xml.battery_light_settings);
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        this.mColorCategory = (PreferenceCategory) findPreference("battery_light_cat");
        this.mBatteryLightOnDnd = (SystemSettingSwitchPreference) findPreference("battery_light_allow_on_dnd");
        this.mLowBatteryBlinking = (SystemSettingSwitchPreference) preferenceScreen.findPreference("battery_light_low_blinking");
        if (getResources().getBoolean(17891496)) {
            this.mLowBatteryBlinking.setChecked(Settings.System.getIntForUser(getContentResolver(), "battery_light_low_blinking", 0, -2) == 1);
            this.mLowBatteryBlinking.setOnPreferenceChangeListener(this);
        } else {
            preferenceScreen.removePreference(this.mLowBatteryBlinking);
        }
        if (getResources().getBoolean(17891507)) {
            int intForUser = Settings.System.getIntForUser(getContentResolver(), "battery_light_low_color", -65536, -2);
            this.mLowColor = (ColorPickerPreference) findPreference("battery_light_low_color");
            this.mLowColor.setAlphaSliderEnabled(false);
            this.mLowColor.setNewPreviewColor(intForUser);
            String format = String.format("#%08x", new Object[]{Integer.valueOf(intForUser & -65536)});
            if (format.equals("#ffff0000")) {
                this.mLowColor.setSummary((int) C1715R.string.default_string);
            } else {
                this.mLowColor.setSummary((CharSequence) format);
            }
            this.mLowColor.setOnPreferenceChangeListener(this);
            int intForUser2 = Settings.System.getIntForUser(getContentResolver(), "battery_light_medium_color", -256, -2);
            this.mMediumColor = (ColorPickerPreference) findPreference("battery_light_medium_color");
            this.mMediumColor.setAlphaSliderEnabled(false);
            this.mMediumColor.setNewPreviewColor(intForUser2);
            String format2 = String.format("#%08x", new Object[]{Integer.valueOf(intForUser2 & -256)});
            if (format2.equals("#ffffff00")) {
                this.mMediumColor.setSummary((int) C1715R.string.default_string);
            } else {
                this.mMediumColor.setSummary((CharSequence) format2);
            }
            this.mMediumColor.setOnPreferenceChangeListener(this);
            int intForUser3 = Settings.System.getIntForUser(getContentResolver(), "battery_light_full_color", -256, -2);
            this.mFullColor = (ColorPickerPreference) findPreference("battery_light_full_color");
            this.mFullColor.setAlphaSliderEnabled(false);
            this.mFullColor.setNewPreviewColor(intForUser3);
            String format3 = String.format("#%08x", new Object[]{Integer.valueOf(intForUser3 & -256)});
            if (format3.equals("#ffffff00")) {
                this.mFullColor.setSummary((int) C1715R.string.default_string);
            } else {
                this.mFullColor.setSummary((CharSequence) format3);
            }
            this.mFullColor.setOnPreferenceChangeListener(this);
            int intForUser4 = Settings.System.getIntForUser(getContentResolver(), "battery_light_reallyfull_color", -16711936, -2);
            this.mReallyFullColor = (ColorPickerPreference) findPreference("battery_light_reallyfull_color");
            this.mReallyFullColor.setAlphaSliderEnabled(false);
            this.mReallyFullColor.setNewPreviewColor(intForUser4);
            String format4 = String.format("#%08x", new Object[]{Integer.valueOf(intForUser4 & -16711936)});
            if (format4.equals("#ff00ff00")) {
                this.mReallyFullColor.setSummary((int) C1715R.string.default_string);
            } else {
                this.mReallyFullColor.setSummary((CharSequence) format4);
            }
            this.mReallyFullColor.setOnPreferenceChangeListener(this);
            return;
        }
        PreferenceCategory preferenceCategory = this.mColorCategory;
        if (preferenceCategory != null) {
            preferenceScreen.removePreference(preferenceCategory);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = LayoutInflater.from(getContext()).inflate(C1715R.layout.master_setting_switch, viewGroup, false);
        ((ViewGroup) inflate).addView(super.onCreateView(layoutInflater, viewGroup, bundle));
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        boolean z = true;
        if (Settings.System.getInt(getContentResolver(), "battery_light_enabled", 1) != 1) {
            z = false;
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
                BatteryLightSettings.this.lambda$onViewCreated$0$BatteryLightSettings(this.f$1, view);
            }
        });
        this.mBatteryLightOnDnd.setEnabled(z);
        this.mLowBatteryBlinking.setEnabled(z);
        this.mColorCategory.setEnabled(z);
    }

    public /* synthetic */ void lambda$onViewCreated$0$BatteryLightSettings(Switch switchR, View view) {
        switchR.setChecked(!switchR.isChecked());
        this.mSwitchBar.setActivated(switchR.isChecked());
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        Settings.System.putInt(getContentResolver(), "battery_light_enabled", z ? 1 : 0);
        this.mTextView.setText(getString(z ? C1715R.string.switch_on_text : C1715R.string.switch_off_text));
        this.mSwitchBar.setActivated(z);
        this.mBatteryLightOnDnd.setEnabled(z);
        this.mLowBatteryBlinking.setEnabled(z);
        this.mColorCategory.setEnabled(z);
    }

    public void onResume() {
        super.onResume();
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference.equals(this.mLowColor)) {
            String convertToARGB = ColorPickerPreference.convertToARGB(Integer.valueOf(String.valueOf(obj)).intValue());
            if (convertToARGB.equals("#ffff0000")) {
                preference.setSummary((int) C1715R.string.default_string);
            } else {
                preference.setSummary((CharSequence) convertToARGB);
            }
            Settings.System.putIntForUser(getContentResolver(), "battery_light_low_color", ColorPickerPreference.convertToColorInt(convertToARGB), -2);
            return true;
        } else if (preference.equals(this.mMediumColor)) {
            String convertToARGB2 = ColorPickerPreference.convertToARGB(Integer.valueOf(String.valueOf(obj)).intValue());
            if (convertToARGB2.equals("#ffffff00")) {
                preference.setSummary((int) C1715R.string.default_string);
            } else {
                preference.setSummary((CharSequence) convertToARGB2);
            }
            Settings.System.putIntForUser(getContentResolver(), "battery_light_medium_color", ColorPickerPreference.convertToColorInt(convertToARGB2), -2);
            return true;
        } else if (preference.equals(this.mFullColor)) {
            String convertToARGB3 = ColorPickerPreference.convertToARGB(Integer.valueOf(String.valueOf(obj)).intValue());
            if (convertToARGB3.equals("#ffffff00")) {
                preference.setSummary((int) C1715R.string.default_string);
            } else {
                preference.setSummary((CharSequence) convertToARGB3);
            }
            Settings.System.putIntForUser(getContentResolver(), "battery_light_full_color", ColorPickerPreference.convertToColorInt(convertToARGB3), -2);
            return true;
        } else if (preference.equals(this.mReallyFullColor)) {
            String convertToARGB4 = ColorPickerPreference.convertToARGB(Integer.valueOf(String.valueOf(obj)).intValue());
            if (convertToARGB4.equals("#ff00ff00")) {
                preference.setSummary((int) C1715R.string.default_string);
            } else {
                preference.setSummary((CharSequence) convertToARGB4);
            }
            Settings.System.putIntForUser(getContentResolver(), "battery_light_reallyfull_color", ColorPickerPreference.convertToColorInt(convertToARGB4), -2);
            return true;
        } else if (preference != this.mLowBatteryBlinking) {
            return false;
        } else {
            boolean booleanValue = ((Boolean) obj).booleanValue();
            Settings.System.putIntForUser(getActivity().getContentResolver(), "battery_light_low_blinking", booleanValue ? 1 : 0, -2);
            this.mLowBatteryBlinking.setChecked(booleanValue);
            return true;
        }
    }
}
