package com.android.dialer.callcomposer.camera;

import android.content.Context;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Looper;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.WindowManager;
import com.android.dialer.callcomposer.CameraComposerFragment;
import com.android.dialer.callcomposer.camera.CameraManager;
import com.android.dialer.callcomposer.camera.ImagePersistWorker;
import com.android.dialer.callcomposer.camera.camerafocus.FocusOverlayManager;
import com.android.dialer.callcomposer.camera.camerafocus.RenderOverlay;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DefaultDialerExecutorFactory;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class CameraManager implements FocusOverlayManager.Listener {
    private static final Camera.ShutterCallback DUMMY_SHUTTER_CALLBACK = new Camera.ShutterCallback() {
        public void onShutter() {
        }
    };
    private static CameraManager instance;
    /* access modifiers changed from: private */
    public Camera camera;
    /* access modifiers changed from: private */
    public int cameraIndex = -1;
    /* access modifiers changed from: private */
    public final Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
    /* access modifiers changed from: private */
    public CameraPreview cameraPreview;
    /* access modifiers changed from: private */
    public final FocusOverlayManager focusOverlayManager;
    private final boolean hasFrontAndBackCamera;
    private boolean isHardwareAccelerationSupported;
    /* access modifiers changed from: private */
    public CameraManagerListener listener;
    /* access modifiers changed from: private */
    public AsyncTask<Integer, Void, Camera> openCameraTask;
    /* access modifiers changed from: private */
    public boolean openRequested;
    private OrientationHandler orientationHandler;
    /* access modifiers changed from: private */
    public int pendingOpenCameraIndex = -1;
    /* access modifiers changed from: private */
    public int rotation;
    /* access modifiers changed from: private */
    public boolean takingPicture;

    public interface CameraManagerListener {
    }

    public interface MediaCallback {
    }

    private class OrientationHandler extends OrientationEventListener {
        OrientationHandler(Context context) {
            super(context);
        }

        public void onOrientationChanged(int i) {
            if (!CameraManager.this.takingPicture) {
                CameraManager cameraManager = CameraManager.this;
                Camera access$900 = cameraManager.camera;
                int access$1200 = CameraManager.this.getScreenRotation();
                int i2 = CameraManager.this.cameraInfo.orientation;
                boolean z = true;
                if (CameraManager.this.cameraInfo.facing != 1) {
                    z = false;
                }
                int unused = cameraManager.rotation = CameraManager.updateCameraRotation(access$900, access$1200, i2, z);
            }
        }
    }

    private static class SizeComparator implements Comparator<Camera.Size> {
        private final int maxHeight;
        private final int maxWidth;
        private final float targetAspectRatio;
        private final int targetPixels;

        public SizeComparator(int i, int i2, float f, int i3) {
            this.maxWidth = i;
            this.maxHeight = i2;
            this.targetAspectRatio = f;
            this.targetPixels = i3;
        }

        public int compare(Object obj, Object obj2) {
            Camera.Size size = (Camera.Size) obj;
            Camera.Size size2 = (Camera.Size) obj2;
            boolean z = false;
            boolean z2 = size.width <= this.maxWidth && size.height <= this.maxHeight;
            if (size2.width <= this.maxWidth && size2.height <= this.maxHeight) {
                z = true;
            }
            if (z2 == z) {
                float f = ((float) size.width) / ((float) size.height);
                float f2 = ((float) size2.width) / ((float) size2.height);
                float abs = Math.abs(f - this.targetAspectRatio);
                float abs2 = Math.abs(f2 - this.targetAspectRatio);
                if (abs == abs2) {
                    return Math.abs((size.width * size.height) - this.targetPixels) - Math.abs((size2.width * size2.height) - this.targetPixels);
                }
                if (abs - abs2 < 0.0f) {
                    return -1;
                }
            } else if (size.width <= this.maxWidth) {
                return -1;
            }
            return 1;
        }
    }

    private CameraManager() {
        Camera.CameraInfo cameraInfo2 = new Camera.CameraInfo();
        int numberOfCameras = Camera.getNumberOfCameras();
        boolean z = false;
        int i = 0;
        boolean z2 = false;
        boolean z3 = false;
        while (i < numberOfCameras) {
            try {
                Camera.getCameraInfo(i, cameraInfo2);
                if (cameraInfo2.facing == 1) {
                    z2 = true;
                } else if (cameraInfo2.facing == 0) {
                    z3 = true;
                }
                if (z2 && z3) {
                    break;
                }
                i++;
            } catch (RuntimeException e) {
                LogUtil.m7e("CameraManager.CameraManager", "Unable to load camera info", (Throwable) e);
            }
        }
        if (z2 && z3) {
            z = true;
        }
        this.hasFrontAndBackCamera = z;
        this.focusOverlayManager = new FocusOverlayManager(this, Looper.getMainLooper());
        this.isHardwareAccelerationSupported = true;
    }

    private Camera.Size chooseBestPreviewSize(Camera.Size size) {
        ArrayList arrayList = new ArrayList(this.camera.getParameters().getSupportedPreviewSizes());
        int i = size.width;
        int i2 = size.height;
        Collections.sort(arrayList, new SizeComparator(Integer.MAX_VALUE, Integer.MAX_VALUE, ((float) i) / ((float) i2), i * i2));
        return (Camera.Size) arrayList.get(0);
    }

    public static CameraManager get() {
        if (instance == null) {
            instance = new CameraManager();
        }
        return instance;
    }

    /* access modifiers changed from: private */
    public int getScreenRotation() {
        return ((WindowManager) this.cameraPreview.getContext().getSystemService(WindowManager.class)).getDefaultDisplay().getRotation();
    }

    private void logCameraSize(String str, Camera.Size size) {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13(str);
        outline13.append(size.width);
        outline13.append("x");
        outline13.append(size.height);
        outline13.append(" (");
        outline13.append(((float) size.width) / ((float) size.height));
        outline13.append(")");
        LogUtil.m9i("CameraManager.logCameraSize", outline13.toString(), new Object[0]);
    }

    /* access modifiers changed from: private */
    public void releaseCamera(final Camera camera2) {
        if (camera2 != null) {
            this.focusOverlayManager.onPreviewStopped();
            new AsyncTask<Void, Void, Void>() {
                /* access modifiers changed from: protected */
                public Object doInBackground(Object[] objArr) {
                    Void[] voidArr = (Void[]) objArr;
                    StringBuilder outline13 = GeneratedOutlineSupport.outline13("Releasing camera ");
                    outline13.append(CameraManager.this.cameraIndex);
                    outline13.toString();
                    camera2.release();
                    return null;
                }
            }.execute(new Void[0]);
        }
    }

    /* access modifiers changed from: private */
    public void setCamera(Camera camera2) {
        Camera camera3 = this.camera;
        if (camera3 != camera2) {
            releaseCamera(camera3);
            this.camera = camera2;
            tryShowPreview();
            CameraManagerListener cameraManagerListener = this.listener;
            if (cameraManagerListener != null) {
                ((CameraComposerFragment) cameraManagerListener).onCameraChanged();
            }
        }
    }

    private void tryShowPreview() {
        Camera camera2;
        if (this.cameraPreview == null || (camera2 = this.camera) == null) {
            OrientationHandler orientationHandler2 = this.orientationHandler;
            if (orientationHandler2 != null) {
                orientationHandler2.disable();
                this.orientationHandler = null;
            }
            this.focusOverlayManager.onPreviewStopped();
            return;
        }
        try {
            camera2.stopPreview();
            boolean z = false;
            if (!this.takingPicture) {
                this.rotation = updateCameraRotation(this.camera, getScreenRotation(), this.cameraInfo.orientation, this.cameraInfo.facing == 1);
            }
            Camera.Parameters parameters = this.camera.getParameters();
            Camera.Size pictureSize = this.camera.getParameters().getPictureSize();
            Camera.Size chooseBestPreviewSize = chooseBestPreviewSize(pictureSize);
            parameters.setPreviewSize(chooseBestPreviewSize.width, chooseBestPreviewSize.height);
            parameters.setPictureSize(pictureSize.width, pictureSize.height);
            logCameraSize("Setting preview size: ", chooseBestPreviewSize);
            logCameraSize("Setting picture size: ", pictureSize);
            this.cameraPreview.setSize(chooseBestPreviewSize, this.cameraInfo.orientation);
            Iterator<String> it = parameters.getSupportedFocusModes().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                String next = it.next();
                if (TextUtils.equals(next, "continuous-picture")) {
                    parameters.setFocusMode(next);
                    break;
                }
            }
            this.camera.setParameters(parameters);
            this.cameraPreview.startPreview(this.camera);
            this.camera.startPreview();
            this.camera.setAutoFocusMoveCallback(new Camera.AutoFocusMoveCallback() {
                public void onAutoFocusMoving(boolean z, Camera camera) {
                    CameraManager.this.focusOverlayManager.onAutoFocusMoving(z);
                }
            });
            this.focusOverlayManager.setParameters(this.camera.getParameters());
            FocusOverlayManager focusOverlayManager2 = this.focusOverlayManager;
            if (this.cameraInfo.facing == 0) {
                z = true;
            }
            focusOverlayManager2.setMirror(z);
            this.focusOverlayManager.onPreviewStarted();
            if (this.orientationHandler == null) {
                this.orientationHandler = new OrientationHandler(this.cameraPreview.getContext());
                this.orientationHandler.enable();
            }
        } catch (IOException e) {
            LogUtil.m7e("CameraManager.tryShowPreview", "IOException in CameraManager.tryShowPreview", (Throwable) e);
            CameraManagerListener cameraManagerListener = this.listener;
            if (cameraManagerListener != null) {
                ((CameraComposerFragment) cameraManagerListener).onCameraError(2, e);
            }
        } catch (RuntimeException e2) {
            LogUtil.m7e("CameraManager.tryShowPreview", "RuntimeException in CameraManager.tryShowPreview", (Throwable) e2);
            CameraManagerListener cameraManagerListener2 = this.listener;
            if (cameraManagerListener2 != null) {
                ((CameraComposerFragment) cameraManagerListener2).onCameraError(2, e2);
            }
        }
    }

    static int updateCameraRotation(Camera camera2, int i, int i2, boolean z) {
        int i3;
        Assert.isNotNull(camera2);
        boolean z2 = true;
        Assert.checkArgument(i2 % 90 == 0);
        if (i == 0) {
            i3 = 0;
        } else if (i == 1) {
            i3 = 90;
        } else if (i == 2) {
            i3 = 180;
        } else if (i == 3) {
            i3 = 270;
        } else {
            throw new IllegalStateException("Invalid surface rotation.");
        }
        if (!(i3 == 0 || i3 == 180)) {
            z2 = false;
        }
        if (!z2 && !z) {
            i3 += 180;
        }
        int i4 = (i3 + i2) % 360;
        if (!z2 || !z) {
            camera2.setDisplayOrientation(i4);
        } else {
            camera2.setDisplayOrientation((i4 + 180) % 360);
        }
        Camera.Parameters parameters = camera2.getParameters();
        parameters.setRotation(i4);
        camera2.setParameters(parameters);
        return i4;
    }

    public void autoFocus() {
        Camera camera2 = this.camera;
        if (camera2 != null) {
            try {
                camera2.autoFocus(new Camera.AutoFocusCallback() {
                    public void onAutoFocus(boolean z, Camera camera) {
                        CameraManager.this.focusOverlayManager.onAutoFocus(z, false);
                    }
                });
            } catch (RuntimeException e) {
                LogUtil.m7e("CameraManager.autoFocus", "RuntimeException in CameraManager.autoFocus", (Throwable) e);
                this.focusOverlayManager.onAutoFocus(false, false);
            }
        }
    }

    public void cancelAutoFocus() {
        Camera camera2 = this.camera;
        if (camera2 != null) {
            try {
                camera2.cancelAutoFocus();
            } catch (RuntimeException e) {
                LogUtil.m7e("CameraManager.cancelAutoFocus", "RuntimeException in CameraManager.cancelAutoFocus", (Throwable) e);
            }
        }
    }

    public boolean capture() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public void closeCamera() {
        this.openRequested = false;
        setCamera((Camera) null);
    }

    public int getCameraIndex() {
        return this.cameraIndex;
    }

    public Camera.CameraInfo getCameraInfo() {
        if (this.cameraIndex == -1) {
            return null;
        }
        return this.cameraInfo;
    }

    public boolean hasFrontAndBackCamera() {
        return this.hasFrontAndBackCamera;
    }

    public boolean isCameraAvailable() {
        return this.camera != null && !this.takingPicture && this.isHardwareAccelerationSupported;
    }

    /* access modifiers changed from: package-private */
    public void openCamera() {
        boolean z;
        if (this.cameraIndex == -1) {
            selectCamera(0);
        }
        this.openRequested = true;
        if (this.pendingOpenCameraIndex != this.cameraIndex && this.camera == null) {
            if (this.openCameraTask != null) {
                this.pendingOpenCameraIndex = -1;
                z = true;
            } else {
                z = false;
            }
            this.pendingOpenCameraIndex = this.cameraIndex;
            this.openCameraTask = new AsyncTask<Integer, Void, Camera>() {
                private Exception exception;

                private void cleanup() {
                    int unused = CameraManager.this.pendingOpenCameraIndex = -1;
                    if (CameraManager.this.openCameraTask == null || CameraManager.this.openCameraTask.getStatus() != AsyncTask.Status.PENDING) {
                        AsyncTask unused2 = CameraManager.this.openCameraTask = null;
                        return;
                    }
                    CameraManager.this.openCameraTask.execute(new Integer[]{Integer.valueOf(CameraManager.this.cameraIndex)});
                }

                /* access modifiers changed from: protected */
                public Object doInBackground(Object[] objArr) {
                    try {
                        "Opening camera " + CameraManager.this.cameraIndex;
                        return Camera.open(((Integer[]) objArr)[0].intValue());
                    } catch (Exception e) {
                        LogUtil.m7e("CameraManager.doInBackground", "Exception while opening camera", (Throwable) e);
                        this.exception = e;
                        return null;
                    }
                }

                /* access modifiers changed from: protected */
                public void onCancelled() {
                    super.onCancelled();
                    cleanup();
                }

                /* access modifiers changed from: protected */
                public void onPostExecute(Object obj) {
                    Camera camera = (Camera) obj;
                    if (CameraManager.this.openCameraTask != this || !CameraManager.this.openRequested) {
                        CameraManager.this.releaseCamera(camera);
                        cleanup();
                        return;
                    }
                    cleanup();
                    StringBuilder outline13 = GeneratedOutlineSupport.outline13("Opened camera ");
                    outline13.append(CameraManager.this.cameraIndex);
                    outline13.append(" ");
                    outline13.append(camera != null);
                    outline13.toString();
                    CameraManager.this.setCamera(camera);
                    if (camera == null) {
                        if (CameraManager.this.listener != null) {
                            ((CameraComposerFragment) CameraManager.this.listener).onCameraError(1, this.exception);
                        }
                        LogUtil.m8e("CameraManager.onPostExecute", "Error opening camera", new Object[0]);
                    }
                }
            };
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Start opening camera ");
            outline13.append(this.cameraIndex);
            outline13.toString();
            if (!z) {
                this.openCameraTask.execute(new Integer[]{Integer.valueOf(this.cameraIndex)});
            }
        }
    }

    public void resetCameraManager() {
        instance = null;
    }

    public void resetPreview() {
        this.camera.startPreview();
        CameraPreview cameraPreview2 = this.cameraPreview;
        if (cameraPreview2 != null) {
            cameraPreview2.setFocusable(true);
        }
    }

    public boolean selectCamera(int i) {
        try {
            if (this.cameraIndex >= 0 && this.cameraInfo.facing == i) {
                return true;
            }
            int numberOfCameras = Camera.getNumberOfCameras();
            Assert.checkState(numberOfCameras > 0);
            this.cameraIndex = -1;
            setCamera((Camera) null);
            Camera.CameraInfo cameraInfo2 = new Camera.CameraInfo();
            int i2 = 0;
            while (true) {
                if (i2 >= numberOfCameras) {
                    break;
                }
                Camera.getCameraInfo(i2, cameraInfo2);
                if (cameraInfo2.facing == i) {
                    this.cameraIndex = i2;
                    Camera.getCameraInfo(i2, this.cameraInfo);
                    break;
                }
                i2++;
            }
            if (this.cameraIndex < 0) {
                this.cameraIndex = 0;
                Camera.getCameraInfo(0, this.cameraInfo);
            }
            if (this.openRequested) {
                openCamera();
            }
            return true;
        } catch (RuntimeException e) {
            LogUtil.m7e("CameraManager.selectCamera", "RuntimeException in CameraManager.selectCamera", (Throwable) e);
            CameraManagerListener cameraManagerListener = this.listener;
            if (cameraManagerListener != null) {
                ((CameraComposerFragment) cameraManagerListener).onCameraError(1, e);
            }
            return false;
        }
    }

    public void selectCameraByIndex(int i) {
        if (this.cameraIndex != i) {
            try {
                this.cameraIndex = i;
                Camera.getCameraInfo(this.cameraIndex, this.cameraInfo);
                if (this.openRequested) {
                    openCamera();
                }
            } catch (RuntimeException e) {
                LogUtil.m7e("CameraManager.selectCameraByIndex", "RuntimeException in CameraManager.selectCameraByIndex", (Throwable) e);
                CameraManagerListener cameraManagerListener = this.listener;
                if (cameraManagerListener != null) {
                    ((CameraComposerFragment) cameraManagerListener).onCameraError(1, e);
                }
            }
        }
    }

    public void setFocusParameters() {
        Camera camera2 = this.camera;
        if (camera2 != null) {
            try {
                Camera.Parameters parameters = camera2.getParameters();
                parameters.setFocusMode(this.focusOverlayManager.getFocusMode());
                if (parameters.getMaxNumFocusAreas() > 0) {
                    parameters.setFocusAreas(this.focusOverlayManager.getFocusAreas());
                }
                parameters.setMeteringAreas(this.focusOverlayManager.getMeteringAreas());
                this.camera.setParameters(parameters);
            } catch (RuntimeException unused) {
                LogUtil.m8e("CameraManager.setFocusParameters", "RuntimeException in CameraManager setFocusParameters", new Object[0]);
            }
        }
    }

    public void setListener(CameraManagerListener cameraManagerListener) {
        CameraManagerListener cameraManagerListener2;
        Assert.isMainThread();
        this.listener = cameraManagerListener;
        if (!this.isHardwareAccelerationSupported && (cameraManagerListener2 = this.listener) != null) {
            ((CameraComposerFragment) cameraManagerListener2).onCameraError(3, (Exception) null);
        }
    }

    public void setRenderOverlay(RenderOverlay renderOverlay) {
        this.focusOverlayManager.setFocusRenderer(renderOverlay != null ? renderOverlay.getPieRenderer() : null);
    }

    /* access modifiers changed from: package-private */
    public void setSurface(CameraPreview cameraPreview2) {
        if (cameraPreview2 != this.cameraPreview) {
            if (cameraPreview2 != null) {
                Assert.checkArgument(cameraPreview2.isValid());
                cameraPreview2.setOnTouchListener(new View.OnTouchListener() {
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        if ((motionEvent.getActionMasked() & 1) == 1) {
                            CameraManager.this.focusOverlayManager.setPreviewSize(view.getWidth(), view.getHeight());
                            CameraManager.this.focusOverlayManager.onSingleTapUp(view.getLeft() + ((int) motionEvent.getX()), view.getTop() + ((int) motionEvent.getY()));
                        }
                        view.performClick();
                        return true;
                    }
                });
            }
            this.cameraPreview = cameraPreview2;
            tryShowPreview();
        }
    }

    public void swapCamera() {
        int i = 0;
        Assert.checkState(this.cameraIndex >= 0);
        if (this.cameraInfo.facing != 1) {
            i = 1;
        }
        selectCamera(i);
    }

    public void takePicture(final float f, final MediaCallback mediaCallback) {
        Assert.checkState(!this.takingPicture);
        Assert.isNotNull(mediaCallback);
        this.cameraPreview.setFocusable(false);
        this.focusOverlayManager.cancelAutoFocus();
        if (this.camera == null) {
            ((CameraComposerFragment) mediaCallback).onMediaFailed((Exception) null);
            return;
        }
        C04034 r0 = new Camera.PictureCallback() {
            public void onPictureTaken(byte[] bArr, Camera camera) {
                int i;
                int i2;
                boolean unused = CameraManager.this.takingPicture = false;
                if (CameraManager.this.camera != camera) {
                    ((CameraComposerFragment) mediaCallback).onMediaInfo(1);
                } else if (bArr == null) {
                    ((CameraComposerFragment) mediaCallback).onMediaInfo(2);
                } else {
                    Camera.Size pictureSize = camera.getParameters().getPictureSize();
                    if (CameraManager.this.rotation == 90 || CameraManager.this.rotation == 270) {
                        i2 = pictureSize.height;
                        i = pictureSize.width;
                    } else {
                        i2 = pictureSize.width;
                        i = pictureSize.height;
                    }
                    int i3 = i;
                    StringBuilder outline13 = GeneratedOutlineSupport.outline13("taken picture size: ");
                    outline13.append(bArr.length);
                    outline13.append(" bytes");
                    LogUtil.m9i("CameraManager.onPictureTaken", outline13.toString(), new Object[0]);
                    DefaultDialerExecutorFactory defaultDialerExecutorFactory = (DefaultDialerExecutorFactory) DialerExecutorComponent.get(CameraManager.this.cameraPreview.getContext()).dialerExecutorFactory();
                    defaultDialerExecutorFactory.createNonUiTaskBuilder(new ImagePersistWorker(i2, i3, f, bArr, CameraManager.this.cameraPreview.getContext())).onSuccess(new DialerExecutor.SuccessListener() {
                        public final void onSuccess(Object obj) {
                            ((CameraComposerFragment) CameraManager.MediaCallback.this).onMediaReady(((ImagePersistWorker.Result) obj).getUri(), "image/jpeg", ((ImagePersistWorker.Result) obj).getWidth(), ((ImagePersistWorker.Result) obj).getHeight());
                        }
                    }).onFailure(new DialerExecutor.FailureListener() {
                        public final void onFailure(Throwable th) {
                            ((CameraComposerFragment) CameraManager.MediaCallback.this).onMediaFailed(new Exception("Persisting image failed", th));
                        }
                    }).build().executeSerial(null);
                }
            }
        };
        this.takingPicture = true;
        try {
            this.camera.takePicture(DUMMY_SHUTTER_CALLBACK, (Camera.PictureCallback) null, (Camera.PictureCallback) null, r0);
        } catch (RuntimeException e) {
            LogUtil.m7e("CameraManager.takePicture", "RuntimeException in CameraManager.takePicture", (Throwable) e);
            this.takingPicture = false;
            CameraManagerListener cameraManagerListener = this.listener;
            if (cameraManagerListener != null) {
                ((CameraComposerFragment) cameraManagerListener).onCameraError(4, e);
            }
        }
    }
}
