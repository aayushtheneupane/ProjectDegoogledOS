package com.android.dialer.app.calllog;

import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.dialer.R;
import com.android.dialer.app.alert.AlertManager;
import com.android.dialer.common.Assert;

public class CallLogAlertManager implements AlertManager {
    private final CallLogAdapter adapter;
    private final ViewGroup container = ((ViewGroup) this.view.findViewById(R.id.container));
    private final LayoutInflater inflater;
    private final ViewGroup parent;
    private final View view;

    private static class AlertViewHolder extends RecyclerView.ViewHolder {
        /* synthetic */ AlertViewHolder(View view, C03021 r2) {
            super(view);
        }
    }

    public CallLogAlertManager(CallLogAdapter callLogAdapter, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        this.adapter = callLogAdapter;
        this.inflater = layoutInflater;
        this.parent = viewGroup;
        this.view = layoutInflater.inflate(R.layout.call_log_alert_item, viewGroup, false);
    }

    public void add(View view2) {
        if (!(this.container.indexOfChild(view2) != -1)) {
            this.container.addView(view2);
            if (this.container.getChildCount() == 1) {
                this.adapter.notifyItemInserted(0);
            }
        }
    }

    public void clear() {
        this.container.removeAllViews();
        this.adapter.notifyItemRemoved(0);
    }

    public RecyclerView.ViewHolder createViewHolder(ViewGroup viewGroup) {
        Assert.checkArgument(viewGroup == this.parent, "createViewHolder should be called with the same parent in constructor", new Object[0]);
        return new AlertViewHolder(this.view, (C03021) null);
    }

    public View inflate(int i) {
        return this.inflater.inflate(i, this.container, false);
    }

    public boolean isEmpty() {
        return this.container.getChildCount() == 0;
    }
}
