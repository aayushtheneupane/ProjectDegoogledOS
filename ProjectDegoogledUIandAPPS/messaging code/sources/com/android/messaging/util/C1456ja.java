package com.android.messaging.util;

/* renamed from: com.android.messaging.util.ja */
final class C1456ja extends Thread {
    final /* synthetic */ C1462ma this$0;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    C1456ja(com.android.messaging.util.C1462ma r2) {
        /*
            r1 = this;
            r1.this$0 = r2
            java.lang.String r0 = "NotificationPlayer-"
            java.lang.StringBuilder r0 = p026b.p027a.p030b.p031a.C0632a.m1011Pa(r0)
            java.lang.String r2 = r2.mTag
            r0.append(r2)
            java.lang.String r2 = r0.toString()
            r1.<init>(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.util.C1456ja.<init>(com.android.messaging.util.ma):void");
    }

    public void run() {
        C1458ka kaVar;
        while (true) {
            synchronized (this.this$0.f2321zK) {
                kaVar = (C1458ka) this.this$0.f2321zK.removeFirst();
            }
            int i = kaVar.code;
            if (i == 1) {
                this.this$0.m3745b(kaVar);
            } else if (i == 2) {
                C1462ma.m3746b(this.this$0, kaVar);
            }
            synchronized (this.this$0.f2321zK) {
                if (this.this$0.f2321zK.size() == 0) {
                    C1456ja unused = this.this$0.mThread = null;
                    C1462ma.m3748d(this.this$0);
                    return;
                }
            }
        }
        while (true) {
        }
    }
}
