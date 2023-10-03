package com.android.dialer.voicemail.listui.error;

import android.content.Context;
import com.android.dialer.common.concurrent.DialerExecutor;
import java.util.List;

public class VoicemailStatusWorker implements DialerExecutor.Worker<Context, List<VoicemailStatus>> {
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0063, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0068, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0069, code lost:
        r9.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x006c, code lost:
        throw r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0045, code lost:
        if (r0 != null) goto L_0x0047;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object doInBackground(java.lang.Object r10) throws java.lang.Throwable {
        /*
            r9 = this;
            android.content.Context r10 = (android.content.Context) r10
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            boolean r0 = com.android.dialer.telecom.TelecomUtil.hasReadWriteVoicemailPermissions(r10)
            if (r0 != 0) goto L_0x000e
            goto L_0x0060
        L_0x000e:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            com.android.voicemail.VoicemailComponent r2 = com.android.voicemail.VoicemailComponent.get(r10)
            com.android.voicemail.VoicemailClient r2 = r2.getVoicemailClient()
            r2.appendOmtpVoicemailStatusSelectionClause(r10, r0, r1)
            android.content.ContentResolver r3 = r10.getContentResolver()
            android.net.Uri r4 = android.provider.VoicemailContract.Status.CONTENT_URI
            java.lang.String[] r5 = com.android.dialer.voicemailstatus.VoicemailStatusQuery.getProjection()
            java.lang.String r6 = r0.toString()
            int r0 = r1.size()
            java.lang.String[] r0 = new java.lang.String[r0]
            java.lang.Object[] r0 = r1.toArray(r0)
            r7 = r0
            java.lang.String[] r7 = (java.lang.String[]) r7
            r8 = 0
            android.database.Cursor r0 = r3.query(r4, r5, r6, r7, r8)
            if (r0 != 0) goto L_0x004b
            if (r0 == 0) goto L_0x0060
        L_0x0047:
            r0.close()
            goto L_0x0060
        L_0x004b:
            r0.moveToFirst()     // Catch:{ all -> 0x0061 }
        L_0x004e:
            boolean r1 = r0.isAfterLast()     // Catch:{ all -> 0x0061 }
            if (r1 != 0) goto L_0x0047
            com.android.dialer.voicemail.listui.error.VoicemailStatus r1 = new com.android.dialer.voicemail.listui.error.VoicemailStatus     // Catch:{ all -> 0x0061 }
            r1.<init>(r10, r0)     // Catch:{ all -> 0x0061 }
            r9.add(r1)     // Catch:{ all -> 0x0061 }
            r0.moveToNext()     // Catch:{ all -> 0x0061 }
            goto L_0x004e
        L_0x0060:
            return r9
        L_0x0061:
            r9 = move-exception
            throw r9     // Catch:{ all -> 0x0063 }
        L_0x0063:
            r10 = move-exception
            r0.close()     // Catch:{ all -> 0x0068 }
            goto L_0x006c
        L_0x0068:
            r0 = move-exception
            r9.addSuppressed(r0)
        L_0x006c:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.voicemail.listui.error.VoicemailStatusWorker.doInBackground(java.lang.Object):java.lang.Object");
    }
}
