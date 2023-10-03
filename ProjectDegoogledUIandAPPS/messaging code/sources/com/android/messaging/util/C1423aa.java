package com.android.messaging.util;

import android.os.Process;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

/* renamed from: com.android.messaging.util.aa */
final class C1423aa extends C1425ba {
    private final Object mLock;
    private final int mSize;

    /* renamed from: rK */
    private final C1461m f2233rK;

    /* renamed from: sK */
    private final SimpleDateFormat f2234sK = new SimpleDateFormat("MM-dd HH:mm:ss.SSS");

    public C1423aa(int i) {
        super((C1420Y) null);
        this.mSize = i;
        this.f2233rK = new C1461m(i);
        this.mLock = new Object();
    }

    /* renamed from: Oj */
    public boolean mo8051Oj() {
        C1449g.get().getBoolean("bugle_persistent_logsaver", false);
        C1449g.get().getInt("bugle_in_memory_logsaver_record_count", 500);
        if (500 == this.mSize) {
            return true;
        }
        return false;
    }

    /* renamed from: b */
    public void mo8052b(int i, String str, String str2) {
        synchronized (this.mLock) {
            C1421Z z = (C1421Z) this.f2233rK.mo8190Jj();
            if (z == null) {
                z = new C1421Z();
            }
            int myTid = Process.myTid();
            long currentTimeMillis = System.currentTimeMillis();
            z.mTid = myTid;
            z.mTimeMillis = currentTimeMillis;
            z.mTag = str;
            z.mMessage = str2;
            z.f2231qK = C1425ba.m3595ja(i);
            this.f2233rK.add(z);
        }
    }

    public void dump(PrintWriter printWriter) {
        int myPid = Process.myPid();
        synchronized (this.mLock) {
            for (int i = 0; i < this.f2233rK.count(); i++) {
                C1421Z z = (C1421Z) this.f2233rK.get(i);
                printWriter.println(String.format("%s %5d %5d %s %s: %s", new Object[]{this.f2234sK.format(Long.valueOf(z.mTimeMillis)), Integer.valueOf(myPid), Integer.valueOf(z.mTid), z.f2231qK, z.mTag, z.mMessage}));
            }
        }
    }
}
