package com.android.contacts.interactions;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import com.android.contacts.ContactSaveService;
import com.android.contacts.R;
import com.android.contacts.activities.PeopleActivity;

public class GroupDeletionDialogFragment extends DialogFragment {
    public static void show(FragmentManager fragmentManager, long j, String str) {
        GroupDeletionDialogFragment groupDeletionDialogFragment = new GroupDeletionDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(ContactSaveService.EXTRA_GROUP_ID, j);
        bundle.putString("label", str);
        groupDeletionDialogFragment.setArguments(bundle);
        groupDeletionDialogFragment.show(fragmentManager, "deleteGroup");
    }

    public Dialog onCreateDialog(Bundle bundle) {
        String string = getArguments().getString("label");
        return new AlertDialog.Builder(getActivity()).setIconAttribute(16843605).setMessage(getActivity().getString(R.string.delete_group_dialog_message, new Object[]{string})).setPositiveButton(17039370, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                GroupDeletionDialogFragment.this.deleteGroup();
            }
        }).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).create();
    }

    /* access modifiers changed from: protected */
    public void deleteGroup() {
        long j = getArguments().getLong(ContactSaveService.EXTRA_GROUP_ID);
        PeopleActivity peopleActivity = (PeopleActivity) getActivity();
        peopleActivity.startService(ContactSaveService.createGroupDeletionIntent(getActivity(), j));
        peopleActivity.switchToAllContacts();
    }
}
