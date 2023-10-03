package com.android.dialer.app.calllog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.CallLog;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.common.concurrent.DefaultDialerExecutorFactory;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.enrichedcall.EnrichedCallComponent;
import com.android.dialer.enrichedcall.stub.EnrichedCallManagerStub;
import com.android.dialer.phonenumbercache.PhoneNumberCache;

public class ClearCallLogDialog extends DialogFragment {
    private DialerExecutor<Void> clearCallLogTask;
    private ProgressDialog progressDialog;

    private static class ClearCallLogWorker implements DialerExecutor.Worker<Void, Void> {
        private final Context appContext;

        /* synthetic */ ClearCallLogWorker(Context context, C03161 r2) {
            this.appContext = context;
        }

        public Object doInBackground(Object obj) throws Throwable {
            Void voidR = (Void) obj;
            this.appContext.getContentResolver().delete(CallLog.Calls.CONTENT_URI, (String) null, (String[]) null);
            PhoneNumberCache.get(this.appContext).getCachedNumberLookupService();
            return null;
        }
    }

    /* access modifiers changed from: private */
    public void onSuccess(Void voidR) {
        Assert.isNotNull(this.progressDialog);
        Activity ownerActivity = this.progressDialog.getOwnerActivity();
        if (ownerActivity != null && !ownerActivity.isDestroyed() && !ownerActivity.isFinishing()) {
            ((EnrichedCallManagerStub) EnrichedCallComponent.get(ownerActivity).getEnrichedCallManager()).hasStoredData();
            ProgressDialog progressDialog2 = this.progressDialog;
            if (progressDialog2 != null && progressDialog2.isShowing()) {
                this.progressDialog.dismiss();
            }
        }
    }

    public /* synthetic */ void lambda$onCreateDialog$0$ClearCallLogDialog(DialogInterface dialogInterface, int i) {
        this.progressDialog = ProgressDialog.show(getActivity(), getString(R.string.clearCallLogProgress_title), "", true, false);
        this.progressDialog.setOwnerActivity(getActivity());
        CallLogNotificationsService.cancelAllMissedCalls(getContext());
        this.progressDialog.show();
        this.clearCallLogTask.executeSerial(null);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.clearCallLogTask = ((DefaultDialerExecutorFactory) DialerExecutorComponent.get(getContext()).dialerExecutorFactory()).createUiTaskBuilder(getFragmentManager(), "clearCallLogTask", new ClearCallLogWorker(getActivity().getApplicationContext(), (C03161) null)).onSuccess(new DialerExecutor.SuccessListener() {
            public final void onSuccess(Object obj) {
                ClearCallLogDialog.this.onSuccess((Void) obj);
            }
        }).build();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        return new AlertDialog.Builder(getActivity()).setTitle(R.string.clearCallLogConfirmation_title).setIconAttribute(16843605).setMessage(R.string.clearCallLogConfirmation).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).setPositiveButton(17039370, new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                ClearCallLogDialog.this.lambda$onCreateDialog$0$ClearCallLogDialog(dialogInterface, i);
            }
        }).setCancelable(true).create();
    }
}
