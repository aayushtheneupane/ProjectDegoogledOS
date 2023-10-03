package p000;

import android.database.Cursor;

/* renamed from: cjb */
/* compiled from: PG */
public final /* synthetic */ class cjb implements hpr {

    /* renamed from: a */
    public static final hpr f4484a = new cjb();

    private cjb() {
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        Cursor cursor = (Cursor) obj;
        hsq g = hsu.m12070g();
        if (!cursor.moveToFirst()) {
            return g.mo7930a();
        }
        do {
            cia a = cia.m4342a(cursor);
            g.mo7932a(a.mo3109c(), a);
        } while (cursor.moveToNext());
        return g.mo7930a();
    }
}
