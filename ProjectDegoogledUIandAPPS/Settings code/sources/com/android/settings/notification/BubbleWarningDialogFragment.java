package com.android.settings.notification;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import com.android.settings.core.instrumentation.InstrumentedDialogFragment;
import com.havoc.config.center.C1715R;

public class BubbleWarningDialogFragment extends InstrumentedDialogFragment {
    public int getMetricsCategory() {
        return 1702;
    }

    public BubbleWarningDialogFragment setPkgInfo(String str, int i) {
        Bundle bundle = new Bundle();
        bundle.putString("p", str);
        bundle.putInt("u", i);
        setArguments(bundle);
        return this;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        String string = arguments.getString("p");
        int i = arguments.getInt("u");
        String string2 = getResources().getString(C1715R.string.bubbles_feature_disabled_dialog_title);
        String string3 = getResources().getString(C1715R.string.bubbles_feature_disabled_dialog_text);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage((CharSequence) string3);
        builder.setTitle((CharSequence) string2);
        builder.setCancelable(true);
        builder.setPositiveButton((int) C1715R.string.bubbles_feature_disabled_button_approve, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener(string, i) {
            private final /* synthetic */ String f$1;
            private final /* synthetic */ int f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                BubbleWarningDialogFragment.this.lambda$onCreateDialog$0$BubbleWarningDialogFragment(this.f$1, this.f$2, dialogInterface, i);
            }
        });
        builder.setNegativeButton((int) C1715R.string.bubbles_feature_disabled_button_cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener(string, i) {
            private final /* synthetic */ String f$1;
            private final /* synthetic */ int f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                BubbleWarningDialogFragment.this.lambda$onCreateDialog$1$BubbleWarningDialogFragment(this.f$1, this.f$2, dialogInterface, i);
            }
        });
        return builder.create();
    }

    public /* synthetic */ void lambda$onCreateDialog$0$BubbleWarningDialogFragment(String str, int i, DialogInterface dialogInterface, int i2) {
        BubblePreferenceController.applyBubblesApproval(getContext(), str, i);
    }

    public /* synthetic */ void lambda$onCreateDialog$1$BubbleWarningDialogFragment(String str, int i, DialogInterface dialogInterface, int i2) {
        BubblePreferenceController.revertBubblesApproval(getContext(), str, i);
    }
}
