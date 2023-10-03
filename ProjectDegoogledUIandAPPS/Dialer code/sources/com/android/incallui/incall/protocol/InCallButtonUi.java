package com.android.incallui.incall.protocol;

import android.support.p000v4.app.Fragment;
import android.telecom.CallAudioState;

public interface InCallButtonUi {
    void enableButton(int i, boolean z);

    Fragment getInCallButtonUiFragment();

    void requestCallRecordingPermissions(String[] strArr);

    void setAudioState(CallAudioState callAudioState);

    void setCallRecordingDuration(long j);

    void setCallRecordingState(boolean z);

    void setCameraSwitched(boolean z);

    void setEnabled(boolean z);

    void setHold(boolean z);

    void setVideoPaused(boolean z);

    void showAudioRouteSelector();

    void showButton(int i, boolean z);

    void updateButtonStates();

    void updateInCallButtonUiColors(int i);
}
