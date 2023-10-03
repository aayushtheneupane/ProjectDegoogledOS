package com.android.settings.slices;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.slices.SliceData;

public class SlicesDatabaseAccessor {
    public static final String[] SELECT_COLUMNS_ALL = {"key", "title", "summary", "screentitle", "keywords", "icon", "fragment", "controller", "platform_slice", "slice_type", "unavailable_slice_subtitle"};
    private final int TRUE = 1;
    private final Context mContext;
    private final SlicesDatabaseHelper mHelper;

    public SlicesDatabaseAccessor(Context context) {
        this.mContext = context;
        this.mHelper = SlicesDatabaseHelper.getInstance(this.mContext);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0023, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0024, code lost:
        if (r1 != null) goto L_0x0026;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0026, code lost:
        $closeResource(r3, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0029, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.android.settings.slices.SliceData getSliceDataFromUri(android.net.Uri r4) {
        /*
            r3 = this;
            android.util.Pair r0 = com.android.settings.slices.SliceBuilderUtils.getPathData(r4)
            if (r0 == 0) goto L_0x002a
            java.lang.Object r1 = r0.second
            java.lang.String r1 = (java.lang.String) r1
            android.database.Cursor r1 = r3.getIndexedSliceData(r1)
            r2 = 0
            java.lang.Object r0 = r0.first     // Catch:{ all -> 0x0021 }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0021 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0021 }
            com.android.settings.slices.SliceData r3 = r3.buildSliceData(r1, r4, r0)     // Catch:{ all -> 0x0021 }
            if (r1 == 0) goto L_0x0020
            $closeResource(r2, r1)
        L_0x0020:
            return r3
        L_0x0021:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0023 }
        L_0x0023:
            r4 = move-exception
            if (r1 == 0) goto L_0x0029
            $closeResource(r3, r1)
        L_0x0029:
            throw r4
        L_0x002a:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Invalid Slices uri: "
            r0.append(r1)
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.slices.SlicesDatabaseAccessor.getSliceDataFromUri(android.net.Uri):com.android.settings.slices.SliceData");
    }

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

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0013, code lost:
        if (r3 != null) goto L_0x0015;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0015, code lost:
        $closeResource(r2, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0018, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0012, code lost:
        r0 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.android.settings.slices.SliceData getSliceDataFromKey(java.lang.String r3) {
        /*
            r2 = this;
            android.database.Cursor r3 = r2.getIndexedSliceData(r3)
            r0 = 0
            r1 = 0
            com.android.settings.slices.SliceData r2 = r2.buildSliceData(r3, r1, r0)     // Catch:{ all -> 0x0010 }
            if (r3 == 0) goto L_0x000f
            $closeResource(r1, r3)
        L_0x000f:
            return r2
        L_0x0010:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0012 }
        L_0x0012:
            r0 = move-exception
            if (r3 == 0) goto L_0x0018
            $closeResource(r2, r3)
        L_0x0018:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.slices.SlicesDatabaseAccessor.getSliceDataFromKey(java.lang.String):com.android.settings.slices.SliceData");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0049, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004a, code lost:
        if (r9 != null) goto L_0x004c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004c, code lost:
        $closeResource(r8, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004f, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<java.lang.String> getSliceKeys(boolean r9) {
        /*
            r8 = this;
            r8.verifyIndexing()
            if (r9 == 0) goto L_0x0008
            java.lang.String r9 = "platform_slice = 1"
            goto L_0x000a
        L_0x0008:
            java.lang.String r9 = "platform_slice = 0"
        L_0x000a:
            r3 = r9
            com.android.settings.slices.SlicesDatabaseHelper r8 = r8.mHelper
            android.database.sqlite.SQLiteDatabase r0 = r8.getReadableDatabase()
            java.lang.String r8 = "key"
            java.lang.String[] r2 = new java.lang.String[]{r8}
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            java.lang.String r1 = "slices_index"
            android.database.Cursor r9 = r0.query(r1, r2, r3, r4, r5, r6, r7)
            r0 = 0
            boolean r1 = r9.moveToFirst()     // Catch:{ all -> 0x0047 }
            if (r1 != 0) goto L_0x0033
            if (r9 == 0) goto L_0x0032
            $closeResource(r0, r9)
        L_0x0032:
            return r8
        L_0x0033:
            r1 = 0
            java.lang.String r1 = r9.getString(r1)     // Catch:{ all -> 0x0047 }
            r8.add(r1)     // Catch:{ all -> 0x0047 }
            boolean r1 = r9.moveToNext()     // Catch:{ all -> 0x0047 }
            if (r1 != 0) goto L_0x0033
            if (r9 == 0) goto L_0x0046
            $closeResource(r0, r9)
        L_0x0046:
            return r8
        L_0x0047:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x0049 }
        L_0x0049:
            r0 = move-exception
            if (r9 == 0) goto L_0x004f
            $closeResource(r8, r9)
        L_0x004f:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.slices.SlicesDatabaseAccessor.getSliceKeys(boolean):java.util.List");
    }

    private Cursor getIndexedSliceData(String str) {
        verifyIndexing();
        String buildKeyMatchWhereClause = buildKeyMatchWhereClause();
        Cursor query = this.mHelper.getReadableDatabase().query("slices_index", SELECT_COLUMNS_ALL, buildKeyMatchWhereClause, new String[]{str}, (String) null, (String) null, (String) null);
        int count = query.getCount();
        if (count == 0) {
            throw new IllegalStateException("Invalid Slices key from path: " + str);
        } else if (count <= 1) {
            query.moveToFirst();
            return query;
        } else {
            throw new IllegalStateException("Should not match more than 1 slice with path: " + str);
        }
    }

    private String buildKeyMatchWhereClause() {
        return "key" + " = ?";
    }

    private SliceData buildSliceData(Cursor cursor, Uri uri, boolean z) {
        String string = cursor.getString(cursor.getColumnIndex("key"));
        String string2 = cursor.getString(cursor.getColumnIndex("title"));
        String string3 = cursor.getString(cursor.getColumnIndex("summary"));
        String string4 = cursor.getString(cursor.getColumnIndex("screentitle"));
        String string5 = cursor.getString(cursor.getColumnIndex("keywords"));
        int i = cursor.getInt(cursor.getColumnIndex("icon"));
        String string6 = cursor.getString(cursor.getColumnIndex("fragment"));
        String string7 = cursor.getString(cursor.getColumnIndex("controller"));
        boolean z2 = true;
        if (cursor.getInt(cursor.getColumnIndex("platform_slice")) != 1) {
            z2 = false;
        }
        int i2 = cursor.getInt(cursor.getColumnIndex("slice_type"));
        String string8 = cursor.getString(cursor.getColumnIndex("unavailable_slice_subtitle"));
        if (z) {
            i2 = 0;
        }
        SliceData.Builder builder = new SliceData.Builder();
        builder.setKey(string);
        builder.setTitle(string2);
        builder.setSummary(string3);
        builder.setScreenTitle(string4);
        builder.setKeywords(string5);
        builder.setIcon(i);
        builder.setFragmentName(string6);
        builder.setPreferenceControllerClassName(string7);
        builder.setUri(uri);
        builder.setPlatformDefined(z2);
        builder.setSliceType(i2);
        builder.setUnavailableSliceSubtitle(string8);
        return builder.build();
    }

    private void verifyIndexing() {
        long clearCallingIdentity = Binder.clearCallingIdentity();
        try {
            FeatureFactory.getFactory(this.mContext).getSlicesFeatureProvider().indexSliceData(this.mContext);
        } finally {
            Binder.restoreCallingIdentity(clearCallingIdentity);
        }
    }
}
