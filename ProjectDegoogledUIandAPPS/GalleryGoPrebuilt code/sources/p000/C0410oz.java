package p000;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;

/* renamed from: oz */
/* compiled from: PG */
abstract class C0410oz {

    /* renamed from: a */
    private BroadcastReceiver f15432a;

    /* renamed from: b */
    private final /* synthetic */ C0416pe f15433b;

    public C0410oz(C0416pe peVar) {
        this.f15433b = peVar;
    }

    /* renamed from: a */
    public abstract int mo9590a();

    /* renamed from: b */
    public abstract void mo9591b();

    /* renamed from: c */
    public abstract IntentFilter mo9592c();

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public final void mo9595e() {
        BroadcastReceiver broadcastReceiver = this.f15432a;
        if (broadcastReceiver != null) {
            try {
                this.f15433b.f15485d.unregisterReceiver(broadcastReceiver);
            } catch (IllegalArgumentException e) {
            }
            this.f15432a = null;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public final void mo9594d() {
        mo9595e();
        IntentFilter c = mo9592c();
        if (c != null && c.countActions() != 0) {
            if (this.f15432a == null) {
                this.f15432a = new C0409oy(this);
            }
            this.f15433b.f15485d.registerReceiver(this.f15432a, c);
        }
    }
}
