package com.android.incallui.video.impl;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Point;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.os.SystemClock;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentTransaction;
import android.support.p000v4.view.animation.FastOutLinearInInterpolator;
import android.support.p000v4.view.animation.LinearOutSlowInInterpolator;
import android.support.p002v7.appcompat.R$style;
import android.telecom.CallAudioState;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import com.android.incallui.videosurface.impl.VideoSurfaceTextureImpl;
import com.android.tools.p006r8.GeneratedOutlineSupport;

public class VideoCallFragment extends Fragment implements InCallScreen, InCallButtonUi, VideoCallScreen, View.OnClickListener, CheckableImageButton.OnCheckedChangeListener, AudioRouteSelectorDialogFragment.AudioRouteSelectorPresenter, View.OnSystemUiVisibilityChangeListener {
    static final String ARG_CALL_ID = "call_id";
    static final float BLUR_PREVIEW_RADIUS = 16.0f;
    static final float BLUR_PREVIEW_SCALE_FACTOR = 1.0f;
    private CheckableImageButton cameraOffButton;
    private final Runnable cameraPermissionDialogRunnable = new Runnable() {
        public void run() {
            if (((VideoCallPresenter) VideoCallFragment.this.videoCallScreenDelegate).shouldShowCameraPermissionToast()) {
                LogUtil.m9i("VideoCallFragment.cameraPermissionDialogRunnable", "showing dialog", new Object[0]);
                VideoCallFragment.this.checkCameraPermission();
            }
        }
    };
    private final ViewOutlineProvider circleOutlineProvider = new ViewOutlineProvider(this) {
        public void getOutline(View view, Outline outline) {
            int width = view.getWidth() / 2;
            int height = view.getHeight() / 2;
            int min = Math.min(width, height);
            outline.setOval(width - min, height - min, width + min, height + min);
        }
    };
    /* access modifiers changed from: private */
    public ContactGridManager contactGridManager;
    /* access modifiers changed from: private */
    public View controls;
    private View controlsContainer;
    /* access modifiers changed from: private */
    public View endCallButton;
    private View fullscreenBackgroundView;
    private View greenScreenBackgroundView;
    private boolean hasInitializedScreenModes;
    private InCallButtonUiDelegate inCallButtonUiDelegate;
    private InCallScreenDelegate inCallScreenDelegate;
    /* access modifiers changed from: private */
    public boolean isInFullscreenMode;
    private boolean isInGreenScreenMode;
    private boolean isRemotelyHeld;
    private CheckableImageButton muteButton;
    private View mutePreviewOverlay;
    private View onHoldContainer;
    private ImageView previewOffBlurredImageView;
    private View previewOffOverlay;
    private TextureView previewTextureView;
    private ImageView remoteOffBlurredImageView;
    private TextureView remoteTextureView;
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
    private final Runnable videoChargesAlertDialogRunnable = new Runnable() {
        public final void run() {
            VideoCallFragment.this.lambda$new$0$VideoCallFragment();
        }
    };

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

    /* access modifiers changed from: private */
    public void enterFullscreenMode() {
        Point point;
        Point point2;
        Point point3;
        LogUtil.m9i("VideoCallFragment.enterFullscreenMode", (String) null, new Object[0]);
        View view = getView();
        if (view != null) {
            view.setSystemUiVisibility(262);
        }
        FastOutLinearInInterpolator fastOutLinearInInterpolator = new FastOutLinearInInterpolator();
        View view2 = this.controls;
        if (isLandscape()) {
            point = new Point(0, view2.getHeight() + ((ViewGroup.MarginLayoutParams) view2.getLayoutParams()).bottomMargin);
        } else {
            int marginStart = ((ViewGroup.MarginLayoutParams) view2.getLayoutParams()).getMarginStart() + view2.getWidth();
            if (view2.getLayoutDirection() == 1) {
                marginStart = -marginStart;
            }
            point = new Point(-marginStart, 0);
        }
        this.controls.animate().translationX((float) point.x).translationY((float) point.y).setInterpolator(fastOutLinearInInterpolator).alpha(0.0f).start();
        View view3 = this.switchOnHoldButton;
        if (isLandscape()) {
            point2 = new Point(0, getOffsetTop(view3));
        } else {
            point2 = new Point(getOffsetEnd(view3), 0);
        }
        this.switchOnHoldButton.animate().translationX((float) point2.x).translationY((float) point2.y).setInterpolator(fastOutLinearInInterpolator).alpha(0.0f);
        View containerView = this.contactGridManager.getContainerView();
        Point point4 = new Point(0, getOffsetTop(containerView));
        containerView.animate().translationX((float) point4.x).translationY((float) point4.y).setInterpolator(fastOutLinearInInterpolator).alpha(0.0f);
        View view4 = this.endCallButton;
        if (isLandscape()) {
            point3 = new Point(getOffsetEnd(view4), 0);
        } else {
            point3 = new Point(0, ((ViewGroup.MarginLayoutParams) view4.getLayoutParams()).bottomMargin);
        }
        this.endCallButton.animate().translationX((float) point3.x).translationY((float) point3.y).setInterpolator(fastOutLinearInInterpolator).alpha(0.0f).withEndAction(new Runnable() {
            public void run() {
                VideoCallFragment.this.endCallButton.setVisibility(4);
            }
        }).setInterpolator(new FastOutLinearInInterpolator()).start();
        if (!this.isInGreenScreenMode) {
            for (View animate : getAllPreviewRelatedViews()) {
                animate.animate().translationX(0.0f).translationY(0.0f).setInterpolator(new AccelerateDecelerateInterpolator()).start();
            }
        }
        updateOverlayBackground();
    }

