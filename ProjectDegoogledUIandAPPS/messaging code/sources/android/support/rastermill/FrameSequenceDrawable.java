package android.support.rastermill;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.support.rastermill.FrameSequence;

public class FrameSequenceDrawable extends Drawable implements Animatable, Runnable {
    private static final long DEFAULT_DELAY_MS = 100;
    public static final int LOOP_DEFAULT = 3;
    public static final int LOOP_FINITE = 1;
    public static final int LOOP_INF = 2;
    @Deprecated
    public static final int LOOP_ONCE = 1;
    private static final long MIN_DELAY_MS = 20;
    private static final int STATE_DECODING = 2;
    private static final int STATE_READY_TO_SWAP = 4;
    private static final int STATE_SCHEDULED = 1;
    private static final int STATE_WAITING_TO_SWAP = 3;
    private static final String TAG = "FrameSequence";
    private static BitmapProvider sAllocatingBitmapProvider = new BitmapProvider() {
        public Bitmap acquireBitmap(int i, int i2) {
            return Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        }

        public void releaseBitmap(Bitmap bitmap) {
        }
    };
    private static HandlerThread sDecodingThread;
    private static Handler sDecodingThreadHandler;
    private static final Object sLock = new Object();
    /* access modifiers changed from: private */
    public Bitmap mBackBitmap;
    private BitmapShader mBackBitmapShader;
    /* access modifiers changed from: private */
    public final BitmapProvider mBitmapProvider;
    private boolean mCircleMaskEnabled;
    private int mCurrentLoop;
    private Runnable mDecodeRunnable;
    /* access modifiers changed from: private */
    public boolean mDestroyed;
    private Runnable mFinishedCallbackRunnable;
    private final FrameSequence mFrameSequence;
    /* access modifiers changed from: private */
    public final FrameSequence.State mFrameSequenceState;
    private Bitmap mFrontBitmap;
    private BitmapShader mFrontBitmapShader;
    /* access modifiers changed from: private */
    public long mLastSwap;
    /* access modifiers changed from: private */
    public final Object mLock;
    private int mLoopBehavior;
    private int mLoopCount;
    /* access modifiers changed from: private */
    public int mNextFrameToDecode;
    /* access modifiers changed from: private */
    public long mNextSwap;
    /* access modifiers changed from: private */
    public OnFinishedListener mOnFinishedListener;
    private final Paint mPaint;
    private final Rect mSrcRect;
    /* access modifiers changed from: private */
    public int mState;
    private RectF mTempRectF;

    public interface BitmapProvider {
        Bitmap acquireBitmap(int i, int i2);

        void releaseBitmap(Bitmap bitmap);
    }

    public interface OnFinishedListener {
        void onFinished(FrameSequenceDrawable frameSequenceDrawable);
    }

    public FrameSequenceDrawable(FrameSequence frameSequence) {
        this(frameSequence, sAllocatingBitmapProvider);
    }

    private static Bitmap acquireAndValidateBitmap(BitmapProvider bitmapProvider, int i, int i2) {
        Bitmap acquireBitmap = bitmapProvider.acquireBitmap(i, i2);
        if (acquireBitmap.getWidth() >= i && acquireBitmap.getHeight() >= i2 && acquireBitmap.getConfig() == Bitmap.Config.ARGB_8888) {
            return acquireBitmap;
        }
        throw new IllegalArgumentException("Invalid bitmap provided");
    }

    private void checkDestroyedLocked() {
        if (this.mDestroyed) {
            throw new IllegalStateException("Cannot perform operation on recycled drawable");
        }
    }

    private static void initializeDecodingThread() {
        synchronized (sLock) {
            if (sDecodingThread == null) {
                sDecodingThread = new HandlerThread("FrameSequence decoding thread", 10);
                sDecodingThread.start();
                sDecodingThreadHandler = new Handler(sDecodingThread.getLooper());
            }
        }
    }

