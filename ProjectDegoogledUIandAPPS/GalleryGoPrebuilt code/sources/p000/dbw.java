package p000;

import java.util.concurrent.Executor;
import p003j$.util.Optional;

/* renamed from: dbw */
/* compiled from: PG */
final /* synthetic */ class dbw implements icf {

    /* renamed from: a */
    private final long f6220a;

    /* renamed from: b */
    private final cip f6221b;

    /* renamed from: c */
    private final iek f6222c;

    public dbw(long j, cip cip, iek iek) {
        this.f6220a = j;
        this.f6221b = cip;
        this.f6222c = iek;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        long j = this.f6220a;
        cip cip = this.f6221b;
        iek iek = this.f6222c;
        hsj j2 = hso.m12048j();
        hvs i = ((hso) obj).listIterator();
        while (i.hasNext()) {
            cff cff = (cff) i.next();
            if (cff.mo3099h().isPresent()) {
                cfe m = cff.mo3105m();
                Optional empty = Optional.empty();
                if (empty != null) {
                    m.f4245a = empty;
                    m.mo3120a(j);
                    m.mo3127c(true);
                    j2.mo7908c(m.mo3117a());
                } else {
                    throw new NullPointerException("Null id");
                }
            }
        }
        hso a = j2.mo7905a();
        if (!a.isEmpty()) {
            return gte.m10770a(cip.f4469a.mo2656a(new cih(a)), dbz.f6225a, (Executor) iek);
        }
        return ife.m12820a((Object) false);
    }
}
