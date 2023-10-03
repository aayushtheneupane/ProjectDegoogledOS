package com.android.systemui.navigation.pulse;

import android.content.Context;
import android.media.audiofx.Visualizer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.android.systemui.Dependency;
import com.android.systemui.UiOffloadThread;

public class VisualizerStreamHandler {
    protected static final String TAG = "VisualizerStreamHandler";
    protected int mConsecutiveFrames;
    protected Context mContext;
    protected PulseControllerImpl mController;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 55) {
                VisualizerStreamHandler visualizerStreamHandler = VisualizerStreamHandler.this;
                visualizerStreamHandler.mIsAnalyzed = true;
                visualizerStreamHandler.mIsValidated = true;
                visualizerStreamHandler.mIsPrepared = false;
                visualizerStreamHandler.mListener.onStreamAnalyzed(true);
            } else if (i == 56) {
                VisualizerStreamHandler visualizerStreamHandler2 = VisualizerStreamHandler.this;
                visualizerStreamHandler2.mIsAnalyzed = true;
                visualizerStreamHandler2.mIsValidated = false;
                visualizerStreamHandler2.mIsPrepared = false;
                visualizerStreamHandler2.mListener.onStreamAnalyzed(false);
            }
        }
    };
    protected boolean mIsAnalyzed;
    protected boolean mIsPaused;
    protected boolean mIsPrepared;
    protected boolean mIsValidated;
    protected Listener mListener;
    private final UiOffloadThread mUiOffloadThread;
    protected Visualizer mVisualizer;

    public interface Listener {
        void onFFTUpdate(byte[] bArr);

        void onStreamAnalyzed(boolean z);
    }

    public VisualizerStreamHandler(Context context, PulseControllerImpl pulseControllerImpl, Listener listener) {
        this.mContext = context;
        this.mController = pulseControllerImpl;
        this.mListener = listener;
        this.mUiOffloadThread = (UiOffloadThread) Dependency.get(UiOffloadThread.class);
    }

    public final void link() {
        this.mUiOffloadThread.submit(new Runnable() {
            public final void run() {
                VisualizerStreamHandler.this.lambda$link$0$VisualizerStreamHandler();
            }
        });
    }

    public /* synthetic */ void lambda$link$0$VisualizerStreamHandler() {
        pause();
        resetAnalyzer();
        if (this.mVisualizer == null) {
            try {
                this.mVisualizer = new Visualizer(0);
                this.mVisualizer.setEnabled(false);
                this.mVisualizer.setCaptureSize(Visualizer.getCaptureSizeRange()[1]);
                this.mVisualizer.setDataCaptureListener(new Visualizer.OnDataCaptureListener() {
                    public void onWaveFormDataCapture(Visualizer visualizer, byte[] bArr, int i) {
                    }

                    public void onFftDataCapture(Visualizer visualizer, byte[] bArr, int i) {
                        VisualizerStreamHandler.this.analyze(bArr);
                        if (VisualizerStreamHandler.this.isValidStream()) {
                            VisualizerStreamHandler visualizerStreamHandler = VisualizerStreamHandler.this;
                            if (!visualizerStreamHandler.mIsPaused) {
                                visualizerStreamHandler.mListener.onFFTUpdate(bArr);
                            }
                        }
                    }
                }, (int) (((double) Visualizer.getMaxCaptureRate()) * 0.75d), false, true);
            } catch (Exception e) {
                Log.e(TAG, "Error enabling visualizer!", e);
                return;
            }
        }
        this.mVisualizer.setEnabled(true);
    }

    public final void unlink() {
        if (this.mVisualizer != null) {
            pause();
            this.mVisualizer.setEnabled(false);
            this.mVisualizer.release();
            this.mVisualizer = null;
            resetAnalyzer();
        }
    }

    public boolean isValidStream() {
        return this.mIsAnalyzed && this.mIsValidated;
    }

    public void resetAnalyzer() {
        this.mIsAnalyzed = false;
        this.mIsValidated = false;
        this.mIsPrepared = false;
        this.mConsecutiveFrames = 0;
    }

    public void pause() {
        this.mIsPaused = true;
    }

    public void resume() {
        this.mIsPaused = false;
    }

    /* access modifiers changed from: private */
    public void analyze(byte[] bArr) {
        if (!this.mIsAnalyzed) {
            if (!this.mIsPrepared) {
                Handler handler = this.mHandler;
                handler.sendMessageDelayed(handler.obtainMessage(56), 6000);
                this.mIsPrepared = true;
            }
            if (isDataEmpty(bArr)) {
                this.mConsecutiveFrames = 0;
            } else {
                this.mConsecutiveFrames++;
            }
            if (this.mConsecutiveFrames == 3) {
                this.mIsPaused = true;
                this.mHandler.removeMessages(56);
                this.mHandler.sendEmptyMessage(55);
            }
        }
    }

    private boolean isDataEmpty(byte[] bArr) {
        for (byte b : bArr) {
            if (b != 0) {
                return false;
            }
        }
        return true;
    }
}
