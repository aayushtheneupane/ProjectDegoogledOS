package com.android.dialer.phonenumbercache;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.p002v7.appcompat.R$style;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import com.android.dialer.common.Assert;
import com.android.dialer.logging.ContactSource$Type;
import com.android.dialer.oem.CequintCallerIdManager;
import com.android.dialer.phonenumberutil.PhoneNumberHelper;
import org.json.JSONException;
import org.json.JSONObject;

public class ContactInfoHelper {
    private final Context context;
    private final String currentCountryIso;

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

    public ContactInfoHelper(Context context2, String str) {
        this.context = context2;
        this.currentCountryIso = str;
        PhoneNumberCache.get(this.context).getCachedNumberLookupService();
    }

    private ContactInfo createEmptyContactInfoForNumber(String str, String str2) {
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.number = str;
        Uri uri = null;
        contactInfo.formattedNumber = formatPhoneNumber(str, (String) null, str2);
        contactInfo.normalizedNumber = PhoneNumberUtils.formatNumberToE164(str, str2);
        String str3 = contactInfo.formattedNumber;
        try {
            uri = ContactsContract.Contacts.CONTENT_LOOKUP_URI.buildUpon().appendPath("encoded").appendQueryParameter("directory", String.valueOf(Long.MAX_VALUE)).encodedFragment(new JSONObject().put("display_name", str3).put("display_name_source", 20).put("vnd.android.cursor.item/contact", new JSONObject().put("vnd.android.cursor.item/phone_v2", new JSONObject().put("data1", str3).put("data2", 0))).toString()).build();
        } catch (JSONException unused) {
        }
        contactInfo.lookupUri = uri;
        return contactInfo;
    }

