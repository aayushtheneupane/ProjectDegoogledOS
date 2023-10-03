package com.android.incallui.incall.protocol;

import android.content.Context;
import android.os.Bundle;
import android.telecom.CallAudioState;

public interface InCallButtonUiDelegate {
    void addCallClicked();

    void callRecordClicked(boolean z);

    void changeToRttClicked();

    void changeToVideoClicked();

    Context getContext();

    CallAudioState getCurrentAudioState();

    void holdClicked(boolean z);

    void mergeClicked();

    void muteClicked(boolean z, boolean z2);

    void onEndCallClicked();

    void onInCallButtonUiReady(InCallButtonUi inCallButtonUi);

    void onInCallButtonUiUnready();

    void onRestoreInstanceState(Bundle bundle);

    void onSaveInstanceState(Bundle bundle);

    void pauseVideoClicked(boolean z);

    void setAudioRoute(int i);

    void showAudioRouteSelector();

    void showDialpadClicked(boolean z);

    void swapClicked();

    void swapSimClicked();

    void toggleCameraClicked();

    void toggleSpeakerphone();
}
