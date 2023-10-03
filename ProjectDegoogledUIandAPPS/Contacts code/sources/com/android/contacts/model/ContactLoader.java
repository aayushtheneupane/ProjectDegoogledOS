package com.android.contacts.model;

import android.content.AsyncTaskLoader;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import com.android.contacts.ContactSaveService;
import com.android.contacts.GeoUtil;
import com.android.contacts.GroupMetaDataLoader;
import com.android.contacts.compat.CompatUtils;
import com.android.contacts.group.GroupMetaData;
import com.android.contacts.model.account.AccountType;
import com.android.contacts.model.account.GoogleAccountType;
import com.android.contacts.model.dataitem.DataItem;
import com.android.contacts.model.dataitem.PhoneDataItem;
import com.android.contacts.model.dataitem.PhotoDataItem;
import com.android.contacts.util.ContactLoaderUtils;
import com.android.contacts.util.DataStatus;
import com.android.contacts.util.UriUtils;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ContactLoader extends AsyncTaskLoader<Contact> {
    private static final boolean DEBUG = Log.isLoggable(TAG, 3);
    private static final String TAG = "ContactLoader";
    private static Contact sCachedResult = null;
    private boolean mComputeFormattedPhoneNumber;
    private Contact mContact;
    private boolean mLoadGroupMetaData;
    private Uri mLookupUri;
    private final Set<Long> mNotifiedRawContactIds;
    private Loader<Contact>.ForceLoadContentObserver mObserver;
    private boolean mPostViewNotification;
    private final Uri mRequestedUri;

    private static class DirectoryQuery {
        static final String[] COLUMNS = {"displayName", "packageName", "typeResourceId", ContactSaveService.EXTRA_ACCOUNT_TYPE, ContactSaveService.EXTRA_ACCOUNT_NAME, "exportSupport"};
    }

    public ContactLoader(Context context, Uri uri, boolean z) {
        this(context, uri, false, z, false);
    }

    public ContactLoader(Context context, Uri uri, boolean z, boolean z2) {
        this(context, uri, z2, z, false);
    }

    public ContactLoader(Context context, Uri uri, boolean z, boolean z2, boolean z3) {
        super(context);
        this.mNotifiedRawContactIds = Sets.newHashSet();
        this.mLookupUri = uri;
        this.mRequestedUri = uri;
        this.mLoadGroupMetaData = z;
        this.mPostViewNotification = z2;
        this.mComputeFormattedPhoneNumber = z3;
    }

    private static class ContactQuery {
        static final String[] COLUMNS;
        static final String[] COLUMNS_INTERNAL = {"name_raw_contact_id", "display_name_source", "lookup", "display_name", "display_name_alt", "phonetic_name", "photo_id", ContactSaveService.EXTRA_STARRED_FLAG, "contact_presence", "contact_status", "contact_status_ts", "contact_status_res_package", "contact_status_label", "contact_id", "raw_contact_id", "account_name", "account_type", "data_set", "dirty", "version", "sourceid", "sync1", "sync2", "sync3", "sync4", "deleted", "data_id", "data1", "data2", "data3", "data4", "data5", "data6", "data7", "data8", "data9", "data10", "data11", "data12", "data13", "data14", "data15", "data_sync1", "data_sync2", "data_sync3", "data_sync4", "data_version", "is_primary", "is_super_primary", "mimetype", "group_sourceid", "mode", "chat_capability", "status", "status_res_package", "status_icon", "status_label", "status_ts", "photo_uri", "send_to_voicemail", "custom_ringtone", "is_user_profile"};

        static {
            ArrayList newArrayList = Lists.newArrayList((E[]) COLUMNS_INTERNAL);
            if (CompatUtils.isMarshmallowCompatible()) {
                newArrayList.add("carrier_presence");
            }
            COLUMNS = (String[]) newArrayList.toArray(new String[newArrayList.size()]);
        }
    }

    public void setNewLookup(Uri uri) {
        this.mLookupUri = uri;
        this.mContact = null;
    }

    public Contact loadInBackground() {
        boolean z;
        Contact contact;
        try {
            ContentResolver contentResolver = getContext().getContentResolver();
            Uri ensureIsContactUri = ContactLoaderUtils.ensureIsContactUri(contentResolver, this.mLookupUri);
            Contact contact2 = sCachedResult;
            sCachedResult = null;
            if (contact2 == null || !UriUtils.areEqual(contact2.getLookupUri(), this.mLookupUri)) {
                if (ensureIsContactUri.getLastPathSegment().equals("encoded")) {
                    contact = loadEncodedContactEntity(ensureIsContactUri, this.mLookupUri);
                } else {
                    contact = loadContactEntity(contentResolver, ensureIsContactUri);
                }
                z = false;
            } else {
                contact = new Contact(this.mRequestedUri, contact2);
                z = true;
            }
            if (contact.isLoaded()) {
                if (contact.isDirectoryEntry()) {
                    if (!z) {
                        loadDirectoryMetaData(contact);
                    }
                } else if (this.mLoadGroupMetaData && contact.getGroupMetaData() == null) {
                    loadGroupMetaData(contact);
                }
                if (this.mComputeFormattedPhoneNumber) {
                    computeFormattedPhoneNumbers(contact);
                }
                if (!z) {
                    loadPhotoBinaryData(contact);
                }
            }
            return contact;
        } catch (Exception e) {
            String str = TAG;
            Log.e(str, "Error loading the contact: " + this.mLookupUri, e);
            return Contact.forError(this.mRequestedUri, e);
        }
    }

    public static Contact parseEncodedContactEntity(Uri uri) {
        try {
            return loadEncodedContactEntity(uri, uri);
        } catch (JSONException unused) {
            return null;
        }
    }

    private static Contact loadEncodedContactEntity(Uri uri, Uri uri2) throws JSONException {
        JSONObject jSONObject = new JSONObject(uri.getEncodedFragment());
        long longValue = Long.valueOf(uri.getQueryParameter("directory")).longValue();
        String optString = jSONObject.optString("display_name");
        String optString2 = jSONObject.optString("display_name_alt", optString);
        Contact contact = r0;
        Contact contact2 = new Contact(uri, uri, uri2, longValue, (String) null, -1, -1, jSONObject.getInt("display_name_source"), 0, jSONObject.optString("photo_uri", (String) null), optString, optString2, (String) null, false, (Integer) null, false, (String) null, false);
        Contact contact3 = contact;
        contact3.setStatuses(new ImmutableMap.Builder().build());
        JSONObject jSONObject2 = jSONObject;
        String optString3 = jSONObject2.optString("account_name", (String) null);
        String queryParameter = uri.getQueryParameter("displayName");
        if (optString3 != null) {
            contact3.setDirectoryMetaData(queryParameter, (String) null, optString3, jSONObject2.getString("account_type"), jSONObject2.optInt("exportSupport", 1));
        } else {
            contact3.setDirectoryMetaData(queryParameter, (String) null, (String) null, (String) null, jSONObject2.optInt("exportSupport", 2));
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", -1);
        contentValues.put("contact_id", -1);
        RawContact rawContact = new RawContact(contentValues);
        JSONObject jSONObject3 = jSONObject2.getJSONObject("vnd.android.cursor.item/contact");
        Iterator<String> keys = jSONObject3.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            JSONObject optJSONObject = jSONObject3.optJSONObject(next);
            if (optJSONObject == null) {
                JSONArray jSONArray = jSONObject3.getJSONArray(next);
                for (int i = 0; i < jSONArray.length(); i++) {
                    processOneRecord(rawContact, jSONArray.getJSONObject(i), next);
                }
            } else {
                processOneRecord(rawContact, optJSONObject, next);
            }
        }
        contact3.setRawContacts(new ImmutableList.Builder().add((Object) rawContact).build());
        return contact3;
    }

    private static void processOneRecord(RawContact rawContact, JSONObject jSONObject, String str) throws JSONException {
        ContentValues contentValues = new ContentValues();
        contentValues.put("mimetype", str);
        contentValues.put("_id", -1);
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object obj = jSONObject.get(next);
            if (obj instanceof String) {
                contentValues.put(next, (String) obj);
            } else if (obj instanceof Integer) {
                contentValues.put(next, (Integer) obj);
            }
        }
        rawContact.addDataItemValues(contentValues);
    }

    /* JADX INFO: finally extract failed */
    private Contact loadContactEntity(ContentResolver contentResolver, Uri uri) {
        Cursor query = contentResolver.query(Uri.withAppendedPath(uri, "entities"), ContactQuery.COLUMNS, (String) null, (String[]) null, "raw_contact_id");
        if (query == null) {
            Log.e(TAG, "No cursor returned in loadContactEntity");
            return Contact.forNotFound(this.mRequestedUri);
        }
        try {
            if (!query.moveToFirst()) {
                query.close();
                Contact forNotFound = Contact.forNotFound(this.mRequestedUri);
                query.close();
                return forNotFound;
            }
            Contact loadContactHeaderData = loadContactHeaderData(query, uri);
            long j = -1;
            RawContact rawContact = null;
            ImmutableList.Builder builder = new ImmutableList.Builder();
            ImmutableMap.Builder builder2 = new ImmutableMap.Builder();
            do {
                long j2 = query.getLong(14);
                if (j2 != j) {
                    RawContact rawContact2 = new RawContact(loadRawContactValues(query));
                    builder.add((Object) rawContact2);
                    rawContact = rawContact2;
                    j = j2;
                }
                if (!query.isNull(26)) {
                    rawContact.addDataItemValues(loadDataValues(query));
                    if (!query.isNull(51) || !query.isNull(53)) {
                        builder2.put(Long.valueOf(query.getLong(26)), new DataStatus(query));
                    }
                }
            } while (query.moveToNext());
            loadContactHeaderData.setRawContacts(builder.build());
            loadContactHeaderData.setStatuses(builder2.build());
            query.close();
            return loadContactHeaderData;
        } catch (Throwable th) {
            query.close();
            throw th;
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x004f A[Catch:{ all -> 0x0064, IOException -> 0x006e }, LOOP:0: B:11:0x0048->B:14:0x004f, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0060 A[Catch:{ all -> 0x0064, IOException -> 0x006e }] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0054 A[EDGE_INSN: B:28:0x0054->B:15:0x0054 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A[Catch:{  }, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void loadPhotoBinaryData(com.android.contacts.model.Contact r7) {
        /*
            r6 = this;
            r6.loadThumbnailBinaryData(r7)
            java.lang.String r0 = r7.getPhotoUri()
            if (r0 == 0) goto L_0x006e
            android.net.Uri r1 = android.net.Uri.parse(r0)     // Catch:{ IOException -> 0x006e }
            java.lang.String r2 = r1.getScheme()     // Catch:{ IOException -> 0x006e }
            java.lang.String r3 = "http"
            boolean r3 = r3.equals(r2)     // Catch:{ IOException -> 0x006e }
            if (r3 != 0) goto L_0x0035
            java.lang.String r3 = "https"
            boolean r2 = r3.equals(r2)     // Catch:{ IOException -> 0x006e }
            if (r2 == 0) goto L_0x0022
            goto L_0x0035
        L_0x0022:
            android.content.Context r0 = r6.getContext()     // Catch:{ IOException -> 0x006e }
            android.content.ContentResolver r0 = r0.getContentResolver()     // Catch:{ IOException -> 0x006e }
            java.lang.String r2 = "r"
            android.content.res.AssetFileDescriptor r0 = r0.openAssetFileDescriptor(r1, r2)     // Catch:{ IOException -> 0x006e }
            java.io.FileInputStream r1 = r0.createInputStream()     // Catch:{ IOException -> 0x006e }
            goto L_0x003f
        L_0x0035:
            java.net.URL r1 = new java.net.URL     // Catch:{ IOException -> 0x006e }
            r1.<init>(r0)     // Catch:{ IOException -> 0x006e }
            java.io.InputStream r1 = r1.openStream()     // Catch:{ IOException -> 0x006e }
            r0 = 0
        L_0x003f:
            r2 = 16384(0x4000, float:2.2959E-41)
            byte[] r2 = new byte[r2]     // Catch:{ IOException -> 0x006e }
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x006e }
            r3.<init>()     // Catch:{ IOException -> 0x006e }
        L_0x0048:
            int r4 = r1.read(r2)     // Catch:{ all -> 0x0064 }
            r5 = -1
            if (r4 == r5) goto L_0x0054
            r5 = 0
            r3.write(r2, r5, r4)     // Catch:{ all -> 0x0064 }
            goto L_0x0048
        L_0x0054:
            byte[] r2 = r3.toByteArray()     // Catch:{ all -> 0x0064 }
            r7.setPhotoBinaryData(r2)     // Catch:{ all -> 0x0064 }
            r1.close()     // Catch:{ IOException -> 0x006e }
            if (r0 == 0) goto L_0x0063
            r0.close()     // Catch:{ IOException -> 0x006e }
        L_0x0063:
            return
        L_0x0064:
            r2 = move-exception
            r1.close()     // Catch:{ IOException -> 0x006e }
            if (r0 == 0) goto L_0x006d
            r0.close()     // Catch:{ IOException -> 0x006e }
        L_0x006d:
            throw r2     // Catch:{ IOException -> 0x006e }
        L_0x006e:
            byte[] r0 = r7.getThumbnailPhotoBinaryData()
            r7.setPhotoBinaryData(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.model.ContactLoader.loadPhotoBinaryData(com.android.contacts.model.Contact):void");
    }

    private void loadThumbnailBinaryData(Contact contact) {
        long photoId = contact.getPhotoId();
        if (photoId > 0) {
            UnmodifiableIterator<RawContact> it = contact.getRawContacts().iterator();
            while (it.hasNext()) {
                Iterator<DataItem> it2 = it.next().getDataItems().iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    DataItem next = it2.next();
                    if (next.getId() == photoId) {
                        if (next instanceof PhotoDataItem) {
                            contact.setThumbnailPhotoBinaryData(((PhotoDataItem) next).getPhoto());
                        }
                    }
                }
            }
        }
    }

    private Contact loadContactHeaderData(Cursor cursor, Uri uri) {
        long j;
        Integer num;
        Uri uri2;
        Cursor cursor2 = cursor;
        Uri uri3 = uri;
        String queryParameter = uri3.getQueryParameter("directory");
        if (queryParameter == null) {
            j = 0;
        } else {
            j = Long.parseLong(queryParameter);
        }
        long j2 = cursor2.getLong(13);
        String string = cursor2.getString(2);
        long j3 = cursor2.getLong(0);
        int i = cursor2.getInt(1);
        String string2 = cursor2.getString(3);
        String string3 = cursor2.getString(4);
        String string4 = cursor2.getString(5);
        long j4 = cursor2.getLong(6);
        String string5 = cursor2.getString(58);
        boolean z = cursor2.getInt(7) != 0;
        if (cursor2.isNull(8)) {
            num = null;
        } else {
            num = Integer.valueOf(cursor2.getInt(8));
        }
        Integer num2 = num;
        boolean z2 = cursor2.getInt(59) == 1;
        String string6 = cursor2.getString(60);
        boolean z3 = cursor2.getInt(61) == 1;
        if (j == 0 || j == 1) {
            uri2 = ContentUris.withAppendedId(Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, string), j2);
        } else {
            uri2 = uri3;
        }
        return new Contact(this.mRequestedUri, uri, uri2, j, string, j2, j3, i, j4, string5, string2, string3, string4, z, num2, z2, string6, z3);
    }

    private ContentValues loadRawContactValues(Cursor cursor) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", Long.valueOf(cursor.getLong(14)));
        cursorColumnToContentValues(cursor, contentValues, 15);
        cursorColumnToContentValues(cursor, contentValues, 16);
        cursorColumnToContentValues(cursor, contentValues, 17);
        cursorColumnToContentValues(cursor, contentValues, 18);
        cursorColumnToContentValues(cursor, contentValues, 19);
        cursorColumnToContentValues(cursor, contentValues, 20);
        cursorColumnToContentValues(cursor, contentValues, 21);
        cursorColumnToContentValues(cursor, contentValues, 22);
        cursorColumnToContentValues(cursor, contentValues, 23);
        cursorColumnToContentValues(cursor, contentValues, 24);
        cursorColumnToContentValues(cursor, contentValues, 25);
        cursorColumnToContentValues(cursor, contentValues, 13);
        cursorColumnToContentValues(cursor, contentValues, 7);
        return contentValues;
    }

    private ContentValues loadDataValues(Cursor cursor) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", Long.valueOf(cursor.getLong(26)));
        cursorColumnToContentValues(cursor, contentValues, 27);
        cursorColumnToContentValues(cursor, contentValues, 28);
        cursorColumnToContentValues(cursor, contentValues, 29);
        cursorColumnToContentValues(cursor, contentValues, 30);
        cursorColumnToContentValues(cursor, contentValues, 31);
        cursorColumnToContentValues(cursor, contentValues, 32);
        cursorColumnToContentValues(cursor, contentValues, 33);
        cursorColumnToContentValues(cursor, contentValues, 34);
        cursorColumnToContentValues(cursor, contentValues, 35);
        cursorColumnToContentValues(cursor, contentValues, 36);
        cursorColumnToContentValues(cursor, contentValues, 37);
        cursorColumnToContentValues(cursor, contentValues, 38);
        cursorColumnToContentValues(cursor, contentValues, 39);
        cursorColumnToContentValues(cursor, contentValues, 40);
        cursorColumnToContentValues(cursor, contentValues, 41);
        cursorColumnToContentValues(cursor, contentValues, 42);
        cursorColumnToContentValues(cursor, contentValues, 43);
        cursorColumnToContentValues(cursor, contentValues, 44);
        cursorColumnToContentValues(cursor, contentValues, 45);
        cursorColumnToContentValues(cursor, contentValues, 46);
        cursorColumnToContentValues(cursor, contentValues, 47);
        cursorColumnToContentValues(cursor, contentValues, 48);
        cursorColumnToContentValues(cursor, contentValues, 49);
        cursorColumnToContentValues(cursor, contentValues, 50);
        cursorColumnToContentValues(cursor, contentValues, 52);
        if (CompatUtils.isMarshmallowCompatible()) {
            cursorColumnToContentValues(cursor, contentValues, 62);
        }
        return contentValues;
    }

    private void cursorColumnToContentValues(Cursor cursor, ContentValues contentValues, int i) {
        int type = cursor.getType(i);
        if (type == 0) {
            return;
        }
        if (type == 1) {
            contentValues.put(ContactQuery.COLUMNS[i], Long.valueOf(cursor.getLong(i)));
        } else if (type == 3) {
            contentValues.put(ContactQuery.COLUMNS[i], cursor.getString(i));
        } else if (type == 4) {
            contentValues.put(ContactQuery.COLUMNS[i], cursor.getBlob(i));
        } else {
            throw new IllegalStateException("Invalid or unhandled data type");
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:12|13) */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r8 = TAG;
        android.util.Log.w(r8, "Contact directory resource not found: " + r1 + "." + r2);
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x005b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void loadDirectoryMetaData(com.android.contacts.model.Contact r12) {
        /*
            r11 = this;
            long r0 = r12.getDirectoryId()
            android.content.Context r2 = r11.getContext()
            android.content.ContentResolver r3 = r2.getContentResolver()
            android.net.Uri r2 = android.provider.ContactsContract.Directory.CONTENT_URI
            android.net.Uri r4 = android.content.ContentUris.withAppendedId(r2, r0)
            java.lang.String[] r5 = com.android.contacts.model.ContactLoader.DirectoryQuery.COLUMNS
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r0 = r3.query(r4, r5, r6, r7, r8)
            if (r0 != 0) goto L_0x001e
            return
        L_0x001e:
            boolean r1 = r0.moveToFirst()     // Catch:{ all -> 0x0081 }
            if (r1 == 0) goto L_0x007d
            r1 = 0
            java.lang.String r3 = r0.getString(r1)     // Catch:{ all -> 0x0081 }
            r1 = 1
            java.lang.String r1 = r0.getString(r1)     // Catch:{ all -> 0x0081 }
            r2 = 2
            int r2 = r0.getInt(r2)     // Catch:{ all -> 0x0081 }
            r4 = 3
            java.lang.String r5 = r0.getString(r4)     // Catch:{ all -> 0x0081 }
            r4 = 4
            java.lang.String r6 = r0.getString(r4)     // Catch:{ all -> 0x0081 }
            r4 = 5
            int r7 = r0.getInt(r4)     // Catch:{ all -> 0x0081 }
            r4 = 0
            boolean r8 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x0081 }
            if (r8 != 0) goto L_0x0079
            android.content.Context r8 = r11.getContext()     // Catch:{ all -> 0x0081 }
            android.content.pm.PackageManager r8 = r8.getPackageManager()     // Catch:{ all -> 0x0081 }
            android.content.res.Resources r8 = r8.getResourcesForApplication(r1)     // Catch:{ NameNotFoundException -> 0x005b }
            java.lang.String r1 = r8.getString(r2)     // Catch:{ NameNotFoundException -> 0x005b }
            r4 = r1
            goto L_0x0079
        L_0x005b:
            java.lang.String r8 = TAG     // Catch:{ all -> 0x0081 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x0081 }
            r9.<init>()     // Catch:{ all -> 0x0081 }
            java.lang.String r10 = "Contact directory resource not found: "
            r9.append(r10)     // Catch:{ all -> 0x0081 }
            r9.append(r1)     // Catch:{ all -> 0x0081 }
            java.lang.String r1 = "."
            r9.append(r1)     // Catch:{ all -> 0x0081 }
            r9.append(r2)     // Catch:{ all -> 0x0081 }
            java.lang.String r1 = r9.toString()     // Catch:{ all -> 0x0081 }
            android.util.Log.w(r8, r1)     // Catch:{ all -> 0x0081 }
        L_0x0079:
            r2 = r12
            r2.setDirectoryMetaData(r3, r4, r5, r6, r7)     // Catch:{ all -> 0x0081 }
        L_0x007d:
            r0.close()
            return
        L_0x0081:
            r12 = move-exception
            r0.close()
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.model.ContactLoader.loadDirectoryMetaData(com.android.contacts.model.Contact):void");
    }

    private static class AccountKey {
        private final String mAccountName;
        private final String mAccountType;
        private final String mDataSet;

        public AccountKey(String str, String str2, String str3) {
            this.mAccountName = str;
            this.mAccountType = str2;
            this.mDataSet = str3;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{this.mAccountName, this.mAccountType, this.mDataSet});
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof AccountKey)) {
                return false;
            }
            AccountKey accountKey = (AccountKey) obj;
            if (!Objects.equals(this.mAccountName, accountKey.mAccountName) || !Objects.equals(this.mAccountType, accountKey.mAccountType) || !Objects.equals(this.mDataSet, accountKey.mDataSet)) {
                return false;
            }
            return true;
        }
    }

    private void loadGroupMetaData(Contact contact) {
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        UnmodifiableIterator<RawContact> it = contact.getRawContacts().iterator();
        while (it.hasNext()) {
            RawContact next = it.next();
            String accountName = next.getAccountName();
            String accountTypeString = next.getAccountTypeString();
            String dataSet = next.getDataSet();
            AccountKey accountKey = new AccountKey(accountName, accountTypeString, dataSet);
            if (!(accountName == null || accountTypeString == null || hashSet.contains(accountKey))) {
                hashSet.add(accountKey);
                if (sb.length() != 0) {
                    sb.append(" OR ");
                }
                sb.append("(account_name=? AND account_type=?");
                arrayList.add(accountName);
                arrayList.add(accountTypeString);
                sb.append(" AND deleted=0");
                if (dataSet != null) {
                    sb.append(" AND data_set=?");
                    arrayList.add(dataSet);
                } else {
                    sb.append(" AND data_set IS NULL");
                }
                sb.append(")");
            }
        }
        ImmutableList.Builder builder = new ImmutableList.Builder();
        Cursor query = getContext().getContentResolver().query(ContactsContract.Groups.CONTENT_URI, GroupMetaDataLoader.COLUMNS, sb.toString(), (String[]) arrayList.toArray(new String[0]), (String) null);
        if (query != null) {
            while (query.moveToNext()) {
                try {
                    builder.add((Object) new GroupMetaData(getContext(), query));
                } finally {
                    query.close();
                }
            }
        }
        contact.setGroupMetaData(builder.build());
    }

    private void computeFormattedPhoneNumbers(Contact contact) {
        String currentCountryIso = GeoUtil.getCurrentCountryIso(getContext());
        ImmutableList<RawContact> rawContacts = contact.getRawContacts();
        int size = rawContacts.size();
        for (int i = 0; i < size; i++) {
            List<DataItem> dataItems = rawContacts.get(i).getDataItems();
            int size2 = dataItems.size();
            for (int i2 = 0; i2 < size2; i2++) {
                DataItem dataItem = dataItems.get(i2);
                if (dataItem instanceof PhoneDataItem) {
                    ((PhoneDataItem) dataItem).computeFormattedPhoneNumber(currentCountryIso);
                }
            }
        }
    }

    public void deliverResult(Contact contact) {
        unregisterObserver();
        if (!isReset() && contact != null) {
            this.mContact = contact;
            if (contact.isLoaded()) {
                this.mLookupUri = contact.getLookupUri();
                if (!contact.isDirectoryEntry()) {
                    String str = TAG;
                    Log.i(str, "Registering content observer for " + this.mLookupUri);
                    if (this.mObserver == null) {
                        this.mObserver = new Loader.ForceLoadContentObserver(this);
                    }
                    getContext().getContentResolver().registerContentObserver(this.mLookupUri, true, this.mObserver);
                }
                if (this.mPostViewNotification) {
                    postViewNotificationToSyncAdapter();
                }
            }
            super.deliverResult(this.mContact);
        }
    }

    private void postViewNotificationToSyncAdapter() {
        Context context = getContext();
        UnmodifiableIterator<RawContact> it = this.mContact.getRawContacts().iterator();
        while (it.hasNext()) {
            RawContact next = it.next();
            long longValue = next.getId().longValue();
            if (!this.mNotifiedRawContactIds.contains(Long.valueOf(longValue))) {
                this.mNotifiedRawContactIds.add(Long.valueOf(longValue));
                AccountType accountType = next.getAccountType(context);
                String viewContactNotifyServiceClassName = accountType.getViewContactNotifyServiceClassName();
                String viewContactNotifyServicePackageName = accountType.getViewContactNotifyServicePackageName();
                if (!TextUtils.isEmpty(viewContactNotifyServiceClassName) && !TextUtils.isEmpty(viewContactNotifyServicePackageName)) {
                    Uri withAppendedId = ContentUris.withAppendedId(ContactsContract.RawContacts.CONTENT_URI, longValue);
                    Intent intent = new Intent();
                    intent.setDataAndType(withAppendedId, "vnd.android.cursor.item/raw_contact");
                    if (accountType instanceof GoogleAccountType) {
                        intent.setPackage(viewContactNotifyServicePackageName);
                        intent.setAction("com.google.android.syncadapters.contacts.SYNC_HIGH_RES_PHOTO");
                        List<ResolveInfo> queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 0);
                        if (!queryBroadcastReceivers.isEmpty()) {
                            if (Log.isLoggable(TAG, 3)) {
                                for (ResolveInfo resolveInfo : queryBroadcastReceivers) {
                                    Log.d(TAG, resolveInfo.activityInfo.toString());
                                }
                            }
                            context.sendBroadcast(intent);
                        }
                    }
                    intent.setClassName(viewContactNotifyServicePackageName, viewContactNotifyServiceClassName);
                    intent.setAction("android.intent.action.VIEW");
                    try {
                        context.startService(intent);
                    } catch (Exception e) {
                        Log.e(TAG, "Error sending message to source-app", e);
                    }
                }
            }
        }
    }

    private void unregisterObserver() {
        if (this.mObserver != null) {
            getContext().getContentResolver().unregisterContentObserver(this.mObserver);
            this.mObserver = null;
        }
    }

    public Uri getLookupUri() {
        return this.mLookupUri;
    }

    /* access modifiers changed from: protected */
    public void onStartLoading() {
        Contact contact = this.mContact;
        if (contact != null) {
            deliverResult(contact);
        }
        if (takeContentChanged() || this.mContact == null) {
            forceLoad();
        }
    }

    /* access modifiers changed from: protected */
    public void onStopLoading() {
        cancelLoad();
    }

    /* access modifiers changed from: protected */
    public void onReset() {
        super.onReset();
        cancelLoad();
        unregisterObserver();
        this.mContact = null;
    }

    public void cacheResult() {
        Contact contact = this.mContact;
        if (contact == null || !contact.isLoaded()) {
            sCachedResult = null;
        } else {
            sCachedResult = this.mContact;
        }
    }
}
