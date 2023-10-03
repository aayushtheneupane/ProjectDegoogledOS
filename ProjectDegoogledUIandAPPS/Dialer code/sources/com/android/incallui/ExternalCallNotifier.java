package com.android.incallui;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.p002v7.appcompat.R$style;
import android.telecom.Call;
import android.telecom.VideoProfile;
import android.text.BidiFormatter;
import android.text.TextDirectionHeuristics;
import android.text.TextUtils;
import android.util.ArrayMap;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.contacts.ContactsComponent;
import com.android.dialer.notification.DialerNotificationManager;
import com.android.dialer.theme.base.ThemeComponent;
import com.android.dialer.theme.base.impl.AospThemeImpl;
import com.android.incallui.ContactInfoCache;
import com.android.incallui.call.DialerCall;
import com.android.incallui.call.DialerCallDelegate;
import com.android.incallui.call.ExternalCallList;
import com.android.incallui.latencyreport.LatencyReport;
import java.util.Map;

public class ExternalCallNotifier implements ExternalCallList.ExternalCallListener {
    private final ContactInfoCache contactInfoCache;
    private final Context context;
    private int nextUniqueNotificationId;
    /* access modifiers changed from: private */
    public Map<Call, NotificationInfo> notifications = new ArrayMap();

    private static class DialerCallDelegateStub implements DialerCallDelegate {
        /* synthetic */ DialerCallDelegateStub(C06351 r1) {
        }

        public DialerCall getDialerCallFromTelecomCall(Call call) {
            return null;
        }
    }

    private static class NotificationInfo {
        private final Call call;
        private String contentTitle;
        private Bitmap largeIcon;
        private final int notificationId;
        private String personReference;

        public NotificationInfo(Call call2, int i) {
            this.call = call2;
            this.notificationId = i;
        }

        public Call getCall() {
            return this.call;
        }

        public String getContentTitle() {
            return this.contentTitle;
        }

        public Bitmap getLargeIcon() {
            return this.largeIcon;
        }

        public int getNotificationId() {
            return this.notificationId;
        }

        public String getPersonReference() {
            return this.personReference;
        }

        public void setContentTitle(String str) {
            this.contentTitle = str;
        }

        public void setLargeIcon(Bitmap bitmap) {
            this.largeIcon = bitmap;
        }

        public void setPersonReference(String str) {
            this.personReference = str;
        }
    }

    public ExternalCallNotifier(Context context2, ContactInfoCache contactInfoCache2) {
        this.context = context2;
        this.contactInfoCache = contactInfoCache2;
    }

    static /* synthetic */ void access$200(ExternalCallNotifier externalCallNotifier, NotificationInfo notificationInfo, ContactInfoCache.ContactCacheEntry contactCacheEntry) {
        String str;
        String str2;
        Context context2 = externalCallNotifier.context;
        Call call = notificationInfo.getCall();
        if (call.getDetails().hasProperty(1)) {
            str = CallerInfoUtils.getConferenceString(context2, call.getDetails().hasProperty(2));
        } else {
            str = ContactsComponent.get(context2).contactDisplayPreferences().getDisplayName(contactCacheEntry.namePrimary, contactCacheEntry.nameAlternative);
            if (TextUtils.isEmpty(str)) {
                if (TextUtils.isEmpty(contactCacheEntry.number)) {
                    str = null;
                } else {
                    str = BidiFormatter.getInstance().unicodeWrap(contactCacheEntry.number, TextDirectionHeuristics.LTR);
                }
            }
        }
        notificationInfo.setContentTitle(str);
        String number = R$style.getNumber(notificationInfo.getCall());
        Uri uri = contactCacheEntry.lookupUri;
        if (uri == null || contactCacheEntry.userType == 1) {
            str2 = !TextUtils.isEmpty(number) ? Uri.fromParts("tel", number, (String) null).toString() : "";
        } else {
            str2 = uri.toString();
        }
        notificationInfo.setPersonReference(str2);
        externalCallNotifier.postNotification(notificationInfo);
    }

    static /* synthetic */ void access$300(ExternalCallNotifier externalCallNotifier, NotificationInfo notificationInfo, ContactInfoCache.ContactCacheEntry contactCacheEntry) {
        Context context2 = externalCallNotifier.context;
        Call call = notificationInfo.getCall();
        Bitmap decodeResource = (!call.getDetails().hasProperty(1) || call.getDetails().hasProperty(2)) ? null : BitmapFactory.decodeResource(context2.getResources(), R.drawable.quantum_ic_group_vd_theme_24);
        Drawable drawable = contactCacheEntry.photo;
        if (drawable != null && (drawable instanceof BitmapDrawable)) {
            decodeResource = ((BitmapDrawable) drawable).getBitmap();
        }
        if (decodeResource != null) {
            Context context3 = externalCallNotifier.context;
            decodeResource = R$style.getRoundedBitmap(decodeResource, (int) context3.getResources().getDimension(17104901), (int) context3.getResources().getDimension(17104902));
        }
        notificationInfo.setLargeIcon(decodeResource);
        externalCallNotifier.postNotification(notificationInfo);
    }

