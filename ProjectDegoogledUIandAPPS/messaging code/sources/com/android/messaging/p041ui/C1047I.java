package com.android.messaging.p041ui;

import android.widget.AbsListView;
import com.android.messaging.util.C1419X;

/* renamed from: com.android.messaging.ui.I */
class C1047I implements AbsListView.OnScrollListener {
    final /* synthetic */ C1049J this$0;

    C1047I(C1049J j) {
        this.this$0 = j;
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (i != 0) {
            C1419X.get().mo8048a(this.this$0.mContext, absListView);
        }
    }
}
