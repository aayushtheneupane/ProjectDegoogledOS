package com.android.settings.accessibility;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.SettingsActivity;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.widget.SwitchBar;
import com.android.settings.widget.ToggleSwitch;
import com.havoc.config.center.C1715R;

public abstract class ToggleFeaturePreferenceFragment extends SettingsPreferenceFragment {
    protected String mPreferenceKey;
    protected Intent mSettingsIntent;
    protected CharSequence mSettingsTitle;
    protected SwitchBar mSwitchBar;
    protected ToggleSwitch mToggleSwitch;

    /* access modifiers changed from: protected */
    public void onInstallSwitchBarToggleSwitch() {
    }

    /* access modifiers changed from: protected */
    public void onRemoveSwitchBarToggleSwitch() {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getPreferenceScreenResId() <= 0) {
            setPreferenceScreen(getPreferenceManager().createPreferenceScreen(getActivity()));
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mSwitchBar = ((SettingsActivity) getActivity()).getSwitchBar();
        updateSwitchBarText(this.mSwitchBar);
        this.mToggleSwitch = this.mSwitchBar.getSwitch();
        onProcessArguments(getArguments());
        if (this.mSettingsTitle != null && this.mSettingsIntent != null) {
            PreferenceScreen preferenceScreen = getPreferenceScreen();
            Preference preference = new Preference(preferenceScreen.getContext());
            preference.setTitle(this.mSettingsTitle);
            preference.setIconSpaceReserved(true);
            preference.setIntent(this.mSettingsIntent);
            preferenceScreen.addPreference(preference);
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        installActionBarToggleSwitch();
    }

    public void onDestroyView() {
        super.onDestroyView();
        removeActionBarToggleSwitch();
    }

    /* access modifiers changed from: protected */
    public void updateSwitchBarText(SwitchBar switchBar) {
        switchBar.setSwitchBarText(C1715R.string.accessibility_service_master_switch_title, C1715R.string.accessibility_service_master_switch_title);
    }

    private void installActionBarToggleSwitch() {
        this.mSwitchBar.show();
        onInstallSwitchBarToggleSwitch();
    }

    private void removeActionBarToggleSwitch() {
        this.mToggleSwitch.setOnBeforeCheckedChangeListener((ToggleSwitch.OnBeforeCheckedChangeListener) null);
        onRemoveSwitchBarToggleSwitch();
        this.mSwitchBar.hide();
    }

    public void setTitle(String str) {
        getActivity().setTitle(str);
    }

    /* access modifiers changed from: protected */
    public void onProcessArguments(Bundle bundle) {
        this.mPreferenceKey = bundle.getString("preference_key");
        if (bundle.containsKey("checked")) {
            this.mSwitchBar.setCheckedInternal(bundle.getBoolean("checked"));
        }
        if (bundle.containsKey("resolve_info")) {
            getActivity().setTitle(((ResolveInfo) bundle.getParcelable("resolve_info")).loadLabel(getPackageManager()).toString());
        } else if (bundle.containsKey("title")) {
            setTitle(bundle.getString("title"));
        }
        if (bundle.containsKey("summary_res")) {
            this.mFooterPreferenceMixin.createFooterPreference().setTitle(bundle.getInt("summary_res"));
        } else if (bundle.containsKey("summary")) {
            this.mFooterPreferenceMixin.createFooterPreference().setTitle(bundle.getCharSequence("summary"));
        }
    }
}
