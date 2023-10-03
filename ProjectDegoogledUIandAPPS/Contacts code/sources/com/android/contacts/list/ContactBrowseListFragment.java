package com.android.contacts.list;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Loader;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import com.android.common.widget.CompositeCursorAdapter;
import java.util.List;

public abstract class ContactBrowseListFragment extends MultiSelectContactsListFragment<ContactListAdapter> {
    private ContactLookupTask mContactLookupTask;
    private boolean mDelaySelection;
    private ContactListFilter mFilter;
    private Handler mHandler;
    private int mLastSelectedPosition = -1;
    protected OnContactBrowserActionListener mListener;
    private String mPersistentSelectionPrefix = "defaultContactBrowserSelection";
    private SharedPreferences mPrefs;
    private boolean mRefreshingContactUri;
    private long mSelectedContactDirectoryId;
    private long mSelectedContactId;
    private String mSelectedContactLookupKey;
    private Uri mSelectedContactUri;
    private boolean mSelectionPersistenceRequested;
    private boolean mSelectionRequired;
    private boolean mSelectionToScreenRequested;
    private boolean mSelectionVerified;
    private boolean mSmoothScrollRequested;
    private boolean mStartedLoading;

    public void onLoaderReset(Loader<Cursor> loader) {
    }

    private final class ContactLookupTask extends AsyncTask<Void, Void, Uri> {
        private boolean mIsCancelled;
        private final Uri mUri;

