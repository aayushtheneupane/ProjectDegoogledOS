package com.android.messaging.datamodel.action;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.messaging.datamodel.C0887c;
import com.android.messaging.datamodel.C0944e;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.datamodel.C0955p;
import com.android.messaging.datamodel.MessagingContentProvider;
import com.android.messaging.sms.C1029y;

public class MarkAsReadAction extends Action implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0831u();

    private MarkAsReadAction(String str) {
        this.f1057Oy.putString("conversation_id", str);
    }

    /* renamed from: Q */
    public static void m1383Q(String str) {
        new MarkAsReadAction(str).start();
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    /* renamed from: ve */
    public Object mo5956ve() {
        String string = this.f1057Oy.getString("conversation_id");
        C0955p database = C0947h.get().getDatabase();
        long f = C0887c.m1668f(database, string);
        if (f != -1) {
            C1029y.m2439b(f, Long.MAX_VALUE);
        }
        database.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("conversation_id", string);
            contentValues.put("read", 1);
            contentValues.put("seen", 1);
            if (database.update("messages", contentValues, "(read !=1 OR seen !=1 ) AND conversation_id=?", new String[]{string}) > 0) {
                MessagingContentProvider.m1273r(string);
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

    /* synthetic */ MarkAsReadAction(Parcel parcel, C0831u uVar) {
        super(parcel);
    }
}
