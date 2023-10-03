package com.android.contacts.editor;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.contacts.R;
import com.android.contacts.model.AccountTypeManager;
import com.android.contacts.model.account.AccountInfo;
import com.android.contacts.model.account.AccountWithDataSet;
import com.android.contacts.model.account.AccountsLoader;
import com.android.contacts.util.AccountsListAdapter;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import java.util.List;

public final class SelectAccountDialogFragment extends DialogFragment implements AccountsLoader.AccountsListener {
    /* access modifiers changed from: private */
    public AccountsListAdapter mAccountsAdapter;
    private AccountTypeManager.AccountFilter mFilter;

    public interface Listener {
        void onAccountChosen(AccountWithDataSet accountWithDataSet, Bundle bundle);

        void onAccountSelectorCancelled();
    }

    public static void show(FragmentManager fragmentManager, int i, AccountTypeManager.AccountFilter accountFilter, Bundle bundle) {
        show(fragmentManager, i, accountFilter, bundle, (String) null);
    }

    public static void show(FragmentManager fragmentManager, int i, AccountTypeManager.AccountFilter accountFilter, Bundle bundle, String str) {
        Bundle bundle2 = new Bundle();
        bundle2.putInt("title_res_id", i);
        if (bundle == null) {
            bundle = Bundle.EMPTY;
        }
        bundle2.putBundle("extra_args", bundle);
        bundle2.putSerializable("list_filter", accountFilter);
        SelectAccountDialogFragment selectAccountDialogFragment = new SelectAccountDialogFragment();
        selectAccountDialogFragment.setArguments(bundle2);
        selectAccountDialogFragment.show(fragmentManager, str);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mFilter = (AccountTypeManager.AccountFilter) getArguments().getSerializable("list_filter");
        if (this.mFilter == null) {
            this.mFilter = AccountTypeManager.AccountFilter.ALL;
        }
    }

    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        Bundle arguments = getArguments();
        this.mAccountsAdapter = new AccountsListAdapter(builder.getContext());
        this.mAccountsAdapter.setCustomLayout(R.layout.account_selector_list_item_condensed);
        C03391 r1 = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                SelectAccountDialogFragment selectAccountDialogFragment = SelectAccountDialogFragment.this;
                selectAccountDialogFragment.onAccountSelected(selectAccountDialogFragment.mAccountsAdapter.getItem(i));
            }
        };
        TextView textView = (TextView) View.inflate(getActivity(), R.layout.dialog_title, (ViewGroup) null);
        textView.setText(arguments.getInt("title_res_id"));
        builder.setCustomTitle(textView);
        builder.setSingleChoiceItems(this.mAccountsAdapter, 0, r1);
        return builder.create();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        AccountsLoader.loadAccounts(this, 0, (Predicate<AccountInfo>) this.mFilter);
    }

    public void onCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        Listener listener = getListener();
        if (listener != null) {
            listener.onAccountSelectorCancelled();
        }
    }

    /* access modifiers changed from: private */
    public void onAccountSelected(AccountWithDataSet accountWithDataSet) {
        Listener listener = getListener();
        if (listener != null) {
            listener.onAccountChosen(accountWithDataSet, getArguments().getBundle("extra_args"));
        }
    }

    private Listener getListener() {
        Activity activity = getActivity();
        if (activity == null || !(activity instanceof Listener)) {
            return null;
        }
        return (Listener) activity;
    }

    public void onAccountsLoaded(List<AccountInfo> list) {
        Preconditions.checkNotNull(this.mAccountsAdapter, "Accounts adapter should have been initialized");
        this.mAccountsAdapter.setAccounts(list, (AccountWithDataSet) null);
    }
}
