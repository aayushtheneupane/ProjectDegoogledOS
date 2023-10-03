package com.android.contacts.vcard;

import android.app.NotificationManager;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import com.android.contacts.R;
import com.android.contactsbind.FeedbackHelper;

public class ExportProcessor extends ProcessorBase {
    private final int SHOW_READY_TOAST = 1;
    private final Handler handler = new Handler() {
        public void handleMessage(Message message) {
            if (message.arg1 == 1) {
                Toast.makeText(ExportProcessor.this.mService, R.string.exporting_vcard_finished_toast, 1).show();
            }
        }
    };
    private final String mCallingActivity;
    private volatile boolean mCanceled;
    private volatile boolean mDone;
    private final ExportRequest mExportRequest;
    private final int mJobId;
    private final NotificationManager mNotificationManager;
    private final ContentResolver mResolver;
    /* access modifiers changed from: private */
    public final VCardService mService;

    public final int getType() {
        return 2;
    }

    public ExportProcessor(VCardService vCardService, ExportRequest exportRequest, int i, String str) {
        this.mService = vCardService;
        this.mResolver = vCardService.getContentResolver();
        this.mNotificationManager = (NotificationManager) this.mService.getSystemService("notification");
        this.mExportRequest = exportRequest;
        this.mJobId = i;
        this.mCallingActivity = str;
    }

