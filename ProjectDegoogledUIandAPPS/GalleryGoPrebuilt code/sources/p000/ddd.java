package p000;

import android.database.Cursor;
import java.io.File;
import java.util.ArrayList;

/* renamed from: ddd */
/* compiled from: PG */
final /* synthetic */ class ddd implements hpr {

    /* renamed from: a */
    public static final hpr f6325a = new ddd();

    private ddd() {
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        Cursor cursor = (Cursor) obj;
        ArrayList f = ife.m12904f();
        if (cursor.moveToFirst()) {
            do {
                f.add(new File(fxk.m9835c(cursor, "_data")));
            } while (cursor.moveToNext());
        }
        return f;
    }
}
