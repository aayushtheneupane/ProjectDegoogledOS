package com.android.incallui.incall.protocol;

public interface InCallScreenDelegate {
    void onEndCallClicked();

    void onInCallScreenDelegateInit(InCallScreen inCallScreen);

    void onInCallScreenPaused();

    void onInCallScreenReady();

    void onInCallScreenResumed();

    void onInCallScreenUnready();

    void onManageConferenceClicked();

    void onSecondaryInfoClicked();
}
