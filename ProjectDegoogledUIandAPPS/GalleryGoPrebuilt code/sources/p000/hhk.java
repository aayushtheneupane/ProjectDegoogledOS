package p000;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Process;
import com.google.apps.tiktok.sync.constraints.onnetworkconnected.OnNetworkConnectedConstraintReceiver_Receiver;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/* renamed from: hhk */
/* compiled from: PG */
public final class hhk implements hhf {

    /* renamed from: a */
    private static final hgz f12737a = hgz.ON_NETWORK_CONNECTED;

    /* renamed from: b */
    private final Set f12738b = new HashSet();

    /* renamed from: c */
    private final ConnectivityManager f12739c;

    /* renamed from: d */
    private final Context f12740d;

    /* renamed from: e */
    private final PackageManager f12741e;

    /* renamed from: f */
    private final iqk f12742f;

    /* renamed from: g */
    private boolean f12743g;

    public hhk(Context context, PackageManager packageManager, ConnectivityManager connectivityManager, iqk iqk) {
        boolean z = false;
        this.f12743g = false;
        if (context.checkPermission("android.permission.INTERNET", Process.myPid(), Process.myUid()) == 0) {
            ife.m12876b(context.checkPermission("android.permission.ACCESS_NETWORK_STATE", Process.myPid(), Process.myUid()) == 0 ? true : z, (Object) "An app using the NETWORK_CONNECTED sync constraint must have the ACCESS_NETWORK_STATE permission.");
        }
        this.f12740d = context;
        this.f12741e = packageManager;
        this.f12739c = connectivityManager;
        this.f12742f = iqk;
    }

    /* renamed from: a */
    public final bhr mo7441a(bhr bhr) {
        bhr.mo2046a(2);
        return bhr;
    }

    /* renamed from: a */
    private final void m11504a(boolean z) {
        this.f12741e.setComponentEnabledSetting(new ComponentName(this.f12740d, OnNetworkConnectedConstraintReceiver_Receiver.class), !z ? 2 : 1, 1);
    }

    /* renamed from: c */
    private final boolean m11505c() {
        NetworkInfo activeNetworkInfo = this.f12739c.getActiveNetworkInfo();
        return (activeNetworkInfo == null || !activeNetworkInfo.isConnected() || activeNetworkInfo.getDetailedState() == NetworkInfo.DetailedState.BLOCKED) ? false : true;
    }

    /* renamed from: a */
    public final synchronized boolean mo7443a() {
        if (!this.f12738b.isEmpty()) {
            return this.f12743g;
        }
        return m11505c();
    }

    /* renamed from: b */
    public final synchronized void mo7444b(hhg hhg) {
        this.f12743g = mo7443a();
        if (this.f12738b.isEmpty()) {
            m11504a(true);
        }
        this.f12738b.add(hhg);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final synchronized ieh mo7447b() {
        boolean c = m11505c();
        if (this.f12743g != c) {
            this.f12743g = c;
            Set set = (Set) this.f12742f.mo2097a();
            HashSet<hhg> hashSet = new HashSet<>(set.size() + this.f12738b.size());
            hashSet.addAll(this.f12738b);
            hashSet.addAll(set);
            ArrayList arrayList = new ArrayList(hashSet.size());
            for (hhg a : hashSet) {
                arrayList.add(a.mo7445a(f12737a));
            }
            return ife.m12895d((Iterable) arrayList);
        }
        return ife.m12820a((Object) null);
    }

    /* renamed from: a */
    public final synchronized void mo7442a(hhg hhg) {
        this.f12738b.remove(hhg);
        if (this.f12738b.isEmpty()) {
            m11504a(false);
        }
    }
}
