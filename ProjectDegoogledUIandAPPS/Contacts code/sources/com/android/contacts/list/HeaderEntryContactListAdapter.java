package com.android.contacts.list;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import com.android.contacts.R;

public class HeaderEntryContactListAdapter extends DefaultContactListAdapter {
    private boolean mShowCreateContact;

    public HeaderEntryContactListAdapter(Context context) {
        super(context);
    }

    private int getHeaderEntryCount() {
        return (isSearchMode() || !this.mShowCreateContact) ? 0 : 1;
    }

    public void setShowCreateContact(boolean z) {
        this.mShowCreateContact = z;
        invalidate();
    }

    public int getCount() {
        return super.getCount() + getHeaderEntryCount();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ContactListItemView contactListItemView;
        if (i != 0 || getHeaderEntryCount() <= 0) {
            return super.getView(i - getHeaderEntryCount(), view, viewGroup);
        }
        if (view == null) {
            contactListItemView = newView(getContext(), 0, getCursor(0), 0, viewGroup);
        } else {
            contactListItemView = (ContactListItemView) view;
        }
        contactListItemView.setDrawableResource(R.drawable.quantum_ic_person_add_vd_theme_24);
        contactListItemView.setDisplayName(getContext().getResources().getString(R.string.header_entry_contact_list_adapter_header_title));
        return contactListItemView;
    }

    public Object getItem(int i) {
        return super.getItem(i - getHeaderEntryCount());
    }

    public boolean isEnabled(int i) {
        return i < getHeaderEntryCount() || super.isEnabled(i - getHeaderEntryCount());
    }

    public int getPartitionForPosition(int i) {
        return super.getPartitionForPosition(i - getHeaderEntryCount());
    }

    /* access modifiers changed from: protected */
    public void bindView(View view, int i, Cursor cursor, int i2) {
        super.bindView(view, i, cursor, i2 + getHeaderEntryCount());
    }

    public int getItemViewType(int i) {
        if (i != 0 || getHeaderEntryCount() <= 0) {
            return super.getItemViewType(i - getHeaderEntryCount());
        }
        return getViewTypeCount() - 1;
    }

    public int getViewTypeCount() {
        return super.getViewTypeCount() + 1;
    }

    /* access modifiers changed from: protected */
    public boolean getExtraStartingSection() {
        return getHeaderEntryCount() > 0;
    }
}
