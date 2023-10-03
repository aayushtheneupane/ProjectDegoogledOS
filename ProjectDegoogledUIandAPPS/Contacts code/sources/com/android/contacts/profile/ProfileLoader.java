package com.android.contacts.profile;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.text.TextUtils;
import com.android.contacts.R;
import com.android.contacts.preference.ContactsPreferences;

public final class ProfileLoader extends CursorLoader {

    public static final class ProfileQuery {
        public static final String[] PROFILE_PROJECTION_ALTERNATIVE = {"_id", "display_name_alt", "is_user_profile", "photo_id", "photo_thumb_uri", "display_name_source"};
        public static final String[] PROFILE_PROJECTION_PRIMARY = {"_id", "display_name", "is_user_profile", "photo_id", "photo_thumb_uri", "display_name_source"};
    }

    public static String[] getProjection(Context context) {
        if (new ContactsPreferences(context).getDisplayOrder() == 1) {
            return ProfileQuery.PROFILE_PROJECTION_PRIMARY;
        }
        return ProfileQuery.PROFILE_PROJECTION_ALTERNATIVE;
    }

    public static ProfileItem getProfileItem(Context context, Cursor cursor) {
        boolean z;
        int i;
        String str;
        long j;
        long j2;
        Cursor cursor2 = cursor;
        String str2 = null;
        if (cursor2 == null || !cursor.moveToFirst()) {
            str = null;
            j2 = -1;
            j = 0;
            i = 0;
            z = false;
        } else {
            boolean z2 = cursor2.getInt(2) == 1;
            String string = cursor2.getString(1);
            long j3 = cursor2.getLong(0);
            long j4 = cursor2.getLong(3);
            String string2 = cursor2.getString(4);
            i = cursor2.getInt(5);
            z = z2;
            str = string2;
            str2 = string;
            j2 = j3;
            j = j4;
        }
        return new ProfileItem((!z || !TextUtils.isEmpty(str2)) ? str2 : context.getResources().getString(R.string.missing_name), j2, j, str, i, z);
    }

    public ProfileLoader(Context context, String[] strArr) {
        super(context, ContactsContract.Profile.CONTENT_URI, strArr, (String) null, (String[]) null, (String) null);
    }

    public Cursor loadInBackground() {
        try {
            return super.loadInBackground();
        } catch (RuntimeException unused) {
            return null;
        }
    }
}
