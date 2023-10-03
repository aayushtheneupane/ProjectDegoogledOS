package com.android.contacts.list;

import android.net.Uri;

public class LegacyPhoneNumberPickerFragment extends PhoneNumberPickerFragment {
    /* access modifiers changed from: protected */
    public boolean getVisibleScrollbarEnabled() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void setPhotoPosition(ContactEntryListAdapter contactEntryListAdapter) {
    }

    /* access modifiers changed from: protected */
    public Uri getPhoneUri(int i) {
        return ((LegacyPhoneNumberListAdapter) getAdapter()).getPhoneUri(i);
    }

    /* access modifiers changed from: protected */
    public ContactEntryListAdapter createListAdapter() {
        LegacyPhoneNumberListAdapter legacyPhoneNumberListAdapter = new LegacyPhoneNumberListAdapter(getActivity());
        legacyPhoneNumberListAdapter.setDisplayPhotos(true);
        return legacyPhoneNumberListAdapter;
    }

    /* access modifiers changed from: protected */
    public void startPhoneNumberShortcutIntent(Uri uri, boolean z) {
        throw new UnsupportedOperationException();
    }
}
