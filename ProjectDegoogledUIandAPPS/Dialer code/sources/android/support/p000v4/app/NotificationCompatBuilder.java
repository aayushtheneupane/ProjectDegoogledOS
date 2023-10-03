package android.support.p000v4.app;

import android.app.Notification;
import android.app.RemoteInput;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: android.support.v4.app.NotificationCompatBuilder */
class NotificationCompatBuilder implements NotificationBuilderWithBuilderAccessor {
    private final List<Bundle> mActionExtrasList = new ArrayList();
    private RemoteViews mBigContentView;
    private final Notification.Builder mBuilder;
    private final NotificationCompat$Builder mBuilderCompat;
    private RemoteViews mContentView;
    private final Bundle mExtras = new Bundle();
    private int mGroupAlertBehavior;
    private RemoteViews mHeadsUpContentView;

    NotificationCompatBuilder(NotificationCompat$Builder notificationCompat$Builder) {
        Bundle bundle;
        this.mBuilderCompat = notificationCompat$Builder;
        int i = Build.VERSION.SDK_INT;
        this.mBuilder = new Notification.Builder(notificationCompat$Builder.mContext, notificationCompat$Builder.mChannelId);
        Notification notification = notificationCompat$Builder.mNotification;
        this.mBuilder.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, notificationCompat$Builder.mTickerView).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(notificationCompat$Builder.mContentTitle).setContentText(notificationCompat$Builder.mContentText).setContentInfo(notificationCompat$Builder.mContentInfo).setContentIntent(notificationCompat$Builder.mContentIntent).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(notificationCompat$Builder.mFullScreenIntent, (notification.flags & 128) == 0 ? false : true).setLargeIcon(notificationCompat$Builder.mLargeIcon).setNumber(notificationCompat$Builder.mNumber).setProgress(notificationCompat$Builder.mProgressMax, notificationCompat$Builder.mProgress, notificationCompat$Builder.mProgressIndeterminate);
        int i2 = Build.VERSION.SDK_INT;
        this.mBuilder.setSubText(notificationCompat$Builder.mSubText).setUsesChronometer(notificationCompat$Builder.mUseChronometer).setPriority(notificationCompat$Builder.mPriority);
        Iterator<NotificationCompat$Action> it = notificationCompat$Builder.mActions.iterator();
        while (true) {
            RemoteInput[] remoteInputArr = null;
            if (it.hasNext()) {
                NotificationCompat$Action next = it.next();
                int i3 = Build.VERSION.SDK_INT;
                Notification.Action.Builder builder = new Notification.Action.Builder(next.icon, next.title, next.actionIntent);
                if (next.getRemoteInputs() != null) {
                    RemoteInput[] remoteInputs = next.getRemoteInputs();
                    if (remoteInputs != null) {
                        RemoteInput[] remoteInputArr2 = new RemoteInput[remoteInputs.length];
                        if (remoteInputs.length <= 0) {
                            remoteInputArr = remoteInputArr2;
                        } else {
                            RemoteInput remoteInput = remoteInputs[0];
                            throw null;
                        }
                    }
                    for (RemoteInput addRemoteInput : remoteInputArr) {
                        builder.addRemoteInput(addRemoteInput);
                    }
                }
                Bundle bundle2 = next.mExtras;
                if (bundle2 != null) {
                    bundle = new Bundle(bundle2);
                } else {
                    bundle = new Bundle();
                }
                bundle.putBoolean("android.support.allowGeneratedReplies", next.getAllowGeneratedReplies());
                int i4 = Build.VERSION.SDK_INT;
                builder.setAllowGeneratedReplies(next.getAllowGeneratedReplies());
                bundle.putInt("android.support.action.semanticAction", next.getSemanticAction());
                int i5 = Build.VERSION.SDK_INT;
                builder.setSemanticAction(next.getSemanticAction());
                bundle.putBoolean("android.support.action.showsUserInterface", next.mShowsUserInterface);
                builder.addExtras(bundle);
                this.mBuilder.addAction(builder.build());
            } else {
                Bundle bundle3 = notificationCompat$Builder.mExtras;
                if (bundle3 != null) {
                    this.mExtras.putAll(bundle3);
                }
                int i6 = Build.VERSION.SDK_INT;
                this.mContentView = notificationCompat$Builder.mContentView;
                this.mBigContentView = notificationCompat$Builder.mBigContentView;
                this.mBuilder.setShowWhen(notificationCompat$Builder.mShowWhen);
                int i7 = Build.VERSION.SDK_INT;
                this.mBuilder.setLocalOnly(notificationCompat$Builder.mLocalOnly).setGroup(notificationCompat$Builder.mGroupKey).setGroupSummary(notificationCompat$Builder.mGroupSummary).setSortKey(notificationCompat$Builder.mSortKey);
                this.mGroupAlertBehavior = notificationCompat$Builder.mGroupAlertBehavior;
                int i8 = Build.VERSION.SDK_INT;
                this.mBuilder.setCategory(notificationCompat$Builder.mCategory).setColor(notificationCompat$Builder.mColor).setVisibility(notificationCompat$Builder.mVisibility).setPublicVersion(notificationCompat$Builder.mPublicVersion).setSound(notification.sound, notification.audioAttributes);
                Iterator<String> it2 = notificationCompat$Builder.mPeople.iterator();
                while (it2.hasNext()) {
                    this.mBuilder.addPerson(it2.next());
                }
                this.mHeadsUpContentView = notificationCompat$Builder.mHeadsUpContentView;
                if (notificationCompat$Builder.mInvisibleActions.size() > 0) {
                    if (notificationCompat$Builder.mExtras == null) {
                        notificationCompat$Builder.mExtras = new Bundle();
                    }
                    Bundle bundle4 = notificationCompat$Builder.mExtras.getBundle("android.car.EXTENSIONS");
                    bundle4 = bundle4 == null ? new Bundle() : bundle4;
                    Bundle bundle5 = new Bundle();
                    for (int i9 = 0; i9 < notificationCompat$Builder.mInvisibleActions.size(); i9++) {
                        bundle5.putBundle(Integer.toString(i9), NotificationCompatJellybean.getBundleForAction(notificationCompat$Builder.mInvisibleActions.get(i9)));
                    }
                    bundle4.putBundle("invisible_actions", bundle5);
                    if (notificationCompat$Builder.mExtras == null) {
                        notificationCompat$Builder.mExtras = new Bundle();
                    }
                    notificationCompat$Builder.mExtras.putBundle("android.car.EXTENSIONS", bundle4);
                    this.mExtras.putBundle("android.car.EXTENSIONS", bundle4);
                }
                int i10 = Build.VERSION.SDK_INT;
                this.mBuilder.setExtras(notificationCompat$Builder.mExtras).setRemoteInputHistory(notificationCompat$Builder.mRemoteInputHistory);
                RemoteViews remoteViews = notificationCompat$Builder.mContentView;
                if (remoteViews != null) {
                    this.mBuilder.setCustomContentView(remoteViews);
                }
                RemoteViews remoteViews2 = notificationCompat$Builder.mBigContentView;
                if (remoteViews2 != null) {
                    this.mBuilder.setCustomBigContentView(remoteViews2);
                }
                RemoteViews remoteViews3 = notificationCompat$Builder.mHeadsUpContentView;
                if (remoteViews3 != null) {
                    this.mBuilder.setCustomHeadsUpContentView(remoteViews3);
                }
                int i11 = Build.VERSION.SDK_INT;
                this.mBuilder.setBadgeIconType(notificationCompat$Builder.mBadgeIcon).setShortcutId(notificationCompat$Builder.mShortcutId).setTimeoutAfter(notificationCompat$Builder.mTimeout).setGroupAlertBehavior(notificationCompat$Builder.mGroupAlertBehavior);
                if (notificationCompat$Builder.mColorizedSet) {
                    this.mBuilder.setColorized(notificationCompat$Builder.mColorized);
                }
                if (!TextUtils.isEmpty(notificationCompat$Builder.mChannelId)) {
                    this.mBuilder.setSound((Uri) null).setDefaults(0).setLights(0, 0, 0).setVibrate((long[]) null);
                    return;
                }
                return;
            }
        }
    }

    public Notification build() {
        NotificationCompat$Style notificationCompat$Style = this.mBuilderCompat.mStyle;
        if (notificationCompat$Style != null) {
            notificationCompat$Style.apply(this);
        }
        int i = Build.VERSION.SDK_INT;
        Notification build = this.mBuilder.build();
        RemoteViews remoteViews = this.mBuilderCompat.mContentView;
        if (remoteViews != null) {
            build.contentView = remoteViews;
        }
        int i2 = Build.VERSION.SDK_INT;
        int i3 = Build.VERSION.SDK_INT;
        if (notificationCompat$Style != null) {
            this.mBuilderCompat.mStyle.makeHeadsUpContentView(this);
        }
        int i4 = Build.VERSION.SDK_INT;
        if (notificationCompat$Style != null) {
            Bundle bundle = build.extras;
        }
        return build;
    }

    public Notification.Builder getBuilder() {
        return this.mBuilder;
    }
}
