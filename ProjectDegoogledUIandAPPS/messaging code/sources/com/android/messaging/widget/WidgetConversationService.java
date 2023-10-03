package com.android.messaging.widget;

import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViewsService;
import com.android.messaging.util.C1430e;

public class WidgetConversationService extends RemoteViewsService {
    public RemoteViewsService.RemoteViewsFactory onGetViewFactory(Intent intent) {
        if (Log.isLoggable("MessagingAppWidget", 2)) {
            C1430e.m3628v("MessagingAppWidget", "onGetViewFactory intent: " + intent);
        }
        return new C1493e(getApplicationContext(), intent);
    }
}
