package com.android.messaging.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import com.android.messaging.util.C1430e;
import p026b.p027a.p030b.p031a.C0632a;

public abstract class BaseWidgetProvider extends AppWidgetProvider {
    /* renamed from: jb */
    private static int m3878jb(int i) {
        return (i + 30) / 70;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Ma */
    public abstract int mo8240Ma();

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo8241a(Context context, int i);

    /* access modifiers changed from: protected */
    public abstract String getAction();

    /* access modifiers changed from: protected */
    /* renamed from: k */
    public void mo8243k(int i) {
    }

    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int i, Bundle bundle) {
        if (Log.isLoggable("MessagingAppWidget", 2)) {
            C1430e.m3628v("MessagingAppWidget", "BaseWidgetProvider.getWidgetSize");
        }
        Bundle appWidgetOptions = appWidgetManager.getAppWidgetOptions(i);
        int i2 = appWidgetOptions.getInt("appWidgetMinWidth");
        int jb = m3878jb(appWidgetOptions.getInt("appWidgetMinHeight"));
        int jb2 = m3878jb(i2);
        if (Log.isLoggable("MessagingAppWidget", 2)) {
            C1430e.m3628v("MessagingAppWidget", "BaseWidgetProvider.getWidgetSize row: " + jb + " columns: " + jb2);
        }
        int i3 = jb == 1 ? 1 : jb2 > 3 ? 0 : 2;
        int i4 = appWidgetOptions.getInt("widgetSizeKey");
        if (i4 != i3) {
            appWidgetOptions.putInt("widgetSizeKey", i3);
            appWidgetManager.updateAppWidgetOptions(i, appWidgetOptions);
            appWidgetManager.notifyAppWidgetViewDataChanged(i, mo8240Ma());
            if (Log.isLoggable("MessagingAppWidget", 2)) {
                C1430e.m3628v("MessagingAppWidget", "BaseWidgetProvider.getWidgetSize old size: " + i4 + " new size saved: " + i3);
            }
        }
        if (Log.isLoggable("MessagingAppWidget", 2)) {
            C1430e.m3628v("MessagingAppWidget", "BaseWidgetProvider.onAppWidgetOptionsChanged new size: " + i3);
        }
        super.onAppWidgetOptionsChanged(context, appWidgetManager, i, bundle);
    }

    public void onDeleted(Context context, int[] iArr) {
        super.onDeleted(context, iArr);
        if (Log.isLoggable("MessagingAppWidget", 2)) {
            C1430e.m3628v("MessagingAppWidget", "BaseWidgetProvider.onDeleted");
        }
        for (int k : iArr) {
            mo8243k(k);
        }
    }

    public void onEnabled(Context context) {
        super.onEnabled(context);
        context.getApplicationContext().registerReceiver(this, new IntentFilter(getAction()));
    }

    public void onReceive(Context context, Intent intent) {
        if (Log.isLoggable("MessagingAppWidget", 2)) {
            C1430e.m3628v("MessagingAppWidget", "onReceive intent: " + intent + " for " + getClass());
        }
        if (getAction().equals(intent.getAction())) {
            AppWidgetManager instance = AppWidgetManager.getInstance(context);
            int[] appWidgetIds = instance.getAppWidgetIds(new ComponentName(context, getClass()));
            if (appWidgetIds.length > 0) {
                if (Log.isLoggable("MessagingAppWidget", 2)) {
                    StringBuilder Pa = C0632a.m1011Pa("onReceive notifyAppWidgetViewDataChanged listId: ");
                    Pa.append(mo8240Ma());
                    Pa.append(" first widgetId: ");
                    Pa.append(appWidgetIds[0]);
                    C1430e.m3628v("MessagingAppWidget", Pa.toString());
                }
                instance.notifyAppWidgetViewDataChanged(appWidgetIds, mo8240Ma());
                return;
            }
            return;
        }
        super.onReceive(context, intent);
    }

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] iArr) {
        super.onUpdate(context, appWidgetManager, iArr);
        for (int a : iArr) {
            mo8241a(context, a);
        }
    }
}
