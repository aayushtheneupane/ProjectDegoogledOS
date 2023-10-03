package com.android.dialer.searchfragment.list;

import android.database.MatrixCursor;
import com.android.dialer.searchfragment.common.SearchCursor;
import java.util.ArrayList;
import java.util.List;

public final class SearchCursorManager {
    private static final LocationPermissionCursor LOCATION_PERMISSION_CURSOR = new LocationPermissionCursor(new String[0]);
    private SearchCursor contactsCursor = null;
    private SearchCursor corpDirectoryCursor = null;
    private SearchCursor nearbyPlacesCursor = null;
    private List<Integer> searchActions = new ArrayList();
    private boolean showLocationPermissionRequest;

    private static class LocationPermissionCursor extends MatrixCursor implements SearchCursor {
        LocationPermissionCursor(String[] strArr) {
            super(strArr);
        }

        public long getDirectoryId() {
            return 0;
        }

        public boolean isHeader() {
            return false;
        }

        public boolean updateQuery(String str) {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public int getCount() {
        SearchCursor searchCursor = this.contactsCursor;
        int i = 0;
        if (searchCursor != null) {
            i = 0 + searchCursor.getCount();
        }
        if (this.showLocationPermissionRequest) {
            i++;
        } else {
            SearchCursor searchCursor2 = this.nearbyPlacesCursor;
            if (searchCursor2 != null) {
                i += searchCursor2.getCount();
            }
        }
        SearchCursor searchCursor3 = this.corpDirectoryCursor;
        if (searchCursor3 != null) {
            i += searchCursor3.getCount();
        }
        return this.searchActions.size() + i;
    }

    /* access modifiers changed from: package-private */
    public SearchCursor getCursor(int i) {
        SearchCursor searchCursor;
        if (this.showLocationPermissionRequest) {
            if (i == 0) {
                return LOCATION_PERMISSION_CURSOR;
            }
            i--;
        }
        SearchCursor searchCursor2 = this.contactsCursor;
        if (searchCursor2 != null) {
            int count = i - searchCursor2.getCount();
            if (count < 0) {
                this.contactsCursor.moveToPosition(i);
                return this.contactsCursor;
            }
            i = count;
        }
        if (!this.showLocationPermissionRequest && (searchCursor = this.nearbyPlacesCursor) != null) {
            int count2 = i - searchCursor.getCount();
            if (count2 < 0) {
                this.nearbyPlacesCursor.moveToPosition(i);
                return this.nearbyPlacesCursor;
            }
            i = count2;
        }
        SearchCursor searchCursor3 = this.corpDirectoryCursor;
        if (searchCursor3 == null || i - searchCursor3.getCount() >= 0) {
            throw new IllegalStateException("No valid cursor.");
        }
        this.corpDirectoryCursor.moveToPosition(i);
        return this.corpDirectoryCursor;
    }

    /* access modifiers changed from: package-private */
    public int getRowType(int i) {
        int count = getCount();
        if (i >= count) {
            throw new IllegalStateException(String.format("Invalid position: %d, cursor count: %d", new Object[]{Integer.valueOf(i), Integer.valueOf(count)}));
        } else if (i >= count - this.searchActions.size()) {
            return 7;
        } else {
            SearchCursor cursor = getCursor(i);
            if (cursor == this.contactsCursor) {
                if (cursor.isHeader()) {
                    return 1;
                }
                return 2;
            } else if (cursor == LOCATION_PERMISSION_CURSOR) {
                return 8;
            } else {
                if (cursor == this.nearbyPlacesCursor) {
                    return cursor.isHeader() ? 3 : 4;
                }
                if (cursor == this.corpDirectoryCursor) {
                    return cursor.isHeader() ? 5 : 6;
                }
                throw new IllegalStateException("No valid row type.");
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int getSearchAction(int i) {
        return this.searchActions.get(this.searchActions.size() + (i - getCount())).intValue();
    }

    /* access modifiers changed from: package-private */
    public boolean setContactsCursor(SearchCursor searchCursor) {
        if (searchCursor == this.contactsCursor) {
            return false;
        }
        if (searchCursor != null) {
            this.contactsCursor = searchCursor;
            return true;
        }
        this.contactsCursor = null;
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean setCorpDirectoryCursor(SearchCursor searchCursor) {
        if (searchCursor == this.corpDirectoryCursor) {
            return false;
        }
        if (searchCursor != null) {
            this.corpDirectoryCursor = searchCursor;
            return true;
        }
        this.corpDirectoryCursor = null;
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean setNearbyPlacesCursor(SearchCursor searchCursor) {
        if (searchCursor == this.nearbyPlacesCursor) {
            return false;
        }
        if (searchCursor != null) {
            this.nearbyPlacesCursor = searchCursor;
            return true;
        }
        this.nearbyPlacesCursor = null;
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean setQuery(String str) {
        SearchCursor searchCursor = this.contactsCursor;
        boolean updateQuery = searchCursor != null ? searchCursor.updateQuery(str) : false;
        SearchCursor searchCursor2 = this.nearbyPlacesCursor;
        if (searchCursor2 != null) {
            updateQuery |= searchCursor2.updateQuery(str);
        }
        SearchCursor searchCursor3 = this.corpDirectoryCursor;
        return searchCursor3 != null ? updateQuery | searchCursor3.updateQuery(str) : updateQuery;
    }

    /* access modifiers changed from: package-private */
    public boolean setSearchActions(List<Integer> list) {
        if (this.searchActions.equals(list)) {
            return false;
        }
        this.searchActions = list;
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean showLocationPermissionRequest(boolean z) {
        if (this.showLocationPermissionRequest == z) {
            return false;
        }
        this.showLocationPermissionRequest = z;
        return true;
    }
}
