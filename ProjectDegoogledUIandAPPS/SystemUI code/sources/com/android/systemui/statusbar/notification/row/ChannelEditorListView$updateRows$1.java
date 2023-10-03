package com.android.systemui.statusbar.notification.row;

import android.transition.Transition;

/* compiled from: ChannelEditorListView.kt */
public final class ChannelEditorListView$updateRows$1 implements Transition.TransitionListener {
    final /* synthetic */ ChannelEditorListView this$0;

    public void onTransitionCancel(Transition transition) {
    }

    public void onTransitionPause(Transition transition) {
    }

    public void onTransitionResume(Transition transition) {
    }

    public void onTransitionStart(Transition transition) {
    }

    ChannelEditorListView$updateRows$1(ChannelEditorListView channelEditorListView) {
        this.this$0 = channelEditorListView;
    }

    public void onTransitionEnd(Transition transition) {
        this.this$0.notifySubtreeAccessibilityStateChangedIfNeeded();
    }
}
