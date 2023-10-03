package com.android.contacts;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.android.contacts.activities.RequestPermissionsActivity;
import com.android.contacts.util.ImplicitIntentsUtil;

public class NonPhoneActivity extends ContactsActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        RequestPermissionsActivity.startPermissionActivityIfNeeded(this);
        String phoneNumber = getPhoneNumber();
        if (TextUtils.isEmpty(phoneNumber)) {
            finish();
            return;
        }
        NonPhoneDialogFragment nonPhoneDialogFragment = new NonPhoneDialogFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putString("PHONE_NUMBER", phoneNumber);
        nonPhoneDialogFragment.setArguments(bundle2);
        getFragmentManager().beginTransaction().add(nonPhoneDialogFragment, "Fragment").commitAllowingStateLoss();
    }

    private String getPhoneNumber() {
        Uri data;
        if (getIntent() == null || (data = getIntent().getData()) == null || !"tel".equals(data.getScheme())) {
            return null;
        }
        return getIntent().getData().getSchemeSpecificPart();
    }

    public static final class NonPhoneDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {
        public Dialog onCreateDialog(Bundle bundle) {
            AlertDialog create = new AlertDialog.Builder(getActivity(), R.style.NonPhoneDialogTheme).create();
            create.setTitle(R.string.non_phone_caption);
            create.setMessage(getArgumentPhoneNumber());
            create.setButton(-1, getActivity().getString(R.string.non_phone_add_to_contacts), this);
            create.setButton(-2, getActivity().getString(R.string.non_phone_close), this);
            return create;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            if (i == -1) {
                Intent intent = new Intent("android.intent.action.INSERT_OR_EDIT");
                intent.setType("vnd.android.cursor.item/contact");
                intent.putExtra("phone", getArgumentPhoneNumber());
                ImplicitIntentsUtil.startActivityInApp(getActivity(), intent);
            }
            dismiss();
        }

        private String getArgumentPhoneNumber() {
            return getArguments().getString("PHONE_NUMBER");
        }

        public void onDismiss(DialogInterface dialogInterface) {
            super.onDismiss(dialogInterface);
            Activity activity = getActivity();
            if (activity != null) {
                activity.finish();
            }
        }
    }
}
