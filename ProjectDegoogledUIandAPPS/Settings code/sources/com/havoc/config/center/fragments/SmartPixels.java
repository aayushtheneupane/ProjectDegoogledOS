package com.havoc.config.center.fragments;

import android.content.ContentResolver;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import androidx.preference.Preference;
import com.android.settings.SettingsPreferenceFragment;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.SystemSettingListPreference;
import com.havoc.support.preferences.SystemSettingSwitchPreference;

public class SmartPixels extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener, CompoundButton.OnCheckedChangeListener {
    private SystemSettingSwitchPreference mSmartPixelsOnPowerSave;
    private SystemSettingListPreference mSmartPixelsPattern;
    private SystemSettingListPreference mSmartPixelsTimeout;
    private View mSwitchBar;
    private TextView mTextView;
    ContentResolver resolver;

    public int getMetricsCategory() {
        return 1999;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(C1715R.xml.smart_pixels);
        this.resolver = getActivity().getContentResolver();
        this.mSmartPixelsPattern = (SystemSettingListPreference) findPreference("smart_pixels_pattern");
        this.mSmartPixelsTimeout = (SystemSettingListPreference) findPreference("smart_pixels_shift_timeout");
        this.mSmartPixelsOnPowerSave = (SystemSettingSwitchPreference) findPreference("smart_pixels_on_power_save");
        updateDependency();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = LayoutInflater.from(getContext()).inflate(C1715R.layout.master_setting_switch, viewGroup, false);
        ((ViewGroup) inflate).addView(super.onCreateView(layoutInflater, viewGroup, bundle));
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        boolean z = false;
        if (Settings.System.getInt(getContentResolver(), "smart_pixels_enable", 0) == 1) {
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
                SmartPixels.this.lambda$onViewCreated$0$SmartPixels(this.f$1, view);
            }
        });
        this.mSmartPixelsPattern.setEnabled(z);
        this.mSmartPixelsTimeout.setEnabled(z);
        this.mSmartPixelsOnPowerSave.setEnabled(z);
    }

    public /* synthetic */ void lambda$onViewCreated$0$SmartPixels(Switch switchR, View view) {
        switchR.setChecked(!switchR.isChecked());
        this.mSwitchBar.setActivated(switchR.isChecked());
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        Settings.System.putInt(getContentResolver(), "smart_pixels_enable", z ? 1 : 0);
        this.mTextView.setText(getString(z ? C1715R.string.switch_on_text : C1715R.string.switch_off_text));
        this.mSwitchBar.setActivated(z);
        this.mSmartPixelsPattern.setEnabled(z);
        this.mSmartPixelsTimeout.setEnabled(z);
        this.mSmartPixelsOnPowerSave.setEnabled(z);
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        preference.getKey();
        updateDependency();
        return true;
    }

    private void updateDependency() {
        boolean z = Settings.System.getIntForUser(this.resolver, "smart_pixels_on_power_save", 0, -2) == 1;
        if (!((PowerManager) getActivity().getSystemService("power")).isPowerSaveMode() || !z) {
            this.mSmartPixelsOnPowerSave.setEnabled(true);
        } else {
            this.mSmartPixelsOnPowerSave.setEnabled(false);
        }
    }
}
