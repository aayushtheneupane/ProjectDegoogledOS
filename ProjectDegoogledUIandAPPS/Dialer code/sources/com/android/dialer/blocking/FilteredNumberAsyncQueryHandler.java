package com.android.dialer.blocking;

import android.content.AsyncQueryHandler;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.net.Uri;
import android.support.design.R$dimen;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import com.android.dialer.common.LogUtil;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Deprecated
public class FilteredNumberAsyncQueryHandler extends AsyncQueryHandler {
    static final int BLOCKED_NUMBER_CACHE_NULL_ID = -1;
    static final Map<String, Integer> blockedNumberCache = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public final Context context;

    private static abstract class Listener {
        private Listener() {
        }

        /* access modifiers changed from: protected */
        public void onDeleteComplete(int i, Object obj, int i2) {
        }

        /* access modifiers changed from: protected */
        public void onInsertComplete(int i, Object obj, Uri uri) {
        }

        /* access modifiers changed from: protected */
        public void onQueryComplete(int i, Object obj, Cursor cursor) {
        }

        /* synthetic */ Listener(C03751 r1) {
        }
    }

    public interface OnBlockNumberListener {
        void onBlockComplete(Uri uri);
    }

    public interface OnCheckBlockedListener {
        void onCheckComplete(Integer num);
    }

    interface OnHasBlockedNumbersListener {
        void onHasBlockedNumbers(boolean z);
    }

    public interface OnUnblockNumberListener {
        void onUnblockComplete(int i, ContentValues contentValues);
    }

    public FilteredNumberAsyncQueryHandler(Context context2) {
        super(context2.getContentResolver());
        this.context = context2;
    }

    private String getIsBlockedNumberSelection(boolean z) {
        return (!FilteredNumberCompat.useNewFiltering(this.context) || z) ? FilteredNumberCompat.useNewFiltering(this.context) ? "e164_number" : "normalized_number" : FilteredNumberCompat.useNewFiltering(this.context) ? "original_number" : "number";
    }

    public void blockNumber(OnBlockNumberListener onBlockNumberListener, String str, String str2) {
        blockNumber(onBlockNumberListener, (String) null, str, str2);
    }

