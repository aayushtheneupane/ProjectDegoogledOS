package com.android.contacts.list;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.android.contacts.ContactPhotoManager;
import com.android.contacts.ContactSaveService;
import com.android.contacts.R;
import com.android.contacts.compat.ContactsCompat;
import com.android.contacts.list.ContactListItemView;
import java.util.HashSet;

public abstract class ContactListAdapter extends MultiSelectEntryContactListAdapter {
    private ContactListItemView.PhotoPosition mPhotoPosition;
    private long mSelectedContactDirectoryId;
    private long mSelectedContactId;
    private String mSelectedContactLookupKey;
    private CharSequence mUnknownNameText;

    public static class ContactQuery {
        /* access modifiers changed from: private */
        public static final String[] CONTACT_PROJECTION_ALTERNATIVE = {"_id", "display_name_alt", "contact_presence", "contact_status", "photo_id", "photo_thumb_uri", "lookup", "phonetic_name", ContactSaveService.EXTRA_STARRED_FLAG};
        public static final String[] CONTACT_PROJECTION_PRIMARY = {"_id", "display_name", "contact_presence", "contact_status", "photo_id", "photo_thumb_uri", "lookup", "phonetic_name", ContactSaveService.EXTRA_STARRED_FLAG};
        /* access modifiers changed from: private */
        public static final String[] FILTER_PROJECTION_ALTERNATIVE = {"_id", "display_name_alt", "contact_presence", "contact_status", "photo_id", "photo_thumb_uri", "lookup", "phonetic_name", ContactSaveService.EXTRA_STARRED_FLAG, "snippet"};
        /* access modifiers changed from: private */
        public static final String[] FILTER_PROJECTION_PRIMARY = {"_id", "display_name", "contact_presence", "contact_status", "photo_id", "photo_thumb_uri", "lookup", "phonetic_name", ContactSaveService.EXTRA_STARRED_FLAG, "snippet"};
    }

    public ContactListAdapter(Context context) {
        super(context, 0);
        this.mUnknownNameText = context.getText(R.string.missing_name);
    }

    public void setPhotoPosition(ContactListItemView.PhotoPosition photoPosition) {
        this.mPhotoPosition = photoPosition;
    }

    public long getSelectedContactDirectoryId() {
        return this.mSelectedContactDirectoryId;
    }

    public String getSelectedContactLookupKey() {
        return this.mSelectedContactLookupKey;
    }

    public long getSelectedContactId() {
        return this.mSelectedContactId;
    }

    public void setSelectedContact(long j, String str, long j2) {
        this.mSelectedContactDirectoryId = j;
        this.mSelectedContactLookupKey = str;
        this.mSelectedContactId = j2;
    }

    protected static Uri buildSectionIndexerUri(Uri uri) {
        return uri.buildUpon().appendQueryParameter("android.provider.extra.ADDRESS_BOOK_INDEX", "true").build();
    }

    public Uri getContactUri(int i) {
        int partitionForPosition = getPartitionForPosition(i);
        Cursor cursor = (Cursor) getItem(i);
        if (cursor != null) {
            return getContactUri(partitionForPosition, cursor);
        }
        return null;
    }

    public Uri getContactUri(int i, Cursor cursor) {
        Uri lookupUri = ContactsContract.Contacts.getLookupUri(cursor.getLong(0), cursor.getString(6));
        long directoryId = ((DirectoryPartition) getPartition(i)).getDirectoryId();
        return (lookupUri == null || directoryId == 0) ? lookupUri : lookupUri.buildUpon().appendQueryParameter("directory", String.valueOf(directoryId)).build();
    }

    public long getContactId(int i) {
        Cursor cursor = (Cursor) getItem(i);
        if (cursor == null) {
            return -1;
        }
        return cursor.getLong(0);
    }

    public boolean isEnterpriseContact(int i) {
        Cursor cursor = (Cursor) getItem(i);
        if (cursor != null) {
            return ContactsCompat.isEnterpriseContactId(cursor.getLong(0));
        }
        return false;
    }

