package com.android.incallui.legacyblocking;

import android.content.Context;
import android.os.AsyncTask;
import java.util.Objects;

public class DeleteBlockedCallTask extends AsyncTask<Void, Void, Long> {
    private final Context context;
    private final Listener listener;
    private final String number;
    private final long timeAddedMillis;

    private static class CallLogDeleteBlockedCallQuery {
        static final String[] PROJECTION = {"_id", "date"};
    }

    public interface Listener {
    }

    public DeleteBlockedCallTask(Context context2, Listener listener2, String str, long j) {
        this.context = (Context) Objects.requireNonNull(context2);
        this.listener = (Listener) Objects.requireNonNull(listener2);
        this.number = str;
        this.timeAddedMillis = j;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0088, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x008d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x008e, code lost:
        r8.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0091, code lost:
        throw r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0092, code lost:
        if (r2 != null) goto L_0x0094;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object doInBackground(java.lang.Object[] r9) {
        /*
            r8 = this;
            java.lang.Void[] r9 = (java.lang.Void[]) r9
            android.content.Context r9 = r8.context
            java.lang.String r0 = "android.permission.READ_CALL_LOG"
            int r9 = android.support.p000v4.content.ContextCompat.checkSelfPermission(r9, r0)
            r0 = -1
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r1 = 0
            if (r9 != 0) goto L_0x0098
            android.content.Context r9 = r8.context
            java.lang.String r2 = "android.permission.WRITE_CALL_LOG"
            int r9 = android.support.p000v4.content.ContextCompat.checkSelfPermission(r9, r2)
            if (r9 == 0) goto L_0x001f
            goto L_0x0098
        L_0x001f:
            android.content.Context r9 = r8.context
            android.content.ContentResolver r2 = r9.getContentResolver()
            android.content.Context r9 = r8.context
            android.net.Uri r3 = com.android.dialer.telecom.TelecomUtil.getCallLogUri(r9)
            java.lang.String[] r4 = com.android.incallui.legacyblocking.DeleteBlockedCallTask.CallLogDeleteBlockedCallQuery.PROJECTION
            r9 = 1
            java.lang.String[] r6 = new java.lang.String[r9]
            java.lang.String r5 = r8.number
            r6[r1] = r5
            java.lang.String r5 = "number= ?"
            java.lang.String r7 = "date DESC LIMIT 1"
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7)
            if (r2 == 0) goto L_0x0092
            boolean r3 = r2.moveToFirst()     // Catch:{ all -> 0x0086 }
            if (r3 == 0) goto L_0x0092
            long r3 = r2.getLong(r9)     // Catch:{ all -> 0x0086 }
            long r5 = r8.timeAddedMillis     // Catch:{ all -> 0x0086 }
            int r9 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r9 <= 0) goto L_0x0092
            long r5 = r8.timeAddedMillis     // Catch:{ all -> 0x0086 }
            long r5 = r5 - r3
            r3 = 3000(0xbb8, double:1.482E-320)
            int r9 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r9 >= 0) goto L_0x0092
            long r0 = r2.getLong(r1)     // Catch:{ all -> 0x0086 }
            android.content.Context r9 = r8.context     // Catch:{ all -> 0x0086 }
            android.content.ContentResolver r9 = r9.getContentResolver()     // Catch:{ all -> 0x0086 }
            android.content.Context r8 = r8.context     // Catch:{ all -> 0x0086 }
            android.net.Uri r8 = com.android.dialer.telecom.TelecomUtil.getCallLogUri(r8)     // Catch:{ all -> 0x0086 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0086 }
            r3.<init>()     // Catch:{ all -> 0x0086 }
            java.lang.String r4 = "_id IN ("
            r3.append(r4)     // Catch:{ all -> 0x0086 }
            r3.append(r0)     // Catch:{ all -> 0x0086 }
            java.lang.String r4 = ")"
            r3.append(r4)     // Catch:{ all -> 0x0086 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0086 }
            r4 = 0
            r9.delete(r8, r3, r4)     // Catch:{ all -> 0x0086 }
            java.lang.Long r0 = java.lang.Long.valueOf(r0)     // Catch:{ all -> 0x0086 }
            goto L_0x0094
        L_0x0086:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x0088 }
        L_0x0088:
            r9 = move-exception
            r2.close()     // Catch:{ all -> 0x008d }
            goto L_0x0091
        L_0x008d:
            r0 = move-exception
            r8.addSuppressed(r0)
        L_0x0091:
            throw r9
        L_0x0092:
            if (r2 == 0) goto L_0x00a1
        L_0x0094:
            r2.close()
            goto L_0x00a1
        L_0x0098:
            java.lang.Object[] r8 = new java.lang.Object[r1]
            java.lang.String r9 = "DeleteBlockedCallTask.doInBackground"
            java.lang.String r1 = "missing call log permissions"
            com.android.dialer.common.LogUtil.m9i(r9, r1, r8)
        L_0x00a1:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.incallui.legacyblocking.DeleteBlockedCallTask.doInBackground(java.lang.Object[]):java.lang.Object");
    }

    public void onPostExecute(Object obj) {
        ((BlockedNumberContentObserver) this.listener).onDeleteBlockedCallTaskComplete(((Long) obj).longValue() >= 0);
    }
}
