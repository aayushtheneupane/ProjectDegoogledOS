package com.android.messaging.datamodel.action;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.android.messaging.datamodel.C0944e;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.datamodel.C0955p;
import com.android.messaging.datamodel.MessagingContentProvider;

public class MarkAsSeenAction extends Action implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0832v();

    public MarkAsSeenAction(String str) {
        this.f1057Oy.putString("conversation_id", str);
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    /* renamed from: ve */
    public Object mo5956ve() {
        String string = this.f1057Oy.getString("conversation_id");
        boolean z = !TextUtils.isEmpty(string);
        C0955p database = C0947h.get().getDatabase();
        database.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("seen", 1);
            if (z) {
                if (database.update("messages", contentValues, "seen != 1 AND conversation_id=?", new String[]{string}) > 0) {
                    MessagingContentProvider.m1273r(string);
                }
            } else {
                database.update("messages", contentValues, "seen != 1", (String[]) null);
            }
            database.setTransactionSuccessful();
            database.endTransaction();
            C0944e.m2090a(false, (String) null, 3);
            return null;
        } catch (Throwable th) {
            database.endTransaction();
            throw th;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        mo5946a(parcel, i);
    }

    /* synthetic */ MarkAsSeenAction(Parcel parcel, C0832v vVar) {
        super(parcel);
    }
}
