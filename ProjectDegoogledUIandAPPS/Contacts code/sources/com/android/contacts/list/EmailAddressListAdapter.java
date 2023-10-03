package com.android.contacts.list;

import android.content.ContentUris;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

public class EmailAddressListAdapter extends ContactEntryListAdapter {
    private final CharSequence mUnknownNameText;

    protected static class EmailQuery {
        /* access modifiers changed from: private */
        public static final String[] PROJECTION_ALTERNATIVE = {"_id", "data2", "data3", "data1", "photo_id", "lookup", "display_name_alt"};
        /* access modifiers changed from: private */
        public static final String[] PROJECTION_PRIMARY = {"_id", "data2", "data3", "data1", "photo_id", "lookup", "display_name"};
    }

    public EmailAddressListAdapter(Context context) {
        super(context);
        this.mUnknownNameText = context.getText(17039374);
    }

    public void configureLoader(CursorLoader cursorLoader, long j) {
        Uri.Builder builder;
        if (isSearchMode()) {
            builder = ContactsContract.CommonDataKinds.Email.CONTENT_FILTER_URI.buildUpon();
            String queryString = getQueryString();
            if (TextUtils.isEmpty(queryString)) {
                queryString = "";
            }
            builder.appendPath(queryString);
        } else {
            builder = ContactsContract.CommonDataKinds.Email.CONTENT_URI.buildUpon();
            if (isSectionHeaderDisplayEnabled()) {
                builder.appendQueryParameter("android.provider.extra.ADDRESS_BOOK_INDEX", "true");
            }
        }
        builder.appendQueryParameter("directory", String.valueOf(j));
        builder.appendQueryParameter("remove_duplicate_entries", "true");
        cursorLoader.setUri(builder.build());
        if (getContactNameDisplayOrder() == 1) {
            cursorLoader.setProjection(EmailQuery.PROJECTION_PRIMARY);
        } else {
            cursorLoader.setProjection(EmailQuery.PROJECTION_ALTERNATIVE);
        }
        if (getSortOrder() == 1) {
            cursorLoader.setSortOrder("sort_key");
        } else {
            cursorLoader.setSortOrder("sort_key_alt");
        }
    }

    public Uri getDataUri(int i) {
        return ContentUris.withAppendedId(ContactsContract.Data.CONTENT_URI, ((Cursor) getItem(i)).getLong(0));
    }

    /* access modifiers changed from: protected */
    public ContactListItemView newView(Context context, int i, Cursor cursor, int i2, ViewGroup viewGroup) {
        ContactListItemView newView = super.newView(context, i, cursor, i2, viewGroup);
        newView.setUnknownNameText(this.mUnknownNameText);
        newView.setQuickContactEnabled(isQuickContactEnabled());
        return newView;
    }

    /* access modifiers changed from: protected */
    public void bindView(View view, int i, Cursor cursor, int i2) {
        super.bindView(view, i, cursor, i2);
        ContactListItemView contactListItemView = (ContactListItemView) view;
        bindSectionHeaderAndDivider(contactListItemView, i2);
        bindName(contactListItemView, cursor);
        bindViewId(contactListItemView, cursor, 0);
        bindPhoto(contactListItemView, cursor);
        bindEmailAddress(contactListItemView, cursor);
    }

    /* access modifiers changed from: protected */
    public void bindEmailAddress(ContactListItemView contactListItemView, Cursor cursor) {
        CharSequence charSequence;
        if (!cursor.isNull(1)) {
            charSequence = ContactsContract.CommonDataKinds.Email.getTypeLabel(getContext().getResources(), cursor.getInt(1), cursor.getString(2));
        } else {
            charSequence = null;
        }
        contactListItemView.setLabel(charSequence);
        contactListItemView.showData(cursor, 3);
    }

    /* access modifiers changed from: protected */
    public void bindSectionHeaderAndDivider(ContactListItemView contactListItemView, int i) {
        int sectionForPosition = getSectionForPosition(i);
        if (getPositionForSection(sectionForPosition) == i) {
            contactListItemView.setSectionHeader((String) getSections()[sectionForPosition]);
        } else {
            contactListItemView.setSectionHeader((String) null);
        }
    }

    /* access modifiers changed from: protected */
    public void bindName(ContactListItemView contactListItemView, Cursor cursor) {
        contactListItemView.showDisplayName(cursor, 6, getContactNameDisplayOrder());
    }

    /* access modifiers changed from: protected */
    public void bindPhoto(ContactListItemView contactListItemView, Cursor cursor) {
        long j = !cursor.isNull(4) ? cursor.getLong(4) : 0;
        getPhotoLoader().loadThumbnail(contactListItemView.getPhotoView(), j, false, getCircularPhotos(), j == 0 ? getDefaultImageRequestFromCursor(cursor, 6, 5) : null);
    }
}
