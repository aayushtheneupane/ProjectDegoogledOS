package com.android.messaging.p041ui.mediapicker;

/* renamed from: com.android.messaging.ui.mediapicker.ba */
class C1301ba extends Thread {
    final /* synthetic */ C1303ca this$0;

    C1301ba(C1303ca caVar) {
        this.this$0 = caVar;
    }

    public void run() {
        while (true) {
            try {
                synchronized (C1303ca.class) {
                    if (this.this$0.f2061oI != null) {
                        this.this$0.f2059Tk.mo7767Ia(this.this$0.getAmplitude());
                    } else {
                        return;
                    }
                }
                Thread.sleep(100);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
