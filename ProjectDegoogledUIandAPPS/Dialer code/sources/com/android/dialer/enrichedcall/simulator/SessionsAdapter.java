package com.android.dialer.enrichedcall.simulator;

import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import java.util.List;

class SessionsAdapter extends RecyclerView.Adapter<SessionViewHolder> {
    private List<String> sessionStrings;

    SessionsAdapter() {
    }

    public int getItemCount() {
        return this.sessionStrings.size();
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ((SessionViewHolder) viewHolder).updateSession(this.sessionStrings.get(i));
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new SessionViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.session_view_holder, viewGroup, false));
    }

    /* access modifiers changed from: package-private */
    public void setSessionStrings(List<String> list) {
        Assert.isNotNull(list);
        this.sessionStrings = list;
    }
}
