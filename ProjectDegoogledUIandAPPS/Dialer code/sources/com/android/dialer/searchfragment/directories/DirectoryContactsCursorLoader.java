package com.android.dialer.searchfragment.directories;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.provider.ContactsContract;
import com.android.dialer.searchfragment.common.Projections;
import com.android.dialer.searchfragment.directories.DirectoriesCursorLoader;
import java.util.ArrayList;
import java.util.List;

public final class DirectoryContactsCursorLoader extends CursorLoader {
    private static final Uri ENTERPRISE_CONTENT_FILTER_URI = Uri.withAppendedPath(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, "filter_enterprise");
    private final Cursor[] cursors;
    private final List<DirectoriesCursorLoader.Directory> directories;
    private final String query;

    public DirectoryContactsCursorLoader(Context context, String str, List<DirectoriesCursorLoader.Directory> list) {
        super(context, (Uri) null, Projections.DATA_PROJECTION, "length(data1) < 1000 AND data1 IS NOT NULL", (String[]) null, "sort_key");
        this.query = str;
        this.directories = new ArrayList(list);
        this.cursors = new Cursor[list.size()];
    }

    static Uri getContentFilterUri(String str, long j) {
        return ENTERPRISE_CONTENT_FILTER_URI.buildUpon().appendPath(str).appendQueryParameter("directory", String.valueOf(j)).appendQueryParameter("remove_duplicate_entries", "true").appendQueryParameter("limit", "10").build();
    }

    private static Object[] objectArrayFromCursor(Cursor cursor) {
        Object[] objArr = new Object[cursor.getColumnCount()];
        for (int i = 0; i < cursor.getColumnCount(); i++) {
            int type = cursor.getType(i);
            if (type == 4) {
                objArr[i] = cursor.getBlob(i);
            } else if (type == 2) {
                objArr[i] = Double.valueOf(cursor.getDouble(i));
            } else if (type == 1) {
                objArr[i] = Long.valueOf(cursor.getLong(i));
            } else if (type == 3) {
                objArr[i] = cursor.getString(i);
            } else if (type == 0) {
                objArr[i] = null;
            } else {
                throw new IllegalStateException("Unknown fieldType (" + type + ") for column: " + i);
            }
        }
        return objArr;
    }

    public Cursor loadInBackground() {
        for (int i = 0; i < this.directories.size(); i++) {
            DirectoriesCursorLoader.Directory directory = this.directories.get(i);
            MatrixCursor matrixCursor = null;
            if (ContactsContract.Directory.isRemoteDirectoryId(directory.getId()) || ContactsContract.Directory.isEnterpriseDirectoryId(directory.getId())) {
                long id = directory.getId();
                if (id == 1 || id == 1000000001) {
                    this.cursors[i] = null;
                } else {
                    Cursor query2 = getContext().getContentResolver().query(getContentFilterUri(this.query, directory.getId()), getProjection(), getSelection(), getSelectionArgs(), getSortOrder());
                    Cursor[] cursorArr = this.cursors;
                    if (query2 != null) {
                        matrixCursor = new MatrixCursor(query2.getColumnNames());
                        try {
                            if (query2.moveToFirst()) {
                                do {
                                    if (query2.getString(3) != null) {
                                        matrixCursor.addRow(objectArrayFromCursor(query2));
                                    }
                                } while (query2.moveToNext());
                            }
                        } finally {
                            query2.close();
                        }
                    }
                    cursorArr[i] = matrixCursor;
                }
            } else {
                this.cursors[i] = null;
            }
        }
        return DirectoryContactsCursor.newInstance(getContext(), this.cursors, this.directories);
    }

    /* renamed from: loadInBackground  reason: collision with other method in class */
    public Object m118loadInBackground() {
        for (int i = 0; i < this.directories.size(); i++) {
            DirectoriesCursorLoader.Directory directory = this.directories.get(i);
            MatrixCursor matrixCursor = null;
            if (ContactsContract.Directory.isRemoteDirectoryId(directory.getId()) || ContactsContract.Directory.isEnterpriseDirectoryId(directory.getId())) {
                long id = directory.getId();
                if (id == 1 || id == 1000000001) {
                    this.cursors[i] = null;
                } else {
                    Cursor query2 = getContext().getContentResolver().query(getContentFilterUri(this.query, directory.getId()), getProjection(), getSelection(), getSelectionArgs(), getSortOrder());
                    Cursor[] cursorArr = this.cursors;
                    if (query2 != null) {
                        matrixCursor = new MatrixCursor(query2.getColumnNames());
                        try {
                            if (query2.moveToFirst()) {
                                do {
                                    if (query2.getString(3) != null) {
                                        matrixCursor.addRow(objectArrayFromCursor(query2));
                                    }
                                } while (query2.moveToNext());
                            }
                        } finally {
                            query2.close();
                        }
                    }
                    cursorArr[i] = matrixCursor;
                }
            } else {
                this.cursors[i] = null;
            }
        }
        return DirectoryContactsCursor.newInstance(getContext(), this.cursors, this.directories);
    }
}
