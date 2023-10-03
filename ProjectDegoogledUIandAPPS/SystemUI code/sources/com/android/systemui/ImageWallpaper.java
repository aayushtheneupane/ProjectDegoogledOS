package com.android.systemui;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Rect;
import android.os.HandlerThread;
import android.os.Trace;
import android.service.wallpaper.WallpaperService;
import android.util.Log;
import android.util.Size;
import android.view.DisplayInfo;
import android.view.SurfaceHolder;
import com.android.internal.annotations.VisibleForTesting;
import com.android.systemui.ImageWallpaper;
import com.android.systemui.glwallpaper.EglHelper;
import com.android.systemui.glwallpaper.GLWallpaperRenderer;
import com.android.systemui.glwallpaper.ImageWallpaperRenderer;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.phone.DozeParameters;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class ImageWallpaper extends WallpaperService {
    /* access modifiers changed from: private */
    public static final String TAG = "ImageWallpaper";
    /* access modifiers changed from: private */
    public HandlerThread mWorker;

    public void onCreate() {
        super.onCreate();
        this.mWorker = new HandlerThread(TAG);
        this.mWorker.start();
    }

    public WallpaperService.Engine onCreateEngine() {
        return new GLEngine(this);
    }

    public void onDestroy() {
        super.onDestroy();
        this.mWorker.quitSafely();
        this.mWorker = null;
    }

    class GLEngine extends WallpaperService.Engine implements GLWallpaperRenderer.SurfaceProxy, StatusBarStateController.StateListener {
        @VisibleForTesting
        static final int MIN_SURFACE_HEIGHT = 64;
        @VisibleForTesting
        static final int MIN_SURFACE_WIDTH = 64;
        private StatusBarStateController mController;
        private final DisplayInfo mDisplayInfo = new DisplayInfo();
        private final boolean mDisplayNeedsBlanking;
        private EglHelper mEglHelper;
        private final Runnable mFinishRenderingTask = new Runnable() {
            public final void run() {
                ImageWallpaper.GLEngine.this.finishRendering();
            }
        };
        private final boolean mIsHighEndGfx = ActivityManager.isHighEndGfx();
        private final Object mMonitor = new Object();
        private boolean mNeedRedraw;
        private final boolean mNeedTransition;
        private GLWallpaperRenderer mRenderer;
        private boolean mShouldStopTransition;
        private boolean mWaitingForRendering;

        GLEngine(Context context) {
            super(ImageWallpaper.this);
            this.mDisplayNeedsBlanking = DozeParameters.getInstance(context).getDisplayNeedsBlanking();
            this.mNeedTransition = this.mIsHighEndGfx && !this.mDisplayNeedsBlanking;
            this.mController = (StatusBarStateController) Dependency.get(StatusBarStateController.class);
            StatusBarStateController statusBarStateController = this.mController;
            if (statusBarStateController != null) {
                statusBarStateController.addCallback(this);
            }
        }

        public void onCreate(SurfaceHolder surfaceHolder) {
            this.mEglHelper = new EglHelper();
            this.mRenderer = new ImageWallpaperRenderer(getDisplayContext(), this);
            getDisplayContext().getDisplay().getDisplayInfo(this.mDisplayInfo);
            setFixedSizeAllowed(true);
            setOffsetNotificationsEnabled(true);
            updateSurfaceSize();
        }

        private void updateSurfaceSize() {
            SurfaceHolder surfaceHolder = getSurfaceHolder();
            Size reportSurfaceSize = this.mRenderer.reportSurfaceSize();
            surfaceHolder.setFixedSize(Math.max(64, reportSurfaceSize.getWidth()), Math.max(64, reportSurfaceSize.getHeight()));
        }

        /* access modifiers changed from: package-private */
        @VisibleForTesting
        public boolean checkIfShouldStopTransition() {
            boolean z = getDisplayContext().getResources().getConfiguration().orientation == 1;
            Rect surfaceFrame = getSurfaceHolder().getSurfaceFrame();
            int width = surfaceFrame.width();
            int height = surfaceFrame.height();
            DisplayInfo displayInfo = this.mDisplayInfo;
            int i = z ? displayInfo.logicalWidth : displayInfo.logicalHeight;
            int i2 = z ? this.mDisplayInfo.logicalHeight : this.mDisplayInfo.logicalWidth;
            if (!this.mNeedTransition) {
                return false;
            }
            if (width < i || height < i2) {
                return true;
            }
            return false;
        }

        public void onOffsetsChanged(float f, float f2, float f3, float f4, int i, int i2) {
            if (ImageWallpaper.this.mWorker != null) {
                ImageWallpaper.this.mWorker.getThreadHandler().post(new Runnable(f, f2) {
                    private final /* synthetic */ float f$1;
                    private final /* synthetic */ float f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final void run() {
                        ImageWallpaper.GLEngine.this.lambda$onOffsetsChanged$0$ImageWallpaper$GLEngine(this.f$1, this.f$2);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onOffsetsChanged$0$ImageWallpaper$GLEngine(float f, float f2) {
            this.mRenderer.updateOffsets(f, f2);
        }

        public void onAmbientModeChanged(boolean z, long j) {
            if (ImageWallpaper.this.mWorker != null && this.mNeedTransition) {
                long j2 = this.mShouldStopTransition ? 0 : j;
                String access$100 = ImageWallpaper.TAG;
                Log.d(access$100, "onAmbientModeChanged: inAmbient=" + z + ", duration=" + j2 + ", mShouldStopTransition=" + this.mShouldStopTransition);
                ImageWallpaper.this.mWorker.getThreadHandler().post(new Runnable(z, j2) {
                    private final /* synthetic */ boolean f$1;
                    private final /* synthetic */ long f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final void run() {
                        ImageWallpaper.GLEngine.this.lambda$onAmbientModeChanged$1$ImageWallpaper$GLEngine(this.f$1, this.f$2);
                    }
                });
                if (z && j == 0) {
                    waitForBackgroundRendering();
                }
            }
        }

        public /* synthetic */ void lambda$onAmbientModeChanged$1$ImageWallpaper$GLEngine(boolean z, long j) {
            this.mRenderer.updateAmbientMode(z, j);
        }

        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0022 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void waitForBackgroundRendering() {
            /*
                r7 = this;
                java.lang.Object r0 = r7.mMonitor
                monitor-enter(r0)
                r1 = 0
                r2 = 1
                r7.mWaitingForRendering = r2     // Catch:{ InterruptedException -> 0x0022, all -> 0x0025 }
                r3 = r2
            L_0x0008:
                boolean r4 = r7.mWaitingForRendering     // Catch:{ InterruptedException -> 0x0022, all -> 0x0025 }
                if (r4 == 0) goto L_0x0022
                java.lang.Object r4 = r7.mMonitor     // Catch:{ InterruptedException -> 0x0022, all -> 0x0025 }
                r5 = 100
                r4.wait(r5)     // Catch:{ InterruptedException -> 0x0022, all -> 0x0025 }
                boolean r4 = r7.mWaitingForRendering     // Catch:{ InterruptedException -> 0x0022, all -> 0x0025 }
                r5 = 10
                if (r3 >= r5) goto L_0x001b
                r5 = r2
                goto L_0x001c
            L_0x001b:
                r5 = r1
            L_0x001c:
                r4 = r4 & r5
                r7.mWaitingForRendering = r4     // Catch:{ InterruptedException -> 0x0022, all -> 0x0025 }
                int r3 = r3 + 1
                goto L_0x0008
            L_0x0022:
                r7.mWaitingForRendering = r1     // Catch:{ all -> 0x002b }
                goto L_0x0029
            L_0x0025:
                r2 = move-exception
                r7.mWaitingForRendering = r1     // Catch:{ all -> 0x002b }
                throw r2     // Catch:{ all -> 0x002b }
            L_0x0029:
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                return
            L_0x002b:
                r7 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.ImageWallpaper.GLEngine.waitForBackgroundRendering():void");
        }

        public void onDestroy() {
            StatusBarStateController statusBarStateController = this.mController;
            if (statusBarStateController != null) {
                statusBarStateController.removeCallback(this);
            }
            this.mController = null;
            ImageWallpaper.this.mWorker.getThreadHandler().post(new Runnable() {
                public final void run() {
                    ImageWallpaper.GLEngine.this.lambda$onDestroy$2$ImageWallpaper$GLEngine();
                }
            });
        }

        public /* synthetic */ void lambda$onDestroy$2$ImageWallpaper$GLEngine() {
            this.mRenderer.finish();
            this.mRenderer = null;
            this.mEglHelper.finish();
            this.mEglHelper = null;
        }

        public void onSurfaceCreated(SurfaceHolder surfaceHolder) {
            if (ImageWallpaper.this.mWorker != null) {
                this.mShouldStopTransition = checkIfShouldStopTransition();
                ImageWallpaper.this.mWorker.getThreadHandler().post(new Runnable(surfaceHolder) {
                    private final /* synthetic */ SurfaceHolder f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        ImageWallpaper.GLEngine.this.lambda$onSurfaceCreated$3$ImageWallpaper$GLEngine(this.f$1);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onSurfaceCreated$3$ImageWallpaper$GLEngine(SurfaceHolder surfaceHolder) {
            this.mEglHelper.init(surfaceHolder);
            this.mRenderer.onSurfaceCreated();
        }

        public void onSurfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            if (ImageWallpaper.this.mWorker != null) {
                ImageWallpaper.this.mWorker.getThreadHandler().post(new Runnable(i2, i3) {
                    private final /* synthetic */ int f$1;
                    private final /* synthetic */ int f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final void run() {
                        ImageWallpaper.GLEngine.this.lambda$onSurfaceChanged$4$ImageWallpaper$GLEngine(this.f$1, this.f$2);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onSurfaceChanged$4$ImageWallpaper$GLEngine(int i, int i2) {
            String access$100 = ImageWallpaper.TAG;
            Log.d(access$100, "onSurfaceChanged: w=" + i + ", h=" + i2);
            this.mRenderer.onSurfaceChanged(i, i2);
            this.mNeedRedraw = true;
        }

        public void onSurfaceRedrawNeeded(SurfaceHolder surfaceHolder) {
            if (ImageWallpaper.this.mWorker != null) {
                ImageWallpaper.this.mWorker.getThreadHandler().post(new Runnable() {
                    public final void run() {
                        ImageWallpaper.GLEngine.this.lambda$onSurfaceRedrawNeeded$5$ImageWallpaper$GLEngine();
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onSurfaceRedrawNeeded$5$ImageWallpaper$GLEngine() {
            String access$100 = ImageWallpaper.TAG;
            Log.d(access$100, "onSurfaceRedrawNeeded: mNeedRedraw=" + this.mNeedRedraw);
            if (this.mNeedRedraw) {
                drawFrame();
                this.mNeedRedraw = false;
            }
        }

        public void onVisibilityChanged(boolean z) {
            String access$100 = ImageWallpaper.TAG;
            Log.d(access$100, "wallpaper visibility changes to: " + z);
        }

        private void drawFrame() {
            preRender();
            requestRender();
            postRender();
        }

        public void onStatePostChange() {
            if (ImageWallpaper.this.mWorker != null && this.mController.getState() == 0) {
                ImageWallpaper.this.mWorker.getThreadHandler().post(new Runnable() {
                    public final void run() {
                        ImageWallpaper.GLEngine.this.scheduleFinishRendering();
                    }
                });
            }
        }

        public void preRender() {
            Log.d(ImageWallpaper.TAG, "preRender start");
            Trace.beginSection("ImageWallpaper#preRender");
            preRenderInternal();
            Trace.endSection();
            Log.d(ImageWallpaper.TAG, "preRender end");
        }

        private void preRenderInternal() {
            boolean z;
            Rect surfaceFrame = getSurfaceHolder().getSurfaceFrame();
            cancelFinishRenderingTask();
            if (!this.mEglHelper.hasEglContext()) {
                this.mEglHelper.destroyEglSurface();
                if (!this.mEglHelper.createEglContext()) {
                    Log.w(ImageWallpaper.TAG, "recreate egl context failed!");
                } else {
                    z = true;
                    if (this.mEglHelper.hasEglContext() && !this.mEglHelper.hasEglSurface() && !this.mEglHelper.createEglSurface(getSurfaceHolder())) {
                        Log.w(ImageWallpaper.TAG, "recreate egl surface failed!");
                    }
                    if (!this.mEglHelper.hasEglContext() && !this.mEglHelper.hasEglSurface() && z) {
                        this.mRenderer.onSurfaceCreated();
                        this.mRenderer.onSurfaceChanged(surfaceFrame.width(), surfaceFrame.height());
                        return;
                    }
                }
            }
            z = false;
            Log.w(ImageWallpaper.TAG, "recreate egl surface failed!");
            if (!this.mEglHelper.hasEglContext() && !this.mEglHelper.hasEglSurface()) {
            }
        }

        public void requestRender() {
            Trace.beginSection("ImageWallpaper#requestRender");
            requestRenderInternal();
            Trace.endSection();
        }

        private void requestRenderInternal() {
            Rect surfaceFrame = getSurfaceHolder().getSurfaceFrame();
            if (this.mEglHelper.hasEglContext() && this.mEglHelper.hasEglSurface() && surfaceFrame.width() > 0 && surfaceFrame.height() > 0) {
                this.mRenderer.onDrawFrame();
                if (!this.mEglHelper.swapBuffer()) {
                    Log.e(ImageWallpaper.TAG, "drawFrame failed!");
                    return;
                }
                return;
            }
            String access$100 = ImageWallpaper.TAG;
            Log.e(access$100, "requestRender: not ready, has context=" + this.mEglHelper.hasEglContext() + ", has surface=" + this.mEglHelper.hasEglSurface() + ", frame=" + surfaceFrame);
        }

        public void postRender() {
            Log.d(ImageWallpaper.TAG, "postRender start");
            Trace.beginSection("ImageWallpaper#postRender");
            notifyWaitingThread();
            scheduleFinishRendering();
            Trace.endSection();
            Log.d(ImageWallpaper.TAG, "postRender end");
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(5:2|3|(3:5|6|7)|8|9) */
        /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x000f */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void notifyWaitingThread() {
            /*
                r2 = this;
                java.lang.Object r0 = r2.mMonitor
                monitor-enter(r0)
                boolean r1 = r2.mWaitingForRendering     // Catch:{ all -> 0x0011 }
                if (r1 == 0) goto L_0x000f
                r1 = 0
                r2.mWaitingForRendering = r1     // Catch:{ IllegalMonitorStateException -> 0x000f }
                java.lang.Object r2 = r2.mMonitor     // Catch:{ IllegalMonitorStateException -> 0x000f }
                r2.notify()     // Catch:{ IllegalMonitorStateException -> 0x000f }
            L_0x000f:
                monitor-exit(r0)     // Catch:{ all -> 0x0011 }
                return
            L_0x0011:
                r2 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0011 }
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.ImageWallpaper.GLEngine.notifyWaitingThread():void");
        }

        private void cancelFinishRenderingTask() {
            if (ImageWallpaper.this.mWorker != null) {
                ImageWallpaper.this.mWorker.getThreadHandler().removeCallbacks(this.mFinishRenderingTask);
            }
        }

        /* access modifiers changed from: private */
        public void scheduleFinishRendering() {
            if (ImageWallpaper.this.mWorker != null) {
                cancelFinishRenderingTask();
                ImageWallpaper.this.mWorker.getThreadHandler().postDelayed(this.mFinishRenderingTask, 1000);
            }
        }

        /* access modifiers changed from: private */
        public void finishRendering() {
            String access$100 = ImageWallpaper.TAG;
            Log.d(access$100, "finishRendering, preserve=" + needPreserveEglContext());
            Trace.beginSection("ImageWallpaper#finishRendering");
            EglHelper eglHelper = this.mEglHelper;
            if (eglHelper != null) {
                eglHelper.destroyEglSurface();
                if (!needPreserveEglContext()) {
                    this.mEglHelper.destroyEglContext();
                }
            }
            Trace.endSection();
        }

        private boolean needPreserveEglContext() {
            StatusBarStateController statusBarStateController;
            if (!this.mNeedTransition || (statusBarStateController = this.mController) == null || statusBarStateController.getState() != 1) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: protected */
        public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            super.dump(str, fileDescriptor, printWriter, strArr);
            printWriter.print(str);
            printWriter.print("Engine=");
            printWriter.println(this);
            printWriter.print(str);
            printWriter.print("isHighEndGfx=");
            printWriter.println(this.mIsHighEndGfx);
            printWriter.print(str);
            printWriter.print("displayNeedsBlanking=");
            printWriter.println(this.mDisplayNeedsBlanking);
            printWriter.print(str);
            printWriter.print("displayInfo=");
            printWriter.print(this.mDisplayInfo);
            printWriter.print(str);
            printWriter.print("mNeedTransition=");
            printWriter.println(this.mNeedTransition);
            printWriter.print(str);
            printWriter.print("mShouldStopTransition=");
            printWriter.println(this.mShouldStopTransition);
            printWriter.print(str);
            printWriter.print("StatusBarState=");
            StatusBarStateController statusBarStateController = this.mController;
            Object obj = "null";
            printWriter.println(statusBarStateController != null ? Integer.valueOf(statusBarStateController.getState()) : obj);
            printWriter.print(str);
            printWriter.print("valid surface=");
            printWriter.println((getSurfaceHolder() == null || getSurfaceHolder().getSurface() == null) ? obj : Boolean.valueOf(getSurfaceHolder().getSurface().isValid()));
            printWriter.print(str);
            printWriter.print("surface frame=");
            if (getSurfaceHolder() != null) {
                obj = getSurfaceHolder().getSurfaceFrame();
            }
            printWriter.println(obj);
            this.mEglHelper.dump(str, fileDescriptor, printWriter, strArr);
            this.mRenderer.dump(str, fileDescriptor, printWriter, strArr);
        }
    }
}
