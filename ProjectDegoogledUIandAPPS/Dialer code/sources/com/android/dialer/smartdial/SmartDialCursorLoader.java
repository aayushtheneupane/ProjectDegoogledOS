package com.android.dialer.smartdial;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import com.android.dialer.database.Database;
import com.android.dialer.database.DialerDatabaseHelper;
import com.android.dialer.smartdial.util.SmartDialNameMatcher;
import com.android.dialer.util.PermissionsUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class SmartDialCursorLoader extends AsyncTaskLoader<Cursor> {
    private final Context context;
    private Cursor cursor;
    private SmartDialNameMatcher nameMatcher;
    private String query;
    private boolean showEmptyListForNullQuery = true;

    public static class PhoneQuery {
        public static final String[] PROJECTION_ALTERNATIVE_INTERNAL = {"_id", "data2", "data3", "data1", "contact_id", "lookup", "photo_id", "display_name_alt", "photo_thumb_uri"};
        public static final String[] PROJECTION_PRIMARY;
        public static final String[] PROJECTION_PRIMARY_INTERNAL = {"_id", "data2", "data3", "data1", "contact_id", "lookup", "photo_id", "display_name", "photo_thumb_uri"};

        static {
            ArrayList arrayList = new ArrayList(Arrays.asList(PROJECTION_PRIMARY_INTERNAL));
            arrayList.add("carrier_presence");
            PROJECTION_PRIMARY = (String[]) arrayList.toArray(new String[arrayList.size()]);
            ArrayList arrayList2 = new ArrayList(Arrays.asList(PROJECTION_ALTERNATIVE_INTERNAL));
            arrayList2.add("carrier_presence");
            String[] strArr = (String[]) arrayList2.toArray(new String[arrayList2.size()]);
        }
    }

    public SmartDialCursorLoader(Context context2) {
        super(context2);
        this.context = context2;
    }

    public void configureQuery(String str) {
        this.query = SmartDialNameMatcher.normalizeNumber(this.context, str);
        this.nameMatcher = new SmartDialNameMatcher(this.query);
        this.nameMatcher.setShouldMatchEmptyQuery(!this.showEmptyListForNullQuery);
    }

    public void onCanceled(Object obj) {
        Cursor cursor2 = (Cursor) obj;
        super.onCanceled(cursor2);
        if (cursor2 != null) {
            cursor2.close();
        }
    }

    /* access modifiers changed from: protected */
    public void onReset() {
        cancelLoad();
        Cursor cursor2 = this.cursor;
        if (cursor2 != null) {
            if (cursor2 != null) {
                cursor2.close();
            }
            this.cursor = null;
        }
    }

    /* access modifiers changed from: protected */
    public void onStartLoading() {
        Cursor cursor2 = this.cursor;
        if (cursor2 != null) {
            deliverResult(cursor2);
        }
        if (this.cursor == null) {
            forceLoad();
        }
    }

    /* access modifiers changed from: protected */
    public void onStopLoading() {
        cancelLoad();
    }

    public void deliverResult(Cursor cursor2) {
        if (!isReset()) {
            Cursor cursor3 = this.cursor;
            this.cursor = cursor2;
            if (isStarted()) {
                super.deliverResult(cursor2);
            }
            if (cursor3 != null && cursor3 != cursor2) {
                cursor3.close();
            }
        } else if (cursor2 != null) {
            cursor2.close();
        }
    }

    public Cursor loadInBackground() {
        if (!PermissionsUtil.hasContactsReadPermissions(this.context)) {
            return new MatrixCursor(PhoneQuery.PROJECTION_PRIMARY);
        }
        ArrayList<DialerDatabaseHelper.ContactNumber> looseMatches = Database.get(this.context).getDatabaseHelper(this.context).getLooseMatches(this.query, this.nameMatcher);
        MatrixCursor matrixCursor = new MatrixCursor(PhoneQuery.PROJECTION_PRIMARY);
        Object[] objArr = new Object[PhoneQuery.PROJECTION_PRIMARY.length];
        Iterator<DialerDatabaseHelper.ContactNumber> it = looseMatches.iterator();
        while (it.hasNext()) {
            DialerDatabaseHelper.ContactNumber next = it.next();
            objArr[0] = Long.valueOf(next.dataId);
            objArr[3] = next.phoneNumber;
            objArr[4] = Long.valueOf(next.f20id);
            objArr[5] = next.lookupKey;
            objArr[6] = Long.valueOf(next.photoId);
            objArr[7] = next.displayName;
            objArr[9] = Integer.valueOf(next.carrierPresence);
            matrixCursor.addRow(objArr);
        }
        return matrixCursor;
    }
}
