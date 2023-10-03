package p000;

import android.database.Cursor;
import java.util.concurrent.Executor;

/* renamed from: cjh */
/* compiled from: PG */
public final class cjh {

    /* renamed from: a */
    public final bpt f4490a;

    public cjh(bpt bpt) {
        this.f4490a = bpt;
    }

    /* renamed from: c */
    public static hgn m4390c() {
        hgn hgn = new hgn();
        hgn.mo7409a("SELECT a, d, b, f, g FROM pt");
        return hgn;
    }

    /* renamed from: a */
    public static hgm m4386a(hso hso, boolean z) {
        hgn hgn = new hgn();
        hgn.mo7409a("UPDATE pt SET g");
        if (z) {
            hgn.mo7409a(" = 1 WHERE a IN (");
        } else {
            hgn.mo7409a(" = 0 WHERE a NOT IN (");
        }
        if (!hso.isEmpty()) {
            for (int i = 0; i < hso.size() - 1; i++) {
                hgn.mo7409a("?,");
                hgn.mo7408a((Long) hso.get(i));
            }
            hgn.mo7409a("?");
            hgn.mo7408a((Long) ife.m12907g((Iterable) hso));
        }
        hgn.mo7409a(")");
        return hgn.mo7407a();
    }

    /* renamed from: b */
    public final ieh mo3171b() {
        return this.f4490a.mo2656a(ciy.f4478a);
    }

    /* renamed from: a */
    public static void m4388a(hfz hfz) {
        hgl a = hgl.m11446a("pt");
        a.mo7404b("1 = 1");
        hfz.mo7387a(a.mo7403a());
    }

    /* renamed from: a */
    public final ieh mo3169a(int i) {
        bpt bpt = this.f4490a;
        hgn c = m4390c();
        c.mo7409a(" WHERE f = 0");
        c.mo7409a(" ORDER BY a DESC ");
        c.mo7409a(" LIMIT ? ");
        c.mo7411b(String.valueOf(i));
        return bpt.mo2655a(c.mo7407a(), cjd.f4486a).mo6899b();
    }

    /* renamed from: a */
    public static hso m4387a(Cursor cursor) {
        hsj j = hso.m12048j();
        if (!cursor.moveToFirst()) {
            return j.mo7905a();
        }
        do {
            j.mo7908c(cia.m4342a(cursor));
        } while (cursor.moveToNext());
        return j.mo7905a();
    }

    /* renamed from: a */
    public final ieh mo3168a() {
        return gte.m10778b(this.f4490a.mo2656a(civ.f4475a), this.f4490a.mo2656a(ciw.f4476a)).mo7613a(cix.f4477a, (Executor) idh.f13918a);
    }

    /* renamed from: a */
    public final ieh mo3170a(hso hso) {
        return this.f4490a.mo2656a(new cjf(hso));
    }

    /* renamed from: a */
    public static void m4389a(hfz hfz, hso hso) {
        hvs i = hso.listIterator();
        while (i.hasNext()) {
            cia cia = (cia) i.next();
            hfz.mo7386a("pt", cia.mo3153i(), "a = ?", String.valueOf(cia.mo3107a().get()));
        }
    }
}
