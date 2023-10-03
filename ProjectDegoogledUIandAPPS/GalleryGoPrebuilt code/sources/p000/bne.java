package p000;

import android.database.Cursor;
import p003j$.util.Optional;

/* renamed from: bne */
/* compiled from: PG */
final /* synthetic */ class bne implements hpr {

    /* renamed from: a */
    public static final hpr f3188a = new bne();

    private bne() {
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        Cursor cursor = (Cursor) obj;
        if (!cursor.moveToFirst()) {
            return Optional.empty();
        }
        return Optional.m16285of(cyc.m5644a(cyc.m5643a(cursor.getInt(cursor.getColumnIndexOrThrow("c"))), cursor.getLong(cursor.getColumnIndexOrThrow("b"))));
    }
}
