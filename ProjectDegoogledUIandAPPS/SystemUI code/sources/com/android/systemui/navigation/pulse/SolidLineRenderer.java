package com.android.systemui.navigation.pulse;

import android.animation.ValueAnimator;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import androidx.core.graphics.ColorUtils;

public class SolidLineRenderer extends Renderer {
    private int dbValue;
    private byte ifk;
    /* access modifiers changed from: private */
    public int mColor = -1;
    /* access modifiers changed from: private */
    public int mDbFuzzFactor;
    /* access modifiers changed from: private */
    public FFTAverage[] mFFTAverage;
    /* access modifiers changed from: private */
    public float[] mFFTPoints;
    private int mHeight;
    private boolean mLeftInLandscape;
    private CMRendererObserver mObserver;
    /* access modifiers changed from: private */
    public Paint mPaint = new Paint();
    /* access modifiers changed from: private */
    public boolean mSmoothingEnabled;
    /* access modifiers changed from: private */
    public int mUnits;
    /* access modifiers changed from: private */
    public int mUnitsOpacity = 255;
    private ValueAnimator[] mValueAnimators;
    private boolean mVertical;
    private int mWidth;
    private float magnitude;
    private byte rfk;

    public SolidLineRenderer(Context context, Handler handler, PulseView pulseView, PulseControllerImpl pulseControllerImpl, ColorController colorController) {
        super(context, handler, pulseView, colorController);
        this.mPaint.setAntiAlias(true);
        this.mDbFuzzFactor = 5;
        this.mObserver = new CMRendererObserver(handler);
        this.mObserver.updateSettings();
        loadValueAnimators();
    }

    public void setLeftInLandscape(boolean z) {
        if (this.mLeftInLandscape != z) {
            this.mLeftInLandscape = z;
            onSizeChanged(0, 0, 0, 0);
        }
    }

