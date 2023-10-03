package com.android.incallui.videotech.ims;

import android.content.Context;
import android.telecom.Call;
import android.telecom.PhoneAccountHandle;
import android.telecom.VideoProfile;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.LoggingBindings;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.logging.VideoTech$Type;
import com.android.dialer.util.CallUtil;
import com.android.incallui.video.protocol.VideoCallScreen;
import com.android.incallui.video.protocol.VideoCallScreenDelegate;
import com.android.incallui.videotech.VideoTech;

public class ImsVideoTech implements VideoTech {
    private final Call call;
    ImsVideoCallCallback callback;
    private final VideoTech.VideoTechListener listener;
    private final LoggingBindings logger;
    private boolean paused = false;
    private int previousVideoState = 0;
    private String savedCameraId;
    private int sessionModificationState = 0;
    private boolean transmissionStopped = false;

    public ImsVideoTech(LoggingBindings loggingBindings, VideoTech.VideoTechListener videoTechListener, Call call2) {
        this.logger = loggingBindings;
        this.listener = videoTechListener;
        this.call = call2;
    }

    public void acceptVideoRequest(Context context) {
        int requestedVideoState = this.callback.getRequestedVideoState();
        Assert.checkArgument(requestedVideoState != 0);
        LogUtil.m9i("ImsVideoTech.acceptUpgradeRequest", "videoState: " + requestedVideoState, new Object[0]);
        this.call.getVideoCall().sendSessionModifyResponse(new VideoProfile(requestedVideoState));
        this.listener.onUpgradedToVideo(false);
        ((LoggingBindingsStub) this.logger).logImpression(DialerImpression$Type.IMS_VIDEO_REQUEST_ACCEPTED);
    }

    public void acceptVideoRequestAsAudio() {
        LogUtil.enterBlock("ImsVideoTech.acceptVideoRequestAsAudio");
        this.call.getVideoCall().sendSessionModifyResponse(new VideoProfile(0));
        ((LoggingBindingsStub) this.logger).logImpression(DialerImpression$Type.IMS_VIDEO_REQUEST_ACCEPTED_AS_AUDIO);
    }

    public void becomePrimary() {
        this.listener.onImpressionLoggingNeeded(DialerImpression$Type.UPGRADE_TO_VIDEO_CALL_BUTTON_SHOWN_FOR_IMS);
    }

    public VideoCallScreenDelegate createVideoCallScreenDelegate(Context context, VideoCallScreen videoCallScreen) {
        throw new UnsupportedOperationException();
    }

    public void declineVideoRequest() {
        LogUtil.enterBlock("ImsVideoTech.declineUpgradeRequest");
        this.call.getVideoCall().sendSessionModifyResponse(new VideoProfile(this.call.getDetails().getVideoState()));
        setSessionModificationState(0);
        ((LoggingBindingsStub) this.logger).logImpression(DialerImpression$Type.IMS_VIDEO_REQUEST_DECLINED);
    }

    public int getSessionModificationState() {
        return this.sessionModificationState;
    }

    public VideoTech$Type getVideoTechType() {
        return VideoTech$Type.IMS_VIDEO_TECH;
    }

    public boolean isAvailable(Context context, PhoneAccountHandle phoneAccountHandle) {
        if (this.call.getVideoCall() == null) {
            LogUtil.m9i("ImsVideoCall.isAvailable", "null video call", new Object[0]);
            return false;
        } else if (VideoProfile.isVideo(this.call.getDetails().getVideoState())) {
            LogUtil.m9i("ImsVideoCall.isAvailable", "already video call", new Object[0]);
            return true;
        } else if (!CallUtil.isVideoEnabled(context)) {
            LogUtil.m9i("ImsVideoCall.isAvailable", "disabled in settings", new Object[0]);
            return false;
        } else if (!this.call.getDetails().can(512)) {
            LogUtil.m9i("ImsVideoCall.isAvailable", "no TX", new Object[0]);
            return false;
        } else if (!this.call.getDetails().can(1024)) {
            LogUtil.m9i("ImsVideoCall.isAvailable", "no RX", new Object[0]);
            return false;
        } else {
            LogUtil.m9i("ImsVideoCall.isAvailable", "available", new Object[0]);
            return true;
        }
    }

    public boolean isPaused() {
        return this.paused;
    }

    public boolean isSelfManagedCamera() {
        return false;
    }

    public boolean isTransmitting() {
        return VideoProfile.isTransmissionEnabled(this.call.getDetails().getVideoState());
    }

    public boolean isTransmittingOrReceiving() {
        return VideoProfile.isVideo(this.call.getDetails().getVideoState());
    }

