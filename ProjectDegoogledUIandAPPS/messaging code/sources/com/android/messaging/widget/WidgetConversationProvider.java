package com.android.messaging.widget;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.android.messaging.R;
import com.android.messaging.p041ui.WidgetPickConversationActivity;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1464na;
import com.android.messaging.util.C1486ya;

public class WidgetConversationProvider extends BaseWidgetProvider {
    /* renamed from: a */
    public static void m3886a(Context context, String str) {
        if (Log.isLoggable("MessagingAppWidget", 2)) {
            C1430e.m3628v("MessagingAppWidget", "notifyConversationDeleted convId: " + str);
        }
        for (int i : AppWidgetManager.getInstance(context).getAppWidgetIds(new ComponentName(context, WidgetConversationProvider.class))) {
            String o = WidgetPickConversationActivity.m2668o(i);
            if (o == null || o.equals(str)) {
                if (o != null) {
                    WidgetPickConversationActivity.m2667n(i);
                }
                m3887b(context, i);
            }
        }
    }

    /* renamed from: b */
    public static void m3888b(Context context, String str) {
        if (Log.isLoggable("MessagingAppWidget", 2)) {
            C1430e.m3628v("MessagingAppWidget", "notifyConversationRenamed convId: " + str);
        }
        for (int i : AppWidgetManager.getInstance(context).getAppWidgetIds(new ComponentName(context, WidgetConversationProvider.class))) {
            String o = WidgetPickConversationActivity.m2668o(i);
            if (o != null && o.equals(str)) {
                m3887b(context, i);
            }
        }
    }

    /* renamed from: c */
    public static void m3889c(Context context, String str) {
        if (Log.isLoggable("MessagingAppWidget", 2)) {
            C1430e.m3628v("MessagingAppWidget", "notifyMessagesChanged");
        }
        Intent intent = new Intent("com.android.Bugle.intent.action.ACTION_NOTIFY_MESSAGES_CHANGED");
        intent.putExtra("conversation_id", str);
        context.sendBroadcast(intent);
    }

    /* renamed from: l */
    public static boolean m3890l(int i) {
        return !TextUtils.isEmpty(WidgetPickConversationActivity.m2668o(i));
    }

    /* access modifiers changed from: protected */
    /* renamed from: Ma */
    public int mo8240Ma() {
        return R.id.message_list;
    }

    /* access modifiers changed from: protected */
    public String getAction() {
        return "com.android.Bugle.intent.action.ACTION_NOTIFY_MESSAGES_CHANGED";
    }

    /* access modifiers changed from: protected */
    /* renamed from: k */
    public void mo8243k(int i) {
        WidgetPickConversationActivity.m2667n(i);
    }

