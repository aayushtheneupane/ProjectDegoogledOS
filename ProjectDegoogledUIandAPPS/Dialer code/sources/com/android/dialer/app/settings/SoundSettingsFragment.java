package com.android.dialer.app.settings;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.provider.Settings;
import android.telephony.CarrierConfigManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;
import com.android.dialer.R;
import com.android.dialer.callrecord.impl.CallRecorderService;
import com.android.dialer.util.SettingsUtil;

public class SoundSettingsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {
    private ListPreference dtmfToneLength;
    private SwitchPreference enableDndInCall;
    private NotificationManager notificationManager;
    private SwitchPreference playDtmfTone;
    private final Handler ringtoneLookupComplete = new Handler() {
        public void handleMessage(Message message) {
            if (message.what == 1) {
                SoundSettingsFragment.this.ringtonePreference.setSummary((CharSequence) message.obj);
            }
        }
    };
    private final Runnable ringtoneLookupRunnable = new Runnable() {
        public void run() {
            SettingsUtil.updateRingtoneName(SoundSettingsFragment.this.getActivity(), SoundSettingsFragment.this.ringtoneLookupComplete, 1, SoundSettingsFragment.this.ringtonePreference.getKey(), 1);
        }
    };
    /* access modifiers changed from: private */
    public Preference ringtonePreference;
    private SwitchPreference vibrateWhenRinging;

    private boolean hasVibrator() {
        Vibrator vibrator = (Vibrator) getActivity().getSystemService("vibrator");
        return vibrator != null && vibrator.hasVibrator();
    }

    public Context getContext() {
        return getActivity();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.sound_settings);
        Activity activity = getActivity();
        this.ringtonePreference = findPreference(activity.getString(R.string.ringtone_preference_key));
        this.vibrateWhenRinging = (SwitchPreference) findPreference(activity.getString(R.string.vibrate_on_preference_key));
        this.playDtmfTone = (SwitchPreference) findPreference(activity.getString(R.string.play_dtmf_preference_key));
        this.dtmfToneLength = (ListPreference) findPreference(activity.getString(R.string.dtmf_tone_length_preference_key));
        this.enableDndInCall = (SwitchPreference) findPreference("incall_enable_dnd");
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        if (hasVibrator()) {
            this.vibrateWhenRinging.setOnPreferenceChangeListener(this);
        } else {
            preferenceScreen.removePreference(findPreference(activity.getString(R.string.incall_vibrate_45_key)));
            preferenceScreen.removePreference(this.vibrateWhenRinging);
            this.vibrateWhenRinging = null;
        }
        Preference findPreference = findPreference(activity.getString(R.string.incall_vibrate_outgoing_key));
        Preference findPreference2 = findPreference(activity.getString(R.string.incall_vibrate_call_waiting_key));
        Preference findPreference3 = findPreference(activity.getString(R.string.incall_vibrate_hangup_key));
        preferenceScreen.removePreference(findPreference);
        preferenceScreen.removePreference(findPreference2);
        preferenceScreen.removePreference(findPreference3);
        this.playDtmfTone.setOnPreferenceChangeListener(this);
        SwitchPreference switchPreference = this.playDtmfTone;
        boolean z = true;
        if (Settings.System.getInt(getActivity().getContentResolver(), "dtmf_tone", 1) != 1) {
            z = false;
        }
        switchPreference.setChecked(z);
        this.enableDndInCall.setOnPreferenceChangeListener(this);
        TelephonyManager telephonyManager = (TelephonyManager) getActivity().getSystemService("phone");
        if (!telephonyManager.canChangeDtmfToneLength() || (!telephonyManager.isWorldPhone() && ((CarrierConfigManager) getActivity().getSystemService("carrier_config")).getConfig().getBoolean("hide_carrier_network_settings_bool"))) {
            getPreferenceScreen().removePreference(this.dtmfToneLength);
            this.dtmfToneLength = null;
        } else {
            this.dtmfToneLength.setOnPreferenceChangeListener(this);
            this.dtmfToneLength.setValueIndex(Settings.System.getInt(activity.getContentResolver(), "dtmf_tone_type", 0));
        }
        if (!CallRecorderService.isEnabled(getActivity())) {
            getPreferenceScreen().removePreference(findPreference(activity.getString(R.string.call_recording_category_key)));
        }
        this.notificationManager = (NotificationManager) activity.getSystemService(NotificationManager.class);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (!Settings.System.canWrite(getActivity())) {
            Toast.makeText(getActivity(), getResources().getString(R.string.toast_cannot_write_system_settings), 0).show();
            return true;
        }
        if (preference == this.vibrateWhenRinging) {
            Settings.System.putInt(getActivity().getContentResolver(), "vibrate_when_ringing", ((Boolean) obj).booleanValue() ? 1 : 0);
        } else {
            ListPreference listPreference = this.dtmfToneLength;
            if (preference == listPreference) {
                Settings.System.putInt(getActivity().getContentResolver(), "dtmf_tone_type", listPreference.findIndexOfValue((String) obj));
            } else if (preference == this.enableDndInCall && ((Boolean) obj).booleanValue() && !this.notificationManager.isNotificationPolicyAccessGranted()) {
                new AlertDialog.Builder(getActivity()).setMessage(R.string.incall_dnd_dialog_message).setPositiveButton(R.string.allow, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        SoundSettingsFragment.this.startActivity(new Intent("android.settings.NOTIFICATION_POLICY_ACCESS_SETTINGS"));
                    }
                }).setNegativeButton(R.string.deny, new DialogInterface.OnClickListener(this) {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
                return false;
            }
        }
        return true;
    }

    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if (!Settings.System.canWrite(getActivity())) {
            Toast.makeText(getActivity(), getResources().getString(R.string.toast_cannot_write_system_settings), 0).show();
            return true;
        }
        if (preference == this.playDtmfTone) {
            Settings.System.putInt(getActivity().getContentResolver(), "dtmf_tone", this.playDtmfTone.isChecked() ? 1 : 0);
        }
        return true;
    }

    public void onResume() {
        super.onResume();
        if (!Settings.System.canWrite(getActivity())) {
            getActivity().onBackPressed();
            return;
        }
        SwitchPreference switchPreference = this.vibrateWhenRinging;
        if (switchPreference != null) {
            boolean z = false;
            int i = Settings.System.getInt(getActivity().getContentResolver(), "vibrate_when_ringing", 0);
            if (hasVibrator() && i == 1) {
                z = true;
            }
            switchPreference.setChecked(z);
        }
        new Thread(this.ringtoneLookupRunnable).start();
    }
}
