package com.android.incallui;

import android.content.Context;
import android.graphics.Point;
import android.os.Handler;
import android.support.p002v7.appcompat.R$style;
import android.telecom.Call;
import android.telecom.InCallService;
import android.telecom.VideoProfile;
import android.view.Surface;
import android.view.SurfaceView;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.configprovider.ConfigProviderComponent;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import com.android.dialer.util.PermissionsUtil;
import com.android.incallui.InCallPresenter;
import com.android.incallui.call.CallList;
import com.android.incallui.call.DialerCall;
import com.android.incallui.call.InCallVideoCallCallbackNotifier;
import com.android.incallui.video.protocol.VideoCallScreen;
import com.android.incallui.video.protocol.VideoCallScreenDelegate;
import com.android.incallui.videosurface.impl.VideoSurfaceTextureImpl;
import com.android.incallui.videosurface.protocol.VideoSurfaceDelegate;
import com.android.incallui.videosurface.protocol.VideoSurfaceTexture;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.Objects;

public class VideoCallPresenter implements InCallPresenter.IncomingCallListener, InCallPresenter.InCallOrientationListener, InCallPresenter.InCallStateListener, InCallPresenter.InCallDetailsListener, InCallVideoCallCallbackNotifier.SurfaceChangeListener, InCallPresenter.InCallEventListener, VideoCallScreenDelegate, CallList.Listener {
    /* access modifiers changed from: private */
    public static boolean isVideoMode = false;
    /* access modifiers changed from: private */
    public boolean autoFullScreenPending = false;
    private Runnable autoFullscreenRunnable = new Runnable() {
        public void run() {
            if (VideoCallPresenter.this.autoFullScreenPending && !InCallPresenter.getInstance().isDialpadVisible() && VideoCallPresenter.isVideoMode) {
                InCallPresenter.getInstance().setFullScreen(true);
                boolean unused = VideoCallPresenter.this.autoFullScreenPending = false;
            }
        }
    };
    private int autoFullscreenTimeoutMillis = 0;
    private Context context;
    private int currentCallState = 0;
    private int currentVideoState;
    private int deviceOrientation = -1;
    private final Handler handler = new Handler();
    private boolean isAutoFullscreenEnabled = false;
    private boolean isRemotelyHeld = false;
    private boolean isVideoCallScreenUiReady;
    /* access modifiers changed from: private */
    public int previewSurfaceState = 0;
    /* access modifiers changed from: private */
    public DialerCall primaryCall;
    /* access modifiers changed from: private */
    public InCallService.VideoCall videoCall;
    /* access modifiers changed from: private */
    public VideoCallScreen videoCallScreen;

    private class LocalDelegate implements VideoSurfaceDelegate {
        /* synthetic */ LocalDelegate(C06481 r2) {
        }

        public void onSurfaceClick(VideoSurfaceTexture videoSurfaceTexture) {
            VideoCallPresenter.this.onSurfaceClick();
        }

        public void onSurfaceCreated(VideoSurfaceTexture videoSurfaceTexture) {
            if (VideoCallPresenter.this.videoCallScreen == null) {
                LogUtil.m8e("VideoCallPresenter.LocalDelegate.onSurfaceCreated", "no UI", new Object[0]);
            } else if (VideoCallPresenter.this.videoCall == null) {
                LogUtil.m8e("VideoCallPresenter.LocalDelegate.onSurfaceCreated", "no video call", new Object[0]);
            } else if (VideoCallPresenter.this.previewSurfaceState == 2) {
                int unused = VideoCallPresenter.this.previewSurfaceState = 3;
                VideoCallPresenter.this.videoCall.setPreviewSurface(((VideoSurfaceTextureImpl) videoSurfaceTexture).getSavedSurface());
            } else if (VideoCallPresenter.this.previewSurfaceState == 0 && VideoCallPresenter.this.isCameraRequired()) {
                VideoCallPresenter videoCallPresenter = VideoCallPresenter.this;
                videoCallPresenter.enableCamera(videoCallPresenter.primaryCall, true);
            }
        }

        public void onSurfaceDestroyed(VideoSurfaceTexture videoSurfaceTexture) {
            if (VideoCallPresenter.this.videoCall == null) {
                LogUtil.m8e("VideoCallPresenter.LocalDelegate.onSurfaceDestroyed", "no video call", new Object[0]);
            } else if (!InCallPresenter.getInstance().isChangingConfigurations()) {
                VideoCallPresenter videoCallPresenter = VideoCallPresenter.this;
                videoCallPresenter.enableCamera(videoCallPresenter.primaryCall, false);
            } else {
                LogUtil.m9i("VideoCallPresenter.LocalDelegate.onSurfaceDestroyed", "activity is being destroyed due to configuration changes. Not closing the camera.", new Object[0]);
            }
        }

