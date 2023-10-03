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
import com.android.settings.SettingsPreferenceFragment;
import com.havoc.config.center.C1715R;
import com.havoc.support.colorpicker.ColorPickerPreference;
import com.havoc.support.preferences.CustomSeekBarPreference;
import com.havoc.support.preferences.SystemSettingListPreference;
import com.havoc.support.preferences.SystemSettingSwitchPreference;

public class EdgeLight extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener, CompoundButton.OnCheckedChangeListener {
    private SystemSettingSwitchPreference mEdgeLightAlways;
    private ColorPickerPreference mEdgeLightColor;
    private ListPreference mEdgeLightColorMode;
    private CustomSeekBarPreference mEdgeLightDuration;
    private SystemSettingListPreference mEdgeLightLayout;
    private CustomSeekBarPreference mEdgeLightRepeatCount;
    private SystemSettingListPreference mEdgeLightRepeatDirection;
    private View mSwitchBar;
    private TextView mTextView;

    public int getMetricsCategory() {
        return 1999;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(C1715R.xml.edge_light);
        this.mEdgeLightAlways = (SystemSettingSwitchPreference) findPreference("ambient_light_pulse_for_all");
        this.mEdgeLightRepeatDirection = (SystemSettingListPreference) findPreference("ambient_light_repeat_direction");
        this.mEdgeLightLayout = (SystemSettingListPreference) findPreference("ambient_light_layout");
        this.mEdgeLightColorMode = (ListPreference) findPreference("ambient_light_color");
        int intForUser = Settings.System.getIntForUser(getContentResolver(), "ambient_light_color", 0, -2);
        this.mEdgeLightColorMode.setValue(String.valueOf(intForUser));
        ListPreference listPreference = this.mEdgeLightColorMode;
        listPreference.setSummary(listPreference.getEntry());
        this.mEdgeLightColorMode.setOnPreferenceChangeListener(this);
        this.mEdgeLightColor = (ColorPickerPreference) findPreference("ambient_light_custom_color");
        int i = Settings.System.getInt(getContentResolver(), "ambient_light_custom_color", -13008641);
        this.mEdgeLightColor.setNewPreviewColor(i);
        String format = String.format("#%08x", new Object[]{Integer.valueOf(i & -13008641)});
        if (format.equals("#ff3980ff")) {
            this.mEdgeLightColor.setSummary((int) C1715R.string.default_string);
        } else {
            this.mEdgeLightColor.setSummary((CharSequence) format);
        }
        this.mEdgeLightColor.setOnPreferenceChangeListener(this);
        this.mEdgeLightDuration = (CustomSeekBarPreference) findPreference("ambient_light_duration");
        this.mEdgeLightDuration.setValue(Settings.System.getIntForUser(getContentResolver(), "ambient_light_duration", 2, -2));
        this.mEdgeLightDuration.setOnPreferenceChangeListener(this);
        this.mEdgeLightRepeatCount = (CustomSeekBarPreference) findPreference("ambient_light_repeat_count");
        this.mEdgeLightRepeatCount.setValue(Settings.System.getIntForUser(getContentResolver(), "ambient_light_repeat_count", 0, -2));
        this.mEdgeLightRepeatCount.setOnPreferenceChangeListener(this);
        updateColorPrefs(intForUser);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = LayoutInflater.from(getContext()).inflate(C1715R.layout.master_setting_switch, viewGroup, false);
        ((ViewGroup) inflate).addView(super.onCreateView(layoutInflater, viewGroup, bundle));
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        boolean z = false;
        if (Settings.System.getInt(getContentResolver(), "ambient_notification_light", 0) == 1) {
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
                EdgeLight.this.lambda$onViewCreated$0$EdgeLight(this.f$1, view);
            }
        });
        this.mEdgeLightAlways.setEnabled(z);
        this.mEdgeLightColorMode.setEnabled(z);
        this.mEdgeLightColor.setEnabled(z);
        this.mEdgeLightDuration.setEnabled(z);
        this.mEdgeLightRepeatCount.setEnabled(z);
        this.mEdgeLightRepeatDirection.setEnabled(z);
        this.mEdgeLightLayout.setEnabled(z);
    }

    public /* synthetic */ void lambda$onViewCreated$0$EdgeLight(Switch switchR, View view) {
        switchR.setChecked(!switchR.isChecked());
        this.mSwitchBar.setActivated(switchR.isChecked());
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        Settings.System.putInt(getContentResolver(), "ambient_notification_light", z ? 1 : 0);
        this.mTextView.setText(getString(z ? C1715R.string.switch_on_text : C1715R.string.switch_off_text));
        this.mSwitchBar.setActivated(z);
        this.mEdgeLightAlways.setEnabled(z);
        this.mEdgeLightColorMode.setEnabled(z);
        this.mEdgeLightColor.setEnabled(z);
        this.mEdgeLightDuration.setEnabled(z);
        this.mEdgeLightRepeatCount.setEnabled(z);
        this.mEdgeLightRepeatDirection.setEnabled(z);
        this.mEdgeLightLayout.setEnabled(z);
    }

    public void onResume() {
        super.onResume();
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference == this.mEdgeLightColorMode) {
            String str = (String) obj;
            int intValue = Integer.valueOf(str).intValue();
            int findIndexOfValue = this.mEdgeLightColorMode.findIndexOfValue(str);
            Settings.System.putIntForUser(getContentResolver(), "ambient_light_color", intValue, -2);
            ListPreference listPreference = this.mEdgeLightColorMode;
            listPreference.setSummary(listPreference.getEntries()[findIndexOfValue]);
            updateColorPrefs(intValue);
            return true;
        } else if (preference == this.mEdgeLightColor) {
            String convertToARGB = ColorPickerPreference.convertToARGB(Integer.valueOf(String.valueOf(obj)).intValue());
            if (convertToARGB.equals("#ff3980ff")) {
                preference.setSummary((int) C1715R.string.default_string);
            } else {
                preference.setSummary((CharSequence) convertToARGB);
            }
            Settings.System.putInt(getContentResolver(), "ambient_light_custom_color", ColorPickerPreference.convertToColorInt(convertToARGB));
            return true;
        } else if (preference == this.mEdgeLightDuration) {
            Settings.System.putIntForUser(getContentResolver(), "ambient_light_duration", ((Integer) obj).intValue(), -2);
            return true;
        } else if (preference != this.mEdgeLightRepeatCount) {
            return false;
        } else {
            Settings.System.putIntForUser(getContentResolver(), "ambient_light_repeat_count", ((Integer) obj).intValue(), -2);
            return true;
        }
    }

    private void updateColorPrefs(int i) {
        if (this.mEdgeLightColor == null) {
            return;
        }
        if (i == 3) {
            getPreferenceScreen().addPreference(this.mEdgeLightColor);
        } else {
            getPreferenceScreen().removePreference(this.mEdgeLightColor);
        }
    }
}
