package com.android.vcard;

import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.OperationApplicationException;
import android.net.Uri;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;

public class VCardEntryCommitter implements VCardEntryHandler {
    public static String LOG_TAG = "vCard";
    private final ContentResolver mContentResolver;
    private int mCounter;
    private final ArrayList mCreatedUris = new ArrayList();
    private ArrayList mOperationList;
    private long mTimeToCommit;

    public VCardEntryCommitter(ContentResolver contentResolver) {
        this.mContentResolver = contentResolver;
    }

    private Uri pushIntoContentResolver(ArrayList arrayList) {
        try {
            ContentProviderResult[] applyBatch = this.mContentResolver.applyBatch("com.android.contacts", arrayList);
            if (applyBatch == null || applyBatch.length == 0) {
                return null;
            }
            if (applyBatch[0] == null) {
                return null;
            }
            return applyBatch[0].uri;
        } catch (RemoteException e) {
            Log.e(LOG_TAG, String.format("%s: %s", new Object[]{e.toString(), e.getMessage()}));
            return null;
        } catch (OperationApplicationException e2) {
            Log.e(LOG_TAG, String.format("%s: %s", new Object[]{e2.toString(), e2.getMessage()}));
            return null;
        }
    }

    public ArrayList getCreatedUris() {
        return this.mCreatedUris;
    }

    public void onEnd() {
        ArrayList arrayList = this.mOperationList;
        if (arrayList != null) {
            this.mCreatedUris.add(pushIntoContentResolver(arrayList));
        }
        VCardConfig.showPerformanceLog();
    }

    public void onEntryCreated(VCardEntry vCardEntry) {
        long currentTimeMillis = System.currentTimeMillis();
        this.mOperationList = vCardEntry.constructInsertOperations(this.mContentResolver, this.mOperationList);
        this.mCounter++;
        if (this.mCounter >= 20) {
            this.mCreatedUris.add(pushIntoContentResolver(this.mOperationList));
            this.mCounter = 0;
            this.mOperationList = null;
        }
        this.mTimeToCommit = (System.currentTimeMillis() - currentTimeMillis) + this.mTimeToCommit;
    }

    public void onStart() {
    }
}