    public void onReceive(Context context, Intent intent) {
        if (Log.isLoggable("MessagingAppWidget", 2)) {
            C1430e.m3628v("MessagingAppWidget", "WidgetConversationProvider onReceive intent: " + intent);
        }
        if (getAction().equals(intent.getAction())) {
            AppWidgetManager instance = AppWidgetManager.getInstance(context);
            int[] appWidgetIds = instance.getAppWidgetIds(new ComponentName(context, WidgetConversationProvider.class));
            if (appWidgetIds.length != 0) {
                String string = intent.getExtras().getString("conversation_id");
                for (int i : appWidgetIds) {
                    String o = WidgetPickConversationActivity.m2668o(i);
                    if (string == null || TextUtils.equals(string, o)) {
                        instance.notifyAppWidgetViewDataChanged(i, mo8240Ma());
                    }
                }
            } else if (Log.isLoggable("MessagingAppWidget", 2)) {
                C1430e.m3628v("MessagingAppWidget", "WidgetConversationProvider onReceive no widget ids");
            }
        } else {
            super.onReceive(context, intent);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: com.android.messaging.datamodel.data.q} */
    /* JADX WARNING: type inference failed for: r3v2 */
    /* JADX WARNING: type inference failed for: r3v12, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r3v13 */
    /* JADX WARNING: type inference failed for: r3v14 */
    /* JADX WARNING: type inference failed for: r3v16 */
    /* JADX WARNING: type inference failed for: r3v17 */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00d4, code lost:
        if (r5 != null) goto L_0x00d6;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x013a  */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m3887b(android.content.Context r21, int r22) {
        /*
            r0 = r21
            r1 = r22
            r2 = 2
            java.lang.String r3 = "MessagingAppWidget"
            boolean r4 = android.util.Log.isLoggable(r3, r2)
            java.lang.String r5 = "WidgetConversationProvider.rebuildWidget appWidgetId: "
            if (r4 == 0) goto L_0x0021
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r5)
            r4.append(r1)
            java.lang.String r4 = r4.toString()
            com.android.messaging.util.C1430e.m3628v(r3, r4)
        L_0x0021:
            android.widget.RemoteViews r4 = new android.widget.RemoteViews
            java.lang.String r6 = r21.getPackageName()
            r7 = 2131558543(0x7f0d008f, float:1.8742405E38)
            r4.<init>(r6, r7)
            com.android.messaging.ui.Ea r6 = com.android.messaging.p041ui.C1040Ea.get()
            boolean r7 = m3890l(r22)
            r8 = 2131362215(0x7f0a01a7, float:1.8344204E38)
            r9 = 2131362032(0x7f0a00f0, float:1.8343833E38)
            r10 = 2131362212(0x7f0a01a4, float:1.8344198E38)
            r11 = 2131362216(0x7f0a01a8, float:1.8344206E38)
            r12 = 8
            r13 = 2131362053(0x7f0a0105, float:1.8343876E38)
            r14 = 0
            if (r7 != 0) goto L_0x0082
            r4.setViewVisibility(r11, r12)
            r4.setViewVisibility(r13, r12)
            r4.setViewVisibility(r9, r14)
            r4.setViewVisibility(r10, r14)
            android.app.PendingIntent r7 = r6.mo6974e((android.content.Context) r0, (int) r1)
            r4.setOnClickPendingIntent(r10, r7)
            android.app.PendingIntent r6 = r6.mo6984u(r0)
            r4.setOnClickPendingIntent(r8, r6)
            boolean r2 = android.util.Log.isLoggable(r3, r2)
            if (r2 == 0) goto L_0x012f
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r5)
            r2.append(r1)
            java.lang.String r5 = " going into configure state"
            r2.append(r5)
            java.lang.String r2 = r2.toString()
            com.android.messaging.util.C1430e.m3628v(r3, r2)
            goto L_0x012f
        L_0x0082:
            r4.setViewVisibility(r11, r14)
            r4.setViewVisibility(r13, r14)
            r4.setViewVisibility(r9, r12)
            r4.setViewVisibility(r10, r12)
            java.lang.String r2 = com.android.messaging.p041ui.WidgetPickConversationActivity.m2668o(r22)
            android.os.Looper r3 = android.os.Looper.myLooper()
            android.os.Looper r5 = android.os.Looper.getMainLooper()
            r7 = 1
            if (r3 != r5) goto L_0x009e
            r14 = r7
        L_0x009e:
            r3 = 0
            if (r14 == 0) goto L_0x00a2
            goto L_0x00d9
        L_0x00a2:
            boolean r5 = android.text.TextUtils.isEmpty(r2)
            if (r5 == 0) goto L_0x00a9
            goto L_0x00d9
        L_0x00a9:
            android.net.Uri r16 = com.android.messaging.datamodel.MessagingContentProvider.m1269n(r2)
            android.content.ContentResolver r15 = r21.getContentResolver()     // Catch:{ all -> 0x0137 }
            java.lang.String[] r17 = com.android.messaging.datamodel.data.C0934q.f1249Wu     // Catch:{ all -> 0x0137 }
            r18 = 0
            r19 = 0
            r20 = 0
            android.database.Cursor r5 = r15.query(r16, r17, r18, r19, r20)     // Catch:{ all -> 0x0137 }
            if (r5 == 0) goto L_0x00d4
            int r9 = r5.getCount()     // Catch:{ all -> 0x00d1 }
            if (r9 <= 0) goto L_0x00d4
            com.android.messaging.datamodel.data.q r3 = new com.android.messaging.datamodel.data.q     // Catch:{ all -> 0x00d1 }
            r3.<init>()     // Catch:{ all -> 0x00d1 }
            r5.moveToFirst()     // Catch:{ all -> 0x00d1 }
            r3.mo6513c(r5)     // Catch:{ all -> 0x00d1 }
            goto L_0x00d6
        L_0x00d1:
            r0 = move-exception
            r3 = r5
            goto L_0x0138
        L_0x00d4:
            if (r5 == 0) goto L_0x00d9
        L_0x00d6:
            r5.close()
        L_0x00d9:
            android.content.Intent r5 = new android.content.Intent
            java.lang.Class<com.android.messaging.widget.WidgetConversationService> r9 = com.android.messaging.widget.WidgetConversationService.class
            r5.<init>(r0, r9)
            java.lang.String r9 = "appWidgetId"
            r5.putExtra(r9, r1)
            java.lang.String r9 = "conversation_id"
            r5.putExtra(r9, r2)
            java.lang.String r7 = r5.toUri(r7)
            android.net.Uri r7 = android.net.Uri.parse(r7)
            r5.setData(r7)
            r4.setRemoteAdapter(r1, r13, r5)
            if (r3 == 0) goto L_0x00ff
            java.lang.String r3 = r3.getName()
            goto L_0x0106
        L_0x00ff:
            r3 = 2131820616(0x7f110048, float:1.9273952E38)
            java.lang.String r3 = r0.getString(r3)
        L_0x0106:
            r4.setTextViewText(r11, r3)
            android.app.PendingIntent r3 = r6.mo6984u(r0)
            r5 = 2131362214(0x7f0a01a6, float:1.8344202E38)
            r4.setOnClickPendingIntent(r5, r3)
            r3 = 987(0x3db, float:1.383E-42)
            android.app.PendingIntent r3 = r6.mo6969c((android.content.Context) r0, (java.lang.String) r2, (int) r3)
            r4.setOnClickPendingIntent(r8, r3)
            r3 = 1985(0x7c1, float:2.782E-42)
            android.app.PendingIntent r2 = r6.mo6969c((android.content.Context) r0, (java.lang.String) r2, (int) r3)
            r4.setPendingIntentTemplate(r13, r2)
            if (r14 == 0) goto L_0x012f
            com.android.messaging.widget.d r2 = new com.android.messaging.widget.d
            r2.<init>(r0, r1)
            com.android.messaging.util.C1478ua.m3823a((java.lang.Runnable) r2)
        L_0x012f:
            android.appwidget.AppWidgetManager r0 = android.appwidget.AppWidgetManager.getInstance(r21)
            r0.updateAppWidget(r1, r4)
            return
        L_0x0137:
            r0 = move-exception
        L_0x0138:
            if (r3 == 0) goto L_0x013d
            r3.close()
        L_0x013d:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.widget.WidgetConversationProvider.m3887b(android.content.Context, int):void");
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo8241a(Context context, int i) {
        if (Log.isLoggable("MessagingAppWidget", 2)) {
            C1430e.m3628v("MessagingAppWidget", "updateWidget appWidgetId: " + i);
        }
        if (C1464na.m3752Sj()) {
            m3887b(context, i);
        } else {
            AppWidgetManager.getInstance(context).updateAppWidget(i, C1486ya.m3845C(context));
        }
    }
}
