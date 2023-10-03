package com.android.messaging.p041ui.mediapicker;

import android.hardware.Camera;

/* renamed from: com.android.messaging.ui.mediapicker.h */
class C1328h implements C1346q {
    C1328h() {
    }

    /* renamed from: b */
    public void mo7861b(Camera camera) {
        camera.release();
    }

    public void getCameraInfo(int i, Camera.CameraInfo cameraInfo) {
        Camera.getCameraInfo(i, cameraInfo);
    }

    public int getNumberOfCameras() {
        return Camera.getNumberOfCameras();
    }

    public Camera open(int i) {
        return Camera.open(i);
    }
}
