package p000;

import android.database.Cursor;
import java.util.Iterator;
import p003j$.util.Iterator$$Dispatch;

/* renamed from: cip */
/* compiled from: PG */
public final class cip {

    /* renamed from: a */
    public final bpt f4469a;

    public cip(bpt bpt) {
        this.f4469a = bpt;
    }

    /* renamed from: a */
    public static hgn m4364a() {
        hgn hgn = new hgn();
        hgn.mo7409a("SELECT a, b, c, d, e, f, g, h, i, j, k, l FROM ft");
        return hgn;
    }

    /* renamed from: a */
    public static hso m4365a(Cursor cursor) {
        hsj j = hso.m12048j();
        if (!cursor.moveToFirst()) {
            return j.mo7905a();
        }
        do {
            j.mo7908c(cff.m4255a(cursor));
        } while (cursor.moveToNext());
        return j.mo7905a();
    }

    /* renamed from: a */
    public final ieh mo3159a(Iterable iterable) {
        Iterator it = iterable.iterator();
        if (!it.hasNext()) {
            return ife.m12820a((Object) hvb.f13454a);
        }
        hgn hgn = new hgn();
        hgn.mo7409a("SELECT a, b, c, d, e, f, g, h, i, j, k, l,  MAX( i )  FROM ft");
        hgn.mo7409a(" WHERE h IN(? ");
        hgn.mo7411b((String) it.next());
        Iterator$$Dispatch.forEachRemaining(it, new cin(hgn));
        hgn.mo7409a(" ) ");
        hgn.mo7409a("GROUP BY h");
        return this.f4469a.mo2655a(hgn.mo7407a(), cio.f4468a).mo6899b();
    }

    /* renamed from: b */
    public final ieh mo3160b(int i) {
        bpt bpt = this.f4469a;
        hgn a = m4364a();
        a.mo7409a(" WHERE l = 0 AND k = 0 AND g IS NOT NULL");
        a.mo7409a(" ORDER BY a DESC ");
        a.mo7409a(" LIMIT ? ");
        a.mo7411b(String.valueOf(i));
        return bpt.mo2655a(a.mo7407a(), cil.f4465a).mo6899b();
    }

    /* renamed from: a */
    public final ieh mo3158a(int i) {
        bpt bpt = this.f4469a;
        hgn a = m4364a();
        a.mo7409a(" WHERE l = 0 AND j = 0");
        a.mo7409a(" ORDER BY a DESC ");
        a.mo7409a(" LIMIT ? ");
        a.mo7411b(String.valueOf(i));
        return bpt.mo2655a(a.mo7407a(), cik.f4464a).mo6899b();
    }

    /* renamed from: a */
    public static void m4366a(hfz hfz, hso hso) {
        hvs i = hso.listIterator();
        while (i.hasNext()) {
            hfz.mo7390a("ft", ((cff) i.next()).mo3129p());
        }
    }

    /* renamed from: b */
    public static void m4367b(hfz hfz, hso hso) {
        hvs i = hso.listIterator();
        while (i.hasNext()) {
            cff cff = (cff) i.next();
            hfz.mo7386a("ft", cff.mo3129p(), "a = ?", String.valueOf(cff.mo3091a().get()));
        }
    }
}
