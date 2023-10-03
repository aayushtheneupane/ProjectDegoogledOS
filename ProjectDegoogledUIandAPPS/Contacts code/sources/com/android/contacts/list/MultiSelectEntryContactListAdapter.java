package com.android.contacts.list;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import androidx.appcompat.widget.AppCompatCheckBox;
import com.android.contacts.ContactPhotoManager;
import com.android.contacts.group.GroupUtil;
import java.util.TreeSet;

public abstract class MultiSelectEntryContactListAdapter extends ContactEntryListAdapter {
    private final int mContactIdColumnIndex;
    private DeleteContactListener mDeleteContactListener;
    private boolean mDisplayCheckBoxes;
    private TreeSet<Long> mSelectedContactIds = new TreeSet<>();
    private SelectedContactsListener mSelectedContactsListener;

    public interface DeleteContactListener {
        void onContactDeleteClicked(int i);
    }

    public interface SelectedContactsListener {
        void onSelectedContactsChanged();
    }

    public MultiSelectEntryContactListAdapter(Context context, int i) {
        super(context);
        this.mContactIdColumnIndex = i;
    }

    public int getContactColumnIdIndex() {
        return this.mContactIdColumnIndex;
    }

    public DeleteContactListener getDeleteContactListener() {
        return this.mDeleteContactListener;
    }

    public void setDeleteContactListener(DeleteContactListener deleteContactListener) {
        this.mDeleteContactListener = deleteContactListener;
    }

    public void setSelectedContactsListener(SelectedContactsListener selectedContactsListener) {
        this.mSelectedContactsListener = selectedContactsListener;
    }

    public TreeSet<Long> getSelectedContactIds() {
        return this.mSelectedContactIds;
    }

    public boolean hasSelectedItems() {
        return this.mSelectedContactIds.size() > 0;
    }

    public long[] getSelectedContactIdsArray() {
        return GroupUtil.convertLongSetToLongArray(this.mSelectedContactIds);
    }

    public void setSelectedContactIds(TreeSet<Long> treeSet) {
        this.mSelectedContactIds = treeSet;
        notifyDataSetChanged();
        SelectedContactsListener selectedContactsListener = this.mSelectedContactsListener;
        if (selectedContactsListener != null) {
            selectedContactsListener.onSelectedContactsChanged();
        }
    }

    public void setDisplayCheckBoxes(boolean z) {
        this.mDisplayCheckBoxes = z;
        notifyDataSetChanged();
        SelectedContactsListener selectedContactsListener = this.mSelectedContactsListener;
        if (selectedContactsListener != null) {
            selectedContactsListener.onSelectedContactsChanged();
        }
    }

    public boolean isDisplayingCheckBoxes() {
        return this.mDisplayCheckBoxes;
    }

    public void toggleSelectionOfContactId(long j) {
        if (this.mSelectedContactIds.contains(Long.valueOf(j))) {
            this.mSelectedContactIds.remove(Long.valueOf(j));
        } else {
            this.mSelectedContactIds.add(Long.valueOf(j));
        }
        notifyDataSetChanged();
        SelectedContactsListener selectedContactsListener = this.mSelectedContactsListener;
        if (selectedContactsListener != null) {
            selectedContactsListener.onSelectedContactsChanged();
        }
    }

    public long getItemId(int i) {
        Cursor cursor = (Cursor) getItem(i);
        if (cursor != null) {
            return cursor.getLong(getContactColumnIdIndex());
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public void bindView(View view, int i, Cursor cursor, int i2) {
        super.bindView(view, i, cursor, i2);
        ContactListItemView contactListItemView = (ContactListItemView) view;
        bindViewId(contactListItemView, cursor, getContactColumnIdIndex());
        bindCheckBox(contactListItemView, cursor, ((long) i) == 0);
    }

    /* access modifiers changed from: protected */
    public void bindPhoto(ContactListItemView contactListItemView, Cursor cursor, int i, int i2, int i3) {
        long j;
        ContactPhotoManager.DefaultImageRequest defaultImageRequest;
        if (cursor.isNull(i)) {
            j = 0;
        } else {
            j = cursor.getLong(i);
        }
        if (j == 0) {
            Cursor cursor2 = cursor;
            defaultImageRequest = getDefaultImageRequestFromCursor(cursor, i3, i2);
        } else {
            defaultImageRequest = null;
        }
        getPhotoLoader().loadThumbnail(contactListItemView.getPhotoView(), j, false, getCircularPhotos(), defaultImageRequest);
    }

    private void bindCheckBox(ContactListItemView contactListItemView, Cursor cursor, boolean z) {
        contactListItemView.setClickable(!z && this.mDisplayCheckBoxes);
        if (!this.mDisplayCheckBoxes || !z) {
            contactListItemView.hideCheckBox();
            return;
        }
        AppCompatCheckBox checkBox = contactListItemView.getCheckBox();
        long j = cursor.getLong(this.mContactIdColumnIndex);
        checkBox.setChecked(this.mSelectedContactIds.contains(Long.valueOf(j)));
        checkBox.setClickable(false);
        checkBox.setTag(Long.valueOf(j));
    }
}
