package com.android.dialer.searchfragment.list;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.android.dialer.R;

final class HeaderViewHolder extends RecyclerView.ViewHolder {
    private final TextView header;

    HeaderViewHolder(View view) {
        super(view);
        this.header = (TextView) view.findViewById(R.id.header);
    }

    public void setHeader(String str) {
        this.header.setText(str);
    }
}
