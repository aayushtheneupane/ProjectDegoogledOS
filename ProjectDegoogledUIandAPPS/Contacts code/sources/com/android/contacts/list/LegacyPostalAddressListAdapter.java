package com.android.contacts.list;

import android.content.ContentUris;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.android.contacts.model.account.BaseAccountType;

public class LegacyPostalAddressListAdapter extends ContactEntryListAdapter {
    static final String[] POSTALS_PROJECTION = {"_id", BaseAccountType.Attr.TYPE, "label", "data", "display_name", "phonetic_name"};
    private CharSequence mUnknownNameText;

    public LegacyPostalAddressListAdapter(Context context) {
        super(context);
        this.mUnknownNameText = context.getText(17039374);
    }

    public void configureLoader(CursorLoader cursorLoader, long j) {
        cursorLoader.setUri(Contacts.ContactMethods.CONTENT_URI);
        cursorLoader.setProjection(POSTALS_PROJECTION);
        cursorLoader.setSortOrder("display_name");
        cursorLoader.setSelection("kind=2");
    }

    public Uri getContactMethodUri(int i) {
        return ContentUris.withAppendedId(Contacts.ContactMethods.CONTENT_URI, ((Cursor) getItem(i)).getLong(0));
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
        bindPostalAddress(contactListItemView, cursor);
    }

    /* access modifiers changed from: protected */
    public void bindName(ContactListItemView contactListItemView, Cursor cursor) {
        contactListItemView.showDisplayName(cursor, 4, getContactNameDisplayOrder());
        contactListItemView.showPhoneticName(cursor, 5);
    }

    /* access modifiers changed from: protected */
    public void bindPostalAddress(ContactListItemView contactListItemView, Cursor cursor) {
        CharSequence charSequence;
        if (!cursor.isNull(1)) {
            charSequence = ContactsContract.CommonDataKinds.StructuredPostal.getTypeLabel(getContext().getResources(), cursor.getInt(1), cursor.getString(2));
        } else {
            charSequence = null;
        }
        contactListItemView.setLabel(charSequence);
        contactListItemView.showData(cursor, 3);
    }
}
