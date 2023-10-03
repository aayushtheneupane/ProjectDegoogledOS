package com.android.contacts.common.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import com.android.dialer.R;
import com.android.dialer.util.PermissionsUtil;

public class ClearFrequentsDialog extends DialogFragment {
    public Dialog onCreateDialog(Bundle bundle) {
        final Context applicationContext = getActivity().getApplicationContext();
        final ContentResolver contentResolver = getActivity().getContentResolver();
        return new AlertDialog.Builder(getActivity()).setTitle(R.string.clearFrequentsConfirmation_title).setMessage(R.string.clearFrequentsConfirmation).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).setPositiveButton(17039370, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (PermissionsUtil.hasContactsReadPermissions(applicationContext)) {
                    final ProgressDialog show = ProgressDialog.show(ClearFrequentsDialog.this.getContext(), ClearFrequentsDialog.this.getString(R.string.clearFrequentsProgress_title), (CharSequence) null, true, true);
                    new AsyncTask<Void, Void, Void>() {
                        /* access modifiers changed from: protected */
                        public Object doInBackground(Object[] objArr) {
                            Void[] voidArr = (Void[]) objArr;
                            contentResolver.delete(ContactsContract.DataUsageFeedback.DELETE_USAGE_URI, (String) null, (String[]) null);
                            return null;
                        }

                        /* access modifiers changed from: protected */
                        public void onPostExecute(Object obj) {
                            Void voidR = (Void) obj;
                            show.dismiss();
                        }
                    }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                }
            }
        }).setCancelable(true).create();
    }
}
