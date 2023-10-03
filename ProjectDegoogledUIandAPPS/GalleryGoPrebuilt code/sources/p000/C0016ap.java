package p000;

import android.os.Handler;

/* renamed from: ap */
/* compiled from: PG */
public final class C0016ap {

    /* renamed from: a */
    public final C0002ab f1311a;

    /* renamed from: b */
    private final Handler f1312b = new Handler();

    /* renamed from: c */
    private C0015ao f1313c;

    public C0016ap(C0681z zVar) {
        this.f1311a = new C0002ab(zVar);
    }

    /* renamed from: a */
    public final void mo1401a(C0546u uVar) {
        C0015ao aoVar = this.f1313c;
        if (aoVar != null) {
            aoVar.run();
        }
        C0015ao aoVar2 = new C0015ao(this.f1311a, uVar);
        this.f1313c = aoVar2;
        this.f1312b.postAtFrontOfQueue(aoVar2);
    }
}
