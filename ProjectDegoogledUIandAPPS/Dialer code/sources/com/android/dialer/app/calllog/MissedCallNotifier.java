package com.android.dialer.app.calllog;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.service.notification.StatusBarNotification;
import android.support.design.R$dimen;
import android.support.p000v4.util.Pair;
import android.support.p002v7.appcompat.R$style;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.PhoneNumberUtils;
import android.text.BidiFormatter;
import android.text.TextDirectionHeuristics;
import android.text.TextUtils;
import android.util.ArraySet;
import com.android.dialer.R;
import com.android.dialer.app.DialtactsActivity;
import com.android.dialer.app.calllog.CallLogNotificationsQueryHelper;
import com.android.dialer.app.contactinfo.ContactPhotoLoader;
import com.android.dialer.callintent.CallInitiationType$Type;
import com.android.dialer.callintent.CallIntentBuilder;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.duo.DuoComponent;
import com.android.dialer.duo.stub.DuoStub;
import com.android.dialer.notification.DialerNotificationManager;
import com.android.dialer.phonenumbercache.ContactInfo;
import com.android.dialer.phonenumberutil.PhoneNumberHelper;
import com.android.dialer.precall.PreCall;
import com.android.dialer.theme.base.ThemeComponent;
import com.android.dialer.theme.base.impl.AospThemeImpl;
import com.android.dialer.util.CallUtil;
import com.android.dialer.util.DialerUtils;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.Iterator;
import java.util.List;

public class MissedCallNotifier implements DialerExecutor.Worker<Pair<Integer, String>, Void> {
    private final CallLogNotificationsQueryHelper callLogNotificationsQueryHelper;
    private final Context context;

    MissedCallNotifier(Context context2, CallLogNotificationsQueryHelper callLogNotificationsQueryHelper2) {
        this.context = context2;
        this.callLogNotificationsQueryHelper = callLogNotificationsQueryHelper2;
    }

