package com.android.settings.livedisplay;

import android.content.Context;
import android.content.res.Resources;
import android.hardware.display.ColorDisplayManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import com.android.internal.custom.hardware.DisplayMode;
import com.android.internal.custom.hardware.LineageHardwareManager;
import com.android.internal.custom.hardware.LiveDisplayConfig;
import com.android.internal.custom.hardware.LiveDisplayManager;
import com.android.internal.util.ArrayUtils;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.custom.preference.CustomDialogPreference;
import com.android.settings.custom.utils.ResourceUtils;
import com.android.settings.custom.utils.SettingsHelper;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settings.search.SearchIndexableRaw;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.SwitchPreference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class LiveDisplaySettings extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener, SettingsHelper.OnSettingsChangeListener {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<String> getNonIndexableKeys(Context context) {
            LiveDisplayConfig config = LiveDisplayManager.getInstance(context).getConfig();
            ArrayList arrayList = new ArrayList();
            if (!config.hasFeature(15)) {
                arrayList.add("live_display_color_profile");
            }
            if (!config.hasFeature(3)) {
                arrayList.add("display_auto_outdoor_mode");
            }
            if (!config.hasFeature(12)) {
                arrayList.add("display_color_enhance");
            }
            if (!config.hasFeature(10)) {
                arrayList.add("display_low_power");
            }
            if (!config.hasFeature(13)) {
                arrayList.add("color_calibration");
            }
            if (!config.hasFeature(17)) {
                arrayList.add("picture_adjustment");
            }
            if (!config.hasFeature(18)) {
                arrayList.add("display_reading_mode");
            }
            if (ColorDisplayManager.isNightDisplayAvailable(context)) {
                if (!config.hasFeature(3)) {
                    arrayList.add("live_display");
                }
                arrayList.add("live_display_color_temperature");
            }
            return arrayList;
        }

        public List<SearchIndexableRaw> getRawDataToIndex(Context context, boolean z) {
            DisplayMode[] displayModes;
            LiveDisplayConfig config = LiveDisplayManager.getInstance(context).getConfig();
            ArrayList arrayList = new ArrayList();
            if (config.hasFeature(15) && (displayModes = LineageHardwareManager.getInstance(context).getDisplayModes()) != null && displayModes.length > 0) {
                for (DisplayMode displayMode : displayModes) {
                    arrayList.add(ResourceUtils.getLocalizedString(context.getResources(), displayMode.name, "live_display_color_profile_%s_title"));
                }
            }
            SearchIndexableRaw searchIndexableRaw = new SearchIndexableRaw(context);
            searchIndexableRaw.entries = TextUtils.join(" ", arrayList);
            searchIndexableRaw.key = "live_display_color_profile";
            searchIndexableRaw.title = context.getString(C1715R.string.live_display_color_profile_title);
            searchIndexableRaw.rank = 2;
            return Collections.singletonList(searchIndexableRaw);
        }
    };
    private final Uri DISPLAY_TEMPERATURE_DAY_URI = Settings.System.getUriFor("display_temperature_day");
    private final Uri DISPLAY_TEMPERATURE_MODE_URI = Settings.System.getUriFor("display_temperature_mode");
    private final Uri DISPLAY_TEMPERATURE_NIGHT_URI = Settings.System.getUriFor("display_temperature_night");
    private SwitchPreference mColorEnhancement;
    private ListPreference mColorProfile;
    private String[] mColorProfileSummaries;
    private LiveDisplayConfig mConfig;
    private DisplayColor mDisplayColor;
    private DisplayTemperature mDisplayTemperature;
    private LineageHardwareManager mHardware;
    private boolean mHasDisplayModes = false;
    private ListPreference mLiveDisplay;
    private LiveDisplayManager mLiveDisplayManager;
    private SwitchPreference mLowPower;
    private String[] mModeEntries;
    private String[] mModeSummaries;
    private String[] mModeValues;
    private SwitchPreference mOutdoorMode;
    private PictureAdjustment mPictureAdjustment;
    private SwitchPreference mReadingMode;

    public int getMetricsCategory() {
        return 1999;
    }

    public void onCreate(Bundle bundle) {
        int[] iArr;
        super.onCreate(bundle);
        Resources resources = getResources();
        boolean isNightDisplayAvailable = ColorDisplayManager.isNightDisplayAvailable(getContext());
        this.mHardware = LineageHardwareManager.getInstance(getActivity());
        this.mLiveDisplayManager = LiveDisplayManager.getInstance(getActivity());
        this.mConfig = this.mLiveDisplayManager.getConfig();
        addPreferencesFromResource(C1715R.xml.livedisplay);
        PreferenceCategory preferenceCategory = (PreferenceCategory) findPreference("live_display_options");
        PreferenceCategory preferenceCategory2 = (PreferenceCategory) findPreference("advanced");
        int mode = this.mLiveDisplayManager.getMode();
        this.mLiveDisplay = (ListPreference) findPreference("live_display");
        this.mLiveDisplay.setValue(String.valueOf(mode));
        this.mModeEntries = resources.getStringArray(17236112);
        this.mModeValues = resources.getStringArray(17236114);
        this.mModeSummaries = resources.getStringArray(17236113);
        if (!this.mConfig.hasFeature(3)) {
            iArr = ArrayUtils.appendInt((int[]) null, ArrayUtils.indexOf(this.mModeValues, String.valueOf(3)));
        } else {
            if (isNightDisplayAvailable) {
                this.mModeSummaries[ArrayUtils.indexOf(this.mModeValues, String.valueOf(2))] = resources.getString(C1715R.string.live_display_outdoor_mode_summary);
            }
            iArr = null;
        }
        if (isNightDisplayAvailable) {
            iArr = ArrayUtils.appendInt(ArrayUtils.appendInt(iArr, ArrayUtils.indexOf(this.mModeValues, String.valueOf(4))), ArrayUtils.indexOf(this.mModeValues, String.valueOf(1)));
        }
        if (iArr != null) {
            String[] strArr = new String[(this.mModeEntries.length - iArr.length)];
            String[] strArr2 = new String[(this.mModeValues.length - iArr.length)];
            String[] strArr3 = new String[(this.mModeSummaries.length - iArr.length)];
            int i = 0;
            for (int i2 = 0; i2 < this.mModeEntries.length; i2++) {
                if (!ArrayUtils.contains(iArr, i2)) {
                    strArr[i] = this.mModeEntries[i2];
                    strArr2[i] = this.mModeValues[i2];
                    strArr3[i] = this.mModeSummaries[i2];
                    i++;
                }
            }
            this.mModeEntries = strArr;
            this.mModeValues = strArr2;
            this.mModeSummaries = strArr3;
        }
        this.mLiveDisplay.setEntries((CharSequence[]) this.mModeEntries);
        this.mLiveDisplay.setEntryValues((CharSequence[]) this.mModeValues);
        this.mLiveDisplay.setOnPreferenceChangeListener(this);
        this.mDisplayTemperature = (DisplayTemperature) findPreference("live_display_color_temperature");
        if (isNightDisplayAvailable) {
            if (!this.mConfig.hasFeature(3)) {
                preferenceCategory.removePreference(this.mLiveDisplay);
            }
            preferenceCategory.removePreference(this.mDisplayTemperature);
        }
        this.mColorProfile = (ListPreference) findPreference("live_display_color_profile");
        if (preferenceCategory == null || this.mColorProfile == null || (this.mConfig.hasFeature(15) && updateDisplayModes())) {
            this.mHasDisplayModes = true;
            this.mColorProfile.setOnPreferenceChangeListener(this);
        } else {
            preferenceCategory.removePreference(this.mColorProfile);
        }
        this.mOutdoorMode = (SwitchPreference) findPreference("display_auto_outdoor_mode");
        if (!(preferenceCategory == null || this.mOutdoorMode == null || (!isNightDisplayAvailable && this.mConfig.hasFeature(3)))) {
            preferenceCategory.removePreference(this.mOutdoorMode);
            this.mOutdoorMode = null;
        }
        this.mReadingMode = (SwitchPreference) findPreference("display_reading_mode");
        if (preferenceCategory == null || this.mReadingMode == null || this.mHardware.isSupported(16384)) {
            this.mReadingMode.setOnPreferenceChangeListener(this);
        } else {
            preferenceCategory.removePreference(this.mReadingMode);
            this.mReadingMode = null;
        }
        this.mLowPower = (SwitchPreference) findPreference("display_low_power");
        if (!(preferenceCategory2 == null || this.mLowPower == null || this.mConfig.hasFeature(10))) {
            preferenceCategory2.removePreference(this.mLowPower);
            this.mLowPower = null;
        }
        this.mColorEnhancement = (SwitchPreference) findPreference("display_color_enhance");
        if (!(preferenceCategory2 == null || this.mColorEnhancement == null || this.mConfig.hasFeature(12))) {
            preferenceCategory2.removePreference(this.mColorEnhancement);
            this.mColorEnhancement = null;
        }
        this.mPictureAdjustment = (PictureAdjustment) findPreference("picture_adjustment");
        if (!(preferenceCategory2 == null || this.mPictureAdjustment == null || this.mConfig.hasFeature(17))) {
            preferenceCategory2.removePreference(this.mPictureAdjustment);
            this.mPictureAdjustment = null;
        }
        this.mDisplayColor = (DisplayColor) findPreference("color_calibration");
        if (preferenceCategory2 != null && this.mDisplayColor != null && !this.mConfig.hasFeature(13)) {
            preferenceCategory2.removePreference(this.mDisplayColor);
            this.mDisplayColor = null;
        }
    }

    public void onResume() {
        super.onResume();
        updateModeSummary();
        updateTemperatureSummary();
        updateColorProfileSummary((String) null);
        updateReadingModeStatus();
        SettingsHelper.get(getActivity()).startWatching(this, this.DISPLAY_TEMPERATURE_DAY_URI, this.DISPLAY_TEMPERATURE_MODE_URI, this.DISPLAY_TEMPERATURE_NIGHT_URI);
    }

    public void onPause() {
        super.onPause();
        SettingsHelper.get(getActivity()).stopWatching(this);
    }

    private boolean updateDisplayModes() {
        int i;
        DisplayMode[] displayModes = this.mHardware.getDisplayModes();
        if (displayModes == null || displayModes.length == 0) {
            return false;
        }
        DisplayMode currentDisplayMode = this.mHardware.getCurrentDisplayMode() != null ? this.mHardware.getCurrentDisplayMode() : this.mHardware.getDefaultDisplayMode();
        String[] strArr = new String[displayModes.length];
        String[] strArr2 = new String[displayModes.length];
        this.mColorProfileSummaries = new String[displayModes.length];
        int i2 = -1;
        for (int i3 = 0; i3 < displayModes.length; i3++) {
            strArr2[i3] = String.valueOf(displayModes[i3].id);
            strArr[i3] = ResourceUtils.getLocalizedString(getResources(), displayModes[i3].name, "live_display_color_profile_%s_title");
            String localizedString = ResourceUtils.getLocalizedString(getResources(), displayModes[i3].name, "live_display_color_profile_%s_summary");
            if (localizedString != null) {
                localizedString = String.format("%s - %s", new Object[]{strArr[i3], localizedString});
            }
            this.mColorProfileSummaries[i3] = localizedString;
            if (currentDisplayMode != null && displayModes[i3].id == (i = currentDisplayMode.id)) {
                i2 = i;
            }
        }
        this.mColorProfile.setEntries((CharSequence[]) strArr);
        this.mColorProfile.setEntryValues((CharSequence[]) strArr2);
        if (i2 >= 0) {
            this.mColorProfile.setValue(String.valueOf(i2));
        }
        return true;
    }

    private void updateColorProfileSummary(String str) {
        int i;
        if (this.mHasDisplayModes) {
            if (str == null) {
                DisplayMode currentDisplayMode = this.mHardware.getCurrentDisplayMode() != null ? this.mHardware.getCurrentDisplayMode() : this.mHardware.getDefaultDisplayMode();
                if (currentDisplayMode != null && (i = currentDisplayMode.id) >= 0) {
                    str = String.valueOf(i);
                }
            }
            int findIndexOfValue = this.mColorProfile.findIndexOfValue(str);
            if (findIndexOfValue < 0) {
                Log.e("LiveDisplay", "No summary resource found for profile " + str);
                this.mColorProfile.setSummary((CharSequence) null);
                return;
            }
            this.mColorProfile.setValue(str);
            this.mColorProfile.setSummary(this.mColorProfileSummaries[findIndexOfValue]);
        }
    }

    private void updateModeSummary() {
        int mode = this.mLiveDisplayManager.getMode();
        int indexOf = ArrayUtils.indexOf(this.mModeValues, String.valueOf(mode));
        boolean z = false;
        if (indexOf < 0) {
            indexOf = ArrayUtils.indexOf(this.mModeValues, String.valueOf(0));
        }
        this.mLiveDisplay.setSummary(this.mModeSummaries[indexOf]);
        this.mLiveDisplay.setValue(String.valueOf(mode));
        DisplayTemperature displayTemperature = this.mDisplayTemperature;
        if (displayTemperature != null) {
            displayTemperature.setEnabled(mode != 0);
        }
        SwitchPreference switchPreference = this.mOutdoorMode;
        if (switchPreference != null) {
            if (mode != 0) {
                z = true;
            }
            switchPreference.setEnabled(z);
        }
    }

    private void updateTemperatureSummary() {
        int dayColorTemperature = this.mLiveDisplayManager.getDayColorTemperature();
        int nightColorTemperature = this.mLiveDisplayManager.getNightColorTemperature();
        this.mDisplayTemperature.setSummary((CharSequence) getResources().getString(C1715R.string.live_display_color_temperature_summary, new Object[]{Integer.valueOf(this.mDisplayTemperature.roundUp(dayColorTemperature)), Integer.valueOf(this.mDisplayTemperature.roundUp(nightColorTemperature))}));
    }

    private void updateReadingModeStatus() {
        SwitchPreference switchPreference = this.mReadingMode;
        if (switchPreference != null) {
            switchPreference.setChecked(this.mHardware.get(16384));
        }
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference == this.mLiveDisplay) {
            this.mLiveDisplayManager.setMode(Integer.valueOf((String) obj).intValue());
        } else if (preference == this.mColorProfile) {
            String str = (String) obj;
            int intValue = Integer.valueOf(str).intValue();
            Log.i("LiveDisplay", "Setting mode: " + intValue);
            DisplayMode[] displayModes = this.mHardware.getDisplayModes();
            int length = displayModes.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                DisplayMode displayMode = displayModes[i];
                if (displayMode.id == intValue) {
                    this.mHardware.setDisplayMode(displayMode, true);
                    updateColorProfileSummary(str);
                    break;
                }
                i++;
            }
        } else if (preference == this.mReadingMode) {
            this.mHardware.set(16384, ((Boolean) obj).booleanValue());
        }
        return true;
    }

    public void onSettingsChanged(Uri uri) {
        updateModeSummary();
        updateTemperatureSummary();
        updateReadingModeStatus();
    }

    public void onDisplayPreferenceDialog(Preference preference) {
        if (preference.getKey() == null) {
            preference.setKey(UUID.randomUUID().toString());
        }
        if (preference instanceof CustomDialogPreference) {
            CustomDialogPreference.CustomPreferenceDialogFragment newInstance = CustomDialogPreference.CustomPreferenceDialogFragment.newInstance(preference.getKey());
            newInstance.setTargetFragment(this, 0);
            newInstance.show(getFragmentManager(), "dialog_preference");
            onDialogShowing();
            return;
        }
        super.onDisplayPreferenceDialog(preference);
    }
}
