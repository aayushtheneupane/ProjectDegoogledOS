package p000;

import android.database.Cursor;

/* renamed from: cio */
/* compiled from: PG */
final /* synthetic */ class cio implements hpr {

    /* renamed from: a */
    public static final hpr f4468a = new cio();

    private cio() {
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        Cursor cursor = (Cursor) obj;
        hsq g = hsu.m12070g();
        if (!cursor.moveToFirst()) {
            return g.mo7930a();
        }
        do {
            cff a = cff.m4255a(cursor);
            g.mo7932a((String) a.mo3099h().get(), a);
        } while (cursor.moveToNext());
        return g.mo7930a();
    }
}
