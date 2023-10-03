package com.android.contacts.vcard;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.android.contacts.R;
import com.android.contacts.activities.RequestImportVCardPermissionsActivity;
import com.android.contacts.model.AccountTypeManager;
import com.android.contacts.model.account.AccountWithDataSet;
import com.android.contacts.vcard.ImportVCardDialogFragment;
import com.android.contacts.vcard.VCardService;
import com.android.contactsbind.FeedbackHelper;
import com.android.vcard.VCardParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImportVCardActivity extends Activity implements ImportVCardDialogFragment.Listener {
    /* access modifiers changed from: private */
    public AccountWithDataSet mAccount;
    private CancelListener mCancelListener = new CancelListener();
    /* access modifiers changed from: private */
    public ImportRequestConnection mConnection;
    /* access modifiers changed from: private */
    public String mErrorMessage;
    private Handler mHandler = new Handler();
    VCardImportExportListener mListener;
    /* access modifiers changed from: private */
    public ProgressDialog mProgressDialogForCachingVCard;
    /* access modifiers changed from: private */
    public VCardCacheThread mVCardCacheThread;

    private class DialogDisplayer implements Runnable {
        private final int mResId = R.id.dialog_error_with_message;

        public DialogDisplayer(String str) {
            String unused = ImportVCardActivity.this.mErrorMessage = str;
        }

        public void run() {
            if (!ImportVCardActivity.this.isFinishing()) {
                ImportVCardActivity.this.showDialog(this.mResId);
            }
        }
    }

    private class CancelListener implements DialogInterface.OnClickListener, DialogInterface.OnCancelListener {
        private CancelListener() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            ImportVCardActivity.this.finish();
        }

        public void onCancel(DialogInterface dialogInterface) {
            ImportVCardActivity.this.finish();
        }
    }

    private class ImportRequestConnection implements ServiceConnection {
        private VCardService mService;

        private ImportRequestConnection() {
        }

        public void sendImportRequest(List<ImportRequest> list) {
            Log.i("VCardImport", "Send an import request");
            this.mService.handleImportRequest(list, ImportVCardActivity.this.mListener);
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            this.mService = ((VCardService.MyBinder) iBinder).getService();
            Log.i("VCardImport", String.format("Connected to VCardService. Kick a vCard cache thread (uri: %s)", new Object[]{Arrays.toString(ImportVCardActivity.this.mVCardCacheThread.getSourceUris())}));
            ImportVCardActivity.this.mVCardCacheThread.start();
        }

        public void onServiceDisconnected(ComponentName componentName) {
            Log.i("VCardImport", "Disconnected from VCardService");
        }
    }

    private class VCardCacheThread extends Thread implements DialogInterface.OnCancelListener {
        private boolean mCanceled;
        private final String mDisplayName;
        private final byte[] mSource = null;
        private final String[] mSourceDisplayNames;
        private final Uri[] mSourceUris;
        private VCardParser mVCardParser;
        private PowerManager.WakeLock mWakeLock;

        public VCardCacheThread(Uri[] uriArr, String[] strArr) {
            this.mSourceUris = uriArr;
            this.mSourceDisplayNames = strArr;
            this.mWakeLock = ((PowerManager) ImportVCardActivity.this.getSystemService("power")).newWakeLock(536870918, "VCardImport");
            this.mDisplayName = null;
        }

        public void finalize() {
            PowerManager.WakeLock wakeLock = this.mWakeLock;
            if (wakeLock != null && wakeLock.isHeld()) {
                Log.w("VCardImport", "WakeLock is being held.");
                this.mWakeLock.release();
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:41:0x00b2, code lost:
            android.util.Log.i("VCardImport", "vCard cache operation is canceled.");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:42:0x00b5, code lost:
            android.util.Log.i("VCardImport", "Finished caching vCard.");
            r1.mWakeLock.release();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
            r1.this$0.unbindService(com.android.contacts.vcard.ImportVCardActivity.access$300(r1.this$0));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:45:0x00ca, code lost:
            r0 = e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:48:0x00d5, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:0x00d6, code lost:
            com.android.contactsbind.FeedbackHelper.sendFeedback(r1.this$0, "VCardImport", "Failed to cache vcard", r0);
            r1.this$0.showFailureNotification(com.android.contacts.R.string.fail_reason_io_error);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:50:0x00e1, code lost:
            android.util.Log.i("VCardImport", "Finished caching vCard.");
            r1.mWakeLock.release();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:52:?, code lost:
            r1.this$0.unbindService(com.android.contacts.vcard.ImportVCardActivity.access$300(r1.this$0));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:53:0x00f6, code lost:
            r0 = e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:54:0x00f9, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:57:?, code lost:
            com.android.contactsbind.FeedbackHelper.sendFeedback(r1.this$0, "VCardImport", "Failed to cache vcard", r0);
            r1.this$0.showFailureNotification(com.android.contacts.R.string.fail_reason_not_supported);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:58:0x0105, code lost:
            android.util.Log.i("VCardImport", "Finished caching vCard.");
            r1.mWakeLock.release();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:60:?, code lost:
            r1.this$0.unbindService(com.android.contacts.vcard.ImportVCardActivity.access$300(r1.this$0));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:61:0x011a, code lost:
            r0 = e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:82:0x0194, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:84:?, code lost:
            com.android.contactsbind.FeedbackHelper.sendFeedback(r1.this$0, "VCardImport", "OutOfMemoryError occured during caching vCard", r0);
            java.lang.System.gc();
            r1.this$0.runOnUiThread(new com.android.contacts.vcard.ImportVCardActivity.DialogDisplayer(r1.this$0, r1.this$0.getString(com.android.contacts.R.string.fail_reason_low_memory_during_import)));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:85:0x01b4, code lost:
            android.util.Log.i("VCardImport", "Finished caching vCard.");
            r1.mWakeLock.release();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:87:?, code lost:
            r1.this$0.unbindService(com.android.contacts.vcard.ImportVCardActivity.access$300(r1.this$0));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:88:0x01c8, code lost:
            r0 = e;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Removed duplicated region for block: B:82:0x0194 A[ExcHandler: OutOfMemoryError (r0v5 'e' java.lang.OutOfMemoryError A[CUSTOM_DECLARE]), Splitter:B:3:0x001e] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r17 = this;
                r1 = r17
                java.lang.String r2 = "Cannot unbind service connection"
                java.lang.String r3 = "Finished caching vCard."
                java.lang.String r4 = "VCardImport"
                java.lang.String r0 = "vCard cache thread starts running."
                android.util.Log.i(r4, r0)
                com.android.contacts.vcard.ImportVCardActivity r0 = com.android.contacts.vcard.ImportVCardActivity.this
                com.android.contacts.vcard.ImportVCardActivity$ImportRequestConnection r0 = r0.mConnection
                if (r0 == 0) goto L_0x01f9
                android.os.PowerManager$WakeLock r0 = r1.mWakeLock
                r0.acquire()
                r5 = 2131755357(0x7f10015d, float:1.914159E38)
                r6 = 0
                boolean r0 = r1.mCanceled     // Catch:{ OutOfMemoryError -> 0x0194, IOException -> 0x014c }
                java.lang.String r7 = "vCard cache operation is canceled."
                r8 = 1
                if (r0 != r8) goto L_0x0056
                android.util.Log.i(r4, r7)     // Catch:{ OutOfMemoryError -> 0x0194, IOException -> 0x014c }
                android.util.Log.i(r4, r3)
                android.os.PowerManager$WakeLock r0 = r1.mWakeLock
                r0.release()
                com.android.contacts.vcard.ImportVCardActivity r0 = com.android.contacts.vcard.ImportVCardActivity.this     // Catch:{ IllegalArgumentException -> 0x003c }
                com.android.contacts.vcard.ImportVCardActivity r3 = com.android.contacts.vcard.ImportVCardActivity.this     // Catch:{ IllegalArgumentException -> 0x003c }
                com.android.contacts.vcard.ImportVCardActivity$ImportRequestConnection r3 = r3.mConnection     // Catch:{ IllegalArgumentException -> 0x003c }
                r0.unbindService(r3)     // Catch:{ IllegalArgumentException -> 0x003c }
                goto L_0x0042
            L_0x003c:
                r0 = move-exception
            L_0x003d:
                com.android.contacts.vcard.ImportVCardActivity r3 = com.android.contacts.vcard.ImportVCardActivity.this
                com.android.contactsbind.FeedbackHelper.sendFeedback(r3, r4, r2, r0)
            L_0x0042:
                com.android.contacts.vcard.ImportVCardActivity r0 = com.android.contacts.vcard.ImportVCardActivity.this
                android.app.ProgressDialog r0 = r0.mProgressDialogForCachingVCard
                r0.dismiss()
                com.android.contacts.vcard.ImportVCardActivity r0 = com.android.contacts.vcard.ImportVCardActivity.this
                android.app.ProgressDialog unused = r0.mProgressDialogForCachingVCard = r6
                com.android.contacts.vcard.ImportVCardActivity r0 = com.android.contacts.vcard.ImportVCardActivity.this
                r0.finish()
                return
            L_0x0056:
                java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ OutOfMemoryError -> 0x0194, IOException -> 0x014c }
                r0.<init>()     // Catch:{ OutOfMemoryError -> 0x0194, IOException -> 0x014c }
                byte[] r8 = r1.mSource     // Catch:{ OutOfMemoryError -> 0x0194, IOException -> 0x014c }
                r9 = 2131755360(0x7f100160, float:1.9141597E38)
                java.lang.String r10 = "Failed to cache vcard"
                if (r8 == 0) goto L_0x0092
                byte[] r7 = r1.mSource     // Catch:{ VCardException -> 0x0071 }
                java.lang.String r8 = r1.mDisplayName     // Catch:{ VCardException -> 0x0071 }
                com.android.contacts.vcard.ImportRequest r7 = r1.constructImportRequest(r7, r6, r8)     // Catch:{ VCardException -> 0x0071 }
                r0.add(r7)     // Catch:{ VCardException -> 0x0071 }
                goto L_0x011d
            L_0x0071:
                r0 = move-exception
                com.android.contacts.vcard.ImportVCardActivity r7 = com.android.contacts.vcard.ImportVCardActivity.this     // Catch:{ OutOfMemoryError -> 0x0194, IOException -> 0x014c }
                com.android.contactsbind.FeedbackHelper.sendFeedback(r7, r4, r10, r0)     // Catch:{ OutOfMemoryError -> 0x0194, IOException -> 0x014c }
                com.android.contacts.vcard.ImportVCardActivity r0 = com.android.contacts.vcard.ImportVCardActivity.this     // Catch:{ OutOfMemoryError -> 0x0194, IOException -> 0x014c }
                r0.showFailureNotification(r9)     // Catch:{ OutOfMemoryError -> 0x0194, IOException -> 0x014c }
                android.util.Log.i(r4, r3)
                android.os.PowerManager$WakeLock r0 = r1.mWakeLock
                r0.release()
                com.android.contacts.vcard.ImportVCardActivity r0 = com.android.contacts.vcard.ImportVCardActivity.this     // Catch:{ IllegalArgumentException -> 0x0090 }
                com.android.contacts.vcard.ImportVCardActivity r3 = com.android.contacts.vcard.ImportVCardActivity.this     // Catch:{ IllegalArgumentException -> 0x0090 }
                com.android.contacts.vcard.ImportVCardActivity$ImportRequestConnection r3 = r3.mConnection     // Catch:{ IllegalArgumentException -> 0x0090 }
                r0.unbindService(r3)     // Catch:{ IllegalArgumentException -> 0x0090 }
                goto L_0x0042
            L_0x0090:
                r0 = move-exception
                goto L_0x003d
            L_0x0092:
                android.net.Uri[] r8 = r1.mSourceUris     // Catch:{ OutOfMemoryError -> 0x0194, IOException -> 0x014c }
                int r11 = r8.length     // Catch:{ OutOfMemoryError -> 0x0194, IOException -> 0x014c }
                r12 = 0
                r13 = 0
            L_0x0097:
                if (r12 >= r11) goto L_0x011d
                r14 = r8[r12]     // Catch:{ OutOfMemoryError -> 0x0194, IOException -> 0x014c }
                boolean r15 = r1.mCanceled     // Catch:{ OutOfMemoryError -> 0x0194, IOException -> 0x014c }
                if (r15 == 0) goto L_0x00a4
                android.util.Log.i(r4, r7)     // Catch:{ OutOfMemoryError -> 0x0194, IOException -> 0x014c }
                goto L_0x011d
            L_0x00a4:
                java.lang.String[] r15 = r1.mSourceDisplayNames     // Catch:{ OutOfMemoryError -> 0x0194, IOException -> 0x014c }
                int r16 = r13 + 1
                r13 = r15[r13]     // Catch:{ OutOfMemoryError -> 0x0194, IOException -> 0x014c }
                com.android.contacts.vcard.ImportRequest r13 = r1.constructImportRequest(r6, r14, r13)     // Catch:{ VCardException -> 0x00f9, IOException -> 0x00d5, OutOfMemoryError -> 0x0194 }
                boolean r14 = r1.mCanceled     // Catch:{ OutOfMemoryError -> 0x0194, IOException -> 0x014c }
                if (r14 == 0) goto L_0x00cd
                android.util.Log.i(r4, r7)     // Catch:{ OutOfMemoryError -> 0x0194, IOException -> 0x014c }
                android.util.Log.i(r4, r3)
                android.os.PowerManager$WakeLock r0 = r1.mWakeLock
                r0.release()
                com.android.contacts.vcard.ImportVCardActivity r0 = com.android.contacts.vcard.ImportVCardActivity.this     // Catch:{ IllegalArgumentException -> 0x00ca }
                com.android.contacts.vcard.ImportVCardActivity r3 = com.android.contacts.vcard.ImportVCardActivity.this     // Catch:{ IllegalArgumentException -> 0x00ca }
                com.android.contacts.vcard.ImportVCardActivity$ImportRequestConnection r3 = r3.mConnection     // Catch:{ IllegalArgumentException -> 0x00ca }
                r0.unbindService(r3)     // Catch:{ IllegalArgumentException -> 0x00ca }
                goto L_0x0042
            L_0x00ca:
                r0 = move-exception
                goto L_0x003d
            L_0x00cd:
                r0.add(r13)     // Catch:{ OutOfMemoryError -> 0x0194, IOException -> 0x014c }
                int r12 = r12 + 1
                r13 = r16
                goto L_0x0097
            L_0x00d5:
                r0 = move-exception
                r7 = r0
                com.android.contacts.vcard.ImportVCardActivity r0 = com.android.contacts.vcard.ImportVCardActivity.this     // Catch:{ OutOfMemoryError -> 0x0194, IOException -> 0x014c }
                com.android.contactsbind.FeedbackHelper.sendFeedback(r0, r4, r10, r7)     // Catch:{ OutOfMemoryError -> 0x0194, IOException -> 0x014c }
                com.android.contacts.vcard.ImportVCardActivity r0 = com.android.contacts.vcard.ImportVCardActivity.this     // Catch:{ OutOfMemoryError -> 0x0194, IOException -> 0x014c }
                r0.showFailureNotification(r5)     // Catch:{ OutOfMemoryError -> 0x0194, IOException -> 0x014c }
                android.util.Log.i(r4, r3)
                android.os.PowerManager$WakeLock r0 = r1.mWakeLock
                r0.release()
                com.android.contacts.vcard.ImportVCardActivity r0 = com.android.contacts.vcard.ImportVCardActivity.this     // Catch:{ IllegalArgumentException -> 0x00f6 }
                com.android.contacts.vcard.ImportVCardActivity r3 = com.android.contacts.vcard.ImportVCardActivity.this     // Catch:{ IllegalArgumentException -> 0x00f6 }
                com.android.contacts.vcard.ImportVCardActivity$ImportRequestConnection r3 = r3.mConnection     // Catch:{ IllegalArgumentException -> 0x00f6 }
                r0.unbindService(r3)     // Catch:{ IllegalArgumentException -> 0x00f6 }
                goto L_0x0042
            L_0x00f6:
                r0 = move-exception
                goto L_0x003d
            L_0x00f9:
                r0 = move-exception
                r7 = r0
                com.android.contacts.vcard.ImportVCardActivity r0 = com.android.contacts.vcard.ImportVCardActivity.this     // Catch:{ OutOfMemoryError -> 0x0194, IOException -> 0x014c }
                com.android.contactsbind.FeedbackHelper.sendFeedback(r0, r4, r10, r7)     // Catch:{ OutOfMemoryError -> 0x0194, IOException -> 0x014c }
                com.android.contacts.vcard.ImportVCardActivity r0 = com.android.contacts.vcard.ImportVCardActivity.this     // Catch:{ OutOfMemoryError -> 0x0194, IOException -> 0x014c }
                r0.showFailureNotification(r9)     // Catch:{ OutOfMemoryError -> 0x0194, IOException -> 0x014c }
                android.util.Log.i(r4, r3)
                android.os.PowerManager$WakeLock r0 = r1.mWakeLock
                r0.release()
                com.android.contacts.vcard.ImportVCardActivity r0 = com.android.contacts.vcard.ImportVCardActivity.this     // Catch:{ IllegalArgumentException -> 0x011a }
                com.android.contacts.vcard.ImportVCardActivity r3 = com.android.contacts.vcard.ImportVCardActivity.this     // Catch:{ IllegalArgumentException -> 0x011a }
                com.android.contacts.vcard.ImportVCardActivity$ImportRequestConnection r3 = r3.mConnection     // Catch:{ IllegalArgumentException -> 0x011a }
                r0.unbindService(r3)     // Catch:{ IllegalArgumentException -> 0x011a }
                goto L_0x0042
            L_0x011a:
                r0 = move-exception
                goto L_0x003d
            L_0x011d:
                boolean r7 = r0.isEmpty()     // Catch:{ OutOfMemoryError -> 0x0194, IOException -> 0x014c }
                if (r7 != 0) goto L_0x012d
                com.android.contacts.vcard.ImportVCardActivity r7 = com.android.contacts.vcard.ImportVCardActivity.this     // Catch:{ OutOfMemoryError -> 0x0194, IOException -> 0x014c }
                com.android.contacts.vcard.ImportVCardActivity$ImportRequestConnection r7 = r7.mConnection     // Catch:{ OutOfMemoryError -> 0x0194, IOException -> 0x014c }
                r7.sendImportRequest(r0)     // Catch:{ OutOfMemoryError -> 0x0194, IOException -> 0x014c }
                goto L_0x0132
            L_0x012d:
                java.lang.String r0 = "Empty import requests. Ignore it."
                android.util.Log.w(r4, r0)     // Catch:{ OutOfMemoryError -> 0x0194, IOException -> 0x014c }
            L_0x0132:
                android.util.Log.i(r4, r3)
                android.os.PowerManager$WakeLock r0 = r1.mWakeLock
                r0.release()
                com.android.contacts.vcard.ImportVCardActivity r0 = com.android.contacts.vcard.ImportVCardActivity.this     // Catch:{ IllegalArgumentException -> 0x0146 }
                com.android.contacts.vcard.ImportVCardActivity r3 = com.android.contacts.vcard.ImportVCardActivity.this     // Catch:{ IllegalArgumentException -> 0x0146 }
                com.android.contacts.vcard.ImportVCardActivity$ImportRequestConnection r3 = r3.mConnection     // Catch:{ IllegalArgumentException -> 0x0146 }
                r0.unbindService(r3)     // Catch:{ IllegalArgumentException -> 0x0146 }
                goto L_0x0180
            L_0x0146:
                r0 = move-exception
                goto L_0x017b
            L_0x0148:
                r0 = move-exception
                r5 = r0
                goto L_0x01cb
            L_0x014c:
                r0 = move-exception
                com.android.contacts.vcard.ImportVCardActivity r7 = com.android.contacts.vcard.ImportVCardActivity.this     // Catch:{ all -> 0x0148 }
                java.lang.String r8 = "IOException during caching vCard"
                com.android.contactsbind.FeedbackHelper.sendFeedback(r7, r4, r8, r0)     // Catch:{ all -> 0x0148 }
                com.android.contacts.vcard.ImportVCardActivity r0 = com.android.contacts.vcard.ImportVCardActivity.this     // Catch:{ all -> 0x0148 }
                com.android.contacts.vcard.ImportVCardActivity$DialogDisplayer r7 = new com.android.contacts.vcard.ImportVCardActivity$DialogDisplayer     // Catch:{ all -> 0x0148 }
                com.android.contacts.vcard.ImportVCardActivity r8 = com.android.contacts.vcard.ImportVCardActivity.this     // Catch:{ all -> 0x0148 }
                com.android.contacts.vcard.ImportVCardActivity r9 = com.android.contacts.vcard.ImportVCardActivity.this     // Catch:{ all -> 0x0148 }
                java.lang.String r5 = r9.getString(r5)     // Catch:{ all -> 0x0148 }
                r7.<init>(r5)     // Catch:{ all -> 0x0148 }
                r0.runOnUiThread(r7)     // Catch:{ all -> 0x0148 }
                android.util.Log.i(r4, r3)
                android.os.PowerManager$WakeLock r0 = r1.mWakeLock
                r0.release()
                com.android.contacts.vcard.ImportVCardActivity r0 = com.android.contacts.vcard.ImportVCardActivity.this     // Catch:{ IllegalArgumentException -> 0x017a }
                com.android.contacts.vcard.ImportVCardActivity r3 = com.android.contacts.vcard.ImportVCardActivity.this     // Catch:{ IllegalArgumentException -> 0x017a }
                com.android.contacts.vcard.ImportVCardActivity$ImportRequestConnection r3 = r3.mConnection     // Catch:{ IllegalArgumentException -> 0x017a }
                r0.unbindService(r3)     // Catch:{ IllegalArgumentException -> 0x017a }
                goto L_0x0180
            L_0x017a:
                r0 = move-exception
            L_0x017b:
                com.android.contacts.vcard.ImportVCardActivity r3 = com.android.contacts.vcard.ImportVCardActivity.this
                com.android.contactsbind.FeedbackHelper.sendFeedback(r3, r4, r2, r0)
            L_0x0180:
                com.android.contacts.vcard.ImportVCardActivity r0 = com.android.contacts.vcard.ImportVCardActivity.this
                android.app.ProgressDialog r0 = r0.mProgressDialogForCachingVCard
                r0.dismiss()
                com.android.contacts.vcard.ImportVCardActivity r0 = com.android.contacts.vcard.ImportVCardActivity.this
                android.app.ProgressDialog unused = r0.mProgressDialogForCachingVCard = r6
                com.android.contacts.vcard.ImportVCardActivity r0 = com.android.contacts.vcard.ImportVCardActivity.this
                r0.finish()
                goto L_0x01ca
            L_0x0194:
                r0 = move-exception
                com.android.contacts.vcard.ImportVCardActivity r5 = com.android.contacts.vcard.ImportVCardActivity.this     // Catch:{ all -> 0x0148 }
                java.lang.String r7 = "OutOfMemoryError occured during caching vCard"
                com.android.contactsbind.FeedbackHelper.sendFeedback(r5, r4, r7, r0)     // Catch:{ all -> 0x0148 }
                java.lang.System.gc()     // Catch:{ all -> 0x0148 }
                com.android.contacts.vcard.ImportVCardActivity r0 = com.android.contacts.vcard.ImportVCardActivity.this     // Catch:{ all -> 0x0148 }
                com.android.contacts.vcard.ImportVCardActivity$DialogDisplayer r5 = new com.android.contacts.vcard.ImportVCardActivity$DialogDisplayer     // Catch:{ all -> 0x0148 }
                com.android.contacts.vcard.ImportVCardActivity r7 = com.android.contacts.vcard.ImportVCardActivity.this     // Catch:{ all -> 0x0148 }
                com.android.contacts.vcard.ImportVCardActivity r8 = com.android.contacts.vcard.ImportVCardActivity.this     // Catch:{ all -> 0x0148 }
                r9 = 2131755358(0x7f10015e, float:1.9141593E38)
                java.lang.String r8 = r8.getString(r9)     // Catch:{ all -> 0x0148 }
                r5.<init>(r8)     // Catch:{ all -> 0x0148 }
                r0.runOnUiThread(r5)     // Catch:{ all -> 0x0148 }
                android.util.Log.i(r4, r3)
                android.os.PowerManager$WakeLock r0 = r1.mWakeLock
                r0.release()
                com.android.contacts.vcard.ImportVCardActivity r0 = com.android.contacts.vcard.ImportVCardActivity.this     // Catch:{ IllegalArgumentException -> 0x01c8 }
                com.android.contacts.vcard.ImportVCardActivity r3 = com.android.contacts.vcard.ImportVCardActivity.this     // Catch:{ IllegalArgumentException -> 0x01c8 }
                com.android.contacts.vcard.ImportVCardActivity$ImportRequestConnection r3 = r3.mConnection     // Catch:{ IllegalArgumentException -> 0x01c8 }
                r0.unbindService(r3)     // Catch:{ IllegalArgumentException -> 0x01c8 }
                goto L_0x0180
            L_0x01c8:
                r0 = move-exception
                goto L_0x017b
            L_0x01ca:
                return
            L_0x01cb:
                android.util.Log.i(r4, r3)
                android.os.PowerManager$WakeLock r0 = r1.mWakeLock
                r0.release()
                com.android.contacts.vcard.ImportVCardActivity r0 = com.android.contacts.vcard.ImportVCardActivity.this     // Catch:{ IllegalArgumentException -> 0x01df }
                com.android.contacts.vcard.ImportVCardActivity r3 = com.android.contacts.vcard.ImportVCardActivity.this     // Catch:{ IllegalArgumentException -> 0x01df }
                com.android.contacts.vcard.ImportVCardActivity$ImportRequestConnection r3 = r3.mConnection     // Catch:{ IllegalArgumentException -> 0x01df }
                r0.unbindService(r3)     // Catch:{ IllegalArgumentException -> 0x01df }
                goto L_0x01e5
            L_0x01df:
                r0 = move-exception
                com.android.contacts.vcard.ImportVCardActivity r3 = com.android.contacts.vcard.ImportVCardActivity.this
                com.android.contactsbind.FeedbackHelper.sendFeedback(r3, r4, r2, r0)
            L_0x01e5:
                com.android.contacts.vcard.ImportVCardActivity r0 = com.android.contacts.vcard.ImportVCardActivity.this
                android.app.ProgressDialog r0 = r0.mProgressDialogForCachingVCard
                r0.dismiss()
                com.android.contacts.vcard.ImportVCardActivity r0 = com.android.contacts.vcard.ImportVCardActivity.this
                android.app.ProgressDialog unused = r0.mProgressDialogForCachingVCard = r6
                com.android.contacts.vcard.ImportVCardActivity r0 = com.android.contacts.vcard.ImportVCardActivity.this
                r0.finish()
                throw r5
            L_0x01f9:
                java.lang.NullPointerException r0 = new java.lang.NullPointerException
                java.lang.String r2 = "vCard cache thread must be launched after a service connection is established"
                r0.<init>(r2)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.vcard.ImportVCardActivity.VCardCacheThread.run():void");
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: com.android.vcard.VCardEntryCounter} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: com.android.vcard.VCardSourceDetector} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v20, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v22, resolved type: com.android.vcard.VCardSourceDetector} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v23, resolved type: com.android.vcard.VCardEntryCounter} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v24, resolved type: com.android.vcard.VCardSourceDetector} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v25, resolved type: com.android.vcard.VCardEntryCounter} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v26, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v27, resolved type: com.android.vcard.VCardSourceDetector} */
        /* JADX WARNING: type inference failed for: r0v11, types: [com.android.vcard.VCardEntryCounter, com.android.vcard.VCardInterpreter] */
        /* JADX WARNING: type inference failed for: r5v2, types: [com.android.vcard.VCardEntryCounter, com.android.vcard.VCardInterpreter] */
        /* JADX WARNING: Can't wrap try/catch for region: R(5:61|60|62|63|64) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:62:0x0098 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:68:0x00a5 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* JADX WARNING: Removed duplicated region for block: B:36:0x0057 A[SYNTHETIC, Splitter:B:36:0x0057] */
        /* JADX WARNING: Removed duplicated region for block: B:38:0x005d A[Catch:{ all -> 0x0053 }] */
        /* JADX WARNING: Removed duplicated region for block: B:47:0x0084 A[SYNTHETIC, Splitter:B:47:0x0084] */
        /* JADX WARNING: Removed duplicated region for block: B:52:0x008a  */
        /* JADX WARNING: Removed duplicated region for block: B:66:0x00a2 A[SYNTHETIC, Splitter:B:66:0x00a2] */
        /* JADX WARNING: Unknown variable types count: 2 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private com.android.contacts.vcard.ImportRequest constructImportRequest(byte[] r13, android.net.Uri r14, java.lang.String r15) throws java.io.IOException, com.android.vcard.exception.VCardException {
            /*
                r12 = this;
                com.android.contacts.vcard.ImportVCardActivity r0 = com.android.contacts.vcard.ImportVCardActivity.this
                android.content.ContentResolver r0 = r0.getContentResolver()
                r1 = 0
                r2 = 0
                r3 = 1
                if (r13 == 0) goto L_0x0011
                java.io.ByteArrayInputStream r4 = new java.io.ByteArrayInputStream     // Catch:{ VCardNestedException -> 0x00a9 }
                r4.<init>(r13)     // Catch:{ VCardNestedException -> 0x00a9 }
                goto L_0x0015
            L_0x0011:
                java.io.InputStream r4 = r0.openInputStream(r14)     // Catch:{ VCardNestedException -> 0x00a9 }
            L_0x0015:
                com.android.vcard.VCardParser_V21 r5 = new com.android.vcard.VCardParser_V21     // Catch:{ VCardNestedException -> 0x00a9 }
                r5.<init>()     // Catch:{ VCardNestedException -> 0x00a9 }
                r12.mVCardParser = r5     // Catch:{ VCardNestedException -> 0x00a9 }
                com.android.vcard.VCardEntryCounter r5 = new com.android.vcard.VCardEntryCounter     // Catch:{ VCardVersionException -> 0x004e, all -> 0x004b }
                r5.<init>()     // Catch:{ VCardVersionException -> 0x004e, all -> 0x004b }
                com.android.vcard.VCardSourceDetector r6 = new com.android.vcard.VCardSourceDetector     // Catch:{ VCardVersionException -> 0x0048, all -> 0x0043 }
                r6.<init>()     // Catch:{ VCardVersionException -> 0x0048, all -> 0x0043 }
                com.android.vcard.VCardParser r2 = r12.mVCardParser     // Catch:{ VCardVersionException -> 0x0049, all -> 0x0041 }
                r2.addInterpreter(r5)     // Catch:{ VCardVersionException -> 0x0049, all -> 0x0041 }
                com.android.vcard.VCardParser r2 = r12.mVCardParser     // Catch:{ VCardVersionException -> 0x0049, all -> 0x0041 }
                r2.addInterpreter(r6)     // Catch:{ VCardVersionException -> 0x0049, all -> 0x0041 }
                com.android.vcard.VCardParser r2 = r12.mVCardParser     // Catch:{ VCardVersionException -> 0x0049, all -> 0x0041 }
                r2.parse(r4)     // Catch:{ VCardVersionException -> 0x0049, all -> 0x0041 }
                if (r4 == 0) goto L_0x003e
                r4.close()     // Catch:{ IOException -> 0x003e, VCardNestedException -> 0x003b }
                goto L_0x003e
            L_0x003b:
                r0 = r5
                goto L_0x00a7
            L_0x003e:
                r0 = r5
                r2 = r6
                goto L_0x0088
            L_0x0041:
                r0 = move-exception
                goto L_0x0045
            L_0x0043:
                r0 = move-exception
                r6 = r2
            L_0x0045:
                r2 = r5
                goto L_0x00a0
            L_0x0048:
                r6 = r2
            L_0x0049:
                r2 = r5
                goto L_0x004f
            L_0x004b:
                r0 = move-exception
                r6 = r2
                goto L_0x00a0
            L_0x004e:
                r6 = r2
            L_0x004f:
                r4.close()     // Catch:{ IOException -> 0x0055 }
                goto L_0x0055
            L_0x0053:
                r0 = move-exception
                goto L_0x00a0
            L_0x0055:
                if (r13 == 0) goto L_0x005d
                java.io.ByteArrayInputStream r0 = new java.io.ByteArrayInputStream     // Catch:{ all -> 0x0053 }
                r0.<init>(r13)     // Catch:{ all -> 0x0053 }
                goto L_0x0061
            L_0x005d:
                java.io.InputStream r0 = r0.openInputStream(r14)     // Catch:{ all -> 0x0053 }
            L_0x0061:
                r4 = r0
                com.android.vcard.VCardParser_V30 r0 = new com.android.vcard.VCardParser_V30     // Catch:{ all -> 0x0053 }
                r0.<init>()     // Catch:{ all -> 0x0053 }
                r12.mVCardParser = r0     // Catch:{ all -> 0x0053 }
                com.android.vcard.VCardEntryCounter r0 = new com.android.vcard.VCardEntryCounter     // Catch:{ VCardVersionException -> 0x0098 }
                r0.<init>()     // Catch:{ VCardVersionException -> 0x0098 }
                com.android.vcard.VCardSourceDetector r2 = new com.android.vcard.VCardSourceDetector     // Catch:{ VCardVersionException -> 0x0097, all -> 0x0093 }
                r2.<init>()     // Catch:{ VCardVersionException -> 0x0097, all -> 0x0093 }
                com.android.vcard.VCardParser r1 = r12.mVCardParser     // Catch:{ VCardVersionException -> 0x0091, all -> 0x008e }
                r1.addInterpreter(r0)     // Catch:{ VCardVersionException -> 0x0091, all -> 0x008e }
                com.android.vcard.VCardParser r1 = r12.mVCardParser     // Catch:{ VCardVersionException -> 0x0091, all -> 0x008e }
                r1.addInterpreter(r2)     // Catch:{ VCardVersionException -> 0x0091, all -> 0x008e }
                com.android.vcard.VCardParser r1 = r12.mVCardParser     // Catch:{ VCardVersionException -> 0x0091, all -> 0x008e }
                r1.parse(r4)     // Catch:{ VCardVersionException -> 0x0091, all -> 0x008e }
                if (r4 == 0) goto L_0x0087
                r4.close()     // Catch:{ IOException -> 0x0087, VCardNestedException -> 0x00aa }
            L_0x0087:
                r1 = 1
            L_0x0088:
                if (r1 == 0) goto L_0x008c
                r1 = 2
                r3 = 2
            L_0x008c:
                r10 = r3
                goto L_0x00b2
            L_0x008e:
                r1 = move-exception
                r6 = r2
                goto L_0x0094
            L_0x0091:
                r6 = r2
                goto L_0x0097
            L_0x0093:
                r1 = move-exception
            L_0x0094:
                r2 = r0
                r0 = r1
                goto L_0x00a0
            L_0x0097:
                r2 = r0
            L_0x0098:
                com.android.vcard.exception.VCardException r0 = new com.android.vcard.exception.VCardException     // Catch:{ all -> 0x0053 }
                java.lang.String r1 = "vCard with unspported version."
                r0.<init>(r1)     // Catch:{ all -> 0x0053 }
                throw r0     // Catch:{ all -> 0x0053 }
            L_0x00a0:
                if (r4 == 0) goto L_0x00a5
                r4.close()     // Catch:{ IOException -> 0x00a5 }
            L_0x00a5:
                throw r0     // Catch:{ VCardNestedException -> 0x00a6 }
            L_0x00a6:
                r0 = r2
            L_0x00a7:
                r2 = r6
                goto L_0x00aa
            L_0x00a9:
                r0 = r2
            L_0x00aa:
                java.lang.String r1 = "VCardImport"
                java.lang.String r4 = "Nested Exception is found (it may be false-positive)."
                android.util.Log.w(r1, r4)
                r10 = 1
            L_0x00b2:
                com.android.contacts.vcard.ImportRequest r1 = new com.android.contacts.vcard.ImportRequest
                com.android.contacts.vcard.ImportVCardActivity r3 = com.android.contacts.vcard.ImportVCardActivity.this
                com.android.contacts.model.account.AccountWithDataSet r4 = r3.mAccount
                int r8 = r2.getEstimatedType()
                java.lang.String r9 = r2.getEstimatedCharset()
                int r11 = r0.getCount()
                r3 = r1
                r5 = r13
                r6 = r14
                r7 = r15
                r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.vcard.ImportVCardActivity.VCardCacheThread.constructImportRequest(byte[], android.net.Uri, java.lang.String):com.android.contacts.vcard.ImportRequest");
        }

        public Uri[] getSourceUris() {
            return this.mSourceUris;
        }

        public void cancel() {
            this.mCanceled = true;
            VCardParser vCardParser = this.mVCardParser;
            if (vCardParser != null) {
                vCardParser.cancel();
            }
        }

        public void onCancel(DialogInterface dialogInterface) {
            Log.i("VCardImport", "Cancel request has come. Abort caching vCard.");
            cancel();
        }
    }

    private void importVCard(Uri uri, String str) {
        importVCard(new Uri[]{uri}, new String[]{str});
    }

    private void importVCard(final Uri[] uriArr, final String[] strArr) {
        runOnUiThread(new Runnable() {
            public void run() {
                if (!ImportVCardActivity.this.isFinishing()) {
                    ImportVCardActivity importVCardActivity = ImportVCardActivity.this;
                    VCardCacheThread unused = importVCardActivity.mVCardCacheThread = new VCardCacheThread(uriArr, strArr);
                    ImportVCardActivity importVCardActivity2 = ImportVCardActivity.this;
                    importVCardActivity2.mListener = new NotificationImportExportListener(importVCardActivity2);
                    ImportVCardActivity.this.showDialog(R.id.dialog_cache_vcard);
                }
            }
        });
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0066  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getDisplayName(android.net.Uri r9) {
        /*
            r8 = this;
            java.lang.String r0 = "_display_name"
            r1 = 0
            if (r9 != 0) goto L_0x0006
            return r1
        L_0x0006:
            android.content.ContentResolver r2 = r8.getContentResolver()
            java.lang.String[] r4 = new java.lang.String[]{r0}     // Catch:{ all -> 0x0062 }
            r5 = 0
            r6 = 0
            r7 = 0
            r3 = r9
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7)     // Catch:{ all -> 0x0062 }
            if (r2 == 0) goto L_0x0052
            int r3 = r2.getCount()     // Catch:{ all -> 0x0050 }
            if (r3 <= 0) goto L_0x0052
            boolean r3 = r2.moveToFirst()     // Catch:{ all -> 0x0050 }
            if (r3 == 0) goto L_0x0052
            int r3 = r2.getCount()     // Catch:{ all -> 0x0050 }
            r4 = 1
            if (r3 <= r4) goto L_0x0045
            java.lang.String r3 = "VCardImport"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0050 }
            r4.<init>()     // Catch:{ all -> 0x0050 }
            java.lang.String r5 = "Unexpected multiple rows: "
            r4.append(r5)     // Catch:{ all -> 0x0050 }
            int r5 = r2.getCount()     // Catch:{ all -> 0x0050 }
            r4.append(r5)     // Catch:{ all -> 0x0050 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0050 }
            android.util.Log.w(r3, r4)     // Catch:{ all -> 0x0050 }
        L_0x0045:
            int r0 = r2.getColumnIndex(r0)     // Catch:{ all -> 0x0050 }
            if (r0 < 0) goto L_0x0052
            java.lang.String r1 = r2.getString(r0)     // Catch:{ all -> 0x0050 }
            goto L_0x0052
        L_0x0050:
            r9 = move-exception
            goto L_0x0064
        L_0x0052:
            if (r2 == 0) goto L_0x0057
            r2.close()
        L_0x0057:
            boolean r0 = android.text.TextUtils.isEmpty(r1)
            if (r0 == 0) goto L_0x0061
            java.lang.String r1 = r9.getLastPathSegment()
        L_0x0061:
            return r1
        L_0x0062:
            r9 = move-exception
            r2 = r1
        L_0x0064:
            if (r2 == 0) goto L_0x0069
            r2.close()
        L_0x0069:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.vcard.ImportVCardActivity.getDisplayName(android.net.Uri):java.lang.String");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v5, resolved type: java.nio.channels.ReadableByteChannel} */
    /* JADX WARNING: type inference failed for: r8v1, types: [java.nio.channels.ReadableByteChannel] */
    /* JADX WARNING: type inference failed for: r8v2 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x007a A[SYNTHETIC, Splitter:B:27:0x007a] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0083 A[SYNTHETIC, Splitter:B:32:0x0083] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.net.Uri copyTo(android.net.Uri r8, java.lang.String r9) throws java.io.IOException {
        /*
            r7 = this;
            java.lang.String r0 = "Failed to close outputChannel"
            java.lang.String r1 = "Failed to close inputChannel."
            r2 = 2
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r3 = 0
            r2[r3] = r8
            r4 = 1
            r2[r4] = r9
            java.lang.String r4 = "Copy a Uri to app local storage (%s -> %s)"
            java.lang.String r2 = java.lang.String.format(r4, r2)
            java.lang.String r4 = "VCardImport"
            android.util.Log.i(r4, r2)
            android.content.ContentResolver r2 = r7.getContentResolver()
            r5 = 0
            java.io.InputStream r8 = r2.openInputStream(r8)     // Catch:{ all -> 0x0076 }
            java.nio.channels.ReadableByteChannel r8 = java.nio.channels.Channels.newChannel(r8)     // Catch:{ all -> 0x0076 }
            java.io.File r2 = r7.getFileStreamPath(r9)     // Catch:{ all -> 0x0074 }
            java.net.URI r2 = r2.toURI()     // Catch:{ all -> 0x0074 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0074 }
            android.net.Uri r2 = android.net.Uri.parse(r2)     // Catch:{ all -> 0x0074 }
            java.io.FileOutputStream r9 = r7.openFileOutput(r9, r3)     // Catch:{ all -> 0x0074 }
            java.nio.channels.FileChannel r5 = r9.getChannel()     // Catch:{ all -> 0x0074 }
            r9 = 8192(0x2000, float:1.14794E-41)
            java.nio.ByteBuffer r9 = java.nio.ByteBuffer.allocateDirect(r9)     // Catch:{ all -> 0x0074 }
        L_0x0043:
            int r3 = r8.read(r9)     // Catch:{ all -> 0x0074 }
            r6 = -1
            if (r3 == r6) goto L_0x0054
            r9.flip()     // Catch:{ all -> 0x0074 }
            r5.write(r9)     // Catch:{ all -> 0x0074 }
            r9.compact()     // Catch:{ all -> 0x0074 }
            goto L_0x0043
        L_0x0054:
            r9.flip()     // Catch:{ all -> 0x0074 }
        L_0x0057:
            boolean r3 = r9.hasRemaining()     // Catch:{ all -> 0x0074 }
            if (r3 == 0) goto L_0x0061
            r5.write(r9)     // Catch:{ all -> 0x0074 }
            goto L_0x0057
        L_0x0061:
            if (r8 == 0) goto L_0x006a
            r8.close()     // Catch:{ IOException -> 0x0067 }
            goto L_0x006a
        L_0x0067:
            android.util.Log.w(r4, r1)
        L_0x006a:
            if (r5 == 0) goto L_0x0073
            r5.close()     // Catch:{ IOException -> 0x0070 }
            goto L_0x0073
        L_0x0070:
            android.util.Log.w(r4, r0)
        L_0x0073:
            return r2
        L_0x0074:
            r9 = move-exception
            goto L_0x0078
        L_0x0076:
            r9 = move-exception
            r8 = r5
        L_0x0078:
            if (r8 == 0) goto L_0x0081
            r8.close()     // Catch:{ IOException -> 0x007e }
            goto L_0x0081
        L_0x007e:
            android.util.Log.w(r4, r1)
        L_0x0081:
            if (r5 == 0) goto L_0x008a
            r5.close()     // Catch:{ IOException -> 0x0087 }
            goto L_0x008a
        L_0x0087:
            android.util.Log.w(r4, r0)
        L_0x008a:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.vcard.ImportVCardActivity.copyTo(android.net.Uri, java.lang.String):android.net.Uri");
    }

    private String readUriToLocalFile(Uri uri) {
        int i = 0;
        while (true) {
            String str = "import_tmp_" + i + ".vcf";
            if (!getFileStreamPath(str).exists()) {
                try {
                    copyTo(uri, str);
                    if (str != null) {
                        return str;
                    }
                    Log.e("VCardImport", "Cannot load uri to local storage.");
                    showFailureNotification(R.string.fail_reason_io_error);
                    return null;
                } catch (IOException | SecurityException e) {
                    FeedbackHelper.sendFeedback(this, "VCardImport", "Failed to copy vcard to local file", e);
                    showFailureNotification(R.string.fail_reason_io_error);
                    return null;
                }
            } else if (i != Integer.MAX_VALUE) {
                i++;
            } else {
                throw new RuntimeException("Exceeded cache limit");
            }
        }
    }

    private Uri readUriToLocalUri(Uri uri) {
        String readUriToLocalFile = readUriToLocalFile(uri);
        if (readUriToLocalFile == null) {
            return null;
        }
        return Uri.parse(getFileStreamPath(readUriToLocalFile).toURI().toString());
    }

    private boolean isStorageUri(Uri uri) {
        return uri != null && uri.toString().startsWith("file:///storage");
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        super.onCreate(bundle);
        Uri data = getIntent().getData();
        if (!isStorageUri(data) || !RequestImportVCardPermissionsActivity.startPermissionActivity(this, isCallerSelf(this))) {
            if (data != null) {
                String stringExtra = getIntent().getStringExtra("com.android.contacts.vcard.LOCAL_TMP_FILE_NAME");
                String stringExtra2 = getIntent().getStringExtra("com.android.contacts.vcard.SOURCE_URI_DISPLAY_NAME");
                if (TextUtils.isEmpty(stringExtra)) {
                    stringExtra = readUriToLocalFile(data);
                    str5 = getDisplayName(data);
                    if (stringExtra == null) {
                        Log.e("VCardImport", "Cannot load uri to local storage.");
                        showFailureNotification(R.string.fail_reason_io_error);
                        return;
                    }
                    getIntent().putExtra("com.android.contacts.vcard.LOCAL_TMP_FILE_NAME", stringExtra);
                    getIntent().putExtra("com.android.contacts.vcard.SOURCE_URI_DISPLAY_NAME", str5);
                } else {
                    str5 = stringExtra2;
                }
                Uri parse = Uri.parse(getFileStreamPath(stringExtra).toURI().toString());
                str = str5;
                data = parse;
            } else {
                str = null;
            }
            if (!RequestImportVCardPermissionsActivity.startPermissionActivity(this, isCallerSelf(this))) {
                Intent intent = getIntent();
                if (intent != null) {
                    str4 = intent.getStringExtra("account_name");
                    str2 = intent.getStringExtra("account_type");
                    str3 = intent.getStringExtra("data_set");
                } else {
                    Log.e("VCardImport", "intent does not exist");
                    str4 = null;
                    str3 = null;
                    str2 = null;
                }
                if (TextUtils.isEmpty(str4) || TextUtils.isEmpty(str2)) {
                    List<AccountWithDataSet> blockForWritableAccounts = AccountTypeManager.getInstance(this).blockForWritableAccounts();
                    if (blockForWritableAccounts.size() == 0) {
                        this.mAccount = null;
                    } else if (blockForWritableAccounts.size() == 1) {
                        this.mAccount = blockForWritableAccounts.get(0);
                    } else {
                        startActivityForResult(new Intent(this, SelectAccountActivity.class), 0);
                        return;
                    }
                } else {
                    this.mAccount = new AccountWithDataSet(str4, str2, str3);
                }
                if (isCallerSelf(this)) {
                    startImport(data, str);
                } else {
                    ImportVCardDialogFragment.show(this, data, str);
                }
            }
        }
    }

    private static boolean isCallerSelf(Activity activity) {
        String packageName;
        ComponentName callingActivity = activity.getCallingActivity();
        if (callingActivity == null || (packageName = callingActivity.getPackageName()) == null) {
            return false;
        }
        return packageName.equals(activity.getApplicationContext().getPackageName());
    }

    public void onImportVCardConfirmed(Uri uri, String str) {
        startImport(uri, str);
    }

    public void onImportVCardDenied() {
        finish();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        Uri readUriToLocalUri;
        if (i == 0) {
            if (i2 == -1) {
                this.mAccount = new AccountWithDataSet(intent.getStringExtra("account_name"), intent.getStringExtra("account_type"), intent.getStringExtra("data_set"));
                Uri data = getIntent().getData();
                if (data == null) {
                    startImport(data, (String) null);
                    return;
                }
                startImport(Uri.parse(getFileStreamPath(getIntent().getStringExtra("com.android.contacts.vcard.LOCAL_TMP_FILE_NAME")).toURI().toString()), getIntent().getStringExtra("com.android.contacts.vcard.SOURCE_URI_DISPLAY_NAME"));
                return;
            }
            if (i2 != 0) {
                Log.w("VCardImport", "Result code was not OK nor CANCELED: " + i2);
            }
            finish();
        } else if (i != 100) {
        } else {
            if (i2 == -1) {
                ClipData clipData = intent.getClipData();
                if (clipData != null) {
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    for (int i3 = 0; i3 < clipData.getItemCount(); i3++) {
                        Uri uri = clipData.getItemAt(i3).getUri();
                        if (!(uri == null || (readUriToLocalUri = readUriToLocalUri(uri)) == null)) {
                            String displayName = getDisplayName(uri);
                            arrayList.add(readUriToLocalUri);
                            arrayList2.add(displayName);
                        }
                    }
                    if (arrayList.isEmpty()) {
                        Log.w("VCardImport", "No vCard was selected for import");
                        finish();
                        return;
                    }
                    Log.i("VCardImport", "Multiple vCards selected for import: " + arrayList);
                    importVCard((Uri[]) arrayList.toArray(new Uri[0]), (String[]) arrayList2.toArray(new String[0]));
                    return;
                }
                Uri data2 = intent.getData();
                if (data2 != null) {
                    Log.i("VCardImport", "vCard selected for import: " + data2);
                    Uri readUriToLocalUri2 = readUriToLocalUri(data2);
                    if (readUriToLocalUri2 != null) {
                        importVCard(readUriToLocalUri2, getDisplayName(data2));
                        return;
                    }
                    Log.w("VCardImport", "No local URI for vCard import");
                    finish();
                    return;
                }
                Log.w("VCardImport", "No vCard was selected for import");
                finish();
                return;
            }
            if (i2 != 0) {
                Log.w("VCardImport", "Result code was not OK nor CANCELED" + i2);
            }
            finish();
        }
    }

    private void startImport(Uri uri, String str) {
        if (uri != null) {
            Log.i("VCardImport", "Starting vCard import using Uri " + uri);
            importVCard(uri, str);
            return;
        }
        Log.i("VCardImport", "Start vCard without Uri. The user will select vCard manually.");
        Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("text/x-vcard");
        intent.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
        startActivityForResult(intent, 100);
    }

    /* access modifiers changed from: protected */
    public Dialog onCreateDialog(int i, Bundle bundle) {
        if (i == R.id.dialog_cache_vcard) {
            if (this.mProgressDialogForCachingVCard == null) {
                String string = getString(R.string.caching_vcard_title);
                String string2 = getString(R.string.caching_vcard_message);
                this.mProgressDialogForCachingVCard = new ProgressDialog(this);
                this.mProgressDialogForCachingVCard.setTitle(string);
                this.mProgressDialogForCachingVCard.setMessage(string2);
                this.mProgressDialogForCachingVCard.setProgressStyle(0);
                this.mProgressDialogForCachingVCard.setOnCancelListener(this.mVCardCacheThread);
                startVCardService();
            }
            return this.mProgressDialogForCachingVCard;
        } else if (i != R.id.dialog_error_with_message) {
            return super.onCreateDialog(i, bundle);
        } else {
            String str = this.mErrorMessage;
            if (TextUtils.isEmpty(str)) {
                Log.e("VCardImport", "Error message is null while it must not.");
                str = getString(R.string.fail_reason_unknown);
            }
            return new AlertDialog.Builder(this).setTitle(getString(R.string.reading_vcard_failed_title)).setIconAttribute(16843605).setMessage(str).setOnCancelListener(this.mCancelListener).setPositiveButton(17039370, this.mCancelListener).create();
        }
    }

    /* access modifiers changed from: package-private */
    public void startVCardService() {
        this.mConnection = new ImportRequestConnection();
        Log.i("VCardImport", "Bind to VCardService.");
        startService(new Intent(this, VCardService.class));
        bindService(new Intent(this, VCardService.class), this.mConnection, 1);
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        if (this.mProgressDialogForCachingVCard != null) {
            Log.i("VCardImport", "Cache thread is still running. Show progress dialog again.");
            showDialog(R.id.dialog_cache_vcard);
        }
    }

    /* access modifiers changed from: package-private */
    public void showFailureNotification(int i) {
        ((NotificationManager) getSystemService("notification")).notify("VCardServiceFailure", 1, NotificationImportExportListener.constructImportFailureNotification(this, getString(i)));
        this.mHandler.post(new Runnable() {
            public void run() {
                ImportVCardActivity importVCardActivity = ImportVCardActivity.this;
                Toast.makeText(importVCardActivity, importVCardActivity.getString(R.string.vcard_import_failed), 1).show();
            }
        });
    }
}
