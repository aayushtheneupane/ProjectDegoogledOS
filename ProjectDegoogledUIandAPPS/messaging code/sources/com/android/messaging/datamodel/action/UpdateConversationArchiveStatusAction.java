package com.android.messaging.datamodel.action;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.android.messaging.datamodel.C0887c;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.datamodel.C0955p;
import com.android.messaging.datamodel.MessagingContentProvider;
import com.android.messaging.util.C1424b;

public class UpdateConversationArchiveStatusAction extends Action {
    public static final Parcelable.Creator CREATOR = new C0804S();

    protected UpdateConversationArchiveStatusAction(String str, boolean z) {
        C1424b.m3592ia(!TextUtils.isEmpty(str));
        this.f1057Oy.putString("conversation_id", str);
        this.f1057Oy.putBoolean("is_archive", z);
    }

    /* renamed from: T */
    public static void m1455T(String str) {
        new UpdateConversationArchiveStatusAction(str, true).start();
    }

    /* renamed from: U */
    public static void m1456U(String str) {
        new UpdateConversationArchiveStatusAction(str, false).start();
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    /* renamed from: ve */
    public Object mo5956ve() {
        String string = this.f1057Oy.getString("conversation_id");
        boolean z = this.f1057Oy.getBoolean("is_archive");
        C0955p database = C0947h.get().getDatabase();
        database.beginTransaction();
        try {
            C0887c.m1650a(database, string, z);
            database.setTransactionSuccessful();
            database.endTransaction();
            MessagingContentProvider.m1263Wa();
            MessagingContentProvider.m1272q(string);
            return null;
        } catch (Throwable th) {
            database.endTransaction();
            throw th;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        mo5946a(parcel, i);
    }

    protected UpdateConversationArchiveStatusAction(Parcel parcel) {
        super(parcel);
    }
}
