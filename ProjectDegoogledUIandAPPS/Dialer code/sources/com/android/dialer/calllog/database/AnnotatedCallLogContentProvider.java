package com.android.dialer.calllog.database;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import com.android.dialer.binary.aosp.DaggerAospDialerRootComponent;
import com.android.dialer.calllog.database.contract.AnnotatedCallLogContract;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.inject.HasRootComponent;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.Arrays;

public class AnnotatedCallLogContentProvider extends ContentProvider {
    private static final UriMatcher uriMatcher = new UriMatcher(-1);
    private final ThreadLocal<Boolean> applyingBatch = new ThreadLocal<>();
    private AnnotatedCallLogDatabaseHelper databaseHelper;

    static {
        uriMatcher.addURI(AnnotatedCallLogContract.AUTHORITY, "AnnotatedCallLog", 1);
        uriMatcher.addURI(AnnotatedCallLogContract.AUTHORITY, "AnnotatedCallLog/#", 2);
        uriMatcher.addURI(AnnotatedCallLogContract.AUTHORITY, "DistinctPhoneNumbers", 3);
    }

    private boolean isApplyingBatch() {
        return this.applyingBatch.get() != null && this.applyingBatch.get().booleanValue();
    }

    private void notifyChange(Uri uri) {
        getContext().getContentResolver().notifyChange(uri, (ContentObserver) null);
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
            int i = 0;
            while (i < arrayList.size()) {
                ContentProviderOperation contentProviderOperation = arrayList.get(i);
                int match = uriMatcher.match(contentProviderOperation.getUri());
                if (match == 1 || match == 2) {
                    ContentProviderResult apply = contentProviderOperation.apply(this, contentProviderResultArr, i);
                    if (arrayList.get(i).isInsert()) {
                        if (apply.uri == null) {
                            throw new OperationApplicationException("error inserting row");
                        }
                    } else if (apply.count.intValue() == 0) {
                        LogUtil.m10w("AnnotatedCallLogContentProvider.applyBatch", "update or delete failed, possibly because row got cleaned up", new Object[0]);
                    }
                    contentProviderResultArr[i] = apply;
                    i++;
                } else if (match != 3) {
                    throw new IllegalArgumentException("Unknown uri: " + contentProviderOperation.getUri());
                } else {
                    throw new UnsupportedOperationException();
                }
            }
            writableDatabase.setTransactionSuccessful();
            this.applyingBatch.set(false);
            writableDatabase.endTransaction();
            notifyChange(AnnotatedCallLogContract.AnnotatedCallLog.CONTENT_URI);
            return contentProviderResultArr;
        } catch (Throwable th) {
            this.applyingBatch.set(false);
            writableDatabase.endTransaction();
            throw th;
        }
    }

    public int delete(Uri uri, String str, String[] strArr) {
        SQLiteDatabase writableDatabase = this.databaseHelper.getWritableDatabase();
        int match = uriMatcher.match(uri);
        if (match != 1) {
            if (match == 2) {
                Assert.checkArgument(str == null, "Do not specify selection when deleting by ID", new Object[0]);
                Assert.checkArgument(strArr == null, "Do not specify selection args when deleting by ID", new Object[0]);
                long parseId = ContentUris.parseId(uri);
                Assert.checkArgument(parseId != -1, "error parsing id from uri %s", uri);
                str = "_id=" + parseId;
            } else if (match != 3) {
                throw new IllegalArgumentException(GeneratedOutlineSupport.outline6("Unknown uri: ", uri));
            } else {
                throw new UnsupportedOperationException();
            }
        }
        int delete = writableDatabase.delete("AnnotatedCallLog", str, strArr);
        if (delete == 0) {
            LogUtil.m10w("AnnotatedCallLogContentProvider.delete", "no rows deleted", new Object[0]);
            return delete;
        }
        if (!isApplyingBatch()) {
            getContext().getContentResolver().notifyChange(uri, (ContentObserver) null);
        }
        return delete;
    }

    public String getType(Uri uri) {
        return "vnd.android.cursor.item/annotated_call_log";
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        Assert.checkArgument(contentValues != null);
        CallLogDatabaseModule.check(contentValues, 1);
        SQLiteDatabase writableDatabase = this.databaseHelper.getWritableDatabase();
        int match = uriMatcher.match(uri);
        if (match == 1) {
            Assert.checkArgument(contentValues.get("_id") != null, "You must specify an _ID when inserting", new Object[0]);
        } else if (match == 2) {
            Long valueOf = Long.valueOf(ContentUris.parseId(uri));
            Long asLong = contentValues.getAsLong("_id");
            Assert.checkArgument(asLong == null || asLong.equals(valueOf), "_ID from values %d does not match ID from URI: %s", asLong, uri);
            if (asLong == null) {
                contentValues.put("_id", valueOf);
            }
        } else if (match != 3) {
            throw new IllegalArgumentException(GeneratedOutlineSupport.outline6("Unknown uri: ", uri));
        } else {
            throw new UnsupportedOperationException();
        }
        long insert = writableDatabase.insert("AnnotatedCallLog", (String) null, contentValues);
        if (insert < 0) {
            LogUtil.m10w("AnnotatedCallLogContentProvider.insert", "error inserting row with id: %d", contentValues.get("_id"));
            return null;
        }
        Uri withAppendedId = ContentUris.withAppendedId(AnnotatedCallLogContract.AnnotatedCallLog.CONTENT_URI, insert);
        if (!isApplyingBatch()) {
            notifyChange(withAppendedId);
        }
        return withAppendedId;
    }

    public boolean onCreate() {
        this.databaseHelper = ((DaggerAospDialerRootComponent) ((HasRootComponent) getContext().getApplicationContext()).component()).callLogDatabaseComponent().annotatedCallLogDatabaseHelper();
        return true;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        Uri uri2 = uri;
        SQLiteDatabase readableDatabase = this.databaseHelper.getReadableDatabase();
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("AnnotatedCallLog");
        int match = uriMatcher.match(uri);
        if (match == 1) {
            String[] strArr3 = strArr;
            Cursor query = sQLiteQueryBuilder.query(readableDatabase, strArr, str, strArr2, (String) null, (String) null, str2);
            if (query != null) {
                query.setNotificationUri(getContext().getContentResolver(), AnnotatedCallLogContract.AnnotatedCallLog.CONTENT_URI);
            } else {
                LogUtil.m10w("AnnotatedCallLogContentProvider.query", "cursor was null", new Object[0]);
            }
            return query;
        } else if (match == 2) {
            String[] strArr4 = strArr;
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("_id=");
            outline13.append(ContentUris.parseId(uri));
            sQLiteQueryBuilder.appendWhere(outline13.toString());
            Cursor query2 = sQLiteQueryBuilder.query(readableDatabase, strArr, str, strArr2, (String) null, (String) null, str2);
            if (query2 != null) {
                query2.setNotificationUri(getContext().getContentResolver(), AnnotatedCallLogContract.AnnotatedCallLog.CONTENT_URI);
            } else {
                LogUtil.m10w("AnnotatedCallLogContentProvider.query", "cursor was null", new Object[0]);
            }
            return query2;
        } else if (match == 3) {
            Assert.checkArgument(Arrays.equals(strArr, new String[]{"number"}), "only NUMBER supported for projection for distinct phone number query, got: %s", Arrays.toString(strArr));
            sQLiteQueryBuilder.setDistinct(true);
            Cursor query3 = sQLiteQueryBuilder.query(readableDatabase, strArr, str, strArr2, (String) null, (String) null, str2);
            if (query3 != null) {
                query3.setNotificationUri(getContext().getContentResolver(), AnnotatedCallLogContract.AnnotatedCallLog.CONTENT_URI);
            } else {
                LogUtil.m10w("AnnotatedCallLogContentProvider.query", "cursor was null", new Object[0]);
            }
            return query3;
        } else {
            throw new IllegalArgumentException(GeneratedOutlineSupport.outline6("Unknown uri: ", uri));
        }
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        boolean z = true;
        Assert.checkArgument(contentValues != null);
        CallLogDatabaseModule.check(contentValues, 2);
        SQLiteDatabase writableDatabase = this.databaseHelper.getWritableDatabase();
        int match = uriMatcher.match(uri);
        if (match == 1) {
            int update = writableDatabase.update("AnnotatedCallLog", contentValues, str, strArr);
            if (update == 0) {
                LogUtil.m10w("AnnotatedCallLogContentProvider.update", "no rows updated", new Object[0]);
                return update;
            }
            if (!isApplyingBatch()) {
                getContext().getContentResolver().notifyChange(uri, (ContentObserver) null);
            }
            return update;
        } else if (match == 2) {
            Assert.checkArgument(!contentValues.containsKey("_id"), "Do not specify _ID when updating by ID", new Object[0]);
            Assert.checkArgument(str == null, "Do not specify selection when updating by ID", new Object[0]);
            if (strArr != null) {
                z = false;
            }
            Assert.checkArgument(z, "Do not specify selection args when updating by ID", new Object[0]);
            int update2 = writableDatabase.update("AnnotatedCallLog", contentValues, "_id=" + ContentUris.parseId(uri), strArr);
            if (update2 == 0) {
                LogUtil.m10w("AnnotatedCallLogContentProvider.update", "no rows updated", new Object[0]);
                return update2;
            }
            if (!isApplyingBatch()) {
                getContext().getContentResolver().notifyChange(uri, (ContentObserver) null);
            }
            return update2;
        } else if (match != 3) {
            throw new IllegalArgumentException(GeneratedOutlineSupport.outline6("Unknown uri: ", uri));
        } else {
            throw new UnsupportedOperationException();
        }
    }
}
