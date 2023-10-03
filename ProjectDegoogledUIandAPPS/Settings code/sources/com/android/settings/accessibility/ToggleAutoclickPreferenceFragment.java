package com.android.settings.accessibility;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import android.provider.Settings;
import android.widget.Switch;
import androidx.preference.Preference;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settings.widget.SeekBarPreference;
import com.android.settings.widget.SwitchBar;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class ToggleAutoclickPreferenceFragment extends ToggleFeaturePreferenceFragment implements SwitchBar.OnSwitchChangeListener, Preference.OnPreferenceChangeListener {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.accessibility_autoclick_settings;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }
    };
    private static final int[] mAutoclickPreferenceSummaries = {C1715R.plurals.accessibilty_autoclick_preference_subtitle_extremely_short_delay, C1715R.plurals.accessibilty_autoclick_preference_subtitle_very_short_delay, C1715R.plurals.accessibilty_autoclick_preference_subtitle_short_delay, C1715R.plurals.accessibilty_autoclick_preference_subtitle_long_delay, C1715R.plurals.accessibilty_autoclick_preference_subtitle_very_long_delay};
    private SeekBarPreference mDelay;

    private int seekBarProgressToDelay(int i) {
        return (i * 100) + 200;
    }

    public int getHelpResource() {
        return C1715R.string.help_url_autoclick;
    }

    public int getMetricsCategory() {
        return 335;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.accessibility_autoclick_settings;
    }

    static CharSequence getAutoclickPreferenceSummary(Resources resources, int i) {
        return resources.getQuantityString(mAutoclickPreferenceSummaries[getAutoclickPreferenceSummaryIndex(i)], i, new Object[]{Integer.valueOf(i)});
    }

    private static int getAutoclickPreferenceSummaryIndex(int i) {
        if (i <= 200) {
            return 0;
        }
        if (i >= 1000) {
            return mAutoclickPreferenceSummaries.length - 1;
        }
        return (i - 200) / (800 / (mAutoclickPreferenceSummaries.length - 1));
    }

    /* access modifiers changed from: protected */
    public void onPreferenceToggled(String str, boolean z) {
        Settings.Secure.putInt(getContentResolver(), str, z ? 1 : 0);
        this.mDelay.setEnabled(z);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int i = Settings.Secure.getInt(getContentResolver(), "accessibility_autoclick_delay", 600);
        this.mDelay = (SeekBarPreference) findPreference("autoclick_delay");
        this.mDelay.setMax(delayToSeekBarProgress(1000));
        this.mDelay.setProgress(delayToSeekBarProgress(i));
        this.mDelay.setOnPreferenceChangeListener(this);
        this.mFooterPreferenceMixin.createFooterPreference().setTitle((int) C1715R.string.accessibility_autoclick_description);
    }

    /* access modifiers changed from: protected */
    public void onInstallSwitchBarToggleSwitch() {
        super.onInstallSwitchBarToggleSwitch();
        boolean z = false;
        int i = Settings.Secure.getInt(getContentResolver(), "accessibility_autoclick_enabled", 0);
        this.mSwitchBar.setCheckedInternal(i == 1);
        this.mSwitchBar.addOnSwitchChangeListener(this);
        SeekBarPreference seekBarPreference = this.mDelay;
        if (i == 1) {
            z = true;
        }
        seekBarPreference.setEnabled(z);
    }

    /* access modifiers changed from: protected */
    public void onRemoveSwitchBarToggleSwitch() {
        super.onRemoveSwitchBarToggleSwitch();
        this.mSwitchBar.removeOnSwitchChangeListener(this);
    }

    public void onSwitchChanged(Switch switchR, boolean z) {
        onPreferenceToggled("accessibility_autoclick_enabled", z);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference != this.mDelay || !(obj instanceof Integer)) {
            return false;
        }
        Settings.Secure.putInt(getContentResolver(), "accessibility_autoclick_delay", seekBarProgressToDelay(((Integer) obj).intValue()));
        return true;
    }

    private int delayToSeekBarProgress(int i) {
        return (i - 200) / 100;
    }
}
