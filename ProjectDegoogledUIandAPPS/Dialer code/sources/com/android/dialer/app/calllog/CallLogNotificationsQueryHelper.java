package com.android.dialer.app.calllog;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.CallLog;
import android.support.design.R$dimen;
import android.support.p002v7.appcompat.R$style;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import com.android.dialer.R;
import com.android.dialer.calllogutils.CallLogDates;
import com.android.dialer.common.LogUtil;
import com.android.dialer.configprovider.ConfigProviderComponent;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import com.android.dialer.phonenumbercache.ContactInfo;
import com.android.dialer.phonenumbercache.ContactInfoHelper;
import com.android.dialer.phonenumberutil.PhoneNumberHelper;
import com.android.dialer.util.PermissionsUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CallLogNotificationsQueryHelper {
    static final String CONFIG_NEW_VOICEMAIL_NOTIFICATION_THRESHOLD_OFFSET = "new_voicemail_notification_threshold";
    private final ContactInfoHelper contactInfoHelper;
    private final Context context;
    private final String currentCountryIso;
    private final NewCallsQuery newCallsQuery;

    private static final class DefaultNewCallsQuery implements NewCallsQuery {
        private static final String[] PROJECTION = {"_id", "number", "voicemail_uri", "presentation", "subscription_component_name", "subscription_id", "transcription", "countryiso", "date"};
        private static final String[] PROJECTION_O;
        private final ContentResolver contentResolver;
        private final Context context;

        private static /* synthetic */ void $closeResource(Throwable th, AutoCloseable autoCloseable) {
            if (th != null) {
                try {
                    autoCloseable.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            } else {
                autoCloseable.close();
            }
        }

        static {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(Arrays.asList(PROJECTION));
            arrayList.add("transcription_state");
            PROJECTION_O = (String[]) arrayList.toArray(new String[arrayList.size()]);
        }

        /* synthetic */ DefaultNewCallsQuery(Context context2, ContentResolver contentResolver2, C03131 r3) {
            this.context = context2;
            this.contentResolver = contentResolver2;
        }

        private NewCall createNewCallsFromCursor(Cursor cursor) {
            Uri uri;
            Cursor cursor2 = cursor;
            String string = cursor2.getString(2);
            Uri withAppendedId = ContentUris.withAppendedId(CallLog.Calls.CONTENT_URI_WITH_VOICEMAIL, cursor2.getLong(0));
            if (string == null) {
                uri = null;
            } else {
                uri = Uri.parse(string);
            }
            String string2 = cursor2.getString(1);
            int i = cursor2.getInt(3);
            String string3 = cursor2.getString(4);
            String string4 = cursor2.getString(5);
            String string5 = cursor2.getString(6);
            String string6 = cursor2.getString(7);
            long j = cursor2.getLong(8);
            int i2 = Build.VERSION.SDK_INT;
            return new NewCall(withAppendedId, uri, string2, i, string3, string4, string5, string6, j, cursor2.getInt(9));
        }

        /* JADX WARNING: Code restructure failed: missing block: B:28:0x00c9, code lost:
            r12 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
            $closeResource(r10, r11);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x00cd, code lost:
            throw r12;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.util.List<com.android.dialer.app.calllog.CallLogNotificationsQueryHelper.NewCall> query(int r11, long r12) {
            /*
                r10 = this;
                android.content.Context r0 = r10.context
                java.lang.String r1 = "android.permission.READ_CALL_LOG"
                boolean r0 = com.android.dialer.util.PermissionsUtil.hasPermission(r0, r1)
                r1 = 0
                java.lang.String r2 = "CallLogNotificationsQueryHelper.DefaultNewCallsQuery.query"
                r3 = 0
                if (r0 != 0) goto L_0x0016
                java.lang.Object[] r10 = new java.lang.Object[r1]
                java.lang.String r11 = "no READ_CALL_LOG permission, returning null for calls lookup."
                com.android.dialer.common.LogUtil.m10w(r2, r11, r10)
                return r3
            L_0x0016:
                com.android.dialer.common.database.Selection$Builder r0 = com.android.dialer.common.database.Selection.builder()
                java.lang.String r4 = "new"
                com.android.dialer.common.database.Selection$Column r4 = com.android.dialer.common.database.Selection.column(r4)
                java.lang.String r5 = "= 1"
                com.android.dialer.common.database.Selection r4 = r4.mo5866is(r5)
                r0.and(r4)
                java.lang.String r4 = "type"
                com.android.dialer.common.database.Selection$Column r4 = com.android.dialer.common.database.Selection.column(r4)
                java.lang.Integer r5 = java.lang.Integer.valueOf(r11)
                java.lang.String r6 = "="
                com.android.dialer.common.database.Selection r4 = r4.mo5867is(r6, r5)
                r0.and(r4)
                java.lang.String r4 = "is_read"
                com.android.dialer.common.database.Selection$Column r4 = com.android.dialer.common.database.Selection.column(r4)
                java.lang.String r5 = "IS NOT 1"
                com.android.dialer.common.database.Selection r4 = r4.mo5866is(r5)
                r0.and(r4)
                r4 = 4
                if (r11 != r4) goto L_0x005d
                java.lang.String r11 = "deleted"
                com.android.dialer.common.database.Selection$Column r11 = com.android.dialer.common.database.Selection.column(r11)
                java.lang.String r4 = " = 0"
                com.android.dialer.common.database.Selection r11 = r11.mo5866is(r4)
                r0.and(r11)
            L_0x005d:
                r4 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r11 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
                if (r11 == 0) goto L_0x008e
                java.lang.String r11 = "date"
                com.android.dialer.common.database.Selection$Column r4 = com.android.dialer.common.database.Selection.column(r11)
                java.lang.String r5 = "IS NULL"
                com.android.dialer.common.database.Selection r4 = r4.mo5866is(r5)
                com.android.dialer.common.database.Selection$Builder r4 = r4.buildUpon()
                com.android.dialer.common.database.Selection$Column r11 = com.android.dialer.common.database.Selection.column(r11)
                java.lang.Long r12 = java.lang.Long.valueOf(r12)
                java.lang.String r13 = ">="
                com.android.dialer.common.database.Selection r11 = r11.mo5867is(r13, r12)
                r4.mo5864or(r11)
                com.android.dialer.common.database.Selection r11 = r4.build()
                r0.and(r11)
            L_0x008e:
                com.android.dialer.common.database.Selection r11 = r0.build()
                android.content.ContentResolver r4 = r10.contentResolver     // Catch:{ RuntimeException -> 0x00ce }
                android.net.Uri r5 = android.provider.CallLog.Calls.CONTENT_URI_WITH_VOICEMAIL     // Catch:{ RuntimeException -> 0x00ce }
                int r12 = android.os.Build.VERSION.SDK_INT     // Catch:{ RuntimeException -> 0x00ce }
                java.lang.String[] r6 = PROJECTION_O     // Catch:{ RuntimeException -> 0x00ce }
                java.lang.String r7 = r11.getSelection()     // Catch:{ RuntimeException -> 0x00ce }
                java.lang.String[] r8 = r11.getSelectionArgs()     // Catch:{ RuntimeException -> 0x00ce }
                java.lang.String r9 = "date DESC"
                android.database.Cursor r11 = r4.query(r5, r6, r7, r8, r9)     // Catch:{ RuntimeException -> 0x00ce }
                if (r11 != 0) goto L_0x00b0
                if (r11 == 0) goto L_0x00af
                $closeResource(r3, r11)     // Catch:{ RuntimeException -> 0x00ce }
            L_0x00af:
                return r3
            L_0x00b0:
                java.util.ArrayList r12 = new java.util.ArrayList     // Catch:{ all -> 0x00c7 }
                r12.<init>()     // Catch:{ all -> 0x00c7 }
            L_0x00b5:
                boolean r13 = r11.moveToNext()     // Catch:{ all -> 0x00c7 }
                if (r13 == 0) goto L_0x00c3
                com.android.dialer.app.calllog.CallLogNotificationsQueryHelper$NewCall r13 = r10.createNewCallsFromCursor(r11)     // Catch:{ all -> 0x00c7 }
                r12.add(r13)     // Catch:{ all -> 0x00c7 }
                goto L_0x00b5
            L_0x00c3:
                $closeResource(r3, r11)     // Catch:{ RuntimeException -> 0x00ce }
                return r12
            L_0x00c7:
                r10 = move-exception
                throw r10     // Catch:{ all -> 0x00c9 }
            L_0x00c9:
                r12 = move-exception
                $closeResource(r10, r11)     // Catch:{ RuntimeException -> 0x00ce }
                throw r12     // Catch:{ RuntimeException -> 0x00ce }
            L_0x00ce:
                java.lang.Object[] r10 = new java.lang.Object[r1]
                java.lang.String r11 = "exception when querying Contacts Provider for calls lookup"
                com.android.dialer.common.LogUtil.m10w(r2, r11, r10)
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.app.calllog.CallLogNotificationsQueryHelper.DefaultNewCallsQuery.query(int, long):java.util.List");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:21:0x006f, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0070, code lost:
            $closeResource(r8, r9);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0073, code lost:
            throw r0;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.android.dialer.app.calllog.CallLogNotificationsQueryHelper.NewCall queryUnreadVoicemail(android.net.Uri r9) {
            /*
                r8 = this;
                android.content.Context r0 = r8.context
                java.lang.String r1 = "android.permission.READ_CALL_LOG"
                boolean r0 = com.android.dialer.util.PermissionsUtil.hasPermission(r0, r1)
                r1 = 0
                if (r0 != 0) goto L_0x0016
                r8 = 0
                java.lang.Object[] r8 = new java.lang.Object[r8]
                java.lang.String r9 = "CallLogNotificationsQueryHelper.DefaultNewCallsQuery.query"
                java.lang.String r0 = "No READ_CALL_LOG permission, returning null for calls lookup."
                com.android.dialer.common.LogUtil.m10w(r9, r0, r8)
                return r1
            L_0x0016:
                java.lang.String r0 = "voicemail_uri"
                com.android.dialer.common.database.Selection$Column r0 = com.android.dialer.common.database.Selection.column(r0)
                java.lang.String r2 = "="
                com.android.dialer.common.database.Selection r9 = r0.mo5867is(r2, r9)
                com.android.dialer.common.database.Selection$Builder r9 = r9.buildUpon()
                java.lang.String r0 = "is_read"
                com.android.dialer.common.database.Selection$Column r0 = com.android.dialer.common.database.Selection.column(r0)
                r2 = 1
                java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
                java.lang.String r3 = "IS NOT"
                com.android.dialer.common.database.Selection r0 = r0.mo5867is(r3, r2)
                r9.and(r0)
                com.android.dialer.common.database.Selection r9 = r9.build()
                android.content.ContentResolver r2 = r8.contentResolver
                android.net.Uri r3 = android.provider.CallLog.Calls.CONTENT_URI_WITH_VOICEMAIL
                int r0 = android.os.Build.VERSION.SDK_INT
                java.lang.String[] r4 = PROJECTION_O
                java.lang.String r5 = r9.getSelection()
                java.lang.String[] r6 = r9.getSelectionArgs()
                r7 = 0
                android.database.Cursor r9 = r2.query(r3, r4, r5, r6, r7)
                if (r9 != 0) goto L_0x005b
                if (r9 == 0) goto L_0x005a
                $closeResource(r1, r9)
            L_0x005a:
                return r1
            L_0x005b:
                boolean r0 = r9.moveToFirst()     // Catch:{ all -> 0x006d }
                if (r0 != 0) goto L_0x0065
                $closeResource(r1, r9)
                return r1
            L_0x0065:
                com.android.dialer.app.calllog.CallLogNotificationsQueryHelper$NewCall r8 = r8.createNewCallsFromCursor(r9)     // Catch:{ all -> 0x006d }
                $closeResource(r1, r9)
                return r8
            L_0x006d:
                r8 = move-exception
                throw r8     // Catch:{ all -> 0x006f }
            L_0x006f:
                r0 = move-exception
                $closeResource(r8, r9)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.app.calllog.CallLogNotificationsQueryHelper.DefaultNewCallsQuery.queryUnreadVoicemail(android.net.Uri):com.android.dialer.app.calllog.CallLogNotificationsQueryHelper$NewCall");
        }
    }

    public static final class NewCall {
        public final String accountComponentName;
        public final String accountId;
        public final Uri callsUri;
        public final String countryIso;
        public final long dateMs;
        public final String number;
        public final int numberPresentation;
        public final String transcription;
        public final int transcriptionState;
        public final Uri voicemailUri;

        public NewCall(Uri uri, Uri uri2, String str, int i, String str2, String str3, String str4, String str5, long j, int i2) {
            this.callsUri = uri;
            this.voicemailUri = uri2;
            this.number = str;
            this.numberPresentation = i;
            this.accountComponentName = str2;
            this.accountId = str3;
            this.transcription = str4;
            this.countryIso = str5;
            this.dateMs = j;
            this.transcriptionState = i2;
        }
    }

    public interface NewCallsQuery {
    }

    CallLogNotificationsQueryHelper(Context context2, NewCallsQuery newCallsQuery2, ContactInfoHelper contactInfoHelper2, String str) {
        this.context = context2;
        this.newCallsQuery = newCallsQuery2;
        this.contactInfoHelper = contactInfoHelper2;
        this.currentCountryIso = str;
    }

    public static CallLogNotificationsQueryHelper getInstance(Context context2) {
        ContentResolver contentResolver = context2.getContentResolver();
        String currentCountryIso2 = R$style.getCurrentCountryIso(context2);
        return new CallLogNotificationsQueryHelper(context2, new DefaultNewCallsQuery(context2.getApplicationContext(), contentResolver, (C03131) null), new ContactInfoHelper(context2, currentCountryIso2), currentCountryIso2);
    }

    public static void markAllMissedCallsInCallLogAsRead(Context context2) {
        markMissedCallsInCallLogAsRead(context2, (Uri) null);
    }

    private static void markMissedCallsInCallLogAsRead(Context context2, Uri uri) {
        if (!R$dimen.isUserUnlocked(context2)) {
            LogUtil.m8e("CallLogNotificationsQueryHelper.markMissedCallsInCallLogAsRead", "locked", new Object[0]);
        } else if (!PermissionsUtil.hasPhonePermissions(context2)) {
            LogUtil.m8e("CallLogNotificationsQueryHelper.markMissedCallsInCallLogAsRead", "no phone permission", new Object[0]);
        } else if (!PermissionsUtil.hasCallLogWritePermissions(context2)) {
            LogUtil.m8e("CallLogNotificationsQueryHelper.markMissedCallsInCallLogAsRead", "no call log write permission", new Object[0]);
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put("new", 0);
            contentValues.put("is_read", 1);
            try {
                ContentResolver contentResolver = context2.getContentResolver();
                if (uri == null) {
                    uri = CallLog.Calls.CONTENT_URI;
                }
                contentResolver.update(uri, contentValues, "new" + " = 1 AND " + "type" + " = ?", new String[]{Integer.toString(3)});
            } catch (IllegalArgumentException e) {
                LogUtil.m7e("CallLogNotificationsQueryHelper.markMissedCallsInCallLogAsRead", "contacts provider update command failed", (Throwable) e);
            }
        }
    }

    public static void markSingleMissedCallInCallLogAsRead(Context context2, Uri uri) {
        if (uri == null) {
            LogUtil.m8e("CallLogNotificationsQueryHelper.markSingleMissedCallInCallLogAsRead", "call URI is null, unable to mark call as read", new Object[0]);
        } else {
            markMissedCallsInCallLogAsRead(context2, uri);
        }
    }

    public ContactInfo getContactInfo(String str, int i, String str2) {
        if (str2 == null) {
            str2 = this.currentCountryIso;
        }
        if (str == null) {
            str = "";
        }
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.number = str;
        contactInfo.formattedNumber = PhoneNumberHelper.formatNumber(this.context, str, str2);
        contactInfo.normalizedNumber = PhoneNumberUtils.formatNumberToE164(str, str2);
        contactInfo.name = CallLogDates.getDisplayName(this.context, str, i, false).toString();
        if (!TextUtils.isEmpty(contactInfo.name)) {
            return contactInfo;
        }
        ContactInfo lookupNumber = this.contactInfoHelper.lookupNumber(str, str2, -1);
        if (lookupNumber != null && !TextUtils.isEmpty(lookupNumber.name)) {
            return lookupNumber;
        }
        if (!TextUtils.isEmpty(contactInfo.formattedNumber)) {
            contactInfo.name = contactInfo.formattedNumber;
        } else if (!TextUtils.isEmpty(str)) {
            contactInfo.name = str;
        } else {
            contactInfo.name = this.context.getResources().getString(R.string.unknown);
        }
        return contactInfo;
    }

    /* access modifiers changed from: package-private */
    public NewCallsQuery getNewCallsQuery() {
        return this.newCallsQuery;
    }

    public List<NewCall> getNewMissedCalls() {
        return ((DefaultNewCallsQuery) this.newCallsQuery).query(3, Long.MAX_VALUE);
    }

    public List<NewCall> getNewVoicemails() {
        NewCallsQuery newCallsQuery2 = this.newCallsQuery;
        return ((DefaultNewCallsQuery) newCallsQuery2).query(4, System.currentTimeMillis() - ((SharedPrefConfigProvider) ConfigProviderComponent.get(this.context).getConfigProvider()).getLong(CONFIG_NEW_VOICEMAIL_NOTIFICATION_THRESHOLD_OFFSET, TimeUnit.DAYS.toMillis(7)));
    }
}
