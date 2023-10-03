package com.android.messaging.p041ui;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/* renamed from: com.android.messaging.ui.A */
public class C1031A {

    /* renamed from: CF */
    boolean f1558CF;

    /* renamed from: DF */
    boolean f1559DF;
    BaseAdapter mAdapter;

    public C1031A(boolean z, boolean z2, BaseAdapter baseAdapter) {
        this.f1558CF = z;
        this.f1559DF = z2;
        this.mAdapter = baseAdapter;
    }

    /* renamed from: a */
    public View mo6855a(View view, ViewGroup viewGroup) {
        throw null;
    }

    public int getCount() {
        int count = this.mAdapter.getCount();
        if (this.f1559DF) {
            return (count != 0 || this.f1558CF) ? count + 1 : count;
        }
        return count;
    }
}
