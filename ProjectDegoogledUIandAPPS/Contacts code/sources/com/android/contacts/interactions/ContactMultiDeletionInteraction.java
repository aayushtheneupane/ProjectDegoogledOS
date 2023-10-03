package com.android.contacts.interactions;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import com.android.contacts.ContactSaveService;
import com.android.contacts.R;
import com.android.contacts.model.AccountTypeManager;
import com.android.contacts.model.account.AccountType;
import com.android.contacts.preference.ContactsPreferences;
import com.android.contacts.util.ContactDisplayUtils;
import com.google.common.collect.Sets;
import java.util.HashSet;
import java.util.TreeSet;

public class ContactMultiDeletionInteraction extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String[] RAW_CONTACT_PROJECTION = {"_id", "account_type", "data_set", "contact_id", "display_name", "display_name_alt"};
    private TreeSet<Long> mContactIds;
    private Context mContext;
    /* access modifiers changed from: private */
    public AlertDialog mDialog;
    /* access modifiers changed from: private */
    public boolean mIsLoaderActive;
    private MultiContactDeleteListener mListener;

    public interface MultiContactDeleteListener {
        void onDeletionFinished();
    }

    public void onLoaderReset(Loader<Cursor> loader) {
    }

    public static ContactMultiDeletionInteraction start(Fragment fragment, TreeSet<Long> treeSet) {
        if (treeSet == null) {
            return null;
        }
        FragmentManager fragmentManager = fragment.getFragmentManager();
        ContactMultiDeletionInteraction contactMultiDeletionInteraction = (ContactMultiDeletionInteraction) fragmentManager.findFragmentByTag(ContactSaveService.ACTION_DELETE_MULTIPLE_CONTACTS);
        if (contactMultiDeletionInteraction == null) {
            ContactMultiDeletionInteraction contactMultiDeletionInteraction2 = new ContactMultiDeletionInteraction();
            contactMultiDeletionInteraction2.setContactIds(treeSet);
            fragmentManager.beginTransaction().add(contactMultiDeletionInteraction2, ContactSaveService.ACTION_DELETE_MULTIPLE_CONTACTS).commitAllowingStateLoss();
            return contactMultiDeletionInteraction2;
        }
        contactMultiDeletionInteraction.setContactIds(treeSet);
        return contactMultiDeletionInteraction;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mContext = activity;
    }

    public void onDestroyView() {
        super.onDestroyView();
        AlertDialog alertDialog = this.mDialog;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.mDialog.setOnDismissListener((DialogInterface.OnDismissListener) null);
            this.mDialog.dismiss();
            this.mDialog = null;
        }
    }

    public void setContactIds(TreeSet<Long> treeSet) {
        this.mContactIds = treeSet;
        this.mIsLoaderActive = true;
        if (isStarted()) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(ContactSaveService.EXTRA_CONTACT_IDS, this.mContactIds);
            getLoaderManager().restartLoader(R.id.dialog_delete_multiple_contact_loader_id, bundle, this);
        }
    }

    private boolean isStarted() {
        return isAdded();
    }

    public void onStart() {
        if (this.mIsLoaderActive) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(ContactSaveService.EXTRA_CONTACT_IDS, this.mContactIds);
            getLoaderManager().initLoader(R.id.dialog_delete_multiple_contact_loader_id, bundle, this);
        }
        super.onStart();
    }

    public void onStop() {
        super.onStop();
        AlertDialog alertDialog = this.mDialog;
        if (alertDialog != null) {
            alertDialog.hide();
        }
    }

    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        TreeSet treeSet = (TreeSet) bundle.getSerializable(ContactSaveService.EXTRA_CONTACT_IDS);
        Object[] array = treeSet.toArray();
        String[] strArr = new String[treeSet.size()];
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < treeSet.size(); i2++) {
            strArr[i2] = String.valueOf(array[i2]);
            sb.append("contact_id =?");
            if (i2 == treeSet.size() - 1) {
                break;
            }
            sb.append(" OR ");
        }
        return new CursorLoader(this.mContext, ContactsContract.RawContacts.CONTENT_URI, RAW_CONTACT_PROJECTION, sb.toString(), strArr, (String) null);
    }

    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        int i;
        Cursor cursor2 = cursor;
        AlertDialog alertDialog = this.mDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
            this.mDialog = null;
        }
        if (this.mIsLoaderActive) {
            if (cursor2 == null || cursor.isClosed()) {
                Log.e("ContactMultiDeletion", "Failed to load contacts");
                return;
            }
            HashSet newHashSet = Sets.newHashSet();
            HashSet newHashSet2 = Sets.newHashSet();
            HashSet newHashSet3 = Sets.newHashSet();
            HashSet newHashSet4 = Sets.newHashSet();
            ContactsPreferences contactsPreferences = new ContactsPreferences(this.mContext);
            AccountTypeManager instance = AccountTypeManager.getInstance(getActivity());
            cursor2.moveToPosition(-1);
            while (cursor.moveToNext()) {
                long j = cursor2.getLong(0);
                String string = cursor2.getString(1);
                String string2 = cursor2.getString(2);
                long j2 = cursor2.getLong(3);
                String preferredDisplayName = ContactDisplayUtils.getPreferredDisplayName(cursor2.getString(4), cursor2.getString(5), contactsPreferences);
                if (!TextUtils.isEmpty(preferredDisplayName)) {
                    newHashSet4.add(preferredDisplayName);
                }
                newHashSet3.add(Long.valueOf(j2));
                AccountType accountType = instance.getAccountType(string, string2);
                if (accountType == null || accountType.areContactsWritable()) {
                    newHashSet2.add(Long.valueOf(j));
                } else {
                    newHashSet.add(Long.valueOf(j));
                }
            }
            int size = newHashSet.size();
            int size2 = newHashSet2.size();
            int i2 = 17039370;
            if (size > 0 && size2 > 0) {
                i = R.string.batch_delete_multiple_accounts_confirmation;
            } else if (size <= 0 || size2 != 0) {
                i = size2 == 1 ? R.string.single_delete_confirmation : R.string.batch_delete_confirmation;
                i2 = R.string.deleteConfirmation_positive_button;
            } else {
                i = R.string.batch_delete_read_only_contact_confirmation;
                i2 = R.string.readOnlyContactWarning_positive_button;
            }
            Long[] lArr = (Long[]) newHashSet3.toArray(new Long[newHashSet3.size()]);
            long[] jArr = new long[newHashSet3.size()];
            for (int i3 = 0; i3 < newHashSet3.size(); i3++) {
                jArr[i3] = lArr[i3].longValue();
            }
            showDialog(i, i2, jArr, (String[]) newHashSet4.toArray(new String[newHashSet4.size()]));
            getLoaderManager().destroyLoader(R.id.dialog_delete_multiple_contact_loader_id);
        }
    }

    private void showDialog(int i, int i2, final long[] jArr, final String[] strArr) {
        this.mDialog = new AlertDialog.Builder(getActivity()).setIconAttribute(16843605).setMessage(i).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).setPositiveButton(i2, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ContactMultiDeletionInteraction.this.doDeleteContact(jArr, strArr);
            }
        }).create();
        this.mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                boolean unused = ContactMultiDeletionInteraction.this.mIsLoaderActive = false;
                AlertDialog unused2 = ContactMultiDeletionInteraction.this.mDialog = null;
            }
        });
        this.mDialog.show();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("active", this.mIsLoaderActive);
        bundle.putSerializable(ContactSaveService.EXTRA_CONTACT_IDS, this.mContactIds);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (bundle != null) {
            this.mIsLoaderActive = bundle.getBoolean("active");
            this.mContactIds = (TreeSet) bundle.getSerializable(ContactSaveService.EXTRA_CONTACT_IDS);
        }
    }

    /* access modifiers changed from: protected */
    public void doDeleteContact(long[] jArr, String[] strArr) {
        Context context = this.mContext;
        context.startService(ContactSaveService.createDeleteMultipleContactsIntent(context, jArr, strArr));
        this.mListener.onDeletionFinished();
    }

    public void setListener(MultiContactDeleteListener multiContactDeleteListener) {
        this.mListener = multiContactDeleteListener;
    }
}
