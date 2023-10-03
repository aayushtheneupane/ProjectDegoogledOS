package com.android.systemui.navigation.pulse;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import com.android.systemui.navigation.pulse.VisualizerStreamHandler;

public abstract class Renderer implements VisualizerStreamHandler.Listener {
    protected ColorController mColorController;
    protected Context mContext;
    private long mCurrentCounter;
    private long mCurrentTime;
    protected Handler mHandler;
    protected boolean mIsValidStream;
    protected boolean mKeyguardShowing;
    private long mRenderCounter = System.currentTimeMillis();
    protected PulseView mView;

    public abstract void destroy();

    public abstract void draw(Canvas canvas);

    public abstract void onFFTUpdate(byte[] bArr);

    public abstract void onSizeChanged(int i, int i2, int i3, int i4);

    public abstract void onUpdateColor(int i);

    public abstract void onVisualizerLinkChanged(boolean z);

    public abstract void setLeftInLandscape(boolean z);

    public Renderer(Context context, Handler handler, PulseView pulseView, ColorController colorController) {
        this.mContext = context;
        this.mHandler = handler;
        this.mView = pulseView;
        this.mColorController = colorController;
    }

    /* access modifiers changed from: protected */
    public final void postInvalidate() {
        this.mCurrentTime = System.currentTimeMillis();
        long j = this.mCurrentTime;
        this.mCurrentCounter = j - this.mRenderCounter;
        if (this.mCurrentCounter >= 25) {
            this.mRenderCounter = j;
            this.mView.postInvalidate();
        }
    }

    public void setKeyguardShowing(boolean z) {
        this.mKeyguardShowing = z;
        onSizeChanged(0, 0, 0, 0);
    }

    public boolean isValidStream() {
        return this.mIsValidStream;
    }
}
