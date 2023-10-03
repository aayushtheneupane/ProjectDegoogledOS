package com.android.incallui.videotech.ims;

import android.content.Context;
import android.os.Handler;
import android.telecom.Call;
import android.telecom.InCallService;
import android.telecom.VideoProfile;
import com.android.dialer.common.LogUtil;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.LoggingBindings;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.incallui.videotech.VideoTech;
import com.android.tools.p006r8.GeneratedOutlineSupport;

public class ImsVideoCallCallback extends InCallService.VideoCall.Callback {
    private final Call call;
    private final Context context;
    private final Handler handler = new Handler();
    private final VideoTech.VideoTechListener listener;
    private final LoggingBindings logger;
    private int requestedVideoState = 0;
    private final ImsVideoTech videoTech;

    ImsVideoCallCallback(LoggingBindings loggingBindings, Call call2, ImsVideoTech imsVideoTech, VideoTech.VideoTechListener videoTechListener, Context context2) {
        this.logger = loggingBindings;
        this.call = call2;
        this.videoTech = imsVideoTech;
        this.listener = videoTechListener;
        this.context = context2;
    }

    private int getSessionModificationStateFromTelecomStatus(int i) {
        if (i == 1) {
            return 0;
        }
        if (i == 2 || i == 3) {
            return VideoProfile.isVideo(this.call.getDetails().getVideoState()) ? 2 : 5;
        }
        if (i == 4) {
            return 4;
        }
        if (i == 5) {
            return 6;
        }
        LogUtil.m8e("ImsVideoCallCallback.getSessionModificationStateFromTelecomStatus", "unknown status: %d", Integer.valueOf(i));
        return 2;
    }

    /* access modifiers changed from: package-private */
    public int getRequestedVideoState() {
        return this.requestedVideoState;
    }

    public /* synthetic */ void lambda$clearFailedResponseState$0$ImsVideoCallCallback(int i) {
        if (this.videoTech.getSessionModificationState() == i) {
            LogUtil.m9i("ImsVideoCallCallback.onSessionModifyResponseReceived", "clearing state", new Object[0]);
            this.videoTech.setSessionModificationState(0);
            return;
        }
        LogUtil.m9i("ImsVideoCallCallback.onSessionModifyResponseReceived", "session modification state has changed, not clearing state", new Object[0]);
    }

    public void onCallDataUsageChanged(long j) {
        LogUtil.m9i("ImsVideoCallCallback.onCallDataUsageChanged", "dataUsage: %d", Long.valueOf(j));
    }

    public void onCallSessionEvent(int i) {
        if (i == 1) {
            LogUtil.m9i("ImsVideoCallCallback.onCallSessionEvent", "rx_pause", new Object[0]);
        } else if (i == 2) {
            LogUtil.m9i("ImsVideoCallCallback.onCallSessionEvent", "rx_resume", new Object[0]);
        } else if (i == 5) {
            LogUtil.m9i("ImsVideoCallCallback.onCallSessionEvent", "camera_failure", new Object[0]);
        } else if (i != 6) {
            LogUtil.m9i("ImsVideoCallCallback.onCallSessionEvent", GeneratedOutlineSupport.outline5("unknown event = : ", i), new Object[0]);
        } else {
            LogUtil.m9i("ImsVideoCallCallback.onCallSessionEvent", "camera_ready", new Object[0]);
        }
    }

    public void onCameraCapabilitiesChanged(VideoProfile.CameraCapabilities cameraCapabilities) {
        if (cameraCapabilities != null) {
            this.listener.onCameraDimensionsChanged(cameraCapabilities.getWidth(), cameraCapabilities.getHeight());
        }
    }

    public void onPeerDimensionsChanged(int i, int i2) {
        this.listener.onPeerDimensionsChanged(i, i2);
    }

    public void onSessionModifyRequestReceived(VideoProfile videoProfile) {
        LogUtil.m9i("ImsVideoCallCallback.onSessionModifyRequestReceived", GeneratedOutlineSupport.outline6("videoProfile: ", videoProfile), new Object[0]);
        int videoState = this.call.getDetails().getVideoState() & -5;
        int videoState2 = videoProfile.getVideoState() & -5;
        boolean isVideo = VideoProfile.isVideo(videoState);
        boolean isVideo2 = VideoProfile.isVideo(videoState2);
        if (isVideo && !isVideo2) {
            LogUtil.m9i("ImsVideoTech.onSessionModifyRequestReceived", "call downgraded to %d", Integer.valueOf(videoState2));
        } else if (videoState != videoState2) {
            this.requestedVideoState = videoState2;
            if (!isVideo) {
                this.videoTech.setSessionModificationState(3);
                this.listener.onVideoUpgradeRequestReceived();
                ((LoggingBindingsStub) this.logger).logImpression(DialerImpression$Type.IMS_VIDEO_REQUEST_RECEIVED);
                return;
            }
            LogUtil.m9i("ImsVideoTech.onSessionModifyRequestReceived", "call updated to %d", Integer.valueOf(videoState2));
            this.videoTech.acceptVideoRequest(this.context);
        }
    }

    public void onSessionModifyResponseReceived(int i, VideoProfile videoProfile, VideoProfile videoProfile2) {
        LogUtil.m9i("ImsVideoCallCallback.onSessionModifyResponseReceived", "status: %d, requestedProfile: %s, responseProfile: %s, session modification state: %d", Integer.valueOf(i), videoProfile, videoProfile2, Integer.valueOf(this.videoTech.getSessionModificationState()));
        if (this.videoTech.getSessionModificationState() == 1) {
            int sessionModificationStateFromTelecomStatus = getSessionModificationStateFromTelecomStatus(i);
            if (i == 1) {
                this.listener.onUpgradedToVideo(false);
            } else {
                this.videoTech.setSessionModificationState(sessionModificationStateFromTelecomStatus);
            }
            this.handler.removeCallbacksAndMessages((Object) null);
            this.handler.postDelayed(new Runnable(sessionModificationStateFromTelecomStatus) {
                private final /* synthetic */ int f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    ImsVideoCallCallback.this.lambda$clearFailedResponseState$0$ImsVideoCallCallback(this.f$1);
                }
            }, 4000);
        } else if (this.videoTech.getSessionModificationState() == 3) {
            this.requestedVideoState = 0;
            this.videoTech.setSessionModificationState(0);
        } else if (this.videoTech.getSessionModificationState() == 7) {
            int sessionModificationStateFromTelecomStatus2 = getSessionModificationStateFromTelecomStatus(i);
            this.videoTech.setSessionModificationState(sessionModificationStateFromTelecomStatus2);
            if (i != 1) {
                this.handler.removeCallbacksAndMessages((Object) null);
                this.handler.postDelayed(new Runnable(sessionModificationStateFromTelecomStatus2) {
                    private final /* synthetic */ int f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        ImsVideoCallCallback.this.lambda$clearFailedResponseState$0$ImsVideoCallCallback(this.f$1);
                    }
                }, 4000);
            }
        } else {
            LogUtil.m9i("ImsVideoCallCallback.onSessionModifyResponseReceived", "call is not waiting for response, doing nothing", new Object[0]);
        }
    }

    public void onVideoQualityChanged(int i) {
        LogUtil.m9i("ImsVideoCallCallback.onVideoQualityChanged", "videoQuality: %d", Integer.valueOf(i));
    }
}
