package com.android.settings.accessibility;

import android.content.Context;
import android.hardware.display.ColorDisplayManager;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import android.provider.Settings;
import android.widget.Switch;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settings.widget.SwitchBar;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class ToggleDaltonizerPreferenceFragment extends ToggleFeaturePreferenceFragment implements Preference.OnPreferenceChangeListener, SwitchBar.OnSwitchChangeListener {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.accessibility_daltonizer_settings;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }
    };
    private ListPreference mType;

    public int getHelpResource() {
        return C1715R.string.help_url_color_correction;
    }

    public int getMetricsCategory() {
        return 5;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.accessibility_daltonizer_settings;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mType = (ListPreference) findPreference("type");
        if (!ColorDisplayManager.isColorTransformAccelerated(getActivity())) {
            this.mFooterPreferenceMixin.createFooterPreference().setTitle((int) C1715R.string.accessibility_display_daltonizer_preference_subtitle);
        }
        initPreferences();
    }

    /* access modifiers changed from: protected */
    public void onPreferenceToggled(String str, boolean z) {
        Settings.Secure.putInt(getContentResolver(), "accessibility_display_daltonizer_enabled", z ? 1 : 0);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference != this.mType) {
            return true;
        }
        Settings.Secure.putInt(getContentResolver(), "accessibility_display_daltonizer", Integer.parseInt((String) obj));
        preference.setSummary((CharSequence) "%s");
        return true;
    }

    /* access modifiers changed from: protected */
    public void onInstallSwitchBarToggleSwitch() {
        super.onInstallSwitchBarToggleSwitch();
        SwitchBar switchBar = this.mSwitchBar;
        boolean z = false;
        if (Settings.Secure.getInt(getContentResolver(), "accessibility_display_daltonizer_enabled", 0) == 1) {
            z = true;
        }
        switchBar.setCheckedInternal(z);
        this.mSwitchBar.addOnSwitchChangeListener(this);
    }

    /* access modifiers changed from: protected */
    public void onRemoveSwitchBarToggleSwitch() {
        super.onRemoveSwitchBarToggleSwitch();
        this.mSwitchBar.removeOnSwitchChangeListener(this);
    }

    /* access modifiers changed from: protected */
    public void updateSwitchBarText(SwitchBar switchBar) {
        switchBar.setSwitchBarText(C1715R.string.accessibility_daltonizer_master_switch_title, C1715R.string.accessibility_daltonizer_master_switch_title);
    }

    private void initPreferences() {
        String num = Integer.toString(Settings.Secure.getInt(getContentResolver(), "accessibility_display_daltonizer", 12));
        this.mType.setValue(num);
        this.mType.setOnPreferenceChangeListener(this);
        if (this.mType.findIndexOfValue(num) < 0) {
            this.mType.setSummary(getString(C1715R.string.daltonizer_type_overridden, getString(C1715R.string.simulate_color_space)));
        }
    }

    public void onSwitchChanged(Switch switchR, boolean z) {
        onPreferenceToggled(this.mPreferenceKey, z);
    }
}
