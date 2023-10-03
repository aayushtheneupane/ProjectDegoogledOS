package p000;

import android.content.Intent;

/* renamed from: hfh */
/* compiled from: PG */
final /* synthetic */ class hfh implements icf {

    /* renamed from: a */
    private final hfk f12650a;

    /* renamed from: b */
    private final Intent f12651b;

    public hfh(hfk hfk, Intent intent) {
        this.f12650a = hfk;
        this.f12651b = intent;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        hfk hfk = this.f12650a;
        Intent intent = this.f12651b;
        hfl a = hfk.mo3316a(obj);
        if (a != null) {
            return (ieh) ife.m12869b((Object) a.mo2555a(intent), (Object) "onReceive must return a ListenableFuture.");
        }
        ((hvv) ((hvv) hfk.f12654a.mo8178a()).mo8201a("com/google/apps/tiktok/receiver/IntentFilterAcledReceiver", "lambda$handleUnorderedBroadcast$1", 117, "IntentFilterAcledReceiver.java")).mo8203a("Ordered receiver got unordered broadcast.");
        return ife.m12820a((Object) null);
    }
}
