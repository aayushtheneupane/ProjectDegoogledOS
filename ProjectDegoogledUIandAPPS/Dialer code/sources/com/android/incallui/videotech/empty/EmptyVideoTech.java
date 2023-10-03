package com.android.incallui.videotech.empty;

import android.content.Context;
import android.telecom.PhoneAccountHandle;
import com.android.dialer.logging.VideoTech$Type;
import com.android.incallui.video.protocol.VideoCallScreen;
import com.android.incallui.video.protocol.VideoCallScreenDelegate;
import com.android.incallui.videotech.VideoTech;

public class EmptyVideoTech implements VideoTech {
    public void acceptVideoRequest(Context context) {
    }

    public void acceptVideoRequestAsAudio() {
    }

    public void becomePrimary() {
    }

    public VideoCallScreenDelegate createVideoCallScreenDelegate(Context context, VideoCallScreen videoCallScreen) {
        throw new UnsupportedOperationException();
    }

    public void declineVideoRequest() {
    }

    public int getSessionModificationState() {
        return 0;
    }

    public VideoTech$Type getVideoTechType() {
        return VideoTech$Type.NONE;
    }

    public boolean isAvailable(Context context, PhoneAccountHandle phoneAccountHandle) {
        return false;
    }

    public boolean isPaused() {
        return false;
    }

    public boolean isSelfManagedCamera() {
        return false;
    }

    public boolean isTransmitting() {
        return false;
    }

    public boolean isTransmittingOrReceiving() {
        return false;
    }

    public void onCallStateChanged(Context context, int i, PhoneAccountHandle phoneAccountHandle) {
    }

    public void onRemovedFromCallList() {
    }

    public void pause() {
    }

    public void resumeTransmission(Context context) {
    }

    public void setCamera(String str) {
    }

    public void setDeviceOrientation(int i) {
    }

    public boolean shouldUseSurfaceView() {
        return false;
    }

    public void stopTransmission() {
    }

    public void unpause() {
    }

    public void upgradeToVideo(Context context) {
    }
}
