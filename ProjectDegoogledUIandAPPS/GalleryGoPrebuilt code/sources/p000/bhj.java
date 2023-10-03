package p000;

import android.app.AppOpsManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import com.firebase.jobdispatcher.GooglePlayReceiver;

/* renamed from: bhj */
/* compiled from: PG */
public final class bhj extends Handler {

    /* renamed from: a */
    private final GooglePlayReceiver f2377a;

    public bhj(Looper looper, GooglePlayReceiver googlePlayReceiver) {
        super(looper);
        this.f2377a = googlePlayReceiver;
    }

    public final void handleMessage(Message message) {
        if (message != null) {
            try {
                ((AppOpsManager) this.f2377a.getApplicationContext().getSystemService("appops")).checkPackage(message.sendingUid, "com.google.android.gms");
                int i = message.what;
                if (i == 1) {
                    Bundle data = message.getData();
                    Messenger messenger = message.replyTo;
                    String string = data.getString("tag");
                    if (messenger != null && string != null) {
                        bhk bhk = new bhk(messenger, string);
                        if (!this.f2377a.mo3312b()) {
                            this.f2377a.mo3310a().mo2037a(this.f2377a.mo3311a((bht) bhk, data));
                            return;
                        }
                        bhk.mo2039a(1);
                    }
                } else if (i == 2) {
                    bhv a = GooglePlayReceiver.f4795a.mo2058a(message.getData());
                    if (a != null) {
                        bhd.m2528a(a.mo2059a(), true);
                    }
                } else if (i != 4) {
                    String valueOf = String.valueOf(message);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 31);
                    sb.append("Unrecognized message received: ");
                    sb.append(valueOf);
                    Log.e("FJD.GooglePlayReceiver", sb.toString());
                }
            } catch (SecurityException e) {
                Log.e("FJD.GooglePlayReceiver", "Message was not sent from GCM.");
            }
        }
    }
}
