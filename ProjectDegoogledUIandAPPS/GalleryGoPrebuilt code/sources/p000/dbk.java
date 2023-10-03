package p000;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import p003j$.util.Optional;

/* renamed from: dbk */
/* compiled from: PG */
public final class dbk extends iox {

    /* renamed from: b */
    private final ioq f6188b;

    /* renamed from: c */
    private final ioq f6189c;

    /* renamed from: d */
    private final ioq f6190d;

    /* renamed from: e */
    private final ioq f6191e;

    public dbk(iqk iqk, iqk iqk2, ioq ioq, ioq ioq2, ioq ioq3, ioq ioq4) {
        super(iqk2, iph.m14288a(dbk.class), iqk);
        this.f6188b = ipd.m14274a(ioq);
        this.f6189c = ipd.m14274a(ioq2);
        this.f6190d = ipd.m14274a(ioq3);
        this.f6191e = ipd.m14274a(ioq4);
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ ieh mo3132b(Object obj) {
        List list = (List) obj;
        efr efr = (efr) list.get(0);
        Optional optional = (Optional) list.get(1);
        ebi ebi = (ebi) list.get(2);
        iek iek = (iek) list.get(3);
        if (!optional.isPresent()) {
            return ife.m12822a((Throwable) new IOException("Cannot write to document file unless destination path is defined."));
        }
        return gte.m10770a(efr.mo4787a(new File((String) optional.get()), true), (hpr) new dba(ebi, iek), (Executor) iek);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final ieh mo3131a() {
        return ife.m12823a(this.f6188b.mo9044b(), this.f6189c.mo9044b(), this.f6190d.mo9044b(), this.f6191e.mo9044b());
    }
}
