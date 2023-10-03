package p000;

import android.database.Cursor;

/* renamed from: dje */
/* compiled from: PG */
final /* synthetic */ class dje implements hpr {

    /* renamed from: a */
    public static final hpr f6661a = new dje();

    private dje() {
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        Cursor cursor = (Cursor) obj;
        hsj j = hso.m12048j();
        if (cursor.moveToFirst()) {
            int columnIndexOrThrow = cursor.getColumnIndexOrThrow("ak");
            do {
                String string = cursor.getString(columnIndexOrThrow);
                if (string != null && !"_".equals(string)) {
                    j.mo7908c(string);
                }
            } while (cursor.moveToNext());
        }
        return j.mo7905a();
    }
}
