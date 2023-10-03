package com.android.dialer.contactsfragment;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.p000v4.util.ArrayMap;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.QuickContactBadge;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.contactphoto.ContactPhotoManager;
import com.android.dialer.contactsfragment.ContactsFragment;
import com.android.tools.p006r8.GeneratedOutlineSupport;

final class ContactsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private int[] counts = new int[0];
    private Cursor cursor;
    private final int header;
    private String[] headers = new String[0];
    private final ArrayMap<ContactViewHolder, Integer> holderMap = new ArrayMap<>();
    private final ContactsFragment.OnContactSelectedListener onContactSelectedListener;

    ContactsAdapter(Context context2, int i, ContactsFragment.OnContactSelectedListener onContactSelectedListener2) {
        this.context = context2;
        this.header = i;
        Assert.isNotNull(onContactSelectedListener2);
        this.onContactSelectedListener = onContactSelectedListener2;
    }

    /* access modifiers changed from: package-private */
    public String getHeaderString(int i) {
        if (this.header != 0) {
            if (i == 0) {
                return "+";
            }
            i--;
        }
        int i2 = 0;
        int i3 = -1;
        while (i2 <= i) {
            i3++;
            i2 += this.counts[i3];
        }
        return this.headers[i3];
    }

    public int getItemCount() {
        Cursor cursor2 = this.cursor;
        int count = (cursor2 == null || cursor2.isClosed()) ? 0 : this.cursor.getCount();
        return this.header != 0 ? count + 1 : count;
    }

    public int getItemViewType(int i) {
        return (this.header == 0 || i != 0) ? 2 : 1;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        Uri uri;
        if (!(viewHolder instanceof AddContactViewHolder)) {
            ContactViewHolder contactViewHolder = (ContactViewHolder) viewHolder;
            this.holderMap.put(contactViewHolder, Integer.valueOf(i));
            this.cursor.moveToPosition(i);
            if (this.header != 0) {
                this.cursor.moveToPrevious();
            }
            String string = this.cursor.getString(1);
            String headerString = getHeaderString(i);
            Cursor cursor2 = this.cursor;
            Uri lookupUri = ContactsContract.Contacts.getLookupUri(cursor2.getLong(0), cursor2.getString(4));
            ContactPhotoManager instance = ContactPhotoManager.getInstance(this.context);
            QuickContactBadge photo = contactViewHolder.getPhoto();
            long j = this.cursor.getLong(2);
            String string2 = this.cursor.getString(3);
            if (string2 == null) {
                uri = null;
            } else {
                uri = Uri.parse(string2);
            }
            instance.loadDialerThumbnailOrPhoto(photo, lookupUri, j, uri, string, 1);
            contactViewHolder.getPhoto().setContentDescription(this.context.getString(R.string.description_quick_contact_for, new Object[]{string}));
            contactViewHolder.bind(headerString, string, lookupUri, this.cursor.getLong(0), i == 0 || !headerString.equals(getHeaderString(i - 1)));
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 1) {
            return new AddContactViewHolder(LayoutInflater.from(this.context).inflate(R.layout.add_contact_row, viewGroup, false));
        }
        if (i == 2) {
            return new ContactViewHolder(LayoutInflater.from(this.context).inflate(R.layout.contact_row, viewGroup, false), this.onContactSelectedListener);
        }
        throw new IllegalStateException(GeneratedOutlineSupport.outline5("Invalid view type: ", i));
    }

    public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof ContactViewHolder) {
            this.holderMap.remove(viewHolder);
        }
    }

    /* access modifiers changed from: package-private */
    public void refreshHeaders() {
        for (ContactViewHolder next : this.holderMap.keySet()) {
            int intValue = this.holderMap.get(next).intValue();
            int i = 0;
            if (!(intValue == 0 || !getHeaderString(intValue).equals(getHeaderString(intValue + -1)))) {
                i = 4;
            }
            next.getHeaderView().setVisibility(i);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateCursor(Cursor cursor2) {
        this.cursor = cursor2;
        this.headers = cursor2.getExtras().getStringArray("android.provider.extra.ADDRESS_BOOK_INDEX_TITLES");
        this.counts = cursor2.getExtras().getIntArray("android.provider.extra.ADDRESS_BOOK_INDEX_COUNTS");
        int[] iArr = this.counts;
        if (iArr != null) {
            int i = 0;
            for (int i2 : iArr) {
                i += i2;
            }
            if (i != cursor2.getCount()) {
                LogUtil.m8e("ContactsAdapter", "Count sum (%d) != cursor count (%d).", Integer.valueOf(i), Integer.valueOf(cursor2.getCount()));
            }
        }
        notifyDataSetChanged();
    }
}
