package p000;

import android.content.Context;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/* renamed from: ejv */
/* compiled from: PG */
final class ejv extends eui {

    /* renamed from: a */
    private final Context f8452a;

    /* renamed from: b */
    private final /* synthetic */ ejw f8453b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ejv(ejw ejw, Context context) {
        super(Looper.myLooper() == null ? Looper.getMainLooper() : Looper.myLooper());
        this.f8453b = ejw;
        this.f8452a = context.getApplicationContext();
    }

    public final void handleMessage(Message message) {
        if (message.what != 1) {
            int i = message.what;
            StringBuilder sb = new StringBuilder(50);
            sb.append("Don't know how to handle this message: ");
            sb.append(i);
            Log.w("GoogleApiAvailability", sb.toString());
            return;
        }
        int b = this.f8453b.mo4918b(this.f8452a);
        if (ekh.m7672b(b)) {
            this.f8453b.mo4915a(this.f8452a, b);
        }
    }
}
