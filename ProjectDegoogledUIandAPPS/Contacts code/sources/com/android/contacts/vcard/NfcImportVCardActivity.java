package com.android.contacts.vcard;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;
import com.android.contacts.R;
import com.android.contacts.activities.RequestPermissionsActivity;
import com.android.contacts.model.AccountTypeManager;
import com.android.contacts.model.account.AccountWithDataSet;
import com.android.contacts.util.ImplicitIntentsUtil;
import com.android.contacts.vcard.VCardService;
import com.android.vcard.VCardEntry;
import java.util.ArrayList;
import java.util.List;

public class NfcImportVCardActivity extends Activity implements ServiceConnection, VCardImportExportListener {
    private AccountWithDataSet mAccount;
    private Handler mHandler = new Handler();
    private NdefRecord mRecord;

    public void onCancelRequest(CancelRequest cancelRequest, int i) {
    }

    public void onExportFailed(ExportRequest exportRequest) {
    }

    public Notification onExportProcessed(ExportRequest exportRequest, int i) {
        return null;
    }

    public void onImportCanceled(ImportRequest importRequest, int i) {
    }

    public Notification onImportParsed(ImportRequest importRequest, int i, VCardEntry vCardEntry, int i2, int i3) {
        return null;
    }

    public Notification onImportProcessed(ImportRequest importRequest, int i, int i2) {
        return null;
    }

    public void onServiceDisconnected(ComponentName componentName) {
    }

    class ImportTask extends AsyncTask<VCardService, Void, ImportRequest> {
        ImportTask() {
        }

