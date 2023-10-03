package android.support.p016v4.p017os;

import android.os.Bundle;

/* renamed from: android.support.v4.os.f */
class C0116f implements Runnable {
    final int mResultCode;
    final Bundle mResultData;
    final /* synthetic */ ResultReceiver this$0;

    C0116f(ResultReceiver resultReceiver, int i, Bundle bundle) {
        this.this$0 = resultReceiver;
        this.mResultCode = i;
        this.mResultData = bundle;
    }

    public void run() {
        this.this$0.onReceiveResult(this.mResultCode, this.mResultData);
    }
}
