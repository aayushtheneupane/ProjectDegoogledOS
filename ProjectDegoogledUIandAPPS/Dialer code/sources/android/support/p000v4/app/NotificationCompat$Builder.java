package android.support.p000v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.RemoteViews;
import java.util.ArrayList;

/* renamed from: android.support.v4.app.NotificationCompat$Builder */
public class NotificationCompat$Builder {
    public ArrayList<NotificationCompat$Action> mActions = new ArrayList<>();
    int mBadgeIcon = 0;
    RemoteViews mBigContentView;
    String mCategory;
    String mChannelId;
    int mColor = 0;
    boolean mColorized;
    boolean mColorizedSet;
    CharSequence mContentInfo;
    PendingIntent mContentIntent;
    CharSequence mContentText;
    CharSequence mContentTitle;
    RemoteViews mContentView;
    public Context mContext;
    Bundle mExtras;
    PendingIntent mFullScreenIntent;
    int mGroupAlertBehavior = 0;
    String mGroupKey;
    boolean mGroupSummary;
    RemoteViews mHeadsUpContentView;
    ArrayList<NotificationCompat$Action> mInvisibleActions = new ArrayList<>();
    Bitmap mLargeIcon;
    boolean mLocalOnly = false;
    Notification mNotification = new Notification();
    int mNumber;
    @Deprecated
    public ArrayList<String> mPeople;
    int mPriority;
    int mProgress;
    boolean mProgressIndeterminate;
    int mProgressMax;
    Notification mPublicVersion;
    CharSequence[] mRemoteInputHistory;
    String mShortcutId;
    boolean mShowWhen = true;
    String mSortKey;
    NotificationCompat$Style mStyle;
    CharSequence mSubText;
    RemoteViews mTickerView;
    long mTimeout;
    boolean mUseChronometer;
    int mVisibility = 0;

    @Deprecated
    public NotificationCompat$Builder(Context context) {
        this.mContext = context;
        this.mChannelId = null;
        this.mNotification.when = System.currentTimeMillis();
        this.mNotification.audioStreamType = -1;
        this.mPriority = 0;
        this.mPeople = new ArrayList<>();
    }

    protected static CharSequence limitCharSequenceLength(CharSequence charSequence) {
        return (charSequence != null && charSequence.length() > 5120) ? charSequence.subSequence(0, 5120) : charSequence;
    }

    private void setFlag(int i, boolean z) {
        if (z) {
            Notification notification = this.mNotification;
            notification.flags = i | notification.flags;
            return;
        }
        Notification notification2 = this.mNotification;
        notification2.flags = (~i) & notification2.flags;
    }

    public Notification build() {
        return new NotificationCompatBuilder(this).build();
    }

    public NotificationCompat$Builder setAutoCancel(boolean z) {
        setFlag(16, z);
        return this;
    }

    public NotificationCompat$Builder setChannelId(String str) {
        this.mChannelId = str;
        return this;
    }

    public NotificationCompat$Builder setColor(int i) {
        this.mColor = i;
        return this;
    }

    public NotificationCompat$Builder setContentIntent(PendingIntent pendingIntent) {
        this.mContentIntent = pendingIntent;
        return this;
    }

    public NotificationCompat$Builder setContentText(CharSequence charSequence) {
        this.mContentText = limitCharSequenceLength(charSequence);
        return this;
    }

    public NotificationCompat$Builder setContentTitle(CharSequence charSequence) {
        this.mContentTitle = limitCharSequenceLength(charSequence);
        return this;
    }

    public NotificationCompat$Builder setDefaults(int i) {
        Notification notification = this.mNotification;
        notification.defaults = i;
        if ((i & 4) != 0) {
            notification.flags |= 1;
        }
        return this;
    }

    public NotificationCompat$Builder setDeleteIntent(PendingIntent pendingIntent) {
        this.mNotification.deleteIntent = pendingIntent;
        return this;
    }

    public NotificationCompat$Builder setGroup(String str) {
        this.mGroupKey = str;
        return this;
    }

    public NotificationCompat$Builder setGroupAlertBehavior(int i) {
        this.mGroupAlertBehavior = i;
        return this;
    }

    public NotificationCompat$Builder setGroupSummary(boolean z) {
        this.mGroupSummary = z;
        return this;
    }

    public NotificationCompat$Builder setLargeIcon(Bitmap bitmap) {
        if (bitmap != null) {
            int i = Build.VERSION.SDK_INT;
        }
        this.mLargeIcon = bitmap;
        return this;
    }

    public NotificationCompat$Builder setOnlyAlertOnce(boolean z) {
        setFlag(8, z);
        return this;
    }

    public NotificationCompat$Builder setSmallIcon(int i) {
        this.mNotification.icon = i;
        return this;
    }

    public NotificationCompat$Builder setSound(Uri uri) {
        Notification notification = this.mNotification;
        notification.sound = uri;
        notification.audioStreamType = -1;
        int i = Build.VERSION.SDK_INT;
        notification.audioAttributes = new AudioAttributes.Builder().setContentType(4).setUsage(5).build();
        return this;
    }

    public NotificationCompat$Builder setStyle(NotificationCompat$Style notificationCompat$Style) {
        if (this.mStyle != notificationCompat$Style) {
            this.mStyle = notificationCompat$Style;
            NotificationCompat$Style notificationCompat$Style2 = this.mStyle;
            if (!(notificationCompat$Style2 == null || notificationCompat$Style2.mBuilder == this)) {
                notificationCompat$Style2.mBuilder = this;
                NotificationCompat$Builder notificationCompat$Builder = notificationCompat$Style2.mBuilder;
                if (notificationCompat$Builder != null) {
                    notificationCompat$Builder.setStyle(notificationCompat$Style2);
                }
            }
        }
        return this;
    }

    public NotificationCompat$Builder setWhen(long j) {
        this.mNotification.when = j;
        return this;
    }
}
