package com.android.dialer.notification;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.service.notification.StatusBarNotification;
import android.telecom.PhoneAccountHandle;
import android.text.TextUtils;
import android.util.ArraySet;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@TargetApi(26)
public final class NotificationChannelManager {
    private static boolean didLogHighGlobalNotificationCountReached;

    public static String getVoicemailChannelId(Context context, PhoneAccountHandle phoneAccountHandle) {
        int i = Build.VERSION.SDK_INT;
        Assert.checkArgument(true);
        Assert.isNotNull(context);
        return VoicemailChannelUtils.getChannelId(context, phoneAccountHandle);
    }

    public static void initChannels(Context context) {
        int i = Build.VERSION.SDK_INT;
        Assert.checkArgument(true);
        Assert.isNotNull(context);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
        ArraySet arraySet = new ArraySet();
        arraySet.add("phone_incoming_call");
        arraySet.add("phone_ongoing_call");
        arraySet.add("phone_missed_call");
        arraySet.add("phone_default");
        arraySet.addAll(VoicemailChannelUtils.getAllChannelIds(context));
        ArraySet<String> arraySet2 = new ArraySet<>();
        for (NotificationChannel id : ((NotificationManager) context.getSystemService(NotificationManager.class)).getNotificationChannels()) {
            arraySet2.add(id.getId());
        }
        if (!arraySet.equals(arraySet2)) {
            LogUtil.m9i("NotificationChannelManager.initChannels", "doing an expensive initialization of all notification channels", new Object[0]);
            LogUtil.m9i("NotificationChannelManager.initChannels", "desired channel IDs: " + arraySet, new Object[0]);
            LogUtil.m9i("NotificationChannelManager.initChannels", "existing channel IDs: " + arraySet2, new Object[0]);
            for (String str : arraySet2) {
                if (!arraySet.contains(str)) {
                    notificationManager.deleteNotificationChannel(str);
                }
            }
            NotificationChannel notificationChannel = new NotificationChannel("phone_incoming_call", context.getText(R.string.notification_channel_incoming_call), 5);
            notificationChannel.setShowBadge(false);
            notificationChannel.enableLights(true);
            notificationChannel.enableVibration(false);
            notificationChannel.setSound((Uri) null, new AudioAttributes.Builder().setUsage(5).build());
            ((NotificationManager) context.getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel);
            NotificationChannel notificationChannel2 = new NotificationChannel("phone_ongoing_call", context.getText(R.string.notification_channel_ongoing_call), 3);
            notificationChannel2.setShowBadge(false);
            notificationChannel2.enableLights(false);
            notificationChannel2.enableVibration(false);
            notificationChannel2.setSound((Uri) null, new AudioAttributes.Builder().setUsage(5).build());
            ((NotificationManager) context.getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel2);
            NotificationChannel notificationChannel3 = new NotificationChannel("phone_missed_call", context.getText(R.string.notification_channel_missed_call), 3);
            notificationChannel3.setShowBadge(true);
            notificationChannel3.enableLights(true);
            notificationChannel3.enableVibration(true);
            notificationChannel3.setSound((Uri) null, new AudioAttributes.Builder().setUsage(5).build());
            ((NotificationManager) context.getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel3);
            NotificationChannel notificationChannel4 = new NotificationChannel("phone_default", context.getText(R.string.notification_channel_misc), 3);
            notificationChannel4.setShowBadge(false);
            notificationChannel4.enableLights(true);
            notificationChannel4.enableVibration(true);
            ((NotificationManager) context.getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel4);
            VoicemailChannelUtils.createAllChannels(context);
        }
    }

    private static boolean isNotificationInGroup(StatusBarNotification statusBarNotification, String str) {
        if ((statusBarNotification.getNotification().flags & 512) != 0) {
            return false;
        }
        return TextUtils.equals(str, statusBarNotification.getNotification().getGroup());
    }

    static Set<StatusBarNotification> throttle(Context context, Notification notification) {
        Assert.isNotNull(context);
        Assert.isNotNull(notification);
        HashSet hashSet = new HashSet();
        String group = notification.getGroup();
        if (TextUtils.isEmpty(group)) {
            return hashSet;
        }
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
        StatusBarNotification[] activeNotifications = notificationManager.getActiveNotifications();
        if (activeNotifications.length > 45 && !didLogHighGlobalNotificationCountReached) {
            LogUtil.m9i("NotificationThrottler.throttle", "app has %d notifications, system may suppress future notifications", Integer.valueOf(activeNotifications.length));
            didLogHighGlobalNotificationCountReached = true;
            ((LoggingBindingsStub) Logger.get(context)).logImpression(DialerImpression$Type.HIGH_GLOBAL_NOTIFICATION_COUNT_REACHED);
        }
        int i = 0;
        for (StatusBarNotification isNotificationInGroup : activeNotifications) {
            if (isNotificationInGroup(isNotificationInGroup, group)) {
                i++;
            }
        }
        if (i > 8) {
            LogUtil.m9i("NotificationThrottler.throttle", "groupKey: %s is over limit, count: %d, limit: %d", group, Integer.valueOf(i), 8);
            ArrayList arrayList = new ArrayList();
            for (StatusBarNotification statusBarNotification : ((NotificationManager) context.getSystemService(NotificationManager.class)).getActiveNotifications()) {
                if (isNotificationInGroup(statusBarNotification, group)) {
                    arrayList.add(statusBarNotification);
                }
            }
            Collections.sort(arrayList, new NotificationThrottler$1());
            for (int i2 = 0; i2 < i - 8; i2++) {
                notificationManager.cancel(((StatusBarNotification) arrayList.get(i2)).getTag(), ((StatusBarNotification) arrayList.get(i2)).getId());
                hashSet.add((StatusBarNotification) arrayList.get(i2));
            }
        }
        return hashSet;
    }
}
