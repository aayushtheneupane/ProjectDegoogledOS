package com.android.incallui;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class InCallCameraManager {
    private final Set<Listener> cameraSelectionListeners = Collections.newSetFromMap(new ConcurrentHashMap(8, 0.9f, 1));
    private Context context;
    private String frontFacingCameraId;
    private boolean isInitialized = false;
    private String rearFacingCameraId;
    private boolean useFrontFacingCamera = true;

    public interface Listener {
    }

    public InCallCameraManager(Context context2) {
        this.context = context2;
    }

    public void addCameraSelectionListener(Listener listener) {
        if (listener != null) {
            this.cameraSelectionListeners.add(listener);
        }
    }

    public String getActiveCameraId() {
        Context context2 = this.context;
        if (!this.isInitialized && context2 != null) {
            Bindings.m40v(this, "initializeCameraList");
            try {
                CameraManager cameraManager = (CameraManager) context2.getSystemService("camera");
                if (cameraManager != null) {
                    try {
                        String[] cameraIdList = cameraManager.getCameraIdList();
                        for (int i = 0; i < cameraIdList.length; i++) {
                            CameraCharacteristics cameraCharacteristics = null;
                            try {
                                cameraCharacteristics = cameraManager.getCameraCharacteristics(cameraIdList[i]);
                            } catch (CameraAccessException | IllegalArgumentException unused) {
                            }
                            if (cameraCharacteristics != null) {
                                int intValue = ((Integer) cameraCharacteristics.get(CameraCharacteristics.LENS_FACING)).intValue();
                                if (intValue == 0) {
                                    this.frontFacingCameraId = cameraIdList[i];
                                } else if (intValue == 1) {
                                    this.rearFacingCameraId = cameraIdList[i];
                                }
                            }
                        }
                        this.isInitialized = true;
                        Bindings.m40v(this, "initializeCameraList : done");
                    } catch (CameraAccessException e) {
                        Bindings.m34d(this, "Could not access camera: " + e);
                    }
                }
            } catch (Exception unused2) {
                Bindings.m36e(this, "Could not get camera service.");
            }
        }
        if (this.useFrontFacingCamera) {
            return this.frontFacingCameraId;
        }
        return this.rearFacingCameraId;
    }

    public boolean isUsingFrontFacingCamera() {
        return this.useFrontFacingCamera;
    }

    public void onCameraPermissionGranted() {
        Iterator<Listener> it = this.cameraSelectionListeners.iterator();
        while (it.hasNext()) {
            ((CallButtonPresenter) it.next()).onCameraPermissionGranted();
        }
    }

    public void removeCameraSelectionListener(Listener listener) {
        if (listener != null) {
            this.cameraSelectionListeners.remove(listener);
        }
    }

    public void setUseFrontFacingCamera(boolean z) {
        this.useFrontFacingCamera = z;
        Iterator<Listener> it = this.cameraSelectionListeners.iterator();
        while (it.hasNext()) {
            ((CallButtonPresenter) it.next()).onActiveCameraSelectionChanged(this.useFrontFacingCamera);
        }
    }
}