        public ContactLookupTask(Uri uri) {
            this.mUri = uri;
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Removed duplicated region for block: B:28:0x0085  */
        /* JADX WARNING: Removed duplicated region for block: B:32:0x008c  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public android.net.Uri doInBackground(java.lang.Void... r9) {
            /*
                r8 = this;
                java.lang.String r9 = "ContactList"
                r0 = 0
                com.android.contacts.list.ContactBrowseListFragment r1 = com.android.contacts.list.ContactBrowseListFragment.this     // Catch:{ Exception -> 0x006b, all -> 0x0068 }
                android.content.Context r1 = r1.getContext()     // Catch:{ Exception -> 0x006b, all -> 0x0068 }
                android.content.ContentResolver r2 = r1.getContentResolver()     // Catch:{ Exception -> 0x006b, all -> 0x0068 }
                android.net.Uri r1 = r8.mUri     // Catch:{ Exception -> 0x006b, all -> 0x0068 }
                android.net.Uri r3 = com.android.contacts.util.ContactLoaderUtils.ensureIsContactUri(r2, r1)     // Catch:{ Exception -> 0x006b, all -> 0x0068 }
                java.lang.String r1 = "_id"
                java.lang.String r4 = "lookup"
                java.lang.String[] r4 = new java.lang.String[]{r1, r4}     // Catch:{ Exception -> 0x006b, all -> 0x0068 }
                r5 = 0
                r6 = 0
                r7 = 0
                android.database.Cursor r1 = r2.query(r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x006b, all -> 0x0068 }
                if (r1 == 0) goto L_0x004c
                boolean r2 = r1.moveToFirst()     // Catch:{ Exception -> 0x004a }
                if (r2 == 0) goto L_0x004c
                r2 = 0
                long r2 = r1.getLong(r2)     // Catch:{ Exception -> 0x004a }
                r4 = 1
                java.lang.String r4 = r1.getString(r4)     // Catch:{ Exception -> 0x004a }
                r5 = 0
                int r7 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
                if (r7 == 0) goto L_0x004c
                boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch:{ Exception -> 0x004a }
                if (r5 != 0) goto L_0x004c
                android.net.Uri r9 = android.provider.ContactsContract.Contacts.getLookupUri(r2, r4)     // Catch:{ Exception -> 0x004a }
                if (r1 == 0) goto L_0x0049
                r1.close()
            L_0x0049:
                return r9
            L_0x004a:
                r2 = move-exception
                goto L_0x006d
            L_0x004c:
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x004a }
                r2.<init>()     // Catch:{ Exception -> 0x004a }
                java.lang.String r3 = "Error: No contact ID or lookup key for contact "
                r2.append(r3)     // Catch:{ Exception -> 0x004a }
                android.net.Uri r3 = r8.mUri     // Catch:{ Exception -> 0x004a }
                r2.append(r3)     // Catch:{ Exception -> 0x004a }
                java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x004a }
                android.util.Log.e(r9, r2)     // Catch:{ Exception -> 0x004a }
                if (r1 == 0) goto L_0x0067
                r1.close()
            L_0x0067:
                return r0
            L_0x0068:
                r9 = move-exception
                r1 = r0
                goto L_0x008a
            L_0x006b:
                r2 = move-exception
                r1 = r0
            L_0x006d:
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0089 }
                r3.<init>()     // Catch:{ all -> 0x0089 }
                java.lang.String r4 = "Error loading the contact: "
                r3.append(r4)     // Catch:{ all -> 0x0089 }
                android.net.Uri r4 = r8.mUri     // Catch:{ all -> 0x0089 }
                r3.append(r4)     // Catch:{ all -> 0x0089 }
                java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0089 }
                android.util.Log.e(r9, r3, r2)     // Catch:{ all -> 0x0089 }
                if (r1 == 0) goto L_0x0088
                r1.close()
            L_0x0088:
                return r0
            L_0x0089:
                r9 = move-exception
            L_0x008a:
                if (r1 == 0) goto L_0x008f
                r1.close()
            L_0x008f:
                throw r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.list.ContactBrowseListFragment.ContactLookupTask.doInBackground(java.lang.Void[]):android.net.Uri");
        }

        public void cancel() {
            super.cancel(true);
            this.mIsCancelled = true;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Uri uri) {
            if (!this.mIsCancelled && ContactBrowseListFragment.this.isAdded()) {
                ContactBrowseListFragment.this.onContactUriQueryFinished(uri);
            }
        }
    }

    private Handler getHandler() {
        if (this.mHandler == null) {
            this.mHandler = new Handler() {
                public void handleMessage(Message message) {
                    if (message.what == 1) {
                        ContactBrowseListFragment.this.selectDefaultContact();
                    }
                }
            };
        }
        return this.mHandler;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mPrefs = PreferenceManager.getDefaultSharedPreferences(activity);
        restoreFilter();
        restoreSelectedUri(false);
    }

    /* access modifiers changed from: protected */
    public void setSearchMode(boolean z) {
        if (isSearchMode() != z) {
            if (!z) {
                restoreSelectedUri(true);
            }
            super.setSearchMode(z);
        }
    }

    public void updateListFilter(ContactListFilter contactListFilter, boolean z) {
        if (this.mFilter != null || contactListFilter != null) {
            ContactListFilter contactListFilter2 = this.mFilter;
            if (contactListFilter2 == null || !contactListFilter2.equals(contactListFilter)) {
                if (Log.isLoggable("ContactList", 2)) {
                    Log.v("ContactList", "New filter: " + contactListFilter);
                }
                setListType(contactListFilter.toListType());
                setLogListEvents(true);
                this.mFilter = contactListFilter;
                this.mLastSelectedPosition = -1;
                if (z) {
                    this.mSelectedContactUri = null;
                    restoreSelectedUri(true);
                }
                reloadData();
                return;
            }
            setLogListEvents(false);
        }
    }

    public void restoreSavedState(Bundle bundle) {
        super.restoreSavedState(bundle);
        if (bundle != null) {
            this.mFilter = (ContactListFilter) bundle.getParcelable("filter");
            this.mSelectedContactUri = (Uri) bundle.getParcelable("selectedUri");
            this.mSelectionVerified = bundle.getBoolean("selectionVerified");
            this.mLastSelectedPosition = bundle.getInt("lastSelected");
            parseSelectedContactUri();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("filter", this.mFilter);
        bundle.putParcelable("selectedUri", this.mSelectedContactUri);
        bundle.putBoolean("selectionVerified", this.mSelectionVerified);
        bundle.putInt("lastSelected", this.mLastSelectedPosition);
    }

    /* access modifiers changed from: protected */
    public void refreshSelectedContactUri() {
        ContactLookupTask contactLookupTask = this.mContactLookupTask;
        if (contactLookupTask != null) {
            contactLookupTask.cancel();
        }
        if (isSelectionVisible()) {
            this.mRefreshingContactUri = true;
            Uri uri = this.mSelectedContactUri;
            if (uri == null) {
                onContactUriQueryFinished((Uri) null);
                return;
            }
            long j = this.mSelectedContactDirectoryId;
            if (j == 0 || j == 1) {
                this.mContactLookupTask = new ContactLookupTask(this.mSelectedContactUri);
                this.mContactLookupTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[]) null);
                return;
            }
            onContactUriQueryFinished(uri);
        }
    }

    /* access modifiers changed from: protected */
    public void onContactUriQueryFinished(Uri uri) {
        this.mRefreshingContactUri = false;
        this.mSelectedContactUri = uri;
        parseSelectedContactUri();
        checkSelection();
    }

    public void setSelectedContactUri(Uri uri) {
        setSelectedContactUri(uri, true, false, true, false);
    }

    public void setQueryString(String str, boolean z) {
        this.mDelaySelection = z;
        super.setQueryString(str, z);
    }

