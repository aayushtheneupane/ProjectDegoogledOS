package com.android.messaging.datamodel.action;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.android.messaging.C0967f;
import com.android.messaging.datamodel.C0950k;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1467p;
import com.android.messaging.util.C1474sa;

public class ProcessPendingMessagesAction extends Action implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0787A();

    private ProcessPendingMessagesAction() {
    }

    /* access modifiers changed from: private */
    /* renamed from: Cb */
    public static void m1416Cb(int i) {
        C1467p ia = C0950k.m2132ia(i);
        if (ia != null) {
            ia.unregister();
        }
        new ProcessPendingMessagesAction().mo5945a(i + 103, Long.MAX_VALUE);
        if (Log.isLoggable("MessagingAppDataModel", 2)) {
            C1430e.m3628v("MessagingAppDataModel", "ProcessPendingMessagesAction: Unregistering for connectivity changed events and clearing scheduled alarm for subId " + i);
        }
    }

    /* renamed from: Ie */
    public static void m1417Ie() {
        C1474sa.m3794a(new C0835y());
    }

    /* access modifiers changed from: private */
    /* renamed from: W */
    public static void m1418W(int i, int i2) {
        C0967f.get().mo6661ha(i2).putInt("buglesub_process_pending_retry", i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x00cd  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00fd A[LOOP:0: B:33:0x00f2->B:37:0x00fd, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00ff A[EDGE_INSN: B:39:0x00ff->B:38:0x00ff ?: BREAK  
    EDGE_INSN: B:40:0x00ff->B:38:0x00ff ?: BREAK  ] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m1420a(boolean r11, com.android.messaging.datamodel.action.Action r12) {
        /*
            android.os.Bundle r0 = r12.f1057Oy
            r1 = -1
            java.lang.String r2 = "sub_id"
            int r0 = r0.getInt(r2, r1)
            java.lang.String r3 = "ProcessPendingMessagesAction: Scheduling pending messages"
            java.lang.StringBuilder r3 = p026b.p027a.p030b.p031a.C0632a.m1011Pa(r3)
            if (r11 == 0) goto L_0x0014
            java.lang.String r4 = "(message failed)"
            goto L_0x0016
        L_0x0014:
            java.lang.String r4 = ""
        L_0x0016:
            r3.append(r4)
            java.lang.String r4 = " for subId "
            r3.append(r4)
            r3.append(r0)
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "MessagingAppDataModel"
            com.android.messaging.util.C1430e.m3625i(r4, r3)
            m1416Cb(r0)
            com.android.messaging.util.sa r3 = com.android.messaging.util.C1474sa.getDefault()
            boolean r3 = r3.mo8228kk()
            r5 = 1
            r6 = 0
            if (r11 != 0) goto L_0x0069
            if (r3 == 0) goto L_0x0069
            m1418W(r6, r0)
            com.android.messaging.datamodel.action.ProcessPendingMessagesAction r11 = new com.android.messaging.datamodel.action.ProcessPendingMessagesAction
            r11.<init>()
            boolean r11 = r11.m1421c(r12)
            if (r11 == 0) goto L_0x0062
            r11 = 2
            boolean r11 = android.util.Log.isLoggable(r4, r11)
            if (r11 == 0) goto L_0x0061
            boolean r11 = r12.mo5957xe()
            if (r11 == 0) goto L_0x005c
            java.lang.String r11 = "ProcessPendingMessagesAction: Action queued"
            com.android.messaging.util.C1430e.m3628v(r4, r11)
            goto L_0x0061
        L_0x005c:
            java.lang.String r11 = "ProcessPendingMessagesAction: No actions to queue"
            com.android.messaging.util.C1430e.m3628v(r4, r11)
        L_0x0061:
            return
        L_0x0062:
            java.lang.String r11 = "ProcessPendingMessagesAction: Action failed to queue; retrying"
            com.android.messaging.util.C1430e.m3630w(r4, r11)
            r11 = r5
            goto L_0x006a
        L_0x0069:
            r11 = r6
        L_0x006a:
            com.android.messaging.datamodel.h r12 = com.android.messaging.datamodel.C0947h.get()
            com.android.messaging.datamodel.p r12 = r12.getDatabase()
            long r7 = java.lang.System.currentTimeMillis()
            java.lang.String r3 = com.android.messaging.datamodel.data.ParticipantData.m1834b(r12, r0)
            if (r3 != 0) goto L_0x0091
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r3 = "ProcessPendingMessagesAction: selfId is null for subId "
            r12.append(r3)
            r12.append(r0)
            java.lang.String r12 = r12.toString()
            com.android.messaging.util.C1430e.m3630w(r4, r12)
            goto L_0x00a0
        L_0x0091:
            java.lang.String r7 = m1419a(r12, r7, r3)
            if (r7 == 0) goto L_0x0098
            goto L_0x009e
        L_0x0098:
            java.lang.String r12 = m1423m(r12, r3)
            if (r12 == 0) goto L_0x00a0
        L_0x009e:
            r12 = r5
            goto L_0x00a1
        L_0x00a0:
            r12 = r6
        L_0x00a1:
            if (r12 != 0) goto L_0x00b0
            if (r11 == 0) goto L_0x00a6
            goto L_0x00b0
        L_0x00a6:
            m1418W(r6, r0)
            java.lang.String r11 = "ProcessPendingMessagesAction: No more pending messages"
            com.android.messaging.util.C1430e.m3625i(r4, r11)
            goto L_0x0128
        L_0x00b0:
            com.android.messaging.datamodel.action.z r11 = new com.android.messaging.datamodel.action.z
            r11.<init>(r0)
            com.android.messaging.f r12 = com.android.messaging.C0967f.get()
            com.android.messaging.util.h r12 = r12.mo6661ha(r0)
            java.lang.String r3 = "buglesub_process_pending_retry"
            int r6 = r12.getInt(r3, r6)
            int r6 = r6 + r5
            r12.putInt(r3, r6)
            com.android.messaging.util.p r12 = com.android.messaging.datamodel.C0950k.m2132ia(r0)
            if (r12 == 0) goto L_0x00d0
            r12.mo8199a((com.android.messaging.util.C1465o) r11)
        L_0x00d0:
            com.android.messaging.datamodel.action.ProcessPendingMessagesAction r11 = new com.android.messaging.datamodel.action.ProcessPendingMessagesAction
            r11.<init>()
            android.os.Bundle r12 = r11.f1057Oy
            r12.putInt(r2, r0)
            com.android.messaging.util.g r12 = com.android.messaging.util.C1449g.get()
            r2 = 5000(0x1388, double:2.4703E-320)
            java.lang.String r5 = "bugle_resend_delay_in_millis"
            r12.getLong(r5, r2)
            com.android.messaging.util.g r12 = com.android.messaging.util.C1449g.get()
            r7 = 7200000(0x6ddd00, double:3.5572727E-317)
            java.lang.String r5 = "bugle_max_resend_delay_in_millis"
            r12.getLong(r5, r7)
            r12 = r6
        L_0x00f2:
            int r12 = r12 + r1
            r9 = 2
            long r9 = r9 * r2
            if (r12 <= 0) goto L_0x00ff
            int r5 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r5 < 0) goto L_0x00fd
            goto L_0x00ff
        L_0x00fd:
            r2 = r9
            goto L_0x00f2
        L_0x00ff:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r1 = "ProcessPendingMessagesAction: Registering for retry #"
            r12.append(r1)
            r12.append(r6)
            java.lang.String r1 = " in "
            r12.append(r1)
            r12.append(r2)
            java.lang.String r1 = " ms for subId "
            r12.append(r1)
            r12.append(r0)
            java.lang.String r12 = r12.toString()
            com.android.messaging.util.C1430e.m3625i(r4, r12)
            int r0 = r0 + 103
            r11.mo5945a((int) r0, (long) r2)
        L_0x0128:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.action.ProcessPendingMessagesAction.m1420a(boolean, com.android.messaging.datamodel.action.Action):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x007d  */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean m1421c(com.android.messaging.datamodel.action.Action r8) {
        /*
            r7 = this;
            com.android.messaging.datamodel.h r7 = com.android.messaging.datamodel.C0947h.get()
            com.android.messaging.datamodel.p r7 = r7.getDatabase()
            long r0 = java.lang.System.currentTimeMillis()
            android.os.Bundle r2 = r8.f1057Oy
            java.lang.String r3 = "sub_id"
            r4 = -1
            int r2 = r2.getInt(r3, r4)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "ProcessPendingMessagesAction: Start queueing for subId "
            r3.append(r4)
            r3.append(r2)
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "MessagingAppDataModel"
            com.android.messaging.util.C1430e.m3625i(r4, r3)
            java.lang.String r2 = com.android.messaging.datamodel.data.ParticipantData.m1834b(r7, r2)
            r3 = 0
            if (r2 != 0) goto L_0x0038
            java.lang.String r7 = "ProcessPendingMessagesAction: selfId is null"
            com.android.messaging.util.C1430e.m3630w(r4, r7)
            return r3
        L_0x0038:
            java.lang.String r0 = m1419a(r7, r0, r2)
            java.lang.String r7 = m1423m(r7, r2)
            java.lang.String r1 = "ProcessPendingMessagesAction: Failed to queue message "
            java.lang.String r2 = "ProcessPendingMessagesAction: Queueing message "
            if (r0 == 0) goto L_0x007a
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r2)
            r5.append(r0)
            java.lang.String r6 = " for sending"
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            com.android.messaging.util.C1430e.m3625i(r4, r5)
            boolean r5 = com.android.messaging.datamodel.action.SendMessageAction.m1441c(r0, r8)
            if (r5 != 0) goto L_0x007a
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r1)
            r5.append(r0)
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            com.android.messaging.util.C1430e.m3630w(r4, r5)
            r5 = r3
            goto L_0x007b
        L_0x007a:
            r5 = 1
        L_0x007b:
            if (r7 == 0) goto L_0x00b0
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r2)
            r6.append(r7)
            java.lang.String r2 = " for download"
            r6.append(r2)
            java.lang.String r6 = r6.toString()
            com.android.messaging.util.C1430e.m3625i(r4, r6)
            boolean r8 = com.android.messaging.datamodel.action.DownloadMmsAction.m1358b(r7, r8)
            if (r8 != 0) goto L_0x00b0
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r1)
            r8.append(r7)
            r8.append(r2)
            java.lang.String r8 = r8.toString()
            com.android.messaging.util.C1430e.m3630w(r4, r8)
            goto L_0x00b1
        L_0x00b0:
            r3 = r5
        L_0x00b1:
            if (r0 != 0) goto L_0x00ba
            if (r7 != 0) goto L_0x00ba
            java.lang.String r7 = "ProcessPendingMessagesAction: No messages to send or download"
            com.android.messaging.util.C1430e.m3625i(r4, r7)
        L_0x00ba:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.action.ProcessPendingMessagesAction.m1421c(com.android.messaging.datamodel.action.Action):boolean");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r1v2 */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x00a2  */
    /* renamed from: m */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String m1423m(com.android.messaging.datamodel.C0955p r16, java.lang.String r17) {
        /*
            r16.beginTransaction()
            r1 = 0
            java.lang.String r0 = "messages"
            java.lang.String r2 = "message_status IN (?, ?) AND self_id =?"
            r3 = 3
            java.lang.String[] r4 = new java.lang.String[r3]     // Catch:{ all -> 0x009c }
            r5 = 105(0x69, float:1.47E-43)
            java.lang.String r5 = java.lang.Integer.toString(r5)     // Catch:{ all -> 0x009c }
            r6 = 0
            r4[r6] = r5     // Catch:{ all -> 0x009c }
            r5 = 103(0x67, float:1.44E-43)
            java.lang.String r5 = java.lang.Integer.toString(r5)     // Catch:{ all -> 0x009c }
            r7 = 1
            r4[r7] = r5     // Catch:{ all -> 0x009c }
            r5 = 2
            r4[r5] = r17     // Catch:{ all -> 0x009c }
            r15 = r16
            long r8 = r15.mo6622a((java.lang.String) r0, (java.lang.String) r2, (java.lang.String[]) r4)     // Catch:{ all -> 0x009c }
            int r0 = (int) r8     // Catch:{ all -> 0x009c }
            java.lang.String r9 = "messages"
            java.lang.String[] r10 = com.android.messaging.datamodel.data.MessageData.getProjection()     // Catch:{ all -> 0x009c }
            java.lang.String r11 = "message_status IN (?, ?) AND self_id =? "
            java.lang.String[] r12 = new java.lang.String[r3]     // Catch:{ all -> 0x009c }
            r2 = 104(0x68, float:1.46E-43)
            java.lang.String r2 = java.lang.Integer.toString(r2)     // Catch:{ all -> 0x009c }
            r12[r6] = r2     // Catch:{ all -> 0x009c }
            r2 = 102(0x66, float:1.43E-43)
            java.lang.String r2 = java.lang.Integer.toString(r2)     // Catch:{ all -> 0x009c }
            r12[r7] = r2     // Catch:{ all -> 0x009c }
            r12[r5] = r17     // Catch:{ all -> 0x009c }
            r13 = 0
            r14 = 0
            java.lang.String r2 = "received_timestamp ASC"
            r8 = r16
            r15 = r2
            android.database.Cursor r2 = r8.query(r9, r10, r11, r12, r13, r14, r15)     // Catch:{ all -> 0x009c }
            int r4 = r2.getCount()     // Catch:{ all -> 0x0099 }
            if (r0 != 0) goto L_0x0066
            boolean r5 = r2.moveToNext()     // Catch:{ all -> 0x0099 }
            if (r5 == 0) goto L_0x0066
            com.android.messaging.datamodel.data.MessageData r1 = new com.android.messaging.datamodel.data.MessageData     // Catch:{ all -> 0x0099 }
            r1.<init>()     // Catch:{ all -> 0x0099 }
            r1.mo6260c(r2)     // Catch:{ all -> 0x0099 }
            java.lang.String r1 = r1.getMessageId()     // Catch:{ all -> 0x0099 }
        L_0x0066:
            r16.setTransactionSuccessful()     // Catch:{ all -> 0x0099 }
            r16.endTransaction()
            r2.close()
            java.lang.String r2 = "MessagingAppDataModel"
            boolean r3 = android.util.Log.isLoggable(r2, r3)
            if (r3 == 0) goto L_0x0098
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = "ProcessPendingMessagesAction: "
            r3.append(r5)
            r3.append(r0)
            java.lang.String r0 = " messages already downloading, "
            r3.append(r0)
            r3.append(r4)
            java.lang.String r0 = " messages to download"
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            com.android.messaging.util.C1430e.m3620d(r2, r0)
        L_0x0098:
            return r1
        L_0x0099:
            r0 = move-exception
            r1 = r2
            goto L_0x009d
        L_0x009c:
            r0 = move-exception
        L_0x009d:
            r16.endTransaction()
            if (r1 == 0) goto L_0x00a5
            r1.close()
        L_0x00a5:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.action.ProcessPendingMessagesAction.m1423m(com.android.messaging.datamodel.p, java.lang.String):java.lang.String");
    }

    /* access modifiers changed from: protected */
    /* renamed from: ve */
    public Object mo5956ve() {
        m1416Cb(this.f1057Oy.getInt("sub_id", -1));
        if (!C1474sa.getDefault().mo8228kk()) {
            if (Log.isLoggable("MessagingAppDataModel", 2)) {
                C1430e.m3628v("MessagingAppDataModel", "ProcessPendingMessagesAction: Not default SMS app; rescheduling");
            }
            m1420a(true, this);
            return null;
        } else if (m1421c(this)) {
            return null;
        } else {
            C1430e.m3628v("MessagingAppDataModel", "ProcessPendingMessagesAction: rescheduling");
            m1420a(true, this);
            return null;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        mo5946a(parcel, i);
    }

    /* synthetic */ ProcessPendingMessagesAction(C0835y yVar) {
    }

    /* synthetic */ ProcessPendingMessagesAction(Parcel parcel, C0835y yVar) {
        super(parcel);
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0119  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String m1419a(com.android.messaging.datamodel.C0955p r16, long r17, java.lang.String r19) {
        /*
            r9 = r16
            r0 = r19
            r16.beginTransaction()
            r10 = 0
            java.lang.String r1 = "messages"
            java.lang.String r2 = "message_status IN (?, ?) AND self_id =? "
            r11 = 3
            java.lang.String[] r3 = new java.lang.String[r11]     // Catch:{ all -> 0x0112 }
            r4 = 5
            java.lang.String r4 = java.lang.Integer.toString(r4)     // Catch:{ all -> 0x0112 }
            r12 = 0
            r3[r12] = r4     // Catch:{ all -> 0x0112 }
            r4 = 6
            java.lang.String r4 = java.lang.Integer.toString(r4)     // Catch:{ all -> 0x0112 }
            r5 = 1
            r3[r5] = r4     // Catch:{ all -> 0x0112 }
            r4 = 2
            r3[r4] = r0     // Catch:{ all -> 0x0112 }
            long r1 = r9.mo6622a((java.lang.String) r1, (java.lang.String) r2, (java.lang.String[]) r3)     // Catch:{ all -> 0x0112 }
            int r13 = (int) r1     // Catch:{ all -> 0x0112 }
            java.lang.String r2 = "messages"
            java.lang.String[] r3 = com.android.messaging.datamodel.data.MessageData.getProjection()     // Catch:{ all -> 0x0112 }
            java.lang.String r6 = "message_status IN (?, ?) AND self_id =? "
            java.lang.String[] r7 = new java.lang.String[r11]     // Catch:{ all -> 0x0112 }
            r1 = 4
            java.lang.String r1 = java.lang.Integer.toString(r1)     // Catch:{ all -> 0x0112 }
            r7[r12] = r1     // Catch:{ all -> 0x0112 }
            r1 = 7
            java.lang.String r1 = java.lang.Integer.toString(r1)     // Catch:{ all -> 0x0112 }
            r7[r5] = r1     // Catch:{ all -> 0x0112 }
            r7[r4] = r0     // Catch:{ all -> 0x0112 }
            r8 = 0
            r14 = 0
            java.lang.String r15 = "received_timestamp ASC"
            r1 = r16
            r4 = r6
            r5 = r7
            r6 = r8
            r7 = r14
            r8 = r15
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x0112 }
            int r2 = r1.getCount()     // Catch:{ all -> 0x0110 }
            android.content.ContentValues r3 = new android.content.ContentValues     // Catch:{ all -> 0x0110 }
            r3.<init>()     // Catch:{ all -> 0x0110 }
            java.lang.String r4 = "message_status"
            r5 = 8
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0110 }
            r3.put(r4, r5)     // Catch:{ all -> 0x0110 }
        L_0x0064:
            boolean r4 = r1.moveToNext()     // Catch:{ all -> 0x0110 }
            if (r4 == 0) goto L_0x00d5
            com.android.messaging.datamodel.data.MessageData r4 = new com.android.messaging.datamodel.data.MessageData     // Catch:{ all -> 0x0110 }
            r4.<init>()     // Catch:{ all -> 0x0110 }
            r4.mo6260c(r1)     // Catch:{ all -> 0x0110 }
            r5 = r17
            boolean r7 = r4.mo6278m(r5)     // Catch:{ all -> 0x0110 }
            if (r7 == 0) goto L_0x00c4
            if (r13 != 0) goto L_0x00d5
            java.lang.String r10 = r4.getMessageId()     // Catch:{ all -> 0x0110 }
            boolean r3 = com.android.messaging.util.C1464na.m3759Zj()     // Catch:{ all -> 0x0110 }
            if (r3 == 0) goto L_0x00d5
            com.android.messaging.datamodel.data.ParticipantData r0 = com.android.messaging.datamodel.C0887c.m1662c(r9, r0)     // Catch:{ all -> 0x0110 }
            if (r0 == 0) goto L_0x0092
            boolean r0 = r0.mo6358wh()     // Catch:{ all -> 0x0110 }
            if (r0 != 0) goto L_0x00d5
        L_0x0092:
            com.android.messaging.util.sa r0 = com.android.messaging.util.C1474sa.getDefault()     // Catch:{ all -> 0x0110 }
            int r0 = r0.getDefaultSmsSubscriptionId()     // Catch:{ all -> 0x0110 }
            com.android.messaging.datamodel.data.ParticipantData r0 = com.android.messaging.datamodel.C0887c.m1633a((com.android.messaging.datamodel.C0955p) r9, (int) r0)     // Catch:{ all -> 0x0110 }
            if (r0 == 0) goto L_0x00d5
            java.lang.String r3 = r0.getId()     // Catch:{ all -> 0x0110 }
            r4.mo6261ca(r3)     // Catch:{ all -> 0x0110 }
            android.content.ContentValues r3 = new android.content.ContentValues     // Catch:{ all -> 0x0110 }
            r3.<init>()     // Catch:{ all -> 0x0110 }
            java.lang.String r5 = "self_id"
            java.lang.String r0 = r0.getId()     // Catch:{ all -> 0x0110 }
            r3.put(r5, r0)     // Catch:{ all -> 0x0110 }
            java.lang.String r0 = r4.getMessageId()     // Catch:{ all -> 0x0110 }
            com.android.messaging.datamodel.C0887c.m1663c((com.android.messaging.datamodel.C0955p) r9, (java.lang.String) r0, (android.content.ContentValues) r3)     // Catch:{ all -> 0x0110 }
            java.lang.String r0 = r4.mo6250Ue()     // Catch:{ all -> 0x0110 }
            com.android.messaging.datamodel.MessagingContentProvider.m1273r(r0)     // Catch:{ all -> 0x0110 }
            goto L_0x00d5
        L_0x00c4:
            int r12 = r12 + 1
            java.lang.String r7 = r4.getMessageId()     // Catch:{ all -> 0x0110 }
            com.android.messaging.datamodel.C0887c.m1663c((com.android.messaging.datamodel.C0955p) r9, (java.lang.String) r7, (android.content.ContentValues) r3)     // Catch:{ all -> 0x0110 }
            java.lang.String r4 = r4.mo6250Ue()     // Catch:{ all -> 0x0110 }
            com.android.messaging.datamodel.MessagingContentProvider.m1273r(r4)     // Catch:{ all -> 0x0110 }
            goto L_0x0064
        L_0x00d5:
            r16.setTransactionSuccessful()     // Catch:{ all -> 0x0110 }
            r16.endTransaction()
            r1.close()
            java.lang.String r0 = "MessagingAppDataModel"
            boolean r1 = android.util.Log.isLoggable(r0, r11)
            if (r1 == 0) goto L_0x010f
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "ProcessPendingMessagesAction: "
            r1.append(r3)
            r1.append(r13)
            java.lang.String r3 = " messages already sending, "
            r1.append(r3)
            r1.append(r2)
            java.lang.String r2 = " messages to send, "
            r1.append(r2)
            r1.append(r12)
            java.lang.String r2 = " failed messages"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.android.messaging.util.C1430e.m3620d(r0, r1)
        L_0x010f:
            return r10
        L_0x0110:
            r0 = move-exception
            goto L_0x0114
        L_0x0112:
            r0 = move-exception
            r1 = r10
        L_0x0114:
            r16.endTransaction()
            if (r1 == 0) goto L_0x011c
            r1.close()
        L_0x011c:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.action.ProcessPendingMessagesAction.m1419a(com.android.messaging.datamodel.p, long, java.lang.String):java.lang.String");
    }
}
