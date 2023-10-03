package com.android.dialer.simulator.impl;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.net.Uri;
import android.telecom.Connection;
import android.telecom.VideoProfile;
import android.util.Size;
import android.view.Surface;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.simulator.Simulator;
import com.android.tools.p006r8.GeneratedOutlineSupport;

final class SimulatorVideoProvider extends Connection.VideoProvider {
    private final SimulatorConnection connection;
    private final Context context;
    private String previewCameraId;
    private SimulatorPreviewCamera simulatorPreviewCamera;
    private SimulatorRemoteVideo simulatorRemoteVideo;

    SimulatorVideoProvider(Context context2, SimulatorConnection simulatorConnection) {
        Assert.isNotNull(context2);
        this.context = context2;
        Assert.isNotNull(simulatorConnection);
        this.connection = simulatorConnection;
    }

    public void onRequestCameraCapabilities() {
        VideoProfile.CameraCapabilities cameraCapabilities;
        LogUtil.enterBlock("SimulatorVideoProvider.onRequestCameraCapabilities");
        Context context2 = this.context;
        String str = this.previewCameraId;
        if (str == null) {
            LogUtil.m8e("SimulatorPreviewCamera.getCameraCapabilities", "null camera ID", new Object[0]);
            cameraCapabilities = null;
        } else {
            try {
                Size size = ((StreamConfigurationMap) ((CameraManager) context2.getSystemService(CameraManager.class)).getCameraCharacteristics(str).get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)).getOutputSizes(SurfaceTexture.class)[0];
                LogUtil.m9i("SimulatorPreviewCamera.getCameraCapabilities", GeneratedOutlineSupport.outline6("preview size: ", size), new Object[0]);
                cameraCapabilities = new VideoProfile.CameraCapabilities(size.getWidth(), size.getHeight());
            } catch (CameraAccessException e) {
                throw new IllegalStateException(GeneratedOutlineSupport.outline6("camera error: ", e));
            }
        }
        changeCameraCapabilities(cameraCapabilities);
    }

    public void onRequestConnectionDataUsage() {
        LogUtil.enterBlock("SimulatorVideoProvider.onRequestConnectionDataUsage");
        setCallDataUsage(10240);
    }

    public void onSendSessionModifyRequest(VideoProfile videoProfile, VideoProfile videoProfile2) {
        LogUtil.enterBlock("SimulatorVideoProvider.onSendSessionModifyRequest");
        this.connection.onEvent(new Simulator.Event(8, Integer.toString(videoProfile.getVideoState()), Integer.toString(videoProfile2.getVideoState())));
    }

    public void onSendSessionModifyResponse(VideoProfile videoProfile) {
        LogUtil.enterBlock("SimulatorVideoProvider.onSendSessionModifyResponse");
    }

    public void onSetCamera(String str) {
        SimulatorRemoteVideo simulatorRemoteVideo2;
        LogUtil.m9i("SimulatorVideoProvider.onSetCamera", GeneratedOutlineSupport.outline8("previewCameraId: ", str), new Object[0]);
        this.previewCameraId = str;
        SimulatorPreviewCamera simulatorPreviewCamera2 = this.simulatorPreviewCamera;
        if (simulatorPreviewCamera2 != null) {
            simulatorPreviewCamera2.stopCamera();
            this.simulatorPreviewCamera = null;
        }
        if (str == null && (simulatorRemoteVideo2 = this.simulatorRemoteVideo) != null) {
            simulatorRemoteVideo2.stopVideo();
            this.simulatorRemoteVideo = null;
        }
    }

    public void onSetDeviceOrientation(int i) {
        LogUtil.m9i("SimulatorVideoProvider.onSetDeviceOrientation", GeneratedOutlineSupport.outline5("rotation: ", i), new Object[0]);
    }

    public void onSetDisplaySurface(Surface surface) {
        LogUtil.enterBlock("SimulatorVideoProvider.onSetDisplaySurface");
        SimulatorRemoteVideo simulatorRemoteVideo2 = this.simulatorRemoteVideo;
        if (simulatorRemoteVideo2 != null) {
            simulatorRemoteVideo2.stopVideo();
            this.simulatorRemoteVideo = null;
        }
        if (surface != null) {
            this.simulatorRemoteVideo = new SimulatorRemoteVideo(surface);
            this.simulatorRemoteVideo.startVideo();
        }
    }

    public void onSetPauseImage(Uri uri) {
        LogUtil.enterBlock("SimulatorVideoProvider.onSetPauseImage");
    }

    public void onSetPreviewSurface(Surface surface) {
        String str;
        LogUtil.enterBlock("SimulatorVideoProvider.onSetPreviewSurface");
        SimulatorPreviewCamera simulatorPreviewCamera2 = this.simulatorPreviewCamera;
        if (simulatorPreviewCamera2 != null) {
            simulatorPreviewCamera2.stopCamera();
            this.simulatorPreviewCamera = null;
        }
        if (surface != null && (str = this.previewCameraId) != null) {
            this.simulatorPreviewCamera = new SimulatorPreviewCamera(this.context, str, surface);
            this.simulatorPreviewCamera.startCamera();
        }
    }

    public void onSetZoom(float f) {
        LogUtil.m9i("SimulatorVideoProvider.onSetZoom", "zoom: " + f, new Object[0]);
    }
}
