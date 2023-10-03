package com.android.incallui.answer.impl;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Handler;
import android.support.p000v4.app.Fragment;
import android.util.Size;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.View;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.incallui.video.protocol.VideoCallScreen;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.Arrays;

public class SelfManagedAnswerVideoCallScreen extends CameraDevice.StateCallback implements VideoCallScreen {
    private final String callId;
    /* access modifiers changed from: private */
    public CameraDevice camera;
    private String cameraId;
    /* access modifiers changed from: private */
    public CaptureRequest.Builder captureRequestBuilder;
    private final Context context;
    private final Fragment fragment;
    private final FixedAspectSurfaceView surfaceView;

    private class CaptureSessionCallback extends CameraCaptureSession.StateCallback {
        /* synthetic */ CaptureSessionCallback(C06671 r2) {
        }

        public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
            LogUtil.m8e("CaptureSessionCallback.onConfigureFailed", "failed to configure", new Object[0]);
        }

        public void onConfigured(CameraCaptureSession cameraCaptureSession) {
            LogUtil.m9i("SelfManagedAnswerVideoCallScreen.onConfigured", "camera capture session configured.", new Object[0]);
            if (SelfManagedAnswerVideoCallScreen.this.camera != null) {
                SelfManagedAnswerVideoCallScreen.this.captureRequestBuilder.set(CaptureRequest.CONTROL_MODE, 1);
                try {
                    cameraCaptureSession.setRepeatingRequest(SelfManagedAnswerVideoCallScreen.this.captureRequestBuilder.build(), (CameraCaptureSession.CaptureCallback) null, (Handler) null);
                } catch (CameraAccessException e) {
                    LogUtil.m7e("CaptureSessionCallback.onConfigured", "failed to configure", (Throwable) e);
                }
            }
        }
    }

    public SelfManagedAnswerVideoCallScreen(String str, Fragment fragment2, View view) {
        Assert.isNotNull(str);
        this.callId = str;
        Assert.isNotNull(fragment2);
        this.fragment = fragment2;
        Context context2 = fragment2.getContext();
        Assert.isNotNull(context2);
        this.context = context2;
        FixedAspectSurfaceView fixedAspectSurfaceView = (FixedAspectSurfaceView) view.findViewById(R.id.incoming_preview_surface_view);
        Assert.isNotNull(fixedAspectSurfaceView);
        this.surfaceView = fixedAspectSurfaceView;
        this.surfaceView.setVisibility(0);
        view.findViewById(R.id.incoming_preview_texture_view_overlay).setVisibility(0);
        view.setBackgroundColor(-16777216);
    }

    private void closeCamera() {
        CameraDevice cameraDevice = this.camera;
        if (cameraDevice != null) {
            cameraDevice.close();
            this.camera = null;
        }
    }

    public String getCallId() {
        return this.callId;
    }

    public Fragment getVideoCallScreenFragment() {
        return this.fragment;
    }

    public void onDisconnected(CameraDevice cameraDevice) {
        closeCamera();
    }

    public void onError(CameraDevice cameraDevice, int i) {
        closeCamera();
    }

    public void onHandoverFromWiFiToLte() {
    }

    public void onLocalVideoDimensionsChanged() {
    }

    public void onLocalVideoOrientationChanged() {
    }

    public void onOpened(CameraDevice cameraDevice) {
        LogUtil.m9i("SelfManagedAnswerVideoCallScreen.opOpened", "camera opened.", new Object[0]);
        this.camera = cameraDevice;
        Surface surface = this.surfaceView.getHolder().getSurface();
        try {
            this.captureRequestBuilder = cameraDevice.createCaptureRequest(1);
            this.captureRequestBuilder.addTarget(surface);
            cameraDevice.createCaptureSession(Arrays.asList(new Surface[]{surface}), new CaptureSessionCallback((C06671) null), (Handler) null);
        } catch (CameraAccessException e) {
            LogUtil.m7e("SelfManagedAnswerVideoCallScreen.createCameraPreview", "failed to create preview", (Throwable) e);
        }
    }

    public void onRemoteVideoDimensionsChanged() {
    }

    public void onVideoScreenStart() {
        CameraManager cameraManager = (CameraManager) this.context.getSystemService(CameraManager.class);
        try {
            for (String str : cameraManager.getCameraIdList()) {
                try {
                    CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics(str);
                    if (((Integer) cameraCharacteristics.get(CameraCharacteristics.LENS_FACING)).intValue() == 0 && (r8 = (StreamConfigurationMap) cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)) != null) {
                        this.cameraId = str;
                        break;
                    }
                } catch (CameraAccessException e) {
                    LogUtil.m7e("SelfManagedAnswerVideoCallScreen.getFrontFacingCameraSizes", "failed to get camera characteristics", (Throwable) e);
                }
            }
            LogUtil.m8e("SelfManagedAnswerVideoCallScreen.getFrontFacingCameraSizes", "No valid configurations.", new Object[0]);
        } catch (CameraAccessException e2) {
            LogUtil.m7e("SelfManagedAnswerVideoCallScreen.getFrontFacingCameraSizes", "failed to get camera ids", (Throwable) e2);
        }
        StreamConfigurationMap streamConfigurationMap = null;
        if (streamConfigurationMap != null) {
            Size[] outputSizes = streamConfigurationMap.getOutputSizes(SurfaceHolder.class);
            Size size = outputSizes[0];
            float width = ((float) size.getWidth()) / ((float) size.getHeight());
            float f = width;
            Size size2 = size;
            for (Size size3 : outputSizes) {
                if (size3.getWidth() < 1920) {
                    float width2 = ((float) size3.getWidth()) / ((float) size3.getHeight());
                    boolean z = true;
                    boolean z2 = Math.abs(width2 - 1.7777778f) < 0.1f;
                    if (Math.abs(f - 1.7777778f) >= 0.1f) {
                        z = false;
                    }
                    if ((z2 && !z) || size3.getWidth() > size2.getWidth()) {
                        size2 = size3;
                        f = width2;
                    }
                }
            }
            LogUtil.m9i("SelfManagedAnswerVideoCallScreen.openCamera", GeneratedOutlineSupport.outline6("Optimal size: ", size2), new Object[0]);
            this.surfaceView.setAspectRatio(((float) size2.getWidth()) / ((float) size2.getHeight()));
            this.surfaceView.getHolder().setFixedSize(size2.getWidth(), size2.getHeight());
            try {
                cameraManager.openCamera(this.cameraId, this, (Handler) null);
            } catch (CameraAccessException e3) {
                LogUtil.m7e("SelfManagedAnswerVideoCallScreen.openCamera", "failed to open camera", (Throwable) e3);
            }
        }
    }

    public void onVideoScreenStop() {
        closeCamera();
    }

    public void showVideoViews(boolean z, boolean z2, boolean z3) {
    }

    public void updateFullscreenAndGreenScreenMode(boolean z, boolean z2) {
    }
}
