package com.android.contacts.list;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.database.MergeCursor;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.provider.ContactsContract;
import com.google.common.collect.Lists;
import java.util.ArrayList;

public class FavoritesAndContactsLoader extends CursorLoader {
    private boolean mLoadFavorites;
    private String[] mProjection;

    public FavoritesAndContactsLoader(Context context) {
        super(context);
    }

    public void setLoadFavorites(boolean z) {
        this.mLoadFavorites = z;
    }

    public void setProjection(String[] strArr) {
        super.setProjection(strArr);
        this.mProjection = strArr;
    }

    public Cursor loadInBackground() {
        ArrayList newArrayList = Lists.newArrayList();
        if (this.mLoadFavorites) {
            newArrayList.add(loadFavoritesContacts());
        }
        final Cursor loadContacts = loadContacts();
        newArrayList.add(loadContacts);
        return new MergeCursor((Cursor[]) newArrayList.toArray(new Cursor[newArrayList.size()])) {
            public Bundle getExtras() {
                Cursor cursor = loadContacts;
                return cursor == null ? new Bundle() : cursor.getExtras();
            }
        };
    }

    private Cursor loadContacts() {
        try {
            return super.loadInBackground();
        } catch (SQLiteException | NullPointerException | SecurityException unused) {
            return null;
        }
    }

    private Cursor loadFavoritesContacts() {
        StringBuilder sb = new StringBuilder();
        sb.append("starred=?");
        ContactListFilter filter = ContactListFilterController.getInstance(getContext()).getFilter();
        if (filter != null && filter.filterType == -3) {
            sb.append(" AND ");
            sb.append("in_visible_group=1");
        }
        return getContext().getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, this.mProjection, sb.toString(), new String[]{"1"}, getSortOrder());
    }
}
