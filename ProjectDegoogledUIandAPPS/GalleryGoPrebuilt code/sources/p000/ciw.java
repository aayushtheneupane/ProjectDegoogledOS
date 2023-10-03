package p000;

import android.content.ContentValues;

/* renamed from: ciw */
/* compiled from: PG */
final /* synthetic */ class ciw implements hga {

    /* renamed from: a */
    public static final hga f4476a = new ciw();

    private ciw() {
    }

    /* renamed from: a */
    public final void mo2584a(hfz hfz) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("f", 0);
        hfz.mo7386a("pt", contentValues, "b NOT IN (SELECT a FROM ft)", new String[0]);
    }
}
