package p000;

/* renamed from: dar */
/* compiled from: PG */
public final class dar implements hgi {
    /* renamed from: a */
    public final void mo4000a(gbr gbr) {
        hgh.m11443a((hgi) this, gbr);
    }

    /* renamed from: a */
    public final void mo4001a(hfz hfz) {
        hfz.mo7389a("ALTER TABLE mt ADD al INTEGER");
        hfz.mo7389a("ALTER TABLE mt ADD am INTEGER NOT NULL DEFAULT 0");
        hfz.mo7389a("ALTER TABLE mt ADD an INTEGER");
        hfz.mo7389a("CREATE INDEX idx_mt_am ON mt(am)");
    }
}
