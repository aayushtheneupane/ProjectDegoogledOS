package com.android.incallui.videotech;

import android.content.Context;
import android.telecom.PhoneAccountHandle;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.VideoTech$Type;
import com.android.incallui.video.protocol.VideoCallScreen;
import com.android.incallui.video.protocol.VideoCallScreenDelegate;

public interface VideoTech {

    public interface VideoTechListener {
        void onCameraDimensionsChanged(int i, int i2);

        void onImpressionLoggingNeeded(DialerImpression$Type dialerImpression$Type);

        void onPeerDimensionsChanged(int i, int i2);

        void onSessionModificationStateChanged();

        void onUpgradedToVideo(boolean z);

        void onVideoUpgradeRequestReceived();
    }

    void acceptVideoRequest(Context context);

    void acceptVideoRequestAsAudio();

    void becomePrimary();

    VideoCallScreenDelegate createVideoCallScreenDelegate(Context context, VideoCallScreen videoCallScreen);

    void declineVideoRequest();

    int getSessionModificationState();

    VideoTech$Type getVideoTechType();

    boolean isAvailable(Context context, PhoneAccountHandle phoneAccountHandle);

    boolean isPaused();

    boolean isSelfManagedCamera();

    boolean isTransmitting();

    boolean isTransmittingOrReceiving();

    void onCallStateChanged(Context context, int i, PhoneAccountHandle phoneAccountHandle);

    void onRemovedFromCallList();

    void pause();

    void resumeTransmission(Context context);

    void setCamera(String str);

    void setDeviceOrientation(int i);

    boolean shouldUseSurfaceView();

    void stopTransmission();

    void unpause();

    void upgradeToVideo(Context context);
}
