package com.android.settingslib.widget;

import android.view.View;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class ActionBarShadowController implements LifecycleObserver {
    static final float ELEVATION_HIGH = 8.0f;
    static final float ELEVATION_LOW = 0.0f;
    private boolean mIsScrollWatcherAttached;
    ScrollChangeWatcher mScrollChangeWatcher;
    private View mScrollView;

    final class ScrollChangeWatcher implements View.OnScrollChangeListener {
        public void updateDropShadow(View view) {
            throw null;
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private void attachScrollWatcher() {
        if (!this.mIsScrollWatcherAttached) {
            this.mIsScrollWatcherAttached = true;
            this.mScrollView.setOnScrollChangeListener(this.mScrollChangeWatcher);
            this.mScrollChangeWatcher.updateDropShadow(this.mScrollView);
            throw null;
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private void detachScrollWatcher() {
        this.mScrollView.setOnScrollChangeListener((View.OnScrollChangeListener) null);
        this.mIsScrollWatcherAttached = false;
    }
}
