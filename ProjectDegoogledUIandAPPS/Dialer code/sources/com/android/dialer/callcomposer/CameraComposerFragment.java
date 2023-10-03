package com.android.dialer.callcomposer;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.dialer.R;
import com.android.dialer.callcomposer.camera.CameraManager;
import com.android.dialer.callcomposer.camera.CameraPreview;
import com.android.dialer.callcomposer.camera.camerafocus.RenderOverlay;
import com.android.dialer.callcomposer.cameraui.CameraMediaChooserView;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.theme.base.ThemeComponent;
import com.android.dialer.theme.base.impl.AospThemeImpl;
import com.android.dialer.util.PermissionsUtil;
import com.android.tools.p006r8.GeneratedOutlineSupport;

public class CameraComposerFragment extends CallComposerFragment implements CameraManager.CameraManagerListener, View.OnClickListener, CameraManager.MediaCallback {
    private View allowPermission;
    private int cameraDirection = 0;
    private Uri cameraUri;
    private CameraMediaChooserView cameraView;
    private ImageButton cancel;
    private ImageButton capture;
    private ImageButton exitFullscreen;
    private RenderOverlay focus;
    private ImageButton fullscreen;
    private ProgressBar loading;
    private View permissionView;
    private String[] permissions = {"android.permission.CAMERA"};
    private CameraPreview.CameraPreviewHost preview;
    private ImageView previewImageView;
    private boolean processingUri;
    private View shutter;
    private ImageButton swapCamera;
    private CameraUriCallback uriCallback;

    public interface CameraUriCallback {
        void uriReady(Uri uri);
    }

    private void setCameraUri(Uri uri) {
        this.cameraUri = uri;
        if (getListener() != null) {
            updateViewState();
            getListener().composeCall(this);
        }
    }

    private void setupCamera() {
        if (!PermissionsUtil.hasCameraPrivacyToastShown(getContext())) {
            PermissionsUtil.showCameraPermissionToast(getContext());
        }
        CameraManager.get().setListener(this);
        this.preview.setShown();
        CameraManager.get().setRenderOverlay(this.focus);
        CameraManager.get().selectCamera(this.cameraDirection);
        setCameraUri(this.cameraUri);
    }

    private void updateViewState() {
        Assert.isNotNull(this.cameraView);
        if (isDetached() || getContext() == null) {
            LogUtil.m9i("CameraComposerFragment.updateViewState", "Fragment detached, cannot update view state", new Object[0]);
            return;
        }
        boolean isCameraAvailable = CameraManager.get().isCameraAvailable();
        boolean z = this.cameraUri != null || this.processingUri;
        Uri uri = this.cameraUri;
        if (uri != null) {
            this.previewImageView.setImageURI(uri);
            this.previewImageView.setVisibility(0);
            this.previewImageView.setScaleX(this.cameraDirection == 1 ? -1.0f : 1.0f);
        } else {
            this.previewImageView.setVisibility(8);
        }
        if (this.cameraDirection == 1) {
            this.swapCamera.setContentDescription(getString(R.string.description_camera_switch_camera_rear));
        } else {
            this.swapCamera.setContentDescription(getString(R.string.description_camera_switch_camera_facing));
        }
        if (this.cameraUri == null && isCameraAvailable) {
            CameraManager.get().resetPreview();
            this.cancel.setVisibility(8);
        }
        if (!CameraManager.get().hasFrontAndBackCamera()) {
            this.swapCamera.setVisibility(8);
        } else {
            this.swapCamera.setVisibility(z ? 8 : 0);
        }
        this.capture.setVisibility(z ? 8 : 0);
        this.cancel.setVisibility(z ? 0 : 8);
        if (z || getListener().isLandscapeLayout()) {
            this.fullscreen.setVisibility(8);
            this.exitFullscreen.setVisibility(8);
        } else if (getListener().isFullscreen()) {
            this.exitFullscreen.setVisibility(0);
            this.fullscreen.setVisibility(8);
        } else {
            this.exitFullscreen.setVisibility(8);
            this.fullscreen.setVisibility(0);
        }
        this.swapCamera.setEnabled(isCameraAvailable);
        this.capture.setEnabled(isCameraAvailable);
    }

