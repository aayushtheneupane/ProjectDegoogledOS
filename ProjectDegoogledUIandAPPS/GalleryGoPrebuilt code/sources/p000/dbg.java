package p000;

import android.net.Uri;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import p003j$.util.Optional;

/* renamed from: dbg */
/* compiled from: PG */
public final class dbg extends iox {

    /* renamed from: b */
    private final ioq f6180b;

    /* renamed from: c */
    private final ioq f6181c;

    /* renamed from: d */
    private final ioq f6182d;

    /* renamed from: e */
    private final ioq f6183e;

    /* renamed from: f */
    private final ioq f6184f;

    public dbg(iqk iqk, iqk iqk2, ioq ioq, ioq ioq2, ioq ioq3, ioq ioq4, ioq ioq5) {
        super(iqk2, iph.m14288a(dbg.class), iqk);
        this.f6180b = ipd.m14274a(ioq);
        this.f6181c = ipd.m14274a(ioq2);
        this.f6182d = ipd.m14274a(ioq3);
        this.f6183e = ipd.m14274a(ioq4);
        this.f6184f = ipd.m14274a(ioq5);
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ ieh mo3132b(Object obj) {
        List list = (List) obj;
        Optional optional = (Optional) list.get(0);
        ioq ioq = this.f6181c;
        ebi ebi = (ebi) list.get(1);
        Optional optional2 = (Optional) list.get(2);
        iek iek = (iek) list.get(3);
        if (!optional2.isPresent()) {
            return ioq.mo9044b();
        }
        Uri uri = (Uri) optional2.get();
        return ife.m12820a((Object) dbf.m5840a(gpc.m10580a((Callable) new dax(ebi, uri), (Executor) iek), uri));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final ieh mo3131a() {
        return ife.m12823a(this.f6180b.mo9044b(), this.f6182d.mo9044b(), this.f6183e.mo9044b(), this.f6184f.mo9044b());
    }
}
