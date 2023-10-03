package com.android.contacts.group;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.android.contacts.ContactSaveService;
import com.android.contacts.R;
import com.android.contacts.group.GroupUtil;
import com.android.contacts.model.account.AccountWithDataSet;
import com.google.android.material.textfield.TextInputLayout;
import com.google.common.base.Strings;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class GroupNameEditDialogFragment extends DialogFragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private AccountWithDataSet mAccount;
    private Set<String> mExistingGroups = Collections.emptySet();
    private long mGroupId;
    /* access modifiers changed from: private */
    public String mGroupName;
    /* access modifiers changed from: private */
    public EditText mGroupNameEditText;
    /* access modifiers changed from: private */
    public TextInputLayout mGroupNameTextLayout;
    private boolean mIsInsert;
    private Listener mListener;

    public interface Listener {
        public static final Listener None = new Listener() {
            public void onGroupNameEditCancelled() {
            }

            public void onGroupNameEditCompleted(String str) {
            }
        };

        void onGroupNameEditCancelled();

        void onGroupNameEditCompleted(String str);
    }

    public void onLoaderReset(Loader<Cursor> loader) {
    }

    public static GroupNameEditDialogFragment newInstanceForCreation(AccountWithDataSet accountWithDataSet, String str) {
        return newInstance(accountWithDataSet, str, -1, (String) null);
    }

    public static GroupNameEditDialogFragment newInstanceForUpdate(AccountWithDataSet accountWithDataSet, String str, long j, String str2) {
        return newInstance(accountWithDataSet, str, j, str2);
    }

    private static GroupNameEditDialogFragment newInstance(AccountWithDataSet accountWithDataSet, String str, long j, String str2) {
        if (accountWithDataSet == null || accountWithDataSet.name == null || accountWithDataSet.type == null) {
            throw new IllegalArgumentException("Invalid account");
        }
        boolean z = j == -1;
        Bundle bundle = new Bundle();
        bundle.putBoolean("isInsert", z);
        bundle.putLong(ContactSaveService.EXTRA_GROUP_ID, j);
        bundle.putString("groupName", str2);
        bundle.putParcelable("account", accountWithDataSet);
        bundle.putString("callbackAction", str);
        GroupNameEditDialogFragment groupNameEditDialogFragment = new GroupNameEditDialogFragment();
        groupNameEditDialogFragment.setArguments(bundle);
        return groupNameEditDialogFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(0, R.style.ContactsAlertDialogThemeAppCompat);
        Bundle arguments = getArguments();
        if (bundle == null) {
            this.mGroupName = arguments.getString("groupName");
        } else {
            this.mGroupName = bundle.getString("groupName");
        }
        this.mGroupId = arguments.getLong(ContactSaveService.EXTRA_GROUP_ID, -1);
        this.mIsInsert = arguments.getBoolean("isInsert", true);
        this.mAccount = (AccountWithDataSet) getArguments().getParcelable("account");
        getLoaderManager().initLoader(0, (Bundle) null, this);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        TextView textView = (TextView) View.inflate(getActivity(), R.layout.dialog_title, (ViewGroup) null);
        textView.setText(this.mIsInsert ? R.string.group_name_dialog_insert_title : R.string.group_name_dialog_update_title);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), getTheme());
        builder.setCustomTitle(textView);
        builder.setView(R.layout.group_name_edit_dialog);
        builder.setNegativeButton(17039360, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                GroupNameEditDialogFragment.this.hideInputMethod();
                GroupNameEditDialogFragment.this.getListener().onGroupNameEditCancelled();
                GroupNameEditDialogFragment.this.dismiss();
            }
        });
        builder.setPositiveButton(17039370, (DialogInterface.OnClickListener) null);
        final AlertDialog create = builder.create();
        create.getWindow().setSoftInputMode(4);
        create.setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                EditText unused = GroupNameEditDialogFragment.this.mGroupNameEditText = (EditText) create.findViewById(16908308);
                TextInputLayout unused2 = GroupNameEditDialogFragment.this.mGroupNameTextLayout = (TextInputLayout) create.findViewById(R.id.text_input_layout);
                if (!TextUtils.isEmpty(GroupNameEditDialogFragment.this.mGroupName)) {
                    GroupNameEditDialogFragment.this.mGroupNameEditText.setText(GroupNameEditDialogFragment.this.mGroupName);
                    int integer = GroupNameEditDialogFragment.this.getResources().getInteger(R.integer.group_name_max_length);
                    EditText access$200 = GroupNameEditDialogFragment.this.mGroupNameEditText;
                    if (GroupNameEditDialogFragment.this.mGroupName.length() <= integer) {
                        integer = GroupNameEditDialogFragment.this.mGroupName.length();
                    }
                    access$200.setSelection(integer);
                }
                GroupNameEditDialogFragment groupNameEditDialogFragment = GroupNameEditDialogFragment.this;
                groupNameEditDialogFragment.showInputMethod(groupNameEditDialogFragment.mGroupNameEditText);
                final Button button = create.getButton(-1);
                button.setEnabled(!TextUtils.isEmpty(GroupNameEditDialogFragment.this.getGroupName()));
                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        GroupNameEditDialogFragment.this.maybePersistCurrentGroupName(view);
                    }
                });
                GroupNameEditDialogFragment.this.mGroupNameEditText.addTextChangedListener(new TextWatcher() {
                    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    }

                    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    }

                    public void afterTextChanged(Editable editable) {
                        GroupNameEditDialogFragment.this.mGroupNameTextLayout.setError((CharSequence) null);
                        button.setEnabled(!TextUtils.isEmpty(editable));
                    }
                });
            }
        });
        return create;
    }

    public void setListener(Listener listener) {
        this.mListener = listener;
    }

    private boolean hasNameChanged() {
        String nullToEmpty = Strings.nullToEmpty(getGroupName());
        return (this.mIsInsert && !nullToEmpty.isEmpty()) || !nullToEmpty.equals(getArguments().getString("groupName"));
    }

    /* access modifiers changed from: private */
    public void maybePersistCurrentGroupName(View view) {
        Intent intent;
        if (!hasNameChanged()) {
            dismiss();
            return;
        }
        String groupName = getGroupName();
        if (!TextUtils.isEmpty(groupName)) {
            groupName = groupName.trim();
        }
        String str = groupName;
        if (this.mExistingGroups.contains(str)) {
            this.mGroupNameTextLayout.setError(getString(R.string.groupExistsErrorMessage));
            view.setEnabled(false);
            return;
        }
        String string = getArguments().getString("callbackAction");
        if (this.mIsInsert) {
            intent = ContactSaveService.createNewGroupIntent(getActivity(), this.mAccount, str, (long[]) null, getActivity().getClass(), string);
        } else {
            intent = ContactSaveService.createGroupRenameIntent(getActivity(), this.mGroupId, str, getActivity().getClass(), string);
        }
        ContactSaveService.startService(getActivity(), intent);
        getListener().onGroupNameEditCompleted(this.mGroupName);
        dismiss();
    }

    public void onCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        getListener().onGroupNameEditCancelled();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("groupName", getGroupName());
    }

    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(getActivity(), ContactsContract.Groups.CONTENT_SUMMARY_URI, new String[]{"title", "system_id", "account_type", "summ_count", "group_is_read_only"}, getSelection(), getSelectionArgs(), (String) null);
    }

    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        this.mExistingGroups = new HashSet();
        GroupUtil.GroupsProjection groupsProjection = new GroupUtil.GroupsProjection(cursor);
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            String title = groupsProjection.getTitle(cursor);
            if (!TextUtils.isEmpty(title)) {
                title = title.trim();
            }
            if (!groupsProjection.isEmptyFFCGroup(cursor)) {
                this.mExistingGroups.add(title);
            }
        }
    }

    /* access modifiers changed from: private */
    public void showInputMethod(View view) {
        InputMethodManager inputMethodManager;
        if (getActivity() != null && (inputMethodManager = (InputMethodManager) getActivity().getSystemService("input_method")) != null) {
            inputMethodManager.showSoftInput(view, 0);
        }
    }

    /* access modifiers changed from: private */
    public void hideInputMethod() {
        EditText editText;
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService("input_method");
        if (inputMethodManager != null && (editText = this.mGroupNameEditText) != null) {
            inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }

    /* access modifiers changed from: private */
    public Listener getListener() {
        Listener listener = this.mListener;
        if (listener != null) {
            return listener;
        }
        if (getActivity() instanceof Listener) {
            return (Listener) getActivity();
        }
        return Listener.None;
    }

    /* access modifiers changed from: private */
    public String getGroupName() {
        EditText editText = this.mGroupNameEditText;
        if (editText == null || editText.getText() == null) {
            return null;
        }
        return this.mGroupNameEditText.getText().toString();
    }

    private String getSelection() {
        StringBuilder sb = new StringBuilder();
        sb.append("account_name");
        sb.append("=? AND ");
        sb.append("account_type");
        sb.append("=? AND ");
        sb.append("deleted");
        sb.append("=?");
        if (this.mAccount.dataSet != null) {
            sb.append(" AND ");
            sb.append("data_set");
            sb.append("=?");
        }
        return sb.toString();
    }

    private String[] getSelectionArgs() {
        String[] strArr = new String[(this.mAccount.dataSet == null ? 3 : 4)];
        AccountWithDataSet accountWithDataSet = this.mAccount;
        strArr[0] = accountWithDataSet.name;
        strArr[1] = accountWithDataSet.type;
        strArr[2] = "0";
        String str = accountWithDataSet.dataSet;
        if (str != null) {
            strArr[3] = str;
        }
        return strArr;
    }
}
