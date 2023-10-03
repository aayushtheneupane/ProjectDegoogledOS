package com.android.settings.wifi.qrcode;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Size;
import android.view.WindowManager;
import com.android.settings.wifi.qrcode.QrCamera;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class QrCamera extends Handler {
    private static long AUTOFOCUS_INTERVAL_MS = 1500;
    private static List<BarcodeFormat> FORMATS = new ArrayList();
    private static Map<DecodeHintType, List<BarcodeFormat>> HINTS = new ArrayMap();
    private static double MAX_RATIO_DIFF = 0.1d;
    /* access modifiers changed from: private */
    public Camera mCamera;
    /* access modifiers changed from: private */
    public int mCameraOrientation;
    private WeakReference<Context> mContext;
    private DecodingTask mDecodeTask;
    private Camera.Parameters mParameters;
    private Size mPreviewSize;
    /* access modifiers changed from: private */
    public MultiFormatReader mReader = new MultiFormatReader();
    /* access modifiers changed from: private */
    public ScannerCallback mScannerCallback;

    public interface ScannerCallback {
        Rect getFramePosition(Size size, int i);

        Size getViewSize();

        void handleCameraFailure();

        void handleSuccessfulResult(String str);

        boolean isValid(String str);

        void setTransform(Matrix matrix);
    }

    private double getRatio(double d, double d2) {
        return d < d2 ? d / d2 : d2 / d;
    }

    static {
        FORMATS.add(BarcodeFormat.QR_CODE);
        HINTS.put(DecodeHintType.POSSIBLE_FORMATS, FORMATS);
    }

    public QrCamera(Context context, ScannerCallback scannerCallback) {
        this.mContext = new WeakReference<>(context);
        this.mScannerCallback = scannerCallback;
        this.mReader.setHints(HINTS);
    }

    public void start(SurfaceTexture surfaceTexture) {
        if (this.mDecodeTask == null) {
            this.mDecodeTask = new DecodingTask(surfaceTexture);
            this.mDecodeTask.executeOnExecutor(Executors.newSingleThreadExecutor(), new Void[0]);
        }
    }

    public void stop() {
        removeMessages(1);
        DecodingTask decodingTask = this.mDecodeTask;
        if (decodingTask != null) {
            decodingTask.cancel(true);
            this.mDecodeTask = null;
        }
        Camera camera = this.mCamera;
        if (camera != null) {
            camera.stopPreview();
        }
    }

    /* access modifiers changed from: private */
    public void setCameraParameter() {
        this.mParameters = this.mCamera.getParameters();
        this.mPreviewSize = getBestPreviewSize(this.mParameters);
        this.mParameters.setPreviewSize(this.mPreviewSize.getWidth(), this.mPreviewSize.getHeight());
        Size bestPictureSize = getBestPictureSize(this.mParameters);
        this.mParameters.setPictureSize(bestPictureSize.getWidth(), bestPictureSize.getHeight());
        List<String> supportedFlashModes = this.mParameters.getSupportedFlashModes();
        if (supportedFlashModes != null && supportedFlashModes.contains("off")) {
            this.mParameters.setFlashMode("off");
        }
        List<String> supportedFocusModes = this.mParameters.getSupportedFocusModes();
        if (supportedFocusModes.contains("continuous-picture")) {
            this.mParameters.setFocusMode("continuous-picture");
        } else if (supportedFocusModes.contains("auto")) {
            this.mParameters.setFocusMode("auto");
        }
        this.mCamera.setParameters(this.mParameters);
    }

    /* access modifiers changed from: private */
    public boolean startPreview() {
        int i = 0;
        if (this.mContext.get() == null) {
            return false;
        }
        int rotation = ((WindowManager) ((Context) this.mContext.get()).getSystemService("window")).getDefaultDisplay().getRotation();
        if (rotation != 0) {
            if (rotation == 1) {
                i = 90;
            } else if (rotation == 2) {
                i = 180;
            } else if (rotation == 3) {
                i = 270;
            }
        }
        this.mCamera.setDisplayOrientation(((this.mCameraOrientation - i) + 360) % 360);
        this.mCamera.startPreview();
        if (this.mParameters.getFocusMode() == "auto") {
            this.mCamera.autoFocus((Camera.AutoFocusCallback) null);
            sendMessageDelayed(obtainMessage(1), AUTOFOCUS_INTERVAL_MS);
        }
        return true;
    }

    private class DecodingTask extends AsyncTask<Void, Void, String> {
        private QrYuvLuminanceSource mImage;
        private SurfaceTexture mSurface;

        private DecodingTask(SurfaceTexture surfaceTexture) {
            this.mSurface = surfaceTexture;
        }

        /* access modifiers changed from: protected */
        public String doInBackground(Void... voidArr) {
            Result result;
            if (!initCamera(this.mSurface)) {
                return null;
            }
            Semaphore semaphore = new Semaphore(0);
            while (true) {
                QrCamera.this.mCamera.setOneShotPreviewCallback(new Camera.PreviewCallback(semaphore) {
                    private final /* synthetic */ Semaphore f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void onPreviewFrame(byte[] bArr, Camera camera) {
                        QrCamera.DecodingTask.this.lambda$doInBackground$0$QrCamera$DecodingTask(this.f$1, bArr, camera);
                    }
                });
                try {
                    semaphore.acquire();
                    result = QrCamera.this.mReader.decodeWithState(new BinaryBitmap(new HybridBinarizer(this.mImage)));
                    QrCamera.this.mReader.reset();
                } catch (ReaderException unused) {
                    QrCamera.this.mReader.reset();
                    result = null;
                } catch (InterruptedException unused2) {
                    Thread.currentThread().interrupt();
                    return null;
                } catch (Throwable th) {
                    QrCamera.this.mReader.reset();
                    throw th;
                }
                if (result != null && QrCamera.this.mScannerCallback.isValid(result.getText())) {
                    return result.getText();
                }
            }
        }

        public /* synthetic */ void lambda$doInBackground$0$QrCamera$DecodingTask(Semaphore semaphore, byte[] bArr, Camera camera) {
            this.mImage = QrCamera.this.getFrameImage(bArr);
            semaphore.release();
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(String str) {
            if (str != null) {
                QrCamera.this.mScannerCallback.handleSuccessfulResult(str);
            }
        }

        private boolean initCamera(SurfaceTexture surfaceTexture) {
            int numberOfCameras = Camera.getNumberOfCameras();
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            int i = 0;
            while (true) {
                if (i >= numberOfCameras) {
                    break;
                }
                try {
                    Camera.getCameraInfo(i, cameraInfo);
                    if (cameraInfo.facing == 0) {
                        Camera unused = QrCamera.this.mCamera = Camera.open(i);
                        QrCamera.this.mCamera.setPreviewTexture(surfaceTexture);
                        int unused2 = QrCamera.this.mCameraOrientation = cameraInfo.orientation;
                        break;
                    }
                    i++;
                } catch (IOException unused3) {
                    Log.e("QrCamera", "Error to init Camera");
                    Camera unused4 = QrCamera.this.mCamera = null;
                    QrCamera.this.mScannerCallback.handleCameraFailure();
                    return false;
                }
            }
            if (QrCamera.this.mCamera == null) {
                Log.e("QrCamera", "Cannot find available back camera.");
                QrCamera.this.mScannerCallback.handleCameraFailure();
                return false;
            }
            QrCamera.this.setCameraParameter();
            QrCamera.this.setTransformationMatrix(QrCamera.this.mScannerCallback.getViewSize());
            if (QrCamera.this.startPreview()) {
                return true;
            }
            Log.e("QrCamera", "Error to init Camera");
            Camera unused5 = QrCamera.this.mCamera = null;
            QrCamera.this.mScannerCallback.handleCameraFailure();
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void setTransformationMatrix(Size size) {
        float f;
        getRatio((double) size.getWidth(), (double) size.getHeight());
        boolean z = true;
        if (((Context) this.mContext.get()).getResources().getConfiguration().orientation != 1) {
            z = false;
        }
        Size size2 = this.mPreviewSize;
        int width = z ? size2.getWidth() : size2.getHeight();
        int height = z ? this.mPreviewSize.getHeight() : this.mPreviewSize.getWidth();
        float ratio = (float) getRatio((double) width, (double) height);
        float f2 = 1.0f;
        if (width > height) {
            f2 = 1.0f / ratio;
            f = 1.0f;
        } else {
            f = 1.0f / ratio;
        }
        Matrix matrix = new Matrix();
        matrix.setScale(f, f2);
        this.mScannerCallback.setTransform(matrix);
    }

    /* access modifiers changed from: private */
    public QrYuvLuminanceSource getFrameImage(byte[] bArr) {
        Rect framePosition = this.mScannerCallback.getFramePosition(this.mPreviewSize, this.mCameraOrientation);
        return (QrYuvLuminanceSource) new QrYuvLuminanceSource(bArr, this.mPreviewSize.getWidth(), this.mPreviewSize.getHeight()).crop(framePosition.left, framePosition.top, framePosition.width(), framePosition.height());
    }

    public void handleMessage(Message message) {
        if (message.what != 1) {
            Log.d("QrCamera", "Unexpected Message: " + message.what);
            return;
        }
        this.mCamera.autoFocus((Camera.AutoFocusCallback) null);
        sendMessageDelayed(obtainMessage(1), AUTOFOCUS_INTERVAL_MS);
    }

    private Size getBestPreviewSize(Camera.Parameters parameters) {
        Size viewSize = this.mScannerCallback.getViewSize();
        double ratio = getRatio((double) viewSize.getWidth(), (double) viewSize.getHeight());
        Size size = new Size(0, 0);
        double d = 0.0d;
        for (Camera.Size next : parameters.getSupportedPreviewSizes()) {
            double ratio2 = getRatio((double) next.width, (double) next.height);
            if (next.height * next.width > size.getWidth() * size.getHeight() && (Math.abs(d - ratio) / ratio > 0.1d || Math.abs(ratio2 - ratio) / ratio <= 0.1d)) {
                size = new Size(next.width, next.height);
                d = getRatio((double) next.width, (double) next.height);
            }
        }
        return size;
    }

    private Size getBestPictureSize(Camera.Parameters parameters) {
        Camera.Size previewSize = parameters.getPreviewSize();
        double ratio = getRatio((double) previewSize.width, (double) previewSize.height);
        ArrayList<Size> arrayList = new ArrayList<>();
        ArrayList arrayList2 = new ArrayList();
        for (Camera.Size next : parameters.getSupportedPictureSizes()) {
            double ratio2 = getRatio((double) next.width, (double) next.height);
            if (ratio2 == ratio) {
                arrayList.add(new Size(next.width, next.height));
            } else if (Math.abs(ratio2 - ratio) < MAX_RATIO_DIFF) {
                arrayList2.add(new Size(next.width, next.height));
            }
        }
        if (arrayList.size() == 0 && arrayList2.size() == 0) {
            Log.d("QrCamera", "No proper picture size, return default picture size");
            Camera.Size pictureSize = parameters.getPictureSize();
            return new Size(pictureSize.width, pictureSize.height);
        }
        if (arrayList.size() == 0) {
            arrayList = arrayList2;
        }
        int i = Integer.MAX_VALUE;
        Size size = null;
        int i2 = previewSize.width * previewSize.height;
        for (Size size2 : arrayList) {
            int abs = Math.abs((size2.getWidth() * size2.getHeight()) - i2);
            if (abs < i) {
                size = size2;
                i = abs;
            }
        }
        return size;
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public void decodeImage(BinaryBitmap binaryBitmap) {
        Result result;
        try {
            result = this.mReader.decodeWithState(binaryBitmap);
            this.mReader.reset();
        } catch (ReaderException unused) {
            this.mReader.reset();
            result = null;
        } catch (Throwable th) {
            this.mReader.reset();
            throw th;
        }
        if (result != null) {
            this.mScannerCallback.handleSuccessfulResult(result.getText());
        }
    }

    public boolean isDecodeTaskAlive() {
        return this.mDecodeTask != null;
    }
}
