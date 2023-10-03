package p000;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;

/* renamed from: epd */
/* compiled from: PG */
public final class epd implements ServiceConnection {

    /* renamed from: a */
    private final int f8740a;

    /* renamed from: b */
    private final /* synthetic */ epi f8741b;

    public epd(epi epi, int i) {
        this.f8741b = epi;
        this.f8740a = i;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        eqm eqm;
        int i;
        int i2;
        if (iBinder == null) {
            epi epi = this.f8741b;
            synchronized (epi.f8751e) {
                i = epi.f8756j;
            }
            if (i == 3) {
                epi.f8760n = true;
                i2 = 5;
            } else {
                i2 = 4;
            }
            Handler handler = epi.f8750d;
            handler.sendMessage(handler.obtainMessage(i2, epi.f8762p.get(), 16));
            return;
        }
        synchronized (this.f8741b.f8752f) {
            epi epi2 = this.f8741b;
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
            if (queryLocalInterface != null) {
                if (queryLocalInterface instanceof eqm) {
                    eqm = (eqm) queryLocalInterface;
                    epi2.f8753g = eqm;
                }
            }
            eqm = new eql(iBinder);
            epi2.f8753g = eqm;
        }
        this.f8741b.mo5109a(0, this.f8740a);
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        synchronized (this.f8741b.f8752f) {
            this.f8741b.f8753g = null;
        }
        Handler handler = this.f8741b.f8750d;
        handler.sendMessage(handler.obtainMessage(6, this.f8740a, 1));
    }
}
