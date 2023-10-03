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
import com.android.settings.SettingsPreferenceFragment;
import com.havoc.config.center.C1715R;
import com.havoc.support.colorpicker.ColorPickerPreference;
import com.havoc.support.preferences.SecureSettingSwitchPreference;

public class PulseSettings extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener, CompoundButton.OnCheckedChangeListener {
    private ListPreference mColorModePref;
    private ColorPickerPreference mColorPickerPref;
    private PreferenceCategory mFadingBarsCat;
    private Preference mLavaSpeedPref;
    private ListPreference mLocation;
    private Preference mRenderMode;
    private SecureSettingSwitchPreference mSmoothingPref;
    private PreferenceCategory mSolidBarsCat;
    private View mSwitchBar;
    private TextView mTextView;

    public int getMetricsCategory() {
        return 1999;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(C1715R.xml.pulse_settings);
        this.mFooterPreferenceMixin.createFooterPreference().setTitle((int) C1715R.string.pulse_help_policy_notice_summary);
        this.mFadingBarsCat = (PreferenceCategory) findPreference("pulse_fading_bars_category");
        this.mSolidBarsCat = (PreferenceCategory) findPreference("pulse_2");
        this.mLavaSpeedPref = findPreference("pulse_lavalamp_speed");
        this.mSmoothingPref = (SecureSettingSwitchPreference) findPreference("pulse_smoothing_enabled");
        this.mColorModePref = (ListPreference) findPreference("pulse_color_mode");
        this.mColorModePref.setOnPreferenceChangeListener(this);
        int intForUser = Settings.Secure.getIntForUser(getContentResolver(), "pulse_color_mode", 0, -2);
        this.mColorPickerPref = (ColorPickerPreference) findPreference("pulse_color_user");
        int i = Settings.Secure.getInt(getContentResolver(), "pulse_color_user", -1);
        this.mColorPickerPref.setNewPreviewColor(i);
        String format = String.format("#%08x", new Object[]{Integer.valueOf(i & -1)});
        if (format.equals("#ffffffff")) {
            this.mColorPickerPref.setSummary((int) C1715R.string.default_string);
        } else {
            this.mColorPickerPref.setSummary((CharSequence) format);
        }
        this.mColorPickerPref.setOnPreferenceChangeListener(this);
        this.mRenderMode = findPreference("pulse_render_style");
        this.mRenderMode.setOnPreferenceChangeListener(this);
        int intForUser2 = Settings.Secure.getIntForUser(getContentResolver(), "pulse_render_style", 0, -2);
        this.mLocation = (ListPreference) findPreference("pulse_location");
        this.mLocation.setOnPreferenceChangeListener(this);
        int intForUser3 = Settings.Secure.getIntForUser(getContentResolver(), "pulse_location", 0, -2);
        updateColorPrefs(intForUser);
        updateRenderCategories(intForUser2);
        updateLocationSummary(intForUser3);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = LayoutInflater.from(getContext()).inflate(C1715R.layout.master_setting_switch, viewGroup, false);
        ((ViewGroup) inflate).addView(super.onCreateView(layoutInflater, viewGroup, bundle));
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        boolean z = false;
        if (Settings.Secure.getInt(getContentResolver(), "pulse_enabled", 0) == 1) {
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
                PulseSettings.this.lambda$onViewCreated$0$PulseSettings(this.f$1, view);
            }
        });
        this.mLocation.setEnabled(z);
        this.mLavaSpeedPref.setEnabled(z);
        this.mColorModePref.setEnabled(z);
        this.mColorPickerPref.setEnabled(z);
        this.mRenderMode.setEnabled(z);
        this.mSmoothingPref.setEnabled(z);
        this.mFadingBarsCat.setEnabled(z);
        this.mSolidBarsCat.setEnabled(z);
    }

    public /* synthetic */ void lambda$onViewCreated$0$PulseSettings(Switch switchR, View view) {
        switchR.setChecked(!switchR.isChecked());
        this.mSwitchBar.setActivated(switchR.isChecked());
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        Settings.Secure.putInt(getContentResolver(), "pulse_enabled", z ? 1 : 0);
        this.mTextView.setText(getString(z ? C1715R.string.switch_on_text : C1715R.string.switch_off_text));
        this.mSwitchBar.setActivated(z);
        this.mLocation.setEnabled(z);
        this.mLavaSpeedPref.setEnabled(z);
        this.mColorModePref.setEnabled(z);
        this.mColorPickerPref.setEnabled(z);
        this.mRenderMode.setEnabled(z);
        this.mSmoothingPref.setEnabled(z);
        this.mFadingBarsCat.setEnabled(z);
        this.mSolidBarsCat.setEnabled(z);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference.equals(this.mColorModePref)) {
            updateColorPrefs(Integer.valueOf(String.valueOf(obj)).intValue());
            return true;
        } else if (preference.equals(this.mRenderMode)) {
            updateRenderCategories(Integer.valueOf(String.valueOf(obj)).intValue());
            return true;
        } else if (preference == this.mColorPickerPref) {
            String convertToARGB = ColorPickerPreference.convertToARGB(Integer.valueOf(String.valueOf(obj)).intValue());
            if (convertToARGB.equals("#ffffffff")) {
                preference.setSummary((int) C1715R.string.default_string);
            } else {
                preference.setSummary((CharSequence) convertToARGB);
            }
            Settings.Secure.putInt(getContentResolver(), "pulse_color_user", ColorPickerPreference.convertToColorInt(convertToARGB));
            return true;
        } else if (!preference.equals(this.mLocation)) {
            return false;
        } else {
            updateLocationSummary(Integer.valueOf(String.valueOf(obj)).intValue());
            return true;
        }
    }

    private void updateColorPrefs(int i) {
        if (i == 0) {
            this.mColorPickerPref.setVisible(false);
            this.mLavaSpeedPref.setVisible(false);
        } else if (i == 1) {
            this.mColorPickerPref.setVisible(true);
            this.mLavaSpeedPref.setVisible(false);
        } else if (i == 2) {
            this.mColorPickerPref.setVisible(false);
            this.mLavaSpeedPref.setVisible(true);
        } else if (i == 3) {
            this.mColorPickerPref.setVisible(false);
            this.mLavaSpeedPref.setVisible(false);
        }
    }

    private void updateRenderCategories(int i) {
        boolean z = false;
        this.mFadingBarsCat.setVisible(i == 0);
        PreferenceCategory preferenceCategory = this.mSolidBarsCat;
        if (i == 1) {
            z = true;
        }
        preferenceCategory.setVisible(z);
    }

    private void updateLocationSummary(int i) {
        String str = (String) this.mLocation.getEntries()[this.mLocation.findIndexOfValue(String.valueOf(i))];
        if (i == 0) {
            this.mLocation.setSummary(getResources().getString(C1715R.string.pulse_location_lockscreen, new Object[]{str}));
        } else if (i == 1) {
            this.mLocation.setSummary(getResources().getString(C1715R.string.pulse_location_navbar, new Object[]{str}));
        } else if (i == 2) {
            this.mLocation.setSummary(getResources().getString(C1715R.string.pulse_location_both_summary, new Object[]{str}));
        }
    }
}
