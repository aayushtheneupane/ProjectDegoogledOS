package com.android.contacts.list;

import android.content.ContentUris;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.android.common.widget.CompositeCursorAdapter;
import com.android.contacts.CallUtil;
import com.android.contacts.ContactPhotoManager;
import com.android.contacts.ContactsUtils;
import com.android.contacts.GeoUtil;
import com.android.contacts.R;
import com.android.contacts.compat.CallableCompat;
import com.android.contacts.compat.CompatUtils;
import com.android.contacts.compat.DirectoryCompat;
import com.android.contacts.compat.PhoneCompat;
import com.android.contacts.extensions.ExtendedPhoneDirectoriesManager;
import com.android.contacts.extensions.ExtensionsFactory;
import com.android.contacts.list.ContactListItemView;
import com.android.contacts.list.IndexerListAdapter;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;

public class PhoneNumberListAdapter extends ContactEntryListAdapter {
    private static final String TAG = "PhoneNumberListAdapter";
    private final String mCountryIso;
    private final List<DirectoryPartition> mExtendedDirectories;
    private long mFirstExtendedDirectoryId = Long.MAX_VALUE;
    private boolean mIsPresenceEnabled;
    private boolean mIsVideoEnabled;
    private Listener mListener;
    private ContactListItemView.PhotoPosition mPhotoPosition;
    private final CharSequence mUnknownNameText;
    private boolean mUseCallableUri;

    public interface Listener {
        void onVideoCallIconClicked(int i);
    }

    public static class PhoneQuery {
        public static final String[] PROJECTION_ALTERNATIVE;
        public static final String[] PROJECTION_ALTERNATIVE_INTERNAL = {"_id", "data2", "data3", "data1", "contact_id", "lookup", "photo_id", "display_name_alt", "photo_thumb_uri"};
        public static final String[] PROJECTION_PRIMARY;
        public static final String[] PROJECTION_PRIMARY_INTERNAL = {"_id", "data2", "data3", "data1", "contact_id", "lookup", "photo_id", "display_name", "photo_thumb_uri"};

        static {
            ArrayList newArrayList = Lists.newArrayList((E[]) PROJECTION_PRIMARY_INTERNAL);
            if (CompatUtils.isMarshmallowCompatible()) {
                newArrayList.add("carrier_presence");
            }
            PROJECTION_PRIMARY = (String[]) newArrayList.toArray(new String[newArrayList.size()]);
            ArrayList newArrayList2 = Lists.newArrayList((E[]) PROJECTION_ALTERNATIVE_INTERNAL);
            if (CompatUtils.isMarshmallowCompatible()) {
                newArrayList2.add("carrier_presence");
            }
            PROJECTION_ALTERNATIVE = (String[]) newArrayList2.toArray(new String[newArrayList2.size()]);
        }
    }

    public PhoneNumberListAdapter(Context context) {
        super(context);
        setDefaultFilterHeaderText(R.string.list_filter_phones);
        this.mUnknownNameText = context.getText(17039374);
        this.mCountryIso = GeoUtil.getCurrentCountryIso(context);
        ExtendedPhoneDirectoriesManager extendedPhoneDirectoriesManager = ExtensionsFactory.getExtendedPhoneDirectoriesManager();
        if (extendedPhoneDirectoriesManager != null) {
            this.mExtendedDirectories = extendedPhoneDirectoriesManager.getExtendedDirectories(this.mContext);
        } else {
            this.mExtendedDirectories = new ArrayList();
        }
        int videoCallingAvailability = CallUtil.getVideoCallingAvailability(context);
        boolean z = false;
        this.mIsVideoEnabled = (videoCallingAvailability & 1) != 0;
        this.mIsPresenceEnabled = (videoCallingAvailability & 2) != 0 ? true : z;
    }

