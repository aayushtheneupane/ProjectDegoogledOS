package com.android.messaging.p041ui;

import android.database.DataSetObserver;

/* renamed from: com.android.messaging.ui.z */
class C1393z extends DataSetObserver {
    final /* synthetic */ C1033B this$0;

    /* synthetic */ C1393z(C1033B b, C1391y yVar) {
        this.this$0 = b;
    }

    public void onChanged() {
        this.this$0.notifyDataSetChanged();
    }

    public void onInvalidated() {
        this.this$0.notifyDataSetInvalidated();
    }
}
