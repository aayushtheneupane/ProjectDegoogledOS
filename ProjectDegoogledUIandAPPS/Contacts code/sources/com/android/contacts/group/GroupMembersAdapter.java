package com.android.contacts.group;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import com.android.contacts.R;
import com.android.contacts.list.ContactListItemView;
import com.android.contacts.list.MultiSelectEntryContactListAdapter;

public class GroupMembersAdapter extends MultiSelectEntryContactListAdapter {
    private boolean mDisplayDeleteButtons;
    private long mGroupId;
    private final CharSequence mUnknownNameText;

    public static class GroupMembersQuery {
        /* access modifiers changed from: private */
        public static final String[] PROJECTION_ALTERNATIVE = {"contact_id", "raw_contact_id", "photo_id", "lookup", "contact_presence", "contact_status", "display_name_alt"};
        /* access modifiers changed from: private */
        public static final String[] PROJECTION_PRIMARY = {"contact_id", "raw_contact_id", "photo_id", "lookup", "contact_presence", "contact_status", "display_name"};
    }

    public GroupMembersAdapter(Context context) {
        super(context, 0);
        this.mUnknownNameText = context.getText(R.string.missing_name);
    }

    public void setGroupId(long j) {
        this.mGroupId = j;
    }

    public Uri getContactUri(int i) {
        Cursor cursor = (Cursor) getItem(i);
        return ContactsContract.Contacts.getLookupUri(cursor.getLong(0), cursor.getString(3));
    }

    public long getContactId(int i) {
        return ((Cursor) getItem(i)).getLong(0);
    }

    public void setDisplayDeleteButtons(boolean z) {
        this.mDisplayDeleteButtons = z;
        notifyDataSetChanged();
    }

    public void configureLoader(CursorLoader cursorLoader, long j) {
        String[] strArr;
        cursorLoader.setUri(ContactsContract.Data.CONTENT_URI.buildUpon().appendQueryParameter("directory", String.valueOf(0)).appendQueryParameter("android.provider.extra.ADDRESS_BOOK_INDEX", "true").build());
        cursorLoader.setSelection("mimetype=? AND data1=?");
        cursorLoader.setSelectionArgs(new String[]{"vnd.android.cursor.item/group_membership", String.valueOf(this.mGroupId)});
        if (getContactNameDisplayOrder() == 1) {
            strArr = GroupMembersQuery.PROJECTION_PRIMARY;
        } else {
            strArr = GroupMembersQuery.PROJECTION_ALTERNATIVE;
        }
        cursorLoader.setProjection(strArr);
        cursorLoader.setSortOrder(getSortOrder() == 1 ? "sort_key" : "sort_key_alt");
    }

    /* access modifiers changed from: protected */
    public ContactListItemView newView(Context context, int i, Cursor cursor, int i2, ViewGroup viewGroup) {
        ContactListItemView newView = super.newView(context, i, cursor, i2, viewGroup);
        newView.setUnknownNameText(this.mUnknownNameText);
        return newView;
    }

    /* access modifiers changed from: protected */
    public void bindView(View view, int i, Cursor cursor, int i2) {
        super.bindView(view, i, cursor, i2);
        ContactListItemView contactListItemView = (ContactListItemView) view;
        bindSectionHeaderAndDivider(contactListItemView, i2);
        bindName(contactListItemView, cursor);
        bindPhoto(contactListItemView, cursor, 2, 3, 6);
        bindDeleteButton(contactListItemView, i2);
    }

    /* access modifiers changed from: protected */
    public void bindSectionHeaderAndDivider(ContactListItemView contactListItemView, int i) {
        contactListItemView.setIsSectionHeaderEnabled(isSectionHeaderDisplayEnabled());
        if (isSectionHeaderDisplayEnabled()) {
            contactListItemView.setSectionHeader(getItemPlacementInSection(i).sectionHeader);
        } else {
            contactListItemView.setSectionHeader((String) null);
        }
    }

    private void bindName(ContactListItemView contactListItemView, Cursor cursor) {
        contactListItemView.showDisplayName(cursor, 6, getContactNameDisplayOrder());
    }

    private void bindDeleteButton(ContactListItemView contactListItemView, int i) {
        if (this.mDisplayDeleteButtons) {
            contactListItemView.getDeleteImageButton(getDeleteContactListener(), i);
        } else {
            contactListItemView.hideDeleteImageButton();
        }
    }
}
