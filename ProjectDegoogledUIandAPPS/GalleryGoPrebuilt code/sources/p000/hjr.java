package p000;

import com.google.apps.tiktok.sync.impl.gcm.SyncFirebaseJobService;

/* renamed from: hjr */
/* compiled from: PG */
public final class hjr implements idw {

    /* renamed from: a */
    private final /* synthetic */ bhx f12866a;

    /* renamed from: b */
    private final /* synthetic */ SyncFirebaseJobService f12867b;

    public hjr(SyncFirebaseJobService syncFirebaseJobService, bhx bhx) {
        this.f12867b = syncFirebaseJobService;
        this.f12866a = bhx;
    }

    /* renamed from: a */
    public final void mo3868a(Throwable th) {
        ((hvv) ((hvv) ((hvv) SyncFirebaseJobService.f5310d.mo8178a()).mo8202a(th)).mo8201a("com/google/apps/tiktok/sync/impl/gcm/SyncFirebaseJobService$1", "onFailure", 74, "SyncFirebaseJobService.java")).mo8203a("Failed to sync");
        this.f12867b.mo3721a(((bhw) this.f12866a).f2412a);
        this.f12867b.mo2068a(this.f12866a, true);
    }

    /* renamed from: a */
    public final void mo3867a(Object obj) {
        this.f12867b.mo3721a(((bhw) this.f12866a).f2412a);
        this.f12867b.mo2068a(this.f12866a, false);
    }
}
