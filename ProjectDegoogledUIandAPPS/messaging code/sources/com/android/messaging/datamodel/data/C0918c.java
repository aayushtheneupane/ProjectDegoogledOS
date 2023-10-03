package com.android.messaging.datamodel.data;

import android.database.Cursor;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.ContactUtil;
import com.android.p032ex.chips.C0699ra;

/* renamed from: com.android.messaging.datamodel.data.c */
public class C0918c {

    /* renamed from: Xz */
    private C0699ra f1219Xz;

    /* renamed from: Yz */
    private CharSequence f1220Yz;

    /* renamed from: Zz */
    private CharSequence f1221Zz;

    /* renamed from: _z */
    private String f1222_z;

    /* renamed from: aA */
    private boolean f1223aA;

    /* renamed from: bA */
    private boolean f1224bA;

    /* renamed from: Af */
    public String mo6405Af() {
        return this.f1222_z;
    }

    /* renamed from: Bf */
    public boolean mo6406Bf() {
        return this.f1219Xz.mo5667zd() || this.f1223aA;
    }

    /* renamed from: Cf */
    public boolean mo6407Cf() {
        if ((this.f1219Xz.getContactId() == -1000) || C1430e.m3624e(this.f1219Xz)) {
            return true;
        }
        return false;
    }

    /* renamed from: Df */
    public boolean mo6408Df() {
        return this.f1224bA;
    }

    /* renamed from: Ef */
    public C0699ra mo6409Ef() {
        return this.f1219Xz;
    }

    /* renamed from: a */
    public void mo6410a(Cursor cursor, String str) {
        boolean z;
        Cursor cursor2 = cursor;
        long j = cursor2.getLong(7);
        boolean z2 = false;
        long j2 = cursor2.getLong(0);
        String string = cursor2.getString(6);
        String string2 = cursor2.getString(1);
        String string3 = cursor2.getString(2);
        String string4 = cursor2.getString(3);
        int i = cursor2.getInt(4);
        String string5 = cursor2.getString(5);
        this.f1220Yz = null;
        this.f1221Zz = null;
        this.f1222_z = str;
        this.f1223aA = false;
        if (cursor.isFirst() || !cursor.moveToPrevious()) {
            z = true;
        } else {
            if (j2 != cursor2.getLong(0)) {
                z2 = true;
            }
            cursor.moveToNext();
            z = z2;
        }
        this.f1219Xz = ContactUtil.m3527a(string2, 40, string4, i, string5, j2, string, j, string3, z);
        this.f1224bA = ContactUtil.isEnterpriseContactId(j2);
    }

    public long getContactId() {
        return this.f1219Xz.getContactId();
    }

    public CharSequence getDestination() {
        CharSequence charSequence = this.f1221Zz;
        if (charSequence == null) {
            charSequence = C1430e.m3617c(this.f1219Xz);
        }
        return charSequence == null ? "" : charSequence;
    }

    public CharSequence getDisplayName() {
        CharSequence charSequence = this.f1220Yz;
        if (charSequence == null) {
            charSequence = C1430e.m3619d(this.f1219Xz);
        }
        return charSequence == null ? "" : charSequence;
    }

    /* renamed from: m */
    public String mo6415m() {
        return this.f1219Xz.mo5659m();
    }

    /* renamed from: td */
    public String mo6416td() {
        return this.f1219Xz.mo5660td();
    }

    /* renamed from: ud */
    public int mo6417ud() {
        return this.f1219Xz.mo5662ud();
    }

    /* renamed from: a */
    public void mo6411a(C0699ra raVar, CharSequence charSequence, CharSequence charSequence2, boolean z, boolean z2) {
        C1424b.m3592ia(raVar.isValid());
        this.f1219Xz = raVar;
        this.f1220Yz = charSequence;
        this.f1221Zz = charSequence2;
        this.f1222_z = null;
        this.f1223aA = z;
        this.f1224bA = z2;
    }
}
