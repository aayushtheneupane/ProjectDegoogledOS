package com.android.dialer.voicemail.settings;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.p000v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.common.LogUtil;
import com.android.dialer.widget.DialerToolbar;
import java.io.IOException;
import java.util.Locale;

public class CurrentVoicemailGreetingActivity extends Activity {
    private ImageButton changeGreetingButton;
    private DialerToolbar currentVoicemailGreetingDialerToolbar;
    private int greetingDuration = -1;
    private MediaPlayer mediaPlayer;
    private boolean permissionToRecordAccepted = false;
    private ImageButton playButton;
    private View playbackDisplay;
    private TextView playbackProgressLabel;
    private String voicemailGreetingFilePath = "";

    private boolean isGreetingRecorded() {
        String stringExtra;
        Intent intent = getIntent();
        if (!(!intent.hasExtra("canonVoicemailGreetingFilePathKey") || (stringExtra = intent.getStringExtra("canonVoicemailGreetingFilePathKey")) == null || stringExtra.length() == 0)) {
            if (this.mediaPlayer == null) {
                this.mediaPlayer = new MediaPlayer();
            }
            try {
                this.mediaPlayer.setDataSource(stringExtra);
                this.greetingDuration = this.mediaPlayer.getDuration();
                this.voicemailGreetingFilePath = stringExtra;
                this.mediaPlayer = null;
                return true;
            } catch (IOException unused) {
                LogUtil.m8e("CurrentVoicemailGreetingActivity.isGreetingRecorded", "bad filepath.", new Object[0]);
                this.mediaPlayer = null;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_current_voicemail_greeting);
        this.playbackDisplay = findViewById(R.id.current_voicemail_greeting_recording_display);
        this.playbackProgressLabel = (TextView) findViewById(R.id.playback_progress_text_view);
        this.currentVoicemailGreetingDialerToolbar = (DialerToolbar) findViewById(R.id.toolbar);
        this.currentVoicemailGreetingDialerToolbar.setTitle((int) R.string.voicemail_change_greeting_preference_title);
        this.changeGreetingButton = (ImageButton) findViewById(R.id.change_greeting_button);
        this.changeGreetingButton.setOnClickListener(new View.OnClickListener(this) {
            public void onClick(View view) {
            }
        });
        this.playButton = (ImageButton) findViewById(R.id.play_button);
        this.playButton.setOnClickListener(new View.OnClickListener(this) {
            public void onClick(View view) {
            }
        });
        if (isGreetingRecorded()) {
            this.playbackProgressLabel.setText(String.format(Locale.US, "00:%d", new Object[]{Integer.valueOf(this.greetingDuration)}));
            return;
        }
        this.playbackDisplay.setVisibility(8);
    }

    public void onPause() {
        if (isGreetingRecorded() && this.mediaPlayer.isPlaying()) {
            this.mediaPlayer.release();
            this.mediaPlayer = null;
        }
        super.onPause();
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 200) {
            this.permissionToRecordAccepted = iArr[0] == 0;
        }
        if (!this.permissionToRecordAccepted) {
            LogUtil.m10w("CurrentVoicemailGreetingActivity.onRequestPermissionsResult", "permissionToRecordAccepted = false.", new Object[0]);
        }
    }

    public void onStart() {
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.RECORD_AUDIO"}, 200);
        if (isGreetingRecorded()) {
            this.mediaPlayer = new MediaPlayer();
            try {
                this.mediaPlayer.setDataSource(this.voicemailGreetingFilePath);
                this.mediaPlayer.prepare();
            } catch (IOException unused) {
                LogUtil.m8e("CurrentVoicemailGreetingActivity.onStart", "mediaPlayer setup failed.", new Object[0]);
            }
        }
        super.onStart();
    }
}
