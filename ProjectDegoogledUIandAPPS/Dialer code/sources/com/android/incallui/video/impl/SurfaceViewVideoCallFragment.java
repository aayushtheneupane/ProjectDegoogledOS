package com.android.incallui.video.impl;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentTransaction;
import android.support.p000v4.view.animation.FastOutLinearInInterpolator;
import android.support.p000v4.view.animation.LinearOutSlowInInterpolator;
import android.support.p002v7.appcompat.R$style;
import android.telecom.CallAudioState;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.common.FragmentUtils;
import com.android.dialer.common.LogUtil;
import com.android.dialer.util.PermissionsUtil;
import com.android.incallui.VideoCallPresenter;
import com.android.incallui.audioroute.AudioRouteSelectorDialogFragment;
import com.android.incallui.contactgrid.ContactGridManager;
import com.android.incallui.hold.OnHoldFragment;
import com.android.incallui.incall.protocol.InCallButtonUi;
import com.android.incallui.incall.protocol.InCallButtonUiDelegate;
import com.android.incallui.incall.protocol.InCallButtonUiDelegateFactory;
import com.android.incallui.incall.protocol.InCallScreen;
import com.android.incallui.incall.protocol.InCallScreenDelegate;
import com.android.incallui.incall.protocol.InCallScreenDelegateFactory;
import com.android.incallui.incall.protocol.PrimaryCallState;
import com.android.incallui.incall.protocol.PrimaryInfo;
import com.android.incallui.incall.protocol.SecondaryInfo;
import com.android.incallui.video.impl.CheckableImageButton;
import com.android.incallui.video.protocol.VideoCallScreen;
import com.android.incallui.video.protocol.VideoCallScreenDelegate;
import com.android.incallui.video.protocol.VideoCallScreenDelegateFactory;
import com.android.tools.p006r8.GeneratedOutlineSupport;

public class SurfaceViewVideoCallFragment extends Fragment implements InCallScreen, InCallButtonUi, VideoCallScreen, View.OnClickListener, CheckableImageButton.OnCheckedChangeListener, AudioRouteSelectorDialogFragment.AudioRouteSelectorPresenter, View.OnSystemUiVisibilityChangeListener {
    static final String ARG_CALL_ID = "call_id";
    private CheckableImageButton cameraOffButton;
    private final Runnable cameraPermissionDialogRunnable = new Runnable() {
        public void run() {
            if (((VideoCallPresenter) SurfaceViewVideoCallFragment.this.videoCallScreenDelegate).shouldShowCameraPermissionToast()) {
                LogUtil.m9i("SurfaceViewVideoCallFragment.cameraPermissionDialogRunnable", "showing dialog", new Object[0]);
                SurfaceViewVideoCallFragment.this.checkCameraPermission();
            }
        }
    };
    /* access modifiers changed from: private */
    public ContactGridManager contactGridManager;
    private View controls;
    private View controlsContainer;
    /* access modifiers changed from: private */
    public View endCallButton;
    private View fullscreenBackgroundView;
    private View greenScreenBackgroundView;
    private boolean hasInitializedScreenModes;
    private InCallButtonUiDelegate inCallButtonUiDelegate;
    private InCallScreenDelegate inCallScreenDelegate;
    private boolean isInFullscreenMode;
    private boolean isInGreenScreenMode;
    private boolean isRemotelyHeld;
    private CheckableImageButton muteButton;
    private View mutePreviewOverlay;
    private View onHoldContainer;
    private View previewOffOverlay;
    private FrameLayout previewRoot;
    private SurfaceView previewSurfaceView;
    /* access modifiers changed from: private */
    public SurfaceView remoteSurfaceView;
    /* access modifiers changed from: private */
    public TextView remoteVideoOff;
    private SecondaryInfo savedSecondaryInfo;
    private boolean shouldShowPreview;
    private boolean shouldShowRemote;
    private CheckableImageButton speakerButton;
    private SpeakerButtonController speakerButtonController;
    private ImageButton swapCameraButton;
    private View switchOnHoldButton;
    /* access modifiers changed from: private */
    public SwitchOnHoldCallController switchOnHoldCallController;
    /* access modifiers changed from: private */
    public VideoCallScreenDelegate videoCallScreenDelegate;

    private static void animateSetVisibility(final View view, final int i) {
        int i2;
        if (view.getVisibility() != i) {
            int i3 = 1;
            if (i == 8) {
                i2 = 0;
            } else if (i == 0) {
                i2 = 1;
                i3 = 0;
            } else {
                Assert.fail();
                throw null;
            }
            view.setAlpha((float) i3);
            view.setVisibility(0);
            view.animate().alpha((float) i2).withEndAction(new Runnable() {
                public void run() {
                    view.setVisibility(i);
                }
            }).start();
        }
    }

