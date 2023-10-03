package com.android.contacts.list;

import android.app.Activity;
import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.android.common.widget.CompositeCursorAdapter;
import com.android.contacts.ContactPhotoManager;
import com.android.contacts.list.ContactEntryListAdapter;
import com.android.contacts.logging.Logger;
import com.android.contacts.preference.ContactsPreferences;
import java.util.Locale;

public abstract class ContactEntryListFragment<T extends ContactEntryListAdapter> extends Fragment implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener, View.OnFocusChangeListener, View.OnTouchListener, AdapterView.OnItemLongClickListener, LoaderManager.LoaderCallbacks<Cursor> {
    private T mAdapter;
    private boolean mAdjustSelectionBoundsEnabled = true;
    private ContactsPreferences mContactsPrefs;
    private Context mContext;
    private boolean mDarkTheme;
    private boolean mDataLoaded;
    private Handler mDelayedDirectorySearchHandler = new Handler() {
        public void handleMessage(Message message) {
            if (message.what == 1) {
                ContactEntryListFragment.this.loadDirectoryPartition(message.arg1, (DirectoryPartition) message.obj);
            }
        }
    };
    private int mDirectoryListStatus = 0;
    private int mDirectoryResultLimit = 20;
    private int mDirectorySearchMode = 0;
    private boolean mDisplayDirectoryHeader = true;
    private int mDisplayOrder;
    private boolean mEnabled = true;
    private boolean mForceLoad;
    private boolean mIncludeFavorites;
    private boolean mLegacyCompatibility;
    private Parcelable mListState;
    private int mListType;
    private ListView mListView;
    private int mListViewTopIndex;
    private int mListViewTopOffset;
    private boolean mLoadPriorityDirectoriesOnly;
    private LoaderManager mLoaderManager;
    private boolean mLogListEvents = true;
    private boolean mPhotoLoaderEnabled;
    private ContactPhotoManager mPhotoManager;
    private ContactsPreferences.ChangeListener mPreferencesChangeListener = new ContactsPreferences.ChangeListener() {
        public void onChange() {
            ContactEntryListFragment.this.loadPreferences();
            ContactEntryListFragment.this.reloadData();
        }
    };
    private String mQueryString;
    private boolean mQuickContactEnabled = true;
    private boolean mSearchMode;
    private boolean mSectionHeaderDisplayEnabled;
    private boolean mSelectionVisible;
    private boolean mShowEmptyListForEmptyQuery;
    private int mSortOrder;
    private int mVerticalScrollbarPosition = getDefaultVerticalScrollbarPosition();
    protected View mView;
    private boolean mVisibleScrollbarEnabled;

    /* access modifiers changed from: protected */
    public abstract T createListAdapter();

    /* access modifiers changed from: protected */
    public abstract View inflateView(LayoutInflater layoutInflater, ViewGroup viewGroup);

    /* access modifiers changed from: protected */
    public abstract void onItemClick(int i, long j);

    /* access modifiers changed from: protected */
    public boolean onItemLongClick(int i, long j) {
        return false;
    }

    public void onLoaderReset(Loader<Cursor> loader) {
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
    }

    /* access modifiers changed from: protected */
    public void setListHeader() {
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        setContext(activity);
        setLoaderManager(super.getLoaderManager());
    }

    public void setContext(Context context) {
        this.mContext = context;
        configurePhotoLoader();
    }

    public Context getContext() {
        return this.mContext;
    }

    public void setEnabled(boolean z) {
        if (this.mEnabled != z) {
            this.mEnabled = z;
            T t = this.mAdapter;
            if (t == null) {
                return;
            }
            if (this.mEnabled) {
                reloadData();
            } else {
                t.clearPartitions();
            }
        }
    }

    public void setLoaderManager(LoaderManager loaderManager) {
        this.mLoaderManager = loaderManager;
    }

    public LoaderManager getLoaderManager() {
        return this.mLoaderManager;
    }

    public T getAdapter() {
        return this.mAdapter;
    }

    public View getView() {
        return this.mView;
    }