    public void run() {
        try {
            runInternal();
            if (isCancelled()) {
                doCancelNotification();
            }
            synchronized (this) {
                this.mDone = true;
            }
        } catch (OutOfMemoryError | RuntimeException e) {
            FeedbackHelper.sendFeedback(this.mService, "VCardExport", "Failed to process vcard export", e);
            throw e;
        } catch (Throwable th) {
            synchronized (this) {
                this.mDone = true;
                throw th;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:100:0x0207  */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x020c A[SYNTHETIC, Splitter:B:102:0x020c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void runInternal() {
        /*
            r18 = this;
            r1 = r18
            java.lang.String r2 = "IOException is thrown during close(). Ignored. "
            java.lang.String r3 = "VCardExport"
            com.android.contacts.vcard.ExportRequest r0 = r1.mExportRequest
            r4 = 0
            r5 = 0
            r6 = 1
            boolean r7 = r18.isCancelled()     // Catch:{ all -> 0x01ff }
            if (r7 == 0) goto L_0x001e
            java.lang.String r0 = "Export request is cancelled before handling the request"
            android.util.Log.i(r3, r0)     // Catch:{ all -> 0x01ff }
        L_0x0016:
            com.android.contacts.vcard.VCardService r0 = r1.mService
            int r2 = r1.mJobId
            r0.handleFinishExportNotification(r2, r4)
            return
        L_0x001e:
            android.net.Uri r7 = r0.destUri     // Catch:{ all -> 0x01ff }
            android.content.ContentResolver r8 = r1.mResolver     // Catch:{ FileNotFoundException -> 0x01e0 }
            java.io.OutputStream r8 = r8.openOutputStream(r7)     // Catch:{ FileNotFoundException -> 0x01e0 }
            java.lang.String r9 = r0.exportType     // Catch:{ all -> 0x01ff }
            boolean r10 = android.text.TextUtils.isEmpty(r9)     // Catch:{ all -> 0x01ff }
            if (r10 == 0) goto L_0x003c
            com.android.contacts.vcard.VCardService r9 = r1.mService     // Catch:{ all -> 0x01ff }
            r10 = 2131755209(0x7f1000c9, float:1.914129E38)
            java.lang.String r9 = r9.getString(r10)     // Catch:{ all -> 0x01ff }
            int r9 = com.android.vcard.VCardConfig.getVCardTypeFromString(r9)     // Catch:{ all -> 0x01ff }
            goto L_0x0040
        L_0x003c:
            int r9 = com.android.vcard.VCardConfig.getVCardTypeFromString(r9)     // Catch:{ all -> 0x01ff }
        L_0x0040:
            com.android.vcard.VCardComposer r15 = new com.android.vcard.VCardComposer     // Catch:{ all -> 0x01ff }
            com.android.contacts.vcard.VCardService r10 = r1.mService     // Catch:{ all -> 0x01ff }
            r15.<init>(r10, r9, r6)     // Catch:{ all -> 0x01ff }
            java.io.BufferedWriter r9 = new java.io.BufferedWriter     // Catch:{ all -> 0x01da }
            java.io.OutputStreamWriter r10 = new java.io.OutputStreamWriter     // Catch:{ all -> 0x01da }
            r10.<init>(r8)     // Catch:{ all -> 0x01da }
            r9.<init>(r10)     // Catch:{ all -> 0x01da }
            android.net.Uri r16 = android.provider.ContactsContract.RawContactsEntity.CONTENT_URI     // Catch:{ all -> 0x01d5 }
            android.net.Uri r11 = android.provider.ContactsContract.Contacts.CONTENT_URI     // Catch:{ all -> 0x01d5 }
            java.lang.String r8 = "_id"
            java.lang.String[] r12 = new java.lang.String[]{r8}     // Catch:{ all -> 0x01d5 }
            r13 = 0
            r14 = 0
            r8 = 0
            r10 = r15
            r17 = r15
            r15 = r8
            boolean r8 = r10.init(r11, r12, r13, r14, r15, r16)     // Catch:{ all -> 0x01d3 }
            if (r8 != 0) goto L_0x00b2
            java.lang.String r0 = r17.getErrorReason()     // Catch:{ all -> 0x01d3 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x01d3 }
            r7.<init>()     // Catch:{ all -> 0x01d3 }
            java.lang.String r8 = "initialization of vCard composer failed: "
            r7.append(r8)     // Catch:{ all -> 0x01d3 }
            r7.append(r0)     // Catch:{ all -> 0x01d3 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x01d3 }
            android.util.Log.e(r3, r7)     // Catch:{ all -> 0x01d3 }
            java.lang.String r0 = r1.translateComposerError(r0)     // Catch:{ all -> 0x01d3 }
            com.android.contacts.vcard.VCardService r7 = r1.mService     // Catch:{ all -> 0x01d3 }
            r8 = 2131755354(0x7f10015a, float:1.9141585E38)
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ all -> 0x01d3 }
            r6[r4] = r0     // Catch:{ all -> 0x01d3 }
            java.lang.String r0 = r7.getString(r8, r6)     // Catch:{ all -> 0x01d3 }
            r1.doFinishNotification(r0, r5)     // Catch:{ all -> 0x01d3 }
            r17.terminate()
            r9.close()     // Catch:{ IOException -> 0x009c }
            goto L_0x0016
        L_0x009c:
            r0 = move-exception
            r5 = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
        L_0x00a3:
            r0.append(r2)
            r0.append(r5)
            java.lang.String r0 = r0.toString()
            android.util.Log.w(r3, r0)
            goto L_0x0016
        L_0x00b2:
            int r8 = r17.getCount()     // Catch:{ all -> 0x01d3 }
            if (r8 != 0) goto L_0x00d4
            com.android.contacts.vcard.VCardService r0 = r1.mService     // Catch:{ all -> 0x01d3 }
            r6 = 2131755359(0x7f10015f, float:1.9141595E38)
            java.lang.String r0 = r0.getString(r6)     // Catch:{ all -> 0x01d3 }
            r1.doFinishNotification(r0, r5)     // Catch:{ all -> 0x01d3 }
            r17.terminate()
            r9.close()     // Catch:{ IOException -> 0x00cc }
            goto L_0x0016
        L_0x00cc:
            r0 = move-exception
            r5 = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            goto L_0x00a3
        L_0x00d4:
            r10 = 1
        L_0x00d5:
            boolean r11 = r17.isAfterLast()     // Catch:{ all -> 0x01d3 }
            if (r11 != 0) goto L_0x0144
            boolean r11 = r18.isCancelled()     // Catch:{ all -> 0x01d3 }
            if (r11 == 0) goto L_0x00f6
            java.lang.String r0 = "Export request is cancelled during composing vCard"
            android.util.Log.i(r3, r0)     // Catch:{ all -> 0x01d3 }
            r17.terminate()
            r9.close()     // Catch:{ IOException -> 0x00ee }
            goto L_0x0016
        L_0x00ee:
            r0 = move-exception
            r5 = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            goto L_0x00a3
        L_0x00f6:
            java.lang.String r11 = r17.createOneEntry()     // Catch:{ IOException -> 0x0107 }
            r9.write(r11)     // Catch:{ IOException -> 0x0107 }
            int r11 = r10 % 100
            if (r11 != r6) goto L_0x0104
            r1.doProgressNotification(r7, r8, r10)     // Catch:{ all -> 0x01d3 }
        L_0x0104:
            int r10 = r10 + 1
            goto L_0x00d5
        L_0x0107:
            java.lang.String r0 = r17.getErrorReason()     // Catch:{ all -> 0x01d3 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x01d3 }
            r7.<init>()     // Catch:{ all -> 0x01d3 }
            java.lang.String r8 = "Failed to read a contact: "
            r7.append(r8)     // Catch:{ all -> 0x01d3 }
            r7.append(r0)     // Catch:{ all -> 0x01d3 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x01d3 }
            android.util.Log.e(r3, r7)     // Catch:{ all -> 0x01d3 }
            java.lang.String r0 = r1.translateComposerError(r0)     // Catch:{ all -> 0x01d3 }
            com.android.contacts.vcard.VCardService r7 = r1.mService     // Catch:{ all -> 0x01d3 }
            r8 = 2131755356(0x7f10015c, float:1.9141589E38)
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ all -> 0x01d3 }
            r6[r4] = r0     // Catch:{ all -> 0x01d3 }
            java.lang.String r0 = r7.getString(r8, r6)     // Catch:{ all -> 0x01d3 }
            r1.doFinishNotification(r0, r5)     // Catch:{ all -> 0x01d3 }
            r17.terminate()
            r9.close()     // Catch:{ IOException -> 0x013b }
            goto L_0x0016
        L_0x013b:
            r0 = move-exception
            r5 = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            goto L_0x00a3
        L_0x0144:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x01d3 }
            r8.<init>()     // Catch:{ all -> 0x01d3 }
            java.lang.String r10 = "Successfully finished exporting vCard "
            r8.append(r10)     // Catch:{ all -> 0x01d3 }
            android.net.Uri r10 = r0.destUri     // Catch:{ all -> 0x01d3 }
            r8.append(r10)     // Catch:{ all -> 0x01d3 }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x01d3 }
            android.util.Log.i(r3, r8)     // Catch:{ all -> 0x01d3 }
            com.android.contacts.vcard.VCardService r8 = r1.mService     // Catch:{ all -> 0x01d3 }
            android.net.Uri r0 = r0.destUri     // Catch:{ all -> 0x01d3 }
            java.lang.String r0 = r0.getPath()     // Catch:{ all -> 0x01d3 }
            r8.updateMediaScanner(r0)     // Catch:{ all -> 0x01d3 }
            com.android.contacts.vcard.VCardService r0 = r1.mService     // Catch:{ all -> 0x01d0 }
            java.lang.String r0 = com.android.contacts.vcard.ExportVCardActivity.getOpenableUriDisplayName(r0, r7)     // Catch:{ all -> 0x01d0 }
            boolean r8 = r1.isLocalFile(r7)     // Catch:{ all -> 0x01d0 }
            r10 = 2131755349(0x7f100155, float:1.9141575E38)
            if (r8 == 0) goto L_0x0194
            android.os.Handler r0 = r1.handler     // Catch:{ all -> 0x01d0 }
            android.os.Message r0 = r0.obtainMessage()     // Catch:{ all -> 0x01d0 }
            r0.arg1 = r6     // Catch:{ all -> 0x01d0 }
            android.os.Handler r4 = r1.handler     // Catch:{ all -> 0x01d0 }
            r4.sendMessage(r0)     // Catch:{ all -> 0x01d0 }
            com.android.contacts.vcard.VCardService r0 = r1.mService     // Catch:{ all -> 0x01d0 }
            java.lang.String r0 = r0.getString(r10)     // Catch:{ all -> 0x01d0 }
            com.android.contacts.vcard.VCardService r4 = r1.mService     // Catch:{ all -> 0x01d0 }
            r5 = 2131755628(0x7f10026c, float:1.914214E38)
            java.lang.String r4 = r4.getString(r5)     // Catch:{ all -> 0x01d0 }
            r1.doFinishNotificationWithShareAction(r0, r4, r7)     // Catch:{ all -> 0x01d0 }
            goto L_0x01ad
        L_0x0194:
            if (r0 != 0) goto L_0x019d
            com.android.contacts.vcard.VCardService r0 = r1.mService     // Catch:{ all -> 0x01d0 }
            java.lang.String r0 = r0.getString(r10)     // Catch:{ all -> 0x01d0 }
            goto L_0x01aa
        L_0x019d:
            com.android.contacts.vcard.VCardService r7 = r1.mService     // Catch:{ all -> 0x01d0 }
            r8 = 2131755348(0x7f100154, float:1.9141573E38)
            java.lang.Object[] r10 = new java.lang.Object[r6]     // Catch:{ all -> 0x01d0 }
            r10[r4] = r0     // Catch:{ all -> 0x01d0 }
            java.lang.String r0 = r7.getString(r8, r10)     // Catch:{ all -> 0x01d0 }
        L_0x01aa:
            r1.doFinishNotification(r0, r5)     // Catch:{ all -> 0x01d0 }
        L_0x01ad:
            r17.terminate()
            r9.close()     // Catch:{ IOException -> 0x01b4 }
            goto L_0x01c8
        L_0x01b4:
            r0 = move-exception
            r4 = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r2)
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            android.util.Log.w(r3, r0)
        L_0x01c8:
            com.android.contacts.vcard.VCardService r0 = r1.mService
            int r2 = r1.mJobId
            r0.handleFinishExportNotification(r2, r6)
            return
        L_0x01d0:
            r0 = move-exception
            r4 = r0
            goto L_0x0205
        L_0x01d3:
            r0 = move-exception
            goto L_0x01d8
        L_0x01d5:
            r0 = move-exception
            r17 = r15
        L_0x01d8:
            r4 = r0
            goto L_0x0204
        L_0x01da:
            r0 = move-exception
            r17 = r15
            r4 = r0
            r9 = r5
            goto L_0x0204
        L_0x01e0:
            r0 = move-exception
            java.lang.String r8 = "FileNotFoundException thrown"
            android.util.Log.w(r3, r8, r0)     // Catch:{ all -> 0x01ff }
            com.android.contacts.vcard.VCardService r8 = r1.mService     // Catch:{ all -> 0x01ff }
            r9 = 2131755355(0x7f10015b, float:1.9141587E38)
            r10 = 2
            java.lang.Object[] r10 = new java.lang.Object[r10]     // Catch:{ all -> 0x01ff }
            r10[r4] = r7     // Catch:{ all -> 0x01ff }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x01ff }
            r10[r6] = r0     // Catch:{ all -> 0x01ff }
            java.lang.String r0 = r8.getString(r9, r10)     // Catch:{ all -> 0x01ff }
            r1.doFinishNotification(r0, r5)     // Catch:{ all -> 0x01ff }
            goto L_0x0016
        L_0x01ff:
            r0 = move-exception
            r4 = r0
            r9 = r5
            r17 = r9
        L_0x0204:
            r6 = 0
        L_0x0205:
            if (r17 == 0) goto L_0x020a
            r17.terminate()
        L_0x020a:
            if (r9 == 0) goto L_0x0224
            r9.close()     // Catch:{ IOException -> 0x0210 }
            goto L_0x0224
        L_0x0210:
            r0 = move-exception
            r5 = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r2)
            r0.append(r5)
            java.lang.String r0 = r0.toString()
            android.util.Log.w(r3, r0)
        L_0x0224:
            com.android.contacts.vcard.VCardService r0 = r1.mService
            int r2 = r1.mJobId
            r0.handleFinishExportNotification(r2, r6)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.vcard.ExportProcessor.runInternal():void");
    }

    private boolean isLocalFile(Uri uri) {
        return this.mService.getString(R.string.contacts_file_provider_authority).equals(uri.getAuthority());
    }

    private String translateComposerError(String str) {
        Resources resources = this.mService.getResources();
        if ("Failed to get database information".equals(str)) {
            return resources.getString(R.string.composer_failed_to_get_database_infomation);
        }
        if ("There's no exportable in the database".equals(str)) {
            return resources.getString(R.string.composer_has_no_exportable_contact);
        }
        return "The vCard composer object is not correctly initialized".equals(str) ? resources.getString(R.string.composer_not_initialized) : str;
    }

    private void doProgressNotification(Uri uri, int i, int i2) {
        String lastPathSegment = uri.getLastPathSegment();
        this.mService.startForeground(this.mJobId, NotificationImportExportListener.constructProgressNotification(this.mService, 2, this.mService.getString(R.string.exporting_contact_list_message, new Object[]{lastPathSegment}), this.mService.getString(R.string.exporting_contact_list_title), this.mJobId, lastPathSegment, i, i2));
    }

    private void doCancelNotification() {
        this.mNotificationManager.notify("VCardServiceProgress", this.mJobId, NotificationImportExportListener.constructCancelNotification(this.mService, this.mService.getString(R.string.exporting_vcard_canceled_title, new Object[]{this.mExportRequest.destUri.getLastPathSegment()})));
    }

    private void doFinishNotification(String str, String str2) {
        Intent intent = new Intent();
        intent.setClassName(this.mService, this.mCallingActivity);
        this.mNotificationManager.notify("VCardServiceProgress", this.mJobId, NotificationImportExportListener.constructFinishNotification(this.mService, str, str2, intent));
    }

    private void doFinishNotificationWithShareAction(String str, String str2, Uri uri) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/x-vcard");
        intent.putExtra("android.intent.extra.STREAM", uri);
        intent.setFlags(268435457);
        this.mNotificationManager.notify("VCardServiceProgress", this.mJobId, NotificationImportExportListener.constructFinishNotification(this.mService, str, str2, intent));
    }

    public synchronized boolean cancel(boolean z) {
        if (!this.mDone) {
            if (!this.mCanceled) {
                this.mCanceled = true;
                return true;
            }
        }
        return false;
    }

    public synchronized boolean isCancelled() {
        return this.mCanceled;
    }

    public synchronized boolean isDone() {
        return this.mDone;
    }

    public ExportRequest getRequest() {
        return this.mExportRequest;
    }
}
