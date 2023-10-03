package com.android.dialer.speeddial.draghelper;

import android.content.Context;
import android.support.p002v7.widget.GridLayoutManager;

public class SpeedDialLayoutManager extends GridLayoutManager {
    private boolean isScrollEnabled = true;

    public SpeedDialLayoutManager(Context context, int i) {
        super(context, i);
    }

    public boolean canScrollVertically() {
        return this.isScrollEnabled && super.canScrollVertically();
    }

    public void setScrollEnabled(boolean z) {
        this.isScrollEnabled = z;
    }
}
