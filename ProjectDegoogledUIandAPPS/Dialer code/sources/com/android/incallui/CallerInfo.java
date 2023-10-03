package com.android.incallui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.telephony.TelephonyManager;
import com.android.dialer.R;
import com.android.dialer.logging.ContactLookupResult$Type;

public class CallerInfo {
    private static final String[] DEFAULT_PHONELOOKUP_PROJECTION = {"contact_id", "display_name", "lookup", "number", "normalized_number", "label", "type", "photo_uri", "custom_ringtone", "send_to_voicemail"};
    public Drawable cachedPhoto;
    public String callSubject;
    public String cnapName;
    public Uri contactDisplayPhotoUri;
    public boolean contactExists;
    public long contactIdOrZero;
    public ContactLookupResult$Type contactLookupResultType = ContactLookupResult$Type.NOT_FOUND;
    public Uri contactRingtoneUri;
    public String countryIso;
    public String geoDescription;
    public boolean isCachedPhotoCurrent;
    private boolean isEmergency = false;
    private boolean isVoiceMail = false;
    public String lookupKeyOrNull;
    public String name;
    public String nameAlternative;
    public String normalizedNumber;
    public String numberLabel;
    public int numberPresentation;
    public int numberType;
    public String phoneLabel;
    public String phoneNumber;
    public int photoResource;
    boolean shouldShowGeoDescription;
    public long userType = 0;

