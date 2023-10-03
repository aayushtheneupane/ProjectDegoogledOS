package p000;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BasePendingResult;

/* renamed from: elw */
/* compiled from: PG */
public final class elw extends eui {
    public elw() {
        super(Looper.getMainLooper());
    }

    public elw(Looper looper) {
        super(looper);
    }

    public final void handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            Pair pair = (Pair) message.obj;
            elb elb = (elb) pair.first;
            ela ela = (ela) pair.second;
            try {
                elb.mo4954a(ela);
            } catch (RuntimeException e) {
                BasePendingResult.m4944b(ela);
                throw e;
            }
        } else if (i != 2) {
            int i2 = message.what;
            StringBuilder sb = new StringBuilder(45);
            sb.append("Don't know how to handle message: ");
            sb.append(i2);
            Log.wtf("BasePendingResult", sb.toString(), new Exception());
        } else {
            ((BasePendingResult) message.obj).mo3513c(Status.f4975d);
        }
    }

    /* renamed from: a */
    public final void mo4989a(elb elb, ela ela) {
        int i = BasePendingResult.f4982g;
        sendMessage(obtainMessage(1, new Pair(elb, ela)));
    }
}
