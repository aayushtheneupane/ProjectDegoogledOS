package com.android.dialer.voicemail.listui;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.android.dialer.R;

final class NewVoicemailHeaderViewHolder extends RecyclerView.ViewHolder {
    private final TextView headerTextView;

    NewVoicemailHeaderViewHolder(View view) {
        super(view);
        this.headerTextView = (TextView) view.findViewById(R.id.new_voicemail_header_text);
    }

    /* access modifiers changed from: package-private */
    public String getHeaderText() {
        return this.headerTextView.getText().toString();
    }

    /* access modifiers changed from: package-private */
    public void setHeader(int i) {
        this.headerTextView.setText(i);
    }
}
