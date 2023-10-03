package com.android.voicemail.impl.sync;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.VoicemailContract;
import android.telecom.PhoneAccountHandle;
import com.android.voicemail.impl.Voicemail;
import java.util.ArrayList;
import java.util.List;

public class VoicemailsQueryHelper {
    static final String[] PROJECTION = {"_id", "source_data", "is_read", "deleted", "transcription"};
    private ContentResolver contentResolver;
    private Context context;
    private Uri sourceUri = VoicemailContract.Voicemails.buildSourceUri(this.context.getPackageName());

    public VoicemailsQueryHelper(Context context2) {
        this.context = context2;
        this.contentResolver = context2.getContentResolver();
    }

    private List getLocalVoicemails(String str) {
        Cursor query = this.contentResolver.query(this.sourceUri, PROJECTION, str, (String[]) null, (String) null);
        if (query == null) {
            return null;
        }
        try {
            ArrayList arrayList = new ArrayList();
            while (query.moveToNext()) {
                boolean z = false;
                long j = query.getLong(0);
                String string = query.getString(1);
                if (query.getInt(2) == 1) {
                    z = true;
                }
                String string2 = query.getString(4);
                Voicemail.Builder createForUpdate = Voicemail.createForUpdate(j, string);
                createForUpdate.setIsRead(z);
                createForUpdate.setTranscription(string2);
                arrayList.add(createForUpdate.build());
            }
            return arrayList;
        } finally {
            query.close();
        }
    }

