package com.android.incallui.incall.protocol;

import android.support.p000v4.app.Fragment;
import android.view.accessibility.AccessibilityEvent;

public interface InCallScreen {
    void dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent);

    int getAnswerAndDialpadContainerResourceId();

    Fragment getInCallScreenFragment();

    boolean isManageConferenceVisible();

    void onInCallScreenDialpadVisibilityChange(boolean z);

    void setCallState(PrimaryCallState primaryCallState);

    void setEndCallButtonEnabled(boolean z, boolean z2);

    void setPrimary(PrimaryInfo primaryInfo);

    void setSecondary(SecondaryInfo secondaryInfo);

    void showLocationUi(Fragment fragment);

    void showManageConferenceCallButton(boolean z);

    void showNoteSentToast();

    void updateInCallScreenColors();
}