    public void configureLoader(CursorLoader cursorLoader, long j) {
        Uri.Builder builder;
        Uri uri;
        String queryString = getQueryString();
        if (queryString == null) {
            queryString = "";
        }
        if (isExtendedDirectory(j)) {
            DirectoryPartition extendedDirectoryFromId = getExtendedDirectoryFromId(j);
            String contentUri = extendedDirectoryFromId.getContentUri();
            if (contentUri != null) {
                Uri.Builder buildUpon = Uri.parse(contentUri).buildUpon();
                buildUpon.appendPath(queryString);
                buildUpon.appendQueryParameter("limit", String.valueOf(getDirectoryResultLimit(extendedDirectoryFromId)));
                cursorLoader.setUri(buildUpon.build());
                cursorLoader.setProjection(PhoneQuery.PROJECTION_PRIMARY);
                return;
            }
            throw new IllegalStateException("Extended directory must have a content URL: " + extendedDirectoryFromId);
        }
        boolean isRemoteDirectoryId = DirectoryCompat.isRemoteDirectoryId(j);
        if (isSearchMode()) {
            if (isRemoteDirectoryId) {
                uri = PhoneCompat.getContentFilterUri();
            } else if (this.mUseCallableUri) {
                uri = CallableCompat.getContentFilterUri();
            } else {
                uri = PhoneCompat.getContentFilterUri();
            }
            Uri.Builder buildUpon2 = uri.buildUpon();
            buildUpon2.appendPath(queryString);
            buildUpon2.appendQueryParameter("directory", String.valueOf(j));
            if (isRemoteDirectoryId) {
                buildUpon2.appendQueryParameter("limit", String.valueOf(getDirectoryResultLimit(getDirectoryById(j))));
            }
            builder = buildUpon2;
        } else {
            builder = (this.mUseCallableUri ? ContactsContract.CommonDataKinds.Callable.CONTENT_URI : ContactsContract.CommonDataKinds.Phone.CONTENT_URI).buildUpon().appendQueryParameter("directory", String.valueOf(0));
            if (isSectionHeaderDisplayEnabled()) {
                builder.appendQueryParameter("android.provider.extra.ADDRESS_BOOK_INDEX", "true");
            }
            applyFilter(cursorLoader, builder, j, getFilter());
        }
        String selection = cursorLoader.getSelection();
        String str = "length(data1) < 1000";
        if (!TextUtils.isEmpty(selection)) {
            str = selection + " AND " + str;
        }
        cursorLoader.setSelection(str);
        builder.appendQueryParameter("remove_duplicate_entries", "true");
        cursorLoader.setUri(builder.build());
        if (getContactNameDisplayOrder() == 1) {
            cursorLoader.setProjection(PhoneQuery.PROJECTION_PRIMARY);
        } else {
            cursorLoader.setProjection(PhoneQuery.PROJECTION_ALTERNATIVE);
        }
        if (getSortOrder() == 1) {
            cursorLoader.setSortOrder("sort_key");
        } else {
            cursorLoader.setSortOrder("sort_key_alt");
        }
    }

    /* access modifiers changed from: protected */
    public boolean isExtendedDirectory(long j) {
        return j >= this.mFirstExtendedDirectoryId;
    }

    private DirectoryPartition getExtendedDirectoryFromId(long j) {
        return this.mExtendedDirectories.get((int) (j - this.mFirstExtendedDirectoryId));
    }

    private void applyFilter(CursorLoader cursorLoader, Uri.Builder builder, long j, ContactListFilter contactListFilter) {
        if (contactListFilter != null && j == 0) {
            StringBuilder sb = new StringBuilder();
            ArrayList arrayList = new ArrayList();
            int i = contactListFilter.filterType;
            if (i != -5) {
                if (i == -3) {
                    sb.append("in_visible_group=1");
                    sb.append(" AND has_phone_number=1");
                } else if (!(i == -2 || i == -1)) {
                    if (i != 0) {
                        String str = TAG;
                        Log.w(str, "Unsupported filter type came (type: " + contactListFilter.filterType + ", toString: " + contactListFilter + ") showing all contacts.");
                    } else {
                        contactListFilter.addAccountQueryParameterToUrl(builder);
                    }
                }
            }
            cursorLoader.setSelection(sb.toString());
            cursorLoader.setSelectionArgs((String[]) arrayList.toArray(new String[0]));
        }
    }

    public String getPhoneNumber(int i) {
        Cursor cursor = (Cursor) getItem(i);
        if (cursor != null) {
            return cursor.getString(3);
        }
        return null;
    }

