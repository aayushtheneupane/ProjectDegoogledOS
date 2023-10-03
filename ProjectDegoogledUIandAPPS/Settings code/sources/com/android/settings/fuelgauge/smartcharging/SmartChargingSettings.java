package com.android.settings.fuelgauge.smartcharging;

import android.content.Context;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import androidx.preference.Preference;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.CustomSeekBarPreference;
import com.havoc.support.preferences.SystemSettingSwitchPreference;
import java.util.ArrayList;
import java.util.List;

public class SmartChargingSettings extends DashboardFragment implements Preference.OnPreferenceChangeListener, CompoundButton.OnCheckedChangeListener {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.smart_charging;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }
    };
    private SystemSettingSwitchPreference mResetStats;
    private CustomSeekBarPreference mSmartChargingLevel;
    private int mSmartChargingLevelDefaultConfig;
    private CustomSeekBarPreference mSmartChargingResumeLevel;
    private int mSmartChargingResumeLevelDefaultConfig;
    private View mSwitchBar;
    private TextView mTextView;

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "SmartChargingSettings";
    }

    public int getMetricsCategory() {
        return 1999;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.smart_charging;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mSmartChargingLevelDefaultConfig = getResources().getInteger(17694919);
        this.mSmartChargingResumeLevelDefaultConfig = getResources().getInteger(17694920);
        this.mSmartChargingLevel = (CustomSeekBarPreference) findPreference("smart_charging_level");
        int i = Settings.System.getInt(getContentResolver(), "smart_charging_level", this.mSmartChargingLevelDefaultConfig);
        this.mSmartChargingLevel.setValue(i);
        this.mSmartChargingLevel.setOnPreferenceChangeListener(this);
        this.mSmartChargingResumeLevel = (CustomSeekBarPreference) findPreference("smart_charging_resume_level");
        int i2 = Settings.System.getInt(getContentResolver(), "smart_charging_resume_level", this.mSmartChargingResumeLevelDefaultConfig);
        int i3 = i - 1;
        this.mSmartChargingResumeLevel.setMax(i3);
        if (i2 >= i) {
            i2 = i3;
        }
        this.mSmartChargingResumeLevel.setValue(i2);
        this.mSmartChargingResumeLevel.setOnPreferenceChangeListener(this);
        this.mResetStats = (SystemSettingSwitchPreference) findPreference("smart_charging_reset_stats");
        this.mFooterPreferenceMixin.createFooterPreference().setTitle((int) C1715R.string.smart_charging_footer);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = LayoutInflater.from(getContext()).inflate(C1715R.layout.master_setting_switch, viewGroup, false);
        ((ViewGroup) inflate).addView(super.onCreateView(layoutInflater, viewGroup, bundle));
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        boolean z = false;
        if (Settings.System.getInt(getContentResolver(), "smart_charging", 0) == 1) {
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
                SmartChargingSettings.this.lambda$onViewCreated$0$SmartChargingSettings(this.f$1, view);
            }
        });
        this.mSmartChargingLevel.setEnabled(z);
        this.mSmartChargingResumeLevel.setEnabled(z);
        this.mResetStats.setEnabled(z);
    }

    public /* synthetic */ void lambda$onViewCreated$0$SmartChargingSettings(Switch switchR, View view) {
        switchR.setChecked(!switchR.isChecked());
        this.mSwitchBar.setActivated(switchR.isChecked());
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        Settings.System.putInt(getContentResolver(), "smart_charging", z ? 1 : 0);
        this.mTextView.setText(getString(z ? C1715R.string.switch_on_text : C1715R.string.switch_off_text));
        this.mSwitchBar.setActivated(z);
        this.mSmartChargingLevel.setEnabled(z);
        this.mSmartChargingResumeLevel.setEnabled(z);
        this.mResetStats.setEnabled(z);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference == this.mSmartChargingLevel) {
            int intValue = ((Integer) obj).intValue();
            int i = Settings.System.getInt(getContentResolver(), "smart_charging_resume_level", this.mSmartChargingResumeLevelDefaultConfig);
            Settings.System.putInt(getContentResolver(), "smart_charging_level", intValue);
            int i2 = intValue - 1;
            this.mSmartChargingResumeLevel.setMax(i2);
            if (intValue <= i) {
                this.mSmartChargingResumeLevel.setValue(i2);
                Settings.System.putInt(getContentResolver(), "smart_charging_resume_level", i2);
            }
            return true;
        } else if (preference != this.mSmartChargingResumeLevel) {
            return false;
        } else {
            int intValue2 = ((Integer) obj).intValue();
            this.mSmartChargingResumeLevel.setMax(Settings.System.getInt(getContentResolver(), "smart_charging_level", this.mSmartChargingLevelDefaultConfig) - 1);
            Settings.System.putInt(getContentResolver(), "smart_charging_resume_level", intValue2);
            return true;
        }
    }
}
