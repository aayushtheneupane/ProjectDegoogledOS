package com.android.settings.notification;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.BidiFormatter;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import com.android.settings.core.instrumentation.InstrumentedDialogFragment;
import com.havoc.config.center.C1715R;

public class ZenDeleteRuleDialog extends InstrumentedDialogFragment {
    protected static PositiveClickListener mPositiveClickListener;

    public interface PositiveClickListener {
        void onOk(String str);
    }

    public int getMetricsCategory() {
        return 1266;
    }

    public static void show(Fragment fragment, String str, String str2, PositiveClickListener positiveClickListener) {
        BidiFormatter instance = BidiFormatter.getInstance();
        Bundle bundle = new Bundle();
        bundle.putString("zen_rule_name", instance.unicodeWrap(str));
        bundle.putString("zen_rule_id", str2);
        mPositiveClickListener = positiveClickListener;
        ZenDeleteRuleDialog zenDeleteRuleDialog = new ZenDeleteRuleDialog();
        zenDeleteRuleDialog.setArguments(bundle);
        zenDeleteRuleDialog.setTargetFragment(fragment, 0);
        zenDeleteRuleDialog.show(fragment.getFragmentManager(), "ZenDeleteRuleDialog");
    }

    public Dialog onCreateDialog(Bundle bundle) {
        final Bundle arguments = getArguments();
        String string = arguments.getString("zen_rule_name");
        final String string2 = arguments.getString("zen_rule_id");
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage((CharSequence) getString(C1715R.string.zen_mode_delete_rule_confirmation, string));
        builder.setNegativeButton((int) C1715R.string.cancel, (DialogInterface.OnClickListener) null);
        builder.setPositiveButton((int) C1715R.string.zen_mode_delete_rule_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (arguments != null) {
                    ZenDeleteRuleDialog.mPositiveClickListener.onOk(string2);
                }
            }
        });
        AlertDialog create = builder.create();
        View findViewById = create.findViewById(16908299);
        if (findViewById != null) {
            findViewById.setTextDirection(5);
        }
        return create;
    }
}
