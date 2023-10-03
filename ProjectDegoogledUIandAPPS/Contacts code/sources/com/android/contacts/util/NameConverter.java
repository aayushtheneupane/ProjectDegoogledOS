package com.android.contacts.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;

public class NameConverter {
    public static final String[] STRUCTURED_NAME_FIELDS = {"data4", "data2", "data5", "data3", "data6"};

    public static String structuredNameToDisplayName(Context context, ContentValues contentValues) {
        Uri.Builder appendPath = ContactsContract.AUTHORITY_URI.buildUpon().appendPath("complete_name");
        for (String str : STRUCTURED_NAME_FIELDS) {
            if (contentValues.containsKey(str)) {
                appendQueryParameter(appendPath, str, contentValues.getAsString(str));
            }
        }
        return fetchDisplayName(context, appendPath.build());
    }

    private static String fetchDisplayName(Context context, Uri uri) {
        Cursor query = context.getContentResolver().query(uri, new String[]{"data1"}, (String) null, (String[]) null, (String) null);
        String str = null;
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    str = query.getString(0);
                }
            } finally {
                query.close();
            }
        }
        return str;
    }

    private static void appendQueryParameter(Uri.Builder builder, String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            builder.appendQueryParameter(str, str2);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.android.contacts.model.dataitem.StructuredNameDataItem parsePhoneticName(java.lang.String r7, com.android.contacts.model.dataitem.StructuredNameDataItem r8) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r7)
            r1 = 0
            if (r0 != 0) goto L_0x002e
            r0 = 3
            java.lang.String r2 = " "
            java.lang.String[] r7 = r7.split(r2, r0)
            int r2 = r7.length
            r3 = 0
            r4 = 1
            if (r2 == r4) goto L_0x0028
            r5 = 2
            if (r2 == r5) goto L_0x0020
            if (r2 == r0) goto L_0x0019
            goto L_0x002e
        L_0x0019:
            r1 = r7[r3]
            r0 = r7[r4]
            r7 = r7[r5]
            goto L_0x0030
        L_0x0020:
            r0 = r7[r3]
            r7 = r7[r4]
            r6 = r1
            r1 = r0
            r0 = r6
            goto L_0x0030
        L_0x0028:
            r7 = r7[r3]
            r0 = r1
            r1 = r7
            r7 = r0
            goto L_0x0030
        L_0x002e:
            r7 = r1
            r0 = r7
        L_0x0030:
            if (r8 != 0) goto L_0x0037
            com.android.contacts.model.dataitem.StructuredNameDataItem r8 = new com.android.contacts.model.dataitem.StructuredNameDataItem
            r8.<init>()
        L_0x0037:
            r8.setPhoneticFamilyName(r1)
            r8.setPhoneticMiddleName(r0)
            r8.setPhoneticGivenName(r7)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.util.NameConverter.parsePhoneticName(java.lang.String, com.android.contacts.model.dataitem.StructuredNameDataItem):com.android.contacts.model.dataitem.StructuredNameDataItem");
    }

    public static String buildPhoneticName(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            sb.append(str.trim());
            sb.append(' ');
        }
        if (!TextUtils.isEmpty(str2)) {
            sb.append(str2.trim());
            sb.append(' ');
        }
        if (!TextUtils.isEmpty(str3)) {
            sb.append(str3.trim());
            sb.append(' ');
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
