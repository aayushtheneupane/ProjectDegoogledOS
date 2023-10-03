package com.android.contacts.list;

import android.content.ContentUris;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Contacts;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.android.contacts.ContactSaveService;

public class LegacyContactListAdapter extends ContactEntryListAdapter {
    static final String[] PEOPLE_PROJECTION = {"_id", "display_name", "phonetic_name", ContactSaveService.EXTRA_STARRED_FLAG, "mode"};
    private CharSequence mUnknownNameText;

    public LegacyContactListAdapter(Context context) {
        super(context);
        this.mUnknownNameText = context.getText(17039374);
    }

    public void configureLoader(CursorLoader cursorLoader, long j) {
        cursorLoader.setUri(Contacts.People.CONTENT_URI);
        cursorLoader.setProjection(PEOPLE_PROJECTION);
        cursorLoader.setSortOrder("display_name");
    }

    public Uri getPersonUri(int i) {
        return ContentUris.withAppendedId(Contacts.People.CONTENT_URI, ((Cursor) getItem(i)).getLong(0));
    }

    /* access modifiers changed from: protected */
    public ContactListItemView newView(Context context, int i, Cursor cursor, int i2, ViewGroup viewGroup) {
        ContactListItemView contactListItemView = new ContactListItemView(context, (AttributeSet) null);
        contactListItemView.setUnknownNameText(this.mUnknownNameText);
        return contactListItemView;
    }

    /* access modifiers changed from: protected */
    public void bindView(View view, int i, Cursor cursor, int i2) {
        super.bindView(view, i, cursor, i2);
        ContactListItemView contactListItemView = (ContactListItemView) view;
        bindName(contactListItemView, cursor);
        bindViewId(contactListItemView, cursor, 0);
        bindPresence(contactListItemView, cursor);
    }

    /* access modifiers changed from: protected */
    public void bindName(ContactListItemView contactListItemView, Cursor cursor) {
        contactListItemView.showDisplayName(cursor, 1, getContactNameDisplayOrder());
        contactListItemView.showPhoneticName(cursor, 2);
    }

    /* access modifiers changed from: protected */
    public void bindPresence(ContactListItemView contactListItemView, Cursor cursor) {
        contactListItemView.showPresenceAndStatusMessage(cursor, 4, 0);
    }
}