    private PendingIntent createCallLogPendingIntent(Uri uri) {
        Context context2 = this.context;
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(context2, "com.android.dialer.app.DialtactsActivity"));
        intent.setAction("ACTION_SHOW_TAB");
        intent.putExtra(DialtactsActivity.EXTRA_SHOW_TAB, 1);
        intent.setData(uri);
        return PendingIntent.getActivity(this.context, 0, intent, 134217728);
    }

    private Notification.Builder createNotificationBuilder() {
        return new Notification.Builder(this.context).setGroup("MissedCallGroup").setSmallIcon(17301631).setColor(((AospThemeImpl) ThemeComponent.get(this.context).theme()).getColorPrimary()).setAutoCancel(true).setOnlyAlertOnce(true).setShowWhen(true).setDefaults(2);
    }

    public static MissedCallNotifier getInstance(Context context2) {
        return new MissedCallNotifier(context2, CallLogNotificationsQueryHelper.getInstance(context2));
    }

    public void callBackFromMissedCall(String str, Uri uri) {
        this.context.sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
        CallLogNotificationsQueryHelper.markSingleMissedCallInCallLogAsRead(this.context, uri);
        R$style.cancelSingle(this.context, uri);
        Context context2 = this.context;
        DialerUtils.startActivityWithErrorToast(context2, PreCall.getIntent(context2, new CallIntentBuilder(str, CallInitiationType$Type.MISSED_CALL_NOTIFICATION)).setFlags(268435456), R.string.activity_not_available);
    }

    public void sendSmsFromMissedCall(String str, Uri uri) {
        this.context.sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
        CallLogNotificationsQueryHelper.markSingleMissedCallInCallLogAsRead(this.context, uri);
        R$style.cancelSingle(this.context, uri);
        DialerUtils.startActivityWithErrorToast(this.context, CallUtil.getSendSmsIntent(str).setFlags(268435456), R.string.activity_not_available);
    }

    /* access modifiers changed from: package-private */
    public void updateMissedCallNotification(int i, String str) {
        CharSequence charSequence;
        int i2;
        CharSequence charSequence2;
        CallLogNotificationsQueryHelper.NewCall newCall;
        ComponentName unflattenFromString;
        PhoneAccountHandle phoneAccountHandle;
        PhoneAccount phoneAccount;
        int i3 = i;
        LogUtil.enterBlock("MissedCallNotifier.updateMissedCallNotification");
        List<CallLogNotificationsQueryHelper.NewCall> newMissedCalls = this.callLogNotificationsQueryHelper.getNewMissedCalls();
        if (newMissedCalls != null) {
            TelecomManager telecomManager = (TelecomManager) this.context.getSystemService(TelecomManager.class);
            Iterator<CallLogNotificationsQueryHelper.NewCall> it = newMissedCalls.iterator();
            while (it.hasNext()) {
                CallLogNotificationsQueryHelper.NewCall next = it.next();
                String str2 = next.accountComponentName;
                if (!(str2 == null || next.accountId == null || (unflattenFromString = ComponentName.unflattenFromString(str2)) == null || (phoneAccount = telecomManager.getPhoneAccount(phoneAccountHandle)) == null)) {
                    ((DuoStub) DuoComponent.get(this.context).getDuo()).isDuoAccount((phoneAccountHandle = new PhoneAccountHandle(unflattenFromString, next.accountId)));
                    if (phoneAccount.hasCapabilities(2048)) {
                        StringBuilder outline13 = GeneratedOutlineSupport.outline13("ignoring self-managed call ");
                        outline13.append(next.callsUri);
                        LogUtil.m9i("MissedCallNotifier.removeSelfManagedCalls", outline13.toString(), new Object[0]);
                        it.remove();
                    }
                }
            }
        }
        if ((newMissedCalls == null || !newMissedCalls.isEmpty()) && i3 != 0) {
            if (newMissedCalls != null) {
                if (!(i3 == -1 || i3 == newMissedCalls.size())) {
                    LogUtil.m10w("MissedCallNotifier.updateMissedCallNotification", "Call count does not match call log count. count: " + i3 + " newCalls.size(): " + newMissedCalls.size(), new Object[0]);
                }
                i3 = newMissedCalls.size();
            }
            if (i3 == -1) {
                LogUtil.m9i("MissedCallNotifier.updateMissedCallNotification", "unknown missed call count", new Object[0]);
                return;
            }
            Notification.Builder createNotificationBuilder = createNotificationBuilder();
            boolean z = newMissedCalls != null;
            long j = 1;
            if (i3 == 1) {
                LogUtil.m9i("MissedCallNotifier.updateMissedCallNotification", "1 missed call, looking up contact info", new Object[0]);
                if (z) {
                    newCall = newMissedCalls.get(0);
                } else {
                    newCall = new CallLogNotificationsQueryHelper.NewCall((Uri) null, (Uri) null, str, 1, (String) null, (String) null, (String) null, (String) null, System.currentTimeMillis(), 0);
                }
                ContactInfo contactInfo = this.callLogNotificationsQueryHelper.getContactInfo(newCall.number, newCall.numberPresentation, newCall.countryIso);
                i2 = contactInfo.userType == 1 ? R.string.notification_missedWorkCallTitle : R.string.notification_missedCallTitle;
                if (TextUtils.equals(contactInfo.name, contactInfo.formattedNumber) || TextUtils.equals(contactInfo.name, contactInfo.number)) {
                    charSequence = PhoneNumberUtils.createTtsSpannable(BidiFormatter.getInstance().unicodeWrap(contactInfo.name, TextDirectionHeuristics.LTR));
                } else {
                    charSequence = contactInfo.name;
                }
                Bitmap loadPhotoIcon = new ContactPhotoLoader(this.context, contactInfo).loadPhotoIcon();
                if (loadPhotoIcon != null) {
                    createNotificationBuilder.setLargeIcon(loadPhotoIcon);
                }
            } else {
                i2 = R.string.notification_missedCallsTitle;
                charSequence = this.context.getString(R.string.notification_missedCallsMsg, new Object[]{Integer.valueOf(i3)});
            }
            LogUtil.m9i("MissedCallNotifier.updateMissedCallNotification", "preparing notification", new Object[0]);
            Notification.Builder createNotificationBuilder2 = createNotificationBuilder();
            createNotificationBuilder2.setContentTitle(this.context.getText(i2)).setContentIntent(createCallLogPendingIntent((Uri) null)).setDeleteIntent(CallLogNotificationsService.createCancelAllMissedCallsPendingIntent(this.context));
            createNotificationBuilder.setContentTitle(this.context.getText(i2)).setContentText(charSequence).setContentIntent(createCallLogPendingIntent((Uri) null)).setDeleteIntent(CallLogNotificationsService.createCancelAllMissedCallsPendingIntent(this.context)).setGroupSummary(z).setOnlyAlertOnce(z).setPublicVersion(createNotificationBuilder2.build());
            int i4 = Build.VERSION.SDK_INT;
            createNotificationBuilder.setChannelId("phone_missed_call");
            Notification build = createNotificationBuilder.build();
            build.flags |= 1;
            build.defaults |= 4;
            LogUtil.m9i("MissedCallNotifier.updateMissedCallNotification", "adding missed call notification", new Object[0]);
            DialerNotificationManager.notify(this.context, "GroupSummary_MissedCall", 1, build);
            if (z) {
                ArraySet arraySet = new ArraySet();
                for (StatusBarNotification tag : DialerNotificationManager.getActiveNotifications(this.context)) {
                    arraySet.add(tag.getTag());
                }
                for (StatusBarNotification tag2 : DialerNotificationManager.getThrottledNotificationSet()) {
                    arraySet.add(tag2.getTag());
                }
                for (CallLogNotificationsQueryHelper.NewCall next2 : newMissedCalls) {
                    String outline6 = GeneratedOutlineSupport.outline6("MissedCall_", next2.callsUri);
                    if (!arraySet.contains(outline6)) {
                        Context context2 = this.context;
                        ContactInfo contactInfo2 = this.callLogNotificationsQueryHelper.getContactInfo(next2.number, next2.numberPresentation, next2.countryIso);
                        int i5 = contactInfo2.userType == j ? R.string.notification_missedWorkCallTitle : R.string.notification_missedCallTitle;
                        Notification.Builder contentTitle = createNotificationBuilder(next2).setContentTitle(this.context.getText(i5));
                        Notification.Builder createNotificationBuilder3 = createNotificationBuilder(next2);
                        if (TextUtils.equals(contactInfo2.name, contactInfo2.formattedNumber) || TextUtils.equals(contactInfo2.name, contactInfo2.number)) {
                            charSequence2 = PhoneNumberUtils.createTtsSpannable(BidiFormatter.getInstance().unicodeWrap(contactInfo2.name, TextDirectionHeuristics.LTR));
                        } else {
                            charSequence2 = contactInfo2.name;
                        }
                        Bitmap loadPhotoIcon2 = new ContactPhotoLoader(this.context, contactInfo2).loadPhotoIcon();
                        if (loadPhotoIcon2 != null) {
                            createNotificationBuilder3.setLargeIcon(loadPhotoIcon2);
                        }
                        createNotificationBuilder3.setContentTitle(this.context.getText(i5)).setContentText(charSequence2).setPublicVersion(contentTitle.build());
                        if (R$dimen.isUserUnlocked(this.context) && !TextUtils.isEmpty(next2.number) && !TextUtils.equals(next2.number, this.context.getString(R.string.handle_restricted))) {
                            Icon createWithResource = Icon.createWithResource(this.context, R.drawable.ic_phone_24dp);
                            String string = this.context.getString(R.string.notification_missedCall_call_back);
                            String str3 = next2.number;
                            Uri uri = next2.callsUri;
                            Intent intent = new Intent(this.context, CallLogNotificationsService.class);
                            intent.setAction("com.android.dialer.calllog.CALL_BACK_FROM_MISSED_CALL_NOTIFICATION");
                            intent.putExtra("android.telecom.extra.NOTIFICATION_PHONE_NUMBER", str3);
                            intent.setData(uri);
                            createNotificationBuilder3.addAction(new Notification.Action.Builder(createWithResource, string, PendingIntent.getService(this.context, 0, intent, 134217728)).build());
                            if (!PhoneNumberHelper.isUriNumber(next2.number)) {
                                Icon createWithResource2 = Icon.createWithResource(this.context, R.drawable.quantum_ic_message_white_24);
                                String string2 = this.context.getString(R.string.notification_missedCall_message);
                                String str4 = next2.number;
                                Uri uri2 = next2.callsUri;
                                Intent intent2 = new Intent(this.context, CallLogNotificationsActivity.class);
                                intent2.setAction("com.android.dialer.calllog.SEND_SMS_FROM_MISSED_CALL_NOTIFICATION");
                                intent2.putExtra("MISSED_CALL_NUMBER", str4);
                                intent2.setData(uri2);
                                createNotificationBuilder3.addAction(new Notification.Action.Builder(createWithResource2, string2, PendingIntent.getActivity(this.context, 0, intent2, 134217728)).build());
                            }
                        }
                        Notification build2 = createNotificationBuilder3.build();
                        build2.flags |= 1;
                        build2.defaults |= 4;
                        DialerNotificationManager.notify(context2, outline6, 1, build2);
                    }
                    j = 1;
                }
                return;
            }
            return;
        }
        CallLogNotificationsQueryHelper.markAllMissedCallsInCallLogAsRead(this.context);
        R$style.cancelAllInGroup(this.context, "MissedCallGroup");
    }

    public Void doInBackground(Pair<Integer, String> pair) throws Throwable {
        updateMissedCallNotification(((Integer) pair.first).intValue(), (String) pair.second);
        return null;
    }

    private Notification.Builder createNotificationBuilder(CallLogNotificationsQueryHelper.NewCall newCall) {
        Notification.Builder contentIntent = createNotificationBuilder().setWhen(newCall.dateMs).setDeleteIntent(CallLogNotificationsService.createCancelSingleMissedCallPendingIntent(this.context, newCall.callsUri)).setContentIntent(createCallLogPendingIntent(newCall.callsUri));
        int i = Build.VERSION.SDK_INT;
        contentIntent.setChannelId("phone_missed_call");
        return contentIntent;
    }
}
