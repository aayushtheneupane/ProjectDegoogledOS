package com.android.contacts.vcard;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import com.android.contacts.R;
import com.android.contacts.model.account.BaseAccountType;
import com.android.contacts.vcard.VCardService;

public class CancelActivity extends Activity implements ServiceConnection {
    private final String LOG_TAG = "VCardCancel";
    private final CancelListener mCancelListener = new CancelListener();
    private String mDisplayName;
    private int mJobId;
    private int mType;

    public void onServiceDisconnected(ComponentName componentName) {
    }

    private class RequestCancelListener implements DialogInterface.OnClickListener {
        private RequestCancelListener() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            CancelActivity cancelActivity = CancelActivity.this;
            cancelActivity.bindService(new Intent(cancelActivity, VCardService.class), CancelActivity.this, 1);
        }
    }

    private class CancelListener implements DialogInterface.OnClickListener, DialogInterface.OnCancelListener {
        private CancelListener() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            CancelActivity.this.finish();
        }

        public void onCancel(DialogInterface dialogInterface) {
            CancelActivity.this.finish();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Uri data = getIntent().getData();
        this.mJobId = Integer.parseInt(data.getQueryParameter("job_id"));
        this.mDisplayName = data.getQueryParameter("display_name");
        this.mType = Integer.parseInt(data.getQueryParameter(BaseAccountType.Attr.TYPE));
        showDialog(R.id.dialog_cancel_confirmation);
    }

    /* access modifiers changed from: protected */
    public Dialog onCreateDialog(int i, Bundle bundle) {
        String str;
        if (i == R.id.dialog_cancel_confirmation) {
            if (this.mType == 1) {
                str = getString(R.string.cancel_import_confirmation_message, new Object[]{this.mDisplayName});
            } else {
                str = getString(R.string.cancel_export_confirmation_message, new Object[]{this.mDisplayName});
            }
            return new AlertDialog.Builder(this).setMessage(str).setPositiveButton(R.string.yes_button, new RequestCancelListener()).setOnCancelListener(this.mCancelListener).setNegativeButton(R.string.no_button, this.mCancelListener).create();
        } else if (i == R.id.dialog_cancel_failed) {
            return new AlertDialog.Builder(this).setTitle(R.string.cancel_vcard_import_or_export_failed).setIconAttribute(16843605).setMessage(getString(R.string.fail_reason_unknown)).setOnCancelListener(this.mCancelListener).setPositiveButton(17039370, this.mCancelListener).create();
        } else {
            Log.w("VCardCancel", "Unknown dialog id: " + i);
            return super.onCreateDialog(i, bundle);
        }
    }

    /* JADX INFO: finally extract failed */
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        try {
            ((VCardService.MyBinder) iBinder).getService().handleCancelRequest(new CancelRequest(this.mJobId, this.mDisplayName), (VCardImportExportListener) null);
            unbindService(this);
            finish();
        } catch (Throwable th) {
            unbindService(this);
            throw th;
        }
    }
}
