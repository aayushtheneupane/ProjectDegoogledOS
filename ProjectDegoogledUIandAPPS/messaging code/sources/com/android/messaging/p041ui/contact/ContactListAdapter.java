package com.android.messaging.p041ui.contact;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.SectionIndexer;
import com.android.messaging.R;
import com.android.messaging.p041ui.contact.ContactListItemView;
import com.android.messaging.util.C1424b;

/* renamed from: com.android.messaging.ui.contact.ContactListAdapter */
public class ContactListAdapter extends CursorAdapter implements SectionIndexer {
    private final ContactListItemView.HostInterface mClivHostInterface;
    private final boolean mNeedAlphabetHeader;
    private ContactSectionIndexer mSectionIndexer;

    public ContactListAdapter(Context context, Cursor cursor, ContactListItemView.HostInterface hostInterface, boolean z) {
        super(context, cursor, 0);
        this.mClivHostInterface = hostInterface;
        this.mNeedAlphabetHeader = z;
        this.mSectionIndexer = new ContactSectionIndexer(cursor);
    }

    public void bindView(View view, Context context, Cursor cursor) {
        String str;
        C1424b.m3592ia(view instanceof ContactListItemView);
        ContactListItemView contactListItemView = (ContactListItemView) view;
        if (this.mNeedAlphabetHeader) {
            int position = cursor.getPosition();
            int sectionForPosition = this.mSectionIndexer.getSectionForPosition(position);
            if (this.mSectionIndexer.getPositionForSection(sectionForPosition) == position) {
                str = (String) this.mSectionIndexer.getSections()[sectionForPosition];
                contactListItemView.bind(cursor, this.mClivHostInterface, this.mNeedAlphabetHeader, str);
            }
        }
        str = null;
        contactListItemView.bind(cursor, this.mClivHostInterface, this.mNeedAlphabetHeader, str);
    }

    public int getPositionForSection(int i) {
        return this.mSectionIndexer.getPositionForSection(i);
    }

    public int getSectionForPosition(int i) {
        return this.mSectionIndexer.getSectionForPosition(i);
    }

    public Object[] getSections() {
        return this.mSectionIndexer.getSections();
    }

    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.contact_list_item_view, viewGroup, false);
    }

    public Cursor swapCursor(Cursor cursor) {
        this.mSectionIndexer = new ContactSectionIndexer(cursor);
        return super.swapCursor(cursor);
    }
}
