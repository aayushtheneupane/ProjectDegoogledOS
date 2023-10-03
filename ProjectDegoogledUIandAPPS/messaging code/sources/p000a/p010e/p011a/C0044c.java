package p000a.p010e.p011a;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;

/* renamed from: a.e.a.c */
final class C0044c {
    final IntentFilter filter;

    /* renamed from: mq */
    final BroadcastReceiver f19mq;

    /* renamed from: nq */
    boolean f20nq;

    /* renamed from: oq */
    boolean f21oq;

    C0044c(IntentFilter intentFilter, BroadcastReceiver broadcastReceiver) {
        this.filter = intentFilter;
        this.f19mq = broadcastReceiver;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("Receiver{");
        sb.append(this.f19mq);
        sb.append(" filter=");
        sb.append(this.filter);
        if (this.f21oq) {
            sb.append(" DEAD");
        }
        sb.append("}");
        return sb.toString();
    }
}
