package p000;

import android.database.Cursor;
import java.io.File;
import java.util.ArrayList;

/* renamed from: czi */
/* compiled from: PG */
final /* synthetic */ class czi implements hpr {

    /* renamed from: a */
    public static final hpr f6102a = new czi();

    private czi() {
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        Cursor cursor = (Cursor) obj;
        ArrayList arrayList = new ArrayList();
        if (cursor.moveToFirst()) {
            do {
                arrayList.add(new File(fxk.m9835c(cursor, "_data")));
            } while (cursor.moveToNext());
        }
        return arrayList;
    }
}
