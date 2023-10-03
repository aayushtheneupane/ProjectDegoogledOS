package com.android.messaging.datamodel.data;

import android.database.Cursor;
import android.graphics.Rect;
import android.net.Uri;
import android.text.TextUtils;
import com.android.messaging.datamodel.p038b.C0849L;
import com.android.messaging.datamodel.p038b.C0860X;
import com.android.messaging.datamodel.p038b.C0877q;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1481w;
import com.android.messaging.util.C1488za;

/* renamed from: com.android.messaging.datamodel.data.B */
public class C0890B {

    /* renamed from: LB */
    public static final String[] f1149LB = {"_id", "_data", "width", "height", "mime_type", "date_modified", "_display_name"};

    /* renamed from: MB */
    public static final String[] f1150MB = {"_id"};

    /* renamed from: HB */
    private C0849L f1151HB;

    /* renamed from: IB */
    private boolean f1152IB;

    /* renamed from: JB */
    private long f1153JB;

    /* renamed from: KB */
    private Uri f1154KB;

    /* renamed from: Ng */
    private String f1155Ng;
    private String mContentType;

    /* renamed from: Kg */
    public long mo6205Kg() {
        return this.f1153JB;
    }

    /* renamed from: Lg */
    public C0849L mo6206Lg() {
        return this.f1151HB;
    }

    /* renamed from: Mg */
    public Uri mo6207Mg() {
        return C1481w.m3831za(this.mContentType) ? this.f1154KB : this.f1151HB.uri;
    }

    /* renamed from: Ng */
    public boolean mo6208Ng() {
        return this.f1152IB;
    }

    /* renamed from: a */
    public void mo6209a(Cursor cursor, int i, int i2) {
        this.f1152IB = TextUtils.equals(cursor.getString(0), "-1");
        if (this.f1152IB) {
            this.f1151HB = null;
            this.mContentType = null;
            return;
        }
        this.mContentType = cursor.getString(4);
        String string = cursor.getString(1);
        String string2 = cursor.getString(5);
        this.f1153JB = !TextUtils.isEmpty(string2) ? Long.parseLong(string2) : -1;
        if (C1481w.m3831za(this.mContentType)) {
            this.f1151HB = null;
            this.f1154KB = C1488za.m3867Na(string);
            this.f1155Ng = cursor.getString(6);
            return;
        }
        int i3 = cursor.getInt(2);
        int i4 = cursor.getInt(3);
        int i5 = i3 <= 0 ? -1 : i3;
        int i6 = i4 <= 0 ? -1 : i4;
        if (C1481w.m3830Ea(this.mContentType)) {
            this.f1151HB = new C0860X(cursor.getLong(0), i, i2, i5, i6);
        } else {
            this.f1151HB = new C0877q(string, i, i2, i5, i6, true, true, true);
        }
    }

    /* renamed from: b */
    public MessagePartData mo6210b(Rect rect) {
        C1424b.m3592ia(!this.f1152IB);
        if (C1481w.m3831za(this.mContentType)) {
            return new MediaPickerMessagePartData(rect, this.mContentType, this.f1154KB, 0, 0);
        }
        String str = this.mContentType;
        C0849L l = this.f1151HB;
        return new MediaPickerMessagePartData(rect, str, l.uri, l.f1129zC, l.f1124AC);
    }

    public String getContentType() {
        return this.mContentType;
    }

    public String getFileName() {
        return this.f1155Ng;
    }
}
