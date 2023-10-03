package com.havoc.config.center.fragments;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.UserManager;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import com.android.settings.SettingsPreferenceFragment;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.CustomSeekBarPreference;
import com.havoc.support.preferences.SystemSettingSwitchPreference;

public class ScreenStateToggles extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener, CompoundButton.OnCheckedChangeListener {
    private Context mContext;
    private SystemSettingSwitchPreference mEnableScreenStateTogglesGps;
    private SystemSettingSwitchPreference mEnableScreenStateTogglesMobileData;
    private SystemSettingSwitchPreference mEnableScreenStateTogglesThreeG;
    private SystemSettingSwitchPreference mEnableScreenStateTogglesTwoG;
    private PreferenceCategory mLocationCategory;
    private PreferenceCategory mMobileDateCategory;
    private CustomSeekBarPreference mSecondsOffDelay;
    private CustomSeekBarPreference mSecondsOnDelay;
    private View mSwitchBar;
    private TextView mTextView;

    public int getMetricsCategory() {
        return 1999;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = getActivity();
        addPreferencesFromResource(C1715R.xml.screen_state_toggles);
        ContentResolver contentResolver = getActivity().getContentResolver();
        this.mSecondsOffDelay = (CustomSeekBarPreference) findPreference("screen_state_off_delay");
        boolean z = false;
        this.mSecondsOffDelay.setValue(Settings.System.getIntForUser(contentResolver, "screen_state_off_delay", 0, -2));
        this.mSecondsOffDelay.setOnPreferenceChangeListener(this);
        this.mSecondsOnDelay = (CustomSeekBarPreference) findPreference("screen_state_on_delay");
        this.mSecondsOnDelay.setValue(Settings.System.getIntForUser(contentResolver, "screen_state_on_delay", 0, -2));
        this.mSecondsOnDelay.setOnPreferenceChangeListener(this);
        this.mMobileDateCategory = (PreferenceCategory) findPreference("screen_state_toggles_mobile_key");
        this.mLocationCategory = (PreferenceCategory) findPreference("screen_state_toggles_location_key");
        this.mEnableScreenStateTogglesTwoG = (SystemSettingSwitchPreference) findPreference("screen_state_toggles_twog");
        this.mEnableScreenStateTogglesThreeG = (SystemSettingSwitchPreference) findPreference("screen_state_toggles_threeg");
        ConnectivityManager connectivityManager = (ConnectivityManager) this.mContext.getSystemService("connectivity");
        if (!connectivityManager.isNetworkSupported(0)) {
            getPreferenceScreen().removePreference(this.mEnableScreenStateTogglesTwoG);
            getPreferenceScreen().removePreference(this.mEnableScreenStateTogglesThreeG);
        } else {
            this.mEnableScreenStateTogglesTwoG.setChecked(Settings.System.getIntForUser(contentResolver, "screen_state_twog", 0, -2) == 1);
            this.mEnableScreenStateTogglesTwoG.setOnPreferenceChangeListener(this);
            this.mEnableScreenStateTogglesThreeG.setChecked(Settings.System.getIntForUser(contentResolver, "screen_state_threeg", 0, -2) == 1);
            this.mEnableScreenStateTogglesThreeG.setOnPreferenceChangeListener(this);
        }
        this.mEnableScreenStateTogglesMobileData = (SystemSettingSwitchPreference) findPreference("screen_state_toggles_mobile_data");
        if (!connectivityManager.isNetworkSupported(0)) {
            getPreferenceScreen().removePreference(this.mEnableScreenStateTogglesMobileData);
        } else {
            this.mEnableScreenStateTogglesMobileData.setChecked(Settings.System.getIntForUser(contentResolver, "screen_state_mobile_data", 0, -2) == 1);
            this.mEnableScreenStateTogglesMobileData.setOnPreferenceChangeListener(this);
        }
        boolean z2 = !((UserManager) getActivity().getSystemService("user")).hasUserRestriction("no_share_location");
        this.mEnableScreenStateTogglesGps = (SystemSettingSwitchPreference) findPreference("screen_state_toggles_gps");
        if (!z2) {
            getPreferenceScreen().removePreference(this.mEnableScreenStateTogglesGps);
            this.mEnableScreenStateTogglesGps = null;
            return;
        }
        SystemSettingSwitchPreference systemSettingSwitchPreference = this.mEnableScreenStateTogglesGps;
        if (Settings.System.getIntForUser(contentResolver, "screen_state_gps", 0, -2) == 1) {
            z = true;
        }
        systemSettingSwitchPreference.setChecked(z);
        this.mEnableScreenStateTogglesGps.setOnPreferenceChangeListener(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = LayoutInflater.from(getContext()).inflate(C1715R.layout.master_setting_switch, viewGroup, false);
        ((ViewGroup) inflate).addView(super.onCreateView(layoutInflater, viewGroup, bundle));
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        boolean z = false;
        if (Settings.System.getInt(getContentResolver(), "start_screen_state_service", 0) == 1) {
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
                ScreenStateToggles.this.lambda$onViewCreated$0$ScreenStateToggles(this.f$1, view);
            }
        });
        this.mSecondsOffDelay.setEnabled(z);
        this.mSecondsOnDelay.setEnabled(z);
        this.mMobileDateCategory.setEnabled(z);
        this.mLocationCategory.setEnabled(z);
    }

    public /* synthetic */ void lambda$onViewCreated$0$ScreenStateToggles(Switch switchR, View view) {
        switchR.setChecked(!switchR.isChecked());
        this.mSwitchBar.setActivated(switchR.isChecked());
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        Settings.System.putInt(getContentResolver(), "start_screen_state_service", z ? 1 : 0);
        this.mTextView.setText(getString(z ? C1715R.string.switch_on_text : C1715R.string.switch_off_text));
        this.mSwitchBar.setActivated(z);
        this.mSecondsOffDelay.setEnabled(z);
        this.mSecondsOnDelay.setEnabled(z);
        this.mMobileDateCategory.setEnabled(z);
        this.mLocationCategory.setEnabled(z);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        ContentResolver contentResolver = getActivity().getContentResolver();
        if (preference == this.mEnableScreenStateTogglesTwoG) {
            Settings.System.putIntForUser(contentResolver, "screen_state_twog", ((Boolean) obj).booleanValue() ? 1 : 0, -2);
            this.mContext.sendBroadcast(new Intent("android.intent.action.SCREEN_STATE_SERVICE_UPDATE"));
            return true;
        } else if (preference == this.mEnableScreenStateTogglesThreeG) {
            Settings.System.putIntForUser(contentResolver, "screen_state_threeg", ((Boolean) obj).booleanValue() ? 1 : 0, -2);
            this.mContext.sendBroadcast(new Intent("android.intent.action.SCREEN_STATE_SERVICE_UPDATE"));
            return true;
        } else if (preference == this.mEnableScreenStateTogglesGps) {
            Settings.System.putIntForUser(contentResolver, "screen_state_gps", ((Boolean) obj).booleanValue() ? 1 : 0, -2);
            this.mContext.sendBroadcast(new Intent("android.intent.action.SCREEN_STATE_SERVICE_UPDATE"));
            return true;
        } else if (preference == this.mEnableScreenStateTogglesMobileData) {
            Settings.System.putIntForUser(contentResolver, "screen_state_mobile_data", ((Boolean) obj).booleanValue() ? 1 : 0, -2);
            this.mContext.sendBroadcast(new Intent("android.intent.action.SCREEN_STATE_SERVICE_UPDATE"));
            return true;
        } else if (preference == this.mSecondsOffDelay) {
            Settings.System.putIntForUser(contentResolver, "screen_state_off_delay", ((Integer) obj).intValue(), -2);
            return true;
        } else if (preference != this.mSecondsOnDelay) {
            return false;
        } else {
            Settings.System.putIntForUser(contentResolver, "screen_state_on_delay", ((Integer) obj).intValue(), -2);
            return true;
        }
    }
}
