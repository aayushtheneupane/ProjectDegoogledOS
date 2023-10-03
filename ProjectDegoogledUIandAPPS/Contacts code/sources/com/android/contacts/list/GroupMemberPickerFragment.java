package com.android.contacts.list;

import android.app.Activity;
import android.content.Loader;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.android.contacts.ContactSaveService;
import com.android.contacts.R;
import com.android.contacts.activities.ContactSelectionActivity;
import com.android.contacts.group.GroupUtil;
import com.android.contacts.model.account.AccountWithDataSet;
import java.util.ArrayList;

public class GroupMemberPickerFragment extends MultiSelectContactsListFragment<DefaultContactListAdapter> {
    private String mAccountDataSet;
    private String mAccountName;
    private String mAccountType;
    /* access modifiers changed from: private */
    public ArrayList<String> mContactIds;
    private Listener mListener;

    public interface Listener {
        void onGroupMemberClicked(long j);

        void onSelectGroupMembers();
    }

    private class FilterCursorWrapper extends CursorWrapper {
        private int mCount;
        private int[] mIndex;
        private int mPos;

        public FilterCursorWrapper(Cursor cursor) {
            super(cursor);
            int i;
            this.mCount = 0;
            this.mPos = 0;
            this.mCount = super.getCount();
            this.mIndex = new int[this.mCount];
            ArrayList arrayList = new ArrayList();
            if (Log.isLoggable("GroupMemberPicker", 2)) {
                Log.v("GroupMemberPicker", "RawContacts CursorWrapper start: " + this.mCount);
            }
            Bundle extras = cursor.getExtras();
            String[] stringArray = extras.getStringArray("android.provider.extra.ADDRESS_BOOK_INDEX_TITLES");
            int[] intArray = extras.getIntArray("android.provider.extra.ADDRESS_BOOK_INDEX_COUNTS");
            ContactsSectionIndexer contactsSectionIndexer = (stringArray == null || intArray == null) ? null : new ContactsSectionIndexer(stringArray, intArray);
            int i2 = 0;
            while (true) {
                i = this.mCount;
                if (i2 >= i) {
                    break;
                }
                super.moveToPosition(i2);
                if (!GroupMemberPickerFragment.this.mContactIds.contains(getString(0))) {
                    int[] iArr = this.mIndex;
                    int i3 = this.mPos;
                    this.mPos = i3 + 1;
                    iArr[i3] = i2;
                } else {
                    arrayList.add(Integer.valueOf(i2));
                }
                i2++;
            }
            if (contactsSectionIndexer != null && GroupUtil.needTrimming(i, intArray, contactsSectionIndexer.getPositions())) {
                GroupUtil.updateBundle(extras, contactsSectionIndexer, arrayList, stringArray, intArray);
            }
            this.mCount = this.mPos;
            this.mPos = 0;
            super.moveToFirst();
            if (Log.isLoggable("GroupMemberPicker", 2)) {
                Log.v("GroupMemberPicker", "RawContacts CursorWrapper end: " + this.mCount);
            }
        }

        public boolean move(int i) {
            return moveToPosition(this.mPos + i);
        }

        public boolean moveToNext() {
            return moveToPosition(this.mPos + 1);
        }

        public boolean moveToPrevious() {
            return moveToPosition(this.mPos - 1);
        }

        public boolean moveToFirst() {
            return moveToPosition(0);
        }

        public boolean moveToLast() {
            return moveToPosition(this.mCount - 1);
        }

        public boolean moveToPosition(int i) {
            int i2 = this.mCount;
            if (i >= i2) {
                this.mPos = i2;
                return false;
            } else if (i < 0) {
                this.mPos = -1;
                return false;
            } else {
                this.mPos = this.mIndex[i];
                return super.moveToPosition(this.mPos);
            }
        }

        public int getCount() {
            return this.mCount;
        }

        public int getPosition() {
            return this.mPos;
        }
    }

    public static GroupMemberPickerFragment newInstance(String str, String str2, String str3, ArrayList<String> arrayList) {
        Bundle bundle = new Bundle();
        bundle.putString(ContactSaveService.EXTRA_ACCOUNT_NAME, str);
        bundle.putString(ContactSaveService.EXTRA_ACCOUNT_TYPE, str2);
        bundle.putString("accountDataSet", str3);
        bundle.putStringArrayList(ContactSaveService.EXTRA_CONTACT_IDS, arrayList);
        GroupMemberPickerFragment groupMemberPickerFragment = new GroupMemberPickerFragment();
        groupMemberPickerFragment.setArguments(bundle);
        return groupMemberPickerFragment;
    }