    private void setSelectedContactUri(Uri uri, boolean z, boolean z2, boolean z3, boolean z4) {
        ContactListAdapter adapter;
        Uri uri2;
        this.mSmoothScrollRequested = z2;
        this.mSelectionToScreenRequested = true;
        if ((this.mSelectedContactUri == null && uri != null) || ((uri2 = this.mSelectedContactUri) != null && !uri2.equals(uri))) {
            this.mSelectionVerified = false;
            this.mSelectionRequired = z;
            this.mSelectionPersistenceRequested = z3;
            this.mSelectedContactUri = uri;
            parseSelectedContactUri();
            if (!z4 && (adapter = getAdapter()) != null) {
                adapter.setSelectedContact(this.mSelectedContactDirectoryId, this.mSelectedContactLookupKey, this.mSelectedContactId);
                getListView().invalidateViews();
            }
            refreshSelectedContactUri();
        }
    }

    private void parseSelectedContactUri() {
        long j;
        Uri uri = this.mSelectedContactUri;
        if (uri != null) {
            String queryParameter = uri.getQueryParameter("directory");
            if (TextUtils.isEmpty(queryParameter)) {
                j = 0;
            } else {
                j = Long.parseLong(queryParameter);
            }
            this.mSelectedContactDirectoryId = j;
            if (this.mSelectedContactUri.toString().startsWith(ContactsContract.Contacts.CONTENT_LOOKUP_URI.toString())) {
                List<String> pathSegments = this.mSelectedContactUri.getPathSegments();
                this.mSelectedContactLookupKey = Uri.encode(pathSegments.get(2));
                if (pathSegments.size() == 4) {
                    this.mSelectedContactId = ContentUris.parseId(this.mSelectedContactUri);
                }
            } else if (!this.mSelectedContactUri.toString().startsWith(ContactsContract.Contacts.CONTENT_URI.toString()) || this.mSelectedContactUri.getPathSegments().size() < 2) {
                Log.e("ContactList", "Unsupported contact URI: " + this.mSelectedContactUri);
                this.mSelectedContactLookupKey = null;
                this.mSelectedContactId = 0;
            } else {
                this.mSelectedContactLookupKey = null;
                this.mSelectedContactId = ContentUris.parseId(this.mSelectedContactUri);
            }
        } else {
            this.mSelectedContactDirectoryId = 0;
            this.mSelectedContactLookupKey = null;
            this.mSelectedContactId = 0;
        }
    }

    public ContactListAdapter getAdapter() {
        return (ContactListAdapter) super.getAdapter();
    }

    /* access modifiers changed from: protected */
    public void configureAdapter() {
        ContactListFilter contactListFilter;
        super.configureAdapter();
        ContactListAdapter adapter = getAdapter();
        if (adapter != null) {
            boolean isSearchMode = isSearchMode();
            if (!isSearchMode && (contactListFilter = this.mFilter) != null) {
                adapter.setFilter(contactListFilter);
                if (this.mSelectionRequired || this.mFilter.filterType == -6) {
                    adapter.setSelectedContact(this.mSelectedContactDirectoryId, this.mSelectedContactLookupKey, this.mSelectedContactId);
                }
            }
            adapter.setIncludeFavorites(!isSearchMode && this.mFilter.isContactsFilterType());
        }
    }

    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        super.onLoadFinished(loader, cursor);
        this.mSelectionVerified = false;
        refreshSelectedContactUri();
    }

    private void checkSelection() {
        ContactListAdapter adapter;
        boolean z;
        int i;
        if (!this.mSelectionVerified && !this.mRefreshingContactUri && !isLoadingDirectoryList() && (adapter = getAdapter()) != null) {
            int partitionCount = adapter.getPartitionCount();
            int i2 = 0;
            while (true) {
                if (i2 >= partitionCount) {
                    z = true;
                    break;
                }
                CompositeCursorAdapter.Partition partition = adapter.getPartition(i2);
                if (partition instanceof DirectoryPartition) {
                    DirectoryPartition directoryPartition = (DirectoryPartition) partition;
                    if (directoryPartition.getDirectoryId() == this.mSelectedContactDirectoryId) {
                        z = directoryPartition.isLoading();
                        break;
                    }
                }
                i2++;
            }
            if (!z) {
                adapter.setSelectedContact(this.mSelectedContactDirectoryId, this.mSelectedContactLookupKey, this.mSelectedContactId);
                int selectedContactPosition = adapter.getSelectedContactPosition();
                if (selectedContactPosition != -1) {
                    this.mLastSelectedPosition = selectedContactPosition;
                } else {
                    if (isSearchMode()) {
                        if (this.mDelaySelection) {
                            selectFirstFoundContactAfterDelay();
                            OnContactBrowserActionListener onContactBrowserActionListener = this.mListener;
                            if (onContactBrowserActionListener != null) {
                                onContactBrowserActionListener.onSelectionChange();
                                return;
                            }
                            return;
                        }
                    } else if (this.mSelectionRequired) {
                        this.mSelectionRequired = false;
                        ContactListFilter contactListFilter = this.mFilter;
                        if (contactListFilter == null || !((i = contactListFilter.filterType) == -6 || i == -2)) {
                            notifyInvalidSelection();
                            return;
                        } else {
                            reloadData();
                            return;
                        }
                    } else {
                        ContactListFilter contactListFilter2 = this.mFilter;
                        if (contactListFilter2 != null && contactListFilter2.filterType == -6) {
                            notifyInvalidSelection();
                            return;
                        }
                    }
                    saveSelectedUri((Uri) null);
                    selectDefaultContact();
                }
                this.mSelectionRequired = false;
                this.mSelectionVerified = true;
                if (this.mSelectionPersistenceRequested) {
                    saveSelectedUri(this.mSelectedContactUri);
                    this.mSelectionPersistenceRequested = false;
                }
                if (this.mSelectionToScreenRequested) {
                    requestSelectionToScreen(selectedContactPosition);
                }
                getListView().invalidateViews();
                OnContactBrowserActionListener onContactBrowserActionListener2 = this.mListener;
                if (onContactBrowserActionListener2 != null) {
                    onContactBrowserActionListener2.onSelectionChange();
                }
            }
        }
    }

