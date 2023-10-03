package com.android.messaging.p041ui;

import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;

/* renamed from: com.android.messaging.ui.F */
class C1041F extends ContentObserver {
    final /* synthetic */ C1045H this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1041F(C1045H h) {
        super(new Handler());
        this.this$0 = h;
    }

    public boolean deliverSelfNotifications() {
        return true;
    }

    public void onChange(boolean z) {
        Cursor cursor;
        C1045H h = this.this$0;
        if (h.mAutoRequery && (cursor = h.mCursor) != null && !cursor.isClosed()) {
            h.mDataValid = h.mCursor.requery();
        }
    }
}
