package com.android.settings.notification;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.preference.Preference;
import com.android.settings.core.instrumentation.InstrumentedDialogFragment;
import com.havoc.config.center.C1715R;

public class VibrationIntensityDialog extends InstrumentedDialogFragment {
    private Context mContext;
    private int mDefaultNotificationVibration;
    private int mDefaultRingVibration;
    /* access modifiers changed from: private */
    public boolean mIsRinger;
    /* access modifiers changed from: private */
    public Preference mPreference;
    private String mPreferenceKey;
    /* access modifiers changed from: private */
    public int mProgress;

    public void setParameters(Context context, String str, Preference preference) {
        this.mContext = context;
        this.mPreferenceKey = str;
        this.mPreference = preference;
    }

    public int getMetricsCategory() {
        return this.mIsRinger ? 1620 : 1293;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        int i;
        this.mIsRinger = this.mPreferenceKey.equals("ring_vibration_intensity");
        final ContentResolver contentResolver = this.mContext.getContentResolver();
        View inflate = getActivity().getLayoutInflater().inflate(C1715R.layout.dialog_vibration_intensity, (ViewGroup) null);
        final TextView textView = (TextView) inflate.findViewById(C1715R.C1718id.vibration_intensity_text);
        SeekBar seekBar = (SeekBar) inflate.findViewById(C1715R.C1718id.vibration_intensity_seekbar);
        this.mDefaultRingVibration = this.mContext.getResources().getInteger(17694788);
        this.mDefaultNotificationVibration = this.mContext.getResources().getInteger(17694783);
        if (this.mIsRinger) {
            seekBar.setMin(1);
            seekBar.setMax(3);
            this.mProgress = Settings.System.getIntForUser(contentResolver, "ring_vibration_intensity", this.mDefaultRingVibration, -2);
            seekBar.setProgress(this.mProgress);
            setText(textView, this.mPreference, this.mProgress);
            i = C1715R.string.vibration_intensity_ringer;
        } else {
            seekBar.setMin(1);
            seekBar.setMax(3);
            this.mProgress = Settings.System.getIntForUser(contentResolver, "notification_vibration_intensity", this.mDefaultNotificationVibration, -2);
            seekBar.setProgress(this.mProgress);
            setText(textView, this.mPreference, this.mProgress);
            i = C1715R.string.vibration_intensity_notification;
        }
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                int unused = VibrationIntensityDialog.this.mProgress = i;
                VibrationIntensityDialog vibrationIntensityDialog = VibrationIntensityDialog.this;
                vibrationIntensityDialog.setText(textView, vibrationIntensityDialog.mPreference, i);
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                Settings.System.putIntForUser(contentResolver, VibrationIntensityDialog.this.mIsRinger ? "ring_vibration_intensity" : "notification_vibration_intensity", VibrationIntensityDialog.this.mProgress, -2);
            }
        });
        return new AlertDialog.Builder(getContext()).setTitle(i).setView(inflate).setPositiveButton(C1715R.string.okay, (DialogInterface.OnClickListener) null).create();
    }

    /* access modifiers changed from: private */
    public void setText(TextView textView, Preference preference, int i) {
        if (i == 0) {
            textView.setText(C1715R.string.vibration_intensity_disabled);
            preference.setSummary((int) C1715R.string.vibration_intensity_disabled);
        } else if (i == 1) {
            textView.setText(C1715R.string.vibration_intensity_light);
            preference.setSummary((int) C1715R.string.vibration_intensity_light);
        } else if (i == 2) {
            textView.setText(C1715R.string.vibration_intensity_medium);
            preference.setSummary((int) C1715R.string.vibration_intensity_medium);
        } else if (i == 3) {
            textView.setText(C1715R.string.vibration_intensity_strong);
            preference.setSummary((int) C1715R.string.vibration_intensity_strong);
        } else if (i == 4) {
            textView.setText(C1715R.string.vibration_intensity_custom);
            preference.setSummary((int) C1715R.string.vibration_intensity_custom);
        }
    }
}
