package p000;

import android.net.Uri;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import p003j$.util.Optional;

/* renamed from: dbl */
/* compiled from: PG */
public final class dbl extends iox {

    /* renamed from: b */
    private final ioq f6192b;

    /* renamed from: c */
    private final ioq f6193c;

    /* renamed from: d */
    private final ioq f6194d;

    /* renamed from: e */
    private final ioq f6195e;

    /* renamed from: f */
    private final ioq f6196f;

    public dbl(iqk iqk, iqk iqk2, ioq ioq, ioq ioq2, ioq ioq3, ioq ioq4, ioq ioq5) {
        super(iqk2, iph.m14288a(dbl.class), iqk);
        this.f6192b = ipd.m14274a(ioq);
        this.f6193c = ipd.m14274a(ioq2);
        this.f6194d = ipd.m14274a(ioq3);
        this.f6195e = ipd.m14274a(ioq4);
        this.f6196f = ipd.m14274a(ioq5);
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ ieh mo3132b(Object obj) {
        List list = (List) obj;
        Optional optional = (Optional) list.get(1);
        ioq ioq = this.f6195e;
        iek iek = (iek) list.get(3);
        return gte.m10773a(iek.mo5933a(hmq.m11749a((Callable) new day(optional, iek, (Uri) list.get(0), (ebi) list.get(2)))), IOException.class, (icf) new daz(ioq), (Executor) iek);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final ieh mo3131a() {
        return ife.m12823a(this.f6192b.mo9044b(), this.f6193c.mo9044b(), this.f6194d.mo9044b(), this.f6196f.mo9044b());
    }
}