    private void loadValueAnimators() {
        if (this.mValueAnimators != null) {
            int i = 0;
            while (true) {
                ValueAnimator[] valueAnimatorArr = this.mValueAnimators;
                if (i >= valueAnimatorArr.length) {
                    break;
                }
                valueAnimatorArr[i].cancel();
                i++;
            }
        }
        this.mValueAnimators = new ValueAnimator[this.mUnits];
        boolean z = this.mVertical;
        for (int i2 = 0; i2 < this.mUnits; i2++) {
            final int i3 = z ? i2 * 4 : (i2 * 4) + 1;
            this.mValueAnimators[i2] = new ValueAnimator();
            this.mValueAnimators[i2].setDuration(128);
            this.mValueAnimators[i2].addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    SolidLineRenderer.this.mFFTPoints[i3] = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    SolidLineRenderer.this.postInvalidate();
                }
            });
        }
    }

    private void setPortraitPoints() {
        float floatValue = Float.valueOf((float) this.mUnits).floatValue();
        float f = ((float) this.mWidth) / floatValue;
        float f2 = (8.0f * f) / 9.0f;
        float f3 = (((f - f2) * floatValue) / (floatValue - 1.0f)) + f2;
        this.mPaint.setStrokeWidth(f2);
        for (int i = 0; i < this.mUnits; i++) {
            float[] fArr = this.mFFTPoints;
            int i2 = i * 4;
            float f4 = (((float) i) * f3) + (f2 / 2.0f);
            fArr[i2 + 2] = f4;
            fArr[i2] = f4;
            int i3 = this.mHeight;
            fArr[i2 + 1] = (float) i3;
            fArr[i2 + 3] = (float) i3;
        }
    }

    private void setVerticalPoints() {
        float floatValue = Float.valueOf((float) this.mUnits).floatValue();
        float f = ((float) this.mHeight) / floatValue;
        float f2 = (8.0f * f) / 9.0f;
        float f3 = (((f - f2) * floatValue) / (floatValue - 1.0f)) + f2;
        this.mPaint.setStrokeWidth(f2);
        for (int i = 0; i < this.mUnits; i++) {
            float[] fArr = this.mFFTPoints;
            int i2 = i * 4;
            float f4 = (((float) i) * f3) + (f2 / 2.0f);
            fArr[i2 + 3] = f4;
            fArr[i2 + 1] = f4;
            float f5 = 0.0f;
            fArr[i2] = this.mLeftInLandscape ? 0.0f : (float) this.mWidth;
            float[] fArr2 = this.mFFTPoints;
            int i3 = i2 + 2;
            if (!this.mLeftInLandscape) {
                f5 = (float) this.mWidth;
            }
            fArr2[i3] = f5;
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
            loadValueAnimators();
            if (this.mVertical) {
                setVerticalPoints();
            } else {
                setPortraitPoints();
            }
        }
    }

    public void onStreamAnalyzed(boolean z) {
        this.mIsValidStream = z;
        if (z) {
            onSizeChanged(0, 0, 0, 0);
            this.mColorController.startLavaLamp();
        }
    }

    public void onFFTUpdate(byte[] bArr) {
        int i = this.mKeyguardShowing ? this.mDbFuzzFactor * 4 : this.mDbFuzzFactor;
        for (int i2 = 0; i2 < this.mUnits; i2++) {
            this.mValueAnimators[i2].cancel();
            int i3 = i2 * 2;
            this.rfk = bArr[i3 + 2];
            this.ifk = bArr[i3 + 3];
            byte b = this.rfk;
            byte b2 = this.ifk;
            this.magnitude = (float) ((b * b) + (b2 * b2));
            float f = this.magnitude;
            this.dbValue = f > 0.0f ? (int) (Math.log10((double) f) * 10.0d) : 0;
            if (this.mSmoothingEnabled) {
                this.dbValue = this.mFFTAverage[i2].average(this.dbValue);
            }
            if (!this.mVertical) {
                ValueAnimator valueAnimator = this.mValueAnimators[i2];
                float[] fArr = this.mFFTPoints;
                valueAnimator.setFloatValues(new float[]{fArr[(i2 * 4) + 1], fArr[3] - ((float) (this.dbValue * i))});
            } else if (this.mLeftInLandscape) {
                this.mValueAnimators[i2].setFloatValues(new float[]{this.mFFTPoints[i2 * 4], (float) (this.dbValue * i)});
            } else {
                ValueAnimator valueAnimator2 = this.mValueAnimators[i2];
                float[] fArr2 = this.mFFTPoints;
                valueAnimator2.setFloatValues(new float[]{fArr2[i2 * 4], fArr2[2] - ((float) (this.dbValue * i))});
            }
            this.mValueAnimators[i2].start();
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawLines(this.mFFTPoints, this.mPaint);
    }

    public void destroy() {
        this.mContext.getContentResolver().unregisterContentObserver(this.mObserver);
        this.mColorController.stopLavaLamp();
    }

    public void onVisualizerLinkChanged(boolean z) {
        if (!z) {
            this.mColorController.stopLavaLamp();
        }
    }

    public void onUpdateColor(int i) {
        this.mColor = i;
        this.mPaint.setColor(ColorUtils.setAlphaComponent(this.mColor, this.mUnitsOpacity));
    }

    private class CMRendererObserver extends ContentObserver {
        public CMRendererObserver(Handler handler) {
            super(handler);
            register();
        }

        /* access modifiers changed from: package-private */
        public void register() {
            ContentResolver contentResolver = SolidLineRenderer.this.mContext.getContentResolver();
            contentResolver.registerContentObserver(Settings.Secure.getUriFor("pulse_solid_fudge_factor"), false, this, -1);
            contentResolver.registerContentObserver(Settings.Secure.getUriFor("pulse_solid_units_count"), false, this, -1);
            contentResolver.registerContentObserver(Settings.Secure.getUriFor("pulse_solid_units_opacity"), false, this, -1);
            contentResolver.registerContentObserver(Settings.Secure.getUriFor("pulse_smoothing_enabled"), false, this, -1);
        }

        public void onChange(boolean z, Uri uri) {
            updateSettings();
        }

        public void updateSettings() {
            ContentResolver contentResolver = SolidLineRenderer.this.mContext.getContentResolver();
            int unused = SolidLineRenderer.this.mDbFuzzFactor = Settings.Secure.getIntForUser(contentResolver, "pulse_solid_fudge_factor", 5, -2);
            SolidLineRenderer solidLineRenderer = SolidLineRenderer.this;
            boolean z = true;
            if (Settings.Secure.getIntForUser(contentResolver, "pulse_smoothing_enabled", 0, -2) != 1) {
                z = false;
            }
            boolean unused2 = solidLineRenderer.mSmoothingEnabled = z;
            int access$300 = SolidLineRenderer.this.mUnits;
            int unused3 = SolidLineRenderer.this.mUnits = Settings.Secure.getIntForUser(contentResolver, "pulse_solid_units_count", 64, -2);
            if (SolidLineRenderer.this.mUnits != access$300) {
                SolidLineRenderer solidLineRenderer2 = SolidLineRenderer.this;
                float[] unused4 = solidLineRenderer2.mFFTPoints = new float[(solidLineRenderer2.mUnits * 4)];
                if (SolidLineRenderer.this.mSmoothingEnabled) {
                    setupFFTAverage();
                }
                SolidLineRenderer.this.onSizeChanged(0, 0, 0, 0);
            }
            if (!SolidLineRenderer.this.mSmoothingEnabled) {
                FFTAverage[] unused5 = SolidLineRenderer.this.mFFTAverage = null;
            } else if (SolidLineRenderer.this.mFFTAverage == null) {
                setupFFTAverage();
            }
            int unused6 = SolidLineRenderer.this.mUnitsOpacity = Settings.Secure.getIntForUser(contentResolver, "pulse_solid_units_opacity", 200, -2);
            SolidLineRenderer.this.mPaint.setColor(ColorUtils.setAlphaComponent(SolidLineRenderer.this.mColor, SolidLineRenderer.this.mUnitsOpacity));
        }

        private void setupFFTAverage() {
            SolidLineRenderer solidLineRenderer = SolidLineRenderer.this;
            FFTAverage[] unused = solidLineRenderer.mFFTAverage = new FFTAverage[solidLineRenderer.mUnits];
            for (int i = 0; i < SolidLineRenderer.this.mUnits; i++) {
                SolidLineRenderer.this.mFFTAverage[i] = new FFTAverage();
            }
        }
    }
}
