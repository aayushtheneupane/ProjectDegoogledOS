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

public class CPUInfoService extends Service {
    /* access modifiers changed from: private */
    public int CPU_TEMP_DIVIDER = 1;
    /* access modifiers changed from: private */
    public String CPU_TEMP_SENSOR = "";
    private final String TAG = "CPUInfoService";
    private Thread mCurCPUThread;
    /* access modifiers changed from: private */
    public String[] mCurrFreq = null;
    /* access modifiers changed from: private */
    public String[] mCurrGov = null;
    private IDreamManager mDreamManager;
    /* access modifiers changed from: private */
    public int mNumCpus = 1;
    private BroadcastReceiver mScreenStateReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.SCREEN_ON")) {
                if (!CPUInfoService.this.isDozeMode()) {
                    CPUInfoService.this.startThread();
                    CPUInfoService.this.mView.setVisibility(0);
                }
            } else if (intent.getAction().equals("android.intent.action.SCREEN_OFF")) {
                CPUInfoService.this.mView.setVisibility(8);
                CPUInfoService.this.stopThread();
            }
        }
    };
    /* access modifiers changed from: private */
    public View mView;

    public IBinder onBind(Intent intent) {
        return null;
    }

    private class CPUView extends View {
        private float mAscent;
        /* access modifiers changed from: private */
        public String mCpuTemp;
        private boolean mCpuTempAvail;
        private Handler mCurCPUHandler = new Handler() {
            public void handleMessage(Message message) {
                Object obj = message.obj;
                if (obj != null && message.what == 1) {
                    String str = (String) obj;
                    try {
                        String[] split = str.split(";");
                        String unused = CPUView.this.mCpuTemp = split[0];
                        String[] split2 = split[1].split("\\|");
                        for (int i = 0; i < split2.length; i++) {
                            String[] split3 = split2[i].split(":");
                            if (split3.length == 2) {
                                CPUInfoService.this.mCurrFreq[i] = split3[0];
                                CPUInfoService.this.mCurrGov[i] = split3[1];
                            } else {
                                CPUInfoService.this.mCurrFreq[i] = "0";
                                CPUInfoService.this.mCurrGov[i] = "";
                            }
                        }
                        boolean unused2 = CPUView.this.mDataAvail = true;
                        CPUView.this.updateDisplay();
                    } catch (ArrayIndexOutOfBoundsException unused3) {
                        Log.e("CPUInfoService", "illegal data " + str);
                    }
                }
            }
        };
        /* access modifiers changed from: private */
        public boolean mDataAvail;
        private int mFH;
        private int mMaxWidth;
        private int mNeededHeight;
        private int mNeededWidth;
        private Paint mOfflinePaint;
        private Paint mOnlinePaint;

        CPUView(Context context) {
            super(context);
            float f = context.getResources().getDisplayMetrics().density;
            int round = Math.round(f * 5.0f);
            setPadding(round, round, round, round);
            setBackgroundColor(Color.argb(96, 0, 0, 0));
            int round2 = Math.round(f * 12.0f);
            this.mOnlinePaint = new Paint();
            this.mOnlinePaint.setAntiAlias(true);
            float f2 = (float) round2;
            this.mOnlinePaint.setTextSize(f2);
            this.mOnlinePaint.setColor(-1);
            this.mOnlinePaint.setShadowLayer(5.0f, 0.0f, 0.0f, -16777216);
            this.mOfflinePaint = new Paint();
            this.mOfflinePaint.setAntiAlias(true);
            this.mOfflinePaint.setTextSize(f2);
            this.mOfflinePaint.setColor(-65536);
            this.mAscent = this.mOnlinePaint.ascent();
            this.mFH = (int) ((this.mOnlinePaint.descent() - this.mAscent) + 0.5f);
            this.mMaxWidth = (int) this.mOnlinePaint.measureText("cpuX interactive 0000000");
            updateDisplay();
        }

        /* access modifiers changed from: protected */
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
        }

        /* access modifiers changed from: protected */
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            this.mCurCPUHandler.removeMessages(1);
        }

        /* access modifiers changed from: protected */
        public void onMeasure(int i, int i2) {
            setMeasuredDimension(View.resolveSize(this.mNeededWidth, i), View.resolveSize(this.mNeededHeight, i2));
        }

        private String getCPUInfoString(int i) {
            String str = CPUInfoService.this.mCurrFreq[i];
            String str2 = CPUInfoService.this.mCurrGov[i];
            return "cpu" + i + " " + str2 + " " + String.format("%7s", new Object[]{str});
        }

        private String getCpuTemp(String str) {
            if (CPUInfoService.this.CPU_TEMP_DIVIDER <= 1) {
                return str;
            }
            return String.format("%s", new Object[]{Integer.valueOf(Integer.parseInt(str) / CPUInfoService.this.CPU_TEMP_DIVIDER)});
        }

        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            if (this.mDataAvail) {
                int width = getWidth() - 1;
                int i = this.mPaddingTop - ((int) this.mAscent);
                if (!this.mCpuTemp.equals("0")) {
                    canvas.drawText("Temp " + getCpuTemp(this.mCpuTemp) + "°C", (float) ((width - this.mPaddingRight) - this.mMaxWidth), (float) (i - 1), this.mOnlinePaint);
                    this.mCpuTempAvail = true;
                    i += this.mFH;
                }
                for (int i2 = 0; i2 < CPUInfoService.this.mCurrFreq.length; i2++) {
                    String cPUInfoString = getCPUInfoString(i2);
                    if (!CPUInfoService.this.mCurrFreq[i2].equals("0")) {
                        canvas.drawText(cPUInfoString, (float) ((width - this.mPaddingRight) - this.mMaxWidth), (float) (i - 1), this.mOnlinePaint);
                    } else {
                        canvas.drawText(cPUInfoString, (float) ((width - this.mPaddingRight) - this.mMaxWidth), (float) (i - 1), this.mOfflinePaint);
                    }
                    i += this.mFH;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void updateDisplay() {
            if (this.mDataAvail) {
                int access$500 = CPUInfoService.this.mNumCpus;
                int i = this.mPaddingLeft + this.mPaddingRight + this.mMaxWidth;
                int i2 = this.mPaddingTop + this.mPaddingBottom + (this.mFH * ((this.mCpuTempAvail ? 1 : 0) + access$500));
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
            return this.mCurCPUHandler;
        }
    }

    protected class CurCPUThread extends Thread {
        private Handler mHandler;
        private boolean mInterrupt = false;

        public CurCPUThread(Handler handler, int i) {
            this.mHandler = handler;
            int unused = CPUInfoService.this.mNumCpus = i;
        }

        public void interrupt() {
            this.mInterrupt = true;
        }

        public void run() {
            while (!this.mInterrupt) {
                try {
                    Thread.sleep(500);
                    StringBuffer stringBuffer = new StringBuffer();
                    String access$700 = CPUInfoService.readOneLine(CPUInfoService.this.CPU_TEMP_SENSOR);
                    if (access$700 == null) {
                        access$700 = "0";
                    }
                    stringBuffer.append(access$700);
                    stringBuffer.append(";");
                    for (int i = 0; i < CPUInfoService.this.mNumCpus; i++) {
                        String access$7002 = CPUInfoService.readOneLine("/sys/devices/system/cpu/cpu" + i + "/cpufreq/scaling_cur_freq");
                        String access$7003 = CPUInfoService.readOneLine("/sys/devices/system/cpu/cpu" + i + "/cpufreq/scaling_governor");
                        if (access$7002 == null) {
                            access$7003 = "";
                            access$7002 = "0";
                        }
                        stringBuffer.append(access$7002 + ":" + access$7003 + "|");
                    }
                    stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                    this.mHandler.sendMessage(this.mHandler.obtainMessage(1, stringBuffer.toString()));
                } catch (InterruptedException unused) {
                    return;
                }
            }
        }
    }

    public void onCreate() {
        super.onCreate();
        this.mNumCpus = getNumOfCpus();
        int i = this.mNumCpus;
        this.mCurrFreq = new String[i];
        this.mCurrGov = new String[i];
        this.CPU_TEMP_DIVIDER = getResources().getInteger(C1778R$integer.config_cpuTempDivider);
        this.CPU_TEMP_SENSOR = getResources().getString(C1784R$string.config_cpuTempSensor);
        this.mView = new CPUView(this);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-2, -2, 2015, 24, -3);
        layoutParams.gravity = 53;
        layoutParams.setTitle("CPU Info");
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

    private static int getNumOfCpus() {
        String[] split = readOneLine("/sys/devices/system/cpu/present").split("-");
        if (split.length <= 1) {
            return 1;
        }
        try {
            int parseInt = (Integer.parseInt(split[1]) - Integer.parseInt(split[0])) + 1;
            if (parseInt < 0) {
                return 1;
            }
            return parseInt;
        } catch (NumberFormatException unused) {
            return 1;
        }
    }

    /* access modifiers changed from: private */
    public boolean isDozeMode() {
        try {
            return this.mDreamManager != null && this.mDreamManager.isDozing();
        } catch (RemoteException unused) {
        }
    }

    /* access modifiers changed from: private */
    public void startThread() {
        this.mCurCPUThread = new CurCPUThread(this.mView.getHandler(), this.mNumCpus);
        this.mCurCPUThread.start();
    }

    /* access modifiers changed from: private */
    public void stopThread() {
        Thread thread = this.mCurCPUThread;
        if (thread != null && thread.isAlive()) {
            this.mCurCPUThread.interrupt();
            try {
                this.mCurCPUThread.join();
            } catch (InterruptedException unused) {
            }
        }
        this.mCurCPUThread = null;
    }
}
