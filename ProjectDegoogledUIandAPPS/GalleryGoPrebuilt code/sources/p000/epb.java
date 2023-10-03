package p000;

/* renamed from: epb */
/* compiled from: PG */
public abstract class epb {

    /* renamed from: a */
    public Object f8737a;

    /* renamed from: b */
    public boolean f8738b = false;

    /* renamed from: c */
    private final /* synthetic */ epi f8739c;

    public epb(epi epi, Object obj) {
        this.f8739c = epi;
        this.f8737a = obj;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract void mo5100b();

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public abstract void mo5101c();

    /* renamed from: e */
    public final void mo5106e() {
        synchronized (this) {
            this.f8737a = null;
        }
    }

    /* renamed from: d */
    public final void mo5105d() {
        mo5106e();
        synchronized (this.f8739c.f8755i) {
            this.f8739c.f8755i.remove(this);
        }
    }
}