    private View[] getAllPreviewRelatedViews() {
        return new View[]{this.previewTextureView, this.previewOffOverlay, this.previewOffBlurredImageView, this.mutePreviewOverlay};
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

    public static VideoCallFragment newInstance(String str) {
        Bundle bundle = new Bundle();
        Assert.isNotNull(str);
        bundle.putString(ARG_CALL_ID, str);
        VideoCallFragment videoCallFragment = new VideoCallFragment();
        videoCallFragment.setArguments(bundle);
        return videoCallFragment;
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
    public void updatePreviewOffView() {
        LogUtil.enterBlock("VideoCallFragment.updatePreviewOffView");
        int i = 0;
        boolean z = this.isInGreenScreenMode || this.shouldShowPreview;
        View view = this.previewOffOverlay;
        if (z) {
            i = 8;
        }
        view.setVisibility(i);
        updateBlurredImageView(this.previewTextureView, this.previewOffBlurredImageView, this.shouldShowPreview, BLUR_PREVIEW_RADIUS, BLUR_PREVIEW_SCALE_FACTOR);
    }

    /* access modifiers changed from: private */
    public void updatePreviewVideoScaling() {
        if (this.previewTextureView.getWidth() == 0 || this.previewTextureView.getHeight() == 0) {
            LogUtil.m9i("VideoCallFragment.updatePreviewVideoScaling", "view layout hasn't finished yet", new Object[0]);
            return;
        }
        Point surfaceDimensions = ((VideoSurfaceTextureImpl) ((VideoCallPresenter) this.videoCallScreenDelegate).getLocalVideoSurfaceTexture()).getSurfaceDimensions();
        if (surfaceDimensions == null) {
            LogUtil.m9i("VideoCallFragment.updatePreviewVideoScaling", "camera dimensions haven't been set", new Object[0]);
        } else if (isLandscape()) {
            R$style.scaleVideoAndFillView(this.previewTextureView, (float) surfaceDimensions.x, (float) surfaceDimensions.y, (float) ((VideoCallPresenter) this.videoCallScreenDelegate).getDeviceOrientation());
        } else {
            R$style.scaleVideoAndFillView(this.previewTextureView, (float) surfaceDimensions.y, (float) surfaceDimensions.x, (float) ((VideoCallPresenter) this.videoCallScreenDelegate).getDeviceOrientation());
        }
    }

    /* access modifiers changed from: private */
    public void updateRemoteOffView() {
        LogUtil.enterBlock("VideoCallFragment.updateRemoteOffView");
        boolean z = true;
        if (!(this.isInGreenScreenMode || this.shouldShowRemote) || this.isRemotelyHeld) {
            z = false;
        }
        int i = R.string.videocall_remote_video_off;
        if (z) {
            this.remoteVideoOff.setText(TextUtils.equals(this.remoteVideoOff.getText(), this.remoteVideoOff.getResources().getString(R.string.videocall_remote_video_off)) ? R.string.videocall_remote_video_on : R.string.videocall_remotely_resumed);
            this.remoteVideoOff.postDelayed(new Runnable() {
                public void run() {
                    VideoCallFragment.this.remoteVideoOff.setVisibility(8);
                }
            }, 2000);
        } else {
            TextView textView = this.remoteVideoOff;
            if (this.isRemotelyHeld) {
                i = R.string.videocall_remotely_held;
            }
            textView.setText(i);
            this.remoteVideoOff.setVisibility(0);
        }
        updateBlurredImageView(this.remoteTextureView, this.remoteOffBlurredImageView, this.shouldShowRemote, 25.0f, 0.25f);
    }

    /* access modifiers changed from: private */
    public void updateRemoteVideoScaling() {
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        Point sourceVideoDimensions = ((VideoSurfaceTextureImpl) ((VideoCallPresenter) this.videoCallScreenDelegate).getRemoteVideoSurfaceTexture()).getSourceVideoDimensions();
        if (sourceVideoDimensions == null) {
            LogUtil.m9i("VideoCallFragment.updateRemoteVideoScaling", "video size is null", new Object[0]);
        } else if (this.remoteTextureView.getWidth() == 0 || this.remoteTextureView.getHeight() == 0) {
            LogUtil.m9i("VideoCallFragment.updateRemoteVideoScaling", "view layout hasn't finished yet", new Object[0]);
        } else {
            float f6 = ((float) sourceVideoDimensions.x) / ((float) sourceVideoDimensions.y);
            float width = ((float) this.remoteTextureView.getWidth()) / ((float) this.remoteTextureView.getHeight());
            if (Math.abs(f6 - width) / (f6 + width) < 0.2f) {
                R$style.scaleVideoAndFillView(this.remoteTextureView, (float) sourceVideoDimensions.x, (float) sourceVideoDimensions.y, 0.0f);
                return;
            }
            TextureView textureView = this.remoteTextureView;
            int i = sourceVideoDimensions.x;
            int i2 = sourceVideoDimensions.y;
            int width2 = textureView.getWidth();
            int height = textureView.getHeight();
            float f7 = BLUR_PREVIEW_SCALE_FACTOR;
            if (width2 > height) {
                int i3 = height * i;
                int i4 = width2 * i2;
                if (i3 > i4) {
                    f5 = (float) (i4 / i);
                    f4 = (float) height;
                } else if (i3 < i4) {
                    f5 = (float) (i3 / i2);
                    f4 = (float) width2;
                }
                float f8 = f5 / f4;
                f = 1.0f;
                f7 = f8;
                LogUtil.m9i("VideoScale.scaleVideoMaintainingAspectRatio", "view: %d x %d, video: %d x %d scale: %f x %f", Integer.valueOf(width2), Integer.valueOf(height), Integer.valueOf(i), Integer.valueOf(i2), Float.valueOf(f7), Float.valueOf(f));
                Matrix matrix = new Matrix();
                matrix.setScale(f7, f, ((float) width2) / 2.0f, ((float) height) / 2.0f);
                textureView.setTransform(matrix);
            }
            int i5 = height * i;
            int i6 = width2 * i2;
            if (i5 > i6) {
                f3 = (float) (i6 / i);
                f2 = (float) height;
            } else if (i5 < i6) {
                f3 = (float) (i5 / i2);
                f2 = (float) width2;
            }
            f = f3 / f2;
            LogUtil.m9i("VideoScale.scaleVideoMaintainingAspectRatio", "view: %d x %d, video: %d x %d scale: %f x %f", Integer.valueOf(width2), Integer.valueOf(height), Integer.valueOf(i), Integer.valueOf(i2), Float.valueOf(f7), Float.valueOf(f));
            Matrix matrix2 = new Matrix();
            matrix2.setScale(f7, f, ((float) width2) / 2.0f, ((float) height) / 2.0f);
            textureView.setTransform(matrix2);
            f = 1.0f;
            LogUtil.m9i("VideoScale.scaleVideoMaintainingAspectRatio", "view: %d x %d, video: %d x %d scale: %f x %f", Integer.valueOf(width2), Integer.valueOf(height), Integer.valueOf(i), Integer.valueOf(i2), Float.valueOf(f7), Float.valueOf(f));
            Matrix matrix22 = new Matrix();
            matrix22.setScale(f7, f, ((float) width2) / 2.0f, ((float) height) / 2.0f);
            textureView.setTransform(matrix22);
        }
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
        LogUtil.m9i("VideoCallFragment.isManageConferenceVisible", (String) null, new Object[0]);
        return false;
    }

    public /* synthetic */ void lambda$new$0$VideoCallFragment() {
        if (((VideoChargesAlertDialogFragment) getChildFragmentManager().findFragmentByTag("tag_video_charges_alert")) != null) {
            LogUtil.m9i("VideoCallFragment.videoChargesAlertDialogRunnable", "already shown for this call", new Object[0]);
        } else if (VideoChargesAlertDialogFragment.shouldShow(getContext(), getCallId())) {
            LogUtil.m9i("VideoCallFragment.videoChargesAlertDialogRunnable", "showing dialog", new Object[0]);
            String callId = getCallId();
            VideoChargesAlertDialogFragment videoChargesAlertDialogFragment = new VideoChargesAlertDialogFragment();
            Bundle bundle = new Bundle();
            Assert.isNotNull(callId);
            bundle.putString(ARG_CALL_ID, callId);
            videoChargesAlertDialogFragment.setArguments(bundle);
            videoChargesAlertDialogFragment.show(getChildFragmentManager(), "tag_video_charges_alert");
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        SecondaryInfo secondaryInfo = this.savedSecondaryInfo;
        if (secondaryInfo != null) {
            setSecondary(secondaryInfo);
        }
    }

    public void onAudioRouteSelected(int i) {
        LogUtil.m9i("VideoCallFragment.onAudioRouteSelected", GeneratedOutlineSupport.outline5("audioRoute: ", i), new Object[0]);
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
            LogUtil.m9i("VideoCallFragment.onCheckedChanged", "show camera permission dialog", new Object[0]);
            checkCameraPermission();
        } else if (checkableImageButton == this.muteButton) {
            this.inCallButtonUiDelegate.muteClicked(z, true);
            ((VideoCallPresenter) this.videoCallScreenDelegate).resetAutoFullscreenTimer();
        }
    }

    public void onClick(View view) {
        if (view == this.endCallButton) {
            LogUtil.m9i("VideoCallFragment.onClick", "end call button clicked", new Object[0]);
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
        LogUtil.m9i("VideoCallFragment.onCreate", (String) null, new Object[0]);
        this.inCallButtonUiDelegate = ((InCallButtonUiDelegateFactory) FragmentUtils.getParent((Fragment) this, InCallButtonUiDelegateFactory.class)).newInCallButtonUiDelegate();
        if (bundle != null) {
            this.inCallButtonUiDelegate.onRestoreInstanceState(bundle);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i = 0;
        LogUtil.m9i("VideoCallFragment.onCreateView", (String) null, new Object[0]);
        View inflate = layoutInflater.inflate(isLandscape() ? R.layout.frag_videocall_land : R.layout.frag_videocall, viewGroup, false);
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
        this.previewOffBlurredImageView = (ImageView) inflate.findViewById(R.id.videocall_preview_off_blurred_image_view);
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
        this.remoteOffBlurredImageView = (ImageView) inflate.findViewById(R.id.videocall_remote_off_blurred_image_view);
        this.endCallButton = inflate.findViewById(R.id.videocall_end_call);
        this.endCallButton.setOnClickListener(this);
        this.previewTextureView = (TextureView) inflate.findViewById(R.id.videocall_video_preview);
        this.previewTextureView.setClipToOutline(true);
        this.previewOffOverlay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                VideoCallFragment.this.checkCameraPermission();
            }
        });
        this.remoteTextureView = (TextureView) inflate.findViewById(R.id.videocall_video_remote);
        this.greenScreenBackgroundView = inflate.findViewById(R.id.videocall_green_screen_background);
        this.fullscreenBackgroundView = inflate.findViewById(R.id.videocall_fullscreen_background);
        this.remoteTextureView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                LogUtil.m9i("VideoCallFragment.onLayoutChange", "remoteTextureView layout changed", new Object[0]);
                VideoCallFragment.this.updateRemoteVideoScaling();
                VideoCallFragment.this.updateRemoteOffView();
            }
        });
        this.previewTextureView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                LogUtil.m9i("VideoCallFragment.onLayoutChange", "previewTextureView layout changed", new Object[0]);
                VideoCallFragment.this.updatePreviewVideoScaling();
                VideoCallFragment.this.updatePreviewOffView();
            }
        });
        this.controls.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                LogUtil.m9i("VideoCallFragment.onLayoutChange", "controls layout changed", new Object[0]);
                if (VideoCallFragment.this.getActivity() != null && VideoCallFragment.this.getView() != null) {
                    VideoCallFragment.this.controls.removeOnLayoutChangeListener(this);
                    if (VideoCallFragment.this.isInFullscreenMode) {
                        VideoCallFragment.this.enterFullscreenMode();
                    }
                }
            }
        });
        return inflate;
    }

    public void onDestroyView() {
        super.onDestroyView();
        LogUtil.m9i("VideoCallFragment.onDestroyView", (String) null, new Object[0]);
        this.inCallButtonUiDelegate.onInCallButtonUiUnready();
        this.inCallScreenDelegate.onInCallScreenUnready();
    }

    public void onHandoverFromWiFiToLte() {
        getView().post(this.videoChargesAlertDialogRunnable);
    }

    public void onInCallScreenDialpadVisibilityChange(boolean z) {
        LogUtil.m9i("VideoCallFragment.onInCallScreenDialpadVisibilityChange", (String) null, new Object[0]);
    }

    public void onLocalVideoDimensionsChanged() {
        LogUtil.m9i("VideoCallFragment.onLocalVideoDimensionsChanged", (String) null, new Object[0]);
        updatePreviewVideoScaling();
    }

    public void onLocalVideoOrientationChanged() {
        LogUtil.m9i("VideoCallFragment.onLocalVideoOrientationChanged", (String) null, new Object[0]);
        updatePreviewVideoScaling();
    }

    public void onPause() {
        super.onPause();
        LogUtil.m9i("VideoCallFragment.onPause", (String) null, new Object[0]);
        this.inCallScreenDelegate.onInCallScreenPaused();
    }

    public void onRemoteVideoDimensionsChanged() {
        LogUtil.m9i("VideoCallFragment.onRemoteVideoDimensionsChanged", (String) null, new Object[0]);
        updateRemoteVideoScaling();
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i != 1) {
            return;
        }
        if (iArr.length <= 0 || iArr[0] != 0) {
            LogUtil.m9i("VideoCallFragment.onRequestPermissionsResult", "Camera permission denied.", new Object[0]);
            return;
        }
        LogUtil.m9i("VideoCallFragment.onRequestPermissionsResult", "Camera permission granted.", new Object[0]);
        ((VideoCallPresenter) this.videoCallScreenDelegate).onCameraPermissionGranted();
    }

    public void onResume() {
        super.onResume();
        LogUtil.m9i("VideoCallFragment.onResume", (String) null, new Object[0]);
        this.inCallScreenDelegate.onInCallScreenResumed();
    }

    public void onSaveInstanceState(Bundle bundle) {
        this.inCallButtonUiDelegate.onSaveInstanceState(bundle);
    }

    public void onStart() {
        super.onStart();
        LogUtil.m9i("VideoCallFragment.onStart", (String) null, new Object[0]);
        onVideoScreenStart();
    }

    public void onStop() {
        super.onStop();
        LogUtil.m9i("VideoCallFragment.onStop", (String) null, new Object[0]);
        onVideoScreenStop();
    }

    public void onSystemUiVisibilityChange(int i) {
        ((VideoCallPresenter) this.videoCallScreenDelegate).onSystemUiVisibilityChange((i & 2) == 0);
    }

    public void onVideoScreenStart() {
        ((VideoCallPresenter) this.videoCallScreenDelegate).onVideoCallScreenUiReady();
        getView().postDelayed(this.cameraPermissionDialogRunnable, 2000);
        getView().postDelayed(this.videoChargesAlertDialogRunnable, 500);
    }

    public void onVideoScreenStop() {
        getView().removeCallbacks(this.videoChargesAlertDialogRunnable);
        getView().removeCallbacks(this.cameraPermissionDialogRunnable);
        ((VideoCallPresenter) this.videoCallScreenDelegate).onVideoCallScreenUiUnready();
    }

    public void onViewCreated(View view, Bundle bundle) {
        LogUtil.m9i("VideoCallFragment.onViewCreated", (String) null, new Object[0]);
        this.inCallScreenDelegate = ((InCallScreenDelegateFactory) FragmentUtils.getParentUnsafe((Fragment) this, InCallScreenDelegateFactory.class)).newInCallScreenDelegate();
        this.videoCallScreenDelegate = ((VideoCallScreenDelegateFactory) FragmentUtils.getParentUnsafe((Fragment) this, VideoCallScreenDelegateFactory.class)).newVideoCallScreenDelegate(this);
        this.speakerButtonController = new SpeakerButtonController(this.speakerButton, this.inCallButtonUiDelegate, this.videoCallScreenDelegate);
        this.switchOnHoldCallController = new SwitchOnHoldCallController(this.switchOnHoldButton, this.onHoldContainer, this.inCallScreenDelegate, this.videoCallScreenDelegate);
        ((VideoCallPresenter) this.videoCallScreenDelegate).initVideoCallScreenDelegate(getContext(), this);
        this.inCallScreenDelegate.onInCallScreenDelegateInit(this);
        this.inCallScreenDelegate.onInCallScreenReady();
        this.inCallButtonUiDelegate.onInCallButtonUiReady(this);
        view.setOnSystemUiVisibilityChangeListener(this);
        if (((VideoCallPresenter) this.videoCallScreenDelegate).isFullscreen()) {
            this.controls.setVisibility(4);
            this.contactGridManager.getContainerView().setVisibility(4);
            this.endCallButton.setVisibility(4);
        }
    }

    public void requestCallRecordingPermissions(String[] strArr) {
    }

    public void setAudioState(CallAudioState callAudioState) {
        LogUtil.m9i("VideoCallFragment.setAudioState", GeneratedOutlineSupport.outline6("audioState: ", callAudioState), new Object[0]);
        this.speakerButtonController.setAudioState(callAudioState);
        this.muteButton.setChecked(callAudioState.isMuted());
        updateMutePreviewOverlayVisibility();
    }

    public void setCallRecordingDuration(long j) {
    }

    public void setCallRecordingState(boolean z) {
    }

    public void setCallState(PrimaryCallState primaryCallState) {
        LogUtil.m9i("VideoCallFragment.setCallState", primaryCallState.toString(), new Object[0]);
        this.contactGridManager.setCallState(primaryCallState);
    }

    public void setCameraSwitched(boolean z) {
        LogUtil.m9i("VideoCallFragment.setCameraSwitched", GeneratedOutlineSupport.outline10("isBackFacingCamera: ", z), new Object[0]);
    }

    public void setEnabled(boolean z) {
        "enabled: " + z;
        this.speakerButtonController.setEnabled(z);
        this.muteButton.setEnabled(z);
        this.cameraOffButton.setEnabled(z);
        this.switchOnHoldCallController.setEnabled(z);
    }

    public void setEndCallButtonEnabled(boolean z, boolean z2) {
        LogUtil.m9i("VideoCallFragment.setEndCallButtonEnabled", GeneratedOutlineSupport.outline10("enabled: ", z), new Object[0]);
    }

    public void setHold(boolean z) {
        LogUtil.m9i("VideoCallFragment.setHold", GeneratedOutlineSupport.outline10("value: ", z), new Object[0]);
    }

    public void setPrimary(PrimaryInfo primaryInfo) {
        LogUtil.m9i("VideoCallFragment.setPrimary", primaryInfo.toString(), new Object[0]);
        this.contactGridManager.setPrimary(primaryInfo);
    }

    public void setSecondary(SecondaryInfo secondaryInfo) {
        LogUtil.m9i("VideoCallFragment.setSecondary", secondaryInfo.toString(), new Object[0]);
        if (!isAdded()) {
            this.savedSecondaryInfo = secondaryInfo;
            return;
        }
        this.savedSecondaryInfo = null;
        this.switchOnHoldCallController.setSecondaryInfo(secondaryInfo);
        updateButtonStates();
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
        LogUtil.m9i("VideoCallFragment.setVideoPaused", GeneratedOutlineSupport.outline10("isPaused: ", z), new Object[0]);
        this.cameraOffButton.setChecked(z);
    }

    public void showAudioRouteSelector() {
        LogUtil.m9i("VideoCallFragment.showAudioRouteSelector", (String) null, new Object[0]);
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
        LogUtil.m8e("VideoCallFragment.showLocationUi", "Emergency video calling not supported", new Object[0]);
    }

    public void showManageConferenceCallButton(boolean z) {
        LogUtil.m9i("VideoCallFragment.showManageConferenceCallButton", GeneratedOutlineSupport.outline10("visible: ", z), new Object[0]);
    }

    public void showNoteSentToast() {
        LogUtil.m9i("VideoCallFragment.showNoteSentToast", (String) null, new Object[0]);
    }

    public void showVideoViews(boolean z, boolean z2, boolean z3) {
        boolean z4 = false;
        LogUtil.m9i("VideoCallFragment.showVideoViews", "showPreview: %b, shouldShowRemote: %b", Boolean.valueOf(z), Boolean.valueOf(z2));
        ((VideoSurfaceTextureImpl) ((VideoCallPresenter) this.videoCallScreenDelegate).getLocalVideoSurfaceTexture()).attachToTextureView(this.previewTextureView);
        ((VideoSurfaceTextureImpl) ((VideoCallPresenter) this.videoCallScreenDelegate).getRemoteVideoSurfaceTexture()).attachToTextureView(this.remoteTextureView);
        if (this.shouldShowRemote != z2) {
            this.shouldShowRemote = z2;
            z4 = true;
        }
        if (this.isRemotelyHeld != z3) {
            this.isRemotelyHeld = z3;
            z4 = true;
        }
        if (z4) {
            updateRemoteOffView();
        }
        if (this.shouldShowPreview != z) {
            this.shouldShowPreview = z;
            updatePreviewOffView();
        }
    }

    /* access modifiers changed from: package-private */
    public void updateBlurredImageView(TextureView textureView, ImageView imageView, boolean z, float f, float f2) {
        TextureView textureView2 = textureView;
        ImageView imageView2 = imageView;
        Context context = getContext();
        if (z || context == null) {
            imageView2.setImageBitmap((Bitmap) null);
            imageView2.setVisibility(8);
            return;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        int round = Math.round(((float) textureView.getWidth()) * f2);
        int round2 = Math.round(((float) textureView.getHeight()) * f2);
        LogUtil.m9i("VideoCallFragment.updateBlurredImageView", "width: %d, height: %d", Integer.valueOf(round), Integer.valueOf(round2));
        Bitmap bitmap = textureView2.getBitmap(round, round2);
        if (bitmap == null) {
            imageView2.setImageBitmap((Bitmap) null);
            imageView2.setVisibility(8);
            return;
        }
        RenderScript create = RenderScript.create(getContext());
        ScriptIntrinsicBlur create2 = ScriptIntrinsicBlur.create(create, Element.U8_4(create));
        Allocation createFromBitmap = Allocation.createFromBitmap(create, bitmap);
        Allocation createFromBitmap2 = Allocation.createFromBitmap(create, bitmap);
        create2.setRadius(f);
        create2.setInput(createFromBitmap);
        create2.forEach(createFromBitmap2);
        createFromBitmap2.copyTo(bitmap);
        create2.destroy();
        createFromBitmap.destroy();
        createFromBitmap2.destroy();
        if (round > round2) {
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), textureView2.getTransform((Matrix) null), true);
        }
        imageView2.setImageBitmap(bitmap);
        imageView2.setVisibility(0);
        LogUtil.m9i("VideoCallFragment.updateBlurredImageView", "took %d millis", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    public void updateButtonStates() {
        LogUtil.m9i("VideoCallFragment.updateButtonState", (String) null, new Object[0]);
        this.speakerButtonController.updateButtonState();
        this.switchOnHoldCallController.updateButtonState();
    }

    public void updateFullscreenAndGreenScreenMode(boolean z, boolean z2) {
        Point point;
        int i;
        LogUtil.m9i("VideoCallFragment.updateFullscreenAndGreenScreenMode", "shouldShowFullscreen: %b, shouldShowGreenScreen: %b", Boolean.valueOf(z), Boolean.valueOf(z2));
        if (getActivity() == null) {
            LogUtil.m9i("VideoCallFragment.updateFullscreenAndGreenScreenMode", "not attached to activity", new Object[0]);
        } else if (this.hasInitializedScreenModes && z2 == this.isInGreenScreenMode && z == this.isInFullscreenMode) {
            LogUtil.m9i("VideoCallFragment.updateFullscreenAndGreenScreenMode", "no change to screen modes", new Object[0]);
        } else {
            this.hasInitializedScreenModes = true;
            this.isInGreenScreenMode = z2;
            this.isInFullscreenMode = z;
            if (getView().isAttachedToWindow() && !getActivity().isInMultiWindowMode()) {
                this.controlsContainer.onApplyWindowInsets(getView().getRootWindowInsets());
            }
            if (z2) {
                LogUtil.m9i("VideoCallFragment.enterGreenScreenMode", (String) null, new Object[0]);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                layoutParams.addRule(20);
                layoutParams.addRule(10);
                this.previewTextureView.setLayoutParams(layoutParams);
                this.previewTextureView.setOutlineProvider((ViewOutlineProvider) null);
                updateOverlayBackground();
                this.contactGridManager.setIsMiddleRowVisible(true);
                updateMutePreviewOverlayVisibility();
                this.previewOffBlurredImageView.setLayoutParams(layoutParams);
                this.previewOffBlurredImageView.setOutlineProvider((ViewOutlineProvider) null);
                this.previewOffBlurredImageView.setClipToOutline(false);
            } else {
                LogUtil.m9i("VideoCallFragment.exitGreenScreenMode", (String) null, new Object[0]);
                Resources resources = getResources();
                RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams((int) resources.getDimension(R.dimen.videocall_preview_width), (int) resources.getDimension(R.dimen.videocall_preview_height));
                layoutParams2.setMargins(0, 0, 0, (int) resources.getDimension(R.dimen.videocall_preview_margin_bottom));
                if (isLandscape()) {
                    layoutParams2.addRule(21);
                    layoutParams2.setMarginEnd((int) resources.getDimension(R.dimen.videocall_preview_margin_end));
                } else {
                    layoutParams2.addRule(20);
                    layoutParams2.setMarginStart((int) resources.getDimension(R.dimen.videocall_preview_margin_start));
                }
                layoutParams2.addRule(12);
                this.previewTextureView.setLayoutParams(layoutParams2);
                this.previewTextureView.setOutlineProvider(this.circleOutlineProvider);
                updateOverlayBackground();
                this.contactGridManager.setIsMiddleRowVisible(false);
                updateMutePreviewOverlayVisibility();
                this.previewOffBlurredImageView.setLayoutParams(layoutParams2);
                this.previewOffBlurredImageView.setOutlineProvider(this.circleOutlineProvider);
                this.previewOffBlurredImageView.setClipToOutline(true);
            }
            if (z) {
                enterFullscreenMode();
            } else {
                LogUtil.m9i("VideoCallFragment.exitFullscreenMode", (String) null, new Object[0]);
                if (!getView().isAttachedToWindow()) {
                    LogUtil.m9i("VideoCallFragment.exitFullscreenMode", "not attached", new Object[0]);
                } else {
                    View view = getView();
                    if (view != null) {
                        view.setSystemUiVisibility(256);
                    }
                    LinearOutSlowInInterpolator linearOutSlowInInterpolator = new LinearOutSlowInInterpolator();
                    this.controls.animate().translationX(0.0f).translationY(0.0f).setInterpolator(linearOutSlowInInterpolator).alpha(BLUR_PREVIEW_SCALE_FACTOR).withStartAction(new Runnable() {
                        public void run() {
                            VideoCallFragment.this.controls.setVisibility(0);
                        }
                    }).start();
                    this.switchOnHoldButton.animate().translationX(0.0f).translationY(0.0f).setInterpolator(linearOutSlowInInterpolator).alpha(BLUR_PREVIEW_SCALE_FACTOR).withStartAction(new Runnable() {
                        public void run() {
                            VideoCallFragment.this.switchOnHoldCallController.setOnScreen();
                        }
                    });
                    this.contactGridManager.getContainerView().animate().translationX(0.0f).translationY(0.0f).setInterpolator(linearOutSlowInInterpolator).alpha(BLUR_PREVIEW_SCALE_FACTOR).withStartAction(new Runnable() {
                        public void run() {
                            VideoCallFragment.this.contactGridManager.show();
                        }
                    });
                    this.endCallButton.animate().translationX(0.0f).translationY(0.0f).setInterpolator(linearOutSlowInInterpolator).alpha(BLUR_PREVIEW_SCALE_FACTOR).withStartAction(new Runnable() {
                        public void run() {
                            VideoCallFragment.this.endCallButton.setVisibility(0);
                        }
                    }).start();
                    if (!this.isInGreenScreenMode) {
                        if (getActivity().isInMultiWindowMode()) {
                            point = new Point();
                        } else if (isLandscape()) {
                            if (getView().getLayoutDirection() == 1) {
                                i = getView().getRootWindowInsets().getSystemWindowInsetLeft();
                            } else {
                                i = -getView().getRootWindowInsets().getSystemWindowInsetRight();
                            }
                            point = new Point(i, 0);
                        } else {
                            point = new Point(0, -getView().getRootWindowInsets().getSystemWindowInsetBottom());
                        }
                        for (View animate : getAllPreviewRelatedViews()) {
                            animate.animate().translationX((float) point.x).translationY((float) point.y).setInterpolator(new AccelerateDecelerateInterpolator()).start();
                        }
                    }
                    updateOverlayBackground();
                }
            }
            OnHoldFragment onHoldFragment = (OnHoldFragment) getChildFragmentManager().findFragmentById(R.id.videocall_on_hold_banner);
            if (onHoldFragment != null) {
                onHoldFragment.setPadTopInset(!this.isInFullscreenMode);
            }
        }
    }

    public void updateInCallButtonUiColors(int i) {
    }

    public void updateInCallScreenColors() {
        LogUtil.m9i("VideoCallFragment.updateColors", (String) null, new Object[0]);
    }
}
