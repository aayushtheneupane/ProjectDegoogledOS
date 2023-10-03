package com.android.messaging.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import com.android.messaging.datamodel.C0946g;
import com.android.messaging.sms.C1027w;
import com.android.p032ex.chips.C0699ra;
import p026b.p027a.p030b.p031a.C0632a;

public class ContactUtil {
    private ContactUtil() {
    }

    /* renamed from: Kj */
    public static boolean m3525Kj() {
        return C1464na.m3750Ha("android.permission.READ_CONTACTS");
    }

    /* renamed from: a */
    private static C0946g m3529a(Context context, Uri uri, String str, long j) {
        if (!m3525Kj()) {
            return C0946g.m2098Wd();
        }
        return new C0946g(context, m3526a(uri, str, j), C1475t.f2341Wu, (String) null, (String[]) null, "sort_key");
    }

    /* renamed from: b */
    private static C0946g m3531b(Context context, Uri uri, String str, long j) {
        if (!m3525Kj()) {
            return C0946g.m2098Wd();
        }
        return new C0946g(context, m3526a(uri, str, j), C1475t.f2341Wu, (String) null, (String[]) null, "sort_key");
    }

    /* renamed from: c */
    public static C0699ra m3532c(Cursor cursor, boolean z) {
        long j = cursor.getLong(0);
        return m3527a(cursor.getString(1), 40, cursor.getString(3), cursor.getInt(4), cursor.getString(5), j, cursor.getString(6), j, cursor.getString(2), z);
    }

    public static C0946g filterEmails(Context context, String str) {
        return m3529a(context, ContactsContract.CommonDataKinds.Email.CONTENT_FILTER_URI, str, 0);
    }

    public static C0946g filterPhones(Context context, String str) {
        return m3531b(context, ContactsContract.CommonDataKinds.Phone.CONTENT_FILTER_URI, str, 0);
    }

    public static C0946g getPhones(Context context) {
        if (!m3525Kj()) {
            return C0946g.m2098Wd();
        }
        return new C0946g(context, ContactsContract.CommonDataKinds.Phone.CONTENT_URI.buildUpon().appendQueryParameter("directory", String.valueOf(0)).appendQueryParameter("android.provider.extra.ADDRESS_BOOK_INDEX", "true").build(), C1475t.f2341Wu, (String) null, (String[]) null, "sort_key");
    }

    public static C0946g getSelf(Context context) {
        if (!m3525Kj()) {
            return C0946g.m2098Wd();
        }
        return new C0946g(context, ContactsContract.Profile.CONTENT_URI, C1477u.f2342Wu, (String) null, (String[]) null, (String) null);
    }

    public static boolean isEnterpriseContactId(long j) {
        return C1464na.m3758Yj() && ContactsContract.Contacts.isEnterpriseContactId(j);
    }

    /* renamed from: j */
    public static C0946g m3533j(Context context, String str) {
        if (str != null && str.contains("@")) {
            return filterEmails(context, str);
        }
        return filterPhones(context, str);
    }

    /* renamed from: k */
    public static C0946g m3534k(Context context, String str) {
        if (str != null && str.contains("@")) {
            return m3535l(context, str);
        }
        return m3536m(context, str);
    }

    /* renamed from: l */
    public static C0946g m3535l(Context context, String str) {
        return m3529a(context, ContactsContract.CommonDataKinds.Email.ENTERPRISE_CONTENT_FILTER_URI, str, 1000000000);
    }

    public static C0946g lookupEmail(Context context, String str) {
        Uri uri;
        if (!m3525Kj()) {
            return C0946g.m2098Wd();
        }
        if (C1464na.m3760_j()) {
            uri = ContactsContract.CommonDataKinds.Email.ENTERPRISE_CONTENT_LOOKUP_URI;
        } else {
            uri = ContactsContract.CommonDataKinds.Email.CONTENT_LOOKUP_URI;
        }
        return new C0946g(context, uri.buildUpon().appendPath(str).appendQueryParameter("directory", String.valueOf(0)).build(), C1469q.f2336Wu, (String) null, (String[]) null, "sort_key");
    }

    public static C0946g lookupPhone(Context context, String str) {
        Uri uri;
        if (!m3525Kj()) {
            return C0946g.m2098Wd();
        }
        if (C1464na.m3760_j()) {
            uri = ContactsContract.PhoneLookup.ENTERPRISE_CONTENT_FILTER_URI;
        } else {
            uri = ContactsContract.PhoneLookup.CONTENT_FILTER_URI;
        }
        return new C0946g(context, uri.buildUpon().appendPath(str).build(), C1473s.f2338Wu, (String) null, (String[]) null, (String) null);
    }

    /* renamed from: m */
    public static C0946g m3536m(Context context, String str) {
        return m3531b(context, ContactsContract.CommonDataKinds.Phone.ENTERPRISE_CONTENT_FILTER_URI, str, 1000000000);
    }

    /* renamed from: n */
    public static C0946g m3537n(Context context, String str) {
        if (C1027w.isEmailAddress(str)) {
            return lookupEmail(context, str);
        }
        return lookupPhone(context, str);
    }

    /* renamed from: a */
    private static C0946g m3528a(Context context, long j, boolean z) {
        if (!m3525Kj()) {
            return C0946g.m2098Wd();
        }
        Uri build = ContactsContract.Contacts.CONTENT_URI.buildUpon().appendPath(String.valueOf(j)).appendPath("data").build();
        String str = "mimetype=?";
        String[] strArr = {"vnd.android.cursor.item/name"};
        if (z) {
            str = C0632a.m1025k(str, " AND display_name=data1");
        }
        return new C0946g(context, build, C1479v.f2346Wu, str, strArr, (String) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x002a  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m3530a(android.content.Context r2, long r3) {
        /*
            boolean r0 = isEnterpriseContactId(r3)
            r1 = 0
            if (r0 == 0) goto L_0x0008
            return r1
        L_0x0008:
            r0 = 1
            com.android.messaging.datamodel.g r2 = m3528a((android.content.Context) r2, (long) r3, (boolean) r0)     // Catch:{ all -> 0x0026 }
            android.database.Cursor r2 = r2.mo6584Xd()     // Catch:{ all -> 0x0026 }
            if (r2 == 0) goto L_0x0020
            boolean r3 = r2.moveToFirst()     // Catch:{ all -> 0x001e }
            if (r3 == 0) goto L_0x0020
            java.lang.String r1 = r2.getString(r0)     // Catch:{ all -> 0x001e }
            goto L_0x0020
        L_0x001e:
            r3 = move-exception
            goto L_0x0028
        L_0x0020:
            if (r2 == 0) goto L_0x0025
            r2.close()
        L_0x0025:
            return r1
        L_0x0026:
            r3 = move-exception
            r2 = r1
        L_0x0028:
            if (r2 == 0) goto L_0x002d
            r2.close()
        L_0x002d:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.util.ContactUtil.m3530a(android.content.Context, long):java.lang.String");
    }

    /* renamed from: a */
    public static C0699ra m3527a(String str, int i, String str2, int i2, String str3, long j, String str4, long j2, String str5, boolean z) {
        if (z) {
            return C0699ra.m1082b(str, i, str2, i2, str3, j, (Long) null, j2, str5, true, str4);
        }
        return C0699ra.m1080a(str, i, str2, i2, str3, j, (Long) null, j2, str5, true, str4);
    }

    /* renamed from: a */
    private static Uri m3526a(Uri uri, String str, long j) {
        return uri.buildUpon().appendPath(str).appendQueryParameter("directory", String.valueOf(j)).build();
    }
}
