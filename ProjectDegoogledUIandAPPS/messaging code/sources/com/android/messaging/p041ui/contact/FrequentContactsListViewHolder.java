package com.android.messaging.p041ui.contact;

import android.content.Context;
import android.database.Cursor;
import com.android.messaging.R;
import com.android.messaging.p041ui.C1049J;
import com.android.messaging.p041ui.contact.ContactListItemView;

/* renamed from: com.android.messaging.ui.contact.FrequentContactsListViewHolder */
public class FrequentContactsListViewHolder extends C1049J {
    public FrequentContactsListViewHolder(Context context, ContactListItemView.HostInterface hostInterface) {
        super(context, new ContactListAdapter(context, (Cursor) null, hostInterface, false));
    }

    /* access modifiers changed from: protected */
    public int getEmptyViewImageResId() {
        return R.drawable.ic_oobe_freq_list;
    }

    /* access modifiers changed from: protected */
    public int getEmptyViewResId() {
        return R.id.empty_view;
    }

    /* access modifiers changed from: protected */
    public int getEmptyViewTitleResId() {
        return R.string.contact_list_empty_text;
    }

    /* access modifiers changed from: protected */
    public int getLayoutResId() {
        return R.layout.frequent_contacts_list_view;
    }

    /* access modifiers changed from: protected */
    public int getListViewResId() {
        return R.id.frequent_contacts_list;
    }

    /* access modifiers changed from: protected */
    public int getPageTitleResId() {
        return R.string.contact_picker_frequents_tab_title;
    }
}
