package android.support.p016v4.p017os;

import android.os.Bundle;
import android.os.Handler;

/* renamed from: android.support.v4.os.e */
class C0115e extends C0112b {
    final /* synthetic */ ResultReceiver this$0;

    C0115e(ResultReceiver resultReceiver) {
        this.this$0 = resultReceiver;
    }

    public void send(int i, Bundle bundle) {
        ResultReceiver resultReceiver = this.this$0;
        Handler handler = resultReceiver.mHandler;
        if (handler != null) {
            handler.post(new C0116f(resultReceiver, i, bundle));
        } else {
            resultReceiver.onReceiveResult(i, bundle);
        }
    }
}
