package com.android.contacts.list;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.android.contacts.R;
import com.android.contacts.model.AccountTypeManager;
import java.util.ArrayList;
import java.util.List;

public class AccountFilterActivity extends Activity implements AdapterView.OnItemClickListener {
    private int mCurrentFilterType;
    private ContactListFilterView mCustomFilterView;
    private boolean mIsCustomFilterViewSelected;
    private ListView mListView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.contact_list_filter);
        this.mListView = (ListView) findViewById(16908298);
        this.mListView.setOnItemClickListener(this);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.mCurrentFilterType = ContactListFilterController.getInstance(this).isCustomFilterPersisted() ? -3 : -2;
        ArrayList arrayList = new ArrayList();
        arrayList.add(ContactListFilter.createFilterWithType(-2));
        arrayList.add(ContactListFilter.createFilterWithType(-3));
        this.mListView.setAdapter(new FilterListAdapter(this, arrayList, this.mCurrentFilterType));
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        ContactListFilterView contactListFilterView = (ContactListFilterView) view;
        ContactListFilter contactListFilter = (ContactListFilter) view.getTag();
        if (contactListFilter != null) {
            if (contactListFilter.filterType == -3) {
                this.mCustomFilterView = contactListFilterView;
                this.mIsCustomFilterViewSelected = contactListFilterView.isChecked();
                Intent putExtra = new Intent(this, CustomContactListFilterActivity.class).putExtra("currentListFilterType", this.mCurrentFilterType);
                contactListFilterView.setActivated(true);
                contactListFilterView.announceForAccessibility(contactListFilterView.generateContentDescription());
                startActivityForResult(putExtra, 0);
                return;
            }
            contactListFilterView.setActivated(true);
            contactListFilterView.announceForAccessibility(contactListFilterView.generateContentDescription());
            Intent intent = new Intent();
            intent.putExtra("contactListFilter", contactListFilter);
            setResult(-1, intent);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        ContactListFilterView contactListFilterView;
        if (i2 == 0 && (contactListFilterView = this.mCustomFilterView) != null && !this.mIsCustomFilterViewSelected) {
            contactListFilterView.setActivated(false);
        } else if (i2 == -1 && i == 0) {
            Intent intent2 = new Intent();
            intent2.putExtra("contactListFilter", ContactListFilter.createFilterWithType(-3));
            setResult(-1, intent2);
            finish();
        }
    }

    private static class FilterListAdapter extends BaseAdapter {
        private final AccountTypeManager mAccountTypes;
        private final int mCurrentFilter;
        private final List<ContactListFilter> mFilters;
        private final LayoutInflater mLayoutInflater;

        public long getItemId(int i) {
            return (long) i;
        }

        public FilterListAdapter(Context context, List<ContactListFilter> list, int i) {
            this.mLayoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
            this.mFilters = list;
            this.mCurrentFilter = i;
            this.mAccountTypes = AccountTypeManager.getInstance(context);
        }

        public int getCount() {
            return this.mFilters.size();
        }

        public ContactListFilter getItem(int i) {
            return this.mFilters.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ContactListFilterView contactListFilterView;
            boolean z = false;
            if (view != null) {
                contactListFilterView = (ContactListFilterView) view;
            } else {
                contactListFilterView = (ContactListFilterView) this.mLayoutInflater.inflate(R.layout.contact_list_filter_item, viewGroup, false);
            }
            contactListFilterView.setSingleAccount(this.mFilters.size() == 1);
            ContactListFilter contactListFilter = this.mFilters.get(i);
            contactListFilterView.setContactListFilter(contactListFilter);
            contactListFilterView.bindView(this.mAccountTypes);
            contactListFilterView.setTag(contactListFilter);
            if (contactListFilter.filterType == this.mCurrentFilter) {
                z = true;
            }
            contactListFilterView.setActivated(z);
            return contactListFilterView;
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }
}
