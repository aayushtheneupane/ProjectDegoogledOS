package com.android.contacts.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.contacts.R;
import com.android.contacts.model.account.AccountInfo;
import com.android.contacts.model.account.AccountWithDataSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class AccountsListAdapter extends BaseAdapter {
    private List<AccountInfo> mAccounts;
    private int mCustomLayout;
    private final LayoutInflater mInflater;

    public long getItemId(int i) {
        return (long) i;
    }

    public AccountsListAdapter(Context context) {
        this(context, Collections.emptyList(), (AccountWithDataSet) null);
    }

    public AccountsListAdapter(Context context, List<AccountInfo> list) {
        this(context, list, (AccountWithDataSet) null);
    }

    public AccountsListAdapter(Context context, List<AccountInfo> list, AccountWithDataSet accountWithDataSet) {
        this.mCustomLayout = -1;
        this.mInflater = LayoutInflater.from(context);
        this.mAccounts = new ArrayList(list.size());
        setAccounts(list, accountWithDataSet);
    }

    public void setAccounts(List<AccountInfo> list, AccountWithDataSet accountWithDataSet) {
        AccountInfo accountInfo;
        if (this.mAccounts.isEmpty()) {
            accountInfo = AccountInfo.getAccount(list, accountWithDataSet);
        } else {
            accountInfo = AccountInfo.getAccount(list, this.mAccounts.get(0).getAccount());
        }
        this.mAccounts.clear();
        this.mAccounts.addAll(list);
        if (accountInfo != null && !this.mAccounts.isEmpty() && !this.mAccounts.get(0).sameAccount(accountWithDataSet) && this.mAccounts.remove(accountInfo)) {
            this.mAccounts.add(0, accountInfo);
        }
        notifyDataSetChanged();
    }

    public void setCustomLayout(int i) {
        this.mCustomLayout = i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        int i2 = 0;
        if (view == null) {
            LayoutInflater layoutInflater = this.mInflater;
            int i3 = this.mCustomLayout;
            if (i3 <= 0) {
                i3 = R.layout.account_selector_list_item_condensed;
            }
            view = layoutInflater.inflate(i3, viewGroup, false);
        }
        TextView textView = (TextView) view.findViewById(16908309);
        ImageView imageView = (ImageView) view.findViewById(16908294);
        ((TextView) view.findViewById(16908308)).setText(this.mAccounts.get(i).getTypeLabel());
        textView.setText(this.mAccounts.get(i).getNameLabel());
        if (this.mAccounts.get(i).getNameLabel().equals(this.mAccounts.get(i).getTypeLabel())) {
            i2 = 8;
        }
        textView.setVisibility(i2);
        imageView.setImageDrawable(this.mAccounts.get(i).getIcon());
        return view;
    }

    public int getCount() {
        return this.mAccounts.size();
    }

    public AccountWithDataSet getItem(int i) {
        return this.mAccounts.get(i).getAccount();
    }
}
