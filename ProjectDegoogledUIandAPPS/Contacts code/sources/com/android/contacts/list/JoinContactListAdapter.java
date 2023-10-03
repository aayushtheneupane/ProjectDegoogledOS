package com.android.contacts.list;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.contacts.R;

public class JoinContactListAdapter extends ContactListAdapter {
    private long mTargetContactId;

    /* access modifiers changed from: protected */
    public void bindHeaderView(View view, int i, Cursor cursor) {
    }

    public boolean isEmpty() {
        return false;
    }

    public JoinContactListAdapter(Context context) {
        super(context);
        setPinnedPartitionHeadersEnabled(true);
        setSectionHeaderDisplayEnabled(true);
        setIndexedPartition(1);
        setDirectorySearchMode(0);
    }

    /* access modifiers changed from: protected */
    public void addPartitions() {
        addPartition(false, true);
        addPartition(createDefaultDirectoryPartition());
    }

    public void setTargetContactId(long j) {
        this.mTargetContactId = j;
    }

    public void configureLoader(CursorLoader cursorLoader, long j) {
        Uri uri;
        JoinContactLoader joinContactLoader = (JoinContactLoader) cursorLoader;
        Uri.Builder buildUpon = ContactsContract.Contacts.CONTENT_URI.buildUpon();
        buildUpon.appendEncodedPath(String.valueOf(this.mTargetContactId));
        buildUpon.appendEncodedPath("suggestions");
        String queryString = getQueryString();
        if (!TextUtils.isEmpty(queryString)) {
            buildUpon.appendEncodedPath(Uri.encode(queryString));
        }
        buildUpon.appendQueryParameter("limit", String.valueOf(4));
        joinContactLoader.setSuggestionUri(buildUpon.build());
        joinContactLoader.setProjection(getProjection(false));
        if (!TextUtils.isEmpty(queryString)) {
            uri = ContactListAdapter.buildSectionIndexerUri(ContactsContract.Contacts.CONTENT_FILTER_URI).buildUpon().appendEncodedPath(Uri.encode(queryString)).appendQueryParameter("directory", String.valueOf(0)).build();
        } else {
            uri = ContactListAdapter.buildSectionIndexerUri(ContactsContract.Contacts.CONTENT_URI).buildUpon().appendQueryParameter("directory", String.valueOf(0)).build();
        }
        joinContactLoader.setUri(uri);
        joinContactLoader.setSelection("_id!=?");
        joinContactLoader.setSelectionArgs(new String[]{String.valueOf(this.mTargetContactId)});
        if (getSortOrder() == 1) {
            joinContactLoader.setSortOrder("sort_key");
        } else {
            joinContactLoader.setSortOrder("sort_key_alt");
        }
    }

    public void setSuggestionsCursor(Cursor cursor) {
        changeCursor(0, cursor);
    }

    public void configureDefaultPartition(boolean z, boolean z2) {
        super.configureDefaultPartition(false, true);
    }

    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }

    public int getItemViewType(int i, int i2) {
        return super.getItemViewType(i, i2);
    }

    /* access modifiers changed from: protected */
    public View newHeaderView(Context context, int i, Cursor cursor, ViewGroup viewGroup) {
        if (i == 0) {
            View inflate = inflate(R.layout.join_contact_picker_section_header, viewGroup);
            ((TextView) inflate.findViewById(R.id.text)).setText(R.string.separatorJoinAggregateSuggestions);
            return inflate;
        } else if (i != 1) {
            return null;
        } else {
            View inflate2 = inflate(R.layout.join_contact_picker_section_header, viewGroup);
            ((TextView) inflate2.findViewById(R.id.text)).setText(R.string.separatorJoinAggregateAll);
            return inflate2;
        }
    }

    /* access modifiers changed from: protected */
    public ContactListItemView newView(Context context, int i, Cursor cursor, int i2, ViewGroup viewGroup) {
        if (i == 0 || i == 1) {
            return super.newView(context, i, cursor, i2, viewGroup);
        }
        return null;
    }

    private View inflate(int i, ViewGroup viewGroup) {
        return LayoutInflater.from(getContext()).inflate(i, viewGroup, false);
    }

    /* access modifiers changed from: protected */
    public void bindView(View view, int i, Cursor cursor, int i2) {
        super.bindView(view, i, cursor, i2);
        if (i == 0) {
            ContactListItemView contactListItemView = (ContactListItemView) view;
            contactListItemView.setSectionHeader((String) null);
            bindPhoto(contactListItemView, i, cursor);
            bindNameAndViewId(contactListItemView, cursor);
        } else if (i == 1) {
            ContactListItemView contactListItemView2 = (ContactListItemView) view;
            bindSectionHeaderAndDivider(contactListItemView2, i2, cursor);
            bindPhoto(contactListItemView2, i, cursor);
            bindNameAndViewId(contactListItemView2, cursor);
        }
    }

    public Uri getContactUri(int i, Cursor cursor) {
        return ContactsContract.Contacts.getLookupUri(cursor.getLong(0), cursor.getString(6));
    }
}
