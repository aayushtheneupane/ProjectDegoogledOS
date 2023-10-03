package p000;

import android.content.ContentValues;
import android.database.Cursor;
import p003j$.util.Optional;

/* renamed from: cia */
/* compiled from: PG */
public abstract class cia {
    /* renamed from: a */
    public abstract Optional mo3107a();

    /* renamed from: b */
    public abstract long mo3108b();

    /* renamed from: c */
    public abstract String mo3109c();

    /* renamed from: d */
    public abstract byte[] mo3110d();

    /* renamed from: e */
    public abstract boolean mo3111e();

    /* renamed from: f */
    public abstract boolean mo3113f();

    /* renamed from: g */
    public abstract chz mo3114g();

    /* renamed from: i */
    public final ContentValues mo3153i() {
        ContentValues contentValues = new ContentValues(5);
        fxk.m9823a(contentValues, "a", mo3107a());
        contentValues.put("b", Long.valueOf(mo3108b()));
        contentValues.put("c", 0);
        contentValues.put("d", mo3109c());
        contentValues.put("f", Integer.valueOf(mo3111e() ? 1 : 0));
        contentValues.put("g", Boolean.valueOf(mo3113f()));
        if (mo3110d().length > 0) {
            contentValues.put("e", mo3110d());
        }
        return contentValues;
    }

    /* renamed from: a */
    public static cia m4342a(Cursor cursor) {
        chz h = m4343h();
        h.f4440a = Optional.m16285of(Long.valueOf(fxk.m9829b(cursor, "a")));
        h.mo3147a(fxk.m9829b(cursor, "b"));
        h.mo3148a(fxk.m9835c(cursor, "d"));
        h.mo3151b(fxk.m9840e(cursor, "f"));
        h.mo3149a(fxk.m9840e(cursor, "g"));
        int columnIndex = cursor.getColumnIndex("e");
        if (columnIndex >= 0 && (!cursor.isNull(columnIndex))) {
            h.mo3150a(fxk.m9839d(cursor, "e"));
        }
        return h.mo3146a();
    }

    /* renamed from: h */
    public static chz m4343h() {
        chz chz = new chz((byte[]) null);
        chz.mo3151b(false);
        chz.mo3150a(new byte[0]);
        chz.mo3147a(-1);
        chz.mo3149a(false);
        return chz;
    }
}
