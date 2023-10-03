package com.android.dialer.contactsfragment;

import android.content.Context;
import android.content.CursorLoader;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;
import com.android.dialer.contacts.ContactsComponent;

final class ContactsCursorLoader extends CursorLoader {
    public static final String[] CONTACTS_PROJECTION_DISPLAY_NAME_ALTERNATIVE = {"_id", "display_name_alt", "photo_id", "photo_thumb_uri", "lookup"};
    public static final String[] CONTACTS_PROJECTION_DISPLAY_NAME_PRIMARY = {"_id", "display_name", "photo_id", "photo_thumb_uri", "lookup"};

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    ContactsCursorLoader(android.content.Context r9, boolean r10) {
        /*
            r8 = this;
            java.lang.String r0 = ""
            android.net.Uri r3 = buildUri(r0)
            java.lang.String[] r4 = getProjection(r9)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String[] r1 = getProjection(r9)
            r2 = 1
            r1 = r1[r2]
            java.lang.String r5 = " IS NOT NULL"
            java.lang.String r0 = com.android.tools.p006r8.GeneratedOutlineSupport.outline12(r0, r1, r5)
            if (r10 == 0) goto L_0x0026
            java.lang.String r10 = " AND has_phone_number=1"
            java.lang.String r10 = com.android.tools.p006r8.GeneratedOutlineSupport.outline8(r0, r10)
            r5 = r10
            goto L_0x0027
        L_0x0026:
            r5 = r0
        L_0x0027:
            r6 = 0
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            com.android.dialer.contacts.ContactsComponent r0 = com.android.dialer.contacts.ContactsComponent.get(r9)
            com.android.dialer.contacts.displaypreference.ContactDisplayPreferences r0 = r0.contactDisplayPreferences()
            com.android.dialer.contacts.displaypreference.ContactDisplayPreferences$SortOrder r0 = r0.getSortOrder()
            int r0 = r0.ordinal()
            if (r0 == 0) goto L_0x004c
            if (r0 != r2) goto L_0x0044
            java.lang.String r0 = "sort_key_alt"
            goto L_0x004e
        L_0x0044:
            java.lang.AssertionError r8 = new java.lang.AssertionError
            java.lang.String r9 = "exhaustive switch"
            r8.<init>(r9)
            throw r8
        L_0x004c:
            java.lang.String r0 = "sort_key"
        L_0x004e:
            java.lang.String r1 = " ASC"
            java.lang.String r7 = com.android.tools.p006r8.GeneratedOutlineSupport.outline12(r10, r0, r1)
            r1 = r8
            r2 = r9
            r1.<init>(r2, r3, r4, r5, r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.contactsfragment.ContactsCursorLoader.<init>(android.content.Context, boolean):void");
    }

    private static Uri buildUri(String str) {
        Uri.Builder builder;
        if (TextUtils.isEmpty(str)) {
            builder = ContactsContract.Contacts.CONTENT_URI.buildUpon();
        } else {
            builder = ContactsContract.Contacts.CONTENT_FILTER_URI.buildUpon().appendPath(str);
        }
        return builder.appendQueryParameter("android.provider.extra.ADDRESS_BOOK_INDEX", "true").build();
    }

    private static String[] getProjection(Context context) {
        int ordinal = ContactsComponent.get(context).contactDisplayPreferences().getDisplayOrder().ordinal();
        if (ordinal == 0) {
            return CONTACTS_PROJECTION_DISPLAY_NAME_PRIMARY;
        }
        if (ordinal == 1) {
            return CONTACTS_PROJECTION_DISPLAY_NAME_ALTERNATIVE;
        }
        throw new AssertionError("exhaustive switch");
    }

    public void setQuery(String str) {
        setUri(buildUri(str));
    }
}
