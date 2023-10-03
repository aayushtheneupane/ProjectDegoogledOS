package com.android.dialer.persistentlog;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.design.R$dimen;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.strictmode.StrictModeUtils;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

public final class PersistentLogger {
    static final int LOG_FILE_COUNT_LIMIT = 8;
    static final int LOG_FILE_SIZE_LIMIT = 65536;
    private static PersistentLogFileHandler fileHandler;
    private static HandlerThread loggerThread;
    private static Handler loggerThreadHandler;
    private static final LinkedBlockingQueue<byte[]> messageQueue = new LinkedBlockingQueue<>();

    private static class DumpStringRunnable implements Runnable {
        private final CountDownLatch latch = new CountDownLatch(1);
        private String result;

        /* synthetic */ DumpStringRunnable(C05071 r2) {
        }

        public String get() throws InterruptedException {
            this.latch.await();
            return this.result;
        }

        public void run() {
            this.result = PersistentLogger.access$100();
            this.latch.countDown();
        }
    }

    static /* synthetic */ String access$100() {
        StringBuilder sb = new StringBuilder();
        try {
            for (byte[] str : readLogs()) {
                sb.append(new String(str, StandardCharsets.UTF_8));
                sb.append("\n");
            }
            return sb.toString();
        } catch (IOException e) {
            return GeneratedOutlineSupport.outline6("Cannot dump logText: ", e);
        }
    }

    public static String dumpLogToString() {
        Assert.isWorkerThread();
        DumpStringRunnable dumpStringRunnable = new DumpStringRunnable((C05071) null);
        loggerThreadHandler.post(dumpStringRunnable);
        try {
            return dumpStringRunnable.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "Cannot dump logText: " + e;
        }
    }

    public static void initialize(Context context) {
        fileHandler = new PersistentLogFileHandler("plain_text", LOG_FILE_SIZE_LIMIT, LOG_FILE_COUNT_LIMIT);
        loggerThread = new HandlerThread("PersistentLogger");
        loggerThread.start();
        loggerThreadHandler = new Handler(loggerThread.getLooper(), new Handler.Callback(context) {
            private final /* synthetic */ Context f$0;

            {
                this.f$0 = r1;
            }

            public final boolean handleMessage(Message message) {
                PersistentLogger.lambda$initialize$0(this.f$0, message);
                return true;
            }
        });
        loggerThreadHandler.post(new Runnable(context) {
            private final /* synthetic */ Context f$0;

            {
                this.f$0 = r1;
            }

            public final void run() {
                PersistentLogger.fileHandler.initialize(this.f$0);
            }
        });
    }

    static /* synthetic */ boolean lambda$initialize$0(Context context, Message message) {
        if (message.what != 1 || messageQueue.isEmpty()) {
            return true;
        }
        loggerThreadHandler.removeMessages(1);
        ArrayList arrayList = new ArrayList();
        messageQueue.drainTo(arrayList);
        if (!R$dimen.isUserUnlocked(context)) {
            return true;
        }
        try {
            fileHandler.writeLogs(arrayList);
        } catch (IOException e) {
            LogUtil.m7e("PersistentLogger.MESSAGE_FLUSH", "error writing message", (Throwable) e);
        }
        return true;
    }

    static void log(byte[] bArr) {
        messageQueue.add(bArr);
        loggerThreadHandler.sendEmptyMessageDelayed(1, 200);
    }

    public static void logText(String str, String str2) {
        Calendar calendar = (Calendar) StrictModeUtils.bypass($$Lambda$PersistentLogger$p3wGZ3_mToPwJfY1BRdCpdWiAt4.INSTANCE);
        Object[] objArr = new Object[LOG_FILE_COUNT_LIMIT];
        objArr[0] = calendar;
        objArr[1] = calendar;
        objArr[2] = calendar;
        objArr[3] = calendar;
        objArr[4] = calendar;
        objArr[5] = calendar;
        objArr[6] = str;
        objArr[7] = str2;
        log(String.format("%tm-%td %tH:%tM:%tS.%tL - %s - %s", objArr).getBytes(StandardCharsets.UTF_8));
    }

    static void rawLogForTest(byte[] bArr) {
        try {
            fileHandler.writeRawLogsForTest(bArr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static List<byte[]> readLogs() throws IOException {
        Assert.isWorkerThread();
        return fileHandler.getLogs();
    }
}
