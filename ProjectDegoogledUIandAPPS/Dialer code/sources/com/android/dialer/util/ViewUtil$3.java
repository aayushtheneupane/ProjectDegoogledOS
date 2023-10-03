package com.android.dialer.util;

import android.view.View;
import android.view.ViewTreeObserver;

class ViewUtil$3 implements ViewTreeObserver.OnGlobalLayoutListener {
    final /* synthetic */ ViewUtil$ViewRunnable val$runnable;
    final /* synthetic */ View val$view;

    ViewUtil$3(View view, ViewUtil$ViewRunnable viewUtil$ViewRunnable) {
        this.val$view = view;
        this.val$runnable = viewUtil$ViewRunnable;
    }

    public void onGlobalLayout() {
        this.val$view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        this.val$runnable.run(this.val$view);
    }
}