    /* JADX WARNING: Removed duplicated region for block: B:41:0x010a  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x010f  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0133  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0157  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0179  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0184  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0194  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x01ae  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x01b9  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x01ca A[SYNTHETIC, Splitter:B:72:0x01ca] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.android.incallui.CallerInfo getCallerInfo(android.content.Context r11, android.net.Uri r12, android.database.Cursor r13) {
        /*
            com.android.incallui.CallerInfo r0 = new com.android.incallui.CallerInfo
            r0.<init>()
            r1 = 0
            r0.cachedPhoto = r1
            r2 = 0
            r0.contactExists = r2
            r0.isCachedPhotoCurrent = r2
            r0.name = r1
            r0.numberLabel = r1
            r0.numberType = r2
            r0.phoneLabel = r1
            r0.photoResource = r2
            r2 = 0
            r0.userType = r2
            java.lang.String r4 = "CallerInfo"
            java.lang.String r5 = "getCallerInfo() based on cursor..."
            com.android.incallui.Bindings.m40v(r4, r5)
            if (r13 == 0) goto L_0x01e9
            boolean r5 = r13.moveToFirst()
            if (r5 != 0) goto L_0x002c
            goto L_0x01e9
        L_0x002c:
            java.lang.String r5 = "number"
            int r5 = r13.getColumnIndex(r5)
            r6 = -1
            if (r5 == r6) goto L_0x0043
            boolean r7 = com.android.dialer.phonenumberutil.PhoneNumberHelper.updateCursorToMatchContactLookupUri(r13, r5, r12)
            if (r7 == 0) goto L_0x0042
            java.lang.String r5 = r13.getString(r5)
            r0.phoneNumber = r5
            goto L_0x0043
        L_0x0042:
            return r0
        L_0x0043:
            java.lang.String r5 = "display_name"
            int r5 = r13.getColumnIndex(r5)
            if (r5 == r6) goto L_0x005b
            java.lang.String r5 = r13.getString(r5)
            if (r5 == 0) goto L_0x0059
            int r7 = r5.length()
            if (r7 <= 0) goto L_0x0058
            goto L_0x0059
        L_0x0058:
            r5 = r1
        L_0x0059:
            r0.name = r5
        L_0x005b:
            java.lang.String r5 = "normalized_number"
            int r5 = r13.getColumnIndex(r5)
            if (r5 == r6) goto L_0x0069
            java.lang.String r5 = r13.getString(r5)
            r0.normalizedNumber = r5
        L_0x0069:
            java.lang.String r5 = "label"
            int r5 = r13.getColumnIndex(r5)
            if (r5 == r6) goto L_0x0097
            java.lang.String r7 = "type"
            int r7 = r13.getColumnIndex(r7)
            if (r7 == r6) goto L_0x0097
            int r7 = r13.getInt(r7)
            r0.numberType = r7
            java.lang.String r5 = r13.getString(r5)
            r0.numberLabel = r5
            android.content.res.Resources r5 = r11.getResources()
            int r7 = r0.numberType
            java.lang.String r8 = r0.numberLabel
            java.lang.CharSequence r5 = android.provider.ContactsContract.CommonDataKinds.Phone.getTypeLabel(r5, r7, r8)
            java.lang.String r5 = r5.toString()
            r0.phoneLabel = r5
        L_0x0097:
            java.lang.String r5 = "lookup"
            int r5 = r13.getColumnIndex(r5)
            if (r5 == r6) goto L_0x00a5
            java.lang.String r5 = r13.getString(r5)
            r0.lookupKeyOrNull = r5
        L_0x00a5:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r7 = "- getColumnIndexForPersonId: contactRef URI = '"
            r5.append(r7)
            r5.append(r12)
            java.lang.String r7 = "'..."
            r5.append(r7)
            java.lang.String r5 = r5.toString()
            com.android.incallui.Bindings.m40v(r4, r5)
            java.lang.String r5 = r12.toString()
            java.lang.String r7 = "content://com.android.contacts/data/phones"
            boolean r7 = r5.startsWith(r7)
            if (r7 == 0) goto L_0x00d0
            java.lang.String r5 = "'data/phones' URI; using RawContacts.CONTACT_ID"
            com.android.incallui.Bindings.m40v(r4, r5)
            goto L_0x00eb
        L_0x00d0:
            java.lang.String r7 = "content://com.android.contacts/data"
            boolean r7 = r5.startsWith(r7)
            if (r7 == 0) goto L_0x00de
            java.lang.String r5 = "'data' URI; using Data.CONTACT_ID"
            com.android.incallui.Bindings.m40v(r4, r5)
            goto L_0x00eb
        L_0x00de:
            java.lang.String r7 = "content://com.android.contacts/phone_lookup"
            boolean r7 = r5.startsWith(r7)
            if (r7 == 0) goto L_0x00ee
            java.lang.String r5 = "'phone_lookup' URI; using PhoneLookup._ID"
            com.android.incallui.Bindings.m40v(r4, r5)
        L_0x00eb:
            java.lang.String r5 = "contact_id"
            goto L_0x0108
        L_0x00ee:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "Unexpected prefix for contactRef '"
            r7.append(r8)
            r7.append(r5)
            java.lang.String r5 = "'"
            r7.append(r5)
            java.lang.String r5 = r7.toString()
            com.android.incallui.Bindings.m40v(r4, r5)
            r5 = r1
        L_0x0108:
            if (r5 == 0) goto L_0x010f
            int r7 = r13.getColumnIndex(r5)
            goto L_0x0110
        L_0x010f:
            r7 = r6
        L_0x0110:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "==> Using column '"
            r8.append(r9)
            r8.append(r5)
            java.lang.String r5 = "' (columnIndex = "
            r8.append(r5)
            r8.append(r7)
            java.lang.String r5 = ") for person_id lookup..."
            r8.append(r5)
            java.lang.String r5 = r8.toString()
            com.android.incallui.Bindings.m40v(r4, r5)
            if (r7 == r6) goto L_0x0157
            long r7 = r13.getLong(r7)
            int r2 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r2 == 0) goto L_0x0155
            boolean r2 = android.provider.ContactsContract.Contacts.isEnterpriseContactId(r7)
            if (r2 != 0) goto L_0x0155
            r0.contactIdOrZero = r7
            java.lang.String r2 = "==> got info.contactIdOrZero: "
            java.lang.StringBuilder r2 = com.android.tools.p006r8.GeneratedOutlineSupport.outline13(r2)
            long r9 = r0.contactIdOrZero
            r2.append(r9)
            java.lang.String r2 = r2.toString()
            com.android.incallui.Bindings.m40v(r4, r2)
        L_0x0155:
            r2 = r7
            goto L_0x016b
        L_0x0157:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r7 = "Couldn't find contactId column for "
            r5.append(r7)
            r5.append(r12)
            java.lang.String r5 = r5.toString()
            com.android.incallui.Bindings.m40v(r4, r5)
        L_0x016b:
            java.lang.String r4 = "photo_uri"
            int r4 = r13.getColumnIndex(r4)
            if (r4 == r6) goto L_0x0184
            java.lang.String r5 = r13.getString(r4)
            if (r5 == 0) goto L_0x0184
            java.lang.String r4 = r13.getString(r4)
            android.net.Uri r4 = android.net.Uri.parse(r4)
            r0.contactDisplayPhotoUri = r4
            goto L_0x0186
        L_0x0184:
            r0.contactDisplayPhotoUri = r1
        L_0x0186:
            java.lang.String r4 = "custom_ringtone"
            int r4 = r13.getColumnIndex(r4)
            if (r4 == r6) goto L_0x01ae
            java.lang.String r5 = r13.getString(r4)
            if (r5 == 0) goto L_0x01ae
            java.lang.String r5 = r13.getString(r4)
            boolean r5 = android.text.TextUtils.isEmpty(r5)
            if (r5 == 0) goto L_0x01a3
            android.net.Uri r4 = android.net.Uri.EMPTY
            r0.contactRingtoneUri = r4
            goto L_0x01b0
        L_0x01a3:
            java.lang.String r4 = r13.getString(r4)
            android.net.Uri r4 = android.net.Uri.parse(r4)
            r0.contactRingtoneUri = r4
            goto L_0x01b0
        L_0x01ae:
            r0.contactRingtoneUri = r1
        L_0x01b0:
            java.lang.String r4 = "send_to_voicemail"
            int r4 = r13.getColumnIndex(r4)
            r5 = 1
            if (r4 == r6) goto L_0x01bc
            r13.getInt(r4)
        L_0x01bc:
            r0.contactExists = r5
            com.android.dialer.logging.ContactLookupResult$Type r4 = com.android.dialer.logging.ContactLookupResult$Type.LOCAL_CONTACT
            r0.contactLookupResultType = r4
            java.lang.String r4 = "directory"
            java.lang.String r12 = r12.getQueryParameter(r4)
            if (r12 == 0) goto L_0x01d2
            long r4 = java.lang.Long.parseLong(r12)     // Catch:{ NumberFormatException -> 0x01d2 }
            java.lang.Long r1 = java.lang.Long.valueOf(r4)     // Catch:{ NumberFormatException -> 0x01d2 }
        L_0x01d2:
            java.lang.Long r12 = java.lang.Long.valueOf(r2)
            long r2 = android.support.p002v7.appcompat.R$style.determineUserType(r1, r12)
            r0.userType = r2
            java.lang.String r12 = r0.lookupKeyOrNull
            long r2 = r0.userType
            java.lang.String r11 = com.android.dialer.phonenumbercache.ContactInfoHelper.lookUpDisplayNameAlternative(r11, r12, r2, r1)
            r0.nameAlternative = r11
            r13.close()
        L_0x01e9:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.incallui.CallerInfo.getCallerInfo(android.content.Context, android.net.Uri, android.database.Cursor):com.android.incallui.CallerInfo");
    }

    static String[] getDefaultPhoneLookupProjection() {
        return DEFAULT_PHONELOOKUP_PROJECTION;
    }

    public boolean isEmergencyNumber() {
        return this.isEmergency;
    }

    public boolean isVoiceMailNumber() {
        return this.isVoiceMail;
    }

    /* access modifiers changed from: package-private */
    public CallerInfo markAsEmergency(Context context) {
        this.name = context.getString(R.string.emergency_number);
        this.phoneNumber = null;
        this.isEmergency = true;
        return this;
    }

    /* access modifiers changed from: package-private */
    public CallerInfo markAsVoiceMail(Context context) {
        this.isVoiceMail = true;
        try {
            this.name = ((TelephonyManager) context.getSystemService("phone")).getVoiceMailAlphaTag();
            this.phoneNumber = null;
        } catch (SecurityException e) {
            Bindings.m38e("CallerInfo", "Cannot access VoiceMail.", (Exception) e);
        }
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(super.toString() + " { ");
        StringBuilder sb2 = new StringBuilder();
        sb2.append("name ");
        String str = "null";
        sb2.append(this.name == null ? str : "non-null");
        sb.append(sb2.toString());
        StringBuilder sb3 = new StringBuilder();
        sb3.append(", phoneNumber ");
        if (this.phoneNumber != null) {
            str = "non-null";
        }
        sb3.append(str);
        sb.append(sb3.toString());
        sb.append(" }");
        return sb.toString();
    }
}
