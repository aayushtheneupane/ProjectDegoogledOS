package com.android.messaging.p041ui.mediapicker;

import android.animation.TimeAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.view.ViewCompat;
import com.android.messaging.C0970i;

/* renamed from: com.android.messaging.ui.mediapicker.SoundLevels */
public class SoundLevels extends View {

    /* renamed from: Mk */
    private boolean f2041Mk;

    /* renamed from: Nk */
    private final Paint f2042Nk;

    /* renamed from: Ok */
    private final float f2043Ok;

    /* renamed from: Pk */
    private final float f2044Pk;

    /* renamed from: Qk */
    private final float f2045Qk;

    /* renamed from: Rk */
    private final TimeAnimator f2046Rk;

    /* renamed from: Sk */
    private float f2047Sk;

    /* renamed from: Tk */
    private C1298a f2048Tk;
    private int mCenterX;
    private int mCenterY;
    private boolean mIsEnabled;

    public SoundLevels(Context context) {
        this(context, (AttributeSet) null, 0);
    }

    /* renamed from: wn */
    private void m3280wn() {
        if (this.f2046Rk.isStarted()) {
            this.f2046Rk.end();
        }
    }

    /* renamed from: a */
    public void mo7748a(C1298a aVar) {
        this.f2048Tk = aVar;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.f2046Rk.isStarted()) {
            this.f2046Rk.end();
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.mIsEnabled) {
            if (!this.f2041Mk) {
                this.mCenterX = getWidth() / 2;
                this.mCenterY = getWidth() / 2;
                this.f2041Mk = true;
            }
            float jj = (float) this.f2048Tk.mo7768jj();
            float f = this.f2047Sk;
            if (jj > f) {
                this.f2047Sk = ((jj - f) / 4.0f) + f;
            } else {
                this.f2047Sk = f * 0.95f;
            }
            float f2 = this.f2043Ok;
            float f3 = (((1.0f - f2) * this.f2047Sk) / 100.0f) + f2;
            this.f2042Nk.setStyle(Paint.Style.FILL);
            canvas.drawCircle((float) this.mCenterX, (float) this.mCenterY, f3 * this.f2045Qk, this.f2042Nk);
        }
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(SoundLevels.class.getCanonicalName());
    }

    public void setEnabled(boolean z) {
        if (z != this.mIsEnabled) {
            super.setEnabled(z);
            this.mIsEnabled = z;
            setKeepScreenOn(z);
            if (!this.mIsEnabled) {
                m3280wn();
            } else if (!this.f2046Rk.isStarted()) {
                this.f2046Rk.start();
            }
        }
    }

    public SoundLevels(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SoundLevels(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f2048Tk = new C1298a();
        this.f2048Tk.mo7767Ia(0);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0970i.SoundLevels, i, 0);
        this.f2045Qk = (float) obtainStyledAttributes.getDimensionPixelOffset(0, 0);
        this.f2044Pk = (float) obtainStyledAttributes.getDimensionPixelOffset(1, 0);
        this.f2043Ok = this.f2044Pk / this.f2045Qk;
        this.f2042Nk = new Paint();
        this.f2042Nk.setColor(obtainStyledAttributes.getColor(2, ViewCompat.MEASURED_STATE_MASK));
        this.f2042Nk.setFlags(1);
        obtainStyledAttributes.recycle();
        this.f2046Rk = new TimeAnimator();
        this.f2046Rk.setRepeatCount(-1);
        this.f2046Rk.setTimeListener(new C1365za(this));
    }
}