        public void onSurfaceReleased(VideoSurfaceTexture videoSurfaceTexture) {
            if (VideoCallPresenter.this.videoCall == null) {
                LogUtil.m8e("VideoCallPresenter.LocalDelegate.onSurfaceReleased", "no video call", new Object[0]);
                return;
            }
            VideoCallPresenter.this.videoCall.setPreviewSurface((Surface) null);
            VideoCallPresenter videoCallPresenter = VideoCallPresenter.this;
            videoCallPresenter.enableCamera(videoCallPresenter.primaryCall, false);
        }
    }

    private class RemoteDelegate implements VideoSurfaceDelegate {
        /* synthetic */ RemoteDelegate(C06481 r2) {
        }

        public void onSurfaceClick(VideoSurfaceTexture videoSurfaceTexture) {
            VideoCallPresenter.this.onSurfaceClick();
        }

        public void onSurfaceCreated(VideoSurfaceTexture videoSurfaceTexture) {
            if (VideoCallPresenter.this.videoCallScreen == null) {
                LogUtil.m8e("VideoCallPresenter.RemoteDelegate.onSurfaceCreated", "no UI", new Object[0]);
            } else if (VideoCallPresenter.this.videoCall == null) {
                LogUtil.m8e("VideoCallPresenter.RemoteDelegate.onSurfaceCreated", "no video call", new Object[0]);
            } else {
                VideoCallPresenter.this.videoCall.setDisplaySurface(((VideoSurfaceTextureImpl) videoSurfaceTexture).getSavedSurface());
            }
        }

        public void onSurfaceDestroyed(VideoSurfaceTexture videoSurfaceTexture) {
        }

        public void onSurfaceReleased(VideoSurfaceTexture videoSurfaceTexture) {
            if (VideoCallPresenter.this.videoCall == null) {
                LogUtil.m8e("VideoCallPresenter.RemoteDelegate.onSurfaceReleased", "no video call", new Object[0]);
            } else {
                VideoCallPresenter.this.videoCall.setDisplaySurface((Surface) null);
            }
        }
    }

    private void adjustVideoMode(DialerCall dialerCall) {
        InCallService.VideoCall videoCall2 = dialerCall.getVideoCall();
        int videoState = dialerCall.getVideoState();
        boolean z = false;
        LogUtil.m9i("VideoCallPresenter.adjustVideoMode", "videoCall: %s, videoState: %d", videoCall2, Integer.valueOf(videoState));
        if (this.videoCallScreen == null) {
            LogUtil.m8e("VideoCallPresenter.adjustVideoMode", "error VideoCallScreen is null so returning", new Object[0]);
            return;
        }
        showVideoUi(videoState, dialerCall.getState(), dialerCall.getVideoTech().getSessionModificationState(), dialerCall.isRemotelyHeld());
        if (videoCall2 != null) {
            Surface savedSurface = ((VideoSurfaceTextureImpl) getRemoteVideoSurfaceTexture()).getSavedSurface();
            if (savedSurface != null) {
                "calling setDisplaySurface with: " + savedSurface;
                videoCall2.setDisplaySurface(savedSurface);
            }
            if (this.deviceOrientation != -1) {
                z = true;
            }
            Assert.checkState(z);
            videoCall2.setDeviceOrientation(this.deviceOrientation);
            enableCamera(dialerCall, isCameraRequired(videoState, dialerCall.getVideoTech().getSessionModificationState()));
        }
        int i = this.currentVideoState;
        this.currentVideoState = videoState;
        isVideoMode = true;
        if (!isVideoCall(i) && isVideoCall(videoState)) {
            maybeAutoEnterFullscreen(dialerCall);
        }
    }

    private void changePreviewDimensions(int i, int i2) {
        if (this.videoCallScreen != null) {
            ((VideoSurfaceTextureImpl) getLocalVideoSurfaceTexture()).setSurfaceDimensions(new Point(i, i2));
            this.videoCallScreen.onLocalVideoDimensionsChanged();
        }
    }

    private void checkForOrientationAllowedChange(DialerCall dialerCall) {
        if (dialerCall != null) {
            InCallPresenter.getInstance().setInCallAllowsOrientationChange(isVideoCall(dialerCall) || isVideoUpgrade(dialerCall));
        }
    }

    /* access modifiers changed from: private */
    public void enableCamera(DialerCall dialerCall, boolean z) {
        Object[] objArr = {dialerCall, Boolean.valueOf(z)};
        if (dialerCall == null) {
            LogUtil.m9i("VideoCallPresenter.enableCamera", "call is null", new Object[0]);
        } else if (!R$style.hasCameraPermissionAndShownPrivacyToast(this.context)) {
            dialerCall.getVideoTech().setCamera((String) null);
            this.previewSurfaceState = 0;
        } else if (z) {
            dialerCall.getVideoTech().setCamera(InCallPresenter.getInstance().getInCallCameraManager().getActiveCameraId());
            this.previewSurfaceState = 1;
        } else {
            this.previewSurfaceState = 0;
            dialerCall.getVideoTech().setCamera((String) null);
        }
    }

