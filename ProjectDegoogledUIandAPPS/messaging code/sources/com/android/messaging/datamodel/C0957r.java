package com.android.messaging.datamodel;

import android.database.Cursor;
import android.database.MatrixCursor;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1475t;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import p000a.p005b.C0027n;

/* renamed from: com.android.messaging.datamodel.r */
public class C0957r {

    /* renamed from: Qx */
    private Cursor f1376Qx;

    /* renamed from: Rx */
    private Cursor f1377Rx;

    public Cursor build() {
        Cursor cursor = this.f1377Rx;
        if (cursor == null || this.f1376Qx == null) {
            return null;
        }
        C1424b.m3592ia(!cursor.isClosed());
        C1424b.m3592ia(!this.f1376Qx.isClosed());
        MatrixCursor matrixCursor = new MatrixCursor(C1475t.f2341Wu);
        C0027n nVar = new C0027n();
        int position = this.f1377Rx.getPosition();
        this.f1377Rx.moveToPosition(-1);
        int i = 0;
        while (this.f1377Rx.moveToNext()) {
            nVar.put(this.f1377Rx.getString(3), Integer.valueOf(i));
            i++;
        }
        this.f1377Rx.moveToPosition(position);
        ArrayList arrayList = new ArrayList(this.f1377Rx.getCount());
        int position2 = this.f1376Qx.getPosition();
        this.f1376Qx.moveToPosition(-1);
        while (this.f1376Qx.moveToNext()) {
            if (nVar.containsKey(this.f1376Qx.getString(6))) {
                Object[] objArr = new Object[C1475t.f2341Wu.length];
                objArr[7] = Long.valueOf(this.f1376Qx.getLong(7));
                objArr[0] = Long.valueOf(this.f1376Qx.getLong(0));
                objArr[6] = this.f1376Qx.getString(6);
                objArr[1] = this.f1376Qx.getString(1);
                objArr[2] = this.f1376Qx.getString(2);
                objArr[3] = this.f1376Qx.getString(3);
                objArr[4] = Integer.valueOf(this.f1376Qx.getInt(4));
                objArr[5] = this.f1376Qx.getString(5);
                arrayList.add(objArr);
            }
        }
        this.f1376Qx.moveToPosition(position2);
        Collections.sort(arrayList, new C0956q(this, nVar));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            matrixCursor.addRow((Object[]) it.next());
        }
        return matrixCursor;
    }

    /* renamed from: ee */
    public void mo6639ee() {
        this.f1376Qx = null;
        this.f1377Rx = null;
    }

    /* renamed from: g */
    public C0957r mo6640g(Cursor cursor) {
        this.f1376Qx = cursor;
        return this;
    }

    /* renamed from: h */
    public C0957r mo6641h(Cursor cursor) {
        this.f1377Rx = cursor;
        return this;
    }
}
