package com.android.systemui.screenrecord;

import android.app.Activity;
import android.content.Intent;
import android.media.projection.MediaProjectionManager;
import android.os.Bundle;
import android.os.SystemProperties;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import com.android.systemui.C1771R$array;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1779R$layout;
import com.android.systemui.C1784R$string;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.android.systemui.statusbar.phone.StatusBar;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ScreenRecordDialog extends Activity {
    /* access modifiers changed from: private */
    public int mAudioSourceOpt;
    /* access modifiers changed from: private */
    public boolean mShowDot;
    /* access modifiers changed from: private */
    public boolean mShowTaps;
    /* access modifiers changed from: private */
    public int mVideoBitrateOpt;

    public void onCreate(Bundle bundle) {
        int i;
        super.onCreate(bundle);
        setContentView(C1779R$layout.screen_record_dialog);
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(window.getAttributes());
        layoutParams.width = -1;
        layoutParams.height = -2;
        layoutParams.gravity = 80;
        window.setAttributes(layoutParams);
        final Spinner spinner = (Spinner) findViewById(C1777R$id.spinner_video_bitrate);
        final Spinner spinner2 = (Spinner) findViewById(C1777R$id.spinner_audio_source);
        final Switch switchR = (Switch) findViewById(C1777R$id.switch_taps);
        final Switch switchR2 = (Switch) findViewById(C1777R$id.switch_stopdot);
        if (SystemProperties.get("ro.config.low_ram").equals("true")) {
            i = C1771R$array.screen_video_quality_go_entries;
        } else {
            i = C1771R$array.screen_video_quality_entries;
        }
        ArrayAdapter<CharSequence> createFromResource = ArrayAdapter.createFromResource(this, i, 17367048);
        createFromResource.setDropDownViewResource(17367049);
        spinner.setAdapter(createFromResource);
        ArrayAdapter<CharSequence> createFromResource2 = ArrayAdapter.createFromResource(this, C1771R$array.screen_audio_recording_entries, 17367048);
        createFromResource2.setDropDownViewResource(17367049);
        spinner2.setAdapter(createFromResource2);
        initialCheckSpinner(spinner, "screenrecord_video_bitrate", 2);
        initialCheckSpinner(spinner2, "screenrecord_audio_source", 0);
        initialCheckSwitch(switchR, "screenrecord_show_taps");
        initialCheckSwitch(switchR2, "screenrecord_stop_dot");
        setSwitchListener(switchR, "screenrecord_show_taps");
        setSwitchListener(switchR2, "screenrecord_stop_dot");
        setSpinnerListener(spinner, "screenrecord_video_bitrate");
        setSpinnerListener(spinner2, "screenrecord_audio_source");
        ((Button) findViewById(C1777R$id.record_button)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean unused = ScreenRecordDialog.this.mShowTaps = switchR.isChecked();
                boolean unused2 = ScreenRecordDialog.this.mShowDot = switchR2.isChecked();
                int unused3 = ScreenRecordDialog.this.mVideoBitrateOpt = spinner.getSelectedItemPosition();
                int unused4 = ScreenRecordDialog.this.mAudioSourceOpt = spinner2.getSelectedItemPosition();
                Log.d("ScreenRecord", "Record button clicked: bitrate " + ScreenRecordDialog.this.mVideoBitrateOpt + " audio " + ScreenRecordDialog.this.mAudioSourceOpt + ", taps " + ScreenRecordDialog.this.mShowTaps + ", dot " + ScreenRecordDialog.this.mShowDot);
                if (ScreenRecordDialog.this.mAudioSourceOpt <= 0 || ScreenRecordDialog.this.checkSelfPermission("android.permission.RECORD_AUDIO") == 0) {
                    ScreenRecordDialog.this.requestScreenCapture();
                    return;
                }
                Log.d("ScreenRecord", "Requesting permission for audio");
                ScreenRecordDialog.this.requestPermissions(new String[]{"android.permission.RECORD_AUDIO"}, 202);
            }
        });
        ((Button) findViewById(C1777R$id.cancel_button)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ScreenRecordDialog.this.finish();
            }
        });
        try {
            ActivityManagerWrapper.getInstance().closeSystemWindows(StatusBar.SYSTEM_DIALOG_REASON_SCREENSHOT).get(3000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException unused) {
        }
    }

    private void initialCheckSwitch(Switch switchR, String str) {
        int intForUser = Settings.System.getIntForUser(getContentResolver(), str, 0, -2);
        boolean z = true;
        if (intForUser != 1) {
            z = false;
        }
        switchR.setChecked(z);
    }

    private void initialCheckSpinner(Spinner spinner, String str, int i) {
        spinner.setSelection(Settings.System.getIntForUser(getContentResolver(), str, i, -2));
    }

    private void setSwitchListener(Switch switchR, String str) {
        switchR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(str) {
            private final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ScreenRecordDialog.this.lambda$setSwitchListener$0$ScreenRecordDialog(this.f$1, compoundButton, z);
            }
        });
    }

    public /* synthetic */ void lambda$setSwitchListener$0$ScreenRecordDialog(String str, CompoundButton compoundButton, boolean z) {
        Settings.System.putIntForUser(getContentResolver(), str, z ? 1 : 0, -2);
    }

    private void setSpinnerListener(Spinner spinner, final String str) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                Settings.System.putIntForUser(ScreenRecordDialog.this.getContentResolver(), str, i, -2);
            }
        });
    }

    /* access modifiers changed from: private */
    public void requestScreenCapture() {
        int i;
        int i2;
        Intent createScreenCaptureIntent = ((MediaProjectionManager) getSystemService("media_projection")).createScreenCaptureIntent();
        if (this.mAudioSourceOpt > 0) {
            if (this.mShowTaps) {
                i2 = this.mShowDot ? 404 : 402;
            } else {
                i2 = this.mShowDot ? 403 : 401;
            }
            startActivityForResult(createScreenCaptureIntent, i2);
            return;
        }
        if (this.mShowTaps) {
            i = this.mShowDot ? 303 : 302;
        } else {
            i = this.mShowDot ? 304 : 301;
        }
        startActivityForResult(createScreenCaptureIntent, i);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        boolean z = true;
        this.mShowTaps = i == 302 || i == 402 || i == 303 || i == 404;
        if (!(i == 403 || i == 304 || i == 303 || i == 404)) {
            z = false;
        }
        this.mShowDot = z;
        if (i != 201) {
            if (i != 202) {
                if (i2 == -1) {
                    startForegroundService(RecordingService.getStartIntent(this, i2, intent, this.mAudioSourceOpt, this.mShowTaps, this.mShowDot, this.mVideoBitrateOpt));
                } else {
                    Toast.makeText(this, getResources().getString(C1784R$string.screenrecord_permission_error), 0).show();
                }
                finish();
                return;
            }
            int checkSelfPermission = checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
            int checkSelfPermission2 = checkSelfPermission("android.permission.RECORD_AUDIO");
            if (checkSelfPermission == 0 && checkSelfPermission2 == 0) {
                requestScreenCapture();
                return;
            }
            Toast.makeText(this, getResources().getString(C1784R$string.screenrecord_permission_error), 0).show();
            finish();
        } else if (checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            Toast.makeText(this, getResources().getString(C1784R$string.screenrecord_permission_error), 0).show();
            finish();
        } else {
            requestScreenCapture();
        }
    }
}
