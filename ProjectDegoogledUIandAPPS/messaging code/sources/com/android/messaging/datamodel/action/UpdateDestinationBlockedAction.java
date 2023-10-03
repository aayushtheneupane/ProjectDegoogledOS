package com.android.messaging.datamodel.action;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.android.messaging.util.C1424b;

public class UpdateDestinationBlockedAction extends Action {
    public static final Parcelable.Creator CREATOR = new C0805T();

    protected UpdateDestinationBlockedAction(String str, boolean z, String str2, String str3) {
        super(str3);
        C1424b.m3592ia(!TextUtils.isEmpty(str));
        this.f1057Oy.putString("destination", str);
        this.f1057Oy.putBoolean("blocked", z);
        this.f1057Oy.putString("conversation_id", str2);
    }

    /* renamed from: a */
    public static C0807V m1458a(String str, boolean z, String str2, C0806U u) {
        C1424b.m3594t(u);
        C0807V v = new C0807V((Object) null, u);
        new UpdateDestinationBlockedAction(str, z, str2, v.mo6033Ne()).mo5947a((C0813c) v);
        return v;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0060  */
    /* renamed from: ve */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object mo5956ve() {
        /*
            r12 = this;
            android.os.Bundle r0 = r12.f1057Oy
            java.lang.String r1 = "destination"
            java.lang.String r0 = r0.getString(r1)
            android.os.Bundle r1 = r12.f1057Oy
            java.lang.String r2 = "blocked"
            boolean r1 = r1.getBoolean(r2)
            android.os.Bundle r12 = r12.f1057Oy
            java.lang.String r2 = "conversation_id"
            java.lang.String r12 = r12.getString(r2)
            com.android.messaging.datamodel.h r2 = com.android.messaging.datamodel.C0947h.get()
            com.android.messaging.datamodel.p r3 = r2.getDatabase()
            com.android.messaging.datamodel.C0887c.m1660b((com.android.messaging.datamodel.C0955p) r3, (java.lang.String) r0, (boolean) r1)
            r2 = 0
            if (r12 != 0) goto L_0x0064
            com.android.messaging.util.C1424b.m3584Gj()
            java.lang.String r4 = "conversations"
            java.lang.String r12 = "_id"
            java.lang.String[] r5 = new java.lang.String[]{r12}     // Catch:{ all -> 0x005c }
            java.lang.String r6 = "participant_normalized_destination=?"
            r12 = 1
            java.lang.String[] r7 = new java.lang.String[r12]     // Catch:{ all -> 0x005c }
            r11 = 0
            r7[r11] = r0     // Catch:{ all -> 0x005c }
            r8 = 0
            r9 = 0
            r10 = 0
            android.database.Cursor r0 = r3.query(r4, r5, r6, r7, r8, r9, r10)     // Catch:{ all -> 0x005c }
            int r3 = r0.getCount()     // Catch:{ all -> 0x005a }
            com.android.messaging.util.C1424b.m3588b(r3, r11, r12)     // Catch:{ all -> 0x005a }
            boolean r12 = r0.moveToFirst()     // Catch:{ all -> 0x005a }
            if (r12 == 0) goto L_0x0055
            java.lang.String r12 = r0.getString(r11)     // Catch:{ all -> 0x005a }
            r0.close()
            goto L_0x0064
        L_0x0055:
            r0.close()
            r12 = r2
            goto L_0x0064
        L_0x005a:
            r12 = move-exception
            goto L_0x005e
        L_0x005c:
            r12 = move-exception
            r0 = r2
        L_0x005e:
            if (r0 == 0) goto L_0x0063
            r0.close()
        L_0x0063:
            throw r12
        L_0x0064:
            if (r12 == 0) goto L_0x0072
            if (r1 == 0) goto L_0x006c
            com.android.messaging.datamodel.action.UpdateConversationArchiveStatusAction.m1455T(r12)
            goto L_0x006f
        L_0x006c:
            com.android.messaging.datamodel.action.UpdateConversationArchiveStatusAction.m1456U(r12)
        L_0x006f:
            com.android.messaging.datamodel.MessagingContentProvider.m1274s(r12)
        L_0x0072:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.action.UpdateDestinationBlockedAction.mo5956ve():java.lang.Object");
    }

    public void writeToParcel(Parcel parcel, int i) {
        mo5946a(parcel, i);
    }

    protected UpdateDestinationBlockedAction(Parcel parcel) {
        super(parcel);
    }
}