    public int deleteFromDatabase(List<Voicemail> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(list.get(i).getId());
        }
        return this.contentResolver.delete(VoicemailContract.Voicemails.CONTENT_URI, String.format("_id IN (%s)", new Object[]{sb.toString()}), (String[]) null);
    }

    public void deleteNonArchivedFromDatabase(Voicemail voicemail) {
        this.contentResolver.delete(VoicemailContract.Voicemails.CONTENT_URI, "_id=? AND archived= 0", new String[]{Long.toString(voicemail.getId())});
    }

    public List<Voicemail> getAllVoicemails(PhoneAccountHandle phoneAccountHandle) {
        return getLocalVoicemails((String) null);
    }

    public List<Voicemail> getDeletedVoicemails(PhoneAccountHandle phoneAccountHandle) {
        return getLocalVoicemails("deleted=1");
    }

    public boolean isVoicemailUnique(Voicemail voicemail) {
        PhoneAccountHandle phoneAccount = voicemail.getPhoneAccount();
        if (phoneAccount != null) {
            String flattenToString = phoneAccount.getComponentName().flattenToString();
            String id = phoneAccount.getId();
            String sourceData = voicemail.getSourceData();
            if (!(flattenToString == null || id == null || sourceData == null)) {
                Cursor cursor = null;
                try {
                    Cursor query = this.contentResolver.query(this.sourceUri, PROJECTION, "subscription_component_name=? AND subscription_id=? AND source_data=?", new String[]{flattenToString, id, sourceData}, (String) null);
                    if (query.getCount() == 0) {
                        query.close();
                        return true;
                    }
                    query.close();
                    return false;
                } catch (Throwable th) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
        }
        return true;
    }

    public void markArchivedInDatabase(List<Voicemail> list) {
        for (Voicemail id : list) {
            Uri withAppendedId = ContentUris.withAppendedId(this.sourceUri, id.getId());
            ContentValues contentValues = new ContentValues();
            contentValues.put("archived", "1");
            this.contentResolver.update(withAppendedId, contentValues, (String) null, (String[]) null);
        }
    }

    public int markCleanInDatabase(List<Voicemail> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            this.contentResolver.update(ContentUris.withAppendedId(this.sourceUri, list.get(i).getId()), new ContentValues(), (String) null, (String[]) null);
        }
        return size;
    }

    public void markReadInDatabase(Voicemail voicemail) {
        Uri withAppendedId = ContentUris.withAppendedId(this.sourceUri, voicemail.getId());
        ContentValues contentValues = new ContentValues();
        contentValues.put("is_read", "1");
        this.contentResolver.update(withAppendedId, contentValues, (String) null, (String[]) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0064, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0065, code lost:
        if (r7 != null) goto L_0x0067;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x006b, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x006c, code lost:
        r8.addSuppressed(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x006f, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.android.voicemail.impl.Voicemail> oldestVoicemailsOnServer(int r8) {
        /*
            r7 = this;
            if (r8 <= 0) goto L_0x0070
            java.lang.String r0 = "date ASC limit "
            java.lang.String r6 = com.android.tools.p006r8.GeneratedOutlineSupport.outline5(r0, r8)
            android.content.ContentResolver r1 = r7.contentResolver
            android.net.Uri r2 = r7.sourceUri
            java.lang.String[] r3 = PROJECTION
            r5 = 0
            java.lang.String r4 = "archived=0"
            android.database.Cursor r7 = r1.query(r2, r3, r4, r5, r6)
            com.android.dialer.common.Assert.isNotNull(r7)     // Catch:{ all -> 0x0062 }
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x0062 }
            r0.<init>()     // Catch:{ all -> 0x0062 }
        L_0x001d:
            boolean r1 = r7.moveToNext()     // Catch:{ all -> 0x0062 }
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x0039
            long r3 = r7.getLong(r3)     // Catch:{ all -> 0x0062 }
            java.lang.String r1 = r7.getString(r2)     // Catch:{ all -> 0x0062 }
            com.android.voicemail.impl.Voicemail$Builder r1 = com.android.voicemail.impl.Voicemail.createForUpdate(r3, r1)     // Catch:{ all -> 0x0062 }
            com.android.voicemail.impl.Voicemail r1 = r1.build()     // Catch:{ all -> 0x0062 }
            r0.add(r1)     // Catch:{ all -> 0x0062 }
            goto L_0x001d
        L_0x0039:
            int r1 = r0.size()     // Catch:{ all -> 0x0062 }
            if (r1 != r8) goto L_0x0043
            r7.close()
            return r0
        L_0x0043:
            java.lang.String r1 = "voicemail count (%d) doesn't matched expected (%d)"
            r4 = 2
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x0062 }
            int r0 = r0.size()     // Catch:{ all -> 0x0062 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x0062 }
            r4[r3] = r0     // Catch:{ all -> 0x0062 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x0062 }
            r4[r2] = r8     // Catch:{ all -> 0x0062 }
            java.lang.String r8 = java.lang.String.format(r1, r4)     // Catch:{ all -> 0x0062 }
            java.lang.AssertionError r0 = new java.lang.AssertionError     // Catch:{ all -> 0x0062 }
            r0.<init>(r8)     // Catch:{ all -> 0x0062 }
            throw r0     // Catch:{ all -> 0x0062 }
        L_0x0062:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x0064 }
        L_0x0064:
            r0 = move-exception
            if (r7 == 0) goto L_0x006f
            r7.close()     // Catch:{ all -> 0x006b }
            goto L_0x006f
        L_0x006b:
            r7 = move-exception
            r8.addSuppressed(r7)
        L_0x006f:
            throw r0
        L_0x0070:
            java.lang.AssertionError r7 = new java.lang.AssertionError
            java.lang.String r8 = "Query for remote voicemails cannot be <= 0"
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.voicemail.impl.sync.VoicemailsQueryHelper.oldestVoicemailsOnServer(int):java.util.List");
    }

    public void updateWithTranscription(Voicemail voicemail, String str) {
        Uri withAppendedId = ContentUris.withAppendedId(this.sourceUri, voicemail.getId());
        ContentValues contentValues = new ContentValues();
        contentValues.put("transcription", str);
        this.contentResolver.update(withAppendedId, contentValues, (String) null, (String[]) null);
    }
}
