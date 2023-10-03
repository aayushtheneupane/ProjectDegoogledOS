package com.android.contacts.common.model;

import android.content.AsyncTaskLoader;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.p002v7.appcompat.R$style;
import android.text.TextUtils;
import com.android.contacts.common.GroupMetaData;
import com.android.contacts.common.model.account.AccountType;
import com.android.contacts.common.model.account.AccountTypeWithDataSet;
import com.android.contacts.common.model.dataitem.DataItem;
import com.android.contacts.common.model.dataitem.PhoneDataItem;
import com.android.dialer.common.LogUtil;
import com.android.dialer.phonenumberutil.PhoneNumberHelper;
import com.android.dialer.util.PermissionsUtil;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ContactLoader extends AsyncTaskLoader<Contact> {
    private static Contact sCachedResult;
    private boolean mComputeFormattedPhoneNumber;
    private Contact mContact;
    private boolean mLoadGroupMetaData;
    private boolean mLoadInvitableAccountTypes;
    private Uri mLookupUri;
    private final Set<Long> mNotifiedRawContactIds = new HashSet();
    private Loader<Contact>.ForceLoadContentObserver mObserver;
    private boolean mPostViewNotification;
    private final Uri mRequestedUri;

    private static class AccountKey {
        private final String mAccountName;
        private final String mAccountType;
        private final String mDataSet;

        public AccountKey(String str, String str2, String str3) {
            this.mAccountName = str;
            this.mAccountType = str2;
            this.mDataSet = str3;
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

        public int hashCode() {
            return Objects.hash(new Object[]{this.mAccountName, this.mAccountType, this.mDataSet});
        }
    }

    private static class ContactQuery {
        static final String[] COLUMNS;
        static final String[] COLUMNS_INTERNAL = {"name_raw_contact_id", "display_name_source", "lookup", "display_name", "display_name_alt", "phonetic_name", "photo_id", "starred", "contact_presence", "contact_status", "contact_status_ts", "contact_status_res_package", "contact_status_label", "contact_id", "raw_contact_id", "account_name", "account_type", "data_set", "dirty", "version", "sourceid", "sync1", "sync2", "sync3", "sync4", "deleted", "data_id", "data1", "data2", "data3", "data4", "data5", "data6", "data7", "data8", "data9", "data10", "data11", "data12", "data13", "data14", "data15", "data_sync1", "data_sync2", "data_sync3", "data_sync4", "data_version", "is_primary", "is_super_primary", "mimetype", "group_sourceid", "mode", "chat_capability", "status", "status_res_package", "status_icon", "status_label", "status_ts", "photo_uri", "send_to_voicemail", "custom_ringtone", "is_user_profile", "times_used", "last_time_used"};

        static {
            ArrayList newArrayList = Lists.newArrayList((E[]) COLUMNS_INTERNAL);
            newArrayList.add("carrier_presence");
            COLUMNS = (String[]) newArrayList.toArray(new String[newArrayList.size()]);
        }
    }

    private static class DirectoryQuery {
        static final String[] COLUMNS = {"displayName", "packageName", "typeResourceId", "accountType", "accountName", "exportSupport"};
    }

    private static class GroupQuery {
        static final String[] COLUMNS = {"account_name", "account_type", "data_set", "_id", "title", "auto_add", "favorites"};
    }

    public ContactLoader(Context context, Uri uri, boolean z) {
        super(context);
        this.mLookupUri = uri;
        this.mRequestedUri = uri;
        this.mLoadGroupMetaData = false;
        this.mLoadInvitableAccountTypes = false;
        this.mPostViewNotification = z;
        this.mComputeFormattedPhoneNumber = false;
    }

    private void computeFormattedPhoneNumbers(Contact contact) {
        String currentCountryIso = R$style.getCurrentCountryIso(getContext());
        ImmutableList<RawContact> rawContacts = contact.getRawContacts();
        int size = rawContacts.size();
        for (int i = 0; i < size; i++) {
            List<DataItem> dataItems = rawContacts.get(i).getDataItems();
            int size2 = dataItems.size();
            for (int i2 = 0; i2 < size2; i2++) {
                DataItem dataItem = dataItems.get(i2);
                if (dataItem instanceof PhoneDataItem) {
                    PhoneDataItem phoneDataItem = (PhoneDataItem) dataItem;
                    Context context = getContext();
                    String asString = phoneDataItem.getContentValues().getAsString("data1");
                    if (asString != null) {
                        phoneDataItem.getContentValues().put("formattedPhoneNumber", PhoneNumberHelper.formatNumber(context, asString, phoneDataItem.getContentValues().getAsString("data4"), currentCountryIso));
                    }
                }
            }
        }
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

    /* JADX INFO: finally extract failed */
    private Contact loadContactEntity(ContentResolver contentResolver, Uri uri) {
        Cursor query = contentResolver.query(Uri.withAppendedPath(uri, "entities"), ContactQuery.COLUMNS, (String) null, (String[]) null, "raw_contact_id");
        if (query == null) {
            LogUtil.m8e("ContactLoader", "No cursor returned in loadContactEntity", new Object[0]);
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
                }
            } while (query.moveToNext());
            loadContactHeaderData.setRawContacts(builder.build());
            query.close();
            return loadContactHeaderData;
        } catch (Throwable th) {
            query.close();
            throw th;
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
        cursorColumnToContentValues(cursor, contentValues, 62);
        cursorColumnToContentValues(cursor, contentValues, 63);
        cursorColumnToContentValues(cursor, contentValues, 64);
        return contentValues;
    }

    private void loadDirectoryMetaData(Contact contact) {
        String string;
        int i;
        String str;
        Cursor query = getContext().getContentResolver().query(ContentUris.withAppendedId(ContactsContract.Directory.CONTENT_URI, contact.getDirectoryId()), DirectoryQuery.COLUMNS, (String) null, (String[]) null, (String) null);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    String string2 = query.getString(0);
                    string = query.getString(1);
                    i = query.getInt(2);
                    String string3 = query.getString(3);
                    String string4 = query.getString(4);
                    int i2 = query.getInt(5);
                    if (!TextUtils.isEmpty(string)) {
                        str = getContext().getPackageManager().getResourcesForApplication(string).getString(i);
                        contact.setDirectoryMetaData(string2, str, string3, string4, i2);
                    }
                    str = null;
                    contact.setDirectoryMetaData(string2, str, string3, string4, i2);
                }
            } catch (PackageManager.NameNotFoundException unused) {
                LogUtil.m10w("ContactLoader", "Contact directory resource not found: " + string + "." + i, new Object[0]);
            } catch (Throwable th) {
                query.close();
                throw th;
            }
            query.close();
        }
    }

    private static Contact loadEncodedContactEntity(Uri uri, Uri uri2) throws JSONException {
        JSONObject jSONObject = new JSONObject(uri.getEncodedFragment());
        long longValue = Long.valueOf(uri.getQueryParameter("directory")).longValue();
        String optString = jSONObject.optString("display_name");
        String optString2 = jSONObject.optString("display_name_alt", optString);
        Contact contact = r0;
        Contact contact2 = new Contact(uri, uri, uri2, longValue, (String) null, -1, -1, jSONObject.getInt("display_name_source"), 0, jSONObject.optString("photo_uri", (String) null), optString, optString2, (String) null, false, (Integer) null, false, (String) null, false);
        JSONObject jSONObject2 = jSONObject;
        String optString3 = jSONObject2.optString("account_name", (String) null);
        String queryParameter = uri.getQueryParameter("displayName");
        if (optString3 != null) {
            contact.setDirectoryMetaData(queryParameter, (String) null, optString3, jSONObject2.getString("account_type"), jSONObject2.optInt("exportSupport", 1));
        } else {
            contact.setDirectoryMetaData(queryParameter, (String) null, (String) null, (String) null, jSONObject2.optInt("exportSupport", 2));
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
        ImmutableList.Builder builder = new ImmutableList.Builder();
        builder.add((Object) rawContact);
        Contact contact3 = contact;
        contact3.setRawContacts(builder.build());
        return contact3;
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
        Cursor query = getContext().getContentResolver().query(ContactsContract.Groups.CONTENT_URI, GroupQuery.COLUMNS, sb.toString(), (String[]) arrayList.toArray(new String[0]), (String) null);
        if (query != null) {
            while (query.moveToNext()) {
                try {
                    builder.add((Object) new GroupMetaData(query.getString(0), query.getString(1), query.getString(2), query.getLong(3), query.getString(4), !query.isNull(5) && query.getInt(5) != 0, !query.isNull(6) && query.getInt(6) != 0));
                } finally {
                    query.close();
                }
            }
        }
        contact.setGroupMetaData(builder.build());
    }

    private void loadInvitableAccountTypes(Contact contact) {
        ImmutableList.Builder builder = new ImmutableList.Builder();
        if (!contact.isUserProfile()) {
            Map<AccountTypeWithDataSet, AccountType> usableInvitableAccountTypes = AccountTypeManager.getInstance(getContext()).getUsableInvitableAccountTypes();
            if (!usableInvitableAccountTypes.isEmpty()) {
                HashMap hashMap = new HashMap(usableInvitableAccountTypes);
                UnmodifiableIterator<RawContact> it = contact.getRawContacts().iterator();
                while (it.hasNext()) {
                    RawContact next = it.next();
                    hashMap.remove(AccountTypeWithDataSet.get(next.getAccountTypeString(), next.getDataSet()));
                }
                builder.addAll(hashMap.values());
            }
        }
        contact.setInvitableAccountTypes(builder.build());
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x009c A[Catch:{ all -> 0x00b1, IOException -> 0x00bb }, LOOP:2: B:24:0x0095->B:27:0x009c, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00ad A[Catch:{ all -> 0x00b1, IOException -> 0x00bb }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00a1 A[EDGE_INSN: B:47:0x00a1->B:28:0x00a1 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:49:? A[Catch:{  }, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void loadPhotoBinaryData(com.android.contacts.common.model.Contact r8) {
        /*
            r7 = this;
            long r0 = r8.getPhotoId()
            r2 = 0
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 > 0) goto L_0x000b
            goto L_0x0050
        L_0x000b:
            com.google.common.collect.ImmutableList r2 = r8.getRawContacts()
            com.google.common.collect.UnmodifiableIterator r2 = r2.iterator()
        L_0x0013:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0050
            java.lang.Object r3 = r2.next()
            com.android.contacts.common.model.RawContact r3 = (com.android.contacts.common.model.RawContact) r3
            java.util.List r3 = r3.getDataItems()
            java.util.Iterator r3 = r3.iterator()
        L_0x0027:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0013
            java.lang.Object r4 = r3.next()
            com.android.contacts.common.model.dataitem.DataItem r4 = (com.android.contacts.common.model.dataitem.DataItem) r4
            long r5 = r4.getId()
            int r5 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r5 != 0) goto L_0x0027
            boolean r3 = r4 instanceof com.android.contacts.common.model.dataitem.PhotoDataItem
            if (r3 != 0) goto L_0x0040
            goto L_0x0013
        L_0x0040:
            com.android.contacts.common.model.dataitem.PhotoDataItem r4 = (com.android.contacts.common.model.dataitem.PhotoDataItem) r4
            android.content.ContentValues r3 = r4.getContentValues()
            java.lang.String r4 = "data15"
            byte[] r3 = r3.getAsByteArray(r4)
            r8.setThumbnailPhotoBinaryData(r3)
            goto L_0x0013
        L_0x0050:
            java.lang.String r0 = r8.getPhotoUri()
            if (r0 == 0) goto L_0x00bb
            android.net.Uri r1 = android.net.Uri.parse(r0)     // Catch:{ IOException -> 0x00bb }
            java.lang.String r2 = r1.getScheme()     // Catch:{ IOException -> 0x00bb }
            java.lang.String r3 = "http"
            boolean r3 = r3.equals(r2)     // Catch:{ IOException -> 0x00bb }
            if (r3 != 0) goto L_0x0082
            java.lang.String r3 = "https"
            boolean r2 = r3.equals(r2)     // Catch:{ IOException -> 0x00bb }
            if (r2 == 0) goto L_0x006f
            goto L_0x0082
        L_0x006f:
            android.content.Context r7 = r7.getContext()     // Catch:{ IOException -> 0x00bb }
            android.content.ContentResolver r7 = r7.getContentResolver()     // Catch:{ IOException -> 0x00bb }
            java.lang.String r0 = "r"
            android.content.res.AssetFileDescriptor r7 = r7.openAssetFileDescriptor(r1, r0)     // Catch:{ IOException -> 0x00bb }
            java.io.FileInputStream r0 = r7.createInputStream()     // Catch:{ IOException -> 0x00bb }
            goto L_0x008c
        L_0x0082:
            java.net.URL r7 = new java.net.URL     // Catch:{ IOException -> 0x00bb }
            r7.<init>(r0)     // Catch:{ IOException -> 0x00bb }
            java.io.InputStream r0 = r7.openStream()     // Catch:{ IOException -> 0x00bb }
            r7 = 0
        L_0x008c:
            r1 = 16384(0x4000, float:2.2959E-41)
            byte[] r1 = new byte[r1]     // Catch:{ IOException -> 0x00bb }
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x00bb }
            r2.<init>()     // Catch:{ IOException -> 0x00bb }
        L_0x0095:
            int r3 = r0.read(r1)     // Catch:{ all -> 0x00b1 }
            r4 = -1
            if (r3 == r4) goto L_0x00a1
            r4 = 0
            r2.write(r1, r4, r3)     // Catch:{ all -> 0x00b1 }
            goto L_0x0095
        L_0x00a1:
            byte[] r1 = r2.toByteArray()     // Catch:{ all -> 0x00b1 }
            r8.setPhotoBinaryData(r1)     // Catch:{ all -> 0x00b1 }
            r0.close()     // Catch:{ IOException -> 0x00bb }
            if (r7 == 0) goto L_0x00b0
            r7.close()     // Catch:{ IOException -> 0x00bb }
        L_0x00b0:
            return
        L_0x00b1:
            r1 = move-exception
            r0.close()     // Catch:{ IOException -> 0x00bb }
            if (r7 == 0) goto L_0x00ba
            r7.close()     // Catch:{ IOException -> 0x00bb }
        L_0x00ba:
            throw r1     // Catch:{ IOException -> 0x00bb }
        L_0x00bb:
            byte[] r7 = r8.getThumbnailPhotoBinaryData()
            r8.setPhotoBinaryData(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.common.model.ContactLoader.loadPhotoBinaryData(com.android.contacts.common.model.Contact):void");
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

    public static Contact parseEncodedContactEntity(Uri uri) {
        try {
            return loadEncodedContactEntity(uri, uri);
        } catch (JSONException unused) {
            return null;
        }
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

    private void unregisterObserver() {
        if (this.mObserver != null) {
            getContext().getContentResolver().unregisterContentObserver(this.mObserver);
            this.mObserver = null;
        }
    }

    public Object loadInBackground() {
        boolean z;
        Contact contact;
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("loadInBackground=");
        outline13.append(this.mLookupUri);
        outline13.toString();
        try {
            ContentResolver contentResolver = getContext().getContentResolver();
            Uri ensureIsContactUri = R$style.ensureIsContactUri(contentResolver, this.mLookupUri);
            Contact contact2 = sCachedResult;
            sCachedResult = null;
            if (contact2 == null || !R$style.areEqual(contact2.getLookupUri(), this.mLookupUri)) {
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
            if (!contact.isLoaded()) {
                return contact;
            }
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
            if (!this.mLoadInvitableAccountTypes || contact.getInvitableAccountTypes() != null) {
                return contact;
            }
            loadInvitableAccountTypes(contact);
            return contact;
        } catch (Exception e) {
            StringBuilder outline132 = GeneratedOutlineSupport.outline13("Error loading the contact: ");
            outline132.append(this.mLookupUri);
            LogUtil.m7e("ContactLoader", outline132.toString(), (Throwable) e);
            return Contact.forError(this.mRequestedUri, e);
        }
    }

    /* access modifiers changed from: protected */
    public void onReset() {
        super.onReset();
        cancelLoad();
        unregisterObserver();
        this.mContact = null;
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

    public void deliverResult(Contact contact) {
        unregisterObserver();
        if (!isReset() && contact != null) {
            this.mContact = contact;
            if (contact.isLoaded()) {
                this.mLookupUri = contact.getLookupUri();
                if (!contact.isDirectoryEntry()) {
                    if (this.mObserver == null) {
                        this.mObserver = new Loader.ForceLoadContentObserver(this);
                    }
                    if (PermissionsUtil.hasContactsReadPermissions(getContext())) {
                        getContext().getContentResolver().registerContentObserver(this.mLookupUri, true, this.mObserver);
                    } else {
                        LogUtil.m10w("ContactLoader.deliverResult", "contacts permission not available", new Object[0]);
                    }
                }
                if (this.mPostViewNotification) {
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
                                intent.setClassName(viewContactNotifyServicePackageName, viewContactNotifyServiceClassName);
                                intent.setAction("android.intent.action.VIEW");
                                intent.setDataAndType(withAppendedId, "vnd.android.cursor.item/raw_contact");
                                intent.addFlags(1);
                                try {
                                    context.startService(intent);
                                } catch (Exception e) {
                                    LogUtil.m7e("ContactLoader", "Error sending message to source-app", (Throwable) e);
                                }
                            }
                        }
                    }
                }
            }
            super.deliverResult(this.mContact);
        }
    }
}
