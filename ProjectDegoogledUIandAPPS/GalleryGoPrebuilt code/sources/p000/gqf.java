package p000;

/* renamed from: gqf */
/* compiled from: PG */
final /* synthetic */ class gqf implements gpe {

    /* renamed from: a */
    private final hpr f11833a;

    /* renamed from: b */
    private final eym f11834b;

    public gqf(hpr hpr, eym eym) {
        this.f11833a = hpr;
        this.f11834b = eym;
    }

    /* renamed from: a */
    public final gpc mo2654a(Object obj) {
        hpr hpr = this.f11833a;
        Void voidR = (Void) obj;
        eyn eyn = (eyn) hpr.mo1484a(this.f11834b);
        if (eyn != null) {
            return gqr.m10642a(eyn);
        }
        String valueOf = String.valueOf(hpr);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 20);
        sb.append("Returned null from: ");
        sb.append(valueOf);
        throw new NullPointerException(sb.toString());
    }
}
