package p000;

import java.util.NoSuchElementException;

/* renamed from: ikt */
/* compiled from: PG */
final class ikt extends ihm {

    /* renamed from: a */
    private final ikv f14407a = new ikv(this.f14409c);

    /* renamed from: b */
    private ihq f14408b = m13868b();

    /* renamed from: c */
    private final /* synthetic */ ikx f14409c;

    public ikt(ikx ikx) {
        this.f14409c = ikx;
    }

    public final boolean hasNext() {
        return this.f14408b != null;
    }

    /* renamed from: a */
    public final byte mo8591a() {
        ihq ihq = this.f14408b;
        if (ihq != null) {
            byte a = ihq.mo8591a();
            if (!this.f14408b.hasNext()) {
                this.f14408b = m13868b();
            }
            return a;
        }
        throw new NoSuchElementException();
    }

    /* renamed from: b */
    private final ihq m13868b() {
        if (this.f14407a.hasNext()) {
            return this.f14407a.next().iterator();
        }
        return null;
    }
}