    public void clearComposer() {
        this.processingUri = false;
        setCameraUri((Uri) null);
    }

    public void onCameraChanged() {
        updateViewState();
    }

    public void onCameraError(int i, Exception exc) {
        LogUtil.m8e("CameraComposerFragment.onCameraError", "errorCode: ", Integer.valueOf(i), exc);
    }

    public void onClick(View view) {
        if (view == this.capture) {
            float f = 1.0f;
            if (!getListener().isFullscreen() && !getListener().isLandscapeLayout()) {
                f = Math.min(((float) this.cameraView.getHeight()) / ((float) this.preview.getView().getHeight()), 1.0f);
            }
            final View view2 = this.shutter;
            AnimationSet animationSet = new AnimationSet(false);
            AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 0.7f);
            long j = (long) 100;
            alphaAnimation.setDuration(j);
            animationSet.addAnimation(alphaAnimation);
            AlphaAnimation alphaAnimation2 = new AlphaAnimation(0.7f, 0.0f);
            alphaAnimation2.setStartOffset(j);
            alphaAnimation2.setDuration(j);
            animationSet.addAnimation(alphaAnimation2);
            animationSet.setAnimationListener(new Animation.AnimationListener(this) {
                public void onAnimationEnd(Animation animation) {
                    view2.setVisibility(8);
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                    view2.setVisibility(0);
                }
            });
            view2.startAnimation(animationSet);
            this.processingUri = true;
            setCameraUri((Uri) null);
            this.focus.getPieRenderer().clear();
            CameraManager.get().takePicture(f, this);
            return;
        }
        ImageButton imageButton = this.swapCamera;
        if (view == imageButton) {
            ((Animatable) imageButton.getDrawable()).start();
            CameraManager.get().swapCamera();
            this.cameraDirection = CameraManager.get().getCameraInfo().facing;
        } else if (view == this.cancel) {
            this.processingUri = false;
            setCameraUri((Uri) null);
        } else if (view == this.exitFullscreen) {
            getListener().showFullscreen(false);
            this.fullscreen.setVisibility(0);
            this.exitFullscreen.setVisibility(8);
        } else if (view == this.fullscreen) {
            getListener().showFullscreen(true);
            this.fullscreen.setVisibility(8);
            this.exitFullscreen.setVisibility(0);
        } else if (view != this.allowPermission) {
        } else {
            if (PermissionsUtil.isFirstRequest(getContext(), this.permissions[0]) || shouldShowRequestPermissionRationale(this.permissions[0])) {
                ((LoggingBindingsStub) Logger.get(getContext())).logImpression(DialerImpression$Type.CAMERA_PERMISSION_REQUESTED);
                LogUtil.m9i("CameraComposerFragment.onClick", "Camera permission requested.", new Object[0]);
                requestPermissions(this.permissions, 1);
                return;
            }
            ((LoggingBindingsStub) Logger.get(getContext())).logImpression(DialerImpression$Type.CAMERA_PERMISSION_SETTINGS);
            LogUtil.m9i("CameraComposerFragment.onClick", "Settings opened to enable permission.", new Object[0]);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.addFlags(268435456);
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("package:");
            outline13.append(getContext().getPackageName());
            intent.setData(Uri.parse(outline13.toString()));
            startActivity(intent, (Bundle) null);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_camera_composer, viewGroup, false);
        this.permissionView = inflate.findViewById(R.id.permission_view);
        this.loading = (ProgressBar) inflate.findViewById(R.id.loading);
        this.cameraView = (CameraMediaChooserView) inflate.findViewById(R.id.camera_view);
        this.shutter = this.cameraView.findViewById(R.id.camera_shutter_visual);
        this.exitFullscreen = (ImageButton) this.cameraView.findViewById(R.id.camera_exit_fullscreen);
        this.fullscreen = (ImageButton) this.cameraView.findViewById(R.id.camera_fullscreen);
        this.swapCamera = (ImageButton) this.cameraView.findViewById(R.id.swap_camera_button);
        this.capture = (ImageButton) this.cameraView.findViewById(R.id.camera_capture_button);
        this.cancel = (ImageButton) this.cameraView.findViewById(R.id.camera_cancel_button);
        this.focus = (RenderOverlay) this.cameraView.findViewById(R.id.focus_visual);
        this.preview = (CameraPreview.CameraPreviewHost) this.cameraView.findViewById(R.id.camera_preview);
        this.previewImageView = (ImageView) inflate.findViewById(R.id.preview_image_view);
        this.exitFullscreen.setOnClickListener(this);
        this.fullscreen.setOnClickListener(this);
        this.swapCamera.setOnClickListener(this);
        this.capture.setOnClickListener(this);
        this.cancel.setOnClickListener(this);
        if (!PermissionsUtil.hasCameraPermissions(getContext())) {
            LogUtil.m9i("CameraComposerFragment.onCreateView", "Permission view shown.", new Object[0]);
            ((LoggingBindingsStub) Logger.get(getContext())).logImpression(DialerImpression$Type.CAMERA_PERMISSION_DISPLAYED);
            ImageView imageView = (ImageView) this.permissionView.findViewById(R.id.permission_icon);
            this.allowPermission = this.permissionView.findViewById(R.id.allow);
            this.allowPermission.setOnClickListener(this);
            ((TextView) this.permissionView.findViewById(R.id.permission_text)).setText(R.string.camera_permission_text);
            imageView.setImageResource(R.drawable.quantum_ic_camera_alt_white_48);
            imageView.setColorFilter(((AospThemeImpl) ThemeComponent.get(getContext()).theme()).getColorPrimary());
            this.permissionView.setVisibility(0);
        } else {
            if (bundle != null) {
                this.cameraDirection = bundle.getInt("camera_direction");
                this.cameraUri = (Uri) bundle.getParcelable("camera_key");
            }
            setupCamera();
        }
        return inflate;
    }

    public void onDestroy() {
        super.onDestroy();
        CameraManager.get().setListener((CameraManager.CameraManagerListener) null);
    }

    public void onMediaFailed(Exception exc) {
        LogUtil.m7e("CallComposerFragment.onMediaFailed", (String) null, (Throwable) exc);
        Toast.makeText(getContext(), R.string.camera_media_failure, 1).show();
        setCameraUri((Uri) null);
        this.processingUri = false;
        if (this.uriCallback != null) {
            this.loading.setVisibility(8);
            this.uriCallback = null;
        }
    }

    public void onMediaInfo(int i) {
        if (i == 2) {
            Toast.makeText(getContext(), R.string.camera_media_failure, 1).show();
        }
        setCameraUri((Uri) null);
        this.processingUri = false;
    }

    public void onMediaReady(Uri uri, String str, int i, int i2) {
        if (this.processingUri) {
            this.processingUri = false;
            setCameraUri(uri);
            CameraUriCallback cameraUriCallback = this.uriCallback;
            if (cameraUriCallback != null) {
                cameraUriCallback.uriReady(uri);
                this.uriCallback = null;
                return;
            }
            return;
        }
        updateViewState();
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (strArr.length > 0 && strArr[0].equals(this.permissions[0])) {
            PermissionsUtil.permissionRequested(getContext(), strArr[0]);
        }
        if (i == 1 && iArr.length > 0 && iArr[0] == 0) {
            ((LoggingBindingsStub) Logger.get(getContext())).logImpression(DialerImpression$Type.CAMERA_PERMISSION_GRANTED);
            LogUtil.m9i("CameraComposerFragment.onRequestPermissionsResult", "Permission granted.", new Object[0]);
            this.permissionView.setVisibility(8);
            PermissionsUtil.setCameraPrivacyToastShown(getContext());
            setupCamera();
        } else if (i == 1) {
            ((LoggingBindingsStub) Logger.get(getContext())).logImpression(DialerImpression$Type.CAMERA_PERMISSION_DENIED);
            LogUtil.m9i("CameraComposerFragment.onRequestPermissionsResult", "Permission denied.", new Object[0]);
        }
    }

    public void onResume() {
        super.onResume();
        if (PermissionsUtil.hasCameraPermissions(getContext())) {
            this.permissionView.setVisibility(8);
            setupCamera();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putInt("camera_direction", this.cameraDirection);
        bundle.putParcelable("camera_key", this.cameraUri);
    }

    public boolean shouldHide() {
        return !this.processingUri && this.cameraUri == null;
    }
}
