package p000;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.firebase.jobdispatcher.GooglePlayReceiver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/* renamed from: bic */
/* compiled from: PG */
public final class bic implements ServiceConnection {

    /* renamed from: a */
    private final Map f2438a = new HashMap();

    /* renamed from: b */
    private final bhn f2439b;

    /* renamed from: c */
    private final Context f2440c;

    /* renamed from: d */
    private boolean f2441d = false;

    /* renamed from: e */
    private bhq f2442e;

    public bic(bhn bhn, Context context) {
        this.f2439b = bhn;
        this.f2440c = context;
    }

    /* renamed from: d */
    private final synchronized boolean m2601d() {
        return this.f2442e != null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final synchronized boolean mo2077a() {
        return this.f2441d;
    }

    /* renamed from: a */
    private static Bundle m2598a(bhx bhx) {
        return GooglePlayReceiver.f4795a.mo2057a(bhx, new Bundle());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public final synchronized void mo2080c() {
        if (!m2601d() && !mo2077a()) {
            Log.w("FJD.ExecutionDelegator", "Binder connection to JobService timed out");
            mo2078b();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final synchronized void mo2075a(bhw bhw) {
        this.f2438a.remove(bhw);
        if (this.f2438a.isEmpty()) {
            mo2078b();
        }
    }

    public final synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        bhq bhq;
        if (mo2077a()) {
            Log.w("FJD.ExecutionDelegator", "Connection have been used already.");
            return;
        }
        if (iBinder != null) {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.firebase.jobdispatcher.IRemoteJobService");
            bhq = queryLocalInterface instanceof bhq ? (bhq) queryLocalInterface : new bho(iBinder);
        } else {
            bhq = null;
        }
        this.f2442e = bhq;
        if (bhq == null) {
            String valueOf = String.valueOf(iBinder);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 22);
            sb.append("null binder provided: ");
            sb.append(valueOf);
            Log.w("FJD.ExecutionDelegator", sb.toString(), new RuntimeException());
            mo2078b();
        }
        HashSet<bhw> hashSet = new HashSet<>();
        for (Map.Entry entry : this.f2438a.entrySet()) {
            if (Boolean.FALSE.equals(entry.getValue())) {
                try {
                    this.f2442e.mo2043a(m2598a((bhx) entry.getKey()), this.f2439b);
                    hashSet.add((bhw) entry.getKey());
                } catch (RemoteException e) {
                    String valueOf2 = String.valueOf(entry.getKey());
                    StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 20);
                    sb2.append("Failed to start job ");
                    sb2.append(valueOf2);
                    Log.e("FJD.ExecutionDelegator", sb2.toString(), e);
                    mo2078b();
                    return;
                }
            }
        }
        for (bhw put : hashSet) {
            this.f2438a.put(put, true);
        }
    }

    public final synchronized void onServiceDisconnected(ComponentName componentName) {
        mo2078b();
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002c, code lost:
        return;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void mo2076a(p000.bhw r3, boolean r4) {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.mo2077a()     // Catch:{ all -> 0x0036 }
            if (r0 != 0) goto L_0x002d
            java.lang.Boolean r0 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x0036 }
            java.util.Map r1 = r2.f2438a     // Catch:{ all -> 0x0036 }
            java.lang.Object r1 = r1.remove(r3)     // Catch:{ all -> 0x0036 }
            boolean r0 = r0.equals(r1)     // Catch:{ all -> 0x0036 }
            if (r0 == 0) goto L_0x001e
            boolean r0 = r2.m2601d()     // Catch:{ all -> 0x0036 }
            if (r0 == 0) goto L_0x001e
            r2.m2599a((boolean) r4, (p000.bhw) r3)     // Catch:{ all -> 0x0036 }
        L_0x001e:
            if (r4 != 0) goto L_0x002b
            java.util.Map r3 = r2.f2438a     // Catch:{ all -> 0x0036 }
            boolean r3 = r3.isEmpty()     // Catch:{ all -> 0x0036 }
            if (r3 == 0) goto L_0x002b
            r2.mo2078b()     // Catch:{ all -> 0x0036 }
        L_0x002b:
            monitor-exit(r2)
            return
        L_0x002d:
            java.lang.String r3 = "FJD.ExecutionDelegator"
            java.lang.String r4 = "Can't send stop request because service was unbound."
            android.util.Log.w(r3, r4)     // Catch:{ all -> 0x0036 }
            monitor-exit(r2)
            return
        L_0x0036:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.bic.mo2076a(bhw, boolean):void");
    }

    /* renamed from: c */
    private final void m2600c(bhw bhw) {
        try {
            this.f2439b.mo2041a(m2598a((bhx) bhw), 1);
        } catch (RemoteException e) {
            String str = bhw.f2412a;
            String valueOf = String.valueOf(e);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 31 + String.valueOf(valueOf).length());
            sb.append("Error sending result for job ");
            sb.append(str);
            sb.append(": ");
            sb.append(valueOf);
            Log.e("FJD.ExecutionDelegator", sb.toString());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final synchronized void mo2079b(bhw bhw) {
        if (mo2077a()) {
            m2600c(bhw);
        }
        boolean d = m2601d();
        if (d) {
            if (Boolean.TRUE.equals((Boolean) this.f2438a.get(bhw))) {
                String valueOf = String.valueOf(bhw);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 54);
                sb.append("Received an execution request for already running job ");
                sb.append(valueOf);
                Log.w("FJD.ExecutionDelegator", sb.toString());
                m2599a(false, bhw);
            }
            try {
                this.f2442e.mo2043a(m2598a((bhx) bhw), this.f2439b);
            } catch (RemoteException e) {
                String valueOf2 = String.valueOf(bhw);
                StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 24);
                sb2.append("Failed to start the job ");
                sb2.append(valueOf2);
                Log.e("FJD.ExecutionDelegator", sb2.toString(), e);
                mo2078b();
                return;
            }
        }
        this.f2438a.put(bhw, Boolean.valueOf(d));
    }

    /* renamed from: a */
    private final synchronized void m2599a(boolean z, bhw bhw) {
        try {
            this.f2442e.mo2044a(m2598a((bhx) bhw), z);
        } catch (RemoteException e) {
            Log.e("FJD.ExecutionDelegator", "Failed to stop a job", e);
            mo2078b();
        }
    }

    /* renamed from: b */
    public final synchronized void mo2078b() {
        if (!mo2077a()) {
            this.f2442e = null;
            this.f2441d = true;
            ArrayList arrayList = new ArrayList(this.f2438a.size());
            Iterator it = this.f2438a.keySet().iterator();
            while (it.hasNext()) {
                arrayList.add((bhw) it.next());
                it.remove();
            }
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                m2600c((bhw) arrayList.get(i));
            }
            try {
                this.f2440c.unbindService(this);
            } catch (IllegalArgumentException e) {
                String valueOf = String.valueOf(e.getMessage());
                Log.w("FJD.ExecutionDelegator", valueOf.length() == 0 ? new String("Error unbinding service: ") : "Error unbinding service: ".concat(valueOf));
            }
        }
    }
}
