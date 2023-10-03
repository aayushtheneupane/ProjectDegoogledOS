package com.android.dialer.app.calllog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.dialer.R;
import com.android.dialer.app.alert.AlertManager;

public class CallLogModalAlertManager implements AlertManager {
    private final ViewGroup container;
    private final LayoutInflater inflater;
    private final Listener listener;
    private final ViewGroup parent;

    interface Listener {
        void onShowModalAlert(boolean z);
    }

    public CallLogModalAlertManager(LayoutInflater layoutInflater, ViewGroup viewGroup, Listener listener2) {
        this.inflater = layoutInflater;
        this.parent = viewGroup;
        this.listener = listener2;
        this.container = (ViewGroup) viewGroup.findViewById(R.id.modal_message_container);
    }

    public void add(View view) {
        if (!(this.container.indexOfChild(view) != -1)) {
            this.container.addView(view);
            this.listener.onShowModalAlert(true);
        }
    }

    public void clear() {
        this.container.removeAllViews();
        this.listener.onShowModalAlert(false);
    }

    public View inflate(int i) {
        return this.inflater.inflate(i, this.parent, false);
    }

    public boolean isEmpty() {
        return this.container.getChildCount() == 0;
    }
}
