package com.android.dialer.blocking;

import android.content.Context;
import android.os.AsyncTask;
import com.android.dialer.common.LogUtil;
import java.util.Objects;

@Deprecated
public class BlockedNumbersMigrator {
    /* access modifiers changed from: private */
    public final Context context;

    public interface Listener {
        void onComplete();
    }

    private class MigratorTask extends AsyncTask<Void, Void, Boolean> {
        private final Listener listener;

        public MigratorTask(Listener listener2) {
            this.listener = listener2;
        }

        /* access modifiers changed from: protected */
        public Object doInBackground(Object[] objArr) {
            Void[] voidArr = (Void[]) objArr;
            LogUtil.m9i("BlockedNumbersMigrator.doInBackground", "migrate - start background migration", new Object[0]);
            return Boolean.valueOf(BlockedNumbersMigrator.access$100(BlockedNumbersMigrator.this.context.getContentResolver()));
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Object obj) {
            LogUtil.m9i("BlockedNumbersMigrator.onPostExecute", "migrate - marking migration complete", new Object[0]);
            FilteredNumberCompat.setHasMigratedToNewBlocking(BlockedNumbersMigrator.this.context, ((Boolean) obj).booleanValue());
            LogUtil.m9i("BlockedNumbersMigrator.onPostExecute", "migrate - calling listener", new Object[0]);
            this.listener.onComplete();
        }
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

    public BlockedNumbersMigrator(Context context2) {
        this.context = (Context) Objects.requireNonNull(context2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0073, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        $closeResource(r14, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0077, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00bc, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00bd, code lost:
        if (r0 != null) goto L_0x00bf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00bf, code lost:
        $closeResource(r14, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00c2, code lost:
        throw r1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0088  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ boolean access$100(android.content.ContentResolver r14) {
        /*
            android.net.Uri r1 = com.android.dialer.database.FilteredNumberContract.FilteredNumber.CONTENT_URI
            java.lang.String r6 = "number"
            java.lang.String[] r2 = new java.lang.String[]{r6}
            r3 = 0
            r4 = 0
            r5 = 0
            r0 = r14
            android.database.Cursor r0 = r0.query(r1, r2, r3, r4, r5)
            r1 = 0
            java.lang.String r2 = "BlockedNumbersMigrator.migrateToNewBlockingInBackground"
            r3 = 0
            r4 = 1
            if (r0 != 0) goto L_0x0025
            java.lang.String r14 = "migrate - cursor was null"
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ all -> 0x00ba }
            com.android.dialer.common.LogUtil.m9i(r2, r14, r4)     // Catch:{ all -> 0x00ba }
            if (r0 == 0) goto L_0x00b9
            $closeResource(r1, r0)
            goto L_0x00b9
        L_0x0025:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ba }
            r5.<init>()     // Catch:{ all -> 0x00ba }
            java.lang.String r7 = "migrate - attempting to migrate "
            r5.append(r7)     // Catch:{ all -> 0x00ba }
            int r7 = r0.getCount()     // Catch:{ all -> 0x00ba }
            r5.append(r7)     // Catch:{ all -> 0x00ba }
            java.lang.String r7 = "numbers"
            r5.append(r7)     // Catch:{ all -> 0x00ba }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x00ba }
            java.lang.Object[] r7 = new java.lang.Object[r3]     // Catch:{ all -> 0x00ba }
            com.android.dialer.common.LogUtil.m9i(r2, r5, r7)     // Catch:{ all -> 0x00ba }
            r5 = r3
        L_0x0045:
            boolean r7 = r0.moveToNext()     // Catch:{ all -> 0x00ba }
            if (r7 == 0) goto L_0x009a
            int r7 = r0.getColumnIndex(r6)     // Catch:{ all -> 0x00ba }
            java.lang.String r7 = r0.getString(r7)     // Catch:{ all -> 0x00ba }
            android.net.Uri r9 = android.provider.BlockedNumberContract.BlockedNumbers.CONTENT_URI     // Catch:{ all -> 0x00ba }
            java.lang.String r8 = "_id"
            java.lang.String[] r10 = new java.lang.String[]{r8}     // Catch:{ all -> 0x00ba }
            java.lang.String[] r12 = new java.lang.String[r4]     // Catch:{ all -> 0x00ba }
            r12[r3] = r7     // Catch:{ all -> 0x00ba }
            java.lang.String r11 = "original_number = ?"
            r13 = 0
            r8 = r14
            android.database.Cursor r8 = r8.query(r9, r10, r11, r12, r13)     // Catch:{ all -> 0x00ba }
            if (r8 == 0) goto L_0x0078
            int r9 = r8.getCount()     // Catch:{ all -> 0x0071 }
            if (r9 == 0) goto L_0x0078
            r9 = r4
            goto L_0x0079
        L_0x0071:
            r14 = move-exception
            throw r14     // Catch:{ all -> 0x0073 }
        L_0x0073:
            r1 = move-exception
            $closeResource(r14, r8)     // Catch:{ all -> 0x00ba }
            throw r1     // Catch:{ all -> 0x00ba }
        L_0x0078:
            r9 = r3
        L_0x0079:
            if (r8 == 0) goto L_0x007e
            $closeResource(r1, r8)     // Catch:{ all -> 0x00ba }
        L_0x007e:
            if (r9 == 0) goto L_0x0088
            java.lang.String r7 = "migrate - number was already blocked in new blocking"
            java.lang.Object[] r8 = new java.lang.Object[r3]     // Catch:{ all -> 0x00ba }
            com.android.dialer.common.LogUtil.m9i(r2, r7, r8)     // Catch:{ all -> 0x00ba }
            goto L_0x0045
        L_0x0088:
            android.content.ContentValues r8 = new android.content.ContentValues     // Catch:{ all -> 0x00ba }
            r8.<init>()     // Catch:{ all -> 0x00ba }
            java.lang.String r9 = "original_number"
            r8.put(r9, r7)     // Catch:{ all -> 0x00ba }
            android.net.Uri r7 = android.provider.BlockedNumberContract.BlockedNumbers.CONTENT_URI     // Catch:{ all -> 0x00ba }
            r14.insert(r7, r8)     // Catch:{ all -> 0x00ba }
            int r5 = r5 + 1
            goto L_0x0045
        L_0x009a:
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ba }
            r14.<init>()     // Catch:{ all -> 0x00ba }
            java.lang.String r6 = "migrate - migration complete. "
            r14.append(r6)     // Catch:{ all -> 0x00ba }
            r14.append(r5)     // Catch:{ all -> 0x00ba }
            java.lang.String r5 = " numbers migrated."
            r14.append(r5)     // Catch:{ all -> 0x00ba }
            java.lang.String r14 = r14.toString()     // Catch:{ all -> 0x00ba }
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x00ba }
            com.android.dialer.common.LogUtil.m9i(r2, r14, r3)     // Catch:{ all -> 0x00ba }
            $closeResource(r1, r0)
            r3 = r4
        L_0x00b9:
            return r3
        L_0x00ba:
            r14 = move-exception
            throw r14     // Catch:{ all -> 0x00bc }
        L_0x00bc:
            r1 = move-exception
            if (r0 == 0) goto L_0x00c2
            $closeResource(r14, r0)
        L_0x00c2:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.blocking.BlockedNumbersMigrator.access$100(android.content.ContentResolver):boolean");
    }

    public boolean migrate(Listener listener) {
        LogUtil.m9i("BlockedNumbersMigrator.migrate", "migrate - start", new Object[0]);
        Objects.requireNonNull(listener);
        new MigratorTask(listener).execute(new Void[0]);
        return true;
    }
}
