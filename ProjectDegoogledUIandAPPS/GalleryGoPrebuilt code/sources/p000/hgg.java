package p000;

/* renamed from: hgg */
/* compiled from: PG */
public final class hgg {

    /* renamed from: a */
    private final gbc f12695a;

    public hgg(gbc gbc) {
        this.f12695a = gbc;
    }

    /* renamed from: a */
    public final void mo7401a(hgi hgi) {
        this.f12695a.f10823a.mo7908c(hgi);
    }

    /* renamed from: a */
    public final void mo7402a(String str) {
        this.f12695a.f10823a.mo7908c(new gbg(str));
    }

    /* renamed from: a */
    public final hgj mo7399a() {
        gbc gbc = this.f12695a;
        if (gbc.f10825c == null) {
            gbc.f10825c = new gbd().f10827a;
        }
        return new hgj(new gbi(gbc.f10826d, gbc.f10823a.mo7905a(), gbc.f10824b.mo7905a(), gbc.f10825c));
    }

    /* renamed from: a */
    public final void mo7400a(int i) {
        gbc gbc = this.f12695a;
        ife.m12876b(gbc.f10823a.mo7905a().isEmpty(), (Object) "dropAllVersionsBefore() must be the first UpgradeStep. The newVersionNumber parameter must be the number of removed addSchemaVersion() steps plus one.");
        ife.m12876b(!gbc.f10826d.mo7646a(), (Object) "Only one call to dropAllVersionsBefore() may be made. It must be the first call to a SQLSchema.Builder.");
        ife.m12876b(true, (Object) "newVersionNumber must be greater than zero in order for it to drop any previous database version. The newVersionNumber parameter must be the number of removed addSchemaVersion() steps plus one.");
        gbc.f10826d = hpy.m11893b(new gbf(i));
    }
}
