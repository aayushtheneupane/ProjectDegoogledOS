package com.android.dialer.util;

import android.view.View;
import android.view.ViewTreeObserver;

class ViewUtil$1 implements ViewTreeObserver.OnPreDrawListener {
    final /* synthetic */ boolean val$drawNextFrame;
    final /* synthetic */ Runnable val$runnable;
    final /* synthetic */ View val$view;

    ViewUtil$1(View view, Runnable runnable, boolean z) {
        this.val$view = view;
        this.val$runnable = runnable;
        this.val$drawNextFrame = z;
    }

    public boolean onPreDraw() {
        this.val$view.getViewTreeObserver().removeOnPreDrawListener(this);
        this.val$runnable.run();
        return this.val$drawNextFrame;
    }
}