    public ListView getListView() {
        return this.mListView;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("sectionHeaderDisplayEnabled", this.mSectionHeaderDisplayEnabled);
        bundle.putBoolean("photoLoaderEnabled", this.mPhotoLoaderEnabled);
        bundle.putBoolean("quickContactEnabled", this.mQuickContactEnabled);
        bundle.putBoolean("adjustSelectionBoundsEnabled", this.mAdjustSelectionBoundsEnabled);
        bundle.putBoolean("searchMode", this.mSearchMode);
        bundle.putBoolean("displayDirectoryHeader", this.mDisplayDirectoryHeader);
        bundle.putBoolean("visibleScrollbarEnabled", this.mVisibleScrollbarEnabled);
        bundle.putInt("scrollbarPosition", this.mVerticalScrollbarPosition);
        bundle.putInt("directorySearchMode", this.mDirectorySearchMode);
        bundle.putBoolean("selectionVisible", this.mSelectionVisible);
        bundle.putBoolean("legacyCompatibility", this.mLegacyCompatibility);
        bundle.putString("queryString", this.mQueryString);
        bundle.putInt("directoryResultLimit", this.mDirectoryResultLimit);
        bundle.putBoolean("darkTheme", this.mDarkTheme);
        bundle.putBoolean("logsListEvents", this.mLogListEvents);
        bundle.putBoolean("dataLoaded", this.mDataLoaded);
        ListView listView = this.mListView;
        if (listView != null) {
            bundle.putParcelable("liststate", listView.onSaveInstanceState());
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        restoreSavedState(bundle);
        this.mAdapter = createListAdapter();
        this.mContactsPrefs = new ContactsPreferences(this.mContext);
    }

    public void restoreSavedState(Bundle bundle) {
        if (bundle != null) {
            this.mSectionHeaderDisplayEnabled = bundle.getBoolean("sectionHeaderDisplayEnabled");
            this.mPhotoLoaderEnabled = bundle.getBoolean("photoLoaderEnabled");
            this.mQuickContactEnabled = bundle.getBoolean("quickContactEnabled");
            this.mAdjustSelectionBoundsEnabled = bundle.getBoolean("adjustSelectionBoundsEnabled");
            this.mSearchMode = bundle.getBoolean("searchMode");
            this.mDisplayDirectoryHeader = bundle.getBoolean("displayDirectoryHeader");
            this.mVisibleScrollbarEnabled = bundle.getBoolean("visibleScrollbarEnabled");
            this.mVerticalScrollbarPosition = bundle.getInt("scrollbarPosition");
            this.mDirectorySearchMode = bundle.getInt("directorySearchMode");
            this.mSelectionVisible = bundle.getBoolean("selectionVisible");
            this.mLegacyCompatibility = bundle.getBoolean("legacyCompatibility");
            this.mQueryString = bundle.getString("queryString");
            this.mDirectoryResultLimit = bundle.getInt("directoryResultLimit");
            this.mDarkTheme = bundle.getBoolean("darkTheme");
            this.mListState = bundle.getParcelable("liststate");
        }
    }

    public void onStart() {
        super.onStart();
        this.mContactsPrefs.registerChangeListener(this.mPreferencesChangeListener);
        this.mForceLoad = loadPreferences();
        this.mDirectoryListStatus = 0;
        this.mLoadPriorityDirectoriesOnly = true;
        startLoading();
    }

    /* access modifiers changed from: protected */
    public void startLoading() {
        if (this.mAdapter != null) {
            configureAdapter();
            int partitionCount = this.mAdapter.getPartitionCount();
            for (int i = 0; i < partitionCount; i++) {
                CompositeCursorAdapter.Partition partition = this.mAdapter.getPartition(i);
                if (partition instanceof DirectoryPartition) {
                    DirectoryPartition directoryPartition = (DirectoryPartition) partition;
                    if (directoryPartition.getStatus() == 0 && (directoryPartition.isPriorityDirectory() || !this.mLoadPriorityDirectoriesOnly)) {
                        startLoadingDirectoryPartition(i);
                    }
                } else {
                    getLoaderManager().initLoader(i, (Bundle) null, this);
                }
            }
            this.mLoadPriorityDirectoriesOnly = false;
        }
    }

    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        if (i == -1) {
            DirectoryListLoader directoryListLoader = new DirectoryListLoader(this.mContext);
            directoryListLoader.setDirectorySearchMode(this.mAdapter.getDirectorySearchMode());
            directoryListLoader.setLocalInvisibleDirectoryEnabled(false);
            return directoryListLoader;
        }
        CursorLoader createCursorLoader = createCursorLoader(this.mContext);
        this.mAdapter.configureLoader(createCursorLoader, (bundle == null || !bundle.containsKey("directoryId")) ? 0 : bundle.getLong("directoryId"));
        return createCursorLoader;
    }

