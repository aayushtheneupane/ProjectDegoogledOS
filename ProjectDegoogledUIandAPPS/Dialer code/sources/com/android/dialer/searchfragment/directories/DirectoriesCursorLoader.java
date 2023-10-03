package com.android.dialer.searchfragment.directories;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.provider.ContactsContract;
import com.android.dialer.common.LogUtil;
import com.android.dialer.util.PermissionsUtil;
import com.google.auto.value.AutoValue;
import java.util.ArrayList;
import java.util.List;

public final class DirectoriesCursorLoader extends CursorLoader {
    public static final String[] PROJECTION = {"_id", "displayName", "photoSupport"};

    @AutoValue
    public static abstract class Directory {
        public abstract String getDisplayName();

        public abstract long getId();
    }

    public DirectoriesCursorLoader(Context context) {
        super(context, ContactsContract.Directory.ENTERPRISE_CONTENT_URI, PROJECTION, (String) null, (String[]) null, "_id");
    }

    public static List<Directory> toDirectories(Cursor cursor) {
        if (cursor == null) {
            LogUtil.m9i("DirectoriesCursorLoader.toDirectories", "Cursor was null", new Object[0]);
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            long j = (long) cursor.getInt(0);
            boolean z = true;
            String string = cursor.getString(1);
            if (cursor.getInt(2) == 0) {
                z = false;
            }
            arrayList.add(new AutoValue_DirectoriesCursorLoader_Directory(j, string, z));
        }
        return arrayList;
    }

    public Cursor loadInBackground() {
        if (PermissionsUtil.hasContactsReadPermissions(getContext())) {
            return super.loadInBackground();
        }
        LogUtil.m9i("DirectoriesCursorLoader.loadInBackground", "Contacts permission denied.", new Object[0]);
        return null;
    }

    /* renamed from: loadInBackground  reason: collision with other method in class */
    public Object m117loadInBackground() {
        if (PermissionsUtil.hasContactsReadPermissions(getContext())) {
            return super.loadInBackground();
        }
        LogUtil.m9i("DirectoriesCursorLoader.loadInBackground", "Contacts permission denied.", new Object[0]);
        return null;
    }
}
