package android.support.p000v4.app;

import android.app.Notification;
import android.os.Build;

/* renamed from: android.support.v4.app.NotificationCompat$BigTextStyle */
public class NotificationCompat$BigTextStyle extends NotificationCompat$Style {
    private CharSequence mBigText;

    public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
        int i = Build.VERSION.SDK_INT;
        Notification.BigTextStyle bigText = new Notification.BigTextStyle(((NotificationCompatBuilder) notificationBuilderWithBuilderAccessor).getBuilder()).setBigContentTitle(this.mBigContentTitle).bigText(this.mBigText);
        if (this.mSummaryTextSet) {
            bigText.setSummaryText(this.mSummaryText);
        }
    }

    public NotificationCompat$BigTextStyle bigText(CharSequence charSequence) {
        this.mBigText = NotificationCompat$Builder.limitCharSequenceLength(charSequence);
        return this;
    }
}
