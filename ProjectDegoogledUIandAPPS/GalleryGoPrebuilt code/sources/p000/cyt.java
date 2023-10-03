package p000;

/* renamed from: cyt */
/* compiled from: PG */
public final class cyt {

    /* renamed from: a */
    public static final hgj f6047a;

    static {
        hgg a = hgj.m11445a();
        a.mo7400a(135);
        a.mo7402a("CREATE TABLE mt(a INTEGER PRIMARY KEY AUTOINCREMENT, b INTEGER NOT NULL, c INTEGER NOT NULL, d TEXT, e INTEGER, f INTEGER, g INTEGER NOT NULL, h INTEGER, i INTEGER NOT NULL, j INTEGER NOT NULL, k INTEGER NOT NULL, l TEXT, m TEXT, n TEXT, o BLOB, aa INTEGER NOT NULL DEFAULT 0, ab INTEGER NOT NULL DEFAULT 0, ac INTEGER NOT NULL DEFAULT 0, ae INTEGER NOT NULL DEFAULT 0, ad INTEGER NOT NULL DEFAULT 0, af INTEGER NOT NULL DEFAULT 0, v BLOB, w INTEGER NOT NULL DEFAULT 0, x INTEGER NOT NULL DEFAULT 0, y INTEGER NOT NULL DEFAULT 0, UNIQUE (b))");
        a.mo7402a("CREATE INDEX idx_mt_aa ON mt(aa)");
        a.mo7402a("CREATE INDEX idx_mt_ab ON mt(ab)");
        a.mo7402a("CREATE INDEX idx_mt_ac ON mt(ac)");
        a.mo7402a("CREATE INDEX idx_mt_ad ON mt(ad)");
        a.mo7402a("CREATE INDEX idx_mt_ae ON mt(ae)");
        a.mo7402a("CREATE INDEX idx_mt_af ON mt(af)");
        a.mo7402a("CREATE TABLE ft(a INTEGER PRIMARY KEY AUTOINCREMENT, b TEXT NOT NULL, c INTEGER NOT NULL, d INTEGER NOT NULL, e INTEGER NOT NULL, f BLOB NOT NULL, g BLOB, h TEXT, i REAL, j INTEGER NOT NULL DEFAULT 0, k INTEGER NOT NULL DEFAULT 0,  CONSTRAINT fk_c FOREIGN KEY(c) REFERENCES mt(a) ON DELETE CASCADE )");
        a.mo7402a("CREATE INDEX idx_ft_h ON ft(h)");
        a.mo7402a("CREATE INDEX idx_ft_c ON ft(c)");
        a.mo7402a("CREATE TABLE pt(a INTEGER PRIMARY KEY AUTOINCREMENT, b INTEGER NOT NULL, c REAL NOT NULL, d TEXT NOT NULL, e BLOB, f INTEGER NOT NULL DEFAULT 0)");
        a.mo7402a("CREATE INDEX idx_pt_d ON pt(d)");
        a.mo7401a((hgi) new das());
        a.mo7401a((hgi) new daj());
        a.mo7401a((hgi) new dal());
        a.mo7401a((hgi) new dai());
        a.mo7401a((hgi) new dan());
        a.mo7401a((hgi) new daf());
        a.mo7401a((hgi) new daq());
        a.mo7401a((hgi) new dae());
        a.mo7401a((hgi) new dar());
        a.mo7401a((hgi) new dah());
        a.mo7401a((hgi) new dag());
        a.mo7401a((hgi) new dao());
        a.mo7401a((hgi) new dak());
        a.mo7401a((hgi) new dap());
        a.mo7401a(cys.f6046a);
        a.mo7401a((hgi) new dam());
        f6047a = a.mo7399a();
    }

    /* renamed from: a */
    public static bpt m5751a(hge hge, iel iel) {
        return new bpt(hge, iel);
    }
}