    public void selectFirstFoundContactAfterDelay() {
        Handler handler = getHandler();
        handler.removeMessages(1);
        String queryString = getQueryString();
        if (queryString == null || queryString.length() < 2) {
            setSelectedContactUri((Uri) null, false, false, false, false);
        } else {
            handler.sendEmptyMessageDelayed(1, 500);
        }
    }

    /* access modifiers changed from: protected */
    public void selectDefaultContact() {
        Uri uri;
        ContactListAdapter adapter = getAdapter();
        if (this.mLastSelectedPosition != -1) {
            int count = adapter.getCount();
            int i = this.mLastSelectedPosition;
            if (i >= count && count > 0) {
                i = count - 1;
            }
            uri = adapter.getContactUri(i);
        } else {
            uri = null;
        }
        if (uri == null) {
            uri = adapter.getFirstContactUri();
        }
        setSelectedContactUri(uri, false, this.mSmoothScrollRequested, false, false);
    }

    /* access modifiers changed from: protected */
    public void requestSelectionToScreen(int i) {
        if (i != -1) {
            AutoScrollListView autoScrollListView = (AutoScrollListView) getListView();
            autoScrollListView.requestPositionToScreen(i + autoScrollListView.getHeaderViewsCount(), this.mSmoothScrollRequested);
            this.mSelectionToScreenRequested = false;
        }
    }

    public boolean isLoading() {
        return this.mRefreshingContactUri || super.isLoading();
    }

    /* access modifiers changed from: protected */
    public void startLoading() {
        this.mStartedLoading = true;
        this.mSelectionVerified = false;
        super.startLoading();
    }

    public void reloadData() {
        if (this.mStartedLoading) {
            this.mSelectionVerified = false;
            this.mLastSelectedPosition = -1;
            super.reloadData();
        }
    }

    public void setOnContactListActionListener(OnContactBrowserActionListener onContactBrowserActionListener) {
        this.mListener = onContactBrowserActionListener;
    }

    public void viewContact(int i, Uri uri, boolean z) {
        setSelectedContactUri(uri, false, false, true, false);
        OnContactBrowserActionListener onContactBrowserActionListener = this.mListener;
        if (onContactBrowserActionListener != null) {
            onContactBrowserActionListener.onViewContactAction(i, uri, z);
        }
    }

    private void notifyInvalidSelection() {
        OnContactBrowserActionListener onContactBrowserActionListener = this.mListener;
        if (onContactBrowserActionListener != null) {
            onContactBrowserActionListener.onInvalidSelection();
        }
    }

    private void saveSelectedUri(Uri uri) {
        if (!isSearchMode()) {
            ContactListFilter.storeToPreferences(this.mPrefs, this.mFilter);
            SharedPreferences.Editor edit = this.mPrefs.edit();
            if (uri == null) {
                edit.remove(getPersistentSelectionKey());
            } else {
                edit.putString(getPersistentSelectionKey(), uri.toString());
            }
            edit.apply();
        }
    }

    private void restoreSelectedUri(boolean z) {
        if (!this.mSelectionRequired) {
            String string = this.mPrefs.getString(getPersistentSelectionKey(), (String) null);
            if (string == null) {
                setSelectedContactUri((Uri) null, false, false, false, z);
                return;
            }
            setSelectedContactUri(Uri.parse(string), false, false, false, z);
        }
    }

    private void restoreFilter() {
        this.mFilter = ContactListFilter.restoreDefaultPreferences(this.mPrefs);
    }

    private String getPersistentSelectionKey() {
        if (this.mFilter == null) {
            return this.mPersistentSelectionPrefix;
        }
        return this.mPersistentSelectionPrefix + "-" + this.mFilter.getId();
    }
}
