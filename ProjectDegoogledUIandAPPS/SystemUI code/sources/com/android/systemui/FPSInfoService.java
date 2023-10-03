package com.android.systemui;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.service.dreams.IDreamManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import java.io.BufferedReader;
import java.io.FileReader;

public class FPSInfoService extends Service {
    /* access modifiers changed from: private */
    public String MEASURED_FPS = "";
    private final String TAG = "FPSInfoService";
    private Thread mCurFPSThread;
    private IDreamManager mDreamManager;
    /* access modifiers changed from: private */
    public String mFps = null;
    private BroadcastReceiver mScreenStateReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.SCREEN_ON")) {
                Log.d("FPSInfoService", "ACTION_SCREEN_ON " + FPSInfoService.this.isDozeMode());
                if (!FPSInfoService.this.isDozeMode()) {
                    FPSInfoService.this.startThread();
                    FPSInfoService.this.mView.setVisibility(0);
                }
            } else if (intent.getAction().equals("android.intent.action.SCREEN_OFF")) {
                Log.d("FPSInfoService", "ACTION_SCREEN_OFF");
                FPSInfoService.this.mView.setVisibility(8);
                FPSInfoService.this.stopThread();
            }
        }
    };
    /* access modifiers changed from: private */
    public View mView;

    public IBinder onBind(Intent intent) {
        return null;
    }

    private class FPSView extends View {
        private float mAscent;
        private Handler mCurFPSHandler = new Handler() {
            public void handleMessage(Message message) {
                Object obj = message.obj;
                if (obj != null && message.what == 1) {
                    String str = (String) obj;
                    String unused = FPSInfoService.this.mFps = str.substring(0, Math.min(str.length(), 9));
                    boolean unused2 = FPSView.this.mDataAvail = true;
                    FPSView.this.updateDisplay();
                }
            }
        };
        /* access modifiers changed from: private */
        public boolean mDataAvail;
        private int mFH;
        private int mMaxWidth;
        private int mNeededHeight;
        private int mNeededWidth;
        private Paint mOnlinePaint;

        FPSView(Context context) {
            super(context);
            float f = context.getResources().getDisplayMetrics().density;
            int round = Math.round(f * 5.0f);
            setPadding(round, round, round, round);
            setBackgroundColor(Color.argb(96, 0, 0, 0));
            int round2 = Math.round(f * 12.0f);
            this.mOnlinePaint = new Paint();
            this.mOnlinePaint.setAntiAlias(true);
            this.mOnlinePaint.setTextSize((float) round2);
            this.mOnlinePaint.setColor(-1);
            this.mOnlinePaint.setShadowLayer(5.0f, 0.0f, 0.0f, -16777216);
            this.mAscent = this.mOnlinePaint.ascent();
            this.mFH = (int) ((this.mOnlinePaint.descent() - this.mAscent) + 0.5f);
            this.mMaxWidth = (int) this.mOnlinePaint.measureText("fps: 60.1");
            updateDisplay();
        }

        /* access modifiers changed from: protected */
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
        }

        /* access modifiers changed from: protected */
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            this.mCurFPSHandler.removeMessages(1);
        }

        /* access modifiers changed from: protected */
        public void onMeasure(int i, int i2) {
            setMeasuredDimension(View.resolveSize(this.mNeededWidth, i), View.resolveSize(this.mNeededHeight, i2));
        }

        private String getFPSInfoString() {
            return FPSInfoService.this.mFps;
        }

        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            if (this.mDataAvail) {
                canvas.drawText(getFPSInfoString(), (float) (((getWidth() - 1) - this.mPaddingLeft) - this.mMaxWidth), (float) ((this.mPaddingTop - ((int) this.mAscent)) - 1), this.mOnlinePaint);
            }
        }

        /* access modifiers changed from: package-private */
        public void updateDisplay() {
            if (this.mDataAvail) {
                int i = this.mPaddingLeft + this.mPaddingRight + this.mMaxWidth;
                int i2 = this.mPaddingTop + this.mPaddingBottom + 40;
                if (i == this.mNeededWidth && i2 == this.mNeededHeight) {
                    invalidate();
                    return;
                }
                this.mNeededWidth = i;
                this.mNeededHeight = i2;
                requestLayout();
            }
        }

        public Handler getHandler() {
            return this.mCurFPSHandler;
        }
    }

    protected class CurFPSThread extends Thread {
        private Handler mHandler;
        private boolean mInterrupt = false;

        public CurFPSThread(Handler handler) {
            this.mHandler = handler;
        }

        public void interrupt() {
            this.mInterrupt = true;
        }

        public void run() {
            while (!this.mInterrupt) {
                try {
                    Thread.sleep(500);
                    new StringBuffer();
                    this.mHandler.sendMessage(this.mHandler.obtainMessage(1, FPSInfoService.readOneLine(FPSInfoService.this.MEASURED_FPS)));
                } catch (InterruptedException unused) {
                    return;
                }
            }
        }
    }

    public void onCreate() {
        super.onCreate();
        this.MEASURED_FPS = getResources().getString(C1784R$string.config_fpsInfoSysNode);
        this.mView = new FPSView(this);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-2, -2, 2015, 24, -3);
        layoutParams.gravity = 51;
        layoutParams.setTitle("FPS Info");
        startThread();
        this.mDreamManager = IDreamManager.Stub.asInterface(ServiceManager.checkService("dreams"));
        IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        registerReceiver(this.mScreenStateReceiver, intentFilter);
        ((WindowManager) getSystemService("window")).addView(this.mView, layoutParams);
    }

    public void onDestroy() {
        super.onDestroy();
        stopThread();
        ((WindowManager) getSystemService("window")).removeView(this.mView);
        this.mView = null;
        unregisterReceiver(this.mScreenStateReceiver);
    }

    /* access modifiers changed from: private */
    public static String readOneLine(String str) {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(str), 512);
            String readLine = bufferedReader.readLine();
            bufferedReader.close();
            return readLine;
        } catch (Exception unused) {
            return null;
        } catch (Throwable th) {
            bufferedReader.close();
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public boolean isDozeMode() {
        try {
            return this.mDreamManager != null && this.mDreamManager.isDreaming();
        } catch (RemoteException unused) {
        }
    }

    /* access modifiers changed from: private */
    public void startThread() {
        Log.d("FPSInfoService", "started CurFPSThread");
        this.mCurFPSThread = new CurFPSThread(this.mView.getHandler());
        this.mCurFPSThread.start();
    }

    /* access modifiers changed from: private */
    public void stopThread() {
        Thread thread = this.mCurFPSThread;
        if (thread != null && thread.isAlive()) {
            Log.d("FPSInfoService", "stopping CurFPSThread");
            this.mCurFPSThread.interrupt();
            try {
                this.mCurFPSThread.join();
            } catch (InterruptedException unused) {
            }
        }
        this.mCurFPSThread = null;
    }
}
