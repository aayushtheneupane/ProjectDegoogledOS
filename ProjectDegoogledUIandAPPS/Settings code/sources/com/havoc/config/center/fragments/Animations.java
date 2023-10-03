package com.havoc.config.center.fragments;

import android.content.ContentResolver;
import android.os.Bundle;
import android.provider.Settings;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import com.android.internal.util.havoc.AwesomeAnimationHelper;
import com.android.settings.SettingsPreferenceFragment;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.CustomSeekBarPreference;

public class Animations extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener {
    ListPreference mActivityClosePref;
    ListPreference mActivityOpenPref;
    private CustomSeekBarPreference mAnimDuration;
    private int[] mAnimations;
    private String[] mAnimationsNum;
    private String[] mAnimationsStrings;
    ListPreference mTaskClosePref;
    ListPreference mTaskMoveToBackPref;
    ListPreference mTaskMoveToFrontPref;
    ListPreference mTaskOpenBehind;
    ListPreference mTaskOpenPref;
    private ListPreference mTileAnimationDuration;
    private ListPreference mTileAnimationInterpolator;
    private ListPreference mTileAnimationStyle;
    ListPreference mWallpaperClose;
    ListPreference mWallpaperIntraClose;
    ListPreference mWallpaperIntraOpen;
    ListPreference mWallpaperOpen;

