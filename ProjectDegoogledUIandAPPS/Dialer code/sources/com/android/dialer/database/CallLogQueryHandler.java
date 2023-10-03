package com.android.dialer.database;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteDiskIOException;
import android.database.sqlite.SQLiteFullException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.CallLog;
import android.provider.VoicemailContract;
import com.android.contacts.common.database.NoNullCursorAsyncQueryHandler;
import com.android.dialer.common.LogUtil;
import com.android.dialer.phonenumbercache.CallLogQuery;
import com.android.dialer.telecom.TelecomUtil;
import com.android.dialer.util.PermissionsUtil;
import com.android.dialer.voicemailstatus.VoicemailStatusQuery;
import com.android.voicemail.VoicemailComponent;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class CallLogQueryHandler extends NoNullCursorAsyncQueryHandler {
    private final Context context;
    private final WeakReference<Listener> listener;
    private final int logLimit;

    private class CatchingWorkerHandler extends AsyncQueryHandler.WorkerHandler {
        CatchingWorkerHandler(CallLogQueryHandler callLogQueryHandler, Looper looper) {
            super(callLogQueryHandler, looper);
        }

        public void handleMessage(Message message) {
            try {
                CallLogQueryHandler.super.handleMessage(message);
            } catch (SQLiteDatabaseCorruptException | SQLiteDiskIOException | SQLiteFullException e) {
                LogUtil.m7e("CallLogQueryHandler.handleMessage", "exception on background worker thread", e);
            } catch (IllegalArgumentException e2) {
                LogUtil.m7e("CallLogQueryHandler.handleMessage", "contactsProvider not present on device", (Throwable) e2);
            } catch (SecurityException e3) {
                LogUtil.m7e("CallLogQueryHandler.handleMessage", "no permission to access ContactsProvider.", (Throwable) e3);
            }
        }
    }

    public interface Listener {
        boolean onCallsFetched(Cursor cursor);

        void onMissedCallsUnreadCountFetched(Cursor cursor);

        void onVoicemailStatusFetched(Cursor cursor);

        void onVoicemailUnreadCountFetched(Cursor cursor);
    }

    public CallLogQueryHandler(Context context2, ContentResolver contentResolver, Listener listener2, int i) {
        super(contentResolver);
        this.context = context2.getApplicationContext();
        this.listener = new WeakReference<>(listener2);
        this.logLimit = i;
    }

    private boolean updateAdapterData(Cursor cursor) {
        Listener listener2 = (Listener) this.listener.get();
        return listener2 != null && listener2.onCallsFetched(cursor);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [android.os.Handler, com.android.dialer.database.CallLogQueryHandler$CatchingWorkerHandler] */
    /* access modifiers changed from: protected */
    public Handler createHandler(Looper looper) {
        return new CatchingWorkerHandler(this, looper);
    }

    public void fetchCalls(int i, long j) {
        cancelOperation(54);
        String str = null;
        if (PermissionsUtil.hasPhonePermissions(this.context)) {
            StringBuilder sb = new StringBuilder();
            ArrayList arrayList = new ArrayList();
            sb.append("(");
            sb.append("type");
            sb.append(" != ?)");
            arrayList.add(Integer.toString(6));
            sb.append(" AND (");
            sb.append("deleted");
            sb.append(" = 0)");
            if (i > -1) {
                sb.append(" AND (");
                sb.append("type");
                sb.append(" = ?)");
                arrayList.add(Integer.toString(i));
            } else {
                sb.append(" AND NOT ");
                sb.append("(type = 4)");
            }
            if (j > 0) {
                sb.append(" AND (");
                sb.append("date");
                sb.append(" > ?)");
                arrayList.add(Long.toString(j));
            }
            if (i == 4) {
                VoicemailComponent.get(this.context).getVoicemailClient().appendOmtpVoicemailSelectionClause(this.context, sb, arrayList);
            } else {
                sb.append(" AND (");
                sb.append("subscription_component_name");
                sb.append(" IS NULL OR ");
                sb.append("subscription_component_name");
                sb.append(" NOT LIKE 'com.google.android.apps.tachyon%' OR ");
                sb.append("features");
                sb.append(" & ");
                sb.append(1);
                sb.append(" == ");
                sb.append(1);
                sb.append(")");
            }
            int i2 = this.logLimit;
            if (i2 == -1) {
                i2 = 1000;
            }
            if (sb.length() > 0) {
                str = sb.toString();
            }
            startQuery(54, (Object) null, TelecomUtil.getCallLogUri(this.context).buildUpon().appendQueryParameter("limit", Integer.toString(i2)).build(), CallLogQuery.getProjection(), str, (String[]) arrayList.toArray(new String[arrayList.size()]), "date DESC");
            return;
        }
        updateAdapterData((Cursor) null);
    }

    public void fetchMissedCallsUnreadCount() {
        if (PermissionsUtil.hasPhonePermissions(this.context)) {
            startQuery(59, (Object) null, CallLog.Calls.CONTENT_URI, new String[]{"_id"}, "is_read = 0 OR is_read IS NULL AND type = 3", (String[]) null, (String) null);
        }
    }

    public void fetchVoicemailStatus() {
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList = new ArrayList();
        VoicemailComponent.get(this.context).getVoicemailClient().appendOmtpVoicemailStatusSelectionClause(this.context, sb, arrayList);
        if (TelecomUtil.hasReadWriteVoicemailPermissions(this.context)) {
            LogUtil.m9i("CallLogQueryHandler.fetchVoicemailStatus", "fetching voicemail status", new Object[0]);
            startQuery(57, (Object) null, VoicemailContract.Status.CONTENT_URI, VoicemailStatusQuery.getProjection(), sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]), (String) null);
            return;
        }
        LogUtil.m9i("CallLogQueryHandler.fetchVoicemailStatus", "fetching voicemail status failed due to permissions", new Object[0]);
    }

    public void fetchVoicemailUnreadCount() {
        if (TelecomUtil.hasReadWriteVoicemailPermissions(this.context)) {
            StringBuilder sb = new StringBuilder("is_read=0 AND deleted=0 ");
            ArrayList arrayList = new ArrayList();
            VoicemailComponent.get(this.context).getVoicemailClient().appendOmtpVoicemailSelectionClause(this.context, sb, arrayList);
            startQuery(58, (Object) null, VoicemailContract.Voicemails.CONTENT_URI, new String[]{"_id"}, sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]), (String) null);
        }
    }

    public void markMissedCallsAsRead() {
        if (PermissionsUtil.hasPhonePermissions(this.context)) {
            ContentValues contentValues = new ContentValues(1);
            contentValues.put("is_read", "1");
            startUpdate(56, (Object) null, CallLog.Calls.CONTENT_URI, contentValues, "is_read = 0 OR is_read IS NULL AND type = 3", (String[]) null);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0068, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void onNotNullableQueryComplete(int r3, java.lang.Object r4, android.database.Cursor r5) {
        /*
            r2 = this;
            monitor-enter(r2)
            if (r5 != 0) goto L_0x0005
            monitor-exit(r2)
            return
        L_0x0005:
            r4 = 54
            if (r3 != r4) goto L_0x0011
            boolean r3 = r2.updateAdapterData(r5)     // Catch:{ all -> 0x0023 }
            if (r3 == 0) goto L_0x0062
            r5 = 0
            goto L_0x0062
        L_0x0011:
            r4 = 57
            if (r3 != r4) goto L_0x0025
            java.lang.ref.WeakReference<com.android.dialer.database.CallLogQueryHandler$Listener> r3 = r2.listener     // Catch:{ all -> 0x0023 }
            java.lang.Object r3 = r3.get()     // Catch:{ all -> 0x0023 }
            com.android.dialer.database.CallLogQueryHandler$Listener r3 = (com.android.dialer.database.CallLogQueryHandler.Listener) r3     // Catch:{ all -> 0x0023 }
            if (r3 == 0) goto L_0x0062
            r3.onVoicemailStatusFetched(r5)     // Catch:{ all -> 0x0023 }
            goto L_0x0062
        L_0x0023:
            r3 = move-exception
            goto L_0x0069
        L_0x0025:
            r4 = 58
            if (r3 != r4) goto L_0x0037
            java.lang.ref.WeakReference<com.android.dialer.database.CallLogQueryHandler$Listener> r3 = r2.listener     // Catch:{ all -> 0x0023 }
            java.lang.Object r3 = r3.get()     // Catch:{ all -> 0x0023 }
            com.android.dialer.database.CallLogQueryHandler$Listener r3 = (com.android.dialer.database.CallLogQueryHandler.Listener) r3     // Catch:{ all -> 0x0023 }
            if (r3 == 0) goto L_0x0062
            r3.onVoicemailUnreadCountFetched(r5)     // Catch:{ all -> 0x0023 }
            goto L_0x0062
        L_0x0037:
            r4 = 59
            if (r3 != r4) goto L_0x0049
            java.lang.ref.WeakReference<com.android.dialer.database.CallLogQueryHandler$Listener> r3 = r2.listener     // Catch:{ all -> 0x0023 }
            java.lang.Object r3 = r3.get()     // Catch:{ all -> 0x0023 }
            com.android.dialer.database.CallLogQueryHandler$Listener r3 = (com.android.dialer.database.CallLogQueryHandler.Listener) r3     // Catch:{ all -> 0x0023 }
            if (r3 == 0) goto L_0x0062
            r3.onMissedCallsUnreadCountFetched(r5)     // Catch:{ all -> 0x0023 }
            goto L_0x0062
        L_0x0049:
            java.lang.String r4 = "CallLogQueryHandler.onNotNullableQueryComplete"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0023 }
            r0.<init>()     // Catch:{ all -> 0x0023 }
            java.lang.String r1 = "unknown query completed: ignoring: "
            r0.append(r1)     // Catch:{ all -> 0x0023 }
            r0.append(r3)     // Catch:{ all -> 0x0023 }
            java.lang.String r3 = r0.toString()     // Catch:{ all -> 0x0023 }
            r0 = 0
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ all -> 0x0023 }
            com.android.dialer.common.LogUtil.m10w(r4, r3, r0)     // Catch:{ all -> 0x0023 }
        L_0x0062:
            if (r5 == 0) goto L_0x0067
            r5.close()     // Catch:{ all -> 0x006d }
        L_0x0067:
            monitor-exit(r2)
            return
        L_0x0069:
            r5.close()     // Catch:{ all -> 0x006d }
            throw r3     // Catch:{ all -> 0x006d }
        L_0x006d:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.database.CallLogQueryHandler.onNotNullableQueryComplete(int, java.lang.Object, android.database.Cursor):void");
    }
}
