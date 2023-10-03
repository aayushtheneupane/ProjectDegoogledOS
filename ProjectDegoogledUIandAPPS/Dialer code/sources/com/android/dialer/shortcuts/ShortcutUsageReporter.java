package com.android.dialer.shortcuts;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.text.TextUtils;
import com.android.dialer.common.Assert;
import com.android.dialer.common.concurrent.AsyncTaskExecutor;
import com.android.dialer.common.concurrent.AsyncTaskExecutors;

@TargetApi(25)
public class ShortcutUsageReporter {
    private static final AsyncTaskExecutor EXECUTOR = AsyncTaskExecutors.createThreadPoolExecutor();

    private static final class Task extends AsyncTask<String, Void, Void> {
        private final Context context;

        public Task(Context context2) {
            this.context = context2;
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0054, code lost:
            r10 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
            r1.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0059, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x005a, code lost:
            r9.addSuppressed(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x005d, code lost:
            throw r10;
         */
        /* JADX WARNING: Removed duplicated region for block: B:25:0x006a  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object doInBackground(java.lang.Object[] r10) {
            /*
                r9 = this;
                java.lang.String[] r10 = (java.lang.String[]) r10
                com.android.dialer.common.Assert.isWorkerThread()
                r0 = 0
                r10 = r10[r0]
                com.android.dialer.common.Assert.isWorkerThread()
                android.content.Context r1 = r9.context
                java.lang.String r2 = "android.permission.READ_CONTACTS"
                int r1 = android.support.p000v4.content.ContextCompat.checkSelfPermission(r1, r2)
                r2 = 0
                if (r1 == 0) goto L_0x0020
                java.lang.Object[] r10 = new java.lang.Object[r0]
                java.lang.String r1 = "ShortcutUsageReporter.queryForLookupKey"
                java.lang.String r3 = "No contact permissions"
                com.android.dialer.common.LogUtil.m9i(r1, r3, r10)
                goto L_0x0063
            L_0x0020:
                android.net.Uri r1 = android.provider.ContactsContract.PhoneLookup.CONTENT_FILTER_URI
                java.lang.String r10 = android.net.Uri.encode(r10)
                android.net.Uri r4 = android.net.Uri.withAppendedPath(r1, r10)
                android.content.Context r10 = r9.context
                android.content.ContentResolver r3 = r10.getContentResolver()
                java.lang.String r10 = "lookup"
                java.lang.String[] r5 = new java.lang.String[]{r10}
                r6 = 0
                r7 = 0
                r8 = 0
                android.database.Cursor r1 = r3.query(r4, r5, r6, r7, r8)
                if (r1 == 0) goto L_0x005e
                boolean r3 = r1.moveToNext()     // Catch:{ all -> 0x0052 }
                if (r3 != 0) goto L_0x0046
                goto L_0x005e
            L_0x0046:
                int r10 = r1.getColumnIndex(r10)     // Catch:{ all -> 0x0052 }
                java.lang.String r10 = r1.getString(r10)     // Catch:{ all -> 0x0052 }
                r1.close()
                goto L_0x0064
            L_0x0052:
                r9 = move-exception
                throw r9     // Catch:{ all -> 0x0054 }
            L_0x0054:
                r10 = move-exception
                r1.close()     // Catch:{ all -> 0x0059 }
                goto L_0x005d
            L_0x0059:
                r0 = move-exception
                r9.addSuppressed(r0)
            L_0x005d:
                throw r10
            L_0x005e:
                if (r1 == 0) goto L_0x0063
                r1.close()
            L_0x0063:
                r10 = r2
            L_0x0064:
                boolean r1 = android.text.TextUtils.isEmpty(r10)
                if (r1 != 0) goto L_0x0083
                r1 = 1
                java.lang.Object[] r1 = new java.lang.Object[r1]
                r1[r0] = r10
                java.lang.String r0 = "ShortcutUsageReporter.backgroundLogUsage"
                java.lang.String r3 = "%s"
                com.android.dialer.common.LogUtil.m9i(r0, r3, r1)
                android.content.Context r9 = r9.context
                java.lang.String r0 = "shortcut"
                java.lang.Object r9 = r9.getSystemService(r0)
                android.content.pm.ShortcutManager r9 = (android.content.pm.ShortcutManager) r9
                r9.reportShortcutUsed(r10)
            L_0x0083:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.shortcuts.ShortcutUsageReporter.Task.doInBackground(java.lang.Object[]):java.lang.Object");
        }
    }

    public static void onOutgoingCallAdded(Context context, String str) {
        Assert.isMainThread();
        Assert.isNotNull(context);
        int i = Build.VERSION.SDK_INT;
        if (!TextUtils.isEmpty(str)) {
            EXECUTOR.submit("ShortcutUsageReporter.Task", new Task(context), str);
        }
    }
}
