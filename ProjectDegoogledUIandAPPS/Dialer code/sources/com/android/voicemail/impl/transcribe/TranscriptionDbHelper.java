package com.android.voicemail.impl.transcribe;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.provider.VoicemailContract;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.tools.p006r8.GeneratedOutlineSupport;

@TargetApi(26)
public class TranscriptionDbHelper {
    static final String[] PROJECTION = {"_id", "transcription", "transcription_state"};
    private final ContentResolver contentResolver;
    private final Uri uri;

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

    TranscriptionDbHelper(Context context, Uri uri2) {
        Assert.isNotNull(uri2);
        this.contentResolver = context.getContentResolver();
        this.uri = uri2;
    }

    private void updateDatabase(ContentValues contentValues) {
        int update = this.contentResolver.update(this.uri, contentValues, (String) null, (String[]) null);
        if (update != 1) {
            LogUtil.m8e("TranscriptionDbHelper.updateDatabase", GeneratedOutlineSupport.outline5("Wrong row count, should have updated 1 row, was: ", update), new Object[0]);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004d, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004e, code lost:
        if (r0 != null) goto L_0x0050;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0050, code lost:
        $closeResource(r9, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0053, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<android.net.Uri> getTranscribingVoicemails() {
        /*
            r9 = this;
            int r0 = android.os.Build.VERSION.SDK_INT
            r0 = 1
            com.android.dialer.common.Assert.checkState(r0)
            com.android.dialer.common.Assert.isWorkerThread()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.lang.String[] r6 = new java.lang.String[r0]
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r8 = 0
            r6[r8] = r0
            android.content.ContentResolver r2 = r9.contentResolver
            android.net.Uri r3 = r9.uri
            java.lang.String[] r4 = PROJECTION
            r7 = 0
            java.lang.String r5 = "transcription_state=?"
            android.database.Cursor r0 = r2.query(r3, r4, r5, r6, r7)
            r2 = 0
            if (r0 != 0) goto L_0x0031
            java.lang.String r9 = "TranscriptionDbHelper.getTranscribingVoicemails"
            java.lang.String r3 = "query failed."
            java.lang.Object[] r4 = new java.lang.Object[r8]     // Catch:{ all -> 0x004b }
            com.android.dialer.common.LogUtil.m8e((java.lang.String) r9, (java.lang.String) r3, (java.lang.Object[]) r4)     // Catch:{ all -> 0x004b }
            goto L_0x0045
        L_0x0031:
            boolean r3 = r0.moveToNext()     // Catch:{ all -> 0x004b }
            if (r3 == 0) goto L_0x0045
            android.net.Uri r3 = r9.uri     // Catch:{ all -> 0x004b }
            long r4 = r0.getLong(r8)     // Catch:{ all -> 0x004b }
            android.net.Uri r3 = android.content.ContentUris.withAppendedId(r3, r4)     // Catch:{ all -> 0x004b }
            r1.add(r3)     // Catch:{ all -> 0x004b }
            goto L_0x0031
        L_0x0045:
            if (r0 == 0) goto L_0x004a
            $closeResource(r2, r0)
        L_0x004a:
            return r1
        L_0x004b:
            r9 = move-exception
            throw r9     // Catch:{ all -> 0x004d }
        L_0x004d:
            r1 = move-exception
            if (r0 == 0) goto L_0x0053
            $closeResource(r9, r0)
        L_0x0053:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.voicemail.impl.transcribe.TranscriptionDbHelper.getTranscribingVoicemails():java.util.List");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004e, code lost:
        if (r2 != null) goto L_0x0050;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0050, code lost:
        $closeResource(r8, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0053, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<android.net.Uri> getUntranscribedVoicemails() {
        /*
            r8 = this;
            int r0 = android.os.Build.VERSION.SDK_INT
            r0 = 1
            com.android.dialer.common.Assert.checkState(r0)
            com.android.dialer.common.Assert.isWorkerThread()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.lang.String[] r6 = new java.lang.String[r0]
            r0 = 0
            java.lang.String r2 = java.lang.String.valueOf(r0)
            r6[r0] = r2
            android.content.ContentResolver r2 = r8.contentResolver
            android.net.Uri r3 = r8.uri
            java.lang.String[] r4 = PROJECTION
            r7 = 0
            java.lang.String r5 = "(transcription is NULL OR transcription = '') AND transcription_state=?"
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7)
            r3 = 0
            if (r2 != 0) goto L_0x0031
            java.lang.String r8 = "TranscriptionDbHelper.getUntranscribedVoicemails"
            java.lang.String r4 = "query failed."
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ all -> 0x004b }
            com.android.dialer.common.LogUtil.m8e((java.lang.String) r8, (java.lang.String) r4, (java.lang.Object[]) r0)     // Catch:{ all -> 0x004b }
            goto L_0x0045
        L_0x0031:
            boolean r4 = r2.moveToNext()     // Catch:{ all -> 0x004b }
            if (r4 == 0) goto L_0x0045
            android.net.Uri r4 = r8.uri     // Catch:{ all -> 0x004b }
            long r5 = r2.getLong(r0)     // Catch:{ all -> 0x004b }
            android.net.Uri r4 = android.content.ContentUris.withAppendedId(r4, r5)     // Catch:{ all -> 0x004b }
            r1.add(r4)     // Catch:{ all -> 0x004b }
            goto L_0x0031
        L_0x0045:
            if (r2 == 0) goto L_0x004a
            $closeResource(r3, r2)
        L_0x004a:
            return r1
        L_0x004b:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x004d }
        L_0x004d:
            r0 = move-exception
            if (r2 == 0) goto L_0x0053
            $closeResource(r8, r2)
        L_0x0053:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.voicemail.impl.transcribe.TranscriptionDbHelper.getUntranscribedVoicemails():java.util.List");
    }

    /* access modifiers changed from: package-private */
    public void setTranscriptionAndState(String str, int i) {
        Assert.isWorkerThread();
        LogUtil.m9i("TranscriptionDbHelper.setTranscriptionAndState", "uri: " + this.uri + ", state: " + i, new Object[0]);
        ContentValues contentValues = new ContentValues();
        contentValues.put("transcription", str);
        contentValues.put("transcription_state", Integer.valueOf(i));
        updateDatabase(contentValues);
    }

    /* access modifiers changed from: package-private */
    public void setTranscriptionState(int i) {
        Assert.isWorkerThread();
        LogUtil.m9i("TranscriptionDbHelper.setTranscriptionState", "uri: " + this.uri + ", state: " + i, new Object[0]);
        ContentValues contentValues = new ContentValues();
        contentValues.put("transcription_state", Integer.valueOf(i));
        updateDatabase(contentValues);
    }

    TranscriptionDbHelper(Context context) {
        Uri buildSourceUri = VoicemailContract.Voicemails.buildSourceUri(context.getPackageName());
        Assert.isNotNull(buildSourceUri);
        this.contentResolver = context.getContentResolver();
        this.uri = buildSourceUri;
    }
}
