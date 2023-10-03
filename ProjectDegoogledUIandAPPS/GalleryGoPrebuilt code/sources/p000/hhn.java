package p000;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Process;
import com.google.apps.tiktok.sync.constraints.onnetworkunmetered.OnNetworkUnmeteredConstraintReceiver_Receiver;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/* renamed from: hhn */
/* compiled from: PG */
public final class hhn implements hhf {

    /* renamed from: a */
    private static final hgz f12745a = hgz.ON_NETWORK_UNMETERED;

    /* renamed from: b */
    private final Set f12746b = new HashSet();

    /* renamed from: c */
    private final ConnectivityManager f12747c;

    /* renamed from: d */
    private final Context f12748d;

    /* renamed from: e */
    private final PackageManager f12749e;

    /* renamed from: f */
    private final iqk f12750f;

    /* renamed from: g */
    private boolean f12751g;

    public hhn(Context context, PackageManager packageManager, ConnectivityManager connectivityManager, iqk iqk) {
        boolean z = false;
        this.f12751g = false;
        if (context.checkPermission("android.permission.INTERNET", Process.myPid(), Process.myUid()) == 0) {
            ife.m12876b(context.checkPermission("android.permission.ACCESS_NETWORK_STATE", Process.myPid(), Process.myUid()) == 0 ? true : z, (Object) "An app using the NETWORK_UNMETERED sync constraint must have the ACCESS_NETWORK_STATE permission.");
        }
        this.f12748d = context;
        this.f12749e = packageManager;
        this.f12747c = connectivityManager;
        this.f12750f = iqk;
    }

    /* renamed from: a */
    public final bhr mo7441a(bhr bhr) {
        bhr.mo2046a(1);
        return bhr;
    }

    /* renamed from: a */
    private final void m11513a(boolean z) {
        this.f12749e.setComponentEnabledSetting(new ComponentName(this.f12748d, OnNetworkUnmeteredConstraintReceiver_Receiver.class), !z ? 2 : 1, 1);
    }

    /* renamed from: c */
    private final boolean m11514c() {
        boolean a = C0321lr.m14630a(this.f12747c);
        NetworkInfo activeNetworkInfo = this.f12747c.getActiveNetworkInfo();
        return !a && activeNetworkInfo != null && activeNetworkInfo.isConnected() && activeNetworkInfo.getDetailedState() != NetworkInfo.DetailedState.BLOCKED;
    }

    /* renamed from: a */
    public final synchronized boolean mo7443a() {
        if (!this.f12746b.isEmpty()) {
            return this.f12751g;
        }
        return m11514c();
    }

    /* renamed from: b */
    public final synchronized void mo7444b(hhg hhg) {
        this.f12751g = mo7443a();
        if (this.f12746b.isEmpty()) {
            m11513a(true);
        }
        this.f12746b.add(hhg);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final synchronized ieh mo7448b() {
        boolean c = m11514c();
        if (this.f12751g != c) {
            this.f12751g = c;
            Set set = (Set) this.f12750f.mo2097a();
            HashSet<hhg> hashSet = new HashSet<>(set.size() + this.f12746b.size());
            hashSet.addAll(this.f12746b);
            hashSet.addAll(set);
            ArrayList arrayList = new ArrayList(hashSet.size());
            for (hhg a : hashSet) {
                arrayList.add(a.mo7445a(f12745a));
            }
            return ife.m12895d((Iterable) arrayList);
        }
        return ife.m12820a((Object) null);
    }

    /* renamed from: a */
    public final synchronized void mo7442a(hhg hhg) {
        this.f12746b.remove(hhg);
        if (this.f12746b.isEmpty()) {
            m11513a(false);
        }
    }
}
