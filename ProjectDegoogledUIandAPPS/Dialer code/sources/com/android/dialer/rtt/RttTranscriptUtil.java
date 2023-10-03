package com.android.dialer.rtt;

import android.content.ContentValues;
import android.content.Context;
import com.android.dialer.common.Assert;

public final class RttTranscriptUtil {
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

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x004e, code lost:
        $closeResource(r11, r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0051, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x004d, code lost:
        r0 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ com.google.common.collect.ImmutableSet lambda$getAvailableRttTranscriptIds$0(android.content.Context r11, com.google.common.collect.ImmutableSet r12) throws java.lang.Exception {
        /*
            com.android.dialer.common.Assert.isWorkerThread()
            com.android.dialer.rtt.RttTranscriptDatabaseHelper r0 = new com.android.dialer.rtt.RttTranscriptDatabaseHelper
            r0.<init>(r11)
            com.google.common.collect.ImmutableSet$Builder r11 = com.google.common.collect.ImmutableSet.builder()
            com.android.dialer.common.database.Selection$Builder r1 = com.android.dialer.common.database.Selection.builder()
            java.lang.String r2 = "rtt_transcript_id"
            com.android.dialer.common.database.Selection$Column r3 = com.android.dialer.common.database.Selection.column(r2)
            com.android.dialer.common.database.Selection r12 = r3.mo5865in(r12)
            r1.and(r12)
            com.android.dialer.common.database.Selection r12 = r1.build()
            android.database.sqlite.SQLiteDatabase r3 = r0.getReadableDatabase()
            java.lang.String[] r5 = new java.lang.String[]{r2}
            java.lang.String r6 = r12.getSelection()
            java.lang.String[] r7 = r12.getSelectionArgs()
            r8 = 0
            r9 = 0
            r10 = 0
            java.lang.String r4 = "rtt_transcript"
            android.database.Cursor r12 = r3.query(r4, r5, r6, r7, r8, r9, r10)
            if (r12 == 0) goto L_0x0052
        L_0x003c:
            boolean r1 = r12.moveToNext()     // Catch:{ all -> 0x004b }
            if (r1 == 0) goto L_0x0052
            r1 = 0
            java.lang.String r1 = r12.getString(r1)     // Catch:{ all -> 0x004b }
            r11.add((java.lang.Object) r1)     // Catch:{ all -> 0x004b }
            goto L_0x003c
        L_0x004b:
            r11 = move-exception
            throw r11     // Catch:{ all -> 0x004d }
        L_0x004d:
            r0 = move-exception
            $closeResource(r11, r12)
            throw r0
        L_0x0052:
            if (r12 == 0) goto L_0x0058
            r1 = 0
            $closeResource(r1, r12)
        L_0x0058:
            r0.close()
            com.google.common.collect.ImmutableSet r11 = r11.build()
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.rtt.RttTranscriptUtil.lambda$getAvailableRttTranscriptIds$0(android.content.Context, com.google.common.collect.ImmutableSet):com.google.common.collect.ImmutableSet");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0046, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        $closeResource(r9, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004a, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ com.android.dialer.rtt.RttTranscript lambda$loadRttTranscript$1(android.content.Context r9, java.lang.String r10) throws java.lang.Exception {
        /*
            com.android.dialer.common.Assert.isWorkerThread()
            com.android.dialer.rtt.RttTranscriptDatabaseHelper r0 = new com.android.dialer.rtt.RttTranscriptDatabaseHelper
            r0.<init>(r9)
            android.database.sqlite.SQLiteDatabase r1 = r0.getReadableDatabase()     // Catch:{ all -> 0x0055 }
            java.lang.String r2 = "rtt_transcript"
            java.lang.String r9 = "transcript_data"
            java.lang.String[] r3 = new java.lang.String[]{r9}     // Catch:{ all -> 0x0055 }
            java.lang.String r4 = "rtt_transcript_id = ?"
            r9 = 1
            java.lang.String[] r5 = new java.lang.String[r9]     // Catch:{ all -> 0x0055 }
            r9 = 0
            r5[r9] = r10     // Catch:{ all -> 0x0055 }
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r10 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x0055 }
            r1 = 0
            if (r10 == 0) goto L_0x004b
            boolean r2 = r10.moveToFirst()     // Catch:{ all -> 0x0044 }
            if (r2 == 0) goto L_0x004b
            byte[] r9 = r10.getBlob(r9)     // Catch:{ InvalidProtocolBufferException -> 0x003b }
            com.android.dialer.rtt.RttTranscript r9 = com.android.dialer.rtt.RttTranscript.parseFrom(r9)     // Catch:{ InvalidProtocolBufferException -> 0x003b }
            $closeResource(r1, r10)     // Catch:{ all -> 0x0055 }
            r0.close()
            goto L_0x0054
        L_0x003b:
            r9 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException     // Catch:{ all -> 0x0044 }
            java.lang.String r2 = "Parse failed for RTT transcript"
            r1.<init>(r2, r9)     // Catch:{ all -> 0x0044 }
            throw r1     // Catch:{ all -> 0x0044 }
        L_0x0044:
            r9 = move-exception
            throw r9     // Catch:{ all -> 0x0046 }
        L_0x0046:
            r1 = move-exception
            $closeResource(r9, r10)     // Catch:{ all -> 0x0055 }
            throw r1     // Catch:{ all -> 0x0055 }
        L_0x004b:
            if (r10 == 0) goto L_0x0050
            $closeResource(r1, r10)     // Catch:{ all -> 0x0055 }
        L_0x0050:
            r0.close()
            r9 = r1
        L_0x0054:
            return r9
        L_0x0055:
            r9 = move-exception
            r0.close()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.rtt.RttTranscriptUtil.lambda$loadRttTranscript$1(android.content.Context, java.lang.String):com.android.dialer.rtt.RttTranscript");
    }

    static /* synthetic */ Void lambda$saveRttTranscript$2(Context context, RttTranscript rttTranscript) throws Exception {
        Assert.isWorkerThread();
        RttTranscriptDatabaseHelper rttTranscriptDatabaseHelper = new RttTranscriptDatabaseHelper(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put("rtt_transcript_id", rttTranscript.getId());
        contentValues.put("transcript_data", rttTranscript.toByteArray());
        long insert = rttTranscriptDatabaseHelper.getWritableDatabase().insert("rtt_transcript", (String) null, contentValues);
        rttTranscriptDatabaseHelper.close();
        if (insert >= 0) {
            return null;
        }
        throw new RuntimeException("Failed to save RTT transcript");
    }
}
