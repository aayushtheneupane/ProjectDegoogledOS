package com.android.dialer.searchfragment.directories;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.support.p002v7.appcompat.R$style;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.searchfragment.common.SearchCursor;
import com.android.dialer.searchfragment.directories.DirectoriesCursorLoader;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class DirectoryContactsCursor extends MergeCursor implements SearchCursor {
    private static final String[] PROJECTION;

    static {
        String[] strArr = SearchCursor.HEADER_PROJECTION;
        String[] strArr2 = (String[]) Arrays.copyOf(strArr, strArr.length + 1);
        strArr2[strArr2.length - 1] = "directory_id";
        PROJECTION = strArr2;
    }

    private DirectoryContactsCursor(Cursor[] cursorArr) {
        super(cursorArr);
    }

    public static DirectoryContactsCursor newInstance(Context context, Cursor[] cursorArr, List<DirectoriesCursorLoader.Directory> list) {
        Assert.checkArgument(cursorArr.length == list.size(), "Directories (%d) and cursors (%d) must be the same size.", Integer.valueOf(list.size()), Integer.valueOf(cursorArr.length));
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < cursorArr.length; i++) {
            Cursor cursor = cursorArr[i];
            if (cursor != null && !cursor.isClosed()) {
                DirectoriesCursorLoader.Directory directory = list.get(i);
                if (cursor.getCount() == 0) {
                    cursor.close();
                } else {
                    String displayName = directory.getDisplayName();
                    long id = directory.getId();
                    MatrixCursor matrixCursor = new MatrixCursor(PROJECTION, 1);
                    if (R$style.isLocalEnterpriseDirectoryId(id)) {
                        matrixCursor.addRow(new Object[]{context.getString(R.string.directory_search_label_work), Long.valueOf(id)});
                    } else {
                        matrixCursor.addRow(new Object[]{context.getString(R.string.directory, new Object[]{displayName}), Long.valueOf(id)});
                    }
                    arrayList.add(matrixCursor);
                    arrayList.add(cursor);
                }
            }
        }
        Cursor[] cursorArr2 = (Cursor[]) arrayList.toArray(new Cursor[arrayList.size()]);
        if (cursorArr2.length > 0) {
            return new DirectoryContactsCursor(cursorArr2);
        }
        return null;
    }

    public long getDirectoryId() {
        int i;
        int position = getPosition();
        while (moveToPrevious()) {
            int columnIndex = getColumnIndex("directory_id");
            if (columnIndex != -1 && (i = getInt(columnIndex)) != -1) {
                moveToPosition(position);
                return (long) i;
            }
        }
        throw new IllegalStateException(GeneratedOutlineSupport.outline5("No directory id for contact at: ", position));
    }

    public boolean isHeader() {
        return !isClosed() && getColumnIndex(SearchCursor.HEADER_PROJECTION[0]) != -1;
    }

    public boolean updateQuery(String str) {
        return false;
    }
}