    public GroupMemberPickerFragment() {
        setPhotoLoaderEnabled(true);
        setSectionHeaderDisplayEnabled(true);
        setHasOptionsMenu(true);
        setDisplayDirectoryHeader(false);
    }

    public void onCreate(Bundle bundle) {
        if (bundle == null) {
            this.mAccountName = getArguments().getString(ContactSaveService.EXTRA_ACCOUNT_NAME);
            this.mAccountType = getArguments().getString(ContactSaveService.EXTRA_ACCOUNT_TYPE);
            this.mAccountDataSet = getArguments().getString("accountDataSet");
            this.mContactIds = getArguments().getStringArrayList(ContactSaveService.EXTRA_CONTACT_IDS);
        } else {
            this.mAccountName = bundle.getString(ContactSaveService.EXTRA_ACCOUNT_NAME);
            this.mAccountType = bundle.getString(ContactSaveService.EXTRA_ACCOUNT_TYPE);
            this.mAccountDataSet = bundle.getString("accountDataSet");
            this.mContactIds = bundle.getStringArrayList(ContactSaveService.EXTRA_CONTACT_IDS);
        }
        super.onCreate(bundle);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString(ContactSaveService.EXTRA_ACCOUNT_NAME, this.mAccountName);
        bundle.putString(ContactSaveService.EXTRA_ACCOUNT_TYPE, this.mAccountType);
        bundle.putString("accountDataSet", this.mAccountDataSet);
        bundle.putStringArrayList(ContactSaveService.EXTRA_CONTACT_IDS, this.mContactIds);
    }

    public void setListener(Listener listener) {
        this.mListener = listener;
    }

    /* access modifiers changed from: protected */
    public View inflateView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.contact_list_content, (ViewGroup) null);
    }

    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor != null) {
            setVisibleScrollbarEnabled(true);
            FilterCursorWrapper filterCursorWrapper = new FilterCursorWrapper(cursor);
            bindListHeader(getContext(), getListView(), getView().findViewById(R.id.account_filter_header_container), new AccountWithDataSet(this.mAccountName, this.mAccountType, this.mAccountDataSet), filterCursorWrapper.getCount());
            super.onLoadFinished(loader, (Cursor) filterCursorWrapper);
        }
    }

    /* access modifiers changed from: protected */
    public DefaultContactListAdapter createListAdapter() {
        DefaultContactListAdapter defaultContactListAdapter = new DefaultContactListAdapter(getActivity());
        defaultContactListAdapter.setFilter(ContactListFilter.createGroupMembersFilter(this.mAccountType, this.mAccountName, this.mAccountDataSet));
        defaultContactListAdapter.setSectionHeaderDisplayEnabled(true);
        defaultContactListAdapter.setDisplayPhotos(true);
        return defaultContactListAdapter;
    }

    /* access modifiers changed from: protected */
    public void onItemClick(int i, long j) {
        if (((DefaultContactListAdapter) getAdapter()).isDisplayingCheckBoxes()) {
            super.onItemClick(i, j);
        } else if (this.mListener != null) {
            long contactId = ((DefaultContactListAdapter) getAdapter()).getContactId(i);
            if (contactId > 0) {
                this.mListener.onGroupMemberClicked(contactId);
            }
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(R.menu.group_member_picker, menu);
    }

    public void onPrepareOptionsMenu(Menu menu) {
        boolean z;
        boolean z2;
        ContactSelectionActivity contactSelectionActivity = getContactSelectionActivity();
        ArrayList<String> arrayList = this.mContactIds;
        boolean z3 = true;
        boolean z4 = arrayList != null && arrayList.size() > 0;
        if (contactSelectionActivity == null) {
            z = false;
        } else {
            z = contactSelectionActivity.isSearchMode();
        }
        if (contactSelectionActivity == null) {
            z2 = false;
        } else {
            z2 = contactSelectionActivity.isSelectionMode();
        }
        setVisible(menu, R.id.menu_search, !z && !z2);
        if (!z4 || z || z2) {
            z3 = false;
        }
        setVisible(menu, R.id.menu_select, z3);
    }

    private ContactSelectionActivity getContactSelectionActivity() {
        Activity activity = getActivity();
        if (activity == null || !(activity instanceof ContactSelectionActivity)) {
            return null;
        }
        return (ContactSelectionActivity) activity;
    }

    private static void setVisible(Menu menu, int i, boolean z) {
        MenuItem findItem = menu.findItem(i);
        if (findItem != null) {
            findItem.setVisible(z);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 16908332) {
            Activity activity = getActivity();
            if (activity != null) {
                activity.onBackPressed();
            }
            return true;
        } else if (itemId != R.id.menu_select) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            Listener listener = this.mListener;
            if (listener != null) {
                listener.onSelectGroupMembers();
            }
            return true;
        }
    }
}
