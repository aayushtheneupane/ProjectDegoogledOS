package com.android.incallui.answer.impl;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p002v7.app.AppCompatDialog;
import android.support.p002v7.app.AppCompatDialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.android.dialer.R;
import com.android.dialer.common.FragmentUtils;
import com.android.incallui.incalluilock.InCallUiLock;

public class CreateCustomSmsDialogFragment extends AppCompatDialogFragment {
    /* access modifiers changed from: private */
    public EditText editText;
    private InCallUiLock inCallUiLock;

    public interface CreateCustomSmsHolder {
    }

    public Dialog onCreateDialog(Bundle bundle) {
        new AppCompatDialog(getContext(), getTheme());
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View inflate = View.inflate(builder.getContext(), R.layout.fragment_custom_sms_dialog, (ViewGroup) null);
        this.editText = (EditText) inflate.findViewById(R.id.custom_sms_input);
        if (bundle != null) {
            this.editText.setText(bundle.getCharSequence("enteredText"));
        }
        this.inCallUiLock = ((AnswerFragment) FragmentUtils.getParentUnsafe((Fragment) this, CreateCustomSmsHolder.class)).acquireInCallUiLock("CreateCustomSmsDialogFragment");
        builder.setCancelable(true).setView(inflate).setPositiveButton(R.string.call_incoming_custom_message_send, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ((AnswerFragment) FragmentUtils.getParentUnsafe((Fragment) CreateCustomSmsDialogFragment.this, CreateCustomSmsHolder.class)).customSmsCreated(CreateCustomSmsDialogFragment.this.editText.getText().toString().trim());
                CreateCustomSmsDialogFragment.this.dismiss();
            }
        }).setNegativeButton(R.string.call_incoming_custom_message_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                CreateCustomSmsDialogFragment.this.dismiss();
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                CreateCustomSmsDialogFragment.this.dismiss();
            }
        }).setTitle(R.string.call_incoming_respond_via_sms_custom_message);
        final AlertDialog create = builder.create();
        create.setOnShowListener(new DialogInterface.OnShowListener(this) {
            public void onShow(DialogInterface dialogInterface) {
                ((AlertDialog) dialogInterface).getButton(-1).setEnabled(false);
            }
        });
        this.editText.addTextChangedListener(new TextWatcher(this) {
            public void afterTextChanged(Editable editable) {
                create.getButton(-1).setEnabled((editable == null || editable.toString().trim().length() == 0) ? false : true);
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        create.getWindow().setSoftInputMode(5);
        create.getWindow().addFlags(524288);
        return create;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        this.inCallUiLock.release();
        ((AnswerFragment) FragmentUtils.getParentUnsafe((Fragment) this, CreateCustomSmsHolder.class)).customSmsDismissed();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putCharSequence("enteredText", this.editText.getText());
    }
}