        public ImportRequest doInBackground(VCardService... vCardServiceArr) {
            ImportRequest createImportRequest = NfcImportVCardActivity.this.createImportRequest();
            if (createImportRequest == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(createImportRequest);
            vCardServiceArr[0].handleImportRequest(arrayList, NfcImportVCardActivity.this);
            return createImportRequest;
        }

        public void onCancelled() {
            NfcImportVCardActivity nfcImportVCardActivity = NfcImportVCardActivity.this;
            nfcImportVCardActivity.unbindService(nfcImportVCardActivity);
        }

        public void onPostExecute(ImportRequest importRequest) {
            if (importRequest == null) {
                NfcImportVCardActivity.this.finish();
            }
            NfcImportVCardActivity nfcImportVCardActivity = NfcImportVCardActivity.this;
            nfcImportVCardActivity.unbindService(nfcImportVCardActivity);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't wrap try/catch for region: R(11:19|20|21|22|23|24|25|26|27|28|31) */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r6.reset();
        r5 = 2;
        r0 = new com.android.vcard.VCardParser_V30();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r9 = new com.android.vcard.VCardEntryCounter();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r7 = new com.android.vcard.VCardSourceDetector();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r0.addInterpreter(r9);
        r0.addInterpreter(r7);
        r0.parse(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0060, code lost:
        r8 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0062, code lost:
        r8 = r7;
        r7 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0065, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0066, code lost:
        r8 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0068, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0069, code lost:
        r8 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x006b, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x006c, code lost:
        r7 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x006e, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0070, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0071, code lost:
        r9 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        com.android.contactsbind.FeedbackHelper.sendFeedback(r1, "NfcImportVCardActivity", "vcard with unsupported version", r0);
        showFailureNotification(com.android.contacts.R.string.fail_reason_not_supported);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x007e, code lost:
        r7 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0080, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0081, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0086, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0087, code lost:
        com.android.contactsbind.FeedbackHelper.sendFeedback(r1, "NfcImportVCardActivity", "Failed to parse vcard", r0);
        showFailureNotification(com.android.contacts.R.string.fail_reason_not_supported);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x008f, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0090, code lost:
        r7 = null;
        r8 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0092, code lost:
        android.util.Log.w("NfcImportVCardActivity", "Nested Exception is found (it may be false-positive).");
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:27:0x005c, B:52:0x0082] */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:45:0x007a, B:52:0x0082] */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:9:0x0030, B:52:0x0082] */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0040 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:54:0x0085 */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0086 A[ExcHandler: VCardException (r0v4 'e' com.android.vcard.exception.VCardException A[CUSTOM_DECLARE]), Splitter:B:52:0x0082] */
    /* JADX WARNING: Removed duplicated region for block: B:62:? A[ExcHandler: VCardNestedException (unused com.android.vcard.exception.VCardNestedException), PHI: r5 r7 r8 
      PHI: (r5v4 int) = (r5v5 int), (r5v5 int), (r5v5 int), (r5v5 int), (r5v0 int), (r5v0 int) binds: [B:52:0x0082, B:54:0x0085, B:55:?, B:53:?, B:9:0x0030, B:10:?] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r7v3 com.android.vcard.VCardEntryCounter) = (r7v4 com.android.vcard.VCardEntryCounter), (r7v4 com.android.vcard.VCardEntryCounter), (r7v4 com.android.vcard.VCardEntryCounter), (r7v4 com.android.vcard.VCardEntryCounter), (r7v13 com.android.vcard.VCardEntryCounter), (r7v13 com.android.vcard.VCardEntryCounter) binds: [B:52:0x0082, B:54:0x0085, B:55:?, B:53:?, B:9:0x0030, B:10:?] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r8v3 com.android.vcard.VCardSourceDetector) = (r8v4 com.android.vcard.VCardSourceDetector), (r8v4 com.android.vcard.VCardSourceDetector), (r8v4 com.android.vcard.VCardSourceDetector), (r8v4 com.android.vcard.VCardSourceDetector), (r8v17 com.android.vcard.VCardSourceDetector), (r8v17 com.android.vcard.VCardSourceDetector) binds: [B:52:0x0082, B:54:0x0085, B:55:?, B:53:?, B:9:0x0030, B:10:?] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC, Splitter:B:9:0x0030] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.android.contacts.vcard.ImportRequest createImportRequest() {
        /*
            r18 = this;
            r1 = r18
            java.lang.String r2 = "NfcImportVCardActivity"
            r3 = 2131755360(0x7f100160, float:1.9141597E38)
            r4 = 0
            r5 = 1
            java.io.ByteArrayInputStream r6 = new java.io.ByteArrayInputStream     // Catch:{ IOException -> 0x00bc, VCardNestedException -> 0x0090, VCardException -> 0x0086 }
            android.nfc.NdefRecord r0 = r1.mRecord     // Catch:{ IOException -> 0x00bc, VCardNestedException -> 0x0090, VCardException -> 0x0086 }
            byte[] r0 = r0.getPayload()     // Catch:{ IOException -> 0x00bc, VCardNestedException -> 0x0090, VCardException -> 0x0086 }
            r6.<init>(r0)     // Catch:{ IOException -> 0x00bc, VCardNestedException -> 0x0090, VCardException -> 0x0086 }
            r0 = 0
            r6.mark(r0)     // Catch:{ IOException -> 0x00bc, VCardNestedException -> 0x0090, VCardException -> 0x0086 }
            com.android.vcard.VCardParser_V21 r0 = new com.android.vcard.VCardParser_V21     // Catch:{ IOException -> 0x00bc, VCardNestedException -> 0x0090, VCardException -> 0x0086 }
            r0.<init>()     // Catch:{ IOException -> 0x00bc, VCardNestedException -> 0x0090, VCardException -> 0x0086 }
            com.android.vcard.VCardEntryCounter r7 = new com.android.vcard.VCardEntryCounter     // Catch:{ VCardVersionException -> 0x003e, all -> 0x003a }
            r7.<init>()     // Catch:{ VCardVersionException -> 0x003e, all -> 0x003a }
            com.android.vcard.VCardSourceDetector r8 = new com.android.vcard.VCardSourceDetector     // Catch:{ VCardVersionException -> 0x0038, all -> 0x0035 }
            r8.<init>()     // Catch:{ VCardVersionException -> 0x0038, all -> 0x0035 }
            r0.addInterpreter(r7)     // Catch:{ VCardVersionException -> 0x0040 }
            r0.addInterpreter(r8)     // Catch:{ VCardVersionException -> 0x0040 }
            r0.parse(r6)     // Catch:{ VCardVersionException -> 0x0040 }
            r6.close()     // Catch:{ IOException -> 0x0097, VCardNestedException -> 0x0092, VCardException -> 0x0086 }
            goto L_0x0097
        L_0x0035:
            r0 = move-exception
            r8 = r4
            goto L_0x0082
        L_0x0038:
            r8 = r4
            goto L_0x0040
        L_0x003a:
            r0 = move-exception
            r7 = r4
            r8 = r7
            goto L_0x0082
        L_0x003e:
            r7 = r4
            r8 = r7
        L_0x0040:
            r6.reset()     // Catch:{ all -> 0x0081 }
            r5 = 2
            com.android.vcard.VCardParser_V30 r0 = new com.android.vcard.VCardParser_V30     // Catch:{ all -> 0x0081 }
            r0.<init>()     // Catch:{ all -> 0x0081 }
            com.android.vcard.VCardEntryCounter r9 = new com.android.vcard.VCardEntryCounter     // Catch:{ VCardVersionException -> 0x0070 }
            r9.<init>()     // Catch:{ VCardVersionException -> 0x0070 }
            com.android.vcard.VCardSourceDetector r7 = new com.android.vcard.VCardSourceDetector     // Catch:{ VCardVersionException -> 0x006e }
            r7.<init>()     // Catch:{ VCardVersionException -> 0x006e }
            r0.addInterpreter(r9)     // Catch:{ VCardVersionException -> 0x0068, all -> 0x0065 }
            r0.addInterpreter(r7)     // Catch:{ VCardVersionException -> 0x0068, all -> 0x0065 }
            r0.parse(r6)     // Catch:{ VCardVersionException -> 0x0068, all -> 0x0065 }
            r6.close()     // Catch:{ IOException -> 0x0062, VCardNestedException -> 0x0060, VCardException -> 0x0086 }
            goto L_0x0062
        L_0x0060:
            r8 = r7
            goto L_0x007e
        L_0x0062:
            r8 = r7
            r7 = r9
            goto L_0x0097
        L_0x0065:
            r0 = move-exception
            r8 = r7
            goto L_0x006c
        L_0x0068:
            r0 = move-exception
            r8 = r7
            goto L_0x0072
        L_0x006b:
            r0 = move-exception
        L_0x006c:
            r7 = r9
            goto L_0x0082
        L_0x006e:
            r0 = move-exception
            goto L_0x0072
        L_0x0070:
            r0 = move-exception
            r9 = r7
        L_0x0072:
            java.lang.String r7 = "vcard with unsupported version"
            com.android.contactsbind.FeedbackHelper.sendFeedback(r1, r2, r7, r0)     // Catch:{ all -> 0x006b }
            r1.showFailureNotification(r3)     // Catch:{ all -> 0x006b }
            r6.close()     // Catch:{ IOException -> 0x0080, VCardNestedException -> 0x007e, VCardException -> 0x0086 }
            goto L_0x0080
        L_0x007e:
            r7 = r9
            goto L_0x0092
        L_0x0080:
            return r4
        L_0x0081:
            r0 = move-exception
        L_0x0082:
            r6.close()     // Catch:{ IOException -> 0x0085, VCardNestedException -> 0x0092, VCardException -> 0x0086 }
        L_0x0085:
            throw r0     // Catch:{ IOException -> 0x00bc, VCardNestedException -> 0x0092, VCardException -> 0x0086 }
        L_0x0086:
            r0 = move-exception
            java.lang.String r5 = "Failed to parse vcard"
            com.android.contactsbind.FeedbackHelper.sendFeedback(r1, r2, r5, r0)
            r1.showFailureNotification(r3)
            return r4
        L_0x0090:
            r7 = r4
            r8 = r7
        L_0x0092:
            java.lang.String r0 = "Nested Exception is found (it may be false-positive)."
            android.util.Log.w(r2, r0)
        L_0x0097:
            r16 = r5
            com.android.contacts.vcard.ImportRequest r0 = new com.android.contacts.vcard.ImportRequest
            com.android.contacts.model.account.AccountWithDataSet r10 = r1.mAccount
            android.nfc.NdefRecord r2 = r1.mRecord
            byte[] r11 = r2.getPayload()
            r12 = 0
            r2 = 2131755502(0x7f1001ee, float:1.9141885E38)
            java.lang.String r13 = r1.getString(r2)
            int r14 = r8.getEstimatedType()
            java.lang.String r15 = r8.getEstimatedCharset()
            int r17 = r7.getCount()
            r9 = r0
            r9.<init>(r10, r11, r12, r13, r14, r15, r16, r17)
            return r0
        L_0x00bc:
            r0 = move-exception
            java.lang.String r3 = "Failed to read vcard data"
            com.android.contactsbind.FeedbackHelper.sendFeedback(r1, r2, r3, r0)
            r0 = 2131755357(0x7f10015d, float:1.914159E38)
            r1.showFailureNotification(r0)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.vcard.NfcImportVCardActivity.createImportRequest():com.android.contacts.vcard.ImportRequest");
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        VCardService service = ((VCardService.MyBinder) iBinder).getService();
        new ImportTask().execute(new VCardService[]{service});
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!RequestPermissionsActivity.startPermissionActivityIfNeeded(this)) {
            Intent intent = getIntent();
            if (!"android.nfc.action.NDEF_DISCOVERED".equals(intent.getAction())) {
                Log.w("NfcImportVCardActivity", "Unknowon intent " + intent);
                finish();
                return;
            }
            String type = intent.getType();
            if (type == null || (!"text/x-vcard".equals(type) && !"text/vcard".equals(type))) {
                Log.w("NfcImportVCardActivity", "Not a vcard");
                finish();
                return;
            }
            this.mRecord = ((NdefMessage) intent.getParcelableArrayExtra("android.nfc.extra.NDEF_MESSAGES")[0]).getRecords()[0];
            List<AccountWithDataSet> blockForWritableAccounts = AccountTypeManager.getInstance(this).blockForWritableAccounts();
            if (blockForWritableAccounts.size() == 0) {
                this.mAccount = null;
            } else if (blockForWritableAccounts.size() == 1) {
                this.mAccount = blockForWritableAccounts.get(0);
            } else {
                startActivityForResult(new Intent(this, SelectAccountActivity.class), 1);
                return;
            }
            startImport();
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i != 1) {
            return;
        }
        if (i2 == -1) {
            this.mAccount = new AccountWithDataSet(intent.getStringExtra("account_name"), intent.getStringExtra("account_type"), intent.getStringExtra("data_set"));
            startImport();
            return;
        }
        finish();
    }

    private void startImport() {
        Intent intent = new Intent(this, VCardService.class);
        startService(intent);
        bindService(intent, this, 1);
    }

    public void onImportFinished(ImportRequest importRequest, int i, Uri uri) {
        if (isFinishing()) {
            Log.i("NfcImportVCardActivity", "Late import -- ignoring");
        } else if (uri != null) {
            ImplicitIntentsUtil.startActivityInAppIfPossible(this, new Intent("android.intent.action.VIEW", ContactsContract.RawContacts.getContactLookupUri(getContentResolver(), uri)));
            finish();
        }
    }

    public void onImportFailed(ImportRequest importRequest) {
        if (isFinishing()) {
            Log.i("NfcImportVCardActivity", "Late import failure -- ignoring");
            return;
        }
        showFailureNotification(R.string.vcard_import_request_rejected_message);
        finish();
    }

    /* access modifiers changed from: package-private */
    public void showFailureNotification(int i) {
        ((NotificationManager) getSystemService("notification")).notify("VCardServiceFailure", 1, NotificationImportExportListener.constructImportFailureNotification(this, getString(i)));
        this.mHandler.post(new Runnable() {
            public void run() {
                NfcImportVCardActivity nfcImportVCardActivity = NfcImportVCardActivity.this;
                Toast.makeText(nfcImportVCardActivity, nfcImportVCardActivity.getString(R.string.vcard_import_failed), 1).show();
            }
        });
    }
}
