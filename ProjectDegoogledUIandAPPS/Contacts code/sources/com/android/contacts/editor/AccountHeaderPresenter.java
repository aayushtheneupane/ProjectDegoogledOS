package com.android.contacts.editor;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.TextView;
import com.android.contacts.R;
import com.android.contacts.model.account.AccountInfo;
import com.android.contacts.model.account.AccountWithDataSet;
import com.android.contacts.util.AccountsListAdapter;
import com.android.contacts.util.UiClosables;
import java.util.List;

public class AccountHeaderPresenter {
    /* access modifiers changed from: private */
    public final View mAccountHeaderContainer;
    private ImageView mAccountHeaderExpanderIcon;
    private ImageView mAccountHeaderIcon;
    private TextView mAccountHeaderName;
    private TextView mAccountHeaderType;
    private List<AccountInfo> mAccounts;
    private final Context mContext;
    private AccountWithDataSet mCurrentAccount;
    private Observer mObserver = Observer.NONE;
    private int mSelectorTitle = R.string.editor_account_selector_title;

    public interface Observer {
        public static final Observer NONE = new Observer() {
            public void onChange(AccountHeaderPresenter accountHeaderPresenter) {
            }
        };

        void onChange(AccountHeaderPresenter accountHeaderPresenter);
    }

    public AccountHeaderPresenter(View view) {
        this.mContext = view.getContext();
        this.mAccountHeaderContainer = view;
        this.mAccountHeaderType = (TextView) view.findViewById(R.id.account_type);
        this.mAccountHeaderName = (TextView) view.findViewById(R.id.account_name);
        this.mAccountHeaderIcon = (ImageView) view.findViewById(R.id.account_type_icon);
        this.mAccountHeaderExpanderIcon = (ImageView) view.findViewById(R.id.account_expander_icon);
    }

    public void setObserver(Observer observer) {
        this.mObserver = observer;
    }

    public void setCurrentAccount(AccountWithDataSet accountWithDataSet) {
        AccountWithDataSet accountWithDataSet2 = this.mCurrentAccount;
        if (accountWithDataSet2 == null || !accountWithDataSet2.equals(accountWithDataSet)) {
            this.mCurrentAccount = accountWithDataSet;
            Observer observer = this.mObserver;
            if (observer != null) {
                observer.onChange(this);
            }
            updateDisplayedAccount();
        }
    }

    public void setAccounts(List<AccountInfo> list) {
        this.mAccounts = list;
        AccountWithDataSet accountWithDataSet = this.mCurrentAccount;
        if (accountWithDataSet == null || !AccountInfo.contains(this.mAccounts, accountWithDataSet)) {
            this.mCurrentAccount = this.mAccounts.isEmpty() ? null : list.get(0).getAccount();
            this.mObserver.onChange(this);
        }
        updateDisplayedAccount();
    }

    public AccountWithDataSet getCurrentAccount() {
        AccountWithDataSet accountWithDataSet = this.mCurrentAccount;
        if (accountWithDataSet != null) {
            return accountWithDataSet;
        }
        return null;
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putParcelable("accountHeaderSelectedAccount", this.mCurrentAccount);
    }

    public void onRestoreInstanceState(Bundle bundle) {
        if (bundle != null) {
            if (this.mCurrentAccount == null) {
                this.mCurrentAccount = (AccountWithDataSet) bundle.getParcelable("accountHeaderSelectedAccount");
            }
            updateDisplayedAccount();
        }
    }

    private void updateDisplayedAccount() {
        this.mAccountHeaderContainer.setVisibility(8);
        AccountWithDataSet accountWithDataSet = this.mCurrentAccount;
        if (accountWithDataSet != null && this.mAccounts != null) {
            String accountLabel = getAccountLabel(accountWithDataSet);
            if (this.mAccounts.size() > 1) {
                addAccountSelector(accountLabel);
            } else {
                addAccountHeader(accountLabel);
            }
        }
    }

    private void addAccountHeader(String str) {
        this.mAccountHeaderContainer.setVisibility(0);
        this.mAccountHeaderName.setVisibility(0);
        this.mAccountHeaderName.setText(str);
        String string = this.mContext.getResources().getString(this.mSelectorTitle);
        TextView textView = this.mAccountHeaderType;
        if (textView != null) {
            textView.setText(string);
        }
        this.mAccountHeaderIcon.setImageDrawable(AccountInfo.getAccount(this.mAccounts, this.mCurrentAccount).getIcon());
        this.mAccountHeaderContainer.setContentDescription(EditorUiUtils.getAccountInfoContentDescription(str, string));
    }

    private void addAccountSelector(CharSequence charSequence) {
        setUpAccountSelector(charSequence.toString(), new View.OnClickListener() {
            public void onClick(View view) {
                AccountHeaderPresenter.this.showPopup();
            }
        });
    }

    /* access modifiers changed from: private */
    public void showPopup() {
        final ListPopupWindow listPopupWindow = new ListPopupWindow(this.mContext);
        final AccountsListAdapter accountsListAdapter = new AccountsListAdapter(this.mContext, this.mAccounts, this.mCurrentAccount);
        listPopupWindow.setWidth(this.mAccountHeaderContainer.getWidth());
        listPopupWindow.setAnchorView(this.mAccountHeaderContainer);
        listPopupWindow.setAdapter(accountsListAdapter);
        listPopupWindow.setModal(true);
        listPopupWindow.setInputMethodMode(2);
        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                UiClosables.closeQuietly(listPopupWindow);
                AccountHeaderPresenter.this.setCurrentAccount(accountsListAdapter.getItem(i));
                AccountHeaderPresenter.this.mAccountHeaderContainer.setAccessibilityLiveRegion(1);
            }
        });
        this.mAccountHeaderContainer.post(new Runnable() {
            public void run() {
                listPopupWindow.show();
            }
        });
    }

    private void setUpAccountSelector(String str, View.OnClickListener onClickListener) {
        addAccountHeader(str);
        this.mAccountHeaderExpanderIcon.setVisibility(0);
        this.mAccountHeaderExpanderIcon.setOnClickListener(onClickListener);
        this.mAccountHeaderContainer.setOnClickListener(onClickListener);
    }

    private String getAccountLabel(AccountWithDataSet accountWithDataSet) {
        AccountInfo account = AccountInfo.getAccount(this.mAccounts, accountWithDataSet);
        if (account != null) {
            return account.getNameLabel().toString();
        }
        return null;
    }
}
