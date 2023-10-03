package com.android.contacts.vcard;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.text.BidiFormatter;
import android.text.TextDirectionHeuristics;
import android.util.Log;
import com.android.contacts.R;
import com.android.contacts.activities.RequestPermissionsActivity;
import com.android.contacts.vcard.VCardService;
import java.util.List;

public class ExportVCardActivity extends Activity implements ServiceConnection, DialogInterface.OnClickListener, DialogInterface.OnCancelListener {
    private static final BidiFormatter mBidiFormatter = BidiFormatter.getInstance();
    protected boolean mConnected;
    private String mErrorReason;
    private volatile boolean mProcessOngoing = true;
    protected VCardService mService;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!RequestPermissionsActivity.startPermissionActivityIfNeeded(this)) {
            if (!hasExportIntentHandler()) {
                Log.e("VCardExport", "Couldn't find export intent handler");
                showErrorDialog();
                return;
            }
            connectVCardService();
        }
    }

    private void connectVCardService() {
        String string = getIntent().getExtras().getString("CALLING_ACTIVITY");
        Intent intent = new Intent(this, VCardService.class);
        intent.putExtra("CALLING_ACTIVITY", string);
        if (startService(intent) == null) {
            Log.e("VCardExport", "Failed to start vCard service");
            showErrorDialog();
        } else if (!bindService(intent, this, 1)) {
            Log.e("VCardExport", "Failed to connect to vCard service.");
            showErrorDialog();
        }
    }

    private boolean hasExportIntentHandler() {
        List<ResolveInfo> queryIntentActivities = getPackageManager().queryIntentActivities(getCreateDocIntent(), 65536);
        return queryIntentActivities != null && queryIntentActivities.size() > 0;
    }

    private Intent getCreateDocIntent() {
        Intent intent = new Intent("android.intent.action.CREATE_DOCUMENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("text/x-vcard");
        intent.putExtra("android.intent.extra.TITLE", mBidiFormatter.unicodeWrap(getString(R.string.exporting_vcard_filename), TextDirectionHeuristics.LTR));
        return intent;
    }

    private void showErrorDialog() {
        this.mErrorReason = getString(R.string.fail_reason_unknown);
        showDialog(R.id.dialog_fail_to_export_with_reason);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 100) {
            if (!(i2 != -1 || this.mService == null || intent == null || intent.getData() == null)) {
                this.mService.handleExportRequest(new ExportRequest(intent.getData()), new NotificationImportExportListener(this));
            }
            finish();
        }
    }

    public synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.mConnected = true;
        this.mService = ((VCardService.MyBinder) iBinder).getService();
        startActivityForResult(getCreateDocIntent(), 100);
    }

    public synchronized void onServiceDisconnected(ComponentName componentName) {
        this.mService = null;
        this.mConnected = false;
        if (this.mProcessOngoing) {
            Log.w("VCardExport", "Disconnected from service during the process ongoing.");
            showErrorDialog();
        }
    }

    /* access modifiers changed from: protected */
    public Dialog onCreateDialog(int i, Bundle bundle) {
        if (i != R.id.dialog_fail_to_export_with_reason) {
            return super.onCreateDialog(i, bundle);
        }
        this.mProcessOngoing = false;
        AlertDialog.Builder title = new AlertDialog.Builder(this).setTitle(R.string.exporting_contact_failed_title);
        Object[] objArr = new Object[1];
        String str = this.mErrorReason;
        if (str == null) {
            str = getString(R.string.fail_reason_unknown);
        }
        objArr[0] = str;
        return title.setMessage(getString(R.string.exporting_contact_failed_message, objArr)).setPositiveButton(17039370, this).setOnCancelListener(this).create();
    }

    /* access modifiers changed from: protected */
    public void onPrepareDialog(int i, Dialog dialog, Bundle bundle) {
        if (i == R.id.dialog_fail_to_export_with_reason) {
            ((AlertDialog) dialog).setMessage(this.mErrorReason);
        } else {
            super.onPrepareDialog(i, dialog, bundle);
        }
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        finish();
    }

    public void onCancel(DialogInterface dialogInterface) {
        this.mProcessOngoing = false;
        finish();
    }

    public void unbindService(ServiceConnection serviceConnection) {
        this.mProcessOngoing = false;
        super.unbindService(serviceConnection);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (this.mConnected) {
            unbindService(this);
            this.mConnected = false;
        }
        super.onDestroy();
    }

    static String getOpenableUriDisplayName(Context context, Uri uri) {
        if (uri == null) {
            return null;
        }
        Cursor query = context.getContentResolver().query(uri, (String[]) null, (String) null, (String[]) null, (String) null);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    return query.getString(query.getColumnIndex("_display_name"));
                }
            } finally {
                if (query != null) {
                    query.close();
                }
            }
        }
        if (query != null) {
            query.close();
        }
        return null;
    }
}
