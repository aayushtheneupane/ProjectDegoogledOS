package com.android.dialer.calllog.p004ui;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.android.dialer.R;

/* renamed from: com.android.dialer.calllog.ui.HeaderViewHolder */
final class HeaderViewHolder extends RecyclerView.ViewHolder {
    private TextView headerTextView;

    HeaderViewHolder(View view) {
        super(view);
        this.headerTextView = (TextView) view.findViewById(R.id.new_call_log_header_text);
    }

    /* access modifiers changed from: package-private */
    public void setHeader(int i) {
        this.headerTextView.setText(i);
    }
}
