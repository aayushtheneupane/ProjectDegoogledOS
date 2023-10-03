package com.android.contacts.vcard;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

public class VCardService extends Service {
    private MyBinder mBinder;
    private String mCallingActivity;
    private int mCurrentJobId = 1;
    private final ExecutorService mExecutorService = Executors.newSingleThreadExecutor();
    private final List<CustomMediaScannerConnectionClient> mRemainingScannerConnections = new ArrayList();
    private final Set<String> mReservedDestination = new HashSet();
    private final SparseArray<ProcessorBase> mRunningJobMap = new SparseArray<>();

    private class CustomMediaScannerConnectionClient implements MediaScannerConnection.MediaScannerConnectionClient {
        final MediaScannerConnection mConnection;
        final String mPath;

        public CustomMediaScannerConnectionClient(String str) {
            this.mConnection = new MediaScannerConnection(VCardService.this, this);
            this.mPath = str;
        }

        public void start() {
            this.mConnection.connect();
        }

        public void onMediaScannerConnected() {
            this.mConnection.scanFile(this.mPath, (String) null);
        }

        public void onScanCompleted(String str, Uri uri) {
            this.mConnection.disconnect();
            VCardService.this.removeConnectionClient(this);
        }
    }

    public class MyBinder extends Binder {
        public MyBinder() {
        }

        public VCardService getService() {
            return VCardService.this;
        }
    }

