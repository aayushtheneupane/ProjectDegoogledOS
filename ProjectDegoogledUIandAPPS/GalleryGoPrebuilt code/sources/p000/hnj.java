package p000;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/* renamed from: hnj */
/* compiled from: PG */
public final class hnj implements ServiceConnection {

    /* renamed from: a */
    private final /* synthetic */ hmu f13107a;

    /* renamed from: b */
    private final /* synthetic */ ServiceConnection f13108b;

    public hnj(hmu hmu, ServiceConnection serviceConnection) {
        this.f13107a = hmu;
        this.f13108b = serviceConnection;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        hmu a = hmu.m11755a(hnf.f13084a);
        hmu.m11756a(this.f13107a);
        try {
            this.f13108b.onServiceConnected(componentName, iBinder);
        } finally {
            hmu.m11756a(a);
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        hmu a = hmu.m11755a(hnf.f13084a);
        hmu.m11756a(this.f13107a);
        try {
            this.f13108b.onServiceDisconnected(componentName);
        } finally {
            hmu.m11756a(a);
        }
    }
}
