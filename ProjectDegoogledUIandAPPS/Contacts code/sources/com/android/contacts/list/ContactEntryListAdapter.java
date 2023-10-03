package com.android.contacts.list;

import android.content.Context;
import android.content.CursorLoader;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.QuickContactBadge;
import android.widget.SectionIndexer;
import android.widget.TextView;
import com.android.common.widget.CompositeCursorAdapter;
import com.android.contacts.ContactPhotoManager;
import com.android.contacts.ContactsUtils;
import com.android.contacts.R;
import com.android.contacts.compat.CompatUtils;
import com.android.contacts.compat.DirectoryCompat;
import com.android.contacts.util.SearchUtil;
import java.util.HashSet;

public abstract class ContactEntryListAdapter extends IndexerListAdapter {
    private boolean mAdjustSelectionBoundsEnabled;
    private boolean mCircularPhotos = true;
    private boolean mDarkTheme = false;
    private CharSequence mDefaultFilterHeaderText;
    private int mDirectoryResultLimit = Integer.MAX_VALUE;
    private int mDirectorySearchMode;
    private int mDisplayOrder;
    private boolean mDisplayPhotos;
    private boolean mEmptyListEnabled = true;
    private ContactListFilter mFilter;
    private View mFragmentRootView;
    private boolean mIncludeFavorites;
    private int mNumberOfFavorites;
    private ContactPhotoManager mPhotoLoader;
    private String mQueryString;
    private boolean mQuickContactEnabled;
    private boolean mSearchMode;
    private boolean mSelectionVisible;
    private int mSortOrder;
    private String mUpperCaseQueryString;

    public abstract void configureLoader(CursorLoader cursorLoader, long j);

    /* access modifiers changed from: protected */
    public boolean getExtraStartingSection() {
        return false;
    }

    public boolean hasStableIds() {
        return true;
    }

    public ContactEntryListAdapter(Context context) {
        super(context);
        setDefaultFilterHeaderText(R.string.local_search_label);
        addPartitions();
    }

    /* access modifiers changed from: protected */
    public void setFragmentRootView(View view) {
        this.mFragmentRootView = view;
    }

    /* access modifiers changed from: protected */
    public void setDefaultFilterHeaderText(int i) {
        this.mDefaultFilterHeaderText = getContext().getResources().getText(i);
    }

    /* access modifiers changed from: protected */
    public ContactListItemView newView(Context context, int i, Cursor cursor, int i2, ViewGroup viewGroup) {
        ContactListItemView contactListItemView = new ContactListItemView(context, (AttributeSet) null);
        contactListItemView.setIsSectionHeaderEnabled(isSectionHeaderDisplayEnabled());
        contactListItemView.setAdjustSelectionBoundsEnabled(isAdjustSelectionBoundsEnabled());
        return contactListItemView;
    }

    /* access modifiers changed from: protected */
    public void bindView(View view, int i, Cursor cursor, int i2) {
        ContactListItemView contactListItemView = (ContactListItemView) view;
        contactListItemView.setIsSectionHeaderEnabled(isSectionHeaderDisplayEnabled());
        bindWorkProfileIcon(contactListItemView, i);
    }

    /* access modifiers changed from: protected */
    public View createPinnedSectionHeaderView(Context context, ViewGroup viewGroup) {
        return new ContactListPinnedHeaderView(context, (AttributeSet) null, viewGroup);
    }

    /* access modifiers changed from: protected */
    public void setPinnedSectionTitle(View view, String str) {
        ((ContactListPinnedHeaderView) view).setSectionHeaderTitle(str);
    }

    /* access modifiers changed from: protected */
    public void addPartitions() {
        addPartition(createDefaultDirectoryPartition());
    }

    /* access modifiers changed from: protected */
    public DirectoryPartition createDefaultDirectoryPartition() {
        DirectoryPartition directoryPartition = new DirectoryPartition(true, true);
        directoryPartition.setDirectoryId(0);
        directoryPartition.setDirectoryType(getContext().getString(R.string.contactsList));
        directoryPartition.setPriorityDirectory(true);
        directoryPartition.setPhotoSupported(true);
        directoryPartition.setLabel(this.mDefaultFilterHeaderText.toString());
        return directoryPartition;
    }

