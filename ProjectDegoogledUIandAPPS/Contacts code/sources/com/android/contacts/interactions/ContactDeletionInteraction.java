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
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.android.contacts.ContactSaveService;
import com.android.contacts.R;
import com.android.contacts.model.AccountTypeManager;
import com.android.contacts.model.account.AccountType;
import com.android.contacts.preference.ContactsPreferences;
import com.android.contacts.util.ContactDisplayUtils;
import com.google.common.collect.Sets;
import java.util.HashSet;

public class ContactDeletionInteraction extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>, DialogInterface.OnDismissListener {
    public static final String ARG_CONTACT_URI = "contactUri";
    private static final int COLUMN_INDEX_ACCOUNT_TYPE = 1;
    private static final int COLUMN_INDEX_CONTACT_ID = 3;
    private static final int COLUMN_INDEX_DATA_SET = 2;
    private static final int COLUMN_INDEX_DISPLAY_NAME = 5;
    private static final int COLUMN_INDEX_DISPLAY_NAME_ALT = 6;
    private static final int COLUMN_INDEX_LOOKUP_KEY = 4;
    private static final int COLUMN_INDEX_RAW_CONTACT_ID = 0;
    private static final String[] ENTITY_PROJECTION = {"raw_contact_id", "account_type", "data_set", "contact_id", "lookup", "display_name", "display_name_alt"};
    private static final String FRAGMENT_TAG = "deleteContact";
    private static final String KEY_ACTIVE = "active";
    private static final String KEY_CONTACT_URI = "contactUri";
    private static final String KEY_FINISH_WHEN_DONE = "finishWhenDone";
    public static final int RESULT_CODE_DELETED = 3;
    private static final String TAG = "ContactDeletion";
    private boolean mActive;
    private Uri mContactUri;
    private Context mContext;
    private AlertDialog mDialog;
    private String mDisplayName;
    private String mDisplayNameAlt;
    private boolean mFinishActivityWhenDone;
    int mMessageId;
    private TestLoaderManagerBase mTestLoaderManager;

    public void onLoaderReset(Loader<Cursor> loader) {
    }

    public static ContactDeletionInteraction start(Activity activity, Uri uri, boolean z) {
        return startWithTestLoaderManager(activity, uri, z, (TestLoaderManagerBase) null);
    }

    static ContactDeletionInteraction startWithTestLoaderManager(Activity activity, Uri uri, boolean z, TestLoaderManagerBase testLoaderManagerBase) {
        if (uri == null || activity.isDestroyed()) {
            return null;
        }
        FragmentManager fragmentManager = activity.getFragmentManager();
        ContactDeletionInteraction contactDeletionInteraction = (ContactDeletionInteraction) fragmentManager.findFragmentByTag(FRAGMENT_TAG);
        if (contactDeletionInteraction == null) {
            ContactDeletionInteraction contactDeletionInteraction2 = new ContactDeletionInteraction();
            contactDeletionInteraction2.setTestLoaderManager(testLoaderManagerBase);
            contactDeletionInteraction2.setContactUri(uri);
            contactDeletionInteraction2.setFinishActivityWhenDone(z);
            fragmentManager.beginTransaction().add(contactDeletionInteraction2, FRAGMENT_TAG).commitAllowingStateLoss();
            return contactDeletionInteraction2;
        }
        contactDeletionInteraction.setTestLoaderManager(testLoaderManagerBase);
        contactDeletionInteraction.setContactUri(uri);
        contactDeletionInteraction.setFinishActivityWhenDone(z);
        return contactDeletionInteraction;
    }

    public LoaderManager getLoaderManager() {
        LoaderManager loaderManager = super.getLoaderManager();
        TestLoaderManagerBase testLoaderManagerBase = this.mTestLoaderManager;
        if (testLoaderManagerBase == null) {
            return loaderManager;
        }
        testLoaderManagerBase.setDelegate(loaderManager);
        return this.mTestLoaderManager;
    }

    private void setTestLoaderManager(TestLoaderManagerBase testLoaderManagerBase) {
        this.mTestLoaderManager = testLoaderManagerBase;
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

    public void setContactUri(Uri uri) {
        this.mContactUri = uri;
        this.mActive = true;
        if (isStarted()) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("contactUri", this.mContactUri);
            getLoaderManager().restartLoader(R.id.dialog_delete_contact_loader_id, bundle, this);
        }
    }

    private void setFinishActivityWhenDone(boolean z) {
        this.mFinishActivityWhenDone = z;
    }

    /* access modifiers changed from: package-private */
    public boolean isStarted() {
        return isAdded();
    }