    public void onCallStateChanged(Context context, int i, PhoneAccountHandle phoneAccountHandle) {
        if (isAvailable(context, phoneAccountHandle)) {
            if (this.callback == null) {
                this.callback = new ImsVideoCallCallback(this.logger, this.call, this, this.listener, context);
                this.call.getVideoCall().registerCallback(this.callback);
            }
            if (getSessionModificationState() == 1 && isTransmittingOrReceiving()) {
                LogUtil.m9i("ImsVideoTech.onCallStateChanged", "upgraded to video, clearing session modification state", new Object[0]);
                setSessionModificationState(0);
            }
            int videoState = this.call.getDetails().getVideoState();
            if (videoState != this.previousVideoState && this.sessionModificationState == 3) {
                LogUtil.m9i("ImsVideoTech.onCallStateChanged", "cancelling upgrade notification", new Object[0]);
                setSessionModificationState(0);
            }
            this.previousVideoState = videoState;
        }
    }

    public void onRemovedFromCallList() {
    }

    public void pause() {
        if (this.call.getState() != 4) {
            LogUtil.m9i("ImsVideoTech.pause", "not pausing because call is not active", new Object[0]);
        } else if (!isTransmittingOrReceiving()) {
            LogUtil.m9i("ImsVideoTech.pause", "not pausing because this is not a video call", new Object[0]);
        } else if (this.paused) {
            LogUtil.m9i("ImsVideoTech.pause", "already paused", new Object[0]);
        } else {
            this.paused = true;
            if (this.call.getDetails().can(1048576)) {
                LogUtil.m9i("ImsVideoTech.pause", "sending pause request", new Object[0]);
                int videoState = this.call.getDetails().getVideoState() | 4;
                if (this.transmissionStopped && VideoProfile.isTransmissionEnabled(videoState)) {
                    LogUtil.m9i("ImsVideoTech.pause", "overriding TX to false due to user request", new Object[0]);
                    videoState &= -2;
                }
                this.call.getVideoCall().sendSessionModifyRequest(new VideoProfile(videoState));
                return;
            }
            LogUtil.m9i("ImsVideoTech.pause", "disabling camera", new Object[0]);
            this.call.getVideoCall().setCamera((String) null);
        }
    }

    public void resumeTransmission(Context context) {
        LogUtil.enterBlock("ImsVideoTech.resumeTransmission");
        this.transmissionStopped = false;
        this.call.getVideoCall().sendSessionModifyRequest(new VideoProfile((this.call.getDetails().getVideoState() & -5) | 1));
        setSessionModificationState(7);
    }

    public void setCamera(String str) {
        this.savedCameraId = str;
        if (this.call.getVideoCall() == null) {
            LogUtil.m10w("ImsVideoTech.setCamera", "video call no longer exist", new Object[0]);
            return;
        }
        this.call.getVideoCall().setCamera(str);
        this.call.getVideoCall().requestCameraCapabilities();
    }

    public void setDeviceOrientation(int i) {
        this.call.getVideoCall().setDeviceOrientation(i);
    }

    /* access modifiers changed from: package-private */
    public void setSessionModificationState(int i) {
        int i2 = this.sessionModificationState;
        if (i != i2) {
            LogUtil.m9i("ImsVideoTech.setSessionModificationState", "%d -> %d", Integer.valueOf(i2), Integer.valueOf(i));
            this.sessionModificationState = i;
            this.listener.onSessionModificationStateChanged();
        }
    }

    public boolean shouldUseSurfaceView() {
        return false;
    }

    public void stopTransmission() {
        LogUtil.enterBlock("ImsVideoTech.stopTransmission");
        this.transmissionStopped = true;
        this.call.getVideoCall().sendSessionModifyRequest(new VideoProfile(this.call.getDetails().getVideoState() & -5 & -2));
        setSessionModificationState(7);
    }

    public void unpause() {
        if (this.call.getState() != 4) {
            LogUtil.m9i("ImsVideoTech.unpause", "not unpausing because call is not active", new Object[0]);
        } else if (!isTransmittingOrReceiving()) {
            LogUtil.m9i("ImsVideoTech.unpause", "not unpausing because this is not a video call", new Object[0]);
        } else if (!this.paused) {
            LogUtil.m9i("ImsVideoTech.unpause", "already unpaused", new Object[0]);
        } else {
            this.paused = false;
            if (this.call.getDetails().can(1048576)) {
                LogUtil.m9i("ImsVideoTech.unpause", "sending unpause request", new Object[0]);
                int videoState = this.call.getDetails().getVideoState() & -5;
                if (this.transmissionStopped && VideoProfile.isTransmissionEnabled(videoState)) {
                    LogUtil.m9i("ImsVideoTech.unpause", "overriding TX to false due to user request", new Object[0]);
                    videoState &= -2;
                }
                this.call.getVideoCall().sendSessionModifyRequest(new VideoProfile(videoState));
                return;
            }
            LogUtil.m9i("ImsVideoTech.pause", "re-enabling camera", new Object[0]);
            setCamera(this.savedCameraId);
        }
    }

    public void upgradeToVideo(Context context) {
        LogUtil.enterBlock("ImsVideoTech.upgradeToVideo");
        this.call.getVideoCall().sendSessionModifyRequest(new VideoProfile((this.call.getDetails().getVideoState() & -5) | 3));
        setSessionModificationState(1);
        ((LoggingBindingsStub) this.logger).logImpression(DialerImpression$Type.IMS_VIDEO_UPGRADE_REQUESTED);
    }
}
