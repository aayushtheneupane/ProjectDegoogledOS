package com.android.messaging.datamodel;

import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import com.android.messaging.C0967f;
import com.android.messaging.R;
import com.android.messaging.p041ui.C1040Ea;
import com.android.messaging.util.C1449g;

/* renamed from: com.android.messaging.datamodel.J */
public abstract class C0769J extends C0771L {

    /* renamed from: ey */
    protected Uri f1004ey;

    /* renamed from: fy */
    protected String f1005fy;
    protected CharSequence mContent;
    protected String mTitle;

    /* renamed from: sy */
    protected String f1006sy;

    /* renamed from: ty */
    protected CharSequence f1007ty;

    /* renamed from: uy */
    final C0763D f1008uy;

    /* renamed from: vy */
    private long f1009vy;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected C0769J(com.android.messaging.datamodel.C0763D r6) {
        /*
            r5 = this;
            r0 = 0
            if (r6 == 0) goto L_0x002a
            java.util.List r1 = r6.f986Vx
            if (r1 == 0) goto L_0x002a
            int r1 = r1.size()
            if (r1 <= 0) goto L_0x002a
            com.android.messaging.util.ConversationIdSet r1 = new com.android.messaging.util.ConversationIdSet
            r1.<init>()
            java.util.List r2 = r6.f986Vx
            java.util.Iterator r2 = r2.iterator()
        L_0x0018:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x002b
            java.lang.Object r3 = r2.next()
            com.android.messaging.datamodel.E r3 = (com.android.messaging.datamodel.C0764E) r3
            java.lang.String r3 = r3.f987Ka
            r1.add(r3)
            goto L_0x0018
        L_0x002a:
            r1 = r0
        L_0x002b:
            r5.<init>(r1)
            r5.f1006sy = r0
            r5.f1007ty = r0
            r5.mTitle = r0
            r5.mContent = r0
            r5.f1004ey = r0
            r5.f1005fy = r0
            r5.f1008uy = r6
            r0 = 0
            r5.mType = r0
            r0 = -9223372036854775808
            r5.f1009vy = r0
            if (r6 == 0) goto L_0x0062
            java.util.List r6 = r6.f986Vx
            java.util.Iterator r6 = r6.iterator()
        L_0x004b:
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto L_0x0062
            java.lang.Object r0 = r6.next()
            com.android.messaging.datamodel.E r0 = (com.android.messaging.datamodel.C0764E) r0
            long r1 = r5.f1009vy
            long r3 = r0.f991Zx
            long r0 = java.lang.Math.max(r1, r3)
            r5.f1009vy = r0
            goto L_0x004b
        L_0x0062:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.C0769J.<init>(com.android.messaging.datamodel.D):void");
    }

    /* renamed from: a */
    static CharSequence m1239a(Context context, CharSequence charSequence) {
        if (charSequence == null) {
            return null;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(charSequence);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.notification_warning_color)), 0, charSequence.length(), 33);
        return spannableStringBuilder;
    }

    /* renamed from: co */
    private static int m1240co() {
        if (!C0944e.m2077Td()) {
            C1449g.get().getInt("bugle_max_messages_in_conversation_notification", 7);
            return 7;
        }
        C1449g.get().getInt("bugle_max_messages_in_conversation_notification_with_wearable", 1);
        return 1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x006d, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x006e, code lost:
        if (r0 != null) goto L_0x0070;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0074, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0075, code lost:
        r7.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0078, code lost:
        throw r1;
     */
    /* renamed from: db */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.HashMap m1241db(java.lang.String r7) {
        /*
            com.android.messaging.f r0 = com.android.messaging.C0967f.get()
            android.content.Context r0 = r0.getApplicationContext()
            android.net.Uri r2 = com.android.messaging.datamodel.MessagingContentProvider.m1270o(r7)
            com.android.messaging.datamodel.data.u r7 = new com.android.messaging.datamodel.data.u
            r7.<init>()
            android.content.ContentResolver r1 = r0.getContentResolver()
            java.lang.String[] r3 = com.android.messaging.datamodel.data.C0901M.f1157Wu
            r4 = 0
            r5 = 0
            r6 = 0
            android.database.Cursor r0 = r1.query(r2, r3, r4, r5, r6)
            r7.mo6574c(r0)     // Catch:{ all -> 0x006b }
            if (r0 == 0) goto L_0x0026
            r0.close()
        L_0x0026:
            java.util.Iterator r7 = r7.iterator()
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r1 = 0
            r2 = r1
        L_0x0031:
            boolean r3 = r7.hasNext()
            if (r3 == 0) goto L_0x006a
            java.lang.Object r3 = r7.next()
            com.android.messaging.datamodel.data.ParticipantData r3 = (com.android.messaging.datamodel.data.ParticipantData) r3
            boolean r4 = r3.mo6362zh()
            r5 = 1
            if (r4 == 0) goto L_0x0048
            if (r2 == 0) goto L_0x0047
            goto L_0x0031
        L_0x0047:
            r2 = r5
        L_0x0048:
            java.lang.String r3 = r3.mo6348oh()
            if (r3 != 0) goto L_0x004f
            goto L_0x0031
        L_0x004f:
            boolean r4 = r0.containsKey(r3)
            if (r4 == 0) goto L_0x0060
            java.lang.Object r4 = r0.get(r3)
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            goto L_0x0061
        L_0x0060:
            r4 = r1
        L_0x0061:
            int r4 = r4 + r5
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r0.put(r3, r4)
            goto L_0x0031
        L_0x006a:
            return r0
        L_0x006b:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x006d }
        L_0x006d:
            r1 = move-exception
            if (r0 == 0) goto L_0x0078
            r0.close()     // Catch:{ all -> 0x0074 }
            goto L_0x0078
        L_0x0074:
            r0 = move-exception
            r7.addSuppressed(r0)
        L_0x0078:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.C0769J.m1241db(java.lang.String):java.util.HashMap");
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x00b8 A[Catch:{ all -> 0x01d5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00b9 A[Catch:{ all -> 0x01d5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00c0 A[Catch:{ all -> 0x01d5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00ed A[Catch:{ all -> 0x01d5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x01e1  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0102 A[SYNTHETIC] */
    /* renamed from: f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.app.Notification m1242f(java.lang.String r16, int r17) {
        /*
            com.android.messaging.f r0 = com.android.messaging.C0967f.get()
            android.content.Context r0 = r0.getApplicationContext()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r2 = 0
            com.android.messaging.datamodel.h r3 = com.android.messaging.datamodel.C0947h.get()     // Catch:{ all -> 0x01dd }
            com.android.messaging.datamodel.p r3 = r3.getDatabase()     // Catch:{ all -> 0x01dd }
            r4 = 1
            java.lang.String[] r5 = new java.lang.String[r4]     // Catch:{ all -> 0x01dd }
            r6 = 0
            r5[r6] = r16     // Catch:{ all -> 0x01dd }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x01dd }
            r7.<init>()     // Catch:{ all -> 0x01dd }
            java.lang.String r8 = com.android.messaging.datamodel.data.C0936s.m2018Hg()     // Catch:{ all -> 0x01dd }
            r7.append(r8)     // Catch:{ all -> 0x01dd }
            java.lang.String r8 = " LIMIT "
            r7.append(r8)     // Catch:{ all -> 0x01dd }
            r8 = 21
            r7.append(r8)     // Catch:{ all -> 0x01dd }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x01dd }
            android.database.Cursor r3 = r3.rawQuery(r7, r5)     // Catch:{ all -> 0x01dd }
            if (r3 == 0) goto L_0x01d7
            boolean r5 = r3.moveToFirst()     // Catch:{ all -> 0x01d5 }
            if (r5 != 0) goto L_0x0044
            goto L_0x01d7
        L_0x0044:
            com.android.messaging.datamodel.data.s r5 = new com.android.messaging.datamodel.data.s     // Catch:{ all -> 0x01d5 }
            r5.<init>()     // Catch:{ all -> 0x01d5 }
            java.util.HashMap r7 = m1241db(r16)     // Catch:{ all -> 0x01d5 }
            r9 = r6
        L_0x004e:
            r5.mo6538c(r3)     // Catch:{ all -> 0x01d5 }
            java.lang.String r10 = r5.mo6567zg()     // Catch:{ all -> 0x01d5 }
            java.lang.String r11 = r5.mo6566yg()     // Catch:{ all -> 0x01d5 }
            java.lang.String r12 = r5.getText()     // Catch:{ all -> 0x01d5 }
            boolean r13 = r5.mo6548hg()     // Catch:{ all -> 0x01d5 }
            if (r13 == 0) goto L_0x008d
            if (r12 == 0) goto L_0x008d
            android.text.Spanned r12 = android.text.Html.fromHtml(r12)     // Catch:{ all -> 0x01d5 }
            boolean r13 = r12 instanceof android.text.Spannable     // Catch:{ all -> 0x01d5 }
            if (r13 == 0) goto L_0x0089
            r13 = r12
            android.text.Spannable r13 = (android.text.Spannable) r13     // Catch:{ all -> 0x01d5 }
            int r14 = r13.length()     // Catch:{ all -> 0x01d5 }
            java.lang.Class<android.text.style.URLSpan> r15 = android.text.style.URLSpan.class
            java.lang.Object[] r14 = r13.getSpans(r6, r14, r15)     // Catch:{ all -> 0x01d5 }
            android.text.style.URLSpan[] r14 = (android.text.style.URLSpan[]) r14     // Catch:{ all -> 0x01d5 }
            int r15 = r14.length     // Catch:{ all -> 0x01d5 }
        L_0x007d:
            if (r6 >= r15) goto L_0x0089
            r8 = r14[r6]     // Catch:{ all -> 0x01d5 }
            r13.removeSpan(r8)     // Catch:{ all -> 0x01d5 }
            int r6 = r6 + 1
            r8 = 21
            goto L_0x007d
        L_0x0089:
            java.lang.String r12 = r12.toString()     // Catch:{ all -> 0x01d5 }
        L_0x008d:
            boolean r6 = android.text.TextUtils.isEmpty(r12)     // Catch:{ all -> 0x01d5 }
            if (r6 == 0) goto L_0x009b
            boolean r6 = r5.mo6553mf()     // Catch:{ all -> 0x01d5 }
            if (r6 != 0) goto L_0x009b
            goto L_0x010b
        L_0x009b:
            boolean r6 = r5.mo6549ig()     // Catch:{ all -> 0x01d5 }
            if (r6 == 0) goto L_0x00a2
            r9 = r4
        L_0x00a2:
            if (r11 != 0) goto L_0x00a5
            goto L_0x00b5
        L_0x00a5:
            java.lang.Object r6 = r7.get(r11)     // Catch:{ all -> 0x01d5 }
            java.lang.Integer r6 = (java.lang.Integer) r6     // Catch:{ all -> 0x01d5 }
            if (r6 == 0) goto L_0x00b5
            int r6 = r6.intValue()     // Catch:{ all -> 0x01d5 }
            if (r6 <= r4) goto L_0x00b5
            r6 = r4
            goto L_0x00b6
        L_0x00b5:
            r6 = 0
        L_0x00b6:
            if (r6 == 0) goto L_0x00b9
            goto L_0x00ba
        L_0x00b9:
            r10 = r11
        L_0x00ba:
            boolean r6 = android.text.TextUtils.isEmpty(r10)     // Catch:{ all -> 0x01d5 }
            if (r6 == 0) goto L_0x00df
            boolean r6 = r5.mo6546gg()     // Catch:{ all -> 0x01d5 }
            if (r6 == 0) goto L_0x00d8
            java.lang.String r10 = r5.mo6564wg()     // Catch:{ all -> 0x01d5 }
            boolean r6 = android.text.TextUtils.isEmpty(r10)     // Catch:{ all -> 0x01d5 }
            if (r6 == 0) goto L_0x00df
            r6 = 2131821004(0x7f1101cc, float:1.9274739E38)
            java.lang.String r10 = r0.getString(r6)     // Catch:{ all -> 0x01d5 }
            goto L_0x00df
        L_0x00d8:
            r6 = 2131821003(0x7f1101cb, float:1.9274737E38)
            java.lang.String r10 = r0.getString(r6)     // Catch:{ all -> 0x01d5 }
        L_0x00df:
            java.util.List r6 = r5.mo6534_f()     // Catch:{ all -> 0x01d5 }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ all -> 0x01d5 }
        L_0x00e7:
            boolean r8 = r6.hasNext()     // Catch:{ all -> 0x01d5 }
            if (r8 == 0) goto L_0x0102
            java.lang.Object r8 = r6.next()     // Catch:{ all -> 0x01d5 }
            com.android.messaging.datamodel.data.MessagePartData r8 = (com.android.messaging.datamodel.data.MessagePartData) r8     // Catch:{ all -> 0x01d5 }
            boolean r11 = r8.isText()     // Catch:{ all -> 0x01d5 }
            if (r11 != 0) goto L_0x00e7
            android.net.Uri r6 = r8.getContentUri()     // Catch:{ all -> 0x01d5 }
            java.lang.String r8 = r8.getContentType()     // Catch:{ all -> 0x01d5 }
            goto L_0x0104
        L_0x0102:
            r6 = r2
            r8 = r6
        L_0x0104:
            java.lang.CharSequence r6 = com.android.messaging.datamodel.C0944e.m2091b(r10, r12, r6, r8)     // Catch:{ all -> 0x01d5 }
            r1.add(r6)     // Catch:{ all -> 0x01d5 }
        L_0x010b:
            boolean r6 = r3.moveToNext()     // Catch:{ all -> 0x01d5 }
            if (r6 != 0) goto L_0x01d0
            r3.close()
            int r5 = m1240co()
            if (r9 != 0) goto L_0x0121
            int r6 = r1.size()
            if (r6 > r5) goto L_0x0121
            return r2
        L_0x0121:
            android.text.SpannableStringBuilder r5 = new android.text.SpannableStringBuilder
            r5.<init>()
            int r3 = r3.getCount()
            java.lang.String r6 = "\n\n"
            r8 = 21
            if (r3 != r8) goto L_0x0159
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r7 = 2131820747(0x7f1100cb, float:1.9274218E38)
            java.lang.String r7 = r0.getString(r7)
            r3.append(r7)
            r3.append(r6)
            java.lang.String r3 = r3.toString()
            r5.append(r3)
            int r3 = r1.size()
            r7 = 20
            if (r3 <= r7) goto L_0x0159
            int r3 = r1.size()
            int r3 = r3 - r4
            r1.remove(r3)
        L_0x0159:
            int r3 = r1.size()
            int r3 = r3 - r4
        L_0x015e:
            if (r3 < 0) goto L_0x0171
            java.lang.Object r7 = r1.get(r3)
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
            r5.append(r7)
            if (r3 <= 0) goto L_0x016e
            r5.append(r6)
        L_0x016e:
            int r3 = r3 + -1
            goto L_0x015e
        L_0x0171:
            int r1 = r17 + 1
            r3 = 2
            if (r1 <= r3) goto L_0x01af
            android.text.SpannableString r3 = new android.text.SpannableString
            android.content.res.Resources r7 = r0.getResources()
            r8 = 2131689495(0x7f0f0017, float:1.9008007E38)
            java.lang.Object[] r9 = new java.lang.Object[r4]
            java.lang.Integer r10 = java.lang.Integer.valueOf(r1)
            r11 = 0
            r9[r11] = r10
            java.lang.String r1 = r7.getQuantityString(r8, r1, r9)
            r3.<init>(r1)
            android.text.style.ForegroundColorSpan r1 = new android.text.style.ForegroundColorSpan
            android.content.res.Resources r7 = r0.getResources()
            r8 = 2131099874(0x7f0600e2, float:1.7812114E38)
            int r7 = r7.getColor(r8)
            r1.<init>(r7)
            int r7 = r3.length()
            r8 = 33
            r3.setSpan(r1, r11, r7, r8)
            android.text.SpannableStringBuilder r1 = r5.append(r6)
            r1.append(r3)
        L_0x01af:
            androidx.core.app.NotificationCompat$Builder r1 = new androidx.core.app.NotificationCompat$Builder
            r1.<init>(r0, r2)
            androidx.core.app.NotificationCompat$BigTextStyle r0 = new androidx.core.app.NotificationCompat$BigTextStyle
            r0.<init>(r1)
            androidx.core.app.NotificationCompat$BigTextStyle r0 = r0.bigText(r5)
            r1.setStyle(r0)
            androidx.core.app.NotificationCompat$WearableExtender r0 = new androidx.core.app.NotificationCompat$WearableExtender
            r0.<init>()
            r0.setStartScrollBottom(r4)
            r1.extend(r0)
            android.app.Notification r0 = r1.build()
            return r0
        L_0x01d0:
            r6 = 0
            r8 = 21
            goto L_0x004e
        L_0x01d5:
            r0 = move-exception
            goto L_0x01df
        L_0x01d7:
            if (r3 == 0) goto L_0x01dc
            r3.close()
        L_0x01dc:
            return r2
        L_0x01dd:
            r0 = move-exception
            r3 = r2
        L_0x01df:
            if (r3 == 0) goto L_0x01e4
            r3.close()
        L_0x01e4:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.C0769J.m1242f(java.lang.String, int):android.app.Notification");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v0, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v1, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v5, resolved type: android.text.SpannableStringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v4, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x0284  */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x02a4  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x02b3  */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x02c4  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x012b A[Catch:{ all -> 0x020d }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0181 A[Catch:{ all -> 0x020d }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0197 A[Catch:{ all -> 0x020d }] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x01c4 A[Catch:{ all -> 0x020d }] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x01c5 A[Catch:{ all -> 0x020d }] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x01d4 A[Catch:{ all -> 0x020d }] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x01dd A[Catch:{ all -> 0x020d }] */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x0213  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x021c  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x021e  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0237  */
    /* renamed from: me */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.android.messaging.datamodel.C0771L m1243me() {
        /*
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            r0.<init>()
            r1 = 0
            com.android.messaging.f r2 = com.android.messaging.C0967f.get()     // Catch:{ all -> 0x02c0 }
            android.content.Context r2 = r2.getApplicationContext()     // Catch:{ all -> 0x02c0 }
            com.android.messaging.datamodel.h r3 = com.android.messaging.datamodel.C0947h.get()     // Catch:{ all -> 0x02c0 }
            com.android.messaging.datamodel.p r3 = r3.getDatabase()     // Catch:{ all -> 0x02c0 }
            java.lang.String r4 = com.android.messaging.datamodel.data.C0936s.m2023ng()     // Catch:{ all -> 0x02c0 }
            android.database.Cursor r4 = r3.rawQuery(r4, r1)     // Catch:{ all -> 0x02c0 }
            r5 = 2
            java.lang.String r6 = "MessagingAppNotif"
            if (r4 == 0) goto L_0x0210
            boolean r7 = r4.moveToFirst()     // Catch:{ all -> 0x020d }
            if (r7 == 0) goto L_0x0210
            boolean r5 = android.util.Log.isLoggable(r6, r5)     // Catch:{ all -> 0x020d }
            if (r5 == 0) goto L_0x0034
            java.lang.String r5 = "MessageNotificationState: Found unseen message notifications."
            com.android.messaging.util.C1430e.m3628v(r6, r5)     // Catch:{ all -> 0x020d }
        L_0x0034:
            com.android.messaging.datamodel.data.s r5 = new com.android.messaging.datamodel.data.s     // Catch:{ all -> 0x020d }
            r5.<init>()     // Catch:{ all -> 0x020d }
            int r7 = m1240co()     // Catch:{ all -> 0x020d }
            r8 = 0
            r9 = r1
            r10 = r8
            r8 = r9
        L_0x0041:
            r5.mo6538c(r4)     // Catch:{ all -> 0x020d }
            java.lang.String r11 = r5.mo6567zg()     // Catch:{ all -> 0x020d }
            java.lang.String r12 = r5.mo6566yg()     // Catch:{ all -> 0x020d }
            java.lang.String r13 = r5.getText()     // Catch:{ all -> 0x020d }
            java.lang.String r15 = r5.mo6533Ue()     // Catch:{ all -> 0x020d }
            java.lang.String r26 = r5.getMessageId()     // Catch:{ all -> 0x020d }
            boolean r27 = r5.mo6548hg()     // Catch:{ all -> 0x020d }
            if (r27 == 0) goto L_0x0072
            r13 = 101(0x65, float:1.42E-43)
            int r14 = r5.getStatus()     // Catch:{ all -> 0x020d }
            com.android.messaging.util.C1424b.equals((int) r13, (int) r14)     // Catch:{ all -> 0x020d }
            android.content.res.Resources r13 = r2.getResources()     // Catch:{ all -> 0x020d }
            r14 = 2131820843(0x7f11012b, float:1.9274412E38)
            java.lang.String r13 = r13.getString(r14)     // Catch:{ all -> 0x020d }
        L_0x0072:
            java.lang.Object r14 = r0.get(r15)     // Catch:{ all -> 0x020d }
            com.android.messaging.datamodel.E r14 = (com.android.messaging.datamodel.C0764E) r14     // Catch:{ all -> 0x020d }
            if (r14 != 0) goto L_0x00d1
            com.android.messaging.datamodel.data.q r9 = com.android.messaging.datamodel.data.C0934q.m1992j(r3, r15)     // Catch:{ all -> 0x020d }
            java.lang.String r14 = r9.mo6522kf()     // Catch:{ all -> 0x020d }
            int r24 = com.android.messaging.datamodel.C0887c.m1667e(r3, r14)     // Catch:{ all -> 0x020d }
            java.lang.String r28 = r9.getName()     // Catch:{ all -> 0x020d }
            android.net.Uri r14 = r5.mo6526Bg()     // Catch:{ all -> 0x020d }
            r29 = r1
            java.lang.String r1 = r5.mo6567zg()     // Catch:{ all -> 0x020d }
            r30 = r3
            java.lang.String r3 = r5.mo6525Ag()     // Catch:{ all -> 0x020d }
            r16 = r15
            java.lang.String r15 = r5.mo6562ug()     // Catch:{ all -> 0x020d }
            android.net.Uri r22 = com.android.messaging.util.C1426c.m3598a((android.net.Uri) r14, (java.lang.CharSequence) r1, (java.lang.String) r3, (java.lang.String) r15)     // Catch:{ all -> 0x020d }
            com.android.messaging.datamodel.E r1 = new com.android.messaging.datamodel.E     // Catch:{ all -> 0x020d }
            boolean r3 = r9.mo6499Of()     // Catch:{ all -> 0x020d }
            boolean r18 = r9.mo6497Mf()     // Catch:{ all -> 0x020d }
            long r19 = r5.mo6558rg()     // Catch:{ all -> 0x020d }
            java.lang.String r21 = r9.mo6522kf()     // Catch:{ all -> 0x020d }
            android.net.Uri r23 = r5.mo6563vg()     // Catch:{ all -> 0x020d }
            int r25 = r9.mo6507Vf()     // Catch:{ all -> 0x020d }
            r14 = r1
            r9 = r16
            r15 = r9
            r16 = r3
            r17 = r28
            r14.<init>(r15, r16, r17, r18, r19, r21, r22, r23, r24, r25)     // Catch:{ all -> 0x020d }
            r0.put(r9, r1)     // Catch:{ all -> 0x020d }
            r14 = r1
            r1 = r9
            r9 = r28
            goto L_0x00d6
        L_0x00d1:
            r29 = r1
            r30 = r3
            r1 = r15
        L_0x00d6:
            int r3 = r14.f994by     // Catch:{ all -> 0x020d }
            if (r3 >= r7) goto L_0x01f8
            boolean r3 = r14.f988Wx     // Catch:{ all -> 0x020d }
            if (r3 == 0) goto L_0x00ea
            if (r12 != 0) goto L_0x00e7
            r17 = r11
            r18 = r17
            r1 = r29
            goto L_0x0119
        L_0x00e7:
            r1 = r29
            goto L_0x0115
        L_0x00ea:
            boolean r3 = android.text.TextUtils.equals(r8, r1)     // Catch:{ all -> 0x020d }
            if (r3 != 0) goto L_0x00f7
            java.util.HashMap r3 = m1241db(r1)     // Catch:{ all -> 0x020d }
            r8 = r1
            r1 = r3
            goto L_0x00f9
        L_0x00f7:
            r1 = r29
        L_0x00f9:
            if (r1 == 0) goto L_0x010b
            java.lang.Object r3 = r1.get(r12)     // Catch:{ all -> 0x020d }
            java.lang.Integer r3 = (java.lang.Integer) r3     // Catch:{ all -> 0x020d }
            if (r3 == 0) goto L_0x010b
            int r3 = r3.intValue()     // Catch:{ all -> 0x020d }
            r15 = 1
            if (r3 <= r15) goto L_0x010b
            r12 = r11
        L_0x010b:
            if (r11 != 0) goto L_0x010e
            r11 = r9
        L_0x010e:
            if (r12 != 0) goto L_0x0115
            r18 = r9
            r17 = r11
            goto L_0x0119
        L_0x0115:
            r17 = r11
            r18 = r12
        L_0x0119:
            android.content.res.Resources r3 = r2.getResources()     // Catch:{ all -> 0x020d }
            java.lang.String r11 = r5.mo6554mg()     // Catch:{ all -> 0x020d }
            java.lang.String r3 = com.android.messaging.sms.C1029y.m2438b((android.content.res.Resources) r3, (java.lang.String) r11)     // Catch:{ all -> 0x020d }
            boolean r11 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x020d }
            if (r11 != 0) goto L_0x0181
            r11 = 2131820986(0x7f1101ba, float:1.9274702E38)
            java.lang.String r11 = r2.getString(r11)     // Catch:{ all -> 0x020d }
            android.text.SpannableStringBuilder r12 = new android.text.SpannableStringBuilder     // Catch:{ all -> 0x020d }
            r12.<init>()     // Catch:{ all -> 0x020d }
            r15 = 2
            java.lang.Object[] r15 = new java.lang.Object[r15]     // Catch:{ all -> 0x020d }
            r19 = 0
            r15[r19] = r11     // Catch:{ all -> 0x020d }
            r19 = 1
            r15[r19] = r3     // Catch:{ all -> 0x020d }
            r3 = 2131820887(0x7f110157, float:1.9274502E38)
            java.lang.String r3 = r2.getString(r3, r15)     // Catch:{ all -> 0x020d }
            r12.append(r3)     // Catch:{ all -> 0x020d }
            android.text.style.TextAppearanceSpan r3 = new android.text.style.TextAppearanceSpan     // Catch:{ all -> 0x020d }
            r15 = 2131886325(0x7f1200f5, float:1.9407226E38)
            r3.<init>(r2, r15)     // Catch:{ all -> 0x020d }
            int r11 = r11.length()     // Catch:{ all -> 0x020d }
            r15 = 33
            r24 = r1
            r1 = 0
            r12.setSpan(r3, r1, r11, r15)     // Catch:{ all -> 0x020d }
            boolean r1 = android.text.TextUtils.isEmpty(r13)     // Catch:{ all -> 0x020d }
            if (r1 != 0) goto L_0x017e
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x020d }
            r1.<init>()     // Catch:{ all -> 0x020d }
            java.lang.String r3 = "line.separator"
            java.lang.String r3 = java.lang.System.getProperty(r3)     // Catch:{ all -> 0x020d }
            r1.append(r3)     // Catch:{ all -> 0x020d }
            r1.append(r13)     // Catch:{ all -> 0x020d }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x020d }
            r12.append(r1)     // Catch:{ all -> 0x020d }
        L_0x017e:
            r19 = r12
            goto L_0x0185
        L_0x0181:
            r24 = r1
            r19 = r13
        L_0x0185:
            java.util.List r1 = r5.mo6534_f()     // Catch:{ all -> 0x020d }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x020d }
            r3 = 0
            r11 = 0
            r12 = 0
            r13 = 0
        L_0x0191:
            boolean r15 = r1.hasNext()     // Catch:{ all -> 0x020d }
            if (r15 == 0) goto L_0x01c2
            java.lang.Object r15 = r1.next()     // Catch:{ all -> 0x020d }
            com.android.messaging.datamodel.data.MessagePartData r15 = (com.android.messaging.datamodel.data.MessagePartData) r15     // Catch:{ all -> 0x020d }
            boolean r16 = r15.mo6304fh()     // Catch:{ all -> 0x020d }
            if (r16 == 0) goto L_0x01a6
            if (r3 != 0) goto L_0x01a6
            r3 = r15
        L_0x01a6:
            boolean r16 = r15.mo6315ih()     // Catch:{ all -> 0x020d }
            if (r16 == 0) goto L_0x01af
            if (r11 != 0) goto L_0x01af
            r11 = r15
        L_0x01af:
            boolean r16 = r15.mo6314hh()     // Catch:{ all -> 0x020d }
            if (r16 == 0) goto L_0x01b8
            if (r13 != 0) goto L_0x01b8
            r13 = r15
        L_0x01b8:
            boolean r16 = r15.mo6302eh()     // Catch:{ all -> 0x020d }
            if (r16 == 0) goto L_0x0191
            if (r12 != 0) goto L_0x0191
            r12 = r15
            goto L_0x0191
        L_0x01c2:
            if (r3 == 0) goto L_0x01c5
            goto L_0x01d2
        L_0x01c5:
            if (r11 == 0) goto L_0x01c9
            r3 = r11
            goto L_0x01d2
        L_0x01c9:
            if (r12 == 0) goto L_0x01cd
            r3 = r12
            goto L_0x01d2
        L_0x01cd:
            if (r13 == 0) goto L_0x01d1
            r3 = r13
            goto L_0x01d2
        L_0x01d1:
            r3 = 0
        L_0x01d2:
            if (r3 == 0) goto L_0x01dd
            android.net.Uri r1 = r3.getContentUri()     // Catch:{ all -> 0x020d }
            java.lang.String r3 = r3.getContentType()     // Catch:{ all -> 0x020d }
            goto L_0x01df
        L_0x01dd:
            r1 = 0
            r3 = 0
        L_0x01df:
            r20 = r1
            r21 = r3
            java.util.List r1 = r14.f993ay     // Catch:{ all -> 0x020d }
            com.android.messaging.datamodel.F r3 = new com.android.messaging.datamodel.F     // Catch:{ all -> 0x020d }
            boolean r11 = r14.f988Wx     // Catch:{ all -> 0x020d }
            r16 = r3
            r22 = r27
            r23 = r26
            r16.<init>(r17, r18, r19, r20, r21, r22, r23)     // Catch:{ all -> 0x020d }
            r1.add(r3)     // Catch:{ all -> 0x020d }
            r1 = r24
            goto L_0x01fa
        L_0x01f8:
            r1 = r29
        L_0x01fa:
            int r10 = r10 + 1
            int r3 = r14.f994by     // Catch:{ all -> 0x020d }
            int r3 = r3 + 1
            r14.f994by = r3     // Catch:{ all -> 0x020d }
            boolean r3 = r4.moveToNext()     // Catch:{ all -> 0x020d }
            if (r3 != 0) goto L_0x0209
            goto L_0x0211
        L_0x0209:
            r3 = r30
            goto L_0x0041
        L_0x020d:
            r0 = move-exception
            goto L_0x02c2
        L_0x0210:
            r10 = 0
        L_0x0211:
            if (r4 == 0) goto L_0x0216
            r4.close()
        L_0x0216:
            boolean r1 = r0.isEmpty()
            if (r1 == 0) goto L_0x021e
            r0 = 0
            goto L_0x022c
        L_0x021e:
            com.android.messaging.datamodel.D r1 = new com.android.messaging.datamodel.D
            java.util.Collection r0 = r0.values()
            java.util.LinkedList r0 = com.google.common.collect.C1664ia.m4588c(r0)
            r1.<init>(r10, r0)
            r0 = r1
        L_0x022c:
            if (r0 == 0) goto L_0x0284
            java.util.List r1 = r0.f986Vx
            int r1 = r1.size()
            if (r1 != 0) goto L_0x0237
            goto L_0x0284
        L_0x0237:
            java.util.List r1 = r0.f986Vx
            r2 = 0
            java.lang.Object r1 = r1.get(r2)
            com.android.messaging.datamodel.E r1 = (com.android.messaging.datamodel.C0764E) r1
            com.android.messaging.datamodel.H r2 = new com.android.messaging.datamodel.H
            r2.<init>(r0)
            java.util.List r3 = r0.f986Vx
            int r3 = r3.size()
            r4 = 1
            if (r3 <= r4) goto L_0x0254
            com.android.messaging.datamodel.G r1 = new com.android.messaging.datamodel.G
            r1.<init>(r0, r2)
            goto L_0x0282
        L_0x0254:
            android.net.Uri r0 = r1.mAvatarUri
            if (r0 == 0) goto L_0x026a
            java.util.ArrayList r0 = r2.f1017qy
            if (r0 != 0) goto L_0x0263
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>(r4)
            r2.f1017qy = r0
        L_0x0263:
            java.util.ArrayList r0 = r2.f1017qy
            android.net.Uri r3 = r1.mAvatarUri
            r0.add(r3)
        L_0x026a:
            android.net.Uri r0 = r1.f995cy
            if (r0 == 0) goto L_0x0281
            java.util.ArrayList r0 = r2.f1018ry
            if (r0 != 0) goto L_0x027a
            java.util.ArrayList r0 = new java.util.ArrayList
            r3 = 1
            r0.<init>(r3)
            r2.f1018ry = r0
        L_0x027a:
            java.util.ArrayList r0 = r2.f1018ry
            android.net.Uri r1 = r1.f995cy
            r0.add(r1)
        L_0x0281:
            r1 = r2
        L_0x0282:
            r0 = 2
            goto L_0x0291
        L_0x0284:
            r0 = 2
            boolean r1 = android.util.Log.isLoggable(r6, r0)
            if (r1 == 0) goto L_0x0290
            java.lang.String r1 = "MessageNotificationState: No unseen notifications"
            com.android.messaging.util.C1430e.m3628v(r6, r1)
        L_0x0290:
            r1 = 0
        L_0x0291:
            if (r1 == 0) goto L_0x02bf
            boolean r0 = android.util.Log.isLoggable(r6, r0)
            if (r0 == 0) goto L_0x02bf
            java.lang.String r0 = "MessageNotificationState: Notification state created, title = "
            java.lang.StringBuilder r0 = p026b.p027a.p030b.p031a.C0632a.m1011Pa(r0)
            java.lang.String r2 = r1.f1006sy
            if (r2 == 0) goto L_0x02a4
            goto L_0x02a6
        L_0x02a4:
            java.lang.String r2 = r1.mTitle
        L_0x02a6:
            r0.append(r2)
            java.lang.String r2 = ", content = "
            r0.append(r2)
            java.lang.CharSequence r2 = r1.f1007ty
            if (r2 == 0) goto L_0x02b3
            goto L_0x02b5
        L_0x02b3:
            java.lang.CharSequence r2 = r1.mContent
        L_0x02b5:
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            com.android.messaging.util.C1430e.m3628v(r6, r0)
        L_0x02bf:
            return r1
        L_0x02c0:
            r0 = move-exception
            r4 = 0
        L_0x02c2:
            if (r4 == 0) goto L_0x02c7
            r4.close()
        L_0x02c7:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.C0769J.m1243me():com.android.messaging.datamodel.L");
    }

    public int getIcon() {
        return R.drawable.ic_sms_light;
    }

    /* renamed from: ke */
    public long mo5880ke() {
        return this.f1009vy;
    }

    /* renamed from: le */
    public PendingIntent mo5881le() {
        return C1040Ea.get().mo6950a(C0967f.get().getApplicationContext(), this.f1013my, this.f1016py + 2);
    }

    /* access modifiers changed from: protected */
    /* renamed from: ne */
    public CharSequence mo5882ne() {
        String str = this.f1006sy;
        if (str == null) {
            str = this.mTitle;
        }
        CharSequence charSequence = this.f1007ty;
        if (charSequence == null) {
            charSequence = this.mContent;
        }
        return C0944e.m2080a(str, charSequence, (Uri) null, (String) null);
    }
}
