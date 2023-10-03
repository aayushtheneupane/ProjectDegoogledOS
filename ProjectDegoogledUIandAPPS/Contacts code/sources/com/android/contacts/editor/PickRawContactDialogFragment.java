package com.android.contacts.editor;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.android.contacts.ContactPhotoManager;
import com.android.contacts.R;
import com.android.contacts.activities.ContactSelectionActivity;
import com.android.contacts.editor.PickRawContactLoader;
import com.android.contacts.logging.Logger;
import com.android.contacts.model.AccountTypeManager;
import com.android.contacts.model.account.AccountType;
import com.android.contacts.model.account.AccountWithDataSet;
import com.android.contacts.model.account.GoogleAccountType;
import com.android.contacts.preference.ContactsPreferences;

public class PickRawContactDialogFragment extends DialogFragment {
    /* access modifiers changed from: private */
    public ListAdapter mAdapter;
    /* access modifiers changed from: private */
    public boolean mShouldFinishActivity = true;

    public interface PickRawContactListener {
        void onPickRawContact(long j);
    }

    private final class RawContactAccountListAdapter extends BaseAdapter {
        private final AccountTypeManager mAccountTypeManager;
        private final Context mContext;
        private final LayoutInflater mInflater;
        private final ContactsPreferences mPreferences;
        private final PickRawContactLoader.RawContactsMetadata mRawContactsMetadata;

        public RawContactAccountListAdapter(Context context, PickRawContactLoader.RawContactsMetadata rawContactsMetadata) {
            this.mContext = context;
            this.mInflater = LayoutInflater.from(context);
            this.mAccountTypeManager = AccountTypeManager.getInstance(context);
            this.mPreferences = new ContactsPreferences(context);
            this.mRawContactsMetadata = rawContactsMetadata;
        }

        public int getCount() {
            return this.mRawContactsMetadata.rawContacts.size();
        }

        public Object getItem(int i) {
            return this.mRawContactsMetadata.rawContacts.get(i);
        }

        public long getItemId(int i) {
            return this.mRawContactsMetadata.rawContacts.get(i).f10id;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            RawContactViewHolder rawContactViewHolder;
            String str;
            if (view == null) {
                view = this.mInflater.inflate(R.layout.raw_contact_list_item, viewGroup, false);
                rawContactViewHolder = new RawContactViewHolder();
                rawContactViewHolder.displayName = (TextView) view.findViewById(R.id.display_name);
                rawContactViewHolder.accountName = (TextView) view.findViewById(R.id.account_name);
                rawContactViewHolder.accountIcon = (ImageView) view.findViewById(R.id.account_icon);
                rawContactViewHolder.photo = (ImageView) view.findViewById(R.id.photo);
                view.setTag(rawContactViewHolder);
            } else {
                rawContactViewHolder = (RawContactViewHolder) view.getTag();
            }
            PickRawContactLoader.RawContact rawContact = this.mRawContactsMetadata.rawContacts.get(i);
            AccountType accountType = this.mAccountTypeManager.getAccountType(rawContact.accountType, rawContact.accountDataSet);
            String str2 = this.mPreferences.getDisplayOrder() == 1 ? rawContact.displayName : rawContact.displayNameAlt;
            if (TextUtils.isEmpty(str2)) {
                str2 = this.mContext.getString(R.string.missing_name);
            }
            rawContactViewHolder.displayName.setText(str2);
            if (this.mRawContactsMetadata.isUserProfile && accountType.areContactsWritable()) {
                str = EditorUiUtils.getAccountHeaderLabelForMyProfile(this.mContext, AccountTypeManager.getInstance(PickRawContactDialogFragment.this.getContext()).getAccountInfoForAccount(new AccountWithDataSet(rawContact.accountName, rawContact.accountType, rawContact.accountDataSet)));
            } else if (!GoogleAccountType.ACCOUNT_TYPE.equals(rawContact.accountType) || accountType.dataSet != null) {
                str = accountType.getDisplayLabel(this.mContext).toString();
            } else {
                str = rawContact.accountName;
            }
            rawContactViewHolder.accountName.setText(str);
            rawContactViewHolder.accountIcon.setImageDrawable(accountType.getDisplayIcon(this.mContext));
            ContactPhotoManager.getInstance(this.mContext).loadThumbnail(rawContactViewHolder.photo, rawContact.photoId, false, true, new ContactPhotoManager.DefaultImageRequest(str2, String.valueOf(rawContact.f10id), true));
            return view;
        }

        class RawContactViewHolder {
            ImageView accountIcon;
            TextView accountName;
            TextView displayName;
            ImageView photo;

            RawContactViewHolder() {
            }
        }
    }

    public static PickRawContactDialogFragment getInstance(PickRawContactLoader.RawContactsMetadata rawContactsMetadata) {
        PickRawContactDialogFragment pickRawContactDialogFragment = new PickRawContactDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("rawContactsMetadata", rawContactsMetadata);
        pickRawContactDialogFragment.setArguments(bundle);
        return pickRawContactDialogFragment;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        if (getActivity() instanceof PickRawContactListener) {
            Bundle arguments = getArguments();
            if (arguments != null) {
                final PickRawContactLoader.RawContactsMetadata rawContactsMetadata = (PickRawContactLoader.RawContactsMetadata) arguments.getParcelable("rawContactsMetadata");
                if (rawContactsMetadata != null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    this.mAdapter = new RawContactAccountListAdapter(getContext(), rawContactsMetadata);
                    if (rawContactsMetadata.showReadOnly) {
                        builder.setTitle(R.string.contact_editor_pick_linked_contact_dialog_title);
                        if (!rawContactsMetadata.isUserProfile) {
                            builder.setPositiveButton(R.string.contact_editor_add_linked_contact, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    boolean unused = PickRawContactDialogFragment.this.mShouldFinishActivity = false;
                                    Intent intent = new Intent(PickRawContactDialogFragment.this.getActivity(), ContactSelectionActivity.class);
                                    intent.setAction("com.android.contacts.action.JOIN_CONTACT");
                                    intent.putExtra("com.android.contacts.action.CONTACT_ID", rawContactsMetadata.contactId);
                                    PickRawContactDialogFragment.this.getActivity().startActivityForResult(intent, 3);
                                }
                            });
                            builder.setNegativeButton(R.string.contact_editor_unlink_contacts, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    boolean unused = PickRawContactDialogFragment.this.mShouldFinishActivity = false;
                                    new SplitContactConfirmationDialogFragment().show(PickRawContactDialogFragment.this.getActivity().getFragmentManager(), "SplitConfirmation");
                                }
                            });
                        }
                    } else {
                        builder.setTitle(R.string.contact_editor_pick_raw_contact_to_edit_dialog_title);
                    }
                    builder.setAdapter(this.mAdapter, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ((PickRawContactListener) PickRawContactDialogFragment.this.getActivity()).onPickRawContact(PickRawContactDialogFragment.this.mAdapter.getItemId(i));
                        }
                    });
                    builder.setCancelable(true);
                    if (bundle == null) {
                        Logger.logEditorEvent(1, this.mAdapter.getCount());
                    }
                    return builder.create();
                }
                throw new IllegalArgumentException("Dialog created with null RawContactsMetadata");
            }
            throw new IllegalArgumentException("Dialog created with no arguments");
        }
        throw new IllegalArgumentException("Host activity doesn't implement PickRawContactListener");
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        if (this.mShouldFinishActivity) {
            finishActivity();
        }
    }

    public Context getContext() {
        return getActivity();
    }

    private void finishActivity() {
        if (getActivity() != null && !getActivity().isFinishing()) {
            getActivity().finish();
        }
    }
}
