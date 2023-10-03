package p000;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import java.util.HashMap;
import java.util.Map;

/* renamed from: eqa */
/* compiled from: PG */
final class eqa implements ServiceConnection {

    /* renamed from: a */
    public final Map f8822a = new HashMap();

    /* renamed from: b */
    public int f8823b = 2;

    /* renamed from: c */
    public boolean f8824c;

    /* renamed from: d */
    public IBinder f8825d;

    /* renamed from: e */
    public final epy f8826e;

    /* renamed from: f */
    public ComponentName f8827f;

    /* renamed from: g */
    public final /* synthetic */ eqb f8828g;

    public eqa(eqb eqb, epy epy) {
        this.f8828g = eqb;
        this.f8826e = epy;
    }

    /* renamed from: a */
    public final void mo5151a(ServiceConnection serviceConnection, ServiceConnection serviceConnection2) {
        this.f8826e.mo5143a();
        this.f8822a.put(serviceConnection, serviceConnection2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0054  */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo5154b() {
        /*
            r5 = this;
            r0 = 3
            r5.f8823b = r0
            eqb r0 = r5.f8828g
            android.content.Context r0 = r0.f8830d
            epy r1 = r5.f8826e
            android.content.Intent r1 = r1.mo5143a()
            android.content.ComponentName r2 = r1.getComponent()
            r3 = 0
            if (r2 == 0) goto L_0x0035
            java.lang.String r2 = r2.getPackageName()
            java.lang.String r4 = "com.google.android.gms"
            r4.equals(r2)
            erb r4 = p000.erc.m8049a(r0)     // Catch:{ NameNotFoundException -> 0x0034 }
            android.content.pm.ApplicationInfo r2 = r4.mo5177a(r2, r3)     // Catch:{ NameNotFoundException -> 0x0034 }
            int r2 = r2.flags     // Catch:{ NameNotFoundException -> 0x0034 }
            r4 = 2097152(0x200000, float:2.938736E-39)
            r2 = r2 & r4
            if (r2 == 0) goto L_0x0035
            java.lang.String r0 = "ConnectionTracker"
            java.lang.String r1 = "Attempted to bind to a service in a STOPPED package."
            android.util.Log.w(r0, r1)
            goto L_0x003b
        L_0x0034:
            r2 = move-exception
        L_0x0035:
            r2 = 129(0x81, float:1.81E-43)
            boolean r3 = r0.bindService(r1, r5, r2)
        L_0x003b:
            r5.f8824c = r3
            if (r3 == 0) goto L_0x0054
            eqb r0 = r5.f8828g
            android.os.Handler r0 = r0.f8831e
            epy r1 = r5.f8826e
            r2 = 1
            android.os.Message r0 = r0.obtainMessage(r2, r1)
            eqb r1 = r5.f8828g
            android.os.Handler r2 = r1.f8831e
            long r3 = r1.f8832f
            r2.sendMessageDelayed(r0, r3)
            return
        L_0x0054:
            r0 = 2
            r5.f8823b = r0
            eqb r0 = r5.f8828g     // Catch:{ IllegalArgumentException -> 0x005f }
            android.content.Context r0 = r0.f8830d     // Catch:{ IllegalArgumentException -> 0x005f }
            r0.unbindService(r5)     // Catch:{ IllegalArgumentException -> 0x005f }
            return
        L_0x005f:
            r0 = move-exception
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.eqa.mo5154b():void");
    }

    /* renamed from: a */
    public final boolean mo5153a(ServiceConnection serviceConnection) {
        return this.f8822a.containsKey(serviceConnection);
    }

    /* renamed from: a */
    public final boolean mo5152a() {
        return this.f8822a.isEmpty();
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        synchronized (this.f8828g.f8829c) {
            this.f8828g.f8831e.removeMessages(1, this.f8826e);
            this.f8825d = iBinder;
            this.f8827f = componentName;
            for (ServiceConnection onServiceConnected : this.f8822a.values()) {
                onServiceConnected.onServiceConnected(componentName, iBinder);
            }
            this.f8823b = 1;
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        synchronized (this.f8828g.f8829c) {
            this.f8828g.f8831e.removeMessages(1, this.f8826e);
            this.f8825d = null;
            this.f8827f = componentName;
            for (ServiceConnection onServiceDisconnected : this.f8822a.values()) {
                onServiceDisconnected.onServiceDisconnected(componentName);
            }
            this.f8823b = 2;
        }
    }
}
