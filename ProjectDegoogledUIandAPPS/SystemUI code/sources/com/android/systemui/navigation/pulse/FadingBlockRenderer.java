package com.android.systemui.navigation.pulse;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.util.TypedValue;

public class FadingBlockRenderer extends Renderer {
    private int dbValue;
    private byte ifk;
    private Canvas mCanvas;
    private Bitmap mCanvasBitmap;
    /* access modifiers changed from: private */
    public int mDbFuzzFactor;
    /* access modifiers changed from: private */
    public int mDivisions;
    private FFTAverage[] mFFTAverage;
    private byte[] mFFTBytes;
    private float[] mFFTPoints;
    private Paint mFadePaint = new Paint();
    private int mHeight;
    private boolean mLeftInLandscape;
    private Matrix mMatrix;
    private LegacySettingsObserver mObserver;
    /* access modifiers changed from: private */
    public Paint mPaint = new Paint();
    /* access modifiers changed from: private */
    public int mPathEffect1;
    /* access modifiers changed from: private */
    public int mPathEffect2;
    /* access modifiers changed from: private */
    public boolean mSmoothingEnabled;
    private boolean mVertical;
    private int mWidth;
    private float magnitude;
    private byte rfk;

    public FadingBlockRenderer(Context context, Handler handler, PulseView pulseView, PulseControllerImpl pulseControllerImpl, ColorController colorController) {
        super(context, handler, pulseView, colorController);
        this.mObserver = new LegacySettingsObserver(handler);
        this.mFadePaint.setColor(Color.argb(200, 255, 255, 255));
        this.mFadePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));
        this.mMatrix = new Matrix();
        this.mObserver.updateSettings();
        this.mPaint.setAntiAlias(true);
        onSizeChanged(0, 0, 0, 0);
    }

    public void onStreamAnalyzed(boolean z) {
        this.mIsValidStream = z;
        if (z) {
            onSizeChanged(0, 0, 0, 0);
            this.mColorController.startLavaLamp();
        }
    }

    public void onFFTUpdate(byte[] bArr) {
        int i;
        int i2 = this.mKeyguardShowing ? this.mDbFuzzFactor * 4 : this.mDbFuzzFactor;
        this.mFFTBytes = bArr;
        byte[] bArr2 = this.mFFTBytes;
        if (bArr2 != null) {
            float[] fArr = this.mFFTPoints;
            if (fArr == null || fArr.length < bArr2.length * 4) {
                this.mFFTPoints = new float[(this.mFFTBytes.length * 4)];
            }
            int length = this.mFFTBytes.length / this.mDivisions;
            if (this.mSmoothingEnabled) {
                FFTAverage[] fFTAverageArr = this.mFFTAverage;
                if (fFTAverageArr == null || fFTAverageArr.length != length) {
                    setupFFTAverage(length);
                }
            } else {
                this.mFFTAverage = null;
            }
            for (int i3 = 0; i3 < length; i3++) {
                if (this.mVertical) {
                    float[] fArr2 = this.mFFTPoints;
                    int i4 = i3 * 4;
                    int i5 = this.mDivisions;
                    fArr2[i4 + 1] = (float) (i4 * i5);
                    fArr2[i4 + 3] = (float) (i4 * i5);
                } else {
                    float[] fArr3 = this.mFFTPoints;
                    int i6 = i3 * 4;
                    int i7 = this.mDivisions;
                    fArr3[i6] = (float) (i6 * i7);
                    fArr3[i6 + 2] = (float) (i6 * i7);
                }
                byte[] bArr3 = this.mFFTBytes;
                int i8 = this.mDivisions;
                this.rfk = bArr3[i8 * i3];
                this.ifk = bArr3[(i8 * i3) + 1];
                byte b = this.rfk;
                byte b2 = this.ifk;
                this.magnitude = (float) ((b * b) + (b2 * b2));
                float f = this.magnitude;
                float f2 = 0.0f;
                this.dbValue = f > 0.0f ? (int) (Math.log10((double) f) * 10.0d) : 0;
                if (this.mSmoothingEnabled) {
                    this.dbValue = this.mFFTAverage[i3].average(this.dbValue);
                }
                if (this.mVertical) {
                    float[] fArr4 = this.mFFTPoints;
                    int i9 = i3 * 4;
                    if (!this.mLeftInLandscape) {
                        f2 = (float) this.mWidth;
                    }
                    fArr4[i9] = f2;
                    float[] fArr5 = this.mFFTPoints;
                    int i10 = i9 + 2;
                    if (this.mLeftInLandscape) {
                        i = (this.dbValue * i2) + 2;
                    } else {
                        i = this.mWidth - ((this.dbValue * i2) + 2);
                    }
                    fArr5[i10] = (float) i;
                } else {
                    float[] fArr6 = this.mFFTPoints;
                    int i11 = i3 * 4;
                    int i12 = this.mHeight;
                    fArr6[i11 + 1] = (float) i12;
                    fArr6[i11 + 3] = (float) (i12 - ((this.dbValue * i2) + 2));
                }
            }
        }
        this.mCanvas.drawLines(this.mFFTPoints, this.mPaint);
        this.mCanvas.drawPaint(this.mFadePaint);
        postInvalidate();
    }

    private void setupFFTAverage(int i) {
        this.mFFTAverage = new FFTAverage[i];
        for (int i2 = 0; i2 < i; i2++) {
            this.mFFTAverage[i2] = new FFTAverage();
        }
    }

    public void onSizeChanged(int i, int i2, int i3, int i4) {
        if (this.mView.getWidth() > 0 && this.mView.getHeight() > 0) {
            this.mWidth = this.mView.getWidth();
            this.mHeight = this.mView.getHeight();
            boolean z = true;
            if (!this.mKeyguardShowing ? this.mHeight <= this.mWidth : this.mHeight >= this.mWidth) {
                z = false;
            }
            this.mVertical = z;
            this.mCanvasBitmap = Bitmap.createBitmap(this.mWidth, this.mHeight, Bitmap.Config.ARGB_8888);
            this.mCanvas = new Canvas(this.mCanvasBitmap);
        }
    }

    public void setLeftInLandscape(boolean z) {
        if (this.mLeftInLandscape != z) {
            this.mLeftInLandscape = z;
            onSizeChanged(0, 0, 0, 0);
        }
    }

    public void destroy() {
        this.mContext.getContentResolver().unregisterContentObserver(this.mObserver);
        this.mColorController.stopLavaLamp();
        this.mCanvasBitmap = null;
    }

    public void onVisualizerLinkChanged(boolean z) {
        if (!z) {
            this.mColorController.stopLavaLamp();
        }
    }

    public void onUpdateColor(int i) {
        this.mPaint.setColor(i);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.mCanvasBitmap, this.mMatrix, (Paint) null);
    }

    private class LegacySettingsObserver extends ContentObserver {
        public LegacySettingsObserver(Handler handler) {
            super(handler);
            register();
        }

        /* access modifiers changed from: package-private */
        public void register() {
            ContentResolver contentResolver = FadingBlockRenderer.this.mContext.getContentResolver();
            contentResolver.registerContentObserver(Settings.Secure.getUriFor("pulse_custom_dimen"), false, this, -1);
            contentResolver.registerContentObserver(Settings.Secure.getUriFor("pulse_custom_div"), false, this, -1);
            contentResolver.registerContentObserver(Settings.Secure.getUriFor("pulse_filled_block_size"), false, this, -1);
            contentResolver.registerContentObserver(Settings.Secure.getUriFor("pulse_empty_block_size"), false, this, -1);
            contentResolver.registerContentObserver(Settings.Secure.getUriFor("pulse_custom_fudge_factor"), false, this, -1);
            contentResolver.registerContentObserver(Settings.Secure.getUriFor("pulse_smoothing_enabled"), false, this, -1);
        }

        public void onChange(boolean z, Uri uri) {
            updateSettings();
        }

        public void updateSettings() {
            ContentResolver contentResolver = FadingBlockRenderer.this.mContext.getContentResolver();
            Resources resources = FadingBlockRenderer.this.mContext.getResources();
            boolean z = true;
            int intForUser = Settings.Secure.getIntForUser(contentResolver, "pulse_empty_block_size", 1, -2);
            int intForUser2 = Settings.Secure.getIntForUser(contentResolver, "pulse_custom_dimen", 14, -2);
            int intForUser3 = Settings.Secure.getIntForUser(contentResolver, "pulse_custom_div", 16, -2);
            int intForUser4 = Settings.Secure.getIntForUser(contentResolver, "pulse_custom_fudge_factor", 4, -2);
            int unused = FadingBlockRenderer.this.mPathEffect1 = FadingBlockRenderer.getLimitedDimenValue(Settings.Secure.getIntForUser(contentResolver, "pulse_filled_block_size", 4, -2), 4, 8, resources);
            int unused2 = FadingBlockRenderer.this.mPathEffect2 = FadingBlockRenderer.getLimitedDimenValue(intForUser, 0, 4, resources);
            FadingBlockRenderer.this.mPaint.setPathEffect((PathEffect) null);
            FadingBlockRenderer.this.mPaint.setPathEffect(new DashPathEffect(new float[]{(float) FadingBlockRenderer.this.mPathEffect1, (float) FadingBlockRenderer.this.mPathEffect2}, 0.0f));
            FadingBlockRenderer.this.mPaint.setStrokeWidth((float) FadingBlockRenderer.getLimitedDimenValue(intForUser2, 1, 30, resources));
            int unused3 = FadingBlockRenderer.this.mDivisions = FadingBlockRenderer.validateDivision(intForUser3);
            int unused4 = FadingBlockRenderer.this.mDbFuzzFactor = Math.max(2, Math.min(6, intForUser4));
            FadingBlockRenderer fadingBlockRenderer = FadingBlockRenderer.this;
            if (Settings.Secure.getIntForUser(contentResolver, "pulse_smoothing_enabled", 0, -2) != 1) {
                z = false;
            }
            boolean unused5 = fadingBlockRenderer.mSmoothingEnabled = z;
        }
    }

    /* access modifiers changed from: private */
    public static int getLimitedDimenValue(int i, int i2, int i3, Resources resources) {
        return (int) TypedValue.applyDimension(1, (float) Math.max(i2, Math.min(i3, i)), resources.getDisplayMetrics());
    }

    /* access modifiers changed from: private */
    public static int validateDivision(int i) {
        if (i % 2 != 0) {
            i = 16;
        }
        return Math.max(2, Math.min(44, i));
    }
}
