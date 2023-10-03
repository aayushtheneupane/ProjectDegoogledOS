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
import com.android.settings.SettingsPreferenceFragment;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.SystemSettingListPreference;
import com.havoc.support.preferences.SystemSettingSeekBarPreference;

public class Ticker extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener, CompoundButton.OnCheckedChangeListener {
    private SystemSettingListPreference mAnimation;
    private SystemSettingSeekBarPreference mDuration;
    private SystemSettingListPreference mMode;
    private View mSwitchBar;
    private TextView mTextView;

    public int getMetricsCategory() {
        return 1999;
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        return false;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(C1715R.xml.statusbar_ticker);
        this.mMode = (SystemSettingListPreference) findPreference("status_bar_ticker_mode");
        this.mAnimation = (SystemSettingListPreference) findPreference("status_bar_ticker_animation_mode");
        this.mDuration = (SystemSettingSeekBarPreference) findPreference("status_bar_ticker_tick_duration");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = LayoutInflater.from(getContext()).inflate(C1715R.layout.master_setting_switch, viewGroup, false);
        ((ViewGroup) inflate).addView(super.onCreateView(layoutInflater, viewGroup, bundle));
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        boolean z = false;
        if (Settings.System.getInt(getContentResolver(), "status_bar_show_ticker", 0) == 1) {
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
                Ticker.this.lambda$onViewCreated$0$Ticker(this.f$1, view);
            }
        });
        this.mMode.setEnabled(z);
        this.mAnimation.setEnabled(z);
        this.mDuration.setEnabled(z);
    }

    public /* synthetic */ void lambda$onViewCreated$0$Ticker(Switch switchR, View view) {
        switchR.setChecked(!switchR.isChecked());
        this.mSwitchBar.setActivated(switchR.isChecked());
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        Settings.System.putInt(getContentResolver(), "status_bar_show_ticker", z ? 1 : 0);
        this.mTextView.setText(getString(z ? C1715R.string.switch_on_text : C1715R.string.switch_off_text));
        this.mSwitchBar.setActivated(z);
        this.mMode.setEnabled(z);
        this.mAnimation.setEnabled(z);
        this.mDuration.setEnabled(z);
    }

    public void onResume() {
        super.onResume();
    }
}
