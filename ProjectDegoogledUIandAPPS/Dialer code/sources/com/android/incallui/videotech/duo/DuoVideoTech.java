package com.android.incallui.videotech.duo;

import android.content.Context;
import android.telecom.Call;
import android.telecom.PhoneAccountHandle;
import com.android.dialer.common.Assert;
import com.android.dialer.common.concurrent.DefaultFutureCallback;
import com.android.dialer.configprovider.ConfigProviderComponent;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import com.android.dialer.duo.Duo;
import com.android.dialer.duo.DuoListener;
import com.android.dialer.duo.stub.DuoStub;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.VideoTech$Type;
import com.android.incallui.video.protocol.VideoCallScreen;
import com.android.incallui.video.protocol.VideoCallScreenDelegate;
import com.android.incallui.videotech.VideoTech;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.MoreExecutors;

public class DuoVideoTech implements VideoTech, DuoListener {
    private final Call call;
    private int callState = 0;
    private final String callingNumber;
    private final Duo duo;
    private boolean isRemoteUpgradeAvailabilityQueried;
    private final VideoTech.VideoTechListener listener;

    public DuoVideoTech(Duo duo2, VideoTech.VideoTechListener videoTechListener, Call call2, String str) {
        Assert.isNotNull(duo2);
        this.duo = duo2;
        Assert.isNotNull(videoTechListener);
        this.listener = videoTechListener;
        Assert.isNotNull(call2);
        this.call = call2;
        Assert.isNotNull(str);
        this.callingNumber = str;
        ((DuoStub) duo2).registerListener(this);
    }

    public void acceptVideoRequest(Context context) {
        throw new UnsupportedOperationException();
    }

    public void acceptVideoRequestAsAudio() {
        throw new UnsupportedOperationException();
    }

    public void becomePrimary() {
        this.listener.onImpressionLoggingNeeded(DialerImpression$Type.UPGRADE_TO_VIDEO_CALL_BUTTON_SHOWN_FOR_LIGHTBRINGER);
    }

    public VideoCallScreenDelegate createVideoCallScreenDelegate(Context context, VideoCallScreen videoCallScreen) {
        throw new UnsupportedOperationException();
    }

    public void declineVideoRequest() {
        throw new UnsupportedOperationException();
    }

    public int getSessionModificationState() {
        return 0;
    }

    public VideoTech$Type getVideoTechType() {
        return VideoTech$Type.LIGHTBRINGER_VIDEO_TECH;
    }

    public boolean isAvailable(Context context, PhoneAccountHandle phoneAccountHandle) {
        if (!((SharedPrefConfigProvider) ConfigProviderComponent.get(context).getConfigProvider()).getBoolean("enable_lightbringer_video_upgrade", true) || this.callState != 4) {
            return false;
        }
        Optional<Boolean> supportsUpgrade = ((DuoStub) this.duo).supportsUpgrade(context, this.callingNumber, phoneAccountHandle);
        if (supportsUpgrade.isPresent()) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("upgrade supported in local cache: ");
            outline13.append(supportsUpgrade.get());
            outline13.toString();
            return supportsUpgrade.get().booleanValue();
        }
        if (!this.isRemoteUpgradeAvailabilityQueried) {
            this.isRemoteUpgradeAvailabilityQueried = true;
            Futures.addCallback(((DuoStub) this.duo).updateReachability(context, ImmutableList.m75of(this.callingNumber)), new DefaultFutureCallback(), MoreExecutors.directExecutor());
        }
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
        if (i == 10) {
            ((DuoStub) this.duo).unregisterListener(this);
        }
        this.callState = i;
    }

    public void onRemovedFromCallList() {
        ((DuoStub) this.duo).unregisterListener(this);
    }

    public void pause() {
    }

    public void resumeTransmission(Context context) {
        throw new UnsupportedOperationException();
    }

    public void setCamera(String str) {
        throw new UnsupportedOperationException();
    }

    public void setDeviceOrientation(int i) {
    }

    public boolean shouldUseSurfaceView() {
        return false;
    }

    public void stopTransmission() {
        throw new UnsupportedOperationException();
    }

    public void unpause() {
    }

    public void upgradeToVideo(Context context) {
        this.listener.onImpressionLoggingNeeded(DialerImpression$Type.LIGHTBRINGER_UPGRADE_REQUESTED);
        ((DuoStub) this.duo).requestUpgrade(context, this.call);
    }
}
