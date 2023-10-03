package com.android.dialer.searchfragment.common;

import android.database.Cursor;

public interface SearchCursor extends Cursor {
    public static final String[] HEADER_PROJECTION = {"header_text"};

    long getDirectoryId();

    boolean isHeader();

    boolean updateQuery(String str);
}