    private void scheduleDecodeLocked() {
        this.mState = 1;
        this.mNextFrameToDecode = (this.mNextFrameToDecode + 1) % this.mFrameSequence.getFrameCount();
        sDecodingThreadHandler.post(this.mDecodeRunnable);
    }

    public void destroy() {
        Bitmap bitmap;
        Bitmap bitmap2;
        if (this.mBitmapProvider != null) {
            synchronized (this.mLock) {
                checkDestroyedLocked();
                bitmap = this.mFrontBitmap;
                bitmap2 = null;
                this.mFrontBitmap = null;
                if (this.mState != 2) {
                    Bitmap bitmap3 = this.mBackBitmap;
                    this.mBackBitmap = null;
                    bitmap2 = bitmap3;
                }
                this.mDestroyed = true;
            }
            this.mBitmapProvider.releaseBitmap(bitmap);
            if (bitmap2 != null) {
                this.mBitmapProvider.releaseBitmap(bitmap2);
                return;
            }
            return;
        }
        throw new IllegalStateException("BitmapProvider must be non-null");
    }

    public void draw(Canvas canvas) {
        synchronized (this.mLock) {
            checkDestroyedLocked();
            if (this.mState == 3 && this.mNextSwap - SystemClock.uptimeMillis() <= 0) {
                this.mState = 4;
            }
            if (isRunning() && this.mState == 4) {
                Bitmap bitmap = this.mBackBitmap;
                this.mBackBitmap = this.mFrontBitmap;
                this.mFrontBitmap = bitmap;
                BitmapShader bitmapShader = this.mBackBitmapShader;
                this.mBackBitmapShader = this.mFrontBitmapShader;
                this.mFrontBitmapShader = bitmapShader;
                this.mLastSwap = SystemClock.uptimeMillis();
                boolean z = true;
                if (this.mNextFrameToDecode == this.mFrameSequence.getFrameCount() - 1) {
                    this.mCurrentLoop++;
                    if ((this.mLoopBehavior == 1 && this.mCurrentLoop == this.mLoopCount) || (this.mLoopBehavior == 3 && this.mCurrentLoop == this.mFrameSequence.getDefaultLoopCount())) {
                        z = false;
                    }
                }
                if (z) {
                    scheduleDecodeLocked();
                } else {
                    scheduleSelf(this.mFinishedCallbackRunnable, 0);
                }
            }
        }
        if (this.mCircleMaskEnabled) {
            Rect bounds = getBounds();
            int intrinsicWidth = getIntrinsicWidth();
            int intrinsicHeight = getIntrinsicHeight();
            float f = (float) intrinsicWidth;
            float width = (((float) bounds.width()) * 1.0f) / f;
            float f2 = (float) intrinsicHeight;
            float height = (((float) bounds.height()) * 1.0f) / f2;
            canvas.save();
            canvas.translate((float) bounds.left, (float) bounds.top);
            canvas.scale(width, height);
            float min = (float) Math.min(bounds.width(), bounds.height());
            float f3 = min / width;
            float f4 = min / height;
            this.mTempRectF.set((f - f3) / 2.0f, (f2 - f4) / 2.0f, (f + f3) / 2.0f, (f2 + f4) / 2.0f);
            this.mPaint.setShader(this.mFrontBitmapShader);
            canvas.drawOval(this.mTempRectF, this.mPaint);
            canvas.restore();
            return;
        }
        this.mPaint.setShader((Shader) null);
        canvas.drawBitmap(this.mFrontBitmap, this.mSrcRect, getBounds(), this.mPaint);
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        try {
            this.mFrameSequenceState.destroy();
        } finally {
            super.finalize();
        }
    }

    public final boolean getCircleMaskEnabled() {
        return this.mCircleMaskEnabled;
    }

    public int getIntrinsicHeight() {
        return this.mFrameSequence.getHeight();
    }

