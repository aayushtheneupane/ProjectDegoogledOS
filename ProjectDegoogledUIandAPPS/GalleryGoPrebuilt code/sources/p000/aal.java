package p000;

/* renamed from: aal */
/* compiled from: PG */
final class aal implements aan {
    /* renamed from: a */
    public final float mo22a(aam aam) {
        return ((aao) ((aaj) aam).f20a).f30b;
    }

    /* renamed from: b */
    public final float mo23b(aam aam) {
        return ((aao) ((aaj) aam).f20a).f29a;
    }

    /* renamed from: c */
    public final void mo24c(aam aam) {
        if (!aam.mo17a()) {
            aam.mo16a(0, 0, 0, 0);
            return;
        }
        float a = mo22a(aam);
        float b = mo23b(aam);
        int ceil = (int) Math.ceil((double) aap.m30b(a, b, aam.mo18b()));
        int ceil2 = (int) Math.ceil((double) aap.m29a(a, b, aam.mo18b()));
        aam.mo16a(ceil, ceil2, ceil, ceil2);
    }
}
