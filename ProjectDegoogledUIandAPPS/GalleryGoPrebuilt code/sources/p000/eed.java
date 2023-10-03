package p000;

import android.database.Cursor;

/* renamed from: eed */
/* compiled from: PG */
final /* synthetic */ class eed implements hpr {

    /* renamed from: a */
    public static final hpr f8094a = new eed();

    private eed() {
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        Cursor cursor = (Cursor) obj;
        cursor.moveToFirst();
        return Integer.valueOf(cursor.getInt(0));
    }
}
