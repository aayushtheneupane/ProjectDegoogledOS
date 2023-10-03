package com.android.dialer.app.calllog;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.PersistableBundle;
import android.provider.CallLog;
import android.support.p000v4.app.NotificationCompat$Builder;
import android.support.p002v7.appcompat.R$style;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.android.dialer.R;
import com.android.dialer.app.DialtactsActivity;
import com.android.dialer.app.calllog.CallLogNotificationsQueryHelper;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.AsyncTaskExecutor;
import com.android.dialer.common.concurrent.AsyncTaskExecutors;
import com.android.dialer.notification.DialerNotificationManager;
import com.android.dialer.notification.NotificationChannelManager;
import com.android.dialer.phonenumberutil.PhoneNumberHelper;
import com.android.dialer.telecom.TelecomUtil;
import com.android.dialer.theme.base.ThemeComponent;
import com.android.dialer.theme.base.impl.AospThemeImpl;
import com.android.dialer.util.PermissionsUtil;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.List;

public class CallLogAsyncTaskUtil {
    private static AsyncTaskExecutor asyncTaskExecutor;

    public interface CallLogAsyncTaskListener {
        void onDeleteVoicemail();
    }

    public enum Tasks {
        DELETE_VOICEMAIL,
        DELETE_CALL,
        MARK_VOICEMAIL_READ,
        MARK_CALL_READ,
        GET_CALL_DETAILS,
        UPDATE_DURATION
    }

    static /* synthetic */ void access$000(Context context) {
        Intent intent = new Intent("com.android.voicemail.VoicemailClient.ACTION_UPLOAD");
        intent.setPackage(context.getPackageName());
        context.sendBroadcast(intent);
    }

    public static void cancelAllVoicemailNotifications(Context context) {
        LogUtil.enterBlock("VisualVoicemailNotifier.cancelAllVoicemailNotifications");
        R$style.cancelAllInGroup(context, "VisualVoicemailGroup");
    }

    public static void cancelNotification(Context context, PhoneAccountHandle phoneAccountHandle) {
        LogUtil.enterBlock("LegacyVoicemailNotifier.cancelNotification");
        int i = Build.VERSION.SDK_INT;
        Assert.checkArgument(true);
        Assert.isNotNull(phoneAccountHandle);
        if ("null".equals(phoneAccountHandle.getId())) {
            LogUtil.m9i("LegacyVoicemailNotifier.cancelNotification", "'null' id, canceling all legacy voicemail notifications", new Object[0]);
            DialerNotificationManager.cancelAll(context, "LegacyVoicemail");
            return;
        }
        DialerNotificationManager.cancel(context, getNotificationTag(context, phoneAccountHandle), 1);
    }

    public static void cancelSingleVoicemailNotification(Context context, Uri uri) {
        LogUtil.enterBlock("VisualVoicemailNotifier.cancelSingleVoicemailNotification");
        if (uri == null) {
            LogUtil.m8e("VisualVoicemailNotifier.cancelSingleVoicemailNotification", "uri is null", new Object[0]);
            return;
        }
        DialerNotificationManager.cancel(context, "VisualVoicemail_" + uri, 1);
    }

    private static NotificationCompat$Builder createNotificationBuilder(Context context) {
        NotificationCompat$Builder notificationCompat$Builder = new NotificationCompat$Builder(context);
        notificationCompat$Builder.setSmallIcon(17301630);
        notificationCompat$Builder.setColor(((AospThemeImpl) ThemeComponent.get(context).theme()).getColorPrimary());
        notificationCompat$Builder.setGroup("VisualVoicemailGroup");
        notificationCompat$Builder.setOnlyAlertOnce(true);
        notificationCompat$Builder.setAutoCancel(true);
        return notificationCompat$Builder;
    }

    public static void deleteVoicemail(final Context context, final Uri uri, final CallLogAsyncTaskListener callLogAsyncTaskListener) {
        if (asyncTaskExecutor == null) {
            initTaskExecutor();
        }
        asyncTaskExecutor.submit(Tasks.DELETE_VOICEMAIL, new AsyncTask<Void, Void, Void>() {
            public Object doInBackground(Object[] objArr) {
                Void[] voidArr = (Void[]) objArr;
                CallLogAsyncTaskUtil.deleteVoicemailSynchronous(context, uri);
                return null;
            }

            public void onPostExecute(Object obj) {
                Void voidR = (Void) obj;
                CallLogAsyncTaskListener callLogAsyncTaskListener = callLogAsyncTaskListener;
                if (callLogAsyncTaskListener != null) {
                    callLogAsyncTaskListener.onDeleteVoicemail();
                }
            }
        }, new Void[0]);
    }

