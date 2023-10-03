package com.android.contacts.list;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Handler;
import android.provider.ContactsContract;
import com.android.contacts.R;
import com.android.contacts.compat.DirectoryCompat;

public class DirectoryListLoader extends AsyncTaskLoader<Cursor> {
    private static final String[] RESULT_PROJECTION = {"_id", "directoryType", "displayName", "photoSupport"};
    private MatrixCursor mDefaultDirectoryList;
    private int mDirectorySearchMode;
    private boolean mLocalInvisibleDirectoryEnabled;
    private final ContentObserver mObserver = new ContentObserver(new Handler()) {
        public void onChange(boolean z) {
            DirectoryListLoader.this.forceLoad();
        }
    };

    private static final class DirectoryQuery {
        public static final String[] PROJECTION = {"_id", "packageName", "typeResourceId", "displayName", "photoSupport"};

        public static Uri getDirectoryUri(int i) {
            if (i == 3 || i == 2) {
                return ContactsContract.Directory.CONTENT_URI;
            }
            return DirectoryCompat.getContentUri();
        }
    }

    public DirectoryListLoader(Context context) {
        super(context);
    }

    public void setDirectorySearchMode(int i) {
        this.mDirectorySearchMode = i;
    }

    public void setLocalInvisibleDirectoryEnabled(boolean z) {
        this.mLocalInvisibleDirectoryEnabled = z;
    }

    /* access modifiers changed from: protected */
    public void onStartLoading() {
        getContext().getContentResolver().registerContentObserver(DirectoryQuery.getDirectoryUri(this.mDirectorySearchMode), false, this.mObserver);
        forceLoad();
    }