    public void onStart() {
        if (this.mActive) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("contactUri", this.mContactUri);
            getLoaderManager().initLoader(R.id.dialog_delete_contact_loader_id, bundle, this);
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
        return new CursorLoader(this.mContext, Uri.withAppendedPath((Uri) bundle.getParcelable("contactUri"), "entities"), ENTITY_PROJECTION, (String) null, (String[]) null, (String) null);
    }

    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        AlertDialog alertDialog = this.mDialog;
        String str = null;
        if (alertDialog != null) {
            alertDialog.dismiss();
            this.mDialog = null;
        }
        if (this.mActive) {
            if (cursor == null || cursor.isClosed()) {
                Log.e(TAG, "Failed to load contacts");
                return;
            }
            long j = 0;
            HashSet newHashSet = Sets.newHashSet();
            HashSet newHashSet2 = Sets.newHashSet();
            AccountTypeManager instance = AccountTypeManager.getInstance(getActivity());
            cursor.moveToPosition(-1);
            while (cursor.moveToNext()) {
                boolean z = false;
                long j2 = cursor.getLong(0);
                String string = cursor.getString(1);
                String string2 = cursor.getString(2);
                long j3 = cursor.getLong(3);
                String string3 = cursor.getString(4);
                this.mDisplayName = cursor.getString(5);
                this.mDisplayNameAlt = cursor.getString(6);
                AccountType accountType = instance.getAccountType(string, string2);
                if (accountType == null || accountType.areContactsWritable()) {
                    z = true;
                }
                if (z) {
                    newHashSet2.add(Long.valueOf(j2));
                } else {
                    newHashSet.add(Long.valueOf(j2));
                }
                j = j3;
                str = string3;
            }
            if (TextUtils.isEmpty(str)) {
                Log.e(TAG, "Failed to find contact lookup key");
                getActivity().finish();
                return;
            }
            int size = newHashSet.size();
            int size2 = newHashSet2.size();
            int i = 17039370;
            if (size > 0 && size2 > 0) {
                this.mMessageId = R.string.readOnlyContactDeleteConfirmation;
            } else if (size <= 0 || size2 != 0) {
                if (size != 0 || size2 <= 1) {
                    this.mMessageId = R.string.deleteConfirmation;
                } else {
                    this.mMessageId = R.string.multipleContactDeleteConfirmation;
                }
                i = R.string.deleteConfirmation_positive_button;
            } else {
                this.mMessageId = R.string.readOnlyContactWarning;
                i = R.string.readOnlyContactWarning_positive_button;
            }
            showDialog(this.mMessageId, i, ContactsContract.Contacts.getLookupUri(j, str));
            getLoaderManager().destroyLoader(R.id.dialog_delete_contact_loader_id);
        }
    }

    private void showDialog(int i, int i2, final Uri uri) {
        this.mDialog = new AlertDialog.Builder(getActivity()).setIconAttribute(16843605).setMessage(i).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).setPositiveButton(i2, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ContactDeletionInteraction.this.doDeleteContact(uri);
            }
        }).create();
        this.mDialog.setOnDismissListener(this);
        this.mDialog.show();
    }

    public void onDismiss(DialogInterface dialogInterface) {
        this.mActive = false;
        this.mDialog = null;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(KEY_ACTIVE, this.mActive);
        bundle.putParcelable("contactUri", this.mContactUri);
        bundle.putBoolean(KEY_FINISH_WHEN_DONE, this.mFinishActivityWhenDone);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (bundle != null) {
            this.mActive = bundle.getBoolean(KEY_ACTIVE);
            this.mContactUri = (Uri) bundle.getParcelable("contactUri");
            this.mFinishActivityWhenDone = bundle.getBoolean(KEY_FINISH_WHEN_DONE);
        }
    }

    /* access modifiers changed from: protected */
    public void doDeleteContact(Uri uri) {
        String str;
        Context context = this.mContext;
        context.startService(ContactSaveService.createDeleteContactIntent(context, uri));
        if (isAdded() && this.mFinishActivityWhenDone) {
            getActivity().setResult(3);
            getActivity().finish();
            String preferredDisplayName = ContactDisplayUtils.getPreferredDisplayName(this.mDisplayName, this.mDisplayNameAlt, new ContactsPreferences(this.mContext));
            if (TextUtils.isEmpty(preferredDisplayName)) {
                str = getResources().getQuantityString(R.plurals.contacts_deleted_toast, 1);
            } else {
                str = getResources().getString(R.string.contacts_deleted_one_named_toast, new Object[]{preferredDisplayName});
            }
            Toast.makeText(this.mContext, str, 1).show();
        }
    }
}
