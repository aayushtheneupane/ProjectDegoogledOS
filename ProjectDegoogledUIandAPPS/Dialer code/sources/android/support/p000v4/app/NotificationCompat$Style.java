package android.support.p000v4.app;

import android.widget.RemoteViews;

/* renamed from: android.support.v4.app.NotificationCompat$Style */
public abstract class NotificationCompat$Style {
    CharSequence mBigContentTitle;
    protected NotificationCompat$Builder mBuilder;
    CharSequence mSummaryText;
    boolean mSummaryTextSet = false;

    public abstract void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor);

    public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
        return null;
    }
}