    /* access modifiers changed from: protected */
    public void onStopLoading() {
        getContext().getContentResolver().unregisterContentObserver(this.mObserver);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        android.util.Log.e("ContactEntryListAdapter", "Cannot obtain directory type from package: " + r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00cb, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00df, code lost:
        r2.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x0091 */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00cb A[ExcHandler: all (th java.lang.Throwable), Splitter:B:20:0x0062] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00d9  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00df  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.database.Cursor loadInBackground() {
        /*
            r15 = this;
            java.lang.String r0 = "ContactEntryListAdapter"
            int r1 = r15.mDirectorySearchMode
            if (r1 != 0) goto L_0x000b
            android.database.Cursor r0 = r15.getDefaultDirectories()
            return r0
        L_0x000b:
            android.database.MatrixCursor r1 = new android.database.MatrixCursor
            java.lang.String[] r2 = RESULT_PROJECTION
            r1.<init>(r2)
            android.content.Context r2 = r15.getContext()
            android.content.pm.PackageManager r3 = r2.getPackageManager()
            int r4 = r15.mDirectorySearchMode
            r5 = 0
            r6 = 3
            r7 = 2
            r8 = 1
            if (r4 == r8) goto L_0x0046
            if (r4 == r7) goto L_0x0042
            if (r4 != r6) goto L_0x0029
            java.lang.String r4 = "shortcutSupport IN (2, 1)"
            goto L_0x0044
        L_0x0029:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Unsupported directory search mode: "
            r1.append(r2)
            int r2 = r15.mDirectorySearchMode
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0042:
            java.lang.String r4 = "shortcutSupport=2"
        L_0x0044:
            r12 = r4
            goto L_0x0047
        L_0x0046:
            r12 = r5
        L_0x0047:
            android.content.ContentResolver r9 = r2.getContentResolver()     // Catch:{ RuntimeException -> 0x00d2 }
            int r2 = r15.mDirectorySearchMode     // Catch:{ RuntimeException -> 0x00d2 }
            android.net.Uri r10 = com.android.contacts.list.DirectoryListLoader.DirectoryQuery.getDirectoryUri(r2)     // Catch:{ RuntimeException -> 0x00d2 }
            java.lang.String[] r11 = com.android.contacts.list.DirectoryListLoader.DirectoryQuery.PROJECTION     // Catch:{ RuntimeException -> 0x00d2 }
            r13 = 0
            java.lang.String r14 = "_id"
            android.database.Cursor r2 = r9.query(r10, r11, r12, r13, r14)     // Catch:{ RuntimeException -> 0x00d2 }
            if (r2 != 0) goto L_0x0062
            if (r2 == 0) goto L_0x0061
            r2.close()
        L_0x0061:
            return r1
        L_0x0062:
            boolean r4 = r2.moveToNext()     // Catch:{ RuntimeException -> 0x00cd, all -> 0x00cb }
            if (r4 == 0) goto L_0x00c5
            r4 = 0
            long r9 = r2.getLong(r4)     // Catch:{ RuntimeException -> 0x00cd, all -> 0x00cb }
            boolean r11 = r15.mLocalInvisibleDirectoryEnabled     // Catch:{ RuntimeException -> 0x00cd, all -> 0x00cb }
            if (r11 != 0) goto L_0x0078
            boolean r11 = com.android.contacts.compat.DirectoryCompat.isInvisibleDirectory(r9)     // Catch:{ RuntimeException -> 0x00cd, all -> 0x00cb }
            if (r11 == 0) goto L_0x0078
            goto L_0x0062
        L_0x0078:
            java.lang.String r11 = r2.getString(r8)     // Catch:{ RuntimeException -> 0x00cd, all -> 0x00cb }
            int r12 = r2.getInt(r7)     // Catch:{ RuntimeException -> 0x00cd, all -> 0x00cb }
            boolean r13 = android.text.TextUtils.isEmpty(r11)     // Catch:{ RuntimeException -> 0x00cd, all -> 0x00cb }
            if (r13 != 0) goto L_0x00a5
            if (r12 == 0) goto L_0x00a5
            android.content.res.Resources r13 = r3.getResourcesForApplication(r11)     // Catch:{ Exception -> 0x0091, all -> 0x00cb }
            java.lang.String r11 = r13.getString(r12)     // Catch:{ Exception -> 0x0091, all -> 0x00cb }
            goto L_0x00a6
        L_0x0091:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ RuntimeException -> 0x00cd, all -> 0x00cb }
            r12.<init>()     // Catch:{ RuntimeException -> 0x00cd, all -> 0x00cb }
            java.lang.String r13 = "Cannot obtain directory type from package: "
            r12.append(r13)     // Catch:{ RuntimeException -> 0x00cd, all -> 0x00cb }
            r12.append(r11)     // Catch:{ RuntimeException -> 0x00cd, all -> 0x00cb }
            java.lang.String r11 = r12.toString()     // Catch:{ RuntimeException -> 0x00cd, all -> 0x00cb }
            android.util.Log.e(r0, r11)     // Catch:{ RuntimeException -> 0x00cd, all -> 0x00cb }
        L_0x00a5:
            r11 = r5
        L_0x00a6:
            java.lang.String r12 = r2.getString(r6)     // Catch:{ RuntimeException -> 0x00cd, all -> 0x00cb }
            r13 = 4
            int r14 = r2.getInt(r13)     // Catch:{ RuntimeException -> 0x00cd, all -> 0x00cb }
            java.lang.Object[] r13 = new java.lang.Object[r13]     // Catch:{ RuntimeException -> 0x00cd, all -> 0x00cb }
            java.lang.Long r9 = java.lang.Long.valueOf(r9)     // Catch:{ RuntimeException -> 0x00cd, all -> 0x00cb }
            r13[r4] = r9     // Catch:{ RuntimeException -> 0x00cd, all -> 0x00cb }
            r13[r8] = r11     // Catch:{ RuntimeException -> 0x00cd, all -> 0x00cb }
            r13[r7] = r12     // Catch:{ RuntimeException -> 0x00cd, all -> 0x00cb }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r14)     // Catch:{ RuntimeException -> 0x00cd, all -> 0x00cb }
            r13[r6] = r4     // Catch:{ RuntimeException -> 0x00cd, all -> 0x00cb }
            r1.addRow(r13)     // Catch:{ RuntimeException -> 0x00cd, all -> 0x00cb }
            goto L_0x0062
        L_0x00c5:
            if (r2 == 0) goto L_0x00dc
            r2.close()
            goto L_0x00dc
        L_0x00cb:
            r0 = move-exception
            goto L_0x00dd
        L_0x00cd:
            r5 = r2
            goto L_0x00d2
        L_0x00cf:
            r0 = move-exception
            r2 = r5
            goto L_0x00dd
        L_0x00d2:
            java.lang.String r2 = "Runtime Exception when querying directory"
            android.util.Log.w(r0, r2)     // Catch:{ all -> 0x00cf }
            if (r5 == 0) goto L_0x00dc
            r5.close()
        L_0x00dc:
            return r1
        L_0x00dd:
            if (r2 == 0) goto L_0x00e2
            r2.close()
        L_0x00e2:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.list.DirectoryListLoader.loadInBackground():android.database.Cursor");
    }

    private Cursor getDefaultDirectories() {
        if (this.mDefaultDirectoryList == null) {
            this.mDefaultDirectoryList = new MatrixCursor(RESULT_PROJECTION);
            this.mDefaultDirectoryList.addRow(new Object[]{0L, getContext().getString(R.string.contactsList), null, null});
            this.mDefaultDirectoryList.addRow(new Object[]{1L, getContext().getString(R.string.local_invisible_directory), null, null});
        }
        return this.mDefaultDirectoryList;
    }

    /* access modifiers changed from: protected */
    public void onReset() {
        stopLoading();
    }
}