    public boolean isSelectedContact(int i, Cursor cursor) {
        long directoryId = ((DirectoryPartition) getPartition(i)).getDirectoryId();
        if (getSelectedContactDirectoryId() != directoryId) {
            return false;
        }
        String selectedContactLookupKey = getSelectedContactLookupKey();
        if (selectedContactLookupKey != null && TextUtils.equals(selectedContactLookupKey, cursor.getString(6))) {
            return true;
        }
        if (directoryId == 0 || directoryId == 1 || getSelectedContactId() != cursor.getLong(0)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public ContactListItemView newView(Context context, int i, Cursor cursor, int i2, ViewGroup viewGroup) {
        ContactListItemView newView = super.newView(context, i, cursor, i2, viewGroup);
        newView.setUnknownNameText(this.mUnknownNameText);
        newView.setQuickContactEnabled(isQuickContactEnabled());
        newView.setAdjustSelectionBoundsEnabled(isAdjustSelectionBoundsEnabled());
        newView.setActivatedStateSupported(isSelectionVisible());
        ContactListItemView.PhotoPosition photoPosition = this.mPhotoPosition;
        if (photoPosition != null) {
            newView.setPhotoPosition(photoPosition);
        }
        return newView;
    }

    /* access modifiers changed from: protected */
    public void bindSectionHeaderAndDivider(ContactListItemView contactListItemView, int i, Cursor cursor) {
        contactListItemView.setIsSectionHeaderEnabled(isSectionHeaderDisplayEnabled());
        if (isSectionHeaderDisplayEnabled()) {
            contactListItemView.setSectionHeader(getItemPlacementInSection(i).sectionHeader);
        } else {
            contactListItemView.setSectionHeader((String) null);
        }
    }

    /* access modifiers changed from: protected */
    public void bindPhoto(ContactListItemView contactListItemView, int i, Cursor cursor) {
        Uri uri;
        if (!isPhotoSupported(i)) {
            contactListItemView.removePhotoView();
            return;
        }
        long j = !cursor.isNull(4) ? cursor.getLong(4) : 0;
        if (j != 0) {
            getPhotoLoader().loadThumbnail(contactListItemView.getPhotoView(), j, false, getCircularPhotos(), (ContactPhotoManager.DefaultImageRequest) null);
            return;
        }
        String string = cursor.getString(5);
        ContactPhotoManager.DefaultImageRequest defaultImageRequest = null;
        if (string == null) {
            uri = null;
        } else {
            uri = Uri.parse(string);
        }
        if (uri == null) {
            defaultImageRequest = getDefaultImageRequestFromCursor(cursor, 1, 6);
        }
        getPhotoLoader().loadDirectoryPhoto(contactListItemView.getPhotoView(), uri, false, getCircularPhotos(), defaultImageRequest);
    }

    /* access modifiers changed from: protected */
    public void bindNameAndViewId(ContactListItemView contactListItemView, Cursor cursor) {
        contactListItemView.showDisplayName(cursor, 1, getContactNameDisplayOrder());
        bindViewId(contactListItemView, cursor, 0);
    }

    /* access modifiers changed from: protected */
    public void bindPresenceAndStatusMessage(ContactListItemView contactListItemView, Cursor cursor) {
        contactListItemView.showPresenceAndStatusMessage(cursor, 2, 3);
    }

    /* access modifiers changed from: protected */
    public void bindSearchSnippet(ContactListItemView contactListItemView, Cursor cursor) {
        contactListItemView.showSnippet(cursor, 9);
    }

    public int getSelectedContactPosition() {
        Cursor cursor;
        int i;
        if (this.mSelectedContactLookupKey == null && this.mSelectedContactId == 0) {
            return -1;
        }
        int partitionCount = getPartitionCount();
        int i2 = 0;
        while (true) {
            if (i2 >= partitionCount) {
                i2 = -1;
                break;
            } else if (((DirectoryPartition) getPartition(i2)).getDirectoryId() == this.mSelectedContactDirectoryId) {
                break;
            } else {
                i2++;
            }
        }
        if (i2 == -1 || (cursor = getCursor(i2)) == null) {
            return -1;
        }
        cursor.moveToPosition(-1);
        while (true) {
            if (!cursor.moveToNext()) {
                i = -1;
                break;
            }
            if (this.mSelectedContactLookupKey != null) {
                if (this.mSelectedContactLookupKey.equals(cursor.getString(6))) {
                    i = cursor.getPosition();
                    break;
                }
            }
            if (this.mSelectedContactId != 0) {
                long j = this.mSelectedContactDirectoryId;
                if ((j == 0 || j == 1) && cursor.getLong(0) == this.mSelectedContactId) {
                    i = cursor.getPosition();
                    break;
                }
            }
        }
        if (i == -1) {
            return -1;
        }
        int positionForPartition = getPositionForPartition(i2) + i;
        return hasHeader(i2) ? positionForPartition + 1 : positionForPartition;
    }

    public Uri getFirstContactUri() {
        Cursor cursor;
        int partitionCount = getPartitionCount();
        for (int i = 0; i < partitionCount; i++) {
            if (!((DirectoryPartition) getPartition(i)).isLoading() && (cursor = getCursor(i)) != null && cursor.moveToFirst()) {
                return getContactUri(i, cursor);
            }
        }
        return null;
    }

    public void changeCursor(int i, Cursor cursor) {
        super.changeCursor(i, cursor);
        if (cursor != null && cursor.moveToFirst() && shouldIncludeFavorites() && cursor.getInt(8) == 1) {
            HashSet hashSet = new HashSet();
            hashSet.add(Integer.valueOf(cursor.getInt(0)));
            while (cursor != null && cursor.moveToNext() && cursor.getInt(8) == 1 && !hashSet.contains(Integer.valueOf(cursor.getInt(0)))) {
                hashSet.add(Integer.valueOf(cursor.getInt(0)));
            }
            setFavoritesSectionHeader(hashSet.size());
        }
    }

    /* access modifiers changed from: protected */
    public final String[] getProjection(boolean z) {
        int contactNameDisplayOrder = getContactNameDisplayOrder();
        if (z) {
            if (contactNameDisplayOrder == 1) {
                return ContactQuery.FILTER_PROJECTION_PRIMARY;
            }
            return ContactQuery.FILTER_PROJECTION_ALTERNATIVE;
        } else if (contactNameDisplayOrder == 1) {
            return ContactQuery.CONTACT_PROJECTION_PRIMARY;
        } else {
            return ContactQuery.CONTACT_PROJECTION_ALTERNATIVE;
        }
    }

    /* access modifiers changed from: protected */
    public final String[] getDataProjectionForContacts(boolean z) {
        int contactNameDisplayOrder = getContactNameDisplayOrder();
        if (z) {
            if (contactNameDisplayOrder == 1) {
                return replaceFirstString(ContactQuery.FILTER_PROJECTION_PRIMARY);
            }
            return replaceFirstString(ContactQuery.FILTER_PROJECTION_ALTERNATIVE);
        } else if (contactNameDisplayOrder == 1) {
            return replaceFirstString(ContactQuery.CONTACT_PROJECTION_PRIMARY);
        } else {
            return replaceFirstString(ContactQuery.CONTACT_PROJECTION_ALTERNATIVE);
        }
    }

    private String[] replaceFirstString(String[] strArr) {
        String[] strArr2 = (String[]) strArr.clone();
        strArr2[0] = "contact_id";
        return strArr2;
    }
}
