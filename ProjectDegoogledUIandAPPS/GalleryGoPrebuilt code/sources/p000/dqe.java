package p000;

import android.database.Cursor;
import p003j$.util.Optional;

/* renamed from: dqe */
/* compiled from: PG */
final /* synthetic */ class dqe implements hpr {

    /* renamed from: a */
    public static final hpr f7102a = new dqe();

    private dqe() {
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        Cursor cursor = (Cursor) obj;
        if (cursor == null) {
            return Optional.empty();
        }
        try {
            int columnIndex = cursor.getColumnIndex("bucket_id");
            if (columnIndex >= 0 && cursor.moveToFirst()) {
                if (cursor.getString(columnIndex) != null) {
                    Optional ofNullable = Optional.ofNullable(cursor.getString(columnIndex));
                    cursor.close();
                    return ofNullable;
                }
            }
            cursor.close();
            return Optional.empty();
        } catch (Throwable th) {
            cursor.close();
            throw th;
        }
    }
}
