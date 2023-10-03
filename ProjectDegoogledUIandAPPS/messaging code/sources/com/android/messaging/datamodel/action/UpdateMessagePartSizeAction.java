package com.android.messaging.datamodel.action;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.messaging.datamodel.C0887c;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.datamodel.C0955p;
import com.android.messaging.util.C1424b;

public class UpdateMessagePartSizeAction extends Action implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0809X();

    private UpdateMessagePartSizeAction(String str, int i, int i2) {
        this.f1057Oy.putString("part_id", str);
        this.f1057Oy.putInt("width", i);
        this.f1057Oy.putInt("height", i2);
    }

    /* renamed from: a */
    public static void m1462a(String str, int i, int i2) {
        C1424b.m3594t(str);
        C1424b.m3588b(i, 0, Integer.MAX_VALUE);
        C1424b.m3588b(i2, 0, Integer.MAX_VALUE);
        new UpdateMessagePartSizeAction(str, i, i2).start();
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    /* renamed from: ve */
    public Object mo5956ve() {
        String string = this.f1057Oy.getString("part_id");
        int i = this.f1057Oy.getInt("width");
        int i2 = this.f1057Oy.getInt("height");
        C0955p database = C0947h.get().getDatabase();
        database.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("width", Integer.valueOf(i));
            contentValues.put("height", Integer.valueOf(i2));
            C0887c.m1656a(database, "parts", "_id", string, contentValues);
            database.setTransactionSuccessful();
            database.endTransaction();
            return null;
        } catch (Throwable th) {
            database.endTransaction();
            throw th;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        mo5946a(parcel, i);
    }

    /* synthetic */ UpdateMessagePartSizeAction(Parcel parcel, C0809X x) {
        super(parcel);
    }
}
