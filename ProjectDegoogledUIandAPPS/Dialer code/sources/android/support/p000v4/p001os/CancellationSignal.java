package android.support.p000v4.p001os;

import android.os.Build;

/* renamed from: android.support.v4.os.CancellationSignal */
public final class CancellationSignal {
    private Object mCancellationSignalObj;
    private boolean mIsCanceled;

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r1 = android.os.Build.VERSION.SDK_INT;
        ((android.os.CancellationSignal) r0).cancel();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0017, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0018, code lost:
        monitor-enter(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        notifyAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x001d, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0021, code lost:
        monitor-enter(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        notifyAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0025, code lost:
        monitor-exit(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0026, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000d, code lost:
        if (r0 == null) goto L_0x0021;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void cancel() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.mIsCanceled     // Catch:{ all -> 0x002a }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r2)     // Catch:{ all -> 0x002a }
            return
        L_0x0007:
            r0 = 1
            r2.mIsCanceled = r0     // Catch:{ all -> 0x002a }
            java.lang.Object r0 = r2.mCancellationSignalObj     // Catch:{ all -> 0x002a }
            monitor-exit(r2)     // Catch:{ all -> 0x002a }
            if (r0 == 0) goto L_0x0021
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0017 }
            android.os.CancellationSignal r0 = (android.os.CancellationSignal) r0     // Catch:{ all -> 0x0017 }
            r0.cancel()     // Catch:{ all -> 0x0017 }
            goto L_0x0021
        L_0x0017:
            r0 = move-exception
            monitor-enter(r2)
            r2.notifyAll()     // Catch:{ all -> 0x001e }
            monitor-exit(r2)     // Catch:{ all -> 0x001e }
            throw r0
        L_0x001e:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x001e }
            throw r0
        L_0x0021:
            monitor-enter(r2)
            r2.notifyAll()     // Catch:{ all -> 0x0027 }
            monitor-exit(r2)     // Catch:{ all -> 0x0027 }
            return
        L_0x0027:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0027 }
            throw r0
        L_0x002a:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x002a }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.p001os.CancellationSignal.cancel():void");
    }

    public Object getCancellationSignalObject() {
        Object obj;
        int i = Build.VERSION.SDK_INT;
        synchronized (this) {
            if (this.mCancellationSignalObj == null) {
                this.mCancellationSignalObj = new android.os.CancellationSignal();
                if (this.mIsCanceled) {
                    ((android.os.CancellationSignal) this.mCancellationSignalObj).cancel();
                }
            }
            obj = this.mCancellationSignalObj;
        }
        return obj;
    }
}