    /* access modifiers changed from: private */
    public void checkCameraPermission() {
        if (!R$style.hasCameraPermissionAndShownPrivacyToast(getContext())) {
            ((VideoCallPresenter) this.videoCallScreenDelegate).onCameraPermissionDialogShown();
            if (!R$style.hasCameraPermission(getContext())) {
                requestPermissions(new String[]{"android.permission.CAMERA"}, 1);
                return;
            }
            PermissionsUtil.showCameraPermissionToast(getContext());
            ((VideoCallPresenter) this.videoCallScreenDelegate).onCameraPermissionGranted();
        }
    }

    private View[] getAllPreviewRelatedViews() {
        return new View[]{this.previewRoot, this.mutePreviewOverlay};
    }

    private int getOffsetEnd(View view) {
        int marginEnd = ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).getMarginEnd() + view.getWidth();
        return view.getLayoutDirection() == 1 ? -marginEnd : marginEnd;
    }

    private int getOffsetTop(View view) {
        return -(view.getHeight() + ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).topMargin);
    }

    private boolean isLandscape() {
        int rotation = getActivity().getWindowManager().getDefaultDisplay().getRotation();
        return rotation == 1 || rotation == 3;
    }

    public static SurfaceViewVideoCallFragment newInstance(String str) {
        Bundle bundle = new Bundle();
        Assert.isNotNull(str);
        bundle.putString(ARG_CALL_ID, str);
        SurfaceViewVideoCallFragment surfaceViewVideoCallFragment = new SurfaceViewVideoCallFragment();
        surfaceViewVideoCallFragment.setArguments(bundle);
        return surfaceViewVideoCallFragment;
    }

    private void updateMutePreviewOverlayVisibility() {
        this.mutePreviewOverlay.setVisibility((!this.muteButton.isChecked() || this.isInGreenScreenMode) ? 8 : 0);
    }

    private void updateOverlayBackground() {
        if (this.isInGreenScreenMode) {
            animateSetVisibility(this.greenScreenBackgroundView, 0);
            animateSetVisibility(this.fullscreenBackgroundView, 8);
        } else if (!this.isInFullscreenMode) {
            animateSetVisibility(this.greenScreenBackgroundView, 8);
            animateSetVisibility(this.fullscreenBackgroundView, 0);
        } else {
            animateSetVisibility(this.greenScreenBackgroundView, 8);
            animateSetVisibility(this.fullscreenBackgroundView, 8);
        }
    }

    /* access modifiers changed from: private */
    public void updateVideoOffViews() {
        boolean z = true;
        this.previewOffOverlay.setVisibility(this.isInGreenScreenMode || this.shouldShowPreview ? 8 : 0);
        if (!(this.isInGreenScreenMode || this.shouldShowRemote) || this.isRemotelyHeld) {
            z = false;
        }
        int i = R.string.videocall_remote_video_off;
        if (z) {
            this.remoteVideoOff.setText(TextUtils.equals(this.remoteVideoOff.getText(), this.remoteVideoOff.getResources().getString(R.string.videocall_remote_video_off)) ? R.string.videocall_remote_video_on : R.string.videocall_remotely_resumed);
            this.remoteVideoOff.postDelayed(new Runnable() {
                public void run() {
                    SurfaceViewVideoCallFragment.this.remoteVideoOff.setVisibility(8);
                }
            }, 2000);
            return;
        }
        TextView textView = this.remoteVideoOff;
        if (this.isRemotelyHeld) {
            i = R.string.videocall_remotely_held;
        }
        textView.setText(i);
        this.remoteVideoOff.setVisibility(0);
    }

    public void dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        this.contactGridManager.dispatchPopulateAccessibilityEvent(accessibilityEvent);
    }

    public void enableButton(int i, boolean z) {
        Object[] objArr = {R$style.toString2(i), Boolean.valueOf(z)};
        if (i == 0) {
            this.speakerButtonController.setEnabled(z);
        } else if (i == 1) {
            this.muteButton.setEnabled(z);
        } else if (i == 10) {
            this.cameraOffButton.setEnabled(z);
        } else if (i == 13) {
            this.switchOnHoldCallController.setEnabled(z);
        }
    }

    public int getAnswerAndDialpadContainerResourceId() {
        return 0;
    }

    public String getCallId() {
        String string = getArguments().getString(ARG_CALL_ID);
        Assert.isNotNull(string);
        return string;
    }

    public Fragment getInCallButtonUiFragment() {
        return this;
    }

    public Fragment getInCallScreenFragment() {
        return this;
    }

    public Fragment getVideoCallScreenFragment() {
        return this;
    }

    public boolean isManageConferenceVisible() {
        LogUtil.m9i("SurfaceViewVideoCallFragment.isManageConferenceVisible", (String) null, new Object[0]);
        return false;
    }

    public /* synthetic */ void lambda$onCreateView$0$SurfaceViewVideoCallFragment(View view) {
        ((VideoCallPresenter) this.videoCallScreenDelegate).resetAutoFullscreenTimer();
        if (this.isInFullscreenMode) {
            updateFullscreenAndGreenScreenMode(false, false);
        } else {
            updateFullscreenAndGreenScreenMode(true, false);
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        SecondaryInfo secondaryInfo = this.savedSecondaryInfo;
        if (secondaryInfo != null) {
            LogUtil.m9i("SurfaceViewVideoCallFragment.setSecondary", secondaryInfo.toString(), new Object[0]);
            if (!isAdded()) {
                this.savedSecondaryInfo = secondaryInfo;
                return;
            }
            this.savedSecondaryInfo = null;
            this.switchOnHoldCallController.setSecondaryInfo(secondaryInfo);
            LogUtil.m9i("SurfaceViewVideoCallFragment.updateButtonState", (String) null, new Object[0]);
            this.speakerButtonController.updateButtonState();
            this.switchOnHoldCallController.updateButtonState();
            FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
            Fragment findFragmentById = getChildFragmentManager().findFragmentById(R.id.videocall_on_hold_banner);
            if (secondaryInfo.shouldShow()) {
                OnHoldFragment newInstance = OnHoldFragment.newInstance(secondaryInfo);
                newInstance.setPadTopInset(!this.isInFullscreenMode);
                beginTransaction.replace(R.id.videocall_on_hold_banner, newInstance);
            } else if (findFragmentById != null) {
                beginTransaction.remove(findFragmentById);
            }
            beginTransaction.setCustomAnimations(R.anim.abc_slide_in_top, R.anim.abc_slide_out_top);
            beginTransaction.commitAllowingStateLoss();
        }
    }

    public void onAudioRouteSelected(int i) {
        LogUtil.m9i("SurfaceViewVideoCallFragment.onAudioRouteSelected", GeneratedOutlineSupport.outline5("audioRoute: ", i), new Object[0]);
        this.inCallButtonUiDelegate.setAudioRoute(i);
    }

    public void onAudioRouteSelectorDismiss() {
    }

    public void onCheckedChanged(CheckableImageButton checkableImageButton, boolean z) {
        if (checkableImageButton == this.cameraOffButton) {
            if (z || R$style.hasCameraPermissionAndShownPrivacyToast(getContext())) {
                this.inCallButtonUiDelegate.pauseVideoClicked(z);
                ((VideoCallPresenter) this.videoCallScreenDelegate).resetAutoFullscreenTimer();
                return;
            }
            LogUtil.m9i("SurfaceViewVideoCallFragment.onCheckedChanged", "show camera permission dialog", new Object[0]);
            checkCameraPermission();
        } else if (checkableImageButton == this.muteButton) {
            this.inCallButtonUiDelegate.muteClicked(z, true);
            ((VideoCallPresenter) this.videoCallScreenDelegate).resetAutoFullscreenTimer();
        }
    }

    public void onClick(View view) {
        if (view == this.endCallButton) {
            LogUtil.m9i("SurfaceViewVideoCallFragment.onClick", "end call button clicked", new Object[0]);
            this.inCallButtonUiDelegate.onEndCallClicked();
            ((VideoCallPresenter) this.videoCallScreenDelegate).resetAutoFullscreenTimer();
            return;
        }
        ImageButton imageButton = this.swapCameraButton;
        if (view == imageButton) {
            if (imageButton.getDrawable() instanceof Animatable) {
                ((Animatable) this.swapCameraButton.getDrawable()).start();
            }
            this.inCallButtonUiDelegate.toggleCameraClicked();
            ((VideoCallPresenter) this.videoCallScreenDelegate).resetAutoFullscreenTimer();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.m9i("SurfaceViewVideoCallFragment.onCreate", (String) null, new Object[0]);
        this.inCallButtonUiDelegate = ((InCallButtonUiDelegateFactory) FragmentUtils.getParent((Fragment) this, InCallButtonUiDelegateFactory.class)).newInCallButtonUiDelegate();
        if (bundle != null) {
            this.inCallButtonUiDelegate.onRestoreInstanceState(bundle);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i = 0;
        LogUtil.m9i("SurfaceViewVideoCallFragment.onCreateView", (String) null, new Object[0]);
        View inflate = layoutInflater.inflate(R.layout.frag_videocall_surfaceview, viewGroup, false);
        this.contactGridManager = new ContactGridManager(inflate, (ImageView) null, 0, false);
        this.controls = inflate.findViewById(R.id.videocall_video_controls);
        this.controls.setVisibility(getActivity().isInMultiWindowMode() ? 8 : 0);
        this.controlsContainer = inflate.findViewById(R.id.videocall_video_controls_container);
        this.speakerButton = (CheckableImageButton) inflate.findViewById(R.id.videocall_speaker_button);
        this.muteButton = (CheckableImageButton) inflate.findViewById(R.id.videocall_mute_button);
        this.muteButton.setOnCheckedChangeListener(this);
        this.mutePreviewOverlay = inflate.findViewById(R.id.videocall_video_preview_mute_overlay);
        this.cameraOffButton = (CheckableImageButton) inflate.findViewById(R.id.videocall_mute_video);
        this.cameraOffButton.setOnCheckedChangeListener(this);
        this.previewOffOverlay = inflate.findViewById(R.id.videocall_video_preview_off_overlay);
        this.swapCameraButton = (ImageButton) inflate.findViewById(R.id.videocall_switch_video);
        this.swapCameraButton.setOnClickListener(this);
        View findViewById = inflate.findViewById(R.id.videocall_switch_controls);
        if (getActivity().isInMultiWindowMode()) {
            i = 8;
        }
        findViewById.setVisibility(i);
        this.switchOnHoldButton = inflate.findViewById(R.id.videocall_switch_on_hold);
        this.onHoldContainer = inflate.findViewById(R.id.videocall_on_hold_banner);
        this.remoteVideoOff = (TextView) inflate.findViewById(R.id.videocall_remote_video_off);
        this.remoteVideoOff.setAccessibilityLiveRegion(1);
        this.endCallButton = inflate.findViewById(R.id.videocall_end_call);
        this.endCallButton.setOnClickListener(this);
        this.previewSurfaceView = (SurfaceView) inflate.findViewById(R.id.videocall_video_preview);
        this.previewSurfaceView.setZOrderMediaOverlay(true);
        this.previewOffOverlay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SurfaceViewVideoCallFragment.this.checkCameraPermission();
            }
        });
        this.remoteSurfaceView = (SurfaceView) inflate.findViewById(R.id.videocall_video_remote);
        this.remoteSurfaceView.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                SurfaceViewVideoCallFragment.this.lambda$onCreateView$0$SurfaceViewVideoCallFragment(view);
            }
        });
        this.greenScreenBackgroundView = inflate.findViewById(R.id.videocall_green_screen_background);
        this.fullscreenBackgroundView = inflate.findViewById(R.id.videocall_fullscreen_background);
        this.previewRoot = (FrameLayout) inflate.findViewById(R.id.videocall_preview_root);
        this.remoteSurfaceView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                LogUtil.m9i("SurfaceViewVideoCallFragment.onGlobalLayout", (String) null, new Object[0]);
                SurfaceViewVideoCallFragment.this.updateVideoOffViews();
                ViewTreeObserver viewTreeObserver = SurfaceViewVideoCallFragment.this.remoteSurfaceView.getViewTreeObserver();
                if (viewTreeObserver.isAlive()) {
                    viewTreeObserver.removeOnGlobalLayoutListener(this);
                }
            }
        });
        return inflate;
    }

    public void onDestroyView() {
        super.onDestroyView();
        LogUtil.m9i("SurfaceViewVideoCallFragment.onDestroyView", (String) null, new Object[0]);
        this.inCallButtonUiDelegate.onInCallButtonUiUnready();
        this.inCallScreenDelegate.onInCallScreenUnready();
    }

    public void onHandoverFromWiFiToLte() {
    }

    public void onInCallScreenDialpadVisibilityChange(boolean z) {
        LogUtil.m9i("SurfaceViewVideoCallFragment.onInCallScreenDialpadVisibilityChange", (String) null, new Object[0]);
    }

    public void onLocalVideoDimensionsChanged() {
        LogUtil.m9i("SurfaceViewVideoCallFragment.onLocalVideoDimensionsChanged", (String) null, new Object[0]);
    }

    public void onLocalVideoOrientationChanged() {
        LogUtil.m9i("SurfaceViewVideoCallFragment.onLocalVideoOrientationChanged", (String) null, new Object[0]);
    }

    public void onPause() {
        super.onPause();
        LogUtil.m9i("SurfaceViewVideoCallFragment.onPause", (String) null, new Object[0]);
        this.inCallScreenDelegate.onInCallScreenPaused();
    }

    public void onRemoteVideoDimensionsChanged() {
        LogUtil.m9i("SurfaceViewVideoCallFragment.onRemoteVideoDimensionsChanged", (String) null, new Object[0]);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i != 1) {
            return;
        }
        if (iArr.length <= 0 || iArr[0] != 0) {
            LogUtil.m9i("SurfaceViewVideoCallFragment.onRequestPermissionsResult", "Camera permission denied.", new Object[0]);
            return;
        }
        LogUtil.m9i("SurfaceViewVideoCallFragment.onRequestPermissionsResult", "Camera permission granted.", new Object[0]);
        ((VideoCallPresenter) this.videoCallScreenDelegate).onCameraPermissionGranted();
    }

    public void onResume() {
        super.onResume();
        LogUtil.m9i("SurfaceViewVideoCallFragment.onResume", (String) null, new Object[0]);
        this.inCallScreenDelegate.onInCallScreenResumed();
    }

    public void onSaveInstanceState(Bundle bundle) {
        this.inCallButtonUiDelegate.onSaveInstanceState(bundle);
    }

    public void onStart() {
        super.onStart();
        LogUtil.m9i("SurfaceViewVideoCallFragment.onStart", (String) null, new Object[0]);
        ((VideoCallPresenter) this.videoCallScreenDelegate).onVideoCallScreenUiReady();
        getView().postDelayed(this.cameraPermissionDialogRunnable, 2000);
    }

    public void onStop() {
        super.onStop();
        LogUtil.m9i("SurfaceViewVideoCallFragment.onStop", (String) null, new Object[0]);
        getView().removeCallbacks(this.cameraPermissionDialogRunnable);
        ((VideoCallPresenter) this.videoCallScreenDelegate).onVideoCallScreenUiUnready();
    }

    public void onSystemUiVisibilityChange(int i) {
        boolean z = (i & 2) == 0;
        ((VideoCallPresenter) this.videoCallScreenDelegate).onSystemUiVisibilityChange(z);
        if (z) {
            updateFullscreenAndGreenScreenMode(false, false);
        } else {
            updateFullscreenAndGreenScreenMode(true, false);
        }
    }

    public void onVideoScreenStart() {
        ((VideoCallPresenter) this.videoCallScreenDelegate).onVideoCallScreenUiReady();
        getView().postDelayed(this.cameraPermissionDialogRunnable, 2000);
    }

    public void onVideoScreenStop() {
        getView().removeCallbacks(this.cameraPermissionDialogRunnable);
        ((VideoCallPresenter) this.videoCallScreenDelegate).onVideoCallScreenUiUnready();
    }

    public void onViewCreated(View view, Bundle bundle) {
        LogUtil.m9i("SurfaceViewVideoCallFragment.onViewCreated", (String) null, new Object[0]);
        this.inCallScreenDelegate = ((InCallScreenDelegateFactory) FragmentUtils.getParentUnsafe((Fragment) this, InCallScreenDelegateFactory.class)).newInCallScreenDelegate();
        this.videoCallScreenDelegate = ((VideoCallScreenDelegateFactory) FragmentUtils.getParentUnsafe((Fragment) this, VideoCallScreenDelegateFactory.class)).newVideoCallScreenDelegate(this);
        this.speakerButtonController = new SpeakerButtonController(this.speakerButton, this.inCallButtonUiDelegate, this.videoCallScreenDelegate);
        this.switchOnHoldCallController = new SwitchOnHoldCallController(this.switchOnHoldButton, this.onHoldContainer, this.inCallScreenDelegate, this.videoCallScreenDelegate);
        ((VideoCallPresenter) this.videoCallScreenDelegate).initVideoCallScreenDelegate(getContext(), this);
        this.inCallScreenDelegate.onInCallScreenDelegateInit(this);
        this.inCallScreenDelegate.onInCallScreenReady();
        this.inCallButtonUiDelegate.onInCallButtonUiReady(this);
        view.setOnSystemUiVisibilityChangeListener(this);
    }

    public void requestCallRecordingPermissions(String[] strArr) {
    }

    public void setAudioState(CallAudioState callAudioState) {
        LogUtil.m9i("SurfaceViewVideoCallFragment.setAudioState", GeneratedOutlineSupport.outline6("audioState: ", callAudioState), new Object[0]);
        this.speakerButtonController.setAudioState(callAudioState);
        this.muteButton.setChecked(callAudioState.isMuted());
        updateMutePreviewOverlayVisibility();
    }

    public void setCallRecordingDuration(long j) {
    }

    public void setCallRecordingState(boolean z) {
    }

    public void setCallState(PrimaryCallState primaryCallState) {
        LogUtil.m9i("SurfaceViewVideoCallFragment.setCallState", primaryCallState.toString(), new Object[0]);
        this.contactGridManager.setCallState(primaryCallState);
    }

    public void setCameraSwitched(boolean z) {
        LogUtil.m9i("SurfaceViewVideoCallFragment.setCameraSwitched", GeneratedOutlineSupport.outline10("isBackFacingCamera: ", z), new Object[0]);
    }

    public void setEnabled(boolean z) {
        "enabled: " + z;
        this.speakerButtonController.setEnabled(z);
        this.muteButton.setEnabled(z);
        this.cameraOffButton.setEnabled(z);
        this.switchOnHoldCallController.setEnabled(z);
    }

    public void setEndCallButtonEnabled(boolean z, boolean z2) {
        LogUtil.m9i("SurfaceViewVideoCallFragment.setEndCallButtonEnabled", GeneratedOutlineSupport.outline10("enabled: ", z), new Object[0]);
    }

    public void setHold(boolean z) {
        LogUtil.m9i("SurfaceViewVideoCallFragment.setHold", GeneratedOutlineSupport.outline10("value: ", z), new Object[0]);
    }

    public void setPrimary(PrimaryInfo primaryInfo) {
        LogUtil.m9i("SurfaceViewVideoCallFragment.setPrimary", primaryInfo.toString(), new Object[0]);
        this.contactGridManager.setPrimary(primaryInfo);
    }

    public void setSecondary(SecondaryInfo secondaryInfo) {
        LogUtil.m9i("SurfaceViewVideoCallFragment.setSecondary", secondaryInfo.toString(), new Object[0]);
        if (!isAdded()) {
            this.savedSecondaryInfo = secondaryInfo;
            return;
        }
        this.savedSecondaryInfo = null;
        this.switchOnHoldCallController.setSecondaryInfo(secondaryInfo);
        LogUtil.m9i("SurfaceViewVideoCallFragment.updateButtonState", (String) null, new Object[0]);
        this.speakerButtonController.updateButtonState();
        this.switchOnHoldCallController.updateButtonState();
        FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
        Fragment findFragmentById = getChildFragmentManager().findFragmentById(R.id.videocall_on_hold_banner);
        if (secondaryInfo.shouldShow()) {
            OnHoldFragment newInstance = OnHoldFragment.newInstance(secondaryInfo);
            newInstance.setPadTopInset(!this.isInFullscreenMode);
            beginTransaction.replace(R.id.videocall_on_hold_banner, newInstance);
        } else if (findFragmentById != null) {
            beginTransaction.remove(findFragmentById);
        }
        beginTransaction.setCustomAnimations(R.anim.abc_slide_in_top, R.anim.abc_slide_out_top);
        beginTransaction.commitAllowingStateLoss();
    }

    public void setVideoPaused(boolean z) {
        LogUtil.m9i("SurfaceViewVideoCallFragment.setVideoPaused", GeneratedOutlineSupport.outline10("isPaused: ", z), new Object[0]);
        this.cameraOffButton.setChecked(z);
    }

    public void showAudioRouteSelector() {
        LogUtil.m9i("SurfaceViewVideoCallFragment.showAudioRouteSelector", (String) null, new Object[0]);
        AudioRouteSelectorDialogFragment.newInstance(this.inCallButtonUiDelegate.getCurrentAudioState()).show(getChildFragmentManager(), (String) null);
    }

    public void showButton(int i, boolean z) {
        Object[] objArr = {R$style.toString2(i), Boolean.valueOf(z)};
        if (i == 0) {
            this.speakerButtonController.setEnabled(z);
        } else if (i == 1) {
            this.muteButton.setEnabled(z);
        } else if (i == 10) {
            this.cameraOffButton.setEnabled(z);
        } else if (i == 13) {
            this.switchOnHoldCallController.setVisible(z);
        } else if (i == 6) {
            this.swapCameraButton.setEnabled(z);
        }
    }

    public void showLocationUi(Fragment fragment) {
        LogUtil.m8e("SurfaceViewVideoCallFragment.showLocationUi", "Emergency video calling not supported", new Object[0]);
    }

    public void showManageConferenceCallButton(boolean z) {
        LogUtil.m9i("SurfaceViewVideoCallFragment.showManageConferenceCallButton", GeneratedOutlineSupport.outline10("visible: ", z), new Object[0]);
    }

    public void showNoteSentToast() {
        LogUtil.m9i("SurfaceViewVideoCallFragment.showNoteSentToast", (String) null, new Object[0]);
    }

    public void showVideoViews(boolean z, boolean z2, boolean z3) {
        int i = 0;
        LogUtil.m9i("SurfaceViewVideoCallFragment.showVideoViews", "showPreview: %b, shouldShowRemote: %b", Boolean.valueOf(z), Boolean.valueOf(z2));
        this.shouldShowPreview = z;
        this.shouldShowRemote = z2;
        this.isRemotelyHeld = z3;
        SurfaceView surfaceView = this.previewSurfaceView;
        if (!z) {
            i = 4;
        }
        surfaceView.setVisibility(i);
        ((VideoCallPresenter) this.videoCallScreenDelegate).setSurfaceViews(this.previewSurfaceView, this.remoteSurfaceView);
        throw null;
    }

    public void updateButtonStates() {
        LogUtil.m9i("SurfaceViewVideoCallFragment.updateButtonState", (String) null, new Object[0]);
        this.speakerButtonController.updateButtonState();
        this.switchOnHoldCallController.updateButtonState();
    }

    public void updateFullscreenAndGreenScreenMode(boolean z, boolean z2) {
        Point point;
        int i;
        Point point2;
        Point point3;
        Point point4;
        int i2 = 0;
        LogUtil.m9i("SurfaceViewVideoCallFragment.updateFullscreenAndGreenScreenMode", "shouldShowFullscreen: %b, shouldShowGreenScreen: %b", Boolean.valueOf(z), Boolean.valueOf(z2));
        if (getActivity() == null) {
            LogUtil.m9i("SurfaceViewVideoCallFragment.updateFullscreenAndGreenScreenMode", "not attached to activity", new Object[0]);
        } else if (this.hasInitializedScreenModes && z2 == this.isInGreenScreenMode && z == this.isInFullscreenMode) {
            LogUtil.m9i("SurfaceViewVideoCallFragment.updateFullscreenAndGreenScreenMode", "no change to screen modes", new Object[0]);
        } else {
            this.hasInitializedScreenModes = true;
            this.isInGreenScreenMode = z2;
            this.isInFullscreenMode = z;
            if (getView().isAttachedToWindow() && !getActivity().isInMultiWindowMode()) {
                this.controlsContainer.onApplyWindowInsets(getView().getRootWindowInsets());
            }
            if (z2) {
                LogUtil.m9i("SurfaceViewVideoCallFragment.enterGreenScreenMode", (String) null, new Object[0]);
                updateOverlayBackground();
                this.contactGridManager.setIsMiddleRowVisible(true);
                updateMutePreviewOverlayVisibility();
            } else {
                LogUtil.m9i("SurfaceViewVideoCallFragment.exitGreenScreenMode", (String) null, new Object[0]);
                updateOverlayBackground();
                this.contactGridManager.setIsMiddleRowVisible(false);
                updateMutePreviewOverlayVisibility();
            }
            if (z) {
                LogUtil.m9i("SurfaceViewVideoCallFragment.enterFullscreenMode", (String) null, new Object[0]);
                View view = getView();
                if (view != null) {
                    view.setSystemUiVisibility(262);
                }
                FastOutLinearInInterpolator fastOutLinearInInterpolator = new FastOutLinearInInterpolator();
                View view2 = this.controls;
                if (isLandscape()) {
                    point2 = new Point(0, view2.getHeight() + ((ViewGroup.MarginLayoutParams) view2.getLayoutParams()).bottomMargin);
                } else {
                    int marginStart = ((ViewGroup.MarginLayoutParams) view2.getLayoutParams()).getMarginStart() + view2.getWidth();
                    if (view2.getLayoutDirection() == 1) {
                        marginStart = -marginStart;
                    }
                    point2 = new Point(-marginStart, 0);
                }
                this.controls.animate().translationX((float) point2.x).translationY((float) point2.y).setInterpolator(fastOutLinearInInterpolator).alpha(0.0f).start();
                View view3 = this.switchOnHoldButton;
                if (isLandscape()) {
                    point3 = new Point(0, getOffsetTop(view3));
                } else {
                    point3 = new Point(getOffsetEnd(view3), 0);
                }
                this.switchOnHoldButton.animate().translationX((float) point3.x).translationY((float) point3.y).setInterpolator(fastOutLinearInInterpolator).alpha(0.0f);
                View containerView = this.contactGridManager.getContainerView();
                Point point5 = new Point(0, getOffsetTop(containerView));
                containerView.animate().translationX((float) point5.x).translationY((float) point5.y).setInterpolator(fastOutLinearInInterpolator).alpha(0.0f);
                View view4 = this.endCallButton;
                if (isLandscape()) {
                    point4 = new Point(getOffsetEnd(view4), 0);
                } else {
                    point4 = new Point(0, ((ViewGroup.MarginLayoutParams) view4.getLayoutParams()).bottomMargin);
                }
                this.endCallButton.animate().translationX((float) point4.x).translationY((float) point4.y).setInterpolator(fastOutLinearInInterpolator).alpha(0.0f).withEndAction(new Runnable() {
                    public void run() {
                        SurfaceViewVideoCallFragment.this.endCallButton.setVisibility(4);
                    }
                }).setInterpolator(new FastOutLinearInInterpolator()).start();
                if (!this.isInGreenScreenMode) {
                    View[] allPreviewRelatedViews = getAllPreviewRelatedViews();
                    int length = allPreviewRelatedViews.length;
                    while (i2 < length) {
                        allPreviewRelatedViews[i2].animate().translationX(0.0f).translationY(0.0f).setInterpolator(new AccelerateDecelerateInterpolator()).start();
                        i2++;
                    }
                }
                updateOverlayBackground();
            } else {
                LogUtil.m9i("SurfaceViewVideoCallFragment.exitFullscreenMode", (String) null, new Object[0]);
                if (!getView().isAttachedToWindow()) {
                    LogUtil.m9i("SurfaceViewVideoCallFragment.exitFullscreenMode", "not attached", new Object[0]);
                } else {
                    View view5 = getView();
                    if (view5 != null) {
                        view5.setSystemUiVisibility(256);
                    }
                    LinearOutSlowInInterpolator linearOutSlowInInterpolator = new LinearOutSlowInInterpolator();
                    this.controls.animate().translationX(0.0f).translationY(0.0f).setInterpolator(linearOutSlowInInterpolator).alpha(1.0f).start();
                    this.switchOnHoldButton.animate().translationX(0.0f).translationY(0.0f).setInterpolator(linearOutSlowInInterpolator).alpha(1.0f).withStartAction(new Runnable() {
                        public void run() {
                            SurfaceViewVideoCallFragment.this.switchOnHoldCallController.setOnScreen();
                        }
                    });
                    this.contactGridManager.getContainerView().animate().translationX(0.0f).translationY(0.0f).setInterpolator(linearOutSlowInInterpolator).alpha(1.0f).withStartAction(new Runnable() {
                        public void run() {
                            SurfaceViewVideoCallFragment.this.contactGridManager.show();
                        }
                    });
                    this.endCallButton.animate().translationX(0.0f).translationY(0.0f).setInterpolator(linearOutSlowInInterpolator).alpha(1.0f).withStartAction(new Runnable() {
                        public void run() {
                            SurfaceViewVideoCallFragment.this.endCallButton.setVisibility(0);
                        }
                    }).start();
                    if (!this.isInGreenScreenMode) {
                        if (getActivity().isInMultiWindowMode()) {
                            point = new Point();
                        } else if (isLandscape()) {
                            if (getView().getLayoutDirection() == 1) {
                                i = getView().getRootWindowInsets().getStableInsetLeft();
                            } else {
                                i = -getView().getRootWindowInsets().getStableInsetRight();
                            }
                            point = new Point(i, 0);
                        } else {
                            point = new Point(0, -getView().getRootWindowInsets().getStableInsetBottom());
                        }
                        View[] allPreviewRelatedViews2 = getAllPreviewRelatedViews();
                        int length2 = allPreviewRelatedViews2.length;
                        while (i2 < length2) {
                            allPreviewRelatedViews2[i2].animate().translationX((float) point.x).translationY((float) point.y).setInterpolator(new AccelerateDecelerateInterpolator()).start();
                            i2++;
                        }
                    }
                    updateOverlayBackground();
                }
            }
            updateVideoOffViews();
            OnHoldFragment onHoldFragment = (OnHoldFragment) getChildFragmentManager().findFragmentById(R.id.videocall_on_hold_banner);
            if (onHoldFragment != null) {
                onHoldFragment.setPadTopInset(!this.isInFullscreenMode);
            }
        }
    }

    public void updateInCallButtonUiColors(int i) {
    }

    public void updateInCallScreenColors() {
        LogUtil.m9i("SurfaceViewVideoCallFragment.updateColors", (String) null, new Object[0]);
    }
}
