package com.android.settings.notification;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;
import androidx.preference.Preference;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.widget.RadioButtonPreference;
import com.android.settingslib.core.AbstractPreferenceController;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.SwitchPreference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VibrationSettingsPreferenceFragment extends DashboardFragment implements Preference.OnPreferenceClickListener {
    private static final long[] DA_DA_DZZZ_VIBRATION_PATTERN = {0, 30, 80, 30, 80, 50, 180, 600, 1050};
    private static final long[] DA_DZZZ_DA_VIBRATION_PATTERN = {0, 80, 200, 600, 150, 20, 1050};
    private static final long[] DZZZ_DA_VIBRATION_PATTERN = {0, 500, 200, 20, 720};
    private static final long[] DZZZ_DZZZ_VIBRATION_PATTERN = {0, 800, 800, 800, 800};
    private static final int[] FIVE_ELEMENTS_VIBRATION_AMPLITUDE = {0, 255, 0, 255, 0};
    private static final long[] MM_MM_MM_VIBRATION_PATTERN = {0, 300, 400, 300, 400, 300, 1700};
    private static final int[] NINE_ELEMENTS_VIBRATION_AMPLITUDE = {0, 255, 0, 255, 0, 255, 0, 255, 0};
    private static final int[] SEVEN_ELEMENTS_VIBRATION_AMPLITUDE = {0, 255, 0, 255, 0, 255, 0};
    private static final String[] mKeys = {"pattern_dzzz_dzzz", "pattern_dzzz_da", "pattern_mm_mm_mm", "pattern_da_da_dzzz", "pattern_da_dzzz_da"};
    /* access modifiers changed from: private */
    public final AudioAttributes mAudioAttributesNotif = new AudioAttributes.Builder().setUsage(5).build();
    private final AudioAttributes mAudioAttributesRingtone = new AudioAttributes.Builder().setUsage(6).build();
    /* access modifiers changed from: private */
    public ContentResolver mContentResolver;
    private Context mContext;

    /* renamed from: mH */
    private final Handler f56mH = new Handler();
    private boolean mHasOnePlusHaptics;
    private SwitchPreference mIncallFeedback;
    private Preference mNotifVibrationIntensity;
    private RadioButtonPreference[] mRadioPreferences = new RadioButtonPreference[5];
    private Preference mRingerVibrationIntensity;
    private SettingsObserver mSettingObserver;
    private final Map<String, RadioButtonPreference> mStringToPreferenceMap = new HashMap();
    /* access modifiers changed from: private */
    public Vibrator mVibrator;

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "VibrationSettingsPreferenceFragment";
    }

    public int getMetricsCategory() {
        return 1292;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.vibration_settings;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = getContext();
        this.mContentResolver = this.mContext.getContentResolver();
        this.mSettingObserver = new SettingsObserver(this.f56mH);
        this.mHasOnePlusHaptics = getResources().getBoolean(17891482);
        this.mVibrator = (Vibrator) this.mContext.getSystemService("vibrator");
        Vibrator vibrator = this.mVibrator;
        if (vibrator != null && !vibrator.hasVibrator()) {
            this.mVibrator = null;
        }
        this.mRingerVibrationIntensity = findPreference("ring_vibration_intensity");
        this.mNotifVibrationIntensity = findPreference("notification_vibration_intensity");
        this.mIncallFeedback = (SwitchPreference) findPreference("incall_feeedback_vibrate");
        for (int i = 0; i < 5; i++) {
            this.mRadioPreferences[i] = (RadioButtonPreference) findPreference(mKeys[i]);
            this.mStringToPreferenceMap.put(mKeys[i], this.mRadioPreferences[i]);
            this.mRadioPreferences[i].setOnPreferenceClickListener(this);
        }
        if (this.mHasOnePlusHaptics) {
            this.mRingerVibrationIntensity.setOnPreferenceClickListener(this);
            this.mNotifVibrationIntensity.setOnPreferenceClickListener(this);
            updateIntensityText();
        } else {
            this.mRingerVibrationIntensity.setEnabled(false);
            this.mRingerVibrationIntensity.setVisible(false);
            this.mNotifVibrationIntensity.setEnabled(false);
            this.mNotifVibrationIntensity.setVisible(false);
        }
        this.mIncallFeedback.setOnPreferenceClickListener(this);
        SwitchPreference switchPreference = this.mIncallFeedback;
        boolean z = true;
        if (Settings.System.getIntForUser(this.mContentResolver, "incall_feeedback_vibrate", 0, -2) != 1) {
            z = false;
        }
        switchPreference.setChecked(z);
        updateVibrationPattern(Settings.System.getIntForUser(this.mContentResolver, "ringtone_vibration_pattern", 0, -2));
    }

    public void onStop() {
        super.onStop();
        if (this.mHasOnePlusHaptics) {
            this.mContentResolver.unregisterContentObserver(this.mSettingObserver);
        }
    }

    public void onStart() {
        super.onStart();
        if (this.mHasOnePlusHaptics) {
            this.mContentResolver.registerContentObserver(Settings.System.getUriFor("ring_vibration_intensity"), true, this.mSettingObserver, -2);
            this.mContentResolver.registerContentObserver(Settings.System.getUriFor("notification_vibration_intensity"), true, this.mSettingObserver, -2);
        }
    }

    /* access modifiers changed from: protected */
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new VibrateOnTouchPreferenceController(context, this, getSettingsLifecycle()));
        return arrayList;
    }

    public boolean onPreferenceClick(Preference preference) {
        String key = preference.getKey();
        if (preference instanceof RadioButtonPreference) {
            int indexOf = Arrays.asList(mKeys).indexOf(key);
            updateVibrationPattern(indexOf);
            performVibrationDemo(indexOf);
        } else if (preference instanceof SwitchPreference) {
            Settings.System.putIntForUser(this.mContentResolver, "incall_feeedback_vibrate", ((SwitchPreference) preference).isChecked() ? 1 : 0, -2);
        } else {
            VibrationIntensityDialog vibrationIntensityDialog = new VibrationIntensityDialog();
            vibrationIntensityDialog.setParameters(this.mContext, key, preference);
            vibrationIntensityDialog.show(getFragmentManager(), "VibrationSettingsPreferenceFragment");
        }
        return true;
    }

    private void updateVibrationPattern(int i) {
        int i2 = 0;
        while (i2 < 5) {
            this.mStringToPreferenceMap.get(mKeys[i2]).setChecked(i == i2);
            i2++;
        }
        Settings.System.putIntForUser(this.mContentResolver, "ringtone_vibration_pattern", i, -2);
    }

    private void updateIntensityText() {
        setText(this.mRingerVibrationIntensity, Settings.System.getIntForUser(this.mContext.getContentResolver(), "ring_vibration_intensity", 2, -2));
        setText(this.mNotifVibrationIntensity, Settings.System.getIntForUser(this.mContext.getContentResolver(), "notification_vibration_intensity", 2, -2));
    }

    private void setText(Preference preference, int i) {
        if (i == 0) {
            preference.setSummary((int) C1715R.string.vibration_intensity_disabled);
        } else if (i == 1) {
            preference.setSummary((int) C1715R.string.vibration_intensity_light);
        } else if (i == 2) {
            preference.setSummary((int) C1715R.string.vibration_intensity_medium);
        } else if (i == 3) {
            preference.setSummary((int) C1715R.string.vibration_intensity_strong);
        } else if (i == 4) {
            preference.setSummary((int) C1715R.string.vibration_intensity_custom);
        }
    }

    /* access modifiers changed from: private */
    public void performVibrationDemo(int i) {
        VibrationEffect vibrationEffect;
        if (i == 1) {
            vibrationEffect = VibrationEffect.createWaveform(DZZZ_DA_VIBRATION_PATTERN, FIVE_ELEMENTS_VIBRATION_AMPLITUDE, -1);
        } else if (i == 2) {
            vibrationEffect = VibrationEffect.createWaveform(MM_MM_MM_VIBRATION_PATTERN, SEVEN_ELEMENTS_VIBRATION_AMPLITUDE, -1);
        } else if (i == 3) {
            vibrationEffect = VibrationEffect.createWaveform(DA_DA_DZZZ_VIBRATION_PATTERN, NINE_ELEMENTS_VIBRATION_AMPLITUDE, -1);
        } else if (i != 4) {
            vibrationEffect = VibrationEffect.createWaveform(DZZZ_DZZZ_VIBRATION_PATTERN, FIVE_ELEMENTS_VIBRATION_AMPLITUDE, -1);
        } else {
            vibrationEffect = VibrationEffect.createWaveform(DA_DZZZ_DA_VIBRATION_PATTERN, SEVEN_ELEMENTS_VIBRATION_AMPLITUDE, -1);
        }
        Vibrator vibrator = this.mVibrator;
        if (vibrator != null && vibrator.hasVibrator()) {
            this.mVibrator.vibrate(vibrationEffect, this.mAudioAttributesRingtone);
        }
    }

    private final class SettingsObserver extends ContentObserver {
        public SettingsObserver(Handler handler) {
            super(handler);
        }

        public void onChange(boolean z, Uri uri) {
            if (z) {
                return;
            }
            if (uri.equals(Settings.System.getUriFor("ring_vibration_intensity"))) {
                VibrationSettingsPreferenceFragment vibrationSettingsPreferenceFragment = VibrationSettingsPreferenceFragment.this;
                vibrationSettingsPreferenceFragment.performVibrationDemo(Settings.System.getIntForUser(vibrationSettingsPreferenceFragment.mContentResolver, "ringtone_vibration_pattern", 0, -2));
            } else if (uri.equals(Settings.System.getUriFor("notification_vibration_intensity")) && VibrationSettingsPreferenceFragment.this.mVibrator != null && VibrationSettingsPreferenceFragment.this.mVibrator.hasVibrator()) {
                VibrationSettingsPreferenceFragment.this.mVibrator.vibrate(250, VibrationSettingsPreferenceFragment.this.mAudioAttributesNotif);
            }
        }
    }
}
