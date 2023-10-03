package p000;

import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: iow */
/* compiled from: PG */
public abstract class iow implements ioy {

    /* renamed from: a */
    public final iov f14611a = new iov((byte[]) null);

    /* renamed from: b */
    private final AtomicBoolean f14612b = new AtomicBoolean();

    protected iow() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public abstract ieh mo9052c();

    /* renamed from: a */
    public final void mo9046a(boolean z) {
        this.f14612b.set(true);
        this.f14611a.mo9051a(z);
    }

    /* renamed from: b */
    public final ieh mo9044b() {
        if (this.f14612b.compareAndSet(false, true)) {
            this.f14611a.mo6946a(mo9052c());
        }
        return this.f14611a;
    }

    /* renamed from: ak */
    public final ioq mo9047ak() {
        return new iou(this);
    }

    /* renamed from: a */
    public final ioq mo9045a(ioz ioz) {
        iou iou = new iou(this);
        iou.f14609a.mo53a(new iot(iou, ioz), idh.f13918a);
        return iou;
    }
}
