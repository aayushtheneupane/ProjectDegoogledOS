package com.android.dialer.searchfragment.cp2;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.provider.ContactsContract;
import android.text.TextUtils;
import com.android.dialer.R;
import com.android.dialer.common.LogUtil;
import com.android.dialer.contacts.ContactsComponent;
import com.android.dialer.contacts.displaypreference.ContactDisplayPreferences;
import com.android.dialer.searchfragment.common.Projections;
import com.android.dialer.searchfragment.common.SearchCursor;
import com.android.dialer.smartdial.SmartDialCursorLoader;
import com.android.dialer.util.PermissionsUtil;
import com.android.tools.p006r8.GeneratedOutlineSupport;

public final class SearchContactsCursorLoader extends CursorLoader {
    private final boolean isRegularSearch;
    private final String query;

    static class RegularSearchCursor extends MergeCursor implements SearchCursor {
        public RegularSearchCursor(Cursor[] cursorArr) {
            super(cursorArr);
        }

        static RegularSearchCursor newInstance(Context context, Cursor cursor) {
            if (cursor == null || cursor.getCount() == 0) {
                LogUtil.m9i("RegularSearchCursor.newInstance", "Cursor was null or empty", new Object[0]);
                return new RegularSearchCursor(new Cursor[]{new MatrixCursor(Projections.CP2_PROJECTION)});
            }
            MatrixCursor matrixCursor = new MatrixCursor(SearchCursor.HEADER_PROJECTION);
            matrixCursor.addRow(new String[]{context.getString(R.string.all_contacts)});
            return new RegularSearchCursor(new Cursor[]{matrixCursor, cursor});
        }

        public long getDirectoryId() {
            return 0;
        }

        public boolean isHeader() {
            return isFirst();
        }

        public boolean updateQuery(String str) {
            return false;
        }
    }

    static class SmartDialCursor extends MergeCursor implements SearchCursor {
        private SmartDialCursor(Cursor[] cursorArr) {
            super(cursorArr);
        }

        static SmartDialCursor newInstance(Context context, Cursor cursor) {
            if (cursor == null || cursor.getCount() == 0) {
                LogUtil.m9i("SmartDialCursor.newInstance", "Cursor was null or empty", new Object[0]);
                return new SmartDialCursor(new Cursor[]{new MatrixCursor(Projections.CP2_PROJECTION)});
            }
            MatrixCursor matrixCursor = new MatrixCursor(SearchCursor.HEADER_PROJECTION);
            matrixCursor.addRow(new String[]{context.getString(R.string.all_contacts)});
            Cursor[] cursorArr = new Cursor[2];
            cursorArr[0] = matrixCursor;
            MatrixCursor matrixCursor2 = new MatrixCursor(Projections.CP2_PROJECTION);
            if (!cursor.moveToFirst()) {
                cursorArr[1] = matrixCursor2;
            } else {
                do {
                    Object[] objArr = new Object[Projections.CP2_PROJECTION.length];
                    int i = 0;
                    while (true) {
                        String[] strArr = Projections.CP2_PROJECTION;
                        if (i >= strArr.length) {
                            break;
                        }
                        int columnIndex = cursor.getColumnIndex(strArr[i]);
                        if (columnIndex != -1) {
                            int type = cursor.getType(columnIndex);
                            if (type == 1) {
                                objArr[i] = Integer.valueOf(cursor.getInt(columnIndex));
                            } else if (type == 2) {
                                objArr[i] = Float.valueOf(cursor.getFloat(columnIndex));
                            } else if (type == 3) {
                                objArr[i] = cursor.getString(columnIndex);
                            } else if (type == 4) {
                                objArr[i] = cursor.getBlob(columnIndex);
                            }
                        }
                        i++;
                    }
                    matrixCursor2.addRow(objArr);
                } while (cursor.moveToNext());
            }
            cursorArr[1] = matrixCursor2;
            return new SmartDialCursor(cursorArr);
        }

        public long getDirectoryId() {
            return 0;
        }

        public boolean isHeader() {
            return isFirst();
        }

        public boolean updateQuery(String str) {
            return false;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SearchContactsCursorLoader(Context context, String str, boolean z) {
        super(context, ContactsContract.CommonDataKinds.Phone.CONTENT_FILTER_URI.buildUpon().appendPath(str).build(), getProjection(context), GeneratedOutlineSupport.outline8(GeneratedOutlineSupport.outline12(new StringBuilder(), getProjection(context)[4], " IS NOT NULL"), " AND data1 IS NOT NULL"), (String[]) null, GeneratedOutlineSupport.outline12(new StringBuilder(), ContactsComponent.get(context).contactDisplayPreferences().getSortOrder() == ContactDisplayPreferences.SortOrder.BY_PRIMARY ? "sort_key" : "sort_key_alt", " ASC"));
        this.query = TextUtils.isEmpty(str) ? "" : str;
        this.isRegularSearch = z;
    }

    private static String[] getProjection(Context context) {
        if (ContactsComponent.get(context).contactDisplayPreferences().getDisplayOrder() == ContactDisplayPreferences.DisplayOrder.PRIMARY) {
            return Projections.CP2_PROJECTION;
        }
        return Projections.CP2_PROJECTION_ALTERNATIVE;
    }

    public Cursor loadInBackground() {
        if (!PermissionsUtil.hasContactsReadPermissions(getContext())) {
            LogUtil.m9i("SearchContactsCursorLoader.loadInBackground", "Contacts permission denied.", new Object[0]);
            return null;
        } else if (this.isRegularSearch) {
            return RegularSearchCursor.newInstance(getContext(), super.loadInBackground());
        } else {
            SmartDialCursorLoader smartDialCursorLoader = new SmartDialCursorLoader(getContext());
            smartDialCursorLoader.configureQuery(this.query);
            return SmartDialCursor.newInstance(getContext(), smartDialCursorLoader.loadInBackground());
        }
    }

    /* renamed from: loadInBackground  reason: collision with other method in class */
    public Object m116loadInBackground() {
        if (!PermissionsUtil.hasContactsReadPermissions(getContext())) {
            LogUtil.m9i("SearchContactsCursorLoader.loadInBackground", "Contacts permission denied.", new Object[0]);
            return null;
        } else if (this.isRegularSearch) {
            return RegularSearchCursor.newInstance(getContext(), super.loadInBackground());
        } else {
            SmartDialCursorLoader smartDialCursorLoader = new SmartDialCursorLoader(getContext());
            smartDialCursorLoader.configureQuery(this.query);
            return SmartDialCursor.newInstance(getContext(), smartDialCursorLoader.loadInBackground());
        }
    }
}
