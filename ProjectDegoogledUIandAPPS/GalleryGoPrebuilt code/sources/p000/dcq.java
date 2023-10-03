package p000;

import java.util.List;

/* renamed from: dcq */
/* compiled from: PG */
public final class dcq extends iox {

    /* renamed from: b */
    private final ioq f6269b;

    /* renamed from: c */
    private final ioq f6270c;

    /* renamed from: d */
    private final ioq f6271d;

    public dcq(iqk iqk, iqk iqk2, ioq ioq, ioq ioq2, ioq ioq3) {
        super(iqk2, iph.m14288a(dcq.class), iqk);
        this.f6269b = ipd.m14274a(ioq);
        this.f6270c = ipd.m14274a(ioq2);
        this.f6271d = ipd.m14274a(ioq3);
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ ieh mo3132b(Object obj) {
        List list = (List) obj;
        boolean booleanValue = ((Boolean) list.get(0)).booleanValue();
        ble ble = (ble) list.get(1);
        iek iek = (iek) list.get(2);
        if (booleanValue) {
            return iek.mo5932a(hmq.m11748a((Runnable) new dbx(ble)), bip.f2457a);
        }
        return ife.m12820a((Object) bip.f2457a);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final ieh mo3131a() {
        return ife.m12823a(this.f6269b.mo9044b(), this.f6270c.mo9044b(), this.f6271d.mo9044b());
    }
}
