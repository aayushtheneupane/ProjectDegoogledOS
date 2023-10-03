package com.android.settings.applications.specialaccess.zenaccess;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.appcompat.app.AlertDialog;
import com.android.settings.core.instrumentation.InstrumentedDialogFragment;
import com.havoc.config.center.C1715R;

public class ScaryWarningDialogFragment extends InstrumentedDialogFragment {
    static /* synthetic */ void lambda$onCreateDialog$1(DialogInterface dialogInterface, int i) {
    }

    public int getMetricsCategory() {
        return 554;
    }

    public ScaryWarningDialogFragment setPkgInfo(String str, CharSequence charSequence) {
        Bundle bundle = new Bundle();
        bundle.putString("p", str);
        if (!TextUtils.isEmpty(charSequence)) {
            str = charSequence.toString();
        }
        bundle.putString("l", str);
        setArguments(bundle);
        return this;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        String string = arguments.getString("p");
        String string2 = arguments.getString("l");
        String string3 = getResources().getString(C1715R.string.zen_access_warning_dialog_title, new Object[]{string2});
        String string4 = getResources().getString(C1715R.string.zen_access_warning_dialog_summary);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage((CharSequence) string4);
        builder.setTitle((CharSequence) string3);
        builder.setCancelable(true);
        builder.setPositiveButton((int) C1715R.string.allow, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener(string) {
            private final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                ScaryWarningDialogFragment.this.lambda$onCreateDialog$0$ScaryWarningDialogFragment(this.f$1, dialogInterface, i);
            }
        });
        builder.setNegativeButton((int) C1715R.string.deny, (DialogInterface.OnClickListener) $$Lambda$ScaryWarningDialogFragment$SbnKl27lVIbIA2Iw6eP0YSmWMao.INSTANCE);
        return builder.create();
    }

    public /* synthetic */ void lambda$onCreateDialog$0$ScaryWarningDialogFragment(String str, DialogInterface dialogInterface, int i) {
        ZenAccessController.setAccess(getContext(), str, true);
    }
}