    public static void deleteVoicemailSynchronous(Context context, Uri uri) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("deleted", "1");
        context.getContentResolver().update(uri, contentValues, (String) null, (String[]) null);
        Intent intent = new Intent("com.android.voicemail.VoicemailClient.ACTION_UPLOAD");
        intent.setPackage(context.getPackageName());
        context.sendBroadcast(intent);
    }

    public static PhoneAccountHandle getAccountForCall(CallLogNotificationsQueryHelper.NewCall newCall) {
        String str;
        if (newCall == null || (str = newCall.accountComponentName) == null || newCall.accountId == null) {
            return null;
        }
        return new PhoneAccountHandle(ComponentName.unflattenFromString(str), newCall.accountId);
    }

    public static PhoneAccountHandle getFallbackAccount(Context context) {
        PhoneAccountHandle defaultOutgoingPhoneAccount = TelecomUtil.getDefaultOutgoingPhoneAccount(context, "tel");
        if (defaultOutgoingPhoneAccount != null) {
            return defaultOutgoingPhoneAccount;
        }
        List<PhoneAccountHandle> callCapablePhoneAccounts = TelecomUtil.getCallCapablePhoneAccounts(context);
        return !callCapablePhoneAccounts.isEmpty() ? callCapablePhoneAccounts.get(0) : defaultOutgoingPhoneAccount;
    }

    private static String getNotificationTag(Context context, PhoneAccountHandle phoneAccountHandle) {
        if (((TelephonyManager) context.getSystemService(TelephonyManager.class)).getPhoneCount() <= 1) {
            return "LegacyVoicemail";
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("LegacyVoicemail_");
        outline13.append(phoneAccountHandle.getId());
        return outline13.toString();
    }

    private static String getNotificationTagForUri(Uri uri) {
        return GeneratedOutlineSupport.outline6("VisualVoicemail_", uri);
    }

    private static void initTaskExecutor() {
        asyncTaskExecutor = AsyncTaskExecutors.createThreadPoolExecutor();
    }

    public static void markCallAsRead(final Context context, final long[] jArr) {
        if (PermissionsUtil.hasPhonePermissions(context) && PermissionsUtil.hasCallLogWritePermissions(context)) {
            if (asyncTaskExecutor == null) {
                initTaskExecutor();
            }
            asyncTaskExecutor.submit(Tasks.MARK_CALL_READ, new AsyncTask<Void, Void, Void>() {
                public Object doInBackground(Object[] objArr) {
                    Void[] voidArr = (Void[]) objArr;
                    StringBuilder sb = new StringBuilder();
                    sb.append("type");
                    sb.append(" = ");
                    sb.append(3);
                    sb.append(" AND ");
                    Long[] lArr = new Long[jArr.length];
                    int i = 0;
                    while (true) {
                        long[] jArr = jArr;
                        if (i < jArr.length) {
                            lArr[i] = Long.valueOf(jArr[i]);
                            i++;
                        } else {
                            sb.append("_id");
                            sb.append(" IN (" + TextUtils.join(",", lArr) + ")");
                            ContentValues contentValues = new ContentValues(1);
                            contentValues.put("is_read", "1");
                            context.getContentResolver().update(CallLog.Calls.CONTENT_URI, contentValues, sb.toString(), (String[]) null);
                            return null;
                        }
                    }
                }
            }, new Void[0]);
        }
    }

    public static void markVoicemailAsRead(final Context context, final Uri uri) {
        LogUtil.enterBlock("CallLogAsyncTaskUtil.markVoicemailAsRead, voicemailUri: " + uri);
        if (asyncTaskExecutor == null) {
            initTaskExecutor();
        }
        asyncTaskExecutor.submit(Tasks.MARK_VOICEMAIL_READ, new AsyncTask<Void, Void, Void>() {
            public Object doInBackground(Object[] objArr) {
                Void[] voidArr = (Void[]) objArr;
                ContentValues contentValues = new ContentValues();
                contentValues.put("is_read", true);
                contentValues.put("dirty", 1);
                if (context.getContentResolver().update(uri, contentValues, "is_read = 0", (String[]) null) > 0) {
                    CallLogAsyncTaskUtil.access$000(context);
                    CallLogNotificationsService.markAllNewVoicemailsAsOld(context);
                }
                return null;
            }
        }, new Void[0]);
    }

    private static PendingIntent newVoicemailIntent(Context context, CallLogNotificationsQueryHelper.NewCall newCall) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(context, "com.android.dialer.app.DialtactsActivity"));
        intent.setAction("ACTION_SHOW_TAB");
        intent.putExtra(DialtactsActivity.EXTRA_SHOW_TAB, 3);
        intent.putExtra("EXTRA_CLEAR_NEW_VOICEMAILS", true);
        if (newCall != null) {
            intent.setData(newCall.voicemailUri);
        }
        return PendingIntent.getActivity(context, 0, intent, 134217728);
    }

    public static void showNotification(Context context, PhoneAccountHandle phoneAccountHandle, int i, String str, PendingIntent pendingIntent, PendingIntent pendingIntent2, boolean z) {
        boolean z2;
        String str2;
        PhoneAccount phoneAccount;
        LogUtil.enterBlock("LegacyVoicemailNotifier.showNotification");
        Assert.isNotNull(phoneAccountHandle);
        int i2 = Build.VERSION.SDK_INT;
        Assert.checkArgument(true);
        TelephonyManager createForPhoneAccountHandle = ((TelephonyManager) context.getSystemService(TelephonyManager.class)).createForPhoneAccountHandle(phoneAccountHandle);
        if (createForPhoneAccountHandle == null) {
            LogUtil.m8e("LegacyVoicemailNotifier.showNotification", "invalid PhoneAccountHandle", new Object[0]);
            return;
        }
        String quantityString = context.getResources().getQuantityString(R.plurals.notification_voicemail_title, i, new Object[]{Integer.valueOf(i)});
        PersistableBundle carrierConfig = createForPhoneAccountHandle.getCarrierConfig();
        if (carrierConfig == null) {
            z2 = false;
        } else {
            z2 = carrierConfig.getBoolean("voicemail_notification_persistent_bool");
        }
        if (TextUtils.isEmpty(str) || pendingIntent == null) {
            str2 = context.getString(R.string.notification_voicemail_no_vm_number);
            pendingIntent = pendingIntent2;
        } else if (TelecomUtil.getCallCapablePhoneAccounts(context).size() <= 1 || (phoneAccount = ((TelecomManager) context.getSystemService(TelecomManager.class)).getPhoneAccount(phoneAccountHandle)) == null) {
            str2 = String.format(context.getString(R.string.notification_voicemail_text_format), new Object[]{PhoneNumberHelper.formatNumber(context, str, R$style.getCurrentCountryIso(context))});
        } else {
            str2 = phoneAccount.getShortDescription().toString();
        }
        Notification.Builder deleteIntent = new Notification.Builder(context).setSmallIcon(17301630).setColor(((AospThemeImpl) ThemeComponent.get(context).theme()).getColorPrimary()).setWhen(System.currentTimeMillis()).setContentTitle(quantityString).setContentText(str2).setContentIntent(pendingIntent).setSound(createForPhoneAccountHandle.getVoicemailRingtoneUri(phoneAccountHandle)).setOngoing(z2).setOnlyAlertOnce(z).setChannelId(NotificationChannelManager.getVoicemailChannelId(context, phoneAccountHandle)).setDeleteIntent(CallLogNotificationsService.createLegacyVoicemailDismissedPendingIntent(context, phoneAccountHandle));
        if (createForPhoneAccountHandle.isVoicemailVibrationEnabled(phoneAccountHandle)) {
            deleteIntent.setDefaults(2);
        }
        DialerNotificationManager.notify(context, getNotificationTag(context, phoneAccountHandle), 1, deleteIntent.build());
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x00cf  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00f4  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0102  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0120  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0192  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x01b0  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x01b3 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void showNotifications(android.content.Context r11, java.util.List<com.android.dialer.app.calllog.CallLogNotificationsQueryHelper.NewCall> r12, java.util.Map<java.lang.String, com.android.dialer.phonenumbercache.ContactInfo> r13, java.lang.String r14, boolean r15) {
        /*
            java.lang.String r0 = "VisualVoicemailNotifier.showNotifications"
            com.android.dialer.common.LogUtil.enterBlock(r0)
            android.app.PendingIntent r0 = com.android.dialer.app.calllog.CallLogNotificationsService.createMarkAllNewVoicemailsAsOldIntent(r11)
            android.content.res.Resources r1 = r11.getResources()
            int r2 = r12.size()
            r3 = 1
            java.lang.Object[] r4 = new java.lang.Object[r3]
            int r5 = r12.size()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r6 = 0
            r4[r6] = r5
            r5 = 2131689479(0x7f0f0007, float:1.9007975E38)
            java.lang.String r1 = r1.getQuantityString(r5, r2, r4)
            android.support.v4.app.NotificationCompat$Builder r2 = createNotificationBuilder(r11)
            r2.setContentTitle(r1)
            r2.setContentText(r14)
            r2.setDeleteIntent(r0)
            r2.setGroupSummary(r3)
            r14 = 0
            android.app.PendingIntent r0 = newVoicemailIntent(r11, r14)
            r2.setContentIntent(r0)
            int r0 = android.os.Build.VERSION.SDK_INT
            r0 = 2
            if (r15 == 0) goto L_0x004a
            r2.setOnlyAlertOnce(r6)
            r2.setGroupAlertBehavior(r6)
            goto L_0x004d
        L_0x004a:
            r2.setGroupAlertBehavior(r0)
        L_0x004d:
            java.lang.Object r15 = r12.get(r6)
            com.android.dialer.app.calllog.CallLogNotificationsQueryHelper$NewCall r15 = (com.android.dialer.app.calllog.CallLogNotificationsQueryHelper.NewCall) r15
            android.telecom.PhoneAccountHandle r15 = getAccountForCall(r15)
            java.lang.String r15 = com.android.dialer.notification.NotificationChannelManager.getVoicemailChannelId(r11, r15)
            r2.setChannelId(r15)
            android.app.Notification r15 = r2.build()
            java.lang.String r1 = "GroupSummary_VisualVoicemail"
            com.android.dialer.notification.DialerNotificationManager.notify(r11, r1, r3, r15)
            java.util.Iterator r12 = r12.iterator()
        L_0x006b:
            boolean r15 = r12.hasNext()
            if (r15 == 0) goto L_0x01ce
            java.lang.Object r15 = r12.next()
            com.android.dialer.app.calllog.CallLogNotificationsQueryHelper$NewCall r15 = (com.android.dialer.app.calllog.CallLogNotificationsQueryHelper.NewCall) r15
            android.net.Uri r1 = r15.voicemailUri
            java.lang.String r1 = getNotificationTagForUri(r1)
            android.telecom.PhoneAccountHandle r2 = getAccountForCall(r15)
            java.lang.String r4 = r15.number
            java.lang.Object r4 = r13.get(r4)
            com.android.dialer.phonenumbercache.ContactInfo r4 = (com.android.dialer.phonenumbercache.ContactInfo) r4
            android.support.v4.app.NotificationCompat$Builder r5 = createNotificationBuilder(r11)
            android.content.res.Resources r7 = r11.getResources()
            java.lang.String r8 = r4.name
            r9 = 2131821177(0x7f110279, float:1.927509E38)
            java.lang.CharSequence r7 = android.support.p002v7.appcompat.R$style.getTtsSpannedPhoneNumber(r7, r9, r8)
            r5.setContentTitle(r7)
            long r7 = r15.dateMs
            r5.setWhen(r7)
            java.lang.String r7 = "null handle, getting fallback"
            if (r2 != 0) goto L_0x00bc
            java.lang.Object[] r8 = new java.lang.Object[r6]
            java.lang.String r9 = "VisualVoicemailNotifier.getVoicemailRingtoneUri"
            com.android.dialer.common.LogUtil.m9i(r9, r7, r8)
            android.telecom.PhoneAccountHandle r8 = getFallbackAccount(r11)
            if (r8 != 0) goto L_0x00bd
            java.lang.Object[] r8 = new java.lang.Object[r6]
            java.lang.String r10 = "no fallback handle, using null (default) ringtone"
            com.android.dialer.common.LogUtil.m9i(r9, r10, r8)
            r8 = r14
            goto L_0x00c9
        L_0x00bc:
            r8 = r2
        L_0x00bd:
            java.lang.Class<android.telephony.TelephonyManager> r9 = android.telephony.TelephonyManager.class
            java.lang.Object r9 = r11.getSystemService(r9)
            android.telephony.TelephonyManager r9 = (android.telephony.TelephonyManager) r9
            android.net.Uri r8 = r9.getVoicemailRingtoneUri(r8)
        L_0x00c9:
            r5.setSound(r8)
            r8 = -1
            if (r2 != 0) goto L_0x00e5
            java.lang.Object[] r9 = new java.lang.Object[r6]
            java.lang.String r10 = "VisualVoicemailNotifier.getNotificationDefaultFlags"
            com.android.dialer.common.LogUtil.m9i(r10, r7, r9)
            android.telecom.PhoneAccountHandle r7 = getFallbackAccount(r11)
            if (r7 != 0) goto L_0x00e6
            java.lang.Object[] r7 = new java.lang.Object[r6]
            java.lang.String r9 = "no fallback handle, using default vibration"
            com.android.dialer.common.LogUtil.m9i(r10, r9, r7)
            r7 = r8
            goto L_0x00f7
        L_0x00e5:
            r7 = r2
        L_0x00e6:
            java.lang.Class<android.telephony.TelephonyManager> r9 = android.telephony.TelephonyManager.class
            java.lang.Object r9 = r11.getSystemService(r9)
            android.telephony.TelephonyManager r9 = (android.telephony.TelephonyManager) r9
            boolean r7 = r9.isVoicemailVibrationEnabled(r7)
            if (r7 == 0) goto L_0x00f6
            r7 = r0
            goto L_0x00f7
        L_0x00f6:
            r7 = r6
        L_0x00f7:
            r5.setDefaults(r7)
            java.lang.String r7 = r15.transcription
            boolean r7 = android.text.TextUtils.isEmpty(r7)
            if (r7 != 0) goto L_0x0120
            com.android.dialer.logging.LoggingBindings r7 = com.android.dialer.logging.Logger.get(r11)
            com.android.dialer.logging.DialerImpression$Type r8 = com.android.dialer.logging.DialerImpression$Type.VVM_NOTIFICATION_CREATED_WITH_TRANSCRIPTION
            com.android.dialer.logging.LoggingBindingsStub r7 = (com.android.dialer.logging.LoggingBindingsStub) r7
            r7.logImpression(r8)
            java.lang.String r7 = r15.transcription
            r5.setContentText(r7)
            android.support.v4.app.NotificationCompat$BigTextStyle r7 = new android.support.v4.app.NotificationCompat$BigTextStyle
            r7.<init>()
            java.lang.String r8 = r15.transcription
            r7.bigText(r8)
            r5.setStyle(r7)
            goto L_0x018e
        L_0x0120:
            int r7 = r15.transcriptionState
            r9 = -2
            if (r7 == r9) goto L_0x0179
            if (r7 == r8) goto L_0x0163
            if (r7 == r3) goto L_0x014d
            if (r7 == r0) goto L_0x0137
            com.android.dialer.logging.LoggingBindings r7 = com.android.dialer.logging.Logger.get(r11)
            com.android.dialer.logging.DialerImpression$Type r8 = com.android.dialer.logging.DialerImpression$Type.VVM_NOTIFICATION_CREATED_WITH_NO_TRANSCRIPTION
            com.android.dialer.logging.LoggingBindingsStub r7 = (com.android.dialer.logging.LoggingBindingsStub) r7
            r7.logImpression(r8)
            goto L_0x018e
        L_0x0137:
            com.android.dialer.logging.LoggingBindings r7 = com.android.dialer.logging.Logger.get(r11)
            com.android.dialer.logging.DialerImpression$Type r8 = com.android.dialer.logging.DialerImpression$Type.VVM_NOTIFICATION_CREATED_WITH_TRANSCRIPTION_FAILURE
            com.android.dialer.logging.LoggingBindingsStub r7 = (com.android.dialer.logging.LoggingBindingsStub) r7
            r7.logImpression(r8)
            r7 = 2131821581(0x7f11040d, float:1.927591E38)
            java.lang.String r7 = r11.getString(r7)
            r5.setContentText(r7)
            goto L_0x018e
        L_0x014d:
            com.android.dialer.logging.LoggingBindings r7 = com.android.dialer.logging.Logger.get(r11)
            com.android.dialer.logging.DialerImpression$Type r8 = com.android.dialer.logging.DialerImpression$Type.VVM_NOTIFICATION_CREATED_WITH_IN_PROGRESS
            com.android.dialer.logging.LoggingBindingsStub r7 = (com.android.dialer.logging.LoggingBindingsStub) r7
            r7.logImpression(r8)
            r7 = 2131821584(0x7f110410, float:1.9275915E38)
            java.lang.String r7 = r11.getString(r7)
            r5.setContentText(r7)
            goto L_0x018e
        L_0x0163:
            com.android.dialer.logging.LoggingBindings r7 = com.android.dialer.logging.Logger.get(r11)
            com.android.dialer.logging.DialerImpression$Type r8 = com.android.dialer.logging.DialerImpression$Type.VVM_NOTIFICATION_CREATED_WITH_TRANSCRIPTION_FAILURE
            com.android.dialer.logging.LoggingBindingsStub r7 = (com.android.dialer.logging.LoggingBindingsStub) r7
            r7.logImpression(r8)
            r7 = 2131821583(0x7f11040f, float:1.9275913E38)
            java.lang.String r7 = r11.getString(r7)
            r5.setContentText(r7)
            goto L_0x018e
        L_0x0179:
            com.android.dialer.logging.LoggingBindings r7 = com.android.dialer.logging.Logger.get(r11)
            com.android.dialer.logging.DialerImpression$Type r8 = com.android.dialer.logging.DialerImpression$Type.VVM_NOTIFICATION_CREATED_WITH_TRANSCRIPTION_FAILURE
            com.android.dialer.logging.LoggingBindingsStub r7 = (com.android.dialer.logging.LoggingBindingsStub) r7
            r7.logImpression(r8)
            r7 = 2131821582(0x7f11040e, float:1.9275911E38)
            java.lang.String r7 = r11.getString(r7)
            r5.setContentText(r7)
        L_0x018e:
            android.net.Uri r7 = r15.voicemailUri
            if (r7 == 0) goto L_0x0199
            android.app.PendingIntent r7 = com.android.dialer.app.calllog.CallLogNotificationsService.createMarkSingleNewVoicemailAsOldIntent(r11, r7)
            r5.setDeleteIntent(r7)
        L_0x0199:
            int r7 = android.os.Build.VERSION.SDK_INT
            java.lang.String r2 = com.android.dialer.notification.NotificationChannelManager.getVoicemailChannelId(r11, r2)
            r5.setChannelId(r2)
            r5.setGroupAlertBehavior(r3)
            com.android.dialer.app.contactinfo.ContactPhotoLoader r2 = new com.android.dialer.app.contactinfo.ContactPhotoLoader
            r2.<init>(r11, r4)
            android.graphics.Bitmap r2 = r2.loadPhotoIcon()
            if (r2 == 0) goto L_0x01b3
            r5.setLargeIcon(r2)
        L_0x01b3:
            android.app.PendingIntent r15 = newVoicemailIntent(r11, r15)
            r5.setContentIntent(r15)
            com.android.dialer.logging.LoggingBindings r15 = com.android.dialer.logging.Logger.get(r11)
            com.android.dialer.logging.DialerImpression$Type r2 = com.android.dialer.logging.DialerImpression$Type.VVM_NOTIFICATION_CREATED
            com.android.dialer.logging.LoggingBindingsStub r15 = (com.android.dialer.logging.LoggingBindingsStub) r15
            r15.logImpression(r2)
            android.app.Notification r15 = r5.build()
            com.android.dialer.notification.DialerNotificationManager.notify(r11, r1, r3, r15)
            goto L_0x006b
        L_0x01ce:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.app.calllog.CallLogAsyncTaskUtil.showNotifications(android.content.Context, java.util.List, java.util.Map, java.lang.String, boolean):void");
    }
}
