package p000;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: ido */
/* compiled from: PG */
public final class ido {

    /* renamed from: a */
    private final AtomicReference f13939a = new AtomicReference(ife.m12820a((Object) null));

    private ido() {
    }

    /* renamed from: a */
    public static ido m12729a() {
        return new ido();
    }

    /* renamed from: a */
    public final ieh mo8417a(ice ice, Executor executor) {
        ife.m12898e((Object) ice);
        AtomicReference atomicReference = new AtomicReference(idn.NOT_RUN);
        idk idk = new idk(atomicReference, ice);
        iev f = iev.m12774f();
        ieh ieh = (ieh) this.f13939a.getAndSet(f);
        ieh a = ife.m12816a((ice) idk, (Executor) new idl(ieh, executor));
        ieh a2 = ife.m12817a(a);
        idm idm = new idm(a, a2, atomicReference, f, ieh);
        a2.mo53a(idm, idh.f13918a);
        a.mo53a(idm, idh.f13918a);
        return a2;
    }
}