    private void exitVideoMode() {
        LogUtil.m9i("VideoCallPresenter.exitVideoMode", "", new Object[0]);
        showVideoUi(0, 3, 0, false);
        enableCamera(this.primaryCall, false);
        InCallPresenter.getInstance().setFullScreen(false);
        InCallPresenter.getInstance().enableScreenTimeout(false);
        isVideoMode = false;
    }

    private static boolean isActiveVideoCall(DialerCall dialerCall) {
        return isVideoCall(dialerCall) && dialerCall.getState() == 3;
    }

    private static boolean isCameraDirectionSet(DialerCall dialerCall) {
        return isVideoCall(dialerCall) && dialerCall.getCameraDir() != -1;
    }

    private static boolean isCameraRequired(int i, int i2) {
        return VideoProfile.isBidirectional(i) || VideoProfile.isTransmissionEnabled(i) || isVideoUpgrade(i2);
    }

    private static boolean isOutgoingVideoCall(DialerCall dialerCall) {
        if (!isVideoCall(dialerCall)) {
            return false;
        }
        int state = dialerCall.getState();
        if (R$style.isDialing(state) || state == 13 || state == 12) {
            return true;
        }
        return false;
    }

    private static boolean isVideoCall(DialerCall dialerCall) {
        return dialerCall != null && dialerCall.isVideoCall();
    }

    private static boolean isVideoUpgrade(DialerCall dialerCall) {
        return dialerCall != null && (dialerCall.hasSentVideoUpgradeRequest() || dialerCall.hasReceivedVideoUpgradeRequest());
    }

    /* access modifiers changed from: private */
    public void onSurfaceClick() {
        LogUtil.m9i("VideoCallPresenter.onSurfaceClick", "", new Object[0]);
        cancelAutoFullScreen();
        if (!InCallPresenter.getInstance().isFullscreen()) {
            InCallPresenter.getInstance().setFullScreen(true);
            return;
        }
        InCallPresenter.getInstance().setFullScreen(false);
        maybeAutoEnterFullscreen(this.primaryCall);
    }

    private static boolean shouldShowVideoUiForCall(DialerCall dialerCall) {
        if (dialerCall == null) {
            return false;
        }
        return isVideoCall(dialerCall) || isVideoUpgrade(dialerCall);
    }

