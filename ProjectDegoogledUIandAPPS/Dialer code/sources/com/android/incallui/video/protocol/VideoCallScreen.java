package com.android.incallui.video.protocol;

import android.support.p000v4.app.Fragment;

public interface VideoCallScreen {
    String getCallId();

    Fragment getVideoCallScreenFragment();

    void onHandoverFromWiFiToLte();

    void onLocalVideoDimensionsChanged();

    void onLocalVideoOrientationChanged();

    void onRemoteVideoDimensionsChanged();

    void onVideoScreenStart();

    void onVideoScreenStop();

    void showVideoViews(boolean z, boolean z2, boolean z3);

    void updateFullscreenAndGreenScreenMode(boolean z, boolean z2);
}
