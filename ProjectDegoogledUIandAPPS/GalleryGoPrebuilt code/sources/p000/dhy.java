package p000;

import android.database.Cursor;
import android.text.TextUtils;

/* renamed from: dhy */
/* compiled from: PG */
final /* synthetic */ class dhy implements hpr {

    /* renamed from: a */
    private final String f6575a;

    public dhy(String str) {
        this.f6575a = str;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        String str = this.f6575a;
        Cursor cursor = (Cursor) obj;
        String[] strArr = dib.f6588a;
        if (cursor == null) {
            return hso.m12047f();
        }
        hsj j = hso.m12048j();
        int columnIndexOrThrow = cursor.getColumnIndexOrThrow("media_store_id");
        int columnIndexOrThrow2 = cursor.getColumnIndexOrThrow("progress_status");
        int columnIndexOrThrow3 = cursor.getColumnIndexOrThrow("progress_percentage");
        int columnIndex = cursor.getColumnIndex("special_type_id");
        while (cursor.moveToNext()) {
            int i = cursor.getInt(columnIndexOrThrow2) == 2 ? 2 : 1;
            iir g = dhu.f6565g.mo8793g();
            long j2 = cursor.getLong(columnIndexOrThrow);
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            dhu dhu = (dhu) g.f14318b;
            int i2 = 1 | dhu.f6567a;
            dhu.f6567a = i2;
            dhu.f6568b = j2;
            int i3 = i2 | 2;
            dhu.f6567a = i3;
            dhu.f6569c = i;
            str.getClass();
            dhu.f6567a = i3 | 16;
            dhu.f6572f = str;
            if (i == 2) {
                int i4 = cursor.getInt(columnIndexOrThrow3);
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                dhu dhu2 = (dhu) g.f14318b;
                dhu2.f6567a |= 4;
                dhu2.f6570d = i4;
            }
            if (columnIndex >= 0) {
                String string = cursor.getString(columnIndex);
                if (!TextUtils.isEmpty(string)) {
                    if (g.f14319c) {
                        g.mo8751b();
                        g.f14319c = false;
                    }
                    dhu dhu3 = (dhu) g.f14318b;
                    string.getClass();
                    dhu3.f6567a |= 8;
                    dhu3.f6571e = string;
                }
            }
            j.mo7908c((dhu) g.mo8770g());
        }
        return j.mo7905a();
    }
}