    static boolean showIncomingVideo(int i, int i2) {
        boolean isPaused = VideoProfile.isPaused(i);
        boolean z = i2 == 3;
        boolean z2 = R$style.isDialing(i2) || i2 == 13;
        if (isPaused || ((!z && !z2) || !VideoProfile.isReceptionEnabled(i))) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x005d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void showVideoUi(int r8, int r9, int r10, boolean r11) {
        /*
            r7 = this;
            com.android.incallui.video.protocol.VideoCallScreen r0 = r7.videoCallScreen
            java.lang.String r1 = "VideoCallPresenter.showVideoUi"
            r2 = 0
            if (r0 != 0) goto L_0x000f
            java.lang.Object[] r7 = new java.lang.Object[r2]
            java.lang.String r8 = "videoCallScreen is null returning"
            com.android.dialer.common.LogUtil.m8e((java.lang.String) r1, (java.lang.String) r8, (java.lang.Object[]) r7)
            return
        L_0x000f:
            boolean r0 = showIncomingVideo(r8, r9)
            android.content.Context r3 = r7.context
            boolean r3 = android.support.p002v7.appcompat.R$style.hasCameraPermissionAndShownPrivacyToast(r3)
            r4 = 1
            if (r3 != 0) goto L_0x0026
            java.lang.Object[] r3 = new java.lang.Object[r2]
            java.lang.String r5 = "VideoCallPresenter.showOutgoingVideo"
            java.lang.String r6 = "Camera permission is disabled by user."
            com.android.dialer.common.LogUtil.m9i(r5, r6, r3)
            goto L_0x0033
        L_0x0026:
            boolean r3 = android.telecom.VideoProfile.isTransmissionEnabled(r8)
            if (r3 != 0) goto L_0x0035
            boolean r3 = isVideoUpgrade((int) r10)
            if (r3 == 0) goto L_0x0033
            goto L_0x0035
        L_0x0033:
            r3 = r2
            goto L_0x0036
        L_0x0035:
            r3 = r4
        L_0x0036:
            r5 = 3
            java.lang.Object[] r5 = new java.lang.Object[r5]
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r0)
            r5[r2] = r6
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r3)
            r5[r4] = r2
            r2 = 2
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r11)
            r5[r2] = r4
            java.lang.String r2 = "showIncoming: %b, showOutgoing: %b, isRemotelyHeld: %b"
            com.android.dialer.common.LogUtil.m9i(r1, r2, r5)
            com.android.incallui.video.protocol.VideoCallScreen r1 = r7.videoCallScreen
            android.support.v4.app.Fragment r1 = r1.getVideoCallScreenFragment()
            android.support.v4.app.FragmentActivity r1 = r1.getActivity()
            if (r1 == 0) goto L_0x0076
            android.graphics.Point r2 = new android.graphics.Point
            r2.<init>()
            android.view.WindowManager r1 = r1.getWindowManager()
            android.view.Display r1 = r1.getDefaultDisplay()
            r1.getSize(r2)
            com.android.incallui.videosurface.protocol.VideoSurfaceTexture r1 = r7.getRemoteVideoSurfaceTexture()
            com.android.incallui.videosurface.impl.VideoSurfaceTextureImpl r1 = (com.android.incallui.videosurface.impl.VideoSurfaceTextureImpl) r1
            r1.setSurfaceDimensions(r2)
        L_0x0076:
            com.android.incallui.video.protocol.VideoCallScreen r1 = r7.videoCallScreen
            r1.showVideoViews(r3, r0, r11)
            com.android.incallui.InCallPresenter r11 = com.android.incallui.InCallPresenter.getInstance()
            boolean r8 = android.telecom.VideoProfile.isAudioOnly(r8)
            r11.enableScreenTimeout(r8)
            r7.updateFullscreenAndGreenScreenMode(r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.incallui.VideoCallPresenter.showVideoUi(int, int, int, boolean):void");
    }

    private static int toCameraDirection(int i) {
        return (!VideoProfile.isTransmissionEnabled(i) || VideoProfile.isBidirectional(i)) ? 0 : 1;
    }

    private void updateCallCache(DialerCall dialerCall) {
        if (dialerCall == null) {
            this.currentVideoState = 0;
            this.currentCallState = 0;
            this.videoCall = null;
            this.primaryCall = null;
            return;
        }
        this.currentVideoState = dialerCall.getVideoState();
        this.videoCall = dialerCall.getVideoCall();
        this.currentCallState = dialerCall.getState();
        this.primaryCall = dialerCall;
    }

    private static void updateCameraSelection(DialerCall dialerCall) {
        String str;
        int state;
        "call=" + dialerCall;
        StringBuilder sb = new StringBuilder();
        sb.append("call=");
        if (dialerCall == null) {
            str = null;
        } else {
            str = dialerCall.toSimpleString();
        }
        sb.append(str);
        sb.toString();
        DialerCall activeCall = CallList.getInstance().getActiveCall();
        int i = -1;
        boolean z = true;
        if (dialerCall == null) {
            LogUtil.m8e("VideoCallPresenter.updateCameraSelection", "call is null. Setting camera direction to default value (CAMERA_DIRECTION_UNKNOWN)", new Object[0]);
        } else {
            if (!(VideoProfile.isAudioOnly(dialerCall.getVideoState())) || isVideoUpgrade(dialerCall)) {
                if (isVideoCall(activeCall)) {
                    if (isVideoCall(dialerCall) && ((state = dialerCall.getState()) == 4 || state == 5)) {
                        i = activeCall.getCameraDir();
                    }
                }
                if (isOutgoingVideoCall(dialerCall) && !isCameraDirectionSet(dialerCall)) {
                    i = toCameraDirection(dialerCall.getVideoState());
                    dialerCall.setCameraDir(i);
                } else if (isOutgoingVideoCall(dialerCall)) {
                    i = dialerCall.getCameraDir();
                } else if (!isActiveVideoCall(dialerCall) || isCameraDirectionSet(dialerCall)) {
                    i = isActiveVideoCall(dialerCall) ? dialerCall.getCameraDir() : toCameraDirection(dialerCall.getVideoState());
                } else {
                    i = toCameraDirection(dialerCall.getVideoState());
                    dialerCall.setCameraDir(i);
                }
            } else {
                dialerCall.setCameraDir(-1);
            }
        }
        LogUtil.m9i("VideoCallPresenter.updateCameraSelection", "setting camera direction to %d, call: %s", Integer.valueOf(i), dialerCall);
        InCallCameraManager inCallCameraManager = InCallPresenter.getInstance().getInCallCameraManager();
        if (i != 0) {
            z = false;
        }
        inCallCameraManager.setUseFrontFacingCamera(z);
    }

    private void updateFullscreenAndGreenScreenMode(int i, int i2) {
        if (this.videoCallScreen != null) {
            this.videoCallScreen.updateFullscreenAndGreenScreenMode(InCallPresenter.getInstance().isFullscreen(), i == 6 || i == 13 || i == 4 || isVideoUpgrade(i2));
        }
    }

    private void updateVideoCall(DialerCall dialerCall) {
        InCallService.VideoCall videoCall2 = dialerCall.getVideoCall();
        InCallService.VideoCall videoCall3 = this.videoCall;
        Object[] objArr = {videoCall2, videoCall3};
        if (!Objects.equals(videoCall2, videoCall3)) {
            InCallService.VideoCall videoCall4 = dialerCall.getVideoCall();
            LogUtil.m9i("VideoCallPresenter.changeVideoCall", "videoCall: %s, mVideoCall: %s", videoCall4, this.videoCall);
            boolean z = this.videoCall == null && videoCall4 != null;
            this.videoCall = videoCall4;
            if (this.videoCall != null && shouldShowVideoUiForCall(dialerCall) && z) {
                adjustVideoMode(dialerCall);
            }
        }
        boolean shouldShowVideoUiForCall = shouldShowVideoUiForCall(dialerCall);
        boolean z2 = this.currentVideoState != dialerCall.getVideoState();
        Object[] objArr2 = {Boolean.valueOf(shouldShowVideoUiForCall), Boolean.valueOf(z2), Boolean.valueOf(isVideoMode), VideoProfile.videoStateToString(this.currentVideoState), VideoProfile.videoStateToString(dialerCall.getVideoState())};
        if (z2) {
            updateCameraSelection(dialerCall);
            if (shouldShowVideoUiForCall) {
                adjustVideoMode(dialerCall);
            } else if (isVideoMode) {
                exitVideoMode();
            }
        }
        boolean shouldShowVideoUiForCall2 = shouldShowVideoUiForCall(dialerCall);
        boolean z3 = (this.currentCallState == dialerCall.getState() && this.isRemotelyHeld == dialerCall.isRemotelyHeld()) ? false : true;
        this.isRemotelyHeld = dialerCall.isRemotelyHeld();
        Object[] objArr3 = {Boolean.valueOf(shouldShowVideoUiForCall2), Boolean.valueOf(z3), Boolean.valueOf(isVideoMode)};
        if (z3) {
            if (shouldShowVideoUiForCall2) {
                InCallCameraManager inCallCameraManager = InCallPresenter.getInstance().getInCallCameraManager();
                String activeCameraId = inCallCameraManager.getActiveCameraId();
                updateCameraSelection(dialerCall);
                if (!Objects.equals(activeCameraId, inCallCameraManager.getActiveCameraId()) && isActiveVideoCall(dialerCall)) {
                    enableCamera(dialerCall, true);
                }
            }
            showVideoUi(dialerCall.getVideoState(), dialerCall.getState(), dialerCall.getVideoTech().getSessionModificationState(), dialerCall.isRemotelyHeld());
        }
        checkForOrientationAllowedChange(dialerCall);
        updateFullscreenAndGreenScreenMode(dialerCall.getState(), dialerCall.getVideoTech().getSessionModificationState());
    }

    public void cancelAutoFullScreen() {
        if (this.autoFullScreenPending) {
            this.autoFullScreenPending = false;
            this.handler.removeCallbacks(this.autoFullscreenRunnable);
        }
    }

    public int getDeviceOrientation() {
        return this.deviceOrientation;
    }

    public VideoSurfaceTexture getLocalVideoSurfaceTexture() {
        return InCallPresenter.getInstance().getLocalVideoSurfaceTexture();
    }

    public VideoSurfaceTexture getRemoteVideoSurfaceTexture() {
        return InCallPresenter.getInstance().getRemoteVideoSurfaceTexture();
    }

    public void initVideoCallScreenDelegate(Context context2, VideoCallScreen videoCallScreen2) {
        this.context = context2;
        this.videoCallScreen = videoCallScreen2;
        this.isAutoFullscreenEnabled = this.context.getResources().getBoolean(R.bool.video_call_auto_fullscreen);
        this.autoFullscreenTimeoutMillis = this.context.getResources().getInteger(R.integer.video_call_auto_fullscreen_timeout);
    }

    public boolean isFullscreen() {
        return InCallPresenter.getInstance().isFullscreen();
    }

    /* access modifiers changed from: protected */
    public void maybeAutoEnterFullscreen(DialerCall dialerCall) {
        Context context2;
        if (this.isAutoFullscreenEnabled) {
            if (dialerCall == null || dialerCall.getState() != 3 || !VideoProfile.isBidirectional(dialerCall.getVideoState()) || InCallPresenter.getInstance().isFullscreen() || ((context2 = this.context) != null && R$style.isTouchExplorationEnabled(context2))) {
                cancelAutoFullScreen();
            } else if (!this.autoFullScreenPending) {
                this.autoFullScreenPending = true;
                this.handler.removeCallbacks(this.autoFullscreenRunnable);
                this.handler.postDelayed(this.autoFullscreenRunnable, (long) this.autoFullscreenTimeoutMillis);
            }
        }
    }

    public void onCallListChange(CallList callList) {
    }

    public void onCameraDimensionsChange(DialerCall dialerCall, int i, int i2) {
        LogUtil.m9i("VideoCallPresenter.onCameraDimensionsChange", "call: %s, width: %d, height: %d", dialerCall, Integer.valueOf(i), Integer.valueOf(i2));
        if (this.videoCallScreen == null) {
            LogUtil.m8e("VideoCallPresenter.onCameraDimensionsChange", "ui is null", new Object[0]);
        } else if (!dialerCall.equals(this.primaryCall)) {
            LogUtil.m8e("VideoCallPresenter.onCameraDimensionsChange", "not the primary call", new Object[0]);
        } else {
            this.previewSurfaceState = 2;
            changePreviewDimensions(i, i2);
            Surface savedSurface = ((VideoSurfaceTextureImpl) getLocalVideoSurfaceTexture()).getSavedSurface();
            if (savedSurface != null) {
                this.previewSurfaceState = 3;
                this.videoCall.setPreviewSurface(savedSurface);
            }
        }
    }

    public void onCameraPermissionDialogShown() {
        DialerCall dialerCall = this.primaryCall;
        if (dialerCall != null) {
            dialerCall.setDidShowCameraPermission(true);
        }
    }

    public void onCameraPermissionGranted() {
        LogUtil.m9i("VideoCallPresenter.onCameraPermissionGranted", "", new Object[0]);
        PermissionsUtil.setCameraPrivacyToastShown(this.context);
        enableCamera(this.primaryCall, isCameraRequired());
        showVideoUi(this.primaryCall.getVideoState(), this.primaryCall.getState(), this.primaryCall.getVideoTech().getSessionModificationState(), this.primaryCall.isRemotelyHeld());
        InCallPresenter.getInstance().getInCallCameraManager().onCameraPermissionGranted();
    }

    public void onDetailsChanged(DialerCall dialerCall, Call.Details details) {
        DialerCall dialerCall2 = this.primaryCall;
        Object[] objArr = {dialerCall, details, dialerCall2};
        if (dialerCall != null && dialerCall.equals(dialerCall2)) {
            updateVideoCall(dialerCall);
            updateCallCache(dialerCall);
        }
    }

    public void onDeviceOrientationChanged(int i) {
        LogUtil.m9i("VideoCallPresenter.onDeviceOrientationChanged", "orientation: %d -> %d", Integer.valueOf(this.deviceOrientation), Integer.valueOf(i));
        this.deviceOrientation = i;
        if (this.videoCallScreen == null) {
            LogUtil.m8e("VideoCallPresenter.onDeviceOrientationChanged", "videoCallScreen is null", new Object[0]);
            return;
        }
        Point surfaceDimensions = ((VideoSurfaceTextureImpl) getLocalVideoSurfaceTexture()).getSurfaceDimensions();
        if (surfaceDimensions != null) {
            Object[] objArr = {Integer.valueOf(i), surfaceDimensions};
            changePreviewDimensions(surfaceDimensions.x, surfaceDimensions.y);
            this.videoCallScreen.onLocalVideoOrientationChanged();
        }
    }

    public void onDisconnect(DialerCall dialerCall) {
    }

    public void onFullscreenModeChanged(boolean z) {
        cancelAutoFullScreen();
        DialerCall dialerCall = this.primaryCall;
        if (dialerCall != null) {
            updateFullscreenAndGreenScreenMode(dialerCall.getState(), this.primaryCall.getVideoTech().getSessionModificationState());
        } else {
            updateFullscreenAndGreenScreenMode(0, 0);
        }
    }

    public void onHandoverToWifiFailed(DialerCall dialerCall) {
    }

    public void onIncomingCall(InCallPresenter.InCallState inCallState, InCallPresenter.InCallState inCallState2, DialerCall dialerCall) {
        if (!this.isVideoCallScreenUiReady) {
            LogUtil.m9i("VideoCallPresenter.onIncomingCall", "UI is not ready", new Object[0]);
        } else {
            onStateChange(inCallState, inCallState2, CallList.getInstance());
        }
    }

    public void onIncomingCall(DialerCall dialerCall) {
    }

    public void onInternationalCallOnWifi(DialerCall dialerCall) {
    }

    public void onSessionModificationStateChange(DialerCall dialerCall) {
    }

    public void onStateChange(InCallPresenter.InCallState inCallState, InCallPresenter.InCallState inCallState2, CallList callList) {
        DialerCall dialerCall;
        Object[] objArr = {inCallState, inCallState2, Boolean.valueOf(isVideoMode)};
        if (inCallState2 == InCallPresenter.InCallState.NO_CALLS) {
            if (isVideoMode) {
                exitVideoMode();
            }
            InCallPresenter.getInstance().cleanupSurfaces();
        }
        DialerCall dialerCall2 = null;
        if (inCallState2 == InCallPresenter.InCallState.INCOMING) {
            dialerCall2 = callList.getActiveCall();
            dialerCall = callList.getIncomingCall();
            if (!isActiveVideoCall(dialerCall2)) {
                dialerCall2 = callList.getIncomingCall();
            }
        } else {
            if (inCallState2 == InCallPresenter.InCallState.OUTGOING) {
                dialerCall2 = callList.getOutgoingCall();
            } else if (inCallState2 == InCallPresenter.InCallState.PENDING_OUTGOING) {
                dialerCall2 = callList.getPendingOutgoingCall();
            } else if (inCallState2 == InCallPresenter.InCallState.INCALL) {
                dialerCall2 = callList.getActiveCall();
            }
            dialerCall = dialerCall2;
        }
        boolean z = !Objects.equals(this.primaryCall, dialerCall2);
        LogUtil.m9i("VideoCallPresenter.onStateChange", "primaryChanged: %b, primary: %s, mPrimaryCall: %s", Boolean.valueOf(z), dialerCall2, this.primaryCall);
        if (z) {
            boolean shouldShowVideoUiForCall = shouldShowVideoUiForCall(dialerCall2);
            boolean z2 = isVideoMode;
            Object[] objArr2 = {Boolean.valueOf(shouldShowVideoUiForCall), Boolean.valueOf(z2)};
            if (!shouldShowVideoUiForCall && z2) {
                LogUtil.m9i("VideoCallPresenter.onPrimaryCallChanged", "exiting video mode...", new Object[0]);
                exitVideoMode();
            } else if (shouldShowVideoUiForCall) {
                LogUtil.m9i("VideoCallPresenter.onPrimaryCallChanged", "entering video mode...", new Object[0]);
                updateCameraSelection(dialerCall2);
                adjustVideoMode(dialerCall2);
            }
            checkForOrientationAllowedChange(dialerCall2);
        } else if (this.primaryCall != null) {
            updateVideoCall(dialerCall2);
        }
        updateCallCache(dialerCall2);
        if (dialerCall != null && (!isVideoCall(dialerCall) || dialerCall.getState() == 4)) {
            LogUtil.m9i("VideoCallPresenter.maybeExitFullscreen", "exiting fullscreen", new Object[0]);
            InCallPresenter.getInstance().setFullScreen(false);
        }
        maybeAutoEnterFullscreen(dialerCall);
    }

    public void onSystemUiVisibilityChange(boolean z) {
        LogUtil.m9i("VideoCallPresenter.onSystemUiVisibilityChange", GeneratedOutlineSupport.outline10("visible: ", z), new Object[0]);
        if (z) {
            InCallPresenter.getInstance().setFullScreen(false);
            maybeAutoEnterFullscreen(this.primaryCall);
        }
    }

    public void onUpdatePeerDimensions(DialerCall dialerCall, int i, int i2) {
        LogUtil.m9i("VideoCallPresenter.onUpdatePeerDimensions", "width: %d, height: %d", Integer.valueOf(i), Integer.valueOf(i2));
        if (this.videoCallScreen == null) {
            LogUtil.m8e("VideoCallPresenter.onUpdatePeerDimensions", "videoCallScreen is null", new Object[0]);
        } else if (!dialerCall.equals(this.primaryCall)) {
            LogUtil.m8e("VideoCallPresenter.onUpdatePeerDimensions", "current call is not equal to primary", new Object[0]);
        } else if (i > 0 && i2 > 0 && this.videoCallScreen != null) {
            ((VideoSurfaceTextureImpl) getRemoteVideoSurfaceTexture()).setSourceVideoDimensions(new Point(i, i2));
            this.videoCallScreen.onRemoteVideoDimensionsChanged();
        }
    }

    public void onUpgradeToVideo(DialerCall dialerCall) {
    }

    public void onVideoCallScreenUiReady() {
        DialerCall dialerCall;
        Assert.checkState(!this.isVideoCallScreenUiReady);
        this.deviceOrientation = InCallOrientationEventListener.getCurrentOrientation();
        InCallPresenter.getInstance().addListener(this);
        InCallPresenter.getInstance().addDetailsListener(this);
        InCallPresenter.getInstance().addIncomingCallListener(this);
        InCallPresenter.getInstance().addOrientationListener(this);
        InCallPresenter.getInstance().addInCallEventListener(this);
        ((VideoSurfaceTextureImpl) InCallPresenter.getInstance().getLocalVideoSurfaceTexture()).setDelegate(new LocalDelegate((C06481) null));
        ((VideoSurfaceTextureImpl) InCallPresenter.getInstance().getRemoteVideoSurfaceTexture()).setDelegate(new RemoteDelegate((C06481) null));
        CallList.getInstance().addListener(this);
        InCallVideoCallCallbackNotifier.getInstance().addSurfaceChangeListener(this);
        boolean z = false;
        this.currentVideoState = 0;
        this.currentCallState = 0;
        InCallPresenter.InCallState inCallState = InCallPresenter.getInstance().getInCallState();
        onStateChange(inCallState, inCallState, CallList.getInstance());
        this.isVideoCallScreenUiReady = true;
        Point sourceVideoDimensions = ((VideoSurfaceTextureImpl) getRemoteVideoSurfaceTexture()).getSourceVideoDimensions();
        if (sourceVideoDimensions != null && (dialerCall = this.primaryCall) != null) {
            int peerDimensionWidth = dialerCall.getPeerDimensionWidth();
            int peerDimensionHeight = this.primaryCall.getPeerDimensionHeight();
            if (!(-1 == peerDimensionWidth || -1 == peerDimensionHeight)) {
                z = true;
            }
            if (!z) {
                return;
            }
            if (sourceVideoDimensions.x != peerDimensionWidth || sourceVideoDimensions.y != peerDimensionHeight) {
                onUpdatePeerDimensions(this.primaryCall, peerDimensionWidth, peerDimensionHeight);
            }
        }
    }

    public void onVideoCallScreenUiUnready() {
        Assert.checkState(this.isVideoCallScreenUiReady);
        cancelAutoFullScreen();
        InCallPresenter.getInstance().removeListener(this);
        InCallPresenter.getInstance().removeDetailsListener(this);
        InCallPresenter.getInstance().removeIncomingCallListener(this);
        InCallPresenter.getInstance().removeOrientationListener(this);
        InCallPresenter.getInstance().removeInCallEventListener(this);
        ((VideoSurfaceTextureImpl) InCallPresenter.getInstance().getLocalVideoSurfaceTexture()).setDelegate((VideoSurfaceDelegate) null);
        CallList.getInstance().removeListener(this);
        InCallVideoCallCallbackNotifier.getInstance().removeSurfaceChangeListener(this);
        DialerCall dialerCall = this.primaryCall;
        if (dialerCall != null) {
            updateCameraSelection(dialerCall);
        }
        this.isVideoCallScreenUiReady = false;
    }

    public void onWiFiToLteHandover(DialerCall dialerCall) {
        if (dialerCall.isVideoCall() || dialerCall.hasSentVideoUpgradeRequest()) {
            this.videoCallScreen.onHandoverFromWiFiToLte();
        }
    }

    public void resetAutoFullscreenTimer() {
        if (this.autoFullScreenPending) {
            LogUtil.m9i("VideoCallPresenter.resetAutoFullscreenTimer", "resetting", new Object[0]);
            this.handler.removeCallbacks(this.autoFullscreenRunnable);
            this.handler.postDelayed(this.autoFullscreenRunnable, (long) this.autoFullscreenTimeoutMillis);
        }
    }

    public void setSurfaceViews(SurfaceView surfaceView, SurfaceView surfaceView2) {
        throw new UnsupportedOperationException();
    }

    public boolean shouldShowCameraPermissionToast() {
        DialerCall dialerCall = this.primaryCall;
        if (dialerCall == null) {
            LogUtil.m9i("VideoCallPresenter.shouldShowCameraPermissionToast", "null call", new Object[0]);
            return false;
        } else if (dialerCall.didShowCameraPermission()) {
            LogUtil.m9i("VideoCallPresenter.shouldShowCameraPermissionToast", "already shown for this call", new Object[0]);
            return false;
        } else if (!((SharedPrefConfigProvider) ConfigProviderComponent.get(this.context).getConfigProvider()).getBoolean("camera_permission_dialog_allowed", true)) {
            LogUtil.m9i("VideoCallPresenter.shouldShowCameraPermissionToast", "disabled by config", new Object[0]);
            return false;
        } else if (!R$style.hasCameraPermission(this.context) || !PermissionsUtil.hasCameraPrivacyToastShown(this.context)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isVideoCall(int i) {
        return VideoProfile.isTransmissionEnabled(i) || VideoProfile.isReceptionEnabled(i);
    }

    private static boolean isVideoUpgrade(int i) {
        return R$style.hasSentVideoUpgradeRequest(i) || R$style.hasReceivedVideoUpgradeRequest(i);
    }

    /* access modifiers changed from: private */
    public boolean isCameraRequired() {
        DialerCall dialerCall = this.primaryCall;
        return dialerCall != null && isCameraRequired(dialerCall.getVideoState(), this.primaryCall.getVideoTech().getSessionModificationState());
    }
}
