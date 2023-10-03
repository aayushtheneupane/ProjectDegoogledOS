package com.android.messaging.p041ui;

import android.database.DataSetObserver;

/* renamed from: com.android.messaging.ui.G */
class C1043G extends DataSetObserver {
    final /* synthetic */ C1045H this$0;

    /* synthetic */ C1043G(C1045H h, C1039E e) {
        this.this$0 = h;
    }

    public void onChanged() {
        C1045H h = this.this$0;
        h.mDataValid = true;
        h.notifyDataSetChanged();
    }

    public void onInvalidated() {
        C1045H h = this.this$0;
        h.mDataValid = false;
        h.notifyDataSetChanged();
    }
}
