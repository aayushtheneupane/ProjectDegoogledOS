package p000;

import android.database.Cursor;
import android.net.Uri;
import java.util.Date;
import p003j$.util.Optional;

/* renamed from: cxv */
/* compiled from: PG */
public final /* synthetic */ class cxv implements hpr {

    /* renamed from: a */
    private final Uri f5966a;

    /* renamed from: b */
    private final cxp f5967b;

    public cxv(Uri uri, cxp cxp) {
        this.f5966a = uri;
        this.f5967b = cxp;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        Uri uri = this.f5966a;
        cxp cxp = this.f5967b;
        Cursor cursor = (Cursor) obj;
        String[] strArr = cxy.f5974g;
        if (cursor != null && cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex("_data");
            int columnIndex2 = cursor.getColumnIndex("bucket_display_name");
            int columnIndex3 = cursor.getColumnIndex("_display_name");
            int columnIndex4 = cursor.getColumnIndex("datetaken");
            int columnIndex5 = cursor.getColumnIndex("width");
            int columnIndex6 = cursor.getColumnIndex("height");
            int columnIndex7 = cursor.getColumnIndex("orientation");
            int columnIndex8 = cursor.getColumnIndex("latitude");
            int columnIndex9 = cursor.getColumnIndex("longitude");
            int columnIndex10 = cursor.getColumnIndex("_size");
            int columnIndex11 = cursor.getColumnIndex("mime_type");
            if (!cursor.isNull(columnIndex)) {
                cxp.mo3948b(cursor.getString(columnIndex));
            }
            if (!cursor.isNull(columnIndex2)) {
                cxp.mo3946a(cursor.getString(columnIndex2));
            }
            if (!cursor.isNull(columnIndex3)) {
                cxp.f5946a = Optional.ofNullable(cursor.getString(columnIndex3));
            }
            if (!cursor.isNull(columnIndex4)) {
                cxp.f5947b = Optional.ofNullable(new Date(cursor.getLong(columnIndex4)));
            }
            if (!cursor.isNull(columnIndex5)) {
                cxp.f5949d = Optional.m16285of(Integer.valueOf(cursor.getInt(columnIndex5)));
            }
            if (!cursor.isNull(columnIndex6)) {
                cxp.f5948c = Optional.m16285of(Integer.valueOf(cursor.getInt(columnIndex6)));
            }
            if (!cursor.isNull(columnIndex7)) {
                cxp.f5950e = Optional.m16285of(Integer.valueOf(cursor.getInt(columnIndex7)));
            }
            if (!cursor.isNull(columnIndex8)) {
                cxp.mo3945a(Double.valueOf(cursor.getDouble(columnIndex8)));
            }
            if (!cursor.isNull(columnIndex9)) {
                cxp.mo3947b(Double.valueOf(cursor.getDouble(columnIndex9)));
            }
            if (!cursor.isNull(columnIndex10)) {
                cxp.mo3944a(cursor.getLong(columnIndex10));
            }
            if (cursor.isNull(columnIndex11)) {
                return null;
            }
            cxp.f5956k = Optional.ofNullable(cursor.getString(columnIndex11));
            return null;
        }
        cwn.m5510a("MediaMetadataDataService: MediaStore query for uri[%s] returned null cursor", uri);
        return null;
    }
}
