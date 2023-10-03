package com.android.contacts.list;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.android.contacts.R;
import com.android.contacts.ShortcutIntentBuilder;

public class ContactPickerFragment extends ContactEntryListFragment<ContactEntryListAdapter> implements ShortcutIntentBuilder.OnShortcutIntentCreatedListener {
    private boolean mCreateContactEnabled;
    private boolean mEditMode;
    private OnContactPickerActionListener mListener;
    private boolean mShortcutRequested;

    public ContactPickerFragment() {
        setPhotoLoaderEnabled(true);
        setSectionHeaderDisplayEnabled(true);
        setVisibleScrollbarEnabled(true);
        setQuickContactEnabled(false);
        setDirectorySearchMode(2);
    }

    public void setOnContactPickerActionListener(OnContactPickerActionListener onContactPickerActionListener) {
        this.mListener = onContactPickerActionListener;
    }

    public void setCreateContactEnabled(boolean z) {
        this.mCreateContactEnabled = z;
    }

    public void setEditMode(boolean z) {
        this.mEditMode = z;
    }

    public void setShortcutRequested(boolean z) {
        this.mShortcutRequested = z;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("editMode", this.mEditMode);
        bundle.putBoolean("createContactEnabled", this.mCreateContactEnabled);
        bundle.putBoolean("shortcutRequested", this.mShortcutRequested);
    }

    public void restoreSavedState(Bundle bundle) {
        super.restoreSavedState(bundle);
        if (bundle != null) {
            this.mEditMode = bundle.getBoolean("editMode");
            this.mCreateContactEnabled = bundle.getBoolean("createContactEnabled");
            this.mShortcutRequested = bundle.getBoolean("shortcutRequested");
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        OnContactPickerActionListener onContactPickerActionListener;
        if (i != 0 || !this.mCreateContactEnabled || (onContactPickerActionListener = this.mListener) == null) {
            super.onItemClick(adapterView, view, i, j);
        } else {
            onContactPickerActionListener.onCreateNewContactAction();
        }
    }

    /* access modifiers changed from: protected */
    public void onItemClick(int i, long j) {
        Uri uri;
        if (isLegacyCompatibilityMode()) {
            uri = ((LegacyContactListAdapter) getAdapter()).getPersonUri(i);
        } else {
            uri = ((ContactListAdapter) getAdapter()).getContactUri(i);
        }
        if (uri != null) {
            if (this.mEditMode) {
                editContact(uri);
            } else if (this.mShortcutRequested) {
                new ShortcutIntentBuilder(getActivity(), this).createContactShortcutIntent(uri);
            } else {
                pickContact(uri);
            }
        }
    }

    public void editContact(Uri uri) {
        OnContactPickerActionListener onContactPickerActionListener = this.mListener;
        if (onContactPickerActionListener != null) {
            onContactPickerActionListener.onEditContactAction(uri);
        }
    }

    public void pickContact(Uri uri) {
        OnContactPickerActionListener onContactPickerActionListener = this.mListener;
        if (onContactPickerActionListener != null) {
            onContactPickerActionListener.onPickContactAction(uri);
        }
    }

    /* access modifiers changed from: protected */
    public ContactEntryListAdapter createListAdapter() {
        if (!isLegacyCompatibilityMode()) {
            HeaderEntryContactListAdapter headerEntryContactListAdapter = new HeaderEntryContactListAdapter(getActivity());
            headerEntryContactListAdapter.setFilter(ContactListFilter.createFilterWithType(-2));
            headerEntryContactListAdapter.setSectionHeaderDisplayEnabled(true);
            headerEntryContactListAdapter.setDisplayPhotos(true);
            headerEntryContactListAdapter.setQuickContactEnabled(false);
            headerEntryContactListAdapter.setShowCreateContact(this.mCreateContactEnabled);
            return headerEntryContactListAdapter;
        }
        LegacyContactListAdapter legacyContactListAdapter = new LegacyContactListAdapter(getActivity());
        legacyContactListAdapter.setSectionHeaderDisplayEnabled(false);
        legacyContactListAdapter.setDisplayPhotos(false);
        return legacyContactListAdapter;
    }

    /* access modifiers changed from: protected */
    public View inflateView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.contact_picker_content, (ViewGroup) null);
    }

    public void onShortcutIntentCreated(Uri uri, Intent intent) {
        OnContactPickerActionListener onContactPickerActionListener = this.mListener;
        if (onContactPickerActionListener != null) {
            onContactPickerActionListener.onShortcutIntentCreated(intent);
        }
    }
}
