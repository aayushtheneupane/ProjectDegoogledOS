package com.android.dialer.voicemail.settings;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.android.dialer.R;

public class RecordVoicemailGreetingActivity extends Activity implements View.OnClickListener {
    private int currentState;
    private int duration;
    private RecordButton recordButton;
    private Button redoButton;
    private Button saveButton;

    private void setSaveRedoButtonsEnabled(boolean z) {
        if (z) {
            this.saveButton.setVisibility(0);
            this.redoButton.setVisibility(0);
            return;
        }
        this.saveButton.setVisibility(8);
        this.redoButton.setVisibility(8);
    }

    private void setState(int i) {
        this.currentState = i;
        if (i == 1) {
            this.recordButton.setState(i);
            this.recordButton.setTracks(0.0f, 0.0f);
            setSaveRedoButtonsEnabled(false);
        } else if (i == 2) {
            this.recordButton.setState(i);
            this.recordButton.setTracks(0.0f, 1.0f);
            setSaveRedoButtonsEnabled(false);
        } else if (i == 3 || i == 4) {
            this.recordButton.setState(i);
            this.recordButton.setTracks(0.0f, ((float) this.duration) / 45000.0f);
            setSaveRedoButtonsEnabled(true);
        }
    }

    public void onClick(View view) {
        if (view == this.recordButton) {
            int i = this.currentState;
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        setState(4);
                        return;
                    } else if (i != 4) {
                        return;
                    }
                }
                setState(3);
                return;
            }
            setState(2);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_record_voicemail_greeting);
        this.recordButton = (RecordButton) findViewById(R.id.record_button);
        this.saveButton = (Button) findViewById(R.id.save_button);
        this.redoButton = (Button) findViewById(R.id.redo_button);
        this.duration = 0;
        setState(1);
        this.recordButton.setOnClickListener(this);
    }
}
