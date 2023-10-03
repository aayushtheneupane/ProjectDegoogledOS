package com.android.contacts.list;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.android.contacts.R;
import com.android.contacts.group.GroupUtil;
import java.util.TreeSet;

public class MultiSelectPhoneNumbersListFragment extends MultiSelectContactsListFragment<MultiSelectPhoneNumbersListAdapter> {
    /* access modifiers changed from: protected */
    public boolean onItemLongClick(int i, long j) {
        return true;
    }

    public MultiSelectPhoneNumbersListFragment() {
        setPhotoLoaderEnabled(true);
        setSectionHeaderDisplayEnabled(false);
        setSearchMode(false);
        setHasOptionsMenu(true);
        setListType(12);
    }

    public MultiSelectPhoneNumbersListAdapter createListAdapter() {
        MultiSelectPhoneNumbersListAdapter multiSelectPhoneNumbersListAdapter = new MultiSelectPhoneNumbersListAdapter(getActivity());
        multiSelectPhoneNumbersListAdapter.setArguments(getArguments());
        return multiSelectPhoneNumbersListAdapter;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(R.menu.items_multi_select, menu);
    }

    public void onPrepareOptionsMenu(Menu menu) {
        final MenuItem findItem = menu.findItem(R.id.menu_send);
        findItem.setVisible(((MultiSelectPhoneNumbersListAdapter) getAdapter()).hasSelectedItems());
        findItem.getActionView().setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MultiSelectPhoneNumbersListFragment.this.onOptionsItemSelected(findItem);
            }
        });
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        getActivity().finish();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.menu_send) {
            return super.onOptionsItemSelected(menuItem);
        }
        String stringExtra = getActivity().getIntent().getStringExtra("com.android.contacts.extra.SELECTION_SEND_SCHEME");
        GroupUtil.startSendToSelectionActivity(this, TextUtils.join(",", GroupUtil.getSendToDataForIds(getActivity(), ((MultiSelectPhoneNumbersListAdapter) getAdapter()).getSelectedContactIdsArray(), stringExtra)), stringExtra, getActivity().getIntent().getStringExtra("com.android.contacts.extra.SELECTION_SEND_TITLE"));
        return true;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        long[] longArrayExtra = getActivity().getIntent().getLongArrayExtra("com.android.contacts.extra.SELECTION_DEFAULT_SELECTION");
        if (!(longArrayExtra == null || longArrayExtra.length == 0)) {
            TreeSet treeSet = new TreeSet();
            for (long valueOf : longArrayExtra) {
                treeSet.add(Long.valueOf(valueOf));
            }
            ((MultiSelectPhoneNumbersListAdapter) getAdapter()).setSelectedContactIds(treeSet);
            onSelectedContactsChanged();
        }
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onStart() {
        super.onStart();
        displayCheckBoxes(true);
    }

    /* access modifiers changed from: protected */
    public View inflateView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.contact_list_content, (ViewGroup) null);
    }
}