    public Uri getDataUri(int i) {
        int partitionForPosition = getPartitionForPosition(i);
        Cursor cursor = (Cursor) getItem(i);
        if (cursor != null) {
            return getDataUri(partitionForPosition, cursor);
        }
        return null;
    }

    public Uri getDataUri(int i, Cursor cursor) {
        long directoryId = ((DirectoryPartition) getPartition(i)).getDirectoryId();
        if (DirectoryCompat.isRemoteDirectoryId(directoryId) || DirectoryCompat.isEnterpriseDirectoryId(directoryId)) {
            return null;
        }
        return ContentUris.withAppendedId(ContactsContract.Data.CONTENT_URI, cursor.getLong(0));
    }

    /* access modifiers changed from: protected */
    public ContactListItemView newView(Context context, int i, Cursor cursor, int i2, ViewGroup viewGroup) {
        ContactListItemView newView = super.newView(context, i, cursor, i2, viewGroup);
        newView.setUnknownNameText(this.mUnknownNameText);
        newView.setQuickContactEnabled(isQuickContactEnabled());
        newView.setPhotoPosition(this.mPhotoPosition);
        return newView;
    }

    /* access modifiers changed from: protected */
    public void setHighlight(ContactListItemView contactListItemView, Cursor cursor) {
        contactListItemView.setHighlightedPrefix(isSearchMode() ? getUpperCaseQueryString() : null);
    }

    /* access modifiers changed from: protected */
    public void bindView(View view, int i, Cursor cursor, int i2) {
        super.bindView(view, i, cursor, i2);
        ContactListItemView contactListItemView = (ContactListItemView) view;
        setHighlight(contactListItemView, cursor);
        cursor.moveToPosition(i2);
        long j = cursor.getLong(4);
        boolean z = !cursor.moveToPrevious() || cursor.isBeforeFirst() || j != cursor.getLong(4);
        cursor.moveToPosition(i2);
        if (cursor.moveToNext() && !cursor.isAfterLast()) {
            int i3 = (j > cursor.getLong(4) ? 1 : (j == cursor.getLong(4) ? 0 : -1));
        }
        cursor.moveToPosition(i2);
        bindViewId(contactListItemView, cursor, 0);
        bindSectionHeaderAndDivider(contactListItemView, i2);
        if (z) {
            bindName(contactListItemView, cursor);
            if (isQuickContactEnabled()) {
                bindQuickContact(contactListItemView, i, cursor, 6, 8, 4, 5, 7);
            } else if (getDisplayPhotos()) {
                bindPhoto(contactListItemView, i, cursor);
            }
        } else {
            unbindName(contactListItemView);
            contactListItemView.removePhotoView(true, false);
        }
        bindPhoneNumber(contactListItemView, cursor, ((DirectoryPartition) getPartition(i)).isDisplayNumber(), i2);
    }

    /* access modifiers changed from: protected */
    public void bindPhoneNumber(ContactListItemView contactListItemView, Cursor cursor, boolean z, int i) {
        String str;
        contactListItemView.setLabel((!z || cursor.isNull(1)) ? null : ContactsContract.CommonDataKinds.Phone.getTypeLabel(getContext().getResources(), cursor.getInt(1), cursor.getString(2)));
        if (z) {
            str = cursor.getString(3);
        } else {
            str = cursor.getString(2);
            if (str == null) {
                str = GeoUtil.getGeocodedLocationFor(this.mContext, cursor.getString(3));
            }
        }
        contactListItemView.setPhoneNumber(str, this.mCountryIso);
        if (CompatUtils.isVideoCompatible()) {
            boolean z2 = false;
            boolean z3 = (cursor.getInt(9) & 1) != 0;
            if (this.mIsVideoEnabled && ((this.mIsPresenceEnabled && z3) || !this.mIsPresenceEnabled)) {
                z2 = true;
            }
            contactListItemView.setShowVideoCallIcon(z2, this.mListener, i);
        }
    }

    /* access modifiers changed from: protected */
    public void bindSectionHeaderAndDivider(ContactListItemView contactListItemView, int i) {
        String str = null;
        if (isSectionHeaderDisplayEnabled()) {
            IndexerListAdapter.Placement itemPlacementInSection = getItemPlacementInSection(i);
            if (itemPlacementInSection.firstInSection) {
                str = itemPlacementInSection.sectionHeader;
            }
            contactListItemView.setSectionHeader(str);
            return;
        }
        contactListItemView.setSectionHeader((String) null);
    }

