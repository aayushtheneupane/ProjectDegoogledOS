package p000;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

/* renamed from: akc */
/* compiled from: PG */
public final class akc extends aka {

    /* renamed from: e */
    public static final String f664e = iol.m14236b("NetworkStateTracker");

    /* renamed from: f */
    private final ConnectivityManager f665f = ((ConnectivityManager) this.f658a.getSystemService("connectivity"));

    /* renamed from: g */
    private final akb f666g;

    public akc(Context context, amz amz) {
        super(context, amz);
        int i = Build.VERSION.SDK_INT;
        this.f666g = new akb(this);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final ajj mo560b() {
        NetworkInfo activeNetworkInfo = this.f665f.getActiveNetworkInfo();
        boolean z = true;
        boolean z2 = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        int i = Build.VERSION.SDK_INT;
        NetworkCapabilities networkCapabilities = this.f665f.getNetworkCapabilities(this.f665f.getActiveNetwork());
        boolean z3 = networkCapabilities != null && networkCapabilities.hasCapability(16);
        boolean a = C0321lr.m14630a(this.f665f);
        if (activeNetworkInfo == null || activeNetworkInfo.isRoaming()) {
            z = false;
        }
        return new ajj(z2, z3, a, z);
    }

    /* renamed from: c */
    public final void mo562c() {
        int i = Build.VERSION.SDK_INT;
        try {
            iol.m14231a();
            this.f665f.registerDefaultNetworkCallback(this.f666g);
        } catch (IllegalArgumentException e) {
            iol.m14231a();
            iol.m14234a(f664e, "Received exception while unregistering network callback", e);
        }
    }

    /* renamed from: d */
    public final void mo563d() {
        int i = Build.VERSION.SDK_INT;
        try {
            iol.m14231a();
            this.f665f.unregisterNetworkCallback(this.f666g);
        } catch (IllegalArgumentException e) {
            iol.m14231a();
            iol.m14234a(f664e, "Received exception while unregistering network callback", e);
        }
    }
}