    public CursorLoader createCursorLoader(Context context) {
        return new CursorLoader(context, (Uri) null, (String[]) null, (String) null, (String[]) null, (String) null) {
            /* access modifiers changed from: protected */
            public Cursor onLoadInBackground() {
                try {
                    return (Cursor) super.onLoadInBackground();
                } catch (RuntimeException unused) {
                    Log.w("ContactEntryList", "RuntimeException while trying to query ContactsProvider.");
                    return null;
                }
            }
        };
    }

    private void startLoadingDirectoryPartition(int i) {
        DirectoryPartition directoryPartition = (DirectoryPartition) this.mAdapter.getPartition(i);
        directoryPartition.setStatus(1);
        long directoryId = directoryPartition.getDirectoryId();
        if (!this.mForceLoad) {
            Bundle bundle = new Bundle();
            bundle.putLong("directoryId", directoryId);
            getLoaderManager().initLoader(i, bundle, this);
        } else if (directoryId == 0) {
            loadDirectoryPartition(i, directoryPartition);
        } else {
            loadDirectoryPartitionDelayed(i, directoryPartition);
        }
    }

    private void loadDirectoryPartitionDelayed(int i, DirectoryPartition directoryPartition) {
        this.mDelayedDirectorySearchHandler.removeMessages(1, directoryPartition);
        this.mDelayedDirectorySearchHandler.sendMessageDelayed(this.mDelayedDirectorySearchHandler.obtainMessage(1, i, 0, directoryPartition), 300);
    }

    /* access modifiers changed from: protected */
    public void loadDirectoryPartition(int i, DirectoryPartition directoryPartition) {
        Bundle bundle = new Bundle();
        bundle.putLong("directoryId", directoryPartition.getDirectoryId());
        getLoaderManager().restartLoader(i, bundle, this);
    }

