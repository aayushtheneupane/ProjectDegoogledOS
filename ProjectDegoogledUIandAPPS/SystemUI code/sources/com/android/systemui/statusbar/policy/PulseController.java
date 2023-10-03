package com.android.systemui.statusbar.policy;

import com.android.systemui.statusbar.NotificationMediaManager;

public interface PulseController extends NotificationMediaManager.MediaListener {

    public interface PulseStateListener {
        void onPulseStateChanged(boolean z);
    }

    void notifyKeyguardGoingAway();

    void setDozing(boolean z);

    void setKeyguardShowing(boolean z);
}
