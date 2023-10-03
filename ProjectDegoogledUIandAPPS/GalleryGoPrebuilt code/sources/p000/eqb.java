package p000;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.util.HashMap;

/* renamed from: eqb */
/* compiled from: PG */
final class eqb extends epz implements Handler.Callback {

    /* renamed from: c */
    public final HashMap f8829c = new HashMap();

    /* renamed from: d */
    public final Context f8830d;

    /* renamed from: e */
    public final Handler f8831e;

    /* renamed from: f */
    public final long f8832f;

    /* renamed from: g */
    private final long f8833g;

    public eqb(Context context) {
        this.f8830d = context.getApplicationContext();
        this.f8831e = new eui(context.getMainLooper(), this);
        if (eqy.f8858b == null) {
            synchronized (eqy.f8857a) {
                if (eqy.f8858b == null) {
                    eqy.f8858b = new eqy();
                }
            }
        }
        eqy eqy = eqy.f8858b;
        this.f8833g = 5000;
        this.f8832f = 300000;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final boolean mo5149b(epy epy, ServiceConnection serviceConnection) {
        boolean z;
        abj.m86a((Object) serviceConnection, (Object) "ServiceConnection must not be null");
        synchronized (this.f8829c) {
            eqa eqa = (eqa) this.f8829c.get(epy);
            if (eqa == null) {
                eqa = new eqa(this, epy);
                eqa.mo5151a(serviceConnection, serviceConnection);
                eqa.mo5154b();
                this.f8829c.put(epy, eqa);
            } else {
                this.f8831e.removeMessages(0, epy);
                if (!eqa.mo5153a(serviceConnection)) {
                    eqa.mo5151a(serviceConnection, serviceConnection);
                    int i = eqa.f8823b;
                    if (i == 1) {
                        serviceConnection.onServiceConnected(eqa.f8827f, eqa.f8825d);
                    } else if (i == 2) {
                        eqa.mo5154b();
                    }
                } else {
                    String valueOf = String.valueOf(epy);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 81);
                    sb.append("Trying to bind a GmsServiceConnection that was already connected before.  config=");
                    sb.append(valueOf);
                    throw new IllegalStateException(sb.toString());
                }
            }
            z = eqa.f8824c;
        }
        return z;
    }

    public final boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 0) {
            synchronized (this.f8829c) {
                epy epy = (epy) message.obj;
                eqa eqa = (eqa) this.f8829c.get(epy);
                if (eqa != null && eqa.mo5152a()) {
                    if (eqa.f8824c) {
                        eqa.f8828g.f8831e.removeMessages(1, eqa.f8826e);
                        eqa.f8828g.f8830d.unbindService(eqa);
                        eqa.f8824c = false;
                        eqa.f8823b = 2;
                    }
                    this.f8829c.remove(epy);
                }
            }
            return true;
        } else if (i != 1) {
            return false;
        } else {
            synchronized (this.f8829c) {
                epy epy2 = (epy) message.obj;
                eqa eqa2 = (eqa) this.f8829c.get(epy2);
                if (eqa2 != null && eqa2.f8823b == 3) {
                    String valueOf = String.valueOf(epy2);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 47);
                    sb.append("Timeout waiting for ServiceConnection callback ");
                    sb.append(valueOf);
                    Log.e("GmsClientSupervisor", sb.toString(), new Exception());
                    ComponentName componentName = eqa2.f8827f;
                    if (componentName == null) {
                        componentName = null;
                    }
                    if (componentName == null) {
                        componentName = new ComponentName(epy2.f8816a, "unknown");
                    }
                    eqa2.onServiceDisconnected(componentName);
                }
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo5147a(epy epy, ServiceConnection serviceConnection) {
        abj.m86a((Object) serviceConnection, (Object) "ServiceConnection must not be null");
        synchronized (this.f8829c) {
            eqa eqa = (eqa) this.f8829c.get(epy);
            if (eqa == null) {
                String valueOf = String.valueOf(epy);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 50);
                sb.append("Nonexistent connection status for service config: ");
                sb.append(valueOf);
                throw new IllegalStateException(sb.toString());
            } else if (eqa.mo5153a(serviceConnection)) {
                eqa.f8822a.remove(serviceConnection);
                if (eqa.mo5152a()) {
                    this.f8831e.sendMessageDelayed(this.f8831e.obtainMessage(0, epy), this.f8833g);
                }
            } else {
                String valueOf2 = String.valueOf(epy);
                StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 76);
                sb2.append("Trying to unbind a GmsServiceConnection  that was not bound before.  config=");
                sb2.append(valueOf2);
                throw new IllegalStateException(sb2.toString());
            }
        }
    }
}