    private void removePendingDirectorySearchRequests() {
        this.mDelayedDirectorySearchHandler.removeMessages(1);
    }

    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (this.mEnabled) {
            getListView().setVisibility(0);
            getView().setVisibility(0);
            int id = loader.getId();
            if (id == -1) {
                this.mDirectoryListStatus = 2;
                this.mAdapter.changeDirectories(cursor);
                startLoading();
                return;
            }
            onPartitionLoaded(id, cursor);
            if (!isSearchMode()) {
                maybeLogListEvent();
                this.mDirectoryListStatus = 0;
                getLoaderManager().destroyLoader(-1);
            } else if (getDirectorySearchMode() == 0) {
            } else {
                if (this.mDirectoryListStatus == 0) {
                    this.mDirectoryListStatus = 1;
                    getLoaderManager().initLoader(-1, (Bundle) null, this);
                    return;
                }
                startLoading();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void maybeLogListEvent() {
        if (!this.mDataLoaded || this.mLogListEvents) {
            Logger.logListEvent(1, getListType(), getAdapter().getCount(), -1, 0);
            this.mLogListEvents = false;
            this.mDataLoaded = true;
        }
    }

    /* access modifiers changed from: protected */
    public void onPartitionLoaded(int i, Cursor cursor) {
        if (i < this.mAdapter.getPartitionCount()) {
            this.mAdapter.changeCursor(i, cursor);
            setListHeader();
            if (!isLoading()) {
                completeRestoreInstanceState();
            }
        }
    }

    public boolean isLoading() {
        T t = this.mAdapter;
        if ((t == null || !t.isLoading()) && !isLoadingDirectoryList()) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000d, code lost:
        r0 = r2.mDirectoryListStatus;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isLoadingDirectoryList() {
        /*
            r2 = this;
            boolean r0 = r2.isSearchMode()
            r1 = 1
            if (r0 == 0) goto L_0x0014
            int r0 = r2.getDirectorySearchMode()
            if (r0 == 0) goto L_0x0014
            int r0 = r2.mDirectoryListStatus
            if (r0 == 0) goto L_0x0015
            if (r0 != r1) goto L_0x0014
            goto L_0x0015
        L_0x0014:
            r1 = 0
        L_0x0015:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.list.ContactEntryListFragment.isLoadingDirectoryList():boolean");
    }

    public void onStop() {
        super.onStop();
        this.mContactsPrefs.unregisterChangeListener();
        this.mAdapter.clearPartitions();
    }

    /* access modifiers changed from: protected */
    public void reloadData() {
        removePendingDirectorySearchRequests();
        this.mAdapter.onDataReload();
        this.mLoadPriorityDirectoriesOnly = true;
        this.mForceLoad = true;
        startLoading();
    }

    public void setSectionHeaderDisplayEnabled(boolean z) {
        if (this.mSectionHeaderDisplayEnabled != z) {
            this.mSectionHeaderDisplayEnabled = z;
            T t = this.mAdapter;
            if (t != null) {
                t.setSectionHeaderDisplayEnabled(z);
            }
            configureVerticalScrollbar();
        }
    }

    public boolean isSectionHeaderDisplayEnabled() {
        return this.mSectionHeaderDisplayEnabled;
    }

    public void setVisibleScrollbarEnabled(boolean z) {
        if (this.mVisibleScrollbarEnabled != z) {
            this.mVisibleScrollbarEnabled = z;
            configureVerticalScrollbar();
        }
    }

    public boolean isVisibleScrollbarEnabled() {
        return this.mVisibleScrollbarEnabled;
    }

    public void setVerticalScrollbarPosition(int i) {
        if (this.mVerticalScrollbarPosition != i) {
            this.mVerticalScrollbarPosition = i;
            configureVerticalScrollbar();
        }
    }

    private void configureVerticalScrollbar() {
        boolean z = isVisibleScrollbarEnabled() && isSectionHeaderDisplayEnabled();
        ListView listView = this.mListView;
        if (listView != null) {
            listView.setFastScrollEnabled(z);
            this.mListView.setVerticalScrollbarPosition(this.mVerticalScrollbarPosition);
            this.mListView.setScrollBarStyle(33554432);
        }
    }

    public void setPhotoLoaderEnabled(boolean z) {
        this.mPhotoLoaderEnabled = z;
        configurePhotoLoader();
    }

    public boolean isPhotoLoaderEnabled() {
        return this.mPhotoLoaderEnabled;
    }

    public boolean isSelectionVisible() {
        return this.mSelectionVisible;
    }

    public void setSelectionVisible(boolean z) {
        this.mSelectionVisible = z;
    }

    public void setQuickContactEnabled(boolean z) {
        this.mQuickContactEnabled = z;
    }

    public void setIncludeFavorites(boolean z) {
        this.mIncludeFavorites = z;
        T t = this.mAdapter;
        if (t != null) {
            t.setIncludeFavorites(z);
        }
    }

    public void setDisplayDirectoryHeader(boolean z) {
        this.mDisplayDirectoryHeader = z;
    }

    /* access modifiers changed from: protected */
    public void setSearchMode(boolean z) {
        if (this.mSearchMode != z) {
            this.mSearchMode = z;
            setSectionHeaderDisplayEnabled(!this.mSearchMode);
            if (!z) {
                this.mDirectoryListStatus = 0;
                getLoaderManager().destroyLoader(-1);
            }
            T t = this.mAdapter;
            if (t != null) {
                t.setSearchMode(z);
                this.mAdapter.clearPartitions();
                if (!z) {
                    this.mAdapter.removeDirectoriesAfterDefault();
                }
                this.mAdapter.configureDefaultPartition(false, shouldDisplayDirectoryHeader());
            }
            ListView listView = this.mListView;
            if (listView != null) {
                listView.setFastScrollEnabled(!z);
            }
        }
    }

    private boolean shouldDisplayDirectoryHeader() {
        if (!this.mSearchMode) {
            return false;
        }
        return this.mDisplayDirectoryHeader;
    }

    public final boolean isSearchMode() {
        return this.mSearchMode;
    }

    public final String getQueryString() {
        return this.mQueryString;
    }

    public void setQueryString(String str, boolean z) {
        if (!TextUtils.equals(this.mQueryString, str)) {
            if (!(!this.mShowEmptyListForEmptyQuery || this.mAdapter == null || this.mListView == null)) {
                if (TextUtils.isEmpty(this.mQueryString)) {
                    this.mListView.setAdapter(this.mAdapter);
                } else if (TextUtils.isEmpty(str)) {
                    this.mListView.setAdapter((ListAdapter) null);
                }
            }
            this.mQueryString = str;
            setSearchMode(!TextUtils.isEmpty(this.mQueryString) || this.mShowEmptyListForEmptyQuery);
            T t = this.mAdapter;
            if (t != null) {
                t.setQueryString(str);
                reloadData();
            }
        }
    }

    public int getDirectorySearchMode() {
        return this.mDirectorySearchMode;
    }

    public void setDirectorySearchMode(int i) {
        this.mDirectorySearchMode = i;
    }

    public boolean isLegacyCompatibilityMode() {
        return this.mLegacyCompatibility;
    }

    public void setLegacyCompatibilityMode(boolean z) {
        this.mLegacyCompatibility = z;
    }

    /* access modifiers changed from: protected */
    public int getContactNameDisplayOrder() {
        return this.mDisplayOrder;
    }

    /* access modifiers changed from: protected */
    public void setContactNameDisplayOrder(int i) {
        this.mDisplayOrder = i;
        T t = this.mAdapter;
        if (t != null) {
            t.setContactNameDisplayOrder(i);
        }
    }

    public int getSortOrder() {
        return this.mSortOrder;
    }

    public void setSortOrder(int i) {
        this.mSortOrder = i;
        T t = this.mAdapter;
        if (t != null) {
            t.setSortOrder(i);
        }
    }

    public void setDirectoryResultLimit(int i) {
        this.mDirectoryResultLimit = i;
    }

    /* access modifiers changed from: protected */
    public boolean loadPreferences() {
        boolean z;
        if (getContactNameDisplayOrder() != this.mContactsPrefs.getDisplayOrder()) {
            setContactNameDisplayOrder(this.mContactsPrefs.getDisplayOrder());
            z = true;
        } else {
            z = false;
        }
        if (getSortOrder() == this.mContactsPrefs.getSortOrder()) {
            return z;
        }
        setSortOrder(this.mContactsPrefs.getSortOrder());
        return true;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        onCreateView(layoutInflater, viewGroup);
        this.mAdapter.setSearchMode(isSearchMode());
        this.mAdapter.configureDefaultPartition(false, shouldDisplayDirectoryHeader());
        this.mAdapter.setPhotoLoader(this.mPhotoManager);
        this.mListView.setAdapter(this.mAdapter);
        if (!isSearchMode()) {
            this.mListView.setFocusableInTouchMode(true);
            this.mListView.requestFocus();
        }
        if (bundle != null) {
            this.mLogListEvents = bundle.getBoolean("logsListEvents", true);
            this.mDataLoaded = bundle.getBoolean("dataLoaded", false);
        }
        return this.mView;
    }

    /* access modifiers changed from: protected */
    public void onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        this.mView = inflateView(layoutInflater, viewGroup);
        this.mListView = (ListView) this.mView.findViewById(16908298);
        if (this.mListView != null) {
            View findViewById = this.mView.findViewById(16908292);
            if (findViewById != null) {
                this.mListView.setEmptyView(findViewById);
            }
            this.mListView.setOnItemClickListener(this);
            this.mListView.setOnItemLongClickListener(this);
            this.mListView.setOnFocusChangeListener(this);
            this.mListView.setOnTouchListener(this);
            this.mListView.setFastScrollEnabled(!isSearchMode());
            this.mListView.setDividerHeight(0);
            this.mListView.setSaveEnabled(false);
            configureVerticalScrollbar();
            configurePhotoLoader();
            getAdapter().setFragmentRootView(getView());
            return;
        }
        throw new RuntimeException("Your content must have a ListView whose id attribute is 'android.R.id.list'");
    }

    /* access modifiers changed from: protected */
    public void configurePhotoLoader() {
        Context context;
        if (isPhotoLoaderEnabled() && (context = this.mContext) != null) {
            if (this.mPhotoManager == null) {
                this.mPhotoManager = ContactPhotoManager.getInstance(context);
            }
            ListView listView = this.mListView;
            if (listView != null) {
                listView.setOnScrollListener(this);
            }
            T t = this.mAdapter;
            if (t != null) {
                t.setPhotoLoader(this.mPhotoManager);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void configureAdapter() {
        T t = this.mAdapter;
        if (t != null) {
            t.setQuickContactEnabled(this.mQuickContactEnabled);
            this.mAdapter.setAdjustSelectionBoundsEnabled(this.mAdjustSelectionBoundsEnabled);
            this.mAdapter.setIncludeFavorites(this.mIncludeFavorites);
            this.mAdapter.setQueryString(this.mQueryString);
            this.mAdapter.setDirectorySearchMode(this.mDirectorySearchMode);
            this.mAdapter.setPinnedPartitionHeadersEnabled(false);
            this.mAdapter.setContactNameDisplayOrder(this.mDisplayOrder);
            this.mAdapter.setSortOrder(this.mSortOrder);
            this.mAdapter.setSectionHeaderDisplayEnabled(this.mSectionHeaderDisplayEnabled);
            this.mAdapter.setSelectionVisible(this.mSelectionVisible);
            this.mAdapter.setDirectoryResultLimit(this.mDirectoryResultLimit);
            this.mAdapter.setDarkTheme(this.mDarkTheme);
        }
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (i == 2) {
            this.mPhotoManager.pause();
        } else if (isPhotoLoaderEnabled()) {
            this.mPhotoManager.resume();
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        hideSoftKeyboard();
        int headerViewsCount = i - this.mListView.getHeaderViewsCount();
        if (headerViewsCount >= 0) {
            onItemClick(headerViewsCount, j);
        }
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        int headerViewsCount = i - this.mListView.getHeaderViewsCount();
        if (headerViewsCount >= 0) {
            return onItemLongClick(headerViewsCount, j);
        }
        return false;
    }

    private void hideSoftKeyboard() {
        ((InputMethodManager) this.mContext.getSystemService("input_method")).hideSoftInputFromWindow(this.mListView.getWindowToken(), 0);
    }

    public void onFocusChange(View view, boolean z) {
        if (view == this.mListView && z) {
            hideSoftKeyboard();
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (view != this.mListView) {
            return false;
        }
        hideSoftKeyboard();
        return false;
    }

    public void onPause() {
        this.mListViewTopIndex = this.mListView.getFirstVisiblePosition();
        int i = 0;
        View childAt = this.mListView.getChildAt(0);
        if (childAt != null) {
            i = childAt.getTop() - this.mListView.getPaddingTop();
        }
        this.mListViewTopOffset = i;
        super.onPause();
        removePendingDirectorySearchRequests();
    }

    public void onResume() {
        super.onResume();
        this.mListView.setSelectionFromTop(this.mListViewTopIndex, this.mListViewTopOffset);
    }

    /* access modifiers changed from: protected */
    public void completeRestoreInstanceState() {
        Parcelable parcelable = this.mListState;
        if (parcelable != null) {
            this.mListView.onRestoreInstanceState(parcelable);
            this.mListState = null;
        }
    }

    public void onPickerResult(Intent intent) {
        throw new UnsupportedOperationException("Picker result handler is not implemented.");
    }

    private int getDefaultVerticalScrollbarPosition() {
        return TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) != 1 ? 2 : 1;
    }

    public void setListType(int i) {
        this.mListType = i;
    }

    public int getListType() {
        return this.mListType;
    }

    public void setLogListEvents(boolean z) {
        this.mLogListEvents = z;
    }
}