    public int getMetricsCategory() {
        return 1999;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(C1715R.xml.config_center_animations);
        ContentResolver contentResolver = getActivity().getContentResolver();
        getPreferenceScreen();
        this.mAnimDuration = (CustomSeekBarPreference) findPreference("animation_controls_duration");
        this.mAnimDuration.setValue(Settings.Global.getInt(contentResolver, "animation_controls_duration", 0));
        this.mAnimDuration.setOnPreferenceChangeListener(this);
        this.mAnimations = AwesomeAnimationHelper.getAnimationsList();
        int length = this.mAnimations.length;
        this.mAnimationsStrings = new String[length];
        this.mAnimationsNum = new String[length];
        for (int i = 0; i < length; i++) {
            this.mAnimationsStrings[i] = AwesomeAnimationHelper.getProperName(getActivity().getApplicationContext(), this.mAnimations[i]);
            this.mAnimationsNum[i] = String.valueOf(this.mAnimations[i]);
        }
        this.mActivityOpenPref = (ListPreference) findPreference("activity_open");
        ListPreference listPreference = this.mActivityOpenPref;
        listPreference.setSummary(getProperSummary(listPreference));
        this.mActivityOpenPref.setEntries((CharSequence[]) this.mAnimationsStrings);
        this.mActivityOpenPref.setEntryValues((CharSequence[]) this.mAnimationsNum);
        this.mActivityOpenPref.setOnPreferenceChangeListener(this);
        this.mActivityClosePref = (ListPreference) findPreference("activity_close");
        ListPreference listPreference2 = this.mActivityClosePref;
        listPreference2.setSummary(getProperSummary(listPreference2));
        this.mActivityClosePref.setEntries((CharSequence[]) this.mAnimationsStrings);
        this.mActivityClosePref.setEntryValues((CharSequence[]) this.mAnimationsNum);
        this.mActivityClosePref.setOnPreferenceChangeListener(this);
        this.mTaskOpenPref = (ListPreference) findPreference("task_open");
        ListPreference listPreference3 = this.mTaskOpenPref;
        listPreference3.setSummary(getProperSummary(listPreference3));
        this.mTaskOpenPref.setEntries((CharSequence[]) this.mAnimationsStrings);
        this.mTaskOpenPref.setEntryValues((CharSequence[]) this.mAnimationsNum);
        this.mTaskOpenPref.setOnPreferenceChangeListener(this);
        this.mTaskOpenBehind = (ListPreference) findPreference("task_open_behind");
        ListPreference listPreference4 = this.mTaskOpenBehind;
        listPreference4.setSummary(getProperSummary(listPreference4));
        this.mTaskOpenBehind.setEntries((CharSequence[]) this.mAnimationsStrings);
        this.mTaskOpenBehind.setEntryValues((CharSequence[]) this.mAnimationsNum);
        this.mTaskOpenBehind.setOnPreferenceChangeListener(this);
        this.mTaskClosePref = (ListPreference) findPreference("task_close");
        ListPreference listPreference5 = this.mTaskClosePref;
        listPreference5.setSummary(getProperSummary(listPreference5));
        this.mTaskClosePref.setEntries((CharSequence[]) this.mAnimationsStrings);
        this.mTaskClosePref.setEntryValues((CharSequence[]) this.mAnimationsNum);
        this.mTaskClosePref.setOnPreferenceChangeListener(this);
        this.mTaskMoveToFrontPref = (ListPreference) findPreference("task_move_to_front");
        ListPreference listPreference6 = this.mTaskMoveToFrontPref;
        listPreference6.setSummary(getProperSummary(listPreference6));
        this.mTaskMoveToFrontPref.setEntries((CharSequence[]) this.mAnimationsStrings);
        this.mTaskMoveToFrontPref.setEntryValues((CharSequence[]) this.mAnimationsNum);
        this.mTaskMoveToFrontPref.setOnPreferenceChangeListener(this);
        this.mTaskMoveToBackPref = (ListPreference) findPreference("task_move_to_back");
        ListPreference listPreference7 = this.mTaskMoveToBackPref;
        listPreference7.setSummary(getProperSummary(listPreference7));
        this.mTaskMoveToBackPref.setEntries((CharSequence[]) this.mAnimationsStrings);
        this.mTaskMoveToBackPref.setEntryValues((CharSequence[]) this.mAnimationsNum);
        this.mTaskMoveToBackPref.setOnPreferenceChangeListener(this);
        this.mWallpaperOpen = (ListPreference) findPreference("wallpaper_open");
        ListPreference listPreference8 = this.mWallpaperOpen;
        listPreference8.setSummary(getProperSummary(listPreference8));
        this.mWallpaperOpen.setEntries((CharSequence[]) this.mAnimationsStrings);
        this.mWallpaperOpen.setEntryValues((CharSequence[]) this.mAnimationsNum);
        this.mWallpaperOpen.setOnPreferenceChangeListener(this);
        this.mWallpaperClose = (ListPreference) findPreference("wallpaper_close");
        ListPreference listPreference9 = this.mWallpaperClose;
        listPreference9.setSummary(getProperSummary(listPreference9));
        this.mWallpaperClose.setEntries((CharSequence[]) this.mAnimationsStrings);
        this.mWallpaperClose.setEntryValues((CharSequence[]) this.mAnimationsNum);
        this.mWallpaperClose.setOnPreferenceChangeListener(this);
        this.mWallpaperIntraOpen = (ListPreference) findPreference("wallpaper_intra_open");
        ListPreference listPreference10 = this.mWallpaperIntraOpen;
        listPreference10.setSummary(getProperSummary(listPreference10));
        this.mWallpaperIntraOpen.setEntries((CharSequence[]) this.mAnimationsStrings);
        this.mWallpaperIntraOpen.setEntryValues((CharSequence[]) this.mAnimationsNum);
        this.mWallpaperIntraOpen.setOnPreferenceChangeListener(this);
        this.mWallpaperIntraClose = (ListPreference) findPreference("wallpaper_intra_close");
        ListPreference listPreference11 = this.mWallpaperIntraClose;
        listPreference11.setSummary(getProperSummary(listPreference11));
        this.mWallpaperIntraClose.setEntries((CharSequence[]) this.mAnimationsStrings);
        this.mWallpaperIntraClose.setEntryValues((CharSequence[]) this.mAnimationsNum);
        this.mWallpaperIntraClose.setOnPreferenceChangeListener(this);
        this.mTileAnimationStyle = (ListPreference) findPreference("qs_tile_animation_style");
        int intForUser = Settings.System.getIntForUser(getContentResolver(), "anim_tile_style", 0, -2);
        this.mTileAnimationStyle.setValue(String.valueOf(intForUser));
        updateTileAnimationStyleSummary(intForUser);
        this.mTileAnimationStyle.setOnPreferenceChangeListener(this);
        this.mTileAnimationDuration = (ListPreference) findPreference("qs_tile_animation_duration");
        int intForUser2 = Settings.System.getIntForUser(getContentResolver(), "anim_tile_duration", 2000, -2);
        this.mTileAnimationDuration.setValue(String.valueOf(intForUser2));
        updateTileAnimationDurationSummary(intForUser2);
        this.mTileAnimationDuration.setOnPreferenceChangeListener(this);
        this.mTileAnimationInterpolator = (ListPreference) findPreference("qs_tile_animation_interpolator");
        int intForUser3 = Settings.System.getIntForUser(getContentResolver(), "anim_tile_interpolator", 0, -2);
        this.mTileAnimationInterpolator.setValue(String.valueOf(intForUser3));
        updateTileAnimationInterpolatorSummary(intForUser3);
        this.mTileAnimationInterpolator.setOnPreferenceChangeListener(this);
        updateAnimTileStyle(intForUser);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        ContentResolver contentResolver = getActivity().getContentResolver();
        if (preference == this.mAnimDuration) {
            Settings.Global.putInt(contentResolver, "animation_controls_duration", ((Integer) obj).intValue());
            return true;
        } else if (preference == this.mActivityOpenPref) {
            Settings.Global.putInt(contentResolver, Settings.Global.ACTIVITY_ANIMATION_CONTROLS[0], Integer.parseInt((String) obj));
            preference.setSummary((CharSequence) getProperSummary(preference));
            return true;
        } else if (preference == this.mActivityClosePref) {
            Settings.Global.putInt(contentResolver, Settings.Global.ACTIVITY_ANIMATION_CONTROLS[1], Integer.parseInt((String) obj));
            preference.setSummary((CharSequence) getProperSummary(preference));
            return true;
        } else if (preference == this.mTaskOpenPref) {
            Settings.Global.putInt(contentResolver, Settings.Global.ACTIVITY_ANIMATION_CONTROLS[2], Integer.parseInt((String) obj));
            preference.setSummary((CharSequence) getProperSummary(preference));
            return true;
        } else if (preference == this.mTaskClosePref) {
            Settings.Global.putInt(contentResolver, Settings.Global.ACTIVITY_ANIMATION_CONTROLS[3], Integer.parseInt((String) obj));
            preference.setSummary((CharSequence) getProperSummary(preference));
            return true;
        } else if (preference == this.mTaskMoveToFrontPref) {
            Settings.Global.putInt(contentResolver, Settings.Global.ACTIVITY_ANIMATION_CONTROLS[4], Integer.parseInt((String) obj));
            preference.setSummary((CharSequence) getProperSummary(preference));
            return true;
        } else if (preference == this.mTaskMoveToBackPref) {
            Settings.Global.putInt(contentResolver, Settings.Global.ACTIVITY_ANIMATION_CONTROLS[5], Integer.parseInt((String) obj));
            preference.setSummary((CharSequence) getProperSummary(preference));
            return true;
        } else if (preference == this.mWallpaperOpen) {
            Settings.Global.putInt(contentResolver, Settings.Global.ACTIVITY_ANIMATION_CONTROLS[6], Integer.parseInt((String) obj));
            preference.setSummary((CharSequence) getProperSummary(preference));
            return true;
        } else if (preference == this.mWallpaperClose) {
            Settings.Global.putInt(contentResolver, Settings.Global.ACTIVITY_ANIMATION_CONTROLS[7], Integer.parseInt((String) obj));
            preference.setSummary((CharSequence) getProperSummary(preference));
            return true;
        } else if (preference == this.mWallpaperIntraOpen) {
            Settings.Global.putInt(contentResolver, Settings.Global.ACTIVITY_ANIMATION_CONTROLS[8], Integer.parseInt((String) obj));
            preference.setSummary((CharSequence) getProperSummary(preference));
            return true;
        } else if (preference == this.mWallpaperIntraClose) {
            Settings.Global.putInt(contentResolver, Settings.Global.ACTIVITY_ANIMATION_CONTROLS[9], Integer.parseInt((String) obj));
            preference.setSummary((CharSequence) getProperSummary(preference));
            return true;
        } else if (preference == this.mTaskOpenBehind) {
            Settings.Global.putInt(contentResolver, Settings.Global.ACTIVITY_ANIMATION_CONTROLS[10], Integer.parseInt((String) obj));
            preference.setSummary((CharSequence) getProperSummary(preference));
            return true;
        } else if (preference == this.mTileAnimationStyle) {
            int intValue = Integer.valueOf((String) obj).intValue();
            Settings.System.putIntForUser(getContentResolver(), "anim_tile_style", intValue, -2);
            updateTileAnimationStyleSummary(intValue);
            updateAnimTileStyle(intValue);
            return true;
        } else if (preference == this.mTileAnimationDuration) {
            int intValue2 = Integer.valueOf((String) obj).intValue();
            Settings.System.putIntForUser(getContentResolver(), "anim_tile_duration", intValue2, -2);
            updateTileAnimationDurationSummary(intValue2);
            return true;
        } else if (preference != this.mTileAnimationInterpolator) {
            return false;
        } else {
            int intValue3 = Integer.valueOf((String) obj).intValue();
            Settings.System.putIntForUser(getContentResolver(), "anim_tile_interpolator", intValue3, -2);
            updateTileAnimationInterpolatorSummary(intValue3);
            return true;
        }
    }

