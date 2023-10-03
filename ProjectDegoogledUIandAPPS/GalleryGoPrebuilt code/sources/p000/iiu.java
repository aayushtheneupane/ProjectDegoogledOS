package p000;

/* renamed from: iiu */
/* compiled from: PG */
public abstract class iiu extends iix implements iiv {

    /* renamed from: j */
    public iim f14321j = iim.f14254c;

    /* renamed from: a */
    public final boolean mo8784a(iih iih) {
        throw null;
    }

    /* renamed from: a */
    public final iim mo8785a() {
        iim iim = this.f14321j;
        if (iim.f14256b) {
            this.f14321j = iim.clone();
        }
        return this.f14321j;
    }

    /* renamed from: b */
    public final void mo8786b(iih iih) {
        if (iih.f14241a != ((iix) mo8790b(6))) {
            throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
        }
    }
}
