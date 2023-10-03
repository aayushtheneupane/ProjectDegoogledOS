package com.android.dialer.searchfragment.nearbyplaces;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import com.android.dialer.R;
import com.android.dialer.searchfragment.common.SearchCursor;

final class NearbyPlacesCursor extends MergeCursor implements SearchCursor {
    private final long directoryId;
    private final Cursor nearbyPlacesCursor;

    private NearbyPlacesCursor(Cursor[] cursorArr, long j) {
        super(cursorArr);
        this.nearbyPlacesCursor = cursorArr[1];
        this.directoryId = j;
    }

    static NearbyPlacesCursor newInstance(Context context, Cursor cursor, long j) {
        MatrixCursor matrixCursor = new MatrixCursor(SearchCursor.HEADER_PROJECTION);
        matrixCursor.addRow(new String[]{context.getString(R.string.nearby_places)});
        return new NearbyPlacesCursor(new Cursor[]{matrixCursor, cursor}, j);
    }

    public int getCount() {
        int count;
        Cursor cursor = this.nearbyPlacesCursor;
        if (cursor == null || cursor.isClosed() || (count = this.nearbyPlacesCursor.getCount()) == 0) {
            return 0;
        }
        return count + 1;
    }

    public long getDirectoryId() {
        return this.directoryId;
    }

    public boolean isHeader() {
        return isFirst();
    }

    public boolean updateQuery(String str) {
        return false;
    }
}
