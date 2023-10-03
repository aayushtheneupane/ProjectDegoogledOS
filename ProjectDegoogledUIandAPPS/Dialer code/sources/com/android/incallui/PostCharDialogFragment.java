package com.android.incallui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.p000v4.app.DialogFragment;
import com.android.dialer.R;
import com.android.incallui.call.TelecomAdapter;

public class PostCharDialogFragment extends DialogFragment {
    /* access modifiers changed from: private */
    public String callId;
    private String postDialStr;

    public PostCharDialogFragment(String str, String str2) {
        this.callId = str;
        this.postDialStr = str2;
    }

    public void onCancel(DialogInterface dialogInterface) {
        TelecomAdapter.getInstance().postDialContinue(this.callId, false);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreateDialog(bundle);
        if (this.postDialStr == null && bundle != null) {
            this.callId = bundle.getString("CALL_ID");
            this.postDialStr = bundle.getString("POST_CHARS");
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getResources().getText(R.string.wait_prompt_str) + this.postDialStr);
        builder.setPositiveButton(R.string.pause_prompt_yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                TelecomAdapter.getInstance().postDialContinue(PostCharDialogFragment.this.callId, true);
            }
        });
        builder.setNegativeButton(R.string.pause_prompt_no, new DialogInterface.OnClickListener(this) {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        return builder.create();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("CALL_ID", this.callId);
        bundle.putString("POST_CHARS", this.postDialStr);
    }
}
