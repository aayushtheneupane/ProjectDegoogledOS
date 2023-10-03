package com.google.android.apps.photosgo.recyclerviewpager;

import android.content.Context;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/* compiled from: PG */
public final class ZoomingRecyclerView extends RecyclerView {
    public ZoomingRecyclerView(Context context) {
        super(context);
    }

    public ZoomingRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ZoomingRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() != 2 || motionEvent.getPointerCount() <= 1) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }

    public final boolean performAccessibilityAction(int i, Bundle bundle) {
        if (i == 8192 || i == 4096) {
            return false;
        }
        return super.performAccessibilityAction(i, bundle);
    }
}