    private String getProperSummary(Preference preference) {
        String str;
        if (preference == this.mActivityOpenPref) {
            str = Settings.Global.ACTIVITY_ANIMATION_CONTROLS[0];
        } else if (preference == this.mActivityClosePref) {
            str = Settings.Global.ACTIVITY_ANIMATION_CONTROLS[1];
        } else if (preference == this.mTaskOpenPref) {
            str = Settings.Global.ACTIVITY_ANIMATION_CONTROLS[2];
        } else if (preference == this.mTaskClosePref) {
            str = Settings.Global.ACTIVITY_ANIMATION_CONTROLS[3];
        } else if (preference == this.mTaskMoveToFrontPref) {
            str = Settings.Global.ACTIVITY_ANIMATION_CONTROLS[4];
        } else if (preference == this.mTaskMoveToBackPref) {
            str = Settings.Global.ACTIVITY_ANIMATION_CONTROLS[5];
        } else if (preference == this.mWallpaperOpen) {
            str = Settings.Global.ACTIVITY_ANIMATION_CONTROLS[6];
        } else if (preference == this.mWallpaperClose) {
            str = Settings.Global.ACTIVITY_ANIMATION_CONTROLS[7];
        } else if (preference == this.mWallpaperIntraOpen) {
            str = Settings.Global.ACTIVITY_ANIMATION_CONTROLS[8];
        } else if (preference == this.mWallpaperIntraClose) {
            str = Settings.Global.ACTIVITY_ANIMATION_CONTROLS[9];
        } else {
            str = preference == this.mTaskOpenBehind ? Settings.Global.ACTIVITY_ANIMATION_CONTROLS[10] : "";
        }
        return this.mAnimationsStrings[Settings.Global.getInt(getActivity().getContentResolver(), str, 0)];
    }

