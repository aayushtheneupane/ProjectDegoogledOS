package com.android.contacts.list;

import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.android.contacts.R;
import com.android.contacts.ShortcutIntentBuilder;
import com.android.contacts.list.ContactListItemView;
import com.android.contacts.list.PhoneNumberListAdapter;

public class PhoneNumberPickerFragment extends ContactEntryListFragment<ContactEntryListAdapter> implements ShortcutIntentBuilder.OnShortcutIntentCreatedListener, PhoneNumberListAdapter.Listener {
    private ContactListFilter mFilter;
    private OnPhoneNumberPickerActionListener mListener;
    private boolean mLoaderStarted;
    private ContactListItemView.PhotoPosition mPhotoPosition = ContactListItemView.getDefaultPhotoPosition(false);
    private String mShortcutAction;
    private boolean mUseCallableUri;

    /* access modifiers changed from: protected */
    public void cacheContactInfo(int i) {
    }

    /* access modifiers changed from: protected */
    public int getCallInitiationType(boolean z) {
        return 0;
    }

    /* access modifiers changed from: protected */
    public boolean getVisibleScrollbarEnabled() {
        return true;
    }

    public void onVideoCallIconClicked(int i) {
        callNumber(i, true);
    }

    public PhoneNumberPickerFragment() {
        setQuickContactEnabled(false);
        setPhotoLoaderEnabled(true);
        setSectionHeaderDisplayEnabled(true);
        setDirectorySearchMode(0);
        setHasOptionsMenu(true);
    }

    public void setOnPhoneNumberPickerActionListener(OnPhoneNumberPickerActionListener onPhoneNumberPickerActionListener) {
        this.mListener = onPhoneNumberPickerActionListener;
    }

    /* access modifiers changed from: protected */
    public void onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        super.onCreateView(layoutInflater, viewGroup);
        setVisibleScrollbarEnabled(getVisibleScrollbarEnabled());
    }

    public void restoreSavedState(Bundle bundle) {
        super.restoreSavedState(bundle);
        if (bundle != null) {
            this.mFilter = (ContactListFilter) bundle.getParcelable("filter");
            this.mShortcutAction = bundle.getString("shortcutAction");
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("filter", this.mFilter);
        bundle.putString("shortcutAction", this.mShortcutAction);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        OnPhoneNumberPickerActionListener onPhoneNumberPickerActionListener = this.mListener;
        if (onPhoneNumberPickerActionListener == null) {
            return true;
        }
        onPhoneNumberPickerActionListener.onHomeInActionBarSelected();
        return true;
    }

    public void setShortcutAction(String str) {
        this.mShortcutAction = str;
    }

    /* access modifiers changed from: protected */
    public void onItemClick(int i, long j) {
        callNumber(i, false);
    }

    private void callNumber(int i, boolean z) {
        Uri phoneUri = getPhoneUri(i);
        if (phoneUri != null) {
            pickPhoneNumber(phoneUri, z);
            return;
        }
        String phoneNumber = getPhoneNumber(i);
        if (!TextUtils.isEmpty(phoneNumber)) {
            cacheContactInfo(i);
            this.mListener.onPickPhoneNumber(phoneNumber, z, getCallInitiationType(true));
            return;
        }
        Log.w("PhoneNumberPicker", "Item at " + i + " was clicked before adapter is ready. Ignoring");
    }

    /* access modifiers changed from: protected */
    public String getPhoneNumber(int i) {
        return ((PhoneNumberListAdapter) getAdapter()).getPhoneNumber(i);
    }

    /* access modifiers changed from: protected */
    public Uri getPhoneUri(int i) {
        return ((PhoneNumberListAdapter) getAdapter()).getDataUri(i);
    }

    /* access modifiers changed from: protected */
    public void startLoading() {
        this.mLoaderStarted = true;
        super.startLoading();
    }

    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        super.onLoadFinished(loader, cursor);
        setVisibleScrollbarEnabled(cursor != null && !cursor.isClosed() && cursor.getCount() > 0);
    }

    /* access modifiers changed from: protected */
    public ContactEntryListAdapter createListAdapter() {
        PhoneNumberListAdapter phoneNumberListAdapter = new PhoneNumberListAdapter(getActivity());
        phoneNumberListAdapter.setDisplayPhotos(true);
        phoneNumberListAdapter.setUseCallableUri(this.mUseCallableUri);
        return phoneNumberListAdapter;
    }

    /* access modifiers changed from: protected */
    public void configureAdapter() {
        ContactListFilter contactListFilter;
        super.configureAdapter();
        ContactEntryListAdapter adapter = getAdapter();
        if (adapter != null) {
            if (!isSearchMode() && (contactListFilter = this.mFilter) != null) {
                adapter.setFilter(contactListFilter);
            }
            setPhotoPosition(adapter);
        }
    }

    /* access modifiers changed from: protected */
    public void setPhotoPosition(ContactEntryListAdapter contactEntryListAdapter) {
        ((PhoneNumberListAdapter) contactEntryListAdapter).setPhotoPosition(this.mPhotoPosition);
    }

    /* access modifiers changed from: protected */
    public View inflateView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.contact_list_content, (ViewGroup) null);
    }

    public void pickPhoneNumber(Uri uri, boolean z) {
        if (this.mShortcutAction == null) {
            this.mListener.onPickDataUri(uri, z, getCallInitiationType(false));
        } else {
            startPhoneNumberShortcutIntent(uri, z);
        }
    }

    /* access modifiers changed from: protected */
    public void startPhoneNumberShortcutIntent(Uri uri, boolean z) {
        new ShortcutIntentBuilder(getActivity(), this).createPhoneNumberShortcutIntent(uri, this.mShortcutAction);
    }

    public void onShortcutIntentCreated(Uri uri, Intent intent) {
        this.mListener.onShortcutIntentCreated(intent);
    }
}
