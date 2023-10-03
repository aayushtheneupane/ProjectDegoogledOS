package com.android.contacts.list;

import android.content.ContentUris;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import com.android.contacts.compat.ContactsCompat;
import com.android.contacts.model.account.AccountWithDataSet;
import com.android.contacts.preference.ContactsPreferences;
import java.util.ArrayList;

public class DefaultContactListAdapter extends ContactListAdapter {
    public DefaultContactListAdapter(Context context) {
        super(context);
    }

    public void configureLoader(CursorLoader cursorLoader, long j) {
        if (cursorLoader instanceof FavoritesAndContactsLoader) {
            ((FavoritesAndContactsLoader) cursorLoader).setLoadFavorites(shouldIncludeFavorites());
        }
        String str = null;
        String str2 = "sort_key";
        if (isSearchMode()) {
            String queryString = getQueryString();
            if (queryString == null) {
                queryString = "";
            }
            String trim = queryString.trim();
            if (TextUtils.isEmpty(trim)) {
                cursorLoader.setUri(ContactsContract.Contacts.CONTENT_URI);
                cursorLoader.setProjection(getProjection(false));
                cursorLoader.setSelection("0");
            } else if (isGroupMembersFilter()) {
                configureUri(cursorLoader, j, getFilter());
                cursorLoader.setProjection(getProjection(false));
                cursorLoader.setSelection("display_name LIKE ?1 OR display_name_alt LIKE ?1");
                cursorLoader.setSelectionArgs(new String[]{trim + "%"});
            } else {
                Uri.Builder buildUpon = ContactsCompat.getContentUri().buildUpon();
                appendSearchParameters(buildUpon, trim, j);
                cursorLoader.setUri(buildUpon.build());
                cursorLoader.setProjection(getProjection(true));
                str = str2;
            }
        } else {
            ContactListFilter filter = getFilter();
            configureUri(cursorLoader, j, filter);
            if (filter == null || filter.filterType != -8) {
                cursorLoader.setProjection(getProjection(false));
            } else {
                cursorLoader.setProjection(getDataProjectionForContacts(false));
            }
            configureSelection(cursorLoader, j, filter);
        }
        if (getSortOrder() == 1) {
            if (str != null) {
                str2 = str + ", sort_key";
            }
        } else if (str == null) {
            str2 = "sort_key_alt";
        } else {
            str2 = str + ", sort_key_alt";
        }
        cursorLoader.setSortOrder(str2);
    }

    private boolean isGroupMembersFilter() {
        ContactListFilter filter = getFilter();
        return filter != null && filter.filterType == -7;
    }

    private void appendSearchParameters(Uri.Builder builder, String str, long j) {
        builder.appendPath(str);
        builder.appendQueryParameter("directory", String.valueOf(j));
        if (!(j == 0 || j == 1)) {
            builder.appendQueryParameter("limit", String.valueOf(getDirectoryResultLimit(getDirectoryById(j))));
        }
        builder.appendQueryParameter("deferred_snippeting", "1");
    }

    /* access modifiers changed from: protected */
    public void configureUri(CursorLoader cursorLoader, long j, ContactListFilter contactListFilter) {
        int i;
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        if (contactListFilter != null) {
            int i2 = contactListFilter.filterType;
            if (i2 == -6) {
                String selectedContactLookupKey = getSelectedContactLookupKey();
                uri = selectedContactLookupKey != null ? Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, selectedContactLookupKey) : ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, getSelectedContactId());
            } else if (i2 == -8) {
                uri = ContactsContract.Data.CONTENT_URI;
            }
        }
        if (j == 0 && isSectionHeaderDisplayEnabled()) {
            uri = ContactListAdapter.buildSectionIndexerUri(uri);
        }
        if (!(contactListFilter == null || (i = contactListFilter.filterType) == -3 || i == -6)) {
            Uri.Builder buildUpon = uri.buildUpon();
            int i3 = contactListFilter.filterType;
            if (i3 == 0 || i3 == -7) {
                contactListFilter.addAccountQueryParameterToUrl(buildUpon);
            }
            uri = buildUpon.build();
        }
        cursorLoader.setUri(uri);
    }

    private void configureSelection(CursorLoader cursorLoader, long j, ContactListFilter contactListFilter) {
        if (contactListFilter != null && j == 0) {
            StringBuilder sb = new StringBuilder();
            ArrayList arrayList = new ArrayList();
            int i = contactListFilter.filterType;
            if (i != 0) {
                switch (i) {
                    case -8:
                        if (contactListFilter.accountType == null) {
                            sb.append(AccountWithDataSet.LOCAL_ACCOUNT_SELECTION);
                            break;
                        } else {
                            sb.append("account_type");
                            sb.append("=?");
                            arrayList.add(contactListFilter.accountType);
                            if (contactListFilter.accountName != null) {
                                sb.append(" AND ");
                                sb.append("account_name");
                                sb.append("=?");
                                arrayList.add(contactListFilter.accountName);
                                break;
                            }
                        }
                        break;
                    case -5:
                        sb.append("has_phone_number=1");
                        break;
                    case -4:
                        sb.append("starred!=0");
                        break;
                    case -3:
                        sb.append("in_visible_group=1");
                        if (isCustomFilterForPhoneNumbersOnly()) {
                            sb.append(" AND has_phone_number=1");
                            break;
                        }
                        break;
                }
            }
            cursorLoader.setSelection(sb.toString());
            cursorLoader.setSelectionArgs((String[]) arrayList.toArray(new String[0]));
        }
    }

    /* access modifiers changed from: protected */
    public void bindView(View view, int i, Cursor cursor, int i2) {
        super.bindView(view, i, cursor, i2);
        ContactListItemView contactListItemView = (ContactListItemView) view;
        contactListItemView.setHighlightedPrefix(isSearchMode() ? getUpperCaseQueryString() : null);
        if (isSelectionVisible()) {
            contactListItemView.setActivated(isSelectedContact(i, cursor));
        }
        bindSectionHeaderAndDivider(contactListItemView, i2, cursor);
        if (isQuickContactEnabled()) {
            bindQuickContact(contactListItemView, i, cursor, 4, 5, 0, 6, 1);
        } else if (getDisplayPhotos()) {
            bindPhoto(contactListItemView, i, cursor);
        }
        bindNameAndViewId(contactListItemView, cursor);
        bindPresenceAndStatusMessage(contactListItemView, cursor);
        if (isSearchMode()) {
            bindSearchSnippet(contactListItemView, cursor);
        } else {
            contactListItemView.setSnippet((String) null);
        }
    }

    private boolean isCustomFilterForPhoneNumbersOnly() {
        return PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean(ContactsPreferences.PREF_DISPLAY_ONLY_PHONES, false);
    }
}