    private void postNotification(NotificationInfo notificationInfo) {
        Notification.Builder builder = new Notification.Builder(this.context);
        builder.setOngoing(true);
        builder.setPriority(1);
        builder.setGroup("ExternalCallGroup");
        boolean isVideo = VideoProfile.isVideo(notificationInfo.getCall().getDetails().getVideoState());
        builder.setContentText(this.context.getString(isVideo ? R.string.notification_external_video_call : R.string.notification_external_call));
        builder.setSmallIcon(R.drawable.quantum_ic_call_white_24);
        builder.setContentTitle(notificationInfo.getContentTitle());
        builder.setLargeIcon(notificationInfo.getLargeIcon());
        builder.setColor(((AospThemeImpl) ThemeComponent.get(this.context).theme()).getColorPrimary());
        builder.addPerson(notificationInfo.getPersonReference());
        int i = Build.VERSION.SDK_INT;
        builder.setChannelId("phone_default");
        if (R$style.canPullExternalCall(notificationInfo.getCall())) {
            Intent intent = new Intent("com.android.incallui.ACTION_PULL_EXTERNAL_CALL", (Uri) null, this.context, NotificationBroadcastReceiver.class);
            intent.putExtra("com.android.incallui.extra.EXTRA_NOTIFICATION_ID", notificationInfo.getNotificationId());
            builder.addAction(new Notification.Action.Builder(R.drawable.quantum_ic_call_white_24, this.context.getString(isVideo ? R.string.notification_take_video_call : R.string.notification_take_call), PendingIntent.getBroadcast(this.context, notificationInfo.getNotificationId(), intent, 0)).build());
        }
        Notification.Builder builder2 = new Notification.Builder(this.context);
        builder2.setSmallIcon(R.drawable.quantum_ic_call_white_24);
        builder2.setColor(((AospThemeImpl) ThemeComponent.get(this.context).theme()).getColorPrimary());
        int i2 = Build.VERSION.SDK_INT;
        builder2.setChannelId("phone_default");
        builder.setPublicVersion(builder2.build());
        DialerNotificationManager.notify(this.context, "EXTERNAL_CALL", notificationInfo.getNotificationId(), builder.build());
        Context context2 = this.context;
        Notification.Builder builder3 = new Notification.Builder(context2);
        builder3.setOngoing(true);
        builder3.setPriority(1);
        builder3.setGroup("ExternalCallGroup");
        builder3.setGroupSummary(true);
        builder3.setSmallIcon(R.drawable.quantum_ic_call_white_24);
        int i3 = Build.VERSION.SDK_INT;
        builder3.setChannelId("phone_default");
        DialerNotificationManager.notify(context2, "GroupSummary_ExternalCall", -1, builder3.build());
    }

    public void onExternalCallAdded(Call call) {
        Bindings.m39i(this, "onExternalCallAdded " + call);
        Assert.checkArgument(this.notifications.containsKey(call) ^ true);
        int i = this.nextUniqueNotificationId;
        this.nextUniqueNotificationId = i + 1;
        final NotificationInfo notificationInfo = new NotificationInfo(call, i);
        this.notifications.put(call, notificationInfo);
        this.contactInfoCache.findInfo(new DialerCall(this.context, new DialerCallDelegateStub((C06351) null), notificationInfo.getCall(), new LatencyReport(), false), false, new ContactInfoCache.ContactInfoCacheCallback() {
            public void onContactInfoComplete(String str, ContactInfoCache.ContactCacheEntry contactCacheEntry) {
                if (ExternalCallNotifier.this.notifications.containsKey(notificationInfo.getCall())) {
                    ExternalCallNotifier.access$200(ExternalCallNotifier.this, notificationInfo, contactCacheEntry);
                }
            }

            public void onImageLoadComplete(String str, ContactInfoCache.ContactCacheEntry contactCacheEntry) {
                if (ExternalCallNotifier.this.notifications.containsKey(notificationInfo.getCall())) {
                    ExternalCallNotifier.access$300(ExternalCallNotifier.this, notificationInfo, contactCacheEntry);
                }
            }
        });
    }

    public void onExternalCallPulled(Call call) {
    }

    public void onExternalCallRemoved(Call call) {
        Bindings.m39i(this, "onExternalCallRemoved " + call);
        Assert.checkArgument(this.notifications.containsKey(call));
        DialerNotificationManager.cancel(this.context, "EXTERNAL_CALL", this.notifications.get(call).getNotificationId());
        this.notifications.remove(call);
    }

    public void onExternalCallUpdated(Call call) {
        Assert.checkArgument(this.notifications.containsKey(call));
        postNotification(this.notifications.get(call));
    }

    @TargetApi(25)
    public void pullExternalCall(int i) {
        for (NotificationInfo next : this.notifications.values()) {
            if (next.getNotificationId() == i && R$style.canPullExternalCall(next.getCall())) {
                next.getCall().pullExternalCall();
                return;
            }
        }
    }
}