    private void updateTileAnimationStyleSummary(int i) {
        this.mTileAnimationStyle.setSummary(getResources().getString(C1715R.string.qs_set_animation_style, new Object[]{(String) this.mTileAnimationStyle.getEntries()[this.mTileAnimationStyle.findIndexOfValue(String.valueOf(i))]}));
    }

    private void updateTileAnimationDurationSummary(int i) {
        this.mTileAnimationDuration.setSummary(getResources().getString(C1715R.string.qs_set_animation_duration, new Object[]{(String) this.mTileAnimationDuration.getEntries()[this.mTileAnimationDuration.findIndexOfValue(String.valueOf(i))]}));
    }

    private void updateTileAnimationInterpolatorSummary(int i) {
        this.mTileAnimationInterpolator.setSummary(getResources().getString(C1715R.string.qs_set_animation_interpolator, new Object[]{(String) this.mTileAnimationInterpolator.getEntries()[this.mTileAnimationInterpolator.findIndexOfValue(String.valueOf(i))]}));
    }

    private void updateAnimTileStyle(int i) {
        ListPreference listPreference = this.mTileAnimationDuration;
        if (listPreference == null) {
            return;
        }
        if (i == 0) {
            listPreference.setEnabled(false);
            this.mTileAnimationInterpolator.setEnabled(false);
            return;
        }
        listPreference.setEnabled(true);
        this.mTileAnimationInterpolator.setEnabled(true);
    }
}
