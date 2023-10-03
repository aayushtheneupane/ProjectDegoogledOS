package p000;

import java.util.ArrayList;
import java.util.List;
import p003j$.util.Optional;

/* renamed from: bro */
/* compiled from: PG */
final class bro implements gvc {

    /* renamed from: a */
    private Optional f3438a = Optional.empty();

    /* renamed from: b */
    private Optional f3439b = Optional.empty();

    /* renamed from: c */
    private final /* synthetic */ brp f3440c;

    public bro(brp brp) {
        this.f3440c = brp;
    }

    /* renamed from: a */
    public final void mo2679a() {
    }

    /* renamed from: a */
    public final void mo2681a(Throwable th) {
        cwn.m5511a(th, "DeviceFoldersFragment: Failed to fetch device folder data.", new Object[0]);
        m3490b();
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2680a(Object obj) {
        btb btb = (btb) obj;
        if (btb.mo2725a().isPresent()) {
            this.f3438a = btb.mo2725a();
        }
        if (btb.mo2726b().isPresent()) {
            this.f3439b = btb.mo2726b();
        }
        ArrayList arrayList = new ArrayList();
        if (this.f3440c.f3442b.f3436f) {
            arrayList.add(new brg(""));
        }
        if (this.f3438a.isPresent()) {
            for (btl btl : (List) this.f3438a.get()) {
                if (btl != null) {
                    arrayList.add(new brf(btl));
                } else {
                    throw null;
                }
            }
            if (this.f3439b.isPresent() && ((Optional) this.f3439b.get()).isPresent() && !arrayList.isEmpty()) {
                btn btn = (btn) ((Optional) this.f3439b.get()).get();
                if (btn != null) {
                    arrayList.add(new brh(btn));
                } else {
                    throw null;
                }
            }
            bru bru = this.f3440c.f3448h;
            bru.f3462g = arrayList;
            bru.f3458c.mo7129a((List) arrayList);
            bru.f3456a.mo3752a();
            m3490b();
        }
    }

    /* renamed from: b */
    private final void m3490b() {
        ((bkz) this.f3440c.f3444d.mo9034a()).mo2540a(this.f3440c.f3443c.mo5653m());
        this.f3440c.f3445e.mo2551b();
    }
}
