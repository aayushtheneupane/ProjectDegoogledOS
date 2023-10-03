package com.android.dialer.voicemail.listui;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.voicemail.listui.error.VoicemailErrorMessage;

final class NewVoicemailAlertViewHolder extends RecyclerView.ViewHolder {
    private final Button primaryButton;
    private final Button secondaryButton;
    private final TextView voicemailErrorDetailsTextView;
    private final TextView voicemailErrorTitleTextView;

    NewVoicemailAlertViewHolder(View view) {
        super(view);
        this.voicemailErrorTitleTextView = (TextView) view.findViewById(R.id.voicemail_alert_header);
        this.voicemailErrorDetailsTextView = (TextView) view.findViewById(R.id.voicemail_alert_details);
        this.primaryButton = (Button) view.findViewById(R.id.voicemail_alert_primary_button);
        this.secondaryButton = (Button) view.findViewById(R.id.voicemail_alert_primary_button);
    }

    /* access modifiers changed from: package-private */
    public void setDescription(CharSequence charSequence) {
        this.voicemailErrorDetailsTextView.setText(charSequence);
    }

    /* access modifiers changed from: package-private */
    public void setPrimaryButton(VoicemailErrorMessage.Action action) {
        this.primaryButton.setVisibility(0);
        this.primaryButton.setText(action.getText());
        this.primaryButton.setOnClickListener(action.getListener());
    }

    /* access modifiers changed from: package-private */
    public void setSecondaryButton(VoicemailErrorMessage.Action action) {
        this.secondaryButton.setVisibility(0);
        this.secondaryButton.setText(action.getText());
        this.secondaryButton.setOnClickListener(action.getListener());
    }

    /* access modifiers changed from: package-private */
    public void setTitle(CharSequence charSequence) {
        this.voicemailErrorTitleTextView.setText(charSequence);
    }
}