    /* access modifiers changed from: protected */
    public void bindName(ContactListItemView contactListItemView, Cursor cursor) {
        contactListItemView.showDisplayName(cursor, 7, getContactNameDisplayOrder());
    }

    /* access modifiers changed from: protected */
    public void unbindName(ContactListItemView contactListItemView) {
        contactListItemView.hideDisplayName();
    }

    /* access modifiers changed from: protected */
    public void bindWorkProfileIcon(ContactListItemView contactListItemView, int i) {
        long directoryId = ((DirectoryPartition) getPartition(i)).getDirectoryId();
        contactListItemView.setWorkProfileIconEnabled(!isExtendedDirectory(directoryId) && ContactsUtils.determineUserType(Long.valueOf(directoryId), (Long) null) == 1);
    }

    /* access modifiers changed from: protected */
    public void bindPhoto(ContactListItemView contactListItemView, int i, Cursor cursor) {
        Uri uri;
        if (!isPhotoSupported(i)) {
            contactListItemView.removePhotoView();
            return;
        }
        long j = !cursor.isNull(6) ? cursor.getLong(6) : 0;
        if (j != 0) {
            getPhotoLoader().loadThumbnail(contactListItemView.getPhotoView(), j, false, getCircularPhotos(), (ContactPhotoManager.DefaultImageRequest) null);
            return;
        }
        String string = cursor.getString(8);
        ContactPhotoManager.DefaultImageRequest defaultImageRequest = null;
        if (string == null) {
            uri = null;
        } else {
            uri = Uri.parse(string);
        }
        if (uri == null) {
            defaultImageRequest = new ContactPhotoManager.DefaultImageRequest(cursor.getString(7), cursor.getString(5), getCircularPhotos());
        }
        getPhotoLoader().loadDirectoryPhoto(contactListItemView.getPhotoView(), uri, false, getCircularPhotos(), defaultImageRequest);
    }

    public void setPhotoPosition(ContactListItemView.PhotoPosition photoPosition) {
        this.mPhotoPosition = photoPosition;
    }

    public void setUseCallableUri(boolean z) {
        this.mUseCallableUri = z;
    }

    public void changeDirectories(Cursor cursor) {
        super.changeDirectories(cursor);
        if (getDirectorySearchMode() != 0) {
            int size = this.mExtendedDirectories.size();
            if (getPartitionCount() != cursor.getCount() + size) {
                this.mFirstExtendedDirectoryId = Long.MAX_VALUE;
                if (size > 0) {
                    int partitionCount = getPartitionCount();
                    long j = 1;
                    int i = 0;
                    for (int i2 = 0; i2 < partitionCount; i2++) {
                        long directoryId = ((DirectoryPartition) getPartition(i2)).getDirectoryId();
                        if (directoryId > j) {
                            j = directoryId;
                        }
                        if (!DirectoryCompat.isRemoteDirectoryId(directoryId)) {
                            i = i2 + 1;
                        }
                    }
                    this.mFirstExtendedDirectoryId = j + 1;
                    for (int i3 = 0; i3 < size; i3++) {
                        long j2 = this.mFirstExtendedDirectoryId + ((long) i3);
                        DirectoryPartition directoryPartition = this.mExtendedDirectories.get(i3);
                        if (getPartitionByDirectoryId(j2) == -1) {
                            addPartition(i, (CompositeCursorAdapter.Partition) directoryPartition);
                            directoryPartition.setDirectoryId(j2);
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public Uri getContactUri(int i, Cursor cursor, int i2, int i3) {
        DirectoryPartition directoryPartition = (DirectoryPartition) getPartition(i);
        long directoryId = directoryPartition.getDirectoryId();
        if (!isExtendedDirectory(directoryId)) {
            return super.getContactUri(i, cursor, i2, i3);
        }
        return ContactsContract.Contacts.CONTENT_LOOKUP_URI.buildUpon().appendPath("encoded").appendQueryParameter("displayName", directoryPartition.getLabel()).appendQueryParameter("directory", String.valueOf(directoryId)).encodedFragment(cursor.getString(i3)).build();
    }
}
