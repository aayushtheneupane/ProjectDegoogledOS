package com.android.dialer.contacts.hiresphoto;

import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import com.android.dialer.common.LogUtil;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import java.util.concurrent.Callable;

public class HighResolutionPhotoRequesterImpl implements HighResolutionPhotoRequester {
    static final ComponentName SYNC_HIGH_RESOLUTION_PHOTO_SERVICE = new ComponentName("com.google.android.syncadapters.contacts", "com.google.android.syncadapters.contacts.SyncHighResPhotoIntentService");
    private final Context appContext;
    private final ListeningExecutorService backgroundExecutor;

    private static class RequestFailedException extends Exception {
        RequestFailedException(String str) {
            super(str);
        }

        RequestFailedException(String str, Throwable th) {
            super(str, th);
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

    HighResolutionPhotoRequesterImpl(Context context, ListeningExecutorService listeningExecutorService) {
        this.appContext = context;
        this.backgroundExecutor = listeningExecutorService;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00e8, code lost:
        r13 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00e9, code lost:
        if (r0 != null) goto L_0x00eb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00eb, code lost:
        $closeResource(r12, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00ee, code lost:
        throw r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00f9, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00fa, code lost:
        if (r13 != null) goto L_0x00fc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00fc, code lost:
        $closeResource(r12, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00ff, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void requestInternal(android.net.Uri r13) throws com.android.dialer.contacts.hiresphoto.HighResolutionPhotoRequesterImpl.RequestFailedException {
        /*
            r12 = this;
            android.content.Context r0 = r12.appContext
            android.content.ContentResolver r1 = r0.getContentResolver()
            java.lang.String r0 = "_id"
            java.lang.String[] r3 = new java.lang.String[]{r0}
            r4 = 0
            r5 = 0
            r6 = 0
            r2 = r13
            android.database.Cursor r13 = r1.query(r2, r3, r4, r5, r6)
            if (r13 == 0) goto L_0x00ef
            boolean r1 = r13.moveToFirst()     // Catch:{ all -> 0x00f7 }
            if (r1 == 0) goto L_0x00ef
            r1 = 0
            long r2 = r13.getLong(r1)     // Catch:{ all -> 0x00f7 }
            r4 = 0
            $closeResource(r4, r13)
            java.util.ArrayList r13 = new java.util.ArrayList
            r13.<init>()
            java.lang.String r5 = "contact_id"
            com.android.dialer.common.database.Selection$Column r5 = com.android.dialer.common.database.Selection.column(r5)
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            java.lang.String r3 = "="
            com.android.dialer.common.database.Selection r2 = r5.mo5867is(r3, r2)
            com.android.dialer.common.database.Selection$Builder r2 = r2.buildUpon()
            java.lang.String r5 = "account_type"
            com.android.dialer.common.database.Selection$Column r6 = com.android.dialer.common.database.Selection.column(r5)
            java.lang.String r7 = "com.google"
            com.android.dialer.common.database.Selection r3 = r6.mo5867is(r3, r7)
            r2.and(r3)
            com.android.dialer.common.database.Selection r2 = r2.build()
            android.content.Context r3 = r12.appContext
            android.content.ContentResolver r6 = r3.getContentResolver()
            android.net.Uri r7 = android.provider.ContactsContract.RawContacts.CONTENT_URI
            java.lang.String[] r8 = new java.lang.String[]{r0, r5}
            java.lang.String r9 = r2.getSelection()
            java.lang.String[] r10 = r2.getSelectionArgs()
            r11 = 0
            android.database.Cursor r0 = r6.query(r7, r8, r9, r10, r11)
            if (r0 == 0) goto L_0x00de
            r0.moveToFirst()     // Catch:{ all -> 0x00e6 }
        L_0x006f:
            boolean r2 = r0.isAfterLast()     // Catch:{ all -> 0x00e6 }
            if (r2 != 0) goto L_0x0084
            long r2 = r0.getLong(r1)     // Catch:{ all -> 0x00e6 }
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ all -> 0x00e6 }
            r13.add(r2)     // Catch:{ all -> 0x00e6 }
            r0.moveToNext()     // Catch:{ all -> 0x00e6 }
            goto L_0x006f
        L_0x0084:
            $closeResource(r4, r0)
            java.util.Iterator r13 = r13.iterator()
        L_0x008b:
            boolean r0 = r13.hasNext()
            if (r0 == 0) goto L_0x00dd
            java.lang.Object r0 = r13.next()
            java.lang.Long r0 = (java.lang.Long) r0
            android.net.Uri r2 = android.provider.ContactsContract.RawContacts.CONTENT_URI
            long r3 = r0.longValue()
            android.net.Uri r0 = android.content.ContentUris.withAppendedId(r2, r3)
            android.content.Intent r2 = new android.content.Intent
            java.lang.String r3 = "android.intent.action.VIEW"
            r2.<init>(r3)
            android.content.ComponentName r3 = SYNC_HIGH_RESOLUTION_PHOTO_SERVICE
            r2.setComponent(r3)
            java.lang.String r3 = "vnd.android.cursor.item/raw_contact"
            r2.setDataAndType(r0, r3)
            r3 = 1
            r2.addFlags(r3)
            java.lang.String r3 = "HighResolutionPhotoRequesterImpl.requestInternal"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IllegalStateException | SecurityException -> 0x00d4 }
            r4.<init>()     // Catch:{ IllegalStateException | SecurityException -> 0x00d4 }
            java.lang.String r5 = "requesting photo for "
            r4.append(r5)     // Catch:{ IllegalStateException | SecurityException -> 0x00d4 }
            r4.append(r0)     // Catch:{ IllegalStateException | SecurityException -> 0x00d4 }
            java.lang.String r0 = r4.toString()     // Catch:{ IllegalStateException | SecurityException -> 0x00d4 }
            java.lang.Object[] r4 = new java.lang.Object[r1]     // Catch:{ IllegalStateException | SecurityException -> 0x00d4 }
            com.android.dialer.common.LogUtil.m9i(r3, r0, r4)     // Catch:{ IllegalStateException | SecurityException -> 0x00d4 }
            android.content.Context r0 = r12.appContext     // Catch:{ IllegalStateException | SecurityException -> 0x00d4 }
            r0.startService(r2)     // Catch:{ IllegalStateException | SecurityException -> 0x00d4 }
            goto L_0x008b
        L_0x00d4:
            r12 = move-exception
            com.android.dialer.contacts.hiresphoto.HighResolutionPhotoRequesterImpl$RequestFailedException r13 = new com.android.dialer.contacts.hiresphoto.HighResolutionPhotoRequesterImpl$RequestFailedException
            java.lang.String r0 = "unable to start sync adapter"
            r13.<init>(r0, r12)
            throw r13
        L_0x00dd:
            return
        L_0x00de:
            com.android.dialer.contacts.hiresphoto.HighResolutionPhotoRequesterImpl$RequestFailedException r12 = new com.android.dialer.contacts.hiresphoto.HighResolutionPhotoRequesterImpl$RequestFailedException     // Catch:{ all -> 0x00e6 }
            java.lang.String r13 = "null cursor from raw contact IDs"
            r12.<init>(r13)     // Catch:{ all -> 0x00e6 }
            throw r12     // Catch:{ all -> 0x00e6 }
        L_0x00e6:
            r12 = move-exception
            throw r12     // Catch:{ all -> 0x00e8 }
        L_0x00e8:
            r13 = move-exception
            if (r0 == 0) goto L_0x00ee
            $closeResource(r12, r0)
        L_0x00ee:
            throw r13
        L_0x00ef:
            com.android.dialer.contacts.hiresphoto.HighResolutionPhotoRequesterImpl$RequestFailedException r12 = new com.android.dialer.contacts.hiresphoto.HighResolutionPhotoRequesterImpl$RequestFailedException     // Catch:{ all -> 0x00f7 }
            java.lang.String r0 = "cannot get contact ID"
            r12.<init>(r0)     // Catch:{ all -> 0x00f7 }
            throw r12     // Catch:{ all -> 0x00f7 }
        L_0x00f7:
            r12 = move-exception
            throw r12     // Catch:{ all -> 0x00f9 }
        L_0x00f9:
            r0 = move-exception
            if (r13 == 0) goto L_0x00ff
            $closeResource(r12, r13)
        L_0x00ff:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.contacts.hiresphoto.HighResolutionPhotoRequesterImpl.requestInternal(android.net.Uri):void");
    }

    public /* synthetic */ Void lambda$request$0$HighResolutionPhotoRequesterImpl(Uri uri) throws Exception {
        try {
            requestInternal(uri);
            return null;
        } catch (RequestFailedException e) {
            LogUtil.m7e("HighResolutionPhotoRequesterImpl.request", "request failed", (Throwable) e);
            return null;
        }
    }

    public ListenableFuture<Void> request(Uri uri) {
        return this.backgroundExecutor.submit(new Callable(uri) {
            private final /* synthetic */ Uri f$1;

            {
                this.f$1 = r2;
            }

            public final Object call() {
                return HighResolutionPhotoRequesterImpl.this.lambda$request$0$HighResolutionPhotoRequesterImpl(this.f$1);
            }
        });
    }
}
