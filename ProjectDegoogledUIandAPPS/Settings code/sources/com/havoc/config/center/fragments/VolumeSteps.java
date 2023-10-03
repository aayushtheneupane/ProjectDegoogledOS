package com.havoc.config.center.fragments;

import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.SettingsPreferenceFragment;
import com.havoc.config.center.C1715R;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class VolumeSteps extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener {
    private static final Set<String> telephony_set = new HashSet();
    private static final Map<String, Integer> volume_map = new HashMap();
    private AudioManager mAudioManager;
    private Set<String> mAvailableKeys = new HashSet();

    public int getMetricsCategory() {
        return 1999;
    }

    static {
        volume_map.put("volume_steps_alarm", new Integer(4));
        volume_map.put("volume_steps_dtmf", new Integer(8));
        volume_map.put("volume_steps_music", new Integer(3));
        volume_map.put("volume_steps_notification", new Integer(5));
        volume_map.put("volume_steps_ring", new Integer(2));
        volume_map.put("volume_steps_system", new Integer(1));
        volume_map.put("volume_steps_voice_call", new Integer(0));
        telephony_set.add("volume_steps_dtmf");
        telephony_set.add("volume_steps_ring");
        telephony_set.add("volume_steps_voice_call");
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(C1715R.xml.volume_steps_fragment);
        this.mAudioManager = (AudioManager) getActivity().getSystemService("audio");
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        this.mAvailableKeys = volume_map.keySet();
        if (!(TelephonyManager.getDefault().getCurrentPhoneType() != 0)) {
            this.mAvailableKeys.removeAll(telephony_set);
            for (String findPreference : telephony_set) {
                Preference findPreference2 = preferenceScreen.findPreference(findPreference);
                if (findPreference2 != null) {
                    preferenceScreen.removePreference(findPreference2);
                }
            }
        }
        boolean checkForFirstRun = checkForFirstRun();
        String string = getString(C1715R.string.volume_steps_reset);
        for (String next : this.mAvailableKeys) {
            Preference findPreference3 = preferenceScreen.findPreference(next);
            if (findPreference3 != null && (findPreference3 instanceof ListPreference)) {
                ListPreference listPreference = (ListPreference) findPreference3;
                int streamMaxVolume = this.mAudioManager.getStreamMaxVolume(volume_map.get(next).intValue());
                if (checkForFirstRun) {
                    saveDefaultSteps(listPreference, streamMaxVolume);
                }
                int defaultSteps = getDefaultSteps(listPreference);
                CharSequence[] charSequenceArr = new CharSequence[(listPreference.getEntries().length + 1)];
                CharSequence[] charSequenceArr2 = new CharSequence[(listPreference.getEntryValues().length + 1)];
                for (int i = 0; i < charSequenceArr.length; i++) {
                    if (i == 0) {
                        charSequenceArr[i] = string;
                        charSequenceArr2[i] = String.valueOf(defaultSteps);
                    } else {
                        int i2 = i - 1;
                        charSequenceArr[i] = listPreference.getEntries()[i2];
                        charSequenceArr2[i] = listPreference.getEntryValues()[i2];
                    }
                }
                listPreference.setEntries(charSequenceArr);
                listPreference.setEntryValues(charSequenceArr2);
                updateVolumeStepPrefs(listPreference, streamMaxVolume);
                listPreference.setOnPreferenceChangeListener(this);
            }
        }
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (!preference.hasKey() || !this.mAvailableKeys.contains(preference.getKey())) {
            return true;
        }
        commitVolumeSteps(preference, Integer.parseInt(obj.toString()));
        return true;
    }

    private SharedPreferences getDefaultStepsPrefs() {
        return getActivity().getSharedPreferences("volume_step_defaults", 0);
    }

    private boolean checkForFirstRun() {
        if (getDefaultStepsPrefs().getString("first_run", (String) null) != null) {
            return false;
        }
        getDefaultStepsPrefs().edit().putString("first_run", "first_run_initialized").commit();
        return true;
    }

    private int getDefaultSteps(Preference preference) {
        String string;
        if (preference == null || !(preference instanceof ListPreference) || (string = getDefaultStepsPrefs().getString(preference.getKey(), (String) null)) == null) {
            return -1;
        }
        return Integer.parseInt(string);
    }

    private void saveDefaultSteps(Preference preference, int i) {
        getDefaultStepsPrefs().edit().putString(preference.getKey(), String.valueOf(i)).commit();
    }

    private void updateVolumeStepPrefs(Preference preference, int i) {
        if (preference != null && (preference instanceof ListPreference)) {
            ListPreference listPreference = (ListPreference) preference;
            listPreference.setSummary(String.valueOf(i));
            listPreference.setValue(String.valueOf(i));
        }
    }

    private void commitVolumeSteps(Preference preference, int i) {
        Settings.System.putInt(getActivity().getContentResolver(), preference.getKey(), i);
        this.mAudioManager.setStreamMaxVolume(volume_map.get(preference.getKey()).intValue(), i);
        updateVolumeStepPrefs(preference, i);
        Log.i("VolumeSteps", "Volume steps:" + preference.getKey() + "" + String.valueOf(i));
    }
}
