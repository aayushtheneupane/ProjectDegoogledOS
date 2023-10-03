package com.android.messaging.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;
import com.android.messaging.R;
import com.android.messaging.p041ui.C1040Ea;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1464na;
import com.android.messaging.util.C1478ua;
import com.android.messaging.util.C1486ya;

public class BugleWidgetProvider extends BaseWidgetProvider {
    /* renamed from: b */
    public static void m3883b(Context context, int i) {
        if (Log.isLoggable("MessagingAppWidget", 2)) {
            C1430e.m3628v("MessagingAppWidget", "BugleWidgetProvider.rebuildWidget appWidgetId: " + i);
        }
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_conversation_list);
        Intent intent = new Intent(context, WidgetConversationListService.class);
        intent.putExtra("appWidgetId", i);
        intent.setData(Uri.parse(intent.toUri(1)));
        remoteViews.setRemoteAdapter(i, R.id.conversation_list, intent);
        remoteViews.setTextViewText(R.id.widget_label, context.getString(R.string.app_name));
        remoteViews.setOnClickPendingIntent(R.id.widget_header, C1040Ea.get().mo6984u(context));
        remoteViews.setOnClickPendingIntent(R.id.widget_compose, C1040Ea.get().mo6969c(context, (String) null, 986));
        remoteViews.setPendingIntentTemplate(R.id.conversation_list, C1040Ea.get().mo6969c(context, (String) null, 987));
        AppWidgetManager.getInstance(context).updateAppWidget(i, remoteViews);
    }

    /* access modifiers changed from: protected */
    /* renamed from: Ma */
    public int mo8240Ma() {
        return R.id.conversation_list;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo8241a(Context context, int i) {
        if (C1464na.m3752Sj()) {
            C1478ua.m3823a((Runnable) new C1490b(this, context, i));
        } else {
            AppWidgetManager.getInstance(context).updateAppWidget(i, C1486ya.m3845C(context));
        }
    }

    /* access modifiers changed from: protected */
    public String getAction() {
        return "com.android.Bugle.intent.action.ACTION_NOTIFY_CONVERSATIONS_CHANGED";
    }

    /* renamed from: a */
    public static void m3882a(Context context) {
        if (Log.isLoggable("MessagingAppWidget", 2)) {
            C1430e.m3628v("MessagingAppWidget", "notifyConversationListChanged");
        }
        context.sendBroadcast(new Intent("com.android.Bugle.intent.action.ACTION_NOTIFY_CONVERSATIONS_CHANGED"));
    }
}