    public void onCreate() {
        super.onCreate();
        this.mBinder = new MyBinder();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent == null || intent.getExtras() == null) {
            this.mCallingActivity = null;
            return 1;
        }
        this.mCallingActivity = intent.getExtras().getString("CALLING_ACTIVITY");
        return 1;
    }

    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }

    public void onDestroy() {
        cancelAllRequestsAndShutdown();
        clearCache();
        stopForeground(false);
        super.onDestroy();
    }

    public synchronized void handleImportRequest(List<ImportRequest> list, VCardImportExportListener vCardImportExportListener) {
        Notification onImportProcessed;
        int size = list.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                break;
            }
            ImportRequest importRequest = list.get(i);
            if (tryExecute(new ImportProcessor(this, vCardImportExportListener, importRequest, this.mCurrentJobId))) {
                if (!(vCardImportExportListener == null || (onImportProcessed = vCardImportExportListener.onImportProcessed(importRequest, this.mCurrentJobId, i)) == null)) {
                    startForeground(this.mCurrentJobId, onImportProcessed);
                }
                this.mCurrentJobId++;
                i++;
            } else if (vCardImportExportListener != null) {
                vCardImportExportListener.onImportFailed(importRequest);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0035, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0051, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void handleExportRequest(com.android.contacts.vcard.ExportRequest r6, com.android.contacts.vcard.VCardImportExportListener r7) {
        /*
            r5 = this;
            monitor-enter(r5)
            com.android.contacts.vcard.ExportProcessor r0 = new com.android.contacts.vcard.ExportProcessor     // Catch:{ all -> 0x0052 }
            int r1 = r5.mCurrentJobId     // Catch:{ all -> 0x0052 }
            java.lang.String r2 = r5.mCallingActivity     // Catch:{ all -> 0x0052 }
            r0.<init>(r5, r6, r1, r2)     // Catch:{ all -> 0x0052 }
            boolean r0 = r5.tryExecute(r0)     // Catch:{ all -> 0x0052 }
            if (r0 == 0) goto L_0x004b
            android.net.Uri r0 = r6.destUri     // Catch:{ all -> 0x0052 }
            java.lang.String r0 = r0.getEncodedPath()     // Catch:{ all -> 0x0052 }
            java.util.Set<java.lang.String> r1 = r5.mReservedDestination     // Catch:{ all -> 0x0052 }
            boolean r1 = r1.add(r0)     // Catch:{ all -> 0x0052 }
            r2 = 1
            if (r1 != 0) goto L_0x0036
            java.lang.String r1 = "VCardService"
            java.lang.String r3 = "The path %s is already reserved. Reject export request"
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x0052 }
            r4 = 0
            r2[r4] = r0     // Catch:{ all -> 0x0052 }
            java.lang.String r0 = java.lang.String.format(r3, r2)     // Catch:{ all -> 0x0052 }
            android.util.Log.w(r1, r0)     // Catch:{ all -> 0x0052 }
            if (r7 == 0) goto L_0x0034
            r7.onExportFailed(r6)     // Catch:{ all -> 0x0052 }
        L_0x0034:
            monitor-exit(r5)
            return
        L_0x0036:
            if (r7 == 0) goto L_0x0045
            int r0 = r5.mCurrentJobId     // Catch:{ all -> 0x0052 }
            android.app.Notification r6 = r7.onExportProcessed(r6, r0)     // Catch:{ all -> 0x0052 }
            if (r6 == 0) goto L_0x0045
            int r7 = r5.mCurrentJobId     // Catch:{ all -> 0x0052 }
            r5.startForeground(r7, r6)     // Catch:{ all -> 0x0052 }
        L_0x0045:
            int r6 = r5.mCurrentJobId     // Catch:{ all -> 0x0052 }
            int r6 = r6 + r2
            r5.mCurrentJobId = r6     // Catch:{ all -> 0x0052 }
            goto L_0x0050
        L_0x004b:
            if (r7 == 0) goto L_0x0050
            r7.onExportFailed(r6)     // Catch:{ all -> 0x0052 }
        L_0x0050:
            monitor-exit(r5)
            return
        L_0x0052:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.vcard.VCardService.handleExportRequest(com.android.contacts.vcard.ExportRequest, com.android.contacts.vcard.VCardImportExportListener):void");
    }

    private synchronized boolean tryExecute(ProcessorBase processorBase) {
        try {
            this.mExecutorService.execute(processorBase);
            this.mRunningJobMap.put(this.mCurrentJobId, processorBase);
        } catch (RejectedExecutionException e) {
            Log.w("VCardService", "Failed to excetute a job.", e);
            return false;
        }
        return true;
    }

    public synchronized void handleCancelRequest(CancelRequest cancelRequest, VCardImportExportListener vCardImportExportListener) {
        int i = cancelRequest.jobId;
        ProcessorBase processorBase = this.mRunningJobMap.get(i);
        this.mRunningJobMap.remove(i);
        if (processorBase != null) {
            processorBase.cancel(true);
            int type = processorBase.getType();
            if (vCardImportExportListener != null) {
                vCardImportExportListener.onCancelRequest(cancelRequest, type);
            }
            if (type == 2) {
                String encodedPath = ((ExportProcessor) processorBase).getRequest().destUri.getEncodedPath();
                Log.i("VCardService", String.format("Cancel reservation for the path %s if appropriate", new Object[]{encodedPath}));
                if (!this.mReservedDestination.remove(encodedPath)) {
                    Log.w("VCardService", "Not reserved.");
                }
            }
        } else {
            Log.w("VCardService", String.format("Tried to remove unknown job (id: %d)", new Object[]{Integer.valueOf(i)}));
        }
        stopServiceIfAppropriate();
    }

    private synchronized void stopServiceIfAppropriate() {
        if (this.mRunningJobMap.size() > 0) {
            int size = this.mRunningJobMap.size();
            int[] iArr = new int[size];
            for (int i = 0; i < size; i++) {
                int keyAt = this.mRunningJobMap.keyAt(i);
                if (!this.mRunningJobMap.valueAt(i).isDone()) {
                    Log.i("VCardService", String.format("Found unfinished job (id: %d)", new Object[]{Integer.valueOf(keyAt)}));
                    for (int i2 = 0; i2 < i; i2++) {
                        this.mRunningJobMap.remove(iArr[i2]);
                    }
                    return;
                }
                iArr[i] = keyAt;
            }
            this.mRunningJobMap.clear();
        }
        if (!this.mRemainingScannerConnections.isEmpty()) {
            Log.i("VCardService", "MediaScanner update is in progress.");
            return;
        }
        Log.i("VCardService", "No unfinished job. Stop this service.");
        this.mExecutorService.shutdown();
        stopSelf();
    }

    /* access modifiers changed from: package-private */
    public synchronized void updateMediaScanner(String str) {
        if (this.mExecutorService.isShutdown()) {
            Log.w("VCardService", "MediaScanner update is requested after executor's being shut down. Ignoring the update request");
            return;
        }
        CustomMediaScannerConnectionClient customMediaScannerConnectionClient = new CustomMediaScannerConnectionClient(str);
        this.mRemainingScannerConnections.add(customMediaScannerConnectionClient);
        customMediaScannerConnectionClient.start();
    }

    /* access modifiers changed from: private */
    public synchronized void removeConnectionClient(CustomMediaScannerConnectionClient customMediaScannerConnectionClient) {
        this.mRemainingScannerConnections.remove(customMediaScannerConnectionClient);
        stopServiceIfAppropriate();
    }

    /* access modifiers changed from: package-private */
    public synchronized void handleFinishImportNotification(int i, boolean z) {
        this.mRunningJobMap.remove(i);
        stopServiceIfAppropriate();
    }

    /* access modifiers changed from: package-private */
    public synchronized void handleFinishExportNotification(int i, boolean z) {
        ProcessorBase processorBase = this.mRunningJobMap.get(i);
        this.mRunningJobMap.remove(i);
        if (processorBase == null) {
            Log.w("VCardService", String.format("Tried to remove unknown job (id: %d)", new Object[]{Integer.valueOf(i)}));
        } else if (!(processorBase instanceof ExportProcessor)) {
            Log.w("VCardService", String.format("Removed job (id: %s) isn't ExportProcessor", new Object[]{Integer.valueOf(i)}));
        } else {
            this.mReservedDestination.remove(((ExportProcessor) processorBase).getRequest().destUri.getEncodedPath());
        }
        stopServiceIfAppropriate();
    }

    private synchronized void cancelAllRequestsAndShutdown() {
        for (int i = 0; i < this.mRunningJobMap.size(); i++) {
            this.mRunningJobMap.valueAt(i).cancel(true);
        }
        this.mRunningJobMap.clear();
        this.mExecutorService.shutdown();
    }

    private void clearCache() {
        for (String str : fileList()) {
            if (str.startsWith("import_tmp_")) {
                Log.i("VCardService", "Remove a temporary file: " + str);
                deleteFile(str);
            }
        }
    }
}
