package com.android.dialer.phonelookup.database;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import com.android.dialer.binary.aosp.DaggerAospDialerRootComponent;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.inject.HasRootComponent;
import com.android.dialer.phonelookup.database.contract.PhoneLookupHistoryContract;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.List;

public class PhoneLookupHistoryContentProvider extends ContentProvider {
    private final ThreadLocal<Boolean> applyingBatch = new ThreadLocal<>();
    private PhoneLookupHistoryDatabaseHelper databaseHelper;

    private boolean isApplyingBatch() {
        return this.applyingBatch.get() != null && this.applyingBatch.get().booleanValue();
    }

    private void notifyChange(Uri uri) {
        getContext().getContentResolver().notifyChange(uri, (ContentObserver) null);
    }

    private int uriType(Uri uri) {
        Assert.checkArgument(uri.getAuthority().equals(PhoneLookupHistoryContract.AUTHORITY));
        List<String> pathSegments = uri.getPathSegments();
        Assert.checkArgument(pathSegments.size() == 1);
        Assert.checkArgument(pathSegments.get(0).equals("PhoneLookupHistory"));
        if (uri.getQueryParameter("number") == null) {
            return 1;
        }
        return 2;
    }

    /* JADX INFO: finally extract failed */
    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> arrayList) throws OperationApplicationException {
        ContentProviderResult[] contentProviderResultArr = new ContentProviderResult[arrayList.size()];
        if (arrayList.isEmpty()) {
            return contentProviderResultArr;
        }
        SQLiteDatabase writableDatabase = this.databaseHelper.getWritableDatabase();
        try {
            this.applyingBatch.set(true);
            writableDatabase.beginTransaction();
            for (int i = 0; i < arrayList.size(); i++) {
                ContentProviderOperation contentProviderOperation = arrayList.get(i);
                int uriType = uriType(contentProviderOperation.getUri());
                if (uriType != 1) {
                    if (uriType != 2) {
                        throw new IllegalArgumentException("Unknown uri: " + contentProviderOperation.getUri());
                    }
                }
                ContentProviderResult apply = contentProviderOperation.apply(this, contentProviderResultArr, i);
                if (contentProviderOperation.isInsert()) {
                    if (apply.uri == null) {
                        throw new OperationApplicationException("error inserting row");
                    }
                } else if (apply.count.intValue() == 0) {
                    throw new OperationApplicationException("error applying operation");
                }
                contentProviderResultArr[i] = apply;
            }
            writableDatabase.setTransactionSuccessful();
            this.applyingBatch.set(false);
            writableDatabase.endTransaction();
            notifyChange(PhoneLookupHistoryContract.PhoneLookupHistory.CONTENT_URI);
            return contentProviderResultArr;
        } catch (Throwable th) {
            this.applyingBatch.set(false);
            writableDatabase.endTransaction();
            throw th;
        }
    }

    public int delete(Uri uri, String str, String[] strArr) {
        SQLiteDatabase writableDatabase = this.databaseHelper.getWritableDatabase();
        int uriType = uriType(uri);
        if (uriType != 1) {
            if (uriType == 2) {
                Assert.checkArgument(str == null, "Do not specify selection when deleting by number", new Object[0]);
                Assert.checkArgument(strArr == null, "Do not specify selection args when deleting by number", new Object[0]);
                String decode = Uri.decode(uri.getQueryParameter("number"));
                Assert.checkArgument(decode != null, "error parsing number from uri: %s", LogUtil.sanitizePii(uri));
                strArr = new String[]{decode};
                str = "normalized_number= ?";
            } else {
                throw new IllegalArgumentException(GeneratedOutlineSupport.outline6("Unknown uri: ", uri));
            }
        }
        int delete = writableDatabase.delete("PhoneLookupHistory", str, strArr);
        if (delete == 0) {
            LogUtil.m10w("PhoneLookupHistoryContentProvider.delete", "no rows deleted", new Object[0]);
            return delete;
        }
        if (!isApplyingBatch()) {
            getContext().getContentResolver().notifyChange(uri, (ContentObserver) null);
        }
        return delete;
    }

    public String getType(Uri uri) {
        return "vnd.android.cursor.item/phone_lookup_history";
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        Assert.checkArgument(contentValues != null);
        SQLiteDatabase writableDatabase = this.databaseHelper.getWritableDatabase();
        int uriType = uriType(uri);
        if (uriType == 1) {
            Assert.checkArgument(contentValues.getAsString("normalized_number") != null, "You must specify a normalized number when inserting", new Object[0]);
        } else if (uriType == 2) {
            String decode = Uri.decode(uri.getQueryParameter("number"));
            String asString = contentValues.getAsString("normalized_number");
            Assert.checkArgument(asString == null || asString.equals(decode), "NORMALIZED_NUMBER from values %s does not match normalized number from URI: %s", LogUtil.sanitizePhoneNumber(asString), LogUtil.sanitizePhoneNumber(decode));
            if (asString == null) {
                contentValues.put("normalized_number", decode);
            }
        } else {
            throw new IllegalArgumentException(GeneratedOutlineSupport.outline6("Unknown uri: ", uri));
        }
        if (writableDatabase.insert("PhoneLookupHistory", (String) null, contentValues) < 0) {
            LogUtil.m10w("PhoneLookupHistoryContentProvider.insert", "error inserting row with number: %s", LogUtil.sanitizePhoneNumber(contentValues.getAsString("normalized_number")));
            return null;
        }
        Uri contentUriForNumber = PhoneLookupHistoryContract.PhoneLookupHistory.contentUriForNumber(contentValues.getAsString("normalized_number"));
        if (!isApplyingBatch()) {
            notifyChange(contentUriForNumber);
        }
        return contentUriForNumber;
    }

    public boolean onCreate() {
        this.databaseHelper = ((DaggerAospDialerRootComponent) ((HasRootComponent) getContext().getApplicationContext()).component()).phoneLookupDatabaseComponent().phoneLookupHistoryDatabaseHelper();
        return true;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        SQLiteDatabase readableDatabase = this.databaseHelper.getReadableDatabase();
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("PhoneLookupHistory");
        int uriType = uriType(uri);
        if (uriType != 1) {
            if (uriType == 2) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("normalized_number=");
                outline13.append(DatabaseUtils.sqlEscapeString(Uri.decode(uri.getQueryParameter("number"))));
                sQLiteQueryBuilder.appendWhere(outline13.toString());
            } else {
                throw new IllegalArgumentException(GeneratedOutlineSupport.outline6("Unknown uri: ", uri));
            }
        }
        Cursor query = sQLiteQueryBuilder.query(readableDatabase, strArr, str, strArr2, (String) null, (String) null, str2);
        if (query == null) {
            LogUtil.m10w("PhoneLookupHistoryContentProvider.query", "cursor was null", new Object[0]);
            return null;
        }
        query.setNotificationUri(getContext().getContentResolver(), PhoneLookupHistoryContract.PhoneLookupHistory.CONTENT_URI);
        return query;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        Assert.checkArgument(contentValues != null);
        SQLiteDatabase writableDatabase = this.databaseHelper.getWritableDatabase();
        int uriType = uriType(uri);
        if (uriType == 1) {
            int update = writableDatabase.update("PhoneLookupHistory", contentValues, str, strArr);
            if (update == 0) {
                LogUtil.m10w("PhoneLookupHistoryContentProvider.update", "no rows updated", new Object[0]);
                return update;
            }
            if (!isApplyingBatch()) {
                getContext().getContentResolver().notifyChange(uri, (ContentObserver) null);
            }
            return update;
        } else if (uriType == 2) {
            Assert.checkArgument(!contentValues.containsKey("normalized_number"), "Do not specify number in values when updating by number", new Object[0]);
            Assert.checkArgument(str == null, "Do not specify selection when updating by ID", new Object[0]);
            Assert.checkArgument(strArr == null, "Do not specify selection args when updating by ID", new Object[0]);
            contentValues.put("normalized_number", Uri.decode(uri.getQueryParameter("number")));
            Assert.checkArgument(writableDatabase.replace("PhoneLookupHistory", (String) null, contentValues) != -1, "replacing PhoneLookupHistory row failed", new Object[0]);
            if (!isApplyingBatch()) {
                getContext().getContentResolver().notifyChange(uri, (ContentObserver) null);
            }
            return 1;
        } else {
            throw new IllegalArgumentException(GeneratedOutlineSupport.outline6("Unknown uri: ", uri));
        }
    }
}