    public void clearCache() {
        blockedNumberCache.clear();
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x00c0  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00c8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Integer getBlockedIdSynchronous(java.lang.String r12, java.lang.String r13) {
        /*
            r11 = this;
            java.lang.String r0 = "_id"
            com.android.dialer.common.Assert.isWorkerThread()
            r1 = 0
            if (r12 != 0) goto L_0x0009
            return r1
        L_0x0009:
            android.content.Context r2 = r11.context
            boolean r2 = com.android.dialer.blocking.FilteredNumberCompat.canAttemptBlockOperations(r2)
            if (r2 != 0) goto L_0x0012
            return r1
        L_0x0012:
            java.util.Map<java.lang.String, java.lang.Integer> r2 = blockedNumberCache
            java.lang.Object r2 = r2.get(r12)
            java.lang.Integer r2 = (java.lang.Integer) r2
            r3 = -1
            if (r2 == 0) goto L_0x0026
            int r11 = r2.intValue()
            if (r11 != r3) goto L_0x0024
            goto L_0x0025
        L_0x0024:
            r1 = r2
        L_0x0025:
            return r1
        L_0x0026:
            java.lang.String r13 = android.telephony.PhoneNumberUtils.formatNumberToE164(r12, r13)
            android.content.Context r2 = r11.context
            java.lang.String r2 = com.android.dialer.blocking.FilteredNumbersUtil.getBlockableNumber(r2, r13, r12)
            boolean r4 = android.text.TextUtils.isEmpty(r2)
            if (r4 == 0) goto L_0x0037
            return r1
        L_0x0037:
            android.content.Context r4 = r11.context     // Catch:{ SecurityException -> 0x00b7, all -> 0x00b5 }
            android.content.ContentResolver r5 = r4.getContentResolver()     // Catch:{ SecurityException -> 0x00b7, all -> 0x00b5 }
            android.content.Context r4 = r11.context     // Catch:{ SecurityException -> 0x00b7, all -> 0x00b5 }
            android.net.Uri r6 = com.android.dialer.blocking.FilteredNumberCompat.getContentUri(r4, r1)     // Catch:{ SecurityException -> 0x00b7, all -> 0x00b5 }
            r4 = 2
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SecurityException -> 0x00b7, all -> 0x00b5 }
            android.content.Context r7 = r11.context     // Catch:{ SecurityException -> 0x00b7, all -> 0x00b5 }
            com.android.dialer.blocking.FilteredNumberCompat.getIdColumnName(r7)     // Catch:{ SecurityException -> 0x00b7, all -> 0x00b5 }
            r7 = 0
            r4[r7] = r0     // Catch:{ SecurityException -> 0x00b7, all -> 0x00b5 }
            android.content.Context r8 = r11.context     // Catch:{ SecurityException -> 0x00b7, all -> 0x00b5 }
            java.lang.String r8 = com.android.dialer.blocking.FilteredNumberCompat.getTypeColumnName(r8)     // Catch:{ SecurityException -> 0x00b7, all -> 0x00b5 }
            r9 = 1
            r4[r9] = r8     // Catch:{ SecurityException -> 0x00b7, all -> 0x00b5 }
            java.lang.String[] r4 = com.android.dialer.blocking.FilteredNumberCompat.filter(r4)     // Catch:{ SecurityException -> 0x00b7, all -> 0x00b5 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ SecurityException -> 0x00b7, all -> 0x00b5 }
            r8.<init>()     // Catch:{ SecurityException -> 0x00b7, all -> 0x00b5 }
            if (r13 == 0) goto L_0x0064
            r13 = r9
            goto L_0x0065
        L_0x0064:
            r13 = r7
        L_0x0065:
            java.lang.String r11 = r11.getIsBlockedNumberSelection(r13)     // Catch:{ SecurityException -> 0x00b7, all -> 0x00b5 }
            r8.append(r11)     // Catch:{ SecurityException -> 0x00b7, all -> 0x00b5 }
            java.lang.String r11 = " = ?"
            r8.append(r11)     // Catch:{ SecurityException -> 0x00b7, all -> 0x00b5 }
            java.lang.String r8 = r8.toString()     // Catch:{ SecurityException -> 0x00b7, all -> 0x00b5 }
            java.lang.String[] r9 = new java.lang.String[r9]     // Catch:{ SecurityException -> 0x00b7, all -> 0x00b5 }
            r9[r7] = r2     // Catch:{ SecurityException -> 0x00b7, all -> 0x00b5 }
            r10 = 0
            r7 = r4
            android.database.Cursor r11 = r5.query(r6, r7, r8, r9, r10)     // Catch:{ SecurityException -> 0x00b7, all -> 0x00b5 }
            if (r11 == 0) goto L_0x00a4
            int r13 = r11.getCount()     // Catch:{ SecurityException -> 0x00b3 }
            if (r13 != 0) goto L_0x0088
            goto L_0x00a4
        L_0x0088:
            r11.moveToFirst()     // Catch:{ SecurityException -> 0x00b3 }
            int r13 = r11.getColumnIndex(r0)     // Catch:{ SecurityException -> 0x00b3 }
            int r13 = r11.getInt(r13)     // Catch:{ SecurityException -> 0x00b3 }
            java.util.Map<java.lang.String, java.lang.Integer> r0 = blockedNumberCache     // Catch:{ SecurityException -> 0x00b3 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r13)     // Catch:{ SecurityException -> 0x00b3 }
            r0.put(r12, r2)     // Catch:{ SecurityException -> 0x00b3 }
            java.lang.Integer r12 = java.lang.Integer.valueOf(r13)     // Catch:{ SecurityException -> 0x00b3 }
            r11.close()
            return r12
        L_0x00a4:
            java.util.Map<java.lang.String, java.lang.Integer> r13 = blockedNumberCache     // Catch:{ SecurityException -> 0x00b3 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r3)     // Catch:{ SecurityException -> 0x00b3 }
            r13.put(r12, r0)     // Catch:{ SecurityException -> 0x00b3 }
            if (r11 == 0) goto L_0x00b2
            r11.close()
        L_0x00b2:
            return r1
        L_0x00b3:
            r12 = move-exception
            goto L_0x00b9
        L_0x00b5:
            r12 = move-exception
            goto L_0x00c6
        L_0x00b7:
            r12 = move-exception
            r11 = r1
        L_0x00b9:
            java.lang.String r13 = "FilteredNumberAsyncQueryHandler.getBlockedIdSynchronous"
            com.android.dialer.common.LogUtil.m7e((java.lang.String) r13, (java.lang.String) r1, (java.lang.Throwable) r12)     // Catch:{ all -> 0x00c4 }
            if (r11 == 0) goto L_0x00c3
            r11.close()
        L_0x00c3:
            return r1
        L_0x00c4:
            r12 = move-exception
            r1 = r11
        L_0x00c6:
            if (r1 == 0) goto L_0x00cb
            r1.close()
        L_0x00cb:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.blocking.FilteredNumberAsyncQueryHandler.getBlockedIdSynchronous(java.lang.String, java.lang.String):java.lang.Integer");
    }

    /* access modifiers changed from: package-private */
    public void hasBlockedNumbers(final OnHasBlockedNumbersListener onHasBlockedNumbersListener) {
        if (!FilteredNumberCompat.canAttemptBlockOperations(this.context)) {
            onHasBlockedNumbersListener.onHasBlockedNumbers(false);
            return;
        }
        C03751 r2 = new Listener(this) {
            /* access modifiers changed from: protected */
            public void onQueryComplete(int i, Object obj, Cursor cursor) {
                try {
                    onHasBlockedNumbersListener.onHasBlockedNumbers(cursor != null && cursor.getCount() > 0);
                } finally {
                    if (cursor != null) {
                        cursor.close();
                    }
                }
            }
        };
        Uri contentUri = FilteredNumberCompat.getContentUri(this.context, (Integer) null);
        FilteredNumberCompat.getIdColumnName(this.context);
        startQuery(0, r2, contentUri, new String[]{"_id"}, FilteredNumberCompat.useNewFiltering(this.context) ? null : "type=1", (String[]) null, (String) null);
    }

    public void isBlockedNumber(final OnCheckBlockedListener onCheckBlockedListener, final String str, String str2) {
        Integer valueOf = Integer.valueOf(BLOCKED_NUMBER_CACHE_NULL_ID);
        if (str == null) {
            onCheckBlockedListener.onCheckComplete(valueOf);
        } else if (!FilteredNumberCompat.canAttemptBlockOperations(this.context)) {
            onCheckBlockedListener.onCheckComplete((Integer) null);
        } else {
            Integer num = blockedNumberCache.get(str);
            if (num != null) {
                if (onCheckBlockedListener != null) {
                    if (num.intValue() == BLOCKED_NUMBER_CACHE_NULL_ID) {
                        num = null;
                    }
                    onCheckBlockedListener.onCheckComplete(num);
                }
            } else if (!R$dimen.isUserUnlocked(this.context)) {
                LogUtil.m9i("FilteredNumberAsyncQueryHandler.isBlockedNumber", "Device locked in FBE mode, cannot access blocked number database", new Object[0]);
                onCheckBlockedListener.onCheckComplete(valueOf);
            } else {
                String formatNumberToE164 = PhoneNumberUtils.formatNumberToE164(str, str2);
                String blockableNumber = FilteredNumbersUtil.getBlockableNumber(this.context, formatNumberToE164, str);
                if (TextUtils.isEmpty(blockableNumber)) {
                    onCheckBlockedListener.onCheckComplete(valueOf);
                    blockedNumberCache.put(str, valueOf);
                    return;
                }
                C03762 r4 = new Listener() {
                    /* access modifiers changed from: protected */
                    public void onQueryComplete(int i, Object obj, Cursor cursor) {
                        if (cursor != null) {
                            try {
                                if (cursor.getCount() != 0) {
                                    cursor.moveToFirst();
                                    if (FilteredNumberCompat.useNewFiltering(FilteredNumberAsyncQueryHandler.this.context) || cursor.getInt(cursor.getColumnIndex("type")) == 1) {
                                        Integer valueOf = Integer.valueOf(cursor.getInt(cursor.getColumnIndex("_id")));
                                        FilteredNumberAsyncQueryHandler.blockedNumberCache.put(str, valueOf);
                                        onCheckBlockedListener.onCheckComplete(valueOf);
                                        cursor.close();
                                        return;
                                    }
                                    FilteredNumberAsyncQueryHandler.blockedNumberCache.put(str, Integer.valueOf(FilteredNumberAsyncQueryHandler.BLOCKED_NUMBER_CACHE_NULL_ID));
                                    onCheckBlockedListener.onCheckComplete((Integer) null);
                                    cursor.close();
                                    return;
                                }
                            } catch (Throwable th) {
                                if (cursor != null) {
                                    cursor.close();
                                }
                                throw th;
                            }
                        }
                        FilteredNumberAsyncQueryHandler.blockedNumberCache.put(str, Integer.valueOf(FilteredNumberAsyncQueryHandler.BLOCKED_NUMBER_CACHE_NULL_ID));
                        onCheckBlockedListener.onCheckComplete((Integer) null);
                        if (cursor != null) {
                            cursor.close();
                        }
                    }
                };
                Uri contentUri = FilteredNumberCompat.getContentUri(this.context, (Integer) null);
                FilteredNumberCompat.getIdColumnName(this.context);
                startQuery(0, r4, contentUri, FilteredNumberCompat.filter(new String[]{"_id", FilteredNumberCompat.getTypeColumnName(this.context)}), GeneratedOutlineSupport.outline12(new StringBuilder(), getIsBlockedNumberSelection(formatNumberToE164 != null), " = ?"), new String[]{blockableNumber}, (String) null);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDeleteComplete(int i, Object obj, int i2) {
        if (obj != null) {
            ((Listener) obj).onDeleteComplete(i, obj, i2);
        }
    }

    /* access modifiers changed from: protected */
    public void onInsertComplete(int i, Object obj, Uri uri) {
        if (obj != null) {
            ((Listener) obj).onInsertComplete(i, obj, uri);
        }
    }

    /* access modifiers changed from: protected */
    public void onQueryComplete(int i, Object obj, Cursor cursor) {
        if (obj != null) {
            try {
                ((Listener) obj).onQueryComplete(i, obj, cursor);
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }
        if (cursor != null) {
            cursor.close();
        }
    }

    /* access modifiers changed from: protected */
    public void onUpdateComplete(int i, Object obj, int i2) {
        if (obj != null) {
            Listener listener = (Listener) obj;
        }
    }

    public void unblock(OnUnblockNumberListener onUnblockNumberListener, Integer num) {
        if (num != null) {
            unblock(onUnblockNumberListener, FilteredNumberCompat.getContentUri(this.context, num));
            return;
        }
        throw new IllegalArgumentException("Null id passed into unblock");
    }

    public void blockNumber(OnBlockNumberListener onBlockNumberListener, String str, String str2, String str3) {
        Context context2 = this.context;
        ContentValues contentValues = new ContentValues();
        contentValues.put(FilteredNumberCompat.getOriginalNumberColumnName(context2), (String) Objects.requireNonNull(str2));
        if (!FilteredNumberCompat.useNewFiltering(context2)) {
            if (str == null) {
                str = PhoneNumberUtils.formatNumberToE164(str2, str3);
            }
            contentValues.put(FilteredNumberCompat.getE164NumberColumnName(context2), str);
            String str4 = null;
            contentValues.put(FilteredNumberCompat.useNewFiltering(context2) ? null : "country_iso", str3);
            contentValues.put(FilteredNumberCompat.getTypeColumnName(context2), 1);
            if (!FilteredNumberCompat.useNewFiltering(context2)) {
                str4 = "source";
            }
            contentValues.put(str4, 1);
        }
        blockNumber(onBlockNumberListener, contentValues);
    }

    public void unblock(final OnUnblockNumberListener onUnblockNumberListener, final Uri uri) {
        blockedNumberCache.clear();
        if (FilteredNumberCompat.canAttemptBlockOperations(this.context)) {
            startQuery(0, new Listener() {
                public void onQueryComplete(int i, Object obj, Cursor cursor) {
                    int i2;
                    if (cursor == null) {
                        i2 = 0;
                    } else {
                        try {
                            i2 = cursor.getCount();
                        } catch (Throwable th) {
                            if (cursor != null) {
                                cursor.close();
                            }
                            throw th;
                        }
                    }
                    if (i2 == 1) {
                        cursor.moveToFirst();
                        final ContentValues contentValues = new ContentValues();
                        DatabaseUtils.cursorRowToContentValues(cursor, contentValues);
                        FilteredNumberCompat.getIdColumnName(FilteredNumberAsyncQueryHandler.this.context);
                        contentValues.remove("_id");
                        FilteredNumberAsyncQueryHandler.this.startDelete(0, new Listener() {
                            public void onDeleteComplete(int i, Object obj, int i2) {
                                OnUnblockNumberListener onUnblockNumberListener = onUnblockNumberListener;
                                if (onUnblockNumberListener != null) {
                                    onUnblockNumberListener.onUnblockComplete(i2, contentValues);
                                }
                            }
                        }, uri, (String) null, (String[]) null);
                        cursor.close();
                        return;
                    }
                    throw new SQLiteDatabaseCorruptException("Returned " + i2 + " rows for uri " + uri + "where 1 expected.");
                }
            }, uri, (String[]) null, (String) null, (String[]) null, (String) null);
        } else if (onUnblockNumberListener != null) {
            onUnblockNumberListener.onUnblockComplete(0, (ContentValues) null);
        }
    }

    public void blockNumber(final OnBlockNumberListener onBlockNumberListener, ContentValues contentValues) {
        blockedNumberCache.clear();
        if (FilteredNumberCompat.canAttemptBlockOperations(this.context)) {
            startInsert(0, new Listener(this) {
                public void onInsertComplete(int i, Object obj, Uri uri) {
                    OnBlockNumberListener onBlockNumberListener = onBlockNumberListener;
                    if (onBlockNumberListener != null) {
                        onBlockNumberListener.onBlockComplete(uri);
                    }
                }
            }, FilteredNumberCompat.getContentUri(this.context, (Integer) null), contentValues);
        } else if (onBlockNumberListener != null) {
            onBlockNumberListener.onBlockComplete((Uri) null);
        }
    }
}
