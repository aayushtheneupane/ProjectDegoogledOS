package com.android.settings.development;

import android.content.Context;
import android.os.SystemProperties;
import android.text.TextUtils;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.development.DeveloperOptionsPreferenceController;
import com.android.settingslib.development.SystemPropPoker;
import com.havoc.config.center.C1715R;

public class SetGpuRendererPreferenceController extends DeveloperOptionsPreferenceController implements Preference.OnPreferenceChangeListener, PreferenceControllerMixin {
    private final String[] mListSummaries;
    private final String[] mListValues;
    private ListPreference mPreference;

    public String getPreferenceKey() {
        return "debug_hw_renderer";
    }

    public SetGpuRendererPreferenceController(Context context) {
        super(context);
        this.mListValues = context.getResources().getStringArray(C1715R.array.debug_hw_renderer_values);
        this.mListSummaries = context.getResources().getStringArray(C1715R.array.debug_hw_renderer_entries);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = (ListPreference) preferenceScreen.findPreference(getPreferenceKey());
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        writeDebugHwRendererOptions(obj);
        updateDebugHwRendererOptions();
        return true;
    }

    public void updateState(Preference preference) {
        updateDebugHwRendererOptions();
    }

    /* access modifiers changed from: protected */
    public void onDeveloperOptionsSwitchEnabled() {
        this.mPreference.setEnabled(true);
    }

    /* access modifiers changed from: protected */
    public void onDeveloperOptionsSwitchDisabled() {
        this.mPreference.setEnabled(false);
    }

    private void writeDebugHwRendererOptions(Object obj) {
        SystemProperties.set("debug.hwui.renderer", obj == null ? "" : obj.toString());
        SystemPropPoker.getInstance().poke();
    }

    private void updateDebugHwRendererOptions() {
        String str = SystemProperties.get("debug.hwui.renderer", "");
        int i = 0;
        int i2 = 0;
        while (true) {
            String[] strArr = this.mListValues;
            if (i2 >= strArr.length) {
                break;
            } else if (TextUtils.equals(str, strArr[i2])) {
                i = i2;
                break;
            } else {
                i2++;
            }
        }
        this.mPreference.setValue(this.mListValues[i]);
        this.mPreference.setSummary(this.mListSummaries[i]);
    }
}
