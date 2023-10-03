package p000;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/* renamed from: bcs */
/* compiled from: PG */
public final class bcs implements bcp {

    /* renamed from: a */
    public final bco f2061a;

    /* renamed from: b */
    public boolean f2062b;

    /* renamed from: c */
    private final Context f2063c;

    /* renamed from: d */
    private boolean f2064d;

    /* renamed from: e */
    private final BroadcastReceiver f2065e = new bcr(this);

    public bcs(Context context, bco bco) {
        this.f2063c = context.getApplicationContext();
        this.f2061a = bco;
    }

    /* renamed from: d */
    public final void mo1444d() {
    }

    /* renamed from: a */
    static final boolean m2164a(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) cns.m4632a((Object) (ConnectivityManager) context.getSystemService("connectivity"))).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                return false;
            }
            return true;
        } catch (RuntimeException e) {
            if (Log.isLoggable("ConnectivityMonitor", 5)) {
                Log.w("ConnectivityMonitor", "Failed to determine connectivity status when connectivity changed", e);
            }
            return true;
        }
    }

    /* renamed from: b */
    public final void mo1442b() {
        if (!this.f2064d) {
            this.f2062b = m2164a(this.f2063c);
            try {
                this.f2063c.registerReceiver(this.f2065e, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                this.f2064d = true;
            } catch (SecurityException e) {
                if (Log.isLoggable("ConnectivityMonitor", 5)) {
                    Log.w("ConnectivityMonitor", "Failed to register", e);
                }
            }
        }
    }

    /* renamed from: c */
    public final void mo1443c() {
        if (this.f2064d) {
            this.f2063c.unregisterReceiver(this.f2065e);
            this.f2064d = false;
        }
    }
}
