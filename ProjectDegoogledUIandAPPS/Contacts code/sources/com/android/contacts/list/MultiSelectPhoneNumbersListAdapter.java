package com.android.contacts.list;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.android.contacts.group.GroupUtil;

public class MultiSelectPhoneNumbersListAdapter extends MultiSelectEntryContactListAdapter {
    private long[] mContactIdsFilter = null;
    private final CharSequence mUnknownNameText;

    public static class PhoneQuery {
        public static final String[] PROJECTION_ALTERNATIVE = {"_id", "data2", "data3", "data1", "contact_id", "lookup", "photo_id", "display_name_alt", "photo_thumb_uri"};
        public static final String[] PROJECTION_PRIMARY = {"_id", "data2", "data3", "data1", "contact_id", "lookup", "photo_id", "display_name", "photo_thumb_uri"};
    }

    public MultiSelectPhoneNumbersListAdapter(Context context) {
        super(context, 0);
        this.mUnknownNameText = context.getText(17039374);
    }

    public void setArguments(Bundle bundle) {
        this.mContactIdsFilter = bundle.getLongArray("com.android.contacts.extra.SELECTION_ITEM_LIST");
    }

    public void configureLoader(CursorLoader cursorLoader, long j) {
        Uri.Builder builder;
        if (isSearchMode()) {
            builder = ContactsContract.CommonDataKinds.Phone.CONTENT_FILTER_URI.buildUpon();
            String queryString = getQueryString();
            if (TextUtils.isEmpty(queryString)) {
                queryString = "";
            }
            builder.appendPath(queryString);
        } else {
            builder = ContactsContract.CommonDataKinds.Phone.CONTENT_URI.buildUpon();
            if (isSectionHeaderDisplayEnabled()) {
                builder.appendQueryParameter("android.provider.extra.ADDRESS_BOOK_INDEX", "true");
            }
        }
        builder.appendQueryParameter("directory", String.valueOf(j));
        cursorLoader.setUri(builder.build());
        if (this.mContactIdsFilter != null) {
            cursorLoader.setSelection("contact_id IN (" + GroupUtil.convertArrayToString(this.mContactIdsFilter) + ")");
        }
        if (getContactNameDisplayOrder() == 1) {
            cursorLoader.setProjection(PhoneQuery.PROJECTION_PRIMARY);
        } else {
            cursorLoader.setProjection(PhoneQuery.PROJECTION_ALTERNATIVE);
        }
        if (getSortOrder() == 1) {
            cursorLoader.setSortOrder("sort_key");
        } else {
            cursorLoader.setSortOrder("sort_key_alt");
        }
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
        cursor.moveToPosition(i2);
        boolean z = !cursor.moveToPrevious() || cursor.isBeforeFirst() || cursor.getLong(4) != cursor.getLong(4);
        cursor.moveToPosition(i2);
        bindViewId(contactListItemView, cursor, 0);
        if (z) {
            bindName(contactListItemView, cursor);
            bindPhoto(contactListItemView, cursor, 6, 5, 7);
        } else {
            unbindName(contactListItemView);
            contactListItemView.removePhotoView(true, false);
        }
        bindPhoneNumber(contactListItemView, cursor);
    }

    /* access modifiers changed from: protected */
    public void unbindName(ContactListItemView contactListItemView) {
        contactListItemView.hideDisplayName();
    }

    /* access modifiers changed from: protected */
    public void bindPhoneNumber(ContactListItemView contactListItemView, Cursor cursor) {
        CharSequence charSequence;
        if (!cursor.isNull(1)) {
            charSequence = ContactsContract.CommonDataKinds.Phone.getTypeLabel(getContext().getResources(), cursor.getInt(1), cursor.getString(2));
        } else {
            charSequence = null;
        }
        contactListItemView.setLabel(charSequence);
        contactListItemView.showData(cursor, 3);
    }

    /* access modifiers changed from: protected */
    public void bindName(ContactListItemView contactListItemView, Cursor cursor) {
        contactListItemView.showDisplayName(cursor, 7, getContactNameDisplayOrder());
    }
}