    public void removeDirectoriesAfterDefault() {
        int partitionCount = getPartitionCount() - 1;
        while (partitionCount >= 0) {
            CompositeCursorAdapter.Partition partition = getPartition(partitionCount);
            if (!(partition instanceof DirectoryPartition) || ((DirectoryPartition) partition).getDirectoryId() != 0) {
                removePartition(partitionCount);
                partitionCount--;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public int getPartitionByDirectoryId(long j) {
        int partitionCount = getPartitionCount();
        for (int i = 0; i < partitionCount; i++) {
            CompositeCursorAdapter.Partition partition = getPartition(i);
            if ((partition instanceof DirectoryPartition) && ((DirectoryPartition) partition).getDirectoryId() == j) {
                return i;
            }
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    public DirectoryPartition getDirectoryById(long j) {
        int partitionCount = getPartitionCount();
        for (int i = 0; i < partitionCount; i++) {
            CompositeCursorAdapter.Partition partition = getPartition(i);
            if (partition instanceof DirectoryPartition) {
                DirectoryPartition directoryPartition = (DirectoryPartition) partition;
                if (directoryPartition.getDirectoryId() == j) {
                    return directoryPartition;
                }
            }
        }
        return null;
    }

    public void onDataReload() {
        int partitionCount = getPartitionCount();
        boolean z = false;
        for (int i = 0; i < partitionCount; i++) {
            CompositeCursorAdapter.Partition partition = getPartition(i);
            if (partition instanceof DirectoryPartition) {
                DirectoryPartition directoryPartition = (DirectoryPartition) partition;
                if (!directoryPartition.isLoading()) {
                    z = true;
                }
                directoryPartition.setStatus(0);
            }
        }
        if (z) {
            notifyDataSetChanged();
        }
    }

    public void clearPartitions() {
        int partitionCount = getPartitionCount();
        for (int i = 0; i < partitionCount; i++) {
            CompositeCursorAdapter.Partition partition = getPartition(i);
            if (partition instanceof DirectoryPartition) {
                ((DirectoryPartition) partition).setStatus(0);
            }
        }
        super.clearPartitions();
    }

    public boolean isSearchMode() {
        return this.mSearchMode;
    }

    public void setSearchMode(boolean z) {
        this.mSearchMode = z;
    }

    public String getQueryString() {
        return this.mQueryString;
    }

    public void setQueryString(String str) {
        this.mQueryString = str;
        if (TextUtils.isEmpty(str)) {
            this.mUpperCaseQueryString = null;
        } else {
            this.mUpperCaseQueryString = SearchUtil.cleanStartAndEndOfSearchQuery(str.toUpperCase());
        }
    }

    public String getUpperCaseQueryString() {
        return this.mUpperCaseQueryString;
    }

    public int getDirectorySearchMode() {
        return this.mDirectorySearchMode;
    }

    public void setDirectorySearchMode(int i) {
        this.mDirectorySearchMode = i;
    }

    public int getDirectoryResultLimit(DirectoryPartition directoryPartition) {
        int resultLimit = directoryPartition.getResultLimit();
        return resultLimit == -1 ? this.mDirectoryResultLimit : resultLimit;
    }

    public void setDirectoryResultLimit(int i) {
        this.mDirectoryResultLimit = i;
    }

    public int getContactNameDisplayOrder() {
        return this.mDisplayOrder;
    }

    public void setContactNameDisplayOrder(int i) {
        this.mDisplayOrder = i;
    }

    public int getSortOrder() {
        return this.mSortOrder;
    }

    public void setSortOrder(int i) {
        this.mSortOrder = i;
    }

    public void setPhotoLoader(ContactPhotoManager contactPhotoManager) {
        this.mPhotoLoader = contactPhotoManager;
    }

    /* access modifiers changed from: protected */
    public ContactPhotoManager getPhotoLoader() {
        return this.mPhotoLoader;
    }

    public boolean getDisplayPhotos() {
        return this.mDisplayPhotos;
    }

    public void setDisplayPhotos(boolean z) {
        this.mDisplayPhotos = z;
    }

    public boolean getCircularPhotos() {
        return this.mCircularPhotos;
    }

    public boolean isSelectionVisible() {
        return this.mSelectionVisible;
    }

    public void setSelectionVisible(boolean z) {
        this.mSelectionVisible = z;
    }

    public boolean isQuickContactEnabled() {
        return this.mQuickContactEnabled;
    }

    public void setQuickContactEnabled(boolean z) {
        this.mQuickContactEnabled = z;
    }

    public boolean isAdjustSelectionBoundsEnabled() {
        return this.mAdjustSelectionBoundsEnabled;
    }

    public void setAdjustSelectionBoundsEnabled(boolean z) {
        this.mAdjustSelectionBoundsEnabled = z;
    }

    public boolean shouldIncludeFavorites() {
        return this.mIncludeFavorites;
    }

    public void setIncludeFavorites(boolean z) {
        this.mIncludeFavorites = z;
    }

    public void setFavoritesSectionHeader(int i) {
        if (this.mIncludeFavorites) {
            this.mNumberOfFavorites = i;
            setSectionHeader(i);
        }
    }

    public int getNumberOfFavorites() {
        return this.mNumberOfFavorites;
    }

    private void setSectionHeader(int i) {
        SectionIndexer indexer = getIndexer();
        if (indexer != null) {
            ((ContactsSectionIndexer) indexer).setFavoritesHeader(i);
        }
    }

    public void setDarkTheme(boolean z) {
        this.mDarkTheme = z;
    }

    public void changeDirectories(Cursor cursor) {
        if (cursor.getCount() == 0) {
            Log.e("ContactEntryListAdapter", "Directory search loader returned an empty cursor, which implies we have no directory entries.", new RuntimeException());
            return;
        }
        HashSet hashSet = new HashSet();
        int columnIndex = cursor.getColumnIndex("_id");
        int columnIndex2 = cursor.getColumnIndex("directoryType");
        int columnIndex3 = cursor.getColumnIndex("displayName");
        int columnIndex4 = cursor.getColumnIndex("photoSupport");
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            long j = cursor.getLong(columnIndex);
            hashSet.add(Long.valueOf(j));
            if (getPartitionByDirectoryId(j) == -1) {
                boolean z = false;
                DirectoryPartition directoryPartition = new DirectoryPartition(false, true);
                directoryPartition.setDirectoryId(j);
                if (DirectoryCompat.isRemoteDirectoryId(j)) {
                    if (DirectoryCompat.isEnterpriseDirectoryId(j)) {
                        directoryPartition.setLabel(this.mContext.getString(R.string.directory_search_label_work));
                    } else {
                        directoryPartition.setLabel(this.mContext.getString(R.string.directory_search_label));
                    }
                } else if (DirectoryCompat.isEnterpriseDirectoryId(j)) {
                    directoryPartition.setLabel(this.mContext.getString(R.string.list_filter_phones_work));
                } else {
                    directoryPartition.setLabel(this.mDefaultFilterHeaderText.toString());
                }
                directoryPartition.setDirectoryType(cursor.getString(columnIndex2));
                directoryPartition.setDisplayName(cursor.getString(columnIndex3));
                int i = cursor.getInt(columnIndex4);
                if (i == 1 || i == 3) {
                    z = true;
                }
                directoryPartition.setPhotoSupported(z);
                addPartition(directoryPartition);
            }
        }
        int partitionCount = getPartitionCount();
        while (true) {
            partitionCount--;
            if (partitionCount >= 0) {
                CompositeCursorAdapter.Partition partition = getPartition(partitionCount);
                if ((partition instanceof DirectoryPartition) && !hashSet.contains(Long.valueOf(((DirectoryPartition) partition).getDirectoryId()))) {
                    removePartition(partitionCount);
                }
            } else {
                invalidate();
                notifyDataSetChanged();
                return;
            }
        }
    }

    public void changeCursor(int i, Cursor cursor) {
        if (i < getPartitionCount()) {
            CompositeCursorAdapter.Partition partition = getPartition(i);
            if (partition instanceof DirectoryPartition) {
                ((DirectoryPartition) partition).setStatus(2);
            }
            if (this.mDisplayPhotos && this.mPhotoLoader != null && isPhotoSupported(i)) {
                this.mPhotoLoader.refreshCache();
            }
            super.changeCursor(i, cursor);
            if (isSectionHeaderDisplayEnabled() && i == getIndexedPartition()) {
                updateIndexer(cursor);
            }
            this.mPhotoLoader.cancelPendingRequests(this.mFragmentRootView);
        }
    }

    private void updateIndexer(Cursor cursor) {
        if (cursor == null || cursor.isClosed()) {
            setIndexer((SectionIndexer) null);
            return;
        }
        Bundle extras = cursor.getExtras();
        if (!extras.containsKey("android.provider.extra.ADDRESS_BOOK_INDEX_TITLES") || !extras.containsKey("android.provider.extra.ADDRESS_BOOK_INDEX_COUNTS")) {
            setIndexer((SectionIndexer) null);
            return;
        }
        String[] stringArray = extras.getStringArray("android.provider.extra.ADDRESS_BOOK_INDEX_TITLES");
        int[] intArray = extras.getIntArray("android.provider.extra.ADDRESS_BOOK_INDEX_COUNTS");
        if (getExtraStartingSection()) {
            String[] strArr = new String[(stringArray.length + 1)];
            int[] iArr = new int[(intArray.length + 1)];
            int i = 0;
            while (i < stringArray.length) {
                int i2 = i + 1;
                strArr[i2] = stringArray[i];
                iArr[i2] = intArray[i];
                i = i2;
            }
            iArr[0] = 1;
            strArr[0] = "";
            setIndexer(new ContactsSectionIndexer(strArr, iArr));
            return;
        }
        setIndexer(new ContactsSectionIndexer(stringArray, intArray));
    }

    public int getViewTypeCount() {
        return (getItemViewTypeCount() * 2) + 1;
    }

    public int getItemViewType(int i, int i2) {
        int itemViewType = super.getItemViewType(i, i2);
        return (!isSectionHeaderDisplayEnabled() || i != getIndexedPartition() || getItemPlacementInSection(i2).firstInSection) ? itemViewType : itemViewType + getItemViewTypeCount();
    }

    public boolean isEmpty() {
        if (!this.mEmptyListEnabled) {
            return false;
        }
        if (isSearchMode()) {
            return TextUtils.isEmpty(getQueryString());
        }
        return super.isEmpty();
    }

    public boolean isLoading() {
        int partitionCount = getPartitionCount();
        for (int i = 0; i < partitionCount; i++) {
            CompositeCursorAdapter.Partition partition = getPartition(i);
            if ((partition instanceof DirectoryPartition) && ((DirectoryPartition) partition).isLoading()) {
                return true;
            }
        }
        return false;
    }

    public boolean areAllPartitionsEmpty() {
        int partitionCount = getPartitionCount();
        for (int i = 0; i < partitionCount; i++) {
            if (!isPartitionEmpty(i)) {
                return false;
            }
        }
        return true;
    }

    public void configureDefaultPartition(boolean z, boolean z2) {
        int partitionCount = getPartitionCount();
        int i = 0;
        while (true) {
            if (i >= partitionCount) {
                i = -1;
                break;
            }
            CompositeCursorAdapter.Partition partition = getPartition(i);
            if ((partition instanceof DirectoryPartition) && ((DirectoryPartition) partition).getDirectoryId() == 0) {
                break;
            }
            i++;
        }
        if (i != -1) {
            setShowIfEmpty(i, z);
            setHasHeader(i, z2);
        }
    }

    /* access modifiers changed from: protected */
    public View newHeaderView(Context context, int i, Cursor cursor, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.directory_header, viewGroup, false);
        if (!getPinnedPartitionHeadersEnabled()) {
            inflate.setBackground((Drawable) null);
        }
        return inflate;
    }

    /* access modifiers changed from: protected */
    public void bindWorkProfileIcon(ContactListItemView contactListItemView, int i) {
        CompositeCursorAdapter.Partition partition = getPartition(i);
        if (partition instanceof DirectoryPartition) {
            contactListItemView.setWorkProfileIconEnabled(ContactsUtils.determineUserType(Long.valueOf(((DirectoryPartition) partition).getDirectoryId()), (Long) null) == 1);
        }
    }

    /* access modifiers changed from: protected */
    public void bindHeaderView(View view, int i, Cursor cursor) {
        CompositeCursorAdapter.Partition partition = getPartition(i);
        if (partition instanceof DirectoryPartition) {
            DirectoryPartition directoryPartition = (DirectoryPartition) partition;
            long directoryId = directoryPartition.getDirectoryId();
            TextView textView = (TextView) view.findViewById(R.id.display_name);
            ((TextView) view.findViewById(R.id.label)).setText(directoryPartition.getLabel());
            if (!DirectoryCompat.isRemoteDirectoryId(directoryId)) {
                textView.setText((CharSequence) null);
            } else {
                String displayName = directoryPartition.getDisplayName();
                if (TextUtils.isEmpty(displayName)) {
                    displayName = directoryPartition.getDirectoryType();
                }
                textView.setText(displayName);
            }
            Resources resources = getContext().getResources();
            int i2 = 0;
            if (i != 1 || !getPartition(0).isEmpty()) {
                i2 = resources.getDimensionPixelOffset(R.dimen.directory_header_extra_top_padding);
            }
            view.setPaddingRelative(view.getPaddingStart(), i2, view.getPaddingEnd(), view.getPaddingBottom());
        }
    }

    public boolean isPhotoSupported(int i) {
        CompositeCursorAdapter.Partition partition = getPartition(i);
        if (partition instanceof DirectoryPartition) {
            return ((DirectoryPartition) partition).isPhotoSupported();
        }
        return true;
    }

    public ContactListFilter getFilter() {
        return this.mFilter;
    }

    public void setFilter(ContactListFilter contactListFilter) {
        this.mFilter = contactListFilter;
    }

    /* access modifiers changed from: protected */
    public void bindQuickContact(ContactListItemView contactListItemView, int i, Cursor cursor, int i2, int i3, int i4, int i5, int i6) {
        Uri uri;
        long j = !cursor.isNull(i2) ? cursor.getLong(i2) : 0;
        QuickContactBadge quickContact = contactListItemView.getQuickContact();
        quickContact.assignContactUri(getContactUri(i, cursor, i4, i5));
        if (CompatUtils.hasPrioritizedMimeType()) {
            quickContact.setPrioritizedMimeType("vnd.android.cursor.item/phone_v2");
        }
        if (j != 0 || i3 == -1) {
            getPhotoLoader().loadThumbnail(quickContact, j, this.mDarkTheme, this.mCircularPhotos, (ContactPhotoManager.DefaultImageRequest) null);
            return;
        }
        String string = cursor.getString(i3);
        if (string == null) {
            uri = null;
        } else {
            uri = Uri.parse(string);
        }
        getPhotoLoader().loadPhoto(quickContact, uri, -1, this.mDarkTheme, this.mCircularPhotos, uri == null ? getDefaultImageRequestFromCursor(cursor, i6, i5) : null);
    }

    /* access modifiers changed from: protected */
    public void bindViewId(ContactListItemView contactListItemView, Cursor cursor, int i) {
        contactListItemView.setId((int) (cursor.getLong(i) % 2147483647L));
    }

    /* access modifiers changed from: protected */
    public Uri getContactUri(int i, Cursor cursor, int i2, int i3) {
        long j = cursor.getLong(i2);
        String string = cursor.getString(i3);
        long directoryId = ((DirectoryPartition) getPartition(i)).getDirectoryId();
        Uri lookupUri = ContactsContract.Contacts.getLookupUri(j, string);
        return (lookupUri == null || directoryId == 0) ? lookupUri : lookupUri.buildUpon().appendQueryParameter("directory", String.valueOf(directoryId)).build();
    }

    public ContactPhotoManager.DefaultImageRequest getDefaultImageRequestFromCursor(Cursor cursor, int i, int i2) {
        return new ContactPhotoManager.DefaultImageRequest(cursor.getString(i), cursor.getString(i2), this.mCircularPhotos);
    }
}
