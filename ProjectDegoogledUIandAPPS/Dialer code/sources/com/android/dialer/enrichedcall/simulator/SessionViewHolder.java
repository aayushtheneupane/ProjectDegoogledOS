package com.android.dialer.enrichedcall.simulator;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.android.dialer.R;

class SessionViewHolder extends RecyclerView.ViewHolder {
    private final TextView sessionStringView;

    SessionViewHolder(View view) {
        super(view);
        this.sessionStringView = (TextView) view.findViewById(R.id.session_string);
    }

    /* access modifiers changed from: package-private */
    public void updateSession(String str) {
        this.sessionStringView.setText(str);
    }
}
