package com.android.vcard;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VCardComposer {
    private static final String[] sContactsProjection = {"_id"};
    private static final Map<Integer, String> sImMap = new HashMap();
    private final String mCharset;
    private final ContentResolver mContentResolver;
    private Uri mContentUriForRawContactsEntity;
    private Cursor mCursor;
    private boolean mCursorSuppliedFromOutside;
    private String mErrorReason;
    private boolean mFirstVCardEmittedInDoCoMoCase;
    private int mIdColumn;
    private boolean mInitDone;
    private final boolean mIsDoCoMo;
    private VCardPhoneNumberTranslationCallback mPhoneTranslationCallback;
    private RawContactEntitlesInfoCallback mRawContactEntitlesInfoCallback;
    private boolean mTerminateCalled;
    private final int mVCardType;

    public static class RawContactEntitlesInfo {
        public final long contactId;
        public final Uri rawContactEntitlesUri;
    }

    public interface RawContactEntitlesInfoCallback {
        RawContactEntitlesInfo getRawContactEntitlesInfo(long j);
    }

    static {
        sImMap.put(0, "X-AIM");
        sImMap.put(1, "X-MSN");
        sImMap.put(2, "X-YAHOO");
        sImMap.put(6, "X-ICQ");
        sImMap.put(7, "X-JABBER");
        sImMap.put(3, "X-SKYPE-USERNAME");
    }

    public VCardComposer(Context context, int i, boolean z) {
        this(context, i, (String) null, z);
    }

    public VCardComposer(Context context, int i, String str, boolean z) {
        this(context, context.getContentResolver(), i, str, z);
    }

    public VCardComposer(Context context, ContentResolver contentResolver, int i, String str, boolean z) {
        this.mErrorReason = "No error";
        boolean z2 = true;
        this.mTerminateCalled = true;
        this.mVCardType = i;
        this.mContentResolver = contentResolver;
        this.mIsDoCoMo = VCardConfig.isDoCoMo(i);
        str = TextUtils.isEmpty(str) ? "UTF-8" : str;
        if (VCardConfig.isVersion30(i) && "UTF-8".equalsIgnoreCase(str)) {
            z2 = false;
        }
        if (this.mIsDoCoMo || z2) {
            if ("SHIFT_JIS".equalsIgnoreCase(str)) {
                this.mCharset = str;
            } else if (TextUtils.isEmpty(str)) {
                this.mCharset = "SHIFT_JIS";
            } else {
                this.mCharset = str;
            }
        } else if (TextUtils.isEmpty(str)) {
            this.mCharset = "UTF-8";
        } else {
            this.mCharset = str;
        }
        Log.d("VCardComposer", "Use the charset \"" + this.mCharset + "\"");
    }

    public boolean init(Uri uri, String[] strArr, String str, String[] strArr2, String str2, Uri uri2) {
        if (!"com.android.contacts".equals(uri.getAuthority())) {
            this.mErrorReason = "The Uri vCard composer received is not supported by the composer.";
            return false;
        } else if (initInterFirstPart(uri2) && initInterCursorCreationPart(uri, strArr, str, strArr2, str2) && initInterMainPart()) {
            return initInterLastPart();
        } else {
            return false;
        }
    }

    private boolean initInterFirstPart(Uri uri) {
        if (uri == null) {
            uri = ContactsContract.RawContactsEntity.CONTENT_URI;
        }
        this.mContentUriForRawContactsEntity = uri;
        if (!this.mInitDone) {
            return true;
        }
        Log.e("VCardComposer", "init() is already called");
        return false;
    }

    private boolean initInterCursorCreationPart(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        this.mCursorSuppliedFromOutside = false;
        this.mCursor = this.mContentResolver.query(uri, strArr, str, strArr2, str2);
        if (this.mCursor != null) {
            return true;
        }
        Log.e("VCardComposer", String.format("Cursor became null unexpectedly", new Object[0]));
        this.mErrorReason = "Failed to get database information";
        return false;
    }

    private boolean initInterMainPart() {
        if (this.mCursor.getCount() == 0 || !this.mCursor.moveToFirst()) {
            closeCursorIfAppropriate();
            return false;
        }
        this.mIdColumn = this.mCursor.getColumnIndex("contact_id");
        if (this.mIdColumn < 0) {
            this.mIdColumn = this.mCursor.getColumnIndex("_id");
        }
        if (this.mIdColumn >= 0) {
            return true;
        }
        return false;
    }

    private boolean initInterLastPart() {
        this.mInitDone = true;
        this.mTerminateCalled = false;
        return true;
    }

    public String createOneEntry() {
        return createOneEntry((Method) null);
    }

    public String createOneEntry(Method method) {
        if (this.mIsDoCoMo && !this.mFirstVCardEmittedInDoCoMoCase) {
            this.mFirstVCardEmittedInDoCoMoCase = true;
        }
        String createOneEntryInternal = createOneEntryInternal(this.mCursor.getLong(this.mIdColumn), method);
        if (!this.mCursor.moveToNext()) {
            Log.e("VCardComposer", "Cursor#moveToNext() returned false");
        }
        return createOneEntryInternal;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: java.lang.Object[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00c6 A[Catch:{ IllegalArgumentException -> 0x0069, IllegalAccessException -> 0x004f, InvocationTargetException -> 0x0041, all -> 0x0108 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String createOneEntryInternal(long r10, java.lang.reflect.Method r12) {
        /*
            r9 = this;
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r1 = 0
            android.net.Uri r2 = r9.mContentUriForRawContactsEntity     // Catch:{ all -> 0x0108 }
            com.android.vcard.VCardComposer$RawContactEntitlesInfoCallback r3 = r9.mRawContactEntitlesInfoCallback     // Catch:{ all -> 0x0108 }
            if (r3 == 0) goto L_0x0016
            com.android.vcard.VCardComposer$RawContactEntitlesInfoCallback r2 = r9.mRawContactEntitlesInfoCallback     // Catch:{ all -> 0x0108 }
            com.android.vcard.VCardComposer$RawContactEntitlesInfo r10 = r2.getRawContactEntitlesInfo(r10)     // Catch:{ all -> 0x0108 }
            android.net.Uri r2 = r10.rawContactEntitlesUri     // Catch:{ all -> 0x0108 }
            long r10 = r10.contactId     // Catch:{ all -> 0x0108 }
        L_0x0016:
            r3 = r2
            r2 = 1
            java.lang.String[] r6 = new java.lang.String[r2]     // Catch:{ all -> 0x0108 }
            java.lang.String r4 = java.lang.String.valueOf(r10)     // Catch:{ all -> 0x0108 }
            r5 = 0
            r6[r5] = r4     // Catch:{ all -> 0x0108 }
            java.lang.String r8 = "VCardComposer"
            if (r12 == 0) goto L_0x0083
            r4 = 5
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ IllegalArgumentException -> 0x0069, IllegalAccessException -> 0x004f, InvocationTargetException -> 0x0041 }
            android.content.ContentResolver r7 = r9.mContentResolver     // Catch:{ IllegalArgumentException -> 0x0069, IllegalAccessException -> 0x004f, InvocationTargetException -> 0x0041 }
            r4[r5] = r7     // Catch:{ IllegalArgumentException -> 0x0069, IllegalAccessException -> 0x004f, InvocationTargetException -> 0x0041 }
            r4[r2] = r3     // Catch:{ IllegalArgumentException -> 0x0069, IllegalAccessException -> 0x004f, InvocationTargetException -> 0x0041 }
            r2 = 2
            java.lang.String r3 = "contact_id=?"
            r4[r2] = r3     // Catch:{ IllegalArgumentException -> 0x0069, IllegalAccessException -> 0x004f, InvocationTargetException -> 0x0041 }
            r2 = 3
            r4[r2] = r6     // Catch:{ IllegalArgumentException -> 0x0069, IllegalAccessException -> 0x004f, InvocationTargetException -> 0x0041 }
            r2 = 4
            r4[r2] = r1     // Catch:{ IllegalArgumentException -> 0x0069, IllegalAccessException -> 0x004f, InvocationTargetException -> 0x0041 }
            java.lang.Object r12 = r12.invoke(r1, r4)     // Catch:{ IllegalArgumentException -> 0x0069, IllegalAccessException -> 0x004f, InvocationTargetException -> 0x0041 }
            android.content.EntityIterator r12 = (android.content.EntityIterator) r12     // Catch:{ IllegalArgumentException -> 0x0069, IllegalAccessException -> 0x004f, InvocationTargetException -> 0x0041 }
            r1 = r12
            goto L_0x0091
        L_0x0041:
            r10 = move-exception
            java.lang.String r11 = "InvocationTargetException has been thrown: "
            android.util.Log.e(r8, r11, r10)     // Catch:{ all -> 0x0108 }
            java.lang.RuntimeException r10 = new java.lang.RuntimeException     // Catch:{ all -> 0x0108 }
            java.lang.String r11 = "InvocationTargetException has been thrown"
            r10.<init>(r11)     // Catch:{ all -> 0x0108 }
            throw r10     // Catch:{ all -> 0x0108 }
        L_0x004f:
            r12 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0108 }
            r2.<init>()     // Catch:{ all -> 0x0108 }
            java.lang.String r3 = "IllegalAccessException has been thrown: "
            r2.append(r3)     // Catch:{ all -> 0x0108 }
            java.lang.String r12 = r12.getMessage()     // Catch:{ all -> 0x0108 }
            r2.append(r12)     // Catch:{ all -> 0x0108 }
            java.lang.String r12 = r2.toString()     // Catch:{ all -> 0x0108 }
            android.util.Log.e(r8, r12)     // Catch:{ all -> 0x0108 }
            goto L_0x0091
        L_0x0069:
            r12 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0108 }
            r2.<init>()     // Catch:{ all -> 0x0108 }
            java.lang.String r3 = "IllegalArgumentException has been thrown: "
            r2.append(r3)     // Catch:{ all -> 0x0108 }
            java.lang.String r12 = r12.getMessage()     // Catch:{ all -> 0x0108 }
            r2.append(r12)     // Catch:{ all -> 0x0108 }
            java.lang.String r12 = r2.toString()     // Catch:{ all -> 0x0108 }
            android.util.Log.e(r8, r12)     // Catch:{ all -> 0x0108 }
            goto L_0x0091
        L_0x0083:
            android.content.ContentResolver r2 = r9.mContentResolver     // Catch:{ all -> 0x0108 }
            r4 = 0
            java.lang.String r5 = "contact_id=?"
            r7 = 0
            android.database.Cursor r12 = r2.query(r3, r4, r5, r6, r7)     // Catch:{ all -> 0x0108 }
            android.content.EntityIterator r1 = android.provider.ContactsContract.RawContacts.newEntityIterator(r12)     // Catch:{ all -> 0x0108 }
        L_0x0091:
            java.lang.String r12 = ""
            if (r1 != 0) goto L_0x00a0
            java.lang.String r10 = "EntityIterator is null"
            android.util.Log.e(r8, r10)     // Catch:{ all -> 0x0108 }
            if (r1 == 0) goto L_0x009f
            r1.close()
        L_0x009f:
            return r12
        L_0x00a0:
            boolean r2 = r1.hasNext()     // Catch:{ all -> 0x0108 }
            if (r2 != 0) goto L_0x00c0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0108 }
            r0.<init>()     // Catch:{ all -> 0x0108 }
            java.lang.String r2 = "Data does not exist. contactId: "
            r0.append(r2)     // Catch:{ all -> 0x0108 }
            r0.append(r10)     // Catch:{ all -> 0x0108 }
            java.lang.String r10 = r0.toString()     // Catch:{ all -> 0x0108 }
            android.util.Log.w(r8, r10)     // Catch:{ all -> 0x0108 }
            if (r1 == 0) goto L_0x00bf
            r1.close()
        L_0x00bf:
            return r12
        L_0x00c0:
            boolean r10 = r1.hasNext()     // Catch:{ all -> 0x0108 }
            if (r10 == 0) goto L_0x00fe
            java.lang.Object r10 = r1.next()     // Catch:{ all -> 0x0108 }
            android.content.Entity r10 = (android.content.Entity) r10     // Catch:{ all -> 0x0108 }
            java.util.ArrayList r10 = r10.getSubValues()     // Catch:{ all -> 0x0108 }
            java.util.Iterator r10 = r10.iterator()     // Catch:{ all -> 0x0108 }
        L_0x00d4:
            boolean r11 = r10.hasNext()     // Catch:{ all -> 0x0108 }
            if (r11 == 0) goto L_0x00c0
            java.lang.Object r11 = r10.next()     // Catch:{ all -> 0x0108 }
            android.content.Entity$NamedContentValues r11 = (android.content.Entity.NamedContentValues) r11     // Catch:{ all -> 0x0108 }
            android.content.ContentValues r11 = r11.values     // Catch:{ all -> 0x0108 }
            java.lang.String r12 = "mimetype"
            java.lang.String r12 = r11.getAsString(r12)     // Catch:{ all -> 0x0108 }
            if (r12 == 0) goto L_0x00d4
            java.lang.Object r2 = r0.get(r12)     // Catch:{ all -> 0x0108 }
            java.util.List r2 = (java.util.List) r2     // Catch:{ all -> 0x0108 }
            if (r2 != 0) goto L_0x00fa
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x0108 }
            r2.<init>()     // Catch:{ all -> 0x0108 }
            r0.put(r12, r2)     // Catch:{ all -> 0x0108 }
        L_0x00fa:
            r2.add(r11)     // Catch:{ all -> 0x0108 }
            goto L_0x00d4
        L_0x00fe:
            if (r1 == 0) goto L_0x0103
            r1.close()
        L_0x0103:
            java.lang.String r10 = r9.buildVCard(r0)
            return r10
        L_0x0108:
            r10 = move-exception
            if (r1 == 0) goto L_0x010e
            r1.close()
        L_0x010e:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.vcard.VCardComposer.createOneEntryInternal(long, java.lang.reflect.Method):java.lang.String");
    }

    public String buildVCard(Map<String, List<ContentValues>> map) {
        if (map == null) {
            Log.e("VCardComposer", "The given map is null. Ignore and return empty String");
            return "";
        }
        VCardBuilder vCardBuilder = new VCardBuilder(this.mVCardType, this.mCharset);
        vCardBuilder.appendNameProperties(map.get("vnd.android.cursor.item/name"));
        vCardBuilder.appendNickNames(map.get("vnd.android.cursor.item/nickname"));
        vCardBuilder.appendPhones(map.get("vnd.android.cursor.item/phone_v2"), this.mPhoneTranslationCallback);
        vCardBuilder.appendEmails(map.get("vnd.android.cursor.item/email_v2"));
        vCardBuilder.appendPostals(map.get("vnd.android.cursor.item/postal-address_v2"));
        vCardBuilder.appendOrganizations(map.get("vnd.android.cursor.item/organization"));
        vCardBuilder.appendWebsites(map.get("vnd.android.cursor.item/website"));
        if ((this.mVCardType & 8388608) == 0) {
            vCardBuilder.appendPhotos(map.get("vnd.android.cursor.item/photo"));
        }
        vCardBuilder.appendNotes(map.get("vnd.android.cursor.item/note"));
        vCardBuilder.appendEvents(map.get("vnd.android.cursor.item/contact_event"));
        vCardBuilder.appendIms(map.get("vnd.android.cursor.item/im"));
        vCardBuilder.appendSipAddresses(map.get("vnd.android.cursor.item/sip_address"));
        vCardBuilder.appendRelation(map.get("vnd.android.cursor.item/relation"));
        return vCardBuilder.toString();
    }

    public void terminate() {
        closeCursorIfAppropriate();
        this.mTerminateCalled = true;
    }

    private void closeCursorIfAppropriate() {
        Cursor cursor;
        if (!this.mCursorSuppliedFromOutside && (cursor = this.mCursor) != null) {
            try {
                cursor.close();
            } catch (SQLiteException e) {
                Log.e("VCardComposer", "SQLiteException on Cursor#close(): " + e.getMessage());
            }
            this.mCursor = null;
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        try {
            if (!this.mTerminateCalled) {
                Log.e("VCardComposer", "finalized() is called before terminate() being called");
            }
        } finally {
            super.finalize();
        }
    }

    public int getCount() {
        Cursor cursor = this.mCursor;
        if (cursor != null) {
            return cursor.getCount();
        }
        Log.w("VCardComposer", "This object is not ready yet.");
        return 0;
    }

    public boolean isAfterLast() {
        Cursor cursor = this.mCursor;
        if (cursor != null) {
            return cursor.isAfterLast();
        }
        Log.w("VCardComposer", "This object is not ready yet.");
        return false;
    }

    public String getErrorReason() {
        return this.mErrorReason;
    }
}
