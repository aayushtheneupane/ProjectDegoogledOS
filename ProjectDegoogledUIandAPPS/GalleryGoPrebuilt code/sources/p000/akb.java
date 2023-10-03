package p000;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;

/* renamed from: akb */
/* compiled from: PG */
final class akb extends ConnectivityManager.NetworkCallback {

    /* renamed from: a */
    private final /* synthetic */ akc f663a;

    public akb(akc akc) {
        this.f663a = akc;
    }

    public final void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        iol.m14231a();
        String str = akc.f664e;
        String.format("Network capabilities changed: %s", new Object[]{networkCapabilities});
        akc akc = this.f663a;
        akc.mo566a((Object) akc.mo560b());
    }

    public final void onLost(Network network) {
        iol.m14231a();
        String str = akc.f664e;
        akc akc = this.f663a;
        akc.mo566a((Object) akc.mo560b());
    }
}
