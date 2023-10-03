package com.android.dialer.callcomposer.camera.camerafocus;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.ArrayList;
import java.util.List;

public class FocusOverlayManager {
    private boolean aeAwbLock;
    private List<Camera.Area> focusArea;
    private boolean focusAreaSupported;
    private String focusMode;
    private Handler handler;
    private boolean initialized;
    private Listener listener;
    private boolean lockAeAwbNeeded;
    private Matrix matrix;
    private List<Camera.Area> meteringArea;
    private boolean meteringAreaSupported;
    private boolean mirror;
    private Camera.Parameters parameters;
    private PieRenderer pieRenderer;
    private int previewHeight;
    private int previewWidth;
    private int state = 0;

    public interface Listener {
        void autoFocus();

        void cancelAutoFocus();

        boolean capture();

        void setFocusParameters();
    }

    private class MainHandler extends Handler {
        public MainHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            if (message.what == 0) {
                FocusOverlayManager.this.cancelAutoFocus();
            }
        }
    }

    public FocusOverlayManager(Listener listener2, Looper looper) {
        this.handler = new MainHandler(looper);
        this.matrix = new Matrix();
        this.listener = listener2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001a, code lost:
        if (r6 < 0) goto L_0x001c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0020  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void calculateTapArea(int r3, int r4, float r5, int r6, int r7, int r8, int r9, android.graphics.Rect r10) {
        /*
            r2 = this;
            float r3 = (float) r3
            float r3 = r3 * r5
            int r3 = (int) r3
            float r4 = (float) r4
            float r4 = r4 * r5
            int r4 = (int) r4
            int r8 = r8 - r3
            r5 = 1
            r0 = 0
            if (r8 <= 0) goto L_0x001c
            int r1 = r3 / 2
            int r6 = r6 - r1
            if (r8 < 0) goto L_0x0012
            r1 = r5
            goto L_0x0013
        L_0x0012:
            r1 = r0
        L_0x0013:
            com.android.dialer.common.Assert.checkArgument(r1)
            if (r6 <= r8) goto L_0x001a
            r6 = r8
            goto L_0x001d
        L_0x001a:
            if (r6 >= 0) goto L_0x001d
        L_0x001c:
            r6 = r0
        L_0x001d:
            int r9 = r9 - r4
            if (r9 <= 0) goto L_0x0032
            int r8 = r4 / 2
            int r7 = r7 - r8
            if (r9 < 0) goto L_0x0026
            goto L_0x0027
        L_0x0026:
            r5 = r0
        L_0x0027:
            com.android.dialer.common.Assert.checkArgument(r5)
            if (r7 <= r9) goto L_0x002e
            r0 = r9
            goto L_0x0032
        L_0x002e:
            if (r7 >= 0) goto L_0x0031
            goto L_0x0032
        L_0x0031:
            r0 = r7
        L_0x0032:
            android.graphics.RectF r5 = new android.graphics.RectF
            float r7 = (float) r6
            float r8 = (float) r0
            int r6 = r6 + r3
            float r3 = (float) r6
            int r0 = r0 + r4
            float r4 = (float) r0
            r5.<init>(r7, r8, r3, r4)
            android.graphics.Matrix r2 = r2.matrix
            r2.mapRect(r5)
            float r2 = r5.left
            int r2 = java.lang.Math.round(r2)
            r10.left = r2
            float r2 = r5.top
            int r2 = java.lang.Math.round(r2)
            r10.top = r2
            float r2 = r5.right
            int r2 = java.lang.Math.round(r2)
            r10.right = r2
            float r2 = r5.bottom
            int r2 = java.lang.Math.round(r2)
            r10.bottom = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.callcomposer.camera.camerafocus.FocusOverlayManager.calculateTapArea(int, int, float, int, int, int, int, android.graphics.Rect):void");
    }

    private boolean isSupported(String str, List<String> list) {
        return list != null && list.indexOf(str) >= 0;
    }

    private void setMatrix() {
        if (this.previewWidth != 0 && this.previewHeight != 0) {
            Matrix matrix2 = new Matrix();
            boolean z = this.mirror;
            int i = this.previewWidth;
            int i2 = this.previewHeight;
            matrix2.setScale(z ? -1.0f : 1.0f, 1.0f);
            float f = (float) i;
            float f2 = (float) i2;
            matrix2.postScale(f / 2000.0f, f2 / 2000.0f);
            matrix2.postTranslate(f / 2.0f, f2 / 2.0f);
            matrix2.invert(this.matrix);
            this.initialized = this.pieRenderer != null;
        }
    }

    private void updateFocusUI() {
        if (this.initialized) {
            PieRenderer pieRenderer2 = this.pieRenderer;
            int i = this.state;
            if (i == 0) {
                if (this.focusArea == null) {
                    pieRenderer2.clear();
                } else {
                    pieRenderer2.showStart();
                }
            } else if (i == 1 || i == 2) {
                pieRenderer2.showStart();
            } else if ("continuous-picture".equals(this.focusMode)) {
                pieRenderer2.showSuccess(false);
            } else {
                int i2 = this.state;
                if (i2 == 3) {
                    pieRenderer2.showSuccess(false);
                } else if (i2 == 4) {
                    pieRenderer2.showFail(false);
                }
            }
        }
    }

    public void cancelAutoFocus() {
        if (this.initialized) {
            this.pieRenderer.clear();
            this.focusArea = null;
            this.meteringArea = null;
        }
        this.listener.cancelAutoFocus();
        this.state = 0;
        updateFocusUI();
        this.handler.removeMessages(0);
    }

    public List<Camera.Area> getFocusAreas() {
        return this.focusArea;
    }

    public String getFocusMode() {
        List<String> supportedFocusModes = this.parameters.getSupportedFocusModes();
        if (!this.focusAreaSupported || this.focusArea == null) {
            this.focusMode = "continuous-picture";
        } else {
            this.focusMode = "auto";
        }
        if (!isSupported(this.focusMode, supportedFocusModes)) {
            if (isSupported("auto", this.parameters.getSupportedFocusModes())) {
                this.focusMode = "auto";
            } else {
                this.focusMode = this.parameters.getFocusMode();
            }
        }
        return this.focusMode;
    }

    public List<Camera.Area> getMeteringAreas() {
        return this.meteringArea;
    }

    public void onAutoFocus(boolean z, boolean z2) {
        int i = this.state;
        if (i == 2) {
            if (z) {
                this.state = 3;
            } else {
                this.state = 4;
            }
            updateFocusUI();
            if (this.listener.capture()) {
                this.state = 0;
                this.handler.removeMessages(0);
            }
        } else if (i == 1) {
            if (z) {
                this.state = 3;
            } else {
                this.state = 4;
            }
            updateFocusUI();
            if (this.focusArea != null) {
                this.handler.sendEmptyMessageDelayed(0, 3000);
            }
            if (z2 && this.lockAeAwbNeeded && !this.aeAwbLock) {
                this.aeAwbLock = true;
                this.listener.setFocusParameters();
            }
        }
    }

    public void onAutoFocusMoving(boolean z) {
        if (!this.initialized || this.state != 0) {
            return;
        }
        if (z) {
            this.pieRenderer.showStart();
        } else {
            this.pieRenderer.showSuccess(true);
        }
    }

    public void onPreviewStarted() {
        this.state = 0;
    }

    public void onPreviewStopped() {
        this.state = 0;
        if (this.initialized) {
            this.pieRenderer.clear();
            this.focusArea = null;
            this.meteringArea = null;
        }
        updateFocusUI();
    }

    public void onSingleTapUp(int i, int i2) {
        int i3;
        if (this.initialized && (i3 = this.state) != 2) {
            if (this.focusArea != null && (i3 == 1 || i3 == 3 || i3 == 4)) {
                cancelAutoFocus();
            }
            int size = this.pieRenderer.getSize();
            int size2 = this.pieRenderer.getSize();
            if (size != 0 && this.pieRenderer.getWidth() != 0 && this.pieRenderer.getHeight() != 0) {
                int i4 = this.previewWidth;
                int i5 = this.previewHeight;
                if (this.focusAreaSupported) {
                    if (this.focusArea == null) {
                        this.focusArea = new ArrayList();
                        this.focusArea.add(new Camera.Area(new Rect(), 1));
                    }
                    calculateTapArea(size, size2, 1.0f, i, i2, i4, i5, this.focusArea.get(0).rect);
                }
                if (this.meteringAreaSupported) {
                    if (this.meteringArea == null) {
                        this.meteringArea = new ArrayList();
                        this.meteringArea.add(new Camera.Area(new Rect(), 1));
                    }
                    calculateTapArea(size, size2, 1.5f, i, i2, i4, i5, this.meteringArea.get(0).rect);
                }
                this.pieRenderer.setFocus(i, i2);
                this.listener.setFocusParameters();
                if (this.focusAreaSupported) {
                    this.listener.autoFocus();
                    this.state = 1;
                    updateFocusUI();
                    this.handler.removeMessages(0);
                    return;
                }
                updateFocusUI();
                this.handler.removeMessages(0);
                this.handler.sendEmptyMessageDelayed(0, 3000);
            }
        }
    }

    public void setFocusRenderer(PieRenderer pieRenderer2) {
        this.pieRenderer = pieRenderer2;
        this.initialized = this.matrix != null;
    }

    public void setMirror(boolean z) {
        this.mirror = z;
        setMatrix();
    }

    public void setParameters(Camera.Parameters parameters2) {
        if (parameters2 != null) {
            this.parameters = parameters2;
            boolean z = true;
            this.focusAreaSupported = parameters2.getMaxNumFocusAreas() > 0 && isSupported("auto", parameters2.getSupportedFocusModes());
            this.meteringAreaSupported = parameters2.getMaxNumMeteringAreas() > 0;
            if (!"true".equals(this.parameters.get("auto-exposure-lock-supported")) && !"true".equals(this.parameters.get("auto-whitebalance-lock-supported"))) {
                z = false;
            }
            this.lockAeAwbNeeded = z;
        }
    }

    public void setPreviewSize(int i, int i2) {
        if (this.previewWidth != i || this.previewHeight != i2) {
            this.previewWidth = i;
            this.previewHeight = i2;
            setMatrix();
        }
    }
}
