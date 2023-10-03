package com.android.messaging.datamodel.action;

import android.os.Parcel;
import android.os.Parcelable;

public class DeleteConversationAction extends Action implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0820j();

    private DeleteConversationAction(String str, long j) {
        this.f1057Oy.putString("conversation_id", str);
        this.f1057Oy.putLong("cutoff_timestamp", j);
    }

    /* renamed from: a */
    public static void m1351a(String str, long j) {
        new DeleteConversationAction(str, j).start();
    }

    /* JADX WARNING: type inference failed for: r5v5 */
    /* JADX WARNING: type inference failed for: r5v6 */
    /* JADX WARNING: type inference failed for: r5v7 */
    /* JADX WARNING: type inference failed for: r5v9 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00c3, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00c4, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00c7, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0276, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0277, code lost:
        r1.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x027a, code lost:
        throw r0;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:4:0x003d, B:14:0x00b0] */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=int, code=?, for r5v1, types: [int, boolean] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:50:0x01de */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00f3 A[Catch:{ all -> 0x00c3, all -> 0x0276 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0104 A[Catch:{ all -> 0x00c3, all -> 0x0276 }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x010e  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x025e  */
    /* renamed from: ue */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.os.Bundle mo5955ue() {
        /*
            r28 = this;
            r0 = r28
            com.android.messaging.datamodel.h r1 = com.android.messaging.datamodel.C0947h.get()
            com.android.messaging.datamodel.p r1 = r1.getDatabase()
            android.os.Bundle r2 = r0.f1057Oy
            java.lang.String r10 = "conversation_id"
            java.lang.String r11 = r2.getString(r10)
            android.os.Bundle r2 = r0.f1057Oy
            java.lang.String r3 = "cutoff_timestamp"
            long r12 = r2.getLong(r3)
            boolean r2 = android.text.TextUtils.isEmpty(r11)
            java.lang.String r15 = "MessagingAppDataModel"
            if (r2 != 0) goto L_0x027b
            long r8 = com.android.messaging.datamodel.C0887c.m1668f(r1, r11)
            com.android.messaging.util.C1424b.m3584Gj()
            r1.beginTransaction()
            r2 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            int r2 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            java.lang.String r3 = "conversation_id=?"
            java.lang.String r4 = "messages"
            r16 = 0
            r6 = 0
            r5 = 1
            if (r2 != 0) goto L_0x004d
            java.lang.String[] r2 = new java.lang.String[r5]     // Catch:{ all -> 0x0276 }
            r2[r6] = r11     // Catch:{ all -> 0x0276 }
            r1.delete(r4, r3, r2)     // Catch:{ all -> 0x0276 }
            r18 = r5
        L_0x0046:
            r3 = r6
            r26 = r8
            r25 = r15
            goto L_0x00f1
        L_0x004d:
            java.lang.String r2 = "conversation_id=? AND received_timestamp<=?"
            r14 = 2
            java.lang.String[] r7 = new java.lang.String[r14]     // Catch:{ all -> 0x0276 }
            r7[r6] = r11     // Catch:{ all -> 0x0276 }
            java.lang.String r19 = java.lang.Long.toString(r12)     // Catch:{ all -> 0x0276 }
            r7[r5] = r19     // Catch:{ all -> 0x0276 }
            r1.delete(r4, r2, r7)     // Catch:{ all -> 0x0276 }
            java.lang.String r2 = "message_status=? AND conversation_id=?"
            java.lang.String[] r7 = new java.lang.String[r14]     // Catch:{ all -> 0x0276 }
            r14 = 3
            java.lang.String r18 = java.lang.Integer.toString(r14)     // Catch:{ all -> 0x0276 }
            r7[r6] = r18     // Catch:{ all -> 0x0276 }
            r7[r5] = r11     // Catch:{ all -> 0x0276 }
            r1.delete(r4, r2, r7)     // Catch:{ all -> 0x0276 }
            java.lang.String[] r2 = new java.lang.String[r5]     // Catch:{ all -> 0x0276 }
            r2[r6] = r11     // Catch:{ all -> 0x0276 }
            long r3 = r1.mo6622a((java.lang.String) r4, (java.lang.String) r3, (java.lang.String[]) r2)     // Catch:{ all -> 0x0276 }
            int r2 = (r3 > r16 ? 1 : (r3 == r16 ? 0 : -1))
            if (r2 != 0) goto L_0x007c
            r18 = r5
            goto L_0x007e
        L_0x007c:
            r18 = r6
        L_0x007e:
            if (r18 != 0) goto L_0x0046
            java.lang.String r2 = "MAX(received_timestamp)"
            java.lang.String[] r7 = new java.lang.String[]{r2}     // Catch:{ all -> 0x0276 }
            java.lang.String[] r2 = new java.lang.String[r5]     // Catch:{ all -> 0x0276 }
            r2[r6] = r11     // Catch:{ all -> 0x0276 }
            java.lang.String r19 = "messages"
            java.lang.String r20 = "conversation_id=?"
            r21 = 0
            r22 = 0
            r23 = 0
            r24 = r2
            r2 = r1
            r25 = r15
            r14 = r3
            r3 = r19
            r4 = r7
            r7 = r5
            r5 = r20
            r6 = r24
            r7 = r21
            r26 = r8
            r8 = r22
            r9 = r23
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ all -> 0x0276 }
            if (r2 == 0) goto L_0x00c8
            boolean r3 = r2.moveToFirst()     // Catch:{ all -> 0x00c3 }
            if (r3 == 0) goto L_0x00bc
            r3 = 0
            long r4 = r2.getLong(r3)     // Catch:{ all -> 0x00c3 }
            goto L_0x00bf
        L_0x00bc:
            r3 = 0
            r4 = r16
        L_0x00bf:
            r2.close()     // Catch:{ all -> 0x0276 }
            goto L_0x00cb
        L_0x00c3:
            r0 = move-exception
            r2.close()     // Catch:{ all -> 0x0276 }
            throw r0     // Catch:{ all -> 0x0276 }
        L_0x00c8:
            r3 = 0
            r4 = r16
        L_0x00cb:
            java.lang.String r2 = "MessagingAppDb"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0276 }
            r6.<init>()     // Catch:{ all -> 0x0276 }
            java.lang.String r7 = "BugleDatabaseOperations: cannot delete all messages in a conversation, after deletion: count="
            r6.append(r7)     // Catch:{ all -> 0x0276 }
            r6.append(r14)     // Catch:{ all -> 0x0276 }
            java.lang.String r7 = ", max timestamp="
            r6.append(r7)     // Catch:{ all -> 0x0276 }
            r6.append(r4)     // Catch:{ all -> 0x0276 }
            java.lang.String r4 = ", cutoff timestamp="
            r6.append(r4)     // Catch:{ all -> 0x0276 }
            r6.append(r12)     // Catch:{ all -> 0x0276 }
            java.lang.String r4 = r6.toString()     // Catch:{ all -> 0x0276 }
            com.android.messaging.util.C1430e.m3630w(r2, r4)     // Catch:{ all -> 0x0276 }
        L_0x00f1:
            if (r18 == 0) goto L_0x0104
            java.lang.String r2 = "conversations"
            java.lang.String r4 = "_id=?"
            r5 = 1
            java.lang.String[] r6 = new java.lang.String[r5]     // Catch:{ all -> 0x0276 }
            r6[r3] = r11     // Catch:{ all -> 0x0276 }
            int r2 = r1.delete(r2, r4, r6)     // Catch:{ all -> 0x0276 }
            if (r2 <= 0) goto L_0x0105
            r2 = r5
            goto L_0x0106
        L_0x0104:
            r5 = 1
        L_0x0105:
            r2 = r3
        L_0x0106:
            r1.setTransactionSuccessful()     // Catch:{ all -> 0x0276 }
            r1.endTransaction()
            if (r2 == 0) goto L_0x025e
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "DeleteConversationAction: Deleted local conversation "
            r1.append(r2)
            r1.append(r11)
            java.lang.String r1 = r1.toString()
            r2 = r25
            com.android.messaging.util.C1430e.m3625i(r2, r1)
            r1 = 0
            com.android.messaging.datamodel.C0944e.m2090a((boolean) r5, (java.lang.String) r1, (int) r5)
            com.android.messaging.datamodel.MessagingContentProvider.m1263Wa()
            com.android.messaging.f r1 = com.android.messaging.C0967f.get()
            android.content.Context r1 = r1.getApplicationContext()
            com.android.messaging.widget.WidgetConversationProvider.m3886a((android.content.Context) r1, (java.lang.String) r11)
            r6 = r26
            int r1 = (r6 > r16 ? 1 : (r6 == r16 ? 0 : -1))
            if (r1 < 0) goto L_0x0183
            int r0 = com.android.messaging.sms.C1029y.m2414a((long) r6, (long) r12)
            if (r0 <= 0) goto L_0x0165
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "DeleteConversationAction: Deleted telephony thread "
            r0.append(r1)
            r0.append(r6)
            java.lang.String r1 = " (cutoffTimestamp = "
            r0.append(r1)
            r0.append(r12)
            java.lang.String r1 = ")"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.android.messaging.util.C1430e.m3625i(r2, r0)
            goto L_0x023e
        L_0x0165:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "DeleteConversationAction: Could not delete thread from telephony: conversationId = "
            r0.append(r1)
            r0.append(r11)
            java.lang.String r1 = ", thread id = "
            r0.append(r1)
            r0.append(r6)
            java.lang.String r0 = r0.toString()
            com.android.messaging.util.C1430e.m3630w(r2, r0)
            goto L_0x023e
        L_0x0183:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r4 = "DeleteConversationAction: Local conversation "
            r1.append(r4)
            r1.append(r11)
            java.lang.String r4 = " has an invalid telephony thread id; will delete messages individually"
            r1.append(r4)
            java.lang.String r1 = r1.toString()
            com.android.messaging.util.C1430e.m3630w(r2, r1)
            com.android.messaging.datamodel.h r1 = com.android.messaging.datamodel.C0947h.get()
            com.android.messaging.datamodel.p r18 = r1.getDatabase()
            android.os.Bundle r0 = r0.f1057Oy
            java.lang.String r0 = r0.getString(r10)
            com.android.messaging.util.C1424b.m3594t(r0)
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.lang.String r19 = "messages"
            java.lang.String r4 = "sms_message_uri"
            java.lang.String[] r20 = new java.lang.String[]{r4}     // Catch:{ all -> 0x0256 }
            java.lang.String r21 = "conversation_id=?"
            java.lang.String[] r4 = new java.lang.String[r5]     // Catch:{ all -> 0x0256 }
            r4[r3] = r0     // Catch:{ all -> 0x0256 }
            r23 = 0
            r24 = 0
            r25 = 0
            r22 = r4
            android.database.Cursor r14 = r18.query(r19, r20, r21, r22, r23, r24, r25)     // Catch:{ all -> 0x0256 }
        L_0x01cc:
            boolean r0 = r14.moveToNext()     // Catch:{ all -> 0x0254 }
            if (r0 == 0) goto L_0x01f3
            java.lang.String r0 = r14.getString(r3)     // Catch:{ all -> 0x0254 }
            android.net.Uri r4 = android.net.Uri.parse(r0)     // Catch:{ Exception -> 0x01de }
            r1.add(r4)     // Catch:{ Exception -> 0x01de }
            goto L_0x01cc
        L_0x01de:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0254 }
            r4.<init>()     // Catch:{ all -> 0x0254 }
            java.lang.String r5 = "DeleteConversationAction: Could not parse message uri "
            r4.append(r5)     // Catch:{ all -> 0x0254 }
            r4.append(r0)     // Catch:{ all -> 0x0254 }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x0254 }
            com.android.messaging.util.C1430e.m3622e(r2, r0)     // Catch:{ all -> 0x0254 }
            goto L_0x01cc
        L_0x01f3:
            r14.close()
            java.util.Iterator r0 = r1.iterator()
        L_0x01fa:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x023e
            java.lang.Object r1 = r0.next()
            android.net.Uri r1 = (android.net.Uri) r1
            int r3 = com.android.messaging.sms.C1029y.m2446l(r1)
            if (r3 <= 0) goto L_0x0228
            r3 = 3
            boolean r4 = android.util.Log.isLoggable(r2, r3)
            if (r4 == 0) goto L_0x01fa
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "DeleteConversationAction: Deleted telephony message "
            r4.append(r5)
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            com.android.messaging.util.C1430e.m3620d(r2, r1)
            goto L_0x01fa
        L_0x0228:
            r3 = 3
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "DeleteConversationAction: Could not delete telephony message "
            r4.append(r5)
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            com.android.messaging.util.C1430e.m3630w(r2, r1)
            goto L_0x01fa
        L_0x023e:
            com.android.messaging.f r0 = com.android.messaging.C0967f.get()
            android.content.Context r0 = r0.getApplicationContext()
            int r1 = android.os.Build.VERSION.SDK_INT
            java.lang.Class<android.app.NotificationManager> r1 = android.app.NotificationManager.class
            java.lang.Object r0 = r0.getSystemService(r1)
            android.app.NotificationManager r0 = (android.app.NotificationManager) r0
            r0.deleteNotificationChannel(r11)
            goto L_0x0281
        L_0x0254:
            r0 = move-exception
            goto L_0x0258
        L_0x0256:
            r0 = move-exception
            r14 = 0
        L_0x0258:
            if (r14 == 0) goto L_0x025d
            r14.close()
        L_0x025d:
            throw r0
        L_0x025e:
            r2 = r25
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "DeleteConversationAction: Could not delete local conversation "
            r0.append(r1)
            r0.append(r11)
            java.lang.String r0 = r0.toString()
            com.android.messaging.util.C1430e.m3630w(r2, r0)
            r1 = 0
            return r1
        L_0x0276:
            r0 = move-exception
            r1.endTransaction()
            throw r0
        L_0x027b:
            r2 = r15
            java.lang.String r0 = "DeleteConversationAction: conversationId is empty"
            com.android.messaging.util.C1430e.m3622e(r2, r0)
        L_0x0281:
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.action.DeleteConversationAction.mo5955ue():android.os.Bundle");
    }

    /* access modifiers changed from: protected */
    /* renamed from: ve */
    public Object mo5956ve() {
        mo5944Ee();
        return null;
    }

    public void writeToParcel(Parcel parcel, int i) {
        mo5946a(parcel, i);
    }

    /* synthetic */ DeleteConversationAction(Parcel parcel, C0820j jVar) {
        super(parcel);
    }
}
