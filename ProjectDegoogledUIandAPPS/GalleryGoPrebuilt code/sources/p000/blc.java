package p000;

import java.util.concurrent.TimeUnit;

/* renamed from: blc */
/* compiled from: PG */
public final class blc {

    /* renamed from: a */
    public final inw f3093a;

    /* renamed from: b */
    public final inw f3094b;

    /* renamed from: c */
    private final Object f3095c = new Object();

    /* renamed from: d */
    private final iel f3096d;

    /* renamed from: e */
    private boolean f3097e = true;

    /* renamed from: f */
    private boolean f3098f = true;

    public blc(inw inw, inw inw2, iel iel) {
        this.f3093a = inw;
        this.f3094b = inw2;
        this.f3096d = iel;
    }

    /* renamed from: a */
    public final void mo2550a() {
        synchronized (this.f3095c) {
            this.f3098f = true;
            this.f3097e = false;
        }
    }

    /* renamed from: c */
    public final void mo2552c() {
        synchronized (this.f3095c) {
            this.f3097e = true;
            if (!this.f3098f) {
                cwn.m5509a((ieh) this.f3096d.mo5935a(hmq.m11748a((Runnable) new bla(this)), 1600, TimeUnit.MILLISECONDS), "OnStartSyncer: scheduled sync failed", new Object[0]);
            }
            this.f3098f = false;
        }
    }

    /* renamed from: b */
    public final void mo2551b() {
        synchronized (this.f3095c) {
            if (this.f3097e) {
                this.f3097e = false;
                cwn.m5509a((ieh) this.f3096d.mo5935a(hmq.m11748a((Runnable) new blb(this)), 300, TimeUnit.MILLISECONDS), "OnStartSyncer: Failed to sync", new Object[0]);
            }
        }
    }
}
