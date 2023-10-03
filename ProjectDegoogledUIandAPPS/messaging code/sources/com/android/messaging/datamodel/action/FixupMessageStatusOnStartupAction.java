package com.android.messaging.datamodel.action;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.datamodel.C0955p;
import com.android.messaging.util.C1430e;

public class FixupMessageStatusOnStartupAction extends Action implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0824n();

    private FixupMessageStatusOnStartupAction() {
    }

    /* renamed from: Ge */
    public static void m1368Ge() {
        new FixupMessageStatusOnStartupAction().start();
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    /* renamed from: ve */
    public Object mo5956ve() {
        C0955p database = C0947h.get().getDatabase();
        database.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("message_status", 106);
            int update = database.update("messages", contentValues, "message_status IN (?, ?)", new String[]{Integer.toString(105), Integer.toString(103)});
            contentValues.clear();
            contentValues.put("message_status", 8);
            int update2 = database.update("messages", contentValues, "message_status IN (?, ?)", new String[]{Integer.toString(5), Integer.toString(6)});
            database.setTransactionSuccessful();
            database.endTransaction();
            C1430e.m3625i("MessagingAppDataModel", "Fixup: Send failed - " + update2 + " Download failed - " + update);
            return null;
        } catch (Throwable th) {
            database.endTransaction();
            throw th;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        mo5946a(parcel, i);
    }

    /* synthetic */ FixupMessageStatusOnStartupAction(Parcel parcel, C0824n nVar) {
        super(parcel);
    }
}
