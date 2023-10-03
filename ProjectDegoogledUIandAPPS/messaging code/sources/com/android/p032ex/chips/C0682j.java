package com.android.p032ex.chips;

import android.database.Cursor;

/* renamed from: com.android.ex.chips.j */
public class C0682j {

    /* renamed from: Gj */
    public final String f797Gj;

    /* renamed from: Xu */
    public final Long f798Xu;

    /* renamed from: _u */
    public final int f799_u;

    /* renamed from: av */
    public final String f800av;

    /* renamed from: bv */
    public final long f801bv;
    public final long contactId;

    /* renamed from: cv */
    public final String f802cv;
    public final String displayName;

    /* renamed from: dv */
    public final int f803dv;
    public final String lookupKey;

    public C0682j(Cursor cursor, Long l) {
        this.displayName = cursor.getString(0);
        this.f797Gj = cursor.getString(1);
        this.f799_u = cursor.getInt(2);
        this.f800av = cursor.getString(3);
        this.contactId = cursor.getLong(4);
        this.f798Xu = l;
        this.f801bv = cursor.getLong(5);
        this.f802cv = cursor.getString(6);
        this.f803dv = cursor.getInt(7);
        this.lookupKey = cursor.getString(8);
    }
}
