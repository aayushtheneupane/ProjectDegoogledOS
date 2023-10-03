package com.android.systemui.bubbles;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import com.android.internal.annotations.VisibleForTesting;
import com.android.systemui.C1779R$layout;
import com.android.systemui.C1784R$string;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Objects;

class Bubble {
    private String mAppName;
    private NotificationEntry mEntry;
    private BubbleExpandedView mExpandedView;
    private final String mGroupId;
    private BubbleView mIconView;
    private boolean mInflated;
    private final String mKey;
    private long mLastAccessed;
    private long mLastUpdated;
    private boolean mShowBubbleUpdateDot = true;
    private boolean mShowInShadeWhenBubble = true;
    private boolean mSuppressFlyout;
    private Drawable mUserBadgedAppIcon;

    public static String groupId(NotificationEntry notificationEntry) {
        UserHandle user = notificationEntry.notification.getUser();
        return user.getIdentifier() + "|" + notificationEntry.notification.getPackageName();
    }

    @VisibleForTesting(visibility = VisibleForTesting.Visibility.PRIVATE)
    Bubble(Context context, NotificationEntry notificationEntry) {
        this.mEntry = notificationEntry;
        this.mKey = notificationEntry.key;
        this.mLastUpdated = notificationEntry.notification.getPostTime();
        this.mGroupId = groupId(notificationEntry);
        PackageManager packageManager = context.getPackageManager();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(this.mEntry.notification.getPackageName(), 795136);
            if (applicationInfo != null) {
                this.mAppName = String.valueOf(packageManager.getApplicationLabel(applicationInfo));
            }
            this.mUserBadgedAppIcon = packageManager.getUserBadgedIcon(packageManager.getApplicationIcon(this.mEntry.notification.getPackageName()), this.mEntry.notification.getUser());
        } catch (PackageManager.NameNotFoundException unused) {
            this.mAppName = this.mEntry.notification.getPackageName();
        }
    }

    public String getKey() {
        return this.mKey;
    }

    public NotificationEntry getEntry() {
        return this.mEntry;
    }

    public String getGroupId() {
        return this.mGroupId;
    }

    public String getPackageName() {
        return this.mEntry.notification.getPackageName();
    }

    public String getAppName() {
        return this.mAppName;
    }

    /* access modifiers changed from: package-private */
    public void updateDotVisibility() {
        BubbleView bubbleView = this.mIconView;
        if (bubbleView != null) {
            bubbleView.updateDotVisibility(true);
        }
    }

    /* access modifiers changed from: package-private */
    public BubbleView getIconView() {
        return this.mIconView;
    }

    /* access modifiers changed from: package-private */
    public BubbleExpandedView getExpandedView() {
        return this.mExpandedView;
    }

    /* access modifiers changed from: package-private */
    public void cleanupExpandedState() {
        BubbleExpandedView bubbleExpandedView = this.mExpandedView;
        if (bubbleExpandedView != null) {
            bubbleExpandedView.cleanUpExpandedState();
        }
    }

    /* access modifiers changed from: package-private */
    public void inflate(LayoutInflater layoutInflater, BubbleStackView bubbleStackView) {
        if (!this.mInflated) {
            this.mIconView = (BubbleView) layoutInflater.inflate(C1779R$layout.bubble_view, bubbleStackView, false);
            this.mIconView.setBubble(this);
            this.mIconView.setAppIcon(this.mUserBadgedAppIcon);
            this.mExpandedView = (BubbleExpandedView) layoutInflater.inflate(C1779R$layout.bubble_expanded_view, bubbleStackView, false);
            this.mExpandedView.setBubble(this, bubbleStackView, this.mAppName);
            this.mInflated = true;
        }
    }

    /* access modifiers changed from: package-private */
    public void setContentVisibility(boolean z) {
        BubbleExpandedView bubbleExpandedView = this.mExpandedView;
        if (bubbleExpandedView != null) {
            bubbleExpandedView.setContentVisibility(z);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateEntry(NotificationEntry notificationEntry) {
        this.mEntry = notificationEntry;
        this.mLastUpdated = notificationEntry.notification.getPostTime();
        if (this.mInflated) {
            this.mIconView.update(this);
            this.mExpandedView.update(this);
        }
    }

    /* access modifiers changed from: package-private */
    public long getLastActivity() {
        return Math.max(this.mLastUpdated, this.mLastAccessed);
    }

    /* access modifiers changed from: package-private */
    public long getLastUpdateTime() {
        return this.mLastUpdated;
    }

    /* access modifiers changed from: package-private */
    public int getDisplayId() {
        BubbleExpandedView bubbleExpandedView = this.mExpandedView;
        if (bubbleExpandedView != null) {
            return bubbleExpandedView.getVirtualDisplayId();
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public void markAsAccessedAt(long j) {
        this.mLastAccessed = j;
        setShowInShadeWhenBubble(false);
        setShowBubbleDot(false);
    }

    /* access modifiers changed from: package-private */
    public boolean showInShadeWhenBubble() {
        return !this.mEntry.isRowDismissed() && !shouldSuppressNotification() && (!this.mEntry.isClearable() || this.mShowInShadeWhenBubble);
    }

    /* access modifiers changed from: package-private */
    public void setShowInShadeWhenBubble(boolean z) {
        this.mShowInShadeWhenBubble = z;
    }

    /* access modifiers changed from: package-private */
    public void setShowBubbleDot(boolean z) {
        this.mShowBubbleUpdateDot = z;
    }

    /* access modifiers changed from: package-private */
    public boolean showBubbleDot() {
        return this.mShowBubbleUpdateDot && !this.mEntry.shouldSuppressNotificationDot();
    }

    /* access modifiers changed from: package-private */
    public boolean showFlyoutForBubble() {
        return !this.mSuppressFlyout && !this.mEntry.shouldSuppressPeek() && !this.mEntry.shouldSuppressNotificationList();
    }

    /* access modifiers changed from: package-private */
    public void setSuppressFlyout(boolean z) {
        this.mSuppressFlyout = z;
    }

    /* access modifiers changed from: package-private */
    public boolean isOngoing() {
        return (this.mEntry.notification.getNotification().flags & 64) != 0;
    }

    /* access modifiers changed from: package-private */
    public float getDesiredHeight(Context context) {
        Notification.BubbleMetadata bubbleMetadata = this.mEntry.getBubbleMetadata();
        if (bubbleMetadata.getDesiredHeightResId() != 0) {
            return (float) getDimenForPackageUser(context, bubbleMetadata.getDesiredHeightResId(), this.mEntry.notification.getPackageName(), this.mEntry.notification.getUser().getIdentifier());
        }
        return ((float) bubbleMetadata.getDesiredHeight()) * context.getResources().getDisplayMetrics().density;
    }

    /* access modifiers changed from: package-private */
    public String getDesiredHeightString() {
        Notification.BubbleMetadata bubbleMetadata = this.mEntry.getBubbleMetadata();
        if (bubbleMetadata.getDesiredHeightResId() != 0) {
            return String.valueOf(bubbleMetadata.getDesiredHeightResId());
        }
        return String.valueOf(bubbleMetadata.getDesiredHeight());
    }

    /* access modifiers changed from: package-private */
    public PendingIntent getBubbleIntent(Context context) {
        Notification.BubbleMetadata bubbleMetadata = this.mEntry.notification.getNotification().getBubbleMetadata();
        if (!BubbleController.canLaunchInActivityView(context, this.mEntry) || bubbleMetadata == null) {
            return null;
        }
        return bubbleMetadata.getIntent();
    }

    /* access modifiers changed from: package-private */
    public Intent getSettingsIntent() {
        Intent intent = new Intent("android.settings.APP_NOTIFICATION_BUBBLE_SETTINGS");
        intent.putExtra("android.provider.extra.APP_PACKAGE", getPackageName());
        intent.putExtra("app_uid", this.mEntry.notification.getUid());
        intent.addFlags(134217728);
        intent.addFlags(268435456);
        intent.addFlags(536870912);
        return intent;
    }

    /* access modifiers changed from: package-private */
    public CharSequence getUpdateMessage(Context context) {
        Notification notification = this.mEntry.notification.getNotification();
        Class notificationStyle = notification.getNotificationStyle();
        try {
            if (Notification.BigTextStyle.class.equals(notificationStyle)) {
                CharSequence charSequence = notification.extras.getCharSequence("android.bigText");
                return !TextUtils.isEmpty(charSequence) ? charSequence : notification.extras.getCharSequence("android.text");
            }
            if (Notification.MessagingStyle.class.equals(notificationStyle)) {
                Notification.MessagingStyle.Message findLatestIncomingMessage = Notification.MessagingStyle.findLatestIncomingMessage(Notification.MessagingStyle.Message.getMessagesFromBundleArray((Parcelable[]) notification.extras.get("android.messages")));
                if (findLatestIncomingMessage != null) {
                    CharSequence name = findLatestIncomingMessage.getSenderPerson() != null ? findLatestIncomingMessage.getSenderPerson().getName() : null;
                    if (TextUtils.isEmpty(name)) {
                        return findLatestIncomingMessage.getText();
                    }
                    return context.getResources().getString(C1784R$string.notification_summary_message_format, new Object[]{name, findLatestIncomingMessage.getText()});
                }
            } else if (Notification.InboxStyle.class.equals(notificationStyle)) {
                CharSequence[] charSequenceArray = notification.extras.getCharSequenceArray("android.textLines");
                if (charSequenceArray != null && charSequenceArray.length > 0) {
                    return charSequenceArray[charSequenceArray.length - 1];
                }
            } else if (Notification.MediaStyle.class.equals(notificationStyle)) {
                return null;
            } else {
                return notification.extras.getCharSequence("android.text");
            }
            return null;
        } catch (ArrayIndexOutOfBoundsException | ClassCastException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    private int getDimenForPackageUser(Context context, int i, String str, int i2) {
        PackageManager packageManager = context.getPackageManager();
        if (str != null) {
            if (i2 == -1) {
                i2 = 0;
            }
            try {
                return packageManager.getResourcesForApplicationAsUser(str, i2).getDimensionPixelSize(i);
            } catch (PackageManager.NameNotFoundException unused) {
            } catch (Resources.NotFoundException e) {
                Log.e("Bubble", "Couldn't find desired height res id", e);
            }
        }
        return 0;
    }

    private boolean shouldSuppressNotification() {
        return this.mEntry.getBubbleMetadata() != null && this.mEntry.getBubbleMetadata().isNotificationSuppressed();
    }

    /* access modifiers changed from: package-private */
    public boolean shouldAutoExpand() {
        Notification.BubbleMetadata bubbleMetadata = this.mEntry.getBubbleMetadata();
        return bubbleMetadata != null && bubbleMetadata.getAutoExpandBubble();
    }

    public String toString() {
        return "Bubble{" + this.mKey + '}';
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print("key: ");
        printWriter.println(this.mKey);
        printWriter.print("  showInShade:   ");
        printWriter.println(showInShadeWhenBubble());
        printWriter.print("  showDot:       ");
        printWriter.println(showBubbleDot());
        printWriter.print("  showFlyout:    ");
        printWriter.println(showFlyoutForBubble());
        printWriter.print("  desiredHeight: ");
        printWriter.println(getDesiredHeightString());
        printWriter.print("  suppressNotif: ");
        printWriter.println(shouldSuppressNotification());
        printWriter.print("  autoExpand:    ");
        printWriter.println(shouldAutoExpand());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Bubble)) {
            return false;
        }
        return Objects.equals(this.mKey, ((Bubble) obj).mKey);
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mKey});
    }
}