    public int getIntrinsicWidth() {
        return this.mFrameSequence.getWidth();
    }

    public int getOpacity() {
        return this.mFrameSequence.isOpaque() ? -1 : -2;
    }

    public boolean isDestroyed() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mDestroyed;
        }
        return z;
    }

    public boolean isRunning() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mNextFrameToDecode > -1 && !this.mDestroyed;
        }
        return z;
    }

    public void run() {
        boolean z;
        synchronized (this.mLock) {
            if (this.mNextFrameToDecode < 0 || this.mState != 3) {
                z = false;
            } else {
                this.mState = 4;
                z = true;
            }
        }
        if (z) {
            invalidateSelf();
        }
    }

    public void setAlpha(int i) {
        this.mPaint.setAlpha(i);
    }

    public final void setCircleMaskEnabled(boolean z) {
        if (this.mCircleMaskEnabled != z) {
            this.mCircleMaskEnabled = z;
            this.mPaint.setAntiAlias(z);
            invalidateSelf();
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
    }

    public void setFilterBitmap(boolean z) {
        this.mPaint.setFilterBitmap(z);
    }

    public void setLoopBehavior(int i) {
        this.mLoopBehavior = i;
    }

    public void setLoopCount(int i) {
        this.mLoopCount = i;
    }

    public void setOnFinishedListener(OnFinishedListener onFinishedListener) {
        this.mOnFinishedListener = onFinishedListener;
    }

    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (!z) {
            stop();
        } else if (z2 || visible) {
            stop();
            start();
        }
        return visible;
    }

    public void start() {
        if (!isRunning()) {
            synchronized (this.mLock) {
                checkDestroyedLocked();
                if (this.mState != 1) {
                    this.mCurrentLoop = 0;
                    scheduleDecodeLocked();
                }
            }
        }
    }

    public void stop() {
        if (isRunning()) {
            unscheduleSelf(this);
        }
    }

    public void unscheduleSelf(Runnable runnable) {
        synchronized (this.mLock) {
            this.mNextFrameToDecode = -1;
            this.mState = 0;
        }
        super.unscheduleSelf(runnable);
    }

    public FrameSequenceDrawable(FrameSequence frameSequence, BitmapProvider bitmapProvider) {
        this.mLock = new Object();
        this.mDestroyed = false;
        this.mLoopBehavior = 3;
        this.mLoopCount = 1;
        this.mTempRectF = new RectF();
        this.mDecodeRunnable = new Runnable() {
            /* JADX WARNING: Code restructure failed: missing block: B:13:0x0028, code lost:
                r5 = 0;
                r7 = false;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
                r5 = android.support.rastermill.FrameSequenceDrawable.access$500(r11.this$0).getFrame(r1, r2, r1 - 2);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:16:0x0038, code lost:
                r0 = false;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:17:0x003a, code lost:
                r0 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:18:0x003b, code lost:
                android.util.Log.e(android.support.rastermill.FrameSequenceDrawable.TAG, "exception during decode: " + r0);
                r0 = true;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r11 = this;
                    android.support.rastermill.FrameSequenceDrawable r0 = android.support.rastermill.FrameSequenceDrawable.this
                    java.lang.Object r0 = r0.mLock
                    monitor-enter(r0)
                    android.support.rastermill.FrameSequenceDrawable r1 = android.support.rastermill.FrameSequenceDrawable.this     // Catch:{ all -> 0x00be }
                    boolean r1 = r1.mDestroyed     // Catch:{ all -> 0x00be }
                    if (r1 == 0) goto L_0x0011
                    monitor-exit(r0)     // Catch:{ all -> 0x00be }
                    return
                L_0x0011:
                    android.support.rastermill.FrameSequenceDrawable r1 = android.support.rastermill.FrameSequenceDrawable.this     // Catch:{ all -> 0x00be }
                    int r1 = r1.mNextFrameToDecode     // Catch:{ all -> 0x00be }
                    if (r1 >= 0) goto L_0x001b
                    monitor-exit(r0)     // Catch:{ all -> 0x00be }
                    return
                L_0x001b:
                    android.support.rastermill.FrameSequenceDrawable r2 = android.support.rastermill.FrameSequenceDrawable.this     // Catch:{ all -> 0x00be }
                    android.graphics.Bitmap r2 = r2.mBackBitmap     // Catch:{ all -> 0x00be }
                    android.support.rastermill.FrameSequenceDrawable r3 = android.support.rastermill.FrameSequenceDrawable.this     // Catch:{ all -> 0x00be }
                    r4 = 2
                    int unused = r3.mState = r4     // Catch:{ all -> 0x00be }
                    monitor-exit(r0)     // Catch:{ all -> 0x00be }
                    int r0 = r1 + -2
                    r5 = 0
                    r3 = 1
                    r7 = 0
                    android.support.rastermill.FrameSequenceDrawable r8 = android.support.rastermill.FrameSequenceDrawable.this     // Catch:{ Exception -> 0x003a }
                    android.support.rastermill.FrameSequence$State r8 = r8.mFrameSequenceState     // Catch:{ Exception -> 0x003a }
                    long r5 = r8.getFrame(r1, r2, r0)     // Catch:{ Exception -> 0x003a }
                    r0 = r7
                    goto L_0x0052
                L_0x003a:
                    r0 = move-exception
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder
                    r1.<init>()
                    java.lang.String r2 = "exception during decode: "
                    r1.append(r2)
                    r1.append(r0)
                    java.lang.String r0 = r1.toString()
                    java.lang.String r1 = "FrameSequence"
                    android.util.Log.e(r1, r0)
                    r0 = r3
                L_0x0052:
                    r1 = 20
                    int r1 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
                    if (r1 >= 0) goto L_0x005a
                    r5 = 100
                L_0x005a:
                    android.support.rastermill.FrameSequenceDrawable r1 = android.support.rastermill.FrameSequenceDrawable.this
                    java.lang.Object r1 = r1.mLock
                    monitor-enter(r1)
                    android.support.rastermill.FrameSequenceDrawable r2 = android.support.rastermill.FrameSequenceDrawable.this     // Catch:{ all -> 0x00bb }
                    boolean r2 = r2.mDestroyed     // Catch:{ all -> 0x00bb }
                    r8 = 0
                    if (r2 == 0) goto L_0x0077
                    android.support.rastermill.FrameSequenceDrawable r0 = android.support.rastermill.FrameSequenceDrawable.this     // Catch:{ all -> 0x00bb }
                    android.graphics.Bitmap r0 = r0.mBackBitmap     // Catch:{ all -> 0x00bb }
                    android.support.rastermill.FrameSequenceDrawable r2 = android.support.rastermill.FrameSequenceDrawable.this     // Catch:{ all -> 0x00bb }
                    android.graphics.Bitmap unused = r2.mBackBitmap = r8     // Catch:{ all -> 0x00bb }
                    r8 = r0
                    goto L_0x00a3
                L_0x0077:
                    android.support.rastermill.FrameSequenceDrawable r2 = android.support.rastermill.FrameSequenceDrawable.this     // Catch:{ all -> 0x00bb }
                    int r2 = r2.mNextFrameToDecode     // Catch:{ all -> 0x00bb }
                    if (r2 < 0) goto L_0x00a3
                    android.support.rastermill.FrameSequenceDrawable r2 = android.support.rastermill.FrameSequenceDrawable.this     // Catch:{ all -> 0x00bb }
                    int r2 = r2.mState     // Catch:{ all -> 0x00bb }
                    if (r2 != r4) goto L_0x00a3
                    android.support.rastermill.FrameSequenceDrawable r2 = android.support.rastermill.FrameSequenceDrawable.this     // Catch:{ all -> 0x00bb }
                    if (r0 == 0) goto L_0x0091
                    r4 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                    goto L_0x0099
                L_0x0091:
                    android.support.rastermill.FrameSequenceDrawable r0 = android.support.rastermill.FrameSequenceDrawable.this     // Catch:{ all -> 0x00bb }
                    long r9 = r0.mLastSwap     // Catch:{ all -> 0x00bb }
                    long r4 = r5 + r9
                L_0x0099:
                    long unused = r2.mNextSwap = r4     // Catch:{ all -> 0x00bb }
                    android.support.rastermill.FrameSequenceDrawable r0 = android.support.rastermill.FrameSequenceDrawable.this     // Catch:{ all -> 0x00bb }
                    r2 = 3
                    int unused = r0.mState = r2     // Catch:{ all -> 0x00bb }
                    r7 = r3
                L_0x00a3:
                    monitor-exit(r1)     // Catch:{ all -> 0x00bb }
                    if (r7 == 0) goto L_0x00af
                    android.support.rastermill.FrameSequenceDrawable r0 = android.support.rastermill.FrameSequenceDrawable.this
                    long r1 = r0.mNextSwap
                    r0.scheduleSelf(r0, r1)
                L_0x00af:
                    if (r8 == 0) goto L_0x00ba
                    android.support.rastermill.FrameSequenceDrawable r11 = android.support.rastermill.FrameSequenceDrawable.this
                    android.support.rastermill.FrameSequenceDrawable$BitmapProvider r11 = r11.mBitmapProvider
                    r11.releaseBitmap(r8)
                L_0x00ba:
                    return
                L_0x00bb:
                    r11 = move-exception
                    monitor-exit(r1)     // Catch:{ all -> 0x00bb }
                    throw r11
                L_0x00be:
                    r11 = move-exception
                    monitor-exit(r0)     // Catch:{ all -> 0x00be }
                    throw r11
                */
                throw new UnsupportedOperationException("Method not decompiled: android.support.rastermill.FrameSequenceDrawable.C00662.run():void");
            }
        };
        this.mFinishedCallbackRunnable = new Runnable() {
            public void run() {
                synchronized (FrameSequenceDrawable.this.mLock) {
                    int unused = FrameSequenceDrawable.this.mNextFrameToDecode = -1;
                    int unused2 = FrameSequenceDrawable.this.mState = 0;
                }
                if (FrameSequenceDrawable.this.mOnFinishedListener != null) {
                    FrameSequenceDrawable.this.mOnFinishedListener.onFinished(FrameSequenceDrawable.this);
                }
            }
        };
        if (frameSequence == null || bitmapProvider == null) {
            throw new IllegalArgumentException();
        }
        this.mFrameSequence = frameSequence;
        this.mFrameSequenceState = frameSequence.createState();
        int width = frameSequence.getWidth();
        int height = frameSequence.getHeight();
        this.mBitmapProvider = bitmapProvider;
        this.mFrontBitmap = acquireAndValidateBitmap(bitmapProvider, width, height);
        this.mBackBitmap = acquireAndValidateBitmap(bitmapProvider, width, height);
        this.mSrcRect = new Rect(0, 0, width, height);
        this.mPaint = new Paint();
        this.mPaint.setFilterBitmap(true);
        Bitmap bitmap = this.mFrontBitmap;
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        this.mFrontBitmapShader = new BitmapShader(bitmap, tileMode, tileMode);
        Bitmap bitmap2 = this.mBackBitmap;
        Shader.TileMode tileMode2 = Shader.TileMode.CLAMP;
        this.mBackBitmapShader = new BitmapShader(bitmap2, tileMode2, tileMode2);
        this.mLastSwap = 0;
        this.mNextFrameToDecode = -1;
        this.mFrameSequenceState.getFrame(0, this.mFrontBitmap, -1);
        initializeDecodingThread();
    }
}
