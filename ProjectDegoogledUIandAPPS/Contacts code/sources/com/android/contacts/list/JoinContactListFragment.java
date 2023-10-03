package com.android.contacts.list;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.contacts.R;
import com.android.contacts.list.JoinContactLoader;

public class JoinContactListFragment extends ContactEntryListFragment<JoinContactListAdapter> {
    private OnContactPickerActionListener mListener;
    private final LoaderManager.LoaderCallbacks<Cursor> mLoaderCallbacks = new LoaderManager.LoaderCallbacks<Cursor>() {
        public void onLoaderReset(Loader<Cursor> loader) {
        }

        public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
            if (i == -2) {
                return new CursorLoader(JoinContactListFragment.this.getActivity(), ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, JoinContactListFragment.this.mTargetContactId), new String[]{"display_name"}, (String) null, (String[]) null, (String) null);
            }
            if (i == 1) {
                JoinContactLoader joinContactLoader = new JoinContactLoader(JoinContactListFragment.this.getActivity());
                JoinContactListAdapter joinContactListAdapter = (JoinContactListAdapter) JoinContactListFragment.this.getAdapter();
                if (joinContactListAdapter != null) {
                    joinContactListAdapter.configureLoader(joinContactLoader, 0);
                }
                return joinContactLoader;
            }
            throw new IllegalArgumentException("No loader for ID=" + i);
        }

        public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
            int id = loader.getId();
            if (id != -2) {
                if (id == 1 && cursor != null) {
                    JoinContactListFragment.this.onContactListLoaded(((JoinContactLoader.JoinContactLoaderResult) cursor).suggestionCursor, cursor);
                    JoinContactListFragment.this.maybeLogListEvent();
                }
            } else if (cursor != null && cursor.moveToFirst()) {
                JoinContactListFragment.this.showTargetContactName(cursor.getString(0));
            }
        }
    };
    /* access modifiers changed from: private */
    public long mTargetContactId;

    public JoinContactListFragment() {
        setPhotoLoaderEnabled(true);
        setSectionHeaderDisplayEnabled(true);
        setVisibleScrollbarEnabled(false);
        setQuickContactEnabled(false);
        setListType(15);
        setLogListEvents(true);
    }

    public void setOnContactPickerActionListener(OnContactPickerActionListener onContactPickerActionListener) {
        this.mListener = onContactPickerActionListener;
    }

    /* access modifiers changed from: protected */
    public void startLoading() {
        configureAdapter();
        getLoaderManager().initLoader(-2, (Bundle) null, this.mLoaderCallbacks);
        getLoaderManager().restartLoader(1, (Bundle) null, this.mLoaderCallbacks);
    }

    /* access modifiers changed from: private */
    public void onContactListLoaded(Cursor cursor, Cursor cursor2) {
        ((JoinContactListAdapter) getAdapter()).setSuggestionsCursor(cursor);
        setVisibleScrollbarEnabled(true);
        onPartitionLoaded(1, cursor2);
    }

    /* access modifiers changed from: private */
    public void showTargetContactName(String str) {
        Activity activity = getActivity();
        TextView textView = (TextView) activity.findViewById(R.id.join_contact_blurb);
        if (TextUtils.isEmpty(str)) {
            str = activity.getString(R.string.missing_name);
        }
        textView.setText(activity.getString(R.string.blurbJoinContactDataWith, new Object[]{str}));
    }

    public void setTargetContactId(long j) {
        this.mTargetContactId = j;
    }

    public JoinContactListAdapter createListAdapter() {
        JoinContactListAdapter joinContactListAdapter = new JoinContactListAdapter(getActivity());
        joinContactListAdapter.setPhotoPosition(ContactListItemView.getDefaultPhotoPosition(false));
        return joinContactListAdapter;
    }

    /* access modifiers changed from: protected */
    public void configureAdapter() {
        super.configureAdapter();
        ((JoinContactListAdapter) getAdapter()).setTargetContactId(this.mTargetContactId);
    }

    /* access modifiers changed from: protected */
    public View inflateView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.join_contact_picker_list_content, (ViewGroup) null);
    }

    /* access modifiers changed from: protected */
    public void onItemClick(int i, long j) {
        Uri contactUri = ((JoinContactListAdapter) getAdapter()).getContactUri(i);
        if (contactUri != null) {
            this.mListener.onPickContactAction(contactUri);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putLong("targetContactId", this.mTargetContactId);
    }

    public void restoreSavedState(Bundle bundle) {
        super.restoreSavedState(bundle);
        if (bundle != null) {
            this.mTargetContactId = bundle.getLong("targetContactId");
        }
    }

    public void setQueryString(String str, boolean z) {
        super.setQueryString(str, z);
        setSearchMode(!TextUtils.isEmpty(str));
    }
}
