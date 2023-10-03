package p000;

/* renamed from: daf */
/* compiled from: PG */
public final class daf implements hgi {
    /* renamed from: a */
    public final void mo4000a(gbr gbr) {
        hgh.m11443a((hgi) this, gbr);
    }

    /* renamed from: a */
    public final void mo4001a(hfz hfz) {
        hfz.mo7389a("ALTER TABLE mt ADD ai TEXT");
        hfz.mo7389a("ALTER TABLE mt ADD aj INTEGER NOT NULL DEFAULT -1");
        hfz.mo7389a("CREATE INDEX idx_mt_ai ON mt(ai) WHERE ai IS NOT NULL");
        hfz.mo7389a("CREATE UNIQUE INDEX idx_mt_aj ON mt(ai) WHERE aj > 0");
    }
}
