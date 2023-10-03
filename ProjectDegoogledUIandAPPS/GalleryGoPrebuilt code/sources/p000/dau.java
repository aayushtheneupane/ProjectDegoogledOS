package p000;

import android.database.Cursor;
import p003j$.util.Optional;

/* renamed from: dau */
/* compiled from: PG */
final /* synthetic */ class dau implements hpr {

    /* renamed from: a */
    public static final hpr f6147a = new dau();

    private dau() {
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        String str;
        Cursor cursor = (Cursor) obj;
        int i = dav.f6148b;
        if (cursor.moveToFirst()) {
            str = cursor.getString(cursor.getColumnIndexOrThrow("_data"));
        } else {
            str = null;
        }
        return Optional.ofNullable(str);
    }
}