    private ContactInfo createPhoneLookupContactInfo(Cursor cursor, String str) {
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.lookupKey = str;
        contactInfo.lookupUri = ContactsContract.Contacts.getLookupUri(cursor.getLong(0), str);
        contactInfo.name = cursor.getString(1);
        contactInfo.type = cursor.getInt(2);
        contactInfo.label = cursor.getString(3);
        contactInfo.number = cursor.getString(4);
        contactInfo.normalizedNumber = cursor.getString(5);
        contactInfo.photoId = cursor.getLong(6);
        contactInfo.photoUri = R$style.parseUriOrNull(cursor.getString(8));
        contactInfo.formattedNumber = null;
        contactInfo.userType = R$style.determineUserType((Long) null, Long.valueOf(cursor.getLong(0)));
        contactInfo.contactExists = true;
        return contactInfo;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003a, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003b, code lost:
        $closeResource(r8, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003e, code lost:
        throw r9;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void fillAdditionalContactInfo(android.content.Context r8, com.android.dialer.phonenumbercache.ContactInfo r9) {
        /*
            r7 = this;
            java.lang.String r7 = r9.number
            if (r7 != 0) goto L_0x0005
            return
        L_0x0005:
            android.net.Uri r0 = android.provider.ContactsContract.CommonDataKinds.Phone.CONTENT_FILTER_URI
            java.lang.String r7 = android.net.Uri.encode(r7)
            android.net.Uri r2 = android.net.Uri.withAppendedPath(r0, r7)
            android.content.ContentResolver r1 = r8.getContentResolver()
            java.lang.String[] r3 = com.android.dialer.phonenumbercache.PhoneQuery.ADDITIONAL_CONTACT_INFO_PROJECTION
            r4 = 0
            r5 = 0
            r6 = 0
            android.database.Cursor r7 = r1.query(r2, r3, r4, r5, r6)
            r8 = 0
            if (r7 == 0) goto L_0x003f
            boolean r0 = r7.moveToFirst()     // Catch:{ all -> 0x0038 }
            if (r0 != 0) goto L_0x0026
            goto L_0x003f
        L_0x0026:
            r0 = 0
            java.lang.String r0 = r7.getString(r0)     // Catch:{ all -> 0x0038 }
            r9.nameAlternative = r0     // Catch:{ all -> 0x0038 }
            r0 = 1
            int r0 = r7.getInt(r0)     // Catch:{ all -> 0x0038 }
            r9.carrierPresence = r0     // Catch:{ all -> 0x0038 }
            $closeResource(r8, r7)
            return
        L_0x0038:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x003a }
        L_0x003a:
            r9 = move-exception
            $closeResource(r8, r7)
            throw r9
        L_0x003f:
            if (r7 == 0) goto L_0x0044
            $closeResource(r8, r7)
        L_0x0044:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.phonenumbercache.ContactInfoHelper.fillAdditionalContactInfo(android.content.Context, com.android.dialer.phonenumbercache.ContactInfo):void");
    }

    private String formatPhoneNumber(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (PhoneNumberHelper.isUriNumber(str)) {
            return str;
        }
        if (TextUtils.isEmpty(str3)) {
            str3 = this.currentCountryIso;
        }
        return PhoneNumberHelper.formatNumber(this.context, str, str2, str3);
    }

    public static Uri getContactInfoLookupUri(String str, long j) {
        Uri.Builder appendQueryParameter = ContactsContract.PhoneLookup.ENTERPRISE_CONTENT_FILTER_URI.buildUpon().appendPath(str).appendQueryParameter("sip", String.valueOf(PhoneNumberHelper.isUriNumber(str)));
        if (j != -1) {
            appendQueryParameter.appendQueryParameter("directory", String.valueOf(j));
        }
        return appendQueryParameter.build();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0048, code lost:
        if (r7 != null) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004a, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x005a, code lost:
        if (r7 != null) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x005d, code lost:
        return null;
     */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0061  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String lookUpDisplayNameAlternative(android.content.Context r7, java.lang.String r8, long r9, java.lang.Long r11) {
        /*
            r0 = 0
            if (r8 == 0) goto L_0x0065
            r1 = 1
            int r9 = (r9 > r1 ? 1 : (r9 == r1 ? 0 : -1))
            if (r9 != 0) goto L_0x000a
            goto L_0x0065
        L_0x000a:
            if (r11 == 0) goto L_0x0022
            long r9 = r11.longValue()
            boolean r9 = android.provider.ContactsContract.Directory.isEnterpriseDirectoryId(r9)
            if (r9 == 0) goto L_0x0017
            return r0
        L_0x0017:
            long r9 = r11.longValue()
            boolean r9 = android.provider.ContactsContract.Directory.isRemoteDirectoryId(r9)
            if (r9 == 0) goto L_0x0022
            return r0
        L_0x0022:
            android.net.Uri r9 = android.provider.ContactsContract.Contacts.CONTENT_LOOKUP_URI
            android.net.Uri r2 = android.net.Uri.withAppendedPath(r9, r8)
            android.content.ContentResolver r1 = r7.getContentResolver()     // Catch:{ IllegalArgumentException -> 0x0051, all -> 0x004e }
            java.lang.String[] r3 = com.android.dialer.phonenumbercache.PhoneQuery.DISPLAY_NAME_ALTERNATIVE_PROJECTION     // Catch:{ IllegalArgumentException -> 0x0051, all -> 0x004e }
            r4 = 0
            r5 = 0
            r6 = 0
            android.database.Cursor r7 = r1.query(r2, r3, r4, r5, r6)     // Catch:{ IllegalArgumentException -> 0x0051, all -> 0x004e }
            if (r7 == 0) goto L_0x0048
            boolean r8 = r7.moveToFirst()     // Catch:{ IllegalArgumentException -> 0x0046 }
            if (r8 == 0) goto L_0x0048
            r8 = 0
            java.lang.String r8 = r7.getString(r8)     // Catch:{ IllegalArgumentException -> 0x0046 }
            r7.close()
            return r8
        L_0x0046:
            r8 = move-exception
            goto L_0x0053
        L_0x0048:
            if (r7 == 0) goto L_0x005d
        L_0x004a:
            r7.close()
            goto L_0x005d
        L_0x004e:
            r8 = move-exception
            r7 = r0
            goto L_0x005f
        L_0x0051:
            r8 = move-exception
            r7 = r0
        L_0x0053:
            java.lang.String r9 = "ContactInfoHelper"
            java.lang.String r10 = "IllegalArgumentException in lookUpDisplayNameAlternative"
            com.android.dialer.common.LogUtil.m7e((java.lang.String) r9, (java.lang.String) r10, (java.lang.Throwable) r8)     // Catch:{ all -> 0x005e }
            if (r7 == 0) goto L_0x005d
            goto L_0x004a
        L_0x005d:
            return r0
        L_0x005e:
            r8 = move-exception
        L_0x005f:
            if (r7 == 0) goto L_0x0064
            r7.close()
        L_0x0064:
            throw r8
        L_0x0065:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.phonenumbercache.ContactInfoHelper.lookUpDisplayNameAlternative(android.content.Context, java.lang.String, long, java.lang.Long):java.lang.String");
    }

    private ContactInfo queryContactInfoForPhoneNumber(String str, String str2, long j) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        ContactInfo lookupContactFromUri = lookupContactFromUri(getContactInfoLookupUri(str, j));
        if (!(lookupContactFromUri == null || lookupContactFromUri == ContactInfo.EMPTY)) {
            lookupContactFromUri.formattedNumber = formatPhoneNumber(str, (String) null, str2);
            if (j == -1) {
                lookupContactFromUri.sourceType = ContactSource$Type.SOURCE_TYPE_DIRECTORY;
            } else {
                lookupContactFromUri.sourceType = ContactSource$Type.SOURCE_TYPE_EXTENDED;
            }
        }
        return lookupContactFromUri;
    }

    public boolean hasName(ContactInfo contactInfo) {
        return contactInfo != null && !TextUtils.isEmpty(contactInfo.name);
    }

    public boolean isBusiness(ContactSource$Type contactSource$Type) {
        return false;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0056, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0057, code lost:
        if (r1 != null) goto L_0x0059;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0059, code lost:
        $closeResource(r8, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x005c, code lost:
        throw r9;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.android.dialer.phonenumbercache.ContactInfo lookupContactFromUri(android.net.Uri r9) {
        /*
            r8 = this;
            r0 = 0
            if (r9 != 0) goto L_0x0004
            return r0
        L_0x0004:
            android.content.Context r1 = r8.context
            boolean r1 = com.android.dialer.util.PermissionsUtil.hasContactsReadPermissions(r1)
            if (r1 != 0) goto L_0x000f
            com.android.dialer.phonenumbercache.ContactInfo r8 = com.android.dialer.phonenumbercache.ContactInfo.EMPTY
            return r8
        L_0x000f:
            android.content.Context r1 = r8.context
            android.content.ContentResolver r2 = r1.getContentResolver()
            java.lang.String[] r4 = com.android.dialer.phonenumbercache.PhoneQuery.getPhoneLookupProjection()
            r5 = 0
            r6 = 0
            r7 = 0
            r3 = r9
            android.database.Cursor r1 = r2.query(r3, r4, r5, r6, r7)
            if (r1 != 0) goto L_0x0029
            if (r1 == 0) goto L_0x0028
            $closeResource(r0, r1)
        L_0x0028:
            return r0
        L_0x0029:
            boolean r2 = r1.moveToFirst()     // Catch:{ all -> 0x0054 }
            if (r2 != 0) goto L_0x0035
            com.android.dialer.phonenumbercache.ContactInfo r8 = com.android.dialer.phonenumbercache.ContactInfo.EMPTY     // Catch:{ all -> 0x0054 }
            $closeResource(r0, r1)
            return r8
        L_0x0035:
            r2 = 4
            boolean r9 = com.android.dialer.phonenumberutil.PhoneNumberHelper.updateCursorToMatchContactLookupUri(r1, r2, r9)     // Catch:{ all -> 0x0054 }
            if (r9 != 0) goto L_0x0042
            com.android.dialer.phonenumbercache.ContactInfo r8 = com.android.dialer.phonenumbercache.ContactInfo.EMPTY     // Catch:{ all -> 0x0054 }
            $closeResource(r0, r1)
            return r8
        L_0x0042:
            r9 = 7
            java.lang.String r9 = r1.getString(r9)     // Catch:{ all -> 0x0054 }
            com.android.dialer.phonenumbercache.ContactInfo r9 = r8.createPhoneLookupContactInfo(r1, r9)     // Catch:{ all -> 0x0054 }
            android.content.Context r2 = r8.context     // Catch:{ all -> 0x0054 }
            r8.fillAdditionalContactInfo(r2, r9)     // Catch:{ all -> 0x0054 }
            $closeResource(r0, r1)
            return r9
        L_0x0054:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x0056 }
        L_0x0056:
            r9 = move-exception
            if (r1 == 0) goto L_0x005c
            $closeResource(r8, r1)
        L_0x005c:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.phonenumbercache.ContactInfoHelper.lookupContactFromUri(android.net.Uri):com.android.dialer.phonenumbercache.ContactInfo");
    }

    public ContactInfo lookupNumber(String str, String str2) {
        return lookupNumber(str, str2, -1);
    }

    public ContactInfo lookupNumberInRemoteDirectory(String str, String str2) {
        return createEmptyContactInfoForNumber(str, str2);
    }

    public void updateCachedNumberLookupService(ContactInfo contactInfo) {
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x0127 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0128  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateCallLogContactInfo(java.lang.String r17, java.lang.String r18, com.android.dialer.phonenumbercache.ContactInfo r19, com.android.dialer.phonenumbercache.ContactInfo r20) {
        /*
            r16 = this;
            r0 = r16
            r1 = r19
            r2 = r20
            android.content.Context r3 = r0.context
            java.lang.String r4 = "android.permission.WRITE_CALL_LOG"
            boolean r3 = com.android.dialer.util.PermissionsUtil.hasPermission(r3, r4)
            if (r3 != 0) goto L_0x0011
            return
        L_0x0011:
            android.content.ContentValues r3 = new android.content.ContentValues
            r3.<init>()
            java.lang.String r4 = "geocoded_location"
            java.lang.String r5 = "formatted_number"
            java.lang.String r6 = "photo_uri"
            java.lang.String r7 = "photo_id"
            java.lang.String r8 = "normalized_number"
            java.lang.String r9 = "matched_number"
            java.lang.String r10 = "lookup_uri"
            java.lang.String r11 = "numberlabel"
            java.lang.String r12 = "numbertype"
            java.lang.String r13 = "name"
            if (r2 == 0) goto L_0x00de
            java.lang.String r14 = r1.name
            java.lang.String r15 = r2.name
            boolean r14 = android.text.TextUtils.equals(r14, r15)
            if (r14 != 0) goto L_0x003d
            java.lang.String r14 = r1.name
            r3.put(r13, r14)
            r15 = 1
            goto L_0x003e
        L_0x003d:
            r15 = 0
        L_0x003e:
            int r13 = r1.type
            int r14 = r2.type
            if (r13 == r14) goto L_0x004c
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
            r3.put(r12, r13)
            r15 = 1
        L_0x004c:
            java.lang.String r12 = r1.label
            java.lang.String r13 = r2.label
            boolean r12 = android.text.TextUtils.equals(r12, r13)
            if (r12 != 0) goto L_0x005c
            java.lang.String r12 = r1.label
            r3.put(r11, r12)
            r15 = 1
        L_0x005c:
            android.net.Uri r11 = r1.lookupUri
            android.net.Uri r12 = r2.lookupUri
            boolean r11 = android.support.p002v7.appcompat.R$style.areEqual(r11, r12)
            if (r11 != 0) goto L_0x0070
            android.net.Uri r11 = r1.lookupUri
            java.lang.String r11 = android.support.p002v7.appcompat.R$style.uriToString(r11)
            r3.put(r10, r11)
            r15 = 1
        L_0x0070:
            java.lang.String r10 = r1.normalizedNumber
            boolean r10 = android.text.TextUtils.isEmpty(r10)
            if (r10 != 0) goto L_0x0088
            java.lang.String r10 = r1.normalizedNumber
            java.lang.String r11 = r2.normalizedNumber
            boolean r10 = android.text.TextUtils.equals(r10, r11)
            if (r10 != 0) goto L_0x0088
            java.lang.String r10 = r1.normalizedNumber
            r3.put(r8, r10)
            r15 = 1
        L_0x0088:
            java.lang.String r8 = r1.number
            java.lang.String r10 = r2.number
            boolean r8 = android.text.TextUtils.equals(r8, r10)
            if (r8 != 0) goto L_0x0098
            java.lang.String r8 = r1.number
            r3.put(r9, r8)
            r15 = 1
        L_0x0098:
            long r8 = r1.photoId
            long r10 = r2.photoId
            int r10 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r10 == 0) goto L_0x00a8
            java.lang.Long r8 = java.lang.Long.valueOf(r8)
            r3.put(r7, r8)
            r15 = 1
        L_0x00a8:
            android.net.Uri r7 = r1.photoUri
            android.net.Uri r7 = android.support.p002v7.appcompat.R$style.nullForNonContactsUri(r7)
            android.net.Uri r8 = r2.photoUri
            boolean r8 = android.support.p002v7.appcompat.R$style.areEqual(r7, r8)
            if (r8 != 0) goto L_0x00be
            java.lang.String r7 = android.support.p002v7.appcompat.R$style.uriToString(r7)
            r3.put(r6, r7)
            r15 = 1
        L_0x00be:
            java.lang.String r6 = r1.formattedNumber
            java.lang.String r7 = r2.formattedNumber
            boolean r6 = android.text.TextUtils.equals(r6, r7)
            if (r6 != 0) goto L_0x00ce
            java.lang.String r6 = r1.formattedNumber
            r3.put(r5, r6)
            r15 = 1
        L_0x00ce:
            java.lang.String r5 = r1.geoDescription
            java.lang.String r2 = r2.geoDescription
            boolean r2 = android.text.TextUtils.equals(r5, r2)
            if (r2 != 0) goto L_0x0125
            java.lang.String r1 = r1.geoDescription
            r3.put(r4, r1)
            goto L_0x0124
        L_0x00de:
            java.lang.String r2 = r1.name
            r3.put(r13, r2)
            int r2 = r1.type
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r3.put(r12, r2)
            java.lang.String r2 = r1.label
            r3.put(r11, r2)
            android.net.Uri r2 = r1.lookupUri
            java.lang.String r2 = android.support.p002v7.appcompat.R$style.uriToString(r2)
            r3.put(r10, r2)
            java.lang.String r2 = r1.number
            r3.put(r9, r2)
            java.lang.String r2 = r1.normalizedNumber
            r3.put(r8, r2)
            long r8 = r1.photoId
            java.lang.Long r2 = java.lang.Long.valueOf(r8)
            r3.put(r7, r2)
            android.net.Uri r2 = r1.photoUri
            android.net.Uri r2 = android.support.p002v7.appcompat.R$style.nullForNonContactsUri(r2)
            java.lang.String r2 = android.support.p002v7.appcompat.R$style.uriToString(r2)
            r3.put(r6, r2)
            java.lang.String r2 = r1.formattedNumber
            r3.put(r5, r2)
            java.lang.String r1 = r1.geoDescription
            r3.put(r4, r1)
        L_0x0124:
            r15 = 1
        L_0x0125:
            if (r15 != 0) goto L_0x0128
            return
        L_0x0128:
            if (r18 != 0) goto L_0x0142
            android.content.Context r1 = r0.context     // Catch:{ SQLiteFullException -> 0x015d }
            android.content.ContentResolver r1 = r1.getContentResolver()     // Catch:{ SQLiteFullException -> 0x015d }
            android.content.Context r0 = r0.context     // Catch:{ SQLiteFullException -> 0x015d }
            android.net.Uri r0 = com.android.dialer.telecom.TelecomUtil.getCallLogUri(r0)     // Catch:{ SQLiteFullException -> 0x015d }
            java.lang.String r2 = "number = ? AND countryiso IS NULL"
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteFullException -> 0x015d }
            r5 = 0
            r4[r5] = r17     // Catch:{ SQLiteFullException -> 0x015d }
            r1.update(r0, r3, r2, r4)     // Catch:{ SQLiteFullException -> 0x015d }
            goto L_0x0165
        L_0x0142:
            android.content.Context r1 = r0.context     // Catch:{ SQLiteFullException -> 0x015d }
            android.content.ContentResolver r1 = r1.getContentResolver()     // Catch:{ SQLiteFullException -> 0x015d }
            android.content.Context r0 = r0.context     // Catch:{ SQLiteFullException -> 0x015d }
            android.net.Uri r0 = com.android.dialer.telecom.TelecomUtil.getCallLogUri(r0)     // Catch:{ SQLiteFullException -> 0x015d }
            java.lang.String r2 = "number = ? AND countryiso = ?"
            r4 = 2
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteFullException -> 0x015d }
            r5 = 0
            r4[r5] = r17     // Catch:{ SQLiteFullException -> 0x015d }
            r5 = 1
            r4[r5] = r18     // Catch:{ SQLiteFullException -> 0x015d }
            r1.update(r0, r3, r2, r4)     // Catch:{ SQLiteFullException -> 0x015d }
            goto L_0x0165
        L_0x015d:
            r0 = move-exception
            java.lang.String r1 = "ContactInfoHelper"
            java.lang.String r2 = "Unable to update contact info in call log db"
            com.android.dialer.common.LogUtil.m7e((java.lang.String) r1, (java.lang.String) r2, (java.lang.Throwable) r0)
        L_0x0165:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.phonenumbercache.ContactInfoHelper.updateCallLogContactInfo(java.lang.String, java.lang.String, com.android.dialer.phonenumbercache.ContactInfo, com.android.dialer.phonenumbercache.ContactInfo):void");
    }

    public void updateFromCequintCallerId(CequintCallerIdManager cequintCallerIdManager, ContactInfo contactInfo, String str) {
        CequintCallerIdManager.CequintCallerIdContact cachedCequintCallerIdContact;
        Assert.isWorkerThread();
        if (CequintCallerIdManager.isCequintCallerIdEnabled(this.context) && cequintCallerIdManager != null && (cachedCequintCallerIdContact = cequintCallerIdManager.getCachedCequintCallerIdContact(this.context, str)) != null) {
            if (TextUtils.isEmpty(contactInfo.name) && !TextUtils.isEmpty(cachedCequintCallerIdContact.name())) {
                contactInfo.name = cachedCequintCallerIdContact.name();
            }
            if (!TextUtils.isEmpty(cachedCequintCallerIdContact.geolocation())) {
                contactInfo.geoDescription = cachedCequintCallerIdContact.geolocation();
                contactInfo.sourceType = ContactSource$Type.SOURCE_TYPE_CEQUINT_CALLER_ID;
            }
            if (!contactInfo.contactExists && contactInfo.photoUri == null && cachedCequintCallerIdContact.photoUri() != null) {
                contactInfo.photoUri = R$style.parseUriOrNull(cachedCequintCallerIdContact.photoUri());
            }
        }
    }

    public ContactInfo lookupNumber(String str, String str2, long j) {
        ContactInfo contactInfo;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (PhoneNumberHelper.isUriNumber(str)) {
            contactInfo = lookupContactFromUri(getContactInfoLookupUri(str, j));
            if (contactInfo == null || contactInfo == ContactInfo.EMPTY) {
                String usernameFromUriNumber = PhoneNumberHelper.getUsernameFromUriNumber(str);
                if (PhoneNumberUtils.isGlobalPhoneNumber(usernameFromUriNumber)) {
                    contactInfo = queryContactInfoForPhoneNumber(usernameFromUriNumber, str2, j);
                }
            }
        } else {
            contactInfo = queryContactInfoForPhoneNumber(str, str2, j);
        }
        if (contactInfo == null) {
            return null;
        }
        return contactInfo == ContactInfo.EMPTY ? createEmptyContactInfoForNumber(str, str2) : contactInfo;
    }
}
