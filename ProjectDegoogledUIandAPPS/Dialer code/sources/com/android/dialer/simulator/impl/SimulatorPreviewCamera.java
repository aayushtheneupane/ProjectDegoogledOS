package com.android.dialer.simulator.impl;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.os.Handler;
import android.view.Surface;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.Arrays;

final class SimulatorPreviewCamera {
    /* access modifiers changed from: private */
    public CameraDevice camera;
    private final String cameraId;
    private final Context context;
    /* access modifiers changed from: private */
    public boolean isStopped;
    /* access modifiers changed from: private */
    public final Surface surface;

    private final class CameraListener extends CameraDevice.StateCallback {
        /* synthetic */ CameraListener(C05601 r2) {
        }

        public void onClosed(CameraDevice cameraDevice) {
            LogUtil.enterBlock("SimulatorPreviewCamera.CameraListener.onCLosed");
        }

        public void onDisconnected(CameraDevice cameraDevice) {
            LogUtil.enterBlock("SimulatorPreviewCamera.CameraListener.onDisconnected");
            SimulatorPreviewCamera.this.stopCamera();
        }

        public void onError(CameraDevice cameraDevice, int i) {
            LogUtil.m9i("SimulatorPreviewCamera.CameraListener.onError", GeneratedOutlineSupport.outline5("error: ", i), new Object[0]);
            SimulatorPreviewCamera.this.stopCamera();
        }

        public void onOpened(CameraDevice cameraDevice) {
            LogUtil.enterBlock("SimulatorPreviewCamera.CameraListener.onOpened");
            CameraDevice unused = SimulatorPreviewCamera.this.camera = cameraDevice;
            if (SimulatorPreviewCamera.this.isStopped) {
                LogUtil.m9i("SimulatorPreviewCamera.CameraListener.onOpened", "stopped", new Object[0]);
                SimulatorPreviewCamera.this.stopCamera();
                return;
            }
            try {
                Surface access$300 = SimulatorPreviewCamera.this.surface;
                Assert.isNotNull(access$300);
                cameraDevice.createCaptureSession(Arrays.asList(new Surface[]{access$300}), new CaptureSessionCallback((C05601) null), (Handler) null);
            } catch (CameraAccessException e) {
                throw new IllegalStateException(GeneratedOutlineSupport.outline6("camera error: ", e));
            }
        }
    }

    private final class CaptureSessionCallback extends CameraCaptureSession.StateCallback {
        /* synthetic */ CaptureSessionCallback(C05601 r2) {
        }

        public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
            LogUtil.enterBlock("SimulatorPreviewCamera.CaptureSessionCallback.onConfigureFailed");
        }

        public void onConfigured(CameraCaptureSession cameraCaptureSession) {
            LogUtil.enterBlock("SimulatorPreviewCamera.CaptureSessionCallback.onConfigured");
            if (SimulatorPreviewCamera.this.isStopped) {
                LogUtil.m9i("SimulatorPreviewCamera.CaptureSessionCallback.onConfigured", "stopped", new Object[0]);
                SimulatorPreviewCamera.this.stopCamera();
                return;
            }
            try {
                CaptureRequest.Builder createCaptureRequest = SimulatorPreviewCamera.this.camera.createCaptureRequest(1);
                createCaptureRequest.addTarget(SimulatorPreviewCamera.this.surface);
                createCaptureRequest.set(CaptureRequest.CONTROL_MODE, 1);
                cameraCaptureSession.setRepeatingRequest(createCaptureRequest.build(), (CameraCaptureSession.CaptureCallback) null, (Handler) null);
            } catch (CameraAccessException e) {
                throw new IllegalStateException(GeneratedOutlineSupport.outline6("camera error: ", e));
            }
        }
    }

    SimulatorPreviewCamera(Context context2, String str, Surface surface2) {
        Assert.isNotNull(context2);
        this.context = context2;
        Assert.isNotNull(str);
        this.cameraId = str;
        Assert.isNotNull(surface2);
        this.surface = surface2;
    }

    /* access modifiers changed from: package-private */
    public void startCamera() {
        LogUtil.enterBlock("SimulatorPreviewCamera.startCamera");
        Assert.checkState(!this.isStopped);
        try {
            ((CameraManager) this.context.getSystemService(CameraManager.class)).openCamera(this.cameraId, new CameraListener((C05601) null), (Handler) null);
        } catch (CameraAccessException | SecurityException e) {
            throw new IllegalStateException(GeneratedOutlineSupport.outline6("camera error: ", e));
        }
    }

    /* access modifiers changed from: package-private */
    public void stopCamera() {
        LogUtil.enterBlock("SimulatorPreviewCamera.stopCamera");
        this.isStopped = true;
        CameraDevice cameraDevice = this.camera;
        if (cameraDevice != null) {
            cameraDevice.close();
            this.camera = null;
        }
    }
}
